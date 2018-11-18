/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.service;

import java.util.List;
import java.util.Map;

import com.ningpai.goods.bean.GoodsTypeExpandParam;
import com.ningpai.goods.vo.GoodsTypeExpandParamVo;

import javax.servlet.http.HttpServletRequest;

/**
 * 商品扩展属性Service
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月21日 下午2:45:12
 * @version 1.0
 */
public interface GoodsTypeExpandParamService {
    /**
     * 添加扩展属性
     * 
     * @param goodsTypeExpandParam
     *            扩展属性 {@link com.ningpai.goods.bean.GoodsTypeExpandParam}
     * @param username
     *            操作的用户名
     * @return 插入的最新的ID {@link java.lang.Long}
     */
    Long saveExpandParam(GoodsTypeExpandParam goodsTypeExpandParam,
            String username);

    /**
     * 删除扩展属性
     * 
     * @param paramId
     *            主键ID {@link java.lang.Long}
     * @param username
     *            操作的用户名
     * @return 删除的行数 插入的行数 {@link java.lang.Integer}
     */
    int delExpandParam(Long paramId, String username);

    /**
     * 更新扩展属性，操作的用户名
     * 
     * @param goodsTypeExpandParam
     *            扩展属性 {@link com.ningpai.goods.bean.GoodsTypeExpandParam}
     * @param username
     *            操作的用户名
     * @return 插入的行数 {@link java.lang.Integer}
     */
    int updateExpandParam(GoodsTypeExpandParam goodsTypeExpandParam,
            String username);

    /**
     * 根据类型ID查询所有的扩展属性
     * 
     * @param typeId
     *            分类ID {@link java.lang.Longs}
     * @return 查询到的扩展属性的集合 {@link java.util.List}
     *         {@link com.ningpai.goods.bean.GoodsTypeExpandParam}
     */
    List<GoodsTypeExpandParamVo> queryParamListByTypeId(Long typeId);

    /**
     * 批量进行扩展属性的操作
     * 
     * @param username
     *            操作人名称呢过
     * @param typeId
     *            类型ID，{@link Long}
     * @param map
     *            所有的参数 {@link java.util.Map}
     * @param request
     * @return 受影响的 行数 {@link java.lang.Integer}
     */
    int batchUpdateExpandParam(String username, Long typeId,
            Map<String, String[]> map, HttpServletRequest request);
}
