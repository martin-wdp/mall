/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.common.util.wxap.util;

import java.security.MessageDigest;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;

import com.ningpai.common.util.ConstantUtil;
import com.ningpai.util.MyLogger;

/**
  *  '============================================================================
   * 'api说明：
   * 'createSHA1Sign创建签名SHA1
   * 'getSha1()Sha1签名
   * '============================================================================
'*/
public class Sha1Util {

    public static final MyLogger LOGGER = new MyLogger(Sha1Util.class);

    /**
     *
     */
    private Sha1Util(){
    }

    /**
     * 随机数 md5加密
     * @return
     */
    public static String getNonceStr() {
        Random random = new Random();
        return MD5Util.MD5Encode(String.valueOf(random.nextInt(10000)), ConstantUtil.UTF);
    }

    /**
     * 获取时间
     * @return
     */
    public static String getTimeStamp() {
        return String.valueOf(System.currentTimeMillis() / 1000);
    }


    /**
     * 创建签名SHA1
     * @param signParams
     * @return
     * @throws Exception
     */
    public static String createSHA1Sign(SortedMap<String, String> signParams) throws Exception {
        StringBuilder sb = new StringBuilder();
        Set es = signParams.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            String v = (String) entry.getValue();
            sb.append(k + "=" + v + "&");
            //要采用URLENCODER的原始值！
        }
        String params = sb.substring(0, sb.lastIndexOf("&"));
        return getSha1(params);
    }

    /**
     * Sha1签名
     * @param str
     * @return
     */
    public static String getSha1(String str) {
        String a = null;
        if (str == null || str.length() == 0) {
            return null;
        }
        char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f' };

        try {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(str.getBytes("GBK"));

            byte[] md = mdTemp.digest();
            int j = md.length;
            char[] buf = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(buf);
        } catch (Exception e) {
            LOGGER.error(""+e);
            return a;
        }
    }
}
