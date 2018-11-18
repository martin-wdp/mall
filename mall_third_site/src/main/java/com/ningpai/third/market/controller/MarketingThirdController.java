/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.third.market.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import com.ningpai.common.util.ConstantUtil;
import com.ningpai.coupon.service.CouponService;
import com.ningpai.customer.service.PointLevelServiceMapper;
import com.ningpai.goods.service.GoodsBrandService;
import com.ningpai.goods.service.GoodsCateService;
import com.ningpai.goods.service.GoodsProductService;
import com.ningpai.marketing.bean.Codex;
import com.ningpai.marketing.bean.Marketing;
import com.ningpai.marketing.service.CodexService;
import com.ningpai.marketing.service.MarketingService;
import com.ningpai.marketing.service.PromotionLogoService;
import com.ningpai.third.goods.service.ThirdCateService;
import com.ningpai.third.grandbrand.service.GrandBrandService;
import com.ningpai.third.market.service.ThirdMarketingService;
import com.ningpai.third.market.util.ThirdMarketPath;
import com.ningpai.third.util.MenuOperationUtil;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;
import com.ningpai.util.SelectBean;

/**
 * <p>
 * 商家促销
 * </p>
 * 
 * @authorzhanghl
 * @since 2014年4月15日 下午4:02:38
 * @version 2.0
 */
@Controller
public class MarketingThirdController {

    // 操作日志对象
    private static final MyLogger LOGGER = new MyLogger(MarketingThirdController.class);

    private static final String THIRDID = "thirdId";
    private static final String DATETIME = "yyyy-MM-dd HH:mm:ss";
    private static final String NOWDATE = "nowdate";
    private static final String CODEXLIST = "codexList";
    private static final String BRANDLIST = "brandlist";
    private static final String CUSTOMERLEVEL = "customerLevel";
    private static final String COUPONLIST = "couponlist";
    private static final String LOGOLIST = "logolist";
    private static final String THIRDCATELIST = "thirdCateList";
    private static final String PROLIST = "prolist";
    private static final String CODEXLISTX = "codexlist";
    private static final String LOGGERINFO = "添加促销时转换时间错误：";
    private static final String CODEXTYPE = "codexType";
    private static final String MARKETING = "marketing";

    // 促销规则接口
    private CodexService codexService;

    // 商品名牌接口
    private GoodsBrandService goodsBrandService;

    // 促销接口
    private MarketingService marketingService;

    // 品牌授权管理
    private GrandBrandService brandService;

    // 第三方商品分类
    private ThirdCateService cateService;

    @Resource(name = "ThirdMarketingService")
    private ThirdMarketingService thirdMarketingService;

    // 会员等级
    @Resource(name = "pointLevelServiceMapper")
    private PointLevelServiceMapper pointLevelServiceMapper;

    // 优惠券
    @Resource(name = "CouponService")
    private CouponService couponService;

    // 促销LOGO
    @Resource(name = "PromotionLogoService")
    private PromotionLogoService logoService;

    @Resource(name = "GoodsCateService")
    private GoodsCateService goodsCateService;

    @Resource(name = "GoodsProductService")
    private GoodsProductService goodsProductService;

    /**
     * 查询单品商品促销列表
     * 
     * @param pageBean
     * @param marketing
     * @return ModelAndView
     */
    @RequestMapping("/thirdproductmarketing")
    public ModelAndView thirdProductMarketing(PageBean pageBean, HttpServletRequest request, Marketing marketing, String n, String l) {
        // 设置分页跳转的路径
        pageBean.setUrl(ThirdMarketPath.INITMARKETGOODSLIST);
        MenuOperationUtil.fillSessionMenuIndex(request, n, l);
        // 商品促销
        marketing.setMarketingType("0");
        // 获取店铺ID
        marketing.setBusinessId((Long) request.getSession().getAttribute(THIRDID));
        // 设置日期格式
        SimpleDateFormat df = new SimpleDateFormat(DATETIME);
        // 获取单品促销列表
        return new ModelAndView(ThirdMarketPath.MARKETGOODSLIST).addObject("pb", marketingService.marketList(pageBean, marketing, "DP")).addObject(NOWDATE, df.format(new Date()));
    }

