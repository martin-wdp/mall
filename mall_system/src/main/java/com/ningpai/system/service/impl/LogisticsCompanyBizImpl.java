package com.ningpai.system.service.impl;

import com.ningpai.system.bean.LogisticsCompany;
import com.ningpai.system.dao.IExpressConfDao;
import com.ningpai.system.dao.ILogisticsCompanyDao;
import com.ningpai.system.service.ILogisticsCompanyBiz;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 物流公司设置业务类
 * 
 * @Description:
 * @author NINGPAI_xiaomm
 * @since 2014-03-21 17:55:21
 * @version V1.0
 */
@Service("logisticsCompanyBizImpl")
public class LogisticsCompanyBizImpl implements ILogisticsCompanyBiz {

    /** 物流公司设置数据操作类 */
    @Resource(name = "logisticsCompanyDaoImpl")
    private ILogisticsCompanyDao logisticsCompanyDaoImpl;

    /** spring注解 */
    @Resource(name = "expressConfDaoImpl")
    private IExpressConfDao expressConfDao;

    /** 日志记录 */
    private static final MyLogger LOGGER = new MyLogger(LogisticsCompanyBizImpl.class);

    /**
     * 保存物流公司设置
     * 
     * @param logisticsCompany
     *            物流公司设置对象
     * @return 是否保存成功
     * @author NINGPAI_xiaomm
     * @since 2014-03-21 17:55:21
     */
    public final boolean saveLogisticsCompany(final LogisticsCompany logisticsCompany) {
        if (logisticsCompany == null) {
            LOGGER.error("物流公司设置对象为空，无法执行保存操作！");
            return false;
        }
        try {
            return logisticsCompanyDaoImpl.saveLogisticsCompany(logisticsCompany);
        } catch (Exception e) {
            LOGGER.info(e);
            return false;
        }
    }

    /**
     * 修改物流公司设置
     * 
     * @param logisticsCompany
     *            待修改物流公司设置对象
     * @return 更新影响的条数
     * @author NINGPAI_xiaomm
     * @since 2014-03-21 17:55:21
     */
    public final int updateLogisticsCompany(final LogisticsCompany logisticsCompany) {
        if (logisticsCompany == null) {
            LOGGER.error("物流公司设置对象为空，无法执行修改操作！");
            return 0;
        }
        try {
            return logisticsCompanyDaoImpl.updateLogisticsCompany(logisticsCompany);
        } catch (Exception e) {
            LOGGER.info(e);
            return 0;
        }
    }

    /**
     * 根据物流公司设置对象的id查询物流公司设置对象
     * 
     * @param id
     *            物流公司设置id
     * @return 物流公司设置对象
     * @author NINGPAI_xiaomm
     * @since 2014-03-21 17:55:21
     */
    public final LogisticsCompany getLogisticsCompanyById(final int id) {
        if (id == 0) {
            LOGGER.error("对象物流公司设置的id为0，无法查询对象！");
            return null;
        }
        try {
            return logisticsCompanyDaoImpl.getLogisticsCompanyById(id);
        } catch (Exception e) {
            LOGGER.info(e);
            return null;
        }
    }

    /**
     * 第三方根据物流公司设置对象的id查询物流公司设置对象
     * 
     * @param id
     *            物流公司设置id
     * @return 物流公司设置对象
     * @author NINGPAI_xiaomm
     * @since 2014-03-21 17:55:21
     */
    public final LogisticsCompany getThirdLogisticsCompanyById(final int id) {
        if (id == 0) {
            LOGGER.error("对象物流公司设置的id为0，无法查询对象！");
            return null;
        }
        try {
            return logisticsCompanyDaoImpl.getThirdLogisticsCompanyById(id);
        } catch (Exception e) {
            LOGGER.info(e);
            return null;
        }
    }

