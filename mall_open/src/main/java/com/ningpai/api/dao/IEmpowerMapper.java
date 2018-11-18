package com.ningpai.api.dao;

import com.ningpai.api.bean.OEmpower;

import java.util.Map;

/**
 * 开放平台--获取秘钥
 * @author lih
 * @version 2.0
 * @since 15/9/1
 */
public interface IEmpowerMapper {

    /**
     * 获取秘钥
     * @param  appUserName 用户账号
     * @return 参与签名用到的秘钥
     */
     OEmpower getKey(String appUserName);

    /**
     * 修改开放角色的token
     * @param map 用户名
     * @return 修改结果
     */
     int updateEmpower( Map<String,Object> map);
}
