/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.site.groupon.bean;

import com.ningpai.marketing.bean.Marketing;
import com.ningpai.site.goods.vo.GoodsProductVo;

/**
 * 团购工具类
 * 
 * @author NingPai-Pro
 * 
 */
public class GrouponUtil {

    /**
     * 团购商品
     */
    private GoodsProductVo goodsProductVo;

    /**
     * 团购优惠信息
     */
    private Marketing marketing;

    public GoodsProductVo getGoodsProductVo() {
        return goodsProductVo;
    }

    public void setGoodsProductVo(GoodsProductVo goodsProductVo) {
        this.goodsProductVo = goodsProductVo;
    }

    public Marketing getMarketing() {
        return marketing;
    }

    public void setMarketing(Marketing marketing) {
        this.marketing = marketing;
    }

}
