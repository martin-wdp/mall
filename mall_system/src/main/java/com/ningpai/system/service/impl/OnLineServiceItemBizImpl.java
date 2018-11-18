package com.ningpai.system.service.impl;

import com.ningpai.system.bean.OnLineServiceItem;
import com.ningpai.system.dao.IOnLineServiceItemDao;
import com.ningpai.system.service.IOnLineServiceItemBiz;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 在线客服项业务类
 * 
 * @Description:
 * @author NINGPAI_xiaomm
 * @since 2014-03-27 17:44:30
 * @version V1.0
 */
@Service("onLineServiceItemBizImpl")
public class OnLineServiceItemBizImpl implements IOnLineServiceItemBiz {

    /** 日志记录 */
    private static final MyLogger LOGGER = new MyLogger(OnLineServiceItemBizImpl.class);
    /** 在线客服项数据操作类 */
    private IOnLineServiceItemDao onLineServiceItemDaoImpl;

    /**
     * 获得在线客服项数据操作类
     * 
     * @return 在线客服项数据操作类
     */
    public final IOnLineServiceItemDao getOnLineServiceItemDaoImpl() {
        return onLineServiceItemDaoImpl;
    }

    /**
     * 自动注入赋值在线客服项数据操作类
     * 
     * @param onLineServiceItemDaoImpl
     *            在线客服项数据操作类
     */
    @Resource(name = "onLineServiceItemDaoImpl")
    public final void setOnLineServiceItemDaoImpl(final IOnLineServiceItemDao onLineServiceItemDaoImpl) {
        this.onLineServiceItemDaoImpl = onLineServiceItemDaoImpl;
    }

    /**
     * 保存在线客服项
     * 
     * @param onLineServiceItem
     *            在线客服项对象
     * @return 是否保存成功
     * @author NINGPAI_xiaomm
     * @since 2014-03-27 17:44:30
     */
    public final boolean saveOnLineServiceItem(final OnLineServiceItem onLineServiceItem) {
        if (onLineServiceItem == null) {
            LOGGER.error("在线客服项对象为空，无法执行保存操作！");
            return false;
        }
        try {
            return onLineServiceItemDaoImpl.saveOnLineServiceItem(onLineServiceItem);
        } catch (Exception e) {
            LOGGER.error("",e);
            return false;
        }
    }

    /**
     * 修改在线客服项
     * 
     * @param onLineServiceItem
     *            待修改在线客服项对象
     * @return 更新影响的条数
     * @author NINGPAI_xiaomm
     * @since 2014-03-27 17:44:30
     */
    public final int updateOnLineServiceItem(final OnLineServiceItem onLineServiceItem) {
        if (onLineServiceItem == null) {
            LOGGER.error("在线客服项对象为空，无法执行修改操作！");
            return 0;
        }
        try {
            return onLineServiceItemDaoImpl.updateOnLineServiceItem(onLineServiceItem);
        } catch (Exception e) {
            LOGGER.error("修改在线客服项出错！", e);
            return 0;
        }
    }

    /**
     * 根据在线客服项对象的id查询在线客服项对象
     * 
     * @param id
     *            在线客服项id
     * @return 在线客服项对象
     * @author NINGPAI_xiaomm
     * @since 2014-03-27 17:44:30
     */
    public final OnLineServiceItem getOnLineServiceItemById(final int id) {
        if (id == 0) {
            LOGGER.error("对象在线客服项的id为0，无法查询对象！");
            return null;
        }
        try {
            return onLineServiceItemDaoImpl.getOnLineServiceItemById(id);
        } catch (Exception e) {
            LOGGER.error("查询在线客服项对象出错！", e);
            return null;
        }
    }

    /**
     * 根据在线客服项对象的id字符集合查询在线客服项对象
     * 
     * @param ids
     *            在线客服项id字符集合(如果多个使用,分割)
     * @return 在线客服项对象集合
     * @author system
     * @since 2014-03-27 17:44:30
     */
    public final List<OnLineServiceItem> getOnLineServiceItemByIds(final String ids) {
        if (ids == null || ids.trim().length() == 0) {
            LOGGER.error("对象在线客服项的id集合字符为空，无法查询对象！");
            return Collections.emptyList();
        }
        try {
            return onLineServiceItemDaoImpl.getOnLineServiceItemByIds(ids);
        } catch (Exception e) {
            LOGGER.error("查询在线客服项对象出错！", e);
            return Collections.emptyList();
        }
    }

