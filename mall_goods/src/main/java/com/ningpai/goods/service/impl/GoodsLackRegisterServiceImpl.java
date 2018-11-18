/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.service.impl;

import com.ningpai.goods.bean.GoodsLackRegister;
import com.ningpai.goods.dao.CascDelMapper;
import com.ningpai.goods.dao.GoodsLackRegisterMapper;
import com.ningpai.goods.service.GoodsLackRegisterService;
import com.ningpai.goods.util.LackRegisterSearchBean;
import com.ningpai.goods.util.ValueUtil;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 到货通知Service实现
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月18日 上午11:46:02
 * @version 1.0
 */
@Service("GoodsLackRegisterService")
public class GoodsLackRegisterServiceImpl implements GoodsLackRegisterService {
    // 到货通知Mapper
    private GoodsLackRegisterMapper goodsLackRegisterMapper;
    private CascDelMapper cascDelMapper;
    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(
            GoodsLackRegisterServiceImpl.class);

    public CascDelMapper getCascDelMapper() {
        return cascDelMapper;
    }

    @Resource(name = "CascDelMapper")
    public void setCascDelMapper(CascDelMapper cascDelMapper) {
        this.cascDelMapper = cascDelMapper;
    }

    public GoodsLackRegisterMapper getGoodsLackRegisterMapper() {
        return goodsLackRegisterMapper;
    }

    @Resource(name = "GoodsLackRegisterMapper")
    public void setGoodsLackRegisterMapper(
            GoodsLackRegisterMapper goodsLackRegisterMapper) {
        this.goodsLackRegisterMapper = goodsLackRegisterMapper;
    }

    /**
     * 插入一条到货通知记录
     *
     * @param goodsLackRegister
     *            {@link com.ningpai.goods.bean.GoodsLackRegister}
     * @return 插入的主键ID {@link java.lang.Integer}
     */
    @Transactional
    public int insert(GoodsLackRegister goodsLackRegister) {
        // 打印日志
        LOGGER.info(ValueUtil.INSERTLACKREG);
        // 插入一条到货通知记录
        return this.goodsLackRegisterMapper.insertSelective(goodsLackRegister);
    }

    /**
     * 更新到货通知记录
     *
     * @param goodsLackRegister
     *            {@link com.ningpai.goods.bean.GoodsLackRegister}
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    @Transactional
    public int update(GoodsLackRegister goodsLackRegister) {
        // 打印日志
        LOGGER.info(ValueUtil.UPDATELACKREG);
        // 更新到货通知记录
        return this.goodsLackRegisterMapper
                .updateByPrimaryKeySelective(goodsLackRegister);
    }

    /**
     * 根据主键查询到货通知
     *
     * @param lackRegisterId
     *            {@link java.lang.Long}
     * @return 查询到的实体 {@link com.ningpai.goods.bean.GoodsLackRegister}
     */
    public GoodsLackRegister queryByPrimaryId(Long lackRegisterId) {
        // 根据主键查询到货通知
        return this.goodsLackRegisterMapper.selectByPrimaryKey(lackRegisterId);
    }

    /**
     * 根据PageBean查询分页列表
     *
     * @param pb
     *            {@link com.ningpai.goods.util.PageBean}
     * @return {@link com.ningpai.goods.util.PageBean}
     */
    public PageBean queryByPageBean(PageBean pb) {
        // 查询总行数
        pb.setRows(this.goodsLackRegisterMapper.queryTotalCount());
        Map<String, Integer> map = new HashMap<String, Integer>();
        try {
            // 封装分页参数
            map.put(ValueUtil.STARTROWNUM, pb.getStartRowNum());
            map.put(ValueUtil.ENDROWNUM, pb.getEndRowNum());
            // 设置列表
            pb.setList(this.goodsLackRegisterMapper.queryAllByPageBean(map));
        } finally {
            // 参数置空
            map = null;
        }
        return pb;
    }

    /**
     * 批量进行到货通知
     *
     * @param lackIds
     *            到货通知实体的id数组 {@link java.lang.Long}
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    @Transactional
    public int updateLackRegisterStatus(Long[] lackIds) {
        // 受影响的行数
        Integer count = 0;
        try {
            for (int i = 0; i < lackIds.length; i++) {
                // 批量进行到货通知
                count += this.goodsLackRegisterMapper
                        .updateNoticeStatus(lackIds[i]);
            }
            return count;
        } finally {
            // 打印日志
            LOGGER.info(ValueUtil.UPDATELACKREGISTERSTATUS);
            count = null;
        }
    }

    /**
     * 根据参数查询
     *
     * @param pb
     *            分页
     * @param searchBean
     *            参数BEAN
     * @return 分页Bean
     */
    public PageBean queryByPageBeanAndSearchBean(PageBean pb,
            LackRegisterSearchBean searchBean) {
        if (null != searchBean && null != searchBean.getGoodsName()
                && !"".equals(searchBean.getGoodsName())) {
                searchBean.setCondition("-1");
                searchBean.setSearchText("");
        }
        // 设置总行数
        pb.setRows(this.goodsLackRegisterMapper
                .queryTotalCountBySearchBean(searchBean));
        // 定义一个HashMap集合
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // 封装分页参数
            map.put(ValueUtil.STARTROWNUM, pb.getStartRowNum());
            map.put(ValueUtil.ENDROWNUM, pb.getEndRowNum());
            map.put(ValueUtil.SEARCHBEAN, searchBean);
            // 设置集合数据
            pb.setList(this.goodsLackRegisterMapper
                    .queryByPageBeanAndSearchBean(map));
        } finally {
            map = null;
        }
        return pb;
    }

    /**
     * 批量删除到货通知
     *
     * @param lackIds
     *            待删除的到货通知的ID数组
     * @return 批量删除的行数
     */
    @Transactional
    public int batchDel(Long[] lackIds) {
        Integer count = 0;
        try {
            for (int i = 0; i < lackIds.length; i++) {
                // 批量删除到货通知
                count += this.goodsLackRegisterMapper
                        .deleteByPrimaryKey(lackIds[i]);
            }
            return count;
        } finally {
            // 打印日志
            LOGGER.info(ValueUtil.BATCHDELLACKREG);
            this.cascDelMapper.cascDel("");
            count = null;
        }
    }

    /**
     * 根据货品Id更新通知状态
     *
     * @param productId
     *            货品ID {@link java.lang.Long}
     * @return 更新的行数 {@link java.lang.Integer}
     */
    @Transactional
    public int updateStatusByProductId(Long productId) {
        // 打印日志
        LOGGER.info(ValueUtil.UPDATESTATUSBYPRODUCTID);
        // 根据货品Id更新通知状态
        return this.goodsLackRegisterMapper.updateStatusByProductId(productId);
    }

}
