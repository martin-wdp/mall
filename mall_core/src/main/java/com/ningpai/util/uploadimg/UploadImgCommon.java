/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.util.uploadimg;

import com.ningpai.manager.bean.ImageSet;
import com.ningpai.util.MyLogger;
import com.ningpai.util.UtilDate;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * @author NINGPAI-WangHaiYang
 * @since 2014年8月27日下午2:30:54
 */
public class UploadImgCommon {

    /** 要保存的文件名 */
    protected static String prefix;
    /** 服务器本地保存路径 */
    protected static String picPath;
    /** 后缀 */
    protected static String suffix;

    private static final int THREE = 3;

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(UploadImgCommon.class);

    private UploadImgCommon() {
    }

    /**
     * 获取项目的url路径
     * 
     * @param request
     * @return
     */
    public static String getProjectURL(HttpServletRequest request) {
        // 获取请求的url
        StringBuffer url = request.getRequestURL();
        // 获取请求的uri
        // String uri = request.getRequestURI();
        // String s = request.getContextPath();
        String uri = request.getServletPath();
        // 截取请求uri，获取请求地址
        // if(uri.indexOf("/",1)>0) {
        // uri = uri.substring(uri.indexOf("/", 1));
        // }
        // 截取请求url，去除请求地址，保留项目的url
        return url.substring(0, url.lastIndexOf(uri));
    }

    /**
     * 获取上传文件的保存名称、服务器本地保存路径、后缀，拼接成文件地址
     * 
     * @return
     */
    public static String getPicNamePathSuffix() {
        // 根据系统时间生成上传后保存的文件名
        long now = System.currentTimeMillis();
        prefix = String.valueOf(now);
        picPath = readValue().getProperty("IMAGEPATH") + UtilDate.todayFormatString(new Date()) + "/";
        // 根据真实路径创建目录文件
        File picSaveFile = new File(picPath);
        if (!picSaveFile.exists()) {
            try {
                picSaveFile.mkdirs();
            } catch (Exception e) {
                LOGGER.error("",e);
            }
        }
        // 文件的后缀
        suffix = ".jpg";
        return picPath + prefix + suffix;
    }

    /**
     * 获取宽度集合
     * 
     * @return
     */
    public static int[] getImgSet(List<ImageSet> imgSet) {
        int[] widths = new int[imgSet.size()];
        for (int i = 0; i < imgSet.size(); i++) {
            widths[i] = Integer.parseInt(imgSet.get(i).getRuleWidth());
        }
        return widths;
    }

    /**
     * 获取宽度集合，去除85这个宽度
     * 
     * @return
     */
    public static int[] getImgSetOut85(List<ImageSet> imgSet) {
        int[] widths = new int[THREE];
        for (int i = 0; i < imgSet.size(); i++) {
            if ("85".equals(imgSet.get(i).getRuleWidth())) {
                if (i < 3) {
                    i--;
                }
                continue;
            }
            widths[i] = Integer.parseInt(imgSet.get(i).getRuleWidth());
        }
        return widths;
    }

    /**
     * 给宽度集合升序排序
     * 
     * @param widths
     */
    public static void sortWidth(int[] widths) {
        for (int i = 0; i < widths.length; i++) {
            for (int j = i + 1; j < widths.length; j++) {
                int temp;
                if (widths[i] > widths[j]) {
                    temp = widths[i];
                    widths[i] = widths[j];
                    widths[j] = temp;
                }
            }
        }
    }

    /**
     * 读取配置文件信息
     * 
     * @return
     */
    public static Properties readValue() {
        Properties p = new Properties();
        try (InputStream inputStream = UploadImgCommon.class.getClassLoader().getResourceAsStream("com/ningpai/web/config/upload.properties")) {
            p.load(inputStream);
        } catch (IOException e1) {
            LOGGER.error("读取Properties属性文件错误：=>" ,e1);
        }
        return p;
    }

}
