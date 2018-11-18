package com.ningpai.system.service.impl;

import com.ningpai.system.bean.CurrencyConf;
import com.ningpai.system.dao.ICurrencyConfDao;
import com.ningpai.system.service.ICurrencyConfBiz;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 货币设置业务类
 * 
 * @Description:
 * @author NINGPAI_xiaomm
 * @since 2014-03-25 13:17:21
 * @version V1.0
 */
@Service("currencyConfBizImpl")
public class CurrencyConfBizImpl implements ICurrencyConfBiz {

    /** 日志记录 */
    private static final MyLogger LOGGER = new MyLogger(CurrencyConfBizImpl.class);
    /** 货币设置数据操作类 */
    private ICurrencyConfDao currencyConfDaoImpl;

    /**
     * 获得货币设置数据操作类
     * 
     * @return 货币设置数据操作类
     */
    public final ICurrencyConfDao getCurrencyConfDaoImpl() {
        return currencyConfDaoImpl;
    }

    /**
     * 自动注入赋值货币设置数据操作类
     * 
     * @param currencyConfDaoImpl
     *            货币设置数据操作类
     */
    @Resource(name = "currencyConfDaoImpl")
    public final void setCurrencyConfDaoImpl(final ICurrencyConfDao currencyConfDaoImpl) {
        this.currencyConfDaoImpl = currencyConfDaoImpl;
    }

    /**
     * 保存货币设置
     * 
     * @param currencyConf
     *            货币设置对象
     * @return 是否保存成功 @ 自定义异常
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 13:17:21
     */
    public final boolean saveCurrencyConf(final CurrencyConf currencyConf) {
        if (currencyConf == null) {
            LOGGER.error("货币设置对象为空，无法执行保存操作！");
            return false;
        }
        try {
            return currencyConfDaoImpl.saveCurrencyConf(currencyConf);
        } catch (Exception e) {
            LOGGER.error("", e);
            return false;
        }
    }

    /**
     * 修改货币设置
     * 
     * @param currencyConf
     *            待修改货币设置对象
     * @return 更新影响的条数 @ 自定义异常
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 13:17:21
     */
    public final int updateCurrencyConf(final CurrencyConf currencyConf) {
        if (currencyConf == null) {
            LOGGER.error("货币设置对象为空，无法执行修改操作！");
            return 0;
        }
        try {
            return currencyConfDaoImpl.updateCurrencyConf(currencyConf);
        } catch (Exception e) {
            LOGGER.error("", e);
            return 0;
        }
    }

    /**
     * 根据货币设置对象的id查询货币设置对象
     * 
     * @param id
     *            货币设置id
     * @return 货币设置对象 @ 自定义异常
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 13:17:21
     */
    public final CurrencyConf getCurrencyConfById(final int id) {
        if (id == 0) {
            LOGGER.error("对象货币设置的id为0，无法查询对象！");
            return null;
        }
        try {
            return currencyConfDaoImpl.getCurrencyConfById(id);
        } catch (Exception e) {
            LOGGER.error("", e);
            return null;
        }
    }

    /**
     * 根据货币设置对象的id字符集合查询货币设置对象
     * 
     * @param ids
     *            货币设置id字符集合(如果多个使用,分割)
     * @return 货币设置对象集合 @ 自定义异常
     * @author system
     * @since 2014-03-25 13:17:21
     */
    public final List<CurrencyConf> getCurrencyConfByIds(final String ids) {
        if (ids == null || ids.trim().length() == 0) {
            LOGGER.error("对象货币设置的id集合字符为空，无法查询对象！");
            return Collections.emptyList();
        }
        try {
            return currencyConfDaoImpl.getCurrencyConfByIds(ids);
        } catch (Exception e) {
            LOGGER.error("", e);
            return Collections.emptyList();
        }
    }

    /**
     * 根据货币设置对象的id字符集合删除货币设置对象
     * 
     * @param ids
     *            货币设置id字符集合(如果多个使用,分割)
     * @return 删除货币设置对象的数目 @ 自定义异常
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 13:17:21
     */
    public final int deleteCurrencyConf(final String ids) {
        if (ids == null || ids.trim().length() == 0) {
            LOGGER.error("对象货币设置的id集合字符为空，无法查询对象！");
            return 0;
        }
        try {
            return currencyConfDaoImpl.deleteCurrencyConf(ids);
        } catch (Exception e) {
            LOGGER.error("", e);
            return 0;
        }
    }

    /**
     * 更新货币设置对象的单个字段 其中要包含ids键，保存更新对象的id（多个id以，号分割）
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 更新货币设置对象的数目 @ 自定义异常
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 13:17:21
     */
    public final int updateCurrencyConfFieldById(final Map<String, Object> parameter) {
        if (parameter == null || parameter.isEmpty()) {
            LOGGER.error("参数Map为空，无法执行更新货币设置对象的单个字段！");
            return 0;
        }
        // 将字符串ids转换为集合，用于sql赋值
        if (parameter.containsKey("ids")) {
            String ids = (String) parameter.get("ids");
            List<String> idList = new ArrayList<String>();
            if (ids.contains(",")) {
                for (String id : ids.split(",")) {
                    idList.add(id);
                }
            } else {
                idList.add(ids);
            }
            parameter.put("ids", idList);
        }
        try {
            return currencyConfDaoImpl.updateCurrencyConfFieldById(parameter);
        } catch (Exception e) {
            LOGGER.error("", e);
            return 0;
        }
    }

