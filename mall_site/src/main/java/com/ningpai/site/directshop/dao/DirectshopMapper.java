package com.ningpai.site.directshop.dao;


import com.ningpai.site.directshop.bean.DirectShop;

import java.util.List;
import java.util.Map;

/**
 * 移动端查询直营店信息DAO接口
 * @author qiyuanyuan
 *
 */
public interface DirectshopMapper {

    /**
     * 根据条件查询店铺详情
     * @param paramMap 查询条件
     * @return 对象
     */
    DirectShop selectInfoById(Map<String, Object> paramMap);
    
    /**
     * 根据条件查询店铺列表
     * @param paramMap 查询条件
     * @return List
     */
    List<DirectShop> directShops(Map<String, Object> paramMap);
}
