package com.ningpai.system.auth.alipay.util;

import java.io.InputStream;
import java.util.Properties;

import com.ningpai.util.MyLogger;

/**
 * 短信接口
 * 
 * @author jiping
 * @since 2015年8月18日 下午6:13:18
 * @version 0.0.1
 */
public class DeployInfoUtil {

    /** 常量 */
    private static final String STREAMSTR = "/deploy.properties";

    /** logger日志 */
    private static final MyLogger LOGGER = new MyLogger(DeployInfoUtil.class);

    /** 读取短信接口url */
    public String getSmsUrl() {
        Properties props = new Properties();

        try {

            InputStream in = getClass().getResourceAsStream(STREAMSTR);
            props.load(in);
            return props.getProperty("sms.url");
        } catch (Exception e) {
            LOGGER.error("",e);
            return null;
        }
    }

    /** 根据key读取value */
    public String getNetPayMerId() {
        Properties props = new Properties();

        try {

            InputStream in = getClass().getResourceAsStream(STREAMSTR);
            props.load(in);
            return props.getProperty("netPay.merId");
        } catch (Exception e) {
            LOGGER.error("",e);
            return null;
        }
    }

    /**
     * 读取属性netPay.backMerchantUrl
     * 
     * @return
     */
    public String getBackMerchantUrl() {
        Properties props = new Properties();

        try {

            InputStream in = getClass().getResourceAsStream(STREAMSTR);
            props.load(in);
            return props.getProperty("netPay.backMerchantUrl");
        } catch (Exception e) {
            LOGGER.error("",e);
        }
        return null;
    }

    /**
     * 读取属性netPay.merchantUrl
     * 
     * @return
     */
    public String getMerchantUrl() {
        Properties props = new Properties();

        try {

            InputStream in = getClass().getResourceAsStream(STREAMSTR);
            props.load(in);
            return props.getProperty("netPay.merchantUrl");
        } catch (Exception e) {
            LOGGER.error("",e);
        }
        return null;
    }

    /**
     * 读取属性netPay.version
     * 
     * @return
     */
    public String getNetPayVersion() {
        Properties props = new Properties();

        try {

            InputStream in = getClass().getResourceAsStream(STREAMSTR);
            props.load(in);
            return props.getProperty("netPay.version");
        } catch (Exception e) {
            LOGGER.error("",e);
        }
        return null;
    }

    /**
     * 读取属性netPay.netPayUrl
     * 
     * @return
     */
    public String getNetPayUrl() {
        Properties props = new Properties();

        try {

            InputStream in = getClass().getResourceAsStream(STREAMSTR);
            props.load(in);
            return props.getProperty("netPay.netPayUrl");
        } catch (Exception e) {
            LOGGER.error("",e);
        }
        return null;
    }
}
