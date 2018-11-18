/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.site.threepart.dao;

import com.ningpai.site.threepart.bean.ThreePart;

/**
 * @author ggn
 * 
 */
public interface ThreePartMapper {

    /**
     * 查询第三方
     * 
     * @param openid
     * @return ThreePart
     */
    ThreePart selectThreePart(String openid);

    /**
     * 插入
     * 
     * @param tp
     * @return int
     */
    int insertThreePart(ThreePart tp);

}
