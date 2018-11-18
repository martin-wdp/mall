/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.imagemanage.controller;

import com.ningpai.api.util.JsonUtil;
import com.ningpai.imagemanage.bean.InfoImageManage;
import com.ningpai.imagemanage.service.InfoImageManageService;
import com.ningpai.manager.bean.UpyunConf;
import com.ningpai.manager.mapper.SysHelperMapper;
import com.ningpai.publicpackage.InfoImageManagePublic;
import com.ningpai.uploadfileset.bean.UploadFileSet;
import com.ningpai.uploadfileset.service.UploadFileSetService;
import com.ningpai.util.MyLogger;
import com.ningpai.util.UploadUtil;
import net.sf.json.JSON;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 图片上传控制器
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年9月2日下午3:44:07
 */
@Controller
public class ManageUploadImgController {

    private static final MyLogger LOGGER = new MyLogger(ManageUploadImgController.class);

    private static final String THIRDID = "thirdId";

    private static final String OLDIMG = "oldimg";

    private static SysHelperMapper sysHelperMapper;

    private SysHelperMapper sysHelperMapperNext;

    /** 资讯图片管理接口 */
    @Resource(name = "InfoImageManagePublic")
    private InfoImageManagePublic infoImageManagePublic;

    /** SERVICE-上传文件设置类 */
    @Resource(name = "UploadFileSetService")
    private UploadFileSetService uploadFileSetService;

    /** SERVICE-资源图片信息 */
    @Resource(name = "InfoImageManageService")
    private InfoImageManageService infoImageManageService;

    /**
     * 初始化
     */
    @PostConstruct
    public void init() {
        sysHelperMapper = this.sysHelperMapperNext;
    }

    public SysHelperMapper getSysHelperMapperNext() {
        return sysHelperMapperNext;
    }

    @Resource(name = "SysHelperMapper")
    public void setSysHelperMapperNext(SysHelperMapper sysHelperMapperNext) {
        this.sysHelperMapperNext = sysHelperMapperNext;
    }

