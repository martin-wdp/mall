package com.ningpai.system.service.impl;

import com.ningpai.system.bean.SysErrorPage;
import com.ningpai.system.dao.ISysErrorPageDao;
import com.ningpai.system.service.ISysErrorPageBiz;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 异常页面设置业务类
 * 
 * @Description:
 * @author NINGPAI_xiaomm
 * @since 2014-03-25 10:10:44
 * @version V1.0
 */
@Service("sysErrorPageBizImpl")
public class SysErrorPageBizImpl implements ISysErrorPageBiz {

    /** 日志记录 */
    private static final MyLogger LOGGER = new MyLogger(SysErrorPageBizImpl.class);
    /** 异常页面设置数据操作类 */
    private ISysErrorPageDao sysErrorPageDaoImpl;

    /**
     * 获得异常页面设置数据操作类
     * 
     * @return 异常页面设置数据操作类
     */
    public final ISysErrorPageDao getSysErrorPageDaoImpl() {
        return sysErrorPageDaoImpl;
    }

    /**
     * 自动注入赋值异常页面设置数据操作类
     * 
     * @param sysErrorPageDaoImpl
     *            异常页面设置数据操作类
     */
    @Resource(name = "sysErrorPageDaoImpl")
    public final void setSysErrorPageDaoImpl(final ISysErrorPageDao sysErrorPageDaoImpl) {
        this.sysErrorPageDaoImpl = sysErrorPageDaoImpl;
    }

    /**
     * 保存异常页面设置
     * 
     * @param sysErrorPage
     *            异常页面设置对象
     * @return 是否保存成功
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 10:10:44
     */
    public final boolean saveSysErrorPage(final SysErrorPage sysErrorPage) {
        if (sysErrorPage == null) {
            LOGGER.error("异常页面设置对象为空，无法执行保存操作！");
            return false;
        }
        try {
            return sysErrorPageDaoImpl.saveSysErrorPage(sysErrorPage);
        } catch (Exception e) {
            LOGGER.error("", e);
            return false;
        }
    }

    /**
     * 修改异常页面设置
     * 
     * @param sysErrorPage
     *            待修改异常页面设置对象
     * @return 更新影响的条数
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 10:10:44
     */
    public final int updateSysErrorPage(final SysErrorPage sysErrorPage) {
        if (sysErrorPage == null) {
            LOGGER.error("异常页面设置对象为空，无法执行修改操作！");
            return 0;
        }
        try {
            return sysErrorPageDaoImpl.updateSysErrorPage(sysErrorPage);
        } catch (Exception e) {
            LOGGER.error("", e);
            return 0;
        }
    }

    /**
     * 根据异常页面设置对象的id查询异常页面设置对象
     * 
     * @param id
     *            异常页面设置id
     * @return 异常页面设置对象
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 10:10:44
     */
    public final SysErrorPage getSysErrorPageById(final int id) {
        if (id == 0) {
            LOGGER.error("对象异常页面设置的id为0，无法查询对象！");
            return null;
        }
        try {
            return sysErrorPageDaoImpl.getSysErrorPageById(id);
        } catch (Exception e) {
            LOGGER.error("", e);
            return null;
        }
    }

    /**
     * 根据异常页面设置对象的id字符集合查询异常页面设置对象
     * 
     * @param ids
     *            异常页面设置id字符集合(如果多个使用,分割)
     * @return 异常页面设置对象集合
     * @author system
     * @since 2014-03-25 10:10:44
     */
    public final List<SysErrorPage> getSysErrorPageByIds(final String ids) {
        if (ids == null || ids.trim().length() == 0) {
            LOGGER.error("对象异常页面设置的id集合字符为空，无法查询对象！");
            return Collections.emptyList();
        }
        try {
            return sysErrorPageDaoImpl.getSysErrorPageByIds(ids);
        } catch (Exception e) {
            LOGGER.error("", e);
            return Collections.emptyList();
        }
    }

