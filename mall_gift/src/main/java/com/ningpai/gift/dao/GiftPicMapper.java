/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.gift.dao;

import java.util.List;

import com.ningpai.gift.bean.GiftPic;

/**
 * 赠品图片接口
 * 
 * @author ggn
 * 
 */
public interface GiftPicMapper {
    /**
     * 创建赠品图片
     * 
     * @param list
     *            {@link java.util.List}
     * @return int
     */
    int createGiftPic(List<GiftPic> list);

    /**
     * 查询赠品图片 根据赠品Id
     * 
     * @param giftId
     *            {@link java.lang.Long}
     * @return List
     */
    List<GiftPic> selectGiftPicByGiftId(Long giftId);

    /**
     * 删除赠品图片根据赠品Id
     * 
     * @param giftId
     *            {@link java.lang.Long}
     * @return int
     */
    int deleteGiftPicByGiftId(Long giftId);

    /**
     * 批量删除赠品图片 根据多个赠品Id
     * 
     * @param list
     *            {@link java.util.List}
     * @return int
     */
    int deleteAllGiftPicByGiftId(List<Long> list);

    /**
     * 根据图片ID删除赠品图片
     * 
     * @param picId
     *            {@link java.lang.Long}
     * @return int
     */
    int delGiftPicByPicId(Long picId);

    /**
     * 添加赠品图片
     * 
     * @param image
     * @return Long
     */
    Long savePic(GiftPic image);
}
