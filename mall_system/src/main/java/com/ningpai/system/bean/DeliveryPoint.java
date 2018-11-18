package com.ningpai.system.bean;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 实体类-自提点
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年9月18日上午10:16:40
 */
public class DeliveryPoint {
    /** 编号 */
    private Long deliveryPointId;
    /** 区县ID */
    @NotNull
    private Long districtId;
    /** 自提点名称 */
    @NotNull
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+")
    private String name;
    /** 自提点地址 */
    @NotNull
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+")
    private String address;
    /** 是否启用 */
    private String isUserd;
    /** 删除标记 1删除 */
    private String delflag;
    /** 创建人ID */
    private Long createUserId;
    /** 创建时间 */
    private Date createDate;
    /** 修改人ID */
    private Long updateUserId;
    /** 修改时间 */
    private Date updateDate;
    /** 区县名称 */
    private String temp1;
    /** 扩展字段2 */
    private String temp2;
    /** 扩展字段3 */
    private String temp3;
    /** 扩展字段4 */
    private String temp4;
    /** 扩展字段5 */
    private String temp5;
    // 联系人
    private String linkman;
    // 联系电话
    private String telephone;

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Long getDeliveryPointId() {
        return deliveryPointId;
    }

    public void setDeliveryPointId(Long deliveryPointId) {
        this.deliveryPointId = deliveryPointId;
    }

    public Long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIsUserd() {
        return isUserd;
    }

    public void setIsUserd(String isUserd) {
        this.isUserd = isUserd;
    }

    public String getDelflag() {
        return delflag;
    }

    public void setDelflag(String delflag) {
        this.delflag = delflag;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }
    /**
     * 获取创建时间
     * */
    public Date getCreateDate() {
        return createDate==null?null:(Date) createDate.clone();
    }
    /**
     * 设置创建时间
     * */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate == null ? null : (Date) createDate.clone();
    }

    public Long getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }
    /**
     * 时间
     * @return
     */
    public Date getUpdateDate() {
        return updateDate==null?null:(Date) updateDate.clone();
    }
    /**
     * 时间
     * @return
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate == null ? null : (Date) updateDate.clone();
    }

    public String getTemp1() {
        return temp1;
    }

    public void setTemp1(String temp1) {
        this.temp1 = temp1;
    }

    public String getTemp2() {
        return temp2;
    }

    public void setTemp2(String temp2) {
        this.temp2 = temp2;
    }

    public String getTemp3() {
        return temp3;
    }

    public void setTemp3(String temp3) {
        this.temp3 = temp3;
    }

    public String getTemp4() {
        return temp4;
    }

    public void setTemp4(String temp4) {
        this.temp4 = temp4;
    }

    public String getTemp5() {
        return temp5;
    }

    public void setTemp5(String temp5) {
        this.temp5 = temp5;
    }

}
