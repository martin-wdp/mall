/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.util;

import com.ningpai.fastdfs.bean.FastDFSInfo;
import com.ningpai.fastdfs.service.FastDFSInfoService;
import com.ningpai.main.Upload;
import com.ningpai.manager.bean.ImageSet;
import com.ningpai.manager.bean.UpyunConf;
import com.ningpai.manager.mapper.SysHelperMapper;
import com.upyun.DownLoad;
import com.upyun.OSinfo;
import com.upyun.UpYunUtil;
import com.upyun.YunBean;
import org.apache.commons.fileupload.FileItem;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * @author ggn 图片上传工具类
 */
@Controller
public class UploadUtilBack {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(UploadUtilBack.class);

    private static final String IMAGE = "image";
    private static final String OLDIMG = "oldimg";
    private static final String LINUX = "Linux";
    private static final String WINDOWS = "Windows";
    private static final String IMAGEPATH = "IMAGEPATH";
    private static final String LOGGERINFO1 = "上传的图片不符合限制";
    private static final String LOGGERINFO2 = "==========原图地址：";
    private static final String LOGGERINFO3 = "图片上传，返回key值列表错误：=>";
    private static final String LOGGERINFO4 = "图片上传，保存一张原图错误：=>";
    private static final String LOGGERINFO5 = "图片上传，返回width值列表错误：=>";
    private static final String LOGGERINFO6 = "Kindeditor富文本框图片上传错误：=>";

    /**
     * SysHelperMapper接口
     */
    private static SysHelperMapper sysHelperMapper;

    /**
     * SysHelperMapper
     */
    private SysHelperMapper sysHelperMapperNext;

    /**
     * 最大文件大小
     */
    private static long maxSize = 10 * 1024 * 1024;

    /**
     * 定义允许上传的文件扩展名
     */
    private static HashMap<String, String> extMap = new HashMap<String, String>();

    /**
     * SERVICE-FastDFS设置信息
     */
    private static FastDFSInfoService fastDFSService;

    /**
     * 静态的私有的常量
     */
    private static final int THREE = 3;

