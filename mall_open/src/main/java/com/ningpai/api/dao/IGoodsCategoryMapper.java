package com.ningpai.api.dao;

import com.ningpai.api.bean.OGoodsCategory;

import java.util.List;
import java.util.Map;

/**
 * 开放接口--商品分类
 * @author lih
 * @version 2.0
 * @since 15/9/6
 */
public interface IGoodsCategoryMapper{

    /**
     * 商品分类列表
     * @param map 分页参数
     * @return 商品分类列表
     */
    List<OGoodsCategory> list(Map<String,Object> map);

    /**
     * 查询商品分类总数
     * @return 总数
     */
    int count();

}
