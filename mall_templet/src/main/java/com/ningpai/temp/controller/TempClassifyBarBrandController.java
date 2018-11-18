/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.temp.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.channel.bean.ChannelTrademark;
import com.ningpai.channel.service.ChannelTrademarkService;
import com.ningpai.common.util.ConstantUtil;
import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.temp.bean.ClassifyBar;
import com.ningpai.temp.service.ClassifyBarService;
import com.ningpai.temp.service.TempService;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;

/**
 * 控制器-分类导航品牌
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年5月5日上午10:54:08
 */
@Controller
public class TempClassifyBarBrandController {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(TempClassifyBarBrandController.class);

    private static final String LOGINUSERID = "loginUserId";

    public static final String NAME = "name";

    public static final String OPERAPATH = "operaPath";

    private static final String TEMP2 = "1";

    private static final String LOGGERINFO1 = ",用户名:";
    private static final String QUERYCLASSIFYBARBRANDBYPAGEBEAN_HTM = "queryClassifybarBrandByPageBean.htm?temp1=";
    private static final String QUERYCLASSIFYBARBRANDBYBOX_TEMPID = "queryClassifybarBrandByBox.htm?tempId=";
    private static final String QUERYCLASSIFYBARBRANDBYBOX_CHANNELID = "queryClassifybarBrandByBox.htm?channelId=";

    /** 频道业务接口 */
    private TempService tempService;

    private ClassifyBarService classifyBarService;

    /** 模板品牌设置业务类 */
    private ChannelTrademarkService channelTrademarkService;

    /**
     * 分页查询分类导航品牌
     * 
     * @param pb
     * @param temp1
     *            分类导航ID
     * @return
     */
    @RequestMapping("/queryClassifybarBrandByPageBean")
    public ModelAndView queryClassifybarBrandByPageBean(PageBean pb, String temp1) {
        ModelAndView mav = new ModelAndView();
        ClassifyBar classifyBar = classifyBarService.getClassifyBarById(Long.valueOf(temp1));
        mav.addObject("classifyBar", classifyBar);
        mav.addObject("pb", channelTrademarkService.selectClassifyTrademarkByParam(pb, null, null, temp1, null));
        mav.setViewName("jsp/temp/temp_classifybar_brand_list");
        return mav;
    }

    /**
     * 查看分类导航品牌
     * 
     * @param trademarkId
     *            品牌ID
     * @param temp1
     *            分类导航ID
     * @return
     */
    @RequestMapping("/showClassifybarBrand")
    public ModelAndView showClassifybarBrand(Long trademarkId, String temp1) {
        ClassifyBar classifyBar = classifyBarService.getClassifyBarById(Long.valueOf(temp1));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("classifyBar", classifyBar);
        if (null != trademarkId) {
            ChannelTrademark channelTrademark = this.channelTrademarkService.getChannelTrademarkById(trademarkId);
            map.put("channelTrademark", channelTrademark);
        }
        return new ModelAndView("jsp/temp/show_temp_classifybar_brand", "map", map);
    }

