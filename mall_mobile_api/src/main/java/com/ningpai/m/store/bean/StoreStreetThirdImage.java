/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.m.store.bean;


import org.springframework.stereotype.Controller;

import java.util.Date;

/**
 * 店铺街图片实体类
 * zhanghailong  20150615
 */
@Controller
public class StoreStreetThirdImage {
    //图片Id
    private Long imageId;
    //广告图片状态
    private Long status;
    //店铺Id
    private Long storeId;
    //排序
    private Long stort;
    //是否删除
    private Long delfage;
    //上传时间
    private Date createtime;
    //图片地址
    private String imageAddress;

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Long getStort() {
        return stort;
    }

    public void setStort(Long stort) {
        this.stort = stort;
    }

    public Long getDelfage() {
        return delfage;
    }

    public void setDelfage(Long delfage) {
        this.delfage = delfage;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getImageAddress() {
        return imageAddress;
    }

    public void setImageAddress(String imageAddress) {
        this.imageAddress = imageAddress;
    }
}