    /**
     * 图片上传，图片管理上传
     * 
     * @param request
     *            包含图片文件的请求
     * @param resp
     *            HttpServletResponse
     * @return String 原图路径
     */
    @RequestMapping("/uploadFileOneForManage")
    public void uploadFileOneForManage(MultipartHttpServletRequest request, HttpServletResponse resp) {
        PrintWriter out = null;
        try {
            /** 又拍云 */
            UpyunConf upConf = sysHelperMapper.selectUpyunConf();
            try {
                out = resp.getWriter();
            } catch (final IOException e) {
                LOGGER.error("创建PrintWriter失败", e);
            }
            /** 定义字符串类型变量imgurl */
            String imgurl = "";
            /** 定义一个String类型的变量imageMap */
            Map<String, String> imageMap = null;
            /** 获取name为file的文件资源 */
            List<MultipartFile> muFiles = request.getFiles("file");
            /** 如果获取的file不为null并且size大于0 */
            if (null != muFiles && !muFiles.isEmpty()) {
                /** 如果获取的file的size等于1 */
                if (muFiles.size() == 1) {
                    int c = checkFile(muFiles.get(0));
                    if (c == 1) {
                        // imgurl = UploadUtil.uploadFileOne(muFiles.get(0),
                        // request);
                        /** 调用图片上传方法 */
                        imageMap = UploadUtil.uploadFile(muFiles.get(0), request);

                        /** new一个图片资源实体类 */
                        InfoImageManage infoImageManage = new InfoImageManage();
                        /**
                         * 通过session获取第三方Id 判断是否为null 如果不为null
                         */
                        if (request.getSession().getAttribute(THIRDID) != null) {
                            /** 设置图片资源的第三方Id为获取的thirdId */
                            infoImageManage.setThirdId((Long) request.getSession().getAttribute(THIRDID));
                        }
                        /** 设置图片资源实体类的图片图片地址 */
                        infoImageManage.setImageManageUrl(imageMap.get(OLDIMG));
                        /** 判断又拍云是否开启 */
                        if (upConf != null && "1".equals(upConf.getUsedStatus())) {
                            /** 设置图片资源实体类的大图地址 */
                            infoImageManage.setBigImgUrl(imageMap.get("2"));
                            /** 设置图片资源实体类的中图地址 */
                            infoImageManage.setMiddleImgUrl(imageMap.get("1"));
                            /** 设置图片资源实体类的小图地址 */
                            infoImageManage.setSmallImgUrl(imageMap.get("0"));
                        } else {
                            /** 设置图片资源实体类的大图地址 */
                            infoImageManage.setBigImgUrl(imageMap.get("0"));
                            /** 设置图片资源实体类的中图地址 */
                            infoImageManage.setMiddleImgUrl(imageMap.get("2"));
                            /** 设置图片资源实体类的小图地址 */
                            infoImageManage.setSmallImgUrl(imageMap.get("1"));
                        }

                        /** 设置图片资源实体类的上传时间 */
                        infoImageManage.setImageOnlineDate(new Date());
                        /** 保存图片资源信息 */
                        infoImageManageService.saveInfoImageManage(infoImageManage);
                        imgurl = imageMap.get(OLDIMG);
                    } else if (c == -1) {
                        imgurl = "-1";
                    } else if (c == -2) {
                        imgurl = "-2";
                    } else if (c == -3) {
                        imgurl = "-3";
                    }
                } else {
                    for (MultipartFile muFile : muFiles) {
                        int c = checkFile(muFile);
                        if (c == 1) {
                            // String url = UploadUtil.uploadFileOne(muFile,
                            // request);
                            /** 上传图片 */
                            imageMap = UploadUtil.uploadFile(muFile, request);
                            /** 获取地址 */
                            String url = imageMap.get(OLDIMG);
                            /** 声明一个资源图片实体类的对象 */
                            InfoImageManage infoImageManage = new InfoImageManage();
                            /** 如果session中的第三方ID不为null */
                            if (request.getSession().getAttribute(THIRDID) != null) {
                                /** 设置图片资源实体类的第三方id */
                                infoImageManage.setThirdId((Long) request.getSession().getAttribute(THIRDID));
                            }
                            /** 设置图片资源实体类的图片图片地址 */
                            infoImageManage.setImageManageUrl(imageMap.get(OLDIMG));
                            /** 判断又拍云是否开启 */
                            if (upConf != null && "1".equals(upConf.getUsedStatus())) {
                                /** 设置图片资源实体类的大图地址 */
                                infoImageManage.setBigImgUrl(imageMap.get("2"));
                                /** 设置图片资源实体类的中图地址 */
                                infoImageManage.setMiddleImgUrl(imageMap.get("1"));
                                /** 设置图片资源实体类的小图地址 */
                                infoImageManage.setSmallImgUrl(imageMap.get("0"));
                            } else {
                                /** 设置图片资源实体类的大图地址 */
                                infoImageManage.setBigImgUrl(imageMap.get("0"));
                                /** 设置图片资源实体类的中图地址 */
                                infoImageManage.setMiddleImgUrl(imageMap.get("2"));
                                /** 设置图片资源实体类的小图地址 */
                                infoImageManage.setSmallImgUrl(imageMap.get("1"));
                            }

                            /** 设置图片资源实体类的上传时间 */
                            infoImageManage.setImageOnlineDate(new Date());
                            /** 执行保存操作 */
                            infoImageManageService.saveInfoImageManage(infoImageManage);
                            imgurl += url + ",";
                        } else if (c == -1) {
                            imgurl = "-1";
                        } else if (c == -2) {
                            imgurl = "-2";
                        } else if (c == -3) {
                            imgurl = "-3";
                        }
                    }
                }
            }
            /** 保存图片信息，以供以后选择 */
            out.append("<script>parent.callback('" + imgurl +"');</script>");
        }
        catch (Exception e){
            out.append("<script>parent.callback('" + e.getMessage() + "');</script>");
        }
    }

