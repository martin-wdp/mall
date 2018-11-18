/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.third.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * @author NINGPAI-WangHaiYang
 * @since 2014年6月13日下午2:17:50
 */
public class ThirdStoreURLUtil {

    /**
     * private static String thirdStoreURL = "http://localhost:8080/";
     */
    private static String thirdStoreURL = "http://www.qianmi.com/";

    /**
     * private static final String THIRDSTOREURLPATH= "";
     *
     */
    private static final Logger LOGGER = Logger.getLogger(ThirdStoreURLUtil.class);

    /**
     * 构造方法
     */
    private ThirdStoreURLUtil() {
    }

    /**
     * main
     * 
     * @param args
     */
    public static void main(String[] args) {
        /**
         * readfile.properties
         */
        String readfile = "d:" + File.separator + "readfile.properties";
        /**
         * writefile.properties
         */
        String writefile = "d:" + File.separator + "writefile.properties";
        /**
         * readtxtfile.txt
         */
        String readtxtfile = "d:" + File.separator + "readtxtfile.txt";
        /**
         * writetxtfile.txt
         */
        String writetxtfile = "d:" + File.separator + "writetxtfile.txt";

        /**
         * 读取properties文件
         */
        readPropertiesFile(readfile);
        /**
         * 写properties文件
         */
        writePropertiesFile(writefile);
        /**
         * 读取txt文件
         */
        readPropertiesFile(readtxtfile);
        /**
         * 写txt文件
         */
        writePropertiesFile(writetxtfile);
    }

    /**
     * 读取资源文件,并处理中文乱码
     * 
     * @param filename
     */
    public static void readPropertiesFile(String filename) {
        Properties properties = new Properties();
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(filename);
            properties.load(inputStream);
        } catch (IOException e) {
            LOGGER.error("IO错误", e);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close(); // 关闭流
                }
            } catch (IOException ioe) {
                LOGGER.error("关闭流时出错", ioe);
            }

        }

        String chinese = properties.getProperty("chinese");
        try {
            chinese = new String(chinese.getBytes("ISO-8859-1"), "GBK"); // 处理中文乱码
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("字符集转换错误", e);
        }
    }

    /**
     * 写资源文件，含中文
     * 
     * @param filename
     */
    public static void writePropertiesFile(String filename) {
        Properties properties = new Properties();
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(filename);
            properties.setProperty("username", "myname");
            properties.setProperty("password", "mypassword");
            properties.setProperty("chinese", "中文");
            properties.store(outputStream, "author: shixing_11@sina.com");
        } catch (IOException e) {
            LOGGER.error("IO错误", e);
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException ioe) {
                LOGGER.error("关闭流时出错", ioe);
            }
        }
    }

    /**
     * GET方法
     * 
     * @return
     */
    public static String getThirdStoreURL() {
        return ThirdStoreURLUtil.thirdStoreURL;
    }

    /**
     * SET方法
     * 
     * @param url
     */
    public static void setThirdStoreURL(String url) {
        ThirdStoreURLUtil.thirdStoreURL = url == null ? "" : url.trim();
    }
}
