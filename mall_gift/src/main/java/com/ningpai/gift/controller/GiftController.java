/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.gift.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.ningpai.goods.util.ValueUtil;
import com.ningpai.logcore.util.OperaLogUtil;
import com.ningpai.util.MyLogger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.common.util.ConstantUtil;
import com.ningpai.gift.bean.Gift;
import com.ningpai.gift.bean.GiftPic;
import com.ningpai.gift.service.GiftCateService;
import com.ningpai.gift.service.GiftPicService;
import com.ningpai.gift.service.GiftService;
import com.ningpai.util.Constants;
import com.ningpai.util.PageBean;

/**
 * @author ggn
 * 
 */
@Controller
public class GiftController {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(GiftController.class);

    private static final String LOGGERINFO1 = "-->赠品名称【";
    private static final String LOGGERINFO2 = "】,用户名：";

    /*
     * 赠品service
     */
    private GiftService giftService;
    /*
     * 赠品分类service
     */
    private GiftCateService giftCateService;

    private GiftPicService giftPicService;

    /**
     * 查询礼品列表分页
     * 
     * @param pageBean
     *            {@link com.ningpai.util.PageBean}
     * @param gift
     *            {@link com.ningpai.util.PageBean}
     * @return ModelAndView
     */
    @RequestMapping("/searchgiftlist")
    public ModelAndView searchGiftList(PageBean pageBean, Gift gift) {
        pageBean.setUrl(Constants.INITGIFTLIST);
        // 获取赠品bean
        return new ModelAndView(Constants.GIFTLIST, "pageBean", giftService.searchGiftList(gift, pageBean));
    }

    /**
     * 查询赠品列表
     * 
     * @param pageBean
     * @param gift
     * @return PageBean
     */
    @RequestMapping("/querygiftlistall")
    @ResponseBody
    public PageBean queryGiftListAll(HttpServletRequest request, PageBean pageBean, Gift gift) {
        // 获取名称
        String name = gift.getGiftName();
        // 判断是否为空
        if (name != null && !"".equals(name)) {
            try {
                // 转码
                name = URLDecoder.decode(name, ConstantUtil.UTF);
                gift.setGiftName(name);
            } catch (UnsupportedEncodingException e) {
                LOGGER.error("",e);
                name = null;
            }
        }
        // 查询赠品bean
        return giftService.searchGiftList(gift, pageBean);
    }

    /**
     * 去添加赠品
     * 
     * @param gift
     * @return ModelAndView
     */
    @RequestMapping("/toaddgift")
    public ModelAndView toAddGift(Gift gift) {
        // 查询赠品分类 跳转添加赠品页面
        return new ModelAndView(Constants.ADDGIFT);
    }

    /**
     * 添加赠品
     * 
     * @param request
     * @param uploadForm
     * @param gift
     * @return ModelAndView
     */
    @RequestMapping("/doaddgift")
    public ModelAndView doAddgift(HttpServletRequest request, Gift gift) {
        // 判断是否添加成功
        if (1 == giftService.doAddGift(gift) && null != gift.getGiftName()) {
            // 非空验证 赠品名称
                OperaLogUtil.addOperaLog(request, (String) request.getSession().getAttribute(ValueUtil.NAME), "添加赠品",
                        (String) request.getSession().getAttribute(ValueUtil.OPERAPATH) + LOGGERINFO1 + gift.getGiftName() + LOGGERINFO2
                                + (String) request.getSession().getAttribute(ValueUtil.NAME));
                // 记录日志
                LOGGER.info("添加赠品【" + gift.getGiftName() + "】成功！");
        }
        return new ModelAndView(new RedirectView(Constants.INITGIFTLIST));
    }

    /**
     * 去修改赠品
     * 
     * @param giftId
     * @return ModelAndView
     */
    @RequestMapping("/toupdategift")
    public ModelAndView toUpdateGift(Long giftId) {
        // 主键获取单个的赠品信息
        Gift gift = giftService.selectGiftDetailById(giftId);
        // 查询赠品跳转修改页面
        return new ModelAndView(Constants.UPDATEGIFT).addObject("giftPic", gift);
    }

    /**
     * ajax得到赠品对象
     *
     * @param giftId
     * @return ModelAndView
     */
    @RequestMapping("/ajaxupdategift")
    @ResponseBody
    public Gift ajaxupdategift(Long giftId) {
        // 主键获取单个的赠品信息，查询赠品跳转修改页面
        return giftService.selectGiftDetailById(giftId);
    }

    /**
     * 修改赠品
     * 
     * @param request
     * @param gift
     * @return ModelAndView
     */
    @RequestMapping("/doupdategift")
    public ModelAndView doUpdateGift(HttpServletRequest request, Gift gift) {
        // 判断是否修改成功
        if (1 == giftService.doUpdateGift(gift) && null != gift.getGiftName()) {
            // 非空验证 赠品名称
                OperaLogUtil.addOperaLog(request, (String) request.getSession().getAttribute(ValueUtil.NAME), "修改赠品",
                        (String) request.getSession().getAttribute(ValueUtil.OPERAPATH) + LOGGERINFO1 + gift.getGiftName() + LOGGERINFO2
                                + (String) request.getSession().getAttribute(ValueUtil.NAME));
                // 记录日志
                LOGGER.info("修改赠品【" + gift.getGiftName() + "】成功！");
        }
        return new ModelAndView(new RedirectView(Constants.INITGIFTLIST));

    }

