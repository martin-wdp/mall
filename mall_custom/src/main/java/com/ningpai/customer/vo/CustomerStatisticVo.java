/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.customer.vo;

import java.util.List;

import com.ningpai.other.bean.CustomerAllInfo;
import com.ningpai.other.bean.ProvinceBean;

/**
 * 会员统计vo
 * @author jiping 
 * @since 2015年2月3日 下午5:03:04 
 * @version 0.0.1
 */
public class CustomerStatisticVo extends CustomerAllInfo{
    /*
     * 序列化
     */
    private static final long serialVersionUID = 7221989306837681710L;

    //会员数
    private Long countSum;

    //时间段
    private String ctime;

    //地区
    private Long address;
    
    //地区集合
    private List<ProvinceBean> provinceList;
    
    public List<ProvinceBean> getProvinceList() {
        return provinceList;
    }

    public void setProvinceList(List<ProvinceBean> provinceList) {
        this.provinceList = provinceList;
    }

    public Long getAddress() {
        return address;
    }

    public void setAddress(Long address) {
        this.address = address;
    }

    public Long getCountSum() {
        return countSum;
    }

    public void setCountSum(Long countSum) {
        this.countSum = countSum;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }
    
    
}
