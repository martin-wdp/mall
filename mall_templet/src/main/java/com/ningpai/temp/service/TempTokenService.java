/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.temp.service;

import com.ningpai.temp.bean.TempToken;

/**
 * SERVICE-模板内容变更token
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年8月6日下午8:18:44
 */
public interface TempTokenService {

    /**
     * 添加token
     * 
     * @param token
     * @return
     */
    int createToken(TempToken token);

    /**
     * 修改token
     * 
     * @param token
     * @return
     */
    int updateToken(TempToken token);

    /**
     * 删除token
     * 
     * @param token
     * @return
     */
    int deleteToken(Long tokenId);

    /**
     * 获取当前token
     * 
     * @return
     */
    TempToken getCurrToken();

    /**
     * 根据类型，获取首页内容变更token
     * 
     * @param temp1
     * @return
     */
    TempToken selectTokenByType(String temp1);
    
    /**
     * 修改字段token的值
     * @param token
     * @return
     */
    int updateTokenValue(TempToken token);
}
