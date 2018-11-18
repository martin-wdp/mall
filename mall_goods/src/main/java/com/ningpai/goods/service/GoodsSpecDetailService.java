/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.service;

import java.util.List;

import com.ningpai.goods.bean.GoodsSpecDetail;

/**
 * 规格值Service
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月18日 下午4:20:28
 * @version 1.0
 */
public interface GoodsSpecDetailService {
    /**
     * 添加规格值
     * 
     * @param goodsSpecDetail
     *            添加的规格值 {@link com.ningpai.goods.bean.GoodsSpecDetail}
     * @param username
     *            操作人名称
     * @return {@link java.lang.Integer}
     */
    int saveSpecDetail(GoodsSpecDetail goodsSpecDetail, String username);

    /**
     * 批量删除规格值
     * 
     * @param specDetailId
     *            规格值ID
     * @param username
     *            删除人名称
     * @return 1.0
     */
    int delSpecDetail(Long specDetailId, String username);

    /**
     * 更新规格值
     * 
     * @param goodsSpecDetail
     *            需要更新的规格值 {@link com.ningpai.goods.bean.GoodsSpecDetail}
     * @param username
     *            操作人名称
     * @return {@link java.lang.Integer}
     */
    int updateSpecDetail(GoodsSpecDetail goodsSpecDetail, String username);

    /**
     * 根据规格ID查询规格值列表
     * 
     * @param specId
     *            {@link java.lang.Long}
     * @return {@link java.util.List}
     */
    List<Object> queryBySpecId(Long specId);

    /**
     * 根据规格值ID查询
     * 
     * @param specDetailId
     *            规格值ID {@Link java.lang.Long}
     * @return 查询到的规格值 {@link com.ningpai.goods.bean.GoodsSpecDetail}
     */
    GoodsSpecDetail queryByPrimaryKey(Long specDetailId);
}
