/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.util;

/**
 * 操作系统判断工具类
 * @author guoguangnan
 * 
 */
public final class OSinfo {

    /** */
    private static String os = System.getProperty("os.name").toLowerCase();
    /** */
    private static OSinfo _instance = new OSinfo();
    /** */
    private EPlatform platform;

    private OSinfo() {
    }

    /**
     * 判断是否linux
     * @return true是 或false不是
     */
    public static boolean isLinux() {
        return os.indexOf("linux") >= 0;
    }
    /**
     *判断是否mac
     * @return true是 或false不是
     */
    public static boolean isMacOS() {
        return os.indexOf("mac") >= 0 && os.indexOf("os") > 0
                && os.indexOf("x") < 0;
    }
    /**
     *判断是否MacOSX
     * @return true是 或false不是
     */
    public static boolean isMacOSX() {
        return os.indexOf("mac") >= 0 && os.indexOf("os") > 0
                && os.indexOf("x") > 0;
    }
    /**
     *判断是否windows
     * @return true是 或false不是
     */
    public static boolean isWindows() {
        return os.indexOf("windows") >= 0;
    }
    /**
     *判断是否os/2
     * @return true是 或false不是
     */
    public static boolean isOS2() {
        return os.indexOf("os/2") >= 0;
    }
    /**
     *判断是否solaris
     * @return true是 或false不是
     */
    public static boolean isSolaris() {
        return os.indexOf("solaris") >= 0;
    }
    /**
     *判断是否sunos
     * @return true是 或false不是
     */
    public static boolean isSunOS() {
        return os.indexOf("sunos") >= 0;
    }
    /**
     *判断是否mpe/ix
     * @return true是 或false不是
     */
    public static boolean isMPEiX() {
        return os.indexOf("mpe/ix") >= 0;
    }
    /**
     *判断是否hp-ux
     * @return true是 或false不是
     */
    public static boolean isHPUX() {
        return os.indexOf("hp-ux") >= 0;
    }
    /**
     *判断是否aix
     * @return true是 或false不是
     */
    public static boolean isAix() {
        return os.indexOf("aix") >= 0;
    }
    /**
     *判断是否os/390
     * @return true是 或false不是
     */
    public static boolean isOS390() {
        return os.indexOf("os/390") >= 0;
    }
    /**
     *判断是否freebsd
     * @return true是 或false不是
     */
    public static boolean isFreeBSD() {
        return os.indexOf("freebsd") >= 0;
    }
    /**
     *判断是否irix
     * @return true是 或false不是
     */
    public static boolean isIrix() {
        return os.indexOf("irix") >= 0;
    }
    /**
     *判断是否digital
     * @return true是 或false不是
     */
    public static boolean isDigitalUnix() {
        return os.indexOf("digital") >= 0 && os.indexOf("unix") > 0;
    }
    /**
     *判断是否netware
     * @return true是 或false不是
     */
    public static boolean isNetWare() {
        return os.indexOf("netware") >= 0;
    }
    /**
     *判断是否osf1
     * @return true是 或false不是
     */
    public static boolean isOSF1() {
        return os.indexOf("osf1") >= 0;
    }
    /**
     *判断是否 openvms
     * @return true是 或false不是
     */
    public static boolean isOpenVMS() {
        return os.indexOf("openvms") >= 0;
    }

    /**
     * 获取操作系统名字
     * 
     * @return true是 或false不是 操作系统名
     */
    public static EPlatform getOSname() {
        if (isAix()) {
            _instance.platform = EPlatform.AIX;
        } else if (isDigitalUnix()) {
            _instance.platform = EPlatform.Digital_Unix;
        } else if (isFreeBSD()) {
            _instance.platform = EPlatform.FreeBSD;
        } else if (isHPUX()) {
            _instance.platform = EPlatform.HP_UX;
        } else if (isIrix()) {
            _instance.platform = EPlatform.Irix;
        } else if (isLinux()) {
            _instance.platform = EPlatform.Linux;
        } else if (isMacOS()) {
            _instance.platform = EPlatform.Mac_OS;
        } else if (isMacOSX()) {
            _instance.platform = EPlatform.Mac_OS_X;
        } else if (isMPEiX()) {
            _instance.platform = EPlatform.MPEiX;
        } else if (isNetWare()) {
            _instance.platform = EPlatform.NetWare_411;
        } else if (isOpenVMS()) {
            _instance.platform = EPlatform.OpenVMS;
        } else if (isOS2()) {
            _instance.platform = EPlatform.OS2;
        } else if (isOS390()) {
            _instance.platform = EPlatform.OS390;
        } else if (isOSF1()) {
            _instance.platform = EPlatform.OSF1;
        } else if (isSolaris()) {
            _instance.platform = EPlatform.Solaris;
        } else if (isSunOS()) {
            _instance.platform = EPlatform.SunOS;
        } else if (isWindows()) {
            _instance.platform = EPlatform.Windows;
        } else {
            _instance.platform = EPlatform.Others;
        }
        return _instance.platform;
    }

}
