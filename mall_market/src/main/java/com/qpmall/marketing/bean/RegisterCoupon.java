package com.qpmall.marketing.bean;
/**
 * Created with com.qpmall.marketing.bean
 * Author:付陈林
 * Date:  15/12/3
 * Time:  下午1:18
 * Email: fuchenlin@live.com
 * 存放注册营销中的优惠劵
 */
public class RegisterCoupon {
    /**
     * id
     * */
    private Long id;

    /**
     * 注册营销id
     * */
    private Long registerId;
    /**
     * 优惠劵id
     * */
    private Long couponId;

    /**
     * 优惠劵名称
     * */
    private String couponName;

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCouponId() {
        return couponId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }
    public Long getRegisterId() {
        return registerId;
    }

    public void setRegisterId(Long registerId) {
        this.registerId = registerId;
    }
 }
