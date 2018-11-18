package com.ningpai.group.dao;

import com.ningpai.group.bean.LabelGroup;

/**
 * 小组
 * 
 * @author qiyuanyuan
 * 
 */
public interface LabelGroupMapper {
    /**
     * 根据主键删除 参数:主键 返回:删除个数
     * 
     * @ibatorgenerated 2014-10-21 17:15:56
     */
    int deleteByPrimaryKey(Long labelGroupId);

    /**
     * 插入，空属性也会插入 参数:pojo对象 返回:删除个数
     * 
     * @ibatorgenerated 2014-10-21 17:15:56
     */
    int insert(LabelGroup record);

    /**
     * 插入，空属性不会插入 参数:pojo对象 返回:删除个数
     * 
     * @ibatorgenerated 2014-10-21 17:15:56
     */
    int insertSelective(LabelGroup record);

    /**
     * 根据主键查询 参数:查询条件,主键值 返回:对象
     * 
     * @ibatorgenerated 2014-10-21 17:15:56
     */
    LabelGroup selectByPrimaryKey(Long labelGroupId);

    /**
     * 根据主键修改，空值条件不会修改成null 参数:1.要修改成的值 返回:成功修改个数
     * 
     * @ibatorgenerated 2014-10-21 17:15:56
     */
    int updateByPrimaryKeySelective(LabelGroup record);

    /**
     * 根据主键修改，空值条件会修改成null 参数:1.要修改成的值 返回:成功修改个数
     * 
     * @ibatorgenerated 2014-10-21 17:15:56
     */
    int updateByPrimaryKey(LabelGroup record);

    /**
     * 根据小组Id删除小组
     * 
     * @param groupId
     * @return
     */
    int deleteByGroupId(Long groupId);
}
