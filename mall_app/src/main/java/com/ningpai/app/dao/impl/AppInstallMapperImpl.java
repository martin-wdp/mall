/**
 * Copyright 2014 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.app.dao.impl;

import com.ningpai.app.bean.AppInstall;
import com.ningpai.app.dao.AppInstallMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**  
 * @Description: np_app_install的dao的实现类:
 * @author Ningpai-HEHU
 * @date 2015-06-12 11:40:17
 * @version V1.0  
 */
@Repository("AppInstallMapper") 
public class AppInstallMapperImpl extends com.ningpai.manager.base.BasicSqlSupport implements AppInstallMapper {

    /*
    * (non-Javadoc)
    * @see com.ningpai.cloud.dao.AppInstallMapper#deleteByPrimaryKey(java.lang.Long)
    */
    @Override
    public int deleteByPrimaryKey(Long installId) {
        return this.update("com.ningpai.mybatis.mapper.AppInstallMapper.deleteByPrimaryKey", installId);
    }

    /*
    * (non-Javadoc)
    * @see com.ningpai.cloud.dao.AppInstallMapper#insertSelective(com.ningpai.cloud.bean.AppInstall)
    */
    @Override
    public int insertSelective(AppInstall record) {
        return this.insert("com.ningpai.mybatis.mapper.AppInstallMapper.insertSelective", record);
    }

    /*
    * (non-Javadoc)
    * @see com.ningpai.cloud.dao.AppInstallMapper#selectByPrimaryKey(java.lang.Long)
    */
    @Override
    public AppInstall selectByPrimaryKey(Long installId) {
        return this.selectOne("com.ningpai.mybatis.mapper.AppInstallMapper.selectByPrimaryKey", installId);
    }

    /*
    * (non-Javadoc)
    * @see com.ningpai.cloud.dao.AppInstallMapper#updateByPrimaryKeySelective(com.ningpai.cloud.bean.AppInstall)
    */
    @Override
    public int updateByPrimaryKeySelective(AppInstall record) {
        return this.update("com.ningpai.mybatis.mapper.AppInstallMapper.updateByPrimaryKeySelective", record);
    }

    /*
    * (non-Javadoc)
    * @see com.ningpai.cloud.dao.AppInstallMapper#deleteMuilti(java.lang.Long installIds)
    */
    @Override
    public int deleteMuilti(Long[] installIds) {
        return this.update("com.ningpai.mybatis.mapper.AppInstallMapper.deleteMuilti", installIds);
    }

    /*
    * (non-Javadoc)
    * @see com.ningpai.cloud.dao.AppInstallMapper#selectList(java.util.Map<String,Object> map)
    */
    @Override
    public List<Object> selectList(Map<String,Object> map) {
        return this.selectList("com.ningpai.mybatis.mapper.AppInstallMapper.selectList", map);
    }

    /*
    * (non-Javadoc)
    * @see com.ningpai.cloud.dao.AppInstallMapper#selectListCount(java.util.Map<String,Object> map)
    */
    @Override
    public Integer selectListCount(Map<String,Object> map) {
        return this.selectOne("com.ningpai.mybatis.mapper.AppInstallMapper.selectListCount", map);
    }

    @Override
    public AppInstall selectByAppId(Long appId) {
        return this.selectOne("com.ningpai.mybatis.mapper.AppInstallMapper.selectByAppId", appId);
    }

    @Override
    public List<AppInstall> queryAllInstallApps() {
        return this.selectList("com.ningpai.mybatis.mapper.AppInstallMapper.queryAllInstallApps");
    }

    /**
     * 修改应用跟新标记
     *
     * @param appIds 应用id
     */
    @Override
    public void modifyUpdateFlag(List<Long> appIds) {
        this.update("com.ningpai.mybatis.mapper.AppInstallMapper.modifyUpdateFlag",appIds);
    }

    /**
     * 修改应用更新标记为0，表示已更新
     *
     * @param appId 应用id
     */
    @Override
    public void modifyAppUpdateFlag(Long appId) {
        this.update("com.ningpai.mybatis.mapper.AppInstallMapper.modifyAppUpdateFlag",appId);
    }
}