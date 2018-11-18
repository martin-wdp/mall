/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.uploadfileset.service;

import com.ningpai.uploadfileset.bean.UploadFileSet;

/**
 * SERVICE-上传文件设置类
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年8月16日下午4:18:14
 */
public interface UploadFileSetService {
    /**
     * 获取当前的上传文件设置
     * 
     * @return
     */
    UploadFileSet getCurrUploadFileSet();

    /**
     * 修改上传文件设置
     * 
     * @param uploadFileSet
     * @return
     */
    int updateUploadFileSet(UploadFileSet uploadFileSet);
}
