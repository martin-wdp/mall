/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.util;

import java.math.BigDecimal;

/**
 * 计算星星评分用
 * 
 * @author Zhangsl
 *
 */
public final class CalcuStar {

    /**
     * 获取评分
     *
     * @param n
     * @return
     */
    public static BigDecimal getNumber(BigDecimal n) {
        // 将BigDecimal转化成String类型
        String temp = n.toString();
        // 根据小数点进行分割
        String[] tempStr = temp.split("\\.");
        // 小数点前面的数字
        int beforeNumber = Integer.parseInt(tempStr[0]);
        // 小数点后面的数字
        int afterNumber = Integer.parseInt(tempStr[1]);
        // 因为样式限制，如果小数小于5且不等于0，小数位显示为0
        if (afterNumber < 5 && afterNumber != 0) {
            temp = beforeNumber + ".0";
            return new BigDecimal(temp);
            // 如果小数大于5且不等于0 ，小数位显示为5
            // 如果小数是5或者0就正常显示
        } else if (afterNumber > 5 && afterNumber != 0) {
            temp = beforeNumber + ".5";
            return new BigDecimal(temp);
        }

        return n;
    }
}
