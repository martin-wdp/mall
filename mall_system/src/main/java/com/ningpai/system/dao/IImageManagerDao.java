package com.ningpai.system.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.system.bean.ImageManager;

/**
 * 图片管理数据操作接口
 * 
 * @Description:
 * @author NINGPAI_xiaomm
 * @since 2014-03-24 14:33:31
 * @version V1.0
 */
@Repository
public interface IImageManagerDao {

    /**
     * 保存图片管理
     * 
     * @param imageManager
     *            图片管理对象
     * @return 是否保存成功
     * @author NINGPAI_xiaomm
     * @since 2014-03-24 14:33:31
     */
    boolean saveImageManager(ImageManager imageManager);

    /**
     * 修改图片管理
     * 
     * @param imageManager
     *            待修改图片管理对象
     * @return 更新影响的条数
     * @author NINGPAI_xiaomm
     * @since 2014-03-24 14:33:31
     */
    int updateImageManager(ImageManager imageManager);

    /**
     * 根据图片管理对象的id查询图片管理对象
     * 
     * @param id
     *            图片管理id
     * @return 图片管理对象
     * @author NINGPAI_xiaomm
     * @since 2014-03-24 14:33:31
     */
    ImageManager getImageManagerById(int id);

    /**
     * 根据图片管理对象的id字符集合查询图片管理对象
     * 
     * @param ids
     *            图片管理id字符集合(如果多个使用,分割)
     * @return 图片管理对象集合
     * @author NINGPAI_xiaomm
     * @since 2014-03-24 14:33:31
     */
    List<ImageManager> getImageManagerByIds(String ids);

    /**
     * 根据图片管理对象的id字符集合删除图片管理对象
     * 
     * @param ids
     *            图片管理id字符集合(如果多个使用,分割)
     * @return 删除图片管理对象的数目
     * @author NINGPAI_xiaomm
     * @since 2014-03-24 14:33:31
     */
    int deleteImageManager(String ids);

    /**
     * 更新图片管理对象的单个字段 其中要包含ids键，保存更新对象的id（多个id以，号分割）
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 更新图片管理对象的数目
     * @author NINGPAI_xiaomm
     * @since 2014-03-24 14:33:31
     */
    int updateImageManagerFieldById(Map<String, Object> parameter);

    /**
     * 根据图片管理对象的单个字段查询图片管理对象信息总数 其中要包含ids键，保存更新对象的id（多个id以，号分割）
     * 如果查询中有时间条件，则字段名格式为：开始时间（字段名Start），结束时间（字段名End）
     * 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 图片管理对象信息总数
     * @author NINGPAI_xiaomm
     * @since 2014-03-24 14:33:31
     */
    int getImageManagerByFieldTotal(Map<String, Object> parameter);

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
    List<ImageManager> getImageManagerByField(Map<String, Object> parameter);

    /**
     * 查询图片管理对象信息总数 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 图片管理对象信息总数
     * @author NINGPAI_xiaomm
     * @since 2014-03-24 14:33:31
     */
    int queryImageManagerTotal(Map<String, Object> parameter);

    /**
     * 分页查询图片管理对象信息 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 图片管理对象的集合
     * @author NINGPAI_xiaomm
     * @since 2014-03-24 14:33:31
     */
    List<ImageManager> queryImageManagerByPage(Map<String, Object> parameter);
}
