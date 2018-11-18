/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.gift.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.ningpai.logcore.util.OperaLogUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.util.MyLogger;
import com.ningpai.gift.bean.GiftCate;
import com.ningpai.gift.service.GiftCateService;
import com.ningpai.goods.util.ValueUtil;
import com.ningpai.util.Constants;
import com.ningpai.util.PageBean;

/**
 * @author ggn
 * 
 */
@Controller
public class GiftCateController {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(GiftCateController.class);

    private static final String LOGGERINFO1 = "-->赠品分类名称【";
    private static final String LOGGERINFO2 = "】,用户名：";

    /**
     * 赠品分类services
     */
    private GiftCateService giftCateService;

    /**
     * 跳转赠品分类列表
     * 
     * @return ModelAndView
     */
    @RequestMapping("/searchgiftcatelist")
    public ModelAndView toSearchGiftCateList() {
        return new ModelAndView(Constants.GIFTCATELIST);
    }

    /**
     * 查询赠品分类列表
     * 
     * @param pageBean
     * @param giftCate
     * @return ModelAndView
     */
    @RequestMapping("/dosearchgiftcatelist")
    @ResponseBody
    public PageBean doSearchGiftCateList(PageBean pageBean, GiftCate giftCate) {
        // 非空验证 分类名称
        if (null != giftCate.getGiftCateName()) {
            LOGGER.info("获取分类名称为：" + giftCate.getGiftCateName() + "的信息！");
        }
        // 设置路径
        pageBean.setUrl("dosearchgiftcatelist.htm");
        return giftCateService.searchGiftCateList(giftCate, pageBean);
    }

    /**
     * 查询赠品分类不带分页列表(new boss)
     *
     *
     * @param giftCate
     * @return List<GiftCate>
     */
    @RequestMapping("/searchgiftcatelistNopage")
    @ResponseBody
    public List<Object> searchgiftcatelistNopage(GiftCate giftCate) {
        // 非空验证 分类名称
        if (null != giftCate.getGiftCateName()) {
            LOGGER.info("获取分类名称为：" + giftCate.getGiftCateName() + "的信息！");
        }
        // 获取赠品分类
        return giftCateService.searchGiftCateListNoPage(giftCate);
    }

    /**
     * 编辑的时候查询赠品分类列表
     * 
     * @param giftCateId
     *            分类ID
     * @return
     */
    @RequestMapping("/querygiftcate")
    @ResponseBody
    public List<GiftCate> giftCateList(Long giftCateId) {
        if (null != giftCateId) {
            return giftCateService.searchGiftCateList(giftCateId);
        }
        return giftCateService.searchGiftCateList();
    }

    /**
     * 根据Id查询赠品分类详细信息
     * 
     * @param giftCateId
     * @return ModelAndView
     */
    @RequestMapping("/searchgiftcatebyid")
    @ResponseBody
    public GiftCate searchGiftCateById(Long giftCateId) {
        // 根据Id查询赠品分类详细信息
        GiftCate giftCate = giftCateService.searchGiftCateById(giftCateId);
        // 非空判断 分类名称
        if (null != giftCate.getGiftCateName()) {
            // 日志记录
            LOGGER.info("查询赠品分类【" + giftCate.getGiftCateName() + "】详细信息");
        }
        return giftCate;
    }

    /**
     * 添加赠品分类
     * 
     * @param giftCate
     * @return ModelAndView
     */
    @RequestMapping("addgiftcate")
    public ModelAndView addGiftCate(HttpServletRequest request, GiftCate giftCate) {
        if (1 == giftCateService.addGiftCate(giftCate) && null != giftCate.getGiftCateName()) {
            // 非空判断 分类名称
                // 添加日志
                OperaLogUtil.addOperaLog(request, (String) request.getSession().getAttribute(ValueUtil.NAME), "添加赠品分类",
                        (String) request.getSession().getAttribute(ValueUtil.OPERAPATH) + LOGGERINFO1 + giftCate.getGiftCateName() + LOGGERINFO2
                                + (String) request.getSession().getAttribute(ValueUtil.NAME));
                // 日志记录
                LOGGER.info("新增赠品分类【" + giftCate.getGiftCateName() + "】成功");
        }
        return new ModelAndView(new RedirectView(Constants.INITGIFTCATELIST));
    }

