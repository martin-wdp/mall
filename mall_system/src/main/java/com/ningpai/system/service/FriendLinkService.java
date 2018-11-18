/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.system.service;

import com.ningpai.system.bean.FriendLink;
import com.ningpai.system.util.SelectBean;
import com.ningpai.util.PageBean;

import java.util.List;

/**
 * 友情链接服务层接口
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2013年12月18日 上午9:47:10
 * @version 1.0
 */
public interface FriendLinkService {

    /**
     * 分页查询友情链接信息
     * 
     * @param pageBean
     * @return List
     */
    PageBean findByPageBean(PageBean pageBean, SelectBean selectBean);

    /**
     * 添加友情链接
     * 
     * @param friendLink
     * @return int
     */
    int insertLink(FriendLink friendLink);

    /**
     * 删除友情链接
     * 
     * @param linkId
     * @return int
     */
    int deleteLink(String[] linkId);

    /**
     * 查询单条友情链接信息
     * 
     * @param linkId
     * @return FriendLink
     */
    FriendLink findByLinkId(Long linkId);

    /**
     * 修改友情链接信息
     * 
     * @param friendLink
     * @return int
     */
    int updateLink(FriendLink friendLink);
 
    /**
     * 查询所有友情链接
     */
    List<FriendLink> selectAllFriendLink();
}
