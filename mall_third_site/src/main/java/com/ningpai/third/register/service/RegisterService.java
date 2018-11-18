package com.ningpai.third.register.service;


import javax.servlet.http.HttpServletRequest;

/**
 * 商家注册 获取手机验证码
 * Created by zhanghailong on 2015/5/7.
 */
public interface RegisterService {

    /**
     * 发送手机验证码
     *
     * @param request
     * @param moblie
     *            目标手机
     * @return 1 失败 0网络连接超时 -1没过90秒
     */
    int sendPost(HttpServletRequest request, String moblie);

    /**
     * 新发送手机验证码
     * @param request
     * @param mobile
     * @return
     */
    int newsendPost(HttpServletRequest request,String mobile);

}
