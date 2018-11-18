package com.ningpai.goods.bean;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品信息表
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月24日 下午2:34:58
 * @version 1.0
 */

public class Goods {
    /*
     * 商品ID
     */
    private Long goodsId;
    /*
     * 分类ID
     */
    private Long catId;
    /*
     * 类型ID
     */
    private Long typeId;
    /*
     * 商品名称
     */
    @Length(min = 3, max = 120, message = "商品名称 长度必须在 3字符 ~ 50字符之间")
    @Pattern(regexp = "[^''\\[\\]\\<\\>?!]+")
    private String goodsName;
    /*
     * 商品副标题
     */
    private String goodsSubtitle;
    /*
     * 商品编号
     */
    @Length(min = 10, max = 32, message = "商品编号 长度必须在10字符 ~ 32字符之间.")
    private String goodsNo;
    /*
     * 商品关键字
     */
    private String goodsKeywords;
    /*
     * 品牌ID
     */
    private Long brandId;
    /*
     * 商品简单介绍
     */
    private String goodsBrief;
    /*
     * 是否立即上架0不上架 1上架
     */
    private String goodsAdded;
    /*
     * 上架时间
     */
    private Date goodsUptime;
    /*
     * 销售价格
     */
    private BigDecimal goodsPrice;
    /*
     * 会员价格
     * 2015.10.22 wuyanbo 添加
     */
    private BigDecimal goodsVipPrice;
    /*
     * 商品推荐0不推荐 1推荐
     */
    private String goodsRecom;
    /*
     * 默认的商品图片
     */
    private String goodsImg;
    /*
     * 商品评分
     */
    private BigDecimal goodsScore;
    /*
     * 商品计价单位
     */
    @Length(min = 1, max = 5, message = "计价单位 长度必须在1字符 ~ 5字符之间.")
    @Pattern(regexp = "[^''\\[\\]\\<\\>?!]+")
    private String goodsDeno;
    /*
     * SEOtitle
     */
    private String goodsSeoTitle;
    /*
     * SEO keyword
     */
    private String goodsSeoKeyword;
    /*
     * SEO desc
     */
    private String goodsSeoDesc;
    /*
     * 是否进行促销 0：不促销 1促销
     */
    private String goodsProm;
    /*
     * 无库存是否也可销售
     */
    private String goodsInfoInstocksell;
    /*
     * 是否可以使用优惠劵
     */
    private String goodsUsecoupon;
    /*
     * 创建商品人的名称
     */
    private String goodsCreateName;
    /*
     * 商品的创建时间
     */
    private Date goodsCreateTime;
    /*
     * 修改商品信息的名称
     */
    private String goodsModifiedName;
    /*
     * 修改商品信息的时间
     */
    private Date goodsModifiedTime;
    /*
     * 删除商品信息人的名称
     */
    private String goodsDelName;
    /*
     * 删除商品信息的时间
     */
    private Date goodsDelTime;
    /*
     * 删除标记
     */
    private String goodsDelflag;
    /*
     * 商品详细介绍
     */
    private String goodsDetailDesc;
    /*
     * 货品编号
     */
    private String goodsInfoItemNo;
    /*
     * 商品所属的商家Id
     */
    private Long goodsBelo;
    /*
     * 商品所属的商家名称
     */
    private String goodsBeloName;
    /*
     * 是否是第三方商品
     */
    private String isThird;
    /*
     * 商家分类ID
     */
    private Long thirdCateId;
    /*
     * 手机端详细介绍
     */
    private String mobileDesc;
    /*
     * 第三方审核是否开启 0 不开启 1 开启
     */
    private String isThirdAuditUsed;
    /*
     * 审核状态 0'未审核、审核通过' 1'审核中' 2'审核不通过' 3'第一次上传商品但未审核'
     */
    private String auditStatus;
    /*
     * 分类名称
     */
    private String catName;

    private String brandName;
    /*
     * 店铺有效期
     */
    private String storeValidTime;
    /*
     * 商品审核拒绝原因
     */
    private String refuseReason;

    private BigDecimal discountInfo;

    private BigDecimal discountPrice;

    private String haveStock;

