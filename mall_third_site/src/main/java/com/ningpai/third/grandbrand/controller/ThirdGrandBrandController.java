/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.third.grandbrand.controller;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import com.ningpai.goods.bean.GoodsBrand;
import com.ningpai.third.grandbrand.service.GrandBrandService;
import com.ningpai.third.grandbrand.util.GrandBrandValue;
import com.ningpai.third.util.MenuOperationUtil;
import com.ningpai.util.PageBean;

/**
 * <p>
 * 商家品牌
 * </p>
 * 
 * @Author zhanghl
 * @since 2014年5月12日 下午5:02:31
 * @version 2.0
 */
@Controller
public class ThirdGrandBrandController {

    private static final String THIRDID = "thirdId";

    private static final String TABSTATUS = "?tabStatus=";

    // 商品品牌service
    private GrandBrandService service;

    /**
     * 查询该品牌商品的数量
     * 
     * @param brandId
     *            品牌ID
     * @param request
     * @return
     */
    @RequestMapping("/checkGoods")
    @ResponseBody
    public int checkGoods(Long brandId, HttpServletRequest request) {
        // 商家ID
        Long thirdId = (Long) request.getSession().getAttribute(THIRDID);
        // 查询该品牌商品的数量
        return service.checkGoodCount(brandId, thirdId);
    }

    /**
     * 
     * @param goodsBrand
     * @param pb
     *            分页bean
     * @param request
     * @param n
     *            导航栏索引头部
     * @param l
     *            导航栏索引 左侧
     * @param tabStatus
     *            tab页选中状态
     * @return 品牌信息页
     */
    @RequestMapping("/queryThirdGrandBrandLists")
    public ModelAndView queryThirdGrandBrandLists(GoodsBrand goodsBrand, PageBean pb, HttpServletRequest request, String n, String l, String tabStatus) {
        String nNew = n;
        String lNew = l;
        String tabStatusNew = tabStatus;
        if (nNew == null || lNew == null) {
            nNew = "2";
            lNew = "63";
        }
        // 填充导航/左侧菜单索引 用于页面控制css选中
        MenuOperationUtil.fillSessionMenuIndex(request, nNew, lNew);
        ModelAndView mav = new ModelAndView();
        Map<String, Object> map = new HashMap<String, Object>();
        if (tabStatusNew == null || "1".equals(tabStatusNew)) {
            tabStatusNew = null;
            // 品牌列表页面
            mav.setViewName(GrandBrandValue.THIRDGRANDBRANDLISTS);
            pb.setUrl("queryThirdGrandBrandLists.html?n=" + nNew + "&l=" + lNew);
        } else {
            // 申请品牌页面
            mav.setViewName(GrandBrandValue.THIRDGRANDBRANDLIST);
            pb.setUrl("queryThirdGrandBrandLists.html?n=" + nNew + "&l=" + lNew + "&tabStatus=2");
        }

        // 设置第三方标示
        map.put("pb", service.queryAllGoodsGrandBrand(pb, goodsBrand, tabStatusNew, (Long) request.getSession().getAttribute(THIRDID)));
        map.put("goodsBrand", goodsBrand);

        // 设置选中tab页 1 品牌列表 2、申请品牌
        map.put(GrandBrandValue.TABSTATUS, tabStatusNew);
        return mav.addObject("map", map);
    }

    /**
     * 查询没有加入的品牌信息
     * 
     * @param request
     * @param goodsBrand
     *            品牌查询参数
     * @param pb
     *            分页bean
     * @return 查询到的品牌信息
     */
    @RequestMapping("/queryForGrandBrandLists")
    @ResponseBody
    public PageBean queryForGrandBrandLists(HttpServletRequest request, GoodsBrand goodsBrand, PageBean pb) {
        pb.setPageSize(16);
        // 查询为加入的品牌
        return service.queryForGoodsGrandBrand(pb, goodsBrand, (Long) request.getSession().getAttribute(THIRDID), "2");
    }

    /**
     * 循环申请品牌
     * 
     * @param
     * @return 品牌信息页
     */
    @RequestMapping("/changeThirdGrandBrand")
    public ModelAndView changeThirdGrandBrand(String brandIds, HttpServletRequest request, String tabStatus) {
        // 把前台传过来的商家ID 分割成数组
        String[] brandId = brandIds.split(",");
        // 循环申请品牌
        service.forTheGoodsBrand(brandId, (Long) request.getSession().getAttribute(THIRDID));
        return new ModelAndView(new RedirectView(GrandBrandValue.QUERYTHIRDGRANDBRANDLISTS + TABSTATUS + tabStatus), GrandBrandValue.TABSTATUS, tabStatus);
    }

    /**
     * 更改品牌状态
     * 
     * @param tabStatus
     *            tab页选中值
     * @return 品牌信息页
     */
    @RequestMapping("/updateGrandBrand")
    public ModelAndView updateGrandBrand(GoodsBrand goodsBrand, HttpServletRequest request, String tabStatus) {

        goodsBrand.setThirdId((Long) request.getSession().getAttribute(THIRDID));
        service.updateGrandBrand(goodsBrand);
        return new ModelAndView(new RedirectView(GrandBrandValue.QUERYTHIRDGRANDBRANDLISTS + TABSTATUS + tabStatus), GrandBrandValue.TABSTATUS, tabStatus);
    }

    /**
     * 批量更改品牌状态
     * 
     * @param tabStatus
     *            tab页选中值
     * @return 返回到品牌页
     */
    @RequestMapping("/updateGrandBrands")
    public ModelAndView updateGrandBrands(Long[] brandIds, HttpServletRequest request, String tabStatus) {
        Long thirdId = (Long) request.getSession().getAttribute(THIRDID);
        service.updateGrandBrands(brandIds, thirdId);
        return new ModelAndView(new RedirectView(GrandBrandValue.QUERYTHIRDGRANDBRANDLISTS + TABSTATUS + tabStatus), GrandBrandValue.TABSTATUS, tabStatus);
    }

    public GrandBrandService getService() {
        return service;
    }

    @Resource(name = "GrandBrandService")
    public void setService(GrandBrandService service) {
        this.service = service;
    }

}
