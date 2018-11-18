/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * 资源文件工具类
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年8月28日下午4:09:16
 */
public class PropertieUtil {

    private static final Logger LOGGER = Logger.getLogger(PropertieUtil.class);

    private static final String AUTHOR_WHY = "author: why";

    private PropertieUtil() {
    }

    /**
     * 读取资源文件
     * 
     * @param filename
     *            资源文件全局名称
     * @return
     */
    public static Properties readPropertiesFile(String filename) throws IOException {
        Properties properties = new Properties();
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(filename);
            properties.load(inputStream);
        } catch (IOException e) {
            LOGGER.error(e.getLocalizedMessage(), e);
        } finally {
            if (inputStream != null) {
                inputStream.close(); // 关闭流
            }
        }
        return properties;
    }

    /**
     * 读取资源文件
     * 
     * @param inputStream
     *            资源文件输入流
     * @return
     */
    public static Properties readPropertiesFile(InputStream inputStream) {
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
            inputStream.close(); // 关闭流
        } catch (IOException e) {
            LOGGER.error(e.getLocalizedMessage(), e);
        }
        return properties;
    }

    /**
     * 写资源文件
     * 
     * @param filename
     *            资源文件全局名称
     * @param properties
     *            资源文件对象
     */
    public static void writePropertiesFile(String filename, Properties properties) throws IOException {
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(filename);
            properties.store(outputStream, AUTHOR_WHY);
        } catch (IOException e) {
            LOGGER.error(e.getLocalizedMessage(), e);
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }

    /**
     * 写资源文件
     * 
     * @param outputStream
     *            资源文件输出流
     * @param properties
     *            资源文件对象
     */
    public static void writePropertiesFile(OutputStream outputStream, Properties properties) {
        try {
            properties.store(outputStream, AUTHOR_WHY);
            outputStream.close();
        } catch (IOException e) {
            LOGGER.error(e.getLocalizedMessage(), e);
        }
    }

    /**
     * 读取XML文件
     * 
     * @param filename
     *            资源文件全局名称
     * @return
     */
    public static Properties readPropertiesFileFromXML(String filename) throws IOException {
        Properties properties = new Properties();
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(filename);
            properties.loadFromXML(inputStream);
        } catch (IOException e) {
            LOGGER.error(e.getLocalizedMessage(), e);
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return properties;
    }

    /**
     * 读取XML文件
     * 
     * @param inputStream
     *            资源文件输入流
     * @return
     */
    public static Properties readPropertiesFileFromXML(InputStream inputStream) {
        Properties properties = new Properties();
        try {
            properties.loadFromXML(inputStream);
            inputStream.close();
        } catch (IOException e) {
            LOGGER.error(e.getLocalizedMessage(), e);
        }
        return properties;
    }

    /**
     * 写资源文件到XML文件，含中文
     * 
     * @param filename
     *            资源文件全局名称
     * @param properties
     *            资源文件对象
     */
    public static void writePropertiesFileToXML(String filename, Properties properties) throws IOException {
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(filename);
            properties.storeToXML(outputStream, AUTHOR_WHY);
        } catch (IOException e) {
            LOGGER.error(e.getLocalizedMessage(), e);
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }

    /**
     * 写资源文件到XML文件，含中文
     * 
     * @param outputStream
     *            资源文件输出流
     * @param properties
     *            资源文件对象
     */
    public static void writePropertiesFileToXML(OutputStream outputStream, Properties properties) {
        try {
            properties.storeToXML(outputStream, AUTHOR_WHY);
            outputStream.close();
        } catch (IOException e) {
            LOGGER.error(e.getLocalizedMessage(), e);
        }
    }
}
