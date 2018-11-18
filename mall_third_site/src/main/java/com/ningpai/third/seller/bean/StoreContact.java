/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.third.seller.bean;

/**
 * 店铺联系人 Bean
 * 
 * @author Zhouh
 * @since 2014年5月5日 下午5:26:36
 * @version 2.0
 */
public class StoreContact {
    // 卖家序号
    private Long conId;
    // 店铺序号
    private Long storeId;
    // 枚举1-5
    private String type;
    // 联系人姓名
    private String name;
    // 手机
    private String pho;
    // 座机
    private String tel;
    // email
    private String email;
    // QQ
    private String qq;
    // 是否删除
    private String delFlag;

    public Long getConId() {
        return conId;
    }

    public void setConId(Long conId) {
        this.conId = conId;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPho() {
        return pho;
    }

    public void setPho(String pho) {
        this.pho = pho;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
}
