package com.ningpai.third.register.mapper.impl;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.third.register.bean.Sms;
import com.ningpai.third.register.mapper.RegisterMapper;
import org.springframework.stereotype.Repository;
/**
 * 商家注册 获取手机验证码
 * Created by zhanghailong on 2015/5/7.
 */
@Repository("registerMapper")
public class RegisterMapperImpl extends BasicSqlSupport implements RegisterMapper {

    /**
     * 查询短信信息
     * @return Sms
     */
    @Override
    public Sms selectSms() {
        return this.selectOne("com.ningpai.third.seller.mapper.SotreInfoMapper.selectSms");
    }
}
