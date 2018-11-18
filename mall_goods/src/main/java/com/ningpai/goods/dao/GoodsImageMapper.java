package com.ningpai.goods.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.goods.bean.GoodsImage;

/**
 * 货品关联图片DAO
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年1月3日 下午4:01:32
 * @version 1.0
 */
public interface GoodsImageMapper {
    /**
     * 根据主键删除
     * 
     * @param map
     *            {@link java.util.Map}
     * @return 删除的行数 {@link java.lang.Integer}
     */
    int deleteByPrimaryKey(Map<String, String> map);

    /**
     * 插入一条记录
     * 
     * @param record
     *            待插入的实体 {@link com.ningpai.goods.bean.GoodsImage}
     * @return 插入的行数 {@link java.lang.Long}
     */
    Long insertSelective(GoodsImage record);

    /**
     * 根据主键ID查询
     * 
     * @param goodsImgId
     *            主键ID
     * @return 查询到的实体 {@link com.ningpai.goods.bean.GoodsImage}
     */
    GoodsImage selectByPrimaryKey(Long goodsImgId);

    /**
     * 更新记录
     * 
     * @param record
     *            待更新的实体 {@link com.ningpai.goods.bean.GoodsImage}
     * @return 更新的行数 {@link java.lang.Integer}
     */
    int updateByPrimaryKeySelective(GoodsImage record);

    /**
     * 根据货品ID查询所有的关联记录
     * 
     * @param productId
     *            货品ID {@link java.lang.Long}
     * @return 查询到的列表 {@link java.util.List}
     *         {@link com.ningpai.goods.bean.GoodsImage}
     */
    List<GoodsImage> queryByProductId(Long productId);

    /**
     * 根据sku id修改商品排序
     * 
     * @param productId
     *            skuId
     * @return
     */
    int updateByProductInfoId(Long productId);

    /**
     * 设置sku 默认图片
     * 
     * @param goodsImgId
     *            商品图片id
     * @return
     */
    int setDefaultImage(Long goodsImgId);

}
