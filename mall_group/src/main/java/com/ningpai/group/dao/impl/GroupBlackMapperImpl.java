/**
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.group.dao.impl;

import com.ningpai.group.bean.GroupBlack;
import com.ningpai.group.dao.GroupBlackMapper;
import com.ningpai.manager.base.BasicSqlSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 小组黑名单实现
 * 
 * @version 2014年5月27日 下午2:23:01
 * @author qiyuanyuan
 */
@Repository("GroupBlackMapper")
public class GroupBlackMapperImpl extends BasicSqlSupport implements GroupBlackMapper {

    /**
     * 根据主键删除
     * @param:blackId 主键{@link java.lang.Long }
     * @return:删除个数
     * @ibatorgenerated 2014-05-26 16:29:40
     */
    public int deleteByPrimaryKey(Long blackId) {
        return this.delete("com.ningpai.group.mapper.GroupBlackMapper.deleteByPrimaryKey", blackId);
    }

    /**
     * 插入，空属性也会插入
     * @param:groupBlack 对象{@link com.ningpai.group.bean.GroupBlack }
     * @return:删除个数
     * @ibatorgenerated 2014-05-26 16:29:40
     */
    public int insert(GroupBlack groupBlack) {
        return this.insert("com.ningpai.group.mapper.GroupBlackMapper.insert", groupBlack);
    }

    /**
     * 插入，空属性不会插入
     * @param:groupBlack 对象{@link com.ningpai.group.bean.GroupBlack }
     * @return:删除个数
     * @ibatorgenerated 2014-05-26 16:29:40
     */
    public int insertSelective(GroupBlack groupBlack) {
        return this.insert("com.ningpai.group.mapper.GroupBlackMapper.insertSelective", groupBlack);
    }

    /**
     * 根据主键查询
     * @param:blackId,主键值{@link java.lang.Long }
     * @return:对象
     * @ibatorgenerated 2014-05-26 16:29:40
     */
    public GroupBlack selectByPrimaryKey(Long blackId) {
        return this.selectOne("com.ningpai.group.mapper.GroupBlackMapper.selectByPrimaryKey", blackId);
    }
    /**
     * 根据主键修改，空值条件不会修改成null
     * @param:groupBlack 对象{@link com.ningpai.group.bean.GroupBlack }
     * @return:成功修改个数
     * @ibatorgenerated 2014-05-26 16:29:40
     */
    public int updateByPrimaryKeySelective(GroupBlack groupBlack) {
        return this.update("com.ningpai.group.mapper.GroupBlackMapper.updateByPrimaryKeySelective", groupBlack);
    }

    /**
     * 根据主键修改，空值条件会修改成null
     * @param:groupBlack 对象{@link com.ningpai.group.bean.GroupBlack }
     * @return:成功修改个数
     * @ibatorgenerated 2014-05-26 16:29:40
     */
    public int updateByPrimaryKey(GroupBlack groupBlack) {
        return this.update("com.ningpai.group.mapper.GroupBlackMapper.updateByPrimaryKey", groupBlack);
    }

    /**
     *  黑名单列表
     * 
     * @see com.ningpai.group.dao.GroupBlackMapper#blackList(com.ningpai.group.bean .GroupBlack)
     */
    public List<Object> blackList(GroupBlack groupBlack) {
        return this.selectList("com.ningpai.group.mapper.GroupBlackMapper.blackList", groupBlack);
    }

    /**
     * 黑名单总数
     *
     * @param groupBlack  对象{@link com.ningpai.group.bean.GroupBlack }
     * @return
     */
    public Long blackListSize(GroupBlack groupBlack) {
        return this.selectOne("com.ningpai.group.mapper.GroupBlackMapper.blackListSize", groupBlack);
    }


    /**
     * 黑名单总数
     *
     * @param groupBlack  对象{@link com.ningpai.group.bean.GroupBlack }
     * @return
     */
    public int dissolvedBlack(GroupBlack groupBlack) {
        return this.update("com.ningpai.group.mapper.GroupBlackMapper.dissolvedBlack", groupBlack);
    }


    /**
     * 根据小组Id和用户Id查询黑名单
     *
     * @param groupBlack
     * @return
     */
    public GroupBlack selectById(GroupBlack groupBlack) {
        return this.selectOne("com.ningpai.group.mapper.GroupBlackMapper.selectBlackById", groupBlack);
    }

}
