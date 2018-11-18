/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.controller;

import com.ningpai.common.safe.CSRFTokenManager;
import com.ningpai.goods.bean.GoodsCate;
import com.ningpai.goods.bean.GoodsGroup;
import com.ningpai.goods.service.GoodsBrandService;
import com.ningpai.goods.service.GoodsCateService;
import com.ningpai.goods.service.GoodsGroupReleProductService;
import com.ningpai.goods.service.GoodsGroupService;
import com.ningpai.goods.util.PathUtil;
import com.ningpai.goods.util.SelectBean;
import com.ningpai.goods.util.ValueUtil;
import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.system.service.BasicSetService;
import com.ningpai.util.MenuSession;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * 商品组合控制器
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月31日 下午4:06:03
 * @version 1.0
 */
@Controller
public class GoodsGroupController {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(GoodsGroupController.class);

    private static final String LOGGERINFO1 = "-->组合名称【";
    private static final String LOGGERINFO2 = "】,用户名：";
    private static final String LOGGERINFO3 = ",组合名称:";

    // 商品组合Service
    private GoodsGroupService goodsGroupService;

    // 商品组合关联货品Service
    private GoodsGroupReleProductService goodsGroupReleProductService;

    // 站点信息service
    @Resource(name = "basicSetService")
    private BasicSetService basicSetService;

    @Resource(name = "GoodsCateService")
    private GoodsCateService goodsCateService;
    @Resource(name = "GoodsBrandService")
    private GoodsBrandService goodsBrandService;

    public GoodsGroupReleProductService getGoodsGroupReleProductService() {
        return goodsGroupReleProductService;
    }

    @Resource(name = "GoodsGroupReleProductService")
    public void setGoodsGroupReleProductService(GoodsGroupReleProductService goodsGroupReleProductService) {
        this.goodsGroupReleProductService = goodsGroupReleProductService;
    }

    public GoodsGroupService getGoodsGroupService() {
        return goodsGroupService;
    }

    @Resource(name = "GoodsGroupService")
    public void setGoodsGroupService(GoodsGroupService goodsGroupService) {
        this.goodsGroupService = goodsGroupService;
    }

    /**
     * 分页查询商品组合列表
     * 
     * @param pb
     *            分页辅助类
     * @return 视图 {@link org.springframework.web.servlet.ModelAndView}
     */
    @RequestMapping("/findAllGroup")
    public ModelAndView queryAllGroup(PageBean pb, SelectBean selectBean, HttpServletRequest request) {
        MenuSession.sessionMenu(request);
        // 打印日志
        LOGGER.info(ValueUtil.FINDALLGROUPINFO);
        // 返回结果
        return new ModelAndView(PathUtil.GOODSGROUP, "pb", this.goodsGroupService.queryGoodsGroupByPageBeanAndSearchBean(pb, selectBean));
    }

    /**
     * 删除组合信息
     * 
     * @param groupId
     *            待删除的组合ID
     * @return 视图 {@link org.springframework.web.servlet.ModelAndView}
     */
    @RequestMapping("/delGroup")
    public ModelAndView delGroup(Long groupId, HttpServletRequest request) {
        // 根据ID获取单个的组合信息
        GoodsGroup goodsGroup = this.goodsGroupService.queryGoodsGroupByPrimaryKey(groupId);
        // 非空验证 组合名称 删除组合
        if (null != goodsGroup.getGroupName() && 1 == this.goodsGroupService.delGoodsGroup(groupId, (String) request.getSession().getAttribute(ValueUtil.NAME))) {
            // 操作日志
            OperaLogUtil.addOperaLog(request, (String) request.getSession().getAttribute(ValueUtil.NAME), "删除组合信息", (String) request.getSession().getAttribute(ValueUtil.OPERAPATH)
                    + LOGGERINFO1 + goodsGroup.getGroupName() + LOGGERINFO2 + (String) request.getSession().getAttribute(ValueUtil.NAME));
            // 操作日志
            LOGGER.info(ValueUtil.DELGROUPINFO + LOGGERINFO3 + goodsGroup.getGroupName());
        } else {
            this.goodsGroupService.delGoodsGroup(groupId, (String) request.getSession().getAttribute(ValueUtil.NAME));
        }
        return new ModelAndView(new RedirectView(PathUtil.QUERYALLGROUP + ValueUtil.TOKENPARAM + CSRFTokenManager.getTokenFromRequest(request)));
    }

