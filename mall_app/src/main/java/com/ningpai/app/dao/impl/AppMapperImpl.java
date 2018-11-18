/**
 * Copyright 2014 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.app.dao.impl;

import com.ningpai.app.bean.App;
import com.ningpai.app.dao.AppMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**  
 * @Description: np_app的dao的实现类:
 * @author Ningpai-HEHU
 * @date 2015-06-10 18:21:40
 * @version V1.0  
 */
@Repository("AppMapper") 
public class AppMapperImpl extends com.ningpai.manager.base.BasicSqlSupport implements AppMapper {

    /*
    * (non-Javadoc)
    * @see com.ningpai.app.dao.AppMapper#deleteByPrimaryKey(java.lang.Long)
    */
    @Override
    public int deleteByPrimaryKey(Long id) {
        return this.update("com.ningpai.mybatis.mapper.AppMapper.deleteByPrimaryKey", id);
    }

    /*
    * (non-Javadoc)
    * @see com.ningpai.app.dao.AppMapper#insertSelective(com.ningpai.app.bean.App)
    */
    @Override
    public int insertSelective(App record) {
        return this.insert("com.ningpai.mybatis.mapper.AppMapper.insertSelective", record);
    }

    /*
    * (non-Javadoc)
    * @see com.ningpai.app.dao.AppMapper#selectByPrimaryKey(java.lang.Long)
    */
    @Override
    public App selectByPrimaryKey(Long id) {
        return this.selectOne("com.ningpai.mybatis.mapper.AppMapper.selectByPrimaryKey", id);
    }

    /*
    * (non-Javadoc)
    * @see com.ningpai.app.dao.AppMapper#updateByPrimaryKeySelective(com.ningpai.app.bean.App)
    */
    @Override
    public int updateByPrimaryKeySelective(App record) {
        return this.update("com.ningpai.mybatis.mapper.AppMapper.updateByPrimaryKeySelective", record);
    }

    /*
    * (non-Javadoc)
    * @see com.ningpai.app.dao.AppMapper#deleteMuilti(java.lang.Long ids)
    */
    @Override
    public int deleteMuilti(Long[] ids) {
        return this.update("com.ningpai.mybatis.mapper.AppMapper.deleteMuilti", ids);
    }

    /*
    * (non-Javadoc)
    * @see com.ningpai.app.dao.AppMapper#selectList(java.util.Map<String,Object> map)
    */
    @Override
    public List<Object> selectList(Map<String,Object> map) {
        return this.selectList("com.ningpai.mybatis.mapper.AppMapper.selectList", map);
    }

    /*
    * (non-Javadoc)
    * @see com.ningpai.app.dao.AppMapper#selectListCount(java.util.Map<String,Object> map)
    */
    @Override
    public Integer selectListCount(Map<String,Object> map) {
        return this.selectOne("com.ningpai.mybatis.mapper.AppMapper.selectListCount", map);
    }

    @Override
    public int checkAppName(String appName) {
        return this.selectOne("com.ningpai.mybatis.mapper.AppMapper.checkAppName", appName);
    }

    @Override
    public Long selectMenuId() {
        return this.selectOne("com.ningpai.mybatis.mapper.AppMapper.selectMenuId");
    }
}