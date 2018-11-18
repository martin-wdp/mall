package com.ningpai.grant.dao;

import java.util.List;
import java.util.Map;

/**
 * 品牌授权管理Dao
 * 
 * @author NINGPAI-LIH
 * @since 2014年5月29日10:14:31
 * @version 1.0
 */
public interface GrantBrandMapper {

    /**
     * 查询行数
     * 
     * @param map
     * @return
     */
    int searchGrandBrandCount(Map<String, Object> map);

    /**
     * 分页查询列表
     * 
     * @return
     */
    List<Object> queryAllThirdGrandBrand(Map<String, Object> map);

    /**
     * 更新品牌状态
     * 
     * @param map
     */
    void updateGrantBrands(Map<String, Object> map);

}
