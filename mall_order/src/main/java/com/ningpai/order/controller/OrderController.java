/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.order.controller;

import com.ningpai.common.safe.CSRFTokenManager;
import com.ningpai.common.util.DateUtil;
import com.ningpai.customer.bean.CustomerAddress;
import com.ningpai.goods.bean.WareHouse;
import com.ningpai.goods.service.WareHouseService;
import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.order.bean.Order;
import com.ningpai.order.bean.OrderExpress;
import com.ningpai.order.service.OrderCouponService;
import com.ningpai.order.service.OrderLogService;
import com.ningpai.order.service.OrderService;
import com.ningpai.other.util.CustomerConstantStr;
import com.ningpai.salesman.bean.Salesman;
import com.ningpai.salesman.service.SalesmanService;
import com.ningpai.system.service.ILogisticsCompanyBiz;
import com.ningpai.util.*;
import net.sf.json.JSONArray;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author NINGPAI-LIH 订单总控制器 2014-04-09
 */
@Controller
public class OrderController {

    /**
     * 日志
     */
    public static final MyLogger LOGGER = new MyLogger(OrderController.class);

    private static final String STATUS = "status";
    private static final String ORDER = "order";
    private static final String PAGEBEAN = "pageBean";
    private static final String BEGINTIME = "beginTime";
    private static final String ENDTIME = "endTime";
    private static final String BEGINPRICE = "beginPrice";
    private static final String ENDPRICE = "endPrice";
    private static final String DATE = "yyyy-MM-dd";

    /**
     * 订单
     */
    private OrderService orderService;
    /**
     * 订单优惠劵
     */
    private OrderCouponService orderCouponService;

    /**
     * 订单操作记录
     */
    private OrderLogService orderLogService;
    @Resource(name = "WareHouseService")
    private WareHouseService wareHouseService;

    @Resource(name = "logisticsCompanyBizImpl")
    private ILogisticsCompanyBiz logisticsCompanyBiz;

    @Resource(name = "salesmanService")
    private SalesmanService salesmanService;

    /**
     * 修改订单价格
     *
     * @param orderId    要修改的订单id
     * @param orderCodex 订单编号
     * @param price      要减去的订单价格
     * @param request
     * @return
     */
    @RequestMapping("/modifyorderprice")
    public ModelAndView modifyOrderPrice(Long orderId, String orderCodex, Double price, HttpServletRequest request, String reason) {
        // 添加操作 0:修改价格
        orderLogService.insertSelective(reason, orderId, request.getSession().getAttribute(CustomerConstantStr.NAME).toString(), "0");
        // 修改订单价格
        orderService.modifyOrderPrice(BigDecimal.valueOf(price), orderId);
        String operaCode = "修改订单价格";
        String operaContent = request.getSession().getAttribute(CustomerConstantStr.OPERAPATH) + "减去订单" + orderCodex + "的价格：" + price;
        OperaLogUtil.addOperaLog(request, request.getSession().getAttribute(CustomerConstantStr.NAME).toString(), operaCode, operaContent);
        return new ModelAndView(new RedirectView(OrderConstants.INITORDER));
    }

    /**
     * 新订单列表页面分页
     *
     * @param order
     * @param status
     * @param pageBean
     * @param request
     * @return
     */

    @ResponseBody
    @RequestMapping("/newajaxgetpagefoot")
    public PageBean newajaxgetpagefoot(Order order, String status, PageBean pageBean, HttpServletRequest request) {
        String statusNew = status;
        order.setBusinessId(0L);

        if (StringUtils.isEmpty(statusNew)) {
            statusNew = "1";
        }
        // String token =
        // request.getSession().getAttribute(CSRFTokenManager.CSRF_TOKEN_FOR_SESSION_ATTR_NAME).toString();
        return orderService.newajaxgetpagefoot(statusNew, order, pageBean);
    }


    /**
     * 根据商品id查询个数 boss后台删除商品用
     * 0:不允许删除商品;1:允许
     *
     * @return
     */
    @RequestMapping("/checkDeleteByGoodsIds")
    @ResponseBody
    public String checkDeleteByGoodsIds(HttpServletRequest request) {

        String[] tags = request.getParameterValues("goodsIds[]");
        Long[] goodsIds = null;
        if (tags.length > 0 && tags != null) {
            goodsIds = new Long[tags.length];
            for (int i = 0; i < tags.length; i++) {
                goodsIds[i] = Long.valueOf(tags[i]);
            }
        }
        if (orderService.queryOrderCountBygoodsIds(goodsIds) > 0) {
            return "0";
        } else {
            return "1";
        }
    }


