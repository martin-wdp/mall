package com.ningpai.system.service.impl;

import com.ningpai.system.bean.ShopConf;
import com.ningpai.system.dao.IShopConfDao;
import com.ningpai.system.service.IShopConfBiz;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 购物设置业务类
 * 
 * @Description:
 * @author NINGPAI_xiaomm
 * @since 2014-03-24 17:58:48
 * @version V1.0
 */
@Service("shopConfBizImpl")
public class ShopConfBizImpl implements IShopConfBiz {

    /** 日志记录 */
    private static final MyLogger LOGGER = new MyLogger(ShopConfBizImpl.class);
    /** 购物设置数据操作类 */
    private IShopConfDao shopConfDaoImpl;

    /**
     * 获得购物设置数据操作类
     * 
     * @return 购物设置数据操作类
     */
    public final IShopConfDao getShopConfDaoImpl() {
        return shopConfDaoImpl;
    }

    /**
     * 自动注入赋值购物设置数据操作类
     * 
     * @param shopConfDaoImpl
     *            购物设置数据操作类
     */
    @Resource(name = "shopConfDaoImpl")
    public final void setShopConfDaoImpl(final IShopConfDao shopConfDaoImpl) {
        this.shopConfDaoImpl = shopConfDaoImpl;
    }

    /**
     * 保存购物设置
     * 
     * @param shopConf
     *            购物设置对象
     * @return 是否保存成功 @ 自定义异常
     * @author NINGPAI_xiaomm
     * @since 2014-03-24 17:58:48
     */
    public final boolean saveShopConf(final ShopConf shopConf) {
        if (shopConf == null) {
            LOGGER.error("购物设置对象为空，无法执行保存操作！");
            return false;
        }
        try {
            return shopConfDaoImpl.saveShopConf(shopConf);
        } catch (Exception e) {
            LOGGER.error("", e);
            return false;
        }
    }

    /**
     * 修改购物设置
     * 
     * @param shopConf
     *            待修改购物设置对象
     * @return 更新影响的条数 @ 自定义异常
     * @author NINGPAI_xiaomm
     * @since 2014-03-24 17:58:48
     */
    public final int updateShopConf(final ShopConf shopConf) {
        if (shopConf == null) {
            LOGGER.error("购物设置对象为空，无法执行修改操作！");
            return 0;
        }
        try {
            return shopConfDaoImpl.updateShopConf(shopConf);
        } catch (Exception e) {
            LOGGER.error("", e);
            return 0;
        }
    }

    /**
     * 根据购物设置对象的id查询购物设置对象
     * 
     * @param id
     *            购物设置id
     * @return 购物设置对象 @ 自定义异常
     * @author NINGPAI_xiaomm
     * @since 2014-03-24 17:58:48
     */
    public final ShopConf getShopConfById(final int id) {
        if (id == 0) {
            LOGGER.error("对象购物设置的id为0，无法查询对象！");
            return null;
        }
        try {
            return shopConfDaoImpl.getShopConfById(id);
        } catch (Exception e) {
            LOGGER.error("", e);
            return null;
        }
    }

    /**
     * 根据购物设置对象的id字符集合查询购物设置对象
     * 
     * @param ids
     *            购物设置id字符集合(如果多个使用,分割)
     * @return 购物设置对象集合 @ 自定义异常
     * @author system
     * @since 2014-03-24 17:58:48
     */
    public final List<ShopConf> getShopConfByIds(final String ids) {
        if (ids == null || ids.trim().length() == 0) {
            LOGGER.error("对象购物设置的id集合字符为空，无法查询对象！");
            return Collections.emptyList();
        }
        try {
            return shopConfDaoImpl.getShopConfByIds(ids);
        } catch (Exception e) {
            LOGGER.error("", e);
            return Collections.emptyList();
        }
    }

    /**
     * 根据购物设置对象的id字符集合删除购物设置对象
     * 
     * @param ids
     *            购物设置id字符集合(如果多个使用,分割)
     * @return 删除购物设置对象的数目 @ 自定义异常
     * @author NINGPAI_xiaomm
     * @since 2014-03-24 17:58:48
     */
    public final int deleteShopConf(final String ids) {
        if (ids == null || ids.trim().length() == 0) {
            LOGGER.error("对象购物设置的id集合字符为空，无法查询对象！");
            return 0;
        }
        try {
            return shopConfDaoImpl.deleteShopConf(ids);
        } catch (Exception e) {
            LOGGER.error("", e);
            return 0;
        }
    }

