/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.m.weixin.dao.impl;

import org.springframework.stereotype.Repository;

import com.ningpai.m.weixin.bean.WXGroup;
import com.ningpai.m.weixin.dao.WXGroupMapper;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * @see com.ningpai.m.weixin.dao.WXGroupMapper
 * @author NINGPAI-zhangqiang
 * @since 2014年9月3日 下午3:25:01
 * @version 0.0.1
 */
@Repository("wXGroupMapper")
public class WXGroupMapperImpl extends BasicSqlSupport implements WXGroupMapper {
    /*
     * 
     * 
     * @see com.ningpai.m.weixin.dao.WXGroupMapper#deleteByPrimaryKey(java.lang.Long)
     */
    @Override
    public int deleteByPrimaryKey(Long wxId) {
        return 0;
    }

    /*
     * 
     * 
     * @see com.ningpai.m.weixin.dao.WXGroupMapper#insert(com.ningpai.m.weixin.bean .WXGroup)
     */
    @Override
    public int insert(WXGroup record) {
        return 0;
    }

    /*
     * 
     * 
     * @see com.ningpai.m.weixin.dao.WXGroupMapper#insertSelective(com.ningpai.m. weixin.bean.WXGroup)
     */
    @Override
    public int insertSelective(WXGroup record) {
        return this.insert("com.ningpai.m.weixin.dao.WXGroupMapper.insertSelective", record);
    }

    /*
     * 
     * 
     * @see com.ningpai.m.weixin.dao.WXGroupMapper#selectByPrimaryKey(java.lang.Long)
     */
    @Override
    public WXGroup selectByPrimaryKey(Long wxId) {
        return null;
    }

    /*
     * 
     * 
     * @see com.ningpai.m.weixin.dao.WXGroupMapper#updateByPrimaryKeySelective(com .ningpai.m.weixin.bean.WXGroup)
     */
    @Override
    public int updateByPrimaryKeySelective(WXGroup record) {
        return 0;
    }

    /*
     * 
     * 
     * @see com.ningpai.m.weixin.dao.WXGroupMapper#updateByPrimaryKey(com.ningpai .m.weixin.bean.WXGroup)
     */
    @Override
    public int updateByPrimaryKey(WXGroup record) {
        return 0;
    }

    /*
     * 
     * 
     * @see com.ningpai.m.weixin.dao.WXGroupMapper#checkOpenIdExist(java.lang.String)
     */
    @Override
    public Long checkOpenIdExist(String openId) {
        return this.selectOne("com.ningpai.m.weixin.dao.WXGroupMapper.checkOpenIdExist", openId);
    }

}
