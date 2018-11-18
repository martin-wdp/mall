/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.dao.impl;

import org.springframework.stereotype.Repository;

import com.ningpai.goods.dao.CascDelMapper;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * 批量删除实现
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年3月28日 下午2:23:00
 * @version 1.0
 */
@Repository("CascDelMapper")
public class CascDelMapperImpl extends BasicSqlSupport implements CascDelMapper {
    /**
     * 批量删除方法
     *
     * @param username
     *            删除人
     */
    public void cascDel(String username) {
        this.update("com.ningpai.goods.dao.GoodsMapper.cascDelAll", username);
    }

}