    /**
     * 根据货品id查询个数 boss后台删除货品用
     * 0:不允许删除货品;1:允许
     *
     * @return
     */
    @RequestMapping("/checkDeleteByGoodsInfoIds")
    @ResponseBody
    public String checkDeleteByGoodsInfoIds(HttpServletRequest request) {
        String[] tags = request.getParameterValues("goodsInfoIds[]");
        Long[] goodsInfoIds = null;
        if (tags.length > 0 && tags != null) {
            goodsInfoIds = new Long[tags.length];
            for (int i = 0; i < tags.length; i++) {
                goodsInfoIds[i] = Long.valueOf(tags[i]);
            }
        }
        if (orderService.queryOrderCountBygoodsInfoIds(goodsInfoIds) > 0) {
            return "0";
        } else {
            return "1";
        }
    }


    /**
     * 查询订单列表
     *
     * @param order    订单信息
     * @param pageBean
     * @return ModelAndView
     */
    @RequestMapping("/orderlist")
    public ModelAndView orderList(Order order, String status, PageBean pageBean, HttpServletRequest request) {
        String statusNew = status;
        MenuSession.sessionMenu(request);
        order.setBusinessId(0L);
        // URL
        pageBean.setUrl(OrderConstants.INITORDER);
        // 所有仓库集合
        List<WareHouse> ware = wareHouseService.findWares();
        Long wId = order.getWareId();
        if (StringUtils.isEmpty(statusNew)) {
            statusNew = "1";
        }
        // 获得token
        String token = request.getSession().getAttribute(CSRFTokenManager.CSRF_TOKEN_FOR_SESSION_ATTR_NAME).toString();
        //查询所有可以关联的业务员
        List<Salesman> salesmans = salesmanService.seletAllEnableSala();
        return new ModelAndView(OrderConstants.ORDERLIST, "map", orderService.newsearchOrderList(statusNew, order, pageBean)).addObject(ORDER, order).addObject(STATUS, statusNew)
                .addObject("sx", token).addObject("ware", ware).addObject("getWareId", wId).addObject("enableSalaList", salesmans);
    }

    /**
     * 查询第三方订单列表
     *
     * @param order    订单信息
     * @param pageBean
     * @return ModelAndView
     */
    @RequestMapping("/orderlististhird")
    public ModelAndView orderLististhird(Order order, String status, PageBean pageBean, HttpServletRequest request) {
        String statusNew = status;
        // 默认全部
        if (StringUtils.isEmpty(statusNew)) {
            statusNew = "1";
        }
        // url
        pageBean.setUrl(OrderConstants.ORDERLISTISTHIRD);
        // 获得token
        String token = request.getSession().getAttribute(CSRFTokenManager.CSRF_TOKEN_FOR_SESSION_ATTR_NAME).toString();
        // 第三方订单列表
        return new ModelAndView(OrderConstants.ORDERLISTISTHIRD, "map", orderService.newsearchThirdOrderList(statusNew, order, pageBean)).addObject(ORDER, order)
                .addObject("sx", token).addObject(STATUS, statusNew);
    }

    /**
     * 跳转到添加订单页面
     *
     * @return ModelAndView
     */
    @RequestMapping("/toaddorder")
    public ModelAndView toaddorder() {
        // 得到所有启用的快递公司

        return new ModelAndView("jsp/order/toaddorder").addObject("companyList", logisticsCompanyBiz.queryAllLogisticsCompany());

    }

    /**
     * ajax 得到订单支付信息
     *
     * @return ModelAndView
     */
    @ResponseBody
    @RequestMapping("/ajaxGetorderDetail")
    public Map<String, Object> ajaxGetorderDetail(HttpServletRequest request, Long[] goodsIdP, String customerName) {

        return orderService.ajaxGetorderDetail(request, null, null, customerName);
    }

    /**
     * ajax 保存后台添加的订单信息
     *
     * @param goodsAllPrice
     * @param freightPrice
     * @param companyInfo
     * @param customerRemark
     * @param goodsIdP
     * @param goodsNum
     * @param distinctId
     * @param payType
     * @param invoiceTitle
     * @param invoiceType
     * @param invoiceContent
     * @param customerAddress
     * @return
     */
    @ResponseBody
    @RequestMapping("/ajaxsaveOrder")
    public int ajaxsaveOrder(BigDecimal goodsAllPrice, BigDecimal freightPrice, String companyInfo, String customerRemark, Long[] goodsIdP, Long[] goodsNum, Long distinctId,
                             Long payType, String invoiceTitle, String invoiceType, String invoiceContent, CustomerAddress customerAddress,String isvip) {

        return orderService.saveAddOrder(goodsAllPrice, freightPrice, companyInfo, customerRemark, goodsIdP, goodsNum, distinctId, payType, invoiceTitle, invoiceType,
                invoiceContent, customerAddress,isvip);
    }

