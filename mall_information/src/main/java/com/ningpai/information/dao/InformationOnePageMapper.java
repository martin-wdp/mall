package com.ningpai.information.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.information.bean.InformationOnePage;

/**
 * 资讯单页DAO
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年02月24日 17:05:10
 * @version
 */
public interface InformationOnePageMapper {
    /**
     * 根据主键删除
     * 
     * @param infoOPId
     * @return
     */
    int deleteByPrimaryKey(Long infoOPId);

    /**
     * 添加资讯单页
     * 
     * @param record
     * @return
     */
    int insert(InformationOnePage record);

    /**
     * 添加资讯单页-字段可选
     * 
     * @param record
     * @return
     */
    int insertSelective(InformationOnePage record);

    /**
     * 更新资讯单页-字段可选
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(InformationOnePage record);

    /**
     * 更新资讯单页-包含内容
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKeyWithBLOBs(InformationOnePage record);

    /**
     * 更新资讯单页
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKey(InformationOnePage record);

    /**
     * 根据主键查询
     * 
     * @param infoOPId
     * @return
     */
    InformationOnePage selectByPrimaryKey(Long infoOPId);

    /**
     * 根据map参数查询所有行数
     * 
     * @param map
     * @return
     */
    Integer queryTotalCount(Map<String, Object> map);

    /**
     * 根据分页参数查询资讯单页列表
     * 
     * @param map
     * @return
     */
    List<Object> queryByPageBean(Map<String, Object> map);

    /**
     * 根据单页标题查询单页数量
     * 
     * @param title
     * @return
     */
    Integer selectInfoOPCountByTitle(String title);

    /**
     * 根据标签查询单页
     * 
     * @param map
     * @return
     */
    List<InformationOnePage> selectByTempTag(Map<String, Object> map);

    /**
     * 根据单页标签查询单页数量，判断是否可删除标签
     * 
     * @param infoOPTagId
     * @return
     */
    Integer selectInfoOPCountByTag(Long infoOPTagId);

    /**
     * 删除单页标签时，根据单页标签修改单页
     * 
     * @param infoOPTagId
     * @return
     */
    int updateTagByTagId(Long infoOPTagId);
}
