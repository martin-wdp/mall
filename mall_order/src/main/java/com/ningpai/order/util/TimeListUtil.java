package com.ningpai.order.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 时间工具类
 * Created by NingPai-lih on 2015/7/20.
 */
public class TimeListUtil {
    
    /**
     * 获取时间
     * @return
     */
    public static List<String> getTimeList(){
        List<String> list=new ArrayList<String>();
        //时间类型
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //获取七天前的时间
        Calendar c = Calendar.getInstance();
        list.add(sdf.format(new Date()));
        for(int i=0;i<6;i++){
            //将当天时间减去1天
            c.add(Calendar.DATE, - 1);
            //获取时间
            Date monday = c.getTime();
            //进行转换
            String preMonday = sdf.format(monday);
            //放到集合内
            list.add(preMonday);
        }

        return list;
    }
}
