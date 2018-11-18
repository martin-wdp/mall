/**
 * Copyright 2014 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.app.dao.impl;

import com.ningpai.app.bean.AppAuth;
import com.ningpai.app.dao.AppAuthMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**  
 * @Description: np_app_auth的dao的实现类:
 * @author Ningpai-HEHU
 * @date 2015-07-17 10:28:52
 * @version V1.0  
 */
@Repository("AppAuthMapper") 
public class AppAuthMapperImpl extends com.ningpai.manager.base.BasicSqlSupport implements AppAuthMapper {

    /*
    * (non-Javadoc)
    * @see com.ningpai.app.dao.AppAuthMapper#deleteByPrimaryKey(java.lang.Long)
    */
    @Override
    public int deleteByPrimaryKey(Long appAuthId) {
        return this.update("com.ningpai.mybatis.mapper.AppAuthMapper.deleteByPrimaryKey", appAuthId);
    }

    /*
    * (non-Javadoc)
    * @see com.ningpai.app.dao.AppAuthMapper#insertSelective(com.ningpai.app.bean.AppAuth)
    */
    @Override
    public int insertSelective(AppAuth record) {
        return this.insert("com.ningpai.mybatis.mapper.AppAuthMapper.insertSelective", record);
    }

    /*
    * (non-Javadoc)
    * @see com.ningpai.app.dao.AppAuthMapper#selectByPrimaryKey(java.lang.Long)
    */
    @Override
    public AppAuth selectByPrimaryKey(Long appAuthId) {
        return this.selectOne("com.ningpai.mybatis.mapper.AppAuthMapper.selectByPrimaryKey", appAuthId);
    }

    /*
    * (non-Javadoc)
    * @see com.ningpai.app.dao.AppAuthMapper#updateByPrimaryKeySelective(com.ningpai.app.bean.AppAuth)
    */
    @Override
    public int updateByPrimaryKeySelective(AppAuth record) {
        return this.update("com.ningpai.mybatis.mapper.AppAuthMapper.updateByPrimaryKeySelective", record);
    }

    /*
    * (non-Javadoc)
    * @see com.ningpai.app.dao.AppAuthMapper#deleteMuilti(java.lang.Long appAuthIds)
    */
    @Override
    public int deleteMuilti(Long[] appAuthIds) {
        return this.update("com.ningpai.mybatis.mapper.AppAuthMapper.deleteMuilti", appAuthIds);
    }

    /**
     * 根据网站地址，查询最新的秘钥
     *
     * @param map 参数包括网站地址
     * @return 秘钥信息
     */
    @Override
    public AppAuth selectLatestByParams(Map<String, Object> map) {
        return this.selectOne("com.ningpai.mybatis.mapper.AppAuthMapper.selectLatestByParams", map);
    }

    /*
    * (non-Javadoc)
    * @see com.ningpai.app.dao.AppAuthMapper#selectList(java.util.Map<String,Object> map)
    */
    @Override
    public List<Object> selectList(Map<String,Object> map) {
        return this.selectList("com.ningpai.mybatis.mapper.AppAuthMapper.selectList", map);
    }

    /*
    * (non-Javadoc)
    * @see com.ningpai.app.dao.AppAuthMapper#selectListCount(java.util.Map<String,Object> map)
    */
    @Override
    public Integer selectListCount(Map<String,Object> map) {
        return this.selectOne("com.ningpai.mybatis.mapper.AppAuthMapper.selectListCount", map);
    }
}