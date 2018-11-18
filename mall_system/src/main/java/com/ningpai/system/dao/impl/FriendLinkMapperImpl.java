/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.system.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.system.bean.FriendLink;
import com.ningpai.system.dao.FriendLinkMapper;
import com.ningpai.system.util.SelectBean;

/**
 * 友情链接接口实现层
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2013年12月18日 上午9:45:53
 * @version 1.0
 */
@Repository("friendLinkMapper")
public class FriendLinkMapperImpl extends BasicSqlSupport implements
        FriendLinkMapper {
    /**
     * 删除友情链接
     * 
     * @return int
     */
    public int deleteByPrimaryKey(Long linkId) {
        return this.delete(
                "com.ningpai.system.dao.FriendLinkMapper.deleteByPrimaryKey",
                linkId);
    }

    /**
     * 添加友情链接
     * 
     * @param record
     * @return
     */
    public int insert(FriendLink record) {
        return 0;
    }

    /**
     * 插入链接信息
     * 
     * @param record
     * @return int
     */
    public int insertSelective(FriendLink record) {
        return this.insert(
                "com.ningpai.system.dao.FriendLinkMapper.insertSelective",
                record);
    }

    /**
     * 按链接Id查询
     * 
     * @param linkId
     * @return FriendLink
     */
    public FriendLink selectByPrimaryKey(Long linkId) {
        return this.selectOne(
                "com.ningpai.system.dao.FriendLinkMapper.selectByPrimaryKey",
                linkId);
    }

    /**
     * 修改链接信息--可选字段
     * 
     * @param record
     * @return int
     */
    public int updateByPrimaryKeySelective(FriendLink record) {
        return this
                .update("com.ningpai.system.dao.FriendLinkMapper.updateByPrimaryKeySelective",
                        record);
    }

    /**
     * 修改链接信息
     * 
     * @param record
     * @return int
     */
    public int updateByPrimaryKey(FriendLink record) {
        return 0;
    }

    /**
     * 分页查询友情链接信息
     * 
     * @param map
     * @return
     */
    public List<Object> findByPageBean(Map<String, Object> map) {
        return this.selectList(
                "com.ningpai.system.dao.FriendLinkMapper.findByPageBean", map);
    }

    /**
     * 查询总行数
     * 
     * @return int
     */
    public int findTotalCount(SelectBean selectBean) {
        return this.selectOne(
                "com.ningpai.system.dao.FriendLinkMapper.findTotalCount",
                selectBean);
    }

    /**
     * 查询所有友情链接
     */
    public List<FriendLink> selectAllFriendLink() {
        return this
                .selectList("com.ningpai.system.dao.FriendLinkMapper.selectAllFriendLink");
    }
}
