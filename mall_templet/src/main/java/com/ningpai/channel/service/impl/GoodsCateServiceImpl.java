/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.channel.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ningpai.channel.bean.GoodsCate;
import com.ningpai.channel.dao.GoodsCateMapper;
import com.ningpai.channel.service.GoodsCateService;

/**
 * 商品分类service实现
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月17日 下午5:12:33
 * @version
 */
@Service("ChannelGoodsCateService")
public class GoodsCateServiceImpl implements GoodsCateService {
    private GoodsCateMapper goodsCateMapper;

    public GoodsCateMapper getGoodsCateMapper() {
        return goodsCateMapper;
    }

    @Resource(name = "ChannelGoodsCateMapper")
    public void setGoodsCateMapper(GoodsCateMapper goodsCateMapper) {
        this.goodsCateMapper = goodsCateMapper;
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.channel.service.GoodsCateService#selectGoosCateById(java.
     * lang.Long)
     */
    @Override
    public GoodsCate selectGoosCateById(Long catId) {
        return goodsCateMapper.selectGoosCateById(catId);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.channel.service.GoodsCateService#queryAllFirstGradeGoosCate()
     */
    @Override
    public List<GoodsCate> queryAllFirstGradeGoosCate() {
        return goodsCateMapper.queryAllFirstGradeGoosCate();
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.channel.service.GoodsCateService#queryGoosCateByParentId(
     * java.lang.Long)
     */
    @Override
    public List<GoodsCate> queryGoosCateByParentId(Long parentId) {
        return goodsCateMapper.queryGoosCateByParentId(parentId);
    }
}
