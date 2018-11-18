/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.weixin.controller;

import com.ningpai.logger.bean.OperationLog;
import com.ningpai.logger.service.OperaLogService;
import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.other.util.CustomerConstantStr;
import com.ningpai.util.MyLogger;
import com.ningpai.util.UtilDate;
import com.ningpai.weixin.service.WXActivityService;
import com.ningpai.weixin.util.WXSendActivityNotice;
import com.upyun.OSinfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * 用于微信端,活动按钮点击获取用户openid
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年9月3日 下午2:10:08
 * @version 0.0.01
 */
@Controller
public class WXActivityController {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(WXActivityController.class);

    private static final String IMGF = "img_f";

    // 属性注入
    private OperaLogService operaLogService;

    private WXActivityService wxActivityService;

    /***
     * 根据之日id获取日志操作内容
     * 
     * @param opId
     *            :日志ID
     * @return
     * @throws Exception
     */
    @RequestMapping("/selectLogById")
    @ResponseBody
    public OperationLog selectLogById(Long opId) throws Exception {
        // 获取单个操作日志对象
        OperationLog operationLog = operaLogService.selectLogById(opId);
        // 非空验证 操作日志关键字
        if (null != operationLog.getOpCode()) {
            // 日志记录
            LOGGER.info("获取操作关键字为：【" + operationLog.getOpCode() + "】的操作日志内容！");
        }

        return operationLog;
    }

    /**
     * 获取群发列表
     * 
     * @return
     */
    @RequestMapping("/initwxgroup")
    public ModelAndView findWXGroup() {

        return new ModelAndView("jsp/customer/wxgroup").addObject("groups", wxActivityService.selectAllGroup());
    }

    /**
     * 发送微信通知
     * 
     * @param request
     * @param response
     * @param content
     *            内容
     * @param title
     *            标题
     * @return
     * @throws IOException
     */
    @RequestMapping("/sendactivitynotice")
    public ModelAndView sendActivityNotice(MultipartHttpServletRequest request, HttpServletResponse response, String content, String title) throws IOException {
        String path = "";
        int res = 0;
        try {
            // 发送消息
            if (checkExtendsName(request, path)) {
                path = transfile(request);
                res = WXSendActivityNotice.sendWXNotice(request, response, path, content, title);
            } else {
                res = -1001;
            }
        } catch (IOException e) {
            LOGGER.error("",e);
            res = -1001;
            OperaLogUtil.addOperaException((String) request.getSession().getAttribute(CustomerConstantStr.NAME), e, request);
        } finally {
            OperaLogUtil.addOperaLog(request, (String) request.getSession().getAttribute(CustomerConstantStr.NAME), "发送微信通知",
                    (String) request.getSession().getAttribute(CustomerConstantStr.OPERAPATH));
        }
        return new ModelAndView("redirect:/initwxgroup.htm");
    }

    /**
     * 上传图片
     * 
     * @param request
     * @return
     * @throws IllegalStateException
     * @throws IOException
     */
    private String transfile(MultipartHttpServletRequest request) throws IOException {
        String s = OSinfo.getOSname().toString();
        String pa = "";
        if ("Linux".equals(s)) {
            pa = "home";
        } else if ("Windows".equals(s)) {
            pa = "D:";
        }

        MultipartFile mf = request.getFile(IMGF);
        // 根据系统时间生成上传后保存的文件名
        String filePath = pa + "/upload/" + UtilDate.todayFormatString(new Date()) + "/";
        // 根据真实路径创建目录文件
        File picSaveFile = new File(filePath);
        if (!picSaveFile.exists()) {
            try {
                picSaveFile.mkdirs();
            } catch (Exception e) {
                e.getLocalizedMessage();
            }
        }
        String path = filePath + mf.getOriginalFilename();
        File file = new File(path);
        mf.transferTo(file);
        return path;
    }

    /**
     * 检查文件扩展名是否为图片
     * 
     * @param path
     *            上传的文件的文件名
     * @return
     * @throws IOException
     * @throws IllegalStateException
     */
    private boolean checkExtendsName(MultipartHttpServletRequest request, String path) throws IOException {
        if (request.getFile(IMGF) != null) {
            String fileName = request.getFile(IMGF).getOriginalFilename();
            if (fileName.indexOf(".") < 0) {
                return false;
            }
            if (!"jpg".equals(fileName.substring(fileName.lastIndexOf(".") + 1))) {
                return false;
            }
        }
        return true;
    }

    public WXActivityService getWxActivityService() {
        return wxActivityService;
    }

    @Resource(name = "wXActivityServicec")
    public void setWxActivityService(WXActivityService wxActivityService) {
        this.wxActivityService = wxActivityService;
    }

    public OperaLogService getOperaLogService() {
        return operaLogService;
    }

    @Resource(name = "operaLogService")
    public void setOperaLogService(OperaLogService operaLogService) {
        this.operaLogService = operaLogService;
    }

}
