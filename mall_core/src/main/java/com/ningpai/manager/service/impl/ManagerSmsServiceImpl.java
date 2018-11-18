/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.manager.service.impl;

import java.io.IOException;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.ningpai.util.MyLogger;
import org.springframework.stereotype.Service;

import com.ningpai.manager.bean.Manager;
import com.ningpai.manager.mapper.ManagerMapper;
import com.ningpai.manager.service.ManagerSmsService;
import com.ningpai.smscommon.bean.Sms;
import com.ningpai.smscommon.dao.SmsMapper;
import com.ningpai.smscommon.util.SmsPost;

/**
 * 管理员手机验证码Service
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年6月17日 上午10:47:01
 * @version 0.0.1
 */
@Service("managerSmsService")
public class ManagerSmsServiceImpl implements ManagerSmsService {

    private ManagerMapper managerMapper;
    private SmsMapper smsMapper;
    private static final int NINE = 9;
    private static final int ELEVEN = 11;
    private static final int THIRDTEEN = 30;
    private static final int SIXTEEN = 60;
    private static final int THOUSAND = 1000;
    private static final int HTHOUSAND = 100000;

    /**
     * 日志
     * */
    public static final MyLogger LOGGER = new MyLogger(ManagerSmsServiceImpl.class);

    /*
     * 
     * 
     * @see
     * com.ningpai.manager.service.ManagerSmsService#sendPost(javax.servlet.
     * http.HttpServletRequest, java.lang.String)
     */
    @Override
    public int sendPost(HttpServletRequest request, String mobile) {
        // 根据当前管理员Id 查询管理员信息
        Manager manager = managerMapper.selectCaptcha((Long) request.getSession().getAttribute("loginUserId"));
        // 判断管理员aeadtime是否过期
        if (manager.getAeadTime() == null || (System.currentTimeMillis() - manager.getAeadTime().getTime()) > (SIXTEEN * THOUSAND)) {
            // 如果没有获取 发送短信
            Sms sms = smsMapper.selectSms();
            sms.setSendSim(manager.getMobile());
            int num = (int) ((Math.random() * NINE + 1) * HTHOUSAND);
            // 设置短信内容
            sms.setMsgContext(((Integer) num).toString());
            // 设置验证
            manager.setCaptcha(((Integer) num).toString());
            // 设置最新事件
            manager.setAeadTime(new Date());
            try {
                // 发送短信
                if (SmsPost.sendPost(sms)) {
                    // 如果短信发送成功 修改数据库
                    managerMapper.updateSmsCaptcha(manager);
                    return 1;
                }
                return 0;
            } catch (IOException e) {
                LOGGER.error("",e);
                return 0;
            }
        }
        return -1;
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.manager.service.ManagerSmsService#getMCode(javax.servlet.
     * http.HttpServletRequest, java.lang.String)
     */
    @Override
    public int getMCode(HttpServletRequest request, String code) {
        // 根据当前管理员Id 查询管理员信息
        Manager manager = managerMapper.selectCaptcha((Long) request.getSession().getAttribute("loginUserId"));
        // 判断管理员aeadtime是否过期
        if (manager.getAeadTime() != null && (System.currentTimeMillis() - manager.getAeadTime().getTime()) > THIRDTEEN * SIXTEEN * THOUSAND) {
            return -1;
        }
        // 如果时间为null 获取当前时间
        if (manager.getAeadTime() == null) {
            manager.setAeadTime(new Date());
        }
        // 如果输入验证码正确
        if (code.equals(manager.getCaptcha())) {
            // 设置过期时间
            manager.setAeadTime(new Date(manager.getAeadTime().getTime() - (ELEVEN * SIXTEEN * THOUSAND)));
            // 设置验证码
            manager.setCaptcha(null);
            // 修改数据库
            managerMapper.updateSmsCaptcha(manager);
            return 1;
        }
        return 0;
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.manager.service.ManagerSmsService#queryManagerById(java.lang
     * .Long)
     */
    @Override
    public Manager queryManagerById(Long managerId) {
        return managerMapper.selectCaptcha(managerId);
    }

    public ManagerMapper getManagerMapper() {
        return managerMapper;
    }

    @Resource(name = "managerMapper")
    public void setManagerMapper(ManagerMapper managerMapper) {
        this.managerMapper = managerMapper;
    }

    public SmsMapper getSmsMapper() {
        return smsMapper;
    }

    @Resource(name = "smsMapperCore")
    public void setSmsMapper(SmsMapper smsMapper) {
        this.smsMapper = smsMapper;
    }
}
