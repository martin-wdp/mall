package com.ningpai.system.service.impl;

import com.ningpai.system.bean.PricePrecisionCof;
import com.ningpai.system.dao.IPricePrecisionCofDao;
import com.ningpai.system.service.IPricePrecisionCofBiz;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 价格精度设置业务类
 * 
 * @Description:
 * @author NINGPAI_xiaomm
 * @since 2014-03-20 17:16:10
 * @version V1.0
 */
@Service("pricePrecisionCofBizImpl")
public class PricePrecisionCofBizImpl implements IPricePrecisionCofBiz {

    /** 日志记录 */
    private static final MyLogger LOGGER = new MyLogger(PricePrecisionCofBizImpl.class);
    /** 价格精度设置数据操作类 */
    private IPricePrecisionCofDao pricePrecisionCofDaoImpl;

    /**
     * 获得价格精度设置数据操作类
     * 
     * @return 价格精度设置数据操作类
     */
    public final IPricePrecisionCofDao getPricePrecisionCofDaoImpl() {
        return pricePrecisionCofDaoImpl;
    }

    /**
     * 自动注入赋值价格精度设置数据操作类
     * 
     * @param pricePrecisionCofDaoImpl
     *            价格精度设置数据操作类
     */
    @Resource(name = "pricePrecisionCofDaoImpl")
    public final void setPricePrecisionCofDaoImpl(final IPricePrecisionCofDao pricePrecisionCofDaoImpl) {
        this.pricePrecisionCofDaoImpl = pricePrecisionCofDaoImpl;
    }

    /**
     * 保存价格精度设置
     * 
     * @param pricePrecisionCof
     *            价格精度设置对象
     * @return 是否保存成功
     * @author NINGPAI_xiaomm
     * @since 2014-03-20 17:16:10
     */
    public final boolean savePricePrecisionCof(final PricePrecisionCof pricePrecisionCof) {
        if (pricePrecisionCof == null) {
            LOGGER.error("价格精度设置对象为空，无法执行保存操作！");
            return false;
        }
        try {
            return pricePrecisionCofDaoImpl.savePricePrecisionCof(pricePrecisionCof);
        } catch (Exception e) {
            LOGGER.error("", e);
            return false;
        }
    }

    /**
     * 修改价格精度设置
     * 
     * @param pricePrecisionCof
     *            待修改价格精度设置对象
     * @return 更新影响的条数
     * @author NINGPAI_xiaomm
     * @since 2014-03-20 17:16:10
     */
    public final int updatePricePrecisionCof(final PricePrecisionCof pricePrecisionCof) {
        if (pricePrecisionCof == null) {
            LOGGER.error("价格精度设置对象为空，无法执行修改操作！");
            return 0;
        }
        try {
            return pricePrecisionCofDaoImpl.updatePricePrecisionCof(pricePrecisionCof);
        } catch (Exception e) {
            LOGGER.error("", e);
            return 0;
        }
    }

    /**
     * 根据价格精度设置对象的id查询价格精度设置对象
     * 
     * @param id
     *            价格精度设置id
     * @return 价格精度设置对象
     * @author NINGPAI_xiaomm
     * @since 2014-03-20 17:16:10
     */
    public final PricePrecisionCof getPricePrecisionCofById(final int id) {
        if (id == 0) {
            LOGGER.error("对象价格精度设置的id为0，无法查询对象！");
            return null;
        }
        try {
            return pricePrecisionCofDaoImpl.getPricePrecisionCofById(id);
        } catch (Exception e) {
            LOGGER.error("", e);
            return null;
        }
    }

    /**
     * 根据价格精度设置对象的id字符集合查询价格精度设置对象
     * 
     * @param ids
     *            价格精度设置id字符集合(如果多个使用,分割)
     * @return 价格精度设置对象集合
     * @author system
     * @since 2014-03-20 17:16:10
     */
    public final List<PricePrecisionCof> getPricePrecisionCofByIds(final String ids) {
        if (ids == null || ids.trim().length() == 0) {
            LOGGER.error("对象价格精度设置的id集合字符为空，无法查询对象！");
            return Collections.emptyList();
        }
        try {
            return pricePrecisionCofDaoImpl.getPricePrecisionCofByIds(ids);
        } catch (Exception e) {
            LOGGER.error("", e);
            return Collections.emptyList();
        }
    }

