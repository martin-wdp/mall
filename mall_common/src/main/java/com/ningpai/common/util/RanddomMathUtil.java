/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.common.util;

import java.util.Random;

/**
 * 生产随机数
 * 
 * @author AthrunNatu
 * @since 2013年11月20日上午9:25:08
 */
public final class RanddomMathUtil {

    private static Random randGen = null;
    private static char[] numbersAndLetters = null;

    private RanddomMathUtil() {
        super();
    }

    /**
     * 生产随机数
     * 
     * @param length
     *            随机数长度
     * @return String 生产好的随机数
     */
    public static final String randomString(int length) {
        if (length < 1) {
            return null;
        }
        if (randGen == null) {
            randGen = new Random();
            numbersAndLetters = ("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();
        }
        char[] randBuffer = new char[length];
        for (int i = 0; i < randBuffer.length; i++) {
            randBuffer[i] = numbersAndLetters[randGen.nextInt(35)];
        }
        return new String(randBuffer);
    }

}
