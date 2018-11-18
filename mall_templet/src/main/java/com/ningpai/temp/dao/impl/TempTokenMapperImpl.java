/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.temp.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.temp.bean.TempToken;
import com.ningpai.temp.dao.TempTokenMapper;

/**
 * DAO实现类-模板内容变更token
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年8月6日下午8:14:55
 */
@Repository("TempTokenMapper")
public class TempTokenMapperImpl extends BasicSqlSupport implements TempTokenMapper {

    /*
     * 
     * 
     * @see com.ningpai.temp.dao.TempTokenMapper#deleteByPrimaryKey(java.lang.Long)
     */
    @Override
    public int deleteByPrimaryKey(Long tokenId) {

        return this.delete("com.ningpai.temp.dao.TempTokenMapper.deleteByPrimaryKey", tokenId);
    }

    /*
     * 
     * 
     * @see com.ningpai.temp.dao.TempTokenMapper#insert(com.ningpai.temp.bean.TempToken)
     */
    @Override
    public int insert(TempToken record) {

        return this.insert("com.ningpai.temp.dao.TempTokenMapper.insert", record);
    }

    /*
     * 
     * 
     * @see com.ningpai.temp.dao.TempTokenMapper#insertSelective(com.ningpai.temp.bean.TempToken)
     */
    @Override
    public int insertSelective(TempToken record) {

        return this.insert("com.ningpai.temp.dao.TempTokenMapper.insertSelective", record);
    }

    /*
     * 
     * 
     * @see com.ningpai.temp.dao.TempTokenMapper#selectByPrimaryKey(java.lang.Long)
     */
    @Override
    public TempToken selectByPrimaryKey(Long tokenId) {

        return this.selectOne("com.ningpai.temp.dao.TempTokenMapper.selectByPrimaryKey", tokenId);
    }

    /*
     * 
     * 
     * @see com.ningpai.temp.dao.TempTokenMapper#updateByPrimaryKeySelective(com.ningpai.temp.bean.TempToken)
     */
    @Override
    public int updateByPrimaryKeySelective(TempToken record) {

        return this.update("com.ningpai.temp.dao.TempTokenMapper.updateByPrimaryKeySelective", record);
    }

    /*
     * 
     * 
     * @see com.ningpai.temp.dao.TempTokenMapper#updateByPrimaryKey(com.ningpai.temp.bean.TempToken)
     */
    @Override
    public int updateByPrimaryKey(TempToken record) {

        return this.update("com.ningpai.temp.dao.TempTokenMapper.updateByPrimaryKey", record);
    }

    /*
     * 
     * 
     * @see com.ningpai.temp.dao.TempTokenMapper#selectAllToken()
     */
    @Override
    public List<TempToken> selectAllToken() {

        return this.selectList("com.ningpai.temp.dao.TempTokenMapper.selectAllToken");
    }

    /*
     * 
     * 
     * @see com.ningpai.temp.dao.TempTokenMapper#selectTokenByType(java.lang.String)
     */
    @Override
    public TempToken selectTokenByType(String temp1) {
        return this.selectOne("com.ningpai.temp.dao.TempTokenMapper.selectTokenByType", temp1);
    }

    /*
     * 
     * @see com.ningpai.temp.dao.TempTokenMapper#updateTokenValue(java.lang.String)
     */
    @Override
    public int updateTokenValue(TempToken token) {
        
        return this.update("com.ningpai.temp.dao.TempTokenMapper.updateTokenValue", token);
    }

}
