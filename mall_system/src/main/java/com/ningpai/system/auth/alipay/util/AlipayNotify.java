package com.ningpai.system.auth.alipay.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import com.ningpai.system.auth.alipay.config.AlipayConfig;
import com.ningpai.util.MyLogger;

/**
 * 类名：AlipayNotify 功能：支付宝通知处理类 详细：处理支付宝各接口通知返回 版本：3.2 日期：2011-03-25 说明：
 * 以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 * 该代码仅供学习和研究支付宝接口使用，只是提供一个参考
 * 
 ************************* 注意************************* 调试通知返回时，可查看或改写log日志的写入TXT里的数据，来检查通知返回是否正常
 */
public final class AlipayNotify {

    /**
     * logger日志
     */
    private static final MyLogger LOGGER = new MyLogger(AlipayNotify.class);

    /**
     * 支付宝通知验证地址
     */
    private static final String HTTPS_VERIFY_URL = "https://mapi.alipay.com/gateway.do?service=notify_verify&";

    /**
     * 构造函数
     */
    private AlipayNotify() {
    }

    /**
     * 验证消息是否是支付宝发出的合法消息
     * 
     * @param params
     *            通知返回来的参数数组
     * @return 验证结果
     */
    public static boolean verify(Map<String, String> params) {
        String mysign = getMysign(params);
        String responseTxt = "true";
        if (params.get("notify_id") != null) {
            responseTxt = verifyResponse(params.get("notify_id"));
        }
        String sign = "";
        if (params.get("sign") != null) {
            sign = params.get("sign");
        }

        // 验证
        // responsetTxt的结果不是true，与服务器设置问题、合作身份者ID、notify_id一分钟失效有关
        // mysign与sign不等，与安全校验码、请求时的参数格式（如：带自定义参数等）、编码格式有关
        return (mysign.equals(sign) && "true".equals(responseTxt)) ? true : false;
    }

    /**
     * 根据反馈回来的信息，生成签名结果
     * 
     * @param Params
     *            通知返回来的参数数组
     * @return 生成的签名结果
     */
    private static String getMysign(Map<String, String> params) {
        // 过滤空值、sign与sign_type参数
        Map<String, String> sParaNew = AlipayCore.paraFilter(params);
        return AlipayCore.buildMysign(sParaNew);
    }

    /**
     * 获取远程服务器ATN结果,验证返回URL
     * 
     * @param notify_id
     *            通知校验ID
     * @return 服务器ATN结果 验证结果集： invalid命令参数不对 出现这个错误，请检测返回处理中partner和key是否为空 true
     *         返回正确信息 false 请检查防火墙或者是服务器阻止端口问题以及验证时间是否超过一分钟
     */
    private static String verifyResponse(String notifyid) {
        // 获取远程服务器ATN结果，验证是否是支付宝服务器发来的请求

        String partner = AlipayConfig.PARTNER;
        String veryfyurl = HTTPS_VERIFY_URL + "partner=" + partner + "&notify_id=" + notifyid;

        return checkUrl(veryfyurl);
    }

    /**
     * 获取远程服务器ATN结果
     * 
     * @param urlvalue
     *            指定URL路径地址
     * @return 服务器ATN结果 验证结果集： invalid命令参数不对 出现这个错误，请检测返回处理中partner和key是否为空 true
     *         返回正确信息 false 请检查防火墙或者是服务器阻止端口问题以及验证时间是否超过一分钟
     */
    private static String checkUrl(String urlvalue) {
        String inputLine = "";
        BufferedReader in = null;
        try {
            URL url = new URL(urlvalue);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = "";
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
            inputLine = sb.toString();
        } catch (Exception e) {
            LOGGER.error("",e);
        } finally {
            try {
                if (null != in) {
                    in.close();
                }
            } catch (IOException e) {
                LOGGER.error("",e);
            }
        }

        return inputLine;
    }
}
