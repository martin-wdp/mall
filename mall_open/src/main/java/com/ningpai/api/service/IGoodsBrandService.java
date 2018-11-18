package com.ningpai.api.service;


/**
 *
 * 开放接口--商品品牌列表
 * @author lih
 * @version 2.0
 * @since 15/9/1
 */
public interface IGoodsBrandService {

    /**
     * 获取订单列表
     * @param pageNo 页数
     * @param pageSize 每页大小
     * @return
     */
    String list(Integer pageNo, Integer pageSize );
}
