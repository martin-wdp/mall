package com.ningpai.mobile.dao;

import java.util.List;

import com.ningpai.mobile.bean.MobSinglepageMark;

/**
 * 移动单页标签dao层接口
 * 
 * @author zhangsl
 * @since 2014年11月24日 上午10:48:44
 * @version 0.0.1
 */
public interface MobSinglepageMarkMapper {
    /**
     * 根据主键ID删除移动单页标签信息
     * 
     * @param markId
     * @return
     */
    int deleteByPrimaryKey(Long markId);

    /**
     * 添加移动单页标签信息
     * 
     * @param record
     * @return
     */
    int insert(MobSinglepageMark record);

    /**
     * 有选择性添加移动单页标签信息
     * 
     * @param record
     * @return
     */
    int insertSelective(MobSinglepageMark record);

    /**
     * 根据主键查询移动单页标签
     * 
     * @param markId
     * @return
     */
    MobSinglepageMark selectByPrimaryKey(Long markId);

    /**
     * 更新移动单页标签
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(MobSinglepageMark record);

    /**
     * 根据主键ID更新移动单页标签
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKey(MobSinglepageMark record);

    /**
     * 查询所有移动单页标签列表
     * 
     * @return
     */
    List<MobSinglepageMark> selectAllMarkInfo();

    /**
     * 根据主键ID逻辑删除移动单页标签数据 修改delflag的状态 0：未删除 1：已删除
     * 
     * @param markId
     * @return
     */
    int updateDelStatus(Long markId);

    /**
     * 查询删除状态为0即不删除的移动单页标签列表
     * 
     * @return
     */
    List<MobSinglepageMark> queryAllMarkInfoByDel();

    /**
     * 验证name是否存在
     * 
     * @param name
     * @return 0:不存在 1：存在
     */
    int checkNameExist(String name);

}
