/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.system.dao.impl;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.system.bean.Remark;
import com.ningpai.system.dao.RemarkMapper;

/**
 * 楼层标签接口实现层
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2014年1月7日 上午10:25:16
 * @version 1.0
 */
@Repository("remarkMapper")
public class RemarkMapperImpl extends BasicSqlSupport implements RemarkMapper {
    /**
     * 删除楼层标签
     * 
     * @param remarkId
     * @return
     */
    public int deleteByPrimaryKey(Long remarkId) {
        return 0;
    }

    /**
     * 添加楼层标签
     * 
     * @param record
     * @return
     */
    public int insert(Remark record) {
        return 0;
    }

    /**
     * 添加楼层标签--可选字段
     * 
     * @param record
     * @return
     */
    public int insertSelective(Remark record) {
        return 0;
    }

    /**
     * 查询单个楼层标签
     * 
     * @param remarkId
     * @return
     */
    public Remark selectByPrimaryKey(Long remarkId) {
        return null;
    }

    /**
     * 修改楼层标签-可选字段
     * 
     * @param record
     * @return
     */
    public int updateByPrimaryKeySelective(Remark record) {
        return 0;
    }

    /**
     * 修改楼层标签
     * 
     * @param record
     * @return
     */
    public int updateByPrimaryKey(Remark record) {
        return 0;
    }

}
