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

import com.ningpai.goods.bean.GoodsOpenSpecValue;
import com.ningpai.goods.bean.GoodsSpecDetail;
import com.ningpai.goods.dao.GoodsOpenSpecValueMapper;
import com.ningpai.goods.dao.GoodsSpecDetailMapper;
import com.ningpai.goods.service.GoodsOpenSpecValueService;
import com.ningpai.goods.vo.GoodsOpenSpecValueVo;

/**
 * 商品开启规格值service实现
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年6月30日 下午5:47:10
 * @version 1.
 */
@Service("GoodsOpenSpecValueService")
public class GoodsOpenSpecValueServiceImpl implements GoodsOpenSpecValueService {

    // 商品开启规格值Mapper
    private GoodsOpenSpecValueMapper goodsOpenSpecValueMapper;

    @Resource(name = "GoodsSpecDetailMapper")
    private GoodsSpecDetailMapper detailMapper;

    /**
     * 保存商品开启规格值记录
     *
     * @param goodsId
     *            商品ID {@link Long}
     * @param specId
     *            规格ID {@link Long}
     * @param specValueId
     *            规格值ID {@link Long}
     * @param valueImg
     *            规格图片 {@link String}
     * @param valueRemark
     *            自定义规格名称 {@link String}
     * @return 插入的行数 {@link Integer}
     */
    public int saveOpenSpecVal(Long goodsId, Long specId, Long specValueId, String valueImg, String valueRemark) {
        // 实例化一个商品规格对象
        GoodsOpenSpecValue goodsOpenSpecValue = new GoodsOpenSpecValue();
        try {
            // 对参数进行赋值
            goodsOpenSpecValue.setGoodsId(goodsId);
            goodsOpenSpecValue.setDelFlag("0");
            goodsOpenSpecValue.setSpecId(specId);
            goodsOpenSpecValue.setSpecValueId(specValueId);
            if (null != valueImg) {
                goodsOpenSpecValue.setImgUrl(valueImg);
            } else {
                goodsOpenSpecValue.setImgUrl("");
            }
            if (null != valueRemark) {
                goodsOpenSpecValue.setSpecValueRemark(valueRemark);
            }
            // 保存商品开启规格值记录
            return this.goodsOpenSpecValueMapper.insertSelective(goodsOpenSpecValue);
        } finally {
            goodsOpenSpecValue = null;
        }
    }

    /**
     * 根据商品ID和规格ID查询开启的规格值集合
     *
     * @param goodsId
     *            商品ID {@link Long}
     * @param specId
     *            规格ID {@link Long}
     * @return 查询到的开启的规格值的集合 {@link GoodsOpenSpecValueVo}
     */
    public List<GoodsOpenSpecValueVo> queryOpenListByGoodsAndSpecId(Long goodsId, Long specId) {
        // 定义一个HashMap集合
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("goodsId", goodsId);
            map.put("specId", specId);
            // 根据商品ID和规格ID查询开启的规格值集合
            return this.goodsOpenSpecValueMapper.queryOpenValueListByGoodsIdAndSpecId(map);
        } finally {
            map = null;
        }
    }

    /**
     * 根据商品id和规格值id查询是否开启规则值
     * 
     * @param goodsId
     *            商品id
     * @param specVlueId
     *            规格值id
     * @return
     */
    @Override
    public int queryOpenListByGoodsAndSpecValueId(Long goodsId, String[] specValueId) {
        // 定义一个HashMap集合
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // 循环开启规格值
            for (int i = 0; i < specValueId.length; i++) {
                map.put("specValueId", specValueId[i]);
                map.put("goodsId", goodsId);
                // 根据商品id和规格值id查询是否开启规则值
                GoodsOpenSpecValueVo goodsOpenSpecValue = goodsOpenSpecValueMapper.queryOpenValueListByGoodsIdAndSpecValueId(map);
                // 判断规格值是否已被开启
                if (goodsOpenSpecValue == null) {
                    GoodsSpecDetail detail = detailMapper.selectByPrimaryKey(Long.parseLong(specValueId[i]));
                    GoodsOpenSpecValue specValue = new GoodsOpenSpecValue();
                    specValue.setDelFlag("0");
                    specValue.setGoodsId(goodsId);
                    specValue.setSpecValueId(Long.parseLong(specValueId[i]));
                    specValue.setSpecId(detail.getSpecId());
                    specValue.setSpecValueRemark(detail.getSpecDetailName());
                    // 添加规格值
                    goodsOpenSpecValueMapper.insertSelective(specValue);

                }
            }
            return 0;
        } finally {
            map = null;
        }
    }

    /**
     * 根据商品id，删除商品与规格之间关系
     *
     * @param goodsId
     *            商品id
     */
    @Override
    public void deleteByGoodsId(Long goodsId) {
        // 根据商品id，删除商品与规格之间关系
        goodsOpenSpecValueMapper.deleteByGoodsId(goodsId);
    }

    public GoodsOpenSpecValueMapper getGoodsOpenSpecValueMapper() {
        return goodsOpenSpecValueMapper;
    }

    @Resource(name = "GoodsOpenSpecValueMapper")
    public void setGoodsOpenSpecValueMapper(GoodsOpenSpecValueMapper goodsOpenSpecValueMapper) {
        this.goodsOpenSpecValueMapper = goodsOpenSpecValueMapper;
    }

}