    /**
     * 更新购物设置对象的单个字段 其中要包含ids键，保存更新对象的id（多个id以，号分割）
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 更新购物设置对象的数目 @ 自定义异常
     * @author NINGPAI_xiaomm
     * @since 2014-03-24 17:58:48
     */
    public final int updateShopConfFieldById(final Map<String, Object> parameter) {
        if (parameter == null || parameter.isEmpty()) {
            LOGGER.error("参数Map为空，无法执行更新购物设置对象的单个字段！");
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
            return shopConfDaoImpl.updateShopConfFieldById(parameter);
        } catch (Exception e) {
            LOGGER.error("", e);
            return 0;
        }
    }

    /**
     * 根据购物设置对象的单个字段查询购物设置对象信息总数 其中要包含ids键，保存更新对象的id（多个id以，号分割） 如果查询中有时间条件
     * 则字段名格式为：开始时间（字段名Start），结束时间（字段名End）
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 购物设置对象信息总数 @ 自定义异常
     * @author NINGPAI_xiaomm
     * @since 2014-03-24 17:58:48
     */
    public final int getShopConfByFieldTotal(final Map<String, Object> parameter) {
        if (parameter == null || parameter.isEmpty()) {
            LOGGER.error("参数Map为空，无法执行根据购物设置对象的单个字段查询购物设置对象信息总数！");
            return 0;
        }
        try {
            return shopConfDaoImpl.getShopConfByFieldTotal(parameter);
        } catch (Exception e) {
            LOGGER.error("", e);
            return 0;
        }
    }

    /**
     * 根据购物设置对象的单个字段查询购物设置对象信息 其中要包含ids键，保存更新对象的id（多个id以，号分割）
     * 如果查询中有时间条件，则字段名格式为：开始时间（字段名Start），结束时间（字段名End）
     * 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 购物设置对象的集合 @ 自定义异常
     * @author NINGPAI_xiaomm
     * @since 2014-03-24 17:58:48
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public final PageBean getShopConfByField(final Map<String, Object> parameter, final PageBean pageBean) {
        if (parameter == null) {
            LOGGER.error("参数Map为空，无法执行根据购物设置对象的单个字段查询购物设置对象信息！");
            return null;
        }
        int no = 0;
        try {
            // 设置总行数
            pageBean.setRows(getShopConfByFieldTotal(parameter));
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
            pageBean.setList((List) shopConfDaoImpl.getShopConfByField(parameter));
        } catch (Exception e) {
            LOGGER.error("", e);
            return null;
        }
        return pageBean;
    }

    /**
     * 查询购物设置对象信息总数
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 购物设置对象信息总数 @ 自定义异常
     * @author NINGPAI_xiaomm
     * @since 2014-03-24 17:58:48
     */
    public final int queryShopConfTotal(final Map<String, Object> parameter) {
        if (parameter == null) {
            LOGGER.error("参数Map为空，无法执行查询购物设置对象信息总数！");
            return 0;
        }
        try {
            return shopConfDaoImpl.queryShopConfTotal(parameter);
        } catch (Exception e) {
            LOGGER.error("", e);
            return 0;
        }
    }

    /**
     * 分页查询购物设置对象信息 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @param pageBean
     *            分页对象
     * @return 购物设置对象的集合
     * @自定义异常对象
     * @author NINGPAI_xiaomm
     * @since 2014-03-24 17:58:48
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public final PageBean queryShopConfByPage(final Map<String, Object> parameter, final PageBean pageBean) {
        if (parameter == null) {
            LOGGER.error("参数Map为空，无法执行分页查询购物设置对象信息！");
            return null;
        }
        int no = 0;
        try {
            // 设置总行数
            pageBean.setRows(queryShopConfTotal(parameter));
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
            pageBean.setList((List) shopConfDaoImpl.queryShopConfByPage(parameter));
        } catch (Exception e) {
            LOGGER.error("", e);
            return null;
        }
        return pageBean;
    }

}
