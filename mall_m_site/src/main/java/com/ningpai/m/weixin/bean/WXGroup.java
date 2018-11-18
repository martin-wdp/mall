package com.ningpai.m.weixin.bean;

import java.util.Date;
/**
 * 微信分组类
 * */
public class WXGroup {
    private Long wxId;

    private String openid;

    private Date getTime;

    private String emp1;

    private String emp2;

    public Long getWxId() {
        return wxId;
    }

    public void setWxId(Long wxId) {
        this.wxId = wxId;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public Date getGetTime() {
        return getTime;
    }

    public void setGetTime(Date getTime) {
        this.getTime = getTime;
    }

    public String getEmp1() {
        return emp1;
    }

    public void setEmp1(String emp1) {
        this.emp1 = emp1;
    }

    public String getEmp2() {
        return emp2;
    }

    public void setEmp2(String emp2) {
        this.emp2 = emp2;
    }
}
