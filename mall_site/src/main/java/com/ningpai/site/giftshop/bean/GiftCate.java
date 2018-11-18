package com.ningpai.site.giftshop.bean;

import java.util.Date;
import java.util.List;

/**
 * 赠品分类
 * 
 * @author qiyuanyuan
 *
 */
public class GiftCate {

    // 赠品分类ID
    private Long giftCateId;
    // 分类名称
    private String giftCateName;
    // 赠品备注
    private String giftCateRemark;
    // 创建时间
    private Date createTime;
    // 修改时间
    private Date modifyTime;
    // 删除标记 0 正常 1删除
    private String delFlag;
    // 商家ID
    private Long busniessId;
    // 父类ID
    private Long giftParentId;

    //赠品分类集合
    private List<GiftCate> cateVos;

    // 父分类
    private GiftCate parentCate;

    public Long getGiftCateId() {
        return giftCateId;
    }

    public void setGiftCateId(Long giftCateId) {
        this.giftCateId = giftCateId;
    }

    public String getGiftCateName() {
        return giftCateName;
    }

    public void setGiftCateName(String giftCateName) {
        this.giftCateName = giftCateName;
    }

    public String getGiftCateRemark() {
        return giftCateRemark;
    }

    public void setGiftCateRemark(String giftCateRemark) {
        this.giftCateRemark = giftCateRemark;
    }


    /**
     * 获取创建时间
     * @return
     */
    public Date getCreateTime() {
        return (Date) createTime.clone();
    }

    /**
     * 设置创建时间
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime == null ? null : (Date) createTime.clone();
    }


    /**
     * 得到修改时间
     * @return
     */
    public Date getModifyTime() {
        return (Date) modifyTime.clone();
    }

    /**
     * 设置修改时间
     * @param modifyTime
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime == null ? null : (Date) modifyTime.clone();
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public Long getBusniessId() {
        return busniessId;
    }

    public void setBusniessId(Long busniessId) {
        this.busniessId = busniessId;
    }

    public Long getGiftParentId() {
        return giftParentId;
    }

    public void setGiftParentId(Long giftParentId) {
        this.giftParentId = giftParentId;
    }

    public List<GiftCate> getCateVos() {
        return cateVos;
    }

    public void setCateVos(List<GiftCate> cateVos) {
        this.cateVos = cateVos;
    }

    public GiftCate getParentCate() {
        return parentCate;
    }

    public void setParentCate(GiftCate parentCate) {
        this.parentCate = parentCate;
    }
}
