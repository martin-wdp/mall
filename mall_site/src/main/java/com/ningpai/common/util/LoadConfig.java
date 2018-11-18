/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.common.util;

import com.ningpai.util.MyLogger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;



/**ss
 * 加载配置文件
 * @author huangyi
 *
 */
public class LoadConfig {
    
    private Properties prop; 
    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(LoadConfig.class);

    /**
     * 初始化
     * @param path 传入的地址
     */
    public LoadConfig(String path) {  

        prop = new Properties();  
  
        InputStream in = null;  
  
        try {  
  
            in = getClass().getResourceAsStream(path);  
  
            prop.load(in);  
  
            in.close();  
  
        } catch (IOException e) {  
            LOGGER.error("发送邮件错误"+e);
        }  
  
    }

    /**
     * 获取jdbc中的driver
     * @return
     */
    public String getDriverClass() {  
  
        return prop.getProperty("jdbc.driver");  
  
    }

    /**
     * 获取jdbc中的url
     * @return
     */
    public String getURL() {  
  
        return prop.getProperty("jdbc.url");  
  
    }

    /**
     * 获取jdbc中的username
     * @return
     */
    public String getUserName() {  
  
        return prop.getProperty("jdbc.username");  
    }

    /**
     * 获取jdbc中的password
     * @return
     */
    public String getPassWord() {  
  
        return prop.getProperty("jdbc.password");  
    }  
  

  
    
}
