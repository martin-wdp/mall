/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.version.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ningpai.util.PageBean;
import com.ningpai.util.UtilDate;
import com.ningpai.version.bean.Version;
import com.ningpai.version.mapper.VersionMapper;
import com.ningpai.version.service.VersionService;
import com.ningpai.version.util.CommonConstant;

/**
 * @see com.ningpai.version.service.VersionService
 * @author NINGPAI-zhangqiang
 * @since 2014年6月26日 上午10:32:41
 * @version 0.0.1
 */
@Service("versionService")
public class VersionServiceImpl implements VersionService {
    // 属性注入
    private VersionMapper versionMapper;

    /*
     * 查询版本信息
     * 
     * @see com.ningpai.version.service.VersionService#seleceVersion()
     */
    @Override
    public List<Version> seleceVersion() {
        return versionMapper.seleceVersion();
    }

    /*
     * 查询所有版本 分页
     * 
     * @see com.ningpai.version.service.VersionService#selectAllConsult(com.ningpai .util.PageBean, com.ningpai.version.bean.Version)
     */
    @Override
    public PageBean selectAllVersion(PageBean pageBean, Version version) {
        Map<String, Object> paramMap = null;
        try {
            Long count = versionMapper.selectVersionSize(version);
            pageBean.setRows(Integer.parseInt(count == null ? "0" : count.toString()));
            if (pageBean.getPageNo() > pageBean.getLastPageNo()) {
                pageBean.setPageNo(pageBean.getLastPageNo());
            }
            if (pageBean.getPageNo() == 0) {
                pageBean.setPageNo(1);
            }
            paramMap = new HashMap<String, Object>();
            paramMap.put("version", version);
            paramMap.put(CommonConstant.STARTNUM, pageBean.getStartRowNum());
            paramMap.put(CommonConstant.ENDNUM, pageBean.getEndRowNum());
            pageBean.setList(versionMapper.selectAllVersion(paramMap));
        } finally {
            // 置空对象
            paramMap = null;
        }
        return pageBean;
    }

    /*
     * 增加版本信息
     * 
     * @see com.ningpai.version.service.VersionService#addVersion(com.ningpai.version .bean.Version)
     */
    @Override
    public int addVersion(Version version, String date) {
        version.setVersionTime(UtilDate.stringToDate(date));
        return versionMapper.insertSelective(version);
    }

    /*
     * 修改版本信息
     * 
     * @see com.ningpai.version.service.VersionService#updateVersion(com.ningpai. version.bean.Version, java.lang.String)
     */
    @Override
    public int updateVersion(Version version, String date) {
        version.setVersionTime(UtilDate.stringToDate(date));
        return versionMapper.updateByPrimaryKeySelective(version);
    }

    /*
     * 查询版本信息
     * 
     * @see com.ningpai.version.service.VersionService#showVersion(java.lang.Long)
     */
    @Override
    public Version showVersion(Long versionId) {
        return versionMapper.selectByPrimaryKey(versionId);
    }

    /*
     * 查询最新版本
     * 
     * @see com.ningpai.version.service.VersionService#showNewVersion()
     */
    @Override
    public Version showNewVersion() {
        return versionMapper.showNewVersion();
    }

    public VersionMapper getVersionMapper() {
        return versionMapper;
    }

    @Resource(name = "versionMapper")
    public void setVersionMapper(VersionMapper versionMapper) {
        this.versionMapper = versionMapper;
    }

}
