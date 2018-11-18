/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * unicode编码转换
 * 
 * @author qiyuanyuan
 */
public class StringUtil {
    /**
     * 无参构造方法
     * */
    private StringUtil() {
    }

    /**
     * 有参构照方法
     * */
    public static String fromUnicode(String str) {

        return fromUnicode(str.toCharArray(), 0, str.length(), new char[1024]);

    }

    /**
     * 将unicode编码转换成String Converts encoded &#92;uxxxx1 to unicode chars
     * 
     * and changes special saved chars to their original forms
     */

    public static String fromUnicode(char[] in, int off, int len,
            char[] convtBuf) {
        char[] convtBufNew = convtBuf;
        if (convtBufNew.length < len) {

            int newLen = len * 2;

            if (newLen < 0) {

                newLen = Integer.MAX_VALUE;

            }

            convtBufNew = new char[newLen];

        }

        char aChar;

        char[] out = convtBufNew;

        int outLen = 0;

        int end = off + len;

        while (off < end) {

            aChar = in[off++];

            if (aChar == '\\') {

                aChar = in[off++];

                if (aChar == 'u') {

                    // Read the xxxx

                    int value = 0;

                    for (int i = 0; i < 4; i++) {

                        aChar = in[off++];

                        switch (aChar) {

                        case '0':

                        case '1':

                        case '2':

                        case '3':

                        case '4':

                        case '5':

                        case '6':

                        case '7':

                        case '8':

                        case '9':

                            value = (value << 4) + aChar - '0';

                            break;

                        case 'a':

                        case 'b':

                        case 'c':

                        case 'd':

                        case 'e':

                        case 'f':

                            value = (value << 4) + 10 + aChar - 'a';

                            break;

                        case 'A':

                        case 'B':

                        case 'C':

                        case 'D':

                        case 'E':

                        case 'F':

                            value = (value << 4) + 10 + aChar - 'A';

                            break;

                        default:

                            throw new IllegalArgumentException(

                            "Malformed \\uxxxx encoding.");

                        }

                    }

                    out[outLen++] = (char) value;

                } else {

                    if (aChar == 't') {

                        aChar = '\t';

                    } else if (aChar == 'r') {

                        aChar = '\r';

                    } else if (aChar == 'n') {

                        aChar = '\n';

                    } else if (aChar == 'f') {

                        aChar = '\f';

                    }

                    out[outLen++] = aChar;

                }

            } else {

                out[outLen++] = (char) aChar;

            }

        }

        return new String(out, 0, outLen);

    }

    /**
     * 手机号验证
     * add by luyong
     * @param  str
     * @return 验证通过返回true
     */
    public static boolean isMobile(String str) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        p = Pattern.compile("^[1][3,4,7,5,8][0-9]{9}$"); // 验证手机号
        m = p.matcher(str);
        b = m.matches();
        return b;
    }

}
