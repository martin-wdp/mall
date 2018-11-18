/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.channel.service.impl;

import com.ningpai.channel.bean.ChannelStoreyGoods;
import com.ningpai.channel.dao.ChannelStoreyGoodsMapper;
import com.ningpai.channel.service.ChannelGoodsService;
import com.ningpai.util.PageBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SERVICE实现类-频道楼层商品
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年4月12日下午5:37:44
 */
@Service("ChannelGoodsService")
public class ChannelGoodsServiceImpl implements ChannelGoodsService {

    private static final String TEMP1 = "temp1";
    private static final String TEMP2 = "temp2";

    /** 频道楼层商品数据访问接口 */
    private ChannelStoreyGoodsMapper storeyGoodsMapper;

    /**
     *
     */
    @Override
    public int deleteChannelStoreyGoodsnew(Long channelStoreyGoodsproductId) {
        // 根据主键获取单个的楼层商品对象
        ChannelStoreyGoods storeyGoods = this.storeyGoodsMapper.selectByPrimaryKey(channelStoreyGoodsproductId);

            Map<String, Object> map = new HashMap<>();
            // 要删除的主键
            map.put("productId", channelStoreyGoodsproductId);
            // 当前商品对应的楼层ID
        if (null != storeyGoods.getStoreyId()) {
            map.put("storeyId", storeyGoods.getStoreyId());
        }
            return storeyGoodsMapper.deleteChannelStoreyGoodsnew(map);


    }

    /**
     *
     */
    @Override
    public int deleteChannelStoreyGoods(Long channelStoreyGoodsproductId, Long userId) {
        // 热销商品
        ChannelStoreyGoods storeyGoods = this.storeyGoodsMapper.selectByPrimaryKey(channelStoreyGoodsproductId);
        // 设置删除状态
        storeyGoods.setDelflag("1");
        storeyGoods.setUpdateUserId(userId);
        storeyGoods.setUpdateDate(new Date());
        return storeyGoodsMapper.updateByPrimaryKeySelective(storeyGoods);
    }

    /**
     *
     */
    @Override
    public int saveChannelStoreyGoods(ChannelStoreyGoods record) {
        record.setDelflag("0");
        Date date = new Date();
        record.setCreateDate(date);
        record.setUpdateDate(date);
        return storeyGoodsMapper.insertSelective(record);
    }

    /**
     *
     */
    @Override
    public int updateChannelStoreyGoods(ChannelStoreyGoods record) {
        record.setUpdateDate(new Date());
        return storeyGoodsMapper.updateByPrimaryKeySelective(record);
    }

    /**
     *
     */
    @Override
    public ChannelStoreyGoods getChannelStoreyGoodsById(Long channelStoreyGoodsproductId) {

        return storeyGoodsMapper.selectByPrimaryKey(channelStoreyGoodsproductId);
    }

    /**
     *
     */
    @Override
    public PageBean selectchannelStoreyGoodsByParam(PageBean pb, String tempId, String channelId, String temp4) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // 查询数据的总行数并设置到PageBean中
            map.put("isHot", "1");
            map.put(TEMP1, tempId);
            map.put(TEMP2, channelId);
            map.put("temp4", temp4);
            pb.setRows(this.storeyGoodsMapper.selectchannelStoreyGoodsCountByParam(map));
            map.put("startRowNum", pb.getStartRowNum());
            map.put("endRowNum", pb.getEndRowNum());
            pb.setList(this.storeyGoodsMapper.selectchannelStoreyGoodsByParam(map));
        } finally {
            map = null;
        }
        return pb;
    }

    /**
     *
     */
    @Override
    public List<ChannelStoreyGoods> selectchannelStoreyGoodsByParamForSite(String tempId, String channelId, String temp4) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(TEMP1, tempId);
        map.put(TEMP2, channelId);
        map.put("temp4", temp4);
        map.put("isHot", "1");
        return this.storeyGoodsMapper.selectchannelStoreyGoodsByParamForSite(map);
    }

    /**
     *
     */
    @Override
    public List<ChannelStoreyGoods> selectchannelStoreyGoodsByParamForSiteRandom(String tempId, String channelId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(TEMP1, tempId);
        map.put(TEMP2, channelId);
        return this.storeyGoodsMapper.selectchannelStoreyGoodsByParamForSiteRandom(map);
    }

    public ChannelStoreyGoodsMapper getStoreyGoodsMapper() {
        return storeyGoodsMapper;
    }

    @Resource(name = "ChannelStoreyGoodsMapper")
    public void setStoreyGoodsMapper(ChannelStoreyGoodsMapper storeyGoodsMapper) {
        this.storeyGoodsMapper = storeyGoodsMapper;
    }

}
