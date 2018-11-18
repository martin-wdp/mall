/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 * 控制器-用户反馈邮箱设置
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年6月6日下午6:19:52
 */
@Controller
public class FeedBackEmailController {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(FeedBackEmailController.class);

    private static final String FEEDBACKEMAIL = "FEEDBACKEMAIL";

    /**
     * 显示用户反馈邮箱设置
     * 
     * @return
     */
    @RequestMapping("/showFeedBackEmail")
    public ModelAndView showFeedBackEmail() {
        Properties properties = new Properties();
        InputStream inputStream = null;
        try {
            String classPath = new File(FeedBackEmailController.class.getResource("/").getFile()).getCanonicalPath();
            String configFilePath = classPath + "/com/ningpai/web/config/feedbackEmail.properties";
            inputStream = new FileInputStream(configFilePath);
            properties.load(inputStream);
        } catch (IOException e) {
            LOGGER.error("",e);
            LOGGER.info(e.getLocalizedMessage());
        } finally {
            // 关闭流
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    LOGGER.error("",e);
                    LOGGER.info(e.getLocalizedMessage());
                }
            }
        }
        return new ModelAndView("jsp/core/manager/feedbackemail", "feedbackemail", properties.getProperty(FEEDBACKEMAIL));
    }

    /**
     * 修改用户反馈邮箱设置
     * 
     * @param feedbackemail
     *            用户反馈邮箱设置地址
     * @return
     */
    @RequestMapping("/updateFeedBackEmail")
    public ModelAndView updateFeedBackEmail(String feedbackemail) {
        Properties properties = new Properties();
        OutputStream outputStream = null;
        try {
            String classPath = new File(FeedBackEmailController.class.getResource("/").getFile()).getCanonicalPath();
            String configFilePath = classPath + "/com/ningpai/web/config/feedbackEmail.properties";
            outputStream = new FileOutputStream(configFilePath);
            properties.setProperty(FEEDBACKEMAIL, feedbackemail);
            properties.store(outputStream, "author: 61321001@163.com");
        } catch (IOException e) {
            LOGGER.error("",e);
            LOGGER.info(e.getLocalizedMessage());
        } finally {
            // 关闭流
            if (null != outputStream) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    LOGGER.error("",e);
                    LOGGER.info(e.getLocalizedMessage());
                }
            }
        }
        return new ModelAndView(new RedirectView("showFeedBackEmail.htm"));
    }

    /**
     * 获取用户反馈邮箱地址
     * 
     * @return
     */
    public static String getFeedBackEmailPath() {
        Properties properties = new Properties();
        InputStream inputStream = null;
        try {
            inputStream = FeedBackEmailController.class.getClassLoader().getResourceAsStream("com/ningpai/web/config/feedbackEmail.properties");
            properties.load(inputStream);
        } catch (IOException e) {
            LOGGER.error("",e);
            LOGGER.info(e.getLocalizedMessage());
        } finally {
            // 关闭流
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    LOGGER.error("",e);
                    LOGGER.info(e.getLocalizedMessage());
                }
            }
        }
        return properties.getProperty(FEEDBACKEMAIL);
    }

}
