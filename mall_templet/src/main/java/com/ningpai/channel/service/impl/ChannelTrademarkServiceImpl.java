/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.channel.service.impl;

import com.ningpai.channel.bean.ChannelTrademark;
import com.ningpai.channel.dao.ChannelTrademarkMapper;
import com.ningpai.channel.service.ChannelTrademarkService;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SERVICE实现类-频道品牌
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年4月25日上午10:19:31
 */
@Service("ChannelTrademarkService")
public class ChannelTrademarkServiceImpl implements ChannelTrademarkService {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(ChannelTrademarkServiceImpl.class);

    private static final String CHANNELID = "channelId";
    private static final String TEMPID = "tempId";
    private static final String TEMP1 = "temp1";
    private static final String TEMP2 = "temp2";

    /* 数据访问层依赖 */
    private ChannelTrademarkMapper channelTrademarkMapper;

    /*
     * 
     * 
     * @see
     * com.ningpai.channel.service.ChannelTrademarkService#deleteChannelTrademark
     * (java.lang.Long, java.lang.Long)
     */
    @Override
    public int deleteChannelTrademark(Long channelTrademarkId, Long userId) {
        try {
            ChannelTrademark channelTrademark = channelTrademarkMapper.selectByPrimaryKey(channelTrademarkId);
            channelTrademark.setDelflag("1");
            channelTrademark.setUpdateUserId(userId);
            channelTrademark.setUpdateDate(new Date());
            return channelTrademarkMapper.updateByPrimaryKeySelective(channelTrademark);
        } catch (Exception e) {
            LOGGER.error("删除频道广告错误:", e);
            return 0;
        }
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.channel.service.ChannelTrademarkService#saveChannelTrademark
     * (com.ningpai.channel.bean.ChannelTrademark)
     */
    @Override
    public int saveChannelTrademark(ChannelTrademark record) {
        try {
            Date date = new Date();
            record.setCreateDate(date);
            record.setUpdateDate(date);
            record.setDelflag("0");
            return channelTrademarkMapper.insertSelective(record);
        } catch (Exception e) {
            LOGGER.error("添加频道广告错误:", e);
            return 0;
        }
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.channel.service.ChannelTrademarkService#updateChannelTrademark
     * (com.ningpai.channel.bean.ChannelTrademark)
     */
    @Override
    public int updateChannelTrademark(ChannelTrademark record) {
        try {
            record.setUpdateDate(new Date());
            return channelTrademarkMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            LOGGER.error("修改频道广告错误:", e);
            return 0;
        }
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.channel.service.ChannelTrademarkService#getChannelTrademarkById
     * (java.lang.Long)
     */
    @Override
    public ChannelTrademark getChannelTrademarkById(Long channelTrademarkId) {
        ChannelTrademark channelTrademark = null;
        try {
            channelTrademark = channelTrademarkMapper.selectByPrimaryKey(channelTrademarkId);
        } catch (Exception e) {
            LOGGER.error("根据ID查询频道品牌错误:", e);
        }
        return channelTrademark;
    }

    /*
     * 
     * 
     * @see com.ningpai.channel.service.ChannelTrademarkService#
     * selectchannelTrademarkByParam(com.ningpai.util.PageBean, java.lang.Long,
     * java.lang.Long, java.lang.Long)
     */
    @Override
    public PageBean selectchannelTrademarkByParam(PageBean pb, Long channelId, Long tempId, Long storeyId, Long storeyTagId, String temp1, String temp3) {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(CHANNELID, channelId);
            map.put(TEMPID, tempId);
            map.put("storeyId", storeyId);
            map.put("storeyTagId", storeyTagId);
            map.put(TEMP1, temp1);
            map.put(TEMP2, "0");
            map.put("temp3", temp3);
            pb.setRows(channelTrademarkMapper.selectchannelTrademarkCountByParam(map));
            map.put("startRowNum", pb.getStartRowNum());
            map.put("endRowNum", pb.getEndRowNum());
            pb.setList(channelTrademarkMapper.selectchannelTrademarkByParam(map));
            return pb;
        } catch (Exception e) {
            LOGGER.error("分页查询品牌列表错误:", e);
            return null;
        }
    }

    /*
     * 
     * 
     * @see com.ningpai.channel.service.ChannelTrademarkService#
     * selectchannelTrademarkByParam(com.ningpai.util.PageBean,
     * java.lang.String, java.lang.String)
     */
    @Override
    public PageBean selectClassifyTrademarkByParam(PageBean pb, Long tempId, Long channelId, String temp1, String temp2) {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(TEMP1, temp1);
            map.put(TEMP2, temp2);
            map.put(TEMPID, tempId);
            map.put(CHANNELID, channelId);
            pb.setRows(channelTrademarkMapper.selectchannelTrademarkCountByParam(map));
            map.put("startRowNum", pb.getStartRowNum());
            map.put("endRowNum", pb.getEndRowNum());
            pb.setList(channelTrademarkMapper.selectchannelTrademarkByParam(map));
            return pb;
        } catch (Exception e) {
            LOGGER.error("分页查询分类导航品牌列表错误:", e);
            return null;
        }
    }

    /*
     * 
     * 
     * @see com.ningpai.channel.service.ChannelTrademarkService#
     * selectChannelTrademarkByParamForSite(com.ningpai.util.PageBean,
     * java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Long,
     * java.lang.String)
     */
    @Override
    public List<ChannelTrademark> selectChannelTrademarkByParamForSite(Long channelId, Long tempId, Long storeyId, Long storeyTagId, String temp1, String temp2, String temp3) {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(CHANNELID, channelId);
            map.put(TEMPID, tempId);
            map.put("storeyId", storeyId);
            map.put("storeyTagId", storeyTagId);
            map.put(TEMP1, temp1);
            map.put(TEMP2, temp2);
            map.put("temp3", temp3);
            return channelTrademarkMapper.selectChannelTrademarkByParamForSite(map);
        } catch (Exception e) {
            LOGGER.error("分页查询品牌列表错误:", e);
            return Collections.emptyList();
        }
    }

    public ChannelTrademarkMapper getChannelTrademarkMapper() {
        return channelTrademarkMapper;
    }

    @Resource(name = "ChannelTrademarkMapper")
    public void setChannelTrademarkMapper(ChannelTrademarkMapper channelTrademarkMapper) {
        this.channelTrademarkMapper = channelTrademarkMapper;
    }

    /**
     * 根据模板ID查询品牌设置
     * 
     * @param tempId
     * @return
     */
    @Override
    public List<ChannelTrademark> selectTrademarkByTempId(Long tempId) {

        return channelTrademarkMapper.selectTrademarkByTempId(tempId);
    }

}
