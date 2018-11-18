/*
 * Copyright 2010-2013 NINGPAI, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.common.util;

import java.security.InvalidParameterException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ningpai.util.MyLogger;

/**
 * 时间工具类
 *
 * @author NINGPAI-ZHOUY
 * @version 1.0
 * @since 2013年12月21日下午4:33:32
 */
public class DateUtil {

    /**
     * 记录日志对象
     */
    private static final MyLogger LOGGER = new MyLogger(DateUtil.class);

    private static final String DATE = "yyyy-MM-dd";
    private static final String LOGGERINFO1 = "时间不能为空!";

    /**
     * 每秒的毫秒数
     */
    public static final long MILLIS_PER_SECOND = 1000;
    /**
     * 每分钟的毫秒数
     */
    public static final long MILLIS_PER_MINUTE = 60 * MILLIS_PER_SECOND;
    /**
     * 每小时的毫秒数
     */
    public static final long MILLIS_PER_HOUR = 60 * MILLIS_PER_MINUTE;
    /**
     * 每天的毫秒数
     */
    public static final long MILLIS_PER_DAY = 24 * MILLIS_PER_HOUR;

    /**
     * 放置一批常用时间格式SimpleDateFormat
     */
    public static final Map<String, SimpleDateFormat> dateFormatMap;

    static {
        dateFormatMap = new HashMap<String, SimpleDateFormat>();
        dateFormatMap.put(DATE, new SimpleDateFormat(DATE));
        dateFormatMap.put("yyyy-MM-dd hh:mm:ss", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"));
        dateFormatMap.put("yyyy/MM/dd", new SimpleDateFormat("yyyy/MM/dd"));
        dateFormatMap.put("yyyy/MM/dd hh:mm:ss", new SimpleDateFormat("yyyy/MM/dd hh:mm:ss"));
        dateFormatMap.put("yyyyMMdd", new SimpleDateFormat("yyyyMMdd"));
        dateFormatMap.put("yyyyMMddhhmmss", new SimpleDateFormat("yyyyMMddhhmmss"));
    }

    // 私有构造
    private DateUtil() {
    }

    /**
     * 根据年月日构建日期对象。注意月份是从1开始计数的，即month为1代表1月份。
     *
     * @param year
     *            年
     * @param month
     *            月
     * @param day
     *            日
     * @return 根据年月日构建日期对象
     */
    public static Date buildDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day, 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 判断两个日期是否同一天（忽略时间部分）
     *
     * @param date1
     *            时间值1
     * @param date2
     *            时间值2
     * @return 同一天返回true否则返回false
     */
    public static boolean isSameDay(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            throw new IllegalArgumentException("日期不能为空!");
        }
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        return isSameDay(cal1, cal2);
    }

    /**
     * 判断两个日历是否是同一天
     *
     * @param cal1
     *            日历值1
     * @param cal2
     *            日历值2
     * @return 同一天返回true否则返回false
     */
    public static boolean isSameDay(Calendar cal1, Calendar cal2) {
        if (cal1 == null || cal2 == null) {
            throw new IllegalArgumentException(LOGGERINFO1);
        }
        return cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) && cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
                && cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
    }

    /**
     * 计算开始日期和结束日期之间包含的天数
     *
     * @param start
     *            开始日期
     * @param end
     *            结束日期
     * @return 开始日期和结束日期之间包含的天数
     */
    public static int getStartToEndDayInterval(Date start, Date end) {
        // 时间非空判断
        if (start == null || end == null) {
            throw new InvalidParameterException("开始日期和结束日期不能为空!");
        }
        // 时间大小判断
        if (start.after(end)) {
            throw new InvalidParameterException("结束日期早于开始日期!");
        }
        // 计算开始日期和结束日期之间包含的天数
        return (int) ((end.getTime() - start.getTime()) / MILLIS_PER_DAY);
    }

    /**
     * 将时间转换成固定格式的时间字符串
     *
     * @param date
     *            待转换的时间值
     * @param dateFormatStr
     *            时间格式(可为空)
     * @return 固定格式的时间字符串
     */
    public static String dateToString(Date date, String dateFormatStr) {
        // 时间非空判断
        if (date == null) {
            throw new InvalidParameterException(LOGGERINFO1);
        }
        SimpleDateFormat dateFormat = null;
        // 获取时间格式对应的转换器
        if (dateFormatStr == null || "".equals(dateFormatStr)) {
            dateFormat = dateFormatMap.get(DATE);
        } else {
            dateFormat = dateFormatMap.get(dateFormatStr);
            // 当前时间格式内存中没有存在的时候实例化并存储起来为下次使用服务
            if (dateFormat == null) {
                dateFormat = new SimpleDateFormat(dateFormatStr);
                dateFormatMap.put(dateFormatStr, dateFormat);
            }
        }
        return dateFormat.format(date);
    }

    /**
     * 将时间格式的字符串转换成{@link java.util.Date}类型
     *
     * @param date
     *            字符串时间
     * @param dateFormatStr
     *            时间格式
     * @return 格式化之后的时间
     */
    public static Date stringToDate(String date, String dateFormatStr) {
        // 时间非空判断
        if (date == null || "".equals(date)) {
            throw new InvalidParameterException(LOGGERINFO1);
        }
        SimpleDateFormat dateFormat = null;
        // 获取时间格式对应的转换器
        if (dateFormatStr == null || "".equals(dateFormatStr)) {
            dateFormat = dateFormatMap.get(DATE);
        } else {
            dateFormat = dateFormatMap.get(dateFormatStr);
            // 当当前时间格式内存中没有存在的时候实例化并存储起来为下次使用服务
            if (dateFormat == null) {
                dateFormat = new SimpleDateFormat(dateFormatStr);
                dateFormatMap.put(dateFormatStr, dateFormat);
            }
        }
        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            return null;
        }

    }

    /**
     * 开始日期和结束日期之间的所有日期
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static List<String> getAllDateBetween2Date(String startTime, String endTime) {
        List<String> dateList = new ArrayList<String>();
        try {
            Calendar startCalendar = Calendar.getInstance();
            Calendar endCalendar = Calendar.getInstance();
            SimpleDateFormat df = new SimpleDateFormat(DATE);
            Date startDate = df.parse(startTime);
            startCalendar.setTime(startDate);
            Date endDate = df.parse(endTime);
            endCalendar.setTime(endDate);
            dateList.add(startTime);
            while (true) {
                startCalendar.add(Calendar.DAY_OF_MONTH, 1);
                if (startCalendar.getTimeInMillis() < endCalendar.getTimeInMillis()) {
                    dateList.add(df.format(startCalendar.getTime()));
                    // System.out.println(df.format(startCalendar.getTime()));
                } else {
                    break;
                }
            }
            dateList.add(endTime);
        } catch (ParseException e) {
            LOGGER.error("日期处理失败:" + e.getLocalizedMessage());
        }
        return dateList;
    }

}
