/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.fastdfs.service;

import com.ningpai.fastdfs.bean.FastDFSInfo;

/**
 * SERVICE-FastDFS设置信息
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年8月15日上午11:53:23
 */
public interface FastDFSInfoService {
    /**
     * 获取当前使用的FastDFS设置
     * 
     * @return
     */
    FastDFSInfo getFastDFSInfoByCurr();

    /**
     * 修改FastDFS设置
     * 
     * @param fastDFS FastDFS参数信息
     * @return 修改结果
     */
    int updateFastDFSInfo(FastDFSInfo fastDFS);

}