    /**
     * 图片上传，图片管理上传 pl 2015.10.21修改
     *
     * @param request
     *            包含图片文件的请求
     * @param resp
     *            HttpServletResponse
     * @return String 原图路径
     */
    @RequestMapping("/uploadFileOneForManage2")
    public void uploadFileOneForManage2(MultipartHttpServletRequest request, HttpServletResponse resp) {
        PrintWriter out = null;
        try {
            /** 又拍云 */
            UpyunConf upConf = sysHelperMapper.selectUpyunConf();
            try {
                out = resp.getWriter();
            } catch (final IOException e) {
                LOGGER.error("创建PrintWriter失败", e);
            }
            /** 定义字符串类型变量imgurl */
            String imgurl = "";
            /** 定义一个String类型的变量imageMap */
            Map<String, String> imageMap = null;
            /** 获取name为file的文件资源 */
            List<MultipartFile> muFiles = request.getFiles("file");
            InfoImageManage infoImageManage = new InfoImageManage();
            /** 如果获取的file不为null并且size大于0 */
            if (null != muFiles && !muFiles.isEmpty()) {
                /** 如果获取的file的size等于1 */
                if (muFiles.size() == 1) {
                    int c = checkFile(muFiles.get(0));
                    if (c == 1) {
                        // imgurl = UploadUtil.uploadFileOne(muFiles.get(0),
                        // request);
                        /** 调用图片上传方法 */
                        imageMap = UploadUtil.uploadFile(muFiles.get(0), request);

                        /** new一个图片资源实体类 */

                        /**
                         * 通过session获取第三方Id 判断是否为null 如果不为null
                         */
                        if (request.getSession().getAttribute(THIRDID) != null) {
                            /** 设置图片资源的第三方Id为获取的thirdId */
                            infoImageManage.setThirdId((Long) request.getSession().getAttribute(THIRDID));
                        }
                        /** 设置图片资源实体类的图片图片地址 */
                        infoImageManage.setImageManageUrl(imageMap.get(OLDIMG));
                        /** 判断又拍云是否开启 */
                        if (upConf != null && "1".equals(upConf.getUsedStatus())) {
                            /** 设置图片资源实体类的大图地址 */
                            infoImageManage.setBigImgUrl(imageMap.get("2"));
                            /** 设置图片资源实体类的中图地址 */
                            infoImageManage.setMiddleImgUrl(imageMap.get("1"));
                            /** 设置图片资源实体类的小图地址 */
                            infoImageManage.setSmallImgUrl(imageMap.get("0"));
                        } else {
                            /** 设置图片资源实体类的大图地址 */
                            infoImageManage.setBigImgUrl(imageMap.get("0"));
                            /** 设置图片资源实体类的中图地址 */
                            infoImageManage.setMiddleImgUrl(imageMap.get("2"));
                            /** 设置图片资源实体类的小图地址 */
                            infoImageManage.setSmallImgUrl(imageMap.get("1"));
                        }

                        /** 设置图片资源实体类的上传时间 */
                        infoImageManage.setImageOnlineDate(new Date());
                        /** 保存图片资源信息 */
                        infoImageManageService.saveInfoImageManage(infoImageManage);
                        imgurl = imageMap.get(OLDIMG);
                    } else if (c == -1) {
                        imgurl = "-1";
                    } else if (c == -2) {
                        imgurl = "-2";
                    } else if (c == -3) {
                        imgurl = "-3";
                    }
                } else {
                    for (MultipartFile muFile : muFiles) {
                        int c = checkFile(muFile);
                        if (c == 1) {
                            // String url = UploadUtil.uploadFileOne(muFile,
                            // request);
                            /** 上传图片 */
                            imageMap = UploadUtil.uploadFile(muFile, request);
                            /** 获取地址 */
                            String url = imageMap.get(OLDIMG);
                            /** 声明一个资源图片实体类的对象 */
                            //InfoImageManage infoImageManage = new InfoImageManage();
                            /** 如果session中的第三方ID不为null */
                            if (request.getSession().getAttribute(THIRDID) != null) {
                                /** 设置图片资源实体类的第三方id */
                                infoImageManage.setThirdId((Long) request.getSession().getAttribute(THIRDID));
                            }
                            /** 设置图片资源实体类的图片图片地址 */
                            infoImageManage.setImageManageUrl(imageMap.get(OLDIMG));
                            /** 判断又拍云是否开启 */
                            if (upConf != null && "1".equals(upConf.getUsedStatus())) {
                                /** 设置图片资源实体类的大图地址 */
                                infoImageManage.setBigImgUrl(imageMap.get("2"));
                                /** 设置图片资源实体类的中图地址 */
                                infoImageManage.setMiddleImgUrl(imageMap.get("1"));
                                /** 设置图片资源实体类的小图地址 */
                                infoImageManage.setSmallImgUrl(imageMap.get("0"));
                            } else {
                                /** 设置图片资源实体类的大图地址 */
                                infoImageManage.setBigImgUrl(imageMap.get("0"));
                                /** 设置图片资源实体类的中图地址 */
                                infoImageManage.setMiddleImgUrl(imageMap.get("2"));
                                /** 设置图片资源实体类的小图地址 */
                                infoImageManage.setSmallImgUrl(imageMap.get("1"));
                            }

                            /** 设置图片资源实体类的上传时间 */
                            infoImageManage.setImageOnlineDate(new Date());
                            /** 执行保存操作 */
                            infoImageManageService.saveInfoImageManage(infoImageManage);
                            imgurl += url + ",";
                        } else if (c == -1) {
                            imgurl = "-1";
                        } else if (c == -2) {
                            imgurl = "-2";
                        } else if (c == -3) {
                            imgurl = "-3";
                        }
                    }
                }
            }
            /** 保存图片信息，以供以后选择 */
            out.append(JsonUtil.getJSONString(infoImageManage));
        }
        catch (Exception e){
            out.append(e.getMessage());
        }
    }

