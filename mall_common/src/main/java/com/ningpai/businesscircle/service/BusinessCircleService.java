package com.ningpai.businesscircle.service;

import java.util.List;

import com.ningpai.businesscircle.bean.BusinessCircle;
import com.ningpai.util.PageBean;

/**
 * 商圈接口service
 * @author ggn 2015-01-20
 *
 */
public interface BusinessCircleService {
    
    
    /**
     * 添加商圈
     * @param businessCircle
     * @return int
     */
    int addBusinessCircle(BusinessCircle businessCircle);
    
    /**
     * 修改商圈的开启状态
     * @param businessCircleId
     * @return int
     */
    int updateBusinessCircleById(Long businessCircleId,String businessCircleIsOpen);
    
    /**
     * 删除商圈
     * @param businessCircleId
     * @return int
     */
    int delBusinessCircle(Long businessCircleId);
    
    /**
     * 修改商圈
     * @param businessCircle
     * @return int
     */
    int updateBusinessCircle(BusinessCircle businessCircle);
    /**
     * 根据商圈名字获得商圈信息
     * @param name
     * @return BusinessCircle
     */
    BusinessCircle findBusinessCircleByName(String name);
    /**
     * 绑定商圈
     * @param businessCircle
     * @return int
     */
    int bindBusinessCircle(Long storeId,Long businessCircle);
    /**
     * 获得未分配的商圈
     * @return
     */
    List<Object> getAll(String businessCircleIsOpen,String businessCircleThirdId);
    
    /**
     * 查询商圈信息(根据条件)分页
     *  @param map businessCircleIsOpen 开启状态 businessCircleThirdId  商家状态 不为null查未绑定商家的
     *  
     * @return PageBean
     */
    PageBean findBusinessCircles(PageBean pb,String searchId,String searchTex);
    /**
     * 根据商家编号查询商圈信息
     * @param thirdId
     * @return BusinessCircle
     */
    BusinessCircle findBusinessCircleByThirdId(Long thirdId);
    /**
     * 根据商圈Id 查询商圈信息
     * @param businessCircleId
     * @return
     */
    BusinessCircle findBusinessCircleByBusinessCircleId(Long businessCircleId);
}
