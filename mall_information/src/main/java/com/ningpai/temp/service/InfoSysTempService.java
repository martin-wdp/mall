/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.temp.service;

import java.util.List;

import com.ningpai.temp.bean.SysTemp;

/**
 * SERVICE-频道用模板service
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年4月4日上午11:35:48
 */
public interface InfoSysTempService {
    /**
     * 获取所有模板设置对象集合
     * 
     * @return
     */
    List<SysTemp> queryAllSystemp();

    /**
     * 根据主键查询模板设置对象
     * 
     * @param systempId
     * @return
     */
    SysTemp getSystempById(Long systempId);
}
