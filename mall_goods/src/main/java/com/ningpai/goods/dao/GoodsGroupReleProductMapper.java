package com.ningpai.goods.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.goods.bean.GoodsGroupReleProduct;
import com.ningpai.goods.vo.GoodsGroupReleProductVo;

/**
 * 商品组合关联货品DAO
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月31日 上午11:56:07
 * @version 1.0
 */
public interface GoodsGroupReleProductMapper {
    /**
     * 删除
     * 
     * @param map
     *            封装删除参数{@link java.util.Map}
     * @return 删除的行数 {@link java.lang.Integer}
     */
    int deleteByPrimaryKey(Map<String, String> map);

    /**
     * 插入记录
     * 
     * @param record
     *            待插入的实体{@link com.ningpai.goods.bean.GoodsGroupReleProduct}
     * @return 插入的行数 {@link java.lang.Integer}
     */
    int insertSelective(GoodsGroupReleProduct record);

    /**
     * 根据主键查询
     * 
     * @param releProductid
     *            主键iD {@link java.lang.Long}
     * @return 查询到的实体{@link com.ningpai.goods.bean.GoodsGroupReleProduct}
     */
    GoodsGroupReleProduct selectByPrimaryKey(Long releProductid);

    /**
     * 更新
     * 
     * @param record
     *            待更新的实体 {@link com.ningpai.goods.bean.GoodsGroupReleProduct}
     * @return 更新的行数 {@link java.lang.Integer}
     */
    int updateByPrimaryKeySelective(GoodsGroupReleProduct record);

    /**
     * 根据主键ID和选中的货品ID批量删除不存在于选中的货品的记录
     * 
     * @param map
     *            封装参数{@link java.util.Map}
     * @return 删除的行数 {@link java.lang.Integer}
     */
    int batchDelWithCheckProductId(Map<String, Long> map);

    /**
     * 根据商品组合ID查询关联的关联记录列表
     * 
     * @param goodsGroupId
     *            商品组合ID {@link java.lang.Long}
     * @return 查询到的列表 {@link java.util.List}
     *         {@link com.ningpai.goods.bean.GoodsGroupReleProduct}
     */
    List<GoodsGroupReleProduct> queryListByGoodsGroupId(Long goodsGroupId);

    /**
     * 根据商品组合ID查询关联的关联记录列表
     * 
     * @param goodsGroupId
     *            商品组合ID {@link java.lang.Long}
     * @return 查询到的列表 {@link java.util.List}
     *         {@link com.ningpai.goods.bean.GoodsGroupReleProduct}
     */
    List<GoodsGroupReleProductVo> queryListByGoodsInfoGroupId(Long goodsGroupId);

    /**
     * 根据组合ID和货品ID查询关联对象
     * 
     * @param map
     *            封装查询参数 {@link java.util.Map}
     * @return 查询到的对象 {@link com.ningpai.goods.bean.GoodsGroupReleProduct}
     */
    GoodsGroupReleProduct queryGroupReleProductByGroupIdAndProductId(
            Map<String, Long> map);
}