    /**
     * 图片上传，返回key值列表
     * 
     * @param muFile
     *            图片
     * @param request
     * @return Map 返回中 key值{0,1,2,oldimg} 0小图 1中图 2大图 oldimg 原图
     */
    public static Map<String, String> uploadFile(MultipartFile muFile, HttpServletRequest request) {
        UpyunConf upConf = sysHelperMapper.selectUpyunConf();
        if (muFile != null && muFile.getSize() != 0) {
            // 检查上传的图片的大小和扩展名
            if (!checkFileForSpringUpload(muFile)) {
                throw new RuntimeException(LOGGERINFO1);
            }

            Map<String, String> imgMap = new HashMap<String, String>();
            long now = System.currentTimeMillis();
            // 根据系统时间生成上传后保存的文件名
            String prefix = String.valueOf(now);
            // 保存图片路径
            UploadUtilBack uu = new UploadUtilBack();
            String picPath = uu.readValue().getProperty(IMAGEPATH) + UtilDate.todayFormatString(new Date()) + "/";
            // 根据真实路径创建目录文件
            File picSaveFile = new File(picPath);
            if (!picSaveFile.exists()) {
                try {
                    picSaveFile.mkdirs();
                } catch (Exception e) {
                    LOGGER.error("", e);
                }
            }
            // 文件的后缀
            String suffix = ".jpg";
            String fileNamess = picPath + prefix + suffix;

            // String mysqlPath = uu.readValue().getProperty("MYSQLPATH");
            File file = new File(fileNamess);
            try {
                muFile.transferTo(file);
                FastDFSInfo fastdfs = fastDFSService.getFastDFSInfoByCurr();
                if (upConf != null) {
                    // 启用又拍云
                    String fastUserd = fastdfs.getUserd();
                    if ("0".equals(fastUserd)) {
                        YunBean yb = new YunBean();
                        yb.setBucketName(upConf.getBucketName());
                        yb.setPassword(upConf.getPassWord());
                        yb.setUserName(upConf.getUserName());
                        UpYunUtil.yunUp(fileNamess, prefix, yb, suffix);
                        // 下载原图
                        LOGGER.debug(LOGGERINFO2 + upConf.getUrlPath() + prefix + suffix);
                        imgMap.put(OLDIMG, upConf.getUrlPath() + prefix + suffix);
                        List<ImageSet> imgSet = sysHelperMapper.selectImageSet();
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
                        for (int i = 0; i < widths.length; i++) {

                            // 下载指定尺寸图
                            LOGGER.debug("==========" + widths[i] + "尺寸的图片地址：" + upConf.getUrlPath() + prefix + suffix + "!" + widths[i]);
                            imgMap.put(i + "", upConf.getUrlPath() + prefix + suffix + "!" + widths[i]);
                        }
                        // 不启用又拍云
                    } else {
                        YunBean yb = new YunBean();
                        yb.setBucketName(upConf.getBucketName());
                        yb.setPassword(upConf.getPassWord());
                        yb.setUserName(upConf.getUserName());
                        UpYunUtil.yunUp(fileNamess, prefix, yb, suffix);
                        String s = OSinfo.getOSname().toString();
                        String pa = "";
                        if (LINUX.equals(s)) {
                            pa = "/";
                        } else if (WINDOWS.equals(s)) {
                            pa = "\\";
                        }
                        // 下载原图
                        imgMap.put(OLDIMG, fastdfs.getResultPath() + Upload.uploadToFSD(picPath + pa + prefix + suffix, fastdfs));

                        List<ImageSet> imgSet = sysHelperMapper.selectImageSet();
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
                        for (int i = 0; i < widths.length; i++) {
                            DownLoad.download(upConf.getUrlPath() + prefix + suffix, prefix + "_" + widths[i] + suffix, picPath, widths[i] + "");
                            // 下载指定尺寸图
                            imgMap.put(i + "", fastdfs.getResultPath() + Upload.uploadToFSD(picPath + pa + prefix + suffix, fastdfs));

                        }
                    }
                }
            } catch (IllegalStateException e) {
                LOGGER.error(LOGGERINFO3, e);
            } catch (IOException e) {
                LOGGER.error(LOGGERINFO3, e);
            } catch (Exception e) {
                LOGGER.error(LOGGERINFO3, e);
            }
            return imgMap;
        } else {
            return null;
        }
    }

    /**
     * 图片上传，保存一张原图
     * 
     * @param muFile
     *            图片
     * @param request
     * @return String
     */
    public static String uploadFileOne(MultipartFile muFile, HttpServletRequest request) {
        UpyunConf upConf = sysHelperMapper.selectUpyunConf();
        if (muFile != null && muFile.getSize() != 0) {
            // 检查上传的图片的大小和扩展名
            if (!checkFileForSpringUpload(muFile)) {
                LOGGER.error("验证上传图片的大小和扩展名错误！");
                return "";
            }

            long now = System.currentTimeMillis();
            // 根据系统时间生成上传后保存的文件名
            String prefix = String.valueOf(now);
            // 保存图片路径
            UploadUtilBack uu = new UploadUtilBack();
            String picPath = uu.readValue().getProperty(IMAGEPATH) + UtilDate.todayFormatString(new Date()) + "/";
            // 根据真实路径创建目录文件
            File picSaveFile = new File(picPath);
            if (!picSaveFile.exists()) {
                picSaveFile.mkdirs();
            }
            // 文件的后缀
            String suffix = ".jpg";
            String fileNamess = picPath + prefix + suffix;
            String saveBase = "";
            File file = new File(fileNamess);
            try {
                muFile.transferTo(file);
                FastDFSInfo fastdfs = fastDFSService.getFastDFSInfoByCurr();
                if (upConf != null) {
                    // 启用又拍云
                    String fastUserd = fastdfs.getUserd();
                    if ("0".equals(fastUserd)) {
                        YunBean yb = new YunBean();
                        yb.setBucketName(upConf.getBucketName());
                        yb.setPassword(upConf.getPassWord());
                        yb.setUserName(upConf.getUserName());
                        UpYunUtil.yunUp(fileNamess, prefix, yb, suffix);
                        // 下载原图
                        LOGGER.debug(LOGGERINFO2 + upConf.getUrlPath() + prefix + suffix);
                        saveBase = upConf.getUrlPath() + prefix + suffix;

                        // 不启用又拍云
                    } else {
                        YunBean yb = new YunBean();
                        yb.setBucketName(upConf.getBucketName());
                        yb.setPassword(upConf.getPassWord());
                        yb.setUserName(upConf.getUserName());
                        UpYunUtil.yunUp(fileNamess, prefix, yb, suffix);
                        String s = OSinfo.getOSname().toString();
                        String pa = "";
                        if (LINUX.equals(s)) {
                            pa = "/";
                        } else if (WINDOWS.equals(s)) {
                            pa = "\\";
                        }
                        // 下载原图
                        saveBase = fastdfs.getResultPath() + Upload.uploadToFSD(picPath + pa + prefix + suffix, fastdfs);
                    }
                }
            } catch (IllegalStateException e) {
                LOGGER.error(LOGGERINFO4, e);
            } catch (IOException e) {
                LOGGER.error(LOGGERINFO4, e);
            } catch (Exception e) {
                LOGGER.error(LOGGERINFO4, e);
            }
            return saveBase;
        } else {
            return "";
        }
    }

