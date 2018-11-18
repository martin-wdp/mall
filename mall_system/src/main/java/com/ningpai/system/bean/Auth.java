package com.ningpai.system.bean;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 登录接口实体类
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2013年12月24日 下午7:57:00
 * @version 1.0
 */
public class Auth {
    /* 主键
     * 
     */
    private Long authId;
    /* 第三方名称
     * 
     */
    @NotNull
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+")
    private String authName;
    /*
     * AppKey
     */
    @NotNull
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+")
    private String authClientId;
    /* AppSecret
     * 
     */
    @NotNull
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+")
    private String authClientSecret;
    /* 回调地址
     * 
     */
    @NotNull
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+")
    private String authRedirectUri;
    /*
     * 排序字段
     */
    private Short authSort;
    /*是否启用
     * 
     */
    private String authShow;
    /*
     *  创建人
     */
    private String authCreateName;
    /*
     *  创建时间
     */
    private Date authCreated;
    /*
     *  修改人
     */
    private String authUpdateName;
    /*
     *  修改时间
     */
    private Date authUpdated;
    /*
     *  删除时间
     */
    private Date authDeleted;
    /*
     * 删除标记
     */
    private String authDeleteFlag;
    /*
     * 类型
     */
    private String authType;
    /*
     * 图片
     */
    @Pattern(regexp = "[^\\<\\>]+|()")
    private String authPic;
    /*
     * 登陆接口
     */
    private String authLogin;
    
    public String getAuthLogin() {
        return authLogin;
    }

    public void setAuthLogin(String authLogin) {
        this.authLogin = authLogin;
    }

    public String getAuthType() {
        return authType;
    }

    public void setAuthType(String authType) {
        this.authType = authType;
    }

    public String getAuthPic() {
        return authPic;
    }

    public void setAuthPic(String authPic) {
        this.authPic = authPic;
    }

    public Long getAuthId() {
        return authId;
    }

    public void setAuthId(Long authId) {
        this.authId = authId;
    }

    public String getAuthName() {
        return authName;
    }

    public void setAuthName(String authName) {
        this.authName = authName;
    }

    public String getAuthClientId() {
        return authClientId;
    }

    public void setAuthClientId(String authClientId) {
        this.authClientId = authClientId;
    }

    public String getAuthClientSecret() {
        return authClientSecret;
    }

    public void setAuthClientSecret(String authClientSecret) {
        this.authClientSecret = authClientSecret;
    }

    public String getAuthRedirectUri() {
        return authRedirectUri;
    }

    public void setAuthRedirectUri(String authRedirectUri) {
        this.authRedirectUri = authRedirectUri;
    }

    public Short getAuthSort() {
        return authSort;
    }

    public void setAuthSort(Short authSort) {
        this.authSort = authSort;
    }

    public String getAuthShow() {
        return authShow;
    }

    public void setAuthShow(String authShow) {
        this.authShow = authShow;
    }

    public String getAuthCreateName() {
        return authCreateName;
    }

    public void setAuthCreateName(String authCreateName) {
        this.authCreateName = authCreateName;
    }

    /**
     * 创建时间
     * @return
     */
    public Date getAuthCreated() {
        if (this.authCreated != null) {
            return new Date(this.authCreated.getTime());
        } else {
            return null;
        }

    }
    /**
     * 创建时间
     * @return
     */
    public void setAuthCreated(Date authCreated) {
        if (authCreated != null) {
            Date tEmp = (Date) authCreated.clone();
            if (tEmp != null) {
                this.authCreated = tEmp;
            }
        }

    }
    
    public String getAuthUpdateName() {
        return authUpdateName;
    }
    
    public void setAuthUpdateName(String authUpdateName) {
        this.authUpdateName = authUpdateName;
    }

    /**
     * 时间
     * @return
     */
    public Date getAuthUpdated() {
        if (this.authUpdated != null) {
            return new Date(this.authUpdated.getTime());
        } else {
            return null;
        }
    }
    /**
     * 时间
     * @return
     */
    public void setAuthUpdated(Date authUpdated) {
        if (authUpdated != null) {
            Date tEmp = (Date) authUpdated.clone();
            if (tEmp != null) {
                this.authUpdated = tEmp;
            }
        }
    }

    /**
     * 时间
     * @return
     */
    public Date getAuthDeleted() {
        if (this.authDeleted != null) {
            return new Date(this.authDeleted.getTime());
        } else {
            return null;
        }
    }

    /**
     * 时间
     * @return
     */
    public void setAuthDeleted(Date authDeleted) {
        if (authDeleted != null) {
            Date tEmp = (Date) authDeleted.clone();
            if (tEmp != null) {
                this.authDeleted = tEmp;
            }
        }
    }

    public String getAuthDeleteFlag() {
        return authDeleteFlag;
    }

    public void setAuthDeleteFlag(String authDeleteFlag) {
        this.authDeleteFlag = authDeleteFlag;
    }
}
