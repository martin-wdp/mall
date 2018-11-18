package com.qpmall.auto.bean;

/**
 * 车辆（包含力扬ID等）
 * Created by Administrator on 2015/12/18.
 */
public class AutoStyleBean {

    /**
     * 力扬ID
     */
    public String auto_style_id_LiYang_ID;

    public String getauto_style_id_LiYang_ID() {
        return auto_style_id_LiYang_ID;
    }

    public void setauto_style_id_LiYang_ID(String auto_style_id_LiYang_ID) {
        this.auto_style_id_LiYang_ID = auto_style_id_LiYang_ID;
    }

    /**
     * 力扬压缩ID
     */
    public String auto_style_id_LiYangYaSuo_ID;

    public String getauto_style_id_LiYangYaSuo_ID() {
        return auto_style_id_LiYangYaSuo_ID;
    }

    public void setauto_style_id_LiYangYaSuo_ID(String auto_style_id_LiYangYaSuo_ID) {
        this.auto_style_id_LiYangYaSuo_ID = auto_style_id_LiYangYaSuo_ID;
    }

    /**
     * 年款
     */
    public String auto_style_year;

    public String getauto_style_year() {
        return auto_style_year;
    }

    public void setauto_style_year(String auto_style_id_LiYang_ID) {
        this.auto_style_year = auto_style_year;
    }

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

    public static String AutoStyleBean_Sql = "" +
            " SELECT" +
            " auto_style_id_LiYang_ID," +
            " auto_style_id_LiYangYaSuo_ID," +
            " auto_style_year," +
            " CONCAT(goods_brand_name,auto_style_brand_make,auto_style_system,auto_style_type ,auto_style_swept_volume,auto_style_transmission_type,auto_style_sales_version,auto_style_drive,auto_style_chassis,auto_style_engine,auto_style_power,auto_style_productive_year) AutoYearID" +
            " FROM qp_auto_style" +
            " GROUP BY" +
            " auto_style_id_LiYang_ID,auto_style_id_LiYangYaSuo_ID,auto_style_year," +
            " CONCAT(goods_brand_name,auto_style_brand_make,auto_style_system,auto_style_type ,auto_style_swept_volume,auto_style_transmission_type,auto_style_sales_version,auto_style_drive,auto_style_chassis,auto_style_engine,auto_style_power,auto_style_productive_year)" +
            " ORDER BY" +
            " AutoYearID,auto_style_year ,auto_style_id_LiYangYaSuo_ID,auto_style_id_LiYang_ID" +
            "";
}

//    SELECT
//    auto_style_id_LiYang_ID, -- 力扬ID
//    auto_style_id_LiYangYaSuo_ID, -- 力扬压缩ID
//    auto_style_year, -- 年款
//    CONCAT(goods_brand_name,auto_style_brand_make,auto_style_system,auto_style_type ,auto_style_swept_volume,auto_style_transmission_type,auto_style_sales_version,auto_style_drive,auto_style_chassis,auto_style_engine,auto_style_power,auto_style_productive_year) AutoYearID -- 生产年份ID
//    FROM qp_auto_style
//    -- WHERE goods_brand_name="奥迪"
//    GROUP BY
//    auto_style_id_LiYang_ID,auto_style_id_LiYangYaSuo_ID,auto_style_year,
//    CONCAT(goods_brand_name,auto_style_brand_make,auto_style_system,auto_style_type ,auto_style_swept_volume,auto_style_transmission_type,auto_style_sales_version,auto_style_drive,auto_style_chassis,auto_style_engine,auto_style_power,auto_style_productive_year)
//    ORDER BY
//    AutoYearID,auto_style_year ,auto_style_id_LiYangYaSuo_ID,auto_style_id_LiYang_ID
