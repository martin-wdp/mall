package com.ningpai.category.dao;

import com.ningpai.category.bean.GoodsCategory;

/**
 * E店宝同步分类DAO接口
 * 
 * @author qiyuanyuan
 *
 */
public interface SynCateMapper {

    /**
     * 判断分类是否存在
     * 
     * @param valueOf
     * @return int
     */
    int checkCate(Long catId);

    /**
     * 插入分类信息
     * 
     * @param category
     * @return int
     */
    int insertGoodsCate(GoodsCategory category);

    /**
     * 修改分类信息
     * 
     * @param category
     * @return int
     */
    int updateGoodsCate(GoodsCategory category);

}