    /**
     * 查询订单明细
     *
     * @param orderId 订单id
     * @return ModelAndView
     */
    @RequestMapping("/orderdetail")
    public ModelAndView orderDetail(Long orderId, HttpServletRequest request) {
        return new ModelAndView(OrderConstants.ORDERDETAIL, ORDER, orderService.orderDetail(orderId)).addObject("relations", orderService.queryContainerRalation(orderId))
                .addObject("orderLogs", orderLogService.selectOrderLogByOrderId(orderId));
    }

    /**
     * ajax查询订单明细
     *
     * @param orderId 订单id
     * @return ModelAndView
     */
    @RequestMapping("/orderdetailajax")
    @ResponseBody
    public Map<String, Object> orderDetailAjax(Long orderId, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        // 查询单个订单详细
       /* map.put(ORDER, orderService.orderDetail(orderId));
        map.put("relations", orderService.queryContainerRalation(orderId));
        map.put("orderLogs", orderLogService.selectOrderLogByOrderId(orderId));*/
        map.put(ORDER, orderService.orderSimpleDetail(orderId));
        map.put("relations", orderService.querySimpleContainerRalation(orderId));
        map.put("orderLogs", orderLogService.selectOrderLogByOrderId(orderId));
        return map;
    }
    /**
     * ajax查询订单操作日志
     *
     * @param orderId 订单id
     * @return ModelAndView
     */
    @RequestMapping("/orderLogAjax")
    @ResponseBody
    public Map<String, Object> orderLogAjax(Long orderId, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("orderLogs", orderLogService.selectOrderLogByOrderId(orderId));
        return map;
    }

    /**
     * 查询物流信息
     *
     * @param orderId
     * @return OrderExpress
     */
    @RequestMapping("/expressdetail")
    @ResponseBody
    public OrderExpress expressDetail(Long orderId) {
        return orderService.expressDetail(orderId);
    }

    /**
     * 查看已发货列表
     *
     * @return
     * @throws java.text.ParseException
     */
    @RequestMapping("/queryysendgoodsorderlist")
    public ModelAndView queryYSendGoodsOrderList(HttpServletRequest request, PageBean pageBean, String status, Order order) throws ParseException {
        String statusNew = status;
        pageBean.setUrl(OrderConstants.QUERYYSENDGOODSORDERLIST);
        List<String> list = null;
        statusNew = "0";
        return new ModelAndView(OrderConstants.PRINTOVERLIST).addObject(PAGEBEAN, orderService.queryYSimpleOrder(request, order, pageBean, list)).addObject(STATUS, statusNew)
                .addObject(ORDER, order).addObject(BEGINTIME, request.getParameter(BEGINTIME)).addObject(ENDTIME, request.getParameter(ENDTIME))
                .addObject(BEGINPRICE, request.getParameter(BEGINPRICE)).addObject(ENDPRICE, request.getParameter(ENDPRICE))
                .addObject("sx", request.getSession().getAttribute(CSRFTokenManager.CSRF_TOKEN_FOR_SESSION_ATTR_NAME).toString());
    }

    /**
     * 查看已装箱列表
     *
     * @return
     * @throws java.text.ParseException
     */
    @RequestMapping("/queryydeorderlist")
    public ModelAndView queryYDeOrderList(HttpServletRequest request, PageBean pageBean, String status, Order order) throws ParseException {
        String statusNew = status;
        pageBean.setUrl(OrderConstants.QUERYYDEORDERLIST);
        List<String> list = new ArrayList<String>();
        list.add("2");
        list.add("3");
        statusNew = "1";
        return new ModelAndView(OrderConstants.PRINTOVERLIST).addObject(PAGEBEAN, orderService.queryYSimpleOrder(request, order, pageBean, list)).addObject(STATUS, statusNew)
                .addObject(ORDER, order).addObject(BEGINTIME, request.getParameter(BEGINTIME)).addObject(ENDTIME, request.getParameter(ENDTIME))
                .addObject(BEGINPRICE, request.getParameter(BEGINPRICE)).addObject(ENDPRICE, request.getParameter(ENDPRICE))
                .addObject("sx", request.getSession().getAttribute(CSRFTokenManager.CSRF_TOKEN_FOR_SESSION_ATTR_NAME).toString());
    }

