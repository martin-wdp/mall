/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.gift.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.gift.bean.Gift;

/**
 * 赠品接口
 * 
 * @author ggn
 * 
 */

public interface GiftMapper {

    /**
     * 查询赠品总数
     * 
     * @param paramMap
     *            {@link java.util.Map}
     * @return int
     */
    int searchGiftListCount(Map<String, Object> paramMap);

    /**
     * 查询赠品列表
     * 
     * @param paramMap
     *            {@link java.util.Map}
     * @return int
     */
    List<Object> searchGiftList(Map<String, Object> paramMap);

    /**
     * 创建赠品
     * 
     * @param gift
     *            {@link com.ningpai.gift.bean.Gift}
     * @return int
     */
    int doAddGift(Gift gift);

    /**
     * 查询最新的ID
     * 
     * @return Long
     */
    Long selectLastId();

    /**
     * 查询赠品图片
     * 
     * @param giftId
     *            {@link java.lang.Long}
     * @return Gift
     */
    Gift selectGiftDetailById(Long giftId);

    /**
     * 修改赠品
     * 
     * @param gift
     *            {@link java.lang.Long}
     * @return int
     */
    int doUpdateGift(Gift gift);

    /**
     * 删除赠品
     * 
     * @param giftId
     *            {@link java.lang.Long}
     * @return delFlag
     */
    int delGift(Long giftId);

    /**
     * 批量删除赠品
     * 
     * @param list
     *            {@link java.util.List}
     * @return int
     */
    int delAllGift(List<Long> list);

    /**
     * 查询多个赠品信息
     * 
     * @param list
     *            {@link java.util.List}
     * @return List
     */
    List<Gift> selectGiftByListId(List<Long> list);

}
