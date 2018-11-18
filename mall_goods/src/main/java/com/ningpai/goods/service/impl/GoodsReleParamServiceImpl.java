/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ningpai.goods.bean.GoodsReleParam;
import com.ningpai.goods.dao.CascDelMapper;
import com.ningpai.goods.dao.GoodsReleParamMapper;
import com.ningpai.goods.service.GoodsReleParamService;
import com.ningpai.goods.util.ValueUtil;
import com.ningpai.util.MyLogger;

/**
 * 商品关联类型详细参数Service
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月25日 下午2:18:57
 * @version 1.0
 */
@Repository("GoodsReleParamService")
public class GoodsReleParamServiceImpl implements GoodsReleParamService {
    // 商品关联类型详细参数 DAO
    private GoodsReleParamMapper goodsReleParamMapper;
    private CascDelMapper cascDelMapper;
    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(
            GoodsReleParamServiceImpl.class);

    public CascDelMapper getCascDelMapper() {
        return cascDelMapper;
    }

    @Resource(name = "CascDelMapper")
    public void setCascDelMapper(CascDelMapper cascDelMapper) {
        this.cascDelMapper = cascDelMapper;
    }

    public GoodsReleParamMapper getGoodsReleParamMapper() {
        return goodsReleParamMapper;
    }

    @Resource(name = "GoodsReleParamMapper")
    public void setGoodsReleParamMapper(
            GoodsReleParamMapper goodsReleParamMapper) {
        this.goodsReleParamMapper = goodsReleParamMapper;
    }

    /**
     * 保存商品关联类型详细参数
     *
     * @param paramId
     *            详细参数ID
     * @param paramValue
     *            详细参数值
     * @param goodsId
     *            商品ID
     * @return 插入的行数 {@link java.lang.Integer}
     */
    @Transactional
    public int saveGoodsReleParam(Long goodsId, Long paramId,
            String paramValue, String usernames) {
        // 创建临时对象
        GoodsReleParam goodsReleParam = new GoodsReleParam();
        try {
            // 临时对象内赋值
            goodsReleParam.setParamCreateName(usernames);
            goodsReleParam.setParamDelflag(ValueUtil.DEFAULTDELFLAG);
            goodsReleParam.setParamId(paramId);
            goodsReleParam.setParamValue(paramValue);
            goodsReleParam.setGoodsId(goodsId);
            //执行方法，返回结果
            return this.goodsReleParamMapper.insertSelective(goodsReleParam);
        } finally {
            //打印日志
            LOGGER.info(ValueUtil.SAVEGOODSRELEPARAM + usernames);
            // 对象置空
            goodsReleParam = null;
        }
    }

    /**
     * 更新商品关联类型详细参数信息
     *
     * @param goodsReleParam
     *            待更新的实体 {@link com.ningpai.goods.bean.GoodsReleParam}
     * @param username
     *            操作人名称
     * @return 更新的行数 {@link java.lang.Integer}
     */
    @Transactional
    public int updateReleParam(GoodsReleParam goodsReleParam, String username) {
        goodsReleParam.setParamModifiedName(username);
        //打印结果
        LOGGER.info(ValueUtil.UPDATERELEPARAM + username);
        //执行方法，返回结果
        return this.goodsReleParamMapper
                .updateByPrimaryKeySelective(goodsReleParam);
    }

    /**
     * 根据商品ID和详细参数ID查询
     *
     * @param goodsId
     *            商品ID{@link java.lang.Long}
     * @param paramId
     *            详细参数ID {@link java.lang.Long}
     * @return 查询到的实体 {@link com.ningpai.goods.bean.GoodsReleParam}
     */
    public GoodsReleParam queryReleParamByGoodsIdAndParamId(Long goodsId,
            Long paramId) {
        //定义一个HashMap集合
        Map<String, Long> map = new HashMap<String, Long>();
        try {
            //把参数放进map集合中
            map.put("goodsId", goodsId);
            map.put("paramId", paramId);
            //执行方法返回结果
            return this.goodsReleParamMapper.queryByGoodsIdAndParamId(map);
        } finally {
            // 参数置空
            map = null;
        }
    }

    /**
     * 根据商品ID删除所有的关联的记录
     *
     * @param goodsId
     *            商品的ID {@link java.lang.Long}
     * @param username
     *            操作人名称
     * @return 删除的记录数
     */
    @Transactional
    public int delAllReleParamByGoodsId(Long goodsId, String username) {
        //定义一个HashMap集合
        Map<String, String> map = new HashMap<String, String>();
        try {
            //把参数放进Map集合中
            map.put("delName", username);
            map.put("goodsId", goodsId.toString());
            //执行方法，返回结果
            return this.goodsReleParamMapper.delAllReleParamByGoodsId(map);
        } finally {
            //打印日志
            LOGGER.info(ValueUtil.DELALLRELEPARAMBYGOODSID + username);
            this.cascDelMapper.cascDel(username);
            // 参数置空
            map = null;
        }
    }

}
