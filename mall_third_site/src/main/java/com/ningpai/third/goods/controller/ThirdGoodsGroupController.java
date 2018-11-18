/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.third.goods.controller;

import com.ningpai.customer.bean.Customer;
import com.ningpai.goods.bean.GoodsGroup;
import com.ningpai.goods.service.GoodsGroupReleProductService;
import com.ningpai.goods.service.GoodsGroupService;
import com.ningpai.goods.vo.GoodsGroupVo;
import com.ningpai.third.goods.util.ThirdValueBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 第三方商品组合控制器
 * </p>
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年5月14日 下午2:04:11
 * @version 2.0
 */
@Controller
public class ThirdGoodsGroupController {

    private GoodsGroupService goodsGroupService;

    // 商品组合关联货品Service
    private GoodsGroupReleProductService goodsGroupReleProductService;

    /**
     * 添加商品组合
     * 
     * @param group
     *            需要添加的商品组合
     * @author NINGPAI-LIH
     */
    @RequestMapping("/thirdAddGoodsGroupManager")
    public ModelAndView thirdAddGoodsGroupManager(GoodsGroup group, Long[] productId, HttpServletRequest request) {
        // 第三方商品组合标记
        group.setIsThird("1");
        // 第三方商品组合是否删除
        group.setGroupDelflag("0");
        // 设置商家ID
        group.setThirdId((Long) request.getSession().getAttribute("thirdId"));
        // 设置商家的名称
        group.setThirdName((String) request.getSession().getAttribute("storeName"));
        // 保存商品组合 并且返回当前插入的Id
        Long groupId = goodsGroupService.saveGoodsGroup(group, ((Customer) request.getSession().getAttribute(ThirdValueBean.CUST)).getCustomerUsername());
        // 新建关联记录
        this.goodsGroupReleProductService.addGroupReleProduct(groupId, productId);
        return new ModelAndView(new RedirectView(ThirdValueBean.THIRDGOODSGROUPMANAGER));
    }

    /**
     * 删除商品组合
     * 
     * @param goodsGroup
     *            要删除的商品组合
     * @return
     * @author NINGPAI-LIH
     */
    @RequestMapping("/thirdDelGoodsGroup")
    public ModelAndView thirdDelGoodsGroup(GoodsGroup goodsGroup, HttpServletRequest request) {

        // 根据id删除商品组合
        goodsGroupService.delGoodsGroupNew(goodsGroup.getGroupId(), ((Customer) request.getSession().getAttribute(ThirdValueBean.CUST)).getCustomerUsername(), (Long) request
                .getSession().getAttribute("thirdId"));
        return new ModelAndView(new RedirectView(ThirdValueBean.THIRDGOODSGROUPMANAGER));
    }

    /**
     * 批量删除商品组合
     * 
     * @param thirdGoodsId
     *            需要批量删除的id
     * @author NINGPAI-LIH
     */
    @RequestMapping("/thirdDelGoodsGroups")
    public ModelAndView thirdDelGoodsGroups(Long[] thirdGoodsId, HttpServletRequest request) {
        // 根据商品组合ID 批量删除 商品组合
        goodsGroupService.batchDelGroup(request, thirdGoodsId, ((Customer) request.getSession().getAttribute(ThirdValueBean.CUST)).getCustomerUsername());
        return new ModelAndView(new RedirectView(ThirdValueBean.THIRDGOODSGROUPMANAGER));
    }

    /**
     * 修改商品组合信息
     * 
     * @param group
     *            组合实体{@link com.ningpai.goods.bean.GoodsGroup}
     * @return 视图 {@link org.springframework.web.servlet.ModelAndView}
     * @author NINGPAI-LIH
     */
    @RequestMapping("/updateThirdGroup")
    public ModelAndView updateThirdGroup(GoodsGroup group, Long[] productId, Long[] delproId, HttpServletRequest request) {
        this.goodsGroupService.updateGoodsGroup(group, ((Customer) request.getSession().getAttribute(ThirdValueBean.CUST)).getCustomerUsername());
        // 删除标记删除的货品关联记录
        this.goodsGroupReleProductService.batchDelGroupReleProductByGroupIdAndProductIds(group.getGroupId(), delproId);
        // 保存不需要删除的货品关联记录
        this.goodsGroupReleProductService.addGroupReleProduct(group.getGroupId(), productId);
        return new ModelAndView(new RedirectView(ThirdValueBean.THIRDGOODSGROUPMANAGER));
    }

    /**
     * 根据组合ID查询组合信息Vo
     * 
     * @param groupId
     *            组合ID {@link Long}
     * @return 查询到的组合信息Vo {@link com.ningpai.goods.vo.GoodsGroupVo}
     */
    @RequestMapping("/toModiThirdGroupByGroupId")
    @ResponseBody
    public GoodsGroupVo queryGroupVoByPrimaryKey(Long groupId) {
        return this.goodsGroupService.queryVoByPrimaryKey(groupId);
    }

    public GoodsGroupService getGoodsGroupService() {
        return goodsGroupService;
    }

    @Resource(name = "GoodsGroupService")
    public void setGoodsGroupService(GoodsGroupService goodsGroupService) {
        this.goodsGroupService = goodsGroupService;
    }

    public GoodsGroupReleProductService getGoodsGroupReleProductService() {
        return goodsGroupReleProductService;
    }

    @Resource(name = "GoodsGroupReleProductService")
    public void setGoodsGroupReleProductService(GoodsGroupReleProductService goodsGroupReleProductService) {
        this.goodsGroupReleProductService = goodsGroupReleProductService;
    }

}
