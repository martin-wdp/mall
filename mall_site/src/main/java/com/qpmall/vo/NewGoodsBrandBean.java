package com.qpmall.vo;

import com.ningpai.goods.bean.GoodsBrand;

/**
 * Created by pl on 2015/10/15.
 * Desc:
 */
public class NewGoodsBrandBean {
    public GoodsBrand goodsBrand;
    public String brandNameInint;

    public void setBrandNameInint(String brandNameInint) {
        this.brandNameInint = brandNameInint;
    }

    public void setGoodsBrand(GoodsBrand goodsBrand) {
        this.goodsBrand = goodsBrand;
    }

    public GoodsBrand getGoodsBrand() {
        return goodsBrand;
    }

    public String getBrandNameInint() {
        return brandNameInint;
    }
}
