/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.order.bean.vo;

import java.math.BigDecimal;

/**
 * 配送方式Bean
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年1月2日 下午4:34:20
 * @version 1.0
 */
public class ExpressBean {
    /**
     * 配送ID
     */
    private Long expid;
    /**
     * 配送公司
     */
    private String expCompany;
    /**
     * 运费
     */
    private BigDecimal expPrice;
    /**
     * 承运公司
     */
    private String expAccept;
    /**
     * 备注
     */
    private String expComment;

    public Long getExpid() {
        return expid;
    }

    public void setExpid(Long expid) {
        this.expid = expid;
    }

    public String getExpCompany() {
        return expCompany;
    }

    public void setExpCompany(String expCompany) {
        this.expCompany = expCompany;
    }

    public BigDecimal getExpPrice() {
        return expPrice;
    }

    public void setExpPrice(BigDecimal expPrice) {
        this.expPrice = expPrice;
    }

    public String getExpAccept() {
        return expAccept;
    }

    public void setExpAccept(String expAccept) {
        this.expAccept = expAccept;
    }

    public String getExpComment() {
        return expComment;
    }

    public void setExpComment(String expComment) {
        this.expComment = expComment;
    }

}
