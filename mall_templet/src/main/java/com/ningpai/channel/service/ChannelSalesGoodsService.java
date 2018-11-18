/*
 * Copyright 2014 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.channel.service;

import java.util.List;

import com.ningpai.channel.bean.ChannelGoods;
import com.ningpai.util.PageBean;

/**
 * @Description:
 * @author zhangyue
 * @date 2014年6月4日 下午4:42:55
 * @version V1.0
 */
public interface ChannelSalesGoodsService {
    /**
     * 根据主键删除 参数:主键 返回:删除个数
     * 
     * @ibatorgenerated 2014-06-04 16:22:26
     */
    int deleteByPrimaryKey(Long channelGoodsId, Long userId);

    /**
     * 插入，空属性不会插入 参数:pojo对象 返回:删除个数
     * 
     * @ibatorgenerated 2014-06-04 16:22:26
     */
    int insertSelective(ChannelGoods record);

    /**
     * 根据主键查询 参数:查询条件,主键值 返回:对象
     * 
     * @ibatorgenerated 2014-06-04 16:22:26
     */
    ChannelGoods selectByPrimaryKey(Long channelGoodsId);

    /**
     * 根据主键修改，空值条件不会修改成null 参数:1.要修改成的值 返回:成功修改个数
     * 
     * @ibatorgenerated 2014-06-04 16:22:26
     */
    int updateByPrimaryKeySelective(ChannelGoods record);

    /**
     * 根据分页参数和楼层ID查询促销商品
     * 
     * @param pb
     * @param tempId
     * @param channelId
     * @return
     */
    PageBean selectChannelGoodsByParam(PageBean pb, Long channelId);

    /**
     * 查询频道页商品
     * 
     * @param channelGoodsFlag
     *            0今日推荐 1新品推荐 2人气明星 。。。
     * @return List
     */
    List<ChannelGoods> selectChannelGoodsByFlag(Long channelId, String channelGoodsFlag, int top);
}
