package com.ningpai.system.service.impl;

import com.ningpai.system.bean.AreaPackage;
import com.ningpai.system.dao.IAreaPackageDao;
import com.ningpai.system.service.IAreaPackageBiz;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 地区设置业务类
 * 
 * @Description:
 * @author NINGPAI_xiaomm
 * @since 2014-03-25 14:04:12
 * @version V1.0
 */
@Service("areaPackageBizImpl")
public class AreaPackageBizImpl implements IAreaPackageBiz {

    /** 日志记录 */
    private static final MyLogger LOGGER = new MyLogger(
            AreaPackageBizImpl.class);
    /** 地区设置数据操作类 */
    private IAreaPackageDao areaPackageDaoImpl;

    /**
     * 获得地区设置数据操作类
     * 
     * @return 地区设置数据操作类
     */
    public IAreaPackageDao getAreaPackageDaoImpl() {
        return areaPackageDaoImpl;
    }

    /**
     * 自动注入赋值地区设置数据操作类
     * 
     * @param areaPackageDaoImpl
     *            地区设置数据操作类
     */
    @Resource(name = "areaPackageDaoImpl")
    public void setAreaPackageDaoImpl(IAreaPackageDao areaPackageDaoImpl) {
        this.areaPackageDaoImpl = areaPackageDaoImpl;
    }

    /**
     * 保存地区设置
     * 
     * @param areaPackage
     *            地区设置对象
     * @return 是否保存成功
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 14:04:12
     */
    public boolean saveAreaPackage(AreaPackage areaPackage) {
        if (areaPackage == null) {
            LOGGER.error("地区设置对象为空，无法执行保存操作！");
            return false;
        }
        try {
            return areaPackageDaoImpl.saveAreaPackage(areaPackage);
        } catch (Exception e) {
            LOGGER.error("", e);
            return false;
        }
    }

    /**
     * 修改地区设置
     * 
     * @param areaPackage
     *            待修改地区设置对象
     * @return 更新影响的条数
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 14:04:12
     */
    public int updateAreaPackage(AreaPackage areaPackage) {
        if (areaPackage == null) {
            LOGGER.error("地区设置对象为空，无法执行修改操作！");
            return 0;
        }
        // 如果默认状态开启，则先把所有记录的默认字段改为0
        if ("1".equals(areaPackage.getDefaultPackage())) {
            areaPackageDaoImpl.changeAllDefaultStatusToNot();
        }
        try {
            return areaPackageDaoImpl.updateAreaPackage(areaPackage);
        } catch (Exception e) {
            LOGGER.error("", e);
            return 0;
        }
    }

    /**
     * 根据地区设置对象的id查询地区设置对象
     * 
     * @param id
     *            地区设置id
     * @return 地区设置对象
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 14:04:12
     */
    public AreaPackage getAreaPackageById(int id) {
        if (id == 0) {
            LOGGER.error("对象地区设置的id为0，无法查询对象！");
            return null;
        }
        try {
            return areaPackageDaoImpl.getAreaPackageById(id);
        } catch (Exception e) {
            LOGGER.error("", e);
            return null;
        }
    }

    /**
     * 根据地区设置对象的id字符集合查询地区设置对象
     * 
     * @param ids
     *            地区设置id字符集合(如果多个使用,分割)
     * @return 地区设置对象集合
     * @author system
     * @since 2014-03-25 14:04:12
     */
    public List<AreaPackage> getAreaPackageByIds(String ids) {
        if (ids == null || ids.trim().length() == 0) {
            LOGGER.error("对象地区设置的id集合字符为空，无法查询对象！");
            return Collections.emptyList();
        }
        try {
            return areaPackageDaoImpl.getAreaPackageByIds(ids);
        } catch (Exception e) {
            LOGGER.error("", e);
            return Collections.emptyList();
        }
    }

    /**
     * 根据地区设置对象的id字符集合删除地区设置对象
     * 
     * @param ids
     *            地区设置id字符集合(如果多个使用,分割)
     * @return 删除地区设置对象的数目
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 14:04:12
     */
    public int deleteAreaPackage(String ids) {
        if (ids == null || ids.trim().length() == 0) {
            LOGGER.error("对象地区设置的id集合字符为空，无法查询对象！");
            return 0;
        }
        try {
            return areaPackageDaoImpl.deleteAreaPackage(ids);
        } catch (Exception e) {
            LOGGER.error("", e);
            return 0;
        }
    }

