package com.qpmall.site.register.bean;

import java.util.Date;

/**
 * @author luyong
 *         Created by ly-qpmall on 2015/9/21.
 */
public class VerificationInfo {
    /**
     * 主键
     */
    private Long verificationId;
    /**
     * 手机号码
     */
    private String mobilePhone;
    /**
     * 短信验证码
     */
    private String verificationCode;
    /**
     * 短信验证码发送时间
     */
    private Date verificationTime;

    public Long getVerificationId() {
        return verificationId;
    }

    public void setVerificationId(Long verificationId) {
        this.verificationId = verificationId;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public Date getVerificationTime() {
        return verificationTime;
    }

    public void setVerificationTime(Date verificationTime) {
        this.verificationTime = verificationTime;
    }
}
