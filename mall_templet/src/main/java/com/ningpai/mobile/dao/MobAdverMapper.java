package com.ningpai.mobile.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.mobile.bean.MobAdver;

/**
 * DAO-移动版广告
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年8月21日上午10:36:12
 */
public interface MobAdverMapper {
    /**
     * 根据ID删除广告
     * 
     * @param modAdverId
     * @return
     */
    int deleteByPrimaryKey(Long modAdverId);

    /**
     * 批量删除
     * 
     * @param ids
     * @return
     */
    int batchDelMobAdver(List<Long> ids);

    /**
     * 添加广告
     * 
     * @param record
     * @return
     */
    int insert(MobAdver record);

    /**
     * 添加广告-字段可选
     * 
     * @param record
     * @return
     */
    int insertSelective(MobAdver record);

    /**
     * 修改-字段可选
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(MobAdver record);

    /**
     * 修改
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKey(MobAdver record);

    /**
     * 根据ID查询
     * 
     * @param modAdverId
     * @return
     */
    MobAdver selectByPrimaryKey(Long modAdverId);

    /**
     * 根据楼层ID查询未删除的广告数量，用于分页，也可验证是否可删除楼层
     * 
     * @param storeyId
     * @return
     */
    int selectCountByStoreyId(Long storeyId);

    /**
     * 根据楼层ID分页查询未删除的广告
     * 
     * @param map
     * @return
     */
    List<Object> selectByStoreyIdAndPb(Map<String, Object> map);

    /**
     * 根据楼层ID查询，未删除、已启用的广告，前台展示用
     * 
     * @param storeyId
     * @return
     */
    List<MobAdver> selectByStoreyIdForSite(Long storeyId);

    /**
     * 根据楼层ID删除广告
     * 
     * @param storeyId
     * @return
     */
    int deleteByStoreyId(Long storeyId);
}
