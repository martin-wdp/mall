/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.gift.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.ningpai.gift.bean.GiftPic;

/**
 * @author ggn 2014-03-21 赠品图片接口
 */
public interface GiftPicService {
    /**
     * 根据图片ID删除赠品图片
     * 
     * @param picId
     *            {@link java.lang.Long}
     * @return int
     */
    int delGiftPicByPicId(Long picId);

    /**
     * 查询赠品图片列表根据赠品Id
     * 
     * @param giftId
     *            {@link java.lang.Long}
     * @return List
     */
    List<GiftPic> selectGiftPicByGiftId(Long giftId);

    /**
     * 新boss保存赠品图片
     *
     * @param giftId
     * @param url
     */
    Boolean newuploadImage(Long giftId, String[] url);

    /**
     * 插入图片
     * 
     * @param giftId
     * @param attribute
     * @param fileList
     * @param request
     * @return GiftPic
     */
    GiftPic uploadImage(Long giftId, String attribute, List<MultipartFile> fileList, HttpServletRequest request);

}
