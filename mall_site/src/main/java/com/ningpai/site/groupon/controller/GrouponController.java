/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.site.groupon.controller;

import com.ningpai.channel.service.ChannelAdverService;
import com.ningpai.common.util.alipay.util.AlipayNotify;
import com.ningpai.customer.bean.CustomerAddress;
import com.ningpai.goods.dao.GoodsCateMapper;
import com.ningpai.goods.pub.GoodsPub;
import com.ningpai.goods.util.CalcStockUtil;
import com.ningpai.goods.vo.GoodsCateVo;
import com.ningpai.index.service.TopAndBottomService;
import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.marketing.service.MarketingService;
import com.ningpai.order.bean.OrderVice;
import com.ningpai.order.service.OrderViceService;
import com.ningpai.other.util.CustomerConstantStr;
import com.ningpai.site.customer.controller.CustomerController;
import com.ningpai.site.customer.service.CustomerServiceInterface;
import com.ningpai.site.customer.vo.CustomerAllInfo;
import com.ningpai.site.goods.bean.GoodsDetailBean;
import com.ningpai.site.goods.service.BrowerService;
import com.ningpai.site.goods.service.GoodsProductService;
import com.ningpai.site.goods.util.ValueUtil;
import com.ningpai.site.goods.vo.GoodsProductVo;
import com.ningpai.site.groupon.bean.GrouponUtil;
import com.ningpai.site.groupon.service.GrouponService;
import com.ningpai.site.shoppingcart.service.ShoppingCartService;
import com.ningpai.system.service.PayService;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * 团购控制器
 * 
 * @author NINGPAI-LIH
 * 
 */
@Controller
public class GrouponController {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(CustomerController.class);

    private static final String CHADDRESS = "chAddress";
    private static final String CHPROVINCE = "chProvince";
    private static final String CHCITY = "chCity";
    private static final String CHDISTINCT = "chDistinct";
    private static final String ISO_8859_1 = "ISO-8859-1";
    private static final String DISTINCTID = "distinctId";
    private static final String UTF_8 = "UTF-8";

    private static final Long ATID1 = 157L;

    private static final Long ADVERTTYPE = 151L;

    // spring 注解 会员service
    private CustomerServiceInterface customerServiceInterface;

    @Resource(name = "GoodsProductService")
    private com.ningpai.goods.service.GoodsProductService productService;

    @Resource(name = "GoodsCateMapper")
    private GoodsCateMapper goodsCateMapper;

    @Resource(name = "GrouponService")
    private GrouponService grouponService;

    @Resource(name = "payService")
    private PayService payService;

    // 团购订单
    @Resource(name = "OrderViceService")
    private OrderViceService orderViceService;

    @Resource(name = "GrouponService")
    private GrouponService service;

    @Resource(name = "MarketingService")
    private MarketingService marketingService;

    @Resource(name = "BrowerService")
    private BrowerService browerService;

    @Resource(name = "ShoppingCartService")
    private ShoppingCartService cartService;

    @Resource(name = "goodsPub")
    private GoodsPub goodsPub;

    @Resource(name = "TopAndBottomService")
    private TopAndBottomService topAndBottomService;

    @Resource(name = "HsiteGoodsProductService")
    private GoodsProductService goodsProductService;

    /** 广告业务接口 */
    @Resource(name = "ChannelAdverService")
    private ChannelAdverService channelAdverService;

    public CustomerServiceInterface getCustomerServiceInterface() {
        return customerServiceInterface;
    }

    @Resource(name = "customerServiceSite")
    public void setCustomerServiceInterface(CustomerServiceInterface customerServiceInterface) {
        this.customerServiceInterface = customerServiceInterface;
    }

