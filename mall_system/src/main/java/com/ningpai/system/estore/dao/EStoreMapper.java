/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.system.estore.dao;

import com.ningpai.system.estore.bean.EStore;

/**
 * E店宝dao
 * 
 * @author jiping
 * @since 2014年12月2日 上午10:38:47
 * @version 0.0.1
 */
public interface EStoreMapper {
    /**
     * 根据id删除信息
     * 
     * @param estoreid
     * @return
     */
    int deleteByPrimaryKey(Long estoreid);

    /**
     * 添加E店宝
     * 
     * @param record
     * @return
     */
    int insertSelective(EStore record);

    /**
     * 根据id查询E店宝信息
     * 
     * @param estoreid
     * @return
     */
    EStore selectByPrimaryKey(Long estoreid);

    /**
     * 查询E店宝信息
     * 
     * @return
     */
    EStore findEStore();

    /**
     * 修改E店宝信息
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(EStore record);

    /**
     * 查询E店宝总数量
     * 
     * @return
     */
    int selectAllCount();
}
