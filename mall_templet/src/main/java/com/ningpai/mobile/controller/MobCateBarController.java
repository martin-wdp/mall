/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.mobile.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.channel.service.GoodsCateService;
import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.mobile.bean.MobCateBar;
import com.ningpai.mobile.service.MobCateBarService;
import com.ningpai.util.PageBean;
import com.ningpai.util.UploadUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 控制器-移动版分类导航
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年8月19日上午11:12:59
 */
@Controller
public class MobCateBarController {

    // 店铺街轮播大图片页面
    private static final String MOB_STORELIST_STREET = "jsp/mobile/mob_storelist_street";
    private static final String QUERY_MOB_CATE_BAR_BY_PB = "queryMobCateBarByPb.htm";

    @Resource(name = "MobCateBarService")
    private MobCateBarService mobCateBarService;

    @Resource(name = "ChannelGoodsCateService")
    private GoodsCateService goodsCateService;

    /**
     * 用户名称
     */
    public static final String NAME = "name";

    /**
     * "operaPath"
     */
    public static final String OPERAPATH = "operaPath";

    /**
     * 分页店铺街轮播大广告
     * 
     * @param pb
     * @return
     */
    @RequestMapping("/storeliststreetimage")
    public ModelAndView storeliststreetimage(PageBean pb) {
        // 装载要返回到页面的数据
        Map<String, Object> map = new HashMap<String, Object>();
        // 装载移动端获取的轮播大广告图片 null:禁用的和启用状态的 大广告都会查出来
        map.put("channelAdvers", this.mobCateBarService.selectStoreListImage(null));
        return new ModelAndView(MOB_STORELIST_STREET).addObject("map", map);
    }

    /**
     * 分类导航列表
     * 
     * @param pb
     * @return ModelAndView
     */
    @RequestMapping("/queryMobCateBarByPb")
    public ModelAndView queryMobCateBarByPb(PageBean pb) {
        return new ModelAndView("jsp/mobile/mobcatebar_list", "pb", mobCateBarService.selectMobCateBarByPb(pb));
    }

    /**
     * 查看一级分类导航，添加、修改，查询所有的商品倒数第2级分类用于选取
     * 
     * @param mobCateBarId
     * @return
     */
    @RequestMapping("/showMobCateBar")
    public ModelAndView showMobCateBar(Long mobCateBarId) {
        ModelAndView mav = new ModelAndView();
        // 修改
        if (null != mobCateBarId) {
            mav.addObject("mobcatebar", this.mobCateBarService.selectMobcateBarById(mobCateBarId));
        }
        mav.addObject("grade", 1);
        mav.setViewName("jsp/mobile/show_mobcatebar");
        return mav;
    }

    /**
     * 查看一级分类导航，添加、修改，查询所有的商品倒数第2级分类用于选取
     * 
     * @param mobCateBarId
     * @return
     */
    @RequestMapping("/showNewMobCateBar")
    @ResponseBody
    public MobCateBar showNewMobCateBar(Long mobCateBarId) {
        return this.mobCateBarService.selectMobcateBarById(mobCateBarId);
    }

    /**
     * 查看二级分类导航，添加、修改，查询它的父分类导航的商品分类的所有子商品分类用于选取
     * 
     * @param mobCateBarId
     * @param parentId
     * @return
     */
    @RequestMapping("/showMobCateBarByParentId")
    public ModelAndView showMobCateBarByParentId(Long mobCateBarId, Long parentId) {
        ModelAndView mav = new ModelAndView();
        // 修改
        if (null != mobCateBarId) {
            mav.addObject("mobcatebar", this.mobCateBarService.selectMobcateBarById(mobCateBarId));
        }
        // 获取父分类导航
        MobCateBar parent = this.mobCateBarService.selectMobcateBarById(parentId);
        mav.addObject("parent", parent);
        // 查询它的父分类导航的商品分类的所有子商品分类用于选取
        mav.addObject("cateList", this.goodsCateService.queryGoosCateByParentId(parent.getCateId()));
        mav.addObject("grade", 2);
        mav.setViewName("jsp/mobile/show_mobcatebar");
        return mav;
    }

    /**
     * 添加分类导航，父分类为不启用时，它也为不启用。
     * 
     * @param mobCateBar
     * @return
     */
    @RequestMapping("/createMobCateBar")
    public ModelAndView createMobCateBar(@Valid MobCateBar mobCateBar, BindingResult bindingResult, MultipartHttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView(new RedirectView(QUERY_MOB_CATE_BAR_BY_PB));
        }
        if (null == mobCateBar.getTemp2()) {
            mobCateBar.setTemp2("0");
        }
        if (null == mobCateBar.getTemp3()) {
            mobCateBar.setTemp3("0");
        }
        // 上传图片并保存地址
        MultipartFile muFile = request.getFile("imageSrc");
        if (null != muFile && muFile.getSize() > 0) {
            mobCateBar.setImgSrc(UploadUtil.uploadFileOne(muFile, request));
        }
        // 设置父分类ID和层级
        setParentIdAndGrade(mobCateBar);
        this.mobCateBarService.createMobCateBar(mobCateBar);