    /**
     * 折扣促销列表
     * 
     * @param pageBean
     *            分页对象
     * @param request
     * @param marketing
     *            促销信息
     * @param n
     * @param l
     * @return
     */
    @RequestMapping("/createzkmarketing")
    public ModelAndView thirdZKProductMarketing(PageBean pageBean, HttpServletRequest request, Marketing marketing, String n, String l) {
        // 设置要跳转的路径
        pageBean.setUrl(ThirdMarketPath.INITMARKETZKSLIST);
        MenuOperationUtil.fillSessionMenuIndex(request, n, l);
        // 商品促销
        marketing.setMarketingType("0");
        // 获取店铺ID
        marketing.setBusinessId((Long) request.getSession().getAttribute(THIRDID));
        // 设置日期格式
        SimpleDateFormat df = new SimpleDateFormat(DATETIME);
        // 获取单品促销列表
        return new ModelAndView(ThirdMarketPath.MARKETZKGOODSLIST).addObject("pb", marketingService.marketList(pageBean, marketing, "ZK"))
                .addObject(NOWDATE, df.format(new Date()));
    }

    /**
     * 包邮促销列表
     * 
     * @param pageBean
     *            分页对象
     * @param request
     * @param marketing
     *            促销信息对象
     * @param n
     * @param l
     * @return
     */
    @RequestMapping("/createbymarketing")
    public ModelAndView thirdBYProductMarketing(PageBean pageBean, HttpServletRequest request, Marketing marketing, String n, String l) {
        // 设置要跳转的页面路径
        pageBean.setUrl(ThirdMarketPath.INITMARKETBYSLIST);
        // 填充导航/左侧菜单索引 用于页面控制css选中
        MenuOperationUtil.fillSessionMenuIndex(request, n, l);
        // 商品促销
        marketing.setMarketingType("0");
        // 获取店铺ID
        marketing.setBusinessId((Long) request.getSession().getAttribute(THIRDID));
        // 设置日期格式
        SimpleDateFormat df = new SimpleDateFormat(DATETIME);
        // 获取单品促销列表
        return new ModelAndView(ThirdMarketPath.MARKETBYGOODSLIST).addObject("pb", marketingService.marketList(pageBean, marketing, "BY"))
                .addObject(NOWDATE, df.format(new Date()));
    }

    /**
     * 满减促销列表
     * 
     * @param pageBean
     *            分页对象
     * @param request
     * @param marketing
     *            促销信息对象
     * @param n
     * @param l
     * @return
     */
    @RequestMapping("/createmjomarketing")
    public ModelAndView thirdMJOProductMarketing(PageBean pageBean, HttpServletRequest request, Marketing marketing, String n, String l) {
        pageBean.setUrl(ThirdMarketPath.INITMARKETMJOSLIST);
        MenuOperationUtil.fillSessionMenuIndex(request, n, l);
        // 商品促销
        marketing.setMarketingType("0");
        // 获取店铺ID
        marketing.setBusinessId((Long) request.getSession().getAttribute(THIRDID));
        // 设置日期格式
        SimpleDateFormat df = new SimpleDateFormat(DATETIME);
        // 获取单品促销列表
        return new ModelAndView(ThirdMarketPath.MARKETMJOGOODSLIST).addObject("pb", marketingService.marketList(pageBean, marketing, "MJO")).addObject(NOWDATE,
                df.format(new Date()));
    }

    /**
     * 满件促销列表
     * 
     * @param pageBean
     *            分页对象
     * @param request
     * @param marketing
     *            满减对象
     * @param n
     * @param l
     * @return
     */
    @RequestMapping("/createmjpmarketing")
    public ModelAndView thirdMJpProductMarketing(PageBean pageBean, HttpServletRequest request, Marketing marketing, String n, String l) {
        // 设置要跳转的url路径
        pageBean.setUrl(ThirdMarketPath.INITMARKETMJPSLIST);
        // 填充导航/左侧菜单索引 用于页面控制css选中
        MenuOperationUtil.fillSessionMenuIndex(request, n, l);
        // 商品促销
        marketing.setMarketingType("0");
        // 获取店铺ID
        marketing.setBusinessId((Long) request.getSession().getAttribute(THIRDID));
        // 设置日期格式
        SimpleDateFormat df = new SimpleDateFormat(DATETIME);
        // 获取单品促销列表
        return new ModelAndView(ThirdMarketPath.MARKETMJPGOODSLIST).addObject("pb", marketingService.marketList(pageBean, marketing, "MJP")).addObject(NOWDATE,
                df.format(new Date()));
    }

