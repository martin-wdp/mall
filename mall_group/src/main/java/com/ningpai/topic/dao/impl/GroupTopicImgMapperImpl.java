/**
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.topic.dao.impl;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.topic.bean.GroupTopicImg;
import com.ningpai.topic.dao.GroupTopicImgMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 话题附件实现
 * 
 * @version 2014年5月27日 下午1:52:35
 * @author qiyuanyuan
 */

@Repository("GroupTopicImgMapper")
public class GroupTopicImgMapperImpl extends BasicSqlSupport implements GroupTopicImgMapper {

    /**
     * 根据主键删除
     *
     * @param:topicImgId 主键{@link java.lang.Long}
     * @return:删除个数
     * @ibatorgenerated 2014-05-26 16:29:34
     */
    public int deleteByPrimaryKey(Long topicImgId) {
        return this.delete("com.ningpai.topic.mapper.GroupTopicImgMapper.deleteByPrimaryKey", topicImgId);
    }

    /**
     * 插入，空属性也会插入
     *
     * @param:groupTopicImg 对象{@link com.ningpai.topic.bean.GroupTopicImg}
     * @return:删除个数
     * @ibatorgenerated 2014-05-26 16:29:34
     */
    public int insert(GroupTopicImg groupTopicImg) {
        return this.insert("com.ningpai.topic.mapper.GroupTopicImgMapper.insert", groupTopicImg);
    }

    /**
     * 插入，空属性不会插入
     *
     * @param:groupTopicImg 对象{@link com.ningpai.topic.bean.GroupTopicImg}
     * @return:删除个数
     * @ibatorgenerated 2014-05-26 16:29:34
     */
    public int insertSelective(GroupTopicImg groupTopicImg) {
        return this.insert("com.ningpai.topic.mapper.GroupTopicImgMapper.insertSelective", groupTopicImg);
    }

    /**
     * 根据主键查询
     *
     * @param:topicImgId 查询条件,主键值{@link java.lang.Long}
     * @return:对象
     * @ibatorgenerated 2014-05-26 16:29:34
     */
    public GroupTopicImg selectByPrimaryKey(Long topicImgId) {
        return this.selectOne("com.ningpai.topic.mapper.GroupTopicImgMapper.selectByPrimaryKey", topicImgId);
    }

    /**
     * 根据主键修改，空值条件不会修改成null
     *
     * @param:groupTopicImg 对象{@link com.ningpai.topic.bean.GroupTopicImg}
     * @return:成功修改个数
     * @ibatorgenerated 2014-05-26 16:29:34
     */
    public int updateByPrimaryKeySelective(GroupTopicImg groupTopicImg) {
        return this.update("com.ningpai.topic.mapper.GroupTopicImgMapper.updateByPrimaryKeySelective", groupTopicImg);
    }

    /**
     * 根据主键修改，空值条件会修改成null
     *
     * @param:groupTopicImg 对象{@link com.ningpai.topic.bean.GroupTopicImg}
     * @return:成功修改个数
     * @ibatorgenerated 2014-05-26 16:29:34
     */
    public int updateByPrimaryKey(GroupTopicImg groupTopicImg) {
        return this.update("com.ningpai.topic.mapper.GroupTopicImgMapper.updateByPrimaryKey", groupTopicImg);
    }

    /**
     * 批量添加小组话题附件
     *
     * @param topicImgs
     * @return
     */
    public int insertBatchSelective(List<GroupTopicImg> topicImgs) {
        return this.insert("com.ningpai.topic.mapper.GroupTopicImgMapper.insertBatchSelective", topicImgs);
    }

    /**
     * 根据小组话题Id查询话题相关附件
     *
     * @param groupTopicId
     *            话题Id
     * @return 对象
     */
    public List<GroupTopicImg> topicImgsList(Long groupTopicId) {
        return this.selectList("com.ningpai.topic.mapper.GroupTopicImgMapper.topicImgList", groupTopicId);
    }

    /**
     * 根据话题ID删除话题相关附件
     *
     * @param groupTopicId
     * @return
     */
    public int deleteGroupImgById(Long groupTopicId) {
        return this.update("com.ningpai.topic.mapper.GroupTopicImgMapper.deleteGroupImgById", groupTopicId);
    }

    /**
     * 小组话题附件列表
     *
     * @param groupTopicId
     * @return
     */
    public List<GroupTopicImg> allGroupTopicList(Long groupTopicId) {
        return this.selectList("com.ningpai.topic.mapper.GroupTopicImgMapper.alltopicImgList", groupTopicId);
    }

}
