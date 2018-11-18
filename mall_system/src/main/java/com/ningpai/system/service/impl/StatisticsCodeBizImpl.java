package com.ningpai.system.service.impl;

import com.ningpai.system.bean.StatisticsCode;
import com.ningpai.system.dao.IStatisticsCodeDao;
import com.ningpai.system.service.IStatisticsCodeBiz;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 统计代码业务类
 * 
 * @Description:
 * @author NINGPAI_xiaomm
 * @since 2014-03-21 17:03:45
 * @version V1.0
 */
@Service("statisticsCodeBizImpl")
public class StatisticsCodeBizImpl implements IStatisticsCodeBiz {

    /** 日志记录 */
    private static final MyLogger LOGGER = new MyLogger(StatisticsCodeBizImpl.class);
    /** 统计代码数据操作类 */
    private IStatisticsCodeDao statisticsCodeDaoImpl;

    /**
     * 获得统计代码数据操作类
     * 
     * @return 统计代码数据操作类
     */
    public final IStatisticsCodeDao getStatisticsCodeDaoImpl() {
        return statisticsCodeDaoImpl;
    }

    /**
     * 自动注入赋值统计代码数据操作类
     * 
     * @param statisticsCodeDaoImpl
     *            统计代码数据操作类
     */
    @Resource(name = "statisticsCodeDaoImpl")
    public final void setStatisticsCodeDaoImpl(final IStatisticsCodeDao statisticsCodeDaoImpl) {
        this.statisticsCodeDaoImpl = statisticsCodeDaoImpl;
    }

    /**
     * 保存统计代码
     * 
     * @param statisticsCode
     *            统计代码对象
     * @return 是否保存成功
     * @author NINGPAI_xiaomm
     * @since 2014-03-21 17:03:45
     */
    public final boolean saveStatisticsCode(final StatisticsCode statisticsCode) {
        if (statisticsCode == null) {
            LOGGER.error("统计代码对象为空，无法执行保存操作！");
            return false;
        }
        try {
            return statisticsCodeDaoImpl.saveStatisticsCode(statisticsCode);
        } catch (Exception e) {
            LOGGER.info(e);
            return false;
        }
    }

    /**
     * 修改统计代码
     * 
     * @param statisticsCode
     *            待修改统计代码对象
     * @return 更新影响的条数
     * @author NINGPAI_xiaomm
     * @since 2014-03-21 17:03:45
     */
    public final int updateStatisticsCode(final StatisticsCode statisticsCode) {
        if (statisticsCode == null) {
            LOGGER.error("统计代码对象为空，无法执行修改操作！");
            return 0;
        }
        try {
            return statisticsCodeDaoImpl.updateStatisticsCode(statisticsCode);
        } catch (Exception e) {
            LOGGER.info(e);
            return 0;
        }
    }

    /**
     * 根据统计代码对象的id查询统计代码对象
     * 
     * @param id
     *            统计代码id
     * @return 统计代码对象
     * @author NINGPAI_xiaomm
     * @since 2014-03-21 17:03:45
     */
    public final StatisticsCode getStatisticsCodeById(final int id) {
        if (id == 0) {
            LOGGER.error("对象统计代码的id为0，无法查询对象！");
            return null;
        }
        try {
            return statisticsCodeDaoImpl.getStatisticsCodeById(id);
        } catch (Exception e) {
            LOGGER.info(e);
            return null;
        }
    }

    /**
     * 根据统计代码对象的id字符集合查询统计代码对象
     * 
     * @param ids
     *            统计代码id字符集合(如果多个使用,分割)
     * @return 统计代码对象集合
     * @author system
     * @since 2014-03-21 17:03:45
     */
    public final List<StatisticsCode> getStatisticsCodeByIds(final String ids) {
        if (ids == null || ids.trim().length() == 0) {
            LOGGER.error("对象统计代码的id集合字符为空，无法查询对象！");
            return Collections.emptyList();
        }
        try {
            return statisticsCodeDaoImpl.getStatisticsCodeByIds(ids);
        } catch (Exception e) {
            LOGGER.info(e);
            return Collections.emptyList();
        }
    }

    /**
     * 根据统计代码对象的id字符集合删除统计代码对象
     * 
     * @param ids
     *            统计代码id字符集合(如果多个使用,分割)
     * @return 删除统计代码对象的数目
     * @author NINGPAI_xiaomm
     * @since 2014-03-21 17:03:45
     */
    public final int deleteStatisticsCode(final String ids) {
        if (ids == null || ids.trim().length() == 0) {
            LOGGER.error("对象统计代码的id集合字符为空，无法查询对象！");
            return 0;
        }
        try {
            return statisticsCodeDaoImpl.deleteStatisticsCode(ids);
        } catch (Exception e) {
            LOGGER.info(e);
            return 0;
        }
    }

