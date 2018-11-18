package com.ningpai.goods.dao;

import java.util.List;

import com.ningpai.goods.bean.GoodsOpenSpec;
import com.ningpai.goods.vo.GoodsOpenSpecVo;

/**
 * 商品开启规格Dao
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年6月30日 下午2:26:20
 * @version 1.0
 */
public interface GoodsOpenSpecMapper {
    /**
     * 插入一条记录
     * 
     * @param record
     *            待插入的实体 {@link GoodsOpenSpec}
     * @return 插入的行数 {@link Integer}
     */
    int insertSelective(GoodsOpenSpec record);

    /**
     * 根据商品ID查询开启的规格的集合
     * 
     * @param goodsId
     *            商品ID {@link Long}
     * @return 查询到的集合{@link List}
     */
    List<GoodsOpenSpecVo> queryOpenSpecListByGoodsId(Long goodsId);

    /**
     * 根据商品id，删除商品与规格之间关系
     * 
     * @param goodsId
     *            商品id
     */
    void deleteByGoodsId(Long goodsId);

}
