/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.third.seller.mapper.impl;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.third.seller.bean.StoreContact;
import com.ningpai.third.seller.mapper.StoreContactMapper;

/**
 * @see com.ningpai.third.seller.mapper.StoreContactMapper
 * @author Zhouh
 * @since 2014年5月5日 下午6:05:41
 * @version 0.0.1
 */
@Repository("storeContactMapper")
public class StoreContactMapperImpl extends BasicSqlSupport implements StoreContactMapper {
    /**
     * 删除店铺联系人
     * @param conId
     * @return
     */
    public int deleteByPrimaryKey(Long conId) {
        return 0;
    }

    /**
     * 新增店铺联系人
     * @param record
     * @return
     */
    public int insert(StoreContact record) {
        return 0;
    }

    /**
     * 新增店铺联系人
     * @param record
     * @return
     */
    public int insertSelective(StoreContact record) {
        return 0;
    }

    /**
     * 根据主键获取店铺联系人
     * @param conId
     * @return
     */
    public StoreContact selectByPrimaryKey(Long conId) {
        return null;
    }

    /**
     * 更新店铺联系人
     * @param record
     *            消息设置 {@link StoreContact}
     * @return
     */
    public int updateByPrimaryKeySelective(StoreContact record) {
        return this.update("com.ningpai.third.seller.mapper.StoreContactMapper.updateByPrimaryKeySelective", record);
    }

    /**
     * 更新店铺联系人
     * @param record
     * @return
     */
    public int updateByPrimaryKey(StoreContact record) {
        return 0;
    }

    /**
     * 根据商铺ID获取店铺联系人集合
     * @param storeId
     *            店铺编号
     * @return
     */
    public List<StoreContact> selectByStoreId(Long storeId) {
        return this.selectList("com.ningpai.third.seller.mapper.StoreContactMapper.selectByStoreId", storeId);
    }

    /**
     * 新增店铺联系人
     * @param record
     *            消息设置 {@link StoreContact}
     * @return
     */
    public int insertStoreSelective(StoreContact record) {
        return this.insert("com.ningpai.third.seller.mapper.StoreContactMapper.insertStoreSelective", record);
    }

}