    /**
     * 根据货币设置对象的单个字段查询货币设置对象信息总数 其中要包含ids键，保存更新对象的id（多个id以，号分割） 如果查询中有时间条件
     * 则字段名格式为：开始时间（字段名Start），结束时间（字段名End）
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 货币设置对象信息总数 @ 自定义异常
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 13:17:21
     */
    public final int getCurrencyConfByFieldTotal(final Map<String, Object> parameter) {
        if (parameter == null || parameter.isEmpty()) {
            LOGGER.error("参数Map为空，无法执行根据货币设置对象的单个字段查询货币设置对象信息总数！");
            return 0;
        }
        try {
            return currencyConfDaoImpl.getCurrencyConfByFieldTotal(parameter);
        } catch (Exception e) {
            LOGGER.error("", e);
            return 0;
        }
    }

    /**
     * 根据货币设置对象的单个字段查询货币设置对象信息 其中要包含ids键，保存更新对象的id（多个id以，号分割）
     * 如果查询中有时间条件，则字段名格式为：开始时间（字段名Start），结束时间（字段名End）
     * 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 货币设置对象的集合 @ 自定义异常
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 13:17:21
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public final PageBean getCurrencyConfByField(final Map<String, Object> parameter, final PageBean pageBean) {
        if (parameter == null) {
            LOGGER.error("参数Map为空，无法执行根据货币设置对象的单个字段查询货币设置对象信息！");
            return null;
        }
        int no = 0;
        try {
            // 设置总行数
            pageBean.setRows(getCurrencyConfByFieldTotal(parameter));
            // 获得总条数
            no = pageBean.getRows() % pageBean.getPageSize() == 0 ? pageBean.getRows() / pageBean.getPageSize()
                    : (pageBean.getRows() / pageBean.getPageSize() + 1);
            no = no == 0 ? 1 : no;
            // 若页码超过最大页码 则显示最后一个
            if (pageBean.getPageNo() >= no) {
                pageBean.setPageNo(no);
                pageBean.setStartRowNum((no - 1) * pageBean.getPageSize());
            }
            // 分页查询开始位置
            parameter.put("startRowNum", pageBean.getStartRowNum());
            // 分页查询结束位置
            parameter.put("endRowNum", pageBean.getEndRowNum());
            // 分页查询
            pageBean.setList((List) currencyConfDaoImpl.getCurrencyConfByField(parameter));
        } catch (Exception e) {
            LOGGER.error("", e);
            return null;
        }
        return pageBean;
    }

    /**
     * 查询货币设置对象信息总数
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 货币设置对象信息总数 @ 自定义异常
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 13:17:21
     */
    public final int queryCurrencyConfTotal(final Map<String, Object> parameter) {
        if (parameter == null) {
            LOGGER.error("参数Map为空，无法执行查询货币设置对象信息总数！");
            return 0;
        }
        try {
            return currencyConfDaoImpl.queryCurrencyConfTotal(parameter);
        } catch (Exception e) {
            LOGGER.error("", e);
            return 0;
        }
    }

    /**
     * 分页查询货币设置对象信息 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @param pageBean
     *            分页对象
     * @return 货币设置对象的集合
     * @自定义异常对象
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 13:17:21
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public final PageBean queryCurrencyConfByPage(final Map<String, Object> parameter, final PageBean pageBean) {
        if (parameter == null) {
            LOGGER.error("参数Map为空，无法执行分页查询货币设置对象信息！");
            return null;
        }
        int no = 0;
        try {
            // 设置总行数
            pageBean.setRows(queryCurrencyConfTotal(parameter));
            // 获得总条数
            no = pageBean.getRows() % pageBean.getPageSize() == 0 ? pageBean.getRows() / pageBean.getPageSize()
                    : (pageBean.getRows() / pageBean.getPageSize() + 1);
            no = no == 0 ? 1 : no;
            // 若页码超过最大页码 则显示最后一个
            if (pageBean.getPageNo() >= no) {
                pageBean.setPageNo(no);
                pageBean.setStartRowNum((no - 1) * pageBean.getPageSize());
            }
            // 分页查询开始位置
            parameter.put("startRowNum", pageBean.getStartRowNum());
            // 分页查询结束位置
            parameter.put("endRowNum", pageBean.getEndRowNum());
            // 分页查询
            pageBean.setList((List) currencyConfDaoImpl.queryCurrencyConfByPage(parameter));
        } catch (Exception e) {
            LOGGER.error("", e);
            return null;
        }
        return pageBean;
    }

}