    public BigDecimal getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(BigDecimal discountPrice) {
        this.discountPrice = discountPrice;
    }

    public String getHaveStock() {
        return haveStock;
    }

    public void setHaveStock(String haveStock) {
        this.haveStock = haveStock;
    }

    public BigDecimal getDiscountInfo() {
        return discountInfo;
    }

    public void setDiscountInfo(BigDecimal discountInfo) {
        this.discountInfo = discountInfo;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Long getCatId() {
        return catId;
    }

    public void setCatId(Long catId) {
        this.catId = catId;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName.trim();
    }

    public String getGoodsSubtitle() {
        return goodsSubtitle;
    }

    public void setGoodsSubtitle(String goodsSubtitle) {
        this.goodsSubtitle = goodsSubtitle.trim();
    }

    public String getGoodsNo() {
        return goodsNo;
    }

    public void setGoodsNo(String goodsNo) {
        this.goodsNo = goodsNo.trim();
    }

    public String getGoodsKeywords() {
        return goodsKeywords;
    }

    public void setGoodsKeywords(String goodsKeywords) {
        this.goodsKeywords = goodsKeywords.trim();
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public String getGoodsBrief() {
        return goodsBrief;
    }

    public void setGoodsBrief(String goodsBrief) {
        this.goodsBrief = goodsBrief.trim();
    }

    public String getGoodsAdded() {
        return goodsAdded;
    }

    public void setGoodsAdded(String goodsAdded) {
        this.goodsAdded = goodsAdded;
    }
    /**
     * 获取修改时间
     * */
    public Date getGoodsUptime() {
        if (this.goodsUptime != null) {
            return new Date(this.goodsUptime.getTime());
        }
        return null;
    }
    /**
     * 设置修改时间
     * */
    public void setGoodsUptime(Date goodsUptime) {
        if (goodsUptime != null) {
            Date tEmp = (Date) goodsUptime.clone();
            if (tEmp != null) {
                this.goodsUptime = tEmp;
            }
        }
    }

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public BigDecimal getGoodsVipPrice() {
        return goodsVipPrice;
    }

    public void setGoodsVipPrice(BigDecimal goodsVipPrice) {
        this.goodsVipPrice = goodsVipPrice;
    }

    public String getGoodsRecom() {
        return goodsRecom;
    }

    public void setGoodsRecom(String goodsRecom) {
        this.goodsRecom = goodsRecom;
    }

    public String getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
    }

    public BigDecimal getGoodsScore() {
        return goodsScore;
    }

    public void setGoodsScore(BigDecimal goodsScore) {
        this.goodsScore = goodsScore;
    }

    public String getGoodsDeno() {
        return goodsDeno;
    }

    public void setGoodsDeno(String goodsDeno) {
        this.goodsDeno = goodsDeno;
    }

    public String getGoodsSeoTitle() {
        return goodsSeoTitle;
    }

    public void setGoodsSeoTitle(String goodsSeoTitle) {
        this.goodsSeoTitle = goodsSeoTitle.trim();
    }

    public String getGoodsSeoKeyword() {
        return goodsSeoKeyword;
    }

    public void setGoodsSeoKeyword(String goodsSeoKeyword) {
        this.goodsSeoKeyword = goodsSeoKeyword.trim();
    }

    public String getGoodsSeoDesc() {
        return goodsSeoDesc;
    }

    public void setGoodsSeoDesc(String goodsSeoDesc) {
        this.goodsSeoDesc = goodsSeoDesc.trim();
    }

    public String getGoodsProm() {
        return goodsProm;
    }

    public void setGoodsProm(String goodsProm) {
        this.goodsProm = goodsProm;
    }

    public String getGoodsInfoInstocksell() {
        return goodsInfoInstocksell;
    }

    public void setGoodsInfoInstocksell(String goodsInfoInstocksell) {
        this.goodsInfoInstocksell = goodsInfoInstocksell;
    }

    public String getGoodsUsecoupon() {
        return goodsUsecoupon;
    }

    public void setGoodsUsecoupon(String goodsUsecoupon) {
        this.goodsUsecoupon = goodsUsecoupon;
    }

    public String getGoodsCreateName() {
        return goodsCreateName;
    }

    public void setGoodsCreateName(String goodsCreateName) {
        this.goodsCreateName = goodsCreateName;
    }
    /**
     * 获取商品创建时间
     * */
    public Date getGoodsCreateTime() {
        if (this.goodsCreateTime != null) {
            return new Date(this.goodsCreateTime.getTime());
        }
        return null;
    }
    /**
     * 设置商品创建时间
     * */
    public void setGoodsCreateTime(Date goodsCreateTime) {
        if (goodsCreateTime != null) {
            Date tEmp = (Date) goodsCreateTime.clone();
            if (tEmp != null) {
                this.goodsCreateTime = tEmp;
            }
        }
    }

    public String getGoodsModifiedName() {
        return goodsModifiedName;
    }

    public void setGoodsModifiedName(String goodsModifiedName) {
        this.goodsModifiedName = goodsModifiedName;
    }

    /**
     *获取商品修改时间
     * */
    public Date getGoodsModifiedTime() {
        if (goodsModifiedTime != null) {
            return new Date(this.goodsModifiedTime.getTime());
        }
        return null;
    }

    /**
     * 设置商品修改时间
     * */
    public void setGoodsModifiedTime(Date goodsModifiedTime) {
        if (goodsModifiedTime != null) {
            Date tEmp = (Date) goodsModifiedTime.clone();
            if (tEmp != null) {
                this.goodsModifiedTime = tEmp;
            }
        }
    }

    public String getGoodsDelName() {
        return goodsDelName;
    }

    public void setGoodsDelName(String goodsDelName) {
        this.goodsDelName = goodsDelName;
    }
    /**
     * 获取商品删除时间
     * */
    public Date getGoodsDelTime() {
        if (this.goodsDelTime != null) {
            return new Date(this.goodsDelTime.getTime());
        }
        return null;
    }
    /**
     * 设置商品删除时间
     * */
    public void setGoodsDelTime(Date goodsDelTime) {
        if (goodsDelTime != null) {
            Date tEmp = (Date) goodsDelTime.clone();
            if (tEmp != null) {
                this.goodsDelTime = tEmp;
            }
        }
    }

    public String getGoodsInfoItemNo() {
        return goodsInfoItemNo;
    }

    public void setGoodsInfoItemNo(String goodsInfoItemNo) {
        this.goodsInfoItemNo = goodsInfoItemNo.trim();
    }

    public String getGoodsDelflag() {
        return goodsDelflag;
    }

    public void setGoodsDelflag(String goodsDelflag) {
        this.goodsDelflag = goodsDelflag;
    }

    public String getGoodsDetailDesc() {
        return goodsDetailDesc;
    }

    public void setGoodsDetailDesc(String goodsDetailDesc) {
        this.goodsDetailDesc = goodsDetailDesc;
    }

    public Long getGoodsBelo() {
        return goodsBelo;
    }

    public void setGoodsBelo(Long goodsBelo) {
        this.goodsBelo = goodsBelo;
    }

    public String getGoodsBeloName() {
        return goodsBeloName;
    }

    public void setGoodsBeloName(String goodsBeloName) {
        this.goodsBeloName = goodsBeloName;
    }

    public String getIsThird() {
        return isThird;
    }

    public void setIsThird(String isThird) {
        this.isThird = isThird;
    }

    public Long getThirdCateId() {
        return thirdCateId;
    }

    public void setThirdCateId(Long thirdCateId) {
        this.thirdCateId = thirdCateId;
    }

    public String getMobileDesc() {
        return mobileDesc;
    }

    public void setMobileDesc(String mobileDesc) {
        this.mobileDesc = mobileDesc;
    }

    public String getIsThirdAuditUsed() {
        return isThirdAuditUsed;
    }

    public void setIsThirdAuditUsed(String isThirdAuditUsed) {
        this.isThirdAuditUsed = isThirdAuditUsed;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getStoreValidTime() {
        return storeValidTime;
    }

    public void setStoreValidTime(String storeValidTime) {
        this.storeValidTime = storeValidTime;
    }

    public String getRefuseReason() {
        return refuseReason;
    }

    public void setRefuseReason(String refuseReason) {
        this.refuseReason = refuseReason;
    }

}