    /**
     * 批量删除商品组合
     * 
     * @param groupIds
     *            待删除的商品组合ID的数组
     * @return 视图 {@link org.springframework.web.servlet.ModelAndView}
     */
    @RequestMapping("/batchDelGroup")
    public ModelAndView batchDelGroup(Long[] groupIds, HttpServletRequest request) {
        // 打印日志
        LOGGER.info(ValueUtil.BATCHDELGROUPINFO);
        // 进行删除
        this.goodsGroupService.batchDelGroup(request, groupIds, (String) request.getSession().getAttribute(ValueUtil.NAME));
        // 记录当前登录者的操作日志
        OperaLogUtil.addOperaLog(request, (String) request.getSession().getAttribute(ValueUtil.NAME), ValueUtil.BATCHDELGROUPINFO,
                (String) request.getSession().getAttribute(ValueUtil.OPERAPATH) + ",用户名：" + (String) request.getSession().getAttribute(ValueUtil.NAME));
        // 返回结果
        return new ModelAndView(new RedirectView(PathUtil.QUERYALLGROUP + ValueUtil.TOKENPARAM + CSRFTokenManager.getTokenFromRequest(request)));
    }

    /**
     * 添加一个商品组合
     * 
     * @param goodsGroup
     *            商品组合实体{@link com.ningpai.goods.bean.GoodsGroup}
     * @return 视图 {@link org.springframework.web.servlet.ModelAndView}
     */
    @RequestMapping("/saveGroup")
    public ModelAndView saveGroup(@Valid GoodsGroup goodsGroup, HttpServletRequest request) {
        // newboss后台商品组合只能是boss商品设置third为0
        goodsGroup.setThirdId(0L);
        // 添加一个商品组合
        this.goodsGroupService.saveGoodsGroup(goodsGroup, (String) request.getSession().getAttribute(ValueUtil.NAME));
        // 非空验证 商品组合名称 和是否添加成功
        if (null != goodsGroup.getGroupName()) {
            // 操作日志
            OperaLogUtil.addOperaLog(request, (String) request.getSession().getAttribute(ValueUtil.NAME), "新增商品组合", (String) request.getSession().getAttribute(ValueUtil.OPERAPATH)
                    + LOGGERINFO1 + goodsGroup.getGroupName() + LOGGERINFO2 + (String) request.getSession().getAttribute(ValueUtil.NAME));
            // 记录日志
            LOGGER.info(ValueUtil.SAVEGROUPINFO + LOGGERINFO3 + goodsGroup.getGroupName());
        }
        return new ModelAndView(new RedirectView(PathUtil.QUERYALLGROUP + ValueUtil.TOKENPARAM + CSRFTokenManager.getTokenFromRequest(request)));
    }

    /**
     * 根据主键查询商品组合信息
     * 
     * @param groupId
     *            商品组合的主键ID
     * @return 查询到的商品组合的实体{@link com.ningpai.goods.bean.GoodsGroup}
     */
    @RequestMapping("/queryGoodsGroupById")
    @ResponseBody
    public GoodsGroup queryGoodsGroupById(Long groupId) {
        // 根据ID获取单个的组合信息
        GoodsGroup goodsGroup = this.goodsGroupService.queryGoodsGroupByPrimaryKey(groupId);
        // 非空验证 商品组合名称
        if (null != goodsGroup.getGroupName()) {
            // 操作日志
            LOGGER.info(ValueUtil.QUERYGOODSGROUPBYIDINFO + LOGGERINFO3 + goodsGroup.getGroupName());
        }
        return this.goodsGroupService.queryGoodsGroupByPrimaryKey(groupId);
    }

