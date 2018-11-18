package com.ningpai.channel.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.channel.bean.ChannelStoreyGoods;
import com.ningpai.goods.bean.GoodsShowListVo;

/**
 * DAO-频道楼层商品
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年4月12日下午5:21:41
 */
public interface ChannelStoreyGoodsMapper {

    /**
     * 根据主键删除（彻底删除）
     *
     * @param map 删除数据时候用到的删除条件
     * @return int
     */
    int deleteChannelStoreyGoodsnew(Map<String,Object> map);
    /**
     * 根据主键删除
     * 
     * @param channelStoreyGoodsproductId
     * @return int
     */
    int deleteByPrimaryKey(Long channelStoreyGoodsproductId);

    /**
     * 添加
     * 
     * @param record
     * @return int
     */
    int insert(ChannelStoreyGoods record);

    /**
     * 添加-字段可选
     * 
     * @param record
     * @return int
     */
    int insertSelective(ChannelStoreyGoods record);

    /**
     * 修改-字段可选
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(ChannelStoreyGoods record);

    /**
     * 修改
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKey(ChannelStoreyGoods record);

    /**
     * 根据主键查询
     * 
     * @param channelStoreyGoodsproductId
     * @return ChannelStoreyGoods
     */
    ChannelStoreyGoods selectByPrimaryKey(Long channelStoreyGoodsproductId);

    /**
     * 根据分页参数和楼层ID、楼层标签ID、是否热销查询频道楼层商品总行数
     * 
     * @param map
     * @return
     */
    Integer selectchannelStoreyGoodsCountByParam(Map<String, Object> map);
    
    /**根据分页参数和楼层ID、楼层标签ID、是否热销查询频道楼层商品总行数
     * @param map
     * @return  Integer
     */
    Integer selectchannelStoreyGoodsNumberByParam(Map<String,Object> map);

    /**
     * 根据分页参数和楼层ID、楼层标签ID、是否热销查询频道楼层商品
     * 
     * @param map
     * @return
     */
    List<Object> selectchannelStoreyGoodsByParam(Map<String, Object> map);

    /**
     * 根据分页参数和楼层ID、楼层标签ID、是否热销查询频道楼层商品-前台展示用
     * 
     * @param map
     * @return
     */
    List<ChannelStoreyGoods> selectchannelStoreyGoodsByParamForSite(Map<String, Object> map);
    /**
     * 根据分页参数和楼层ID、楼层标签ID、是否热销查询频道楼层商品-前台展示用
     *
     * @param map
     * @return
     */
    List<Object> selectchannelStoreyGoodsByParamForChannelSite(Map<String, Object> map);
    /**
     * 通过商品Id查询商品的信息
     * @param map
     * @return
     */
    GoodsShowListVo queryGoodsListByGoodsInfoId(Map<String,Object> map);
    /**
     * 根据分页参数和楼层ID、楼层标签ID、是否热销查询频道楼层商品-前台展示用
     * 
     * @param map
     * @return
     */
    List<ChannelStoreyGoods> selectchannelStoreyGoodsByParamForSiteRandom(Map<String, Object> map);
}