        String customerName = (String) request.getSession().getAttribute(NAME);
        OperaLogUtil.addOperaLog(request, customerName, "添加移动版分类导航", (String) request.getSession().getAttribute(OPERAPATH));
        return new ModelAndView(new RedirectView(QUERY_MOB_CATE_BAR_BY_PB));
    }

    /**
     * 修改分类导航，层级不可变，设置为不启用时，它的子分类也要设置为不启用
     * 
     * @param mobCateBar
     * @return
     */
    @RequestMapping("/updateMobCateBar")
    public ModelAndView updateMobCateBar(@Valid MobCateBar mobCateBar, BindingResult bindingResult, MultipartHttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView(new RedirectView(QUERY_MOB_CATE_BAR_BY_PB));
        }
        if (null == mobCateBar.getTemp2()) {
            mobCateBar.setTemp2("0");
        }
        if (null == mobCateBar.getTemp3()) {
            mobCateBar.setTemp3("0");
        }
        // 上传图片并保存地址
        MultipartFile muFile = request.getFile("imageSrc");
        if (null != muFile && muFile.getSize() > 0) {
            mobCateBar.setImgSrc(UploadUtil.uploadFileOne(muFile, request));
        }
        // 设置父分类ID和层级
        setParentIdAndGrade(mobCateBar);
        this.mobCateBarService.updateMobCateBar(mobCateBar);

        String customerName = (String) request.getSession().getAttribute(NAME);
        OperaLogUtil.addOperaLog(request, customerName, "修改移动版分类导航", (String) request.getSession().getAttribute(OPERAPATH));
        return new ModelAndView(new RedirectView(QUERY_MOB_CATE_BAR_BY_PB));
    }

    /**
     * 修改分类导航的启用状态
     * 
     * @param mobCateBarId
     * @param request
     * @return
     */
    @RequestMapping("/changeMobCateBarUserdStatus")
    public ModelAndView changeMobCateBarUserdStatus(Long mobCateBarId, HttpServletRequest request) {
        this.mobCateBarService.changeUserdStatus(mobCateBarId);

        String customerName = (String) request.getSession().getAttribute(NAME);
        OperaLogUtil.addOperaLog(request, customerName, "修改移动版分类导航启用状态", (String) request.getSession().getAttribute(OPERAPATH));
        return new ModelAndView(new RedirectView(QUERY_MOB_CATE_BAR_BY_PB));
    }

    /**
     * 设置父分类ID和层级
     * 
     * @param mobCateBar
     */
    private void setParentIdAndGrade(MobCateBar mobCateBar) {
        // 没有父分类设置为0
        if (null == mobCateBar.getParentId()) {
            mobCateBar.setParentId(0L);
        }
        // 设置层级
        if (mobCateBar.getParentId() == 0L) {
            mobCateBar.setGrade(1);
        } else {
            mobCateBar.setGrade(2);
        }
    }

    /**
     * 删除分类导航，删除时要级联删除它的子分类
     * 
     * @param mobCateBarId
     * @return
     */
    @RequestMapping("/deleteMobCateBar")
    public ModelAndView deleteMobCateBar(Long mobCateBarId, HttpServletRequest request) {
        this.mobCateBarService.deleteMobCateBar(mobCateBarId);

        String customerName = (String) request.getSession().getAttribute(NAME);
        OperaLogUtil.addOperaLog(request, customerName, "删除移动版分类导航", (String) request.getSession().getAttribute(OPERAPATH));
        return new ModelAndView(new RedirectView(QUERY_MOB_CATE_BAR_BY_PB));
    }

    /**
     * 验证分类导航的唯一性，用关联的商品分类ID验证。
     * 
     * @param cateId
     * @return
     */
    @ResponseBody
    @RequestMapping("/checkMobCateBarIsOnly")
    public boolean checkMobCateBarIsOnly(Long cateId) {
        return this.mobCateBarService.checkMobCateBarIsOnly(cateId);
    }

    /**
     * 验证是否可删除
     * 
     * @param mobCateBarId
     * @return
     */
    @ResponseBody
    @RequestMapping("/checkDele")
    public boolean checkDele(Long mobCateBarId) {
        return this.mobCateBarService.checkDelete(mobCateBarId);
    }

    // /**
    // * Ajax查询已启用、未删除的移动版分类导航
    // */
    // @ResponseBody
    // @RequestMapping("/ajaxQueryMobCateBarForSite")
    // public List<MobCateBarVo> ajaxQueryMobCateBarForSite(){
    // return this.mobCateBarService.selectMobCateBarForSite();
    // }

}
