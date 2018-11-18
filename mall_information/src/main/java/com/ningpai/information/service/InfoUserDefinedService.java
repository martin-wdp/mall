/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.information.service;

import java.util.List;

import com.ningpai.information.bean.InfoUserDefined;

/**
 * SERVICE-文章自定义属性
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年3月26日下午5:08:27
 */
public interface InfoUserDefinedService {
    /**
     * 根据主键删除
     * 
     * @param infoUdId
     * @return
     */
    int deleteUserDefinedById(Long infoUdId);

    /**
     * 添加
     * 
     * @param record
     * @return
     */
    int createUserDefined(InfoUserDefined record);

    /**
     * 修改
     * 
     * @param record
     * @return
     */
    int updateUserDefined(InfoUserDefined record);

    /**
     * 查询所有
     * 
     * @return
     */
    List<InfoUserDefined> findAllUserDefined();
}
