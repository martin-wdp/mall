package com.ningpai.goods.dao;

import com.ningpai.goods.bean.Sms;

;

/**
 * 邮箱服务器数据接口层
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2014年1月14日 上午11:54:59
 * @version 1.0
 */
public interface SmsMapper {

    /**
     * 查询短信信息
     * 
     * @return BasicEmailServer
     */
    Sms selectSms();
}
