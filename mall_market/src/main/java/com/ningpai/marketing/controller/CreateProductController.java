package com.ningpai.marketing.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ningpai.coupon.service.CouponService;
import com.ningpai.customer.service.PointLevelServiceMapper;
import com.ningpai.goods.service.GoodsBrandService;
import com.ningpai.goods.service.GoodsCateService;
import com.ningpai.marketing.bean.Codex;
import com.ningpai.marketing.service.CodexService;
import com.ningpai.marketing.service.PromotionLogoService;
import com.ningpai.util.PageBean;

/**
 * 促销信息
 * 
 * @author ggn
 *
 */
@Controller
public class CreateProductController {

    private static final String BRANDLIST = "brandlist";
    private static final String CODEXLIST = "codexList";
    private static final String MARKETFLAG = "marketFlag";
    private static final String CUSTOMERLEVEL = "customerLevel";
    private static final String COUPONLIST = "couponlist";
    private static final String LOGOLIST = "logolist";
    private static final String THIRDCATELIST = "thirdCateList";

    // 会员等级 Spring注入
    @Resource(name = "pointLevelServiceMapper")
    private PointLevelServiceMapper pointLevelServiceMapper;

    // 优惠券 Spring注入
    @Resource(name = "CouponService")
    private CouponService couponService;

    // 促销LOGO Spring注入
    @Resource(name = "PromotionLogoService")
    private PromotionLogoService logoService;
    // Spring注入
    @Resource(name = "GoodsCateService")
    private GoodsCateService goodsCateService;

    // Spring注入
    @Resource(name = "CodexService")
    private CodexService codexService;
    // Spring注入
    @Resource(name = "GoodsBrandService")
    private GoodsBrandService goodsBrandService;

    /**
     * 创建促销初始页
     * 
     * @param codex
     * @param pageBean
     * @return ModelAndView
     */
    @RequestMapping("createproductmarket")
    public ModelAndView createProductMarket(Codex codex, PageBean pageBean) {
        // 查询所有规则的促销方式
        return new ModelAndView("jsp/marketing/createmarketing").addObject("pageBean", codexService.selectCodexList(codex, pageBean));
    }

    /**
     * 创建单品促销
     * 
     * @param marketFlag
     * @return ModelAndView
     */
    @RequestMapping("createallmarket")
    public ModelAndView createAllMarket(String marketFlag) {
        // 查询促销规则
        List<Codex> codexList = codexService.selectCodexListUseBox();
        if ("DP".equals(marketFlag)) {
            // 单品促销
            // 查询单品促销信息的品牌、分类等
            return new ModelAndView("jsp/marketing/createmarketing_dp").addObject(CODEXLIST, codexList).addObject(BRANDLIST, goodsBrandService.queryAllBrandList())
                    .addObject(MARKETFLAG, marketFlag).addObject(CUSTOMERLEVEL, pointLevelServiceMapper.selectAllPointLevel())
                    .addObject(COUPONLIST, couponService.selectCouponListByAble()).addObject(LOGOLIST, logoService.queryAllLogoList())
                    .addObject(THIRDCATELIST, goodsCateService.queryAllGoodThirdCate());
        } else if ("MJO".equals(marketFlag)) {
            // 满减
            // 查询促销信息的品牌、分类等
            return new ModelAndView("jsp/marketing/createmarketing_mjo").addObject(CODEXLIST, codexList).addObject(BRANDLIST, goodsBrandService.queryAllBrandList())
                    .addObject(MARKETFLAG, marketFlag).addObject(CUSTOMERLEVEL, pointLevelServiceMapper.selectAllPointLevel())
                    .addObject(COUPONLIST, couponService.selectCouponListByAble()).addObject(LOGOLIST, logoService.queryAllLogoList())
                    .addObject(THIRDCATELIST, goodsCateService.queryAllGoodThirdCate());
        } else if ("ZK".equals(marketFlag)) {
            // 15折扣促销
            // 查询促销信息的品牌、分类等
            return new ModelAndView("jsp/marketing/createmarketing_zk").addObject(CODEXLIST, codexList).addObject(MARKETFLAG, marketFlag)
                    .addObject(CUSTOMERLEVEL, pointLevelServiceMapper.selectAllPointLevel()).addObject(COUPONLIST, couponService.selectCouponListByAble())
                    .addObject(LOGOLIST, logoService.queryAllLogoList());
        } else if ("MJP".equals(marketFlag)) {
            // 满件
            // 查询促销信息的品牌、分类等
            return new ModelAndView("jsp/marketing/createmarketing_mjp").addObject(CODEXLIST, codexList).addObject(BRANDLIST, goodsBrandService.queryAllBrandList())
                    .addObject(MARKETFLAG, marketFlag).addObject(CUSTOMERLEVEL, pointLevelServiceMapper.selectAllPointLevel())
                    .addObject(COUPONLIST, couponService.selectCouponListByAble()).addObject(LOGOLIST, logoService.queryAllLogoList())
                    .addObject(THIRDCATELIST, goodsCateService.queryAllGoodThirdCate());
        } else if ("BY".equals(marketFlag)) {
            // 包邮
            // 查询促销信息的品牌、分类等
            return new ModelAndView("jsp/marketing/createmarketing_by").addObject(CODEXLIST, codexList).addObject(BRANDLIST, goodsBrandService.queryAllBrandList())
                    .addObject(MARKETFLAG, marketFlag).addObject(CUSTOMERLEVEL, pointLevelServiceMapper.selectAllPointLevel())
                    .addObject(COUPONLIST, couponService.selectCouponListByAble()).addObject(LOGOLIST, logoService.queryAllLogoList())
                    .addObject(THIRDCATELIST, goodsCateService.queryAllGoodThirdCate());
        } else if ("TG".equals(marketFlag)) {
            // 团购
            // 查询促销信息的品牌、分类等
            return new ModelAndView("jsp/marketing/createmarketing_tgou").addObject(CODEXLIST, codexList).addObject(MARKETFLAG, marketFlag)
                    .addObject(CUSTOMERLEVEL, pointLevelServiceMapper.selectAllPointLevel()).addObject(COUPONLIST, couponService.selectCouponListByAble())
                    .addObject(LOGOLIST, logoService.queryAllLogoList());
        } else if ("QG".equals(marketFlag)) {
            // 抢购
            // 查询促销信息的品牌、分类等
            return new ModelAndView("jsp/marketing/createmarketing_qgou").addObject(CODEXLIST, codexList).addObject(MARKETFLAG, marketFlag)
                    .addObject(CUSTOMERLEVEL, pointLevelServiceMapper.selectAllPointLevel()).addObject(COUPONLIST, couponService.selectCouponListByAble())
                    .addObject(LOGOLIST, logoService.queryAllLogoList());
        } else {
            return new ModelAndView("jsp/marketing/notfind").addObject(MARKETFLAG, marketFlag);
        }

    }
}