    /**
     * 添加单品促销页面
     * 
     * @return
     */
    @RequestMapping("/adddpmarketing")
    public ModelAndView addDpMarketing(HttpServletRequest request, String n, String l) {
        // 查询所有促销规则
        List<Codex> codexList = codexService.selectCodexListUseBox();
        // 填充导航/左侧菜单索引 用于页面控制css选中
        MenuOperationUtil.fillSessionMenuIndex(request, n, l);
        return new ModelAndView(ThirdMarketPath.DPMARKETING)
        // 查询所有促销规则
                .addObject(CODEXLIST, codexList)
                // 查询全部品牌
                .addObject(BRANDLIST, brandService.queryAllGoodsGrandBrand((Long) request.getSession().getAttribute(THIRDID)))
                // 查询所有会员等级 用于添加会员时填充会员等级下拉列表
                .addObject(CUSTOMERLEVEL, pointLevelServiceMapper.selectAllPointLevel())
                // 根据店铺ID查询优惠券
                .addObject(COUPONLIST, couponService.selectCouponListByStoreId((Long) request.getSession().getAttribute(THIRDID)))
                // 查询所有的促销logo集合
                .addObject(LOGOLIST, logoService.queryAllLogoList())
                // 查询所有三级分类
                .addObject(THIRDCATELIST, goodsCateService.queryAllGoodThirdCate())
                // 查询第三方货品列表
                .addObject(PROLIST, goodsProductService.queryProductForMarketing((Long) request.getSession().getAttribute(THIRDID)));
    }

    /**
     * 添加折扣促销页面
     * 
     * @return
     */
    @RequestMapping("/addzkmarketing")
    public ModelAndView addZkMarketing(HttpServletRequest request, String n, String l) {
        // 查询所有促销规则
        List<Codex> codexList = codexService.selectCodexListUseBox();
        // 填充导航/左侧菜单索引 用于页面控制css选中
        MenuOperationUtil.fillSessionMenuIndex(request, n, l);
        return new ModelAndView(ThirdMarketPath.ZKMARKETING)
        // //查询所有促销规则
                .addObject(CODEXLIST, codexList)
                // 查询全部品牌
                .addObject(BRANDLIST, brandService.queryAllGoodsGrandBrand((Long) request.getSession().getAttribute(THIRDID)))
                // 查询所有会员等级 用于添加会员时填充会员等级下拉列表
                .addObject(CUSTOMERLEVEL, pointLevelServiceMapper.selectAllPointLevel())
                // 根据店铺ID查询优惠券
                .addObject(COUPONLIST, couponService.selectCouponListByStoreId((Long) request.getSession().getAttribute(THIRDID)))
                // 查询所有的促销logo集合
                .addObject(LOGOLIST, logoService.queryAllLogoList())
                // 查询所有三级分类
                .addObject(THIRDCATELIST, goodsCateService.queryAllGoodThirdCate())
                // 查询第三方货品列表
                .addObject(PROLIST, goodsProductService.queryProductForMarketing((Long) request.getSession().getAttribute(THIRDID)));
    }

    /**
     * 添加包邮促销页面
     * 
     * @return
     */
    @RequestMapping("/addbymarketing")
    public ModelAndView addByMarketing(HttpServletRequest request, String n, String l) {
        // 查询所有促销规则
        List<Codex> codexList = codexService.selectCodexListUseBox();
        // 填充导航/左侧菜单索引 用于页面控制css选中
        MenuOperationUtil.fillSessionMenuIndex(request, n, l);
        return new ModelAndView(ThirdMarketPath.BYMARKETING)
        // 查询所有促销规则
                .addObject(CODEXLIST, codexList)
                // 查询全部品牌
                .addObject(BRANDLIST, brandService.queryAllGoodsGrandBrand((Long) request.getSession().getAttribute(THIRDID)))
                // 查询所有会员等级 用于添加会员时填充会员等级下拉列表
                .addObject(CUSTOMERLEVEL, pointLevelServiceMapper.selectAllPointLevel())
                // 根据店铺ID查询优惠券
                .addObject(COUPONLIST, couponService.selectCouponListByStoreId((Long) request.getSession().getAttribute(THIRDID)))
                // 查询所有的促销logo集合
                .addObject(LOGOLIST, logoService.queryAllLogoList())
                // 查询所有三级分类
                .addObject(THIRDCATELIST, goodsCateService.queryAllGoodThirdCate())
                // 查询第三方货品列表
                .addObject(PROLIST, goodsProductService.queryProductForMarketing((Long) request.getSession().getAttribute(THIRDID)));
    }

