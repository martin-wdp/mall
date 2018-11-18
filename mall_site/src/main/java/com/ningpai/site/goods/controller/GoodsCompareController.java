/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.site.goods.controller;

import com.ningpai.common.kuaidi.KuaiDiUtil;
import com.ningpai.index.service.TopAndBottomService;
import com.ningpai.site.goods.bean.GoodsDetailBean;
import com.ningpai.site.goods.service.GoodsProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author NINPAI-HEHU
 * @since 2014年5月26日 下午2:31:55
 * @version
 */
@Controller
public class GoodsCompareController {

    private GoodsProductService goodsProductService;

    @Resource(name = "TopAndBottomService")
    private TopAndBottomService topAndBottomService;

    /**
     * 跳转到对比页面
     * 
     * @param goodsIds
     * @return
     */
    @RequestMapping("/compare")
    public ModelAndView compare(String goodsIds, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", goodsProductService.execCompProduct(getIds(goodsIds, ","), request));
        ModelAndView mav = new ModelAndView("goods/compare").addObject("map", map);
        return topAndBottomService.getTopAndBottom(mav);
    }

    /**
     * 加载对比栏
     * 
     * @param goodsIds
     * @return
     */
    @RequestMapping("/getCompareGoods")
    @ResponseBody
    public List<GoodsDetailBean> compareGoods(String goodsIds, HttpServletRequest request) {
        return goodsProductService.execCompProduct(getIds(goodsIds, "-"));
    }

    /**
     * 将前台传来的goodsids（0-0-0-0）转换成list
     * 
     * @param goodsIds
     * @return
     */
    public List<Long> getIds(String goodsIds, String reg) {
        List<Long> ids = new ArrayList<Long>();
        for (String id : goodsIds.split(reg)) {
            if ("0".equals(id)) {
                continue;
            }
            ids.add(Long.parseLong(id));
        }
        return ids;
    }

    /**
     * @return the goodsProductService
     */
    public GoodsProductService getGoodsProductService() {
        return goodsProductService;
    }

    /**
     * @param goodsProductService
     *            the goodsProductService to set
     */
    @Resource(name = "HsiteGoodsProductService")
    public void setGoodsProductService(GoodsProductService goodsProductService) {
        this.goodsProductService = goodsProductService;
    }

    /**
     * 根据快递公司编号和运单编号查询快递信息
     * 
     * @param expressType
     * @param expressNo
     * @return
     */
    @RequestMapping("/execLookExpress")
    @ResponseBody
    public String execLookExpress(String expressType, String expressNo) {
        return KuaiDiUtil.execLookKuaiDi(expressType, expressNo);
    }

}
