package com.ningpai.goodsimport.bean;

/**
 * 临时条目编码
 * 
 * @author qiyuanyuan
 * 
 */
public class TempBarCode {

    /**
     * 到达数量
     */
    private String arrivalAmounto;
    /**
     * 成本价
     */
    private String costTaxPrice;

    public String getArrivalAmounto() {
        return arrivalAmounto;
    }

    public void setArrivalAmounto(String arrivalAmounto) {
        this.arrivalAmounto = arrivalAmounto;
    }

    public String getCostTaxPrice() {
        return costTaxPrice;
    }

    public void setCostTaxPrice(String costTaxPrice) {
        this.costTaxPrice = costTaxPrice;
    }

}
