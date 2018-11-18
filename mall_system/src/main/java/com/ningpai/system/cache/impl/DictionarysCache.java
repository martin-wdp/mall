package com.ningpai.system.cache.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ningpai.system.cache.IDictionarysCache;
import com.ningpai.system.exception.NPException;
import com.ningpai.util.MyLogger;

/**
 * 自定义异常父类 负责创建异常和打印异常信息
 *
 * @author NINGPAI_xiaomm
 * @version V1.0
 * @Description:
 * @since 2014-03-19 16:01:06
 */
@Component("dictionarysCache")
public class DictionarysCache implements IDictionarysCache {

    /**
     * logger日志
     */
    private static final MyLogger LOGGER = new MyLogger(DictionarysCache.class);

    /**
     * 字典缓存map对象
     */
    private static final Map<String, List<DicItem>> DICTIONARYSCACHE = new HashMap<String, List<DicItem>>();

    /**
     * 常量sum1
     */
    private static final int SUM1 = 1;
    /**
     * 常量sum2
     */
    private static final int SUM2 = 1;
    /**
     * 常量sum3
     */
    private static final int SUM3 = 1;
    /**
     * 常量sum4
     */
    private static final int SUM4 = 1;
    /**
     * 常量sum5
     */
    private static final int SUM5 = 1;

    /**
     * 字典值缓存单个字典项
     *
     * @author XiaoMM
     */
    public static class DicItem {
        /**
         * id
         */
        private int id;
        /**
         * code
         */
        private String code;
        /**
         * 名字
         */
        private String name;

        /**
         * 有参构造
         */
        public DicItem(int id, String code, String name) {
            super();
            this.id = id;
            this.code = code;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    /**
     * 无参构造函数 作用：初始化参数
     */
    public DictionarysCache() {
        doInit();
    }

    /**
     * 初始化缓冲
     *
     * @return true初始化缓存成功，false初始化缓存失败
     * @author xmm
     * @createdate 2012-12-17 14:35
     */
    protected final boolean doInit() {
        synchronized (this) {
            if (DICTIONARYSCACHE != null) {
                LOGGER.info("正在初始化字典缓存...");
                // "sysDecimalDigits", 保留位数
                List<DicItem> sysDecimalDigits = new ArrayList<DicItem>();
                sysDecimalDigits.add(new DicItem(SUM1, "zsqz", "整数取整"));
                sysDecimalDigits.add(new DicItem(SUM2, "qzd1wxs", "取整到1位小数"));
                sysDecimalDigits.add(new DicItem(SUM3, "qzd2wxs", "取整到2位小数"));
                sysDecimalDigits.add(new DicItem(SUM4, "qzd3wxs", "取整到3位小数"));
                sysDecimalDigits.add(new DicItem(SUM5, "qzd4wxs", "取整到4位小数"));
                DICTIONARYSCACHE.put("sysDecimalDigits", sysDecimalDigits);
                // "sysGetIntegerStyle", 取整方式
                List<DicItem> sysGetIntegerStyle = new ArrayList<DicItem>();
                sysGetIntegerStyle.add(new DicItem(SUM1, "sswr", "四舍五入"));
                sysGetIntegerStyle.add(new DicItem(SUM2, "xsqz", "向上取整"));
                sysGetIntegerStyle.add(new DicItem(SUM3, "xxqz", "向下取整"));
                DICTIONARYSCACHE.put("sysGetIntegerStyle", sysGetIntegerStyle);
                // sysUpyunOpType 又拍云操作类型
                List<DicItem> sysUpyunOpType = new ArrayList<DicItem>();
                sysUpyunOpType.add(new DicItem(SUM1, "sc", "上传"));
                sysUpyunOpType.add(new DicItem(SUM2, "xz", "下载"));
                sysUpyunOpType.add(new DicItem(SUM3, "sl", "缩略"));
                sysUpyunOpType.add(new DicItem(SUM4, "xz", "旋转"));
                sysUpyunOpType.add(new DicItem(SUM4, "cj", "裁剪"));
                DICTIONARYSCACHE.put("sysUpyunOpType", sysUpyunOpType);
                // sysDicAdverIndex 广告位置
                List<DicItem> sysDicAdverIndex = new ArrayList<DicItem>();
                sysDicAdverIndex.add(new DicItem(SUM1, "top", "上"));
                sysDicAdverIndex.add(new DicItem(SUM2, "down", "下"));
                sysDicAdverIndex.add(new DicItem(SUM3, "left", "左"));
                sysDicAdverIndex.add(new DicItem(SUM4, "right", "右"));
                DICTIONARYSCACHE.put("sysDicAdverIndex", sysDicAdverIndex);
                // sysDicBarPos 导航位置
                List<DicItem> sysDicBarPos = new ArrayList<DicItem>();
                sysDicBarPos.add(new DicItem(SUM1, "head", "头部"));
                sysDicBarPos.add(new DicItem(SUM2, "bottom", "底部"));
                DICTIONARYSCACHE.put("sysDicBarPos", sysDicBarPos);
                // sysDicBarType 导航位置
                List<DicItem> sysDicBarType = new ArrayList<DicItem>();
                sysDicBarType.add(new DicItem(SUM1, "index", "首页"));
                sysDicBarType.add(new DicItem(SUM2, "channel", "频道页"));
                DICTIONARYSCACHE.put("sysDicBarType", sysDicBarType);
                LOGGER.info("初始化字典缓存完成");
            }
            return false;
        }
    }

    /**
     * 根据字段的名称获得字典
     *
     * @param name
     *            字典名称
     * @return 字典
     * @throws com.ningpai.system.exception.NPException
     *             自定义异常
     * @author system
     * @date 2013-06-20 13:02
     */
    public final List<DicItem> getDictionaryByName(final String name) throws NPException {
        if (DICTIONARYSCACHE != null && DICTIONARYSCACHE.containsKey(name)) {
            return DICTIONARYSCACHE.get(name);
        }
        return new ArrayList<DicItem>();
    }

}
