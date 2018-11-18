package com.ningpai.system.dao;

import java.util.List;

import com.ningpai.system.bean.Floor;

/**
 * 楼层层数接口
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2014年1月6日 上午11:13:02
 * @version 1.0
 */
public interface FloorMapper {
    /**
     * 删除楼层层数
     * 
     * @param floorId
     * @return
     */
    int deleteByPrimaryKey(Long floorId);

    /**
     * 添加楼层层数
     * 
     * @param record
     * @return
     */
    int insert(Floor record);

    /**
     * 添加楼层层数--可选字段
     * 
     * @param record
     * @return
     */
    int insertSelective(Floor record);

    /**
     * 查询单个楼层层数
     * 
     * @param floorId
     * @return
     */
    Floor selectByPrimaryKey(Long floorId);

    /**
     * 修改楼层层数--可选字段
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Floor record);

    /**
     * 修改楼层层数
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKey(Floor record);

    /**
     * 查询所有楼层层数信息
     * 
     * @return List
     */
    List<Floor> findAll();
}
