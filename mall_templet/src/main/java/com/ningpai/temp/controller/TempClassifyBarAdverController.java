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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.channel.bean.ChannelAdver;
import com.ningpai.channel.service.ChannelAdverService;
import com.ningpai.common.util.ConstantUtil;
import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.publicpackage.InfoImageManagePublic;
import com.ningpai.temp.bean.ClassifyBar;
import com.ningpai.temp.service.ClassifyBarService;
import com.ningpai.temp.service.TempService;
import com.ningpai.util.PageBean;
import com.ningpai.util.UploadUtil;

/**
 * 控制器-分类导航广告
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年5月5日上午9:46:09
 */
@Controller
public class TempClassifyBarAdverController {

    private static final String TEMP3 = "1";

    /* 业务逻辑层依赖 */
    private TempService tempService;
    private ClassifyBarService classifyBarService;
    private ChannelAdverService channelAdverService;
    @Resource(name = "InfoImageManagePublic")
    private InfoImageManagePublic infoImageManagePublic;

    // session中保存的登录用户的id
    private static final String LOGINUSERID = "loginUserId";
    private static final String LIST_VIEW = "jsp/temp/temp_classifybar_adver_list";
    private static final String BOXLIST_VIEW = "jsp/temp/temp_classifybarbox_adver_list";
    private static final String IMGSRC = "imgSrc";
    private static final String MAP = "map";
    private static final String QUERYCLASSIFYADVERBYPAGEBEAN_HTM = "queryClassifyAdverByPageBean.htm?temp1=";
    private static final String QUERYCLASSIFYADVERBYBOX_TEMPID = "queryClassifyAdverByBox.htm?tempId=";
    private static final String QUERYCLASSIFYADVERBYBOX_CHANNELID = "queryClassifyAdverByBox.htm?channelId=";
    private static final String LOGGERINFO1 = ",用户名:";

    /**
     * 用户名称
     */
    public static final String NAME = "name";

    /**
     * "operaPath"
     */
    public static final String OPERAPATH = "operaPath";

    /**
     * 分页查看分类导航广告设置
     * 
     * @param pb
     *            分页参数
     * @param temp1
     *            分类导航ID
     * @return
     */
    @RequestMapping("/queryClassifyAdverByPageBean")
    public ModelAndView queryClassifyAdverByPageBean(PageBean pb, String temp1) {
        Map<String, Object> map = new HashMap<String, Object>();
        ClassifyBar classifyBar = classifyBarService.getClassifyBarById(Long.valueOf(temp1));
        map.put("classifyBar", classifyBar);
        map.put("pb", channelAdverService.selectClassifyBarAdverByParam(pb, null, null, temp1, null));
        return new ModelAndView(LIST_VIEW, MAP, map);
    }

    /**
     * 查看分类导航广告
     * 
     * @param channelAdverId
     * @param temp1
     * @return
     */
    @RequestMapping("/showClassifyAdver")
    public ModelAndView showClassifyAdver(Long channelAdverId, String temp1) {
        Map<String, Object> map = new HashMap<String, Object>();
        ClassifyBar classifyBar = classifyBarService.getClassifyBarById(Long.valueOf(temp1));
        map.put("classifyBar", classifyBar);
        if (null != channelAdverId) {
            ChannelAdver channelAdver = this.channelAdverService.selectByPrimaryKey(channelAdverId);
            map.put("channelAdver", channelAdver);
        }
        return new ModelAndView("jsp/temp/show_temp_classifybar_adver", MAP, map);
    }

    /**
     * 添加分类导航广告
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/createClassifyAdver")
    public ModelAndView createClassifyAdver(MultipartHttpServletRequest request, HttpServletResponse response, @Valid ChannelAdver channelAdver, BindingResult bindingResult,
            Integer width, Integer height) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView(new RedirectView(QUERYCLASSIFYADVERBYPAGEBEAN_HTM + channelAdver.getTemp1() + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN)));
        }
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        MultipartFile muFile = request.getFile(IMGSRC);
        if (null != muFile && muFile.getSize() > 0) {
            channelAdver.setAdverPath(UploadUtil.uploadFileOne(muFile, request));
            // 保存图片信息，以供以后选择
            infoImageManagePublic.saveImage(channelAdver.getAdverPath());
        }
        channelAdver.setCreateUserId(loginUserId);
        channelAdverService.saveChannelAdver(channelAdver);
        String customerName = (String) request.getSession().getAttribute(NAME);
        OperaLogUtil.addOperaLog(request, customerName, "添加分类导航广告", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
        return new ModelAndView(new RedirectView(QUERYCLASSIFYADVERBYPAGEBEAN_HTM + channelAdver.getTemp1() + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN)));
    }

    /**
     * 修改分类导航广告
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/updateClassifyAdver")
    public ModelAndView updateClassifyAdver(MultipartHttpServletRequest request, HttpServletResponse response, @Valid ChannelAdver channelAdver, BindingResult bindingResult,
            Integer width, Integer height) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView(new RedirectView(QUERYCLASSIFYADVERBYPAGEBEAN_HTM + channelAdver.getTemp1() + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN)));
        }
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        MultipartFile muFile = request.getFile(IMGSRC);
        if (null != muFile && muFile.getSize() > 0) {
            channelAdver.setAdverPath(UploadUtil.uploadFileOne(muFile, request));
            // 保存图片信息，以供以后选择
            infoImageManagePublic.saveImage(channelAdver.getAdverPath());
        }
        channelAdver.setUpdateUserId(loginUserId);
        channelAdverService.updateChannelAdver(channelAdver);
        String customerName = (String) request.getSession().getAttribute(NAME);
        OperaLogUtil.addOperaLog(request, customerName, "修改分类导航广告", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
        return new ModelAndView(new RedirectView(QUERYCLASSIFYADVERBYPAGEBEAN_HTM + channelAdver.getTemp1() + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN)));
    }

    /**
     * 分页查看分类导航父框广告
     * 
     * @param pb
     *            分页参数
     * @return
     */
    @RequestMapping("/queryClassifyAdverByBox")
    public ModelAndView queryClassifyAdverByBox(PageBean pb, Long tempId, Long channelId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("pb", channelAdverService.selectClassifyBarAdverByParam(pb, tempId, channelId, null, TEMP3));
        map.put("tempId", tempId);
        map.put("channelId", channelId);
        return new ModelAndView(BOXLIST_VIEW, MAP, map);
    }

