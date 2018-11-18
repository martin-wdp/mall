/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.coupon.bean;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 优惠券信息
 * 
 * @author NINGPAI-LIH 2014-03-19
 * 
 */
public class Coupon {
    /**
     * 优惠券ID
     */
    private Long couponId;
    /**
     * 优惠券名称
     */
    @Length(min = 2, max = 50, message = "优惠劵名称在2到50字符之间")
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+")
    @NotNull
    private String couponName;
    /**
     * 优惠券备注
     */
    private String couponRemark;
    /**
     * 开始时间
     */
    private Date couponStartTime;
    /**
     * 结束时间
     */
    private Date couponEndTime;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date modifyTime;
    /**
     * 删除标记
     */
    private String delFlag;
    /**
     * 商家ID
     */
    private Long businessId;
    /**
     * 商家名称
     */
    private String thirdName;
    /**
     * 兑换所需积分
     */
    private Long couponIntegral;
    /**
     * 是否发布
     */
    private String couponIsShow;
    /**
     * 图片
     */
    private String couponPic;
    /**
     * 领取张数
     */
    private Long couponGetNo;
    /**
     * 已领取张数
     */
    private Long couponGettedNo;

    /**
     * 生成多少张
     */
    @NotNull
    private Integer couponCount;

    /**
     * 规则类型 1直降 2满减 3 不受限制
     */
    @NotNull
    @Pattern(regexp = "^[0-9]*$", message = "必须为数字")
    private String couponRulesType;

    /**
     * 直降
     */
    private CouponStraightDown couponStraightDown;

    /**
     * 满减
     */
    private CouponFullReduction couponFullReduction;
    /**
     * 商品集合
     */
    private List<Object> goodList;

    /**
     * 活动站点 0 平台 1 移动端 2 全部
     */
    private String activeSiteType;

    /**
     * 优惠券面额
     */
    private BigDecimal reductionPrice;

    /**
     * 领用方式 0：领取券 1：发放
     */
    private String couponGetType;

    /**
     * 是否全场促销
     */
    private String isAll;

    /**
     * 券码ID
     */
    private Long codeId;
    /**
     * 券码号
     */
    private String codeNo;
    /**
     * 用户ID
     */
    private Long customerId;
    /**
     * 券码状态 0正常可领取 1已经领取可使用 2已经使用 3过期作废
     */
    private String codeStatus;
    /**
     * 领取方式 0积分兑换 1购买赠送
     */
    private String codeGetType;
    /**
     * 领取时间
     */
    private Date codeGetTime;
    /**
     * 所使用的订单编号
     */
    private Long codeUseOrderId;
    /**
     * 分类范围
     */
    private List<Object> gclist;
    /**
     * 品牌范围
     */
    private List<Object> gblist;
    /**
     * 商品范围
     */
    private List<Object> gplist;
    /**
     * 用户已领取的张数
     */
    private int receivedCount;
    /**
     * 剩余的总张数
     */
    private int remainderCount;

    /**
     * 优惠券中间关联对象
     */
    private List<CouponRange> couponrangList;


    public int getReceivedCount() {
        return receivedCount;
    }

    public void setReceivedCount(int receivedCount) {
        this.receivedCount = receivedCount;
    }

    public int getRemainderCount() {
        return remainderCount;
    }

    public void setRemainderCount(int remainderCount) {
        this.remainderCount = remainderCount;
    }

    /**
     * GoodsList
     * 
     * @return
     */
    public List<Object> getGoodList() {
        return goodList;
    }

    /**
     * GoodsList
     * 
     * @param goodList
     */
    public void setGoodList(List<Object> goodList) {
        this.goodList = goodList;
    }

    /**
     * 商家名称
     * 
     * @param thirdName
     */
    public void setThirdName(String thirdName) {
        this.thirdName = thirdName;
    }

    /**
     * 商家名称
     * 
     * @return
     */
    public String getThirdName() {
        return this.thirdName;
    }

    /**
     * Get
     * 
     * @return List
     */
    public List<CouponRange> getCouponrangList() {
        return couponrangList;
    }

    /**
     * Set
     * 
     * @param couponrangList
     */
    public void setCouponrangList(List<CouponRange> couponrangList) {
        this.couponrangList = couponrangList;
    }

    public List<Object> getGclist() {
        return gclist;
    }

    public void setGclist(List<Object> gclist) {
        this.gclist = gclist;
    }

    public List<Object> getGblist() {
        return gblist;
    }

    public void setGblist(List<Object> gblist) {
        this.gblist = gblist;
    }

    public List<Object> getGplist() {
        return gplist;
    }

    public void setGplist(List<Object> gplist) {
        this.gplist = gplist;
    }

    public Long getCodeId() {
        return codeId;
    }

    public void setCodeId(Long codeId) {
        this.codeId = codeId;
    }

    public String getCodeNo() {
        return codeNo;
    }

