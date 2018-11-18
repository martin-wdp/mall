/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.fastdfs.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ningpai.fastdfs.bean.FastDFSInfo;
import com.ningpai.fastdfs.dao.FastDFSInfoMapper;
import com.ningpai.fastdfs.service.FastDFSInfoService;

/**
 * SERVICE实体类-FastDFS设置信息
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年8月15日上午11:56:18
 */
@Service("FastDFSInfoService")
public class FastDFSInfoServiceImpl implements FastDFSInfoService {

    // Spring注入
    @Resource(name = "FastDFSInfoMapper")
    private FastDFSInfoMapper fastdfsMapper;

    /*
     * 
     * 
     * @see
     * com.ningpai.system.service.FastDFSInfoService#getFastDFSInfoById(java
     * .lang.Long)
     */
    @Override
    public FastDFSInfo getFastDFSInfoByCurr() {
        // 获取FastDfs配置
        return fastdfsMapper.selectAllFastDFS().get(0);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.system.service.FastDFSInfoService#updateFastDFSInfo(com.ningpai
     * .system.bean.FastDFSInfo)
     */
    @Override
    public int updateFastDFSInfo(FastDFSInfo fastDFS) {
        // 修改FastDfs配置
        return fastdfsMapper.updateByPrimaryKeySelective(fastDFS);
    }

}
