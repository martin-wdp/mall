package com.ningpai.api.dao;

import com.ningpai.api.bean.OGoodsProductWare;

import java.util.List;
import java.util.Map;

/**
 * 开放接口--商品库存
 * @author lih
 * @version 2.0
 * @since 15/9/7
 */
public interface IProductWareMapper {

    /**
     * 根据商品编号查询库存信息
     * @param goodsInfoItemNo 商品编号
     * @return 库存信息
     */
    List<OGoodsProductWare> get(String goodsInfoItemNo);

    /**
     * 根据商品编号和仓库标识查询库存数量
     * @param map  商品编号goodsInfoItemNo 仓库标识 IdentifyNo
     * @return
     */
    Long getCount(Map<String,Object> map);

    /**
     * 添加库存
     * @param map 商品编号 商品编号goodsInfoItemNo 仓库标识 IdentifyNo count 添加的数量
     * @return 修改结果
     */
    int addStock(Map<String,Object> map);


    /**
     * 减去库存
     * @param map 商品编号goodsInfoItemNo 仓库标识 IdentifyNo count 添加的数量
     * @return 修改结果
     */
    int minStock(Map<String,Object> map);
}
