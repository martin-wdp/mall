package com.ningpai.third.register.bean;

import com.ningpai.system.service.BasicSetService;
import com.ningpai.util.MSMSendUtil;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * Created by zhanghailong on 2015/5/7.
 */
public class SmsPost {

    /** 站点信息业务接口 */
    private static BasicSetService basicSetService;

    /**
     * 构造方法
     */
    private SmsPost() {

    }

    /**
     * 短信发送
     *
     * @param sms
     *            接口帮助类
     * @return
     * @throws java.io.IOException
     */
    public static boolean sendPost(Sms sms) throws IOException {
        sms.setMsgContext("您本次的验证码是："+sms.getMsgContext()+"，打死也不要告诉别人");
        return MSMSendUtil.sendMsm("", sms.getLoginName(), sms.getPassword(), new String[]{sms.getSendSim()}, sms.getMsgContext(),sms.getSmsKind(),sms.getHttpUrl());
    }

    /** 站点信息业务接口GET方法 */
    public BasicSetService getBasicSetService() {
        return basicSetService;
    }

    /** 站点信息业务接口GET方法 */
    @Resource(name = "basicSetService")
    public void setBasicSetService(BasicSetService basicSetService) {
        SmsPost.basicSetService = basicSetService;
    }
}
