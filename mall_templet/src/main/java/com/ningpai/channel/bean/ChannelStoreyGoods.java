package com.ningpai.channel.bean;

import com.ningpai.searchplatform.annotation.ESField;
import com.ningpai.searchplatform.enumeration.ESAnalyzer;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 实体类-楼层商品
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年4月12日下午3:04:02
 */
public class ChannelStoreyGoods implements Serializable{
    private static final long serialVersionUID = -2556222765428208964L;
    /** 编号 */
    private Long channelStoreyGoodsproductId;
    /** 楼层ID */
    private Long storeyId;
    /** 楼层标签ID */
    private Long storeyTagId;
    /** 货品名称 */
    @NotNull
    //@Pattern(regexp = "[^\\[\\]\\<\\>?!]+")
    private String goodsproductName;
    /** 货品图片路径 */
    @NotNull
    @Pattern(regexp = "[^\\<\\>]+")
    private String goodsproductImgsrc;
    /** 货品编号 */
    @NotNull
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+")
    private String goodsproductNo;
    /** 货品价格 */
    @NotNull
    @Digits(integer = 20, fraction = 2)
    private BigDecimal goodsproductPrice;
    /** 货品ID */
    private Long goodsproductId;
    /** 是否是热销商品 */
    private String isHot;
    /** 排序 */
    private Long sort;
    /** 是否启用 */
    private String userStatus;
    /** 是否删除 */
    private String delflag;
    /** 创建人ID */
    private Long createUserId;
    /** 创建时间 */
    private Date createDate;
    /** 修改人ID */
    private Long updateUserId;
    /** 修改时间 */
    private Date updateDate;
    /** 备用字段1 (模板ID) */
    private String temp1;
    /** 备用字段2 (频道ID) */
    private String temp2;
    /** 备用字段3 */
    private String temp3;
    /** 备用字段4 */
    private String temp4;
    /** 备用字段5 */
    private String temp5;
    // BOSS货品库存
    private Long goodsInfoStock = 0L;
    // 销售价
    private BigDecimal goodsInfoPreferPrice;
    /**
     * 货品会员价格
     * 2015.10.22 wuyanbo 添加会员价格
     */
    private BigDecimal goodsInfoVipPrice;
    // 货品的分仓价格
    private BigDecimal warePrice;
    /**
     * 货品的分仓价格
     * 2015.10.22 wuyanbo 添加分仓会员价格
     */
    private BigDecimal wareVipPrice;
    // 市场价
    private BigDecimal goodsInfoMarketPrice;
    // 第三方库存
    private Long goodsInfoStockThird;
    // 0:不是第三方商品 1:第三方商品 2:B2B商品
    private Long isThird;




    /**
     * 汽车配件类型：“1”表示OEM件，“2”表示常用件          杨国栋 2015.10.31
     */
    @ESField()
    private String goodsInfoAutoPartsType;
    /**
     * OEM号     goodsInfoOem                                     杨国栋 2015.10.31
     */
    @ESField()
    private String goodsInfoOem;
    /**
     * OEM件的适配车型：用空格隔开      goodsInfoAutoStyle                   杨国栋 2015.10.31
     */
    @ESField(analyzerType = ESAnalyzer.ik)
    private String goodsInfoAutoStyle;
    /**
     * 汽车配件类型：“1”表示OEM件，“2”表示常用件          杨国栋 2015.10.31
     *
     * @return String
     */
    public String getGoodsInfoAutoPartsType() {
        return goodsInfoAutoPartsType;
    }
    /**
     * 汽车配件类型：“1”表示OEM件，“2”表示常用件          杨国栋 2015.10.31
     *
     * @param goodsInfoAutoPartsType
     */
    public void setGoodsInfoAutoPartsType(String goodsInfoAutoPartsType) {
        this.goodsInfoAutoPartsType = goodsInfoAutoPartsType;
    }
    /**
     * OEM号                                          杨国栋 2015.10.31
     *
     * @return String
     */
    public String getGoodsInfoOem() {
        return goodsInfoOem;
    }
    /**
     * OEM号                                          杨国栋 2015.10.31
     *
     * @param goodsInfoOem
     */
    public void setGoodsInfoOem(String goodsInfoOem) {
        this.goodsInfoOem = goodsInfoOem;
    }
    /**
     * OEM件的适配车型：用空格隔开                         杨国栋 2015.10.31
     *
     * @return String
     */
    public String getGoodsInfoAutoStyle() {
        return goodsInfoAutoStyle;
    }
    /**
     * OEM件的适配车型：用空格隔开                         杨国栋 2015.10.31
     *
     * @param goodsInfoAutoStyle
     */
    public void setGoodsInfoAutoStyle(String goodsInfoAutoStyle) {
        this.goodsInfoAutoStyle = goodsInfoAutoStyle;
    }









