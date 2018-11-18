/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.site.marketingrush.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ningpai.util.MyLogger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.channel.service.ChannelAdverService;
import com.ningpai.customer.bean.CustomerAddress;
import com.ningpai.goods.pub.GoodsPub;
import com.ningpai.index.service.TopAndBottomService;
import com.ningpai.marketing.bean.Marketing;
import com.ningpai.marketing.service.MarketingService;
import com.ningpai.order.service.OrderViceService;
import com.ningpai.other.util.CustomerConstantStr;
import com.ningpai.site.goods.bean.GoodsDetailBean;
import com.ningpai.site.goods.service.BrowerService;
import com.ningpai.site.goods.service.GoodsProductService;
import com.ningpai.site.goods.util.ValueUtil;
import com.ningpai.site.marketingrush.bean.MarketingRushUtil;
import com.ningpai.site.marketingrush.service.MarketingRushService;
import com.ningpai.site.shoppingcart.service.ShoppingCartService;
import com.ningpai.system.service.PayService;
import com.ningpai.util.PageBean;

/**
 * 抢购秒杀控制器
 * 
 * @author jiping
 * @since 2015年1月20日 上午9:18:47
 * @version 0.0.1
 */
@Controller
public class MarketingRushSiteController {

    private static final String CHADDRESS = "chAddress";
    private static final String CHPROVINCE = "chProvince";
    private static final String CHCITY = "chCity";
    private static final String CHDISTINCT = "chDistinct";
    private static final String ISO_8859_1 = "ISO-8859-1";
    private static final String UTF_8 = "utf-8";
    private static final String DISTINCTID = "distinctId";

    private static final Long ATID1 = 157L;

    private static final Long ADVERTTYPE = 151L;

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(MarketingRushSiteController.class);

    @Resource(name = "MarketingRushService")
    private MarketingRushService marketingRushService;

    @Resource(name = "payService")
    private PayService payService;

    // 团购抢购订单
    @Resource(name = "OrderViceService")
    private OrderViceService orderViceService;

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

    /**
     * 抢购秒杀列表
     * 
     * @param pb
     *            分页参数
     * @param request
     *            请求
     * @return
     */
    @RequestMapping("selectmrlist")
    public ModelAndView selectMarketingRushList(PageBean pb, HttpServletRequest request) {
        PageBean pb1 = pb;
        pb1.setPageSize(15);
        pb1 = goodsProductService.selectMarketingRushList(pb1, request);
        pb1.setUrl("marketingrushlist");
        int tempId = topAndBottomService.getIndexDefalutTemp().getTempId();
        ModelAndView mav = new ModelAndView("marketingrush/marketingrushlist").addObject("pb", pb1).addObject("avs",
                channelAdverService.selectchannelAdverByParamForSite(null, Long.valueOf(tempId), null, null, ATID1, ADVERTTYPE, null, "0", null, "6"));
        LOGGER.info("获取抢购秒杀列表");
        return topAndBottomService.getTopAndBottom(mav);
    }

    /**
     * 抢购商品详情页
     * 
     * @param productId
     *            货品ID
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("/marketingrush")
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
                Marketing marketing = marketingService.selectRushMarket(productId, 5L, detailBean.getProductVo().getGoods().getCatId(), detailBean.getBrand().getBrandId());
                String rushs = goodsProductService.compareTime(request, marketing.getMarketingBegin(), marketing.getMarketingEnd());
                ModelAndView mav = new ModelAndView("marketingrush/mrushdetails", ValueUtil.MAP, map);
                mav.addObject("market", marketing).addObject("rushTime", rushs);
                // 非空验证 货品名称
                if (null != detailBean.getProductVo().getProductName()) {
                    LOGGER.info("进入抢购秒杀货品【" + detailBean.getProductVo().getProductName() + "】详情页");
                }
                return topAndBottomService.getTopAndBottom(mav);
            } else {
                return topAndBottomService.getTopAndBottom(new ModelAndView("/goods/no_exit"));
            }
        } finally {
            detailBean = null;
        }
    }

    /**
     * 抢购订单
     * 
     * @param productId
     *            货品id
     * @param distinctId
     *            地区id
     * @return
     */
    @RequestMapping("submrorder")
    public ModelAndView subMROrder(Long productId, Long distinctId, HttpServletRequest request) {
        Long customerId = (Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID);
        if (customerId == null) {
            return new ModelAndView(new RedirectView("../login.html"));
        }
        MarketingRushUtil mrutil = new MarketingRushUtil();
        GoodsDetailBean detailBean = this.goodsProductService.queryDetailBeanByProductId(productId, distinctId);
        mrutil.setMarketing(marketingService.selectRushMarket(productId, 5L, detailBean.getProductVo().getGoods().getCatId(), detailBean.getBrand().getBrandId()));
        mrutil.setGoodsProductVo(detailBean.getProductVo());
        ModelAndView mav = new ModelAndView("marketingrush/mrushorder");
        mav.addObject("rushs", mrutil);
        // 非空验证 货品名称
        if (null != detailBean.getProductVo().getProductName()) {
            LOGGER.info("进入抢购秒杀货品【" + detailBean.getProductVo().getProductName() + "】的订单");
        }
        return topAndBottomService.getTopAndBottom(mav);
    }

    /**
     * 抢购订单提交
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
    @RequestMapping("submitmrorder")
    public ModelAndView submitGrouponOrder(HttpServletRequest request, Long custAddress, String chInvoiceTitle, String chInvoiceType, String chInvoiceContent, Long productId,
            Long productNum) throws UnsupportedEncodingException {
        String chInvoiceTitle1 = chInvoiceTitle;
        String chInvoiceContent1 = chInvoiceContent;
        chInvoiceTitle1 = new String(chInvoiceTitle1.getBytes(ISO_8859_1), UTF_8);
        chInvoiceContent1 = new String(chInvoiceContent1.getBytes(ISO_8859_1), UTF_8);
        String orderCode = marketingRushService.subMarketingRushOrder(request, custAddress, chInvoiceTitle1, chInvoiceType, chInvoiceContent1, productId, productNum);
        Map<String, Object> map = new HashMap<String, Object>();
        // 付款信息
        map.put("order", orderViceService.payOrder(orderCode));
        // 付款方式
        map.put("paylist", payService.queryAllPaySet());
        ModelAndView mav = new ModelAndView("groupon/subsuccess").addObject("map", map);
        // 非空验证 订单单号
        if (null != orderCode) {
            // 日志记录
            LOGGER.info("提交抢购订单【" + orderCode + "】");
        }
        return topAndBottomService.getTopAndBottom(mav);
    }

    /**
     * 抢购支付
     * 
     * @param orderCode
     *            订单编号
     * @param payId
     *            支付id
     * @return 支付请求
     */
    @RequestMapping("paymrorder")
    public ModelAndView payMROrder(String orderCode, Long payId) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("sHtmlText", marketingRushService.payMarketingRushOrder(orderCode, payId));
        mav.setViewName("order/netbank");
        // 非空验证 订单单号
        if (null != orderCode) {
            // 日志记录
            LOGGER.info("抢购支付订单【" + orderCode + "】");
        }
        return topAndBottomService.getTopAndBottom(mav);
    }
}
