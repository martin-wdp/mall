package com.ningpai.goods.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.goods.bean.GoodsProductSupp;
import com.ningpai.goods.vo.GoodsProductSuppVo;

/**
 * 货品关联服务支持Dao
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年7月31日 上午9:51:31
 * @version 1.0
 */
public interface GoodsProductSuppMapper {
    /**
     * 根据主键删除关联信息
     * 
     * @param map
     *            需要删除的主键ID和操作人名称
     * @return 删除影响的行数
     */
    int deleteByPrimaryKey(Long suppId);

    /**
     * 新建关联记录
     * 
     * @param record
     *            需要插入的实体
     * @return 插入的行数
     */
    int insertSelective(GoodsProductSupp record);

    /**
     * 更新关联信息
     * 
     * @param record
     *            需要更新的关联实体
     * @return 更新影响的行数
     */
    int updateByPrimaryKeySelective(GoodsProductSupp record);

    /**
     * 根据货品ID查询所有的关联记录集合
     * 
     * @param productId
     *            货品ID
     * @return 查询到的关联集合
     */
    List<GoodsProductSupp> queryAllByProductId(Long productId);

    /**
     * 删除所有的关联信息
     * 
     * @param map
     *            货品ID和操作人名称
     * @return 批量删除影响的行数
     */
    int delAllProductSupp(Long productId);

    /**
     * 批量保存关联信息
     * 
     * @param map
     *            货品ID和服务ID数组
     * @return 插入的行数
     */
    int batchInsertSupp(Map<String, Object> map);

    /**
     * 根据货品ID查询所有的关联服务的VoBean
     * 
     * @param productId
     *            货品ID
     * @return 查询到的集合
     */
    List<GoodsProductSuppVo> queryAllVoByProductId(Long productId);
}
