/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.system.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.system.bean.FriendLink;
import com.ningpai.system.util.SelectBean;

/**
 * 友情链接接口层
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2013年12月18日 上午9:42:55
 * @version 1.0
 */
public interface FriendLinkMapper {
    /**
     * 删除友情链接
     * 
     * @return int
     */
    int deleteByPrimaryKey(Long linkId);

    /**
     * 添加友情链接
     * 
     * @param record
     * @return
     */
    int insert(FriendLink record);

    /**
     * 插入链接信息
     * 
     * @param record
     * @return int
     */
    int insertSelective(FriendLink record);

    /**
     * 按链接Id查询
     * 
     * @param linkId
     * @return FriendLink
     */
    FriendLink selectByPrimaryKey(Long linkId);

    /**
     * 修改链接信息--可选字段
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(FriendLink record);

    /**
     * 修改链接信息
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKey(FriendLink record);

    /**
     * 分页查询友情链接信息
     * 
     * @param map
     * @return
     */
    List<Object> findByPageBean(Map<String, Object> map);

    /**
     * 查询总行数
     * 
     * @return int
     */
    int findTotalCount(SelectBean selectBean);

    /**
     * 查询所有友情链接
     */
    List<FriendLink> selectAllFriendLink();
}
