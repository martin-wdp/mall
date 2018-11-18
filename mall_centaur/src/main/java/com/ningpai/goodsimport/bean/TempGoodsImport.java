package com.ningpai.goodsimport.bean;

import java.math.BigDecimal;
import java.util.List;

/**
 * l临时商品导入实体
 * 
 * @author qiyuanyuan
 * 
 */
public class TempGoodsImport {
    /*
     * 货品id
     */
    private String proBI_id;
    /*
     * 货品编号
     */
    private String pro_code;
    /*
     * 货品名称
     */
    private String pro_name;
    /*
     * 优惠价格
     */
    private BigDecimal market_price;
    /*
     * 货品介绍
     */
    private String pro_intro;
    /*
     * 货品类
     */
    private String pro_class;
    /*
     * 销售价
     */
    private BigDecimal sell_price;
    /*
     * 货品重量
     */
    private String weight;
    /*
     * 货品图片
     */

    private String pro_picture;
    /*
     * 货品成本价
     */
    private BigDecimal cost;
    /*
     * 平均成本
     */
    private BigDecimal avg_taxCost;
    /*
     * bar编号
     */
    private List<TempBarCode> barCode;

    public List<TempBarCode> getBarCode() {
        return barCode;
    }

    public void setBarCode(List<TempBarCode> barCode) {
        this.barCode = barCode;
    }

    public String getProBI_id() {
        return proBI_id;
    }

    public void setProBI_id(String proBIId) {
        this.proBI_id = proBIId;
    }

    public String getPro_code() {
        return pro_code;
    }

    public void setPro_code(String proCode) {
        this.pro_code = proCode;
    }

    public String getPro_name() {
        return pro_name;
    }

    public void setPro_name(String proName) {
        this.pro_name = proName;
    }

    public String getPro_intro() {
        return pro_intro;
    }

    public void setPro_intro(String proIntro) {
        this.pro_intro = proIntro;
    }

    public String getPro_class() {
        return pro_class;
    }

    public void setPro_class(String proClass) {
        this.pro_class = proClass;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getPro_picture() {
        return pro_picture;
    }

    public void setPro_picture(String proPicture) {
        this.pro_picture = proPicture;
    }

    public BigDecimal getMarket_price() {
        return market_price;
    }

    public void setMarket_price(BigDecimal marketPrice) {
        this.market_price = marketPrice;
    }

    public BigDecimal getSell_price() {
        return sell_price;
    }

    public void setSell_price(BigDecimal sellPrice) {
        this.sell_price = sellPrice;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public BigDecimal getAvg_taxCost() {
        return avg_taxCost;
    }

    public void setAvg_taxCost(BigDecimal avgTaxCost) {
        this.avg_taxCost = avgTaxCost;
    }

}
