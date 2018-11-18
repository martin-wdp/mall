package com.ningpai.api.dao;

import com.ningpai.api.bean.OGoodsBrand;

import java.util.List;
import java.util.Map;

/**
 * 开放接口--商品品牌
 * @author lih
 * @version 2.0
 * @since 15/9/1
 */
public interface IGoodsBrandMapper {

    /**
     * 获取商品品牌促销
     * @param map 分页参数
     * @return 商品品牌促销
     */
    List<OGoodsBrand> list(Map<String,Object> map);


    /**
     * 查询总数
     * @return 总行数
     */
    int count();
}
