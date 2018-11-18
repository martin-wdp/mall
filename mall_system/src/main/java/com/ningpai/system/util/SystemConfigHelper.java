package com.ningpai.system.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ningpai.system.bean.UpyunConf;
import com.ningpai.system.service.IUpyunConfBiz;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;

/**
 * 系统配置帮助类 负责读取系统的各种配置信息
 *
 * @author NINGPAI_xiaomm
 * @version V1.0
 * @since 2014-03-25 13:17:21
 */
public final class SystemConfigHelper {

    /**
     * 日志记录
     */
    private static final MyLogger LOGGER = new MyLogger(SystemConfigHelper.class);

    private SystemConfigHelper() {
    }

    /**
     * 获得又拍云设置业务对象
     *
     * @return 又拍云设置对象业务对象
     */
    public static final IUpyunConfBiz getUpyunConfBizImpl() {
        return (IUpyunConfBiz) SpringContextTool.getBean("upyunConfBizImpl");
    }

    /**
     * 获得默认的又拍云配置信息 获得启用又拍云配置信息的第一个配置信息
     *
     * @return 又拍云配置信息
     * @author NINGPAI_xiaomm
     * @version V1.0
     * @since 2014-03-25 13:17:21
     */
    @SuppressWarnings("rawtypes")
    public static UpyunConf getDefaultUpyunConfig() {
        Map<String, Object> pa = new HashMap<String, Object>(2);
        pa.put("usedStatus", 1);
        pa.put("deleteStatus", 0);
        PageBean pg = new PageBean();
        pg.setPageSize(2);
        List upList = null;
        try {
            upList = ((PageBean) getUpyunConfBizImpl().getUpyunConfByField(pa, pg)).getList();
        } catch (Exception e) {
            LOGGER.error("获得默认的又拍云配置信息异常，错误：" + e.getMessage());
        }
        return (UpyunConf) (upList != null && !upList.isEmpty() ? upList.get(0) : null);
    }

    /**
     * 根据配置方案或代码值获得又拍云配置信息
     *
     * @param title
     *            设置方案标题
     * @param code
     *            设置方案代码
     * @return 又拍云配置信息
     * @author NINGPAI_xiaomm
     * @version V1.0
     * @since 2014-03-25 13:17:21
     */
    @SuppressWarnings("rawtypes")
    public static UpyunConf getUpyunConfigByTitleOrCode(String title, String code) {
        if (null == title && null == code) {
            LOGGER.error("设置方案标题和设置方案代码都为空，无法根据配置方案或代码值获得又拍云配置信息");
            return null;
        }
        Map<String, Object> pa = new HashMap<String, Object>(2);
        pa.put("usedStatus", 1);
        pa.put("deleteStatus", 0);
        if (null != title && !"".equals(title)) {
            pa.put("title", title);
        }
        if (null != code && !"".equals(code)) {
            pa.put("setCode", code);
        }
        PageBean pg = new PageBean();
        pg.setPageSize(2);
        List upList = null;
        try {
            upList = ((PageBean) getUpyunConfBizImpl().getUpyunConfByField(pa, pg)).getList();
        } catch (Exception e) {
            LOGGER.error("根据配置方案或代码值获得又拍云配置信息异常，错误：" + e.getMessage());
        }
        return (UpyunConf) (upList != null && !upList.isEmpty() ? upList.get(0) : null);
    }
}
