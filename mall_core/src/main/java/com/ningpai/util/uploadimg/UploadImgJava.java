/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 *
 */
package com.ningpai.util.uploadimg;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.coobird.thumbnailator.Thumbnails;

import org.apache.commons.fileupload.FileItem;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.ningpai.manager.bean.ImageSet;
import com.ningpai.manager.mapper.SysHelperMapper;
import com.ningpai.util.MyLogger;
import com.ningpai.util.UtilDate;

/**
 * 图片上传-JAVA
 *
 * @author NINGPAI-WangHaiYang
 * @since 2014年8月27日上午10:05:44
 */
@Component("UploadImgJava")
public class UploadImgJava {

    /**
     * 记录日志对象
     */
    private static final MyLogger LOGGER = new MyLogger(UploadImgJava.class);

    private static final String OLDIMG = "oldimg";
    private static final String _SMALL = "small";
    private static final String LOGGERINFO1 = "原图地址：";
    private static final String LOGGERINFO2 = "尺寸的图片地址：";
    private static final String LOGGERINFO3 = "图片上传，返回key值列表错误：=>";
    private static final String LOGGERINFO4 = "==========原图地址：";
    private static final String LOGGERINFO5 = "==========";

    private static final int SMALL = 56;

    /**
     * 项目url
     */
    private static String url;

    @Resource(name = "SysHelperMapper")
    private SysHelperMapper sysHelperMapper;

