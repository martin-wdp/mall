/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.m.customer.vo;

/**
 * 省份bean
 * 
 * @author NINGPAI-zhangqiang
 * @since 2013年12月23日 上午11:08:51
 * @version
 */
public class ProvinceBean {
    // 省份编号
    private Long provinceId;
    // 省份名称
    private String provinceName;

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }
}
