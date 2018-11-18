package com.ningpai.system.service.impl;

import com.ningpai.system.bean.OnLineService;
import com.ningpai.system.dao.IOnLineServiceDao;
import com.ningpai.system.service.IOnLineServiceBiz;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 在线客服业务类
 * 
 * @Description:
 * @author NINGPAI_xiaomm
 * @since 2014-03-27 17:40:56
 * @version V1.0
 */
@Service("onLineServiceBizImpl")
public class OnLineServiceBizImpl implements IOnLineServiceBiz {

    /** 日志记录 */
    private static final MyLogger LOGGER = new MyLogger(OnLineServiceBizImpl.class);
    /** 在线客服数据操作类 */
    private IOnLineServiceDao onLineServiceDaoImpl;

    /**
     * 获得在线客服数据操作类
     * 
     * @return 在线客服数据操作类
     */
    public final IOnLineServiceDao getOnLineServiceDaoImpl() {
        return onLineServiceDaoImpl;
    }

    /**
     * 自动注入赋值在线客服数据操作类
     * 
     * @param onLineServiceDaoImpl
     *            在线客服数据操作类
     */
    @Resource(name = "onLineServiceDaoImpl")
    public final void setOnLineServiceDaoImpl(final IOnLineServiceDao onLineServiceDaoImpl) {
        this.onLineServiceDaoImpl = onLineServiceDaoImpl;
    }

    /**
     * 保存在线客服
     * 
     * @param onLineService
     *            在线客服对象
     * @return 在线客服记录ID
     * @author NINGPAI_xiaomm
     * @since 2014-03-27 17:40:56
     */
    public final int saveOnLineService(final OnLineService onLineService) {
        if (onLineService == null) {
            LOGGER.error("在线客服对象为空，无法执行保存操作！");
            return 0;
        }
        try {
            return onLineServiceDaoImpl.saveOnLineService(onLineService);
        } catch (Exception e) {
            LOGGER.error("", e);
            return 0;
        }
    }

    /**
     * 修改在线客服
     * 
     * @param onLineService
     *            待修改在线客服对象
     * @return 更新影响的条数
     * @author NINGPAI_xiaomm
     * @since 2014-03-27 17:40:56
     */
    public final int updateOnLineService(final OnLineService onLineService) {
        if (onLineService == null) {
            LOGGER.error("在线客服对象为空，无法执行修改操作！");
            return 0;
        }
        try {
            return onLineServiceDaoImpl.updateOnLineService(onLineService);
        } catch (Exception e) {
            LOGGER.error("", e);
            return 0;
        }
    }

    /**
     * 根据在线客服对象的id查询在线客服对象
     * 
     * @param id
     *            在线客服id
     * @return 在线客服对象
     * @author NINGPAI_xiaomm
     * @since 2014-03-27 17:40:56
     */
    public final OnLineService getOnLineServiceById(final int id) {
        if (id == 0) {
            LOGGER.error("对象在线客服的id为0，无法查询对象！");
            return null;
        }
        try {
            return onLineServiceDaoImpl.getOnLineServiceById(id);
        } catch (Exception e) {
            LOGGER.error("", e);
            return null;
        }
    }

    /**
     * 根据在线客服对象的id字符集合查询在线客服对象
     * 
     * @param ids
     *            在线客服id字符集合(如果多个使用,分割)
     * @return 在线客服对象集合
     * @author system
     * @since 2014-03-27 17:40:56
     */
    public final List<OnLineService> getOnLineServiceByIds(final String ids) {
        if (ids == null || ids.trim().length() == 0) {
            LOGGER.error("对象在线客服的id集合字符为空，无法查询对象！");
            return Collections.emptyList();
        }
        try {
            return onLineServiceDaoImpl.getOnLineServiceByIds(ids);
        } catch (Exception e) {
            LOGGER.error("", e);
            return Collections.emptyList();
        }
    }

    /**
     * 根据在线客服对象的id字符集合删除在线客服对象
     * 
     * @param ids
     *            在线客服id字符集合(如果多个使用,分割)
     * @return 删除在线客服对象的数目
     * @author NINGPAI_xiaomm
     * @since 2014-03-27 17:40:56
     */
    public final int deleteOnLineService(final String ids) {
        if (ids == null || ids.trim().length() == 0) {
            LOGGER.error("对象在线客服的id集合字符为空，无法查询对象！");
            return 0;
        }
        try {
            return onLineServiceDaoImpl.deleteOnLineService(ids);
        } catch (Exception e) {
            LOGGER.error("", e);
            return 0;
        }
    }

