package com.ningpai.channel.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.channel.bean.ChannelTrademark;

/**
 * DAO-频道品牌
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年4月25日上午9:42:57
 */
public interface ChannelTrademarkMapper {
    /**
     * 根据ID删除
     * 
     * @param channelTrademarkId
     * @return
     */
    int deleteByPrimaryKey(Long channelTrademarkId);

    /**
     * 添加
     * 
     * @param record
     * @return
     */
    int insert(ChannelTrademark record);

    /**
     * 添加-字段可选
     * 
     * @param record
     * @return
     */
    int insertSelective(ChannelTrademark record);

    /**
     * 修改-字段可选
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(ChannelTrademark record);

    /**
     * 修改
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKey(ChannelTrademark record);

    /**
     * 根据ID查询
     * 
     * @param channelTrademarkId
     * @return
     */
    ChannelTrademark selectByPrimaryKey(Long channelTrademarkId);

    /**
     * 根据分页参数和频道ID、模板ID、楼层ID查询频道品牌总行数
     * 
     * @param map
     * @return
     */
    Integer selectchannelTrademarkCountByParam(Map<String, Object> map);

    /**
     * 根据分页参数和频道ID、模板ID、楼层ID查询频道品牌
     * 
     * @param map
     * @return
     */
    List<Object> selectchannelTrademarkByParam(Map<String, Object> map);

    /**
     * 根据分页参数和频道ID、模板ID、楼层ID查询频道品牌--前台展示用
     * 
     * @param map
     * @return
     */
    List<ChannelTrademark> selectChannelTrademarkByParamForSite(Map<String, Object> map);
    
    /**
     * 根据模板ID查询品牌设置
     * @param tempId
     * @return
     */
   List< ChannelTrademark>  selectTrademarkByTempId(Long tempId);

}
