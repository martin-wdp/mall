package com.ningpai.thirdaudit.bean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 货品信息实体 Created by rennn on 2015/7/31.
 */
public class GoodsProduct {
    // 主键ID
    private Long goodsInfoId;
    // 商品ID
    private Long goodsId;
    // 货号
    /* @Length(min = 10, max = 32, message = "货品编号 长度必须在10字符 ~ 32字符之间.") */
    // @Pattern(regexp = "[0-9]+$", message = "货品编号必须为数字.")
    private String goodsInfoItemNo;
    // 货品名称
    /* @Length(min = 3, max = 50, message = "货品名称 长度必须在 3字符 ~ 50字符之间.") */
    // @Pattern(regexp = "[^''\\[\\]\\<\\>?!]+")
    private String goodsInfoName;
    // 货品副标题
    private String goodsInfoSubtitle;
    // 是否上架 默认1上架
    private String goodsInfoAdded;
    // 上架时间
    private Date goodsInfoAddedTime;
    // 下架时间
    private Date goodsInfounAddedTime;
    // 货品库存
    private Long goodsInfoStock;
    // 销售价
    private BigDecimal goodsInfoPreferPrice;
    // 成本价
    private BigDecimal goodsInfoCostPrice;
    // 市场价
    private BigDecimal goodsInfoMarketPrice;
    // 重量
    private BigDecimal goodsInfoWeight;
    // 货品初始图片
    private String goodsInfoImgId;
    // 创建人名称
    private String goodsInfoCreateName;
    // 创建时间
    private Date goodsInfoCreateTime;
    // 修改人名称
    private String goodsInfoModifiedName;
    // 修改时间
    private Date goodsInfoModifiedTime;
    // 删除人名称
    private String goodsInfoDelName;
    // 删除时间
    private Date goodsInfoDelTime;
    // 删除标记
    private String goodsInfoDelflag;
    // 第三方ID
    private Long thirdId;
    // 第三方店铺名称
    private String thirdName;
    // 第三方标记 0:不是第三方商品 1:第三方商品 2:B2B商品
    private String isThird;
    /* 是否显示在列表页 */
    private String showList;
    /* 是否在手机版显示 */
    private String showMobile;
    /* 是否参加会员折扣 */
    private String isCustomerDiscount;
    /* 审核状态 */
    private String auditStatus;
    /* 商品审核拒绝原因 */
    private String refuseReason;

    private BigDecimal discountInfo;

    private BigDecimal discountPrice;
    // 运费模板id
    private Long freghtId;

    // 是否提供包邮 1,卖家提供邮费 0，买家提供邮费
    private String isMailBay;

    // 关注商品地区
    private String disName;
    // 关注商品收藏的价格
    private BigDecimal ofollowPrice;
    // 降低以后的新价格
    private BigDecimal nfollowPrice;

    public Long getFreghtId() {
        return freghtId;
    }

    public void setFreghtId(Long freghtId) {
        this.freghtId = freghtId;
    }

    public BigDecimal getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(BigDecimal discountPrice) {
        this.discountPrice = discountPrice;
    }

    public BigDecimal getDiscountInfo() {
        return discountInfo;
    }

    public void setDiscountInfo(BigDecimal discountInfo) {
        this.discountInfo = discountInfo;
    }

    public String getIsMailBay() {
        return isMailBay;
    }

    public void setIsMailBay(String isMailBay) {
        this.isMailBay = isMailBay;
    }

    public String getIsCustomerDiscount() {
        return isCustomerDiscount;
    }

    public void setIsCustomerDiscount(String isCustomerDiscount) {
        this.isCustomerDiscount = isCustomerDiscount;
    }

    public Long getGoodsInfoId() {
        return goodsInfoId;
    }