    /**
     * 根据在线客服项对象的id字符集合删除在线客服项对象
     * 
     * @param ids
     *            在线客服项id字符集合(如果多个使用,分割)
     * @return 删除在线客服项对象的数目
     * @author NINGPAI_xiaomm
     * @since 2014-03-27 17:44:30
     */
    public final int deleteOnLineServiceItem(final String ids) {
        if (ids == null || ids.trim().length() == 0) {
            LOGGER.error("对象在线客服项的id集合字符为空，无法查询对象！");
            return 0;
        }
        try {
            return onLineServiceItemDaoImpl.deleteOnLineServiceItem(ids);
        } catch (Exception e) {
            LOGGER.error("删除在线客服项对象出错！", e);
            return 0;
        }
    }

    /**
     * 更新在线客服项对象的单个字段 其中要包含ids键，保存更新对象的id（多个id以，号分割）
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 更新在线客服项对象的数目
     * @author NINGPAI_xiaomm
     * @since 2014-03-27 17:44:30
     */
    public final int updateOnLineServiceItemFieldById(final Map<String, Object> parameter) {
        if (parameter == null || parameter.isEmpty()) {
            LOGGER.error("参数Map为空，无法执行更新在线客服项对象的单个字段！");
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
            return onLineServiceItemDaoImpl.updateOnLineServiceItemFieldById(parameter);
        } catch (Exception e) {
            LOGGER.error("更新在线客服项对象出错！", e);
            return 0;
        }
    }

    /**
     * 根据在线客服项对象的单个字段查询在线客服项对象信息总数 其中要包含ids键，保存更新对象的id（多个id以，号分割） 如果查询中有时间条件
     * 则字段名格式为：开始时间（字段名Start），结束时间（字段名End）
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 在线客服项对象信息总数
     * @author NINGPAI_xiaomm
     * @since 2014-03-27 17:44:30
     */
    public final int getOnLineServiceItemByFieldTotal(final Map<String, Object> parameter) {
        if (parameter == null || parameter.isEmpty()) {
            LOGGER.error("参数Map为空，无法执行根据在线客服项对象的单个字段查询在线客服项对象信息总数！");
            return 0;
        }
        try {
            return onLineServiceItemDaoImpl.getOnLineServiceItemByFieldTotal(parameter);
        } catch (Exception e) {
            LOGGER.error("查询在线客服项对象信息总数出错！", e);
            return 0;
        }
    }

    /**
     * 根据在线客服项对象的单个字段查询在线客服项对象信息 其中要包含ids键，保存更新对象的id（多个id以，号分割）
     * 如果查询中有时间条件，则字段名格式为：开始时间（字段名Start），结束时间（字段名End）
     * 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 在线客服项对象的集合
     * @author NINGPAI_xiaomm
     * @since 2014-03-27 17:44:30
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public final PageBean getOnLineServiceItemByField(final Map<String, Object> parameter, final PageBean pageBean) {
        if (parameter == null) {
            LOGGER.error("参数Map为空，无法执行根据在线客服项对象的单个字段查询在线客服项对象信息！");
            return null;
        }
        int no = 0;
        try {
            // 设置总行数
            pageBean.setRows(getOnLineServiceItemByFieldTotal(parameter));
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
            pageBean.setList((List) onLineServiceItemDaoImpl.getOnLineServiceItemByField(parameter));
        } catch (Exception e) {
            LOGGER.error("查询在线客服项对象信息出错！", e);
            return null;
        }
        return pageBean;
    }

    /**
     * 查询在线客服项对象信息总数
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 在线客服项对象信息总数
     * @author NINGPAI_xiaomm
     * @since 2014-03-27 17:44:30
     */
    public final int queryOnLineServiceItemTotal(final Map<String, Object> parameter) {
        if (parameter == null) {
            LOGGER.error("参数Map为空，无法执行查询在线客服项对象信息总数！");
            return 0;
        }
        try {
            return onLineServiceItemDaoImpl.queryOnLineServiceItemTotal(parameter);
        } catch (Exception e) {
            LOGGER.error("查询在线客服项对象信息总数出错！", e);
            return 0;
        }
    }

