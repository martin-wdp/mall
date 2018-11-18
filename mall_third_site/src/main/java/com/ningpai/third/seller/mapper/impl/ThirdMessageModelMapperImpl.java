/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.third.seller.mapper.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.third.seller.bean.ThirdMessageModel;
import com.ningpai.third.seller.mapper.ThirdMessageModelMapper;

/**
 * @see ThirdMessageModelMapper
 * @author NINGPAI-zhangqiang
 * @since 2014年5月20日 上午11:38:15
 * @version 0.0.1
 */
@Repository("thirdMessageModelMapper")
public class ThirdMessageModelMapperImpl extends BasicSqlSupport implements ThirdMessageModelMapper {

    /**
     * 第三方消息接收Bean
     * @param messModId
     * @return
     */

    public int deleteByPrimaryKey(Long messModId) {
        return 0;
    }

    /**
     * 新增第三方消息接收Bean
     * @param record
     * @return
     */
    public int insert(ThirdMessageModel record) {
        return 0;
    }

    /**
     * 新增第三方消息接收Bean
     * @param record
     * @return
     */
    public int insertSelective(ThirdMessageModel record) {
        return 0;
    }

    /**
     * 根据主键搜索新增第三方消息接收Bean
     * @param messModId
     * @return
     */
    public ThirdMessageModel selectByPrimaryKey(Long messModId) {
        return null;
    }

    /**
     * 根据主键修改新增第三方消息接收Bean
     * @param record
     * @return
     */
    public int updateByPrimaryKeySelective(ThirdMessageModel record) {
        return 0;
    }

    /**
     * 根据主键新增第三方消息接收Bean
     * @param record
     * @return
     */
    public int updateByPrimaryKey(ThirdMessageModel record) {
        return 0;
    }

    /**
     * 根据商铺ID 获取第三方消息接收集合
     * @param storeId
     *            商家编号 {@link Long}
     * @return
     */
    public List<ThirdMessageModel> selectAllMessModel(Long storeId) {
        return this.selectList("com.ningpai.third.seller.mapper.ThirdMessageModelMapper.selectAllMessModel", storeId);
    }

}
