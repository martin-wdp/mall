/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.channel.service;

import com.ningpai.channel.bean.ChannelStoreyGoods;
import com.ningpai.util.PageBean;

import java.util.List;

/**
 * SERVICE-热销推荐商品
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年4月12日下午5:31:22
 */
public interface ChannelGoodsService {

    /**
     * 根据主键删除（彻底删除）
     *
     * @param channelStoreyGoodsproductId
     * @return
     */
    int deleteChannelStoreyGoodsnew(Long channelStoreyGoodsproductId);

    /**
     * 根据主键删除(这个只是进行了状态修改 不是彻底删除 新增商品的时候 就会出现要添加的商品ID存在的问题  id是去的当前商品的ID并不是自增长的ID)
     * 
     * @param channelStoreyGoodsproductId
     * @return
     */
    int deleteChannelStoreyGoods(Long channelStoreyGoodsproductId, Long userId);

    /**
     * 添加
     * 
     * @param record
     * @return
     */
    int saveChannelStoreyGoods(ChannelStoreyGoods record);

    /**
     * 修改
     * 
     * @param record
     * @return
     */
    int updateChannelStoreyGoods(ChannelStoreyGoods record);

    /**
     * 根据主键查询
     * 
     * @param channelStoreyGoodsproductId
     * @return
     */
    ChannelStoreyGoods getChannelStoreyGoodsById(Long channelStoreyGoodsproductId);

    /**
     * 根据分页参数和楼层ID、楼层标签ID、是否热销查询频道楼层商品
     * 
     * @param pb
     * @param tempId
     * @param channelId
     * @param temp4
     * @return
     */
    PageBean selectchannelStoreyGoodsByParam(PageBean pb, String tempId, String channelId,String temp4);

    /**
     * 根据分页参数和楼层ID、楼层标签ID、是否热销查询频道楼层商品-前台展示用
     * 
     * @param tempId
     * @param channelId
     * @param temp4 商家ID
     * @param
     * @return
     */
    List<ChannelStoreyGoods> selectchannelStoreyGoodsByParamForSite(String tempId, String channelId,String temp4);

    /**
     * 根据分页参数和楼层ID、楼层标签ID、是否热销查询频道楼层商品-前台展示用
     * 
     * @param tempId
     * @param channelId
     * @return List
     */
    List<ChannelStoreyGoods> selectchannelStoreyGoodsByParamForSiteRandom(String tempId, String channelId);
}
