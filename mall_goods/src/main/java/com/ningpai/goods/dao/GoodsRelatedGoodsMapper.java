package com.ningpai.goods.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.goods.bean.GoodsRelatedGoods;
import com.ningpai.goods.vo.GoodsRelatedGoodsVo;

/**
 * 商品关联商品DAO
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月25日 下午4:31:58
 * @version 1.0
 */
public interface GoodsRelatedGoodsMapper {
    /**
     * 根据商品ID 关联商品ID删除 商品关联商品
     * @param relatedId  商品关联ID
     * @param relatedProductId 商品ID
     * @return
     */
    int deleteRelatedProduct(Long relatedId,Long relatedProductId);
    /**
     * 删除关联商品记录 (更新字段)
     * 
     * @param map
     *            封装所有的参数 {@link java.util.Map}
     * @return 影响的行数{@link java.lang.Integer}
     */
    int deleteByPrimaryKey(Map<String, String> map);

    /**
     * 插入一条记录(全属性 不推荐)
     * 
     * @param record
     *            待插入的实体{@link com.ningpai.goods.bean.GoodsRelatedGoods}
     * @return 影响的行数
     */
    int insert(GoodsRelatedGoods record);

    /**
     * 插入一条记录(可选属性 推荐)
     * 
     * @param record
     *            待插入的实体{@link com.ningpai.goods.bean.GoodsRelatedGoods}
     * @return 影响的行数
     */
    int insertSelective(GoodsRelatedGoods record);

    /**
     * 根据主键ID查询关联对象
     * 
     * @param relatedId
     *            主键ID {@link java.lang.Long}
     * @return 查询到的实体 {@link com.ningpai.goods.bean.GoodsRelatedGoods}
     */
    GoodsRelatedGoods selectByPrimaryKey(Long relatedId);

    /**
     * 更新商品关联商品记录(可选属性 推荐)
     * 
     * @param record
     *            待更新的实体 {@link com.ningpai.goods.bean.GoodsRelatedGoods}
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    int updateByPrimaryKeySelective(GoodsRelatedGoods record);

    /**
     * 更新商品关联商品记录(全属性 不推荐)
     * 
     * @param record
     *            待更新的实体 {@link com.ningpai.goods.bean.GoodsRelatedGoods}
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    int updateByPrimaryKey(GoodsRelatedGoods record);

    /**
     * 根据商品ID查询所有的关联记录
     * 
     * @param goodsId
     *            商品ID {@link java.lang.Long}
     * @return 查询到的关联记录的列表 {@link java.util.List}
     *         {@link com.ningpai.goods.bean.GoodsRelatedGoods}
     */
    List<GoodsRelatedGoodsVo> queryAllByGoodsId(Long goodsId);

    /**
     * 根据商品ID 删除所有的关联商品记录
     * 
     * @param map
     *            封装所需的参数
     * @return 删除的行数 {@link java.lang.Integer}
     */
    int delAllRelaGoodsByGoodsId(Map<String, String> map);

    /**
     * 根据商品ID和关联的商品的ID查询管理的记录
     * 
     * @param map
     *            封装参数
     * @return 查询到的关联记录实体{@link com.ningpai.goods.bean.GoodsRelatedGoods}
     */
    GoodsRelatedGoods queryByGoodsIdAndRelaGoodsIdIncludeDel(
            Map<String, Long> map);

    /**
     * 根据商品ID和选中的关联的商品ID删除未选中的
     * 
     * @param goodsId
     *            商品ID
     * @param relaGoodsIds
     *            选中的关联的商品ID的数组
     * @return 删除的行数
     */
    int delGoodsRelaGoods(Map<String, Object> map);
}
