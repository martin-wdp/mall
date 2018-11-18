/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.smscommon.dao.impl;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.smscommon.bean.Sms;
import com.ningpai.smscommon.dao.SmsMapper;

/**
 * @see com.ningpai.smscommon.dao.SmsMapper
 * @author NINGPAI-zhangqiang
 * @since 2014年4月25日 下午5:45:08
 * @version 0.0.1
 */
@Repository("smsMapperCore")
public class SmsMapperImpl extends BasicSqlSupport implements SmsMapper {
    /*
     * 
     * 
     * @see com.ningpai.common.dao.SmsMapper#selectSms()
     */
    @Override
    public Sms selectSms() {
        return this.selectOne("com.ningpai.smscommon.dao.SmsMapper.selectSms");
    }

}