    /**
     * 根据异常页面设置对象的id字符集合删除异常页面设置对象
     * 
     * @param ids
     *            异常页面设置id字符集合(如果多个使用,分割)
     * @return 删除异常页面设置对象的数目
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 10:10:44
     */
    public final int deleteSysErrorPage(final String ids) {
        if (ids == null || ids.trim().length() == 0) {
            LOGGER.error("对象异常页面设置的id集合字符为空，无法查询对象！");
            return 0;
        }
        try {
            return sysErrorPageDaoImpl.deleteSysErrorPage(ids);
        } catch (Exception e) {
            LOGGER.error("", e);
            return 0;
        }
    }

    /**
     * 更新异常页面设置对象的单个字段 其中要包含ids键，保存更新对象的id（多个id以，号分割）
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 更新异常页面设置对象的数目
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 10:10:44
     */
    public final int updateSysErrorPageFieldById(final Map<String, Object> parameter) {
        if (parameter == null || parameter.isEmpty()) {
            LOGGER.error("参数Map为空，无法执行更新异常页面设置对象的单个字段！");
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
            return sysErrorPageDaoImpl.updateSysErrorPageFieldById(parameter);
        } catch (Exception e) {
            LOGGER.error("", e);
            return 0;
        }
    }

    /**
     * 根据异常页面设置对象的单个字段查询异常页面设置对象信息总数 其中要包含ids键，保存更新对象的id（多个id以，号分割） 如果查询中有时间条件
     * 则字段名格式为：开始时间（字段名Start），结束时间（字段名End）
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 异常页面设置对象信息总数
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 10:10:44
     */
    public final int getSysErrorPageByFieldTotal(final Map<String, Object> parameter) {
        if (parameter == null || parameter.isEmpty()) {
            LOGGER.error("参数Map为空，无法执行根据异常页面设置对象的单个字段查询异常页面设置对象信息总数！");
            return 0;
        }
        try {
            return sysErrorPageDaoImpl.getSysErrorPageByFieldTotal(parameter);
        } catch (Exception e) {
            LOGGER.error("", e);
            return 0;
        }
    }

    /**
     * 根据异常页面设置对象的单个字段查询异常页面设置对象信息 其中要包含ids键，保存更新对象的id（多个id以，号分割）
     * 如果查询中有时间条件，则字段名格式为：开始时间（字段名Start），结束时间（字段名End）
     * 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 异常页面设置对象的集合
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 10:10:44
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public final PageBean getSysErrorPageByField(final Map<String, Object> parameter, final PageBean pageBean) {
        if (parameter == null) {
            LOGGER.error("参数Map为空，无法执行根据异常页面设置对象的单个字段查询异常页面设置对象信息！");
            return null;
        }
        int no = 0;
        try {
            // 设置总行数
            pageBean.setRows(getSysErrorPageByFieldTotal(parameter));
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
            pageBean.setList((List) sysErrorPageDaoImpl.getSysErrorPageByField(parameter));
        } catch (Exception e) {
            LOGGER.error("", e);
            return null;
        }
        return pageBean;
    }

    /**
     * 查询异常页面设置对象信息总数
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 异常页面设置对象信息总数
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 10:10:44
     */
    public final int querySysErrorPageTotal(final Map<String, Object> parameter) {
        if (parameter == null) {
            LOGGER.error("参数Map为空，无法执行查询异常页面设置对象信息总数！");
            return 0;
        }
        try {
            return sysErrorPageDaoImpl.querySysErrorPageTotal(parameter);
        } catch (Exception e) {
            LOGGER.error("", e);
            return 0;
        }
    }

    /**
     * 分页查询异常页面设置对象信息 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @param pageBean
     *            分页对象
     * @return 异常页面设置对象的集合
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 10:10:44
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public final PageBean querySysErrorPageByPage(final Map<String, Object> parameter, final PageBean pageBean) {
        if (parameter == null) {
            LOGGER.error("参数Map为空，无法执行分页查询异常页面设置对象信息！");
            return null;
        }
        int no = 0;
        try {
            // 设置总行数
            pageBean.setRows(querySysErrorPageTotal(parameter));
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
            pageBean.setList((List) sysErrorPageDaoImpl.querySysErrorPageByPage(parameter));
        } catch (Exception e) {
            LOGGER.error("", e);
            return null;
        }
        return pageBean;
    }

    @Override
    public SysErrorPage querySysErrorByPageName(String pageName) {

        return this.sysErrorPageDaoImpl.querySysErrorByPageName(pageName);
    }

}
