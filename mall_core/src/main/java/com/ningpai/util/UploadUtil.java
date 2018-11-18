/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.util;

import com.ningpai.fastdfs.bean.FastDFSInfo;
import com.ningpai.fastdfs.service.FastDFSInfoService;
import com.ningpai.manager.bean.UpyunConf;
import com.ningpai.manager.mapper.SysHelperMapper;
import com.ningpai.util.uploadimg.UploadImgFastDFS;
import com.ningpai.util.uploadimg.UploadImgJava;
import com.ningpai.util.uploadimg.UploadImgUpyun;
import org.apache.commons.fileupload.FileItem;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ggn 图片上传工具类
 */
@Controller
public class UploadUtil {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(UploadUtil.class);

    private static final String LOGGERINFO1 = "的图片不符合限制";
    private static final String IMAGE = "image";

    /** 帮助中心DAO */
    private static SysHelperMapper sysHelperMapper;

    /** 帮助中心DAO */
    private SysHelperMapper sysHelperMapperNext;

    /** 最大文件大小 */
    private static long maxSize = 10 * 1024 * 1024;

    /** 定义允许上传的文件扩展名 */
    private static HashMap<String, String> extMap = new HashMap<String, String>();

    /**
     * FastDFSInfoService 接口服务类
     */
    private static FastDFSInfoService fastDFSService;

    /**
     * 又拍云上传工具类
     */
    private static UploadImgUpyun uploadImgUpyun;

    /**
     * 保存到FastDFS工具类
     */
    private static UploadImgFastDFS uploadImgFastDFS;

    /** Java上传工具类 */
    private static UploadImgJava uploadImgJava;

    /**
     * 初始化帮助中心DAO
     */
    @PostConstruct
    public void init() {
        sysHelperMapper = this.sysHelperMapperNext;
    }

