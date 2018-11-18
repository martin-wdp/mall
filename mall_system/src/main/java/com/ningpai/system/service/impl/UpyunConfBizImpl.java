package com.ningpai.system.service.impl;

import com.ningpai.system.bean.UpyunConf;
import com.ningpai.system.dao.IUpyunConfDao;
import com.ningpai.system.service.IUpyunConfBiz;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 又拍云设置业务类
 * 
 * @Description:
 * @author NINGPAI_xiaomm
 * @since 2014-03-24 15:44:09
 * @version V1.0
 */
@Service("upyunConfBizImpl")
public class UpyunConfBizImpl implements IUpyunConfBiz {

    /** 日志记录 */
    private static final MyLogger LOGGER = new MyLogger(UpyunConfBizImpl.class);
    /** 又拍云设置数据操作类 */
    private IUpyunConfDao upyunConfDaoImpl;

    /**
     * 获得又拍云设置数据操作类
     * 
     * @return 又拍云设置数据操作类
     */
    public final IUpyunConfDao getUpyunConfDaoImpl() {
        return upyunConfDaoImpl;
    }

    /**
     * 自动注入赋值又拍云设置数据操作类
     * 
     * @param upyunConfDaoImpl
     *            又拍云设置数据操作类
     */
    @Resource(name = "upyunConfDaoImpl")
    public final void setUpyunConfDaoImpl(final IUpyunConfDao upyunConfDaoImpl) {
        this.upyunConfDaoImpl = upyunConfDaoImpl;
    }

    /**
     * 保存又拍云设置
     * 
     * @param upyunConf
     *            又拍云设置对象
     * @return 是否保存成功 @ 自定义异常
     * @author NINGPAI_xiaomm
     * @since 2014-03-24 15:44:09
     */
    public final boolean saveUpyunConf(final UpyunConf upyunConf) {
        if (upyunConf == null) {
            LOGGER.error("又拍云设置对象为空，无法执行保存操作！");
            return false;
        }
        try {
            return upyunConfDaoImpl.saveUpyunConf(upyunConf);
        } catch (Exception e) {
            LOGGER.error("", e);
            return false;
        }
    }

    /**
     * 修改又拍云设置
     * 
     * @param upyunConf
     *            待修改又拍云设置对象
     * @return 更新影响的条数 @ 自定义异常
     * @author NINGPAI_xiaomm
     * @since 2014-03-24 15:44:09
     */
    public final int updateUpyunConf(final UpyunConf upyunConf) {
        if (upyunConf == null) {
            LOGGER.error("又拍云设置对象为空，无法执行修改操作！");
            return 0;
        }
        try {
            return upyunConfDaoImpl.updateUpyunConf(upyunConf);
        } catch (Exception e) {
            LOGGER.error("", e);
            return 0;
        }
    }

    /**
     * 根据又拍云设置对象的id查询又拍云设置对象
     * 
     * @param id
     *            又拍云设置id
     * @return 又拍云设置对象 @ 自定义异常
     * @author NINGPAI_xiaomm
     * @since 2014-03-24 15:44:09
     */
    public final UpyunConf getUpyunConfById(final int id) {
        if (id == 0) {
            LOGGER.error("对象又拍云设置的id为0，无法查询对象！");
            return null;
        }
        try {
            return upyunConfDaoImpl.getUpyunConfById(id);
        } catch (Exception e) {
            LOGGER.error("", e);
            return null;
        }
    }

    /**
     * 根据又拍云设置对象的id字符集合查询又拍云设置对象
     * 
     * @param ids
     *            又拍云设置id字符集合(如果多个使用,分割)
     * @return 又拍云设置对象集合 @ 自定义异常
     * @author system
     * @since 2014-03-24 15:44:09
     */
    public final List<UpyunConf> getUpyunConfByIds(final String ids) {
        if (ids == null || ids.trim().length() == 0) {
            LOGGER.error("对象又拍云设置的id集合字符为空，无法查询对象！");
            return Collections.emptyList();
        }
        try {
            return upyunConfDaoImpl.getUpyunConfByIds(ids);
        } catch (Exception e) {
            LOGGER.error("", e);
            return Collections.emptyList();
        }
    }