    /**
     * 添加模板品牌
     * 
     * @param request
     * @param channelTrademark
     * @param bindingResult
     * @return
     */
    @RequestMapping("/createClassifybarBrand")
    public ModelAndView createClassifybarBrand(HttpServletRequest request, @Valid ChannelTrademark channelTrademark, BindingResult bindingResult, String temp1) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView(new RedirectView(QUERYCLASSIFYBARBRANDBYPAGEBEAN_HTM + temp1 + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN)));
        }
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        ModelAndView mav = new ModelAndView();
        try {
            if (null == loginUserId) {
                channelTrademark.setCreateUserId(1L);
            } else {
                channelTrademark.setCreateUserId(loginUserId);
            }
            // 添加分类导航品牌
            int n = channelTrademarkService.saveChannelTrademark(channelTrademark);
            if (n > 0) {
                String customerName = (String) request.getSession().getAttribute(NAME);
                OperaLogUtil.addOperaLog(request, customerName, "添加分类导航品牌", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
                LOGGER.debug("保存分类导航品牌成功！");
            } else {
                LOGGER.debug("保存分类导航品牌失败！");
            }
        } catch (Exception e) {
            LOGGER.error("保存模板品牌异常！", e);
        }
        mav.setView(new RedirectView(QUERYCLASSIFYBARBRANDBYPAGEBEAN_HTM + temp1 + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN)));
        return mav;
    }

    /**
     * 修改模板品牌
     * 
     * @param request
     * @param channelTrademark
     * @param bindingResult
     * @return
     */
    @RequestMapping("/modifClassifybarBrand")
    public ModelAndView updateClassifybarBrand(HttpServletRequest request, @Valid ChannelTrademark channelTrademark, BindingResult bindingResult, String temp1) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView(new RedirectView(QUERYCLASSIFYBARBRANDBYPAGEBEAN_HTM + temp1 + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN)));
        }
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        ModelAndView mav = new ModelAndView();
        try {
            if (null == loginUserId) {
                channelTrademark.setUpdateUserId(1L);
            } else {
                channelTrademark.setUpdateUserId(loginUserId);
            }
            // 修改分类导航品牌
            int n = this.channelTrademarkService.updateChannelTrademark(channelTrademark);
            if (n > 0) {
                String customerName = (String) request.getSession().getAttribute(NAME);
                OperaLogUtil.addOperaLog(request, customerName, "修改分类导航品牌", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
                LOGGER.debug("修改分类导航品牌成功！");
            } else {
                LOGGER.debug("修改分类导航品牌失败！");
            }
        } catch (Exception e) {
            LOGGER.error("修改模板品牌异常！", e);
        }
        mav.setView(new RedirectView(QUERYCLASSIFYBARBRANDBYPAGEBEAN_HTM + temp1 + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN)));
        return mav;
    }

    /**
     * 分页查询分类导航父框品牌
     * 
     * @param pb
     * @param tempId
     *            分类导航ID
     * @param channelId
     *            是否分类导航父框
     * @return
     */
    @RequestMapping("/queryClassifybarBrandByBox")
    public ModelAndView queryClassifybarBrandByBox(PageBean pb, Long tempId, Long channelId) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("pb", channelTrademarkService.selectClassifyTrademarkByParam(pb, tempId, channelId, null, TEMP2));
        mav.addObject("tempId", tempId);
        mav.addObject("channelId", channelId);
        mav.setViewName("jsp/temp/temp_classifybarbox_brand_list");
        return mav;
    }

    /**
     * 查看分类导航父框品牌
     * 
     * @param trademarkId
     *            品牌ID
     * @param tempId
     *            分类导航ID
     * @param channelId
     *            是否分类导航父框
     * @return
     */
    @RequestMapping("/showClassifybarBrandBox")
    public ModelAndView showClassifybarBrandBox(Long trademarkId, Long tempId, Long channelId) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (null != trademarkId) {
            ChannelTrademark channelTrademark = this.channelTrademarkService.getChannelTrademarkById(trademarkId);
            map.put("channelTrademark", channelTrademark);
        }
        map.put("tempId", tempId);
        map.put("channelId", channelId);
        map.put("temp2", TEMP2);
        return new ModelAndView("jsp/temp/show_temp_classifybarbox_brand", "map", map);
    }

    /**
     * 添加分类导航父框品牌
     * 
     * @param request
     * @param channelTrademark
     * @param bindingResult
     * @return
     */
    @RequestMapping("/createClassifybarBrandBox")
    public ModelAndView createClassifybarBrandBox(HttpServletRequest request, @Valid ChannelTrademark channelTrademark, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            if (null == channelTrademark.getChannelId()) {
                return new ModelAndView(new RedirectView(QUERYCLASSIFYBARBRANDBYBOX_TEMPID + channelTrademark.getTempId() + ConstantUtil.CSRF
                        + request.getParameter(ConstantUtil.CSRFTOKEN)));
            } else {
                return new ModelAndView(new RedirectView(QUERYCLASSIFYBARBRANDBYBOX_CHANNELID + channelTrademark.getChannelId() + ConstantUtil.CSRF
                        + request.getParameter(ConstantUtil.CSRFTOKEN)));
            }
        }
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        ModelAndView mav = new ModelAndView();
        String view = "";
        if (null == loginUserId) {
            loginUserId = 1L;
        }
        channelTrademark.setCreateUserId(loginUserId);
        // 添加频道品牌
        channelTrademarkService.saveChannelTrademark(channelTrademark);
        String customerName = (String) request.getSession().getAttribute(NAME);
        OperaLogUtil.addOperaLog(request, customerName, "添加分类导航框品牌", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
        if (null == channelTrademark.getChannelId()) {
            view = QUERYCLASSIFYBARBRANDBYBOX_TEMPID + channelTrademark.getTempId() + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN);
        } else {
            view = QUERYCLASSIFYBARBRANDBYBOX_CHANNELID + channelTrademark.getChannelId() + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN);
        }
        mav.setView(new RedirectView(view));
        return mav;
    }

    /**
     * 修改分类导航父框品牌
     * 
     * @param request
     * @param channelTrademark
     * @param bindingResult
     * @return
     */
    @RequestMapping("/modifClassifybarBrandBox")
    public ModelAndView updateClassifybarBrandBox(HttpServletRequest request, @Valid ChannelTrademark channelTrademark, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            if (null == channelTrademark.getChannelId()) {
                return new ModelAndView(new RedirectView(QUERYCLASSIFYBARBRANDBYBOX_TEMPID + channelTrademark.getTempId() + ConstantUtil.CSRF
                        + request.getParameter(ConstantUtil.CSRFTOKEN)));
            } else {
                return new ModelAndView(new RedirectView(QUERYCLASSIFYBARBRANDBYBOX_CHANNELID + channelTrademark.getChannelId() + ConstantUtil.CSRF
                        + request.getParameter(ConstantUtil.CSRFTOKEN)));
            }
        }
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        ModelAndView mav = new ModelAndView();
        String view = "";
        if (null == loginUserId) {
            loginUserId = 1L;
        }
        channelTrademark.setCreateUserId(loginUserId);
        // 添加频道品牌
        channelTrademarkService.updateChannelTrademark(channelTrademark);
        String customerName = (String) request.getSession().getAttribute(NAME);
        OperaLogUtil.addOperaLog(request, customerName, "修改分类导航框品牌", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
        if (null == channelTrademark.getChannelId()) {
            view = QUERYCLASSIFYBARBRANDBYBOX_TEMPID + channelTrademark.getTempId() + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN);
        } else {
            view = QUERYCLASSIFYBARBRANDBYBOX_CHANNELID + channelTrademark.getChannelId() + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN);
        }
        mav.setView(new RedirectView(view));
        return mav;
    }

    /**
     * 删除分类导航品牌
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/delClassifybarBrand")
    public void deleteClassifybarBrand(HttpServletRequest request, HttpServletResponse response) {
        String[] trademarkIds = request.getParameterValues("trademarkIds[]");
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        if (null == loginUserId) {
            loginUserId = 1L;
        }
        for (int i = 0; i < trademarkIds.length; i++) {
            channelTrademarkService.deleteChannelTrademark(Long.valueOf(trademarkIds[i]), loginUserId);
        }
        String customerName = (String) request.getSession().getAttribute(NAME);
        OperaLogUtil.addOperaLog(request, customerName, "删除分类导航品牌", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
    }

    /**
     * 分页查询分类导航品牌(新)
     *
     * @param pb
     * @param temp1
     *            分类导航ID
     * @return
     */
    @RequestMapping("/queryclassifybarbrandbypagebeanajax")
    @ResponseBody
    public Map<String, Object> queryClassifybarBrandByPageBeanAjax(PageBean pb, String temp1, Long tempId, Long channelId, String temp2) {
        // 设置map集合
        Map<String, Object> map = new HashMap<String, Object>();
        // 设置集合
        map.put("pb", channelTrademarkService.selectClassifyTrademarkByParam(pb, tempId, channelId, temp1, temp2));
        // 返回
        return map;
    }

    public TempService getTempService() {
        return tempService;
    }

    @Resource(name = "TempService")
    public void setTempService(TempService tempService) {
        this.tempService = tempService;
    }

    public ChannelTrademarkService getChannelTrademarkService() {
        return channelTrademarkService;
    }

    @Resource(name = "ChannelTrademarkService")
    public void setChannelTrademarkService(ChannelTrademarkService channelTrademarkService) {
        this.channelTrademarkService = channelTrademarkService;
    }

    public ClassifyBarService getClassifyBarService() {
        return classifyBarService;
    }

    @Resource(name = "ClassifyBarService")
    public void setClassifyBarService(ClassifyBarService classifyBarService) {
        this.classifyBarService = classifyBarService;
    }

}
