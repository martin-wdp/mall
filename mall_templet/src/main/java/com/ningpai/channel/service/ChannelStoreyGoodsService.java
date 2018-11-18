/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.channel.service;

import java.util.List;

import com.ningpai.channel.bean.ChannelStoreyGoods;
import com.ningpai.channel.bean.GoodsSiteSearchBean;
import com.ningpai.util.PageBean;

import javax.servlet.http.HttpServletRequest;

/**
 * SERVICE-频道楼层商品
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年4月12日下午5:31:22
 */
public interface ChannelStoreyGoodsService {
    /**
     * 根据主键删除
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
     * @param storeyId
     * @param storeyTagId
     * @param isHot
     * @return
     */
    PageBean selectchannelStoreyGoodsByParam(PageBean pb, Long storeyId, Long storeyTagId, String isHot);

    /**
     * 根据楼层ID、楼层标签ID、是否热销查询频道楼层商品-前台展示用
     * 
     * @param storeyId
     * @param storeyTagId
     * @param isHot
     * @return
     */
    List<ChannelStoreyGoods> selectchannelStoreyGoodsByParamForSite(Long storeyId, Long storeyTagId, String isHot);
    /**
     * 根据分页参数和楼层ID、楼层标签ID、是否热销查询频道楼层商品-前台展示用
     *
     * @param storeyId
     * @param storeyTagId
     * @param isHot
     * @return
     */
    List<ChannelStoreyGoods> selectchannelStoreyGoodsByParamForChannelSite(HttpServletRequest request,Long storeyId, Long storeyTagId, String isHot,GoodsSiteSearchBean searchBean,PageBean pageBean);
}
