/*
 * Copyright 2013 NINGPAI, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.system.dao;

import com.ningpai.system.bean.Remark;

/**
 * 楼层标签接口层
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2014年1月7日 上午10:24:29
 * @version 1.0
 */
public interface RemarkMapper {
    /**
     * 删除楼层标签
     * 
     * @param remarkId
     * @return
     */
    int deleteByPrimaryKey(Long remarkId);

    /**
     * 添加楼层标签
     * 
     * @param record
     * @return
     */
    int insert(Remark record);

    /**
     * 添加楼层标签--可选字段
     * 
     * @param record
     * @return
     */
    int insertSelective(Remark record);

    /**
     * 查询单个楼层标签
     * 
     * @param remarkId
     * @return
     */
    Remark selectByPrimaryKey(Long remarkId);

    /**
     * 修改楼层标签-可选字段
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Remark record);

    /**
     * 修改楼层标签
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKey(Remark record);
}
