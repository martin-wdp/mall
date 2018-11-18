/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.site.shoppingcart.util;

/**
 * @version 1.0
 */
public final class CompanyFreightExpress {

    //快递公司名字
    private String companyName;
    //快递公司id
    private Long companyId;
    //快递公司所属商家id
    private Long companyThirdId;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getCompanyThirdId() {
        return companyThirdId;
    }

    public void setCompanyThirdId(Long companyThirdId) {
        this.companyThirdId = companyThirdId;
    }
}
