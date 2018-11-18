/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.site.goods.service;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 浏览记录Service
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年4月17日 下午2:23:06
 * @version 1.0
 */
public interface BrowerService {
    /**
     * 保存浏览记录
     * 
     * @param request
     *            请求对象
     * @param productId
     *            货品ID
     * @return 插入是否成功的标记 true 成功 false 失败
     * @throws UnsupportedEncodingException
     */
    boolean saveBrowerHis(HttpServletRequest request, HttpServletResponse response, Long productId) throws UnsupportedEncodingException;

    /**
     * 获取浏览的历史记录
     * 
     * @param request
     *            请求对象
     * @return 查询到的封装的Map
     */
    Map<String, Object> loadBrowHist(HttpServletRequest request);
}
