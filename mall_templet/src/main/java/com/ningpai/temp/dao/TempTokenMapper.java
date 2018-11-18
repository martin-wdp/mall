package com.ningpai.temp.dao;

import java.util.List;

import com.ningpai.temp.bean.TempToken;

/**
 * DAO-模板内容变更token
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年8月6日下午8:11:21
 */
public interface TempTokenMapper {
    /**
     * 根据主键删除
     * 
     * @param tokenId
     * @return
     */
    int deleteByPrimaryKey(Long tokenId);

    /**
     * 添加
     * 
     * @param record
     * @return
     */
    int insert(TempToken record);

    /**
     * 添加-字段可选
     * 
     * @param record
     * @return
     */
    int insertSelective(TempToken record);

    /**
     * 根据主键查询
     * 
     * @param tokenId
     * @return
     */
    TempToken selectByPrimaryKey(Long tokenId);

    /**
     * 修改-字段可选
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(TempToken record);

    /**
     * 修改
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKey(TempToken record);

    /**
     * 查询所有
     * 
     * @return
     */
    List<TempToken> selectAllToken();

    /**
     * 根据类型，获取首页内容变更token
     * 
     * @param temp1
     * @return
     */
    TempToken selectTokenByType(String temp1);
    
    /**
     * 修改字段token的值
     * @param token
     * @return
     */
    int updateTokenValue(TempToken token);

}
