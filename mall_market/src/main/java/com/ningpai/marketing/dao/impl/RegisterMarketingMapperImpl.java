package com.ningpai.marketing.dao.impl;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.marketing.bean.RegisterMarketing;
import com.ningpai.marketing.dao.RegisterMarketingMapper;
import com.qpmall.marketing.bean.RegisterCoupon;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 注册营销实现类 Created by Zhoux on 2015/4/1.
 */
@Repository("RegisterMarketingMapper")
public class RegisterMarketingMapperImpl extends BasicSqlSupport implements
        RegisterMarketingMapper {

    /**
     * 查询注册营销信息
     * 
     * @return
     */
    @Override
    public RegisterMarketing findRegisterMarketing() {
        RegisterMarketing registerMarketing = null;
        List<RegisterMarketing> blist = this
                .selectList("com.ningpai.web.dao.RegisterMarketingMapper.findRegisterMarketing");
        if (blist.isEmpty()) {
            registerMarketing = new RegisterMarketing();
            registerMarketing.setId(1L);
            this.insert(
                    "com.ningpai.web.dao.RegisterMarketingMapper.insertSelective",
                    registerMarketing);
        } else {
            registerMarketing = blist.get(0);
        }
//        EditBy 付陈林 Date 2015年12月3日。
//        增加了对多个优惠券的处理，在这里获取所有的优惠券
//        获取所有的优惠券
        List<RegisterCoupon> rclist = this
                .selectList("com.ningpai.web.dao.RegisterCouponMapper.findRegisterCoupon");
        registerMarketing.setRcList(rclist);
        //Edit END
        return registerMarketing;
    }

    /**
     * 更新注册营销信息
     * 
     * @param registerMarketing
     * @return
     */
    @Override
    public int updateRegisterCoupon(RegisterMarketing registerMarketing) {
//        edit by 付陈林   增加对优惠劵列表的处理  start
//        删除所有的优惠劵
        this.delete("com.ningpai.web.dao.RegisterCouponMapper.deleteRegisterCoupon");
//        增加目前选择的优惠劵
        if(registerMarketing.getRcList().size()>0){
            this.insert("com.ningpai.web.dao.RegisterCouponMapper.insertRegisterCoupon",registerMarketing.getRcList());

        }
        //edit end
        return this
                .update("com.ningpai.web.dao.RegisterMarketingMapper.updateRegisterCoupon",
                        registerMarketing);
    }

}
