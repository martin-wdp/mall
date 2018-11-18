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
import org.springframework.transaction.annotation.Transactional;

import com.ningpai.goods.bean.GoodsReleTag;
import com.ningpai.goods.dao.CascDelMapper;
import com.ningpai.goods.dao.GoodsReleTagMapper;
import com.ningpai.goods.service.GoodsReleTagService;
import com.ningpai.goods.util.ValueUtil;
import com.ningpai.goods.vo.GoodsReleTagVo;
import com.ningpai.util.MyLogger;

/**
 * 商品关联标签Service实现
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月24日 下午9:07:05
 * @version 1.0
 */
@Service("GoodsReleTagService")
public class GoodsReleTagServiceImpl implements GoodsReleTagService {
    // 商品关联标签DAO
    private GoodsReleTagMapper goodsReleTagMapper;
    private CascDelMapper cascDelMapper;
    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(
            GoodsReleTagServiceImpl.class);

    public CascDelMapper getCascDelMapper() {
        return cascDelMapper;
    }

    @Resource(name = "CascDelMapper")
    public void setCascDelMapper(CascDelMapper cascDelMapper) {
        this.cascDelMapper = cascDelMapper;
    }

    public GoodsReleTagMapper getGoodsReleTagMapper() {
        return goodsReleTagMapper;
    }

    @Resource(name = "GoodsReleTagMapper")
    public void setGoodsReleTagMapper(GoodsReleTagMapper goodsReleTagMapper) {
        this.goodsReleTagMapper = goodsReleTagMapper;
    }

    /**
     * 保存关联标签
     *
     * @param tagId
     *            标签的ID
     * @param goodsId
     *            商品的ID
     * @param username
     *            操作人名称
     * @return 添加的行数
     */
    @Transactional
    public int saveReleTag(Long tagId, Long goodsId, String username) {
        //New一个商品关联标签的实体
        GoodsReleTag releTag = new GoodsReleTag();
        try {
            //对关联标签的属性进行赋值
            releTag.setGoodsId(goodsId);
            releTag.setRelaTagDelflag("0");
            releTag.setRelaTagCreateName(username);
            releTag.setTagId(tagId);
            //执行方法，返回结果
            return this.goodsReleTagMapper.insertSelective(releTag);
        } finally {
            //打印日志
            LOGGER.info(ValueUtil.SAVERELETAG + username);
            releTag = null;
        }
    }

    /**
     * 根据商品ID和标签ID查询实体
     *
     * @param goodsId
     *            商品ID {@link java.lang.Long}
     * @param tagId
     *            标签ID {@link java.lang.Long}
     * @return 查询到的实体 {@link com.ningpai.goods.bean.GoodsReleTag}
     */
    public GoodsReleTag queryByGoodsIdAndTagId(Long goodsId, Long tagId) {
        //定义一个HashMap集合
        Map<String, Long> map = new HashMap<String, Long>();
        try {
            //把参数放进map集合中
            map.put("goodsId", goodsId);
            map.put("tagId", tagId);
            //执行方法，返回结果
            return this.goodsReleTagMapper.queryByGoodsIdAndTagId(map);
        } finally {
            // 参数置空
            map = null;
        }
    }

    /**
     * 更新商品关联标签记录
     *
     * @param releTag
     *            待更新的实体 {@link com.ningpai.goods.bean.GoodsReleTag}
     * @param username
     *            操作人名称
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    @Transactional
    public int update(GoodsReleTag releTag, String username) {
        releTag.setRelaTagCreateName(username);
        //打印日志
        LOGGER.info(ValueUtil.UPDATERELETAG + username);
        //执行方法，返回结果
        return this.goodsReleTagMapper.updateByPrimaryKeySelective(releTag);
    }

    /**
     * 删除关联标签的记录
     *
     * @param releTagId
     *            关联的标签ID {@link java.lang.Long}
     * @param username
     *            用户名名称
     * @return 删除的行数 {@link java.lang.Integer}
     */
    @Transactional
    public int deleteByPrimaryKey(Long releTagId, String username) {
        //定义一个HashMap集合
        Map<String, String> map = new HashMap<String, String>();
        try {
            //把参数放进Map集合
            map.put("delName", username);
            map.put("relaTagId", releTagId.toString());
            //执行方法，返回结果
            return this.goodsReleTagMapper.deleteByPrimaryKey(map);
        } finally {
            //打印日志
            LOGGER.info(ValueUtil.DELETERELETAG + username);
            this.cascDelMapper.cascDel(username);
            map = null;
        }
    }

    /**
     * 根据货品ID查询关联的记录
     *
     * @param productId
     *            货品ID {@link Long}
     * @return 查询到的集合 {@link GoodsReleTagVo}
     */
    public List<GoodsReleTagVo> queryreleListByProductId(Long productId) {
        //执行方法，返回结果
        return this.goodsReleTagMapper.queryAllByProductId(productId);
    }

}
