/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.system.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.system.bean.AdverType;
import com.ningpai.system.dao.AdverTypeMapper;

/**
 * 广告分类数据舱
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2014年1月15日 上午9:59:57
 * @version 1.0
 */
@Repository("adverTypeMapper")
public class AdverTypeMapperImpl extends BasicSqlSupport implements
        AdverTypeMapper {
    /***
     * 删除首页广告图片
     * 
     * @param atId
     * @return
     */
    public int deleteByPrimaryKey(Long atId) {
        return 0;
    }

    /**
     * 添加首页广告图片
     * 
     * @param record
     * @return
     */
    public int insert(AdverType record) {
        return 0;
    }

    /**
     * 添加首页广告图片
     * 
     * @param record
     * @return
     */
    public int insertSelective(AdverType record) {
        return 0;
    }

    /**
     * 查找单个首页广告图片
     * 
     * @param atId
     * @return
     */
    public AdverType selectByPrimaryKey(Long atId) {
        return null;
    }

    /**
     * 修改首页广告图片
     * 
     * @param record
     * @return
     */
    public int updateByPrimaryKeySelective(AdverType record) {
        return 0;
    }

    /**
     * 修改首页广告图片
     * 
     * @param record
     * @return
     */
    public int updateByPrimaryKey(AdverType record) {
        return 0;
    }

    /**
     * 查询所有广告分类
     * 
     * @return List
     */
    public List<AdverType> selectAll() {

        return this
                .selectList("com.ningpai.system.dao.AdverTypeMapper.selectAll");
    }

}