    /**
     * 图片上传,保存多张图片，key值为宽度
     * 
     * @param muFile
     *            图片
     * @param request
     * @return Map 返回中 key值{width...,oldimg} oldimg 原图
     */
    public static Map<String, String> uploadFileByWidth(MultipartFile muFile, HttpServletRequest request) {
        UpyunConf upConf = sysHelperMapper.selectUpyunConf();
        if (muFile != null && muFile.getSize() != 0) {
            // 检查上传的图片的大小和扩展名
            if (!checkFileForSpringUpload(muFile)) {
                throw new RuntimeException(LOGGERINFO1);
            }

            Map<String, String> imgMap = new HashMap<String, String>();
            long now = System.currentTimeMillis();
            // 根据系统时间生成上传后保存的文件名
            String prefix = String.valueOf(now);
            // 保存图片路径
            UploadUtilBack uu = new UploadUtilBack();
            String picPath = uu.readValue().getProperty(IMAGEPATH) + UtilDate.todayFormatString(new Date()) + "/";
            // 根据真实路径创建目录文件
            File picSaveFile = new File(picPath);
            if (!picSaveFile.exists()) {
                try {
                    picSaveFile.mkdirs();
                } catch (Exception e) {
                    LOGGER.error("", e);
                }
            }
            // 文件的后缀
            String suffix = ".jpg";
            String fileNamess = picPath + prefix + suffix;
            File file = new File(fileNamess);
            try {
                muFile.transferTo(file);
                FastDFSInfo fastdfs = fastDFSService.getFastDFSInfoByCurr();
                if (upConf != null) {
                    // 启用又拍云
                    String fastUserd = fastdfs.getUserd();
                    if ("0".equals(fastUserd)) {
                        YunBean yb = new YunBean();
                        yb.setBucketName(upConf.getBucketName());
                        yb.setPassword(upConf.getPassWord());
                        yb.setUserName(upConf.getUserName());
                        UpYunUtil.yunUp(fileNamess, prefix, yb, suffix);
                        // 下载原图
                        LOGGER.debug(LOGGERINFO2 + upConf.getUrlPath() + prefix + suffix);
                        imgMap.put(OLDIMG, upConf.getUrlPath() + prefix + suffix);
                        List<ImageSet> imgSet = sysHelperMapper.selectImageSet();
                        int[] widths = new int[imgSet.size()];
                        for (int i = 0; i < imgSet.size(); i++) {
                            widths[i] = Integer.parseInt(imgSet.get(i).getRuleWidth());
                        }

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
                        for (int i = 0; i < widths.length; i++) {
                            // 下载指定尺寸图
                            LOGGER.debug("==========" + widths[i] + "尺寸的图片地址：" + upConf.getUrlPath() + prefix + suffix + "!" + widths[i]);
                            imgMap.put(widths[i] + "", upConf.getUrlPath() + prefix + suffix + "!" + widths[i]);
                        }
                        // 不启用又拍云
                    } else {
                        YunBean yb = new YunBean();
                        yb.setBucketName(upConf.getBucketName());
                        yb.setPassword(upConf.getPassWord());
                        yb.setUserName(upConf.getUserName());
                        UpYunUtil.yunUp(fileNamess, prefix, yb, suffix);
                        String s = OSinfo.getOSname().toString();
                        String pa = "";
                        if (LINUX.equals(s)) {
                            pa = "/";
                        } else if (WINDOWS.equals(s)) {
                            pa = "\\";
                        }
                        // 下载原图
                        imgMap.put(OLDIMG, fastdfs.getResultPath() + Upload.uploadToFSD(picPath + pa + prefix + suffix, fastdfs));
                        List<ImageSet> imgSet = sysHelperMapper.selectImageSet();
                        int[] widths = new int[imgSet.size()];
                        for (int i = 0; i < imgSet.size(); i++) {
                            widths[i] = Integer.parseInt(imgSet.get(i).getRuleWidth());
                        }

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
                        for (int i = 0; i < widths.length; i++) {
                            DownLoad.download(upConf.getUrlPath() + prefix + suffix, prefix + "_" + widths[i] + suffix, picPath, widths[i] + "");
                            // 下载指定尺寸图
                            imgMap.put(widths[i] + "", fastdfs.getResultPath() + Upload.uploadToFSD(picPath + pa + prefix + suffix, fastdfs));
                        }
                    }
                }
            } catch (IllegalStateException e) {
                LOGGER.error(LOGGERINFO5, e);
            } catch (IOException e) {
                LOGGER.error(LOGGERINFO5, e);
            } catch (Exception e) {
                LOGGER.error(LOGGERINFO5, e);
            }
            return imgMap;
        } else {
            return null;
        }
    }

