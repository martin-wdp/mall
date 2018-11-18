package com.ningpai.goods.bean;

/**
 * Created by wuyanbo on 2015/12/20.
 * 货品适配车型表
 */
public class GoodsProductAutoStyle {
    /*
     * 主键ID
     */
    private Long proAutoStyleId;
    /*
     * 货品信息ID
     */
    private Long goodsInfoId;
    /*
     * 品牌
     */
    private String goodsBrandName;
    /*
     * 品牌厂家
     */
    private String autoStyleBrandMake;
    /*
     * 车系
     */
    private String autoStyleSystem;
    /*
     * 车型
     */
    private String autoStyleType;
    /*
     * 发动机
     */
    private String autoStyleEngine;
    /*
     * 变速箱
     */
    private String autoStyleGearbox;
    /*
     * 生产年份
     */
    private String autoStyleProductiveYear;

    public Long getProAutoStyleId() {
        return proAutoStyleId;
    }

    public void setProAutoStyleId(Long proAutoStyleId) {
        this.proAutoStyleId = proAutoStyleId;
    }

    public Long getGoodsInfoId() {
        return goodsInfoId;
    }

    public void setGoodsInfoId(Long goodsInfoId) {
        this.goodsInfoId = goodsInfoId;
    }

    public String getGoodsBrandName() {
        return goodsBrandName;
    }

    public void setGoodsBrandName(String goodsBrandName) {
        this.goodsBrandName = goodsBrandName;
    }

    public String getAutoStyleBrandMake() {
        return autoStyleBrandMake;
    }

    public void setAutoStyleBrandMake(String autoStyleBrandMake) {
        this.autoStyleBrandMake = autoStyleBrandMake;
    }

    public String getAutoStyleSystem() {
        return autoStyleSystem;
    }

    public void setAutoStyleSystem(String autoStyleSystem) {
        this.autoStyleSystem = autoStyleSystem;
    }

    public String getAutoStyleType() {
        return autoStyleType;
    }

    public void setAutoStyleType(String autoStyleType) {
        this.autoStyleType = autoStyleType;
    }

    public String getAutoStyleEngine() {
        return autoStyleEngine;
    }

    public void setAutoStyleEngine(String autoStyleEngine) {
        this.autoStyleEngine = autoStyleEngine;
    }

    public String getAutoStyleGearbox() {
        return autoStyleGearbox;
    }

    public void setAutoStyleGearbox(String autoStyleGearbox) {
        this.autoStyleGearbox = autoStyleGearbox;
    }

    public String getAutoStyleProductiveYear() {
        return autoStyleProductiveYear;
    }

    public void setAutoStyleProductiveYear(String autoStyleProductiveYear) {
        this.autoStyleProductiveYear = autoStyleProductiveYear;
    }
}
