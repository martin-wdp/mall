/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ningpai.goods.dao.CascDelMapper;
import com.ningpai.goods.dao.GoodsAtteMapper;
import com.ningpai.goods.service.GoodsAtteService;
import com.ningpai.goods.util.ValueUtil;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;
import com.ningpai.util.SelectBean;

/**
 * 商品关注Service实现
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年3月18日 下午4:41:38
 * @version 1.0
 */
@Service("GoodsAtteService")
public class GoodsAtteServiceImpl implements GoodsAtteService {
    private GoodsAtteMapper goodsAtteMapper;
    private CascDelMapper cascDelMapper;
    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(
            GoodsAtteServiceImpl.class);

    public CascDelMapper getCascDelMapper() {
        return cascDelMapper;
    }

    @Resource(name = "CascDelMapper")
    public void setCascDelMapper(CascDelMapper cascDelMapper) {
        this.cascDelMapper = cascDelMapper;
    }

    public GoodsAtteMapper getGoodsAtteMapper() {
        return goodsAtteMapper;
    }

    @Resource(name = "GoodsAtteMapper")
    public void setGoodsAtteMapper(GoodsAtteMapper goodsAtteMapper) {
        this.goodsAtteMapper = goodsAtteMapper;
    }

    /**
     * 删除商品关注信息
     *
     * @param atteId
     *            主键ID {@link java.lang.Long}
     * @return 删除的行数 {@link java.lang.Integer}
     */
    @Transactional
    public int deleteAtte(Long atteId) {
        // 定义一个HashMap集合
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("atteId", atteId);
            // 执行删除商品关系信息方法
            return goodsAtteMapper.deleteByPrimaryKey(map);
        } finally {
            // 打印日志
            LOGGER.info(ValueUtil.DELETEATTE + "，删除的关注ID为：" + atteId);
            this.cascDelMapper.cascDel("");
            map = null;
        }
    }

    /**
     * 批量删除商品关注信息
     *
     * @param atteIds
     *            {@link java.lang.Long}
     * @return 删除的行数 {@link java.lang.Integer}
     */
    @Transactional
    public int batchDelAtte(Long[] atteIds) {
        // 定义一个HashMap集合
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("atteIds", atteIds);
            // 打印日志
            LOGGER.info(ValueUtil.BATCHDELATTE);
            // 执行批量删除商品关注信息方法
            return goodsAtteMapper.batchDelete(map);
        } finally {
            this.cascDelMapper.cascDel("");
            map = null;
        }
    }

    /**
     * 根据分页参数和参数查询分页集合
     *
     * @param pb
     *            分页辅助Bean {@link com.ningpai.util.PageBean}
     * @return 封装过集合的PageBean {@link com.ningpai.util.PageBean}
     */
    public PageBean queryByPageAndParam(PageBean pb, SelectBean selectBean) {
        // 定义一个HashMap集合
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put(ValueUtil.CONDITION,
                    "".equals(selectBean.getCondition()) ? null : selectBean
                            .getCondition());
            map.put(ValueUtil.SEARCHTEXT,
                    "".equals(selectBean.getSearchText()) ? null : selectBean
                            .getSearchText());
            pb.setRows(goodsAtteMapper.queryTotalCount(map));
            map.put(ValueUtil.STARTROWNUM, pb.getStartRowNum());
            map.put(ValueUtil.ENDROWNUM, pb.getEndRowNum());
            pb.setList(goodsAtteMapper.queryByParam(map));
            return pb;
        } finally {
            map = null;
        }
    }

    /**
     * 根据货品ID和分页参数查询关注信息
     *
     * @param pb
     *            分页辅助Bean {@link com.ningpai.util.PageBean}
     * @param productId
     *            {@link java.lang.Long}
     * @return {@link com.ningpai.util.PageBean}
     */
    public PageBean querybyProductId(PageBean pb, Long productId) {
        // 定义一个HashMap集合
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("productId", productId);
            // 根据货品id和分页参数查询关注信息行数
            pb.setRows(this.goodsAtteMapper.queryTotalCountToProduct(map));
            map.put(ValueUtil.STARTROWNUM, pb.getStartRowNum());
            map.put(ValueUtil.ENDROWNUM, pb.getEndRowNum());
            // 根据货品id和分页参数查询关注信息集合
            pb.setList(this.goodsAtteMapper.queryByProductId(map));
            // 返回结果
            return pb;
        } finally {
            map = null;
        }
    }

    /**
     * 查询商品关注列表
     */
    @Override
    public int selectGoodsAtteCount() {
        // 返回结果
        return goodsAtteMapper.queryTotalCount(null);
    }

}
