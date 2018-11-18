/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.marketing.controller;

import com.ningpai.common.safe.CSRFTokenManager;
import com.ningpai.coupon.service.CouponService;
import com.ningpai.customer.service.PointLevelServiceMapper;
import com.ningpai.goods.bean.Goods;
import com.ningpai.goods.bean.GoodsProduct;
import com.ningpai.goods.service.GoodsBrandService;
import com.ningpai.goods.service.GoodsCateService;
import com.ningpai.goods.service.GoodsProductService;
import com.ningpai.goods.service.GoodsService;
import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.marketing.bean.*;
import com.ningpai.marketing.common.MarketingPath;
import com.ningpai.marketing.service.CodexService;
import com.ningpai.marketing.service.MarketingService;
import com.ningpai.marketing.service.PromotionLogoService;
import com.ningpai.other.util.CustomerConstantStr;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author ggn 2014-03-21 促销信息控制器
 */
@Controller
public class MarketingController {

    private static final MyLogger LOGGER = new MyLogger(MarketingController.class);

    private static final String PAGEBEAN = "pageBean";
    private static final String MARKETING = "marketing";
    private static final String BRANDLIST = "brandlist";
    private static final String CODEXLIST = "codexlist";
    private static final String THIRDCATELIST = "thirdCateList";
    private static final String DATETIME = "yyyy-MM-dd HH:mm:ss";
    private static final String CODEXTYPE = "codexType";
    private static final String GROUPGOODSLIST_HTM = "groupgoodslist.htm";
    private static final String PANICGOODSLIST_HTM = "panicgoodslist.htm";
    private static final String QUERYPRELIST_HTM = "queryprelist.htm";

    @Resource(name = "CodexService")
    private CodexService codexService;

    @Resource(name = "MarketingService")
    private MarketingService marketingService;

    @Resource(name = "GoodsBrandService")
    private GoodsBrandService goodsBrandService;

    @Resource(name = "GoodsCateService")
    private GoodsCateService goodsCateService;

    @Resource(name = "GoodsProductService")
    private GoodsProductService goodsProductService;

    // 商品的Service
    @Resource(name = "GoodsService")
    private GoodsService goodsService;

    // 会员等级
    @Resource(name = "pointLevelServiceMapper")
    private PointLevelServiceMapper pointLevelServiceMapper;

    // 优惠券
    @Resource(name = "CouponService")
    private CouponService couponService;

    // 促销LOGO
    @Resource(name = "PromotionLogoService")
    private PromotionLogoService logoService;

    /**
     * 查询订单促销列表
     * 
     * @param pageBean
     * @param marketing
     *            参数列表
     * @return ModelAndView
     */
    @RequestMapping("/marketorderlist")
    public ModelAndView marketOrderList(PageBean pageBean, Marketing marketing, String marketingBeginTime, String marketingEndTime, HttpServletRequest request) {
        pageBean.setUrl(MarketingPath.INITMARKETORDERLIST);
        // Type==1 订单 Type==0商品
        marketing.setMarketingType("1");
        // 根据参数查询订单促销列表
        return new ModelAndView(MarketingPath.MARKETORDERLIST, PAGEBEAN, marketingService.marketOrderList(pageBean, marketing, marketingBeginTime, marketingEndTime)).addObject(
                "sx", request.getSession().getAttribute(CSRFTokenManager.CSRF_TOKEN_FOR_SESSION_ATTR_NAME).toString());
    }

    /**
     * 查询商品促销列表
     * 
     * @param pageBean
     * @param marketing
     * @return ModelAndView
     */
    @RequestMapping("/marketgoodslist")
    public ModelAndView marketGoodsList(PageBean pageBean, Marketing marketing, String marketingBeginTime, String marketingEndTime, HttpServletRequest request) {
        // 设置URL
        pageBean.setUrl(MarketingPath.INITMARKETGOODSLIST);
        // Type==1 订单 Type==0商品
        marketing.setMarketingType("0");
        // 查询商品促销列表
        return new ModelAndView(MarketingPath.MARKETGOODSLIST, PAGEBEAN, marketingService.marketOrderList(pageBean, marketing, marketingBeginTime, marketingEndTime)).addObject(
                "sx", request.getSession().getAttribute(CSRFTokenManager.CSRF_TOKEN_FOR_SESSION_ATTR_NAME).toString()).addObject("codexs", codexService.selectCodexListUseBox());
    }

