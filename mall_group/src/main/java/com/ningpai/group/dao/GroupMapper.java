package com.ningpai.group.dao;

import com.ningpai.group.bean.Group;
import com.ningpai.group.bean.GroupLabel;
import com.ningpai.group.vo.GroupVo;

import java.util.List;
import java.util.Map;

/**
 * 小组DAO
 * 
 * @author qiyuanyuan
 * 
 */
public interface GroupMapper {
    /**
     * 根据主键删除
     * 
     * @param:groupId 主键{@link java.lang.Long}
     * @return:删除个数
     * @ibatorgenerated 2014-05-26 16:29:41
     */
    int deleteByGroupId(Long groupId);

    /**
     * 插入，空属性也会插入
     * 
     * @param:group 对象{@link com.ningpai.group.bean.Group}
     * @return:删除个数
     * @ibatorgenerated 2014-05-26 16:29:41
     */
    int insert(Group group);

    /**
     * 插入，空属性不会插入
     * 
     * @param:group 对象{@link com.ningpai.group.bean.Group}
     * @return:删除个数
     * @ibatorgenerated 2014-05-26 16:29:41
     */
    int insertSelective(Group group);

    /**
     * 根据主键查询
     * 
     * @param:groupId查询条件,主键值{@link java.lang.Long}
     * @return:对象
     * @ibatorgenerated 2014-05-26 16:29:41
     */
    Group selectByPrimaryKey(Long groupId);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 
     * @param:group 对象{@link com.ningpai.group.bean.Group}
     * @return:成功修改个数
     * @ibatorgenerated 2014-05-26 16:29:41
     */
    int updateByPrimaryKeySelective(Group group);

    /**
     * 根据主键修改，空值条件会修改成null
     * 
     * @param:group 对象{@link com.ningpai.group.bean.Group}
     * @return:成功修改个数
     * @ibatorgenerated 2014-05-26 16:29:41
     */
    int updateByPrimaryKey(Group group);

    /**
     * 查询活跃小组
     * 
     * @return List
     */
    List<GroupVo> findGroupList(GroupVo groupVo);

    /**
     * 查询所有小组
     * 
     * @return List
     */
    /*
     * List<GroupVo> findAllGroup();
     */

    /**
     * 查询所有小组记录
     * 
     * @return int
     */
    int queryTotalCount();

    /**
     * 根据分页参数查询分页列表
     * 
     * @param map 封装好的map参数
     *            包括开始的条数和结束的条数
     * @return 查询到的实体列表
     */
    List<Object> selectGroupByLimit(Map<String, Integer> map);

    /**
     * 后台分页显示小组列表
     * 
     * @param  map 对象
     *            {@link com.ningpai.group.vo.GroupVo}
     * @return List
     */
    List<Object> selectGroup(Map<String, Object> map);

    /**
     * 查询分页列表总数
     * 
     * @param  map gromapup
     *            对象{@link com.ningpai.group.bean.Group}
     * @return int
     */
    int selectGroupSize(Map<String, Object> map);

    /**
     * 后台解散小组
     * 
     * @param groupId
     *            小组ID{@link java.lang.Long}
     * @return int
     */
    int dissolveGroupById(Long groupId);

    /**
     * 后台审核通过小组
     * 
     * @param groupId
     *            小组ID{@link java.lang.Long}
     * @return int
     */
    int passGroupById(Long groupId);

    /**
     * 后台拒绝待审核的小组
     * 
     * @param group
     *            小组
     * @return int
     */
    int refuseGroupById(Group group);

    /**
     * 根据小组ID查询小组信息
     * 
     * @param groupId
     *            小组ID{@link java.lang.Long}
     * @return GroupVo
     */
    GroupVo selectGroupVoByGroupId(Long groupId);

    /**
     * 后台设置活跃小组
     * 
     * @param groupId
     *            小组ID{@link java.lang.Long}
     * @return int
     */
    int activeGroupById(Long groupId);

    /**
     * 后台设置活跃小组为一般小组
     * 
     * @param groupId
     *            小组ID{@link java.lang.Long}
     * @return int
     */
    int commonGroupId(Long groupId);

    /**
     * 后台设置热门小组
     * 
     * @param groupId
     *            小组ID{@link java.lang.Long}
     * @return int
     */
    int hotGroupById(Long groupId);

    /**
     * 后台设置热门小组为一般小组
     * 
     * @param groupId
     *            小组ID{@link java.lang.Long}
     * @return int
     */
    int cancelHotGroupId(Long groupId);

    /**
     * 后台设置特别推荐小组
     * 
     * @param groupId
     *            小组ID{@link java.lang.Long}
     * @return int
     */
    int recommendGroupById(Long groupId);

    /**
     * 后台取特别推荐小组
     * 
     * @param groupId
     *            小组ID{@link java.lang.Long}
     * @return int
     */
    int cancelRecommendGroupId(Long groupId);

    /**
     * 统计小组成员数量
     * 
     * @return int
     */
    List<GroupVo> groupMemberById();

    /**
     * 小组数量
     * 
     * @return long
     */
    long groupCount();

    /**
     * 小组成员总数
     * 
     * @return long
     */
    long groupMember();

    /**
     * 去除小组背景
     * 
     * @param group
     *            小组对象 {@link com.ningpai.group.bean.Group}
     * @return int
     */
    int editGroupbg(GroupVo group);

    /**
     * 我管理的小组
     * 
     * @return List
     */
    List<GroupVo> myManagerGroupList(GroupVo groupVo);

    /**
     * 我管理的小组
     * 
     * @return List
     */
    List<Object> myManagerGroupList(Map<String, Object> paramMap);

    /**
     * 我管理的小组的数量
     * 
     * @return int
     */
    int myManagerGroupCount(Map<String, Object> paramMap);

    /**
     * 我加入的小组
     * 
     * @return
     */
    List<Object> myJoinedGroupList(Map<String, Object> paramMap);

    /**
     * 我加入的小组的数量
     * 
     * @return
     */
    int myJoinedGroupCount(Map<String, Object> paramMap);

    /**
     * 根据分类ID查询小组标签
     * 
     * @param paramMap groupTypeId
     *            分类ID{@link java.lang.Long}
     * @return list
     */
    List<GroupLabel> groupLabelList(Map<String, Object> paramMap);

    /**
     * 根据用户Id查询他加入的小组
     * 
     * @param customerId
     *            用户ID{@link java.lang.Long}
     * @return
     */
    List<Group> joinedGroup(Long customerId);

    /**
     * 根据小组标签和小组类型查询小组
     * 
     * @param paramMap
     * @return
     */
    List<Object> labelGroup(Map<String, Object> paramMap);

    /**
     * 根据小组标签和小组类型查询小组数目
     * 
     * @param paramMap
     * @return int
     */
    int labelGroupCount(Map<String, Object> paramMap);

}
