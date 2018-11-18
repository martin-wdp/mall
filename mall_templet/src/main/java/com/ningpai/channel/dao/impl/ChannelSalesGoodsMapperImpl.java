/*
 * Copyright 2014 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.channel.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.channel.bean.ChannelGoods;
import com.ningpai.channel.dao.ChannelSalesGoodsMapper;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * @Description:
 * @author zhangyue
 * @date 2014年6月4日 下午4:26:20
 * @version V1.0
 */
@Repository("ChannelSalesGoodsMapper")
public class ChannelSalesGoodsMapperImpl extends BasicSqlSupport implements ChannelSalesGoodsMapper {

    /*
     * 
     * 
     * @see com.ningpai.channel.dao.ChannelGoodsMapper#deleteByPrimaryKey(java.lang.Long)
     */
    @Override
    public int deleteByPrimaryKey(Long channelGoodsId) {
        return this.update("com.ningpai.channel.dao.ChannelGoodsMapper.deleteByPrimaryKey", channelGoodsId);
    }

    /*
     * 
     * 
     * @see com.ningpai.channel.dao.ChannelGoodsMapper#insertSelective(com.ningpai.channelGoods.bean.ChannelGoods)
     */
    @Override
    public int insertSelective(ChannelGoods record) {
        return this.insert("com.ningpai.channel.dao.ChannelGoodsMapper.insertSelective", record);
    }

    /*
     * 
     * 
     * @see com.ningpai.channel.dao.ChannelGoodsMapper#selectByPrimaryKey(java.lang.Long)
     */
    @Override
    public ChannelGoods selectByPrimaryKey(Long channelGoodsId) {
        return this.selectOne("com.ningpai.channel.dao.ChannelGoodsMapper.selectByPrimaryKey", channelGoodsId);
    }

    /*
     * 
     * 
     * @see com.ningpai.channel.dao.ChannelGoodsMapper#updateByPrimaryKeySelective(com.ningpai.channelGoods.bean.ChannelGoods)
     */
    @Override
    public int updateByPrimaryKeySelective(ChannelGoods record) {
        return this.update("com.ningpai.channel.dao.ChannelGoodsMapper.updateByPrimaryKeySelective", record);
    }

    /*
     * 
     * 
     * @see com.ningpai.channel.dao.ChannelSalesGoodsMapper#selectChannelGoodsCount(java.util.Map)
     */
    @Override
    public int selectChannelGoodsCount(Map<String, Object> map) {

        return this.selectOne("com.ningpai.channel.dao.ChannelGoodsMapper.selectChannelGoodsCount", map);
    }

    /*
     * 
     * 
     * @see com.ningpai.channel.dao.ChannelSalesGoodsMapper#selectChannelGoodsList(java.util.Map)
     */
    @Override
    public List<Object> selectChannelGoodsList(Map<String, Object> map) {

        return this.selectList("com.ningpai.channel.dao.ChannelGoodsMapper.selectChannelGoodsList", map);
    }

    @Override
    public List<ChannelGoods> selectChannelGoodsByFlag(Map<String, Object> pMap) {
        return this.selectList("com.ningpai.channel.dao.ChannelGoodsMapper.selectChannelGoodsByFlag", pMap);
    }

}
