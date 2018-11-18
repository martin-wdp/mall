/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.smscommon.util;

import com.ningpai.smscommon.bean.Sms;
import com.ningpai.util.MSMSendUtil;

import java.io.IOException;

/**
 * 短信验证码辅助类
 * 
 * @author NINGPAI-LIH
 * @since 2014年11月18日11:05:59
 * @version 0.0.1
 */
public final class SmsPostContext {

    private SmsPostContext() {

    }

    /**
     * 短信发送
     * 
     * @param sms
     *            接口帮助类
     * @return
     * @throws IOException
     */
    public static boolean sendPost(Sms sms) throws IOException {
        sms.setMsgContext("您本次的验证码是："+sms.getMsgContext()+"，打死也不要告诉别人");
        return MSMSendUtil.sendMsm("",sms.getLoginName(),sms.getPassword(),new String[]{sms.getSendSim()},sms.getMsgContext(),sms.getSmsKind(),sms.getHttpUrl());
    }
}
