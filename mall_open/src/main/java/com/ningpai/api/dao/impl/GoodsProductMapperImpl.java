package com.ningpai.api.dao.impl;

import com.ningpai.api.bean.OGoodsProduct;
import com.ningpai.api.dao.IGoodsProductMapper;
import com.ningpai.manager.base.BasicSqlSupport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 *
 * 开放接口 - 商品接口实现类
 * @author lih
 * @version 2.0
 * @since 15/8/25
 */
@Repository("OpenGoodsProductMapper")
public class GoodsProductMapperImpl extends BasicSqlSupport implements IGoodsProductMapper {

    /**
     * 货品集合
     *
     * @param map 参数容器
     * @return 货品集合
     * @author lih
     */
    @Override
    public List<OGoodsProduct> list(Map<String, Object> map) {
        return this.selectList("com.ningpai.goods.dao.OGoodsProductMapper.list",map);
    }

    /**
     * 根据货号获取货品信息
     *
     * @param goodsInfoItemNo 货品编号
     * @return 货品信息
     */
    @Override
    public OGoodsProduct get(String goodsInfoItemNo) {
        return this.selectOne("com.ningpai.goods.dao.OGoodsProductMapper.get",goodsInfoItemNo);
    }

    /**
     * 商品总数
     *
     * @param map 参数容器
     * @return
     */
    @Override
    public int count(Map<String, Object> map) {
        return this.selectOne("com.ningpai.goods.dao.OGoodsProductMapper.count",map);
    }


}
