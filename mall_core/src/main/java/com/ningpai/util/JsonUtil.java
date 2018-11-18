/*
 * Copyright 2010-2013 NINGPAI, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;
import net.sf.json.util.JSONUtils;

import org.apache.log4j.Logger;

/**
 * 处理json格式的工具类
 *
 * @author qiyuanyuan
 */
public class JsonUtil {

    private static final Logger LOGGER = Logger.getLogger(JsonUtil.class);

    private JsonUtil() {
    }

    /**
     * 从一个JSON 对象字符格式中得到一个java对象，形如： {"id" : idValue, "name" : nameValue,
     * "aBean" : {"aBeanId" : aBeanIdValue, ...}}
     *
     * @param jsonString
     * @param clazz
     * @return Object
     */
    public static Object getDTO(String jsonString, Class<?> clazz) {
        JSONObject jsonObject = null;
        try {
            setDataFormat2JAVA();
            jsonObject = JSONObject.fromObject(jsonString);
        } catch (Exception e) {
            LOGGER.error("JSON转换对象失败！", e);
        }
        return JSONObject.toBean(jsonObject, clazz);
    }

    /**
     * 从一个JSON 对象字符格式中得到一个java对象，其中beansList是一类的集合，形如： {"id" : idValue, "name" :
     * nameValue, "aBean" : {"aBeanId" : aBeanIdValue, ...}, beansList:[{}, {},
     * ...]}
     *
     * @param jsonString
     * @param clazz
     * @param map
     *            集合属性的类型 (key : 集合属性名, value : 集合属性类型class) eg: ("beansList" :
     *            Bean.class)
     * @return Object
     */
    public static Object getDTO(String jsonString, Class<?> clazz, Map<?, ?> map) {
        JSONObject jsonObject = null;
        try {
            setDataFormat2JAVA();
            jsonObject = JSONObject.fromObject(jsonString);
        } catch (Exception e) {
            LOGGER.error("JSON转换对象失败！", e);
        }
        return JSONObject.toBean(jsonObject, clazz, map);
    }

    /**
     * 从一个JSON数组得到一个java对象数组，形如： [{"id" : idValue, "name" : nameValue}, {"id" :
     * idValue, "name" : nameValue}, ...]
     *
     * @param jsonString
     * @param clazz
     * @return Object[]
     */
    public static Object[] getDTOArray(String jsonString, Class<?> clazz) {
        setDataFormat2JAVA();
        JSONArray array = JSONArray.fromObject(jsonString);
        Object[] obj = new Object[array.size()];
        for (int i = 0; i < array.size(); i++) {
            JSONObject jsonObject = array.getJSONObject(i);
            obj[i] = JSONObject.toBean(jsonObject, clazz);
        }
        return obj;
    }

    /**
     * 从一个JSON数组得到一个java对象数组，形如： [{"id" : idValue, "name" : nameValue}, {"id" :
     * idValue, "name" : nameValue}, ...]
     *
     * @param jsonString
     * @param clazz
     * @param map
     * @return Object[]
     */
    public static Object[] getDTOArray(String jsonString, Class<?> clazz, Map<?, ?> map) {
        setDataFormat2JAVA();
        JSONArray array = JSONArray.fromObject(jsonString);
        Object[] obj = new Object[array.size()];
        for (int i = 0; i < array.size(); i++) {
            JSONObject jsonObject = array.getJSONObject(i);
            obj[i] = JSONObject.toBean(jsonObject, clazz, map);
        }
        return obj;
    }

    /**
     * 从一个JSON数组得到一个java对象集合
     *
     * @param jsonString
     * @param clazz
     * @return List<T>
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> getDTOList(String jsonString, Class<T> clazz) {
        setDataFormat2JAVA();
        JSONArray array = JSONArray.fromObject(jsonString);
        List<T> list = new ArrayList<T>();
        for (Iterator<T> iter = array.iterator(); iter.hasNext();) {
            JSONObject jsonObject = (JSONObject) iter.next();
            list.add((T) JSONObject.toBean(jsonObject, clazz));
        }
        return list;
    }

    /**
     * 从一个JSON数组得到一个java对象集合，其中对象中包含有集合属性
     *
     * @param jsonString
     * @param clazz
     * @param map
     *            集合属性的类型
     * @return List
     */
    public static List<Object> getDTOList(String jsonString, Class<?> clazz, Map<?, ?> map) {
        setDataFormat2JAVA();
        JSONArray array = JSONArray.fromObject(jsonString);
        List<Object> list = new ArrayList<Object>();
        for (Iterator<?> iter = array.iterator(); iter.hasNext();) {
            JSONObject jsonObject = (JSONObject) iter.next();
            list.add(JSONObject.toBean(jsonObject, clazz, map));
        }
        return list;
    }

    /**
     * 从json HASH表达式中获取一个map，该map支持嵌套功能 形如：{"id" : "johncon", "name" : "小强"}
     * 注意commons
     * -collections版本，必须包含org.apache.commons.collections.map.MultiKeyMap
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
     * 从json数组中得到相应java数组 json形如：["123", "456"]
     *
     * @param jsonString
     * @return Object[]
     */
    public static Object[] getObjectArrayFromJson(String jsonString) {
        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        return jsonArray.toArray();
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
