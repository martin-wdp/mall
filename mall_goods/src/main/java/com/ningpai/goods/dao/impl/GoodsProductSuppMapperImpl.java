/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.goods.bean.GoodsProductSupp;
import com.ningpai.goods.dao.GoodsProductSuppMapper;
import com.ningpai.goods.vo.GoodsProductSuppVo;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * 货品关联服务支持Dao实现
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年7月31日 上午10:07:29
 * @version 1.0
 */
@Repository("GoodsProductSuppMapper")
public class GoodsProductSuppMapperImpl extends BasicSqlSupport implements
        GoodsProductSuppMapper {

    /**
     * 根据主键删除关联信息
     *
     * @param map
     *            需要删除的主键ID和操作人名称
     * @return 删除影响的行数
     */
    public int deleteByPrimaryKey(Long suppId) {
        return this
                .delete("com.ningpai.goods.dao.GoodsProductSuppMapper.deleteByPrimaryKey",
                        suppId);
    }

    /**
     * 新建关联记录
     *
     * @param record
     *            需要插入的实体
     * @return 插入的行数
     */
    public int insertSelective(GoodsProductSupp record) {
        return this.insert(
                "com.ningpai.goods.dao.GoodsProductSuppMapper.insertSelective",
                record);
    }

    /**
     * 更新关联信息
     *
     * @param record
     *            需要更新的关联实体
     * @return 更新影响的行数
     */
    public int updateByPrimaryKeySelective(GoodsProductSupp record) {
        return this
                .update("com.ningpai.goods.dao.GoodsProductSuppMapper.updateByPrimaryKeySelective",
                        record);
    }

    /**
     * 根据货品ID查询所有的关联记录集合
     *
     * @param productId
     *            货品ID
     * @return 查询到的关联集合
     */
    public List<GoodsProductSupp> queryAllByProductId(Long productId) {
        return this
                .selectList(
                        "com.ningpai.goods.dao.GoodsProductSuppMapper.queryAllByProductId",
                        productId);
    }

    /**
     * 删除所有的关联信息
     *
     * @param map
     *            货品ID和操作人名称
     * @return 批量删除影响的行数
     */
    public int delAllProductSupp(Long productId) {
        return this
                .update("com.ningpai.goods.dao.GoodsProductSuppMapper.delAllProductSupp",
                        productId);
    }

    /**
     * 批量保存关联信息
     *
     * @param map
     *            货品ID和服务ID数组
     * @return 插入的行数
     */
    public int batchInsertSupp(Map<String, Object> map) {
        return this.insert(
                "com.ningpai.goods.dao.GoodsProductSuppMapper.batchInsertSupp",
                map);
    }

    /**
     * 根据货品ID查询所有的关联服务的VoBean
     *
     * @param productId
     *            货品ID
     * @return 查询到的集合
     */
    public List<GoodsProductSuppVo> queryAllVoByProductId(Long productId) {
        return this
                .selectList(
                        "com.ningpai.goods.dao.GoodsProductSuppMapper.queryAllVoByProductId",
                        productId);
    }

}
