/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.uploadfileset.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ningpai.uploadfileset.bean.UploadFileSet;
import com.ningpai.uploadfileset.dao.UploadFileSetMapper;

/**
 * SERVICE 上传文件设置
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年8月16日下午4:20:53
 */
@Service("UploadFileSetService")
public class UploadFileSetServiceImpl implements UploadFileSetService {

    @Resource(name = "UploadFileSetMapper")
    private UploadFileSetMapper uploadFileSetMapper;

    /*
     * 
     * 
     * @see
     * com.ningpai.uploadfileset.service.UploadFileSetService#getCurrUploadFileSet
     * ()
     */
    @Override
    public UploadFileSet getCurrUploadFileSet() {
        return uploadFileSetMapper.selectUploadfileset();
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.uploadfileset.service.UploadFileSetService#updateUploadFileSet
     * (com.ningpai.uploadfileset.bean.UploadFileSet)
     */
    @Override
    public int updateUploadFileSet(UploadFileSet uploadFileSet) {

        return uploadFileSetMapper.updateByPrimaryKeySelective(uploadFileSet);
    }

}
