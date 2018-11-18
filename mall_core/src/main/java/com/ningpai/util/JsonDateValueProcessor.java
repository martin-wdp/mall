/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.util;

/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

/**
 * json转换成时间格式
 * 
 * @author ningpai09
 * @version 2013.04.28
 */
public class JsonDateValueProcessor implements JsonValueProcessor {

    /*
     * 定义一个时间类型
     */
    private String format = "yyyy-MM-dd HH:mm:ss";

    @Override
    public Object processArrayValue(Object value, JsonConfig config) {

        return process(value);
    }

    @Override
    public Object processObjectValue(String key, Object value, JsonConfig config) {
        return process(value);
    }

    /**
     * json时间转换
     * 
     * @param value
     * @return Object
     */
    public Object process(Object value) {
        if (value instanceof Date) {
            SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.UK);
            return sdf.format(value);
        }
        return value == null ? "" : value.toString();
    }

}
