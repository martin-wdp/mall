/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.gift.service;

import com.ningpai.gift.bean.Gift;
import com.ningpai.util.PageBean;

/**
 * @author ggn 赠品接口
 */
public interface GiftService {

    /**
     * 查询赠品列表
     * 
     * @param gift
     *            {@link com.ningpai.gift.bean.Gift}
     * @param pageBean
     *            {@link com.ningpai.util.PageBean}
     * @return PageBean
     */
    PageBean searchGiftList(Gift gift, PageBean pageBean);

    /**
     * 确定添加赠品
     * 
     * @param gift
     *            {@link com.ningpai.gift.bean.Gift}
     * @return int
     */
    int doAddGift(Gift gift);

    /**
     * 查询赠品详细信息
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
     *            {@link com.ningpai.gift.bean.Gift}
     * @return int
     */
    int doUpdateGift(Gift gift);

    /**
     * 删除赠品
     * 
     * @param giftId
     *            {@link java.lang.Long}
     * @return int
     */
    int delGift(Long giftId);

    /**
     * 批量删除赠品
     * 
     * @param giftId
     *            {@link java.lang.Long}
     * @return int
     */
    int delAllGift(Long[] giftId);

}
