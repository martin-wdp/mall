/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.gift.service;

import java.util.List;

import com.ningpai.gift.bean.GiftCate;
import com.ningpai.util.PageBean;

/**
 * @author ggn 赠品分类service接口
 */
public interface GiftCateService {

    /**
     * 查询分类
     *
     * @return List
     */
    List<GiftCate> searchGiftCateList(Long giftCateId);

    /**
     * 分页查询赠品分类
     * 
     * @param giftCate
     *            {@link com.ningpai.gift.bean.GiftCate}
     * @param pageBean
     *            {@link com.ningpai.util.PageBean}
     * @return PageBean
     */
    PageBean searchGiftCateList(GiftCate giftCate, PageBean pageBean);

    /**
     * 不带分页查询赠品分类
     * @param giftCate
     * @return
     */
    List<Object> searchGiftCateListNoPage(GiftCate giftCate);

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
     * 修改赠品分类
     * 
     * @param giftCate
     *            {@link com.ningpai.gift.bean.GiftCate}
     * @return int
     */
    int updateGiftCate(GiftCate giftCate);

    /**
     * 删除赠品分类
     * 
     * @param giftCateId
     *            {@link java.lang.Long}
     */
    int delGiftCate(Long giftCateId);

    /**
     * 批量删除赠品分类
     * 
     * @param giftCateId
     *            {@link java.lang.Long}
     */
    int delAllGiftCate(Long[] giftCateId);

    /**
     * 查询所有赠品分类 作为赠品添加时的下来菜单
     * 
     * @return List
     */
    List<GiftCate> selectGiftListUseSelect();

    /**
     * 查询分类
     * 
     * @return List
     */
    List<GiftCate> searchGiftCateList();

    /**
     * 验证是否可以删除 如果传递过来的分类 下面有子类 就返回false表示不可以删除
     * 
     * @param cateId
     *            将要删除的分类ID
     * @return 是否可以删除
     */
    boolean checkDelGiftCate(Long cateId);
}
