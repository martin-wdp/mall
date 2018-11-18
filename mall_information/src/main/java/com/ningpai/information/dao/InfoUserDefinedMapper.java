package com.ningpai.information.dao;

import java.util.List;

import com.ningpai.information.bean.InfoUserDefined;

/**
 * DAO-文章自定义属性
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年3月26日下午5:00:43
 */
public interface InfoUserDefinedMapper {
    /**
     * 根据主键删除
     * 
     * @param infoUdId
     * @return
     */
    int deleteByPrimaryKey(Long infoUdId);

    /**
     * 添加
     * 
     * @param record
     * @return
     */
    int insert(InfoUserDefined record);

    /**
     * 添加-字段可为null
     * 
     * @param record
     * @return
     */
    int insertSelective(InfoUserDefined record);

    /**
     * 修改-字段可为null
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(InfoUserDefined record);

    /**
     * 修改
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKey(InfoUserDefined record);

    /**
     * 根据主键查询
     * 
     * @param infoUdId
     * @return
     */
    InfoUserDefined selectByPrimaryKey(Long infoUdId);

    /**
     * 查询所有
     * 
     * @return
     */
    List<InfoUserDefined> selectAll();
}
