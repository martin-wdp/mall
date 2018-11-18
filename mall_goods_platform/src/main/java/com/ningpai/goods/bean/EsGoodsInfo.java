package com.ningpai.goods.bean;

import com.ningpai.searchplatform.annotation.ESField;
import com.ningpai.searchplatform.bean.IESMappingType;
import com.ningpai.searchplatform.enumeration.ESAnalyzer;
import com.ningpai.searchplatform.enumeration.ESFieldType;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 货品信息
 */
public class EsGoodsInfo extends EsGoodsInfoKey implements IESMappingType {
    /**
     * 商品ID
     */
    @ESField(fieldType = ESFieldType.LONG, analyzerType = ESAnalyzer.not_analyzed, isStore = true)
    private Long goodsId;
    /**
     * 货品编号
     */
    @ESField()
    private String goodsInfoItemNo;
    /**
     * 货品价格
     */
    @ESField(analyzerType = ESAnalyzer.ik)
    private String goodsInfoName;
    /**
     * 副标�?
     */
    @ESField(analyzerType = ESAnalyzer.ik)
    private String goodsInfoSubtitle;
    /**
     * 是否上架
     */
    @ESField()
    private String goodsInfoAdded;
    /**
     * 上架 时间
     */
    @ESField(fieldType = ESFieldType.DATE)
    private Date goodsInfoAddedTime;
    /**
     * 下架时间
     */
    @ESField(fieldType = ESFieldType.DATE)
    private Date goodsInfoUnaddedTime;
    /**
     * 库存
     */
    @ESField(fieldType = ESFieldType.LONG)
    private Long goodsInfoStock;
    /**
     * �?售价
     */
    @ESField(fieldType = ESFieldType.DOUBLE)
    private BigDecimal goodsInfoPreferPrice;
    /**
     * �?会员售价
     * 2015.10.22 wuyanbo 添加
     */
    @ESField(fieldType = ESFieldType.DOUBLE)
    private BigDecimal goodsInfoVipPrice;
    /**
     * 优惠�?
     */
    @ESField(fieldType = ESFieldType.DOUBLE)
    private BigDecimal goodsInfoCostPrice;
    /**
     * 市场价格
     */
    @ESField(fieldType = ESFieldType.DOUBLE)
    private BigDecimal goodsInfoMarketPrice;
    /**
     * 重量
     */
    @ESField(fieldType = ESFieldType.DOUBLE)
    private BigDecimal goodsInfoWeight;
    /**
     * 创建�?
     */
    @ESField()
    private String goodsInfoCreateName;
    /**
     * 创建时间
     */
    @ESField(fieldType = ESFieldType.DATE)
    private Date goodsInfoCreateTime;
    /**
     * 修改�?
     */
    @ESField()
    private String goodsInfoModifiedName;
    /**
     * 修改时间
     */
    @ESField(fieldType = ESFieldType.DATE)
    private Date goodsInfoModifiedTime;
    /**
     * 删除�?
     */
    @ESField()
    private String goodsInfoDelName;
    /**
     * 删除时间
     */
    @ESField(fieldType = ESFieldType.DATE)
    private Date goodsInfoDelTime;
    /**
     * 删除标记
     */
    @ESField()
    private String goodsInfoDelflag;
    /**
     * 第三方ID
     */
    @ESField(fieldType = ESFieldType.LONG)
    private Long thirdId;
    /**
     * 第三商家名称
     */
    @ESField(analyzerType = ESAnalyzer.ik)
    private String thirdName;
    /**
     * 是否是第三方 0 平台 1商家
     */
    @ESField()
    private String isThird;
    /**
     * ISBN�?
     */
    @ESField()
    private String goodsInfoIsbn;
    /**
     * 是否显示列表
     */
    @ESField()
    private String showList;
    /**
     * 是否显示移动�?
     */
    @ESField()
    private String showMobile;
    /**
     * 是否参与会员折扣
     */
    @ESField()
    private String isCustomerDiscount;
    /**
     * 是否审核标志
     */
    @ESField()
    private String auditStatus;
    /**
     * 1卖家包邮 0,买家自费
     */
    @ESField()
    private String ismailbay;
    /**
     * 原因
     */
    @ESField(analyzerType = ESAnalyzer.ik)
    private String refuseReason;

