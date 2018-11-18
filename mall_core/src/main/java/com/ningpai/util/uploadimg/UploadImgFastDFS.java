/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.util.uploadimg;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ningpai.fastdfs.bean.FastDFSInfo;
import com.ningpai.main.Upload;
import com.ningpai.util.MyLogger;

/**
 * 图片上传-FastDFS
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年8月27日上午10:04:00
 */
@Component("UploadImgFastDFS")
public class UploadImgFastDFS {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(UploadImgFastDFS.class);

    /**
     * 上传、保存已处理的图片图片
     * 
     * @param map
     * @return
     */
    public Map<String, String> saveToFastDFS(Map<String, String> map, FastDFSInfo fastdfs) {
        Map<String, String> newMap = new HashMap<String, String>();
        try {
            for (String key : map.keySet()) {
                String fileNamess = UploadImgCommon.getPicNamePathSuffix();
                URL url = new URL(map.get(key));
                URLConnection con = url.openConnection();
                try (InputStream is = con.getInputStream(); FileOutputStream fos = new FileOutputStream(fileNamess)) {
                    byte[] bt = new byte[2048];
                    int b = 0;
                    while ((b = is.read(bt)) != -1) {
                        // 将对象写入到对应的文件中
                        fos.write(bt, 0, b);
                    }
                } catch (final Exception e) {
                    LOGGER.error("", e);
                }
                newMap.put(key, fastdfs.getResultPath() + Upload.uploadToFSD(fileNamess, fastdfs));
            }
            return newMap;
        } catch (Exception e) {
            for (String key : map.keySet()) {
                //
                newMap.put(key, fastdfs.getResultPath() + Upload.uploadToFSD(map.get(key), fastdfs));
            }
            LOGGER.error("保存到FastDFS失败-执行本地上传" ,e);
            return newMap;

        }
    }

    /**
     * 上传、保存已处理的图片图片
     * 
     * @param imgPath
     * @return
     */
    public String saveToFastDFS(String imgPath, FastDFSInfo fastdfs) {
        try {
            return fastdfs.getResultPath() + Upload.uploadToFSD(imgPath, fastdfs);
        } catch (Exception e) {
            LOGGER.error("保存到FastDFS失败",e);
            return imgPath;
        }
    }

    /**
     * 上传、保存已处理的图片图片
     * 
     * @param imgPath
     * @return
     */
    public String saveToFastDFSSite(String imgPath, FastDFSInfo fastdfs) {
        try {
            String fileNamess = UploadImgCommon.getPicNamePathSuffix();
            URL url = new URL(imgPath);
            URLConnection con = url.openConnection();
            try (InputStream is = con.getInputStream(); FileOutputStream fos = new FileOutputStream(fileNamess)) {
                byte[] bt = new byte[2048];
                int b = 0;
                while ((b = is.read(bt)) != -1) {
                    // 将对象写入到对应的文件中
                    fos.write(bt, 0, b);
                }
            } catch (final Exception e) {
                LOGGER.error(e.getLocalizedMessage(), e);
            }
            return fastdfs.getResultPath() + Upload.uploadToFSD(fileNamess, fastdfs);
        } catch (Exception e) {
            LOGGER.error("保存到FastDFS失败" ,e);
            return imgPath;
        }
    }

}
