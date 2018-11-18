/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.system.estore.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.system.estore.bean.EStore;
import com.ningpai.system.estore.dao.EStoreMapper;

/**
 * E店宝dao实现类
 *
 * @author jiping
 * @version 0.0.1
 * @since 2014年12月2日 上午10:52:17
 */
@Repository("estoreMapper")
public class EStoreMapperImpl extends BasicSqlSupport implements EStoreMapper {

    /*
     * 
     * 
     * @see com.ningpai.system.estore.dao.EStoreMapper#deleteByPrimaryKey(java.lang.Long)
     */
    @Override
    public int deleteByPrimaryKey(Long estoreid) {
        return this.update("com.ningpai.system.estore.dao.EStoreMapper.delete", estoreid);
    }

    /*
     * 
     * 
     * @see com.ningpai.system.estore.dao.EStoreMapper#insertSelective(com.ningpai.system.estore.bean.EStore)
     */
    @Override
    public int insertSelective(EStore record) {
        return this.insert("com.ningpai.system.estore.dao.EStoreMapper.insertSelective", record);
    }

    /*
     * 
     * 
     * @see com.ningpai.system.estore.dao.EStoreMapper#selectByPrimaryKey(java.lang.Long)
     */
    @Override
    public EStore selectByPrimaryKey(Long estoreid) {
        return this.selectOne("com.ningpai.system.estore.dao.EStoreMapper.selectByPrimaryKey", estoreid);
    }

    /*
     * 
     * 
     * @see com.ningpai.system.estore.dao.EStoreMapper#updateByPrimaryKeySelective(com.ningpai.system.estore.bean.EStore)
     */
    @Override
    public int updateByPrimaryKeySelective(EStore record) {
        return this.update("com.ningpai.system.estore.dao.EStoreMapper.updateByPrimaryKeySelective", record);
    }

    /*
     * 
     * 
     * @see com.ningpai.system.estore.dao.EStoreMapper#selectAllCount()
     */
    @Override
    public int selectAllCount() {
        return this.selectOne("com.ningpai.system.estore.dao.EStoreMapper.selectAllCount");
    }

    /*
     * 
     * 
     * @see com.ningpai.system.estore.dao.EStoreMapper#findEStore()
     */
    @Override
    public EStore findEStore() {
        EStore estore = null;
        List<EStore> slist = this.selectList("com.ningpai.system.estore.dao.EStoreMapper.selectAllByPb");
        if (slist.isEmpty()) {
            estore = new EStore();
            estore.setEstoreid(1L);
            this.insert("com.ningpai.system.estore.dao.EStoreMapper.insertSelective", estore);
        } else {
            estore = slist.get(0);
        }
        return estore;
    }

}
