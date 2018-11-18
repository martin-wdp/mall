package com.ningpai.system.dao;

import com.ningpai.system.bean.SysBasic;

/**
 * DAO-后台logo设置
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年9月24日下午8:34:49
 */
public interface SysBasicMapper {
    /**
     * 根据ID删除
     * 
     * @param basicId
     * @return
     */
    int deleteByPrimaryKey(Long basicId);

    /**
     * 添加
     * 
     * @param record
     * @return
     */
    int insert(SysBasic record);

    /**
     * 添加-字段可选
     * 
     * @param record
     * @return
     */
    int insertSelective(SysBasic record);

    /**
     * 根据ID查询
     * 
     * @param basicId
     * @return
     */
    SysBasic selectByPrimaryKey(Long basicId);

    /**
     * 修改-字段可选
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(SysBasic record);

    /**
     * 修改
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKey(SysBasic record);

    /**
     * 查询当前
     * 
     * @return
     */
    SysBasic selectCurr();


}