    /**
     * 更新地区设置对象的单个字段 其中要包含ids键，保存更新对象的id（多个id以，号分割）
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 更新地区设置对象的数目
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 14:04:12
     */
    public int updateAreaPackageFieldById(Map<String, Object> parameter) {
        if (parameter == null || parameter.isEmpty()) {
            LOGGER.error("参数Map为空，无法执行更新地区设置对象的单个字段！");
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
            return areaPackageDaoImpl.updateAreaPackageFieldById(parameter);
        } catch (Exception e) {
            LOGGER.error("", e);
            return 0;
        }
    }

    /**
     * 根据地区设置对象的单个字段查询地区设置对象信息总数 其中要包含ids键，保存更新对象的id（多个id以，号分割） 如果查询中有时间条件
     * 则字段名格式为：开始时间（字段名Start），结束时间（字段名End）
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 地区设置对象信息总数
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 14:04:12
     */
    public int getAreaPackageByFieldTotal(Map<String, Object> parameter) {
        if (parameter == null || parameter.isEmpty()) {
            LOGGER.error("参数Map为空，无法执行根据地区设置对象的单个字段查询地区设置对象信息总数！");
            return 0;
        }
        try {
            return areaPackageDaoImpl.getAreaPackageByFieldTotal(parameter);
        } catch (Exception e) {
            LOGGER.error("", e);
            return 0;
        }
    }

    /**
     * 根据地区设置对象的单个字段查询地区设置对象信息 其中要包含ids键，保存更新对象的id（多个id以，号分割）
     * 如果查询中有时间条件，则字段名格式为：开始时间（字段名Start），结束时间（字段名End）
     * 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 地区设置对象的集合
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 14:04:12
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public PageBean getAreaPackageByField(Map<String, Object> parameter,
            PageBean pageBean) {
        if (parameter == null) {
            LOGGER.error("参数Map为空，无法执行根据地区设置对象的单个字段查询地区设置对象信息！");
            return null;
        }
        int no = 0;
        try {
            // 设置总行数
            pageBean.setRows(getAreaPackageByFieldTotal(parameter));
            // 获得总条数
            no = pageBean.getRows() % pageBean.getPageSize() == 0 ? pageBean
                    .getRows() / pageBean.getPageSize() : (pageBean.getRows()
                    / pageBean.getPageSize() + 1);
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
            pageBean.setList((List) areaPackageDaoImpl
                    .getAreaPackageByField(parameter));
        } catch (Exception e) {
            LOGGER.error("", e);
            return null;
        }
        return pageBean;
    }

    /**
     * 查询地区设置对象信息总数
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 地区设置对象信息总数
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 14:04:12
     */
    public int queryAreaPackageTotal(Map<String, Object> parameter) {
        if (parameter == null) {
            LOGGER.error("参数Map为空，无法执行查询地区设置对象信息总数！");
            return 0;
        }
        try {
            return areaPackageDaoImpl.queryAreaPackageTotal(parameter);
        } catch (Exception e) {
            LOGGER.error("", e);
            return 0;
        }
    }

    /**
     * 分页查询地区设置对象信息 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @param pageBean
     *            分页对象
     * @return 地区设置对象的集合
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 14:04:12
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public PageBean queryAreaPackageByPage(Map<String, Object> parameter,
            PageBean pageBean) {
        if (parameter == null) {
            LOGGER.error("参数Map为空，无法执行分页查询地区设置对象信息！");
            return null;
        }
        int no = 0;
        try {
            // 设置总行数
            pageBean.setRows(queryAreaPackageTotal(parameter));
            // 获得总条数
            no = pageBean.getRows() % pageBean.getPageSize() == 0 ? pageBean
                    .getRows() / pageBean.getPageSize() : (pageBean.getRows()
                    / pageBean.getPageSize() + 1);
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
            pageBean.setList((List) areaPackageDaoImpl
                    .queryAreaPackageByPage(parameter));
        } catch (Exception e) {
            LOGGER.error("", e);
            return null;
        }
        return pageBean;
    }

    /**
     * 根据主键id删除地区包
     * 
     * @param areaPackageId
     *            地区包id
     */
    @Override
    public void deleteById(Integer areaPackageId) {
        AreaPackage areaPackage = new AreaPackage();
        areaPackage.setAreaPackageId(areaPackageId);
        areaPackage.setDeleteStatus(1);
        areaPackageDaoImpl.updateAreaPackage(areaPackage);
    }

}
