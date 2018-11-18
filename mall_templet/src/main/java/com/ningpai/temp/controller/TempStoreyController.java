/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.temp.controller;

import java.util.HashMap;
import java.util.List;
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

import com.ningpai.channel.bean.ChannelStorey;
import com.ningpai.channel.bean.GoodsCate;
import com.ningpai.channel.service.GoodsCateService;
import com.ningpai.channel.service.impl.ChannelStoreyServiceImpl;
import com.ningpai.common.util.ConstantUtil;
import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.publicpackage.InfoImageManagePublic;
import com.ningpai.temp.bean.SysTemp;
import com.ningpai.temp.service.TempService;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;
import com.ningpai.util.UploadUtil;

/**
 * 控制器-模板楼层
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年5月5日上午11:21:34
 */
@Controller
public class TempStoreyController {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(TempStoreyController.class);

    private static final String REDIRECT = "queryTempStoreyByPageBean.htm?tempId=";
    private static final String LOGGERINFO1 = ",用户名:";
    private static final String LOGGERINFO2 = "保存楼层设置成功！";
    private static final String LOGGERINFO3 = "保存楼层设置失败！";
    private static final String LOGGERINFO4 = "保存楼层设置对象异常！";
    private static final String LOGGERINFO5 = "删除模板楼层";

    /** 频道业务接口 */
    private TempService tempService;

    /** 频道楼层业务接口 */
    private ChannelStoreyServiceImpl channelStoreyServiceImpl;

    /** 商品分类业务接口 */
    private GoodsCateService goodsCateService;

    @Resource(name = "InfoImageManagePublic")
    private InfoImageManagePublic infoImageManagePublic;

    private static final String LOGINUSERID = "loginUserId";

    /**
     * 用户名称
     */
    public static final String NAME = "name";

    /**
     * "operaPath"
     */
    public static final String OPERAPATH = "operaPath";

    /**
     * 分页查看模板楼层设置
     * 
     * @param pb
     * @param tempId
     * @return
     */
    @RequestMapping("/queryTempStoreyByPageBean")
    public ModelAndView queryTempStoreyByPageBean(PageBean pb, Long tempId) {
        SysTemp temp = this.tempService.getSystempById(tempId);
        /* 设置查询参数，获取查询后的pb */
        return new ModelAndView("jsp/temp/temp_storey_list", "pb", channelStoreyServiceImpl.selectchannelStoreyByParam(pb, null, tempId, null)).addObject("temp", temp);
    }

    /**
     * 分页查看模板楼层设置（新）
     *
     * @param pb
     * @param tempId
     * @return
     */
    @RequestMapping("/querytempstoreybypagebeanajax")
    @ResponseBody
    public Map<String, Object> queryTempStoreyByPageBeanAjax(PageBean pb, Long tempId) {
        // 设置查询参数
        Map<String, Object> map = new HashMap<String, Object>();
        // 获取集合
        map.put("pb", channelStoreyServiceImpl.selectchannelStoreyByParam(pb, null, tempId, null));
        // 设置楼层分类
        map.put("goodsCate", this.goodsCateService.queryAllFirstGradeGoosCate());
        // 返回列表
        return map;
    }

    /**
     * 查看模板楼层
     * 
     * @param storeyId
     * @param tempId
     * @return
     */
    @RequestMapping("/showTempStorey")
    public ModelAndView showTempStorey(Long storeyId, Long tempId) {
        Map<String, Object> map = new HashMap<String, Object>();
        SysTemp temp = this.tempService.getSystempById(tempId);
        map.put("temp", temp);
        List<GoodsCate> goodsCates = this.goodsCateService.queryAllFirstGradeGoosCate();
        map.put("goodsCates", goodsCates);
        if (null != storeyId) {
            ChannelStorey channelStorey = this.channelStoreyServiceImpl.getChannelStoreyById(storeyId);
            map.put("channelStorey", channelStorey);
        }
        return new ModelAndView("jsp/temp/show_temp_storey", "map", map);
    }

    /**
     * 查看模板楼层(新)
     *
     * @param storeyId
     * @param tempId
     * @return
     */
    @RequestMapping("/showtempstoreynew")
    @ResponseBody
    public ChannelStorey showTempStoreyNew(Long storeyId, Long tempId) {
        return this.channelStoreyServiceImpl.getChannelStoreyById(storeyId);
    }

