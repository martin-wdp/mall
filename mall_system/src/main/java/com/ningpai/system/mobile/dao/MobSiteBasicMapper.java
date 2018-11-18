package com.ningpai.system.mobile.dao;

import com.ningpai.system.mobile.bean.MobSiteBasic;

/**
 * DAO-移动版站点基础设置
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年9月19日下午2:30:38
 */
public interface MobSiteBasicMapper {
    /**
     * 根据主键删除
     * 
     * @param siteBasicId
     * @return
     */
    int deleteByPrimaryKey(Long siteBasicId);

    /**
     * 添加
     * 
     * @param record
     * @return
     */
    int insert(MobSiteBasic record);

    /**
     * 添加-字段可选
     * 
     * @param record
     * @return
     */
    int insertSelective(MobSiteBasic record);

    /**
     * 根据主键查询
     * 
     * @param siteBasicId
     * @return
     */
    MobSiteBasic selectByPrimaryKey(Long siteBasicId);

    /**
     * 修改-字段可选
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(MobSiteBasic record);

    /**
     * 修改
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKey(MobSiteBasic record);

    /**
     * 查询当前站点信息
     * 
     * @return
     */
    MobSiteBasic selectCurr();

}
