package com.ningpai.mobile.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.mobile.bean.MobSinglepage;

/**
 * 移动版单页dao层接口
 * 
 * @author zhangsl
 * @since 2014年11月21日 下午5:51:18
 * @version 0.0.1
 */
public interface MobSinglepageMapper {
    /**
     * 根据主键Id删除移动版单页信息
     * 
     * @param singlepageId
     * @return
     */
    int deleteByPrimaryKey(Long singlepageId);

    /**
     * 添加移动版单页信息
     * 
     * @param record
     * @return
     */
    int insert(MobSinglepage record);

    /**
     * 添加移动版单页信息(可选择性的添加)
     * 
     * @param record
     * @return
     */
    int insertSelective(MobSinglepage record);

    /**
     * 根据singlepageId查询移动版单页信息
     * 
     * @param singlepageId
     * @return
     */
    MobSinglepage selectByPrimaryKey(Long singlepageId);

    /**
     * 根据singlepageId修改移动版单页信息
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(MobSinglepage record);

    /**
     * 修改移动版单页信息(包括修改数据类型为longtext的content字段)
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKeyWithBLOBs(MobSinglepage record);

    /**
     * 根据主键ID修改移动版单页信息
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKey(MobSinglepage record);

    /**
     * 分页查询移动版单页信息
     * 
     * @param paraMap
     *            分页参数
     * @return
     */
    List<Object> queryMobByPage(Map<Object, Object> paraMap);

    /**
     * 移动版单页与移动版单页标签左连接查询 分页查询
     * 
     * @param paraMap
     * @return
     */
    List<Object> newQueryMobByPage(Map<Object, Object> paraMap);

    /**
     * 查询移动单页列表的总条数
     * 
     * @return
     */
    int queryMobAllCount(Map<Object, Object> paraMap);

    /**
     * 逻辑删除 根据singlepageId修改delflag状态 0:未删除 1：已删除
     * 
     * @param singlepageId
     * @return
     */
    int updatedelstatus(Long singlepageId);

    /**
     * 根据MarkId查询符合条件的总条数
     * 
     * @param markId
     * @return
     */
    int queryCountByMarkId(Long markId);

    /**
     * 新查询总条数 移动版单页与移动版单页标签左连接 条件查询总记录数
     * 
     * @param paraMap
     * @return
     */
    int newQueryMobAllCount(Map<Object, Object> paraMap);

}