    /**
     * 根据价格精度设置对象的id字符集合删除价格精度设置对象
     * 
     * @param ids
     *            价格精度设置id字符集合(如果多个使用,分割)
     * @return 删除价格精度设置对象的数目
     * @author NINGPAI_xiaomm
     * @since 2014-03-20 17:16:10
     */
    public final int deletePricePrecisionCof(final String ids) {
        if (ids == null || ids.trim().length() == 0) {
            LOGGER.error("对象价格精度设置的id集合字符为空，无法查询对象！");
            return 0;
        }
        try {
            return pricePrecisionCofDaoImpl.deletePricePrecisionCof(ids);
        } catch (Exception e) {
            LOGGER.error("", e);
            return 0;
        }
    }

    /**
     * 更新价格精度设置对象的单个字段 其中要包含ids键，保存更新对象的id（多个id以，号分割）
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 更新价格精度设置对象的数目
     * @author NINGPAI_xiaomm
     * @since 2014-03-20 17:16:10
     */
    public final int updatePricePrecisionCofFieldById(final Map<String, Object> parameter) {
        if (parameter == null || parameter.isEmpty()) {
            LOGGER.error("参数Map为空，无法执行更新价格精度设置对象的单个字段！");
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
            return pricePrecisionCofDaoImpl.updatePricePrecisionCofFieldById(parameter);
        } catch (Exception e) {
            LOGGER.error("", e);
            return 0;
        }
    }

    /**
     * 根据价格精度设置对象的单个字段查询价格精度设置对象信息总数 其中要包含ids键，保存更新对象的id（多个id以，号分割） 如果查询中有时间条件
     * 则字段名格式为：开始时间（字段名Start），结束时间（字段名End）
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 价格精度设置对象信息总数
     * @author NINGPAI_xiaomm
     * @since 2014-03-20 17:16:10
     */
    public final int getPricePrecisionCofByFieldTotal(final Map<String, Object> parameter) {
        if (parameter == null || parameter.isEmpty()) {
            LOGGER.error("参数Map为空，无法执行根据价格精度设置对象的单个字段查询价格精度设置对象信息总数！");
            return 0;
        }
        try {
            return pricePrecisionCofDaoImpl.getPricePrecisionCofByFieldTotal(parameter);
        } catch (Exception e) {
            LOGGER.error("", e);
            return 0;
        }
    }

    /**
     * 根据价格精度设置对象的单个字段查询价格精度设置对象信息 其中要包含ids键，保存更新对象的id（多个id以，号分割）
     * 如果查询中有时间条件，则字段名格式为：开始时间（字段名Start），结束时间（字段名End）
     * 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 价格精度设置对象的集合
     * @author NINGPAI_xiaomm
     * @since 2014-03-20 17:16:10
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public final PageBean getPricePrecisionCofByField(final Map<String, Object> parameter, final PageBean pageBean) {
        if (parameter == null) {
            LOGGER.error("参数Map为空，无法执行根据价格精度设置对象的单个字段查询价格精度设置对象信息！");
            return null;
        }
        int no = 0;
        try {
            // 设置总行数
            pageBean.setRows(getPricePrecisionCofByFieldTotal(parameter));
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
            pageBean.setList((List) pricePrecisionCofDaoImpl.getPricePrecisionCofByField(parameter));
        } catch (Exception e) {
            LOGGER.error("", e);
            return null;
        }
        return pageBean;
    }

    /**
     * 查询价格精度设置对象信息总数
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 价格精度设置对象信息总数
     * @author NINGPAI_xiaomm
     * @since 2014-03-20 17:16:10
     */
    public final int queryPricePrecisionCofTotal(final Map<String, Object> parameter) {
        if (parameter == null) {
            LOGGER.error("参数Map为空，无法执行查询价格精度设置对象信息总数！");
            return 0;
        }
        try {
            return pricePrecisionCofDaoImpl.queryPricePrecisionCofTotal(parameter);
        } catch (Exception e) {
            LOGGER.error("", e);
            return 0;
        }
    }

    /**
     * 分页查询价格精度设置对象信息 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @param pageBean
     *            分页对象
     * @return 价格精度设置对象的集合
     * @author NINGPAI_xiaomm
     * @since 2014-03-20 17:16:10
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public final PageBean queryPricePrecisionCofByPage(final Map<String, Object> parameter, final PageBean pageBean) {
        if (parameter == null) {
            LOGGER.error("参数Map为空，无法执行分页查询价格精度设置对象信息！");
            return null;
        }
        int no = 0;
        try {
            // 设置总行数
            pageBean.setRows(queryPricePrecisionCofTotal(parameter));
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
            pageBean.setList((List) pricePrecisionCofDaoImpl.queryPricePrecisionCofByPage(parameter));
        } catch (Exception e) {
            LOGGER.error("", e);
            return null;
        }
        return pageBean;
    }

}