    /**
     * 跳转修改促销页面
     * 
     * @param marketingId
     * @param request
     * @return
     */
    @RequestMapping("/toModifyMarketingdetail")
    public ModelAndView toModifyMarketingdetail(Long marketingId, HttpServletRequest request) {
        // 查询促销详细
        Marketing marketing = marketingService.searchMarketByMarketingId(marketingId);
        // 查询促销Codex
        MarketingCodex marketingCodex = marketingService.searchMarketRuleByMarketingId(marketingId);
        // 查询促销详细
        Marketing marketingDetail = marketingService.queryMarketingDetail(marketingId, marketingCodex.getCodexId());
        return new ModelAndView(MarketingPath.MODIFYMARKETLIST).addObject(MARKETING, marketing).addObject("marketingCodex", marketingCodex)
                .addObject("marketingDetail", marketingDetail).addObject("marketingId", marketingId)
                .addObject("sx", request.getSession().getAttribute(CSRFTokenManager.CSRF_TOKEN_FOR_SESSION_ATTR_NAME));
    }

    /**
     * ajax获取货品信息
     * 
     * @param marketingId
     * @return
     */
    @RequestMapping("/queryGoodsByAjax")
    @ResponseBody
    public List queryGoodsByAjax(Long marketingId, Long codexType) {
        // 查询促销范围
        List<MarketingScope> list = marketingService.queryMarketingScope(marketingId);
        List<GoodsProduct> product = new ArrayList<GoodsProduct>();
        if (codexType.intValue() != 15) {
            for (int i = 0; i < list.size(); i++) {
                product.add(goodsProductService.queryProductByGoodsId(list.get(i).getScopeId()));
            }
            return product;
        } else {
            // 查询促销范围
            List lista = new ArrayList();
            List listb = new ArrayList();
            List listc = new ArrayList();
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("marketingId", marketingId);
                map.put("productId", list.get(i).getScopeId());
                GoodsProduct goodsproduct = goodsProductService.queryProductById(map);
                if (goodsproduct != null) {
                    lista.add(goodsproduct.getGoodsId());
                    listb.add(goodsproduct.getDiscountInfo());
                    listc.add(goodsproduct.getDiscountPrice());
                }
            }
            List<Goods> goods = new ArrayList<Goods>();
            for (int k = 0; k < lista.size(); k++) {
                Goods gd = this.goodsService.queryGoodsByGoodsId((Long) lista.get(k));// 货品id获取商品信息
                gd.setDiscountInfo((BigDecimal) listb.get(k));
                if (listc.get(k) == null) {
                    gd.setDiscountPrice(gd.getGoodsPrice().multiply((BigDecimal) listb.get(k)));
                } else {
                    gd.setDiscountPrice((BigDecimal) listc.get(k));
                }
                goods.add(gd);
            }
            for (int i = 0; i < goods.size() - 1; i++) {
                for (int j = goods.size() - 1; j > i; j--) {
                    if (goods.get(j).getGoodsId().equals(goods.get(i).getGoodsId())) {
                        goods.remove(j);
                    }
                }
            }
            return goods;
        }

    }

    /**
     * 查询团购促销列表
     * 
     * @param pageBean
     * @param marketing
     * @return ModelAndView
     */
    @RequestMapping("/groupgoodslist")
    public ModelAndView groupGoodsList(PageBean pageBean, Marketing marketing, String marketingBeginTime, String marketingEndTime, HttpServletRequest request) {
        // 设置促销URL
        pageBean.setUrl(MarketingPath.INITMARKETGOODSLIST);
        // 设置促销Type
        marketing.setMarketingType(null);
        // 设置促销CodexID
        marketing.setCodexId(10L);
        // 查询促销
        return new ModelAndView(MarketingPath.GROUPGOODSLIST, PAGEBEAN, marketingService.marketOrderList(pageBean, marketing, marketingBeginTime, marketingEndTime)).addObject(
                "sx", request.getSession().getAttribute(CSRFTokenManager.CSRF_TOKEN_FOR_SESSION_ATTR_NAME).toString());
    }

    /**
     * 查询抢购促销列表
     * 
     * @param pageBean
     * @param marketing
     * @return ModelAndView
     */
    @RequestMapping("/panicgoodslist")
    public ModelAndView panicGoodsList(PageBean pageBean, Marketing marketing, String marketingBeginTime, String marketingEndTime, HttpServletRequest request) {
        // 设置URL
        pageBean.setUrl(MarketingPath.INITMARKETGOODSLIST);
        marketing.setMarketingType(null);
        // 设置COdeXID
        marketing.setCodexId(11L);
        // 查询促销列表
        return new ModelAndView(MarketingPath.PANICGOODSLIST, PAGEBEAN, marketingService.marketOrderList(pageBean, marketing, marketingBeginTime, marketingEndTime)).addObject(
                "sx", request.getSession().getAttribute(CSRFTokenManager.CSRF_TOKEN_FOR_SESSION_ATTR_NAME).toString());
    }

    /**
     * 查询促销详细信息列表
     * 
     * @param marketingId
     *            促销ID
     * @return ModelAndView
     */
    @RequestMapping("/marketingdetail")
    public ModelAndView marketingDetail(Long marketingId) {
        // 查询促销详细
        // 查询促销范围
        return new ModelAndView(MarketingPath.MARKETDETAIL).addObject(MARKETING, marketingService.marketingDetailNotTime(marketingId))
                .addObject("catelist", marketingService.selectMarketingScope(marketingId, "0")).addObject(BRANDLIST, marketingService.selectMarketingScope(marketingId, "1"))
                .addObject("kulist", marketingService.selectMarketingScope(marketingId, "2"))
                // 促销活动对象
                .addObject(MARKETING, marketingService.marketingDetailNotTime(marketingId));
    }

    /**
     * 跳转添加订单促销页面
     * 
     * @return ModelAndView
     */
    @RequestMapping("/toaddordermarketing")
    public ModelAndView toAddOrderMarketing(HttpServletRequest request) {
        Marketing mk = new Marketing();
        mk.setMarketingType("1");
        return new ModelAndView(MarketingPath.CREATEMARKETING).addObject("mk", mk).addObject(CODEXLIST, codexService.selectCodexListUseBox())
                .addObject(BRANDLIST, goodsBrandService.queryAllBrandList()).addObject("pre", marketingService.selectAll())
                .addObject("sx", request.getSession().getAttribute(CSRFTokenManager.CSRF_TOKEN_FOR_SESSION_ATTR_NAME).toString())
                .addObject(THIRDCATELIST, this.goodsCateService.queryAllGoodThirdCate());
    }

    /**
     * 跳转添加商品促销页面
     * 
     * @return ModelAndView
     */
    @RequestMapping("/toaddgoodsmarketing")
    public ModelAndView toAddGoodsMarketing(HttpServletRequest request) {
        Marketing mk = new Marketing();
        mk.setMarketingType("0");
        return new ModelAndView(MarketingPath.CREATEMARKETING).addObject("mk", mk).addObject(CODEXLIST, codexService.selectCodexListUseBox())
                .addObject(BRANDLIST, goodsBrandService.queryAllBrandList()).addObject("pre", marketingService.selectAll())
                .addObject("sx", request.getSession().getAttribute(CSRFTokenManager.CSRF_TOKEN_FOR_SESSION_ATTR_NAME).toString())
                .addObject(THIRDCATELIST, this.goodsCateService.queryAllGoodThirdCate());
    }

    /**
     * 跳转添加抢购促销页面
     * 
     * @return ModelAndView
     */
    @RequestMapping("/toaddgoodspainc")
    public ModelAndView toAddGoodsPainc(HttpServletRequest request) {
        Marketing mk = new Marketing();
        mk.setMarketingType("0");
        return new ModelAndView(MarketingPath.CREATEGOODSPAINC).addObject("mk", mk).addObject(CODEXLIST, codexService.selectCodexListUseBox())
                .addObject(BRANDLIST, goodsBrandService.queryAllBrandList()).addObject("pre", marketingService.selectAll())
                .addObject("sx", request.getSession().getAttribute(CSRFTokenManager.CSRF_TOKEN_FOR_SESSION_ATTR_NAME).toString())
                .addObject(THIRDCATELIST, this.goodsCateService.queryAllGoodThirdCate());
    }

    /**
     * 跳转添加团购促销页面
     * 
     * @return ModelAndView
     */
    @RequestMapping("/toaddgoodsgroup")
    public ModelAndView toAddGoodsGroup(HttpServletRequest request) {
        Marketing mk = new Marketing();
        mk.setMarketingType("0");
        return new ModelAndView(MarketingPath.CREATEGOODSGROUP).addObject("mk", mk).addObject(CODEXLIST, codexService.selectCodexListUseBox())
                .addObject(BRANDLIST, goodsBrandService.queryAllBrandList()).addObject("pre", marketingService.selectAll())
                .addObject("sx", request.getSession().getAttribute(CSRFTokenManager.CSRF_TOKEN_FOR_SESSION_ATTR_NAME).toString())
                .addObject(THIRDCATELIST, this.goodsCateService.queryAllGoodThirdCate());
    }

    /**
     * 准备修改促销
     * 
     * @param marketingId
     * @return ModelAndView
     */
    @RequestMapping("/toupdatemarketing")
    public ModelAndView toupdateMarketing(Long marketingId) {

        return new ModelAndView(MarketingPath.UPDATEMARKETING).addObject(MARKETING, marketingService.marketingDetail(marketingId))
                .addObject("skulist", marketingService.selectMarketingScope(marketingId, "2")).addObject(CODEXLIST, codexService.selectCodexListUseBox())
                .addObject(BRANDLIST, goodsBrandService.queryAllBrandList()).addObject("pre", marketingService.selectAll());
    }

    /**
     * 修改订单促销
     * 
     * @param request
     * @param marketing
     * @param sTime
     * @param eTime
     * @return ModelAndView
     */
    @RequestMapping("/doupdatemarketing")
    public ModelAndView doUpdateMarketing(HttpServletRequest request, Marketing marketing, String sTime, String eTime) {

        SimpleDateFormat formatDate = new SimpleDateFormat(DATETIME);

        try {
            marketing.setMarketingBegin(formatDate.parse(sTime));
            marketing.setMarketingEnd(formatDate.parse(eTime));
        } catch (ParseException e) {
            LOGGER.error("转换异常" + e);
        }
        // 非空验证 促销名称
        if (null != marketing.getMarketingName()) {
            LOGGER.info("修改订单促销,促销的名称为：" + marketing.getMarketingName());
        }

        // 获取促销规则类型
        String codexType = request.getParameter(CODEXTYPE);
        marketingService.doUpdateMarketing(marketing, codexType, request);
        if ("1".equals(marketing.getMarketingType())) {
            return new ModelAndView(new RedirectView(MarketingPath.INITORDERMARKETING));
        } else {
            return new ModelAndView(new RedirectView(MarketingPath.INITGOODSMARKETING));
        }

    }

    /**
     * 添加促销信息
     * 
     * @param request
     * @param marketing
     * @return ModelAndView
     */
    @RequestMapping("/doaddmarketing")
    public ModelAndView doAddMarketing(HttpServletRequest request, @Valid Marketing marketing, String sTime, String eTime, String status, BindingResult result) {
        SimpleDateFormat formatDate = new SimpleDateFormat(DATETIME);
        try {
            marketing.setMarketingBegin(formatDate.parse(sTime));
            marketing.setMarketingEnd(formatDate.parse(eTime));
        } catch (ParseException e) {
            // 日志
            String operaCode = "商品促销";
            String operaContent = request.getSession().getAttribute(CustomerConstantStr.OPERAPATH) + "添加商品促销出错";
            OperaLogUtil.addOperaLog(request, request.getSession().getAttribute(CustomerConstantStr.NAME).toString(), operaCode, operaContent);
        }
        if (status == null) {
            throw new RuntimeException("商品促销类型不正确");
        }

        // 获取促销规则类型
        String codexType = request.getParameter(CODEXTYPE);
        // 非空验证 促销名称
        if (null != marketing.getMarketingName()) {
            LOGGER.info("新增促销信息,促销的名称为：" + marketing.getMarketingName());
        }
        marketingService.doAddMarketing(marketing, codexType, request, status);
        if ("1".equals(marketing.getMarketingType())) {
            return new ModelAndView(new RedirectView(MarketingPath.INITORDERMARKETING));
        } else {
            if ("10".equals(codexType)) {
                return new ModelAndView(new RedirectView(GROUPGOODSLIST_HTM));
            } else if ("11".equals(codexType)) {
                return new ModelAndView(new RedirectView(PANICGOODSLIST_HTM));
            } else {
                return new ModelAndView(new RedirectView(MarketingPath.INITGOODSMARKETING));
            }

        }
    }

    /**
     * 添加促销信息
     * 
     * @param request
     * @param marketing
     * @return ModelAndView
     */
    @RequestMapping("/newdoaddmarketing")
    public ModelAndView newDoAddMarketing(HttpServletRequest request, @Valid Marketing marketing, String sTime, String eTime, String status, BindingResult result, Long[] lelvelId,
            Long[] promotionLogoId) {
        SimpleDateFormat formatDate = new SimpleDateFormat(DATETIME);

        try {
            marketing.setMarketingBegin(formatDate.parse(sTime));
            marketing.setMarketingEnd(formatDate.parse(eTime));
        } catch (ParseException e) {
            // 日志
            String operaCode = "商品促销";
            String operaContent = request.getSession().getAttribute(CustomerConstantStr.OPERAPATH) + "添加商品促销出错";
            OperaLogUtil.addOperaLog(request, request.getSession().getAttribute(CustomerConstantStr.NAME).toString(), operaCode, operaContent);
        }
        if (status == null) {
            throw new RuntimeException("商品促销类型不正确");
        }

        // 获取促销规则类型
        String codexType = request.getParameter(CODEXTYPE);
        // 非空验证 促销名称
        if (null != marketing.getMarketingName()) {
            LOGGER.info("新增促销信息,促销的名称为：" + marketing.getMarketingName());
        }

        marketingService.newDoAddMarketing(marketing, codexType, request, status, lelvelId, promotionLogoId);
        if ("1".equals(marketing.getMarketingType())) {
            return new ModelAndView(new RedirectView(MarketingPath.INITORDERMARKETING));
        } else {
            if ("10".equals(codexType)) {
                return new ModelAndView(new RedirectView(GROUPGOODSLIST_HTM));
            } else if ("11".equals(codexType)) {
                return new ModelAndView(new RedirectView(PANICGOODSLIST_HTM));
            } else {
                return new ModelAndView(new RedirectView(MarketingPath.INITGOODSMARKETING));
            }

        }
    }

    /**
     * 修改促销信息
     * 
     * @param request
     * @param marketing
     * @param sTime
     * @param eTime
     * @param status
     * @return
     */
    @RequestMapping("/domodifymarketing")
    public ModelAndView doModifyMarketing(HttpServletRequest request, @Valid Marketing marketing, String sTime, String eTime, String status) {
        SimpleDateFormat formatDate = new SimpleDateFormat(DATETIME);
        try {
            marketing.setMarketingBegin(formatDate.parse(sTime));
            marketing.setMarketingEnd(formatDate.parse(eTime));
        } catch (ParseException e) {
            // 日志
            String operaCode = "商品促销";
            String operaContent = request.getSession().getAttribute(CustomerConstantStr.OPERAPATH) + "修改商品促销出错";
            OperaLogUtil.addOperaLog(request, request.getSession().getAttribute(CustomerConstantStr.NAME).toString(), operaCode, operaContent);
        }
        // 获取促销规则类型
        String codexType = request.getParameter(CODEXTYPE);
        marketingService.doModifyMarketing(marketing, codexType, request, status);
        return new ModelAndView(new RedirectView("marketgoodslist.htm"));
    }

    /**
     * 根据参数查询商品促销信息
     *
     * @param goodsInfoId
     *            货品ID
     * @param brandId
     *            品牌ID
     * @param cateId
     *            分类ID
     * @return 查询到的促销集合
     */
    @RequestMapping("/queryGoodsMarket")
    @ResponseBody
    public List<Marketing> queryProductMarketBySomeId(Long goodsInfoId, Long brandId, Long cateId) {

        // 查询并返回
        return this.marketingService.selectMarketingByGoodsInfoId(goodsInfoId, brandId, cateId);
    }

    /**
     * 删除营销信息
     * 
     * @param marketingId
     * @return ModelAndView
     */
    @RequestMapping("/delmarketingbyid")
    public ModelAndView delMarketingById(Long marketingId, String codexType) {
        Marketing marketing = marketingService.marketingDetailNotTime(marketingId);
        // 非空验证 促销名称
        if (null != marketing.getMarketingName()) {
            LOGGER.info("删除营销信息,删除的营销信息名称为：" + marketing.getMarketingName());
        }
        marketingService.deleteMarketingById(marketing);
        if ("1".equals(marketing.getMarketingType())) {
            return new ModelAndView(new RedirectView(MarketingPath.INITORDERMARKETING));
        } else {
            if ("10".equals(codexType)) {
                return new ModelAndView(new RedirectView(GROUPGOODSLIST_HTM));
            } else if ("11".equals(codexType)) {
                return new ModelAndView(new RedirectView(PANICGOODSLIST_HTM));
            }
            return new ModelAndView(new RedirectView(MarketingPath.INITGOODSMARKETING));
        }
    }

    /**
     * 批量删除促销
     * 
     * @param marketingId
     * @param marketingType
     * @return ModelAndView
     */
    @RequestMapping("/delallmarketingbyids")
    public ModelAndView delAllMarketingByIds(Long[] marketingId, String marketingType, String codexType) {
        marketingService.delAllMarketingByIds(marketingId, null);
        if ("1".equals(marketingType)) {
            return new ModelAndView(new RedirectView(MarketingPath.INITORDERMARKETING));
        } else {
            if ("10".equals(codexType)) {
                return new ModelAndView(new RedirectView(GROUPGOODSLIST_HTM));
            } else if ("11".equals(codexType)) {
                return new ModelAndView(new RedirectView(PANICGOODSLIST_HTM));
            }
            return new ModelAndView(new RedirectView(MarketingPath.INITGOODSMARKETING));
        }
    }

    /**
     * ajax移除
     * 
     * @param goodsId
     * @param codexId
     * @param marketingId
     * @return
     */
    @RequestMapping("/removegoodsbymIdandgid")
    @ResponseBody
    public int removeGoodsByMidAndGid(Long goodsId, Long codexId, Long marketingId) {
        int count = -1;
        if (codexId != 15) {
            // 非折扣促销
            count = marketingService.removeGoodsByMidAndGid(goodsId, marketingId);
        } else {
            // 折扣促销
            // 商品查询所有货品
            List<GoodsProduct> product = goodsProductService.queryProductsByGoodsId(goodsId);
            for (int i = 0; i < product.size(); i++) {
                count = marketingService.removeMarketByMidAndGid(product.get(i).getGoodsInfoId(), marketingId);
            }

        }
        return count;
    }

    /**
     * 根据分组编号查询促销规则
     * 
     * @param codexStatus
     * @return 分组下的促销规则
     * @author NINGPAI-LIH
     */
    @RequestMapping("/querycodexListbyparam")
    @ResponseBody
    public List<Codex> queryCodexListByParam(Long codexStatus) {
        // 日志记录
        LOGGER.info("根据分组编号【" + codexStatus + "】查询促销规则");
        return codexService.queryCodexListByParam(codexStatus);
    }

    // 促销分组
    /**
     * 添加促销分组
     * 
     * @return ModelAndView
     */
    @RequestMapping("/insertmaketinggroup")
    public ModelAndView insertMaketingGroup(MarketingGroup group) {
        // 非空验证 营销名称
        if (null != group.getPreferentialName()) {
            LOGGER.info("新增的促销分组名称为：" + group.getPreferentialName());
        }
        // 插入信息
        marketingService.insertMarketingGroup(group);
        return new ModelAndView(new RedirectView(QUERYPRELIST_HTM));
    }

    /**
     * 根据id删除促销分组
     * 
     * @return ModelAndView
     */
    @RequestMapping("/delmarketinggroupbyparam")
    public ModelAndView delMarketingGroupByParam(Long groupId) {
        marketingService.deleteByPrimaryKey(groupId);
        return new ModelAndView(new RedirectView(QUERYPRELIST_HTM));
    }

    /**
     * 根据id修改促销分组
     * 
     * @return ModelAndView
     */
    @RequestMapping("/updatemarketing")
    public ModelAndView updateMarketing(MarketingGroup group) {
        // 非空验证 促销分组名称是否为空
        if (null != group.getPreferentialName()) {
            // 日志记录
            LOGGER.info("修改促销分组，修改的促销分组名称为：" + group.getPreferentialName());
        }
        marketingService.updateByPrimaryKeySelective(group);
        return new ModelAndView(new RedirectView(QUERYPRELIST_HTM));
    }

    /**
     * 根据分组id查询分组下是否存在规则
     * 
     * @param groupId
     *            分组id
     * @return 分组下的总数量
     */
    @RequestMapping("/delgroupbycodexis")
    @ResponseBody
    public int delGroupByCodexIs(Long groupId) {
        return marketingService.delGroupByCodexIs(groupId);
    }

    /**
     * 全部的促销分组
     * 
     * @param pb
     *            分页实体bean
     * @param preferentialName
     *            查询参数
     * @return ModelAndView
     */
    @RequestMapping("/queryprelist")
    public ModelAndView queryprelist(PageBean pb, String preferentialName, HttpServletRequest request) {
        pb.setUrl("/queryprelist.htm");
        return new ModelAndView(MarketingPath.PREFERGROUPINFO).addObject("pb", marketingService.selectByPrimary(pb, preferentialName))
                .addObject("preferentialName", preferentialName).addObject("sx", request.getSession().getAttribute(CSRFTokenManager.CSRF_TOKEN_FOR_SESSION_ATTR_NAME).toString());
    }

    /**
     * 新跳转促销页面
     * 
     * @param marketingId
     *            促销id{@link java.lang.Long}
     * @return
     */
    @RequestMapping("/modifybossmarketing")
    public ModelAndView newModifyBossMarketing(Long marketingId, String marketingFlag) {
        // 习题时间
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATETIME);
        Date date = new Date();
        String time = simpleDateFormat.format(date);
        List<Codex>  ce= codexService.selectCodexListUseBox();
        // 修改促销页面
        return new ModelAndView("jsp/marketing/modifymarketing")
        // 所有三级分类
                .addObject(THIRDCATELIST, goodsCateService.queryAllGoodThirdCate())
                // 所有品牌
                .addObject("allbrandlist", goodsBrandService.queryAllBrandList())
                // 促销活动对象
                .addObject(MARKETING, marketingService.marketingDetailNotTime(marketingId))
                // 促销类型
                .addObject("marketingFlag", marketingFlag)
                // 促销分类
                .addObject("catelist", marketingService.selectMarketingScope(marketingId, "0"))
                // 促销品牌
                .addObject(BRANDLIST, marketingService.selectMarketingScope(marketingId, "1"))
                // 促销商品
                .addObject("kulist", marketingService.selectMarketingScope(marketingId, "2"))
                // 促销会员等级
                .addObject("customerLevel", pointLevelServiceMapper.selectAllPointLevel())
                // 促销优惠券
                .addObject("couponlist", couponService.selectCouponListByAble())
                // 促销logo
                .addObject("logolist", logoService.queryAllLogoList())
                // 促销分类
                .addObject(THIRDCATELIST, goodsCateService.queryAllGoodThirdCate())
                // 促销分类
                .addObject(CODEXLIST, codexService.selectCodexListUseBox())
                // 获取系统当前时间
                .addObject("nowtime", time);

    }

    /**
     * 新修改促销信息
     * 
     * @param request
     * @param marketing
     * @param sTime
     * @param eTime
     * @param status
     * @return
     */
    @RequestMapping("/newdomodifymarketing")
    public ModelAndView newDoModifyMarketing(HttpServletRequest request, @Valid Marketing marketing, String sTime, String eTime, String status, Long[] lelvelId,
            Long[] promotionLogoId) {
        SimpleDateFormat formatDate = new SimpleDateFormat(DATETIME);
        try {
            marketing.setMarketingBegin(formatDate.parse(sTime));
            marketing.setMarketingEnd(formatDate.parse(eTime));
        } catch (ParseException e) {
            // 日志
            String operaCode = "商品促销";
            String operaContent = request.getSession().getAttribute(CustomerConstantStr.OPERAPATH) + "修改商品促销出错";
            OperaLogUtil.addOperaLog(request, request.getSession().getAttribute(CustomerConstantStr.NAME).toString(), operaCode, operaContent);
        }
        // 获取赠送的积分
        String giveIntegral = request.getParameter("giveIntegral");
        if (giveIntegral == null || "".equals(giveIntegral)) {
            marketing.setGiveIntegral(0);
        }
        // 获取促销规则类型
        String codexType = request.getParameter(CODEXTYPE);
        if(marketing.getBusinessId()==null){
            marketing.setBusinessId(0L);
        }
        marketingService.newDoUpdateMarketing(marketing, codexType, status, request, lelvelId, promotionLogoId);
        if ("10".equals(codexType)) {
            return new ModelAndView(new RedirectView(GROUPGOODSLIST_HTM));
        } else if ("11".equals(codexType)) {
            return new ModelAndView(new RedirectView(PANICGOODSLIST_HTM));
        }
        return new ModelAndView(new RedirectView(MarketingPath.INITGOODSMARKETING));

    }

}
