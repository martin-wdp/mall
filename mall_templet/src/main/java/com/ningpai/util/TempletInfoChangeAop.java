/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Date;

import javax.annotation.Resource;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.ningpai.temp.bean.TempToken;
import com.ningpai.temp.service.TempTokenService;

/**
 * 模板内容变更Aop
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年8月4日下午5:59:46
 */
@Component
@Aspect
public class TempletInfoChangeAop {

    @Resource(name = "TempTokenService")
    private TempTokenService tokenService;

    /** 记录日志对象 */

    private static final MyLogger LOGGER = new MyLogger(TempletInfoChangeAop.class);

    /** 定义切入点 */
    @Pointcut("execution(* com.ningpai.temp.controller.*.create*(..)) || " + "execution(* com.ningpai.temp.controller.*.update*(..)) || "
            + "execution(* com.ningpai.temp.controller.*.delete*(..)) || " + "execution(* com.ningpai.channel.controller.*.create*(..)) || "
            + "execution(* com.ningpai.channel.controller.*.update*(..)) || " + "execution(* com.ningpai.channel.controller.*.delete*(..))")
    private void anyMethod() {
    }

    /**
     * 模板内容变更Aop
     */
    @AfterReturning("anyMethod()")
    public void doAfter() {
        String tokenValue;
        // 生成tokin
        MessageDigest md;
        try {
            // 生成一个MD5加密计算摘要
            md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(new Date().toString().getBytes());
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            tokenValue = new BigInteger(1, md.digest()).toString(16);
            LOGGER.debug("==========token码为" + tokenValue);
            TempToken token = tokenService.selectTokenByType("1");
            if (null != token) {
                token.setToken(tokenValue);
                tokenService.updateToken(token);
            } else {
                token = new TempToken();
                token.setTemp1("1");
                token.setToken(tokenValue);
                tokenService.createToken(token);
            }
        } catch (Exception e) {
            LOGGER.error("生成token码错误：=>" + e.getLocalizedMessage());
        }
    }

}
