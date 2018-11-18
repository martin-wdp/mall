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

import com.ningpai.goods.bean.GoodsSpecDetail;
import com.ningpai.goods.dao.CascDelMapper;
import com.ningpai.goods.dao.GoodsSpecDetailMapper;
import com.ningpai.goods.service.GoodsSpecDetailService;
import com.ningpai.goods.util.ValueUtil;
import com.ningpai.util.MyLogger;

/**
 * 规格值Service实现
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月18日 下午4:28:28
 * @version 1.0
 */
@Service("GoodsSpecDetailService")
public class GoodsSpecDetailServiceImpl implements GoodsSpecDetailService {
    private GoodsSpecDetailMapper goodsSpecDetailMapper;
    private CascDelMapper cascDelMapper;
    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(
            GoodsSpecDetailServiceImpl.class);

    public CascDelMapper getCascDelMapper() {
        return cascDelMapper;
    }

    @Resource(name = "CascDelMapper")
    public void setCascDelMapper(CascDelMapper cascDelMapper) {
        this.cascDelMapper = cascDelMapper;
    }

    public GoodsSpecDetailMapper getGoodsSpecDetailMapper() {
        return goodsSpecDetailMapper;
    }

    @Resource(name = "GoodsSpecDetailMapper")
    public void setGoodsSpecDetailMapper(
            GoodsSpecDetailMapper goodsSpecDetailMapper) {
        this.goodsSpecDetailMapper = goodsSpecDetailMapper;
    }

    /**
     * 添加规格值
     *
     * @param goodsSpecDetail
     *            添加的规格值 {@link com.ningpai.goods.bean.GoodsSpecDetail}
     * @param username
     *            操作人名称
     * @return {@link java.lang.Integer}
     */
    @Transactional
    public int saveSpecDetail(GoodsSpecDetail goodsSpecDetail, String username) {
        Integer count = 0;
        //设置规格创建人名称
        goodsSpecDetail.setSpecDetailCreateName(username);
        //插入一条规格值
        count += this.goodsSpecDetailMapper.insertSelective(goodsSpecDetail);
        //打印日志
        LOGGER.info(ValueUtil.SAVESPECDETAIL + username);
        //返回结果
        return count;
    }

    /**
     * 批量删除规格值
     *
     * @param specDetailId
     *            规格值ID
     * @param username
     *            删除人名称
     * @return 1.0
     */
    @Transactional
    public int delSpecDetail(Long specDetailId, String username) {
        // 把参数封装到Map中
        Map<String, String> map = new HashMap<String, String>();
        try {
            //把username放进map集合中
            map.put("delName", username);
            //把规格放进map集合中
            map.put("specDetailId", specDetailId.toString());
            // 执行删除
            return this.goodsSpecDetailMapper.deleteByPrimaryKey(map);
        } finally {
            //打印日志
            LOGGER.info(ValueUtil.DELSPECDETAIL + username);
            this.cascDelMapper.cascDel(username);
            map = null;
        }
    }

    /**
     * 更新规格值
     *
     * @param goodsSpecDetail
     *            需要更新的规格值 {@link com.ningpai.goods.bean.GoodsSpecDetail}
     * @param name
     *            操作人名称
     * @return {@link java.lang.Integer}
     */
    @Transactional
    public int updateSpecDetail(GoodsSpecDetail goodsSpecDetail, String name) {
        goodsSpecDetail.setSpecDetailModifiedName(name);
        //打印日志
        LOGGER.info(ValueUtil.UPDATESPECDETAIL + name);
        //更新规格值
        return this.goodsSpecDetailMapper
                .updateByPrimaryKeySelective(goodsSpecDetail);
    }

    /**
     * 根据规格ID查询规格值列表
     *
     * @param specId
     *            {@link java.lang.Long}
     * @return {@link java.util.List}
     */
    public List<Object> queryBySpecId(Long specId) {
        //根据规格ID查询规格值列表
        return this.goodsSpecDetailMapper.querySpecDeetailBySpecId(specId);
    }

    /**
     * 根据规格值ID查询
     *
     * @param specDetailId
     *            规格值ID {@Link java.lang.Long}
     * @return 查询到的规格值 {@link com.ningpai.goods.bean.GoodsSpecDetail}
     */
    public GoodsSpecDetail queryByPrimaryKey(Long specDetailId) {
        //根据主键查询规格值
        return this.goodsSpecDetailMapper.selectByPrimaryKey(specDetailId);
    }

}
