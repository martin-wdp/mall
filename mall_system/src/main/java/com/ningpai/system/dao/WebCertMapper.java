package com.ningpai.system.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.system.bean.WebCert;

/**
 * DAO-网站认证
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年6月5日下午4:17:25
 */
public interface WebCertMapper {
    /**
     * 根据主键删除
     * 
     * @param certificationId
     * @return
     */
    int deleteByPrimaryKey(Long certificationId);

    /**
     * 添加
     * 
     * @param record
     * @return
     */
    int insert(WebCert record);

    /**
     * 添加-字段可选
     * 
     * @param record
     * @return
     */
    int insertSelective(WebCert record);

    /**
     * 修改-字段可选
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(WebCert record);

    /**
     * 修改
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKey(WebCert record);

    /**
     * 根据ID查询
     * 
     * @param certificationId
     * @return
     */
    WebCert selectByPrimaryKey(Long certificationId);

    /**
     * 查询所有行数-分页用
     * 
     * @return
     */
    Integer selectCountByPb();

    /**
     * 根据分页参数查询认证
     * 
     * @param map
     * @return
     */
    List<Object> selectAllByPb(Map<String, Object> map);

    /**
     * 查询所有认证-前台展示用
     * 
     * @return
     */
    List<WebCert> selectAll();
}
