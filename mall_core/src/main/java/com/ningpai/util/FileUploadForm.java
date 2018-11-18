/*
 *
 * Copyright 2010-2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.util;

import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传表单
 * 
 * @author NP-HEHU
 * @date 2015-8-28 16:55:21
 */
public class FileUploadForm {
    // 优惠券图片
    private MultipartFile couponImg;

    // 赠品图片
    private MultipartFile[] giftImg;

    // 图片文件
    private MultipartFile partPic;

    public MultipartFile getPartPic() {
        return partPic;
    }

    public void setPartPic(MultipartFile partPic) {
        this.partPic = partPic;
    }

    /**
     * 获取动态图片文件
     * 
     * @return 动态图片文件
     */
    public MultipartFile[] getGiftImg() {
        MultipartFile[] temp = this.giftImg.clone();
        if (temp == null || temp.length == 0) {
            return new MultipartFile[0];
        }
        return temp;
    }

    /**
     * 设置动态图
     * 
     * @param giftImg
     *            动态图
     */
    public void setGiftImg(MultipartFile[] giftImg) {
        if (null != giftImg) {
            MultipartFile[] temp = giftImg.clone();
            this.giftImg = temp;
        }
    }

    public MultipartFile getCouponImg() {
        return couponImg;
    }

    public void setCouponImg(MultipartFile couponImg) {
        this.couponImg = couponImg;
    }

}
