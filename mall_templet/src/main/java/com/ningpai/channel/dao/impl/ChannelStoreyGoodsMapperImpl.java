/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.channel.dao.impl;

import java.util.List;
import java.util.Map;

import com.ningpai.goods.bean.GoodsShowListVo;
import org.springframework.stereotype.Repository;

import com.ningpai.channel.bean.ChannelStoreyGoods;
import com.ningpai.channel.dao.ChannelStoreyGoodsMapper;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * DAO实现类-频道楼层商品
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年4月12日下午5:26:25
 */
@Repository("ChannelStoreyGoodsMapper")
public class ChannelStoreyGoodsMapperImpl extends BasicSqlSupport implements ChannelStoreyGoodsMapper {

    /**根据主键删除（彻底删除）
     * @see com.ningpai.channel.dao.ChannelStoreyGoodsMapper#deleteChannelStoreyGoodsnew(java.util.Map)
     */
    @Override
    public int deleteChannelStoreyGoodsnew(Map<String,Object> map) {
        return this.update("com.ningpai.channel.dao.ChannelStoreyGoodsMapper.deleteChannelStoreyGoodsnew", map);
    }

    /**根据主键删除
     *
     * @see com.ningpai.channel.dao.ChannelStoreyGoodsMapper#deleteByPrimaryKey(java.lang.Long)
     */
    @Override
    public int deleteByPrimaryKey(Long channelStoreyGoodsproductId) {

        return this.delete("com.ningpai.channel.dao.ChannelStoreyGoodsMapper.deleteByPrimaryKey", channelStoreyGoodsproductId);
    }

    /**
     * 添加
     * 
     * @see com.ningpai.channel.dao.ChannelStoreyGoodsMapper#insert(com.ningpai.channel.bean.ChannelStoreyGoods)
     */
    @Override
    public int insert(ChannelStoreyGoods record) {

        return this.insert("com.ningpai.channel.dao.ChannelStoreyGoodsMapper.insert", record);
    }

    /**
     *  添加-字段可选
     * @see com.ningpai.channel.dao.ChannelStoreyGoodsMapper#insertSelective(com.ningpai.channel.bean.ChannelStoreyGoods)
     */
    @Override
    public int insertSelective(ChannelStoreyGoods record) {

        return this.insert("com.ningpai.channel.dao.ChannelStoreyGoodsMapper.insertSelectiveNew", record);
    }

    /**修改-字段可选
     * 
     * @see com.ningpai.channel.dao.ChannelStoreyGoodsMapper#updateByPrimaryKeySelective(com.ningpai.channel.bean.ChannelStoreyGoods)
     */
    @Override
    public int updateByPrimaryKeySelective(ChannelStoreyGoods record) {

        return this.update("com.ningpai.channel.dao.ChannelStoreyGoodsMapper.updateByPrimaryKeySelective", record);
    }

    /**
     * 修改
     * @see com.ningpai.channel.dao.ChannelStoreyGoodsMapper#updateByPrimaryKey(com.ningpai.channel.bean.ChannelStoreyGoods)
     */
    @Override
    public int updateByPrimaryKey(ChannelStoreyGoods record) {

        return this.update("com.ningpai.channel.dao.ChannelStoreyGoodsMapper.updateByPrimaryKey", record);
    }

    /** 根据主键查询
     * 
     * @see com.ningpai.channel.dao.ChannelStoreyGoodsMapper#selectByPrimaryKey(java.lang.Long)
     */
    @Override
    public ChannelStoreyGoods selectByPrimaryKey(Long channelStoreyGoodsproductId) {

        return this.selectOne("com.ningpai.channel.dao.ChannelStoreyGoodsMapper.selectByPrimaryKey", channelStoreyGoodsproductId);
    }

    /**根据分页参数和楼层ID、楼层标签ID、是否热销查询频道楼层商品总行数
     * 
     * @see com.ningpai.channel.dao.ChannelStoreyGoodsMapper#selectchannelStoreyGoodsCountByParam(java.util.Map)
     */
    @Override
    public Integer selectchannelStoreyGoodsCountByParam(Map<String, Object> map) {

        return this.selectOne("com.ningpai.channel.dao.ChannelStoreyGoodsMapper.selectchannelStoreyGoodsCountByParam", map);
    }

    /**
     * 根据分页参数和楼层ID、楼层标签ID、是否热销查询频道楼层商品总行数
     * @see com.ningpai.channel.dao.ChannelStoreyGoodsMapper#selectchannelStoreyGoodsNumberByParam(java.util.Map)
     */
    @Override
    public Integer selectchannelStoreyGoodsNumberByParam(Map<String, Object> map) {
        return this.selectOne("com.ningpai.channel.dao.ChannelStoreyGoodsMapper.selectchannelStoreyGoodsNumberByParam", map);
    }

    /**根据分页参数和楼层ID、楼层标签ID、是否热销查询频道楼层商品
     * 
     * @see com.ningpai.channel.dao.ChannelStoreyGoodsMapper#selectchannelStoreyGoodsByParam(java.util.Map)
     */
    @Override
    public List<Object> selectchannelStoreyGoodsByParam(Map<String, Object> map) {

        return this.selectList("com.ningpai.channel.dao.ChannelStoreyGoodsMapper.selectchannelStoreyGoodsByParam", map);
    }

    /**根据分页参数和楼层ID、楼层标签ID、是否热销查询频道楼层商品-前台展示用
     * 
     * @see com.ningpai.channel.dao.ChannelStoreyGoodsMapper#selectchannelStoreyGoodsByParamForSite(java.util.Map)
     */
    @Override
    public List<ChannelStoreyGoods> selectchannelStoreyGoodsByParamForSite(Map<String, Object> map) {

        return this.selectList("com.ningpai.channel.dao.ChannelStoreyGoodsMapper.selectchannelStoreyGoodsByParamForSite", map);
    }

    /** 根据分页参数和楼层ID、楼层标签ID、是否热销查询频道楼层商品-前台展示用
     * @see com.ningpai.channel.dao.ChannelStoreyGoodsMapper#selectchannelStoreyGoodsByParamForChannelSite(java.util.Map)
     */
    @Override
    public List<Object> selectchannelStoreyGoodsByParamForChannelSite(Map<String, Object> map) {
        return this.selectList("com.ningpai.channel.dao.ChannelStoreyGoodsMapper.selectchannelStoreyGoodsByParamForChannelSite", map);
    }

    /** 通过商品Id查询商品的信息
     * @see com.ningpai.channel.dao.ChannelStoreyGoodsMapper#queryGoodsListByGoodsInfoId(java.util.Map)
     */
    @Override
    public GoodsShowListVo queryGoodsListByGoodsInfoId(Map<String, Object> map) {
        return this.selectOne("com.ningpai.channel.dao.ChannelStoreyGoodsMapper.queryGoodsListByGoodsInfoId",map);
    }

    /**根据分页参数和楼层ID、楼层标签ID、是否热销查询频道楼层商品-前台展示用
     * 
     * @see com.ningpai.channel.dao.ChannelStoreyGoodsMapper#selectchannelStoreyGoodsByParamForSiteRandom(java.util.Map)
     */
    @Override
    public List<ChannelStoreyGoods> selectchannelStoreyGoodsByParamForSiteRandom(Map<String, Object> map) {

        return this.selectList("com.ningpai.channel.dao.ChannelStoreyGoodsMapper.selectchannelStoreyGoodsByParamForSiteRandom", map);
    }
}
