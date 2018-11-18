package com.ningpai.site.returns.controller;

import com.ningpai.common.util.ConstantUtil;
import com.ningpai.goods.bean.GoodsProduct;
import com.ningpai.goods.service.GoodsProductService;
import com.ningpai.goods.vo.GoodsProductDetailViewVo;
import com.ningpai.index.service.TopAndBottomService;
import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.order.bean.*;
import com.ningpai.order.service.BackOrderLogService;
import com.ningpai.order.service.BackOrderService;
import com.ningpai.order.service.OrderService;
import com.ningpai.site.customer.service.CustomerServiceInterface;
import com.ningpai.site.customer.vo.CustomerAllInfo;
import com.ningpai.site.customer.vo.CustomerConstantStr;
import com.ningpai.site.customer.vo.OrderInfoBean;
import com.ningpai.site.order.service.SiteOrderService;
import com.ningpai.site.returns.service.ReturnGoodsService;
import com.ningpai.system.bean.SystemsSet;
import com.ningpai.system.service.IsBackOrderService;
import com.ningpai.util.MyLogger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/***
 * 退货控制器
 * 
 * @author zhanghailong
 *
 */
@Controller()
public class ReturnGoodsController {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(ReturnGoodsController.class);

    private static final String LOGGERINFO1 = "】-->用户名：";
    private static final String ORDER = "order";
    private static final String BACKORDER = "backorder";

    // 注入service
    private ReturnGoodsService goodsService;

    // 获取头尾
    private TopAndBottomService topAndBottomService;

    // 订单
    private SiteOrderService siteOrderService;

    private BackOrderService backOrderService;

    private OrderService orderService;

    // 退单操作日志
    private BackOrderLogService backOrderLogService;

    private IsBackOrderService isbackOrderService;

    // spring 注解 会员service
    private CustomerServiceInterface customerServiceInterface;

    @Resource(name = "GoodsProductService")
    private GoodsProductService goodsProductService;

    /***
     * 
     * @param wlname
     *            物流名称
     * @param wlno
     *            物流单号
     * @param orderNo
     *            订单号
     * @return
     */
    @RequestMapping("/saveBackOrderGeneral")
    @ResponseBody
    public int saveBackOrderGeneral(HttpServletRequest request, String wlname, String wlno, String orderNo) {
        // 当前登录会员id
        Long customerId = (Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID);
        // 当前登录成功的会员信息
        CustomerAllInfo customerAllInfo = customerServiceInterface.selectByPrimaryKey(customerId);
        // 非空验证 订单号
        if (null != orderNo) {
            LOGGER.info("新增一条退货的物流信息");
            // 操作日志
            OperaLogUtil.addOperaLog(request, customerAllInfo.getCustomerUsername(), "新增退单物流信息", "新增退单物流信息-->需要执行退单操作的订单号【" + orderNo + "】,物流信息：名称【" + wlname + "】,物流单号【" + wlno
                    + LOGGERINFO1 + customerAllInfo.getCustomerUsername());
        }
        return goodsService.saveBackOrderGeneral(wlname, wlno, orderNo);
    }