    public void setCodeNo(String codeNo) {
        this.codeNo = codeNo;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCodeStatus() {
        return codeStatus;
    }

    public void setCodeStatus(String codeStatus) {
        this.codeStatus = codeStatus;
    }

    public String getCodeGetType() {
        return codeGetType;
    }

    public void setCodeGetType(String codeGetType) {
        this.codeGetType = codeGetType;
    }

    /**
     * 时间
     */
    public Date getCodeGetTime() {
        if (this.codeGetTime != null) {
            return new Date(this.codeGetTime.getTime());
        }
        return null;
    }

    /**
     * 时间
     */
    public void setCodeGetTime(Date codeGetTime) {
        if (codeGetTime != null) {
            Date tEmp = (Date) codeGetTime.clone();
            if (tEmp != null) {
                this.codeGetTime = tEmp;
            }
        }
    }

    public Long getCodeUseOrderId() {
        return codeUseOrderId;
    }

    public void setCodeUseOrderId(Long codeUseOrderId) {
        this.codeUseOrderId = codeUseOrderId;
    }

    public CouponStraightDown getCouponStraightDown() {
        return couponStraightDown;
    }

    public void setCouponStraightDown(CouponStraightDown couponStraightDown) {
        this.couponStraightDown = couponStraightDown;
    }

    public CouponFullReduction getCouponFullReduction() {
        return couponFullReduction;
    }

    public void setCouponFullReduction(CouponFullReduction couponFullReduction) {
        this.couponFullReduction = couponFullReduction;
    }

    public String getCouponRulesType() {
        return couponRulesType;
    }

    public void setCouponRulesType(String couponRulesType) {
        this.couponRulesType = couponRulesType;
    }

    public Integer getCouponCount() {
        return couponCount;
    }

    public void setCouponCount(Integer couponCount) {
        this.couponCount = couponCount;
    }

    public Long getCouponId() {
        return couponId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public String getCouponRemark() {
        return couponRemark;
    }

    public void setCouponRemark(String couponRemark) {
        this.couponRemark = couponRemark;
    }

    /**
     * 时间
     */
    public Date getCouponStartTime() {
        if (this.couponStartTime != null) {
            return new Date(this.couponStartTime.getTime()); // 正确值
        } else {
            return null;
        }
    }

    /**
     * 时间
     */
    public void setCouponStartTime(Date couponStartTime) {
        if (couponStartTime != null) {
            Date tEmp = (Date) couponStartTime.clone();
            if (tEmp != null) {
                this.couponStartTime = tEmp;
            }
        }
    }

    /**
     * 时间
     */
    public Date getCouponEndTime() {
        if (this.couponEndTime != null) {
            return new Date(this.couponEndTime.getTime()); // 正确值
        } else {
            return null;
        }
    }

    /**
     * 时间
     */
    public void setCouponEndTime(Date couponEndTime) {
        if (couponEndTime != null) {
            Date tEmp = (Date) couponEndTime.clone();
            if (tEmp != null) {
                this.couponEndTime = tEmp;
            }
        }
    }

    /**
     * 时间
     */
    public Date getCreateTime() {
        if (this.createTime != null) {
            return new Date(this.createTime.getTime()); // 正确值
        } else {
            return null;
        }
    }

    /**
     * 时间
     */
    public void setCreateTime(Date createTime) {
        if (createTime != null) {
            Date tEmp = (Date) createTime.clone();
            if (tEmp != null) {
                this.createTime = tEmp;
            }
        }
    }

    /**
     * 时间
     */
    public Date getModifyTime() {
        if (this.modifyTime != null) {
            return new Date(this.modifyTime.getTime());
        }
        return null;
    }

    /**
     * 时间
     */
    public void setModifyTime(Date modifyTime) {
        if (modifyTime != null) {
            Date tEmp = (Date) modifyTime.clone();
            if (tEmp != null) {
                this.modifyTime = tEmp;
            }
        }
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    public Long getCouponIntegral() {
        return couponIntegral;
    }

    public void setCouponIntegral(Long couponIntegral) {
        this.couponIntegral = couponIntegral;
    }

    public String getCouponIsShow() {
        return couponIsShow;
    }

    public void setCouponIsShow(String couponIsShow) {
        this.couponIsShow = couponIsShow;
    }

    public String getCouponPic() {
        return couponPic;
    }

    public void setCouponPic(String couponPic) {
        this.couponPic = couponPic;
    }

    public Long getCouponGetNo() {
        return couponGetNo;
    }

    public void setCouponGetNo(Long couponGetNo) {
        this.couponGetNo = couponGetNo;
    }

    public Long getCouponGettedNo() {
        return couponGettedNo;
    }

    public void setCouponGettedNo(Long couponGettedNo) {
        this.couponGettedNo = couponGettedNo;
    }

    public String getActiveSiteType() {
        return activeSiteType;
    }

    public void setActiveSiteType(String activeSiteType) {
        this.activeSiteType = activeSiteType;
    }

    public BigDecimal getReductionPrice() {
        return reductionPrice;
    }

    public void setReductionPrice(BigDecimal reductionPrice) {
        this.reductionPrice = reductionPrice;
    }

    public String getCouponGetType() {
        return couponGetType;
    }

    public void setCouponGetType(String couponGetType) {
        this.couponGetType = couponGetType;
    }

    public String getIsAll() {
        return isAll;
    }

    public void setIsAll(String isAll) {
        this.isAll = isAll;
    }
}