    /**
     * 添加模板楼层
     * 
     * @param request
     * @param response
     * @param channelStorey
     * @param tempId
     * @return
     */
    @RequestMapping("/createTempStorey")
    public ModelAndView createTempStorey(MultipartHttpServletRequest request, HttpServletResponse response, @Valid ChannelStorey channelStorey, BindingResult bindingResult,
            Long tempId) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView(new RedirectView(REDIRECT + tempId + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN)));
        }
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        ModelAndView mav = new ModelAndView();
        try {
            // 待文件上传区
            MultipartFile file = request.getFile("netLogo");
            // 若有数据则上传文件
            if (file.getSize() != 0) {
                channelStorey.setStoreyImg(UploadUtil.uploadFileOne(file, request));
                // 保存图片信息，以供以后选择
                infoImageManagePublic.saveImage(channelStorey.getStoreyImg());
            }
            MultipartFile file2 = request.getFile("netLogo_temp2");
            // 若有数据则上传文件
            if (null != file2 && file2.getSize() != 0) {
                channelStorey.setTemp2(UploadUtil.uploadFileOne(file2, request));
                // 保存图片信息，以供以后选择
                infoImageManagePublic.saveImage(channelStorey.getTemp2());
            }
            channelStorey.setTempId(tempId);
            if (null == loginUserId) {
                loginUserId = 1L;
            }
            channelStorey.setCreateUserId(loginUserId);
            // 添加楼层设置
            int n = channelStoreyServiceImpl.saveChannelStorey(channelStorey);
            if (n > 0) {
                String customerName = (String) request.getSession().getAttribute(NAME);
                OperaLogUtil.addOperaLog(request, customerName, "添加模板楼层", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
                LOGGER.debug(LOGGERINFO2);
            } else {
                LOGGER.debug(LOGGERINFO3);
            }
        } catch (Exception e) {
            LOGGER.error(LOGGERINFO4, e);
        }
        mav.setView(new RedirectView(REDIRECT + tempId + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN)));
        // 返回楼层设置列表
        return mav;
    }

    /**
     * 添加模板楼层（新）
     *
     * @param request
     * @param response
     * @param channelStorey
     * @param tempId
     * @return
     */
    @RequestMapping("/createtempstoreyajax")
    @ResponseBody
    public int createTempStoreyAjax(HttpServletRequest request, HttpServletResponse response, @Valid ChannelStorey channelStorey, BindingResult bindingResult, Long tempId) {
        // 判断是否符合规范
        if (bindingResult.hasErrors()) {
            // 返回
            return 0;
        }
        // 获取登录名称
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        // 设置模板id
        channelStorey.setTempId(tempId);
        // 设置登录id
        channelStorey.setCreateUserId(loginUserId);
        // 添加楼层设置
        int n = channelStoreyServiceImpl.saveChannelStorey(channelStorey);
        if (n > 0) {
            String customerName = (String) request.getSession().getAttribute(NAME);
            OperaLogUtil.addOperaLog(request, customerName, "添加模板楼层", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
            LOGGER.debug(LOGGERINFO2);
        } else {
            LOGGER.debug(LOGGERINFO3);
        }
        return n;
    }

    /**
     * 修改模板楼层
     * 
     * @param request
     * @param response
     * @param channelStorey
     * @param tempId
     * @return
     */
    @RequestMapping("/updatetempstoreyajax")
    @ResponseBody
    public int updatetempstoreyajax(HttpServletRequest request, HttpServletResponse response, @Valid ChannelStorey channelStorey, BindingResult bindingResult, Long tempId) {
        // 判断是否符规范
        if (bindingResult.hasErrors()) {
            // 返回结果
            return 0;
        }
        // 获取用户名
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        // 设置模板id
        channelStorey.setTempId(tempId);
        // 设置登录id
        channelStorey.setUpdateUserId(loginUserId);
        // 修改楼层设置
        int n = channelStoreyServiceImpl.updateChannelStorey(channelStorey);
        // 判断结果
        if (n > 0) {
            // 获取用户名
            String customerName = (String) request.getSession().getAttribute(NAME);
            // 记录日志
            OperaLogUtil.addOperaLog(request, customerName, "修改模板楼层", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
            // 记录日志
            LOGGER.debug("修改楼层设置成功！");
        } else {
            // 记录失败日志
            LOGGER.debug("修改楼层设置失败！");
        }
        // 返回结果
        // 1：成功
        // 0：失败
        return n;

    }

    /**
     * 修改模板楼层
     *
     * @param request
     * @param response
     * @param channelStorey
     * @param tempId
     * @return
     */
    @RequestMapping("/updateTempStorey")
    public ModelAndView updateTempStorey(MultipartHttpServletRequest request, HttpServletResponse response, @Valid ChannelStorey channelStorey, BindingResult bindingResult,
            Long tempId) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView(new RedirectView(REDIRECT + tempId + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN)));
        }
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        ModelAndView mav = new ModelAndView();
        try {
            // 待文件上传区
            MultipartFile file = request.getFile("netLogo");
            // 若有数据则上传文件
            if (file.getSize() != 0) {
                channelStorey.setStoreyImg(UploadUtil.uploadFileOne(file, request));
                // 保存图片信息，以供以后选择
                infoImageManagePublic.saveImage(channelStorey.getStoreyImg());
            }
            MultipartFile file2 = request.getFile("netLogo_temp2");
            // 若有数据则上传文件
            if (null != file2 && file2.getSize() != 0) {
                channelStorey.setTemp2(UploadUtil.uploadFileOne(file2, request));
                // 保存图片信息，以供以后选择
                infoImageManagePublic.saveImage(channelStorey.getTemp2());
            }

            channelStorey.setTempId(tempId);
            if (null == loginUserId) {
                loginUserId = 1L;
            }
            channelStorey.setUpdateUserId(loginUserId);
            // 添加楼层设置
            int n = channelStoreyServiceImpl.updateChannelStorey(channelStorey);
            if (n > 0) {
                String customerName = (String) request.getSession().getAttribute(NAME);
                OperaLogUtil.addOperaLog(request, customerName, "修改模板楼层", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
                LOGGER.debug(LOGGERINFO2);
            } else {
                LOGGER.debug(LOGGERINFO3);
            }
        } catch (Exception e) {
            LOGGER.error(LOGGERINFO4, e);
        }
        mav.setView(new RedirectView(REDIRECT + tempId + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN)));
        // 返回楼层设置列表
        return mav;
    }

    /**
     * 删除模板楼层
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/deleteTempStorey")
    public void deleteTempStorey(HttpServletRequest request, HttpServletResponse response) {
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        if (null == loginUserId) {
            loginUserId = 1L;
        }
        String[] storeyIds = request.getParameterValues("storeyIds[]");
        for (int i = 0; i < storeyIds.length; i++) {
            channelStoreyServiceImpl.deleteChannelStorey(Long.valueOf(storeyIds[i]), loginUserId);
        }
        String customerName = (String) request.getSession().getAttribute(NAME);
        OperaLogUtil.addOperaLog(request, customerName, LOGGERINFO5, request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
    }

    /**
     * 删除模板楼层（新）
     *
     * @param request
     * @param response
     */
    @RequestMapping("/deletetempstoreyajax")
    @ResponseBody
    public void deleteTempStoreyAjax(HttpServletRequest request, HttpServletResponse response) {
        // 获取登录名称
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        // 获取需要删除的id
        String[] storeyIds = request.getParameterValues("storeyId");
        // 删除模板楼层
        for (int i = 0; i < storeyIds.length; i++) {
            // 进行删除
            channelStoreyServiceImpl.deleteChannelStorey(Long.valueOf(storeyIds[i]), loginUserId);
        }
        // 获取用户名
        String customerName = (String) request.getSession().getAttribute(NAME);
        // 记录日志
        OperaLogUtil.addOperaLog(request, customerName, LOGGERINFO5, request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
    }

    /**
     * 调用存储过程删除模板楼层
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/deleteTempStoreyPro")
    public void deleteTempStoreyPro(HttpServletRequest request, HttpServletResponse response) {
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        if (null == loginUserId) {
            loginUserId = 1L;
        }
        String[] storeyIds = request.getParameterValues("storeyIds[]");
        for (int i = 0; i < storeyIds.length; i++) {
            channelStoreyServiceImpl.deleteByPrimaryKeyCallPro(Long.valueOf(storeyIds[i]));
        }
        String customerName = (String) request.getSession().getAttribute(NAME);
        OperaLogUtil.addOperaLog(request, customerName, LOGGERINFO5, request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
    }

    public TempService getTempService() {
        return tempService;
    }

    @Resource(name = "TempService")
    public void setTempService(TempService tempService) {
        this.tempService = tempService;
    }

    public GoodsCateService getGoodsCateService() {
        return goodsCateService;
    }

    @Resource(name = "ChannelGoodsCateService")
    public void setGoodsCateService(GoodsCateService goodsCateService) {
        this.goodsCateService = goodsCateService;
    }

    public ChannelStoreyServiceImpl getChannelStoreyServiceImpl() {
        return channelStoreyServiceImpl;
    }

    @Resource(name = "ChannelStoreyService")
    public void setChannelStoreyServiceImpl(ChannelStoreyServiceImpl channelStoreyServiceImpl) {
        this.channelStoreyServiceImpl = channelStoreyServiceImpl;
    }

}