    /**
     * 新增一条退货记录
     * 
     * @param returnyuanyin
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("/saveReturnGoodsTake")
    @ResponseBody
    public int saveReturnGoodsTake(HttpServletRequest request, HttpServletResponse response, Long orderId, String returnyuanyin, String tuikuanyuanyin) throws Exception {

        String returnyuanyinNew = returnyuanyin;
        // 转码
        Boolean bool = true;// 保存退货记录返回的结果
        // 当前登录会员id
        Long customerId = (Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID);

        String tuikuanyuanyin1 = tuikuanyuanyin;
        // 当前登录成功的会员信息
        CustomerAllInfo customerAllInfo = customerServiceInterface.selectByPrimaryKey(customerId);
        // 判断是退款还是退货
        if (null != tuikuanyuanyin1) {
            /* 需要处理乱码异常 */
            tuikuanyuanyin1 = java.net.URLDecoder.decode(tuikuanyuanyin1, ConstantUtil.UTF);
            // 新增一条退货记录 ，更改交易表状态退单金额，更新商品信息表
            bool = this.goodsService.saveReturnGoodsDetail(orderId, tuikuanyuanyin1, 0L);
            if (bool) {
                // 操作日志
                OperaLogUtil.addOperaLog(request, customerAllInfo.getCustomerUsername(), "新增退款记录",
                        "新增退款记录-->退款原因【" + tuikuanyuanyin + LOGGERINFO1 + customerAllInfo.getCustomerUsername());
            }
        } else {
            returnyuanyinNew = java.net.URLDecoder.decode(returnyuanyinNew, ConstantUtil.UTF);/* 需要处理乱码异常 */
            // 新增一条退货记录 ，更改交易表状态退单金额，更新商品信息表
            bool = this.goodsService.saveReturnGoodsDetail(orderId, returnyuanyinNew, 1L);
            if (bool) {
                // 操作日志
                OperaLogUtil.addOperaLog(request, customerAllInfo.getCustomerUsername(), "新增退单记录",
                        "新增退单记录-->退单原因【" + returnyuanyinNew + LOGGERINFO1 + customerAllInfo.getCustomerUsername());
            }
        }
        // 操作成功！
        if (bool) {
            return 1;
        } else {
            return 0;
        }

    }

    /**
     * 申请退款控制器
     * 
     * @param orderId
     * @return
     */
    @RequestMapping("customer/applybackmoneyprice")
    public ModelAndView applybackmoneyprice(HttpServletRequest request, Long orderId) {
        ModelAndView mav = null;
        Order order = siteOrderService.getPayOrder(orderId);
        mav = new ModelAndView("customer/applybackmoneyprice");
        Long customerId = (Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID);
        // 订单详细信息
        OrderInfoBean backorder = (OrderInfoBean) customerServiceInterface.queryOrderByCustIdAndOrderId(orderId, customerId);
        // 4174,1-
        String str = "";
        for (int i = 0; i < backorder.getGoods().size(); i++) {
            Long goodsids = backorder.getGoods().get(i).getGoodsId();
            Long goodsNum = backorder.getGoods().get(i).getGoodsNum();
            str += goodsids + "," + goodsNum + "-";
        }

        // 获取退款说明
        SystemsSet systemsSet = isbackOrderService.getIsBackOrder();
        return topAndBottomService.getTopAndBottom(mav).addObject(ORDER, order).addObject(BACKORDER, backorder).addObject("backPriceRemark", systemsSet.getBackPriceRemark())
                .addObject("customerId", customerId).addObject("backGoodsIdAndSum", str);
    }

    /**
     * 申请退货控制器
     * 
     * @param orderId
     * @return
     */
    @RequestMapping("customer/applybackmoney")
    public ModelAndView applyBackMoney(HttpServletRequest request, Long orderId) {
        ModelAndView mav = null;
        Order order = siteOrderService.getPayOrder(orderId);
        mav = new ModelAndView("customer/applybackmoney");
        //没有参加优惠的话，计算订单金额
        BigDecimal price=new BigDecimal(0);
        //标识为未参加促销的订单
        String staCheck="0";
        //
        if(!"0.00".equals(order.getOrderPrePrice().toString())||(order.getOrderIntegral()!=null&&order.getOrderIntegral()>0L)){
            //计算去除运费的金额
            price=order.getOrderPrice().subtract(order.getExpressPrice());
            //标识为已参加促销的订单
            staCheck="1";
        }
        Long customerId = (Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID);
        // 订单详细信息
        OrderInfoBean backorder = (OrderInfoBean) customerServiceInterface.queryOrderByCustIdAndOrderId(orderId, customerId);
        for (int i = 0; i < backorder.getGoods().size(); i++) {
            // 查询货品详细信息
            GoodsProductDetailViewVo goodsProductDetailViewVo = goodsProductService.queryViewVoByProductId(backorder.getGoods().get(i).getGoodsId());
            // 规格信息
            backorder.getGoods().get(i).setSpecVo(goodsProductDetailViewVo.getSpecVo());

        }
        // 获取退货说明
        String backInfoRemark = isbackOrderService.queryBackInfoRemark();
        return topAndBottomService.getTopAndBottom(mav).addObject(ORDER, order).addObject(BACKORDER, backorder).addObject("backInfoRemark", backInfoRemark).addObject("price",price).addObject("staCheck",staCheck)
                .addObject("customerId", customerId);
    }

    /**
     * 退款详情控制器
     * 
     * @param orderId
     * @return
     */
    @RequestMapping("customer/backdetailprice")
    public ModelAndView backDetailInfoprice(HttpServletRequest request, Long orderId) {
        ModelAndView mav = null;
        Order order = siteOrderService.getPayOrder(orderId);
        mav = new ModelAndView("customer/backdetailprice");
        Long customerId = (Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID);

        if (customerId == null) {
            return new ModelAndView(new RedirectView("/login.html"));
        }
        // 订单详细信息
        Order backorder = orderService.getPayOrder(orderId);
        // 根据订单编号查找退单信息
        BackOrder bOrder = backOrderService.queryBackOrderByOrderCode(backorder,request);
        List imglist =null;
        if (bOrder.getUploadDocuments() != null&&!"".equals(bOrder.getUploadDocuments())) {
            imglist= new ArrayList();
            String[] imgs = bOrder.getUploadDocuments().split(",");
            for (int i = 0; i < imgs.length; i++) {
                imglist.add(imgs[i]);
            }
        }
        // 获取退单日志信息
        List<BackOrderLog> backOrderLogs = backOrderLogService.queryByBackId(bOrder.getBackOrderId());
        return topAndBottomService.getTopAndBottom(mav).addObject(ORDER, order).addObject(BACKORDER, backorder).addObject("bOrder", bOrder).addObject("imglist", imglist)
                .addObject("backOrderLogs", backOrderLogs);
    }

    /**
     * 退单详情控制器
     * 
     * @param orderId
     * @return
     */
    @RequestMapping("customer/backdetail")
    public ModelAndView backDetailInfo(HttpServletRequest request, Long orderId) {
        ModelAndView mav = null;
        Order order = siteOrderService.getPayOrder(orderId);
        mav = new ModelAndView("customer/backdetail");
        Long customerId = (Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID);

        if (customerId == null) {
            return new ModelAndView(new RedirectView("/login.html"));
        }
        // 订单详细信息
        Order backorder = orderService.getPayOrder(orderId);
        // 根据订单编号查找退单信息
        BackOrder bOrder = backOrderService.queryBackOrderByOrderCode(backorder,request);
        // 根据订单编号查找退单物流信息
        BackOrderGeneral general = backOrderService.queryBackOrderGeneral(bOrder.getBackOrderId());
        List imglist = new ArrayList();
        if (bOrder.getUploadDocuments() != null) {
            String[] imgs = bOrder.getUploadDocuments().split(",");
            for (int i = 0; i < imgs.length; i++) {
                imglist.add(imgs[i]);
            }
        }
        // 获取退单日志信息
        List<BackOrderLog> backOrderLogs = backOrderLogService.queryByBackId(bOrder.getBackOrderId());
        // 查询退货商品信息（根据orderId和退单编号查询）
        List<OrderGoods> goodslist = orderService.queryOrderGoodsByOrderIdAndBackCode(orderId, bOrder.getBackOrderCode());
        for (int j = 0; j < goodslist.size(); j++) {
            GoodsProduct goodsProduct = goodsProductService.queryProductByGoodsId(goodslist.get(j).getGoodsInfoId());
            goodslist.get(j).setGoodsImg(goodsProduct.getGoodsInfoImgId());
            goodslist.get(j).setGoodsName(goodsProduct.getGoodsInfoName());
            goodslist.get(j).setGoodsCode(goodsProduct.getGoodsInfoItemNo());
        }
        return topAndBottomService.getTopAndBottom(mav).addObject(ORDER, order).addObject(BACKORDER, backorder).addObject("bOrder", bOrder).addObject("goodslist", goodslist)
                .addObject("imglist", imglist).addObject("backOrderLogs", backOrderLogs).addObject("general", general);
    }

    public ReturnGoodsService getGoodsService() {
        return goodsService;
    }

    @Resource(name = "ReturnGoodsService")
    public void setGoodsService(ReturnGoodsService goodsService) {
        this.goodsService = goodsService;
    }

    public CustomerServiceInterface getCustomerServiceInterface() {
        return customerServiceInterface;
    }

    @Resource(name = "customerServiceSite")
    public void setCustomerServiceInterface(CustomerServiceInterface customerServiceInterface) {
        this.customerServiceInterface = customerServiceInterface;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    @Resource(name = "OrderService")
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    public BackOrderService getBackOrderService() {
        return backOrderService;
    }

    @Resource(name = "BackOrderService")
    public void setBackOrderService(BackOrderService backOrderService) {
        this.backOrderService = backOrderService;
    }

    public SiteOrderService getSiteOrderService() {
        return siteOrderService;
    }

    @Resource(name = "SiteOrderService")
    public void setSiteOrderService(SiteOrderService siteOrderService) {
        this.siteOrderService = siteOrderService;
    }

    public IsBackOrderService getIsbackOrderService() {
        return isbackOrderService;
    }

    @Resource(name = "IsBackOrderService")
    public void setIsbackOrderService(IsBackOrderService isbackOrderService) {
        this.isbackOrderService = isbackOrderService;
    }

    public BackOrderLogService getBackOrderLogService() {
        return backOrderLogService;
    }

    @Resource(name = "BackOrderLogService")
    public void setBackOrderLogService(BackOrderLogService backOrderLogService) {
        this.backOrderLogService = backOrderLogService;
    }

    public TopAndBottomService getTopAndBottomService() {
        return topAndBottomService;
    }

    @Resource(name = "TopAndBottomService")
    public void setTopAndBottomService(TopAndBottomService topAndBottomService) {
        this.topAndBottomService = topAndBottomService;
    }

}