    /**
     * 新增
     */
    /**
     * 分类ID
     */
    @ESField(fieldType = ESFieldType.LONG)
    private Long catId;

    /**
     * 分类ID
     */
    @ESField(fieldType = ESFieldType.LONG)
    private Long thirdCateId;

    /**
     * 类型ID
     */
    @ESField(fieldType = ESFieldType.LONG)
    private Long typeId;
    /**
     * 分类
     */
    @ESField(fieldType = ESFieldType.NESTED)
    private List<EsGoodsCategory> cateList;
    /**
     * 商家分类
     */
    @ESField(fieldType = ESFieldType.NESTED)
    private List<EsThirdCate> thirdCateList;

    /**
     * 图片
     */
    @ESField(fieldType = ESFieldType.NESTED)
    private List<EsGoodsImage> imgList;
    /**
     * 品牌
     */
    @ESField(fieldType = ESFieldType.LONG)
    private Long brandId;
    /**
     * 品牌信息
     */
    @ESField(fieldType = ESFieldType.NESTED)
    private EsGoodsBrand brand;
    /**
     * 属�?�参�?
     */
    @ESField(fieldType = ESFieldType.NESTED)
    private List<EsExpandparam> paramList;
    /**
     * 库存信息
     */
    @ESField(fieldType = ESFieldType.NESTED)
    private List<EsProductWare> wareList;
    /**
     * 销量
     */
    @ESField(fieldType = ESFieldType.INTEGER)
    private Integer saleCount;
    /**
     * 收藏数量（人气）
     */
    @ESField(fieldType = ESFieldType.INTEGER)
    private Integer collectionCount;

