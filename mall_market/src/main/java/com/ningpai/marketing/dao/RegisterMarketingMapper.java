package com.ningpai.marketing.dao;

import com.ningpai.marketing.bean.RegisterMarketing;

/**
 * 注册营销dao Created by Zhoux on 2015/4/1.
 */
public interface RegisterMarketingMapper {

    /**
     * 查询注册营销信息
     * 
     * @return
     */
    RegisterMarketing findRegisterMarketing();

    /**
     * 更新注册营销信息
     * 
     * @param registerMarketing
     * @return
     */
    int updateRegisterCoupon(RegisterMarketing registerMarketing);

}