    /**
     * 查看已拣货列表
     *
     * @return
     * @throws java.text.ParseException
     */
    @RequestMapping("/queryyorderlist")
    public ModelAndView queryYOrderList(HttpServletRequest request, PageBean pageBean, String status, Order order) throws ParseException {
        String statusNew = status;
        pageBean.setUrl(OrderConstants.QUERYYORDERLIST);
        List<String> list = new ArrayList<String>();
        list.add("1");
        list.add("2");
        list.add("3");
        statusNew = "2";
        return new ModelAndView(OrderConstants.PRINTOVERLIST).addObject(PAGEBEAN, orderService.queryYSimpleOrder(request, order, pageBean, list)).addObject(STATUS, statusNew)
                .addObject(ORDER, order).addObject(BEGINTIME, request.getParameter(BEGINTIME)).addObject(ENDTIME, request.getParameter(ENDTIME))
                .addObject(BEGINPRICE, request.getParameter(BEGINPRICE)).addObject(ENDPRICE, request.getParameter(ENDPRICE))
                .addObject("sx", request.getSession().getAttribute(CSRFTokenManager.CSRF_TOKEN_FOR_SESSION_ATTR_NAME).toString()).addObject("orderCargoStatus");
    }

    /**
     * 查看销售量走势图
     *
     * @return
     */
    @RequestMapping("/saleCount")
    public ModelAndView saleCount(String startTime, String endTime, HttpServletRequest request) {
        String startTimeNew = startTime;
        String endTimeNew = endTime;
        // 格式化日期 如果时间不存在 初始化当前日期的前7天
        if (startTimeNew == null || endTimeNew == null || "".equals(startTimeNew) || "".equals(endTimeNew)) {
            endTimeNew = UtilDate.todayFormat(new Date());
            startTimeNew = UtilDate.addDay(endTimeNew, -6);
        }
        String sttime = startTimeNew;
        String entime = endTimeNew;
        List<String> payTime = DateUtil.getAllDateBetween2Date(startTimeNew, endTimeNew);
        List<Order> orderList = orderService.querySaleCountByDay(startTimeNew, endTimeNew);
        List<Long> count = new ArrayList<Long>();
        for (String time : payTime) {
            boolean flag = false;
            for (Order order : orderList) {
                if (time.equals(DateUtil.dateToString(order.getPayTime(), DATE))) {
                    flag = true;
                    count.add(order.getDayCount());
                }
            }
            if (!flag) {
                count.add(0L);
            }
        }
        JSONArray times = JSONArray.fromObject(payTime);
        JSONArray counts = JSONArray.fromObject(count);
        return new ModelAndView(OrderConstants.SALECOUNT).addObject("orderList", orderService.querySaleCountByDay(startTimeNew, endTimeNew)).addObject("counts", counts.toString())
                .addObject("times", times.toString()).addObject("startTime", sttime).addObject(ENDTIME, entime);
    }

    /**
     * 查看销售额走势图
     *
     * @return
     */
    @RequestMapping("/saleMoney")
    public ModelAndView saleMoney(String startTime, String endTime, HttpServletRequest request) {
        String startTimeNew = startTime;
        String endTimeNew = endTime;
        // 格式化日期 如果时间不存在 初始化当前日期的前7天
        if (startTimeNew == null || endTimeNew == null || "".equals(startTimeNew) || "".equals(endTimeNew)) {
            endTimeNew = UtilDate.todayFormat(new Date());
            startTimeNew = UtilDate.addDay(endTimeNew, -6);
        }
        String sttime = startTimeNew;
        String entime = endTimeNew;
        List<String> payTime = DateUtil.getAllDateBetween2Date(startTimeNew, endTimeNew);
        List<Order> orderList = orderService.querySaleMoneyByDay(startTimeNew, endTimeNew);
        List<BigDecimal> total = new ArrayList<BigDecimal>();
        for (String time : payTime) {
            boolean flag = false;
            for (Order order : orderList) {
                if (time.equals(DateUtil.dateToString(order.getPayTime(), DATE))) {
                    flag = true;
                    total.add(order.getDayMoney());
                }
            }
            if (!flag) {
                total.add(new BigDecimal(0));
            }
        }
        JSONArray times = JSONArray.fromObject(payTime);
        JSONArray totals = JSONArray.fromObject(total);
        return new ModelAndView(OrderConstants.SALEMONEY).addObject("orderList", orderService.querySaleCountByDay(startTimeNew, endTimeNew)).addObject("totals", totals.toString())
                .addObject("times", times.toString()).addObject("startTime", sttime).addObject(ENDTIME, entime);
    }