    /**
     * 添加满减促销页面
     * 
     * @return
     */
    @RequestMapping("/addmjomarketing")
    public ModelAndView addMjoMarketing(HttpServletRequest request, String n, String l) {
        // 查询所有促销规则addmjpmarketing
        List<Codex> codexList = codexService.selectCodexListUseBox();
        // 填充导航/左侧菜单索引 用于页面控制css选中
        MenuOperationUtil.fillSessionMenuIndex(request, n, l);
        // 设置需要跳转的路径
        return new ModelAndView(ThirdMarketPath.MJOMARKETING)
        // 促销规则
                .addObject(CODEXLIST, codexList)
                // 询全部品牌
                .addObject(BRANDLIST, brandService.queryAllGoodsGrandBrand((Long) request.getSession().getAttribute(THIRDID)))
                // 查询所有会员等级 用于添加会员时填充会员等级下拉列表
                .addObject(CUSTOMERLEVEL, pointLevelServiceMapper.selectAllPointLevel())
                // 根据店铺ID查询优惠券
                .addObject(COUPONLIST, couponService.selectCouponListByStoreId((Long) request.getSession().getAttribute(THIRDID)))
                // 查询所有的促销logo集合
                .addObject(LOGOLIST, logoService.queryAllLogoList())
                // 查询所有三级分类
                .addObject(THIRDCATELIST, goodsCateService.queryAllGoodThirdCate())
                // 查询第三方货品列表
                .addObject(PROLIST, goodsProductService.queryProductForMarketing((Long) request.getSession().getAttribute(THIRDID)));
    }

    /**
     * 添加满件促销页面
     * 
     * @return
     */
    @RequestMapping("/addmjpmarketing")
    public ModelAndView addMjpMarketing(HttpServletRequest request, String n, String l) {
        // 查询所有促销规则
        List<Codex> codexList = codexService.selectCodexListUseBox();
        // 填充导航/左侧菜单索引 用于页面控制css选中
        MenuOperationUtil.fillSessionMenuIndex(request, n, l);
        // 设置需要跳转的路径
        return new ModelAndView(ThirdMarketPath.MJPMARKETING)
        // 促销规则
                .addObject(CODEXLIST, codexList)
                // 询全部品牌
                .addObject(BRANDLIST, brandService.queryAllGoodsGrandBrand((Long) request.getSession().getAttribute(THIRDID)))
                // 查询所有会员等级 用于添加会员时填充会员等级下拉列表
                .addObject(CUSTOMERLEVEL, pointLevelServiceMapper.selectAllPointLevel())
                // 根据店铺ID查询优惠券
                .addObject(COUPONLIST, couponService.selectCouponListByStoreId((Long) request.getSession().getAttribute(THIRDID)))
                // 根据店铺ID查询优惠券
                .addObject(LOGOLIST, logoService.queryAllLogoList())
                // 查询所有三级分类
                .addObject(THIRDCATELIST, goodsCateService.queryAllGoodThirdCate())
                // 查询第三方货品列表
                .addObject(PROLIST, goodsProductService.queryProductForMarketing((Long) request.getSession().getAttribute(THIRDID)));
    }

    /**
     * 查询订单促销列表
     * 
     * @param pageBean
     * @param marketing
     * @return ModelAndView
     */
    @RequestMapping("/thirdordermarketing")
    public ModelAndView thirdOrderMarketing(PageBean pageBean, HttpServletRequest request, Marketing marketing, String marketingBeginTime, String marketingEndTime, String n,
            String l) {
        // 查询所有促销规则
        pageBean.setUrl(ThirdMarketPath.INITMARKETORDERLIST);
        // 填充导航/左侧菜单索引 用于页面控制css选中
        MenuOperationUtil.fillSessionMenuIndex(request, n, l);
        // 设置促销类型
        marketing.setMarketingType("1");
        // 设置促销对应的商家ID
        marketing.setBusinessId((Long) request.getSession().getAttribute(THIRDID));
        // 设置要跳转的页面
        return new ModelAndView(ThirdMarketPath.MARKETORDERSLIST)
        // 查询促销列表
                .addObject("pageBean", marketingService.marketOrderList(pageBean, marketing, marketingBeginTime, marketingEndTime));

    }

