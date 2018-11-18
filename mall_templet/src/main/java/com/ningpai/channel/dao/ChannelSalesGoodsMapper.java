package com.ningpai.channel.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.channel.bean.ChannelGoods;

/**
 * 频道销售商品
 * @author ggn
 *
 */
public interface ChannelSalesGoodsMapper {
    /**
     * 根据主键删除 参数:主键 返回:删除个数
     * 
     * @param channelGoodsId
     * @return int
     */
    int deleteByPrimaryKey(Long channelGoodsId);

    /**
     * 插入，空属性不会插入 参数:pojo对象 返回:删除个数
     * 
     * @ibatorgenerated 2014-06-04 16:22:26
     */
    /**
     * 插入，空属性不会插入 参数:pojo对象 返回:删除个数
     * 
     * @param record
     * @return int
     */
    int insertSelective(ChannelGoods record);

    /**根据主键查询 参数:查询条件,主键值 返回:对象
     * @param channelGoodsId
     * @return ChannelGoods
     */
    ChannelGoods selectByPrimaryKey(Long channelGoodsId);

    /**根据主键修改，空值条件不会修改成null 参数:1.要修改成的值 返回:成功修改个数
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(ChannelGoods record);

    /**
     * 查询频道商品列表总数
     * 
     * @param map
     * @return int
     */
    int selectChannelGoodsCount(Map<String, Object> map);

    /**
     * 查询频道商品列表
     * 
     * @param map
     * @return List<Object>
     */
    List<Object> selectChannelGoodsList(Map<String, Object> map);

    /**
     * 查询频道页商品通过商品类型
     * 
     * @param pMap
     * @return List
     */
    List<ChannelGoods> selectChannelGoodsByFlag(Map<String, Object> pMap);
}
