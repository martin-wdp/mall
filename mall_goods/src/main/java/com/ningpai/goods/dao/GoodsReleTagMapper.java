package com.ningpai.goods.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.goods.bean.GoodsReleTag;
import com.ningpai.goods.vo.GoodsReleTagVo;

/**
 * 商品关联标签DAO
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月24日 下午4:15:00
 * @version 1.0
 */
public interface GoodsReleTagMapper {
    /**
     * 删除记录 （更新字段）
     * 
     * @param map
     *            封装参数
     * @return 删除的行数
     */
    int deleteByPrimaryKey(Map<String, String> map);

    /**
     * 插入一条记录(全属性 不推荐)
     * 
     * @param record
     *            需要插入的实体{@link com.ningpai.goods.bean.GoodsReleTag}
     * @return 插入的行数 {@link java.lang.Integer}
     */
    int insert(GoodsReleTag record);

    /**
     * 插入一条记录(可选属性 推荐)
     * 
     * @param record
     *            需要插入的实体{@link com.ningpai.goods.bean.GoodsReleTag}
     * @return 插入的行数 {@link java.lang.Integer}
     */
    int insertSelective(GoodsReleTag record);

    /**
     * 根据主键查询实体
     * 
     * @param relaTagId
     *            主键ID {@link java.lang.Long}
     * @return 查询到的实体 {@link com.ningpai.goods.bean.GoodsReleTag}
     */
    GoodsReleTag selectByPrimaryKey(Long relaTagId);

    /**
     * 更新一条记录(可选属性 推荐)
     * 
     * @param record
     *            需要更新的实体{@link com.ningpai.goods.bean.GoodsReleTag}
     * @return 更新的行数 {@link java.lang.Integer}
     */
    int updateByPrimaryKeySelective(GoodsReleTag record);

    /**
     * 插入一条记录(全属性 不推荐)
     * 
     * @param record
     *            需要更新的实体{@link com.ningpai.goods.bean.GoodsReleTag}
     * @return 更新的行数 {@link java.lang.Integer}
     */
    int updateByPrimaryKey(GoodsReleTag record);

    /**
     * 根据商品ID查询所有的关联记录
     * 
     * @param goodsId
     *            商品的ID {@link java.lang.Long}
     * @return 查询到的列表 {@link java.util.List}
     *         {@link com.ningpai.goods.bean.GoodsReleTag}
     */
    List<GoodsReleTagVo> queryAllByGoodsId(Long goodsId);

    /**
     * 根据商品ID和标签ID查询实体
     * 
     * @param map
     *            {@link java.util.Map}
     * @return 查询到的实体 {@link com.ningpai.goods.bean.GoodsReleTag}
     */
    GoodsReleTag queryByGoodsIdAndTagId(Map<String, Long> map);

    /**
     * 根据货品ID查询关联的标签信息
     * 
     * @param productId
     *            货品ID {@link Long}
     * @return 查询到的关联的集合 {@link GoodsReleTagVo}
     */
    List<GoodsReleTagVo> queryAllByProductId(Long productId);
}
