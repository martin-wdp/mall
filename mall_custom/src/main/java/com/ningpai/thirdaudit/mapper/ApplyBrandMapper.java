package com.ningpai.thirdaudit.mapper;

import java.util.List;
import java.util.Map;

/**
 * 应用品牌接口
 *
 * */
public interface ApplyBrandMapper {

    /**
     * 查询未审核列表
     * 
     * @param pmap
     * @return
     */
    List<Object> queryApplyBrand(Map<String, Object> pmap);

    /**
     * 修改审核状态
     * 
     * @param pmap
     * @return
     */
    int updateApplyBrand(Map<String, Object> pmap);

    /**
     * 查询未审核个数
     * 
     * @param pmap
     * @return
     */
    int queryApplyBrandCount(Map<String, Object> pmap);
}