    /**
     * 更新商品组合信息
     * 
     * @param group
     *            组合实体{@link com.ningpai.goods.bean.GoodsGroup}
     * @return 视图 {@link org.springframework.web.servlet.ModelAndView}
     */
    @RequestMapping("/updateGroup")
    public ModelAndView updateGroup(@Valid GoodsGroup group, HttpServletRequest request) {
        // 根据ID获取单个的组合信息
        GoodsGroup goodsGroup = this.goodsGroupService.queryGoodsGroupByPrimaryKey(group.getGroupId());
        // 更新组合信息
        this.goodsGroupService.updateGoodsGroup(group, (String) request.getSession().getAttribute(ValueUtil.NAME));
        // 非空验证 商品组合名称 和是否更新成功
        if (null != goodsGroup.getGroupName()) {
            // 操作日志
            OperaLogUtil.addOperaLog(request, (String) request.getSession().getAttribute(ValueUtil.NAME), "更新商品组合", (String) request.getSession().getAttribute(ValueUtil.OPERAPATH)
                    + LOGGERINFO1 + goodsGroup.getGroupName() + LOGGERINFO2 + (String) request.getSession().getAttribute(ValueUtil.NAME));
            // 记录日志
            LOGGER.info(ValueUtil.UPDATEGROUPINFO + LOGGERINFO3 + goodsGroup.getGroupName());
        }
        return new ModelAndView(new RedirectView(PathUtil.QUERYALLGROUP + ValueUtil.TOKENPARAM + CSRFTokenManager.getTokenFromRequest(request)));
    }

    /**
     * 根据主键查询商品组合ID
     * 
     * @param groupId
     * @return
     */
    @RequestMapping("/queryGroupByPrimaryKey")
    public ModelAndView queryByPrimaryKey(Long groupId) {
        String basicAddress = basicSetService.findBasicSet().getBsetAddress();
        // 根据ID获取单个的组合信息
        GoodsGroup goodsGroup = this.goodsGroupService.queryGoodsGroupByPrimaryKey(groupId);
        // 非空验证 商品组合名称
        if (null != goodsGroup.getGroupName()) {
            // 操作日志
            LOGGER.info(ValueUtil.QUERYGROUPBYPRIMARYKEYINFO + LOGGERINFO3 + goodsGroup.getGroupName());
        }
        // 查询结果并赋值给商品分类集合
        List<GoodsCate> oneCateList = goodsCateService.querySonCateByParentIdAndName(0L, null);
        return new ModelAndView(PathUtil.GROUPRELEPRODUCTVIEW, "groupVo", this.goodsGroupService.queryVoByPrimaryKey(groupId)).addObject("basicAddress", basicAddress)
                .addObject("brandList", this.goodsBrandService.queryAllBrand()).addObject("oneCateList", oneCateList);

    }

