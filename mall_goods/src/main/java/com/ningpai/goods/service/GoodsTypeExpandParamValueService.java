/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.service;

import java.util.List;

import com.ningpai.goods.bean.GoodsTypeExpandParamValue;

/**
 * 商品类型扩展属性值Service
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月21日 下午3:14:55
 * @version 1.0
 */
public interface GoodsTypeExpandParamValueService {
    /**
     * 添加属性值
     * 
     * @param goodsTypeExpandParamValue
     *            属性值实体 {@link com.ningpai.goods.bean.GoodsTypeExpandParamValue}
     * @param username
     *            操作人名称
     * @return 添加的行数
     */
    int saveParamValue(GoodsTypeExpandParamValue goodsTypeExpandParamValue,
            String username);

    /**
     * 删除属性值
     * 
     * @param paramValueId
     *            属性值ID {@link java.lang.Long }
     * @param username
     *            操作人名称
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    int delParamValue(Long paramValueId, String username);

    /**
     * 修改属性值
     * 
     * @param goodsTypeExpandParamValue
     *            带修改的属性值实体
     *            {@link com.ningpai.goods.bean.GoodsTypeExpandParamValue}
     * @param username
     *            操作人名称
     * @return 受影响的行数
     */
    int updateParamValue(GoodsTypeExpandParamValue goodsTypeExpandParamValue,
            String username);

    /**
     * 根据属性ID查询所有的属性值
     * 
     * @param paramId
     *            属性ID {@link java.lang.Long}
     * @return 查询到的列表 {@link java.util.List}
     *         {@link com.ningpai.goods.bean.GoodsTypeExpandParamValue}
     */
    List<GoodsTypeExpandParamValue> queryParamValueByParamId(Long paramId);

    /**
     * 更新商品类型扩展属性值
     * 
     * @param paramIds
     *            扩展属性ID数组
     * @param expandParamValueId
     *            扩展属性值ID数组
     * @param expandParamValueDelFlag
     *            扩展属性值删除标记数组
     * @param expandParamValueName
     *            扩展属性值名称数组
     * @param expandParamValueSort
     *            扩展属性值排序数组
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    int updateParamValues(String[] paramIds, String[] expandParamValueId,
            String[] expandParamValueDelFlag, String[] expandParamValueName,
            String[] expandParamValueSort, String username);

    /**
     * 根据扩展属性值ID查询商品扩展属性值对象
     * 
     * @param expandParamId
     *            扩展属性值ID {@link java.lang.Long}
     * @return 查询到的扩展属性值对象
     *         {@link com.ningpai.goods.bean.GoodsTypeExpandParamValue}
     */
    GoodsTypeExpandParamValue queryExpandParamValueByExpandParamId(
            Long expandParamId);
}
