/*
 * Copyright 2014 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.channel.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ningpai.channel.bean.ChannelGoods;
import com.ningpai.channel.dao.ChannelSalesGoodsMapper;
import com.ningpai.channel.service.ChannelSalesGoodsService;
import com.ningpai.util.PageBean;

/**
 * @Description:
 * @author zhangyue
 * @date 2014年6月4日 下午4:43:46
 * @version V1.0
 */
@Service("ChannelSalesGoodsService")
public class ChannelSalesGoodsServiceImpl implements ChannelSalesGoodsService {

    private ChannelSalesGoodsMapper channelSalesGoodsMapper;

    /**
     * 
     * 
     * @see com.ningpai.channelgoods.service.ChannelGoodsService#deleteByPrimaryKey(java.lang.Long)
     */
    @Override
    public int deleteByPrimaryKey(Long channelGoodsId, Long userId) {
        ChannelGoods record = new ChannelGoods();
        record.setChannelGoodsModifyUser(userId);
        record.setChannelGoodsModifyTime(new Date());
        record.setChannelGoodsDelFlag("1");
        record.setChannelGoodsId(channelGoodsId);
        return channelSalesGoodsMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 
     * 
     * @see com.ningpai.channelgoods.service.ChannelGoodsService#insertSelective(com.ningpai.channelgoods.bean.ChannelGoods)
     */
    @Override
    public int insertSelective(ChannelGoods record) {
        record.setChannelGoodsCreateTime(new Date());
        record.setChannelGoodsDelFlag("0");
        record.setChannelGoodsModifyTime(new Date());
        return channelSalesGoodsMapper.insertSelective(record);
    }

    /**
     * 
     * 
     * @see com.ningpai.channelgoods.service.ChannelGoodsService#selectByPrimaryKey(java.lang.Long)
     */
    @Override
    public ChannelGoods selectByPrimaryKey(Long channelGoodsId) {

        return channelSalesGoodsMapper.selectByPrimaryKey(channelGoodsId);
    }

    /**
     * 
     * 
     * @see com.ningpai.channelgoods.service.ChannelGoodsService#updateByPrimaryKeySelective(com.ningpai.channelgoods.bean.ChannelGoods)
     */
    @Override
    public int updateByPrimaryKeySelective(ChannelGoods record) {

        return channelSalesGoodsMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 
     * 
     * @see com.ningpai.channelgoods.service.ChannelSalesGoodsService#selectchannelStoreyGoodsByParam(com.ningpai.util.PageBean,
     *      java.lang.String, java.lang.String)
     */
    @Override
    public PageBean selectChannelGoodsByParam(PageBean pb, Long channelId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // 查询数据的总行数并设置到PageBean中
            // map.put("temp1", tempId);
            map.put("channelId", channelId);
            pb.setRows(this.channelSalesGoodsMapper.selectChannelGoodsCount(map));
            map.put("startRowNum", pb.getStartRowNum());
            map.put("endRowNum", pb.getEndRowNum());
            pb.setList(this.channelSalesGoodsMapper.selectChannelGoodsList(map));
        } finally {
            map = null;
        }
        return pb;
    }

    /**
     * 
     * 
     * @see com.ningpai.channelgoods.service.ChannelSalesGoodsService#selectChannelGoodsByFlag(java.lang.String)
     */
    @Override
    public List<ChannelGoods> selectChannelGoodsByFlag(Long channelId, String channelGoodsFlag, int top) {
        Map<String, Object> pMap = new HashMap<String, Object>();
        pMap.put("channelId", channelId);
        pMap.put("channelGoodsFlag", channelGoodsFlag);
        pMap.put("top", top);
        return channelSalesGoodsMapper.selectChannelGoodsByFlag(pMap);
    }

    public ChannelSalesGoodsMapper getChannelSalesGoodsMapper() {
        return channelSalesGoodsMapper;
    }

    @Resource(name = "ChannelSalesGoodsMapper")
    public void setChannelSalesGoodsMapper(ChannelSalesGoodsMapper channelSalesGoodsMapper) {
        this.channelSalesGoodsMapper = channelSalesGoodsMapper;
    }

}
