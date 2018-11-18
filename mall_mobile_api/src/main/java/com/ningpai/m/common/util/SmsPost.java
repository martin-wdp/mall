/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.m.common.util;

import com.ningpai.m.common.bean.Sms;
import com.ningpai.system.service.BasicSetService;
import com.ningpai.util.MSMSendUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * 短信验证码辅助类
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年6月12日 下午3:44:51
 * @version 0.0.1
 */
@Service
public class SmsPost {

    /** 站点信息业务接口 */
    private static BasicSetService basicSetService;

    /**
     * 无参构造
     * */
    private SmsPost() {

    }

    /**
     * 短信发送
     * 
     * @param sms
     *            接口帮助类
     * @return boolean
     * @throws IOException
     */
    public static boolean sendPost(Sms sms) throws IOException {
        // 调用短信接口
        sms.setMsgContext("您本次的验证码是："+sms.getMsgContext()+"，打死也不要告诉别人");
        return MSMSendUtil.sendMsm("", sms.getLoginName(), sms.getPassword(), new String[]{sms.getSendSim()}, sms.getMsgContext(),sms.getSmsKind(),sms.getHttpUrl());
    }

    /**
     * 获取
     * */
    public BasicSetService getBasicSetService() {
        return basicSetService;
    }
    /**
     * SPrin注入
     * */
    @Resource(name = "basicSetService")
    public void setBasicSetService(BasicSetService basicSetService) {
        SmsPost.basicSetService = basicSetService;
    }

}