    /**
     * 商品IＤ
     * 
     * @return Long
     */
    public Long getChannelStoreyGoodsproductId() {
        return channelStoreyGoodsproductId;
    }

    /**
     * 商品ID
     * 
     * @param channelStoreyGoodsproductId
     */
    public void setChannelStoreyGoodsproductId(Long channelStoreyGoodsproductId) {
        this.channelStoreyGoodsproductId = channelStoreyGoodsproductId;
    }

    /**
     * 楼层ID
     * 
     * @return Long
     */
    public Long getStoreyId() {
        return storeyId;
    }

    /**
     * 楼层ID
     * 
     * @param storeyId
     */
    public void setStoreyId(Long storeyId) {
        this.storeyId = storeyId;
    }

    /**
     * 标签ID
     * 
     * @return Long
     */
    public Long getStoreyTagId() {
        return storeyTagId;
    }

    /**
     * 标签ID
     * 
     * @param storeyTagId
     */
    public void setStoreyTagId(Long storeyTagId) {
        this.storeyTagId = storeyTagId;
    }

    /**
     * 商品名称
     * 
     * @return String
     */
    public String getGoodsproductName() {
        return goodsproductName;
    }

    /**
     * 商品名称
     * 
     * @param goodsproductName
     */
    public void setGoodsproductName(String goodsproductName) {
        this.goodsproductName = goodsproductName;
    }

    /**
     * 商品图片
     * 
     * @return String
     */
    public String getGoodsproductImgsrc() {
        return goodsproductImgsrc;
    }

    /**
     * 商品图片
     * 
     * @param goodsproductImgsrc
     */
    public void setGoodsproductImgsrc(String goodsproductImgsrc) {
        this.goodsproductImgsrc = goodsproductImgsrc;
    }

    /**
     * 商品编号
     * 
     * @return String
     */
    public String getGoodsproductNo() {
        return goodsproductNo;
    }

    /**
     * 商品编号
     * 
     * @param goodsproductNo
     */
    public void setGoodsproductNo(String goodsproductNo) {
        this.goodsproductNo = goodsproductNo;
    }

    /**
     * 商品价格
     * 
     * @return BigDecimal
     */
    public BigDecimal getGoodsproductPrice() {
        return goodsproductPrice;
    }

    /**
     * 商品价格
     * 
     * @param goodsproductPrice
     */
    public void setGoodsproductPrice(BigDecimal goodsproductPrice) {
        this.goodsproductPrice = goodsproductPrice;
    }

    /**
     * 商品ID
     * 
     * @return Long
     */
    public Long getGoodsproductId() {
        return goodsproductId;
    }

    /**
     * 商品ID
     * 
     * @param goodsproductId
     */
    public void setGoodsproductId(Long goodsproductId) {
        this.goodsproductId = goodsproductId;
    }

    /**
     * 是否热销
     * 
     * @return String
     */
    public String getIsHot() {
        return isHot;
    }

    /**
     * 是否热销
     * 
     * @param isHot
     */
    public void setIsHot(String isHot) {
        this.isHot = isHot;
    }

    /**
     * 排序
     * 
     * @return Long
     */
    public Long getSort() {
        return sort;
    }

    /**
     * 排序
     * 
     * @param sort
     */
    public void setSort(Long sort) {
        this.sort = sort;
    }

    /**
     * 是否删除
     * 
     * @return String
     */
    public String getDelflag() {
        return delflag;
    }

    /**
     * 是否删除
     * 
     * @param delflag
     */
    public void setDelflag(String delflag) {
        this.delflag = delflag;
    }

