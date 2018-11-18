/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.site.goods.util;

/**
 * 面包屑跳转辅助Bean
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年7月8日 下午3:52:48
 * @version 1.0
 */
public class NavUtil {
    // 第一季分类ID
    private String firstCatId;
    // 第三级分类ID
    private String thirdCatId;

    public String getFirstCatId() {
        return firstCatId;
    }

    public void setFirstCatId(String firstCatId) {
        this.firstCatId = firstCatId;
    }

    public String getThirdCatId() {
        return thirdCatId;
    }

    public void setThirdCatId(String thirdCatId) {
        this.thirdCatId = thirdCatId;
    }

}
