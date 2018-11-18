package com.ningpai.thirdaudit.mapper;

import com.ningpai.thirdaudit.bean.ThirdStorePoint;
/**
 * 商家信息接口
 *
 * */
public interface ThirdStorePointMapper {
    /**
     * 根据主键删除
     *
     * @param storePointId
     * */
    int deleteByPrimaryKey(Long storePointId);
    /**
     * 插入一条记录
     *
     * @param record
     * */
    int insert(ThirdStorePoint record);
    /**
     * 插入记录
     *
     * @param record
     * */
    int insertSelective(ThirdStorePoint record);
    /**
     * 根据主键查询
     *
     * @param storePointId
     * */
    ThirdStorePoint selectByPrimaryKey(Long storePointId);
    /**
     * 修改记录
     *
     * @param record
     * */
    int updateByPrimaryKeySelective(ThirdStorePoint record);
    /**
     * 根据主键修改
     *
     * @param record
     * */
    int updateByPrimaryKey(ThirdStorePoint record);
}
