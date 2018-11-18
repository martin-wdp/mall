package com.ningpai.api.service;

/**
 * 开放接口--获取秘钥
 * @author lih
 * @version 2.0
 * @since 15/9/1
 */
public interface IEmpowerService {

    /**
     * 获取秘钥
     * @return 参与签名所用到的key
     */
    String getKey(String appUserName,String sign,String timestamp);
}
