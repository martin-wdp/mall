package com.qpmall.auto.bean;

/**
 * 车辆参数
 * Created by yanggd on 2015/12/18.
 */
public class AutoParameterBean {

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

    /**
     * 排量（升）
     */
    public String auto_style_swept_volume;

    public String getauto_style_swept_volume() {
        return auto_style_swept_volume;
    }

    public void setauto_style_swept_volume(String auto_style_swept_volume) {
        this.auto_style_swept_volume = auto_style_swept_volume;
    }

    /**
     * 变速器类型
     */
    public String auto_style_transmission_type;

    public String getauto_style_transmission_type() {
        return auto_style_transmission_type;
    }

    public void setauto_style_transmission_type(String auto_style_transmission_type) {
        this.auto_style_transmission_type = auto_style_transmission_type;
    }

    /**
     * 销售版本
     */
    public String auto_style_sales_version;

    public String getauto_style_sales_version() {
        return auto_style_sales_version;
    }

    public void setauto_style_sales_version(String auto_style_sales_version) {
        this.auto_style_sales_version = auto_style_sales_version;
    }

    /**
     * 驱动方式
     */
    public String auto_style_drive;

    public String getauto_style_drive() {
        return auto_style_drive;
    }

    public void setauto_style_drive(String auto_style_drive) {
        this.auto_style_drive = auto_style_drive;
    }

    /**
     * 底盘
     */
    public String auto_style_chassis;

    public String getauto_style_chassis() {
        return auto_style_chassis;
    }

    public void setauto_style_chassis(String auto_style_chassis) {
        this.auto_style_chassis = auto_style_chassis;
    }

    /**
     * 发动机
     */
    public String auto_style_engine;

    public String getauto_style_engine() {
        return auto_style_engine;
    }

    public void setauto_style_engine(String auto_style_engine) {
        this.auto_style_engine = auto_style_engine;
    }

    /**
     * 功率（kW）
     */
    public String auto_style_power;

    public String getauto_style_power() {
        return auto_style_power;
    }

    public void setauto_style_power(String auto_style_power) {
        this.auto_style_power = auto_style_power;
    }

    /**
     * 车型ID
     */
    public String AutoTypeID;

    public String getAutoTypeID() {
        return AutoTypeID;
    }

    public void setAutoTypeID(String AutoTypeID) {
        this.AutoTypeID = AutoTypeID;
    }

    public static String AutoParameterBean_Sql = "" +
            " SELECT" +
            " CONCAT(goods_brand_name,auto_style_brand_make,auto_style_system,auto_style_type ,auto_style_swept_volume,auto_style_transmission_type,auto_style_sales_version,auto_style_drive,auto_style_chassis,auto_style_engine,auto_style_power) AutoParameterID," +
            " auto_style_swept_volume," +
            " auto_style_transmission_type," +
            " auto_style_sales_version," +
            " auto_style_drive," +
            " auto_style_chassis," +
            " auto_style_engine," +
            " auto_style_power," +
            " CONCAT(goods_brand_name,auto_style_brand_make,auto_style_system,auto_style_type) AutoTypeID" +
            " FROM qp_auto_style" +
            " GROUP BY" +
            " CONCAT(goods_brand_name,auto_style_brand_make,auto_style_system,auto_style_type ,auto_style_swept_volume,auto_style_transmission_type,auto_style_sales_version,auto_style_drive,auto_style_chassis,auto_style_engine,auto_style_power)," +
            " auto_style_swept_volume ,auto_style_transmission_type ,auto_style_sales_version ,auto_style_drive ,auto_style_chassis ,auto_style_engine ,auto_style_power," +
            " CONCAT(goods_brand_name,auto_style_brand_make,auto_style_system,auto_style_type)" +
            " ORDER BY" +
            " AutoParameterID";
}

//    SELECT
//    CONCAT(goods_brand_name,auto_style_brand_make,auto_style_system,auto_style_type ,auto_style_swept_volume,auto_style_transmission_type,auto_style_sales_version,auto_style_drive,auto_style_chassis,auto_style_engine,auto_style_power) AutoParameterID , -- 车辆参数ID
//    auto_style_swept_volume, -- 排量（升）
//    auto_style_transmission_type, -- 变速器类型
//    auto_style_sales_version, -- 销售版本
//    auto_style_drive, -- 驱动方式
//    auto_style_chassis, -- 底盘
//    auto_style_engine, -- 发动机
//    auto_style_power, -- 功率（kW）
//    CONCAT(goods_brand_name,auto_style_brand_make,auto_style_system,auto_style_type) AutoTypeID -- 车型ID
//    FROM qp_auto_style
//    -- WHERE goods_brand_name="宝马"
//    GROUP BY
//    CONCAT(goods_brand_name,auto_style_brand_make,auto_style_system,auto_style_type ,auto_style_swept_volume,auto_style_transmission_type,auto_style_sales_version,auto_style_drive,auto_style_chassis,auto_style_engine,auto_style_power) ,
//    auto_style_swept_volume ,auto_style_transmission_type ,auto_style_sales_version ,auto_style_drive ,auto_style_chassis ,auto_style_engine ,auto_style_power,
//    CONCAT(goods_brand_name,auto_style_brand_make,auto_style_system,auto_style_type)
//    ORDER BY
//    AutoParameterID
