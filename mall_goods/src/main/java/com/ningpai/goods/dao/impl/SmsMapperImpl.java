/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.goods.dao.impl;

import org.springframework.stereotype.Repository;
import com.ningpai.goods.bean.Sms;
import com.ningpai.goods.dao.SmsMapper;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * @see com.ningpai.common.dao.SmsMapper
 * @author NINGPAI-zhangqiang
 * @since 2014年4月25日 下午5:45:08
 * @version 0.0.1
 */
@Repository("smsMapperSites")
public class SmsMapperImpl extends BasicSqlSupport implements SmsMapper {
    /**
     * 查询短信信息
     *
     * @return BasicEmailServer
     */
    @Override
    public Sms selectSms() {
        return this.selectOne("com.ningpai.goods.dao.SmsMapper.selectSms");
    }

}