    /**
     * 跳转添加商品促销
     * 
     * @return
     */
    @RequestMapping("/toaddgoodsmarketingthird")
    public ModelAndView toAddGoodsMarketing(HttpServletRequest request, SelectBean selectBean) {
        // 营销信息
        Marketing mk = new Marketing();
        // 设置营销的类型
        mk.setMarketingType("0");
        // 要跳转的页面
        return new ModelAndView(ThirdMarketPath.CREATEMARKETING)
        // 营销信息
                .addObject("mk", mk)
                // 询所有促销规则
                .addObject(CODEXLISTX, codexService.selectCodexListUseBox())
                // 查询全部品牌
                .addObject(BRANDLIST, brandService.queryAllGoodsGrandBrand((Long) request.getSession().getAttribute(THIRDID)))
                // 查询所有的促销分组
                .addObject("pre", marketingService.selectAll());
    }

    /**
     * 跳转添加商品促销
     * 
     * @return
     */
    @RequestMapping("/toaddordersmarketingthird")
    public ModelAndView toAddOrdersMarketing(HttpServletRequest request) {
        // 营销信息
        Marketing mk = new Marketing();
        // 设置营销的类型
        mk.setMarketingType("1");
        // 要跳转的页面
        return new ModelAndView(ThirdMarketPath.CREATEMARKETING)
        // 营销信息
                .addObject("mk", mk)
                // 询所有促销规则
                .addObject(CODEXLISTX, codexService.selectCodexListUseBox())
                // 查询全部品牌
                .addObject(BRANDLIST, brandService.queryAllGoodsGrandBrand((Long) request.getSession().getAttribute(ConstantUtil.THIRDID)))
                // 查询所有促销规则
                .addObject(CODEXLISTX, codexService.selectCodexListUseBox())
                // 查询所有促销规则
                .addObject(BRANDLIST, goodsBrandService.queryAllBrandList())
                // 查询所有的促销分组
                .addObject("pre", marketingService.selectAll());
    }

    /**
     * 添加促销信息
     * 
     * @param request
     * @param marketing
     * @return ModelAndView
     */
    @RequestMapping("/addmarketing")
    public ModelAndView doAddMarketing(HttpServletRequest request, Marketing marketing, String sTime, String eTime) {
        // 商家ID
        marketing.setBusinessId((Long) request.getSession().getAttribute(ConstantUtil.THIRDID));
        // 转换日期格式
        SimpleDateFormat formatDate = new SimpleDateFormat(DATETIME);
        try {
            // 设置开始时间
            marketing.setMarketingBegin(formatDate.parse(sTime));
            // 设置结束时间
            marketing.setMarketingEnd(formatDate.parse(eTime));
        } catch (ParseException e) {
            LOGGER.error(LOGGERINFO + e);
        }
        // 获取促销规则类型
        String codexType = request.getParameter(CODEXTYPE);
        // 插入促销信息
        marketingService.doAddMarketing(marketing, codexType, request);
        // 重定向到设置的页面
        if ("1".equals(marketing.getMarketingType())) {
            return new ModelAndView(new RedirectView(ThirdMarketPath.INITMARKETORDERLIST));
        } else {
            return new ModelAndView(new RedirectView(ThirdMarketPath.INITMARKETGOODSLIST));
        }

    }

