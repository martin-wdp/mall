package com.ningpai.information.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.information.bean.InfoOPTag;

/**
 * DAO-单页标签
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年4月10日下午1:29:26
 */
public interface InfoOPTagMapper {
    /**
     * 根据主键删除
     * 
     * @param infoopTagId
     * @return
     */
    int deleteByPrimaryKey(Long infoopTagId);

    /**
     * 添加
     * 
     * @param record
     * @return
     */
    int insert(InfoOPTag record);

    /**
     * 添加-字段可选
     * 
     * @param record
     * @return
     */
    int insertSelective(InfoOPTag record);

    /**
     * 修改-字段可选
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(InfoOPTag record);

    /**
     * 修改
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKey(InfoOPTag record);

    /**
     * 根据主键查询
     * 
     * @param infoopTagId
     * @return
     */
    InfoOPTag selectByPrimaryKey(Long infoopTagId);

    /**
     * 根据模板ID查询
     * 
     * @return
     */
    List<InfoOPTag> selectAll(String tempId);

    /**
     * 查询所有
     * 
     * @return
     */
    List<InfoOPTag> findAll();

    /**
     * 分页查询所有
     *
     * @return
     */
    List<Object> findAllPage(Map<String, Object> map);
    /**
     * 查询总条数
     *
     * @return
     */
    int findAllPagecount();
}
