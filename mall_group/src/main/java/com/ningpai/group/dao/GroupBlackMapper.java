package com.ningpai.group.dao;

import java.util.List;

import com.ningpai.group.bean.GroupBlack;

/**
 * 小组黑名单DAO
 * 
 * @author qiyuanyuan
 * 
 */
public interface GroupBlackMapper {
      /**
     * 根据主键删除
     * @param:blackId 主键{@link java.lang.Long }
     * @return:删除个数
     * @ibatorgenerated 2014-05-26 16:29:40
     */
    int deleteByPrimaryKey(Long blackId);

    /**
     * 插入，空属性也会插入
     * @param:groupBlack 对象{@link com.ningpai.group.bean.GroupBlack }
     * @return:删除个数
     * @ibatorgenerated 2014-05-26 16:29:40
     */
    int insert(GroupBlack groupBlack);

    /**
     * 插入，空属性不会插入
     * @param:groupBlack 对象{@link com.ningpai.group.bean.GroupBlack }
     * @return:删除个数
     * @ibatorgenerated 2014-05-26 16:29:40
     */
    int insertSelective(GroupBlack groupBlack);

    /**
     * 根据主键查询
     * @param:blackId,主键值{@link java.lang.Long }
     * @return:对象
     * @ibatorgenerated 2014-05-26 16:29:40
     */
    GroupBlack selectByPrimaryKey(Long blackId);

    /**
     * 根据主键修改，空值条件不会修改成null
     * @param:groupBlack 对象{@link com.ningpai.group.bean.GroupBlack }
     * @return:成功修改个数
     * @ibatorgenerated 2014-05-26 16:29:40
     */
    int updateByPrimaryKeySelective(GroupBlack groupBlack);

    /**
     * 根据主键修改，空值条件会修改成null
     * @param:groupBlack 对象{@link com.ningpai.group.bean.GroupBlack }
     * @return:成功修改个数
     * @ibatorgenerated 2014-05-26 16:29:40
     */
    int updateByPrimaryKey(GroupBlack groupBlack);
    
    /**
     * 黑名单列表
     * 
     * @param groupBlack  对象{@link com.ningpai.group.bean.GroupBlack }
     * @return
     */
    List<Object> blackList(GroupBlack groupBlack);
    
    /**
     * 黑名单总数
     * 
     * @param groupBlack  对象{@link com.ningpai.group.bean.GroupBlack }
     * @return
     */
    Long blackListSize(GroupBlack groupBlack);
    
    /**
     * 解除黑名单
     * 
     * @param groupBlack
     * @return
     */
    int dissolvedBlack(GroupBlack groupBlack);
    
    /**
     * 根据小组Id和用户Id查询黑名单
     * 
     * @param groupBlack
     * @return
     */
    GroupBlack selectById(GroupBlack groupBlack);
}
