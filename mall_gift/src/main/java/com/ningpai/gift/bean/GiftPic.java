/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.gift.bean;

/**
 * 赠品图片
 * 
 * @author ggn 2014-03-18
 * 
 */
public class GiftPic {
    /*
     *  图片ID
     */
    private Long picId;
    /*
     *  赠品ID
     */
    private Long giftId;
    /*
     *  图片URL
     */
    private String picUrl;
    /*
     *  是否删除 0正常 1删除
     */
    private String delFlag;
    /*
     * 小图
     */
    private String picLittle;
    /*
     * 中图
     */
    private String picMiddle;
    /*
     * 大图
     */
    private String picBig;

    public Long getPicId() {
        return picId;
    }

    public void setPicId(Long picId) {
        this.picId = picId;
    }

    public Long getGiftId() {
        return giftId;
    }

    public void setGiftId(Long giftId) {
        this.giftId = giftId;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getPicLittle() {
        return picLittle;
    }

    public void setPicLittle(String picLittle) {
        this.picLittle = picLittle;
    }

    public String getPicMiddle() {
        return picMiddle;
    }

    public void setPicMiddle(String picMiddle) {
        this.picMiddle = picMiddle;
    }

    public String getPicBig() {
        return picBig;
    }

    public void setPicBig(String picBig) {
        this.picBig = picBig;
    }
}
