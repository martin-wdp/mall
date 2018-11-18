/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.third.goods.controller;

import com.ningpai.goods.bean.GoodsBrand;
import com.ningpai.goods.bean.GoodsCate;
import com.ningpai.goods.bean.GoodsTag;
import com.ningpai.goods.service.GoodsCateService;
import com.ningpai.third.goods.service.ThirdOtherService;
import com.ningpai.third.goods.util.ThirdValueBean;
import com.ningpai.third.util.MenuOperationUtil;
import com.ningpai.util.PageBean;
import com.ningpai.util.SelectBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 第三方Other 控制器
 * </p>
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年5月9日 上午10:40:23
 * @version 2.0
 */
@Controller
public class ThirdOtherController {
    // 第三方Other 接口
    private ThirdOtherService thirdOtherService;
    @Resource(name="GoodsCateService")
    private GoodsCateService goodsCateService;




    /**
     * 查询所有授权的商品品牌
     * 
     * @return 查询到的品牌列表 {@link com.ningpai.goods.bean.GoodsBrand}
     */
    @RequestMapping("/queryGrandBrandByThirdId")
    @ResponseBody
    public List<GoodsBrand> queryGrandBrandByThirdId(HttpServletRequest request) {
        // 根据第三方ID查询签约的品牌
        return this.thirdOtherService.queryGrandBrandByThirdId((Long) request
                .getSession().getAttribute(ThirdValueBean.THIRDID));
    }

    /**
     * 查询所有的商品标签
     * 
     * @return 查询到的标签列表 {@link com.ningpai.goods.bean.GoodsTag}
     */
    @RequestMapping("/queryAllTagForThird")
    @ResponseBody
    public List<GoodsTag> queryAllTagForThird() {
        return this.thirdOtherService.queryAllGoodsTagForThird();
    }

    /**
     * 查询所有的第三方签约的分类
     * 
     * @return 查询到的分类的列表 {@link com.ningpai.goods.bean.GoodsCate}
     */
    @RequestMapping("/queryAllGrandCateForThird")
    @ResponseBody
    public List<GoodsCate> queryAllGrandCateForThird(HttpServletRequest request) {
        return this.thirdOtherService.queryGrandCateForThird((Long) request
                .getSession().getAttribute(ThirdValueBean.THIRDID));
    }

    /**
     * 查询商家的商品类目的一级分类
     * 
     * @return 查询到的分类的列表 {@link com.ningpai.goods.bean.GoodsCate}
     */
    @RequestMapping("/queryAllGrandCateForThirdtwo")
    @ResponseBody
    public List<GoodsCate> queryAllGrandCateForThirdtwo(Long cateId,
            HttpServletRequest request) {
        return this.thirdOtherService.queryGrandCateForThirdnew((Long) request
                .getSession().getAttribute(ThirdValueBean.THIRDID), cateId);
    }




    /**
     *
     *
     * @return 查询到的分类的列表 {@link com.ningpai.goods.bean.GoodsCate}
     */
    @RequestMapping("/queryAllGrandCateForThirdThree")
    @ResponseBody
    public List<GoodsCate> queryAllGrandCateForThirdThree(Long cateId,HttpServletRequest request) {
        return this.goodsCateService.querySonCateByParentId(cateId);
    }








    /**
     * 根据第三方相关参数查询相关商品的集合 选择的第三方分类的集合 {@link Long}
     * 
     * @return 封装好的分页辅助Bean {@link com.ningpai.util.PageBean}
     */
    @RequestMapping("/queryAboutGoodsByThirdInfo")
    @ResponseBody
    public PageBean queryAboutGoodsByThirdInfo(Long goodsCatId, PageBean pb,
            HttpServletRequest request) {
        return this.thirdOtherService.queryAboutGoodsForThirdByThirdInfo(pb,
                (Long) request.getSession()
                        .getAttribute(ThirdValueBean.THIRDID), goodsCatId);
    }

    /**
     * 第三方商品组合列表
     * 
     * @param pb
     *            分页辅助Bean {@link com.ningpai.util.PageBean}
     * @param selectBean
     *            基本字段查询辅助Bean {@link com.ningpai.util.SelectBean}
     */
    @RequestMapping("/thirdGoodsGroupManager")
    public ModelAndView thirdGoodsGroupManager(PageBean pb,
            SelectBean selectBean, String n, String l,
            HttpServletRequest request) {
        MenuOperationUtil.fillSessionMenuIndex(request, n, l);
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // 判断是否是按照商品名称进行查询
            if (!"".equals(selectBean.getSearchText())) {
                selectBean.setCondition("1");
            }
            map.put("selectBean", selectBean);
            // 根据第三方的信息，查询相关的商品组合
            map.put("pb", this.thirdOtherService.queryThirdGroupByParam(
                    pb,
                    (Long) request.getSession().getAttribute(
                            ThirdValueBean.THIRDID), selectBean));
            return new ModelAndView("goods/thirdgoodsgroup", "map", map);
        } finally {
            map = null;
        }
    }

    public ThirdOtherService getThirdOtherService() {
        return thirdOtherService;
    }

    @Resource(name = "ThirdOtherService")
    public void setThirdOtherService(ThirdOtherService thirdOtherService) {
        this.thirdOtherService = thirdOtherService;
    }
}
