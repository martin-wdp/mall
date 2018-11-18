package com.ningpai.system.service.impl;

import com.ningpai.system.bean.ImageManager;
import com.ningpai.system.dao.IImageManagerDao;
import com.ningpai.system.service.IImageManagerBiz;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 图片管理业务类
 * 
 * @Description:
 * @author NINGPAI_xiaomm
 * @since 2014-03-24 14:33:31
 * @version V1.0
 */
@Service("imageManagerBizImpl")
public class ImageManagerBizImpl implements IImageManagerBiz {

    /** 日志记录 */
    private static final MyLogger LOGGER = new MyLogger(ImageManagerBizImpl.class);
    /** 图片管理数据操作类 */
    private IImageManagerDao imageManagerDaoImpl;

    /**
     * 获得图片管理数据操作类
     * 
     * @return 图片管理数据操作类
     */
    public final IImageManagerDao getImageManagerDaoImpl() {
        return imageManagerDaoImpl;
    }

    /**
     * 自动注入赋值图片管理数据操作类
     * 
     * @param imageManagerDaoImpl
     *            图片管理数据操作类
     */
    @Resource(name = "imageManagerDaoImpl")
    public final void setImageManagerDaoImpl(final IImageManagerDao imageManagerDaoImpl) {
        this.imageManagerDaoImpl = imageManagerDaoImpl;
    }

    /**
     * 保存图片管理
     * 
     * @param imageManager
     *            图片管理对象
     * @return 是否保存成功 @ 自定义异常
     * @author NINGPAI_xiaomm
     * @since 2014-03-24 14:33:31
     */
    public final boolean saveImageManager(final ImageManager imageManager) {
        if (imageManager == null) {
            LOGGER.error("图片管理对象为空，无法执行保存操作！");
            return false;
        }
        try {
            return imageManagerDaoImpl.saveImageManager(imageManager);
        } catch (Exception e) {
            LOGGER.error("", e);
            return false;
        }
    }

    /**
     * 修改图片管理
     * 
     * @param imageManager
     *            待修改图片管理对象
     * @return 更新影响的条数 @ 自定义异常
     * @author NINGPAI_xiaomm
     * @since 2014-03-24 14:33:31
     */
    public final int updateImageManager(final ImageManager imageManager) {
        if (imageManager == null) {
            LOGGER.error("图片管理对象为空，无法执行修改操作！");
            return 0;
        }
        try {
            return imageManagerDaoImpl.updateImageManager(imageManager);
        } catch (Exception e) {
            LOGGER.error("", e);
            return 0;
        }
    }

    /**
     * 根据图片管理对象的id查询图片管理对象
     * 
     * @param id
     *            图片管理id
     * @return 图片管理对象 @ 自定义异常
     * @author NINGPAI_xiaomm
     * @since 2014-03-24 14:33:31
     */
    public final ImageManager getImageManagerById(final int id) {
        if (id == 0) {
            LOGGER.error("对象图片管理的id为0，无法查询对象！");
            return null;
        }
        try {
            return imageManagerDaoImpl.getImageManagerById(id);
        } catch (Exception e) {
            LOGGER.error("", e);
            return null;
        }
    }

    /**
     * 根据图片管理对象的id字符集合查询图片管理对象
     * 
     * @param ids
     *            图片管理id字符集合(如果多个使用,分割)
     * @return 图片管理对象集合 @ 自定义异常
     * @author system
     * @since 2014-03-24 14:33:31
     */
    public final List<ImageManager> getImageManagerByIds(final String ids) {
        if (ids == null || ids.trim().length() == 0) {
            LOGGER.error("对象图片管理的id集合字符为空，无法查询对象！");
            return Collections.emptyList();
        }
        try {
            return imageManagerDaoImpl.getImageManagerByIds(ids);
        } catch (Exception e) {
            LOGGER.error("", e);
            return Collections.emptyList();
        }
    }

    /**
     * 根据图片管理对象的id字符集合删除图片管理对象
     * 
     * @param ids
     *            图片管理id字符集合(如果多个使用,分割)
     * @return 删除图片管理对象的数目 @ 自定义异常
     * @author NINGPAI_xiaomm
     * @since 2014-03-24 14:33:31
     */
    public final int deleteImageManager(final String ids) {
        if (ids == null || ids.trim().length() == 0) {
            LOGGER.error("对象图片管理的id集合字符为空，无法查询对象！");
            return 0;
        }
        try {
            return imageManagerDaoImpl.deleteImageManager(ids);
        } catch (Exception e) {
            LOGGER.error("", e);
            return 0;
        }
    }

