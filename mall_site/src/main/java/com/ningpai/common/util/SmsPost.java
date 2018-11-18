/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.common.util;

import com.ningpai.common.bean.Sms;
import com.ningpai.system.service.BasicSetService;
import com.ningpai.util.MSMSendUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * 短信验证码辅助类
 *
 * @author NINGPAI-zhangqiang
 * @version 0.0.1
 * @since 2014年6月12日 下午3:44:51
 */
@Service
public class SmsPost {

    /**
     * 站点信息业务接口
     */
    private static BasicSetService basicSetService;

    /**
     * 私有构造函数
     */
    private SmsPost() {

    }

    /**
     * 短信发送
     *
     * @param sms 接口帮助类
     * @return
     * @throws IOException
     */
    public static boolean sendPost(Sms sms) throws IOException {
        sms.setMsgContext("您本次的验证码是：" + sms.getMsgContext() + "，打死也不要告诉别人【千品猫】");
        return MSMSendUtil.sendMsm("", sms.getLoginName(), sms.getPassword(), new String[]{sms.getSendSim()}, sms.getMsgContext(), sms.getSmsKind(), sms.getHttpUrl());
    }

    /**
     * 短信发送
     *
     * @param sdem      接口帮助类
     * @param funcType 1注册  2修改
     * @return
     * @throws IOException
     */
    public static boolean sendPost(Sms sdem, String funcType) throws IOException {
        String msgContent = "";//"您本次的验证码是："+sms.getMsgContext()+"，打死也不要告诉别人"

        if ("1".equals(funcType)) {//注册
            msgContent = "您正在使用手机进行注册，验证码为：" + sdem.getMsgContext() + "。如需帮助，请您致电400-087-6599 联系客服";
        } else if ("2".equals(funcType)) {//修改
            msgContent = "您正在使用手机进行修改密码，验证码为：" + sdem.getMsgContext() + "。如需帮助，请您致电400-087-6599 联系客服";
        } else {
            msgContent = "您本次的验证码是：" + sdem.getMsgContext() + "。如需帮助，请您致电400-087-6599 联系客服";
        }
        sdem.setMsgContext(msgContent);
        return MSMSendUtil.sendMsm("", sdem.getLoginName(), sdem.getPassword(), new String[]{sdem.getSendSim()}, sdem.getMsgContext(), sdem.getSmsKind(), sdem.getHttpUrl());
    }


    /**
     * 获取站点信息业务接口
     *
     * @return
     */
    public BasicSetService getBasicSetService() {
        return basicSetService;
    }

    /**
     * 设置站点信息业务接口
     *
     * @param basicSetService
     */
    @Resource(name = "basicSetService")
    public void setBasicSetService(BasicSetService basicSetService) {
        SmsPost.basicSetService = basicSetService;
    }

}
