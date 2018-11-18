package com.ningpai.system.service.impl;

import com.ningpai.system.bean.ExpressConf;
import com.ningpai.system.bean.LogisticsCompany;
import com.ningpai.system.dao.IExpressConfDao;
import com.ningpai.system.dao.ILogisticsCompanyDao;
import com.ningpai.system.service.IExpressConfBiz;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 配送方式设置业务类
 * 
 * @Description:
 * @author NINGPAI_xiaomm
 * @since 2014-03-26 16:38:32
 * @version V1.0
 */
@Service("expressConfBizImpl")
public class ExpressConfBizImpl implements IExpressConfBiz {

    /** 日志记录 */
    private static final MyLogger LOGGER = new MyLogger(ExpressConfBizImpl.class);

    private static final String START_ROW_NUM = "startRowNum";

    private static final String END_ROW_NUM = "endRowNum";

    /** 配送方式设置数据操作类 */
    private IExpressConfDao expressConfDaoImpl;

    /** 物流公司设置数据操作类 */
    private ILogisticsCompanyDao logisticsCompanyDaoImpl;

    /** 结束行数 */
    private static final int ENDROWNUM = 10000;

    /**
     * 获得配送方式设置数据操作类
     * 
     * @return 配送方式设置数据操作类
     */
    public final IExpressConfDao getExpressConfDaoImpl() {
        return expressConfDaoImpl;
    }

    /**
     * 自动注入赋值配送方式设置数据操作类
     * 
     * @param expressConfDaoImpl
     *            配送方式设置数据操作类
     */
    @Resource(name = "expressConfDaoImpl")
    public final void setExpressConfDaoImpl(final IExpressConfDao expressConfDaoImpl) {
        this.expressConfDaoImpl = expressConfDaoImpl;
    }

    /**
     * 保存配送方式设置
     * 
     * @param expressConf
     *            配送方式设置对象
     * @return 是否保存成功
     * @author NINGPAI_xiaomm
     * @since 2014-03-26 16:38:32
     */
    public final boolean saveExpressConf(final ExpressConf expressConf) {
        if (expressConf == null) {
            LOGGER.error("配送方式设置对象为空，无法执行保存操作！");
            return false;
        }
        try {
            return expressConfDaoImpl.saveExpressConf(expressConf);
        } catch (Exception e) {
            LOGGER.error("", e);
            return false;
        }
    }

    /**
     * 修改配送方式设置
     * 
     * @param expressConf
     *            待修改配送方式设置对象
     * @return 更新影响的条数
     * @author NINGPAI_xiaomm
     * @since 2014-03-26 16:38:32
     */
    public final int updateExpressConf(final ExpressConf expressConf) {
        if (expressConf == null) {
            LOGGER.error("配送方式设置对象为空，无法执行修改操作！");
            return 0;
        }
        try {
            return expressConfDaoImpl.updateExpressConf(expressConf);
        } catch (Exception e) {
            LOGGER.error("", e);
            return 0;
        }
    }

    /**
     * 根据配送方式设置对象的id查询配送方式设置对象
     * 
     * @param id
     *            配送方式设置id
     * @return 配送方式设置对象
     * @author NINGPAI_xiaomm
     * @since 2014-03-26 16:38:32
     */
    @Override
    public final ExpressConf getExpressConfById(final int id) {
        if (id == 0) {
            LOGGER.error("对象配送方式设置的id为0，无法查询对象！");
            return null;
        }
        try {
            return expressConfDaoImpl.getExpressConfById(id);
        } catch (Exception e) {
            LOGGER.error("", e);
            return null;
        }
    }

    /**
     * 根据配送方式设置对象的id字符集合查询配送方式设置对象
     * 
     * @param ids
     *            配送方式设置id字符集合(如果多个使用,分割)
     * @return 配送方式设置对象集合
     * @author system
     * @since 2014-03-26 16:38:32
     */
    public final List<ExpressConf> getExpressConfByIds(final String ids) {
        if (ids == null || ids.trim().length() == 0) {
            LOGGER.error("对象配送方式设置的id集合字符为空，无法查询对象！");
            return Collections.emptyList();
        }
        try {
            return expressConfDaoImpl.getExpressConfByIds(ids);
        } catch (Exception e) {
            LOGGER.error("", e);
            return Collections.emptyList();
        }
    }

