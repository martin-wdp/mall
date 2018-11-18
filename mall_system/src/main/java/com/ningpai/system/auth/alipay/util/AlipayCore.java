package com.ningpai.system.auth.alipay.util;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.ningpai.system.auth.alipay.config.AlipayConfig;
import com.ningpai.util.MyLogger;

/**
 * 类名：AlipayFunction 功能：支付宝接口公用函数类 详细：该类是请求、通知返回两个文件所调用的公用函数核心处理文件，不需要修改 版本：3.2
 * 日期：2011-03-17 说明： 以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 * 该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public final class AlipayCore {

    /**
     * logger日志
     */
    private static final MyLogger LOGGER = new MyLogger(AlipayCore.class);

    /**
     * 构造函数
     */
    private AlipayCore() {
    }

    /**
     * 生成签名结果
     *
     * @param sArray
     *            要签名的数组
     * @return 签名结果字符串
     */
    public static String buildMysign(Map<String, String> sArray) {
        // 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
        String prestr = createLinkString(sArray);
        // 把拼接后的字符串再与安全校验码直接连接起来
        prestr = prestr + AlipayConfig.KEY;
        return AlipayMd5Encrypt.md5(prestr);
    }

    /**
     * 除去数组中的空值和签名参数
     *
     * @param sArray
     *            签名参数组
     * @return 去掉空值与签名参数后的新签名参数组
     */
    public static Map<String, String> paraFilter(Map<String, String> sArray) {

        Map<String, String> result = new HashMap<String, String>();

        if (sArray == null || sArray.isEmpty()) {
            return result;
        }

        for (Entry<String, String> key : sArray.entrySet()) {
            String value = key.getValue();
            if (value == null || "".equals(value) || "sign".equalsIgnoreCase(key.getKey()) || "sign_type".equalsIgnoreCase(key.getKey())) {
                continue;
            }
            result.put(key.getKey(), value);
        }

        return result;
    }

    /**
     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     *
     * @param params
     *            需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     */
    public static String createLinkString(Map<String, String> params) {

        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);
        StringBuilder prestr = new StringBuilder();

        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);

            prestr.append(key);
            prestr.append("=");
            prestr.append(value);
            // 拼接时，不包括最后一个&字符
            if (!(i == keys.size() - 1)) {
                prestr.append("&");
            }
        }
        return prestr.toString();

    }

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     *
     * @param sWord
     *            要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(AlipayConfig.LOG_PATH);
            writer.write(sWord);
        } catch (Exception e) {
            LOGGER.error("", e);
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    LOGGER.error("",e);
                }
            }
        }
    }
}