    /**
     * 分页查询在线客服项对象信息 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @param pageBean
     *            分页对象
     * @return 在线客服项对象的集合
     * @author NINGPAI_xiaomm
     * @since 2014-03-27 17:44:30
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public final PageBean queryOnLineServiceItemByPage(final Map<String, Object> parameter, final PageBean pageBean) {
        if (parameter == null) {
            LOGGER.error("参数Map为空，无法执行分页查询在线客服项对象信息！");
            return null;
        }
        int no = 0;
        try {
            // 设置总行数
            pageBean.setRows(queryOnLineServiceItemTotal(parameter));
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
            pageBean.setList((List) onLineServiceItemDaoImpl.queryOnLineServiceItemByPage(parameter));
        } catch (Exception e) {
            LOGGER.error("分页查询在线客服项对象信息出错！", e);
            return null;
        }
        return pageBean;
    }

    /**
     * 根据在线客服id删除在线客服项
     * 
     * @param onLineServiceId
     *            在线客服id
     * @return 删除的个数
     */
    public int deleteOnLineServiceItem(int onLineServiceId) {
        if (onLineServiceId == 0) {
            LOGGER.error("在线客服id为0，无法执行根据在线客服id删除在线客服项！");
            return 0;
        }
        try {
            return onLineServiceItemDaoImpl.deleteOnLineServiceItem(onLineServiceId);
        } catch (Exception e) {
            LOGGER.error("删除在线客服项对象信息出错！", e);
            return 0;
        }
    }

    /*
     * 根据在线客服信息ID查询客服数量
     * 
     * @see
     * com.ningpai.system.service.IOnLineServiceItemBiz#selectCountByOnLineService
     * (int)
     */
    @Override
    public int selectCountByOnLineService(int onLineServiceId) {
        return onLineServiceItemDaoImpl.selectCountByOnLineService(onLineServiceId);
    }

    /*
     * 根据主键删除
     * 
     * @see
     * com.ningpai.system.service.IOnLineServiceItemBiz#delOnLineServiceItem
     * (int)
     */
    @Override
    public int delOnLineServiceItem(int onLineServiceItemId) {
        return onLineServiceItemDaoImpl.delOnLineServiceItem(onLineServiceItemId);
    }

    /*
     * 排序上移
     * 
     * @see com.ningpai.system.service.IOnLineServiceItemBiz#upItem(int, int)
     */
    @Override
    public boolean upItem(int id1, int id2) {
        int n = 0;
        try {
            OnLineServiceItem item1 = onLineServiceItemDaoImpl.getOnLineServiceItemById(id1);
            OnLineServiceItem item2 = onLineServiceItemDaoImpl.getOnLineServiceItemById(id2);
            int tempSort = item1.getOnlineSort();
            item1.setOnlineSort(item2.getOnlineSort());
            item2.setOnlineSort(tempSort);
            n = onLineServiceItemDaoImpl.updateOnLineServiceItem(item1);
            n = n + onLineServiceItemDaoImpl.updateOnLineServiceItem(item2);
        } catch (Exception e) {
            LOGGER.error("客服项上移出错：", e);
        }
        return n >= 2 ? true : false;
    }

    /*
     * 排序下移
     * 
     * @see com.ningpai.system.service.IOnLineServiceItemBiz#downItem(int, int)
     */
    @Override
    public boolean downItem(int id1, int id2) {
        int n = 0;
        try {
            OnLineServiceItem item1 = onLineServiceItemDaoImpl.getOnLineServiceItemById(id1);
            OnLineServiceItem item2 = onLineServiceItemDaoImpl.getOnLineServiceItemById(id2);
            int tempSort = item1.getOnlineSort();
            item1.setOnlineSort(item2.getOnlineSort());
            item2.setOnlineSort(tempSort);
            n = onLineServiceItemDaoImpl.updateOnLineServiceItem(item1);
            n = n + onLineServiceItemDaoImpl.updateOnLineServiceItem(item2);
        } catch (Exception e) {
            LOGGER.error("客服项下移出错：", e);
        }
        return n >= 2 ? true : false;
    }

    /***
     * 根据类型查询平台客服
     */
    public List<OnLineServiceItem> selectItemsByType(int type) {
        return onLineServiceItemDaoImpl.selectItemsByType(type);
    }
}
