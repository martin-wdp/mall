package com.ningpai.system.exception;

import com.ningpai.util.MyLogger;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * 异常信息类 作用：根据异常的值，从异常信息配置文件
 * com.ningpai.system.exception.exception_messages_zh_CN 中获取异常的说明信息
 * 
 * @Description:
 * @author NINGPAI_xiaomm
 * @since 2014-03-19 16:01:06
 * @version V1.0
 */
public final class ExceptionMessages {

    /**
     * 日志
     * */
    public static final MyLogger LOGGER = new MyLogger(ExceptionMessages.class);

    /** 异常代码说明配置属性文件名 */
    private static final String BUNDLE_NAME = "com.ningpai.system.exception.exception_messages_zh_CN";
    /** 绑定异常代码 */
    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

    private ExceptionMessages() {
    }

    /**
     * 根据异常值获取说明信息
     * 
     * @param nErrorNumber
     *            异常编号
     * @return 异常说明信息
     */
    public static String getString(int nErrorNumber) {
        // 拼key值
        String key = "ExceptionNum." + nErrorNumber;
        try {
            // 获取value值
            return RESOURCE_BUNDLE.getString(key);
        } catch (MissingResourceException e) {
            LOGGER.error("",e);
            return '!' + key + '!';
        }
    }

}
