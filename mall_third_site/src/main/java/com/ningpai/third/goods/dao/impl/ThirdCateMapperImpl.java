/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.third.goods.dao.impl;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.third.goods.bean.ThirdCate;
import com.ningpai.third.goods.dao.ThirdCateMapper;
import com.ningpai.third.goods.vo.ThirdCateVo;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 第三方分类DAO实现
 * </p>
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年5月6日 上午11:36:23
 * @version 2.0
 */
@Repository("ThirdCateMapper")
public class ThirdCateMapperImpl extends BasicSqlSupport implements
        ThirdCateMapper {
    /**
     * 删除商家分类
     * 
     * @param map
     *            删除的参数
     * @return
     */
    public int deleteByPrimaryKey(Map<String, Object> map) {
        return this
                .delete("com.ningpai.third.goods.dao.ThirdCateMapper.deleteByPrimaryKey",
                        map);
    }

    /**
     * 删除商家分类
     * 
     * @param map
     * @return
     */
    public int deleteByPrimaryKeyNew(Map<String, Object> map) {
        return this
                .delete("com.ningpai.third.goods.dao.ThirdCateMapper.deleteByPrimaryKeyNew",
                        map);
    }

    /**
     * 新增商家分类
     * 
     * @param record
     *            待插入的实体
     * @return
     */
    public int insertSelective(ThirdCate record) {
        return this.insert(
                "com.ningpai.third.goods.dao.ThirdCateMapper.insertSelective",
                record);
    }

    /**
     * 根据ID获取单个商家类型ID
     * 
     * @param catId
     *            分类ID {@link Integer}
     * @return
     */
    public ThirdCateVo selectByPrimaryKey(Long catId) {
        return this
                .selectOne(
                        "com.ningpai.third.goods.dao.ThirdCateMapper.selectByPrimaryKey",
                        catId);
    }

    /**
     * 修改单个的商家分类信息
     * 
     * @param record
     *            更新的商品实体 {@link com.ningpai.third.goods.bean.ThirdCate}
     * @return
     */
    public int updateByPrimaryKeySelective(ThirdCate record) {
        return this
                .update("com.ningpai.third.goods.dao.ThirdCateMapper.updateByPrimaryKeySelective",
                        record);
    }

    /**
     * 获取商家分类信息
     * 
     * @param map
     * @return
     */
    public List<ThirdCateVo> queryAllThirdCate(Map<String, Object> map) {
        return this
                .selectList(
                        "com.ningpai.third.goods.dao.ThirdCateMapper.queryAllThirdCate",
                        map);
    }

    /**
     * 根据父分类获取子分类的信息
     * 
     * @param cateId
     *            父分类ID
     * @return
     */
    public int querySonCountByParentId(Long cateId) {
        return this
                .selectOne(
                        "com.ningpai.third.goods.dao.ThirdCateMapper.querySonCountByParentId",
                        cateId);
    }

    /**
     * 获取单个分类信息
     * 
     * @param map
     * @return
     */
    public ThirdCate queryCateByCateName(Map<String, Object> map) {
        return this
                .selectOne(
                        "com.ningpai.third.goods.dao.ThirdCateMapper.queryCateByCateName",
                        map);
    }

    /**
     * 获取所有的商品分类集合
     * 
     * @param list
     * @return
     */
    public List<Object> selectProductCateList(List<Long> list) {
        return this
                .selectList(
                        "com.ningpai.third.goods.dao.ThirdCateMapper.selectProductCateList",
                        list);
    }

    /**
     * 获取所有的商家分类
     * 
     * @param thirdId
     * @return
     */
    public List<ThirdCate> queryAllCate(Long thirdId) {
        return this.selectList(
                "com.ningpai.third.goods.dao.ThirdCateMapper.queryAllCate",
                thirdId);
    }

    /**
     * 获取商家分类集合
     * 
     * @param thirdId
     * @return
     */
    public List<ThirdCateVo> queryAllCateForList(Long thirdId) {
        return this
                .selectList(
                        "com.ningpai.third.goods.dao.ThirdCateMapper.queryAllParentCate",
                        thirdId);
    }

    /**
     * 获取商家分类集合
     * 
     * @param map
     *            封装参数 catId 父分类ID thirdId 第三方店家ID
     * @return
     */
    public List<ThirdCate> queryThirdCateByParentId(Map<String, Object> map) {
        return this
                .selectList(
                        "com.ningpai.third.goods.dao.ThirdCateMapper.queryThirdCateByParentId",
                        map);
    }

    /**
     * 获取商家分类集合
     * 
     * @param thirdId
     *            封装参数 catId 父分类ID thirdId 第三方店家ID
     * @return
     */
    public List<ThirdCate> queryThirdCateByParentIdtwo(Long thirdId) {
        return this
                .selectList(
                        "com.ningpai.third.goods.dao.ThirdCateMapper.queryThirdCateByParentIdtwo",
                        thirdId);
    }

    /**
     * 获取商家分类集合
     * 
     * @param map
     *            封装参数 thirdId 第三方ID catName 分类名称 grade 层级标记
     * @return
     */
    public List<ThirdCate> queryThirdCateByCatNameAndGrade(
            Map<String, Object> map) {
        return this
                .selectList(
                        "com.ningpai.third.goods.dao.ThirdCateMapper.queryThirdCateByCatNameAndGrade",
                        map);
    }

    /**
     * 获取商家分类集合
     * 
     * @param map
     * @return
     */
    @Override
    public List<ThirdCate> querySonCatByParm(Map<String, Object> map) {
        return this
                .selectList(
                        "com.ningpai.third.goods.dao.ThirdCateMapper.queryThirdSonCateByParam",
                        map);
    }

}
