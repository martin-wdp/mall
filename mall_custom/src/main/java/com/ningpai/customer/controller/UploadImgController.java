/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.customer.controller;

import com.ningpai.customer.service.UploadImgServiceMapper;
import com.ningpai.other.util.CustomerConstantStr;
import com.ningpai.util.UploadUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 修改头像控制层
 * 
 * @author jiping
 * @since 2014年7月22日 下午1:56:00
 * @version 0.0.1
 */
@Controller
public class UploadImgController {

    // spring注入
    private UploadImgServiceMapper uism;

    /**
     * 修改头像
     */
    @RequestMapping("/uploadimg")
    public void uploadShareImg(MultipartHttpServletRequest request, HttpServletResponse resp, Long customerId) throws IOException {
        PrintWriter out = resp.getWriter();
        String msg = null;
        MultipartFile file = request.getFile("shareFile");
        file.getOriginalFilename();
        // 检查文件大小
        if (file.getSize() > CustomerConstantStr.FOUR * CustomerConstantStr.NUM1024 * CustomerConstantStr.NUM1024) {
            msg = "101";
        } else if (!checkExtendsName(file.getOriginalFilename())) {
            msg = "102";
        } else {
            // 图片路径
            String imgSrc = UploadUtil.uploadFileCustomerHeadOne(request.getFile("shareFile"), request);
            msg = imgSrc + "," + customerId;
            uism.uploadImg(customerId, imgSrc);
        }
        out.append("<script>parent.callback('" + msg + "');</script>");
    }

    /**
     * 修改头像
     */
    @RequestMapping("/uploadImageBackUrl")
    @ResponseBody
    public void uploadImageBackUrl(MultipartHttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = null;
        try {
            out = response.getWriter();
            // 待文件上传区
            MultipartFile file = request.getFile("picFile");
            // 若有数据则上传文件
            if (!file.isEmpty()) {
                String picPath = UploadUtil.uploadFileOne(file, request);
                out.append(picPath);
            }
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    /**
     * 检查文件扩展名是否为图片
     * 
     * @param fileName
     *            上传的文件的文件名
     * @return boolean
     */
    private boolean checkExtendsName(String fileName) {
        if (fileName.indexOf(".") < 0) {
            return false;
        }
        String extend = fileName.substring(fileName.lastIndexOf(".") + 1);
        String[] extendNames = { "jpg", "jpeg", "bmp", "png", "gif" };
        for (String extendName : extendNames) {
            if (extend.equals(extendName)) {
                return true;
            }
        }
        return false;
    }

    public UploadImgServiceMapper getUism() {
        return uism;
    }

    @Resource(name = "uploadImgServiceMapper")
    public void setUism(UploadImgServiceMapper uism) {
        this.uism = uism;
    }

}
