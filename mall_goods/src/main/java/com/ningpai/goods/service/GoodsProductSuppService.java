/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.service;

import java.util.List;

import com.ningpai.goods.bean.GoodsProductSupp;
import com.ningpai.goods.vo.GoodsProductSuppVo;

/**
 * 货品关联服务支持Service
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年7月31日 上午10:10:54
 * @version 1.0
 */
public interface GoodsProductSuppService {
    /**
     * 批量保存关联信息
     * 
     * @param suppIds
     *            支持ID数组
     * @param productId
     *            货品ID
     * @return 保存的行数
     */
    int batchInsert(String[] suppIds, int productId);

    /**
     * 删除所有的关联信息
     * 
     * @param productId
     * @return
     */
    int delAll(Long productId);

    /**
     * 根据货品ID查询所有的关联的支持列表
     * 
     * @param productId
     *            货品ID
     * @return 查询到的集合
     */
    List<GoodsProductSupp> queryAllSuppByProductId(Long productId);

    /**
     * 根据货品ID查询所有的关联服务的Vo集合
     * 
     * @param productId
     *            货品ID
     * @return 查询到的服务集合
     */
    List<GoodsProductSuppVo> queryAllSuppVoByProId(Long productId);

}
