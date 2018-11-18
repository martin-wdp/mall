package com.ningpai.group.dao.impl;

import org.springframework.stereotype.Repository;

import com.ningpai.group.bean.LabelGroup;
import com.ningpai.group.dao.LabelGroupMapper;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * 小组标签级联
 * 
 * @author qiyuanyuan
 * 
 */
@Repository("LabelGroupMapper")
public class LabelGroupMapperImpl extends BasicSqlSupport implements LabelGroupMapper {

    /**
     * 根据主键删除 参数:主键 返回:删除个数
     *
     * @ibatorgenerated 2014-10-21 17:15:56
     */
    public int deleteByPrimaryKey(Long labelGroupId) {

        return this.delete("com.ningpai.dao.LabelGroupMapper.deleteByPrimaryKey", labelGroupId);
    }

    /**
     * 插入，空属性也会插入 参数:pojo对象 返回:删除个数
     *
     * @ibatorgenerated 2014-10-21 17:15:56
     */
    public int insert(LabelGroup labelGroup) {

        return this.insert("com.ningpai.dao.LabelGroupMapper.insert", labelGroup);
    }

    /**
     * 插入，空属性不会插入 参数:pojo对象 返回:删除个数
     *
     * @ibatorgenerated 2014-10-21 17:15:56
     */
    public int insertSelective(LabelGroup labelGroup) {

        return this.insert("com.ningpai.dao.LabelGroupMapper.insertSelective", labelGroup);
    }

    /**
     * 根据主键查询 参数:查询条件,主键值 返回:对象
     *
     * @ibatorgenerated 2014-10-21 17:15:56
     */
    public LabelGroup selectByPrimaryKey(Long labelGroupId) {

        return this.selectOne("com.ningpai.dao.LabelGroupMapper.selectByPrimaryKey", labelGroupId);
    }

    /**
     * 根据主键修改，空值条件不会修改成null 参数:1.要修改成的值 返回:成功修改个数
     *
     * @ibatorgenerated 2014-10-21 17:15:56
     */
    public int updateByPrimaryKeySelective(LabelGroup labelGroup) {

        return this.update("com.ningpai.dao.LabelGroupMapper.updateByPrimaryKeySelective", labelGroup);
    }
    /**
     * 根据主键修改，空值条件会修改成null 参数:1.要修改成的值 返回:成功修改个数
     *
     * @ibatorgenerated 2014-10-21 17:15:56
     */
    public int updateByPrimaryKey(LabelGroup labelGroup) {

        return this.update("com.ningpai.dao.LabelGroupMapper.updateByPrimaryKey", labelGroup);
    }

    /**
     * 根据小组Id删除小组
     *
     * @param groupId
     * @return
     */
    public int deleteByGroupId(Long groupId) {
        return this.update("com.ningpai.dao.LabelGroupMapper.deleteByGroupId", groupId);
    }
}
