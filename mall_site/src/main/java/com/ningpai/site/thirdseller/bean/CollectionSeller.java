package com.ningpai.site.thirdseller.bean;



import java.util.Date;

/**
 * 第三方控制器
 * @Description 第三方控制器
 * @author Songhl
 * @since 2015年8月28日 14:37:39
 */
public class CollectionSeller {
    /**
     * 主键ID
     */
    private Long collectionSellerId;
    /**
     * 第三方Id
     */
    private Long collectionThirdId;
    /**
     * 用户Id
     */
    private Long collectionCustomerId;
    /**
     * 是否删除
     */
    private String collectionDelFlag;
    /**
     * 创建时间
     */
    private Date collectionCreateTime;
    /**
     * 修改时间
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
        return (Date) collectionCreateTime.clone();
    }

    public void setCollectionCreateTime(Date collectionCreateTime) {
        this.collectionCreateTime = collectionCreateTime == null?null: (Date) collectionCreateTime.clone();
    }

    public Date getCollectionModifyTime() {
        return (Date) collectionModifyTime.clone();
    }

    public void setCollectionModifyTime(Date collectionModifyTime) {
        this.collectionModifyTime = collectionModifyTime == null?null: (Date) collectionModifyTime.clone();
    }
}
