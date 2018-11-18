package com.ningpai.api.service;


/**
 *
 * 开放接口 - 商品service类
 * @author lih
 * @version 2.0
 * @since 15/8/25
 */
public interface IGoodsProductService {

    /**
     *
     * 获取货品列表
     * @param thirdId 第三方编号
     * @param goodsId 商品id
     * @param pageNo 页数
     * @param pageSize 每页条数
     * @return 货品列表
     */
    String list(String thirdId,Long goodsId,Integer pageNo, Integer pageSize);


    /**
     * 根据货品编号查询货品信息
     * @param goodsInfoItemNo 货品编号
     * @return 货品信息
     */
    String get(String goodsInfoItemNo);
}
