package com.ningpai.system.service.impl;

import com.ningpai.system.bean.SysDictionary;
import com.ningpai.system.dao.ISysDictionaryDao;
import com.ningpai.system.service.ISysDictionaryBiz;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 系统字典业务类
 * 
 * @Description:
 * @author NINGPAI_xiaomm
 * @since 2014-03-20 11:03:23
 * @version V1.0
 */
@Service("sysDictionaryBizImpl")
public class SysDictionaryBizImpl implements ISysDictionaryBiz {

    /** 日志记录 */
    private static final MyLogger LOGGER = new MyLogger(SysDictionaryBizImpl.class);
    /** 系统字典数据操作类 */
    private ISysDictionaryDao sysDictionaryDaoImpl;

    /**
     * 获得系统字典数据操作类
     * 
     * @return 系统字典数据操作类
     */
    public final ISysDictionaryDao getSysDictionaryDaoImpl() {
        return sysDictionaryDaoImpl;
    }

    /**
     * 自动注入赋值系统字典数据操作类
     * 
     * @param sysDictionaryDaoImpl
     *            系统字典数据操作类
     */
    @Resource(name = "sysDictionaryDaoImpl")
    public final void setSysDictionaryDaoImpl(final ISysDictionaryDao sysDictionaryDaoImpl) {
        this.sysDictionaryDaoImpl = sysDictionaryDaoImpl;
    }

    /**
     * 保存系统字典
     * 
     * @param sysDictionary
     *            系统字典对象
     * @return 是否保存成功
     * @author NINGPAI_xiaomm
     * @since 2014-03-20 11:03:23
     */
    public final boolean saveSysDictionary(final SysDictionary sysDictionary) {
        if (sysDictionary == null) {
            LOGGER.error("系统字典对象为空，无法执行保存操作！");
            return false;
        }
        try {
            return sysDictionaryDaoImpl.saveSysDictionary(sysDictionary);
        } catch (Exception e) {
            LOGGER.error("", e);
            return false;
        }
    }

    /**
     * 修改系统字典
     * 
     * @param sysDictionary
     *            待修改系统字典对象
     * @return 更新影响的条数
     * @author NINGPAI_xiaomm
     * @since 2014-03-20 11:03:23
     */
    public final int updateSysDictionary(final SysDictionary sysDictionary) {
        if (sysDictionary == null) {
            LOGGER.error("系统字典对象为空，无法执行修改操作！");
            return 0;
        }
        try {
            return sysDictionaryDaoImpl.updateSysDictionary(sysDictionary);
        } catch (Exception e) {
            LOGGER.error("", e);
            return 0;
        }
    }

    /**
     * 根据系统字典对象的id查询系统字典对象
     * 
     * @param id
     *            系统字典id
     * @return 系统字典对象
     * @author NINGPAI_xiaomm
     * @since 2014-03-20 11:03:23
     */
    public final SysDictionary getSysDictionaryById(final int id) {
        if (id == 0) {
            LOGGER.error("对象系统字典的id为0，无法查询对象！");
            return null;
        }
        try {
            return sysDictionaryDaoImpl.getSysDictionaryById(id);
        } catch (Exception e) {
            LOGGER.error("", e);
            return null;
        }
    }

    /**
     * 根据系统字典对象的id字符集合查询系统字典对象
     * 
     * @param ids
     *            系统字典id字符集合(如果多个使用,分割)
     * @return 系统字典对象集合
     * @author system
     * @since 2014-03-20 11:03:23
     */
    public final List<SysDictionary> getSysDictionaryByIds(final String ids) {
        if (ids == null || ids.trim().length() == 0) {
            LOGGER.error("对象系统字典的id集合字符为空，无法查询对象！");
            return Collections.emptyList();
        }
        try {
            return sysDictionaryDaoImpl.getSysDictionaryByIds(ids);
        } catch (Exception e) {
            LOGGER.error("", e);
            return Collections.emptyList();
        }
    }

    /**
     * 根据系统字典对象的id字符集合删除系统字典对象
     * 
     * @param ids
     *            系统字典id字符集合(如果多个使用,分割)
     * @return 删除系统字典对象的数目
     * @author NINGPAI_xiaomm
     * @since 2014-03-20 11:03:23
     */
    public final int deleteSysDictionary(final String ids) {
        if (ids == null || ids.trim().length() == 0) {
            LOGGER.error("对象系统字典的id集合字符为空，无法查询对象！");
            return 0;
        }
        try {
            return sysDictionaryDaoImpl.deleteSysDictionary(ids);
        } catch (Exception e) {
            LOGGER.error("", e);
            return 0;
        }
    }

