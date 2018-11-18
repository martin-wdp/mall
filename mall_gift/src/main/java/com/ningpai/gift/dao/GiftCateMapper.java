/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.gift.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.gift.bean.GiftCate;

/**
 * 赠品分类接口
 * 
 * @author ggn
 * 
 */
public interface GiftCateMapper {
    /**
     * 查询所有分类
     *
     * @return List
     */
    List<GiftCate> searchGiftCateList(Long giftCateId);

    /**
     * 查询赠品分类总数
     * 
     * @param paramMap
     *            {@link java.util.Map}
     * @return int
     */
    int searchGiftCateListCount(Map<String, Object> paramMap);

    /**
     * 查询赠品分类列表
     * 
     * @param paramMap
     *            {@link java.util.Map}
     * @return int
     */
    List<GiftCate> searchGiftParentCateList(Map<String, Object> paramMap);

    /**
     * 查询赠品分类详细信息
     * 
     * @param giftCateId
     *            {@link java.lang.Long}
     * @return GiftCate
     */
    GiftCate searchGiftCateById(Long giftCateId);

    /**
     * 添加赠品分类信息
     * 
     * @param giftCate
     *            {@link com.ningpai.gift.bean.GiftCate}
     * @return int
     */
    int addGiftCate(GiftCate giftCate);

    /**
     * 修改赠品分类信息
     * 
     * @param giftCate
     *            {@link com.ningpai.gift.bean.GiftCate}
     * @return int
     */
    int updateGiftCate(GiftCate giftCate);

    /**
     * 逐个删除赠品分类
     * 
     * @param giftCateId
     *            {@link java.lang.Long}
     * @return int
     */
    int delGiftCate(Long giftCateId);

    /**
     * 批量删除赠品分类
     * 
     * @param list
     *            {@link java.util.List}
     * @return int
     */
    int delAllGiftCate(List<Long> list);

    /**
     * 查询赠品分类列表 作为赠品添加时候的下拉菜单
     * 
     * @return List
     */
    List<GiftCate> selectGiftListUseSelect();

    /**
     * 查询所有分类
     * 
     * @return List
     */
    List<GiftCate> searchGiftCateList();

    /**
     * 根据父分类ID查询子分类的个数
     * 
     * @param cateId
     *             父分类Id
     * @return int
     */
    int querySonCateByParentId(Long cateId);
}
