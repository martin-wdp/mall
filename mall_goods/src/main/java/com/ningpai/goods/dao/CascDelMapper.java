/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.dao;

/**
 * 批量删除接口
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年3月28日 下午2:22:18
 * @version 1.0
 */
public interface CascDelMapper {
    /**
     * 批量删除方法
     * 
     * @param username
     *            删除人
     */
    void cascDel(String username);
}