    /**
     * 新添加促销信息
     * 
     * @param request
     * @param marketing
     * @return ModelAndView
     */
    @RequestMapping("/newaddmarketing")
    public ModelAndView newDoAddMarketing(HttpServletRequest request, Marketing marketing, String sTime, String eTime, String status, Long[] lelvelId, Long[] promotionLogoId,
            String marketingFlag) {
        // 设置营销对应的商家ID
        marketing.setBusinessId((Long) request.getSession().getAttribute(ConstantUtil.THIRDID));
        // 日期格式转换
        SimpleDateFormat formatDate = new SimpleDateFormat(DATETIME);
        try {
            // 起始日期
            marketing.setMarketingBegin(formatDate.parse(sTime));
            // 终止日期
            marketing.setMarketingEnd(formatDate.parse(eTime));
        } catch (ParseException e) {
            LOGGER.error(LOGGERINFO + e);
        }
        String giveIntegral = request.getParameter("giveIntegral");
        if (giveIntegral == null || "".equals(giveIntegral)) {
            marketing.setGiveIntegral(0);
        }
        // 获取促销规则类型
        String codexType = request.getParameter(CODEXTYPE);
        marketingService.newDoAddMarketing(marketing, codexType, request, status, lelvelId, promotionLogoId);
        if ("DP".equals(marketingFlag)) {
            return new ModelAndView(new RedirectView(ThirdMarketPath.INITMARKETGOODSLIST));
            // 折扣促销
        } else if ("ZK".equals(marketingFlag)) {
            return new ModelAndView(new RedirectView(ThirdMarketPath.INITMARKETZKSLIST));
            // 包邮促销
        } else if ("BY".equals(marketingFlag)) {
            return new ModelAndView(new RedirectView(ThirdMarketPath.INITMARKETBYSLIST));
            // 满减促销
        } else if ("MJO".equals(marketingFlag)) {
            return new ModelAndView(new RedirectView(ThirdMarketPath.INITMARKETMJOSLIST));
        } else {
            return new ModelAndView(new RedirectView(ThirdMarketPath.INITMARKETMJPSLIST));
        }

    }

    /**
     * 跳转修改 促销信息
     * 
     * @param marketingId
     *            需要修改的促销ID
     * @return ModelAndView
     */
    @RequestMapping("/editmarket")
    public ModelAndView editMarket(Long marketingId) {
        // 需要跳转的页面
        return new ModelAndView(ThirdMarketPath.UPDATEMARKETING)
        // 查询促销详细信息
                .addObject(MARKETING, marketingService.marketingDetail(marketingId))
                // 查询范围信息
                .addObject("skulist", marketingService.selectMarketingScope(marketingId, "2"))
                // 查询所有促销规则
                .addObject(CODEXLISTX, codexService.selectCodexListUseBox())
                // 查询所有品牌
                .addObject(BRANDLIST, goodsBrandService.queryAllBrandList());
    }

    /**
     * 跳转修改 促销信息
     * 
     * @param marketingId
     * @return ModelAndView
     */
    @RequestMapping("/marketdetails")
    public ModelAndView marketDetails(Long marketingId) {
        // 跳转的页面
        return new ModelAndView("/marketing/marketdetails")
        // 查询促销详细信息
                .addObject(MARKETING, marketingService.marketingDetail(marketingId))
                // 查询范围信息
                .addObject("skulist", marketingService.selectMarketingScope(marketingId, "2"))
                // 查询所有促销规则
                .addObject(CODEXLISTX, codexService.selectCodexListUseBox())
                // 查询所有品牌
                .addObject(BRANDLIST, goodsBrandService.queryAllBrandList());
    }

    /**
     * 修改促销信息
     * 
     * @param request
     * @param marketing
     *            促销对象
     * @param sTime
     *            促销开始时间
     * @param eTime
     *            促销结束时间
     * @return ModelAndView
     */
    @RequestMapping("/doeditmarket")
    public ModelAndView doUpdateMarketing(HttpServletRequest request, Marketing marketing, String sTime, String eTime) {

        SimpleDateFormat formatDate = new SimpleDateFormat(DATETIME);

        try {
            marketing.setMarketingBegin(formatDate.parse(sTime));
            marketing.setMarketingEnd(formatDate.parse(eTime));
        } catch (ParseException e) {
            LOGGER.error("修改促销时转换时间错误：" + e);
        }

        // 获取促销规则类型
        String codexType = request.getParameter(CODEXTYPE);
        // 修改促销信息
        marketingService.doUpdateMarketing(marketing, codexType, request);
        if ("1".equals(marketing.getMarketingType())) {
            return new ModelAndView(new RedirectView(ThirdMarketPath.INITMARKETORDERLIST));
        } else {
            return new ModelAndView(new RedirectView(ThirdMarketPath.INITMARKETGOODSLIST));
        }

    }

