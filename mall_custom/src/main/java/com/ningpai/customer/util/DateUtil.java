package com.ningpai.customer.util;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by NINGPAI-zhangqiang on 2015-6-17.
 */
public class DateUtil {

    public DateUtil() {
    }

    /**
     * 得到一天的最大时间
     * 
     * @param date
     * @return
     */
    public static Date getLastTime(Date date) {
        if (date == null) {
            return null;
        }
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        // 设置时分秒为各自的最大值
        cd.set(Calendar.HOUR_OF_DAY, 23);
        cd.set(Calendar.MINUTE, 59);
        cd.set(Calendar.SECOND, 59);
        return cd.getTime();
    }
}
