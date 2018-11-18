/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.system.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ningpai.system.bean.WebCert;
import com.ningpai.system.dao.WebCertMapper;
import com.ningpai.system.service.WebCertService;
import com.ningpai.util.PageBean;

/**
 * SERVICE实现类-网站认证
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年6月5日下午4:49:19
 */
@Service("WebCertService")
public class WebCertServiceImpl implements WebCertService {

    /** 网站认证数据访问接口 */
    @Resource(name = "WebCertMapper")
    private WebCertMapper webCertMapper;

    /**
     * 根据主键删除
     * 
     * @see com.ningpai.system.service.WebCertService#deleteWebCert(java.lang.Long)
     */
    public int deleteWebCert(Long certificationId) {

        return this.webCertMapper.deleteByPrimaryKey(certificationId);
    }

    /**
     * 添加
     * 
     * @see com.ningpai.system.service.WebCertService#saveWebCert(com.ningpai.system
     *      .bean.WebCert)
     */
    public int saveWebCert(WebCert record) {
        Date date = new Date();
        record.setCreateDate(date);
        record.setUpdateDate(date);
        return this.webCertMapper.insertSelective(record);
    }

    /**
     * 修改
     * 
     * @see com.ningpai.system.service.WebCertService#updateWebCert(com.ningpai.system
     *      .bean.WebCert)
     */
    public int updateWebCert(WebCert record) {
        record.setUpdateDate(new Date());
        return this.webCertMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 根据ID查询
     * 
     * @see com.ningpai.system.service.WebCertService#selectByPrimaryKey(java.lang
     *      .Long)
     */
    public WebCert selectByPrimaryKey(Long certificationId) {

        return this.webCertMapper.selectByPrimaryKey(certificationId);
    }

    /**
     * 根据分页参数查询认证
     * 
     * @see com.ningpai.system.service.WebCertService#selectAllByPb(java.util.Map)
     */
    public PageBean selectAllByPb(PageBean pb) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            pb.setRows(this.webCertMapper.selectCountByPb());
            map.put("startRowNum", pb.getStartRowNum());
            map.put("endRowNum", pb.getEndRowNum());
            pb.setList(this.webCertMapper.selectAllByPb(map));
        } finally {
            map = null;
        }
        return pb;
    }

    /**
     * 查询所有认证-前台展示用
     * 
     * @see com.ningpai.system.service.WebCertService#selectAll()
     */
    public List<WebCert> selectAll() {

        return this.webCertMapper.selectAll();
    }
}
