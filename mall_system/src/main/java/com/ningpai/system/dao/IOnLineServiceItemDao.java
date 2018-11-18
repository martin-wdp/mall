package com.ningpai.system.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.system.bean.OnLineServiceItem;

/**
 * 在线客服项数据操作接口
 * 
 * @Description:
 * @author NINGPAI_xiaomm
 * @since 2014-03-27 17:44:30
 * @version V1.0
 */
@Repository
public interface IOnLineServiceItemDao {

    /**
     * 保存在线客服项
     * 
     * @param onLineServiceItem
     *            在线客服项对象
     * @return 是否保存成功
     * @author NINGPAI_xiaomm
     * @since 2014-03-27 17:44:30
     */
    boolean saveOnLineServiceItem(OnLineServiceItem onLineServiceItem);

    /**
     * 修改在线客服项
     * 
     * @param onLineServiceItem
     *            待修改在线客服项对象
     * @return 更新影响的条数
     * @author NINGPAI_xiaomm
     * @since 2014-03-27 17:44:30
     */
    int updateOnLineServiceItem(OnLineServiceItem onLineServiceItem);

    /**
     * 根据在线客服项对象的id查询在线客服项对象
     * 
     * @param id
     *            在线客服项id
     * @return 在线客服项对象
     * @author NINGPAI_xiaomm
     * @since 2014-03-27 17:44:30
     */
    OnLineServiceItem getOnLineServiceItemById(int id);

    /**
     * 根据在线客服项对象的id字符集合查询在线客服项对象
     * 
     * @param ids
     *            在线客服项id字符集合(如果多个使用,分割)
     * @return 在线客服项对象集合
     * @author NINGPAI_xiaomm
     * @since 2014-03-27 17:44:30
     */
    List<OnLineServiceItem> getOnLineServiceItemByIds(String ids);

    /**
     * 根据在线客服项对象的id字符集合删除在线客服项对象
     * 
     * @param ids
     *            在线客服项id字符集合(如果多个使用,分割)
     * @return 删除在线客服项对象的数目
     * @author NINGPAI_xiaomm
     * @since 2014-03-27 17:44:30
     */
    int deleteOnLineServiceItem(String ids);

    /**
     * 更新在线客服项对象的单个字段 其中要包含ids键，保存更新对象的id（多个id以，号分割）
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 更新在线客服项对象的数目
     * @author NINGPAI_xiaomm
     * @since 2014-03-27 17:44:30
     */
    int updateOnLineServiceItemFieldById(Map<String, Object> parameter);

    /**
     * 根据在线客服项对象的单个字段查询在线客服项对象信息总数 其中要包含ids键，保存更新对象的id（多个id以，号分割）
     * 如果查询中有时间条件，则字段名格式为：开始时间（字段名Start），结束时间（字段名End）
     * 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 在线客服项对象信息总数
     * @author NINGPAI_xiaomm
     * @since 2014-03-27 17:44:30
     */
    int getOnLineServiceItemByFieldTotal(Map<String, Object> parameter);

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
    List<OnLineServiceItem> getOnLineServiceItemByField(
            Map<String, Object> parameter);

    /**
     * 查询在线客服项对象信息总数 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 在线客服项对象信息总数
     * @author NINGPAI_xiaomm
     * @since 2014-03-27 17:44:30
     */
    int queryOnLineServiceItemTotal(Map<String, Object> parameter);

    /**
     * 分页查询在线客服项对象信息 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 在线客服项对象的集合
     * @author NINGPAI_xiaomm
     * @since 2014-03-27 17:44:30
     */
    List<OnLineServiceItem> queryOnLineServiceItemByPage(
            Map<String, Object> parameter);

    /**
     * 根据在线客服id删除在线客服项
     * 
     * @param onLineServiceId
     *            在线客服id
     * @return 删除的个数
     */
    int deleteOnLineServiceItem(int onLineServiceId);

    /**
     * 根据在线客服设置ID查询客服数量
     * 
     * @param onLineServiceId
     * @return
     */
    int selectCountByOnLineService(int onLineServiceId);

    /**
     * 根据主键删除
     * 
     * @param onLineServiceItemId
     * @return
     */
    int delOnLineServiceItem(int onLineServiceItemId);

    /**
     * 根据客服类型查询平台客服
     * 
     * @param type
     * @return
     */
    List<OnLineServiceItem> selectItemsByType(int type);
}