    /**
     * 更新图片管理对象的单个字段 其中要包含ids键，保存更新对象的id（多个id以，号分割）
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 更新图片管理对象的数目 @ 自定义异常
     * @author NINGPAI_xiaomm
     * @since 2014-03-24 14:33:31
     */
    public final int updateImageManagerFieldById(final Map<String, Object> parameter) {
        if (parameter == null || parameter.isEmpty()) {
            LOGGER.error("参数Map为空，无法执行更新图片管理对象的单个字段！");
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
            return imageManagerDaoImpl.updateImageManagerFieldById(parameter);
        } catch (Exception e) {
            LOGGER.error("", e);
            return 0;
        }
    }

    /**
     * 根据图片管理对象的单个字段查询图片管理对象信息总数 其中要包含ids键，保存更新对象的id（多个id以，号分割） 如果查询中有时间条件
     * 则字段名格式为：开始时间（字段名Start），结束时间（字段名End）
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 图片管理对象信息总数 @ 自定义异常
     * @author NINGPAI_xiaomm
     * @since 2014-03-24 14:33:31
     */
    public final int getImageManagerByFieldTotal(final Map<String, Object> parameter) {
        if (parameter == null || parameter.isEmpty()) {
            LOGGER.error("参数Map为空，无法执行根据图片管理对象的单个字段查询图片管理对象信息总数！");
            return 0;
        }
        try {
            return imageManagerDaoImpl.getImageManagerByFieldTotal(parameter);
        } catch (Exception e) {
            LOGGER.error("", e);
            return 0;
        }
    }

    /**
     * 根据图片管理对象的单个字段查询图片管理对象信息 其中要包含ids键，保存更新对象的id（多个id以，号分割）
     * 如果查询中有时间条件，则字段名格式为：开始时间（字段名Start），结束时间（字段名End）
     * 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 图片管理对象的集合 @ 自定义异常
     * @author NINGPAI_xiaomm
     * @since 2014-03-24 14:33:31
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public final PageBean getImageManagerByField(final Map<String, Object> parameter, final PageBean pageBean) {
        if (parameter == null) {
            LOGGER.error("参数Map为空，无法执行根据图片管理对象的单个字段查询图片管理对象信息！");
            return null;
        }
        int no = 0;
        try {
            // 设置总行数
            pageBean.setRows(getImageManagerByFieldTotal(parameter));
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
            pageBean.setList((List) imageManagerDaoImpl.getImageManagerByField(parameter));
        } catch (Exception e) {
            LOGGER.error("", e);
            return null;
        }
        return pageBean;
    }

    /**
     * 查询图片管理对象信息总数
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 图片管理对象信息总数 @ 自定义异常
     * @author NINGPAI_xiaomm
     * @since 2014-03-24 14:33:31
     */
    public final int queryImageManagerTotal(final Map<String, Object> parameter) {
        if (parameter == null) {
            LOGGER.error("参数Map为空，无法执行查询图片管理对象信息总数！");
            return 0;
        }
        try {
            return imageManagerDaoImpl.queryImageManagerTotal(parameter);
        } catch (Exception e) {
            LOGGER.error("", e);
            return 0;
        }
    }

    /**
     * 分页查询图片管理对象信息 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @param pageBean
     *            分页对象
     * @return 图片管理对象的集合
     * @自定义异常对象
     * @author NINGPAI_xiaomm
     * @since 2014-03-24 14:33:31
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public final PageBean queryImageManagerByPage(final Map<String, Object> parameter, final PageBean pageBean) {
        if (parameter == null) {
            LOGGER.error("参数Map为空，无法执行分页查询图片管理对象信息！");
            return null;
        }
        int no = 0;
        try {
            // 设置总行数
            pageBean.setRows(queryImageManagerTotal(parameter));
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
            pageBean.setList((List) imageManagerDaoImpl.queryImageManagerByPage(parameter));
        } catch (Exception e) {
            LOGGER.error("", e);
            return null;
        }
        return pageBean;
    }
}
