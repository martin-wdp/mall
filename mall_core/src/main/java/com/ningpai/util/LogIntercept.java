/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.util;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author NINGPAI-WangHaiYang
 * @since 2014年6月21日下午4:33:34
 */
@Aspect
public class LogIntercept {
    /** 记录日志 */
    private static final Logger LOGGER = Logger.getLogger(LogIntercept.class);

    /** 定义切点 */
    @Pointcut("execution(* com.ningpai.information.controller.InfoUserDefinedController.*(..))")
    public void recordLog() {
    }

    /**
     * 定义前置运行方法
     */
    @Before("recordLog()")
    public void before() {
        LOGGER.debug("已经记录下操作日志@Before 方法执行前");

    }

    /**
     * 定义后置运行方法
     */
    @After("recordLog()")
    public void after() {

        LOGGER.debug("已经记录下操作日志@After 方法执行后");

    }

    /**
     * 定义双向运行方法
     * @param pjp 运行点
     * @throws Throwable 运行期抛出的异常
     */
    @Around("recordLog()")
    public void around(ProceedingJoinPoint pjp) throws Throwable {

        LOGGER.debug("已经记录下操作日志@Around 方法执行前");

        pjp.proceed();

        LOGGER.debug("已经记录下操作日志@Around 方法执行后");

    }

}