    /**
     * 删除营销信息
     * 
     * @param marketingId
     *            要删除的促销 ID
     * @return ModelAndView
     */
    @RequestMapping("/delmarketing")
    public ModelAndView delMarketingById(Long marketingId) {
        // 根据ID获取单个的促销信息
        Marketing marketing = marketingService.marketingDetail(marketingId);
        // 删除单个的促销信息
        marketingService.deleteMarketingById(marketing);
        // 设置要跳转的页面
        if ("1".equals(marketing.getMarketingType())) {
            return new ModelAndView(new RedirectView(ThirdMarketPath.INITMARKETORDERLIST));
        } else {
            return new ModelAndView(new RedirectView(ThirdMarketPath.INITMARKETGOODSLIST));
        }
    }

    /**
     * 删除营销信息
     * 
     * @param marketingId
     * @return ModelAndView
     */
    @RequestMapping("/delnewmarketing")
    @ResponseBody
    public int newDelMarketingById(Long marketingId, HttpServletRequest request) {
        // 根据ID获取单个的促销信息
        Marketing marketing = marketingService.marketingDetail(marketingId);
        if (null != marketing) {
            // 设置促销对应的商家id
            marketing.setBusinessId((Long) request.getSession().getAttribute(ConstantUtil.THIRDID));
            // 根据ID删除单个的促销信息
            return marketingService.deleteMarketingById(marketing);
        }
        return marketingService.delleteMarkting(marketingId, (Long) request.getSession().getAttribute(ConstantUtil.THIRDID));
    }

    /**
     * 批量删除促销
     * 
     * @param marketingId
     *            要删除的促销ID
     * @param marketingType
     *            促销类型
     * @return ModelAndView
     */
    @RequestMapping("/delmarketings")
    public ModelAndView delAllMarketingByIds(Long[] marketingId, String marketingType, HttpServletRequest request) {
        // 商家ID
        Long businessId = (Long) request.getSession().getAttribute(ConstantUtil.THIRDID);
        // 根据商家ID和促销ID删除促销信息
        marketingService.delAllMarketingByIds(marketingId, businessId);
        // 根据不同的促销类型 跳转到不同的压面
        if ("1".equals(marketingType)) {
            return new ModelAndView(new RedirectView(ThirdMarketPath.INITMARKETORDERLIST));
        } else {
            return new ModelAndView(new RedirectView(ThirdMarketPath.INITMARKETGOODSLIST));
        }
    }

    /**
     * 批量删除促销
     * 
     * @param marketingId
     *            要删除的促销ID
     * @return ModelAndView
     */
    @RequestMapping("/newdelmarketings")
    @ResponseBody
    public int newdelAllMarketingByIds(Long[] marketingId, HttpServletRequest request) {
        // 设置商家ID
        Long businessId = (Long) request.getSession().getAttribute(ConstantUtil.THIRDID);
        // 根据商家ID和促销ID删除单个的促销信息
        return marketingService.delAllMarketingByIds(marketingId, businessId);
    }

    /**
     * 查询店铺所有的分类
     * 
     * @return
     */
    @RequestMapping("/queryAllThirdGoodsCate")
    @ResponseBody
    public List<Object> queryAllThirdGoodsCate(SelectBean selectBean, HttpServletRequest request) {
        // 根据商家ID 获取店铺所有的分类
        return cateService.getAllCalcThirdCate(selectBean, (Long) request.getSession().getAttribute(ConstantUtil.THIRDID));
    }

