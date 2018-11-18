package com.qpmall.auto.bean;

/**
 * 车系
 * Created by yanggd on 2015/12/18.
 */
public class AutoSystemBean {

    /**
     * 车系ID
     */
    private String AutoSystemID;

    public String getAutoSystemID() {
        return AutoSystemID;
    }

    public void setAutoSystemID(String AutoSystemID) {
        this.AutoSystemID = AutoSystemID;
    }

    /**
     * 车系
     */
    private String auto_style_system;

    public String getauto_style_system() {
        return auto_style_system;
    }

    public void setauto_style_system(String auto_style_system) {
        this.auto_style_system = auto_style_system;
    }

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

    public static String AutoSystemBean_Sql = "" +
            " SELECT" +
            " CONCAT(goods_brand_name,auto_style_brand_make,auto_style_system) AutoSystemID," +
            " auto_style_system," +
            " CONCAT(goods_brand_name,auto_style_brand_make) AutoStyleID" +
            " FROM qp_auto_style" +
            " GROUP BY" +
            " CONCAT(goods_brand_name,auto_style_brand_make,auto_style_system)," +
            " auto_style_system," +
            " CONCAT(goods_brand_name,auto_style_brand_make)" +
            " ORDER BY" +
            " AutoSystemID";
}

//    SELECT
//    CONCAT(goods_brand_name,auto_style_brand_make,auto_style_system) AutoSystemID, -- 车系ID
//    auto_style_system, -- 车系
//    CONCAT(goods_brand_name,auto_style_brand_make) AutoStyleID -- 品牌车系ID
//    FROM qp_auto_style
//    -- where goods_brand_name="奥迪"
//    GROUP BY
//    CONCAT(goods_brand_name,auto_style_brand_make,auto_style_system),
//    auto_style_system,
//    CONCAT(goods_brand_name,auto_style_brand_make)
//    ORDER BY
//    AutoSystemID