    /**
     * 根据配送方式设置对象的id字符集合删除配送方式设置对象
     * 
     * @param ids
     *            配送方式设置id字符集合(如果多个使用,分割)
     * @return 删除配送方式设置对象的数目
     * @author NINGPAI_xiaomm
     * @since 2014-03-26 16:38:32
     */
    public final int deleteExpressConf(final String ids) {
        if (ids == null || ids.trim().length() == 0) {
            LOGGER.error("对象配送方式设置的id集合字符为空，无法查询对象！");
            return 0;
        }
        try {
            return expressConfDaoImpl.deleteExpressConf(ids);
        } catch (Exception e) {
            LOGGER.error("", e);
            return 0;
        }
    }

    /**
     * 更新配送方式设置对象的单个字段 其中要包含ids键，保存更新对象的id（多个id以，号分割）
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 更新配送方式设置对象的数目
     * @author NINGPAI_xiaomm
     * @since 2014-03-26 16:38:32
     */
    public final int updateExpressConfFieldById(final Map<String, Object> parameter) {
        if (parameter == null || parameter.isEmpty()) {
            LOGGER.error("参数Map为空，无法执行更新配送方式设置对象的单个字段！");
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
            return expressConfDaoImpl.updateExpressConfFieldById(parameter);
        } catch (Exception e) {
            LOGGER.error("", e);
            return 0;
        }
    }

    /**
     * 根据配送方式设置对象的单个字段查询配送方式设置对象信息总数 其中要包含ids键，保存更新对象的id（多个id以，号分割） 如果查询中有时间条件
     * 则字段名格式为：开始时间（字段名Start），结束时间（字段名End）
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 配送方式设置对象信息总数
     * @author NINGPAI_xiaomm
     * @since 2014-03-26 16:38:32
     */
    public final int getExpressConfByFieldTotal(final Map<String, Object> parameter) {
        if (parameter == null || parameter.isEmpty()) {
            LOGGER.error("参数Map为空，无法执行根据配送方式设置对象的单个字段查询配送方式设置对象信息总数！");
            return 0;
        }
        try {
            return expressConfDaoImpl.getExpressConfByFieldTotal(parameter);
        } catch (Exception e) {
            LOGGER.error("", e);
            return 0;
        }
    }

    /**
     * 根据配送方式设置对象的单个字段查询配送方式设置对象信息 其中要包含ids键，保存更新对象的id（多个id以，号分割）
     * 如果查询中有时间条件，则字段名格式为：开始时间（字段名Start），结束时间（字段名End）
     * 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 配送方式设置对象的集合
     * @author NINGPAI_xiaomm
     * @since 2014-03-26 16:38:32
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public final PageBean getExpressConfByField(final Map<String, Object> parameter, final PageBean pageBean) {
        if (parameter == null) {
            LOGGER.error("参数Map为空，无法执行根据配送方式设置对象的单个字段查询配送方式设置对象信息！");
            return null;
        }
        int no = 0;
        try {
            // 设置总行数
            pageBean.setRows(getExpressConfByFieldTotal(parameter));
            // 获得总条数
            no = pageBean.getRows() % pageBean.getPageSize() == 0 ? pageBean.getRows() / pageBean.getPageSize() : (pageBean.getRows() / pageBean.getPageSize() + 1);
            no = no == 0 ? 1 : no;
            // 若页码超过最大页码 则显示最后一个
            if (pageBean.getPageNo() >= no) {
                pageBean.setPageNo(no);
                pageBean.setStartRowNum((no - 1) * pageBean.getPageSize());
            }
            // 分页查询开始位置
            parameter.put(START_ROW_NUM, pageBean.getStartRowNum());
            // 分页查询结束位置
            parameter.put(END_ROW_NUM, pageBean.getEndRowNum());
            // 分页查询
            pageBean.setList((List) expressConfDaoImpl.getExpressConfByField(parameter));
        } catch (Exception e) {
            LOGGER.error("", e);
            return null;
        }
        return pageBean;
    }

    /**
     * 根据启用与否查询配送方式
     *
     * @return 配送方式设置对象
     */
    @Override
    public final ExpressConf getExpressConfByUsedField() {
        return expressConfDaoImpl.getExpressConfByUsedField();
    }

    /**
     * 查询配送方式设置对象信息总数
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 配送方式设置对象信息总数
     * @author NINGPAI_xiaomm
     * @since 2014-03-26 16:38:32
     */
    public final int queryExpressConfTotal(final Map<String, Object> parameter) {
        if (parameter == null) {
            LOGGER.error("参数Map为空，无法执行查询配送方式设置对象信息总数！");
            return 0;
        }
        try {
            return expressConfDaoImpl.queryExpressConfTotal(parameter);
        } catch (Exception e) {
            LOGGER.error("", e);
            return 0;
        }
    }

