package com.qpmall.site.register.service;


import com.qpmall.site.register.bean.VerificationInfo;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 注册
 * @author luyong
 * @serialData 2015-09-21
 * Created by ly-qpmall on 2015/9/21.
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
    int sendPost(HttpServletRequest request, String moblie) throws IOException;
   /* *//**
     * 修改验证码
     * @param verificationInfo
     * @return
     *//*
    int updateVerification(VerificationInfo verificationInfo);

    *//**
     * 添加验证码信息
     * @param verificationInfo
     * @return
     *//*
    int insertVerification(VerificationInfo verificationInfo);

    *//**
     * 根据手机号码查询验证码信息
     * @param mobilePhone
     * @return
     *//*
    VerificationInfo selectVerificationByNumber(String mobilePhone);*/


}
