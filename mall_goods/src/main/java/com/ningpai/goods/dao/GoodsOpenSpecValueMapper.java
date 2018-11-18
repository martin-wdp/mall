package com.ningpai.goods.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.goods.bean.GoodsOpenSpecValue;
import com.ningpai.goods.vo.GoodsOpenSpecValueVo;

/**
 * 商品开启规格值DAO
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年6月30日 下午5:35:53
 * @version 1.0
 */
public interface GoodsOpenSpecValueMapper {
    /**
     * 插入记录
     * 
     * @param record
     *            待插入的实体 {@link GoodsOpenSpecValue}
     * @return 插入的行数 {@link Integer}
     */
    int insertSelective(GoodsOpenSpecValue record);

    /**
     * 根据规格ID查询关联集合
     * 
     * @param specId
     *            规格ID {@link Long}
     * @return 查询到的集合 {@link List}
     */
    List<GoodsOpenSpecValue> queryOpenValListBySpecId(Long specId);

    /**
     * 根据商品ID查询关联集合
     * 
     * @param goodsId
     *            商品ID {@link Long}
     * @return 插叙到的集合 {@link List}
     */
    List<GoodsOpenSpecValue> queryOpenValListByGoodsId(Long goodsId);

    /**
     * 根据商品ID和规格ID查询开启的规格列表
     * 
     * @param map
     *            商品ID 和 规格ID
     * @return 查询到的集合 {@link List}
     */
    List<GoodsOpenSpecValueVo> queryOpenValueListByGoodsIdAndSpecId(
            Map<String, Object> map);

    /**
     * 根据商品ID和规格值ID查询开启的规格列表
     * 
     * @param map
     *            商品ID 和 规格ID
     * @return 查询到的集合 {@link List}
     */
    GoodsOpenSpecValueVo queryOpenValueListByGoodsIdAndSpecValueId(
            Map<String, Object> map);

    /**
     * 根据商品id，删除商品与规格之间关系
     *
     * @param goodsId
     *            商品id
     */
    void deleteByGoodsId(Long goodsId);
}
