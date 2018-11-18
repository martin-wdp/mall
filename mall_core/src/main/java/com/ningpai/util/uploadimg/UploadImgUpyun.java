/*
 *
 * Copyright 2010-2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.util.uploadimg;

import com.ningpai.manager.bean.UpyunConf;
import com.ningpai.manager.mapper.SysHelperMapper;
import com.ningpai.util.MyLogger;
import com.upyun.UpYunUtil;
import com.upyun.YunBean;
import org.apache.commons.fileupload.FileItem;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 图片上传-又拍云
 * 
 * @author NINGPAI-zhangqiang
 * @since 2013年12月30日 下午2:24:18
 * @version 1.0
 */
@Component("UploadImgUpyun")
public class UploadImgUpyun {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(UploadImgUpyun.class);

    private static final int SMALL = 56;

    private static final String OLDIMG = "oldimg";
    private static final String LOGGERINFO1 = "==========原图地址：";
    private static final String LOGGERINFO2 = "==========";
    private static final String LOGGERINFO3 = "尺寸的图片地址：";
    private static final String LOGGERINFO4 = "图片上传，返回key值列表错误：=>";

    @Resource(name = "SysHelperMapper")
    private SysHelperMapper sysHelperMapper;

    /**
     * 返回大、中、小、原图
     * 
     * @param muFile
     * @return Map集合，其中有4个值，oldimg是原图，0：小图、1：中图、2：大图
     */
    public Map<String, String> uploadForABCSize(MultipartFile muFile, UpyunConf upConf) {
        Map<String, String> imgMap = new HashMap<String, String>();
        if (muFile != null && muFile.getSize() != 0) {
            // 是否需要java处理
            @SuppressWarnings("unused")
            boolean flag = false;

            // 获取上传文件的保存名称、服务器本地保存路径、后缀，拼接成文件地址
            String fileNamess = UploadImgCommon.getPicNamePathSuffix();

            File file = new File(fileNamess);
            try {
                muFile.transferTo(file);
                if (upConf != null) {
                    YunBean yb = new YunBean();
                    yb.setBucketName(upConf.getBucketName());
                    yb.setPassword(upConf.getPassWord());
                    yb.setUserName(upConf.getUserName());
                    UpYunUtil.yunUp(fileNamess, UploadImgCommon.prefix, yb, UploadImgCommon.suffix);
                    // 获取原图地址
                    LOGGER.debug(LOGGERINFO1 + upConf.getUrlPath() + UploadImgCommon.prefix + UploadImgCommon.suffix);
                    imgMap.put(OLDIMG, upConf.getUrlPath() + UploadImgCommon.prefix + UploadImgCommon.suffix);

                    // 获取宽度集合，去除85这个宽度
                    int[] widths = UploadImgCommon.getImgSetOut85(sysHelperMapper.selectImageSet());
                    UploadImgCommon.sortWidth(widths);
                    for (int i = 0; i < widths.length; i++) {
                        // 获取指定尺寸图片地址
                        LOGGER.debug(LOGGERINFO2 + widths[i] + LOGGERINFO3 + upConf.getUrlPath() + UploadImgCommon.prefix + UploadImgCommon.suffix + "!" + widths[i]);
                        imgMap.put(i + "", upConf.getUrlPath() + UploadImgCommon.prefix + UploadImgCommon.suffix + "!" + widths[i]);
                    }

                } else {
                    flag = true;
                }
            } catch (IllegalStateException e) {
                LOGGER.error(LOGGERINFO4, e);
                flag = true;
            } catch (IOException e) {
                LOGGER.error(LOGGERINFO4, e);
                flag = true;
            } catch (Exception e) {
                LOGGER.error(LOGGERINFO4, e);
                flag = true;
            }
            return imgMap;
        } else {
            return null;
        }
    }

