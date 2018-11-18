/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ningpai.goods.bean.GoodsOpenSpec;
import com.ningpai.goods.bean.GoodsSpecDetail;
import com.ningpai.goods.dao.GoodsOpenSpecMapper;
import com.ningpai.goods.dao.GoodsSpecDetailMapper;
import com.ningpai.goods.service.GoodsOpenSpecService;
import com.ningpai.goods.service.GoodsOpenSpecValueService;
import com.ningpai.goods.vo.GoodsOpenSpecValueVo;
import com.ningpai.goods.vo.GoodsOpenSpecVo;

/**
 * 商品开启规格Service实现
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年6月30日 下午3:04:14
 * @version 1.0
 */
@Service("GoodsOpenSpecService")
public class GoodsOpenSpecServiceImpl implements GoodsOpenSpecService {

    // 商品开启规格Mapper
    private GoodsOpenSpecMapper goodsOpenSpecMapper;

    private GoodsOpenSpecValueService goodsOpenSpecValueService;

    @Resource(name = "GoodsSpecDetailMapper")
    private GoodsSpecDetailMapper detailMapper;

    /**
     * 保存商品开启规格信息
     *
     * @param goodsId
     *            商品ID {@link Long}
     * @param specId
     *            规格ID {@link Long}
     * @return 插入的行数{@link　Integer}
     */
    public int saveOpenSpec(Long goodsId, Long specId) {
        // 实例化一个商品规格对象
        GoodsOpenSpec openSpec = new GoodsOpenSpec();
        try {
            // 进行赋值
            openSpec.setGoodsId(goodsId);
            openSpec.setSpecId(specId);
            openSpec.setDelFlag("0");
            // 保存商品开启规格信息
            return this.goodsOpenSpecMapper.insertSelective(openSpec);
        } finally {
            openSpec = null;
        }
    }

    /**
     * 根据商品ID查询开启的规格集合
     *
     * @param goodsId
     *            商品ID {@link Long}
     * @return 查询到的集合 {@link List}
     */
    public List<GoodsOpenSpecVo> queryOpenListByGoodsIdInBoss(Long goodsId) {
        // 根据商品ID查询开启的规格集合
        List<GoodsOpenSpecVo> openList = this.goodsOpenSpecMapper.queryOpenSpecListByGoodsId(goodsId);
        try {
            for (int i = 0; i < openList.size(); i++) {
                List<GoodsOpenSpecValueVo> goodsOpenSpecValueVos = this.goodsOpenSpecValueService.queryOpenListByGoodsAndSpecId(goodsId, openList.get(i).getSpecId());
                List<GoodsSpecDetail> goodsSpecDetails = null;
                // 查询所有规格值
                if (goodsOpenSpecValueVos != null && goodsOpenSpecValueVos.get(0) != null) {
                    goodsSpecDetails = detailMapper.selectSpecDeetailBySpecId(goodsOpenSpecValueVos.get(0).getSpecId());
                }
                // 排除重复的规格值
                /*
                 * for (GoodsSpecDetail specDetail:goodsSpecDetails) { for (int
                 * j2 = 0; j2 < goodsOpenSpecValueVos.size(); j2++) {
                 * if(specDetail
                 * .getSpecDetailId().equals(goodsOpenSpecValueVos.get
                 * (j2).getSpecValueId())){ goodsSpecDetails.remove(specDetail);
                 * continue; } } }
                 */
                // 添加规格值

                outer: for (int j = 0; j < goodsSpecDetails.size(); j++) {
                    for (int j2 = 0; j2 < goodsOpenSpecValueVos.size(); j2++) {
                        if (goodsSpecDetails.get(j).getSpecDetailId().equals(goodsOpenSpecValueVos.get(j2).getSpecValueId())) {
                            System.out.println("重复");
                            continue outer;
                            // continue;
                        }
                    }
                    GoodsSpecDetail g = goodsSpecDetails.get(j);
                    // 实例化一个商品规格vo对象
                    GoodsOpenSpecValueVo vlVo = new GoodsOpenSpecValueVo();
                    // 对参数进行赋值
                    vlVo.setDelFlag("0");
                    vlVo.setSpecValueRemark(g.getSpecDetailName());
                    vlVo.setSpecId(g.getSpecId());
                    vlVo.setSpecValueId(g.getSpecDetailId());
                    vlVo.setSpecDetail(g);
                    // 添加
                    goodsOpenSpecValueVos.add(vlVo);
                }
                openList.get(i).setSpecValList(goodsOpenSpecValueVos);
            }
            return openList;
        } finally {
            openList = null;
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
        goodsOpenSpecMapper.deleteByGoodsId(goodsId);
    }

    /**
     * 根据商品ID查询开启的规格集合
     *
     * @param goodsId
     *            商品ID {@link Long}
     * @return 查询到的集合 {@link List}
     */
    public List<GoodsOpenSpecVo> queryOpenListByGoodsId(Long goodsId) {
        List<GoodsOpenSpecVo> openList = this.goodsOpenSpecMapper.queryOpenSpecListByGoodsId(goodsId);
        try {
            for (int i = 0; i < openList.size(); i++) {
                openList.get(i).setSpecValList(this.goodsOpenSpecValueService.queryOpenListByGoodsAndSpecId(goodsId, openList.get(i).getSpecId()));
            }
            return openList;
        } finally {
            openList = null;
        }
    }

    public GoodsOpenSpecMapper getGoodsOpenSpecMapper() {
        return goodsOpenSpecMapper;
    }

    @Resource(name = "GoodsOpenSpecMapper")
    public void setGoodsOpenSpecMapper(GoodsOpenSpecMapper goodsOpenSpecMapper) {
        this.goodsOpenSpecMapper = goodsOpenSpecMapper;
    }

    public GoodsOpenSpecValueService getGoodsOpenSpecValueService() {
        return goodsOpenSpecValueService;
    }

    @Resource(name = "GoodsOpenSpecValueService")
    public void setGoodsOpenSpecValueService(GoodsOpenSpecValueService goodsOpenSpecValueService) {
        this.goodsOpenSpecValueService = goodsOpenSpecValueService;
    }

}
