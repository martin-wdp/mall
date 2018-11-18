/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.customer.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ningpai.customer.dao.UploadImgMapper;

/**
 * 头像上传服务类
 * 
 * @author jiping
 * @since 2014年7月22日 下午1:55:12
 * @version 1.0
 */
@Service("uploadImgServiceMapper")
public class UploadImgServiceMapper {
    // spring注入
    private UploadImgMapper uploadImgMapper;
    /**
     * 下载图片
     * */
    public int uploadImg(Long customerId, String customerImg) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("customerId", customerId);
        paramMap.put("customerImg", customerImg);
        return uploadImgMapper.updateImg(paramMap);
    }

    public UploadImgMapper getUploadImgMapper() {
        return uploadImgMapper;
    }

    @Resource(name = "uploadImgMapper")
    public void setUploadImgMapper(UploadImgMapper uploadImgMapper) {
        this.uploadImgMapper = uploadImgMapper;
    }

}
