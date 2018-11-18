package com.ningpai.third.auth.mapper;

import com.ningpai.third.auth.bean.ThirdPage;
/**
 * <p>第三方管理员底层</p>
 * @author zhanghl
 * @since 20150730
 * @version 2.0
 */
public interface ThirdPageMapper {
    /**
     * 根据ID删除管理关系
     * @param id 管理关系ID
     * @return
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 新增一条权限关系对象
     * @param record 权限关系对象
     * @return
     */
    int insert(ThirdPage record);
    /**
     * 新增一条权限关系对象
     * @param record 权限关系对象
     * @return
     */
    int insertSelective(ThirdPage record);

    /**
     * 根据ID查询权限关系对象
     * @param id 权限关系对象id
     * @return
     */
    ThirdPage selectByPrimaryKey(Long id);

    /**
     * 修改权限关系对象
     * @param record 权限关系对象
     * @return
     */
    int updateByPrimaryKeySelective(ThirdPage record);

    /**
     * 修改权限关系对象
     * @param record 权限关系对象
     * @return
     */
    int updateByPrimaryKey(ThirdPage record);
}
