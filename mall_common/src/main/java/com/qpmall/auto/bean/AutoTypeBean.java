package com.qpmall.auto.bean;

/**
 * 车型
 * Created by yanggd on 2015/12/18.
 */
public class AutoTypeBean {

    /**
     * 车型ID
     */
    private String AutoTypeID;

    public String getAutoTypeID() {
        return AutoTypeID;
    }

    public void setAutoTypeID(String AutoTypeID) {
        this.AutoTypeID = AutoTypeID;
    }

    /**
     * 车型
     */
    private String auto_style_type;

    public String getauto_style_type() {
        return auto_style_type;
    }

    public void setauto_style_type(String auto_style_type) {
        this.auto_style_type = auto_style_type;
    }

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

    public static String AutoTypeBean_Sql = "" +
            " SELECT" +
            " CONCAT(goods_brand_name,auto_style_brand_make,auto_style_system,auto_style_type) AutoTypeID," +
            " auto_style_type," +
            " CONCAT(goods_brand_name,auto_style_brand_make,auto_style_system) AutoSystemID" +
            " FROM qp_auto_style" +
            " GROUP BY" +
            " CONCAT(goods_brand_name,auto_style_brand_make,auto_style_system,auto_style_type)," +
            " auto_style_type," +
            " CONCAT(goods_brand_name,auto_style_brand_make,auto_style_system)" +
            " ORDER BY" +
            " AutoTypeID";
}

//    SELECT
//    CONCAT(goods_brand_name,auto_style_brand_make,auto_style_system,auto_style_type) AutoTypeID, -- 车型ID
//    auto_style_type, -- 车型
//    CONCAT(goods_brand_name,auto_style_brand_make,auto_style_system) AutoSystemID -- 车系ID
//    FROM qp_auto_style
//    -- WHERE goods_brand_name="奥迪"
//    GROUP BY
//    CONCAT(goods_brand_name,auto_style_brand_make,auto_style_system,auto_style_type),
//    auto_style_type,
//    CONCAT(goods_brand_name,auto_style_brand_make,auto_style_system)
//    ORDER BY
//    AutoTypeID
