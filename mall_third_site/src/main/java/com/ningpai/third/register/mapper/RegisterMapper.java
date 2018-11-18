package com.ningpai.third.register.mapper;


import com.ningpai.third.register.bean.Sms;

/**
 * 商家注册 获取手机验证码
 * Created by zhanghailong on 2015/5/7.
 */
public interface RegisterMapper {
    /**
     * 查询短信信息
     *
     * @return BasicEmailServer
     */
    Sms selectSms();
}
