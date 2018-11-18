package com.qpmall.auto.bean;

/**
 * 品牌车系（品牌厂家）
 * Created by yanggd on 2015/12/23.
 */
public class AutoStyleBrandBean {

    /**
     * 品牌车系ID
     */
    private String AutoStyleID;
    public String getAutoStyleID() {
        return AutoStyleID;
    }
    public void setAutoStyleID(String AutoStyleID) {
        this.AutoStyleID = AutoStyleID;
    }

    /**
     * 品牌厂家
     */
    private String auto_style_brand_make;
    public String getauto_style_brand_make() {
        return auto_style_brand_make;
    }
    public void setauto_style_brand_make(String auto_style_brand_make) {this.auto_style_brand_make = auto_style_brand_make;}

    /**
     * 车辆品牌的ID
     */
    private String AutoBrandID;
    public String getAutoBrandID() {
        return AutoBrandID;
    }
    public void setAutoBrandID(String AutoBrandID) {
        this.AutoBrandID = AutoBrandID;
    }

    public static String AutoStyleBrandBean_Sql = "" +
            " SELECT" +
            " CONCAT(goods_brand_name,auto_style_brand_make) AutoStyleID," +
            " auto_style_brand_make," +
            " goods_brand_name AutoBrandID" +
            " FROM qp_auto_style" +
            " GROUP BY" +
            " CONCAT(goods_brand_name,auto_style_brand_make)," +
            " auto_style_brand_make,goods_brand_name" +
            " ORDER BY" +
            " AutoStyleID";
}

//    SELECT
//    CONCAT(goods_brand_name,auto_style_brand_make) AutoStyleID, -- 品牌车系ID
//    auto_style_brand_make,  -- 品牌厂家
//    goods_brand_name AutoBrandID  -- 车辆品牌的ID
//    FROM qp_auto_style
//    -- WHERE goods_brand_name="奥迪"
//    GROUP BY
//    CONCAT(goods_brand_name,auto_style_brand_make),
//    auto_style_brand_make,goods_brand_name
//    ORDER BY
//    AutoStyleID