    /**
     * 更新系统字典对象的单个字段 其中要包含ids键，保存更新对象的id（多个id以，号分割）
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 更新系统字典对象的数目
     * @author NINGPAI_xiaomm
     * @since 2014-03-20 11:03:23
     */
    public final int updateSysDictionaryFieldById(final Map<String, Object> parameter) {
        if (parameter == null || parameter.isEmpty()) {
            LOGGER.error("参数Map为空，无法执行更新系统字典对象的单个字段！");
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
            return sysDictionaryDaoImpl.updateSysDictionaryFieldById(parameter);
        } catch (Exception e) {
            LOGGER.error("", e);
            return 0;
        }
    }

    /**
     * 根据系统字典对象的单个字段查询系统字典对象信息总数 其中要包含ids键，保存更新对象的id（多个id以，号分割） 如果查询中有时间条件
     * 则字段名格式为：开始时间（字段名Start），结束时间（字段名End）
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 系统字典对象信息总数
     * @author NINGPAI_xiaomm
     * @since 2014-03-20 11:03:23
     */
    public final int getSysDictionaryByFieldTotal(final Map<String, Object> parameter) {
        if (parameter == null || parameter.isEmpty()) {
            LOGGER.error("参数Map为空，无法执行根据系统字典对象的单个字段查询系统字典对象信息总数！");
            return 0;
        }
        try {
            return sysDictionaryDaoImpl.getSysDictionaryByFieldTotal(parameter);
        } catch (Exception e) {
            LOGGER.error("", e);
            return 0;
        }
    }

    /**
     * 根据系统字典对象的单个字段查询系统字典对象信息 其中要包含ids键，保存更新对象的id（多个id以，号分割）
     * 如果查询中有时间条件，则字段名格式为：开始时间（字段名Start），结束时间（字段名End）
     * 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 系统字典对象的集合
     * @author NINGPAI_xiaomm
     * @since 2014-03-20 11:03:23
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public final PageBean getSysDictionaryByField(final Map<String, Object> parameter, final PageBean pageBean) {
        if (parameter == null) {
            LOGGER.error("参数Map为空，无法执行根据系统字典对象的单个字段查询系统字典对象信息！");
            return null;
        }
        int no = 0;
        try {
            // 设置总行数
            pageBean.setRows(getSysDictionaryByFieldTotal(parameter));
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
            pageBean.setList((List) sysDictionaryDaoImpl.getSysDictionaryByField(parameter));
        } catch (Exception e) {
            LOGGER.error("", e);
            return null;
        }
        return pageBean;
    }

    /**
     * 查询系统字典对象信息总数
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 系统字典对象信息总数
     * @author NINGPAI_xiaomm
     * @since 2014-03-20 11:03:23
     */
    public final int querySysDictionaryTotal(final Map<String, Object> parameter) {
        if (parameter == null) {
            LOGGER.error("参数Map为空，无法执行查询系统字典对象信息总数！");
            return 0;
        }
        try {
            return sysDictionaryDaoImpl.querySysDictionaryTotal(parameter);
        } catch (Exception e) {
            LOGGER.error("", e);
            return 0;
        }
    }

    /**
     * 分页查询系统字典对象信息 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @param pageBean
     *            分页对象
     * @return 系统字典对象的集合
     * @author NINGPAI_xiaomm
     * @since 2014-03-20 11:03:23
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public final PageBean querySysDictionaryByPage(final Map<String, Object> parameter, final PageBean pageBean) {
        if (parameter == null) {
            LOGGER.error("参数Map为空，无法执行分页查询系统字典对象信息！");
            return null;
        }
        int no = 0;
        try {
            // 设置总行数
            pageBean.setRows(querySysDictionaryTotal(parameter));
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
            pageBean.setList((List) sysDictionaryDaoImpl.querySysDictionaryByPage(parameter));
        } catch (Exception e) {
            LOGGER.error("", e);
            return null;
        }
        return pageBean;
    }

}
