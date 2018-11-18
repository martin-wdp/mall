package com.ningpai.coupon.bean;

import java.io.Serializable;
/**
 * serial
 * @author Zhuoy
 *
 */
public class CouponGet implements Serializable{
    
    /*
     * 默认
     */
    private static final long serialVersionUID = 1L;
    /*
     * url
     */
    private String url;
    /*
     * 编号
     */
    private Long codeId;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getCodeId() {
        return codeId;
    }

    public void setCodeId(Long codeId) {
        this.codeId = codeId;
    }

}