    /**
     * 团购列表
     * 
     * @param pb
     *            分页参数
     * @param request
     *            请求
     * @return
     */
    @RequestMapping("selectgrouponlist")
    public ModelAndView selectGrouponList(PageBean pb, HttpServletRequest request,
                                          Long parentId,@RequestParam(defaultValue = "0")String isAsc ) {
        PageBean pb1 = pb;
        pb1.setPageSize(8);
        pb1 = goodsProductService.selectGrouponList(pb1,request,parentId,isAsc);
        String endUrl="";
        String urlchange = null;
        String urlchange1 = null;
        String urlchange2 = null;
        if(parentId !=null){
            endUrl = "&parentId="+parentId;
            urlchange = endUrl;
        }
        if("0".equals(isAsc)){
            endUrl += endUrl+"&isAsc=0";
            urlchange1 = "&isAsc=0";
            urlchange2 = "?isAsc=1";
        }
        if("1".equals(isAsc)){
            endUrl += endUrl+"&isAsc=1";
            urlchange1 = "&isAsc=1";
            urlchange2 = "?isAsc=0";
        }
        if(endUrl.length()>0&&endUrl.indexOf("&")!=-1){
            endUrl = endUrl.replaceFirst("&","?");
            pb1.setEndUrl(endUrl);
        }
        pb1.setUrl("grouponlist");
        int tempId = topAndBottomService.getIndexDefalutTemp().getTempId();
        List<GoodsCateVo> goodsCateVos =goodsCateMapper.queryFirstLevelGoodsCate();
        ModelAndView mav = new ModelAndView("groupon/grouponlist").addObject("pb", pb1).addObject("avs",
                channelAdverService.selectchannelAdverByParamForSite(null, Long.valueOf(tempId), null, null, ATID1, ADVERTTYPE, null, "0", null, "5"));
        LOGGER.info("获取团购列表！");
        mav.addObject("firstGoodsCateList", goodsCateVos);
        mav.addObject("urlchange", urlchange);
        mav.addObject("urlchange",urlchange);
        mav.addObject("urlchange1",urlchange1);
        mav.addObject("urlchange2",urlchange2);
        mav.addObject("parentId",parentId);
        return topAndBottomService.getTopAndBottom(mav);
    }

