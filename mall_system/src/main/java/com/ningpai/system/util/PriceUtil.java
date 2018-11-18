/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.system.util;

import java.math.BigDecimal;

import com.ningpai.system.bean.PriceScale;
import com.ningpai.system.bean.PriceScale.RoundType;

/**
 * 价格精度-工具类
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年02月27日 17:37:38
 * @version
 */
public final class PriceUtil {

    public static final PriceScale PRICESCALE = new PriceScale(2, RoundType.roundHalfUp);

    private PriceUtil() {
    }

    /**
     * 获取精度处理后的价格
     * 
     */
    public static BigDecimal getPriceScaleBigDecimal(BigDecimal price) {
        if (PRICESCALE.getPriceRoundType() == RoundType.roundHalfUp) {
            return price.setScale(PRICESCALE.getPriceScale(), BigDecimal.ROUND_HALF_UP);
        } else if (PRICESCALE.getPriceRoundType() == RoundType.roundUp) {
            return price.setScale(PRICESCALE.getPriceScale(), BigDecimal.ROUND_UP);
        } else {
            return price.setScale(PRICESCALE.getPriceScale(), BigDecimal.ROUND_DOWN);
        }
    }
}