    /**
     * 根据物流公司设置对象的id字符集合查询物流公司设置对象
     * 
     * @param ids
     *            物流公司设置id字符集合(如果多个使用,分割)
     * @return 物流公司设置对象集合
     * @author system
     * @since 2014-03-21 17:55:21
     */
    public final List<LogisticsCompany> getLogisticsCompanyByIds(final String ids) {
        if (ids == null || ids.trim().length() == 0) {
            LOGGER.error("对象物流公司设置的id集合字符为空，无法查询对象！");
            return Collections.emptyList();
        }
        try {
            return logisticsCompanyDaoImpl.getLogisticsCompanyByIds(ids);
        } catch (Exception e) {
            LOGGER.info(e);
            return Collections.emptyList();
        }
    }

    /**
     * 根据物流公司设置对象的id字符集合删除物流公司设置对象
     * 
     * @param ids
     *            物流公司设置id字符集合(如果多个使用,分割)
     * @return 删除物流公司设置对象的数目
     * @author NINGPAI_xiaomm
     * @since 2014-03-21 17:55:21
     */
    public final int deleteLogisticsCompany(final String ids) {
        if (ids == null || ids.trim().length() == 0) {
            LOGGER.error("对象物流公司设置的id集合字符为空，无法查询对象！");
            return 0;
        }
        try {
            return logisticsCompanyDaoImpl.deleteLogisticsCompany(ids);
        } catch (Exception e) {
            LOGGER.info(e);
            return 0;
        }
    }

    /**
     * 更新物流公司设置对象的单个字段 其中要包含ids键，保存更新对象的id（多个id以，号分割）
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 更新物流公司设置对象的数目
     * @author NINGPAI_xiaomm
     * @since 2014-03-21 17:55:21
     */
    public final int updateLogisticsCompanyFieldById(final Map<String, Object> parameter) {
        if (parameter == null || parameter.isEmpty()) {
            LOGGER.error("参数Map为空，无法执行更新物流公司设置对象的单个字段！");
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
            return logisticsCompanyDaoImpl.updateLogisticsCompanyFieldById(parameter);
        } catch (Exception e) {
            LOGGER.info(e);
            return 0;
        }
    }

    /**
     * 根据物流公司设置对象的单个字段查询物流公司设置对象信息总数 其中要包含ids键，保存更新对象的id（多个id以，号分割） 如果查询中有时间条件
     * 则字段名格式为：开始时间（字段名Start），结束时间（字段名End）
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 物流公司设置对象信息总数
     * @author NINGPAI_xiaomm
     * @since 2014-03-21 17:55:21
     */
    public final int getLogisticsCompanyByFieldTotal(final Map<String, Object> parameter) {
        if (parameter == null || parameter.isEmpty()) {
            LOGGER.error("参数Map为空，无法执行根据物流公司设置对象的单个字段查询物流公司设置对象信息总数！");
            return 0;
        }
        try {
            return logisticsCompanyDaoImpl.getLogisticsCompanyByFieldTotal(parameter);
        } catch (Exception e) {
            LOGGER.info(e);
            return 0;
        }
    }

    /**
     * 根据物流公司设置对象的单个字段查询物流公司设置对象信息 其中要包含ids键，保存更新对象的id（多个id以，号分割）
     * 如果查询中有时间条件，则字段名格式为：开始时间（字段名Start），结束时间（字段名End）
     * 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 物流公司设置对象的集合
     * @author NINGPAI_xiaomm
     * @since 2014-03-21 17:55:21
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public final PageBean getLogisticsCompanyByField(final Map<String, Object> parameter, final PageBean pageBean) {
        if (parameter == null) {
            LOGGER.error("参数Map为空，无法执行根据物流公司设置对象的单个字段查询物流公司设置对象信息！");
            return null;
        }
        int no = 0;
        try {
            // 设置总行数
            pageBean.setRows(getLogisticsCompanyByFieldTotal(parameter));
            // 获得总条数
            no = pageBean.getRows() % pageBean.getPageSize() == 0 ? pageBean.getRows() / pageBean.getPageSize() : (pageBean.getRows() / pageBean.getPageSize() + 1);
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
            pageBean.setList((List) logisticsCompanyDaoImpl.getLogisticsCompanyByField(parameter));
        } catch (Exception e) {
            LOGGER.info(e);
            return null;
        }
        return pageBean;
    }

    /**
     * 查询物流公司设置对象信息总数
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 物流公司设置对象信息总数
     * @author NINGPAI_xiaomm
     * @since 2014-03-21 17:55:21
     */
    public final int queryLogisticsCompanyTotal(final Map<String, Object> parameter) {
        if (parameter == null) {
            LOGGER.error("参数Map为空，无法执行查询物流公司设置对象信息总数！");
            return 0;
        }
        try {
            return logisticsCompanyDaoImpl.queryLogisticsCompanyTotal(parameter);
        } catch (Exception e) {
            LOGGER.info(e);
            return 0;
        }
    }

