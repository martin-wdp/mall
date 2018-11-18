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
public interface ThirdTempService {

    /**
     * 根据第三方商家ID和模板类型查询模板
     * 
     * @param typeId
     *            137：首页模板 139：频道页模板 141：单页模板
     * @return
     */
    List<SysTemp> querySystempByType(Long typeId);

    /**
     * 根据主键查询模板设置对象
     * 
     * @param systempId
     * @return
     */
    SysTemp getSystempById(Long systempId);

    /**
     * 修改模板设置对象
     * 
     * @param temp
     * @return
     */
    int updateSystemp(SysTemp temp);

    /**
     * 获取当前使用的首页模板
     * 
     * @return
     */
    SysTemp getCurrTemp();

}