    /**
     * 新修改促销页面
     * 
     * @param marketingId
     *            促销ID
     * @param marketingFlag
     *            促销状态
     * @return
     */
    @RequestMapping("/neweditmarketing")
    public ModelAndView newEditMarketing(Long marketingId, String marketingFlag, HttpServletRequest request) {
        // 商家ID
        Long thirdId = (Long) request.getSession().getAttribute(THIRDID);
        // 修改促销页面
        return new ModelAndView("/marketing/modifymarketing")
        // 促销活动对象
                .addObject(MARKETING, marketingService.marketingDetailNotTime(marketingId))
                // 促销类型
                .addObject("marketingFlag", marketingFlag)
                // 促销分类范围
                .addObject("catelist", thirdMarketingService.selectThirdMarketingScope(marketingId, "0", thirdId))
                // 促销品牌范围
                .addObject(BRANDLIST, thirdMarketingService.selectThirdMarketingScope(marketingId, "1", thirdId))
                // 促销货品范围
                .addObject("kulist", thirdMarketingService.selectThirdMarketingScope(marketingId, "2", thirdId))
                // 促销会员等级
                .addObject(CUSTOMERLEVEL, pointLevelServiceMapper.selectAllPointLevel())
                // 促销优惠券
                .addObject(COUPONLIST, couponService.selectCouponListByAble())
                // 促销logo
                .addObject(LOGOLIST, logoService.queryAllLogoList())
                // 促销分类
                .addObject(THIRDCATELIST, goodsCateService.queryAllGoodThirdCate())
                // 促销分类
                .addObject(CODEXLIST, codexService.selectCodexListUseBox())
                // 店铺货品
                .addObject(PROLIST, goodsProductService.queryProductForMarketing(thirdId));
    }

    /**
     * 新保存修改后促销信息
     * 
     * @param request
     * @param marketing
     * @return ModelAndView
     */
    @RequestMapping("/newsavemarketing")
    public ModelAndView newDoSaveMarketing(HttpServletRequest request, Marketing marketing, String sTime, String eTime, String status, Long[] lelvelId, Long[] promotionLogoId,
            String marketingFlag) {
        // 设置促销的商家ID
        marketing.setBusinessId((Long) request.getSession().getAttribute(ConstantUtil.THIRDID));
        // 日期格式装换
        SimpleDateFormat formatDate = new SimpleDateFormat(DATETIME);
        try {
            // 促销开始日期
            marketing.setMarketingBegin(formatDate.parse(sTime));
            // 促销结束日期
            marketing.setMarketingEnd(formatDate.parse(eTime));
        } catch (ParseException e) {
            LOGGER.error(LOGGERINFO + e);
        }
        String giveIntegral = request.getParameter("giveIntegral");
        if (giveIntegral == null || "".equals(giveIntegral)) {
            marketing.setGiveIntegral(0);
        }
        // 获取促销规则类型
        String codexType = request.getParameter(CODEXTYPE);
        // 修改促销信息
        marketingService.newDoUpdateMarketing(marketing, codexType, status, request, lelvelId, promotionLogoId);
        if ("DP".equals(marketingFlag)) {
            return new ModelAndView(new RedirectView(ThirdMarketPath.INITMARKETGOODSLIST));
            // 折扣
        } else if ("ZK".equals(marketingFlag)) {
            return new ModelAndView(new RedirectView(ThirdMarketPath.INITMARKETZKSLIST));
            // 包邮
        } else if ("BY".equals(marketingFlag)) {
            return new ModelAndView(new RedirectView(ThirdMarketPath.INITMARKETBYSLIST));
            // 满减
        } else if ("MJO".equals(marketingFlag)) {
            return new ModelAndView(new RedirectView(ThirdMarketPath.INITMARKETMJOSLIST));
        } else {
            return new ModelAndView(new RedirectView(ThirdMarketPath.INITMARKETMJPSLIST));
        }

    }

    public ThirdCateService getCateService() {
        return cateService;
    }

    @Resource(name = "ThirdCateService")
    public void setCateService(ThirdCateService cateService) {
        this.cateService = cateService;
    }

    public GrandBrandService getBrandService() {
        return brandService;
    }

    @Resource(name = "GrandBrandService")
    public void setBrandService(GrandBrandService brandService) {
        this.brandService = brandService;
    }

    public CodexService getCodexService() {
        return codexService;
    }

    @Resource(name = "CodexService")
    public void setCodexService(CodexService codexService) {
        this.codexService = codexService;
    }

    public GoodsBrandService getGoodsBrandService() {
        return goodsBrandService;
    }

    @Resource(name = "GoodsBrandService")
    public void setGoodsBrandService(GoodsBrandService goodsBrandService) {
        this.goodsBrandService = goodsBrandService;
    }

    public MarketingService getMarketingService() {
        return marketingService;
    }

    @Resource(name = "MarketingService")
    public void setMarketingService(MarketingService marketingService) {
        this.marketingService = marketingService;
    }
}
