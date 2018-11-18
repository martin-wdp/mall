package com.ningpai.report.util;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.ningpai.util.MyLogger;

/**
 * 时间工具类
 *
 * @author NP-Heh
 * @date 2014年12月4日
 */
public class DateUtil implements Serializable {

    /**
     * 记录日志对象
     */
    private static final MyLogger LOGGER = new MyLogger(DateUtil.class);

    private static final String LOGGERINFO1 = "日期处理失败:";

    public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    private static final long MILLISECONDS_PER_DAY = 24 * 60 * 60 * 1000L;

    /**
     * 构造方法
     */
    private DateUtil() {
    }

    /**
     * 在原来日期加一天
     *
     * @param strDate
     *            原来日期 yyyy-MM-dd
     * @return 加一天后的日期字符串 yyyy-MM-dd
     */
    public static String addOneDay(String strDate) {
        Long mills;
        String date = null;
        try {

            synchronized (sdf) {
                mills = sdf.parse(strDate).getTime();

                date = sdf.format(new Date(mills + 24 * 60 * 60 * 1000));
            }
        } catch (ParseException e) {
            LOGGER.error(LOGGERINFO1, e);
        }

        return date;
    }

    /**
     * 在原来的日期上减一天
     *
     * @param strDate
     *            原来的日期 yyyy-MM-dd
     * @return 处理后的日期 yyyy-MM-dd
     */
    public static String subOneDay(String strDate) {
        Long mills;
        String date = null;
        try {
            synchronized (sdf) {
                mills = sdf.parse(strDate).getTime();
                date = sdf.format(new Date(mills - 24 * 60 * 60 * 1000));
            }
        } catch (ParseException e) {
            LOGGER.error(LOGGERINFO1, e);
        }
        return date;
    }

    /**
     * 查询两个日期之间的天数en
     *
     * @param startDate
     *            开始日期 yyyy-MM-dd
     * @param endDate
     *            结束日期 yyyy-MM-dd
     * @return 天数
     */
    public static int getDays(String startDate, String endDate) {
        try {
            synchronized (sdf) {
                Long days = (sdf.parse(addOneDay(endDate)).getTime() - sdf.parse(startDate).getTime()) / (24 * 60 * 60 * 1000);
                return days.intValue();
            }
        } catch (ParseException e) {
            LOGGER.error(LOGGERINFO1 + e.getLocalizedMessage());
            return 0;
        }
    }

    /**
     * 一个日期加days天
     *
     * @param startDate
     *            原来的日期
     * @param days
     *            天数
     * @return 处理后的日期yyyy-MM-dd
     */
    public static String addSomeDays(String startDate, int days) {
        try {

            synchronized (sdf) {

                return sdf.format(new Date(sdf.parse(startDate).getTime() + days * MILLISECONDS_PER_DAY));
            }
        } catch (ParseException e) {
            LOGGER.error(LOGGERINFO1 + e.getLocalizedMessage());
            return null;
        }
    }

    /**
     * 获取当前日期是当月中的第几天
     *
     * @param date
     *            时间
     *
     * @return 返回
     */
    public static int getDayOfMonth(String date) {
        return Integer.parseInt(date.split("-")[2]);
    }

    /**
     * 获取该日期所在月份的天数
     *
     * @param date
     *            给定日期 yyyy-MM-dd
     * @return 所在月份的天数
     */
    // public static int getDayOfLastMonth(String date) {
    // try {
    // Calendar calendar = Calendar.getInstance();
    // calendar.setTime(sdf.parse(date));
    // calendar.add(Calendar.MONTH, -1);
    //
    // synchronized (sdf) {
    // return Integer.parseInt(sdf.format(calendar.getTime()).split("-")[2]);
    // }
    // } catch (ParseException e) {
    // LOGGER.error(LOGGERINFO1 + e.getLocalizedMessage());
    // return 0;
    // }
    // }

    /**
     * 获取该日期所在月份的最后一天的日期 yyyy-MM-dd
     *
     * @param date
     *            指定日期
     * @return 最后一天的日期
     */
    public static String getDateOfLastMonth(String date) {
        try {

            synchronized (sdf) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(sdf.parse(date));
                calendar.add(Calendar.MONTH, -1);
                return sdf.format(calendar.getTime());
            }
        } catch (ParseException e) {
            LOGGER.error(LOGGERINFO1 + e.getLocalizedMessage());
            return null;
        }
    }

    /**
     * 获取前N个月的当天
     *
     * @param date
     *            时间
     * @param n
     *            月数
     * @return String
     */
    public static String getDateOfLastNMonth(String date, int n) {
        String dateNew = date;
        int nNew = n;
        while (n > 0) {
            dateNew = getDateOfLastMonth(dateNew);
            nNew--;
        }
        if (nNew == 0) {
            Calendar ca = Calendar.getInstance();
            ca.setTime(stringToDate(dateNew));
            int lastDay = ca.getActualMaximum(Calendar.DAY_OF_MONTH);
            Date lastDate = ca.getTime();
            lastDate.setDate(lastDay);
            dateNew = dateToString(lastDate);
        }
        return dateNew;
    }

    /**
     * 根据当前日期和结算日期，计算上一个结算日期
     *
     * @param currDate
     *            当前日期
     * @param countDates
     *            结算周期数组（每个月哪些天是结算日期）
     * @return 上一个结算日期 yyyy-MM-dd
     */
    public static String getLastCountDate(String currDate, String[] countDates) {
        String dateOfLastMonth = getDateOfLastMonth(currDate);
        int dayOfLastMonth = getDayOfMonth(currDate);
        String result = null;
        if (countDates.length == 1) {
            result = dateOfLastMonth;
        } else {
            if (countDates[0].equals(dayOfLastMonth + "")) {
                result = dateOfLastMonth.substring(0, dateOfLastMonth.lastIndexOf("-")) + "-" + countDates[countDates.length - 1];
            } else {
                for (int i = 0; i < countDates.length; i++) {
                    if (countDates[i].equals(dayOfLastMonth + "")) {
                        result = currDate.substring(0, currDate.lastIndexOf("-")) + "-" + countDates[i - 1];
                    }
                }
            }
        }

        return result;
    }

    /**
     * String格式的日期转换成Date
     *
     * @param strDate
     *            当前日期
     * @return Date类型日期
     */
    public static Date stringToDate(String strDate) {
        try {

            synchronized (sdf) {
                return sdf.parse(strDate);
            }
        } catch (ParseException e) {
            LOGGER.error(LOGGERINFO1 + e.getLocalizedMessage());
            return null;
        }
    }

    /*
     * public static void main(String[] args) { //
     * System.out.println(getDays("2014-10-29", "2014-11-01")); //
     * System.out.println(DateUtil.addSomeDays("2014-10-21", 11)); //
     * System.out.println(getDayOfMonth("2014-10-04")); String[] str = new
     * String[] { "5", "15", "21", "27" };
     * System.out.println(getLastCountDate("2014-10-5", str)); }
     */

    /**
     * Date类型日期
     * 
     * @param date
     *            Date类型日期
     * @return String类型日期yyyy-MM-dd
     */
    public static String dateToString(Date date) {
        synchronized (sdf) {
            return sdf.format(date);
        }
    }
}