    /**
     * 删除赠品
     * 
     * @param giftId
     * @return ModelAndView
     */
    @RequestMapping("/delgift")
    public ModelAndView delGift(HttpServletRequest request, Long giftId) {
        // 删除赠品
        if (1 == giftService.delGift(giftId)) {
            // 主键获取单个的赠品信息
            Gift gift = giftService.selectGiftDetailById(giftId);
            // 非空验证 赠品名称
            if (null != gift.getGiftName()) {
                OperaLogUtil.addOperaLog(request, (String) request.getSession().getAttribute(ValueUtil.NAME), "删除赠品",
                        (String) request.getSession().getAttribute(ValueUtil.OPERAPATH) + LOGGERINFO1 + gift.getGiftName() + LOGGERINFO2
                                + (String) request.getSession().getAttribute(ValueUtil.NAME));
                // 记录日志
                LOGGER.info("删除赠品【" + gift.getGiftName() + "】成功！");
            }
        }
        return new ModelAndView(new RedirectView(Constants.INITGIFTLIST));
    }

    /**
     * 批量删除赠品
     * 
     * @param giftId
     * @return ModelAndView
     */
    @RequestMapping("/delallgift")
    public ModelAndView delAllGift(HttpServletRequest request, Long[] giftId) {
        // 删除
        giftService.delAllGift(giftId);
        OperaLogUtil.addOperaLog(request, (String) request.getSession().getAttribute(ValueUtil.NAME), "批量删除赠品", (String) request.getSession().getAttribute(ValueUtil.OPERAPATH)
                + ",用户名：" + (String) request.getSession().getAttribute(ValueUtil.NAME));
        return new ModelAndView(new RedirectView(Constants.INITGIFTLIST));
    }

    /**
     * 查询赠品图片
     * 
     * @param giftId
     * @return List
     */
    @RequestMapping("/selectgiftpicbyId")
    @ResponseBody
    public List<GiftPic> selectGiftPicById(Long giftId) {
        // 主键获取单个的赠品信息
        Gift gift = giftService.selectGiftDetailById(giftId);
        // 非空验证 赠品名称
        if (null != gift.getGiftCateName()) {
            // 记录日志
            LOGGER.info("查询赠品【" + gift.getGiftCateName() + "】下面所包含的图片！");
        }
        return giftPicService.selectGiftPicByGiftId(giftId);
    }

    /**
     * 查询赠品图片
     * 
     * @param picId
     * @return int
     */
    @RequestMapping("/deletepicbypicid")
    @ResponseBody
    public int deletePicByPicId(Long picId) {
        return giftPicService.delGiftPicByPicId(picId);
    }

    /**
     * 老boss批量添加赠品图片
     * 
     * @param giftIds
     * @param request
     * @param request2
     * @return
     */
    @RequestMapping("/batchSaveGiftImage")
    @ResponseBody
    public GiftPic batchSaveGiftImage(Long giftIds, HttpServletRequest request, MultipartHttpServletRequest request2, Long flag, String[] url) {

        // 获取所有的File控件
        List<MultipartFile> fileList = request2.getFiles("filedata");
        OperaLogUtil.addOperaLog(request, (String) request.getSession().getAttribute(ValueUtil.NAME), "批量添加赠品", (String) request.getSession().getAttribute(ValueUtil.OPERAPATH)
                + ",用户名：" + (String) request.getSession().getAttribute(ValueUtil.NAME));
        // 到Service进行添加
        return this.giftPicService.uploadImage(giftIds, (String) request.getSession().getAttribute(""), fileList, request);

    }

    /**
     * 新boss批量添加赠品图片
     *
     * @param giftIds
     * @param url
     * @return
     */
    @RequestMapping("/newbatchSaveGiftImage")
    @ResponseBody
    public Boolean batchSaveGiftImage(Long giftIds, String[] url) {
        return giftPicService.newuploadImage(giftIds, url);
    }

    public GiftService getGiftService() {
        return giftService;
    }

    @Resource(name = "GiftService")
    public void setGiftService(GiftService giftService) {
        this.giftService = giftService;
    }

    public GiftCateService getGiftCateService() {
        return giftCateService;
    }

    @Resource(name = "GiftCateService")
    public void setGiftCateService(GiftCateService giftCateService) {
        this.giftCateService = giftCateService;
    }

    public GiftPicService getGiftPicService() {
        return giftPicService;
    }

    @Resource(name = "GiftPicService")
    public void setGiftPicService(GiftPicService giftPicService) {
        this.giftPicService = giftPicService;
    }

}