    /**
     * 创建人
     * 
     * @return Long
     */
    public Long getCreateUserId() {
        return createUserId;
    }

    /**
     * 创建人
     * 
     * @param createUserId
     */
    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * 时间
     * 
     * @return Date
     */
    public Date getCreateDate() {
        if (this.createDate != null) {
            return new Date(this.createDate.getTime());
        } else {
            return null;
        }
    }

    /**
     * 时间
     */
    public void setCreateDate(Date createDate) {
        if (createDate != null) {
            Date tEmp = (Date) createDate.clone();
            if (tEmp != null) {
                this.createDate = tEmp;
            }
        }
    }

    /**
     * 修改人
     * 
     * @return
     */
    public Long getUpdateUserId() {
        return updateUserId;
    }

    /**
     * 修改人
     * 
     * @param updateUserId
     */
    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    /**
     * 时间
     * 
     * @return Date
     */
    public Date getUpdateDate() {
        if (this.updateDate != null) {
            return new Date(this.updateDate.getTime());
        } else {
            return null;
        }
    }

    /**
     * 时间
     */
    public void setUpdateDate(Date updateDate) {
        if (updateDate != null) {
            Date tEmp = (Date) updateDate.clone();
            if (tEmp != null) {
                this.updateDate = tEmp;
            }
        }
    }

    /**
     * 备用
     * 
     * @return String
     */
    public String getTemp1() {
        return temp1;
    }

    /**
     * 备用
     * 
     * @param temp1
     */
    public void setTemp1(String temp1) {
        this.temp1 = temp1;
    }

    /**
     * 备用
     * 
     * @return String
     */
    public String getTemp2() {
        return temp2;
    }

    /**
     * 备用
     * 
     * @param temp2
     */
    public void setTemp2(String temp2) {
        this.temp2 = temp2;
    }

    /**
     * 备用
     * 
     * @return String
     */
    public String getTemp3() {
        return temp3;
    }

    /**
     * 备用
     * 
     * @param temp3
     */
    public void setTemp3(String temp3) {
        this.temp3 = temp3;
    }

    /**
     * 备用
     * 
     * @return String
     */
    public String getTemp4() {
        return temp4;
    }

    /**
     * 备用
     * 
     * @param temp4
     */
    public void setTemp4(String temp4) {
        this.temp4 = temp4;
    }

    /**
     * 备用
     * 
     * @return String
     */
    public String getTemp5() {
        return temp5;
    }

    /**
     * 备用
     * 
     * @param temp5
     */
    public void setTemp5(String temp5) {
        this.temp5 = temp5;
    }

    /**
     * 是否使用
     * 
     * @return String
     */
    public String getUserStatus() {
        return userStatus;
    }

    /**
     * 是否使用
     * 
     * @param userStatus
     */
    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
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

    public BigDecimal getGoodsInfoVipPrice() {
        return goodsInfoVipPrice;
    }

    public void setGoodsInfoVipPrice(BigDecimal goodsInfoVipPrice) {
        this.goodsInfoVipPrice = goodsInfoVipPrice;
    }

    public BigDecimal getWarePrice() {
        return warePrice;
    }

    public void setWarePrice(BigDecimal warePrice) {
        this.warePrice = warePrice;
    }

    public BigDecimal getWareVipPrice() {
        return wareVipPrice;
    }

    public void setWareVipPrice(BigDecimal wareVipPrice) {
        this.wareVipPrice = wareVipPrice;
    }

    public BigDecimal getGoodsInfoMarketPrice() {
        return goodsInfoMarketPrice;
    }

    public void setGoodsInfoMarketPrice(BigDecimal goodsInfoMarketPrice) {
        this.goodsInfoMarketPrice = goodsInfoMarketPrice;
    }

    public Long getGoodsInfoStockThird() {
        return goodsInfoStockThird;
    }

    public void setGoodsInfoStockThird(Long goodsInfoStockThird) {
        this.goodsInfoStockThird = goodsInfoStockThird;
    }

    public Long getIsThird() {
        return isThird;
    }

    public void setIsThird(Long isThird) {
        this.isThird = isThird;
    }
}
