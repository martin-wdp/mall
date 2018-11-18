package com.ningpai.cloudshop.dao;

import com.ningpai.cloudshop.bean.CloudshopAuthorInfo;

/**
 * 云销用户授权信息mapper
 * 
 * @aurhor liangck
 * @since 2015年6月25日
 * @version 1.0
 */
public interface CloudshopAuthorInfoMapper {

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(CloudshopAuthorInfo record);

    /**
     * 根据主键查询记录
     */
    CloudshopAuthorInfo selectByPrimaryKey(Integer cloudshopAuthorInfoId);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(CloudshopAuthorInfo record);

    /**
     * 查询现有授权信息(只有一条)
     * 
     * @return 授权信息
     */
    CloudshopAuthorInfo selectAuthorInfo();

    /**
     * 获取accesstoken值
     * 
     * @return token值
     */
    String getAccessToken();

    /**
     * 删除用户授权信息
     * 
     * @return 受影响条数
     */
    int deleteAuthorInfo();
}