    /**
     * 首页统计查询
     *
     * @return map
     * @auth lih
     */
    @RequestMapping("/querystatistics")
    @ResponseBody
    public Map<String, Object> queryStatistics() {
        return orderService.queryStatistics();
    }

    /**
     * 导出订单列表
     *
     * @author houyichang 2015/8/14
     */
    @RequestMapping("exportallorder")
    public void exportOrderList(HttpServletResponse response) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");
        // 第一步 创建一个webbook,对应一个excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步 在webbook中添加sheet，对应excel中的sheet
        HSSFSheet sheet = wb.createSheet("订单列表");
        // 第三步 在sheet中添加表头第0行，此处需要注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow((int) 0);

        // 第四步 创建单元格，并设置值表头，设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        // 创建一个居中格式
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 设置宽度
        /*sheet.setColumnWidth(0, 6000);
        sheet.setColumnWidth(1, 6000);
        sheet.setColumnWidth(2, 6000);
        sheet.setColumnWidth(3, 6000);
        sheet.setColumnWidth(4, 6000);
        sheet.setColumnWidth(5, 6000);
        sheet.setColumnWidth(6, 6000);
        */
        sheet.setColumnWidth(0, 9000);
        sheet.setColumnWidth(1, 6000);
        sheet.setColumnWidth(2, 6000);
        sheet.setColumnWidth(3, 6000);
        sheet.setColumnWidth(4, 6000);
        sheet.setColumnWidth(5, 6000);
        sheet.setColumnWidth(6, 6000);
        sheet.setColumnWidth(7, 6000);
        sheet.setColumnWidth(8, 6000);
        sheet.setColumnWidth(9, 6000);
        sheet.setColumnWidth(10, 6000);
        sheet.setColumnWidth(11, 6000);
        sheet.setColumnWidth(12, 6000);
        sheet.setColumnWidth(13, 6000);
        sheet.setColumnWidth(14, 6000);
        sheet.setColumnWidth(15, 6000);
        sheet.setColumnWidth(16, 6000);
        sheet.setColumnWidth(17, 6000);
        sheet.setColumnWidth(18, 6000);
        sheet.setColumnWidth(19, 6000);
        sheet.setColumnWidth(20, 6000);
        sheet.setColumnWidth(21, 6000);
        sheet.setColumnWidth(22, 6000);
        sheet.setColumnWidth(23, 6000);
        sheet.setColumnWidth(24, 6000);
        sheet.setColumnWidth(25, 6000);
        sheet.setColumnWidth(26, 6000);
        sheet.setColumnWidth(27, 6000);
        sheet.setColumnWidth(28, 6000);
        sheet.setColumnWidth(29, 6000);
        sheet.setColumnWidth(30, 6000);
        // 设置列头信息
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("订单编号");
        cell.setCellStyle(style);
        cell = row.createCell(1);
        cell.setCellValue("下单用户");
        cell.setCellStyle(style);
        cell = row.createCell(2);
        cell.setCellValue("下单时间");
        cell.setCellStyle(style);
        cell = row.createCell(3);
        cell.setCellValue("订单状态");
        cell.setCellStyle(style);
        cell = row.createCell(4);
        cell.setCellValue("订单总价");
        cell.setCellStyle(style);
        cell = row.createCell(5);
        cell.setCellValue("订单数量");
        cell.setCellStyle(style);
        cell = row.createCell(6);
        cell.setCellValue("付款时间");
        cell.setCellStyle(style);
        cell = row.createCell(7);
        cell.setCellValue("支付方式");
        cell.setCellStyle(style);
        cell = row.createCell(8);
        cell.setCellValue("实付金额");
        cell.setCellStyle(style);
        cell = row.createCell(9);
        cell.setCellValue("发货时间");
        cell.setCellStyle(style);
        cell = row.createCell(10);
        cell.setCellValue("运费");
        cell.setCellStyle(style);
        cell = row.createCell(11);
        cell.setCellValue("快递公司");
        cell.setCellStyle(style);
        cell = row.createCell(12);
        cell.setCellValue("快递单号");
        cell.setCellStyle(style);
        cell = row.createCell(13);
        cell.setCellValue("收货人");
        cell.setCellStyle(style);
        cell = row.createCell(14);
        cell.setCellValue("联系电话");
        cell.setCellStyle(style);
        cell = row.createCell(15);
        cell.setCellValue("地址");
        cell.setCellStyle(style);
        cell = row.createCell(16);
        cell.setCellValue("发票类型");
        cell.setCellStyle(style);
        cell = row.createCell(17);
        cell.setCellValue("发票抬头");
        cell.setCellStyle(style);
        cell = row.createCell(18);
        cell.setCellValue("发票明细");
        cell.setCellStyle(style);
        cell = row.createCell(19);
        cell.setCellValue("完成时间");
        cell.setCellStyle(style);
        cell = row.createCell(20);
        cell.setCellValue("业务员");
        cell.setCellStyle(style);
        cell = row.createCell(21);
        cell.setCellValue("订单来源");
        cell.setCellStyle(style);
        cell = row.createCell(22);
        cell.setCellValue("商家");
        cell.setCellStyle(style);
        cell = row.createCell(23);
        cell.setCellValue("商品编号");
        cell.setCellStyle(style);
        cell = row.createCell(24);
        cell.setCellValue("商品名称");
        cell.setCellStyle(style);
        cell = row.createCell(25);
        cell.setCellValue("品牌");
        cell.setCellStyle(style);
        cell = row.createCell(26);
        cell.setCellValue("商品分类");
        cell.setCellStyle(style);
        cell = row.createCell(27);
        cell.setCellValue("商品原始价格");
        cell.setCellStyle(style);
        cell = row.createCell(28);
        cell.setCellValue("商品数量");
        cell.setCellStyle(style);
        cell = row.createCell(29);
        cell.setCellValue("商品交易价格");
        cell.setCellStyle(style);
        cell = row.createCell(30);
        cell.setCellValue("商品总价");
        cell.setCellStyle(style);
        cell = row.createCell(31);