    public void setGoodsInfoId(Long goodsInfoId) {
        this.goodsInfoId = goodsInfoId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsInfoItemNo() {
        return goodsInfoItemNo;
    }

    public void setGoodsInfoItemNo(String goodsInfoItemNo) {
        this.goodsInfoItemNo = goodsInfoItemNo;
    }

    public String getGoodsInfoAdded() {
        return goodsInfoAdded;
    }

    public void setGoodsInfoAdded(String goodsInfoAdded) {
        this.goodsInfoAdded = goodsInfoAdded;
    }

    /**
     * 获取上架时间
     * */
    public Date getGoodsInfoAddedTime() {
        if (this.goodsInfoAddedTime != null) {
            return new Date(this.goodsInfoAddedTime.getTime());
        }
        return null;
    }

    /**
     * 设置上架时间
     * */
    public void setGoodsInfoAddedTime(Date goodsInfoAddedTime) {
        if (goodsInfoAddedTime != null) {
            Date tEmp = (Date) goodsInfoAddedTime.clone();
            if (tEmp != null) {
                this.goodsInfoAddedTime = tEmp;
            }
        }
    }

    public Long getGoodsInfoStock() {
        return goodsInfoStock;
    }

    public void setGoodsInfoStock(Long goodsInfoStock) {
        this.goodsInfoStock = goodsInfoStock;
    }

    public BigDecimal getGoodsInfoPreferPrice() {
        return goodsInfoPreferPrice;
    }

    public void setGoodsInfoPreferPrice(BigDecimal goodsInfoPreferPrice) {
        this.goodsInfoPreferPrice = goodsInfoPreferPrice;
    }

    public BigDecimal getGoodsInfoCostPrice() {
        return goodsInfoCostPrice;
    }

    public void setGoodsInfoCostPrice(BigDecimal goodsInfoCostPrice) {
        this.goodsInfoCostPrice = goodsInfoCostPrice;
    }


    public BigDecimal getGoodsInfoMarketPrice() {
        return goodsInfoMarketPrice;
    }

    public void setGoodsInfoMarketPrice(BigDecimal goodsInfoMarketPrice) {
        this.goodsInfoMarketPrice = goodsInfoMarketPrice;
    }

    public BigDecimal getGoodsInfoWeight() {
        return goodsInfoWeight;
    }

    public void setGoodsInfoWeight(BigDecimal goodsInfoWeight) {
        this.goodsInfoWeight = goodsInfoWeight;
    }

    public String getGoodsInfoImgId() {
        return goodsInfoImgId;
    }

    public void setGoodsInfoImgId(String goodsInfoImgId) {
        this.goodsInfoImgId = goodsInfoImgId;
    }

    public String getGoodsInfoCreateName() {
        return goodsInfoCreateName;
    }

    public void setGoodsInfoCreateName(String goodsInfoCreateName) {
        this.goodsInfoCreateName = goodsInfoCreateName;
    }

    /**
     * 获取创建时间
     * */
    public Date getGoodsInfoCreateTime() {
        if (this.goodsInfoCreateTime != null) {
            return new Date(this.goodsInfoCreateTime.getTime());
        }
        return null;
    }

    /**
     * 设置创建时间
     * */
    public void setGoodsInfoCreateTime(Date goodsInfoCreateTime) {
        if (goodsInfoCreateTime != null) {
            Date tEmp = (Date) goodsInfoCreateTime.clone();
            if (tEmp != null) {
                this.goodsInfoCreateTime = tEmp;
            }
        }
    }

    public String getGoodsInfoModifiedName() {
        return goodsInfoModifiedName;
    }

    public void setGoodsInfoModifiedName(String goodsInfoModifiedName) {
        this.goodsInfoModifiedName = goodsInfoModifiedName;
    }

    /**
     * 获取修改时间
     * */
    public Date getGoodsInfoModifiedTime() {
        if (this.goodsInfoModifiedTime != null) {
            return new Date(this.goodsInfoModifiedTime.getTime());
        }
        return null;
    }

    /**
     * 设置修改时间
     * */
    public void setGoodsInfoModifiedTime(Date goodsInfoModifiedTime) {
        if (goodsInfoModifiedTime != null) {
            Date tEmp = (Date) goodsInfoModifiedTime.clone();
            if (tEmp != null) {
                this.goodsInfoModifiedTime = tEmp;
            }
        }
    }

    public String getGoodsInfoDelName() {
        return goodsInfoDelName;
    }

    public void setGoodsInfoDelName(String goodsInfoDelName) {
        this.goodsInfoDelName = goodsInfoDelName;
    }

    /**
     * 获取删除时间
     * */
    public Date getGoodsInfoDelTime() {
        if (this.goodsInfoDelTime != null) {
            return new Date(this.goodsInfoDelTime.getTime());
        }
        return null;
    }

    /**
     * 设置删除时间
     * */
    public void setGoodsInfoDelTime(Date goodsInfoDelTime) {
        if (goodsInfoDelTime != null) {
            Date tEmp = (Date) goodsInfoDelTime.clone();
            if (tEmp != null) {
                this.goodsInfoDelTime = tEmp;
            }
        }
    }

    public String getGoodsInfoDelflag() {
        return goodsInfoDelflag;
    }

    public void setGoodsInfoDelflag(String goodsInfoDelflag) {
        this.goodsInfoDelflag = goodsInfoDelflag;
    }

    public String getGoodsInfoName() {
        return goodsInfoName;
    }

    public void setGoodsInfoName(String goodsInfoName) {
        this.goodsInfoName = goodsInfoName;
    }

    public String getGoodsInfoSubtitle() {
        return goodsInfoSubtitle;
    }

    public void setGoodsInfoSubtitle(String goodsInfoSubtitle) {
        this.goodsInfoSubtitle = goodsInfoSubtitle;
    }

    public Long getThirdId() {
        return thirdId;
    }

    public void setThirdId(Long thirdId) {
        this.thirdId = thirdId;
    }

    public String getThirdName() {
        return thirdName;
    }

    public void setThirdName(String thirdName) {
        this.thirdName = thirdName;
    }

    public String getIsThird() {
        return isThird;
    }

    public void setIsThird(String isThird) {
        this.isThird = isThird;
    }

    /**
     * 获取下架时间
     * */
    public Date getGoodsInfounAddedTime() {
        if (this.goodsInfounAddedTime != null) {
            return new Date(this.goodsInfounAddedTime.getTime());
        }
        return null;
    }

    /**
     * 设置下架时间
     * */
    public void setGoodsInfounAddedTime(Date goodsInfounAddedTime) {
        if (this.goodsInfounAddedTime != null) {
            Date tEmp = (Date) goodsInfounAddedTime.clone();
            if (tEmp != null) {
                this.goodsInfounAddedTime = tEmp;
            }
        }
    }

    public String getShowList() {
        return showList;
    }

    public void setShowList(String showList) {
        this.showList = showList;
    }

    public String getShowMobile() {
        return showMobile;
    }

    public void setShowMobile(String showMobile) {
        this.showMobile = showMobile;
    }

    public String getDisName() {
        return disName;
    }

    public void setDisName(String disName) {
        this.disName = disName;
    }

    public BigDecimal getOfollowPrice() {
        return ofollowPrice;
    }

    public void setOfollowPrice(BigDecimal ofollowPrice) {
        this.ofollowPrice = ofollowPrice;
    }

    public BigDecimal getNfollowPrice() {
        return nfollowPrice;
    }

    public void setNfollowPrice(BigDecimal nfollowPrice) {
        this.nfollowPrice = nfollowPrice;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getRefuseReason() {
        return refuseReason;
    }

    public void setRefuseReason(String refuseReason) {
        this.refuseReason = refuseReason;
    }
}
