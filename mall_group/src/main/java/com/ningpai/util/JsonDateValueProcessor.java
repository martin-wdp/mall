/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.util;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *@version 2014年5月30日 下午2:00:39
 *@author qiyuanyuan
 */

public class JsonDateValueProcessor implements JsonValueProcessor{
     /**
     * 定义一个时间类型
     */
    private String format = "yyyy-MM-dd HH:mm:ss";

    /**
     * json时间转换
     * @param value
     * @param config
     * @return
     */
    public Object processArrayValue(Object value, JsonConfig config) {
        
        return process(value);
    }

    /**
     * json时间转换
     * @param key
     * @param value
     * @param config
     * @return
     */
    public Object processObjectValue(String key, Object value, JsonConfig config) {
        return process(value);
    }
    
    /**
     * json时间转换
     * @param value
     * @return Object
     */
    public Object process(Object value) {
        if(value instanceof Date){
            SimpleDateFormat sdf = new SimpleDateFormat(format,Locale.UK);
            return sdf.format(value);
        }
        return value== null ? "" :value.toString();
    }
}
