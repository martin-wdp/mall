/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.uploadfileset.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.ningpai.util.PropertieUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ningpai.uploadfileset.bean.UploadFileSet;
import com.ningpai.uploadfileset.service.UploadFileSetService;

/**
 * 控制器-上传文件设置
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年8月16日下午4:44:33
 */
@Controller
public class UploadFileSetController {

    private static final String SUFFIXARRAY = "suffixArray";

    @Resource(name = "UploadFileSetService")
    private UploadFileSetService uploadFileSetService;

    /**
     * 查看上传文件设置
     * 
     * @return
     */
    @ResponseBody
    @RequestMapping("/getUploadFileSet")
    public Map<String, Object> getUploadFileSet() {
        // 获取系统配置信息
        UploadFileSet ufs = this.uploadFileSetService.getCurrUploadFileSet();
        // 设置返回参数
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("maxSize", ufs.getMaxSize());
        String[] suffixArray = ufs.getSuffixArray().split(",");
        map.put(SUFFIXARRAY, suffixArray);
        return map;
    }

    /**
     * 修改上传文件设置
     * 
     * @param maxSize
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateUploadFileSet")
    public Map<String, Object> updateUploadFileSet(Long maxSize, HttpServletRequest request) {
        Properties properties = PropertieUtil.readPropertiesFile(this.getClass().getClassLoader().getResourceAsStream("com/ningpai/web/config/upload.properties"));
        String[] extendNames = properties.get("FILE_EXTEND_NAME").toString().split(",");
        Map<String, Object> map = new HashMap<String, Object>();
        String[] suffixArray = request.getParameterValues("suffixArray[]");
        map.put("maxSize", maxSize);
        map.put(SUFFIXARRAY, suffixArray);
        UploadFileSet ufs = this.uploadFileSetService.getCurrUploadFileSet();
        ufs.setMaxSize(maxSize);
        StringBuilder suffixs = new StringBuilder();
        if (suffixArray != null) {
            for (int i = 0; i < suffixArray.length; i++) {
                boolean isExtendNameValid = false;
                for (String extendName : extendNames) {
                    if (suffixArray[i].equals(extendName)) {
                        isExtendNameValid = true;
                        break;
                    }
                }
                if (!isExtendNameValid) {
                    continue;
                }
                suffixs.append(suffixArray[i] + ",");
            }
            ufs.setSuffixArray(suffixs.toString());
        }
        map.put(SUFFIXARRAY, suffixs.toString().split(","));
        this.uploadFileSetService.updateUploadFileSet(ufs);
        return map;
    }

    /**
     * 修改上传文件设置
     * 
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateUploadFileSetNew")
    public int updateUploadFileSetNew(UploadFileSet ufs, HttpServletRequest request) {
        this.uploadFileSetService.updateUploadFileSet(ufs);
        return 1;
    }
}
