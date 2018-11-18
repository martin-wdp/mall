/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.info.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.info.bean.MessageBean;
/**
 * 消息设置DAO接口
 * @author huangyi
 *
 */
public interface MessageSendMapper {
    /**
     * 根据type 和 status 字段查询
     * @param map 参数封装对象Map
     * @return MessageBean对象
     */
    MessageBean selectInformMapper(Map<String, Object> map);
    /**
     * 修改消息设置
     * @param message MessageBean参数信息
     * @return  修改结果
     */
    int updateInformMapper(MessageBean message);
    /**
     * 查询所有列表行数
     * @param map 参数封装对象
     * @return 记录条数
     */
    int selectAllSize(Map<String,Object> map);
    /**
     * 分页查询列表
     * @param map 参数封装对象
     * @return 查询记录列表
     */
    List<Object> selectListMapper(Map<String, Object> map);
    /**
     * 更具id查询
     * @param informId 主键ID
     * @return MessageBean信息
     */
    MessageBean selectByIdMapper(int informId);

    /**
     * 根据id查询Subject字段
     * @param informId 主键ID
     * @return Subject字段值
     */
    String findSubjectMapper(int informId);
    /**
     * 根据id查询Text字段
     * @param informId 主键ID
     * @return Text字段值
     */
    String findTextMapper(int informId);
}
