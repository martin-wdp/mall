/*
 * Copyright 2013 NINGPAI, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.system.dao;

import com.ningpai.system.bean.ProvinceDefault;

/**
 * 设置默认地区
 * 
 * @author NINGPAI-LIH
 * @since 2015年1月10日10:27:28
 *
 */
public interface ProvinceDefaultMapper {

    /**
     * 修改所有的默认地址为已删除
     * 
     * @return
     */
    int deleteAllDefault();

    /**
     * 插入新的默认的地址
     * 
     * @param record
     * @return
     */
    int insertSelective(ProvinceDefault record);

    /**
     * 查询默认地址
     * 
     * @return
     */
    Long selectDefaultId();
}