    /**
     * 图片上传，返回key值列表
     * 
     * @param muFile
     *            图片
     * @param request
     *            HttpServletRequest
     * @return Map 返回中 key值{0,1,2,oldimg} 0小图 1中图 2大图 oldimg 原图
     */
    public static synchronized Map<String, String> uploadFile(MultipartFile muFile, HttpServletRequest request) {
        Map<String, String> map = new HashMap<String, String>();
        UpyunConf upConf = sysHelperMapper.selectUpyunConf();
        FastDFSInfo fastdfs = fastDFSService.getFastDFSInfoByCurr();

        if (muFile != null && muFile.getSize() != 0) {
            // 检查上传的图片的大小和扩展名
            if (!checkFileForSpringUpload(muFile)) {
                throw new RuntimeException("上传" + muFile.getOriginalFilename() + LOGGERINFO1);
            }
            // 又拍云处理
            if (upConf != null && "1".equals(upConf.getUsedStatus())) {
                // 又拍云保存
                if (null == fastdfs || "0".equals(fastdfs.getUserd())) {
                    map = uploadImgUpyun.uploadForABCSize(muFile, upConf);
                } else {
                    // FastDFS存储
                    map = uploadImgUpyun.uploadForABCSize(muFile, upConf);
                    map = uploadImgFastDFS.saveToFastDFS(map, fastdfs);
                }
            } else {
                // Java处理
                // 本地服务器保存
                if (null == fastdfs || "0".equals(fastdfs.getUserd())) {
                    map = uploadImgJava.uploadForABCSize(muFile);
                } else {
                    // FastDFS存储
                    map = uploadImgJava.uploadForABCSizeSaveFastDFS(muFile);
                    map = uploadImgFastDFS.saveToFastDFS(map, fastdfs);
                }
            }

            return map;
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
     *            HttpServletRequest
     * @return String
     */
    public static synchronized String uploadFileOne(MultipartFile muFile, HttpServletRequest request) {
        Map<String, String> map = new HashMap<String, String>();
        UpyunConf upConf = sysHelperMapper.selectUpyunConf();
        FastDFSInfo fastdfs = fastDFSService.getFastDFSInfoByCurr();
        if (muFile != null && muFile.getSize() != 0) {
            // 检查上传的图片的大小和扩展名
            if (!checkFileForSpringUpload(muFile)) {
                throw new RuntimeException("上传" + muFile.getOriginalFilename() + LOGGERINFO1);
            }
            // 又拍云处理
            if (upConf != null && "1".equals(upConf.getUsedStatus())) {
                // 又拍云保存
                if (null == fastdfs || "0".equals(fastdfs.getUserd())) {
                    map = uploadImgUpyun.uploadForOldAndSmall(muFile, upConf);
                } else {
                    // FastDFS存储
                    map = uploadImgUpyun.uploadForOldAndSmall(muFile, upConf);
                    map = uploadImgFastDFS.saveToFastDFS(map, fastdfs);
                }
            } else {
                // Java处理
                // 本地服务器保存
                if (null == fastdfs || "0".equals(fastdfs.getUserd())) {
                    map = uploadImgJava.uploadForOldAndSmall(muFile);
                } else {
                    // FastDFS存储
                   // map = uploadImgJava.uploadForOldAndSmallSaveFastDFS(muFile);
                    map = uploadImgJava.uploadForABCSizeSaveFastDFS(muFile);
                    map = uploadImgFastDFS.saveToFastDFS(map, fastdfs);
                }
            }
            return map.get("oldimg");
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
     *            HttpServletRequest
     * @return String
     */
    public static synchronized String uploadFileCustomerHeadOne(MultipartFile muFile, HttpServletRequest request) {
        Map<String, String> map = new HashMap<String, String>();
        UpyunConf upConf = sysHelperMapper.selectUpyunConf();
        FastDFSInfo fastdfs = fastDFSService.getFastDFSInfoByCurr();
        if (muFile != null && muFile.getSize() != 0) {
            // 检查上传的图片的大小和扩展名
            if (!checkFileForSpringUpload(muFile)) {
                throw new RuntimeException("上传" + muFile.getOriginalFilename() + LOGGERINFO1);
            }
            // 又拍云处理
            if (upConf != null && "1".equals(upConf.getUsedStatus())) {
                // 又拍云保存
                if (null == fastdfs || "0".equals(fastdfs.getUserd())) {
                    map = uploadImgUpyun.uploadForOldAndSmall(muFile, upConf);
                } else {
                    // FastDFS存储
                    map = uploadImgUpyun.uploadForOldAndSmall(muFile, upConf);
                    map = uploadImgFastDFS.saveToFastDFS(map, fastdfs);
                }
            } else {
                // Java处理
                // 本地服务器保存
                if (null == fastdfs || "0".equals(fastdfs.getUserd())) {
                    map = uploadImgJava.uploadForOldAndSmall(muFile);
                } else {
                    // FastDFS存储
                    map = uploadImgJava.uploadForABCSizeSaveFastDFS(muFile);
                    map = uploadImgFastDFS.saveToFastDFS(map, fastdfs);
                }
            }
            return map.get("oldimg");
        } else {
            return null;
        }
    }

    /**
     * 图片上传,保存多张图片，key值为宽度
     * 
     * @param muFile
     *            图片
     * @param request
     *            HttpServletRequest
     * @return Map 返回中 key值{width...,oldimg} oldimg 原图
     */
    public static synchronized Map<String, String> uploadFileByWidth(MultipartFile muFile, HttpServletRequest request) {
        Map<String, String> map = new HashMap<String, String>();
        UpyunConf upConf = sysHelperMapper.selectUpyunConf();
        FastDFSInfo fastdfs = fastDFSService.getFastDFSInfoByCurr();
        if (muFile != null && muFile.getSize() != 0) {
            // 检查上传的图片的大小和扩展名
            if (!checkFileForSpringUpload(muFile)) {
                throw new RuntimeException("上传" + muFile.getOriginalFilename() + LOGGERINFO1);
            }
            // 又拍云处理
            if (upConf != null && "1".equals(upConf.getUsedStatus())) {
                // 又拍云保存
                if (null == fastdfs || "0".equals(fastdfs.getUserd())) {
                    map = uploadImgUpyun.uploadForAllSize(muFile, upConf);
                } else {
                    // FastDFS存储
                    map = uploadImgUpyun.uploadForAllSize(muFile, upConf);
                    map = uploadImgFastDFS.saveToFastDFS(map, fastdfs);
                }
            } else {
                // Java处理
                // 本地服务器保存
                if (null == fastdfs || "0".equals(fastdfs.getUserd())) {
                    map = uploadImgJava.uploadForAllSize(muFile);
                } else {
                    // FastDFS存储
                    map = uploadImgJava.uploadForABCSizeSaveFastDFSWidth(muFile);
                    map = uploadImgFastDFS.saveToFastDFS(map, fastdfs);
                }
            }
            return map;
        } else {
            return null;
        }
    }

    /**
     * Kindeditor富文本框图片上传
     * 
     * @param item
     *            FileItem
     * @param request
     *            HttpServletRequest
     * @return 图片地址
     */
    public static synchronized String uploadFileOneByFile(FileItem item, HttpServletRequest request) {
        String resoult = "";
        UpyunConf upConf = sysHelperMapper.selectUpyunConf();
        FastDFSInfo fastdfs = fastDFSService.getFastDFSInfoByCurr();
        if (item != null && item.getSize() != 0) {
            // 检查上传的图片的大小和扩展名
            if (!checkFile(item)) {
                throw new RuntimeException("上传的图片不符合限制");
            }
            // 又拍云处理
            if (upConf != null && "1".equals(upConf.getUsedStatus())) {
                // 又拍云保存
                if (null == fastdfs || "0".equals(fastdfs.getUserd())) {
                    resoult = uploadImgUpyun.uploadForRichEdit(item, upConf);
                } else {
                    // FastDFS存储
                    resoult = uploadImgUpyun.uploadForRichEdit(item, upConf);
                    resoult = uploadImgFastDFS.saveToFastDFS(resoult, fastdfs);
                }
            } else {
                // Java处理
                // 本地服务器保存
                if (null == fastdfs || "0".equals(fastdfs.getUserd())) {
                    resoult = uploadImgJava.uploadForRichEdit(item);
                } else {
                    // FastDFS存储
                    resoult = uploadImgJava.uploadForRichEditSaveFastDFS(item);
                    resoult = uploadImgFastDFS.saveToFastDFS(resoult, fastdfs);
                }
            }
            return resoult;
        } else {
            return null;
        }
    }

    /**
     * Kindeditor富文本框图片上传
     * 
     * @param item
     *            FileItem
     * @param request
     *            HttpServletRequest
     * @return 图片地址
     */
    public static synchronized String uploadFileOneByFileSite(FileItem item, HttpServletRequest request) {
        String resoult = "";
        UpyunConf upConf = sysHelperMapper.selectUpyunConf();
        FastDFSInfo fastdfs = fastDFSService.getFastDFSInfoByCurr();
        if (item != null && item.getSize() != 0) {
            // 检查上传的图片的大小和扩展名
            if (!checkFile(item)) {
                throw new RuntimeException("上传的图片不符合限制");
            }
            // 又拍云处理
            if (upConf != null && "1".equals(upConf.getUsedStatus())) {
                // 又拍云保存
                if (null == fastdfs || "0".equals(fastdfs.getUserd())) {
                    resoult = uploadImgUpyun.uploadForRichEdit(item, upConf);
                } else {
                    // FastDFS存储
                    resoult = uploadImgUpyun.uploadForRichEdit(item, upConf);
                    resoult = uploadImgFastDFS.saveToFastDFSSite(resoult, fastdfs);
                }
            } else {
                // Java处理
                // 本地服务器保存
                if (null == fastdfs || "0".equals(fastdfs.getUserd())) {
                    resoult = uploadImgJava.uploadForRichEdit(item);
                } else {
                    // FastDFS存储
                    resoult = uploadImgJava.uploadForRichEditSaveFastDFS(item);
                    resoult = uploadImgFastDFS.saveToFastDFS(resoult, fastdfs);
                }
            }
            return resoult;
        } else {
            return null;
        }
    }

    /**
     * 验证上传图片的大小和扩展名,SpringMVC上传
     * 
     * @param muFile
     *            MultipartFile
     * @return 文件是否符合规定
     */
    private static boolean checkFileForSpringUpload(MultipartFile muFile) {
        boolean bool = true;
        // 检查文件大小
        if (muFile.getSize() > maxSize) {
            LOGGER.error("=============>上传" + muFile.getOriginalFilename() + "文件大小超过限制");
            bool = false;
        }
        String fileName = muFile.getOriginalFilename();
        // 检查扩展名
        String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        if (!Arrays.<String> asList(extMap.get(IMAGE).split(",")).contains(fileExt)) {
            LOGGER.error("上传文件" + muFile.getOriginalFilename() + "扩展名是不允许的扩展名。\n只允许" + extMap.get(IMAGE) + "格式。");
            bool = false;
        }
        return bool;
    }

    /**
     * 测试又拍云
     * 
     * @param muFile
     *            MultipartFile
     * @param request
     *            HttpServletRequest
     * @return 又拍云图片地址Map
     * @throws IOException
     *             上传过程抛出的异常
     */
    public static synchronized Map<String, String> testUpYun(MultipartFile muFile, HttpServletRequest request) throws IOException {
        Map<String, String> map = new HashMap<String, String>();
        UpyunConf upConf = sysHelperMapper.selectUpyunConf();
        if (muFile != null && muFile.getSize() != 0) {
            // 检查上传的图片的大小和扩展名
            if (!checkFileForSpringUpload(muFile)) {
                throw new RuntimeException("上传" + muFile.getOriginalFilename() + LOGGERINFO1);
            }
            // 又拍云处理
            if (upConf != null) {
                // 又拍云保存
                map = uploadImgUpyun.testUpYun(muFile, upConf);
            }
            return map;
        } else {
            return null;
        }
    }

    /**
     * 验证上传图片的大小和扩展名,普通上传
     * 
     * @param file
     *            FileItem
     * @return 验证通过返回true，否则返回false
     */
    private static boolean checkFile(FileItem file) {
        boolean bool = true;
        // 检查文件大小
        if (file.getSize() > maxSize) {
            LOGGER.error("=============>上传" + file.getFieldName() + "文件大小超过限制");
            bool = false;
        }
        String fileName = file.getName();
        // 检查扩展名
        String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        if (!Arrays.<String> asList(extMap.get(IMAGE).split(",")).contains(fileExt)) {
            LOGGER.error("上传文件" + file.getFieldName() + "扩展名是不允许的扩展名。\n只允许" + extMap.get(IMAGE) + "格式。");
            bool = false;
        }
        return bool;
    }

    /**
     * 获取FastDFSInfoService接口服务类
     * 
     * @return FastDFSInfoService
     */
    public FastDFSInfoService getFastDFSService() {
        return fastDFSService;
    }

    /**
     * spring注入FastDFSInfoService
     * 
     * @param fastDFSService
     *            FastDFSInfoService
     */
    @Resource(name = "FastDFSInfoService")
    public void setFastDFSService(FastDFSInfoService fastDFSService) {
        UploadUtil.fastDFSService = fastDFSService;
    }

    /**
     * 获取UploadImgUpyun
     * 
     * @return UploadImgUpyun
     */
    public UploadImgUpyun getUploadImgUpyun() {
        return uploadImgUpyun;
    }

    /**
     * spring注入UploadImgUpyun
     * 
     * @param uploadImgUpyun
     *            要注入的UploadImgUpyun
     */
    @Resource(name = "UploadImgUpyun")
    public void setUploadImgUpyun(UploadImgUpyun uploadImgUpyun) {
        UploadUtil.uploadImgUpyun = uploadImgUpyun;
    }

    /**
     * 获取UploadImgFastDFS
     * 
     * @return UploadImgFastDFS
     */
    public UploadImgFastDFS getUploadImgFastDFS() {
        return uploadImgFastDFS;
    }

    /**
     * spring 注入uploadImgFastDFS
     * 
     * @param uploadImgFastDFS
     *            要注入的uploadImgFastDFS
     */
    @Resource(name = "UploadImgFastDFS")
    public void setUploadImgFastDFS(UploadImgFastDFS uploadImgFastDFS) {
        UploadUtil.uploadImgFastDFS = uploadImgFastDFS;
    }

    /**
     * 获取UploadImgJava
     * 
     * @return UploadImgJava
     */
    public UploadImgJava getUploadImgJava() {
        return uploadImgJava;
    }

    /**
     * spring注入UploadImgJava
     * 
     * @param uploadImgJava
     *            要注入的UploadImgJava
     */
    @Resource(name = "UploadImgJava")
    public void setUploadImgJava(UploadImgJava uploadImgJava) {
        UploadUtil.uploadImgJava = uploadImgJava;
    }

    /**
     * 获取帮助中心DAO
     * 
     * @return
     */
    public SysHelperMapper getSysHelperMapperNext() {
        return sysHelperMapperNext;
    }

    /**
     * spring 注入SysHelperMapper
     * 
     * @param sysHelperMapperNext
     */
    @Resource(name = "SysHelperMapper")
    public void setSysHelperMapperNext(SysHelperMapper sysHelperMapperNext) {
        this.sysHelperMapperNext = sysHelperMapperNext;
    }

    // 初始化
    static {
        extMap.put(IMAGE, "gif,jpg,jpeg,png,bmp,ico");
    }
}