    /**
     * Kindeditor富文本框图片上传
     * 
     * @param item
     * @param request
     * @return
     */
    public static String uploadFileOneByFile(FileItem item, HttpServletRequest request) {
        UpyunConf upConf = sysHelperMapper.selectUpyunConf();

        if (item != null) {
            // 检查上传的图片的大小和扩展名
            if (!checkFile(item)) {
                throw new RuntimeException(LOGGERINFO1);
            }

            long now = System.currentTimeMillis();
            // 根据系统时间生成上传后保存的文件名
            String prefix = String.valueOf(now);
            // 保存图片路径
            UploadUtilBack uu = new UploadUtilBack();
            String picPath = uu.readValue().getProperty(IMAGEPATH) + UtilDate.todayFormatString(new Date()) + "/";
            // 根据真实路径创建目录文件
            File picSaveFile = new File(picPath);
            if (!picSaveFile.exists()) {
                picSaveFile.mkdirs();
            }
            // 文件的后缀
            String suffix = ".jpg";
            String fileNamess = picPath + prefix + suffix;

            String mysqlPath = uu.readValue().getProperty("MYSQLPATH");
            String saveBase = "";
            File file = new File(fileNamess);
            try {
                item.write(file);
                if (upConf != null) {
                    // 启用又拍云
                    String fastUserd = fastDFSService.getFastDFSInfoByCurr().getUserd();
                    if ("0".equals(fastUserd)) {
                        YunBean yb = new YunBean();
                        yb.setBucketName(upConf.getBucketName());
                        yb.setPassword(upConf.getPassWord());
                        yb.setUserName(upConf.getUserName());
                        UpYunUtil.yunUp(fileNamess, prefix, yb, suffix);
                        // 下载原图
                        LOGGER.debug(LOGGERINFO2 + upConf.getUrlPath() + prefix + suffix);
                        saveBase = upConf.getUrlPath() + prefix + suffix;

                        // 不启用又拍云
                    } else {
                        YunBean yb = new YunBean();
                        yb.setBucketName(upConf.getBucketName());
                        yb.setPassword(upConf.getPassWord());
                        yb.setUserName(upConf.getUserName());
                        UpYunUtil.yunUp(fileNamess, prefix, yb, suffix);
                        String s = OSinfo.getOSname().toString();
                        String pa = "";
                        if (LINUX.equals(s)) {
                            pa = "/";
                        } else if (WINDOWS.equals(s)) {
                            pa = "\\";
                        }
                        // 下载原图
                        saveBase = mysqlPath + Upload.uploadToFSD(picPath + pa + prefix + suffix);
                    }
                }
            } catch (IllegalStateException e) {
                LOGGER.error(LOGGERINFO6, e);
            } catch (IOException e) {
                LOGGER.error(LOGGERINFO6, e);
            } catch (Exception e) {
                LOGGER.error(LOGGERINFO6, e);
            }
            return saveBase;
        } else {
            return "";
        }
    }

