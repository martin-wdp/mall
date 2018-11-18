/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.system.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.system.bean.BasicSet;
import com.ningpai.system.dao.BasicSetMapper;

/**
 * 站点基本信息实现类
 *
 * @author NINGPAI-LiHaoZe
 * @version 1.0
 * @since 2013年12月16日 下午4:59:55
 */
@Repository("basicSetMapper")
public class BasicSetMapperImpl extends BasicSqlSupport implements
        BasicSetMapper {
    /**
     * 查询站点信息
     *
     * @return BasicSet
     */
    public BasicSet findBasicSet() {
        BasicSet basicSet = null;
        List<BasicSet> blist = this
                .selectList("com.ningpai.system.dao.BasicSetMapper.selectByPrimaryKey");
        if (blist.isEmpty()) {
            basicSet = new BasicSet();
            basicSet.setBsetId(1L);
            this.insert(
                    "com.ningpai.system.dao.BasicSetMapper.insertSelective",
                    basicSet);
        } else {
            basicSet = blist.get(0);
        }
        return basicSet;
    }

    /**
     * 删除站点基本信息
     *
     * @param bsetId
     * @return
     */
    public int deleteByPrimaryKey(Long bsetId) {
        return 0;
    }

    /**
     * 添加站点基本信息
     *
     * @param record
     * @return
     */
    public int insert(BasicSet record) {
        return 0;
    }

    /**
     * 添加站点基本信息
     *
     * @param record
     * @return
     */
    public int insertSelective(BasicSet record) {
        return 0;
    }

    /**
     * 查询单个站点基本信息
     *
     * @param record
     * @return
     */
    public BasicSet selectByPrimaryKey(Long bsetId) {
        return null;
    }

    /**
     * 修改站点信息
     *
     * @param record
     * @return int
     */
    public int updateByPrimaryKeySelective(BasicSet record) {
        // 修改站点设置

        return this
                .update("com.ningpai.system.dao.BasicSetMapper.updateByPrimaryKeySelective",
                        record);
    }

    /**
     * 修改站点信息
     *
     * @param record
     * @return int
     */
    public int updateByPrimaryKey(BasicSet record) {
        return 0;
    }


    /**
     * 查询店铺是否开启
     *
     * @return 开启状态
     */
    @Override
    public String getStoreStatus() {
        return this
                .selectOne("com.ningpai.system.dao.BasicSetMapper.getStoreStatus");
    }
}
