package com.ningpai.system.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.system.bean.ShopConf;

/**
 * 购物设置数据操作接口
 * 
 * @Description:
 * @author NINGPAI_xiaomm
 * @since 2014-03-24 17:58:48
 * @version V1.0
 */
@Repository
public interface IShopConfDao {

    /**
     * 保存购物设置
     * 
     * @param shopConf
     *            购物设置对象
     * @return 是否保存成功
     * @author NINGPAI_xiaomm
     * @since 2014-03-24 17:58:48
     */
    boolean saveShopConf(ShopConf shopConf);

    /**
     * 修改购物设置
     * 
     * @param shopConf
     *            待修改购物设置对象
     * @return 更新影响的条数
     * @author NINGPAI_xiaomm
     * @since 2014-03-24 17:58:48
     */
    int updateShopConf(ShopConf shopConf);

    /**
     * 根据购物设置对象的id查询购物设置对象
     * 
     * @param id
     *            购物设置id
     * @return 购物设置对象
     * @author NINGPAI_xiaomm
     * @since 2014-03-24 17:58:48
     */
    ShopConf getShopConfById(int id);

    /**
     * 根据购物设置对象的id字符集合查询购物设置对象
     * 
     * @param ids
     *            购物设置id字符集合(如果多个使用,分割)
     * @return 购物设置对象集合
     * @author NINGPAI_xiaomm
     * @since 2014-03-24 17:58:48
     */
    List<ShopConf> getShopConfByIds(String ids);

    /**
     * 根据购物设置对象的id字符集合删除购物设置对象
     * 
     * @param ids
     *            购物设置id字符集合(如果多个使用,分割)
     * @return 删除购物设置对象的数目
     * @author NINGPAI_xiaomm
     * @since 2014-03-24 17:58:48
     */
    int deleteShopConf(String ids);

    /**
     * 更新购物设置对象的单个字段 其中要包含ids键，保存更新对象的id（多个id以，号分割）
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 更新购物设置对象的数目
     * @author NINGPAI_xiaomm
     * @since 2014-03-24 17:58:48
     */
    int updateShopConfFieldById(Map<String, Object> parameter);

    /**
     * 根据购物设置对象的单个字段查询购物设置对象信息总数 其中要包含ids键，保存更新对象的id（多个id以，号分割）
     * 如果查询中有时间条件，则字段名格式为：开始时间（字段名Start），结束时间（字段名End）
     * 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 购物设置对象信息总数
     * @author NINGPAI_xiaomm
     * @since 2014-03-24 17:58:48
     */
    int getShopConfByFieldTotal(Map<String, Object> parameter);

    /**
     * 根据购物设置对象的单个字段查询购物设置对象信息 其中要包含ids键，保存更新对象的id（多个id以，号分割）
     * 如果查询中有时间条件，则字段名格式为：开始时间（字段名Start），结束时间（字段名End）
     * 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 购物设置对象的集合
     * @author NINGPAI_xiaomm
     * @since 2014-03-24 17:58:48
     */
    List<ShopConf> getShopConfByField(Map<String, Object> parameter);

    /**
     * 查询购物设置对象信息总数 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 购物设置对象信息总数
     * @author NINGPAI_xiaomm
     * @since 2014-03-24 17:58:48
     */
    int queryShopConfTotal(Map<String, Object> parameter);

    /**
     * 分页查询购物设置对象信息 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 购物设置对象的集合
     * @author NINGPAI_xiaomm
     * @since 2014-03-24 17:58:48
     */
    List<ShopConf> queryShopConfByPage(Map<String, Object> parameter);
}
