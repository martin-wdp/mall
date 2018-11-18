package com.ningpai.common.util;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.io.FileInputStream;

/**
 * 计算文件大小的工具类
 * 
 * @author AthrunNatu
 * @since 2013年11月20日上午9:13:39
 */
public class GetFileSizeUtil {

    private GetFileSizeUtil() {
    }

    /**
     * 计算文件大小
     * 
     * @param f
     *            <code>File</code> @see java.io.File {@link java.io.File} 文件对象
     * @return 文件大小
     */
    public static long getFileSizes(File f) {
        try {
            if (f.exists()) {
                return new FileInputStream(f).available();
            } else {
                f.createNewFile();
                return 0L;
            }
        } catch (IOException e) {
            return 0L;
        }
    }

    /**
     * 计算文件大小
     * 
     * @param f
     *            <code>File</code> @see java.io.File {@link java.io.File} 文件对象
     * @return 文件大小
     */
    public static long getFileSize(File f) {
        long size = 0;
        File[] flist = f.listFiles();
        for (int i = 0; i < flist.length; i++) {
            if (flist[i].isDirectory()) {
                size = size + getFileSize(flist[i]);
            } else {
                size = size + flist[i].length();
            }
        }
        return size;
    }

    /**
     * 按格式计算文件大小
     * 
     * @param fileS
     *            文件字节数
     * @return 文件格式大小
     */
    public static String FormetFileSize(long fileS) {// 转换文件大小
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "K";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "M";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "G";
        }
        return fileSizeString;
    }

}
