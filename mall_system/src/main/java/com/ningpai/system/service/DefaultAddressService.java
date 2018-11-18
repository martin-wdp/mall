/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.system.service;



/**
 * 默认地址
 * @author NINGPAI-LIH
 * @since 2015年1月10日10:58:32
 */
public interface DefaultAddressService {

    
    /**
     * 插入默认地址
     * @param provinceDefault 地址信息
     * @return
     */
    public int insertAddressDefaultService(Long districtId);
    
    /**
     * 获取默认地址id
     * @return 
     */
    public Long getDefaultIdService();
}
