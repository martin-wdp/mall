/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.site.threepart.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import com.upyun.OSinfo;

/**
 * @author ggn
 * 
 */
public final class LoginDownLoad {

    private LoginDownLoad() {

    }

    /**
     * 下载
     * 
     * @param urlString
     *            图片路径
     * @param filename
     *            图片名称
     * @param savePath
     *            储存路径
     * @throws Exception
     */
    public static void download(String urlString, String filename, String savePath) throws Exception {
        // 构造URL
        URL url = new URL(urlString);
        // 打开连接
        URLConnection con = url.openConnection();
        // 设置请求超时为5s
        con.setConnectTimeout(5 * 1000);
        // 输入流
        InputStream is = con.getInputStream();

        // 1K的数据缓冲
        byte[] bs = new byte[1024];
        // 读取到的数据长度
        int len;
        // 输出的文件流
        File sf = new File(savePath);
        if (!sf.exists()) {
            sf.mkdirs();
        }
        String s = OSinfo.getOSname().toString();
        String pa = "";
        if ("Linux".equals(s)) {
            pa = "/";
        } else if ("Windows".equals(s)) {
            pa = "\\";
        }

        OutputStream os = new FileOutputStream(sf.getPath() + pa + filename);
        // 开始读取
        while ((len = is.read(bs)) != -1) {
            os.write(bs, 0, len);
        }
        // 完毕，关闭所有链接
        os.close();
        is.close();
    }

}