    /**
     * 查看分类导航父框广告
     * 
     * @param channelAdverId
     * @param tempId
     * @return
     */
    @RequestMapping("/showClassifyAdverByBox")
    public ModelAndView showClassifyAdverByBox(Long channelAdverId, Long tempId, Long channelId) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (null != channelAdverId) {
            ChannelAdver channelAdver = this.channelAdverService.selectByPrimaryKey(channelAdverId);
            map.put("channelAdver", channelAdver);
        }
        map.put("tempId", tempId);
        map.put("channelId", channelId);
        map.put("temp3", TEMP3);
        return new ModelAndView("jsp/temp/show_temp_classifybarbox_adver", MAP, map);
    }

    /**
     * 添加分类导航父框广告
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/createClassifyAdverBox")
    public ModelAndView createClassifyAdverBox(MultipartHttpServletRequest request, HttpServletResponse response, @Valid ChannelAdver channelAdver, BindingResult bindingResult,
            Integer width, Integer height) {
        if (bindingResult.hasErrors()) {
            if (null == channelAdver.getChannelId()) {
                return new ModelAndView(new RedirectView(QUERYCLASSIFYADVERBYBOX_TEMPID + channelAdver.getTempId() + ConstantUtil.CSRF
                        + request.getParameter(ConstantUtil.CSRFTOKEN)));
            } else {
                return new ModelAndView(new RedirectView(QUERYCLASSIFYADVERBYBOX_CHANNELID + channelAdver.getChannelId() + ConstantUtil.CSRF
                        + request.getParameter(ConstantUtil.CSRFTOKEN)));
            }
        }
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        MultipartFile muFile = request.getFile(IMGSRC);
        if (null != muFile && muFile.getSize() > 0) {
            channelAdver.setAdverPath(UploadUtil.uploadFileOne(muFile, request));
            // 保存图片信息，以供以后选择
            infoImageManagePublic.saveImage(channelAdver.getAdverPath());
        }
        if (null == loginUserId) {
            loginUserId = 1L;
        }
        channelAdver.setCreateUserId(loginUserId);
        channelAdverService.saveChannelAdver(channelAdver);
        String customerName = (String) request.getSession().getAttribute(NAME);
        OperaLogUtil.addOperaLog(request, customerName, "添加分类导航框广告", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
        String view = "";
        if (null == channelAdver.getChannelId()) {
            view = QUERYCLASSIFYADVERBYBOX_TEMPID + channelAdver.getTempId() + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN);
        } else {
            view = QUERYCLASSIFYADVERBYBOX_CHANNELID + channelAdver.getChannelId() + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN);
        }
        return new ModelAndView(new RedirectView(view));
    }

    /**
     * 修改分类导航父框广告
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/updateClassifyAdverBox")
    public ModelAndView updateClassifyAdverBox(MultipartHttpServletRequest request, HttpServletResponse response, @Valid ChannelAdver channelAdver, BindingResult bindingResult,
            Integer width, Integer height) {
        if (bindingResult.hasErrors()) {
            if (null == channelAdver.getChannelId()) {
                return new ModelAndView(new RedirectView(QUERYCLASSIFYADVERBYBOX_TEMPID + channelAdver.getTempId() + ConstantUtil.CSRF
                        + request.getParameter(ConstantUtil.CSRFTOKEN)));
            } else {
                return new ModelAndView(new RedirectView(QUERYCLASSIFYADVERBYBOX_CHANNELID + channelAdver.getChannelId() + ConstantUtil.CSRF
                        + request.getParameter(ConstantUtil.CSRFTOKEN)));
            }
        }
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        MultipartFile muFile = request.getFile(IMGSRC);
        if (null != muFile && muFile.getSize() > 0) {
            channelAdver.setAdverPath(UploadUtil.uploadFileOne(muFile, request));
            // 保存图片信息，以供以后选择
            infoImageManagePublic.saveImage(channelAdver.getAdverPath());
        }
        if (null == loginUserId) {
            loginUserId = 1L;
        }
        channelAdver.setUpdateUserId(loginUserId);
        channelAdverService.updateChannelAdver(channelAdver);
        String customerName = (String) request.getSession().getAttribute(NAME);
        OperaLogUtil.addOperaLog(request, customerName, "修改分类导航框广告", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
        String view = "";
        if (null == channelAdver.getChannelId()) {
            view = QUERYCLASSIFYADVERBYBOX_TEMPID + channelAdver.getTempId() + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN);
        } else {
            view = QUERYCLASSIFYADVERBYBOX_CHANNELID + channelAdver.getChannelId() + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN);
        }
        return new ModelAndView(new RedirectView(view));
    }

    /**
     * 删除分类导航广告
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/deleteClassifyAdver")
    public void deleteClassifyAdver(HttpServletRequest request, HttpServletResponse response) {
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        String[] adverIds = request.getParameterValues("adverIds[]");
        for (int i = 0; i < adverIds.length; i++) {
            Long id = Long.valueOf(adverIds[i]);
            channelAdverService.deleteChannelAdver(id, loginUserId);
        }
        String customerName = (String) request.getSession().getAttribute(NAME);
        OperaLogUtil.addOperaLog(request, customerName, "删除分类导航广告", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
    }

    /**
     * 分页查看分类导航广告设置(新)
     *
     * @param pb
     *            分页参数
     * @param temp1
     *            分类导航ID
     * @return
     */
    @RequestMapping("/queryclassifyadverbypagebeanajax")
    @ResponseBody
    public Map<String, Object> queryClassifyAdverByPageBeanAjax(PageBean pb, String temp1, Long tempId, Long channelId, String temp3) {
        // 设置参数
        Map<String, Object> map = new HashMap<String, Object>();
        // 设置集合
        map.put("pb", channelAdverService.selectClassifyBarAdverByParam(pb, tempId, channelId, temp1, temp3));
        return map;
    }

    /**
     * 添加分类导航广告(新)
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/createclassifyadverajax")
    @ResponseBody
    public int createClassifyAdverAjax(HttpServletRequest request, HttpServletResponse response, @Valid ChannelAdver channelAdver, BindingResult bindingResult, Integer width,
            Integer height) {
        // 判断参数是否符合规范
        if (bindingResult.hasErrors()) {
            // 返回结果
            return 0;
        }
        // 获取登录id
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        // 设置创建人id
        channelAdver.setCreateUserId(loginUserId);
        // 获取用户名
        String customerName = (String) request.getSession().getAttribute(NAME);
        // 记录日志
        OperaLogUtil.addOperaLog(request, customerName, "添加分类导航广告", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
        // 返回结果
        // 1：成功
        // 0:失败
        return channelAdverService.saveChannelAdver(channelAdver);
    }

    /**
     * 查看分类导航广告（新）
     *
     * @param channelAdverId
     * @param temp1
     * @return
     */
    @RequestMapping("/showclassifyadverajax")
    @ResponseBody
    public ClassifyBar showClassifyAdverAjax(Long channelAdverId, String temp1) {
        // 返回结果
        return classifyBarService.getClassifyBarById(Long.valueOf(temp1));

    }

    /**
     * 修改分类导航广告
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/updateclassifyadverajax")
    @ResponseBody
    public int updateClassifyAdverAjax(HttpServletRequest request, HttpServletResponse response, @Valid ChannelAdver channelAdver, BindingResult bindingResult, Integer width,
            Integer height) {
        if (bindingResult.hasErrors()) {
            return 0;
        }
        // 获取id
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        // 设置更新人id
        channelAdver.setUpdateUserId(loginUserId);
        // 获取用户名
        String customerName = (String) request.getSession().getAttribute(NAME);
        // 记录日志
        OperaLogUtil.addOperaLog(request, customerName, "修改分类导航广告", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
        // 返回结果
        // 1：成功
        // 0：失败
        return channelAdverService.updateChannelAdver(channelAdver);
    }

    public TempService getTempService() {
        return tempService;
    }

    @Resource(name = "TempService")
    public void setTempService(TempService tempService) {
        this.tempService = tempService;
    }

    public ChannelAdverService getChannelAdverService() {
        return channelAdverService;
    }

    @Resource(name = "ChannelAdverService")
    public void setChannelAdverService(ChannelAdverService channelAdverService) {
        this.channelAdverService = channelAdverService;
    }

    public ClassifyBarService getClassifyBarService() {
        return classifyBarService;
    }

    @Resource(name = "ClassifyBarService")
    public void setClassifyBarService(ClassifyBarService classifyBarService) {
        this.classifyBarService = classifyBarService;
    }

}
