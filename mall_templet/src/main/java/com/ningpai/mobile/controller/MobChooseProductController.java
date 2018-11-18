/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.mobile.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ningpai.goods.service.GoodsProductService;
import com.ningpai.mobile.bean.MobCateBar;
import com.ningpai.mobile.service.MobCateBarService;
import com.ningpai.util.PageBean;

/**
 * 控制器-选择商品（货品）
 * 
 * @ClassName: MobChooseProductController
 * @Description: 获取根据分类查询移动版货品，用于移动版首页的商品模块选取
 * @author Wanghy
 * @date 2014年11月14日 上午11:17:46
 * 
 */
@Controller
public class MobChooseProductController {

    @Resource(name = "MobCateBarService")
    private MobCateBarService mobCateBarService;

    @Resource(name = "GoodsProductService")
    private GoodsProductService goodsProductService;

    /**
     * 查询移动版货品
     * 
     * @Title: queryMobProductForGoods
     * @Description: 根据分类和名称查询移动版货品，用于移动版可视化配置首页商品模块选取
     * @param pb
     *            分页工具bean,可不传
     * @param cateId
     *            分类ID,可为null
     * @param name
     *            货品名称,可为null,可模糊查询
     * @param request
     * @return 选择货品页面，此页面应该为弹出框
     */
    @RequestMapping("/queryMobProductForGoods")
    public ModelAndView queryMobProductForGoods(PageBean pb, Long cateId, String name, Long size, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        String[] cateIds = null;
        if (null != cateId && cateId > -1) {
            cateIds = new String[] { cateId.toString() };
        }
        map.put("cateId", cateId);
        map.put("name", name);
        map.put("size", size);
        Map<String, String> extraParam = new HashMap<>();
        extraParam.put("showMobile", "1");
        pb.setObjectBean(extraParam);
        map.put("pb", goodsProductService.queryProductForCouponLife(pb, cateIds, null, (Long) request.getSession().getAttribute("thirdId"), null, name, null, null, null,null));
        return new ModelAndView("mobile_home_page/choose_product", "map", map);
    }

    /**
     * 查询移动版分类
     * 
     * @Title: ajaxQueryMobCateBarForSite
     * @Description: Ajax查询已启用、未删除的移动版分类导航
     * @return
     */
    @ResponseBody
    @RequestMapping("/ajaxQueryMobCateBarForChoose")
    public List<MobCateBar> ajaxQueryMobCateBarForChoose() {
        return this.mobCateBarService.selectMobCateBarForMobChoose();
    }

}