    /**
     * 按照大、中、小、原图处理图片
     *
     * @param muFile
     * @return Map集合，其中有4个值，oldimg是原图，0：小图、1：中图、2：大图
     */
    public Map<String, String> uploadForABCSize(MultipartFile muFile) {
        Map<String, String> imgMap = new HashMap<String, String>();
        if (muFile != null && muFile.getSize() != 0) {
            // 服务器本地保存图片路径
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            // 获取项目url
            url = UploadImgCommon.getProjectURL(request);
            String baisc = request.getSession().getServletContext().getRealPath("/");
            // 获取上传文件的保存名称、服务器本地保存路径、后缀，拼接成文件地址
            String fileNamess = getPicNamePathSuffix(baisc);

            File file = new File(baisc + fileNamess);
            try {
                muFile.transferTo(file);
                // 获取原图地址
                LOGGER.info(LOGGERINFO1 + url + fileNamess);
                imgMap.put(OLDIMG, url + fileNamess);

                // 处理图片
                List<ImageSet> imgSet = sysHelperMapper.selectImageSet();
                for (int i = 0; i < imgSet.size(); i++) {
                    String width = imgSet.get(i).getRuleWidth();
                    // String height = imgSet.get(i).getRuleHeight();
                    if ("85".equals(width)) {
                        if (i < 3) {
                            i--;
                        }
                        continue;
                    }
                    // 处理图片，并返回保存地址
                    // String resoultPath = getResoultPath(baisc, baisc +
                    // fileNamess, width, height);
                    // 获取指定尺寸图片地址
                    LOGGER.info(width + LOGGERINFO2 + url + UploadImgCommon.picPath + UploadImgCommon.prefix + "!" + width + UploadImgCommon.suffix);
                    imgMap.put(i + "", url + UploadImgCommon.picPath + UploadImgCommon.prefix + "!" + width + UploadImgCommon.suffix);
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
     * 按照大、中、小、原图处理图片
     *
     * @param muFile
     * @return Map集合，其中有4个值，oldimg是原图，0：小图、1：中图、2：大图
     */
    public Map<String, String> uploadForABCSizeSaveFastDFS(MultipartFile muFile) {
        Map<String, String> imgMap = new HashMap<String, String>();
        if (muFile != null && muFile.getSize() != 0) {
            // 服务器本地保存图片路径
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            // 获取项目url
            String url = UploadImgCommon.getProjectURL(request);
            String baisc = request.getSession().getServletContext().getRealPath("/");
            // 获取上传文件的保存名称、服务器本地保存路径、后缀，拼接成文件地址
            String fileNamess = getPicNamePathSuffix(baisc);

            File file = new File(baisc + fileNamess);
            try {
                muFile.transferTo(file);
                // 获取原图地址
                LOGGER.info(LOGGERINFO1 + url + fileNamess);
                imgMap.put(OLDIMG, url + fileNamess);

                // 处理图片
                List<ImageSet> imgSet = sysHelperMapper.selectImageSet();
                for (int i = 0; i < imgSet.size(); i++) {
                    String width = imgSet.get(i).getRuleWidth();
                    // String height = imgSet.get(i).getRuleHeight();
                    if ("85".equals(width)) {
                        if (i < 3) {
                            i--;
                        }
                        continue;
                    }
                    // 处理图片，并返回保存地址
                    // String resoultPath = getResoultPath(baisc, baisc
                    // +fileNamess, width, height);
                    // 获取指定尺寸图片地址
                    LOGGER.info(width + LOGGERINFO2 + url + UploadImgCommon.picPath + UploadImgCommon.prefix + UploadImgCommon.suffix);

                    imgMap.put(i + "", url + UploadImgCommon.picPath + UploadImgCommon.prefix + UploadImgCommon.suffix);
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
     * 按照图片宽度 原图处理图片
     *
     * @param muFile
     * @return Map集合，key值为图片宽度值
     */
    public Map<String, String> uploadForABCSizeSaveFastDFSWidth(MultipartFile muFile) {
        Map<String, String> imgMap = new HashMap<String, String>();
        if (muFile != null && muFile.getSize() != 0) {
            // 服务器本地保存图片路径
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            // 获取项目url
            String url = UploadImgCommon.getProjectURL(request);
            String baisc = request.getSession().getServletContext().getRealPath("/");
            // 获取上传文件的保存名称、服务器本地保存路径、后缀，拼接成文件地址
            String fileNamess = getPicNamePathSuffix(baisc);

            File file = new File(baisc + fileNamess);
            try {
                muFile.transferTo(file);
                // 获取原图地址
                LOGGER.info(LOGGERINFO1 + url + fileNamess);
                imgMap.put(OLDIMG, url + fileNamess);

                // 处理图片
                List<ImageSet> imgSet = sysHelperMapper.selectImageSet();
                for (int i = 0; i < imgSet.size(); i++) {
                    String width = imgSet.get(i).getRuleWidth();
                    // String height = imgSet.get(i).getRuleHeight();
                    if ("85".equals(width)) {
                        if (i < 3) {
                            i--;
                        }
                        continue;
                    }
                    // 处理图片，并返回保存地址
                    // String resoultPath = getResoultPath(baisc, baisc
                    // +fileNamess, width, height);
                    // 获取指定尺寸图片地址
                    LOGGER.info(width + LOGGERINFO2 + url + UploadImgCommon.picPath + UploadImgCommon.prefix + "!" + width + UploadImgCommon.suffix);
                    imgMap.put(width, url + UploadImgCommon.picPath + UploadImgCommon.prefix + "!" + width + UploadImgCommon.suffix);
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
     * 返回所有数据库中保存大小的图片、原图
     *
     * @param muFile
     * @return Map集合，key值为图片宽度值
     */
    public Map<String, String> uploadForAllSize(MultipartFile muFile) {
        Map<String, String> imgMap = new HashMap<String, String>();
        if (muFile != null && muFile.getSize() != 0) {
            // 服务器本地保存图片路径
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            // 获取项目url
            url = UploadImgCommon.getProjectURL(request);
            String baisc = request.getSession().getServletContext().getRealPath("/");
            // 获取上传文件的保存名称、服务器本地保存路径、后缀，拼接成文件地址
            String fileNamess = getPicNamePathSuffix(baisc);

            File file = new File(baisc + fileNamess);
            try {
                muFile.transferTo(file);
                // 获取原图地址
                LOGGER.info(LOGGERINFO1 + url + fileNamess);
                imgMap.put(OLDIMG, url + fileNamess);

                // 处理图片
                List<ImageSet> imgSet = sysHelperMapper.selectImageSet();
                for (int i = 0; i < imgSet.size(); i++) {
                    String width = imgSet.get(i).getRuleWidth();
                    String height = imgSet.get(i).getRuleHeight();
                    // 处理图片，并返回保存地址
                    String resoultPath = getResoultPath(baisc, baisc + fileNamess, width, height);
                    // 获取指定尺寸图片地址
                    LOGGER.info(width + LOGGERINFO2 + url + resoultPath);
                    imgMap.put(width, url + resoultPath);
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
     * 返回所有数据库中保存大小的图片、原图
     *
     * @param muFile
     * @return Map集合，key值为图片宽度值
     */
    public Map<String, String> uploadForAllSizeSaveFastDFS(MultipartFile muFile) {
        Map<String, String> imgMap = new HashMap<String, String>();
        if (muFile != null && muFile.getSize() != 0) {
            // 服务器本地保存图片路径
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String baisc = request.getSession().getServletContext().getRealPath("/");
            // 获取上传文件的保存名称、服务器本地保存路径、后缀，拼接成文件地址
            String fileNamess = getPicNamePathSuffix(baisc);

            File file = new File(baisc + fileNamess);
            try {
                muFile.transferTo(file);
                // 获取原图地址
                LOGGER.debug(LOGGERINFO4 + baisc + fileNamess);
                imgMap.put(OLDIMG, baisc + fileNamess);

                // 处理图片
                List<ImageSet> imgSet = sysHelperMapper.selectImageSet();
                for (int i = 0; i < imgSet.size(); i++) {
                    String width = imgSet.get(i).getRuleWidth();
                    String height = imgSet.get(i).getRuleHeight();
                    // 处理图片，并返回保存地址
                    String resoultPath = getResoultPath(baisc, baisc + fileNamess, width, height);
                    // 获取指定尺寸图片地址
                    LOGGER.debug(LOGGERINFO5 + width + LOGGERINFO2 + baisc + resoultPath);
                    imgMap.put(width, baisc + resoultPath);
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
     * 按照原图、小图处理图片
     *
     * @param muFile
     * @return Map集合，其中有两个值Worked是按指定宽高处理的图，small是小图
     */
    public Map<String, String> uploadForOldAndSmall(MultipartFile muFile) {
        Map<String, String> imgMap = new HashMap<String, String>();
        if (muFile != null && muFile.getSize() != 0) {

            // 服务器本地保存图片路径
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            // 获取项目url
            url = UploadImgCommon.getProjectURL(request);
            String baisc = request.getSession().getServletContext().getRealPath("/");
            // 获取上传文件的保存名称、服务器本地保存路径、后缀，拼接成文件地址
            String fileNamess = getPicNamePathSuffix(baisc);

            File file = new File(baisc + fileNamess);
            try {
                muFile.transferTo(file);
                // 获取原图地址
                LOGGER.debug(LOGGERINFO4 + url + fileNamess);
                imgMap.put(OLDIMG, url + fileNamess);

                // 处理图片，并返回保存地址
                String widthAndHidth = String.valueOf(SMALL);
                String resoultPath = getResoultPath(baisc, baisc + fileNamess, widthAndHidth, widthAndHidth);
                // 获取小图片地址
                LOGGER.debug(LOGGERINFO5 + SMALL + LOGGERINFO2 + url + resoultPath);
                imgMap.put(_SMALL, url + resoultPath);

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
     * 按照原图、小图处理图片
     *
     * @param muFile
     * @return Map集合，其中有两个值Worked是按指定宽高处理的图，small是小图
     */
    public Map<String, String> uploadForOldAndSmallSaveFastDFS(MultipartFile muFile) {
        Map<String, String> imgMap = new HashMap<String, String>();
        if (muFile != null && muFile.getSize() != 0) {

            // 服务器本地保存图片路径
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String baisc = request.getSession().getServletContext().getRealPath("/");
            /*String baisc =  request.getScheme() + "://"
                    + request.getServerName() + ":" + request.getServerPort()+request.getContextPath();*/
            // 获取上传文件的保存名称、服务器本地保存路径、后缀，拼接成文件地址
            String fileNamess = getPicNamePathSuffix(baisc);

            File file = new File(baisc + fileNamess);
            try {
                muFile.transferTo(file);
                // 获取原图地址
                LOGGER.debug(LOGGERINFO4 + baisc + fileNamess);
                imgMap.put(OLDIMG, baisc + fileNamess);

                // 处理图片，并返回保存地址
                String widthAndHidth = String.valueOf(SMALL);
                String resoultPath = getResoultPath(baisc, baisc + fileNamess, widthAndHidth, widthAndHidth);
                // 获取小图片地址
                LOGGER.debug(LOGGERINFO5 + SMALL + LOGGERINFO2 + baisc + resoultPath);
                imgMap.put(_SMALL, resoultPath);

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
     * 按照指定宽高、小图处理图片
     *
     * @param muFile
     * @param width
     * @param height
     * @return Map集合，其中有两个值Worked是按指定宽高处理的图，small是小图
     */
    public Map<String, String> uploadForWorkedAndSmall(MultipartFile muFile, String width, String height) {
        Map<String, String> imgMap = new HashMap<String, String>();
        if (muFile != null && muFile.getSize() != 0) {
            // 服务器本地保存图片路径
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            // 获取项目url
            url = UploadImgCommon.getProjectURL(request);
            String baisc = request.getSession().getServletContext().getRealPath("/");
            // 获取上传文件的保存名称、服务器本地保存路径、后缀，拼接成文件地址
            String fileNamess = getPicNamePathSuffix(baisc);

            File file = new File(baisc + fileNamess);
            try {
                muFile.transferTo(file);
                // 根据指定宽高对原图处理,并替换原图
                try {
                    Thumbnails.of(fileNamess).size(Integer.valueOf(width), Integer.valueOf(height)).keepAspectRatio(false).toFile(fileNamess);
                } catch (IOException e) {
                    LOGGER.error(e.getLocalizedMessage(), e);
                }
                // 获取原图地址
                LOGGER.debug(LOGGERINFO4 + url + fileNamess);
                imgMap.put("worked", url + fileNamess);

                // 处理图片，并返回保存地址
                String widthAndHidth = String.valueOf(SMALL);
                String resoultPath = getResoultPath(baisc, baisc + fileNamess, widthAndHidth, widthAndHidth);
                // 获取小图地址
                LOGGER.debug(LOGGERINFO5 + SMALL + LOGGERINFO2 + url + resoultPath);
                imgMap.put(_SMALL, url + resoultPath);

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
     * 按照指定宽高、小图处理图片
     *
     * @param muFile
     * @param width
     * @param height
     * @return Map集合，其中有两个值Worked是按指定宽高处理的图，small是小图
     */
    public Map<String, String> uploadForWorkedAndSmallSaveFastDFS(MultipartFile muFile, String width, String height) {
        Map<String, String> imgMap = new HashMap<String, String>();
        if (muFile != null && muFile.getSize() != 0) {
            // 服务器本地保存图片路径
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String baisc = request.getSession().getServletContext().getRealPath("/");
            // 获取上传文件的保存名称、服务器本地保存路径、后缀，拼接成文件地址
            String fileNamess = getPicNamePathSuffix(baisc);

            File file = new File(baisc + fileNamess);
            try {
                muFile.transferTo(file);
                // 根据指定宽高对原图处理,并替换原图
                try {
                    Thumbnails.of(fileNamess).size(Integer.valueOf(width), Integer.valueOf(height)).keepAspectRatio(false).toFile(fileNamess);
                } catch (IOException e) {
                    LOGGER.error(e.getLocalizedMessage(), e);
                }
                // 获取原图地址
                LOGGER.debug(LOGGERINFO4 + baisc + fileNamess);
                imgMap.put("worked", baisc + fileNamess);

                // 处理图片，并返回保存地址
                String widthAndHidth = String.valueOf(SMALL);
                String resoultPath = getResoultPath(baisc, baisc + fileNamess, widthAndHidth, widthAndHidth);
                // 获取小图地址
                LOGGER.debug(LOGGERINFO5 + SMALL + LOGGERINFO2 + baisc + resoultPath);
                imgMap.put(_SMALL, baisc + resoultPath);

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
     * 返回原图，用于富文本框
     *
     * @param item
     * @return 原图地址
     */
    public String uploadForRichEdit(FileItem item) {

        if (item != null && item.getSize() != 0) {
            // 服务器本地保存图片路径
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            // 获取项目url
            url = UploadImgCommon.getProjectURL(request);
            String baisc = request.getSession().getServletContext().getRealPath("/");
            // 获取上传文件的保存名称、服务器本地保存路径、后缀，拼接成文件地址
            String fileNamess = getPicNamePathSuffix(baisc);

            File file = new File(baisc + fileNamess);
            try {
                item.write(file);

            } catch (IllegalStateException e) {
                LOGGER.error(LOGGERINFO3, e);
            } catch (IOException e) {
                LOGGER.error(LOGGERINFO3, e);
            } catch (Exception e) {
                LOGGER.error(LOGGERINFO3, e);
            }
            return url + fileNamess;
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
    public String uploadForRichEditSaveFastDFS(FileItem item) {

        if (item != null && item.getSize() != 0) {
            // 服务器本地保存图片路径
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String baisc = request.getSession().getServletContext().getRealPath("/");
            // 获取上传文件的保存名称、服务器本地保存路径、后缀，拼接成文件地址
            String fileNamess = getPicNamePathSuffix(baisc);

            File file = new File(baisc + fileNamess);
            try {
                item.write(file);

            } catch (IllegalStateException e) {
                LOGGER.error(LOGGERINFO3, e);
            } catch (IOException e) {
                LOGGER.error(LOGGERINFO3, e);
            } catch (Exception e) {
                LOGGER.error(LOGGERINFO3, e);
            }
            return baisc + fileNamess;
        } else {
            return null;
        }
    }

    /**
     * 处理图片，并返回保存地址
     *
     * @param fileNamess
     * @param width
     * @param height
     * @return
     */
    private String getResoultPath(String baisc, String fileNamess, String width, String height) {
        // 处理后保存的图片地址
        String resoultPath = baisc + UploadImgCommon.picPath + UploadImgCommon.prefix + "!" + width + UploadImgCommon.suffix;
        try {
            Thumbnails.of(fileNamess).size(Integer.valueOf(width), Integer.valueOf(height)).keepAspectRatio(false).toFile(resoultPath);
        } catch (IOException e) {
            LOGGER.error(e.getLocalizedMessage(), e);
        }
        return resoultPath;
    }

    /**
     * 获取上传文件的保存名称、服务器本地保存路径、后缀，拼接成文件地址
     *
     * @return
     */
    public static String getPicNamePathSuffix(String baisc) {
        // 根据系统时间生成上传后保存的文件名
        long now = System.currentTimeMillis();
        UploadImgCommon.prefix = String.valueOf(now);

        UploadImgCommon.picPath = UploadImgCommon.readValue().getProperty("IMAGEPATH") + UtilDate.todayFormatString(new Date()) + "/";

        // 根据真实路径创建目录文件
        File picSaveFile = new File(baisc + UploadImgCommon.picPath);
        if (!picSaveFile.exists()) {
            try {
                picSaveFile.mkdirs();
            } catch (Exception e) {
                LOGGER.error(e.getLocalizedMessage(), e);
            }
        }
        // 文件的后缀
        UploadImgCommon.suffix = ".jpg";
        return UploadImgCommon.picPath + UploadImgCommon.prefix + UploadImgCommon.suffix;
    }

}