        // 第五步 写入实际数据 实际应用中这些数据从数据库中取出
        List<Order> list = orderService.queryAllOrderList();
        int q = 0;
        int m = 0;
        if (null != list) {
            for (int i = 0; i < list.size(); i++) {

                row = sheet.createRow((int) (++q));
                /* 第六步，创建单元格，并设置值 */
                Order order = list.get(i);
                // 如果订单编号不为空
                // 就设置订单编号列
                if (null != order.getOrderCode()) {
                    row.createCell(0).setCellValue(order.getOrderCode());
                }
                if (null != order.getCustomerUsername()) {
                    if(order.getIsEnterprise()!=null&&"1".equals(order.getIsEnterprise())){
                        row.createCell(1).setCellValue(order.getCompanyName());
                    }else {
                        row.createCell(1).setCellValue(order.getCustomerNickname());
                    }
                }
               /* // 如果订单总价不为空
                // 社会订单总价列
                // 如果购买数量不为空
                // 就设置显示购买数量
                if (null != order.getOrderOldPrice()) {
                    if (null != order.getOrderGoodsList()) {
                        row.createCell(1).setCellValue(order.getOrderOldPrice() + "/" + order.getOrderGoodsList().size());
                    } else {
                        row.createCell(1).setCellValue(order.getOrderOldPrice() + "");
                    }
                }*/
                // 如果下单时间不为空
                // 设置显示下单时间
                if (null != order.getCreateTime()) {
                    row.createCell(2).setCellValue(new SimpleDateFormat(DATE).format(order.getCreateTime()));
                }
                // 如果订单状态不为空
                // 设置订单状态显示
                // 定义一个变量负责接送订单状态值，方便最后给列赋值
                String status = "";
                if (null != order.getOrderStatus()) {
                    // 如果状态为1 需要在进一步判断
                    if ("0".equals(order.getOrderStatus()) && null != order.getOrderLinePay()) {
                        // 如果订单付款方式不为空
                        // 设置显示订单状态
                        // 待发货
                        if ("0".equals(order.getOrderLinePay())) {
                            status = "待发货";
                        } else if ("1".equals(order.getOrderLinePay())) {
                            status = "待付款";
                        }
                    }
                    // 已付款未发货
                    if ("1".equals(order.getOrderStatus())) {
                        status = "已付款未发货";
                    }

                    // 已发货
                    if ("2".equals(order.getOrderStatus())) {
                        status = "已发货";
                    }

                    // 已完成
                    if ("3".equals(order.getOrderStatus())) {
                        status = "已完成";
                    }

                    // 已取消
                    if ("4".equals(order.getOrderStatus())) {
                        status = "已取消";
                    }

                    // 退单审核中
                    if ("14".equals(order.getOrderStatus())) {
                        status = "退单审核中";
                    }

                    // 同意退货
                    if ("8".equals(order.getOrderStatus())) {
                        status = "同意退货";
                    }

                    // 拒绝退货
                    if ("9".equals(order.getOrderStatus())) {
                        status = "拒绝退货";
                    }

                    // 待商家收货
                    if ("10".equals(order.getOrderStatus())) {
                        status = "待商家收货";
                    }

                    // 退单结束
                    if ("11".equals(order.getOrderStatus())) {
                        status = "退单结束";
                    }

                    // 退款审核中
                    if ("15".equals(order.getOrderStatus())) {
                        status = "退款审核中";
                    }

                    // 商家收货失败
                    if ("16".equals(order.getOrderStatus())) {
                        status = "商家收货失败";
                    }

                    // 已退款
                    if ("17".equals(order.getOrderStatus())) {
                        status = "已退款";
                    }

                    // 退款成功
                    if ("18".equals(order.getOrderStatus())) {
                        status = "退款成功";
                    }
                    row.createCell(3).setCellValue(status);
                }
                // 设置订单原始总金额
                if (null != order.getOrderOldPrice()) {
                    row.createCell(4).setCellValue(order.getOrderOldPrice() + "");
                }

                row.createCell(5).setCellValue(1);
                if (order.getPayTime() != null) {
                    row.createCell(6).setCellValue(df.format(order.getPayTime()));
                }
                if (order.getOrderLinePay() != null) {
                    row.createCell(7).setCellValue("1".equals(order.getOrderLinePay())?"在线支付":"货到付款");
                }
                if (null != order.getOrderPrice()) {
                    row.createCell(8).setCellValue(order.getOrderPrice() + "");
                }
                if (order.getGetGoodsTime() != null) {
                    row.createCell(9).setCellValue(df.format(order.getGetGoodsTime()));
                }
                if (order.getExpressPrice() != null) {
                    row.createCell(10).setCellValue(order.getExpressPrice().setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                }
                if(list.get(i).getOrderContainerRelations()!=null){
                    for (int k = 0; k < list.get(i).getOrderContainerRelations().size(); k++) {
                        m = q;
                        if (k > 0) {
                            row = sheet.createRow((int) (++m));
                        }
                        if (null != list.get(i).getOrderContainerRelations().get(k).getExpressName())
                            row.createCell(11).setCellValue(list.get(i).getOrderContainerRelations().get(k).getExpressName());

                        if (list.get(i).getOrderContainerRelations().get(k).getExpressNo()!=null) {
                            row.createCell(12).setCellValue(list.get(i).getOrderContainerRelations().get(k).getExpressNo());
                        }
                    }
                }
                // 如果收货人不为空
                // 设置显示收货人名称
                if (null != order.getShippingPerson()) {
                    // 如果收货人电话不为空
                    // 设置显示收货人电话信息
                    row.createCell(13).setCellValue(order.getShippingPerson());
                }
                if (null != order.getShippingMobile()) {
                    // 如果收货人电话不为空
                    // 设置显示收货人电话信息
                    row.createCell(14).setCellValue(order.getShippingMobile());

                }
                String shoppingAddress = "";
                if (order.getShippingProvince() != null && !"".equals(order.getShippingProvince())) {
                    shoppingAddress += order.getShippingProvince() + "省";
                }
                if (order.getShippingCity() != null && !"".equals(order.getShippingCity())) {
                    shoppingAddress += order.getShippingCity() + "市";
                }
                if (order.getShippingCounty() != null && !"".equals(order.getShippingCounty())) {
                    shoppingAddress += order.getShippingCounty() + "区（县）";
                }
                if (order.getShippingAddress() != null && !"".equals(order.getShippingAddress())) {
                    shoppingAddress += order.getShippingAddress();
                }
                if (shoppingAddress.length() > 0) {
                    row.createCell(15).setCellValue(shoppingAddress);
                }
                if (order.getInvoiceType() != null) {
                    row.createCell(16).setCellValue("0".equals(order.getInvoiceType())?"":"普通发票");
                }

                if (order.getInvoiceTitle() != null) {
                    row.createCell(17).setCellValue(order.getInvoiceTitle());
                }
                if (order.getInvoiceContent() != null) {
                    row.createCell(18).setCellValue(order.getInvoiceContent());
                }
                if (order.getGetGoodsTime() != null) {
                    row.createCell(19).setCellValue(df.format(order.getGetGoodsTime()));
                }

                if (order.getSalesmanName()!=null) {
                    row.createCell(20).setCellValue(order.getSalesmanName());
                }

                if (order.getOrderMType() != null) {
                    row.createCell(21).setCellValue("2".equals(order.getOrderMType()) ? "手机订单" : "PC订单");
                }

                if (true) {
                    row.createCell(22).setCellValue("BOSS");
                }

                for (int j = 0; j < list.get(i).getOrderGoodsList().size(); j++) {
                    if (j > 0) {
                        row = sheet.createRow((int) (++q));
                    }
                    // 如果商品名称不为空
                    // 设置显示商品名称
                    if (null != list.get(i).getOrderGoodsList().get(j).getGoodsInfoName()) {
                        row.createCell(24).setCellValue(list.get(i).getOrderGoodsList().get(j).getGoodsInfoName());
                    }
                    // 如果商品编号不为空
                    // 设置显示商品编号
                    if (null != list.get(i).getOrderGoodsList().get(j).getGoodsInfoItemNo()) {
                        row.createCell(23).setCellValue(list.get(i).getOrderGoodsList().get(j).getGoodsInfoItemNo());
                    }
                    if (list.get(i).getOrderGoodsList().get(j).getGoodsProductVo()!=null&&
                            list.get(i).getOrderGoodsList().get(j).getGoodsProductVo().getGoods()!=null
                            &&list.get(i).getOrderGoodsList().get(j).getGoodsProductVo().getGoods().getGoodsBrand()!=null&&
                            list.get(i).getOrderGoodsList().get(j).getGoodsProductVo().getGoods().getGoodsBrand().getBrandName()!=null) {
                        row.createCell(25).setCellValue(list.get(i).getOrderGoodsList().get(j).getGoodsProductVo().getGoods().getGoodsBrand().getBrandName());
                    }
                    if (list.get(i).getOrderGoodsList().get(j).getGoodsProductVo()!=null&&
                            list.get(i).getOrderGoodsList().get(j).getGoodsProductVo().getGoods()!=null
                            &&list.get(i).getOrderGoodsList().get(j).getGoodsProductVo().getGoods().getGoodsCate()!=null&&
                            list.get(i).getOrderGoodsList().get(j).getGoodsProductVo().getGoods().getGoodsCate().getCatName()!=null) {
                        row.createCell(26).setCellValue(list.get(i).getOrderGoodsList().get(j).getGoodsProductVo().getGoods().getGoodsCate().getCatName());
                    }
                    if (null != list.get(i).getOrderGoodsList().get(j).getGoodsInfoOldPrice()) {
                            row.createCell(27).setCellValue(list.get(i).getOrderGoodsList().get(j).getGoodsInfoOldPrice() + "");
                    }
                    if (list.get(i).getOrderGoodsList().get(j).getGoodsInfoNum()!=null) {
                        row.createCell(28).setCellValue(list.get(i).getOrderGoodsList().get(j).getGoodsInfoNum());
                    }
                    if (list.get(i).getOrderGoodsList().get(j).getGoodsInfoPrice()!=null) {
                        row.createCell(29).setCellValue(list.get(i).getOrderGoodsList().get(j)
                                .getGoodsInfoPrice().setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                    }
                    if (list.get(i).getOrderGoodsList().get(j).getGoodsInfoSumPrice()!=null) {
                        row.createCell(30).setCellValue(list.get(i).getOrderGoodsList().get(j)
                                .getGoodsInfoSumPrice().setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                    }
                    q = q>m?q:m;
                }
            }
        }

        // 第七步 将文件存储到指定位置
        String filename = String.valueOf(System.currentTimeMillis()).concat(".xls");
        // 设置下载时客户端Excel的名称
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename=" + filename);
        OutputStream ouputStream;
        try

        {
            ouputStream = response.getOutputStream();
            wb.write(ouputStream);
            ouputStream.flush();
            ouputStream.close();
        } catch (
                IOException e
                )

        {
            LOGGER.error("", e);
        }

    }

    public OrderService getOrderService() {
        return orderService;
    }

    @Resource(name = "OrderService")
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    public OrderCouponService getOrderCouponService() {
        return orderCouponService;
    }

    @Resource(name = "OrderCouponService")
    public void setOrderCouponService(OrderCouponService orderCouponService) {
        this.orderCouponService = orderCouponService;
    }

    public OrderLogService getOrderLogService() {
        return orderLogService;
    }

    @Resource(name = "OrderLogService")
    public void setOrderLogService(OrderLogService orderLogService) {
        this.orderLogService = orderLogService;
    }

}
