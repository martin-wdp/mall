package com.ningpai.system.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.system.bean.ErrorPage;

/**
 * 异常页面DAO
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年2月25日 15:51:25
 * @version 1.0
 */
public interface ErrorPageMapper {
    /**
     * 根据主键删除
     * 
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 添加异常页面
     * 
     * @param record
     * @return
     */
    int insert(ErrorPage record);

    /**
     * 添加异常页面-字段可选
     * 
     * @param record
     * @return
     */
    int insertSelective(ErrorPage record);

    /**
     * 更新异常页面-字段可选
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(ErrorPage record);

    /**
     * 更新异常页面-包含提示内容
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKeyWithBLOBs(ErrorPage record);

    /**
     * 更新异常页面
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKey(ErrorPage record);

    /**
     * 根据主键查询异常页面
     * 
     * @param id
     * @return
     */
    ErrorPage selectByPrimaryKey(Long id);

    /**
     * 查询所有异常页面的数量-分页用
     * 
     * @return
     */
    Integer queryTotalCount();

    /**
     * 根据分页参数查询异常页面列表
     * 
     * @param map
     * @return
     */
    List<Object> queryByPageBean(Map<String, Object> map);
}
