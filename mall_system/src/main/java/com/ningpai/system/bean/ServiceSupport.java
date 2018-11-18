package com.ningpai.system.bean;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 服务支持
 * 
 * @author NINGPAI-zhuoyu
 * @since 2014年7月31日上午10:37:28
 */
public class ServiceSupport {
    /** 编号 */
    private Long id;
    /** 服务支持名称 */
    @NotNull
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+")
    private String serviceName;
    /** 服务支持图片 */
    @Pattern(regexp = "[^\\<\\>]+|()")
    private String serviceImageUrl;
    /** 帮助ID */
    private Long helpId;
    /** 删除标记 0：未删除 1：已删除 */
    private String delfalg;
    /** 创建人ID */
    private Long createUserId;
    /** 创建时间 */
    private Date createTime;
    /** 修改人ID */
    private Long updateUserId;
    /** 修改时间 */
    private Date updateTime;
    /** 备用字段1 */
    private String reserve1;
    /** 备用字段2 */
    private String reserve2;
    /** 备用字段3 */
    private String reserve3;
    /** 备用字段4 */
    private String reserve4;
    /** 备用字段5 */
    private String reserve5;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceImageUrl() {
        return serviceImageUrl;
    }

    public void setServiceImageUrl(String serviceImageUrl) {
        this.serviceImageUrl = serviceImageUrl;
    }

    public Long getHelpId() {
        return helpId;
    }

    public void setHelpId(Long helpId) {
        this.helpId = helpId;
    }

    public String getDelfalg() {
        return delfalg;
    }

    public void setDelfalg(String delfalg) {
        this.delfalg = delfalg;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getReserve1() {
        return reserve1;
    }

    public void setReserve1(String reserve1) {
        this.reserve1 = reserve1;
    }

    public String getReserve2() {
        return reserve2;
    }

    public void setReserve2(String reserve2) {
        this.reserve2 = reserve2;
    }

    public String getReserve3() {
        return reserve3;
    }

    public void setReserve3(String reserve3) {
        this.reserve3 = reserve3;
    }

    public String getReserve4() {
        return reserve4;
    }

    public void setReserve4(String reserve4) {
        this.reserve4 = reserve4;
    }

    public String getReserve5() {
        return reserve5;
    }

    public void setReserve5(String reserve5) {
        this.reserve5 = reserve5;
    }
}
