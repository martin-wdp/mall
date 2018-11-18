package com.ningpai.m.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.ningpai.common.util.tenpay.util.MD5Util;

public class PayTools {
	private final static String key = "jinghuayijiajinghuayijiajinghuay";

	/***
	 * 获得防串改码
	 * 
	 * @param map
	 * @return
	 */
	public static String getSign(TreeMap<Object, Object> map) {
		Iterator<Entry<Object, Object>> iter = map.entrySet().iterator();
		StringBuffer temp = new StringBuffer();
		while (iter.hasNext()) {
			StringBuffer str = new StringBuffer();
			Entry<Object, Object> entry = (Entry<Object, Object>) iter
					.next();
			if (null == entry.getValue() || "".equals(entry.getValue())) {
				continue;
			}
			str.append(entry.getKey()).append("=").append(entry.getValue())
					.append("&");
			temp.append(str);
		}
		return MD5Util.MD5Encode(temp + "key=" + key ,"utf-8").toUpperCase();
	}

	public static Map<String, Object[]> MapToArray(TreeMap<String, Object> map) {
		Map<String, Object[]> data = new HashMap<String, Object[]>();
		String[] paramName = new String[map.size()];
		Object[] paramvalues = new Object[map.size()];
		Iterator<Entry<String, Object>> iter = map.entrySet().iterator();
		int i = 0;
		while (iter.hasNext()) {
			Entry<String, Object> entry = (Entry<String, Object>) iter
					.next();
			paramName[i] = entry.getKey();
			paramvalues[i] = entry.getValue();
			i++;
		}
		data.put("Names", paramName);
		data.put("Values", paramvalues);
		return data;
	}

}