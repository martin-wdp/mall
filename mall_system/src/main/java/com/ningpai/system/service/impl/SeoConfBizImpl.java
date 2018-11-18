package com.ningpai.system.service.impl;

import com.ningpai.system.bean.SeoConf;
import com.ningpai.system.dao.ISeoConfDao;
import com.ningpai.system.service.ISeoConfBiz;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * SEO设置业务类
 * 
 * @Description:
 * @author NINGPAI_xiaomm
 * @since 2014-03-24 13:35:13
 * @version V1.0
 */
@Service("seoConfBizImpl")
public class SeoConfBizImpl implements ISeoConfBiz {

    /** 日志记录 */
    private static final MyLogger LOGGER = new MyLogger(SeoConfBizImpl.class);
    /** SEO设置数据操作类 */
    private ISeoConfDao seoConfDaoImpl;

    /**
     * 获得SEO设置数据操作类
     * 
     * @return SEO设置数据操作类
     */
    public final ISeoConfDao getSeoConfDaoImpl() {
        return seoConfDaoImpl;
    }

    /**
     * 自动注入赋值SEO设置数据操作类
     * 
     * @param seoConfDaoImpl
     *            SEO设置数据操作类
     */
    @Resource(name = "seoConfDaoImpl")
    public final void setSeoConfDaoImpl(final ISeoConfDao seoConfDaoImpl) {
        this.seoConfDaoImpl = seoConfDaoImpl;
    }

    /**
     * 保存SEO设置
     * 
     * @param seoConf
     *            SEO设置对象
     * @return 是否保存成功
     * @author NINGPAI_xiaomm
     * @since 2014-03-24 13:35:13
     */
    public final boolean saveSeoConf(final SeoConf seoConf) {
        if (seoConf == null) {
            LOGGER.error("SEO设置对象为空，无法执行保存操作！");
            return false;
        }
        try {
            if ("1".equals(seoConf.getUsedStatus())) {
                seoConfDaoImpl.updateSeoConfByUsedStatus();
            }
            return seoConfDaoImpl.saveSeoConf(seoConf);
        } catch (Exception e) {
            LOGGER.info(e);
            return false;
        }
    }

    /**
     * 修改SEO设置
     * 
     * @param seoConf
     *            待修改SEO设置对象
     * @return 更新影响的条数
     * @author NINGPAI_xiaomm
     * @since 2014-03-24 13:35:13
     */
    public final int updateSeoConf(final SeoConf seoConf) {
        if (seoConf == null) {
            LOGGER.error("SEO设置对象为空，无法执行修改操作！");
            return 0;
        }
        try {
            return seoConfDaoImpl.updateSeoConf(seoConf);
        } catch (Exception e) {
            LOGGER.info(e);
            return 0;
        }
    }

    /**
     * 创建
     */
    @Override
    public void createRoborts(SeoConf seoConf, HttpServletRequest request) {
        // 根目录
        String path = request.getSession().getServletContext().getRealPath("/");
        // 服务器qpmall前台路径
        // String path2 = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/";
        // newboss和qpmall的前台在同一个服务器上,取得qpmall前台的上一级目录
        String qpmallpath = new File(path).getParent();
        // 得到qpmall前台的根目录
        File fl = new File(qpmallpath + "/site/robots.txt");
        try {
            if (!fl.getParentFile().exists()) {
                fl.getParentFile().mkdirs();
            }
            if (!fl.exists()) {

                fl.createNewFile();
            }
            FileWriter flw = new FileWriter(fl);
            flw.write("");
            flw.write(seoConf.getMeteDes());
            flw.append("\nSitemap:" + basePath + "sitemap.xml");

            flw.close();
        } catch (IOException e) {
            LOGGER.info(e);
        }
    }

    /**
     * 根据SEO设置对象的id查询SEO设置对象
     * 
     * @param id
     *            SEO设置id
     * @return SEO设置对象
     * @author NINGPAI_xiaomm
     * @since 2014-03-24 13:35:13
     */
    public final SeoConf getSeoConfById(final int id) {
        if (id == 0) {
            LOGGER.error("对象SEO设置的id为0，无法查询对象！");
            return null;
        }
        try {
            return seoConfDaoImpl.getSeoConfById(id);
        } catch (Exception e) {
            LOGGER.info(e);
            return null;
        }
    }

    /**
     * 根据SEO设置对象的id字符集合查询SEO设置对象
     * 
     * @param ids
     *            SEO设置id字符集合(如果多个使用,分割)
     * @return SEO设置对象集合
     * @author system
     * @since 2014-03-24 13:35:13
     */
    public final List<SeoConf> getSeoConfByIds(final String ids) {
        if (ids == null || ids.trim().length() == 0) {
            LOGGER.error("对象SEO设置的id集合字符为空，无法查询对象！");
            return Collections.emptyList();
        }
        try {
            return seoConfDaoImpl.getSeoConfByIds(ids);
        } catch (Exception e) {
            LOGGER.info(e);
            return Collections.emptyList();
        }
    }

