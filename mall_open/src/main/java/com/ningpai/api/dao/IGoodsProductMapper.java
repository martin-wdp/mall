package com.ningpai.api.dao;

import com.ningpai.api.bean.OGoodsProduct;

import java.util.List;
import java.util.Map;

/**
 *
 * 开发接口－－ 商品dao层接口
 * @author lih
 * @version 2.0
 * @since 15/8/25
 */
public interface IGoodsProductMapper {

    /**
     * 货品集合
     * @author lih
     * @param  map 参数容器
     * @return 货品集合
     */
    List<OGoodsProduct> list(Map<String,Object> map);

    /**
     * 根据货号获取货品详情
     * @param goodsInfoItemNo 货品编号
     * @return 货品信息
     */
    OGoodsProduct get(String goodsInfoItemNo);


    /**
     * 商品总数
     * @param map 参数容器
     * @return
     */
    int count(Map<String,Object> map);
}