    /**
     * 根据组合ID和productId删除组合关联的货品信息
     * 
     * @param groupId
     *            {@link java.lang.Long}
     * @param productId
     *            {@link java.lang.Long}
     * @return 视图 {@link org.springframework.web.servlet.ModelAndView}
     */
    @RequestMapping("/delGroupReleProduct")
    public ModelAndView delGroupReleProduct(Long groupId, Long productId, HttpServletRequest request) {
        // 根据ID获取单个的组合信息
        GoodsGroup goodsGroup = this.goodsGroupService.queryGoodsGroupByPrimaryKey(groupId);
        this.goodsGroupReleProductService.delGroupReleProductByGroupIdAndProductId(groupId, productId);
        // 非空验证 商品组合名称
        if (null != goodsGroup.getGroupName()) {
            // 操作日志
            OperaLogUtil.addOperaLog(request, (String) request.getSession().getAttribute(ValueUtil.NAME), "删除组合关联的货品信息",
                    (String) request.getSession().getAttribute(ValueUtil.OPERAPATH) + LOGGERINFO1 + goodsGroup.getGroupName() + LOGGERINFO2
                            + (String) request.getSession().getAttribute(ValueUtil.NAME));
            // 记录日志
            LOGGER.info(ValueUtil.DELGROUPRELEPRODUCTINFO + LOGGERINFO3 + goodsGroup.getGroupName());
        }
        // 返回结果
        return new ModelAndView(new RedirectView(PathUtil.GROUPRELEPRODUCT + groupId + ValueUtil.TOKENPARAM2 + CSRFTokenManager.getTokenFromRequest(request)));
    }

    /**
     * 批量删除组合关联货品
     * 
     * @param groupId
     *            组合ID {@link java.lang.Long}
     * @param productIds
     *            待删除的货品ID数组
     * @return 视图 {@link org.springframework.web.servlet.ModelAndView}
     */
    @RequestMapping("/batchDelGroupReleProduct")
    public ModelAndView batchDelGroupReleProduct(Long groupId, Long[] productIds, HttpServletRequest request) {
        // 打印日志
        LOGGER.info(ValueUtil.BATCHDELGROUPRELEPRODUCTINFO);
        // 执行批量删除组合货品方法
        this.goodsGroupReleProductService.batchDelGroupReleProductByGroupIdAndProductIds(groupId, productIds);
        // 记录当前登录者的操作日志
        OperaLogUtil.addOperaLog(request, (String) request.getSession().getAttribute(ValueUtil.NAME), ValueUtil.BATCHDELGROUPRELEPRODUCTINFO, (String) request.getSession()
                .getAttribute(ValueUtil.OPERAPATH) + ",用户名：" + (String) request.getSession().getAttribute(ValueUtil.NAME));
        // 返回结果
        return new ModelAndView(new RedirectView(PathUtil.GROUPRELEPRODUCT + groupId + ValueUtil.TOKENPARAM2 + CSRFTokenManager.getTokenFromRequest(request)));
    }

    /**
     * 根据组合ID和货品ID数组新建关联关系
     * 
     * @param groupId
     *            组合ID {@link java.lang.Long}
     * @param productId
     *            货品ID的数组{@link java.lang.Long}
     * @return 视图 {@link org.springframework.web.servlet.ModelAndView}
     */
    @RequestMapping("/saveGroupReleProduct")
    public ModelAndView saveGroupReleProduct(Long groupId, Long[] productId, HttpServletRequest request) {
        // 根据ID获取单个的组合信息
        GoodsGroup goodsGroup = this.goodsGroupService.queryGoodsGroupByPrimaryKey(groupId);
        // 执行添加方法
        this.goodsGroupReleProductService.addGroupReleProduct(groupId, productId);
        // 非空验证 商品组合名称
        if (null != goodsGroup.getGroupName()) {
            // 操作日志
            OperaLogUtil.addOperaLog(request, (String) request.getSession().getAttribute(ValueUtil.NAME), "保存商品组合关联的货品信息",
                    (String) request.getSession().getAttribute(ValueUtil.OPERAPATH) + LOGGERINFO1 + goodsGroup.getGroupName() + LOGGERINFO2
                            + (String) request.getSession().getAttribute(ValueUtil.NAME));
            // 操作日志
            LOGGER.info(ValueUtil.SAVEGROUPRELEPRODUCTINFO + LOGGERINFO3 + goodsGroup.getGroupName());
        }
        // 返回结果
        return new ModelAndView(new RedirectView(PathUtil.GROUPRELEPRODUCT + groupId + ValueUtil.TOKENPARAM2 + CSRFTokenManager.getTokenFromRequest(request)));
    }
}
