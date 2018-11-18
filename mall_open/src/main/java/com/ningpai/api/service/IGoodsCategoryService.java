package com.ningpai.api.service;

/**
 *
 * 开放接口--商品分类
 * @author lih
 * @version 2.0
 * @since 15/9/6
 */
public interface IGoodsCategoryService {
    /**
     * 获取订单列表
     * @param sign  获取签名 "goodscategory.list"+key 通过MD5方式进行加密
     * @param pageNo 页数
     * @param pageSize 每页大小
     * @return
     */
    String list(Integer pageNo, Integer pageSize );
}
