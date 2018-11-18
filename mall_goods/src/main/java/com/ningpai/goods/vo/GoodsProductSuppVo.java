/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.vo;

import com.ningpai.system.bean.ServiceSupport;

/**
 * 货品服务支持Vo
 * */
public class GoodsProductSuppVo {
    /*
     *主键ID
      */
    private Long id;
    /*
     *货品ID
      */
    private Long productId;
    /*
     *支持ID
      */
    private Long suppId;
    /*
     *删除标记
      */
    private String delFlag;
    /*
     *服务支持Bean
      */
    private ServiceSupport serviceSupp;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getSuppId() {
        return suppId;
    }

    public void setSuppId(Long suppId) {
        this.suppId = suppId;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public ServiceSupport getServiceSupp() {
        return serviceSupp;
    }

    public void setServiceSupp(ServiceSupport serviceSupp) {
        this.serviceSupp = serviceSupp;
    }

}