    /**
     * 验证上传图片的大小和扩展名,SpringMVC上传
     * 
     * @param muFile
     * @return
     */
    private static boolean checkFileForSpringUpload(MultipartFile muFile) {
        boolean bool = true;
        // 检查文件大小
        if (muFile.getSize() > maxSize) {
            LOGGER.error("=============>上传文件大小超过限制");
            bool = false;
        }
        String fileName = muFile.getOriginalFilename();
        // 检查扩展名
        String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        if (!Arrays.<String> asList(extMap.get(IMAGE).split(",")).contains(fileExt)) {
            LOGGER.error("上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(IMAGE) + "格式。");
            bool = false;
        }
        return bool;
    }

    /**
     * 验证上传图片的大小和扩展名,普通上传
     * 
     * @param file
     * @return
     */
    private static boolean checkFile(FileItem file) {
        boolean bool = true;
        // 检查文件大小
        if (file.getSize() > maxSize) {
            LOGGER.error("=============>上传文件大小超过限制");
            bool = false;
        }
        String fileName = file.getName();
        // 检查扩展名
        String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        if (!Arrays.<String> asList(extMap.get(IMAGE).split(",")).contains(fileExt)) {
            LOGGER.error("上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(IMAGE) + "格式。");
            bool = false;
        }
        return bool;
    }

    /**
     * 读取配置文件信息
     * 
     * @return
     */
    public Properties readValue() {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("com/ningpai/web/config/upload.properties");
        Properties p = new Properties();
        try {
            p.load(inputStream);
        } catch (IOException e1) {
            LOGGER.error("读取Properties属性文件错误：=>", e1);
        }
        return p;
    }

    /**
     * 获取SERVICE-FastDFS设置信息
     * 
     * @return
     */
    public FastDFSInfoService getFastDFSService() {
        return fastDFSService;
    }

    /**
     * 设置SERVICE-FastDFS设置信息
     * 
     * @param fastDFSService
     */
    @Resource(name = "FastDFSInfoService")
    public void setFastDFSService(FastDFSInfoService fastDFSService) {
        UploadUtilBack.fastDFSService = fastDFSService;
    }

    /**
     * SysHelperMapper 初始化方法
     */

    @PostConstruct
    public void init() {
        sysHelperMapper = this.sysHelperMapperNext;
    }

    /**
     * 获取SysHelperMapper对象
     * 
     * @return
     */
    public SysHelperMapper getSysHelperMapperNext() {
        return sysHelperMapperNext;
    }

    /**
     * 设置SysHelperMapper对象
     * 
     * @param sysHelperMapperNext
     */
    @Resource(name = "SysHelperMapper")
    public void setSysHelperMapperNext(SysHelperMapper sysHelperMapperNext) {
        this.sysHelperMapperNext = sysHelperMapperNext;
    }

    static {
        extMap.put(IMAGE, "gif,jpg,jpeg,png,bmp,ico");
    }

}
