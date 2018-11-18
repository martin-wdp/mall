/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.system.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ningpai.system.bean.FriendLink;
import com.ningpai.system.dao.FriendLinkMapper;
import com.ningpai.system.service.FriendLinkService;
import com.ningpai.system.util.SelectBean;
import com.ningpai.util.PageBean;

/**
 * 友情链接服务层接口实现类
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2013年12月18日 上午9:49:49
 * @version 1.0
 */
@Service("friendLinkService")
public class FriendLinkServiceImpl implements FriendLinkService {

    @Resource(name = "friendLinkMapper")
    private FriendLinkMapper friendLinkMapper;

    /**
     * 查询单条友情链接信息
     * 
     * @param linkId
     * @return FriendLink
     */
    public FriendLink findByLinkId(Long linkId) {
        // 查询单条链接信息
        return friendLinkMapper.selectByPrimaryKey(linkId);
    }

    /**
     * 分页查询友情链接信息
     * 
     * @param pageBean
     * @return List
     */
    public PageBean findByPageBean(PageBean pageBean, SelectBean selectBean) {

        // 查询总行数

        pageBean.setRows(friendLinkMapper.findTotalCount(selectBean));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("startRowNum", pageBean.getStartRowNum());
        map.put("endRowNum", pageBean.getEndRowNum());
        map.put("condition", selectBean.getCondition());
        map.put("searchText", selectBean.getSearchText());
        map.put("searchTextTwo", selectBean.getSearchTextTwo());
        // 设置列表
        pageBean.setList(friendLinkMapper.findByPageBean(map));

        map = null;

        return pageBean;
    }

    /**
     * 查询所有友情链接
     * 
     * @param friendLink
     * @return
     */
    @Override
    public List<FriendLink> selectAllFriendLink() {
        return friendLinkMapper.selectAllFriendLink();
    }

    /**
     * 添加友情链接
     * 
     * @param friendLink
     * @return int
     */
    public int insertLink(FriendLink friendLink) {
        friendLink.setDelFlag("0");
        // 添加链接信息
        return friendLinkMapper.insertSelective(friendLink);
    }

    /**
     * 删除友情链接
     * 
     * @param linkId
     * @return int
     */
    public int deleteLink(String[] linkId) {
        int count = 0;
        // 删除链接信息
        for (String id : linkId) {
            count += friendLinkMapper.deleteByPrimaryKey(Long.parseLong(id));
        }
        return count;
    }

    /**
     * 修改友情链接信息
     * 
     * @param friendLink
     * @return int
     */
    public int updateLink(FriendLink friendLink) {
        // 修改链接信息
        friendLink.setModifyTime(new Date());
        return friendLinkMapper.updateByPrimaryKeySelective(friendLink);
    }

}