    /**
     * 返回所有数据库中保存大小的图片、原图
     * 
     * @param muFile
     * @return Map集合，key值为图片宽度值
     */
    public Map<String, String> uploadForAllSize(MultipartFile muFile, UpyunConf upConf) {
        Map<String, String> imgMap = new HashMap<String, String>();
        if (muFile != null && muFile.getSize() != 0) {
            // 是否需要java处理
            @SuppressWarnings("unused")
            boolean flag = false;

            // 获取上传文件的保存名称、服务器本地保存路径、后缀，拼接成文件地址
            String fileNamess = UploadImgCommon.getPicNamePathSuffix();

            File file = new File(fileNamess);
            try {
                muFile.transferTo(file);
                if (upConf != null) {
                    YunBean yb = new YunBean();
                    yb.setBucketName(upConf.getBucketName());
                    yb.setPassword(upConf.getPassWord());
                    yb.setUserName(upConf.getUserName());
                    UpYunUtil.yunUp(fileNamess, UploadImgCommon.prefix, yb, UploadImgCommon.suffix);
                    // 获取原图地址
                    LOGGER.debug(LOGGERINFO1 + upConf.getUrlPath() + UploadImgCommon.prefix + UploadImgCommon.suffix);
                    imgMap.put(OLDIMG, upConf.getUrlPath() + UploadImgCommon.prefix + UploadImgCommon.suffix);

                    // 获取宽度集合
                    int[] widths = UploadImgCommon.getImgSet(sysHelperMapper.selectImageSet());
                    UploadImgCommon.sortWidth(widths);
                    for (int i = 0; i < widths.length; i++) {
                        // 获取指定尺寸图片地址
                        LOGGER.debug(LOGGERINFO2 + widths[i] + LOGGERINFO3 + upConf.getUrlPath() + UploadImgCommon.prefix + UploadImgCommon.suffix + "!" + widths[i]);
                        imgMap.put(widths[i] + "", upConf.getUrlPath() + UploadImgCommon.prefix + UploadImgCommon.suffix + "!" + widths[i]);
                    }

                } else {
                    flag = true;
                }
            } catch (IllegalStateException e) {
                LOGGER.error(LOGGERINFO4, e);
                flag = true;
            } catch (IOException e) {
                LOGGER.error(LOGGERINFO4, e);
                flag = true;
            } catch (Exception e) {
                LOGGER.error(LOGGERINFO4, e);
                flag = true;
            }
            return imgMap;
        } else {
            return null;
        }
    }

    /**
     * 返回原图、小图
     * 
     * @param muFile
     * @return Map集合，其中有两个值oldimg是原图，small是小图
     */
    public Map<String, String> uploadForOldAndSmall(MultipartFile muFile, UpyunConf upConf) {
        Map<String, String> imgMap = new HashMap<String, String>();
        if (muFile != null && muFile.getSize() != 0) {
            // 是否需要java处理
            @SuppressWarnings("unused")
            boolean flag = false;

            // 获取上传文件的保存名称、服务器本地保存路径、后缀，拼接成文件地址
            String fileNamess = UploadImgCommon.getPicNamePathSuffix();

            File file = new File(fileNamess);
            try {
                muFile.transferTo(file);
                if (upConf != null) {
                    YunBean yb = new YunBean();
                    yb.setBucketName(upConf.getBucketName());
                    yb.setPassword(upConf.getPassWord());
                    yb.setUserName(upConf.getUserName());
                    UpYunUtil.yunUp(fileNamess, UploadImgCommon.prefix, yb, UploadImgCommon.suffix);
                    // 获取原图地址
                    LOGGER.debug(LOGGERINFO1 + upConf.getUrlPath() + UploadImgCommon.prefix + UploadImgCommon.suffix);
                    imgMap.put(OLDIMG, upConf.getUrlPath() + UploadImgCommon.prefix + UploadImgCommon.suffix);

                    // 获取小图地址
                    LOGGER.debug("==========56尺寸的图片地址：" + upConf.getUrlPath() + UploadImgCommon.prefix + UploadImgCommon.suffix + "!" + SMALL);
                    imgMap.put("small", upConf.getUrlPath() + UploadImgCommon.prefix + UploadImgCommon.suffix + "!" + SMALL);

                } else {
                    flag = true;
                }
            } catch (IllegalStateException e) {
                LOGGER.error(LOGGERINFO4, e);
                flag = true;
            } catch (IOException e) {
                LOGGER.error(LOGGERINFO4, e);
                flag = true;
            } catch (Exception e) {
                LOGGER.error(LOGGERINFO4, e);
                flag = true;
            }
            return imgMap;
        } else {
            return null;
        }
    }

