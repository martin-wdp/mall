package com.ningpai.site.thirdseller.dao;

import java.util.List;
import java.util.Map;
import com.ningpai.site.thirdseller.vo.ThirdCateVo;

/**
 * 第三方店铺分类DAO
 * @author qiyuanyuan
 *
 */
public interface ThirdCateMapper {

    /**
     * 根据id获取单个店铺的信息
     * @param customerId
     * @return
     */
    Long selectByCustomerId(Long customerId);
    /**
     * 查询商家是否被删除
     * @param thirdId
     * @return
     */
    String findStoreFlag(Long thirdId);
     /**
     * 查询所有的商品分类
     * 
     * @return 所有的商品分类列表
     */
    List<ThirdCateVo> queryAllThirdCate(Map<String, Object> map);
    
    /**
     * 查询所有的父分类集合
     * 
     * @return 查询到的父分类的集合
     */
    List<ThirdCateVo> queryAllCateForList(Long thirdId);
    
    /**
     * 根据主键查询
     * @param catId 分类ID {@link java.lang.Integer}
     * @return 查询到的实体 
     */
    ThirdCateVo selectByPrimaryKey(Long catId);
    
    /**
     * 根据店铺ID和父分类id查询子分类
     * @param map
     *          查询条件
     * @return  查询到的VO实体 
     */
    List<ThirdCateVo> queryThirdCateByParentCateId(Map<String, Object> map);
    
}
