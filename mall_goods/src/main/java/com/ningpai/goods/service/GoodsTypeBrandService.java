/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.service;

import java.util.List;

import com.ningpai.goods.bean.GoodsTypeBrand;
import com.ningpai.goods.vo.GoodsTypeBrandVo;

/**
 * 商品关联品牌类Service
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月21日 上午10:54:25
 * @version 1.0
 */
public interface GoodsTypeBrandService {
    /**
     * 插入类型关联品牌记录
     * 
     * @param goodsTypeBrand
     *            待插入的实体 {@link com.ningpai.goods.bean.GoodsTypeBrands}
     * @param username
     *            操作的用户名
     * @return 插入的行数 {@link java.lang.Integer}
     */
    int insertTypeBrand(GoodsTypeBrand goodsTypeBrand, String username);

    /**
     * 根据主键删除
     * 
     * @param typeBrandId
     *            类型品牌ID {@link java.lang.Long}
     * @param username
     *            操作的用户名称
     * @return 删除的行数 {@link java.lang.Integer}
     */
    int delTypeBrand(Long typeBrandId, String username);

    /**
     * 修改记录
     * 
     * @param goodsTypeBrand
     *            需要修改的实体 {@link com.ningpai.goods.bean.GoodsTypeBrand}
     * @param username
     *            操作的用户名称
     * @return 更新的行数
     */
    int updateTypeBrand(GoodsTypeBrand goodsTypeBrand, String username);

    /**
     * 根据主键查询类型关联品牌实体
     * 
     * @param typeBrandId
     *            主键ID {@link java.lang.Long}
     * @return 查询到的实体 {@link com.ningpai.goods.bean.GoodsTypeBrand}
     */
    GoodsTypeBrand queryTypeBrandById(Long typeBrandId);

    /**
     * 根据类型ID查询关联的品牌的列表
     * 
     * @param typeId
     *            类型ID {@link java.lang.Long}
     * @return 查询到的列表 {@link java.util.List}
     *         {@link com.ningpai.goods.bean.GoodsTypeBrand}
     */
    List<GoodsTypeBrandVo> queryTypeBrandByTypeId(Long typeId);

    /**
     * 执行批量更新商品类型关联品牌的操作
     * 
     * @param typeId
     *            商品类型的ID {@link java.lang.Long}
     * @param username
     *            操作人名称
     * @param brandIds
     *            商品类型关联品牌的所有ID
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    int batchUpdateTypeBrand(Long typeId, String username, String[] brandIds);
}
