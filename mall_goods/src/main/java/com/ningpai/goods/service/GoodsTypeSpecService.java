/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.service;

import java.util.List;

import com.ningpai.goods.bean.GoodsTypeSpec;
import com.ningpai.goods.vo.GoodsTypeSpecVo;

/**
 * 商品类型关联规格表Service
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月21日 下午4:41:41
 * @version 1.0
 */
public interface GoodsTypeSpecService {
    /**
     * 增加一条记录
     * 
     * @param goodsTypeSpec
     *            实体 {@link com.ningpai.goods.bean.GoodsTypeSpec}
     * @param username
     *            操作人名称
     * @return 插入的条数 {@link java.lang.Integer}
     */
    int saveTypeSpec(GoodsTypeSpec goodsTypeSpec, String username);

    /**
     * 删除一条记录
     * 
     * @param typeSpecId
     *            主键ID {@link com.ningpai.goods.bean.GoodsTypeSpec}
     * @param username
     *            操作人名称
     * @return 删除的条数 {@link java.lang.Integer}
     */
    int delTypeSpec(Long typeSpecId, String username);

    /**
     * 更新记录
     * 
     * @param goodsTypeSpec
     *            待更新的实体 {@link com.ningpai.goods.bean.GoodsTypeSpec}
     * @param username
     *            操作人名称
     * @return 更新的条数 {@link java.lang.Integer}
     */
    int updateTypeSpec(GoodsTypeSpec goodsTypeSpec, String username);

    /**
     * 根据类型ID查询商品类型关联的规格VO
     * 
     * @param typeId
     * @return
     */
    List<GoodsTypeSpecVo> queryTypeSpecByTypeId(Long typeId);

    /**
     * 批量更新商品类型关联规格
     * 
     * @param typeId
     *            商品类型的iD {@link java.lang.Long}
     * @param username
     *            操作人名称
     * @param specIds
     *            类型ID {@link java.lang.Long}
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    int batchUpdate(Long typeId, String username, String[] specIds);
}
