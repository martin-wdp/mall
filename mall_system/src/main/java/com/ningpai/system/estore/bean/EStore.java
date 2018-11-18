/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.system.estore.bean;

import java.util.Date;

import javax.validation.constraints.Pattern;

/**
 * E店宝实体类
 * 
 * @author jiping
 * @since 2014年12月2日 上午10:33:39
 * @version 0.0.1
 */
public class EStore {
   /* 
    * E店宝ID
    */
    private Long estoreid;

   /*
    *  服务器
    */
   //@NotNull
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+")
    private String server;

   /*
    *  主账号
    */
   //@NotNull
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+")
    private String dbhost;

   /*
    *  AppKey
    */
   //@NotNull
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+")
    private String appkey;

   /* 
    * AppScret
    */
   //@NotNull
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+")
    private String appscret;

   /*
    *  token
    */
   //@NotNull
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+")
    private String token;
   /*
    *  申请ERP地址
    */

    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+")
    private String address;
   /* 
    * ERP提供商
    */

    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+")
    private String provider;
   /* 
    * ERP图片
    */
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+")
    private String image;


   /* 
    * 是否开启
    */
    private String isopen;

   /* 
    * 创建时间
    */
    private Date createtime;

   /*
    *  修改时间
    */
    private Date updatetime;

   /*
    *  删除标记
    */
    private String delflag;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Long getEstoreid() {
        return estoreid;
    }

    public void setEstoreid(Long estoreid) {
        this.estoreid = estoreid;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getDbhost() {
        return dbhost;
    }

    public void setDbhost(String dbhost) {
        this.dbhost = dbhost;
    }

    /**
     * 时间
     * @return
     */
    public Date getCreateTime() {
        if (this.createtime != null) {
            return new Date(this.createtime.getTime());
        } else {
            return null;
        }
    }
    /**
     * 时间
     * @return
     */
    public void setCreateTime(Date createTime) {
        if (createTime != null) {
            Date tEmp = (Date) createTime.clone();
            if (tEmp != null) {
                this.createtime = tEmp;
            }
        }
    }
    /**
     * 时间
     * @return
     */
    public Date getUpdatetime() {
        if (this.updatetime != null) {
            return new Date(this.updatetime.getTime());
        } else {
            return null;
        }
    }
    /**
     * 时间
     * @return
     */
    public void setUpdatetime(Date updatetime) {
        if (updatetime != null) {
            Date tEmp = (Date) updatetime.clone();
            if (tEmp != null) {
                this.updatetime = tEmp;
            }
        }
    }

    public String getDelflag() {
        return delflag;
    }

    public void setDelflag(String delflag) {
        this.delflag = delflag;
    }

    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }

    public String getAppscret() {
        return appscret;
    }

    public void setAppscret(String appscret) {
        this.appscret = appscret;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getIsopen() {
        return isopen;
    }

    public void setIsopen(String isopen) {
        this.isopen = isopen;
    }

}