    /**
     * 分页查询配送方式设置对象信息 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @param pageBean
     *            分页对象
     * @return 配送方式设置对象的集合
     * @author NINGPAI_xiaomm
     * @since 2014-03-26 16:38:32
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public final PageBean queryExpressConfByPage(final Map<String, Object> parameter, final PageBean pageBean) {
        if (parameter == null) {
            LOGGER.error("参数Map为空，无法执行分页查询配送方式设置对象信息！");
            return null;
        }
        int no = 0;
        try {
            // 设置总行数
            pageBean.setRows(queryExpressConfTotal(parameter));
            // 获得总条数
            no = pageBean.getRows() % pageBean.getPageSize() == 0 ? pageBean.getRows() / pageBean.getPageSize() : (pageBean.getRows() / pageBean.getPageSize() + 1);
            no = no == 0 ? 1 : no;
            // 若页码超过最大页码 则显示最后一个
            if (pageBean.getPageNo() >= no) {
                pageBean.setPageNo(no);
                pageBean.setStartRowNum((no - 1) * pageBean.getPageSize());
            }
            // 分页查询开始位置
            parameter.put(START_ROW_NUM, pageBean.getStartRowNum());
            // 分页查询结束位置
            parameter.put(END_ROW_NUM, pageBean.getEndRowNum());
            // 分页查询
            pageBean.setList((List) expressConfDaoImpl.queryExpressConfByPage(parameter));
        } catch (Exception e) {
            LOGGER.error("", e);
            return null;
        }
        return pageBean;
    }

    /**
     * 查询所有的配送方式
     * 
     * @see com.ningpai.system.service.IExpressConfBiz#queryAllExpress()
     */
    public Map<String, Object> queryAllExpress() {
        Map<String, Object> map = new HashMap<String, Object>();
        // 分页查询开始位置
        map.put(START_ROW_NUM, 0);
        // 分页查询结束位置
        map.put(END_ROW_NUM, ENDROWNUM);
        map.put("deleteStatus", 0);
        // 分页查询
        map.put("usedStatus", 1);
        map.put("expresss", expressConfDaoImpl.getExpressConfByField(map));
        map.put("expressCompany", logisticsCompanyDaoImpl.getLogisticsCompanyByField(map));
        return map;
    }

    /**
     * 根据配送方式查询物流公司快递100代码
     * 
     * @see com.ningpai.system.service.IExpressConfBiz#queryKuaidi100CodeByExpressId
     *      (java.lang.Long)
     */
    @Override
    public String queryKuaidi100CodeByExpressId(Long expressid) {
        return this.expressConfDaoImpl.queryKuaidi100CodeByExpressId(expressid);
    }

    /**
     * 修改配送方式启用状态
     *
     */
    @Override
    public boolean changeUserdStatus(Long expressid) {
        ExpressConf express = this.expressConfDaoImpl.getExpressConfById(expressid.intValue());
        if ("0".equals(express.getUsedStatus())) {
            express.setUsedStatus("1");
        } else {
            express.setUsedStatus("0");
        }
        return expressConfDaoImpl.updateExpressConf(express) > 0;
    }

    /**
     * 根据配送方式获取物流公司
     *
     */
    @Override
    public LogisticsCompany getLogisticsByExpressId(Long expressId) {
        try {
            Integer logistId = this.expressConfDaoImpl.getExpressConfById(expressId.intValue()).getExpress();
            return this.logisticsCompanyDaoImpl.getLogisticsCompanyById(logistId);
        } catch (Exception e) {
            LOGGER.error("根据配送方式获取物流公司错误：=>", e);
            return null;
        }
    }

    /**
     * 删除单个配送方式
     */
    @Override
    public int deleteOneExpressConf(Integer expressId) {
        ExpressConf expressConf = new ExpressConf();
        expressConf.setExpressId(expressId);
        expressConf.setDeleteStatus(1);
        expressConfDaoImpl.updateExpressConf(expressConf);
        return 1;
    }

    /**
     * 获得物流公司设置数据操作类
     * 
     * @return 物流公司设置数据操作类
     */
    public final ILogisticsCompanyDao getLogisticsCompanyDaoImpl() {
        return logisticsCompanyDaoImpl;
    }

    /**
     * 自动注入赋值物流公司设置数据操作类
     * 
     * @param logisticsCompanyDaoImpl
     *            物流公司设置数据操作类
     */
    @Resource(name = "logisticsCompanyDaoImpl")
    public final void setLogisticsCompanyDaoImpl(final ILogisticsCompanyDao logisticsCompanyDaoImpl) {
        this.logisticsCompanyDaoImpl = logisticsCompanyDaoImpl;
    }
}
