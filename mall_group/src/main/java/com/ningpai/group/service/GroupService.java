/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.group.service;

import java.util.List;

import com.ningpai.group.bean.Group;
import com.ningpai.group.bean.GroupPermissions;
import com.ningpai.group.vo.GroupVo;
import com.ningpai.util.PageBean;

/**
 * 后台小组service层接口
 * 
 * @version 2014年5月26日 下午2:12:47
 * @author qiyuanyuan
 */

public interface GroupService {

    /**
     * 查询小组分页列表
     * 
     * @param group
     * @return
     */
    PageBean selectGroupList(GroupVo groupVo, PageBean bean);

    /**
     * 更新小组
     * 
     * @param group
     *            对象{@link com.ningpai.group.bean.Group}
     * @return
     */
    int updateGroup(Group group);

    /**
     * 根据小组ID查询小组
     * 
     * @param groupId
     *            小组ID{@link java.lang.Long}
     * @return 对象
     */
    Group selectByGroupId(Long groupId);

    /**
     * 根据小组Id解散小组
     * 
     * @param groupId
     *            小组ID{@link java.lang.Long}
     * @return int
     */
    int dissolvGroupById(Long groupId);

    /**
     * 根据小组ID 通过待审核的小组
     * 
     * @param groupId
     *            小组ID{@link java.lang.Long}
     * @return int
     */
    int passGroup(Long groupId);

    /**
     * 根据小组ID 拒绝待审核的小组
     * 
     * @param groupId
     *            小组ID{@link java.lang.Long}
     * @return int
     */
    int refuseGroup(Long groupId, String reason);

    /**
     * 根据小组ID查询小组信息
     * 
     * @param groupId
     *            小组ID{@link java.lang.Long}
     * @return GroupVo
     */
    GroupVo selectGroupVoByGroupId(Long groupId);

    /**
     * 设置活跃小组
     * 
     * @param groupId
     *            小组ID{@link java.lang.Long}
     * @return int
     */
    int activeGroup(Long groupId);

    /**
     * 设置活跃小组为一般小组
     * 
     * @param groupId
     *            小组ID{@link java.lang.Long}
     * @return int
     */
    int commonGroup(Long groupId);

    /**
     * 设置热门小组
     * 
     * @param groupId
     *            小组ID{@link java.lang.Long}
     * @return int
     */
    int hotGroup(Long groupId);

    /**
     * 设置热门小组为一般小组
     * 
     * @param groupId
     *            小组ID{@link java.lang.Long}
     * @return int
     */
    int cancelHotGroup(Long groupId);

    /**
     * 设置特别推荐小组
     * 
     * @param groupId
     *            小组ID{@link java.lang.Long}
     * @return int
     */
    int recommendGroup(Long groupId);

    /**
     * 设置热门小组为一般小组
     * 
     * @param groupId
     *            小组ID{@link java.lang.Long}
     * @return int
     */
    int cancelrecommendGroup(Long groupId);

    /**
     * 统计小组成员数量
     * 
     * @return
     */
    List<GroupVo> groupMemberById();

    /**
     * 设置小组
     * 
     * @param group
     *            小组对象{@link com.ningpai.group.bean.Group}
     * @param groupPermissions
     *            小组权限对象{@link com.ningpai.group.bean.GroupPermissions}
     * @return int
     */
    int updateGroup(Group group, GroupPermissions groupPermissions);
}
