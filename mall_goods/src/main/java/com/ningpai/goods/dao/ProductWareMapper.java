package com.ningpai.goods.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.goods.bean.ProductWare;
import com.ningpai.goods.bean.WareHouse;

/**
 * 货品分仓DAO
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年4月28日 下午7:35:37
 * @version 1.0
 */
public interface ProductWareMapper {
    /**
     *
     * 查询分仓信息
     * */
    WareHouse findWare(Long did);

    /**
     * 插入记录
     * 
     * @param
     * @return 插入的行数
     */
    int insertSelective(ProductWare record);

    /**
     * 更新记录
     * 
     * @param record
     *            待更新的实体
     * @return 更新的行数
     */
    int updateByPrimaryKeySelective(ProductWare record);

    /**
     * 根据货品ID查询所有的记录
     * 
     * @param productId
     *            货品ID
     * @return 查询到的集合
     */
    List<ProductWare> queryAllByProductId(Long productId);

    /**
     * 根据货品ID和仓库ID查询是否已经存在记录
     * 
     * @param map
     *            封装参数的MAP
     * @return 查询到的行数
     */
    int queryCountByProductIdAndWareId(Map<String, Object> map);

    /**
     * 根据参数查询主键ID
     * 
     * @param map
     *            参数
     * @return 查询到的ID
     */
    Long queryIdByProductIdAndWareId(Map<String, Object> map);

    /**
     * 根据货品ID和城市ID查询关联记录
     * 
     * @param map
     *            参数
     * @return 查询到的实体
     */
    ProductWare queryProductWareByProductIdAndDistinctId(Map<String, Long> map);

    /**
     * 根据仓库id删除记录
     * 
     * @param wareId仓库id
     * @return
     * @author NINGPAI-LIH
     */
    int deleteWareCity(Long wareId);

}
