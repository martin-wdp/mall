/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.util;

import java.util.Random;

/**
 * 随机数
 * 
 * @author ggn
 *
 */
public class RanddomMath {
    /**
     * 产生随机字符串
     * */
    private static Random randGen = null;
    /**
     * 数字和字母
     * */
    private static char[] numbersAndLetters = null;

    private RanddomMath() {
    }

    /**
     * 字符串
     * 
     * @param length
     * @return String
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