    /**
     * 根据SEO设置对象的id字符集合删除SEO设置对象
     * 
     * @param ids
     *            SEO设置id字符集合(如果多个使用,分割)
     * @return 删除SEO设置对象的数目
     * @author NINGPAI_xiaomm
     * @since 2014-03-24 13:35:13
     */
    public final int deleteSeoConf(final String ids) {
        if (ids == null || ids.trim().length() == 0) {
            LOGGER.error("对象SEO设置的id集合字符为空，无法查询对象！");
            return 0;
        }
        try {
            return seoConfDaoImpl.deleteSeoConf(ids);
        } catch (Exception e) {
            LOGGER.info(e);
            return 0;
        }
    }

    /**
     * 更新SEO设置对象的单个字段 其中要包含ids键，保存更新对象的id（多个id以，号分割）
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 更新SEO设置对象的数目
     * @author NINGPAI_xiaomm
     * @since 2014-03-24 13:35:13
     */
    public final int updateSeoConfFieldById(final Map<String, Object> parameter) {
        if (parameter == null || parameter.isEmpty()) {
            LOGGER.error("参数Map为空，无法执行更新SEO设置对象的单个字段！");
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
            return seoConfDaoImpl.updateSeoConfFieldById(parameter);
        } catch (Exception e) {
            LOGGER.info(e);
            return 0;
        }
    }

    /**
     * 根据SEO设置对象的单个字段查询SEO设置对象信息总数 其中要包含ids键，保存更新对象的id（多个id以，号分割） 如果查询中有时间条件
     * 则字段名格式为：开始时间（字段名Start），结束时间（字段名End）
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return SEO设置对象信息总数
     * @author NINGPAI_xiaomm
     * @since 2014-03-24 13:35:13
     */
    public final int getSeoConfByFieldTotal(final Map<String, Object> parameter) {
        if (parameter == null || parameter.isEmpty()) {
            LOGGER.error("参数Map为空，无法执行根据SEO设置对象的单个字段查询SEO设置对象信息总数！");
            return 0;
        }
        try {
            return seoConfDaoImpl.getSeoConfByFieldTotal(parameter);
        } catch (Exception e) {
            LOGGER.info(e);
            return 0;
        }
    }

    /**
     * 根据SEO设置对象的单个字段查询SEO设置对象信息 其中要包含ids键，保存更新对象的id（多个id以，号分割）
     * 如果查询中有时间条件，则字段名格式为：开始时间（字段名Start），结束时间（字段名End）
     * 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return SEO设置对象的集合
     * @author NINGPAI_xiaomm
     * @since 2014-03-24 13:35:13
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public final PageBean getSeoConfByField(final Map<String, Object> parameter, final PageBean pageBean) {
        if (parameter == null) {
            LOGGER.error("参数Map为空，无法执行根据SEO设置对象的单个字段查询SEO设置对象信息！");
            return null;
        }
        int no = 0;
        try {
            // 设置总行数
            pageBean.setRows(getSeoConfByFieldTotal(parameter));
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
            pageBean.setList((List) seoConfDaoImpl.getSeoConfByField(parameter));
        } catch (Exception e) {
            LOGGER.info(e);
            return null;
        }
        return pageBean;
    }

    /**
     * 查询SEO设置对象信息总数
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return SEO设置对象信息总数
     * @author NINGPAI_xiaomm
     * @since 2014-03-24 13:35:13
     */
    public final int querySeoConfTotal(final Map<String, Object> parameter) {
        if (parameter == null) {
            LOGGER.error("参数Map为空，无法执行查询SEO设置对象信息总数！");
            return 0;
        }
        try {
            return seoConfDaoImpl.querySeoConfTotal(parameter);
        } catch (Exception e) {
            LOGGER.info(e);
            return 0;
        }
    }

    /**
     * 分页查询SEO设置对象信息 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @param pageBean
     *            分页对象
     * @return SEO设置对象的集合
     * @author NINGPAI_xiaomm
     * @since 2014-03-24 13:35:13
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public final PageBean querySeoConfByPage(final Map<String, Object> parameter, final PageBean pageBean) {
        if (parameter == null) {
            LOGGER.error("参数Map为空，无法执行分页查询SEO设置对象信息！");
            return null;
        }
        int no = 0;
        try {
            // 设置总行数
            pageBean.setRows(querySeoConfTotal(parameter));
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
            pageBean.setList((List) seoConfDaoImpl.querySeoConfByPage(parameter));
        } catch (Exception e) {
            LOGGER.info(e);
            return null;
        }
        return pageBean;
    }

    /*
     * 获取已启用的SEO设置对象
     * 
     * @see com.ningpai.system.service.ISeoConfBiz#querySeoByUsedStatus()
     */
    @Override
    public SeoConf querySeoByUsedStatus() {
        return seoConfDaoImpl.querySeoByUsedStatus();
    }

    /**
     * 修改启用状态
     */
    @Override
    public int updateSeoConfByUsedStatus() {
        return seoConfDaoImpl.updateSeoConfByUsedStatus();
    }

    /*
     * 删除SEO记录
     * 
     * @see com.ningpai.system.service.ISeoConfBiz#deleteSeoConf(java.lang.Long)
     */
    @Override
    public int deleteSeoConf(Long seoId) {
        return seoConfDaoImpl.deleteSeoByPrimaryKey(seoId);
    }
}
