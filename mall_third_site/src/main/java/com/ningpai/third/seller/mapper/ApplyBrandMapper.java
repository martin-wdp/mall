package com.ningpai.third.seller.mapper;

import java.util.List;
import java.util.Map;

import com.ningpai.third.seller.bean.ApplyBrand;

/**
 * 自定义品牌接口
 */
public interface ApplyBrandMapper {

    /**
     * 查询申请的品牌
     * 
     * @param thirdId
     * @return List
     */
    List<ApplyBrand> selectApplyBrand(Long thirdId);

    /**
     * 删除自定义品牌
     * 
     * @param map
     * @return int
     */
    int delApplyBrand(Map<String, Object> map);

    /**
     * 添加自定义品牌
     * 
     * @param applyBrand
     * @return int
     */
    int addApplyBrand(ApplyBrand applyBrand);

    /**
     * 
     * 查询最新Id
     * 
     * @return Long
     */
    Long selectLastId();
    
    /**
     * 查询自定义品牌数量
     * @param pmap
     * @return
     */
    int queryApplyBrandCount(Map<String, Object> pmap);
    
    /**
     * 查询自定义品牌列表
     * @param pmap
     * @return
     */
    List<Object> queryApplyBrand(Map<String, Object> pmap);
    
    /**
     * 批量删除自定义品牌
     * @param map
     * @return
     */
    int delApplyBrands(Map<String, Object> map);

    /**
     * 第三方与品牌的绑定关联
     * @param map
     * @return
     */
    int updateThirdApplyBrand(Map<String, Object> map);
}
