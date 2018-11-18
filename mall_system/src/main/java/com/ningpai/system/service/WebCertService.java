/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.system.service;

import java.util.List;

import com.ningpai.system.bean.WebCert;
import com.ningpai.util.PageBean;

/**
 * SERVICE-网站认证
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年6月5日下午4:47:22
 */
public interface WebCertService {
    /**
     * 根据主键删除
     * 
     * @param certificationId
     * @return
     */
    int deleteWebCert(Long certificationId);

    /**
     * 添加
     * 
     * @param record
     * @return
     */
    int saveWebCert(WebCert record);

    /**
     * 修改
     * 
     * @param record
     * @return
     */
    int updateWebCert(WebCert record);

    /**
     * 根据ID查询
     * 
     * @param certificationId
     * @return
     */
    WebCert selectByPrimaryKey(Long certificationId);

    /**
     * 根据分页参数查询认证
     * 
     * @param map
     * @return
     */
    PageBean selectAllByPb(PageBean pb);

    /**
     * 查询所有认证-前台展示用
     * 
     * @return
     */
    List<WebCert> selectAll();
}
