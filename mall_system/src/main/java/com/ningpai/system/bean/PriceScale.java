/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.system.bean;

/**
 * 价格精度-实体类
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年02月27日 17:37:38
 * @version
 */
public class PriceScale {
    /**
     * 小数位精确方式<br>
     * roundHalfUp:四舍五入<br>
     * roundUp:向上取整<br>
     * roundDown:向下取整
     * 
     * @author NINGPAI-WangHaiYang
     */
    public enum RoundType {
        roundHalfUp, roundUp, roundDown
    }

    /**
     *  价格精度小数位数
     */
    private Integer priceScale;
    /**
     *  价格精度取整方式
     */
    private RoundType priceRoundType;

    /**
     * 无参构造函数
     */
    public PriceScale() {
    }

    /**
     * 带参构造函数
     * @param priceScale
     * @param priceRoundType
     */
    public PriceScale(Integer priceScale, RoundType priceRoundType) {
        this.priceScale = priceScale;
        this.priceRoundType = priceRoundType;
    }

    public Integer getPriceScale() {
        return priceScale;
    }

    public void setPriceScale(Integer priceScale) {
        this.priceScale = priceScale;
    }

    public RoundType getPriceRoundType() {
        return priceRoundType;
    }
    public void setPriceRoundType(RoundType priceRoundType) {
        this.priceRoundType = priceRoundType;
    }
}
