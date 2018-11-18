/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.third.templet.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.channel.bean.ChannelStorey;
import com.ningpai.channel.service.impl.ChannelStoreyServiceImpl;
import com.ningpai.publicpackage.InfoImageManagePublic;
import com.ningpai.temp.bean.SysTemp;
import com.ningpai.temp.service.ThirdTempService;
import com.ningpai.third.goods.bean.ThirdCate;
import com.ningpai.third.goods.service.ThirdCateService;
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
public class ThirdTempStoreyController {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(ThirdTempStoreyController.class);

    private static final String LOGINUSERID = "loginUserId";

    private static final String SHOW_VIEW = "showThirdTempStorey.htm?tempId=";

    private static final String LIST_VIEW = "queryThirdTempStoreyByPageBean.htm?tempId=";

    private static final String THIRDID = "thirdId";

    /** 模板业务接口 */
    @Resource(name = "ThirdTempService")
    private ThirdTempService tempService;

    /** 频道楼层业务接口 */
    @Resource(name = "ChannelStoreyService")
    private ChannelStoreyServiceImpl channelStoreyServiceImpl;

    /** 商品分类业务接口 */
    @Resource(name = "ThirdCateService")
    private ThirdCateService goodsCateService;

    /** 图片管理业务接口 */
    @Resource(name = "InfoImageManagePublic")
    private InfoImageManagePublic infoImageManagePublic;

    /**
     * 分页查看模板楼层设置
     * 
     * @param pb
     * @param tempId
     * @return
     */
    @RequestMapping("/queryThirdTempStoreyByPageBean")
    public ModelAndView queryThirdTempStoreyByPageBean(HttpServletRequest request, PageBean pb, Long tempId) {
        SysTemp temp = this.tempService.getSystempById(tempId);
        // 获取第三方商家ID
        String thirdId = ((Long) request.getSession().getAttribute(THIRDID)).toString();
        /* 设置查询参数，获取查询后的pb */
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("goodsCates", this.goodsCateService.getThirdCateByParentId(0L, Long.parseLong(thirdId)));
        map.put("pb", channelStoreyServiceImpl.selectchannelStoreyByParam(pb, null, tempId, thirdId));
        map.put("temp", temp);
        map.put(THIRDID, thirdId);
        return new ModelAndView("temp/temp_storey_list", "map", map);
    }

    /**
     * 查看模板楼层
     * 
     * @param storeyId
     * @param tempId
     * @return
     */
    @RequestMapping("/showThirdTempStorey")
    public ModelAndView showThirdTempStorey(HttpServletRequest request, Long storeyId, Long tempId) {
        Map<String, Object> map = new HashMap<String, Object>();
        SysTemp temp = this.tempService.getSystempById(tempId);
        map.put("temp", temp);
        // 获取第三方商家ID
        Long thirdId = (Long) request.getSession().getAttribute(THIRDID);
        List<ThirdCate> goodsCates = this.goodsCateService.getThirdCateByParentId(0L, thirdId);
        map.put("goodsCates", goodsCates);
        map.put(THIRDID, thirdId);
        if (null != storeyId) {
            ChannelStorey channelStorey = this.channelStoreyServiceImpl.getChannelStoreyById(storeyId);
            map.put("channelStorey", channelStorey);
        }
        return new ModelAndView("temp/show_temp_storey", "map", map);
    }

    /**
     * 根据楼层id查询楼层信息
     * 
     * @param storyId
     *            楼层id
     * @return 楼层信息
     */
    @RequestMapping("/getChannelStoreyById")
    @ResponseBody
    public ChannelStorey getChannelStoreyById(Long storyId) {
        return channelStoreyServiceImpl.getChannelStoreyById(storyId);
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
    @RequestMapping("/createThirdTempStorey")
    public ModelAndView createThirdTempStorey(MultipartHttpServletRequest request, HttpServletResponse response, ChannelStorey channelStorey, Long tempId) {
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
            channelStorey.setTempId(tempId);
            if (null == loginUserId) {
                loginUserId = 1L;
            }
            channelStorey.setCreateUserId(loginUserId);
            // 添加楼层设置
            int n = channelStoreyServiceImpl.saveChannelStorey(channelStorey);
            if (n > 0) {
                LOGGER.debug("保存楼层设置成功！");
                mav.setView(new RedirectView(LIST_VIEW + tempId));
            } else {
                LOGGER.debug("保存楼层设置失败！");
                mav.setView(new RedirectView(SHOW_VIEW + tempId));
            }
        } catch (Exception e) {
            LOGGER.error("保存楼层设置对象异常！", e);
            mav.setView(new RedirectView(SHOW_VIEW + tempId));
        }
        // 返回楼层设置列表
        return mav;
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
    @RequestMapping("/updateThirdTempStorey")
    public ModelAndView updateThirdTempStorey(MultipartHttpServletRequest request, HttpServletResponse response, ChannelStorey channelStorey, Long tempId) {
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

            channelStorey.setTempId(tempId);
            if (null == loginUserId) {
                loginUserId = 1L;
            }
            channelStorey.setUpdateUserId(loginUserId);
            // 添加楼层设置
            int n = channelStoreyServiceImpl.updateChannelStorey(channelStorey);
            if (n > 0) {
                LOGGER.debug("保存楼层设置成功！");
                mav.setView(new RedirectView(LIST_VIEW + tempId));
            } else {
                LOGGER.debug("保存楼层设置失败！");
                mav.setView(new RedirectView(LIST_VIEW + tempId));
            }
        } catch (Exception e) {
            LOGGER.error("保存楼层设置对象异常！", e);
            mav.setView(new RedirectView(LIST_VIEW + tempId));
        }
        // 返回楼层设置列表
        return mav;
    }

    /**
     * 删除模板楼层
     * 
     * @param request
     * @param channelStoreyId
     */
    @RequestMapping("/deleteThirdTempStorey")
    public ModelAndView deleteThirdTempStorey(HttpServletRequest request, Long[] channelStoreyId, Long tempId) {
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        if (null == loginUserId) {
            loginUserId = 1L;
        }
        for (int i = 0; i < channelStoreyId.length; i++) {
            channelStoreyServiceImpl.deleteChannelStorey(channelStoreyId[i], loginUserId);
        }
        return new ModelAndView(new RedirectView(LIST_VIEW + tempId));
    }

}