    /**
     * 返回原图，用于富文本框
     * 
     * @param item
     * @return 原图地址
     */
    public String uploadForRichEdit(FileItem item, UpyunConf upConf) {
        String resoult = null;
        if (item != null && item.getSize() != 0) {
            // 是否需要java处理
            @SuppressWarnings("unused")
            boolean flag = false;

            // 获取上传文件的保存名称、服务器本地保存路径、后缀，拼接成文件地址
            String fileNamess = UploadImgCommon.getPicNamePathSuffix();

            File file = new File(fileNamess);
            try {
                item.write(file);
                if (upConf != null) {
                    YunBean yb = new YunBean();
                    yb.setBucketName(upConf.getBucketName());
                    yb.setPassword(upConf.getPassWord());
                    yb.setUserName(upConf.getUserName());
                    UpYunUtil.yunUp(fileNamess, UploadImgCommon.prefix, yb, UploadImgCommon.suffix);
                    // 获取原图地址
                    LOGGER.debug(LOGGERINFO1 + upConf.getUrlPath() + UploadImgCommon.prefix + UploadImgCommon.suffix);
                    resoult = upConf.getUrlPath() + UploadImgCommon.prefix + UploadImgCommon.suffix;
                } else {
                    flag = true;
                }
            } catch (IllegalStateException e) {
                LOGGER.error(LOGGERINFO4, e);
                flag = true;
            } catch (IOException e) {
                LOGGER.error(LOGGERINFO4, e);
                flag = true;
            } catch (Exception e) {
                LOGGER.error(LOGGERINFO4, e);
                flag = true;
            }
            return resoult;
        } else {
            return null;
        }
    }

    /**
     * 测试又拍云，返回原图、小图
     *
     * @param muFile
     * @return Map集合，其中有两个值oldimg是原图，small是小图
     */
    public Map<String, String> testUpYun(MultipartFile muFile, UpyunConf upConf) throws IOException {
        Map<String, String> imgMap = new HashMap<String, String>();
        if (muFile != null && muFile.getSize() != 0) {
            // 获取上传文件的保存名称、服务器本地保存路径、后缀，拼接成文件地址
            String fileNamess = UploadImgCommon.getPicNamePathSuffix();
            File file = new File(fileNamess);
            muFile.transferTo(file);
            if (upConf != null) {
                YunBean yb = new YunBean();
                yb.setBucketName(upConf.getBucketName());
                yb.setPassword(upConf.getPassWord());
                yb.setUserName(upConf.getUserName());
                KUpYunUtil.kYunUp(fileNamess, UploadImgCommon.prefix, yb, UploadImgCommon.suffix);
                // 获取原图地址
                LOGGER.debug(LOGGERINFO1 + upConf.getUrlPath() + UploadImgCommon.prefix + UploadImgCommon.suffix);
                imgMap.put(OLDIMG, upConf.getUrlPath() + UploadImgCommon.prefix + UploadImgCommon.suffix);

                // 获取宽度集合
                int[] widths = UploadImgCommon.getImgSet(sysHelperMapper.selectImageSet());
                UploadImgCommon.sortWidth(widths);
                for (int i = 0; i < widths.length; i++) {
                    // 获取指定尺寸图片地址
                    LOGGER.debug(LOGGERINFO2 + widths[i] + LOGGERINFO3 + upConf.getUrlPath() + UploadImgCommon.prefix + UploadImgCommon.suffix + "!" + widths[i]);
                    imgMap.put(widths[i] + "", upConf.getUrlPath() + UploadImgCommon.prefix + UploadImgCommon.suffix + "!" + widths[i]);
                }
            }
            return imgMap;
        } else {
            return null;
        }
    }

}
