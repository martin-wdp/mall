/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.channel.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ningpai.channel.bean.ChannelAdver;
import com.ningpai.channel.dao.ChannelAdverMapper;
import com.ningpai.channel.service.ChannelAdverService;
import com.ningpai.util.PageBean;

/**
 * SERVICE实现类-频道广告
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年4月1日下午4:30:11
 */
@Service("ChannelAdverService")
public class ChannelAdverServiceImpl implements ChannelAdverService {

    private static final String CHANNELID = "channelId";
    private static final String TEMPID = "tempId";
    private static final String ADVER_TYPE = "adverType";
    private static final String TEMP1 = "temp1";
    private static final String TEMP3 = "temp3";

    private ChannelAdverMapper channelAdverMapper;

    private static final Long ATID = 161L;

    private static final Long ADVERTYPE = 151L;

    /**
     * 
     *
     */
    @Override
    public int deleteChannelAdver(Long channelAdverId, Long userId) {
        ChannelAdver ca = channelAdverMapper.selectByPrimaryKey(channelAdverId);
        ca.setDelflag("1");
        ca.setUpdateUserId(userId);
        ca.setUpdateDate(new Date());
        return channelAdverMapper.updateByPrimaryKeySelective(ca);
    }

    /**
     * 
     * 
     * @see com.ningpai.channel.service.ChannelAdverService#saveChannelAdver(com.ningpai.channel.bean.ChannelAdver)
     */
    @Override
    public int saveChannelAdver(ChannelAdver record) {
        // 设置删除标记
        record.setDelflag("0");
        // 设置时间
        Date date = new Date();
        // 设置创建时间
        record.setCreateDate(date);
        // 设置更新时间
        record.setUpdateDate(date);
        // 返回结果
        return channelAdverMapper.insertSelective(record);
    }

    /**
     * 
     * 
     * @see com.ningpai.channel.service.ChannelAdverService#updateChannelAdver(com.ningpai.channel.bean.ChannelAdver)
     */
    @Override
    public int updateChannelAdver(ChannelAdver record) {
        // 设置更新时间
        record.setUpdateDate(new Date());
        // 进行更新
        return channelAdverMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 
     * 
     * @see com.ningpai.channel.service.ChannelAdverService#selectByPrimaryKey(java.lang.Long)
     */
    @Override
    public ChannelAdver selectByPrimaryKey(Long channelAdverId) {

        return channelAdverMapper.selectByPrimaryKey(channelAdverId);
    }

    /**
     * 
     *
     */
    @Override
    public PageBean selectchannelAdverByParam(PageBean pb, Long channelId, Long tempId, Long floorId, Long floorTagId, Long atId, Long adverType, String temp1, String temp4) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // 查询数据的总行数并设置到PageBean中
            // 频道广告ID
            map.put(CHANNELID, channelId);
            // 模板id
            map.put(TEMPID, tempId);
            // 楼层ID
            map.put("floorId", floorId);
            // 楼层标签ID
            map.put("floorTagId", floorTagId);
            // 广告分类-关联系统字典表的广告分类 <br/>
            // 157：轮播大图片 159：轮播小图片 161：首页图片
            map.put("atId", atId);
            // 广告类型-关联系统字典表的广告类型<br/>
            // 151：频道广告
            map.put(ADVER_TYPE, adverType);
            // 分类导航ID
            map.put(TEMP1, temp1);
            // 是否分类导航父框广告
            map.put(TEMP3, "0");
            // 第三方ID
            map.put("temp4", temp4);
            // 设置总行数
            pb.setRows(this.channelAdverMapper.selectchannelAdverCountByParam(map));
            // 开始行数
            map.put("startRowNum", pb.getStartRowNum());
            // 结束行数
            map.put("endRowNum", pb.getEndRowNum());
            // 设置集合
            pb.setList(this.channelAdverMapper.selectchannelAdverByParam(map));
        } finally {
            // 至空参数
            map = null;
        }
        // 返回结果
        return pb;
    }

    /**
     * 
     * 
     * @see com.ningpai.channel.service.ChannelAdverService#selectClassifyBarAdverByParam(com.ningpai.util.PageBean,
     *      java.lang.Long, java.lang.Long, java.lang.String, java.lang.String)
     */
    @Override
    public PageBean selectClassifyBarAdverByParam(PageBean pb, Long tempId, Long channelId, String temp1, String temp3) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // 查询数据的总行数并设置到PageBean中
            map.put(CHANNELID, channelId);
            map.put(TEMPID, tempId);
            map.put("atId", ATID);
            map.put(ADVER_TYPE, ADVERTYPE);
            map.put(TEMP1, temp1);
            map.put(TEMP3, temp3);
            pb.setRows(this.channelAdverMapper.selectchannelAdverCountByParam(map));
            map.put("startRowNum", pb.getStartRowNum());
            map.put("endRowNum", pb.getEndRowNum());
            pb.setList(this.channelAdverMapper.selectchannelAdverByParam(map));
        } finally {
            map = null;
        }
        return pb;
    }

    /**
     * 
     *
     */
    @Override
    public List<ChannelAdver> selectchannelAdverByParamForSite(Long channelId, Long tempId, Long floorId, Long floorTagId, Long atId, Long adverType, String temp1, String temp3,
            String temp4, String temp5) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(CHANNELID, channelId);
        map.put(TEMPID, tempId);
        map.put("floorId", floorId);
        map.put("floorTagId", floorTagId);
        map.put("atId", atId);
        map.put(ADVER_TYPE, adverType);
        map.put(TEMP1, temp1);
        map.put(TEMP3, temp3);
        map.put("temp4", temp4);
        map.put("temp5", temp5);
        return this.channelAdverMapper.selectchannelAdverByParamForSite(map);
    }

    public ChannelAdverMapper getChannelAdverMapper() {
        return channelAdverMapper;
    }

    @Resource(name = "ChannelAdverMapper")
    public void setChannelAdverMapper(ChannelAdverMapper channelAdverMapper) {
        this.channelAdverMapper = channelAdverMapper;
    }
}
