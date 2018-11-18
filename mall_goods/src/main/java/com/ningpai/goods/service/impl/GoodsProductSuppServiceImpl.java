/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ningpai.goods.bean.GoodsProductSupp;
import com.ningpai.goods.dao.GoodsProductSuppMapper;
import com.ningpai.goods.service.GoodsProductSuppService;
import com.ningpai.goods.vo.GoodsProductSuppVo;

/**
 * 商品关联服务Service实现
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年7月31日 上午10:17:32
 * @version 1.0
 */
@Service("GoodsProductSuppService")
public class GoodsProductSuppServiceImpl implements GoodsProductSuppService {
    @Resource(name = "GoodsProductSuppMapper")
    private GoodsProductSuppMapper goodsProductSuppMapper;

    /**
     * 批量保存关联信息
     *
     * @param suppIds
     *            支持ID数组
     * @param productId
     *            货品ID
     * @return 保存的行数
     */
    public int batchInsert(String[] suppIds, int productId) {
        if (null != suppIds && suppIds.length > 0) {
            // 定义一个Hashmap集合
            Map<String, Object> map = new HashMap<String, Object>();
            try {
                // 把参数放入map集合中
                map.put("suppId", suppIds);
                map.put("productId", productId);
                // 批量保存关联信息
                return this.goodsProductSuppMapper.batchInsertSupp(map);
            } finally {
                map = null;
            }
        } else {
            return 0;
        }
    }

    /**
     * 删除所有的关联信息
     *
     * @param productId
     * @return
     */
    public int delAll(Long productId) {
        // 删除所有的关联信息
        return this.goodsProductSuppMapper.delAllProductSupp(productId);
    }

    /**
     * 根据货品ID查询所有的关联的支持列表
     *
     * @param productId
     *            货品ID
     * @return 查询到的集合
     */
    public List<GoodsProductSupp> queryAllSuppByProductId(Long productId) {
        // 根据货品id查询所有的关联的支持列表
        return this.goodsProductSuppMapper.queryAllByProductId(productId);
    }

    /**
     * 根据货品ID查询所有的关联服务的Vo集合
     *
     * @param productId
     *            货品ID
     * @return 查询到的服务集合
     */
    public List<GoodsProductSuppVo> queryAllSuppVoByProId(Long productId) {
        // 根据货品id查询所有的关联服务的vo集合
        return this.goodsProductSuppMapper.queryAllVoByProductId(productId);
    }

}
