package com.ningpai.goodsimport.bean;

import java.math.BigDecimal;

/**
 * 商品导入实体
 * 
 * @author qiyuanyuan
 * 
 */
public class GoodsImport {
    /*
     * 商品id
     */
    private Long id;

    /*
     * 商品名称
     */
    private String goodsName;

    /*
     * 商品副标题
     */
    private String goodsSubtitle;

    /*
     * 销售价格
     */
    private BigDecimal goodsPrice;

    /*
     * 商品成本价
     */
    private BigDecimal goodsCostPrice;

    /*
     * SEO key
     */
    private String seoKey;

    /*
     * SEO title
     */
    private String seoTit;

    /*
     * SEO desc
     */
    private String seoDes;

    /*
     * 货品促销价格
     */
    private BigDecimal goodsMarketPrice;

    private Long importUser;

    /*
     * 是否上架
     */
    private String addFlag;

    /*
     * 是否删除
     */
    private String delFlag;

    /*
     * 店铺Id
     */
    private Long thirdId;

    /*
     * 店铺名称
     */
    private String thirdName;

    /*
     * e店铺商品编号
     */
    private String eGoodsNo;

    /*
     * e店宝商品id
     */
    private Long eGoodsId;

    /*
     * 商品描述
     */
    private String goodsDesp;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsSubtitle() {
        return goodsSubtitle;
    }

    public void setGoodsSubtitle(String goodsSubtitle) {
        this.goodsSubtitle = goodsSubtitle;
    }

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public BigDecimal getGoodsCostPrice() {
        return goodsCostPrice;
    }

    public void setGoodsCostPrice(BigDecimal goodsCostPrice) {
        this.goodsCostPrice = goodsCostPrice;
    }

    public String getSeoKey() {
        return seoKey;
    }

    public void setSeoKey(String seoKey) {
        this.seoKey = seoKey;
    }

    public String getSeoTit() {
        return seoTit;
    }

    public void setSeoTit(String seoTit) {
        this.seoTit = seoTit;
    }

    public String getSeoDes() {
        return seoDes;
    }

    public void setSeoDes(String seoDes) {
        this.seoDes = seoDes;
    }

    public BigDecimal getGoodsMarketPrice() {
        return goodsMarketPrice;
    }

    public void setGoodsMarketPrice(BigDecimal goodsMarketPrice) {
        this.goodsMarketPrice = goodsMarketPrice;
    }

    public Long getImportUser() {
        return importUser;
    }

    public void setImportUser(Long importUser) {
        this.importUser = importUser;
    }

    public String getAddFlag() {
        return addFlag;
    }

    public void setAddFlag(String addFlag) {
        this.addFlag = addFlag;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
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

    public Long geteGoodsId() {
        return eGoodsId;
    }

    public void seteGoodsId(Long eGoodsId) {
        this.eGoodsId = eGoodsId;
    }

    public String geteGoodsNo() {
        return eGoodsNo;
    }

    public void seteGoodsNo(String eGoodsNo) {
        this.eGoodsNo = eGoodsNo;
    }

    public String getGoodsDesp() {
        return goodsDesp;
    }

    public void setGoodsDesp(String goodsDesp) {
        this.goodsDesp = goodsDesp;
    }

}