    /**
     * 更新统计代码对象的单个字段 其中要包含ids键，保存更新对象的id（多个id以，号分割）
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 更新统计代码对象的数目
     * @author NINGPAI_xiaomm
     * @since 2014-03-21 17:03:45
     */
    public final int updateStatisticsCodeFieldById(final Map<String, Object> parameter) {
        if (parameter == null || parameter.isEmpty()) {
            LOGGER.error("参数Map为空，无法执行更新统计代码对象的单个字段！");
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
            return statisticsCodeDaoImpl.updateStatisticsCodeFieldById(parameter);
        } catch (Exception e) {
            LOGGER.info(e);
            return 0;
        }
    }

    /**
     * 根据统计代码对象的单个字段查询统计代码对象信息总数 其中要包含ids键，保存更新对象的id（多个id以，号分割） 如果查询中有时间条件
     * 则字段名格式为：开始时间（字段名Start），结束时间（字段名End）
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 统计代码对象信息总数
     * @author NINGPAI_xiaomm
     * @since 2014-03-21 17:03:45
     */
    public final int getStatisticsCodeByFieldTotal(final Map<String, Object> parameter) {
        if (parameter == null || parameter.isEmpty()) {
            LOGGER.error("参数Map为空，无法执行根据统计代码对象的单个字段查询统计代码对象信息总数！");
            return 0;
        }
        try {
            return statisticsCodeDaoImpl.getStatisticsCodeByFieldTotal(parameter);
        } catch (Exception e) {
            LOGGER.info(e);
            return 0;
        }
    }

    /**
     * 根据统计代码对象的单个字段查询统计代码对象信息 其中要包含ids键，保存更新对象的id（多个id以，号分割）
     * 如果查询中有时间条件，则字段名格式为：开始时间（字段名Start），结束时间（字段名End）
     * 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     *
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 统计代码对象的集合
     * @author NINGPAI_xiaomm
     * @since 2014-03-21 17:03:45
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public final PageBean getStatisticsCodeByField(final Map<String, Object> parameter, final PageBean pageBean) {
        if (parameter == null) {
            LOGGER.error("参数Map为空，无法执行根据统计代码对象的单个字段查询统计代码对象信息！");
            return null;
        }
        int no = 0;
        try {
            // 设置总行数
            pageBean.setRows(getStatisticsCodeByFieldTotal(parameter));
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
            pageBean.setList((List) statisticsCodeDaoImpl.getStatisticsCodeByField(parameter));
        } catch (Exception e) {
            LOGGER.info(e);
            return null;
        }
        return pageBean;
    }

    /**
     * 查询统计代码对象信息总数
     *
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 统计代码对象信息总数
     * @author NINGPAI_xiaomm
     * @since 2014-03-21 17:03:45
     */
    public final int queryStatisticsCodeTotal(final Map<String, Object> parameter) {
        if (parameter == null) {
            LOGGER.error("参数Map为空，无法执行查询统计代码对象信息总数！");
            return 0;
        }
        try {
            return statisticsCodeDaoImpl.queryStatisticsCodeTotal(parameter);
        } catch (Exception e) {
            LOGGER.info(e);
            return 0;
        }
    }

    /**
     * 分页查询统计代码对象信息 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     *
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @param pageBean
     *            分页对象
     * @return 统计代码对象的集合
     * @author NINGPAI_xiaomm
     * @since 2014-03-21 17:03:45
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public final PageBean queryStatisticsCodeByPage(final Map<String, Object> parameter, final PageBean pageBean) {
        if (parameter == null) {
            LOGGER.error("参数Map为空，无法执行分页查询统计代码对象信息！");
            return null;
        }
        int no = 0;
        try {
            // 设置总行数
            pageBean.setRows(queryStatisticsCodeTotal(parameter));
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
            pageBean.setList((List) statisticsCodeDaoImpl.queryStatisticsCodeByPage(parameter));
        } catch (Exception e) {
            LOGGER.info(e);
            return null;
        }
        return pageBean;
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.system.service.IStatisticsCodeBiz#changeUserdStatus(java.
     * lang.Long)
     */
    @Override
    public boolean changeUserdStatus(Long scodeId) {
        boolean b = false;
        try {
            StatisticsCode scode = statisticsCodeDaoImpl.getStatisticsCodeById(scodeId.intValue());
            if ("0".equals(scode.getUsedStatus())) {
                scode.setUsedStatus("1");
            } else {
                scode.setUsedStatus("0");
            }
            return this.statisticsCodeDaoImpl.updateStatisticsCode(scode) > 0;
        } catch (Exception e) {
            LOGGER.error("修改统计代码启用状态错误:=>", e);
        }
        return b;
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.system.service.IStatisticsCodeBiz#getCurrStatisticsCode()
     */
    @Override
    public List<StatisticsCode> getCurrStatisticsCode() {
        List<StatisticsCode> list = null;
        try {
            list = this.statisticsCodeDaoImpl.selectAllStatisticsCode();
        } catch (Exception e) {
            LOGGER.error("获取统计代码错误:=>", e);
        }
        return list;
    }

}
