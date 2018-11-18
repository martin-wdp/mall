/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.log4j.Logger;

/**
 * 通过java获取图片的宽和高
 * 
 * @author sunlightcs 2011-6-1 http://hi.juziku.com/sunlightcs/
 */
public class ImageTools {

    private static final Logger LOGGER = Logger.getLogger(ImageTools.class);

    private ImageTools() {
    }

    /**
     * 获取图片宽度
     * 
     * @param file
     *            图片文件
     * @return 宽度
     */
    public static int getImgWidth(File file) {
        InputStream is = null;
        BufferedImage src = null;
        int ret = -1;
        try {
            is = new FileInputStream(file);
            src = javax.imageio.ImageIO.read(is);
            ret = src.getWidth(null); // 得到源图宽
            is.close();
        } catch (Exception e) {
            LOGGER.error("获取图片宽度失败！", e);
        }
        return ret;
    }

    /**
     * 获取图片高度
     * 
     * @param file
     *            图片文件
     * @return 高度
     */
    public static int getImgHeight(File file) {
        InputStream is = null;
        BufferedImage src = null;
        int ret = -1;
        try {
            is = new FileInputStream(file);
            src = javax.imageio.ImageIO.read(is);
            ret = src.getHeight(null); // 得到源图高
            is.close();
        } catch (Exception e) {
            LOGGER.error("获取图片高度失败！", e);
        }
        return ret;
    }

}
