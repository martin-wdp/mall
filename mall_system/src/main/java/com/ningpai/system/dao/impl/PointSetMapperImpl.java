/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.system.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.system.bean.PointSet;
import com.ningpai.system.dao.PointSetMapper;

/**
 * 积分设置实现层
 *
 * @author NINGPAI-LiHaoZe
 * @version 1.0
 * @since 2013年12月16日 下午2:32:47
 */
@Repository("pointSetMapper")
public class PointSetMapperImpl extends BasicSqlSupport implements
        PointSetMapper {
    /**
     * 删除积分设置信息
     *
     * @param psetId
     * @return
     */
    public int deleteByPrimaryKey(Long psetId) {
        return 0;
    }

    /**
     * 查询积分设置信息
     *
     * @return PointSet
     */
    public PointSet findPointSet() {
        PointSet pointSet = null;
        List<PointSet> plist = this
                .selectList("com.ningpai.system.dao.PointSetMapper.selectByPrimaryKey");

        if (plist.isEmpty()) {
            pointSet = new PointSet();
            pointSet.setPsetId(1L);
            this.insert(
                    "com.ningpai.system.dao.PointSetMapper.insertSelective",
                    pointSet);
        } else {
            pointSet = plist.get(0);
        }
        return pointSet;
    }

    /**
     * 添加积分设置信息
     *
     * @param record
     * @return
     */
    public int insert(PointSet record) {
        return 0;
    }

    /**
     * 添加积分设置信息--可选字段
     *
     * @param record
     * @return
     */
    public int insertSelective(PointSet record) {
        return 0;
    }

    /**
     * 查询单个积分设置信息
     *
     * @param psetId
     * @return
     */
    public PointSet selectByPrimaryKey(Long psetId) {
        return null;
    }

    /**
     * 修改积分设置--可选字段
     *
     * @param record
     * @return int
     */
    public int updateByPrimaryKeySelective(PointSet record) {
        return this
                .update("com.ningpai.system.dao.PointSetMapper.updateByPrimaryKeySelective",
                        record);
    }

    /**
     * 修改积分设置
     *
     * @param record
     * @return
     */
    public int updateByPrimaryKey(PointSet record) {
        return 0;
    }

}
