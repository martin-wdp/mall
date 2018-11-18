package com.ningpai.mobile.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.mobile.bean.MobStorey;

/**
 * DAO-移动版楼层
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年8月21日下午8:00:14
 */
public interface MobStoreyMapper {
    /**
     * 删除
     * 
     * @param mobStoreyId
     * @return
     */
    int deleteByPrimaryKey(Long mobStoreyId);

    /**
     * 添加
     * 
     * @param record
     * @return
     */
    int insert(MobStorey record);

    /**
     * 添加-字段可选
     * 
     * @param record
     * @return
     */
    int insertSelective(MobStorey record);

    /**
     * 修改-字段可选
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(MobStorey record);

    /**
     * 修改
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKey(MobStorey record);

    /**
     * 根据ID查询移动版楼层
     * 
     * @param mobStoreyId
     * @return
     */
    MobStorey selectByPrimaryKey(Long mobStoreyId);

    /**
     * 查询所有楼层数量，分页用
     * 
     * @return
     */
    int selectCount();

    /**
     * 分页查询楼层
     * 
     * @param map
     * @return
     */
    List<Object> selectByPb(Map<String, Object> map);

    /**
     * 查询所有楼层，前台展示用
     * 
     * @return
     */
    List<MobStorey> selectAllForSite();
}
