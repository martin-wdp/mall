package com.ningpai.goods.bean;

import java.math.BigDecimal;

/**
 * 商品导入临时Bean
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年10月20日 上午9:31:26
 * @version
 */
public class GoodsImport {
    /*
     * 主键ID
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
     * 商品价格
     */
    private BigDecimal goodsPrice;
    /*
     * 商品会员价格
     * 2015.10.22 wuyanbo 添加会员价格
     */
    private BigDecimal goodsVipPrice;
    /*
     * 商品成本价格
     */
    private BigDecimal goodsCostPrice;
    /*
     * 商品市场价格
     */
    private BigDecimal goodsMarketPrice;
    /*
     * 搜索引擎优化标题
     */
    private String seoTit;
    /*
     * 搜索引擎优化关键字
     */
    private String seoKey;
    /*
     * 搜索引擎详细优化
     */
    private String seoDes;
    /*
     * 商品详细介绍
     */
    private String goodsDes;
    /*
     * 第三方ID
     */
    private Long thirdId;
    /*
     * 第三方名称
     */
    private String thirdName;

    /*
     * 导入人ID
     */
    private Long importUser;
    /*
     * 是否发布的标记 0:未发布
     */
    private String addFlag;
    /*
     * 商品删除的标记 0:未删除
     */
    private String delFlag;

    private String goodsNo;

    private Long goodsId;

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

    public BigDecimal getGoodsVipPrice() {
        return goodsVipPrice;
    }

    public void setGoodsVipPrice(BigDecimal goodsVipPrice) {
        this.goodsVipPrice = goodsVipPrice;
    }

    public BigDecimal getGoodsCostPrice() {
        return goodsCostPrice;
    }

    public void setGoodsCostPrice(BigDecimal goodsCostPrice) {
        this.goodsCostPrice = goodsCostPrice;
    }

    public BigDecimal getGoodsMarketPrice() {
        return goodsMarketPrice;
    }

    public void setGoodsMarketPrice(BigDecimal goodsMarketPrice) {
        this.goodsMarketPrice = goodsMarketPrice;
    }

    public String getSeoTit() {
        return seoTit;
    }

    public void setSeoTit(String seoTit) {
        this.seoTit = seoTit;
    }

    public String getSeoKey() {
        return seoKey;
    }

    public void setSeoKey(String seoKey) {
        this.seoKey = seoKey;
    }

    public String getSeoDes() {
        return seoDes;
    }

    public void setSeoDes(String seoDes) {
        this.seoDes = seoDes;
    }

    public String getGoodsDes() {
        return goodsDes;
    }

    public void setGoodsDes(String goodsDes) {
        this.goodsDes = goodsDes;
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

    public String getGoodsNo() {
        return goodsNo;
    }

    public void setGoodsNo(String goodsNo) {
        this.goodsNo = goodsNo;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

}
