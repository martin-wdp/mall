/*
 * Copyright 2013 NINGPAI, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.system.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.system.bean.MessageServer;

/**
 * 短信服务器接口
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2013年12月14日 下午5:14:05
 * @version 1.0
 */
public interface MessageServerMapper {
    /**
     * 删除短信服务器
     * 
     * @param smsId
     * @return
     */
    int deleteByPrimaryKey(Long smsId);

    /**
     * 添加短信服务器
     * 
     * @param record
     * @return
     */
    int insert(MessageServer record);

    /**
     * 添加短信服务器--可选字段
     * 
     * @param record
     * @return
     */
    int insertSelective(MessageServer record);

    /**
     * 查询单个短信服务器
     * 
     * @param smsId
     * @return
     */
    MessageServer selectByPrimaryKey(Long smsId);

    /**
     * 修改短信接口信息--可选字段
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(MessageServer record);

    /**
     * 修改短信服务器
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKey(MessageServer record);

    /**
     * 查询短信接口信息
     * 
     * @return
     */
    MessageServer findMessage();

    /**
     * 查询所有短信接口数量
     * 
     * @return
     */
    int selectAllCount();

    /**
     * 分页查询所有短信接口
     * 
     * @param map
     * @return
     */
    List<Object> selectAllByPb(Map<String, Object> map);

    /**
     * 关闭所有短信接口
     * 
     * @return
     */
    int closeAll();
}