    /**
     * 验证上传的资源
     * 
     * @param muFile
     * @return int
     */
    public int checkFile(MultipartFile muFile) {
        /** 定义一个Int类型的变量 */
        int result = 0;
        /** 获取文件上传设置类信息 */
        UploadFileSet ufs = this.uploadFileSetService.getCurrUploadFileSet();
        /** 定义suffixArray数组，并赋值 */
        String[] suffixArray = ufs.getSuffixArray().split(",");
        /** 如果传入参数muFile不为null并且size大于0 */
        if (null != muFile && muFile.getSize() > 0) {
            /** 如果木File的size小于文件上传设置类的最大size */
            if (muFile.getSize() < ufs.getMaxSize()) {
                /** 获取muFile的名称并赋值给fileName */
                String filename = muFile.getOriginalFilename();
                /** 定义变量sufix */
                String sufix = null;
                try {
                    /** 为sufix赋值 */
                    sufix = filename.substring(filename.lastIndexOf(".") + 1);
                } catch (Exception e) {
                    LOGGER.error("上传的文件没有后缀名!", e);
                    result = -3;
                }

                for (int i = 0; i < suffixArray.length; i++) {
                    if (sufix.equalsIgnoreCase(suffixArray[i])) {
                        result = 1;
                        break;
                    } else {
                        /** 禁止上传的后缀 */
                        result = -3;
                    }
                }

            } else {
                /** 文件过大 */
                result = -2;
            }
        } else {
            /** 没有上传文件 */
            result = -1;
        }
        return result;
    }
}