    /**
     * 分页查询物流公司设置对象信息 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @param pageBean
     *            分页对象
     * @return 物流公司设置对象的集合
     * @author NINGPAI_xiaomm
     * @since 2014-03-21 17:55:21
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public final PageBean queryLogisticsCompanyByPage(final Map<String, Object> parameter, final PageBean pageBean) {
        if (parameter == null) {
            LOGGER.error("参数Map为空，无法执行分页查询物流公司设置对象信息！");
            return null;
        }
        int no = 0;
        try {
            // 设置总行数
            pageBean.setRows(queryLogisticsCompanyTotal(parameter));
            // 获得总条数
            no = pageBean.getRows() % pageBean.getPageSize() == 0 ? pageBean.getRows() / pageBean.getPageSize() : (pageBean.getRows() / pageBean.getPageSize() + 1);
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
            pageBean.setList((List) logisticsCompanyDaoImpl.queryLogisticsCompanyByPage(parameter));
        } catch (Exception e) {
            LOGGER.info(e);
            return null;
        }
        return pageBean;
    }

    /**
     * 获得物流公司当前最大排序值
     * 
     * @return 当前最大排序值
     * @author NINGPAI_xiaomm
     * @since 2014-03-21 17:55:21
     */
    public int getLogisticsCompanyMaxSort() {
        try {
            return logisticsCompanyDaoImpl.getLogisticsCompanyMaxSort();
        } catch (Exception e) {
            LOGGER.info(e);
            return 0;
        }
    }

    /*
     * 修改物流公司启用状态
     * 
     * @see
     * com.ningpai.system.service.ILogisticsCompanyBiz#changeUserdStatus(java
     * .lang.Long)
     */
    @Override
    public boolean changeUserdStatus(Long logComId) {
        LogisticsCompany logcom = this.logisticsCompanyDaoImpl.getLogisticsCompanyById(logComId.intValue());
        if ("0".equals(logcom.getUsedStatus())) {
            logcom.setUsedStatus("1");
        } else {
            logcom.setUsedStatus("0");
        }
        return logisticsCompanyDaoImpl.updateLogisticsCompany(logcom) > 0;
    }

    /*
     * 验证物流公司是否可删除
     * 
     * @see
     * com.ningpai.system.service.ILogisticsCompanyBiz#checkDeleteLogistics(
     * java.lang.Long)
     */
    @Override
    public boolean checkDeleteLogistics(Long logComId) {
        return this.expressConfDao.queryExpressCountByLogistics(logComId) > 0 ? false : true;
    }

    /*
     * 查询所有未删除、已启用的物流公司对象信息
     * 
     * @see
     * com.ningpai.system.service.ILogisticsCompanyBiz#queryAllLogisticsCompany
     * ()
     */
    @Override
    public List<LogisticsCompany> queryAllLogisticsCompany() {
        return this.logisticsCompanyDaoImpl.queryAllLogisticsCompany();
    }

    /**
     * 根据主键删除物流公司
     */
    @Override
    public void deleteLogisticsCompanyOne(Integer logComId) {
        LogisticsCompany company = new LogisticsCompany();
        company.setLogComId(logComId);
        company.setDeleteStatus(1);
        logisticsCompanyDaoImpl.updateLogisticsCompany(company);
    }

}
