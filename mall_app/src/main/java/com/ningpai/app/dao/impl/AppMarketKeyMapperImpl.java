/**
 * Copyright 2014 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.app.dao.impl;

import com.ningpai.app.bean.AppMarketKey;
import com.ningpai.app.dao.AppMarketKeyMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**  
 * @Description: np_app_market_key的dao的实现类:
 * @author Ningpai-HEHU
 * @date 2015-07-17 10:28:52
 * @version V1.0  
 */
@Repository("AppMarketKeyMapper") 
public class AppMarketKeyMapperImpl extends com.ningpai.manager.base.BasicSqlSupport implements AppMarketKeyMapper {

    /*
    * (non-Javadoc)
    * @see com.ningpai.app.dao.AppMarketKeyMapper#deleteByPrimaryKey(java.lang.Long)
    */
    @Override
    public int deleteByPrimaryKey(Long id) {
        return this.update("com.ningpai.mybatis.mapper.AppMarketKeyMapper.deleteByPrimaryKey", id);
    }

    /*
    * (non-Javadoc)
    * @see com.ningpai.app.dao.AppMarketKeyMapper#insertSelective(com.ningpai.app.bean.AppMarketKey)
    */
    @Override
    public int insertSelective(AppMarketKey record) {
        return this.insert("com.ningpai.mybatis.mapper.AppMarketKeyMapper.insertSelective", record);
    }

    /*
    * (non-Javadoc)
    * @see com.ningpai.app.dao.AppMarketKeyMapper#selectByPrimaryKey(java.lang.Long)
    */
    @Override
    public AppMarketKey selectByPrimaryKey(Long id) {
        return this.selectOne("com.ningpai.mybatis.mapper.AppMarketKeyMapper.selectByPrimaryKey", id);
    }

    /*
    * (non-Javadoc)
    * @see com.ningpai.app.dao.AppMarketKeyMapper#updateByPrimaryKeySelective(com.ningpai.app.bean.AppMarketKey)
    */
    @Override
    public int updateByPrimaryKeySelective(AppMarketKey record) {
        return this.update("com.ningpai.mybatis.mapper.AppMarketKeyMapper.updateByPrimaryKeySelective", record);
    }

    /*
    * (non-Javadoc)
    * @see com.ningpai.app.dao.AppMarketKeyMapper#deleteMuilti(java.lang.Long ids)
    */
    @Override
    public int deleteMuilti(Long[] ids) {
        return this.update("com.ningpai.mybatis.mapper.AppMarketKeyMapper.deleteMuilti", ids);
    }

    /*
    * (non-Javadoc)
    * @see com.ningpai.app.dao.AppMarketKeyMapper#selectList(java.util.Map<String,Object> map)
    */
    @Override
    public List<Object> selectList(Map<String,Object> map) {
        return this.selectList("com.ningpai.mybatis.mapper.AppMarketKeyMapper.selectList", map);
    }

    /*
    * (non-Javadoc)
    * @see com.ningpai.app.dao.AppMarketKeyMapper#selectListCount(java.util.Map<String,Object> map)
    */
    @Override
    public Integer selectListCount(Map<String,Object> map) {
        return this.selectOne("com.ningpai.mybatis.mapper.AppMarketKeyMapper.selectListCount", map);
    }
}