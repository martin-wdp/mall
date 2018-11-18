package com.qpmall.auto.bean;

/**
 * 生产年份（不是年款）
 * Created by yanggd on 2015/12/23.
 */
public class AutoProductiveYearBean {

    /**
     * 生产年份ID
     */
    public String AutoYearID;

    public String getAutoYearID() {
        return AutoYearID;
    }

    public void setAutoYearID(String AutoYearID) {
        this.AutoYearID = AutoYearID;
    }

    /**
     * 生产年份
     */
    public String auto_style_productive_year;

    public String getauto_style_productive_year() {
        return auto_style_productive_year;
    }

    public void setauto_style_productive_year(String auto_style_productive_year) {
        this.auto_style_productive_year = auto_style_productive_year;
    }

    /**
     * 车辆参数ID
     */
    public String AutoParameterID;

    public String getAutoParameterID() {
        return AutoParameterID;
    }

    public void setAutoParameterID(String AutoParameterID) {
        this.AutoParameterID = AutoParameterID;
    }

    public static String AutoProductiveYearBean_Sql = "" +
            " SELECT" +
            " CONCAT(goods_brand_name,auto_style_brand_make,auto_style_system,auto_style_type ,auto_style_swept_volume,auto_style_transmission_type,auto_style_sales_version,auto_style_drive,auto_style_chassis,auto_style_engine,auto_style_power,auto_style_productive_year) AutoYearID," +
            " auto_style_productive_year," +
            " CONCAT(goods_brand_name,auto_style_brand_make,auto_style_system,auto_style_type ,auto_style_swept_volume,auto_style_transmission_type,auto_style_sales_version,auto_style_drive,auto_style_chassis,auto_style_engine,auto_style_power) AutoParameterID" +
            " FROM qp_auto_style" +
            " GROUP BY" +
            " CONCAT(goods_brand_name,auto_style_brand_make,auto_style_system,auto_style_type ,auto_style_swept_volume,auto_style_transmission_type,auto_style_sales_version,auto_style_drive,auto_style_chassis,auto_style_engine,auto_style_power,auto_style_productive_year)," +
            " auto_style_productive_year," +
            " CONCAT(goods_brand_name,auto_style_brand_make,auto_style_system,auto_style_type ,auto_style_swept_volume,auto_style_transmission_type,auto_style_sales_version,auto_style_drive,auto_style_chassis,auto_style_engine,auto_style_power)" +
            " ORDER BY" +
            " AutoYearID";
}

//    SELECT
//    CONCAT(goods_brand_name,auto_style_brand_make,auto_style_system,auto_style_type ,auto_style_swept_volume,auto_style_transmission_type,auto_style_sales_version,auto_style_drive,auto_style_chassis,auto_style_engine,auto_style_power,auto_style_productive_year) AutoYearID,  -- 生产年份ID
//    auto_style_productive_year,  -- 生产年份
//    CONCAT(goods_brand_name,auto_style_brand_make,auto_style_system,auto_style_type ,auto_style_swept_volume,auto_style_transmission_type,auto_style_sales_version,auto_style_drive,auto_style_chassis,auto_style_engine,auto_style_power) AutoParameterID -- 车辆参数ID
//    FROM qp_auto_style
//    -- WHERE goods_brand_name="奥迪"
//    GROUP BY
//    CONCAT(goods_brand_name,auto_style_brand_make,auto_style_system,auto_style_type ,auto_style_swept_volume,auto_style_transmission_type,auto_style_sales_version,auto_style_drive,auto_style_chassis,auto_style_engine,auto_style_power,auto_style_productive_year) ,
//    auto_style_productive_year,
//    CONCAT(goods_brand_name,auto_style_brand_make,auto_style_system,auto_style_type ,auto_style_swept_volume,auto_style_transmission_type,auto_style_sales_version,auto_style_drive,auto_style_chassis,auto_style_engine,auto_style_power)
//    ORDER BY
//    AutoYearID