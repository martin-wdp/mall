/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.third.seller.mapper;

import java.util.List;

import com.ningpai.third.seller.bean.StoreContact;

/**
 * 店铺联系人Mapper
 * 
 * @author Zhouh
 * @since 2014年5月5日 下午5:49:51
 * @version 0.0.1
 */
public interface StoreContactMapper {

    /**
     * 根据主键删除店铺联系人
     * @param conId
     * @return
     */
    int deleteByPrimaryKey(Long conId);

    /**
     * 新增店铺联系人
     * @param record
     * @return
     */
    int insert(StoreContact record);

    /**
     * 店铺联系人
     * @param record
     * @return
     */
    int insertSelective(StoreContact record);

    /**
     * 根据主键获取店铺联系人
     * @param conId
     * @return
     */
    StoreContact selectByPrimaryKey(Long conId);

    /**
     * 根据主键修改店铺联系人
     * @param record
     * @return
     */
    int updateByPrimaryKey(StoreContact record);

    /**
     * 修改店铺联系人信息
     * 
     * @param record
     *            消息设置 {@link StoreContact}
     * @return 0失败 1成功
     */
    int updateByPrimaryKeySelective(StoreContact record);

    /**
     * 查询店铺的联系人信息
     * 
     * @param storeId
     *            店铺编号
     * @return List<StoreContact> 商家消息接收设置集合 {@link List} {@link StoreContact}
     */
    List<StoreContact> selectByStoreId(Long storeId);

    /**
     * 添加店铺联系人信息
     * 
     * @param record
     *            消息设置 {@link StoreContact}
     * @return 0失败 1成功
     */
    int insertStoreSelective(StoreContact record);

}
