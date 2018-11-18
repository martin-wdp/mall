package com.ningpai.marketing.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.marketing.bean.FullbuyNoDiscountMarketing;

/**
 * 满x件打y折
 * 
 * @author zhoux
 *
 */
public interface FullbuyNoDiscountMarketingMapper {

    /**
     * 插入满x件打y折数据
     * 
     * @param fbuyno
     */
    int insertSelective(FullbuyNoDiscountMarketing fbuyno);

    /**
     * 查询促销信息
     * 
     * @param marketingId
     * @return
     */
    FullbuyNoDiscountMarketing selectByMarketId(Long marketingId);

    /**
     * 插入计数
     * 
     * @param param
     * @return
     */
    int insertCountConditionByMarketing(Map<String, Object> param);

    /**
     * 更新满购件数打折计数为空
     * 
     * @return
     */
    int update();

    /**
     * 修改满购件数打折
     * 
     * @param fbuyno
     * @return
     */
    int modifySelective(FullbuyNoDiscountMarketing fbuyno);

    /**
     * 查询促销信息
     * 
     * @param marketingId
     * @return
     */
    List<FullbuyNoDiscountMarketing> selectdiscountsByMarketId(Long marketingId);

    /**
     * 根据促销id删除满件打X折信息
     * 
     * @param marketingId
     *            促销活动Id{@link java.lang.Long}
     * @return int
     */
    int delFullbuyNoDiscountByNarketingId(Long marketingId);

}
