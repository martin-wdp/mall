/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.site.goods.vo;

import com.ningpai.goods.bean.GoodsBrand;

/**
 * 类型关联品牌类VO
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月21日 下午1:35:28
 * @version
 */
public class GoodsTypeBrandVo {
    // 主键ID
    private Long typeBrandId;

    // 商品类型的主键ID
    private Long typeId;

    // 删除标记 0：未删除 1：已删除 默认
    private String delflag;

    // 商品品牌
    private GoodsBrand brand;

    public String getDelflag() {
        return delflag;
    }

    public void setDelflag(String delflag) {
        this.delflag = delflag;
    }

    public Long getTypeBrandId() {
        return typeBrandId;
    }

    public void setTypeBrandId(Long typeBrandId) {
        this.typeBrandId = typeBrandId;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public GoodsBrand getBrand() {
        return brand;
    }

    public void setBrand(GoodsBrand brand) {
        this.brand = brand;
    }

}
