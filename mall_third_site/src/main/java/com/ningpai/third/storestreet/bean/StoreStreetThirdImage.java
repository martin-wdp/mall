/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.third.storestreet.bean;


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
    private String status;
    //店铺Id
    private Long storeId;
    //排序
    private Long stort;
    //是否删除
    private String delfage;
    //上传时间
    private Date createtime;
    //图片地址
    private String imageAddress;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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

    public String getDelfage() {
        return delfage;
    }

    public void setDelfage(String delfage) {
        this.delfage = delfage;
    }

    /**
     * 获取时间
     * */
    public Date getCreatetime() {
        return createtime==null?null: (Date) createtime.clone();
    }
    /**
     * 设置时间
     * */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime==null?null: (Date) createtime.clone();
    }

    public String getImageAddress() {
        return imageAddress;
    }

    public void setImageAddress(String imageAddress) {
        this.imageAddress = imageAddress;
    }
}