    /**
     * 更新在线客服对象的单个字段 其中要包含ids键，保存更新对象的id（多个id以，号分割）
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 更新在线客服对象的数目
     * @author NINGPAI_xiaomm
     * @since 2014-03-27 17:40:56
     */
    public final int updateOnLineServiceFieldById(final Map<String, Object> parameter) {
        if (parameter == null || parameter.isEmpty()) {
            LOGGER.error("参数Map为空，无法执行更新在线客服对象的单个字段！");
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
            return onLineServiceDaoImpl.updateOnLineServiceFieldById(parameter);
        } catch (Exception e) {
            LOGGER.error("", e);
            return 0;
        }
    }

    /**
     * 根据在线客服对象的单个字段查询在线客服对象信息总数 其中要包含ids键，保存更新对象的id（多个id以，号分割） 如果查询中有时间条件
     * 则字段名格式为：开始时间（字段名Start），结束时间（字段名End）
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 在线客服对象信息总数
     * @author NINGPAI_xiaomm
     * @since 2014-03-27 17:40:56
     */
    public final int getOnLineServiceByFieldTotal(final Map<String, Object> parameter) {
        if (parameter == null || parameter.isEmpty()) {
            LOGGER.error("参数Map为空，无法执行根据在线客服对象的单个字段查询在线客服对象信息总数！");
            return 0;
        }
        try {
            return onLineServiceDaoImpl.getOnLineServiceByFieldTotal(parameter);
        } catch (Exception e) {
            LOGGER.error("", e);
            return 0;
        }
    }

    /**
     * 根据在线客服对象的单个字段查询在线客服对象信息 其中要包含ids键，保存更新对象的id（多个id以，号分割）
     * 如果查询中有时间条件，则字段名格式为：开始时间（字段名Start），结束时间（字段名End）
     * 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 在线客服对象的集合
     * @author NINGPAI_xiaomm
     * @since 2014-03-27 17:40:56
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public final PageBean getOnLineServiceByField(final Map<String, Object> parameter, final PageBean pageBean) {
        if (parameter == null) {
            LOGGER.error("参数Map为空，无法执行根据在线客服对象的单个字段查询在线客服对象信息！");
            return null;
        }
        int no = 0;
        try {
            // 设置总行数
            pageBean.setRows(getOnLineServiceByFieldTotal(parameter));
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
            pageBean.setList((List) onLineServiceDaoImpl.getOnLineServiceByField(parameter));
        } catch (Exception e) {
            LOGGER.error("", e);
            return null;
        }
        return pageBean;
    }

    /**
     * 查询在线客服对象信息总数
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 在线客服对象信息总数
     * @author NINGPAI_xiaomm
     * @since 2014-03-27 17:40:56
     */
    public final int queryOnLineServiceTotal(final Map<String, Object> parameter) {
        if (parameter == null) {
            LOGGER.error("参数Map为空，无法执行查询在线客服对象信息总数！");
            return 0;
        }
        try {
            return onLineServiceDaoImpl.queryOnLineServiceTotal(parameter);
        } catch (Exception e) {
            LOGGER.error("", e);
            return 0;
        }
    }

    /**
     * 分页查询在线客服对象信息 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @param pageBean
     *            分页对象
     * @return 在线客服对象的集合
     * @author NINGPAI_xiaomm
     * @since 2014-03-27 17:40:56
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public final PageBean queryOnLineServiceByPage(final Map<String, Object> parameter, final PageBean pageBean) {
        if (parameter == null) {
            LOGGER.error("参数Map为空，无法执行分页查询在线客服对象信息！");
            return null;
        }
        int no = 0;
        try {
            // 设置总行数
            pageBean.setRows(queryOnLineServiceTotal(parameter));
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
            pageBean.setList((List) onLineServiceDaoImpl.queryOnLineServiceByPage(parameter));
        } catch (Exception e) {
            LOGGER.error("", e);
            return null;
        }
        return pageBean;
    }

    /**
     * 查询在线客服设置
     * 
     * @return
     */
    @Override
    public OnLineService selectSetting() {
        return onLineServiceDaoImpl.selectSetting();
    }

}
