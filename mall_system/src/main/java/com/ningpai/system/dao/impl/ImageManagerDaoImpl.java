package com.ningpai.system.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.system.bean.ImageManager;
import com.ningpai.system.dao.IImageManagerDao;

/**
 * 图片管理数据操作类
 * 
 * @Description:
 * @author NINGPAI_xiaomm
 * @since 2014-03-24 14:33:31
 * @version V1.0
 */
@Repository("imageManagerDaoImpl")
public class ImageManagerDaoImpl extends BasicSqlSupport implements
        IImageManagerDao {

    /**
     * 保存图片管理
     * 
     * @param imageManager
     *            图片管理对象
     * @return 是否保存成功
     * @author NINGPAI_xiaomm
     * @since 2014-03-24 14:33:31
     */
    public final boolean saveImageManager(final ImageManager imageManager) {
        return this.insert(
                "com.ningpai.system.dao.ImageManagerDaoImpl.saveImageManager",
                imageManager) >= 1 ? true : false;
    }

    /**
     * 修改图片管理
     * 
     * @param imageManager
     *            待修改图片管理对象
     * @return 更新影响的条数
     * @author NINGPAI_xiaomm
     * @since 2014-03-24 14:33:31
     */
    public final int updateImageManager(final ImageManager imageManager) {
        return this
                .update("com.ningpai.system.dao.ImageManagerDaoImpl.updateImageManager",
                        imageManager);
    }

    /**
     * 根据图片管理对象的id查询图片管理对象
     * 
     * @param id
     *            图片管理id
     * @return 图片管理对象
     * @author NINGPAI_xiaomm
     * @since 2014-03-24 14:33:31
     */
    public final ImageManager getImageManagerById(final int id) {
        return this
                .selectOne(
                        "com.ningpai.system.dao.ImageManagerDaoImpl.getImageManagerById",
                        id);
    }

    /**
     * 根据图片管理对象的id字符集合查询图片管理对象
     * 
     * @param ids
     *            图片管理id字符集合(如果多个使用,分割)
     * @return 图片管理对象集合
     * @author system
     * @since 2014-03-24 14:33:31
     */
    public final List<ImageManager> getImageManagerByIds(final String ids) {
        // 将字符串ids转换为集合，用于sql赋值
        Map<String, Object> para = new HashMap<String, Object>(1);
        List<String> idList = new ArrayList<String>();
        if (ids.contains(",")) {
            for (String id : ids.split(",")) {
                idList.add(id);
            }
        } else {
            idList.add(ids);
        }
        para.put("ids", idList);
        return this
                .selectList(
                        "com.ningpai.system.dao.ImageManagerDaoImpl.getImageManagerByIds",
                        para);
    }

    /**
     * 根据图片管理对象的id字符集合删除图片管理对象
     * 
     * @param ids
     *            图片管理id字符集合(如果多个使用,分割)
     * @return 删除图片管理对象的数目
     * @author NINGPAI_xiaomm
     * @since 2014-03-24 14:33:31
     */
    public final int deleteImageManager(final String ids) {
        Map<String, Object> para = new HashMap<String, Object>(1);
        List<String> idList = new ArrayList<String>();
        if (ids.contains(",")) {
            for (String id : ids.split(",")) {
                idList.add(id);
            }
        } else {
            idList.add(ids);
        }
        para.put("ids", idList);
        return this
                .delete("com.ningpai.system.dao.ImageManagerDaoImpl.deleteImageManager",
                        para);
    }

    /**
     * 更新图片管理对象的单个字段 其中要包含ids键，保存更新对象的id（多个id以，号分割）
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 更新图片管理对象的数目
     * @author NINGPAI_xiaomm
     * @since 2014-03-24 14:33:31
     */
    public final int updateImageManagerFieldById(
            final Map<String, Object> parameter) {
        return this
                .update("com.ningpai.system.dao.ImageManagerDaoImpl.updateImageManagerFieldById",
                        parameter);
    }

    /**
     * 根据图片管理对象的单个字段查询图片管理对象信息总数 其中要包含ids键，保存更新对象的id（多个id以，号分割） 如果查询中有时间条件
     * 则字段名格式为：开始时间（字段名Start），结束时间（字段名End）
     * 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 图片管理对象信息总数
     * @author NINGPAI_xiaomm
     * @since 2014-03-24 14:33:31
     */
    public final int getImageManagerByFieldTotal(
            final Map<String, Object> parameter) {
        return this
                .selectOne(
                        "com.ningpai.system.dao.ImageManagerDaoImpl.getImageManagerByFieldTotal",
                        parameter);
    }

    /**
     * 根据图片管理对象的单个字段查询图片管理对象信息 其中要包含ids键，保存更新对象的id（多个id以，号分割）
     * 如果查询中有时间条件，则字段名格式为：开始时间（字段名Start），结束时间（字段名End）
     * 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 图片管理对象的集合
     * @author NINGPAI_xiaomm
     * @since 2014-03-24 14:33:31
     */
    public final List<ImageManager> getImageManagerByField(
            final Map<String, Object> parameter) {
        return this
                .selectList(
                        "com.ningpai.system.dao.ImageManagerDaoImpl.getImageManagerByField",
                        parameter);
    }

    /**
     * 查询图片管理对象信息总数 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 图片管理对象信息总数
     * @author NINGPAI_xiaomm
     * @since 2014-03-24 14:33:31
     */
    public final int queryImageManagerTotal(final Map<String, Object> parameter) {
        return this
                .selectOne(
                        "com.ningpai.system.dao.ImageManagerDaoImpl.queryImageManagerTotal",
                        parameter);
    }

    /**
     * 分页查询图片管理对象信息 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param _parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 图片管理对象的集合
     * @author NINGPAI_xiaomm
     * @since 2014-03-24 14:33:31
     */
    public final List<ImageManager> queryImageManagerByPage(
            final Map<String, Object> parameter) {
        return this
                .selectList(
                        "com.ningpai.system.dao.ImageManagerDaoImpl.queryImageManagerByPage",
                        parameter);
    }
}
