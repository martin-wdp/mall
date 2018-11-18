package com.qpmall.address.bean;

import java.util.Date;

/**
 * Created by ly-qpmall on 2016/1/9.
 */
public class AddressBean {
    /**
     *  省 市 区 ID
     */
    private Long addressId;
    /**
     *  省 市 区 Name
     */
    private String addressName;
    /**
     *  省 市 区 排序
     */
    private String addressSort;
    /**
     *  删除标记
     */
    private String delFlag;
    /**
     *  创建时间
     */
    private Date createTime;
    /**
     *  删除时间
     */
    private String modifyTime;

    /**
     *  删除时间
     */
    private Long parentId;


    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public String getAddressSort() {
        return addressSort;
    }

    public void setAddressSort(String addressSort) {
        this.addressSort = addressSort;
    }
}
