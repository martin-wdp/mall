/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.api.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.ningpai.util.JsonDateValueProcessor;
import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;
import net.sf.json.util.JSONUtils;

/**
 * @author NINGPAI-ZHOUY
 * @since 2013年12月23日下午3:51:15
 * @version 1.0
 */
public class JsonUtil {
    
    private JsonUtil(){
    }

    /**
     * 从json HASH表达式中获取一个map，该map支持嵌套功能 形如：{"id" : "johncon", "name" : "小强"} 注意commons -collections版本，必须包含org.apache.commons.collections.map.MultiKeyMap
     * 
     * @param jsonString
     * @return Map
     */
    public static Map<String, Object> getMapFromJson(String jsonString) {
        setDataFormat2JAVA();
        JSONObject jsonObject = JSONObject.fromObject(jsonString);
        Map<String, Object> map = new HashMap<String, Object>();
        for (Iterator<?> iter = jsonObject.keys(); iter.hasNext();) {
            String key = (String) iter.next();
            map.put(key, jsonObject.get(key));
        }
        return map;
    }
    /**
     * 把数据对象转换成json字符串 DTO对象形如：{"id" : idValue, "name" : nameValue, ...}
     * 数组对象形如：[{}, {}, {}, ...] map对象形如：{key1 : {"id" : idValue, "name" :
     * nameValue, ...}, key2 : {}, ...}
     *
     * @param object
     * @return String
     */
    public static String getJSONString(Object object) {
        String jsonString = null;
        // 日期值处理器
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(java.util.Date.class, new JsonDateValueProcessor());
        jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
        if (object != null) {
            if (object instanceof Collection || object instanceof Object[]) {
                jsonString = JSONArray.fromObject(object, jsonConfig).toString();
            } else {
                jsonString = JSONObject.fromObject(object, jsonConfig).toString();
            }
        }
        return jsonString == null ? "{}" : jsonString;
    }

    private static void setDataFormat2JAVA() {
        // 设定日期转换格式
        JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(new String[] { "yyyy-MM-dd HH:mm:ss" }));
    }
}
