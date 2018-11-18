package com.ningpai.api.dao.impl;

import com.ningpai.api.bean.OGoodsProductWare;
import com.ningpai.api.dao.IProductWareMapper;
import com.ningpai.manager.base.BasicSqlSupport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author lih
 * @version 2.0
 * @since 15/9/7
 */
@Repository("openProductWareMapper")
public class ProductWareMapperImpl extends BasicSqlSupport implements IProductWareMapper{
    /**
     * 根据商品编号查询库存信息
     *
     * @param goodsInfoItemNo 商品编号
     * @return 库存信息
     */
    @Override
    public List<OGoodsProductWare> get(String goodsInfoItemNo) {
        return this.selectList("com.ningpai.goodsproductware.bean.OGoodsProductWare.get",goodsInfoItemNo);
    }

    /**
     * 根据商品编号和仓库标识查询库存数量
     *
     * @param map 商品编号goodsInfoItemNo 仓库标识 IdentifyNo
     * @return
     */
    @Override
    public Long getCount(Map<String, Object> map) {
        return this.selectOne("com.ningpai.goodsproductware.bean.OGoodsProductWare.getCount", map);
    }

    /**
     * 添加库存
     *
     * @param map 商品编号 商品编号goodsInfoItemNo 仓库标识 IdentifyNo count 添加的数量
     * @return 修改结果
     */
    @Override
    public int addStock(Map<String, Object> map) {
        return this.update("com.ningpai.goodsproductware.bean.OGoodsProductWare.addStock",map);
    }

    /**
     * 商品编号
     *
     * @param map 商品编号goodsInfoItemNo 仓库标识 IdentifyNo count 添加的数量
     * @return 修改结果
     */
    @Override
    public int minStock(Map<String, Object> map) {
        return this.update("com.ningpai.goodsproductware.bean.OGoodsProductWare.minStock",map);
    }
}
