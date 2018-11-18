package com.ningpai.marketing.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.marketing.bean.FullbuyNoCountMarketing;

/**
 * 满件数多少钱
 * 
 * @author zhouxu
 *
 */

public interface FullbuyNoCountMarketingMapper {

    /**
     * 插入满件数多少钱数据
     * 
     * @param fcountno
     * @return
     */
    int insertSelective(FullbuyNoCountMarketing fcountno);

    /**
     * 查询数据
     * 
     * @param marketingId
     * @return
     */
    FullbuyNoCountMarketing selectByMarketId(Long marketingId);

    /**
     * 插入计数
     * 
     * @param marketingId
     * @return
     */
    int insertCountConditionByMarketing(Map<String, Object> param);

    /**
     * 更新满购件数 金额计数都为空
     */
    int update();

    /**
     * 修改满购件数 金额
     * 
     * @param fcountno
     * @return
     */
    int modifySelective(FullbuyNoCountMarketing fcountno);

    /**
     * 查询数据
     * 
     * @param marketingId
     * @return
     */
    List<FullbuyNoCountMarketing> selectCountsByMarketId(Long marketingId);

    /**
     * 根据促销id删除满件打X折信息
     * 
     * @param marketingId
     *            促销活动Id{@link java.lang.Long}
     * @return int
     */
    int delFullbuyNoCounttByNarketingId(Long marketingId);
}
