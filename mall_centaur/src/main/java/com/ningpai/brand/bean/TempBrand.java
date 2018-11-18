package com.ningpai.brand.bean;

/**
 * 
 * @author ggn Ｅ店宝需要使用的实体
 */
public class TempBrand {

    /*
     * E店宝品牌编号
     */
    private String brandCode;
    /*
     * E店宝品牌名称
     */
    private String brandName;
    /*
     * E店宝品牌的字段 暂时无用
     */
    private String enable;

    public String getBrandCode() {
        return brandCode;
    }

    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }

}
