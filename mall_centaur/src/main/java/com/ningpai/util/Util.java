package com.ningpai.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import com.ningpai.common.util.ConstantUtil;

/**
 * 工具类
 * 
 * @author ggn
 * 
 */
public class Util {

    /**
     * 日志
     * */
    public static final MyLogger LOGGER = new MyLogger(Util.class);

    /**
     * 构造
     */
    private Util() {
    }

    /**
     * URLEncoder转码
     * 
     * @throws UnsupportedEncodingException
     */
    public static String encodeUri(String s) throws UnsupportedEncodingException {
        return URLEncoder.encode(s, ConstantUtil.UTF);
    }

    /**
     * md5加密
     * 
     * @param params
     *            参数
     * @param secret
     * @return
     */

    public static String md5Signature(TreeMap<String, String> params, String secret) {

        String result = null;

        StringBuilder orgin = getBeforeSign(params, new StringBuilder(secret));

        if (orgin == null) {
            return result;
        }

        try {
            // MD5算法
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 二进制转字符串赋值给result
            result = byte2hex(md.digest(orgin.toString().getBytes(ConstantUtil.UTF)));

        } catch (Exception e) {
            LOGGER.error("",e);
            // 抛出异常
            throw new java.lang.RuntimeException("sign error !");

        }
        // 返回result
        return result;

    }

    /**
     * 二进制转字符串
     * 
     * @param b
     *            字节数组
     * @return String
     */

    private static String byte2hex(byte[] b) {

        StringBuilder hs = new StringBuilder();

        String stmp;

        // 遍历字节数组
        for (int n = 0; n < b.length; n++) {
            // toHexString方法类型为int型，所以转Hex前参数会提升成整型后再进行转换
            stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));

            // 判断stmp长度是否为1
            if (stmp.length() == 1) {
                // hs拼接0后拼接stmp
                hs.append("0").append(stmp);
            } else {
                // hs拼接stmp
                hs.append(stmp);
            }
        }
        // 返回转化为大写字母的字符串
        return hs.toString().toUpperCase();

    }

    /**
     * 获取参数
     * 
     * @param params
     * @param orgin
     * @return
     */

    private static StringBuilder getBeforeSign(TreeMap<String, String> params, StringBuilder orgin) {
        // 判断参数是否为空
        if (params == null) {
            // 返回null
            return null;
        }

        Map<String, String> treeMap = new TreeMap<String, String>();

        treeMap.putAll(params);
        // 迭代器用于while循环
        Iterator<String> iter = treeMap.keySet().iterator();
        while (iter.hasNext()) {

            String name = (String) iter.next();

            orgin.append(name).append(params.get(name));

        }
        return orgin;

    }

    /**
     * 获取结果
     * 
     * @param urlStr
     *            链接
     * @param content
     *            内容
     * @return String
     */

    public static String getResult(String urlStr, String content) {

        // 定义URL对象url
        URL url = null;

        // 定义HttpURLConnection对象
        HttpURLConnection connection = null;

        try {
            // 创建URL对象
            url = new URL(urlStr);
            // 返回一个URLConnection对象，它表示到URL所引用的远程对象的连接
            connection = (HttpURLConnection) url.openConnection();
            // http正文内，因此需要设为true, 默认情况下是false
            connection.setDoOutput(true);
            // 设置是否从httpUrlConnection读入，默认情况下是true;
            connection.setDoInput(true);
            // 设定请求方式
            connection.setRequestMethod("POST");
            // Post 请求不能使用缓存
            connection.setUseCaches(false);
            // 设定传送的内容类型是可序列化的java对象
            // (如果不设此项,在传送序列化对象时,当WEB服务默认的不是这种类型时可能抛java.io.EOFException)
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            // 建立到远程对象的实际连接
            connection.connect();

            // 数据输出流
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            // 将content写入到out输出流对象中
            out.write(content.getBytes(ConstantUtil.UTF));
            // 流刷新
            out.flush();
            // 关闭流
            out.close();

            // 读取文本文件
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), ConstantUtil.UTF));

            StringBuilder buffer = new StringBuilder();

            String line = "";

            // 每读一行进行一次写入动作
            while ((line = reader.readLine()) != null) {

                buffer.append(line);

            }

            reader.close();
            // 转String类型
            return buffer.toString();

        } catch (IOException e) {
            LOGGER.error("",e);
            connection = null;

        } finally {

            if (connection != null) {

                connection.disconnect();

            }

        }

        return null;

    }

}
