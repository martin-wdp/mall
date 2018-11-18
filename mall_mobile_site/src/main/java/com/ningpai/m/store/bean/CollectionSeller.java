/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.m.store.bean;
import java.util.Date;
/**
 * 信息 Bean
 *
 * @author NINGPAI-zhangqiang
 * @since 2014年5月5日 下午5:27:01
 * @version 0.0.1
 */
public class CollectionSeller {
    /**
    *主键ID
     */
    private Long collectionSellerId;
    /**
    *第三方Id
     */
    private Long collectionThirdId;
    /**
    *用户Id
     */
    private Long collectionCustomerId;
    /**
    *是否删除
     */
    private String collectionDelFlag;
    /**
    *创建时间
     */
    private Date collectionCreateTime;
    /**
    *修改时间
     */
    private Date collectionModifyTime;

    public Long getCollectionSellerId() {
        return collectionSellerId;
    }

    public void setCollectionSellerId(Long collectionSellerId) {
        this.collectionSellerId = collectionSellerId;
    }

    public Long getCollectionThirdId() {
        return collectionThirdId;
    }

    public void setCollectionThirdId(Long collectionThirdId) {
        this.collectionThirdId = collectionThirdId;
    }

    public Long getCollectionCustomerId() {
        return collectionCustomerId;
    }

    public void setCollectionCustomerId(Long collectionCustomerId) {
        this.collectionCustomerId = collectionCustomerId;
    }

    public String getCollectionDelFlag() {
        return collectionDelFlag;
    }

    public void setCollectionDelFlag(String collectionDelFlag) {
        this.collectionDelFlag = collectionDelFlag;
    }

    public Date getCollectionCreateTime() {
        return collectionCreateTime;
    }

    public void setCollectionCreateTime(Date collectionCreateTime) {
        this.collectionCreateTime = collectionCreateTime;
    }

    public Date getCollectionModifyTime() {
        return collectionModifyTime;
    }

    public void setCollectionModifyTime(Date collectionModifyTime) {
        this.collectionModifyTime = collectionModifyTime;
    }
}
