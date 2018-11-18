/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.channel.service.impl;

import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.ningpai.channel.bean.GoodsSiteSearchBean;
import com.ningpai.system.service.DefaultAddressService;
import org.springframework.stereotype.Service;

import com.ningpai.channel.bean.ChannelStoreyGoods;
import com.ningpai.channel.dao.ChannelStoreyGoodsMapper;
import com.ningpai.channel.service.ChannelStoreyGoodsService;
import com.ningpai.util.PageBean;

/**
 * SERVICE实现类-频道楼层商品
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年4月12日下午5:37:44
 */
@Service("ChannelStoreyGoodsService")
public class ChannelStoreyGoodsServiceImpl implements ChannelStoreyGoodsService {

    private static final String STOREYID = "storeyId";
    private static final String STOREYTAGID = "storeyTagId";
    private static final String ISHOT = "isHot";
    private static final String DISTINCTID = "distinctId";

    /** 频道楼层商品数据访问接口 */
    private ChannelStoreyGoodsMapper storeyGoodsMapper;

    @Resource(name = "DefaultAddressService")
    private DefaultAddressService addressService;

    /*
     * 
     * 
     * @see com.ningpai.channel.service.ChannelStoreyGoodsService#
     * deleteChannelStoreyGoods(java.lang.Long)
     */
    @Override
    public int deleteChannelStoreyGoods(Long channelStoreyGoodsproductId, Long userId) {
        ChannelStoreyGoods storeyGoods = this.storeyGoodsMapper.selectByPrimaryKey(channelStoreyGoodsproductId);
        storeyGoods.setDelflag("1");
        storeyGoods.setUpdateUserId(userId);
        storeyGoods.setUpdateDate(new Date());
        return storeyGoodsMapper.updateByPrimaryKeySelective(storeyGoods);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.channel.service.ChannelStoreyGoodsService#saveChannelStoreyGoods
     * (com.ningpai.channel.bean.ChannelStoreyGoods)
     */
    @Override
    public int saveChannelStoreyGoods(ChannelStoreyGoods record) {
        record.setDelflag("0");
        Date date = new Date();
        record.setCreateDate(date);
        record.setUpdateDate(date);
        return storeyGoodsMapper.insertSelective(record);
    }

    /*
     * 
     * 
     * @see com.ningpai.channel.service.ChannelStoreyGoodsService#
     * updateChannelStoreyGoods(com.ningpai.channel.bean.ChannelStoreyGoods)
     */
    @Override
    public int updateChannelStoreyGoods(ChannelStoreyGoods record) {
        record.setUpdateDate(new Date());
        return storeyGoodsMapper.updateByPrimaryKeySelective(record);
    }

    /*
     * 
     * 
     * @see com.ningpai.channel.service.ChannelStoreyGoodsService#
     * getChannelStoreyGoodsById(java.lang.Long)
     */
    @Override
    public ChannelStoreyGoods getChannelStoreyGoodsById(Long channelStoreyGoodsproductId) {

        return storeyGoodsMapper.selectByPrimaryKey(channelStoreyGoodsproductId);
    }

    /*
     * 
     * 
     * @see com.ningpai.channel.service.ChannelStoreyGoodsService#
     * selectchannelStoreyGoodsByParam(com.ningpai.util.PageBean,
     * java.lang.Long, java.lang.Long, java.lang.String)
     */
    @Override
    public PageBean selectchannelStoreyGoodsByParam(PageBean pb, Long storeyId, Long storeyTagId, String isHot) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // 查询数据的总行数并设置到PageBean中
            map.put(STOREYID, storeyId);
            map.put(STOREYTAGID, storeyTagId);
            map.put(ISHOT, isHot);
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
     * 根据分页参数和楼层ID、楼层标签ID、是否热销查询频道楼层商品-前台展示用
     * 
     * @param storeyId
     * @param storeyTagId
     * @param isHot
     * @param searchBean
     * @param pageBean
     * @return
     */
    @Override
    public List<ChannelStoreyGoods> selectchannelStoreyGoodsByParamForChannelSite(HttpServletRequest request, Long storeyId, Long storeyTagId, String isHot,
            GoodsSiteSearchBean searchBean, PageBean pageBean) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(STOREYID, storeyId);
        map.put(STOREYTAGID, storeyTagId);
        map.put(ISHOT, isHot);
        map.put("searchBean", searchBean);
        Long distinctId = null;
        if (null != request.getSession().getAttribute(DISTINCTID)) {
            distinctId = Long.parseLong(request.getSession().getAttribute(DISTINCTID).toString());
        }
        if (pageBean != null) {
            pageBean.setRows(this.storeyGoodsMapper.selectchannelStoreyGoodsNumberByParam(map));
            map.put("startRowNum", pageBean.getStartRowNum());
            map.put("endRowNum", pageBean.getEndRowNum());
            if (null == distinctId) {
                Long dId = addressService.getDefaultIdService();
                if (dId == null) {
                    distinctId = addressService.getDefaultIdService();
                    if (distinctId == null) {
                        distinctId = 1103L;
                    }
                }
                map.put(DISTINCTID, dId);
                List<Object> goods = this.storeyGoodsMapper.selectchannelStoreyGoodsByParamForChannelSite(map);
                pageBean.setList(goods);
            } else {
                map.put(DISTINCTID, distinctId);
                List<Object> goods = this.storeyGoodsMapper.selectchannelStoreyGoodsByParamForChannelSite(map);
                pageBean.setList(goods);
            }
        }
        return this.storeyGoodsMapper.selectchannelStoreyGoodsByParamForSite(map);
    }

    /*
     * 
     * 
     * @see com.ningpai.channel.service.ChannelStoreyGoodsService#
     * selectchannelStoreyGoodsByParamForSite(java.lang.Long, java.lang.Long,
     * java.lang.String)
     */
    @Override
    public List<ChannelStoreyGoods> selectchannelStoreyGoodsByParamForSite(Long storeyId, Long storeyTagId, String isHot) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(STOREYID, storeyId);
        map.put(STOREYTAGID, storeyTagId);
        map.put(ISHOT, isHot);
        return this.storeyGoodsMapper.selectchannelStoreyGoodsByParamForSite(map);
    }

    public ChannelStoreyGoodsMapper getStoreyGoodsMapper() {
        return storeyGoodsMapper;
    }

    @Resource(name = "ChannelStoreyGoodsMapper")
    public void setStoreyGoodsMapper(ChannelStoreyGoodsMapper storeyGoodsMapper) {
        this.storeyGoodsMapper = storeyGoodsMapper;
    }

}
