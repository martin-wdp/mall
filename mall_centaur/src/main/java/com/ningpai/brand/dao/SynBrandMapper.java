package com.ningpai.brand.dao;

import com.ningpai.brand.bean.GoodsBrand;

/**
 * E店宝同步品牌DAO接口
 * 
 * @author qiyuanyuan
 *
 */
public interface SynBrandMapper {

    /**
     * 检测品牌是否存在
     * 
     * @param brandId
     *            品牌ID
     * @return int
     */
    int checkBrand(Long brandId);

    /**
     * 插入品牌
     * 
     * @param gb
     * @return int
     */
    int insertBrand(GoodsBrand gb);

    /**
     * 修改品牌
     * 
     * @param gb
     * @return int
     */
    int updateBrand(GoodsBrand gb);

}
