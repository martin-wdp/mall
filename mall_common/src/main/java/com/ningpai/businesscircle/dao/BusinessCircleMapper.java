package com.ningpai.businesscircle.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.businesscircle.bean.BusinessCircle;

/**
 * 商圈接口操作类
 * @author ggn 2015-01-20
 *
 */
public interface BusinessCircleMapper {
    /**
     * 添加商圈
     * @param businessCircle
     * @return int
     */
    int addBusinessCircle(BusinessCircle businessCircle);
    
    /**
     * 删除商圈
     * @param businessCircle
     * @return int
     */
    int delBusinessCircleById(Long businessCircleId);
    /**
     * 修改商圈的开启状态
     * @param businessCircle
     * @return int
     */
    int updatebusinessCircleIsOpen(BusinessCircle businessCircle);
    
    /**
     * 修改商圈
     * @param businessCircle
     * @return int
     */
    int updateBusinessCircle(BusinessCircle businessCircle);
    
    /**
     * 查询商圈信息(根据条件)分页
     *  @param map businessCircleIsOpen 开启状态 businessCircleThirdId  商家状态 不为null查未绑定商家的
     *  
     * @return List<BusinessCircle>
     */
    List<Object> findBusinessCircles(Map<String,Object> map);
    /**
     * 查询商圈信息(根据条件)
     *  @param map businessCircleIsOpen 开启状态 businessCircleThirdId  商家状态 不为null查未绑定商家的
     *  
     * @return List<BusinessCircle>
     */
    List<Object> findBusinessCirclesByMap(Map<String,Object> map);
    
    /**
     * 查询商圈的个数
     * @param map
     * @return int
     */
    int findBusinessCirclesCount(Map<String,Object> map);
    
    /**
     * 根据商家Id获得商圈
     * @param id
     * @return int
     */
    BusinessCircle findBusinessCircleById(Long id);
    /**
     * 根据商圈名称获得商圈
     * @param name
     * @return int
     */
    BusinessCircle findBusinessCircleByName(String name);
    /**
     * 根据商商圈Id获得商圈信息
     * @param Id
     * @return int
     */
    BusinessCircle findBusinessCircleByBusinessCircleId(Long id);
    
    /**
     * 修改所有商圈
     * @param businessCircle
     * @return int
     */
    int delThirdIdToBusinessCircle(BusinessCircle businessCircle);
}