    /**
     * 修改赠品分类
     * 
     * @param giftCate
     * @return ModelAndView
     */
    @RequestMapping("/updategiftcate")
    public ModelAndView updateGiftCate(HttpServletRequest request, GiftCate giftCate) {
        // 非空判断 分类名称
        if (null != giftCate.getGiftCateName()) {
            // 添加日志
            OperaLogUtil.addOperaLog(request, (String) request.getSession().getAttribute(ValueUtil.NAME), "修改赠品分类", (String) request.getSession().getAttribute(ValueUtil.OPERAPATH)
                    + LOGGERINFO1 + giftCate.getGiftCateName() + LOGGERINFO2 + (String) request.getSession().getAttribute(ValueUtil.NAME));
            // 日志记录
            LOGGER.info("修改赠品分类【" + giftCate.getGiftCateName() + "】成功");
        }
        // 修改赠品分类
        giftCateService.updateGiftCate(giftCate);
        return new ModelAndView(new RedirectView(Constants.INITGIFTCATELIST));
    }

    /**
     * 删除赠品分类
     * 
     * @param giftCateId
     * @return ModelAndView
     */
    @RequestMapping("/delgiftcate")
    public ModelAndView delGiftCate(HttpServletRequest request, Long giftCateId) {
        // 判断是否删除
        if (1 == giftCateService.delGiftCate(giftCateId)) {
            // 根据Id查询赠品分类详细信息
            GiftCate giftCate = giftCateService.searchGiftCateById(giftCateId);
            // 非空判断 分类名称
            if (null != giftCate.getGiftCateName()) {
                OperaLogUtil.addOperaLog(request, (String) request.getSession().getAttribute(ValueUtil.NAME), "删除赠品分类",
                        (String) request.getSession().getAttribute(ValueUtil.OPERAPATH) + LOGGERINFO1 + giftCate.getGiftCateName() + LOGGERINFO2
                                + (String) request.getSession().getAttribute(ValueUtil.NAME));
                // 日志记录
                LOGGER.info("删除赠品分类【" + giftCate.getGiftCateName() + "】成功");
            }
        }
        return new ModelAndView(new RedirectView(Constants.INITGIFTCATELIST));
    }

    /**
     * 批量删除赠品分类
     * 
     * @param request
     * @return ModelAndView
     */
    @RequestMapping("/delallgiftcate")
    public ModelAndView delAllGiftCate(HttpServletRequest request, Long[] giftCateIds) {
        // 删除
        giftCateService.delAllGiftCate(giftCateIds);
        // 添加日志
        OperaLogUtil.addOperaLog(request, (String) request.getSession().getAttribute(ValueUtil.NAME), "批量删除赠品分类", (String) request.getSession().getAttribute(ValueUtil.OPERAPATH)
                + ",用户名：" + (String) request.getSession().getAttribute(ValueUtil.NAME));
        return new ModelAndView(new RedirectView(Constants.INITGIFTCATELIST));
    }

    /**
     * 根据传递的分类ID验证是否存在子类
     * 
     * @param cateId
     *            分类ID {@link java.lang.Long}
     * @return 是否可以删除标记
     */
    @RequestMapping("/checkgiftcate")
    @ResponseBody
    public boolean checkGIftCate(Long cateId) {
        // 根据ID获取单个的分类对象信息
        GiftCate giftCate = giftCateService.searchGiftCateById(cateId);
        // 非空判断 分类名称
        if (null != giftCate.getGiftCateName()) {
            // 日志记录
            LOGGER.info("分类信息【" + giftCate.getGiftCateName() + "】存在子分类,不可删除");
        } else {
            LOGGER.info(ValueUtil.CHECKDELINFO);
        }
        return giftCateService.checkDelGiftCate(cateId);
    }

    public GiftCateService getGiftCateService() {
        return giftCateService;
    }

    @Resource(name = "GiftCateService")
    public void setGiftCateService(GiftCateService giftCateService) {
        this.giftCateService = giftCateService;
    }

}