    /**
     * 团购商品详情页
     * 
     * @param productId
     *            货品ID
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("/groupon")
    public ModelAndView goodsDetail(Long productId, Long distinctId, String chAddress, HttpServletRequest request, HttpServletResponse response)
            throws UnsupportedEncodingException {
        Long distinctId1 = distinctId;
        Map<String, Object> map = new HashMap<String, Object>();
        if (null == distinctId1) {
            if (null != request.getSession().getAttribute(ValueUtil.ADDRESS) && null == request.getSession().getAttribute(CHADDRESS)) {
                distinctId1 = ((CustomerAddress) request.getSession().getAttribute(ValueUtil.ADDRESS)).getDistrict().getDistrictId();
                map.put(CHADDRESS,
                        ((CustomerAddress) request.getSession().getAttribute(ValueUtil.ADDRESS)).getProvince().getProvinceName()
                                + ((CustomerAddress) request.getSession().getAttribute(ValueUtil.ADDRESS)).getCity().getCityName()
                                + ((CustomerAddress) request.getSession().getAttribute(ValueUtil.ADDRESS)).getDistrict().getDistrictName());
                map.put(CHPROVINCE, ((CustomerAddress) request.getSession().getAttribute(ValueUtil.ADDRESS)).getProvince().getProvinceName());
                map.put(CHCITY, ((CustomerAddress) request.getSession().getAttribute(ValueUtil.ADDRESS)).getCity().getCityName());
                map.put(CHDISTINCT, ((CustomerAddress) request.getSession().getAttribute(ValueUtil.ADDRESS)).getDistrict().getDistrictName());
            } else {
                if (null == request.getSession().getAttribute(CHADDRESS)) {
                    // 默认设置为南京建邺区仓库
                    distinctId1 = new Long(1103L);
                    map.put(CHADDRESS, null == chAddress ? "江苏南京建邺区" : new String(chAddress.getBytes(ISO_8859_1), UTF_8));
                    map.put(CHPROVINCE, "江苏");
                    map.put(CHCITY, "南京市");
                    map.put(CHDISTINCT, "建邺区");
                } else {
                    /* 取session的数据 */
                    map.put(CHPROVINCE, request.getSession().getAttribute(CHPROVINCE));
                    map.put(CHADDRESS, request.getSession().getAttribute(CHADDRESS));
                    map.put(CHCITY, request.getSession().getAttribute(CHCITY));
                    map.put(CHDISTINCT, request.getSession().getAttribute(CHDISTINCT));
                    distinctId1 = Long.parseLong(request.getSession().getAttribute(DISTINCTID).toString());
                }
            }
        } else {
            /* 如果页面传过来的地区值不为null */
            map.put(CHADDRESS, new String(chAddress.getBytes(ISO_8859_1), UTF_8));
            map.put(CHPROVINCE, new String(request.getParameter(CHPROVINCE).getBytes(ISO_8859_1), UTF_8));
            map.put(CHCITY, new String(request.getParameter(CHCITY).getBytes(ISO_8859_1), UTF_8));
            map.put(CHDISTINCT, new String(request.getParameter(CHDISTINCT).getBytes(ISO_8859_1), UTF_8));
        }
        map.put(DISTINCTID, distinctId1);
        /* 保存到session中 */
        request.getSession().setAttribute(CHPROVINCE, map.get(CHPROVINCE));
        request.getSession().setAttribute(CHADDRESS, map.get(CHADDRESS));
        request.getSession().setAttribute(CHCITY, map.get(CHCITY));
        request.getSession().setAttribute(CHDISTINCT, map.get(CHDISTINCT));
        request.getSession().setAttribute(DISTINCTID, map.get(DISTINCTID));

        GoodsDetailBean detailBean = this.goodsProductService.queryDetailBeanByProductId(productId, distinctId1);
        try {
            if (null != detailBean.getProductVo()) {
                detailBean = cartService.forPurchasing(detailBean, (Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID));
                map.put("detailBean", detailBean);
                /* 查询商品关联的标签 */
                map.put("tags", this.goodsPub.getGoodsReleTagService().queryreleListByProductId(detailBean.getProductVo().getGoodsInfoId()));
                // 保存浏览记录
                this.browerService.saveBrowerHis(request, response, productId);
                ModelAndView mav = new ModelAndView("groupon/groupondetails", ValueUtil.MAP, map);
                mav.addObject("market", marketingService.selectGrouponMarket(productId, 4L, detailBean.getProductVo().getGoods().getCatId(), detailBean.getBrand().getBrandId()));
                // 货品ID非空验证
                if (null != productId) {
                    // 根据货品ID获取单个货品对象
                    GoodsProductVo goodsProductVo = goodsProductService.queryProductByProductId(productId);
                    // 日志记录
                    OperaLogUtil.addOperaLog(request, "张三", "查看货品", "进入团购货品【" + goodsProductVo.getProductName() + "】的详情页");
                }
                return topAndBottomService.getTopAndBottom(mav);
            } else {
                return null;
            }
        } finally {
            detailBean = null;
        }
    }

    /**
     * 团购订单
     *
     * @param productId
     *            货品id
     * @param distinctId
     *            地区id
     * @return
     */
    @RequestMapping("subgrouponorder")
    public ModelAndView subGrouponOrder(Long productId, Long distinctId, HttpServletRequest request) {
        Long customerId = (Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID);
        if (customerId == null) {
            return new ModelAndView(new RedirectView("../login.html"));
        }
        GrouponUtil group = new GrouponUtil();
        GoodsDetailBean detailBean = this.goodsProductService.queryDetailBeanByProductId(productId, distinctId);
        group.setMarketing(marketingService.selectGrouponMarket(productId, 4L, detailBean.getProductVo().getGoods().getCatId(), detailBean.getBrand().getBrandId()));
        group.setGoodsProductVo(detailBean.getProductVo());
        ModelAndView mav = new ModelAndView("groupon/grouponorder");
        mav.addObject("groupons", group);
        if (null != productId) {
            // 根据货品ID获取单个货品对象
            GoodsProductVo goodsProductVo = goodsProductService.queryProductByProductId(productId);
            // 日志记录
            LOGGER.info("进入团购货品【" + goodsProductVo.getProductName() + "】的订单");
        }
        return topAndBottomService.getTopAndBottom(mav);
    }

    /**
     * 团购订单提交
     * 
     * @param request
     *            请求
     * @param custAddress
     *            收货地址
     * @param chInvoiceTitle
     *            发票抬头
     * @param chInvoiceType
     *            发票抬头
     * @param chInvoiceContent
     *            发票内容
     * @param productId
     *            商品id
     * @param productNum
     *            商品数量
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("submitgrouponorder")
    public ModelAndView submitGrouponOrder(HttpServletRequest request, Long custAddress, String chInvoiceTitle, String chInvoiceType, String chInvoiceContent, Long productId,
            Long productNum) throws UnsupportedEncodingException {
        String chInvoiceTitle1 = chInvoiceTitle;
        String chInvoiceContent1 = chInvoiceContent;
        chInvoiceTitle1 = new String(chInvoiceTitle1.getBytes(ISO_8859_1), UTF_8);
        chInvoiceContent1 = new String(chInvoiceContent1.getBytes(ISO_8859_1), UTF_8);
        String orderCode = service.subGrouponOrder(request, custAddress, chInvoiceTitle1, chInvoiceType, chInvoiceContent1, productId, productNum);
        Map<String, Object> map = new HashMap<String, Object>();
        // 当前登录会员id
        Long customerId = (Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID);
        // 当前登录成功的会员信息
        CustomerAllInfo customerAllInfo = customerServiceInterface.selectByPrimaryKey(customerId);
        // 付款信息
        OrderVice orderVice = orderViceService.payOrder(orderCode);
        // 付款信息
        map.put("order", orderVice);
        // 付款方式
        map.put("paylist", payService.queryAllPaySet());
        ModelAndView mav = new ModelAndView("groupon/subsuccess").addObject("map", map);
        if (null != productId) {
            // 根据货品ID获取单个货品对象
            GoodsProductVo goodsProductVo = goodsProductService.queryProductByProductId(productId);
            // 日志记录
            LOGGER.info("提交团购货品【" + goodsProductVo.getProductName() + "】的订单");
            OperaLogUtil.addOperaLog(request, customerAllInfo.getCustomerUsername(), "提交团购订单",
                    "提交团购订单-->货品名称【" + goodsProductVo.getProductName() + "】,商家名称【" + orderVice.getStoreName() + "】-->用户名：" + customerAllInfo.getCustomerUsername());
        }
        return topAndBottomService.getTopAndBottom(mav);
    }

    /**
     * 团购支付
     * 
     * @param orderCode
     *            订单编号
     * @param payId
     *            支付id
     * @return 支付请求
     */
    @RequestMapping("paygrouponorder")
    public ModelAndView payGrouponOrder(String orderCode, Long payId) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("sHtmlText", service.payGrouponOrder(orderCode, payId));
        mav.setViewName("order/netbank");
        // 验证非空
        if (null != orderCode && null != payId) {
            // 日志记录
            LOGGER.info("团购商品支付，支付订单为:" + orderCode + "支付Id为：" + payId);
        }
        return topAndBottomService.getTopAndBottom(mav);
    }

    /**
     * 支付宝回调
     * 
     * @param request
     *            请求参数
     * @param agr1
     * @return 支付成功
     */
    @RequestMapping("paygrouonusccess")
    public ModelAndView payGrouonuSccess(HttpServletRequest request, HttpServletResponse agr1) {
        Map<String, String> params = new HashMap<String, String>();
        // 获取返回的Map
        Map<?, ?> requestParams = request.getParameterMap();
        // 循环取出
        for (@SuppressWarnings("rawtypes")
        Map.Entry entrySet : requestParams.entrySet()) {
            String[] values = (String[]) entrySet.getValue();
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr.concat(values[i]) : valueStr.concat(values[i]).concat(",");
            }
            try {
                valueStr = new String(valueStr.getBytes(ISO_8859_1), UTF_8);
                // 根据名字 和value 存入Map
                params.put(entrySet.getKey().toString(), valueStr);
                LOGGER.info("支付宝回调成功！");
            } catch (UnsupportedEncodingException e) {
                LOGGER.error("支付宝回调报错：" + e);
            }
        }

        // 订单号
        String orderCode = request.getParameter("out_trade_no");
        OrderVice orderVice = orderViceService.payOrder(orderCode);
        orderVice.setOrderStatus("1");
        orderVice.setPayTime(new Date());
        orderViceService.updateOrderVice(orderVice);
        grouponService.addGroupCount(orderVice.getMarketingId());
        // 减库存的操作
        CalcStockUtil calcStockUtil = new CalcStockUtil();
        calcStockUtil.setDistinctId(orderVice.getDistrictId());
        calcStockUtil.setProductId(orderVice.getGoodsInfoId());
        calcStockUtil.setStock(Integer.parseInt(orderVice.getGoodsNum().toString()));
        if (orderVice.getBusinessId() == 0) {
            calcStockUtil.setIsThird("0");
        } else {
            calcStockUtil.setIsThird("1");
        }
        List<CalcStockUtil> calcStockUtils = new ArrayList<CalcStockUtil>();
        calcStockUtils.add(calcStockUtil);
        productService.minStock(calcStockUtils);
        ModelAndView mav = new ModelAndView("groupon/paysuccess").addObject("order", orderVice);
        return topAndBottomService.getSimpleTopAndBottom(mav);
    }

    /**
     * 支付宝回调
     * 
     * @param request
     * @param agr1
     * @return ModelAndView
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("/paygrouonusccessyb")
    public void paySuccessyb(HttpServletRequest request, HttpServletResponse agr1) throws UnsupportedEncodingException {
        // 获取支付宝POST过来反馈信息
        Map<String, String> params = new HashMap<String, String>();
        Map<?, ?> requestParams = request.getParameterMap();
        for (Iterator<?> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            // 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
            // valueStr = new String(valueStr.getBytes(ISO_8859_1), "gbk");
            params.put(name, valueStr);
        }

        // 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
        // 商户订单号

        String outTradeNo = new String(request.getParameter("out_trade_no").getBytes(ISO_8859_1), UTF_8);

        // 支付宝交易号

        // 交易状态
        String tradeStatus = new String(request.getParameter("trade_status").getBytes(ISO_8859_1), UTF_8);

        // 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//

        if (AlipayNotify.verify(params)) {// 验证成功
            // ////////////////////////////////////////////////////////////////////////////////////////
            // 请在这里加上商户的业务逻辑程序代码

            // ——请根据您的业务逻辑来编写程序（以下代码仅作参考）——

            if ("TRADE_FINISHED".equals(tradeStatus)) {
                // 判断该笔订单是否在商户网站中已经做过处理
                // 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                // 如果有做过处理，不执行商户的业务程序
                // 重新将session登入
                OrderVice orderVice = orderViceService.payOrder(outTradeNo);
                if ("0".equals(orderVice.getOrderStatus())) {
                    orderVice.setOrderStatus("1");
                    orderVice.setPayTime(new Date());
                    grouponService.addGroupCount(orderVice.getMarketingId());
                    orderViceService.updateOrderVice(orderVice);
                    // 减库存的操作
                    CalcStockUtil calcStockUtil = new CalcStockUtil();
                    calcStockUtil.setDistinctId(orderVice.getDistrictId());
                    calcStockUtil.setProductId(orderVice.getGoodsInfoId());
                    calcStockUtil.setStock(Integer.parseInt(orderVice.getGoodsNum().toString()));
                    if (orderVice.getBusinessId() == 0) {
                        calcStockUtil.setIsThird("0");
                    } else {
                        calcStockUtil.setIsThird("1");
                    }
                    List<CalcStockUtil> calcStockUtils = new ArrayList<CalcStockUtil>();
                    calcStockUtils.add(calcStockUtil);
                    productService.minStock(calcStockUtils);
                }

                // 注意：
                // 该种交易状态只在两种情况下出现
                // 1、开通了普通即时到账，买家付款成功后。
                // 2、开通了高级即时到账，从该笔交易成功时间算起，过了签约时的可退款时限（如：三个月以内可退款、一年以内可退款等）后。
            } else if ("TRADE_SUCCESS".equals(tradeStatus)) {
                // 判断该笔订单是否在商户网站中已经做过处理
                // 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                // 如果有做过处理，不执行商户的业务程序
                OrderVice orderVice = orderViceService.payOrder(outTradeNo);
                if ("0".equals(orderVice.getOrderStatus())) {
                    orderVice.setOrderStatus("1");
                    orderVice.setPayTime(new Date());
                    grouponService.addGroupCount(orderVice.getMarketingId());
                    orderViceService.updateOrderVice(orderVice);
                    // 减库存的操作
                    CalcStockUtil calcStockUtil = new CalcStockUtil();
                    calcStockUtil.setDistinctId(orderVice.getDistrictId());
                    calcStockUtil.setProductId(orderVice.getGoodsInfoId());
                    calcStockUtil.setStock(Integer.parseInt(orderVice.getGoodsNum().toString()));
                    if (orderVice.getBusinessId() == 0) {
                        calcStockUtil.setIsThird("0");
                    } else {
                        calcStockUtil.setIsThird("1");
                    }
                    List<CalcStockUtil> calcStockUtils = new ArrayList<CalcStockUtil>();
                    calcStockUtils.add(calcStockUtil);
                    productService.minStock(calcStockUtils);
                }

                // 注意：
                // 该种交易状态只在一种情况下出现——开通了高级即时到账，买家付款成功后。
            }

            // ——请根据您的业务逻辑来编写程序（以上代码仅作参考）——

            //System.out.println("success"); // 请不要修改或删除
            LOGGER.info("success");

            // ////////////////////////////////////////////////////////////////////////////////////////
        } else {// 验证失败
            //System.out.println("fail");
            LOGGER.info("fail");
        }

    }
}
