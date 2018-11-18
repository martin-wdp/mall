package com.qpmall.auto.bean;

/**
 * 品牌
 * Created by yanggd on 2015/12/18.
 */
public class AutoBrandBean {

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

    /**
     * 车辆品牌的名称
     */
    private String goods_brand_name;

    public String getgoods_brand_name() {
        return goods_brand_name;
    }

    public void setgoods_brand_name(String goods_brand_name) {
        this.goods_brand_name = goods_brand_name;
    }

    /**
     * 车辆品牌的图标地址
     */
    private String brand_logo;

    public String getbrand_logo() {
        return brand_logo;
    }

    public void setbrand_logo(String brand_logo) {
        this.brand_logo = brand_logo;
    }

    public static String AutoBrandBean_Sql = "" +
            " SELECT" +
            " a.goods_brand_name AutoBrandID," +
            " a.goods_brand_name," +
            " b.brand_logo" +
            " FROM qp_auto_style a  LEFT OUTER JOIN np_goods_brand b ON a.goods_brand_id=b.brand_id" +
            " GROUP BY" +
            " a.goods_brand_name,b.brand_logo" +
            " ORDER BY" +
            " AutoBrandID";
}

//    SELECT
//    a.goods_brand_name AutoBrandID, -- 车辆品牌ID
//    a.goods_brand_name, -- 品牌
//    b.brand_logo -- 品牌的图标
//    FROM qp_auto_style a  LEFT OUTER JOIN np_goods_brand b ON a.goods_brand_id=b.brand_id
//    -- WHERE a.goods_brand_name="奥迪"
//    GROUP BY
//    a.goods_brand_name,b.brand_logo
//    ORDER BY
//    AutoBrandID