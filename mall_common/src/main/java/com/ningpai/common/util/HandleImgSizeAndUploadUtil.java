package com.ningpai.common.util;

import java.awt.Dimension;
import java.io.File;
import java.io.IOException;

import net.coobird.thumbnailator.Thumbnails;

/**
 * 处理图片大小和上传图片
 * 
 * @author AthrunNatu
 * @since 2013年11月20日上午8:41:06
 */
public class HandleImgSizeAndUploadUtil {
    /**
     * 设置改变上传图片的尺寸大小并上传到指定位置
     * 
     * @param pathFileName
     *            图片的路径大小
     * @param uploadPath
     *            指定上传位置
     * @param dimension
     *            <code>Dimension<code>@see java.awt.Dimension {@link java.awt.Dimension}}尺寸
     * @return int 1:表示成功 0 表示不成功
     */
    public int installImgSizeAndUpLoadImgByPathFileNamel(String pathFileName, String uploadPath, Dimension dimension) {
        try {
            Thumbnails.of(new File(pathFileName)).size(dimension.width, dimension.height).toFile(new File(uploadPath));
            return 1;
        } catch (IOException e) {
            return 0;
        }
    }

}
