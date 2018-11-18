package com.ningpai.goods.dao;

import java.util.List;
import java.util.Map;

/**
 * 商品关注DAO
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年3月18日 下午4:00:27
 * @version 1.0
 */
public interface GoodsAtteMapper {
    /**
     * 根据主键删除
     * 
     * @param map
     *            参数Map {@link java.util.Map}
     * @return 删除的行数 {@link java.lang.Integer}
     */
    int deleteByPrimaryKey(Map<String, Object> map);

    /**
     * 批量删除
     * 
     * @param map
     *            封装的参数 {@link java.util.Map}
     * @return 删除的行数 {@link java.lang.Integer}
     */
    int batchDelete(Map<String, Object> map);

    /**
     * 根据参数查询所有的行数,去除重复的货品ID
     * 
     * @param map
     *            封装的参数 {@link java.util.Map}
     * @return 查询到的行数 {@link java.lang.Integer}
     */
    int queryTotalCount(Map<String, Object> map);

    /**
     * 查询货品所有的关注信息行数
     * 
     * @param map
     *            封装的参数 {@link java.util.Map}
     * @return 查询到的行数 {@link java.lang.Integer}
     */
    int queryTotalCountToProduct(Map<String, Object> map);

    /**
     * 根据参数查询集合
     * 
     * @param map
     *            封装的参数 {@link java.util.Map}
     * @return 查询到的集合 {@link java.util.List}
     */
    List<Object> queryByParam(Map<String, Object> map);

    /**
     * 根据货品Id查询关注的列表
     * 
     * @param map
     *            参数 {@link java.util.Map}
     * @return 查询到的结果 {@link java.util.List}
     */
    List<Object> queryByProductId(Map<String, Object> map);

}
