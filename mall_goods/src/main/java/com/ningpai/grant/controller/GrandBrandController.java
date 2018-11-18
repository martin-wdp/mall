/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.grant.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.common.safe.CSRFTokenManager;
import com.ningpai.goods.bean.GoodsBrand;
import com.ningpai.grant.service.GrantBrandService;
import com.ningpai.grant.util.GrantValue;
import com.ningpai.util.PageBean;

/**
 * 品牌授权管理controller
 * 
 * @author NINGPAI-LIH
 * @since 2014年5月29日09:55:38
 * 
 */
@Controller
public class GrandBrandController {

    // 商品品牌service
    private GrantBrandService service;

    /**
     * 商品品牌列表
     * 
     * @param goodsBrand
     *            品牌查询参数
     * @param pb
     *            分页查询bean
     * @return 商品品牌页
     */
    @RequestMapping("/querygrandbrandlists")
    public ModelAndView queryGrandBrandLists(GoodsBrand goodsBrand,
            PageBean pb, HttpServletRequest request, String searchId,
            String searchText) {
        if (searchId != null) {
            // 搜索条件
            if ("1".equals(searchId)) {
                // 1 根据品牌名称搜索品牌
                goodsBrand.setBrandName(searchText);
            } else if ("2".equals(searchId)) {
                // 2 根据店铺名称搜索品牌
                goodsBrand.setStoreName(searchText);
            }
        }
        pb.setUrl(GrantValue.QUERYGRANDBRANDLISTS);
        // 查询品牌列表
        PageBean pbBean = service.queryAllGoodsGrandBrand(pb, goodsBrand);
        return new ModelAndView(GrantValue.GRANTBRANDS, "pb", pbBean)
                .addObject("searchId", searchId)
                .addObject("searchText", searchText)
                .addObject(
                        "sx",
                        request.getSession()
                                .getAttribute(
                                        CSRFTokenManager.CSRF_TOKEN_FOR_SESSION_ATTR_NAME)
                                .toString());
    }

    /**
     * 更改品牌状态
     * 
     * @param brandIds品牌id
     * @param reason
     *            拒绝原因
     * @param rateStatuts要修改的状态
     * @return
     */
    @RequestMapping("/updategrantbrands")
    public ModelAndView updateGrantBrands(Long[] brandIds, String reason,
            String rateStatuts) {
        service.updateGrantBrands(brandIds, reason, rateStatuts);
        return new ModelAndView(new RedirectView(
                GrantValue.QUERYGRANDBRANDLISTS));
    }

    public GrantBrandService getService() {
        return service;
    }

    @Resource(name = "GrantBrandService")
    public void setService(GrantBrandService service) {
        this.service = service;
    }

}
