package com.ningpai.topic.dao;

import com.ningpai.topic.bean.GroupTopicImg;

import java.util.List;

/**
 * 话题图片DAO
 * 
 * @author qiyuanyuan
 * 
 */
public interface GroupTopicImgMapper {
    /**
     * 根据主键删除
     * 
     * @param:topicImgId 主键{@link java.lang.Long}
     * @return:删除个数
     * @ibatorgenerated 2014-05-26 16:29:34
     */
    int deleteByPrimaryKey(Long topicImgId);

    /**
     * 插入，空属性也会插入
     * 
     * @param:groupTopicImg 对象{@link com.ningpai.topic.bean.GroupTopicImg}
     * @return:删除个数
     * @ibatorgenerated 2014-05-26 16:29:34
     */
    int insert(GroupTopicImg groupTopicImg);

    /**
     * 插入，空属性不会插入
     * 
     * @param:groupTopicImg 对象{@link com.ningpai.topic.bean.GroupTopicImg}
     * @return:删除个数
     * @ibatorgenerated 2014-05-26 16:29:34
     */
    int insertSelective(GroupTopicImg groupTopicImg);

    /**
     * 根据主键查询
     * 
     * @param:topicImgId 查询条件,主键值{@link java.lang.Long}
     * @return:对象
     * @ibatorgenerated 2014-05-26 16:29:34
     */
    GroupTopicImg selectByPrimaryKey(Long topicImgId);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 
     * @param:groupTopicImg 对象{@link com.ningpai.topic.bean.GroupTopicImg}
     * @return:成功修改个数
     * @ibatorgenerated 2014-05-26 16:29:34
     */
    int updateByPrimaryKeySelective(GroupTopicImg groupTopicImg);

    /**
     * 根据主键修改，空值条件会修改成null
     * 
     * @param:groupTopicImg 对象{@link com.ningpai.topic.bean.GroupTopicImg}
     * @return:成功修改个数
     * @ibatorgenerated 2014-05-26 16:29:34
     */
    int updateByPrimaryKey(GroupTopicImg groupTopicImg);

    /**
     * 批量添加小组话题附件
     * 
     * @param topicImgs
     * @return
     */
    int insertBatchSelective(List<GroupTopicImg> topicImgs);

    /**
     * 根据小组话题Id查询话题相关附件
     *
     * @param groupTopicId
     *            话题Id
     * @return 对象
     */
    List<GroupTopicImg> topicImgsList(Long groupTopicId);

    /**
     * 根据话题ID删除话题相关附件
     *
     * @param groupTopicId
     * @return
     */
    int deleteGroupImgById(Long groupTopicId);

    /**
     * 小组话题附件列表
     *
     * @param groupTopicId
     * @return
     */
    List<GroupTopicImg> allGroupTopicList(Long groupTopicId);

}
