package com.ningpai.group.dao;

import com.ningpai.group.bean.GroupType;

import java.util.List;
import java.util.Map;

/**
 * 小组类型DAO
 * 
 * @author qiyuanyuan
 * 
 */
public interface GroupTypeMapper {
    /**
     * 根据主键删除
     * 
     * @param:groupTypeId 主键{@link java.lang.Long}
     * @return:删除个数
     * @ibatorgenerated 2014-05-26 16:29:37
     */
    int deleteByPrimaryKey(Long groupTypeId);

    /**
     * 插入，空属性也会插入
     * 
     * @param:groupType 对象{@link com.ningpai.group.bean.GroupType}
     * @return:删除个数
     * @ibatorgenerated 2014-05-26 16:29:37
     */
    int insert(GroupType groupType);

    /**
     * 插入，空属性不会插入
     * 
     * @param:groupType 对象{@link com.ningpai.group.bean.GroupType}
     * @return:删除个数
     * @ibatorgenerated 2014-05-26 16:29:37
     */
    int insertSelective(GroupType groupType);

    /**
     * 根据主键查询
     * 
     * @param:groupTypeId 查询条件,主键值{@link java.lang.Long}
     * @return:对象
     * @ibatorgenerated 2014-05-26 16:29:37
     */
    GroupType selectByPrimaryKey(Long groupTypeId);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 
     * @param:groupType 对象{@link com.ningpai.group.bean.GroupType}
     * @return:成功修改个数
     * @ibatorgenerated 2014-05-26 16:29:37
     */
    int updateByPrimaryKeySelective(GroupType groupType);

    /**
     * 根据主键修改，空值条件会修改成null
     * 
     * @param:groupType 对象{@link com.ningpai.group.bean.GroupType}
     * @return:成功修改个数
     * @ibatorgenerated 2014-05-26 16:29:37
     */
    int updateByPrimaryKey(GroupType groupType);

    /**
     * 根据小组分类主键删除小组（更新小组分类的delFlag）
     * 
     * @param groupTypeId
     *            小组分类ID{@link java.lang.Long}
     * @return
     */
    int deleteByGroupTypeId(Long groupTypeId);

    /**
     * 恢复小组分类
     * 
     * @param groupTypeId
     *            小组分类ID{@link java.lang.Long}
     * @return
     */
    int updateDisStatusById(Long groupTypeId);

    /**
     * 
     * @param groupTypeId
     * @return
     */
    int updateStatusById(Long groupTypeId);

    /**
     * 查询所有的小组分类
     * 
     * @return List
     */
    List<GroupType> selectAll();

    /**
     * 按条件查询小组分类
     * 
     * @param groupType
     *            小组分类对象 {@link com.ningpai.group.bean.GroupType}
     * @return List
     */
    List<Object> selectGrouptype(GroupType groupType);

    /**
     * 按条件查询会员 返回数量
     * 
     * @param groupType
     *            {@link com.ningpai.group.bean.GroupType}
     * @return Long
     */
    Long selectGroupTypeSize(GroupType groupType);

    /**
     * 分页查询小组分类
     * 
     * @param paramMap
     *            查询参数
     * @return List
     */
    List<Object> selectGroupTypeByLimit(Map<String, Integer> paramMap);

    /**
     * 根据分类名称查询分类小组
     * 
     * @param typeName
     *            分类名称 {@link java.lang.String}
     * @return 对象
     */
    GroupType queryTypeByTypeName(String typeName);

}