    /**
     * 高亮显示的商品名称
     */
    private String highLightName;

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
        if( null != goodsInfoOem && !"".equals(goodsInfoOem) ){
            return goodsInfoOem.replace(" ","");
        }else{
            return "";
        }
    }
    /**
     * OEM号                                          杨国栋 2015.10.31
     *
     * @param goodsInfoOem
     */
    public void setGoodsInfoOem(String goodsInfoOem) {
        if(!"".equals(goodsInfoOem) && null != goodsInfoOem){
            goodsInfoOem = goodsInfoOem.replace(" ","");
        }else{
            goodsInfoOem = "";
        }
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



    public Long getThirdCateId() {
        return thirdCateId;
    }

    public void setThirdCateId(Long thirdCateId) {
        this.thirdCateId = thirdCateId;
    }

    public List<EsThirdCate> getThirdCateList() {
        return thirdCateList;
    }

    public void setThirdCateList(List<EsThirdCate> thirdCateList) {
        this.thirdCateList = thirdCateList;
    }

    /**
     * 销售数量
     * 
     * @return Integer
     */
    public Integer getSaleCount() {
        if (saleCount == null) {
            saleCount = 0;
        }
        return saleCount;
    }

    /**
     * 销售数量
     * 
     * @param saleCount
     */
    public void setSaleCount(Integer saleCount) {
        this.saleCount = saleCount;
    }

    /**
     * 人气
     * 
     * @return Integer
     */
    public Integer getCollectionCount() {
        if (collectionCount == null) {
            collectionCount = 0;
        }
        return collectionCount;
    }

    /**
     * 人气
     * 
     * @param collectionCount
     */
    public void setCollectionCount(Integer collectionCount) {
        this.collectionCount = collectionCount;
    }

    /**
     * 仓库
     * 
     * @return List
     */
    public List<EsProductWare> getWareList() {
        return wareList;
    }

    /**
     * 仓库
     * 
     * @param wareList
     */
    public void setWareList(List<EsProductWare> wareList) {
        this.wareList = wareList;
    }

    /**
     * 属性参数
     * 
     * @return List
     */
    public List<EsExpandparam> getParamList() {
        return paramList;
    }

    /**
     * ParamList
     * 
     * @param paramList
     */
    public void setParamList(List<EsExpandparam> paramList) {
        this.paramList = paramList;
    }

    /**
     * 品牌
     * 
     * @return EsGoodsBrand
     */
    public EsGoodsBrand getBrand() {
        return brand;
    }

    /**
     * 品牌
     * 
     * @param brand
     */
    public void setBrand(EsGoodsBrand brand) {
        this.brand = brand;
    }

    /**
     * 品牌ID
     * 
     * @return Long
     */
    public Long getBrandId() {
        return brandId;
    }

    /**
     * 品牌ID
     * 
     * @param brandId
     */
    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    /**
     * 图片
     * 
     * @return List
     */
    public List<EsGoodsImage> getImgList() {
        return imgList;
    }

    /**
     * 图片
     * 
     * @param imgList
     */
    public void setImgList(List<EsGoodsImage> imgList) {
        this.imgList = imgList;
    }

    /**
     * 分类
     * 
     * @return List
     */
    public List<EsGoodsCategory> getCateList() {
        return cateList;
    }

    /**
     * 分类
     * 
     * @param cateList
     */
    public void setCateList(List<EsGoodsCategory> cateList) {
        this.cateList = cateList;
    }

    /**
     * 分类ID
     * 
     * @return Long
     */
    public Long getCatId() {
        return catId;
    }

    /**
     * 分类ID
     * 
     * @param catId
     */
    public void setCatId(Long catId) {
        this.catId = catId;
    }

    /**
     * 类型ID
     * 
     * @return Long
     */
    public Long getTypeId() {
        return typeId;
    }

    /**
     * 类型ID
     * 
     * @param typeId
     */
    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    /**
     * 商品ID
     * 
     * @return Long
     */
    public Long getGoodsId() {
        return goodsId;
    }

    /**
     * 商品ID
     * 
     * @param goodsId
     */
    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * 货品编号
     * 
     * @return String
     */
    public String getGoodsInfoItemNo() {
        return goodsInfoItemNo;
    }

    /**
     * 货品编号
     * 
     * @param goodsInfoItemNo
     */
    public void setGoodsInfoItemNo(String goodsInfoItemNo) {
        this.goodsInfoItemNo = goodsInfoItemNo;
    }

    /**
     * 货品名称
     * 
     * @return String
     */
    public String getGoodsInfoName() {
        return goodsInfoName;
    }

    /**
     * 货品名称
     * 
     * @param goodsInfoName
     */
    public void setGoodsInfoName(String goodsInfoName) {
        this.goodsInfoName = goodsInfoName;
    }

    /**
     * 副标题
     * 
     * @return String
     */
    public String getGoodsInfoSubtitle() {
        return goodsInfoSubtitle;
    }

    /**
     * 副标题
     * 
     * @param goodsInfoSubtitle
     */
    public void setGoodsInfoSubtitle(String goodsInfoSubtitle) {
        this.goodsInfoSubtitle = goodsInfoSubtitle;
    }

    /**
     * 是否上架
     * 
     * @return String
     */
    public String getGoodsInfoAdded() {
        return goodsInfoAdded;
    }

    /**
     * 是否上架
     * 
     * @param goodsInfoAdded
     */
    public void setGoodsInfoAdded(String goodsInfoAdded) {
        this.goodsInfoAdded = goodsInfoAdded;
    }

    /**
     * 上架时间
     * 
     * @return Date
     */
    public Date getGoodsInfoAddedTime() {
        return goodsInfoDelTime==null?null:(Date) goodsInfoAddedTime.clone();
    }

    /**
     * 上架时间
     * 
     * @param goodsInfoAddedTime
     */
    public void setGoodsInfoAddedTime(Date goodsInfoAddedTime) {
        this.goodsInfoAddedTime = goodsInfoAddedTime == null ? null : (Date) goodsInfoAddedTime.clone();
    }

    /**
     * 时间
     * 
     * @return
     */
    public Date getGoodsInfoUnaddedTime() {
        return goodsInfoUnaddedTime==null?null:(Date) goodsInfoUnaddedTime.clone();
    }

    /**
     * 时间
     * 
     * @param goodsInfoUnaddedTime
     */
    public void setGoodsInfoUnaddedTime(Date goodsInfoUnaddedTime) {
        this.goodsInfoUnaddedTime = goodsInfoUnaddedTime == null ? null : (Date) goodsInfoUnaddedTime.clone();
    }

    /**
     * 库存
     * 
     * @return Long
     */
    public Long getGoodsInfoStock() {
        return goodsInfoStock;
    }

    /**
     * 库存
     * 
     * @param goodsInfoStock
     */
    public void setGoodsInfoStock(Long goodsInfoStock) {
        this.goodsInfoStock = goodsInfoStock;
    }

    /**
     * 价格
     * 
     * @return BigDecimal
     */
    public BigDecimal getGoodsInfoPreferPrice() {
        return goodsInfoPreferPrice;
    }

    /**
     * 价格
     * 
     * @param goodsInfoPreferPrice
     */
    public void setGoodsInfoPreferPrice(BigDecimal goodsInfoPreferPrice) {
        this.goodsInfoPreferPrice = goodsInfoPreferPrice;
    }

    /**
     * 会员价格
     *
     * @return BigDecimal
     */
    public BigDecimal getGoodsInfoVipPrice() {
        return goodsInfoVipPrice;
    }

    /**
     * 会员价格
     *
     * @param goodsInfoVipPrice
     */
    public void setGoodsInfoVipPrice(BigDecimal goodsInfoVipPrice) {
        this.goodsInfoVipPrice = goodsInfoVipPrice;
    }

    /**
     * 批发价
     * 
     * @return BigDecimal
     */
    public BigDecimal getGoodsInfoCostPrice() {
        return goodsInfoCostPrice;
    }

    /**
     * 批发价
     * 
     * @param goodsInfoCostPrice
     */
    public void setGoodsInfoCostPrice(BigDecimal goodsInfoCostPrice) {
        this.goodsInfoCostPrice = goodsInfoCostPrice;
    }

    /**
     * 市场价
     * 
     * @return BigDecimal
     */
    public BigDecimal getGoodsInfoMarketPrice() {
        return goodsInfoMarketPrice;
    }

    /**
     * 市场价
     * 
     * @param goodsInfoMarketPrice
     */
    public void setGoodsInfoMarketPrice(BigDecimal goodsInfoMarketPrice) {
        this.goodsInfoMarketPrice = goodsInfoMarketPrice;
    }

    /**
     * 重量
     * 
     * @return BigDecimal
     */
    public BigDecimal getGoodsInfoWeight() {
        return goodsInfoWeight;
    }

    /**
     * 重量
     * 
     * @param goodsInfoWeight
     */
    public void setGoodsInfoWeight(BigDecimal goodsInfoWeight) {
        this.goodsInfoWeight = goodsInfoWeight;
    }

    /**
     * 创建人
     * 
     * @return String
     */
    public String getGoodsInfoCreateName() {
        return goodsInfoCreateName;
    }

    /**
     * 创建人
     * 
     * @param goodsInfoCreateName
     */
    public void setGoodsInfoCreateName(String goodsInfoCreateName) {
        this.goodsInfoCreateName = goodsInfoCreateName;
    }

    /**
     * 创建时间
     * 
     * @return Date
     */
    public Date getGoodsInfoCreateTime() {
        return goodsInfoCreateTime==null?null:(Date) goodsInfoCreateTime.clone();
    }

    /**
     * 创建时间
     * 
     * @param goodsInfoCreateTime
     */
    public void setGoodsInfoCreateTime(Date goodsInfoCreateTime) {
        this.goodsInfoCreateTime = goodsInfoCreateTime == null ? null : (Date) goodsInfoCreateTime.clone();
    }

    /**
     * 修改时间
     * 
     * @return String
     */
    public String getGoodsInfoModifiedName() {
        return goodsInfoModifiedName;
    }

    /**
     * 修改时间
     * 
     * @param goodsInfoModifiedName
     */
    public void setGoodsInfoModifiedName(String goodsInfoModifiedName) {
        this.goodsInfoModifiedName = goodsInfoModifiedName;
    }

    /**
     * 修改时间
     * 
     * @return Date
     */
    public Date getGoodsInfoModifiedTime() {
        return goodsInfoModifiedTime==null?null:(Date) goodsInfoModifiedTime.clone();
    }

    /**
     * 修改时间
     * 
     * @param goodsInfoModifiedTime
     */
    public void setGoodsInfoModifiedTime(Date goodsInfoModifiedTime) {
        this.goodsInfoModifiedTime = goodsInfoModifiedTime == null ? null : (Date) goodsInfoModifiedTime.clone();
    }

    /**
     * 删除人
     * 
     * @return
     */
    public String getGoodsInfoDelName() {
        return goodsInfoDelName;
    }

    /**
     * 删除人
     * 
     * @param goodsInfoDelName
     */
    public void setGoodsInfoDelName(String goodsInfoDelName) {
        this.goodsInfoDelName = goodsInfoDelName;
    }

    /**
     * 删除时间
     * 
     * @return
     */
    public Date getGoodsInfoDelTime() {
        return goodsInfoDelTime==null?null:(Date) goodsInfoDelTime.clone();
    }

    /**
     * 删除时间
     * 
     * @param goodsInfoDelTime
     */
    public void setGoodsInfoDelTime(Date goodsInfoDelTime) {
        this.goodsInfoDelTime = goodsInfoDelTime == null ? null : (Date) goodsInfoDelTime.clone();
    }

    /**
     * 删除标记
     * 
     * @return
     */
    public String getGoodsInfoDelflag() {
        return goodsInfoDelflag;
    }

    /**
     * 删除标记
     * 
     * @param goodsInfoDelflag
     */
    public void setGoodsInfoDelflag(String goodsInfoDelflag) {
        this.goodsInfoDelflag = goodsInfoDelflag;
    }

    /**
     * 第三方ＩＤ
     * 
     * @return
     */
    public Long getThirdId() {
        return thirdId;
    }

    /**
     * 第三方ID
     * 
     * @param thirdId
     */
    public void setThirdId(Long thirdId) {
        this.thirdId = thirdId;
    }

    /**
     * 第三方名称
     * 
     * @return
     */
    public String getThirdName() {
        return thirdName;
    }

    /**
     * 第三方名称
     * 
     * @param thirdName
     */
    public void setThirdName(String thirdName) {
        this.thirdName = thirdName;
    }

    /**
     * 是否第三方
     * 
     * @return
     */
    public String getIsThird() {
        return isThird;
    }

    /**
     * 是否第三方
     * 
     * @param isThird
     */
    public void setIsThird(String isThird) {
        this.isThird = isThird;
    }

    /**
     * ISBN
     * 
     * @return
     */
    public String getGoodsInfoIsbn() {
        return goodsInfoIsbn;
    }

    /**
     * ISBN
     * 
     * @param goodsInfoIsbn
     */
    public void setGoodsInfoIsbn(String goodsInfoIsbn) {
        this.goodsInfoIsbn = goodsInfoIsbn;
    }

    /**
     * 列表是否显示
     * 
     * @return
     */
    public String getShowList() {
        return showList;
    }

    /**
     * 列表是否显示
     * 
     * @param showList
     */
    public void setShowList(String showList) {
        this.showList = showList;
    }

    /**
     * 手机是否显示
     * 
     * @return
     */
    public String getShowMobile() {
        return showMobile;
    }

    /**
     * 手机是否显示
     * 
     * @param showMobile
     */
    public void setShowMobile(String showMobile) {
        this.showMobile = showMobile;
    }

    /**
     * 会员折扣
     * 
     * @return
     */
    public String getIsCustomerDiscount() {
        return isCustomerDiscount;
    }

    /**
     * 会员折扣
     * 
     * @param isCustomerDiscount
     */
    public void setIsCustomerDiscount(String isCustomerDiscount) {
        this.isCustomerDiscount = isCustomerDiscount;
    }

    /**
     * 审核
     * 
     * @return
     */
    public String getAuditStatus() {
        return auditStatus;
    }

    /**
     * 审核
     * 
     * @param auditStatus
     */
    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    /**
     * @return
     */
    public String getIsmailbay() {
        return ismailbay;
    }

    /**
     * @param ismailbay
     */
    public void setIsmailbay(String ismailbay) {
        this.ismailbay = ismailbay;
    }

    /**
     * @return
     */
    public String getRefuseReason() {
        return refuseReason;
    }

    /**
     * @param refuseReason
     */
    public void setRefuseReason(String refuseReason) {
        this.refuseReason = refuseReason;
    }

    /**
     * typeField
     */
    @Override
    public Field[] foundTypeField() {
        return this.getClass().getDeclaredFields();
    }

    /**
     * 高亮显示的商品名称,用于前台展示
     * 
     * @return
     */
    public String getHighLightName() {
        return highLightName;
    }

    /**
     * 高亮显示的商品名称,用于前台展示
     * 
     * @param highLightName
     */
    public void setHighLightName(String highLightName) {
        this.highLightName = highLightName;
    }

    /**
     * 文档ID
     * 
     * @see com.ningpai.searchplatform.bean.IESMappingType#generateDocId()
     */
    @Override
    public String generateDocId() {
        return this.getGoodsInfoId() == null ? "" : this.getGoodsInfoId().toString();
    }

}