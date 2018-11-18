/**
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.common.util.tenpay.util;

import com.ningpai.util.MyLogger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * TenpayUtil
 */
public class TenpayUtil {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(TenpayUtil.class);

    /**
     * TenpayUtil
     */
    private TenpayUtil() {
    }

    /**
     * 把对象转换成字符串
     * 
     * @param obj
     * @return String 转换成字符串,若对象为null,则返回空字符串.
     */
    public static String toString(Object obj) {
        if (obj == null) {
            return "";
        }

        return obj.toString();
    }

    /**
     * 把对象转换为int数值.
     * 
     * @param obj
     *            包含数字的对象.
     * @return int 转换后的数值,对不能转换的对象返回0。
     */
    public static int toInt(Object obj) {
        int a = 0;
        try {
            if (obj != null) {
                a = Integer.parseInt(obj.toString());
            }
        } catch (Exception e) {
            a = 0;
            // 日志记录
            LOGGER.info("添加站内信息错误" + e);
        }
        return a;
    }

    /**
     * 获取当前时间 yyyyMMddHHmmss
     * 
     * @return String
     */
    public static String getCurrTime() {
        Date now = new Date();
        SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return outFormat.format(now);
    }

    /**
     * 获取当前日期 yyyyMMdd
     * 
     * @param date
     * @return String
     */
    public static String formatDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        return formatter.format(date);
    }

    /**
     * 取出一个指定长度大小的随机正整数.
     * 
     * @param length
     *            int 设定所取出随机数的长度。length小于11
     * @return int 返回生成的随机数。
     */
    public static int buildRandom(int length) {
        int num = 1;
        double random = Math.random();
        if (random < 0.1) {
            random = random + 0.1;
        }
        for (int i = 0; i < length; i++) {
            num = num * 10;
        }
        return (int) (random * num);
    }

    /**
     * 获取编码字符集
     * 
     * @param request
     * @param response
     * @return String
     */
    public static String getCharacterEncoding(HttpServletRequest request, HttpServletResponse response) {

        if (null == request || null == response) {
            return "gbk";
        }

        String enc = request.getCharacterEncoding();
        if (null == enc || "".equals(enc)) {
            enc = response.getCharacterEncoding();
        }

        if (null == enc || "".equals(enc)) {
            enc = "gbk";
        }

        return enc;
    }

    /**
     * 获取unix时间，从1970-01-01 00:00:00开始的秒数
     * 
     * @param date
     * @return long
     */
    public static long getUnixTime(Date date) {
        if (null == date) {
            return 0;
        }

        return date.getTime() / 1000;
    }

    /**
     * 时间转换成字符串
     * 
     * @param date
     *            时间
     * @param formatType
     *            格式化类型
     * @return String
     */
    public static String date2String(Date date, String formatType) {
        SimpleDateFormat sdf = new SimpleDateFormat(formatType);
        return sdf.format(date);
    }

}
