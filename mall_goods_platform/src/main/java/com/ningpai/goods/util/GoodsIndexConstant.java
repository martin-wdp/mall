package com.ningpai.goods.util;

import com.ningpai.goods.bean.EsGoodsInfo;

/**
 * <p>
 * 索引常量
 * </p>
 * 
 * @author liangck
 * @version 1.0
 * @since 15/8/19 10:38
 */
public class GoodsIndexConstant {

    /**
     * 商品索引名
     */
    public static final String PRODUCT_INDEX_NAME = "qm-test-item";

    /**
     * 商品类型名
     */
    public static final String PRODUCT_TYPE = EsGoodsInfo.class.getSimpleName();

    /**
     * 构造
     */
    private GoodsIndexConstant(){

    }


}
