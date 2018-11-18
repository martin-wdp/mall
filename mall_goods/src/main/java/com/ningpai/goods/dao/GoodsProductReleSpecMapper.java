package com.ningpai.goods.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.goods.bean.GoodsProductReleSpec;
import com.ningpai.goods.vo.GoodsProductReleSpecVo;

/**
 * 货品信息关联规格值表
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月27日 下午3:40:22
 * @version 1.0
 */
public interface GoodsProductReleSpecMapper {
    /**
     * 删除记录
     * 
     * @param map
     *            封装参数 {@link java.util.Map}
     * @return 删除的ID
     */
    int deleteByPrimaryKey(Map<String, String> map);

    /**
     * 根据货品id删除记录
     *
     * @param productId
     *            货品id
     * @return
     */
    int deleteByProductId(Long productId);

    /**
     * 插入一条记录(可选属性 推荐)
     * 
     * @param record
     *            待插入的实体{@link com.ningpai.goods.bean.GoodsProductReleSpec}
     * @return 插入的行数 {@link java.lang.Integer}
     */
    int insertSelective(GoodsProductReleSpec record);

    /**
     * 根据主键查询
     * 
     * @param releSpecDetailId
     *            主键ID {@link java.lang.Long}
     * @return 查询到的实体 {@link com.ningpai.goods.bean.GoodsProductReleSpec}
     */
    GoodsProductReleSpec selectByPrimaryKey(Long releSpecDetailId);

    /**
     * 更新实体
     * 
     * @param record
     *            待更新的实体 {@link com.ningpai.goods.bean.GoodsProductReleSpec}
     * @return 更新的行数
     */
    int updateByPrimaryKeySelective(GoodsProductReleSpec record);

    /**
     * 根据货品ID查询关联的规格值列表
     * 
     * @param productId
     *            货品ID {@link java.lang.Long}
     * @return 查询到的关联列表 {@link java.util.List}
     *         {@link com.ningpai.goods.bean.GoodsProductReleSpec}
     */
    List<GoodsProductReleSpecVo> queryAllByProductId(Long productId);
    /**
     * 根据货品ID查询关联的规格值列表
     *
     * @param productId
     *            货品ID {@link java.lang.Long}
     * @return 查询到的关联列表 {@link java.util.List}
     *         {@link com.ningpai.goods.bean.GoodsProductReleSpec}
     */
    List<GoodsProductReleSpecVo> queryAllSimpleByProductId(Long productId);

    /**
     * 根据货品ID和规格ID更新关联关系
     * 
     * @param map
     *            封装参数的 {@link java.util.Map}
     * @return 更新的行数 {@link java.lang.Integer}
     */
    int updateProductReleSpec(Map<String, String> map);
}