    /**
     * 根据又拍云设置对象的id字符集合删除又拍云设置对象
     * 
     * @param ids
     *            又拍云设置id字符集合(如果多个使用,分割)
     * @return 删除又拍云设置对象的数目 @ 自定义异常
     * @author NINGPAI_xiaomm
     * @since 2014-03-24 15:44:09
     */
    public final int deleteUpyunConf(final String ids) {
        if (ids == null || ids.trim().length() == 0) {
            LOGGER.error("对象又拍云设置的id集合字符为空，无法查询对象！");
            return 0;
        }
        try {
            return upyunConfDaoImpl.deleteUpyunConf(ids);
        } catch (Exception e) {
            LOGGER.error("", e);
            return 0;
        }
    }

    /**
     * 更新又拍云设置对象的单个字段 其中要包含ids键，保存更新对象的id（多个id以，号分割）
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 更新又拍云设置对象的数目 @ 自定义异常
     * @author NINGPAI_xiaomm
     * @since 2014-03-24 15:44:09
     */
    public final int updateUpyunConfFieldById(final Map<String, Object> parameter) {
        if (parameter == null || parameter.isEmpty()) {
            LOGGER.error("参数Map为空，无法执行更新又拍云设置对象的单个字段！");
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
            return upyunConfDaoImpl.updateUpyunConfFieldById(parameter);
        } catch (Exception e) {
            LOGGER.error("", e);
            return 0;
        }
    }

    /**
     * 根据又拍云设置对象的单个字段查询又拍云设置对象信息总数 其中要包含ids键，保存更新对象的id（多个id以，号分割） 如果查询中有时间条件
     * 则字段名格式为：开始时间（字段名Start），结束时间（字段名End）
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 又拍云设置对象信息总数 @ 自定义异常
     * @author NINGPAI_xiaomm
     * @since 2014-03-24 15:44:09
     */
    public final int getUpyunConfByFieldTotal(final Map<String, Object> parameter) {
        if (parameter == null || parameter.isEmpty()) {
            LOGGER.error("参数Map为空，无法执行根据又拍云设置对象的单个字段查询又拍云设置对象信息总数！");
            return 0;
        }
        try {
            return upyunConfDaoImpl.getUpyunConfByFieldTotal(parameter);
        } catch (Exception e) {
            LOGGER.error("", e);
            return 0;
        }
    }

    /**
     * 根据又拍云设置对象的单个字段查询又拍云设置对象信息 其中要包含ids键，保存更新对象的id（多个id以，号分割）
     * 如果查询中有时间条件，则字段名格式为：开始时间（字段名Start），结束时间（字段名End）
     * 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 又拍云设置对象的集合 @ 自定义异常
     * @author NINGPAI_xiaomm
     * @since 2014-03-24 15:44:09
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public final PageBean getUpyunConfByField(final Map<String, Object> parameter, final PageBean pageBean) {
        if (parameter == null) {
            LOGGER.error("参数Map为空，无法执行根据又拍云设置对象的单个字段查询又拍云设置对象信息！");
            return null;
        }
        int no = 0;
        try {
            // 设置总行数
            pageBean.setRows(getUpyunConfByFieldTotal(parameter));
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
            pageBean.setList((List) upyunConfDaoImpl.getUpyunConfByField(parameter));
        } catch (Exception e) {
            LOGGER.error("", e);
            return null;
        }
        return pageBean;
    }

    /**
     * 查询又拍云设置对象信息总数
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 又拍云设置对象信息总数 @ 自定义异常
     * @author NINGPAI_xiaomm
     * @since 2014-03-24 15:44:09
     */
    public final int queryUpyunConfTotal(final Map<String, Object> parameter) {
        if (parameter == null) {
            LOGGER.error("参数Map为空，无法执行查询又拍云设置对象信息总数！");
            return 0;
        }
        try {
            return upyunConfDaoImpl.queryUpyunConfTotal(parameter);
        } catch (Exception e) {
            LOGGER.error("", e);
            return 0;
        }
    }

    /**
     * 分页查询又拍云设置对象信息 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @param pageBean
     *            分页对象
     * @return 又拍云设置对象的集合
     * @自定义异常对象
     * @author NINGPAI_xiaomm
     * @since 2014-03-24 15:44:09
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public final PageBean queryUpyunConfByPage(final Map<String, Object> parameter, final PageBean pageBean) {
        if (parameter == null) {
            LOGGER.error("参数Map为空，无法执行分页查询又拍云设置对象信息！");
            return null;
        }
        int no = 0;
        try {
            // 设置总行数
            pageBean.setRows(queryUpyunConfTotal(parameter));
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
            pageBean.setList((List) upyunConfDaoImpl.queryUpyunConfByPage(parameter));
        } catch (Exception e) {
            LOGGER.error("", e);
            return null;
        }
        return pageBean;
    }

}
