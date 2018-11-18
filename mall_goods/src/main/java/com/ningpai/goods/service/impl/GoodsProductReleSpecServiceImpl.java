/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.ningpai.goods.dao.GoodsSpecMapper;
import com.ningpai.goods.vo.GoodsSpecVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ningpai.goods.bean.GoodsProductReleSpec;
import com.ningpai.goods.dao.GoodsProductReleSpecMapper;
import com.ningpai.goods.service.GoodsProductReleSpecService;
import com.ningpai.goods.util.ValueUtil;
import com.ningpai.util.MyLogger;

/**
 * 商品关联规格值Service实现
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月30日 上午11:25:32
 * @version 1.0
 */
@Service("GoodsProductReleSpecService")
public class GoodsProductReleSpecServiceImpl implements GoodsProductReleSpecService {

    @Resource(name = "GoodsSpecMapper")
    private GoodsSpecMapper goodsSpecMapper;
    // 货品关联规格值Dao
    private GoodsProductReleSpecMapper goodsProductReleSpecMapper;
    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(GoodsProductReleSpecServiceImpl.class);

    /**
     * 插入一条货品关联规格值记录
     *
     * @param productId
     *            货品ID {@link java.lang.Long}
     * @param specId
     *            规格ID {@link java.lang.Long}
     * @param specDetailId
     *            规格值ID {@link java.lang.Long}
     * @param specRemark
     *            自定义规格名称 {@link String}
     * @param useranme
     *            操作人名称
     * @return 保存的行数 {@link java.lang.Integer}
     */
    @Transactional
    public int saveProductReleSpec(Long productId, Long specId, Long specDetailId, String specRemark, String useranme) {
        // 实例化一个货品规格对象
        GoodsProductReleSpec goodsProductReleSpec = new GoodsProductReleSpec();
        try {
            if (specId > 0) {
                // 对参数进行赋值
                goodsProductReleSpec.setGoodsInfoId(productId);
                goodsProductReleSpec.setSpecDetailId(specDetailId);
                goodsProductReleSpec.setSpecId(specId);
                goodsProductReleSpec.setSpecValueRemark(specRemark);
                // 插入一条货品关联规格值记录
                return this.goodsProductReleSpecMapper.insertSelective(goodsProductReleSpec);
            } else {
                return 0;
            }
        } finally {
            // 打印日志
            LOGGER.info(ValueUtil.SAVEPRODUCTRELESPEC);
            // 参数置空
            goodsProductReleSpec = null;
        }
    }

    /**
     * 修改货品关联规格
     * 
     * @return
     */
    @Transactional
    public int updateProductReleSpec(Long productId, Long specId, Long specDetailId, String specValueRemark) {
        // 定义一个HashMap集合
        Map<String, String> map = new HashMap<String, String>();
        try {
            map.put("productId", productId.toString());
            map.put("specDetailId", specDetailId.toString());
            map.put("specId", specId.toString());
            map.put("specValueRemark", specValueRemark);
            // 执行修改操作
            return this.goodsProductReleSpecMapper.updateProductReleSpec(map);
        } finally {
            // 打印日志
            LOGGER.info(ValueUtil.UPDATEPRODUCTRELESPEC);
            // 参数置空
            map = null;
        }
    }

    /**
     * 根据货品ID查询关联的规格
     *
     * @param goodsId
     *            货品ID {@link java.lang.Long}
     * @return 查询到的列表 {@link java.util.List}
     *         {@link com.ningpai.goods.vo.GoodsSpecVo}
     */
    @Override
    public List<GoodsSpecVo> querySpecVoByGoodsInfoId(Long goodsId) {
        // 根据货品ID查询关联的规格
        return goodsSpecMapper.querySpecVoByGoodsInfoId(goodsId);
    }

    /**
     * 根据货品id删除所有规格和规格值
     *
     * @param productId
     *            货品id
     * @return
     */
    @Override
    public int deleteByProductId(Long productId) {
        // 根据货品id删除所有规格和规格值
        return goodsProductReleSpecMapper.deleteByProductId(productId);
    }

    public GoodsProductReleSpecMapper getGoodsProductReleSpecMapper() {
        return goodsProductReleSpecMapper;
    }

    @Resource(name = "GoodsProductReleSpecMapper")
    public void setGoodsProductReleSpecMapper(GoodsProductReleSpecMapper goodsProductReleSpecMapper) {
        this.goodsProductReleSpecMapper = goodsProductReleSpecMapper;
    }

}
