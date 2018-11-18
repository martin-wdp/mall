/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.info.dao.impl;

import java.util.List;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.info.bean.MessageBean;
import com.ningpai.info.dao.MessageSendMapper;
import com.ningpai.manager.base.BasicSqlSupport;
/**
 * 消息设置DAO实现类
 * @author huangyi
 *
 */
@Repository("MessageSendMapper")
public class MessageSendMapperImpl extends BasicSqlSupport implements MessageSendMapper {

    /*
     * 
     * @see com.ningpai.info.dao.MessageSendMapper#selectInformMapper(java.util.Map)
     */
    @Override
    public MessageBean selectInformMapper(Map<String , Object> map) {
        return this.selectOne("com.ningpai.info.dao.MessageSendMapper.selectInformMapper",map);
    }
    /*
     * 
     * @see com.ningpai.info.dao.MessageSendMapper#updateInformMapper(com.ningpai.info.bean.MessageBean)
     */
    @Override
    public int updateInformMapper(MessageBean message) {
        return this.update("com.ningpai.info.dao.MessageSendMapper.updateInformMapper", message);
    }
    /*
     * 
     * @see com.ningpai.info.dao.MessageSendMapper#selectListMapper(java.util.Map)
     */
    @Override
    public List<Object> selectListMapper(Map<String,Object> map) {
        return this.selectList("com.ningpai.info.dao.MessageSendMapper.selectListMapper",map);
    }
    /*
     * 
     * @see com.ningpai.info.dao.MessageSendMapper#selectAllSize(java.util.Map)
     */
    @Override
    public int selectAllSize(Map<String, Object> map) {
        return this.selectOne("com.ningpai.info.dao.MessageSendMapper.selectAllSize",map);
    }
    /*
     * 
     * @see com.ningpai.info.dao.MessageSendMapper#selectByIdMapper(int)
     */
    @Override
    public MessageBean selectByIdMapper(int informId) {
        return this.selectOne("com.ningpai.info.dao.MessageSendMapper.selectByIdMapper", informId);
    }
    /*
     * 
     * @see com.ningpai.info.dao.MessageSendMapper#findSubjectMapper(int)
     */
    @Override
    public String findSubjectMapper(int informId) {
        return this.selectOne("com.ningpai.info.dao.MessageSendMapper.findSubjectMapper", informId);
    }
    /*
     * 
     * @see com.ningpai.info.dao.MessageSendMapper#findTextMapper(int)
     */
    @Override
    public String findTextMapper(int informId) {
        return this.selectOne("com.ningpai.info.dao.MessageSendMapper.findTextMapper", informId);
    }
    
}
