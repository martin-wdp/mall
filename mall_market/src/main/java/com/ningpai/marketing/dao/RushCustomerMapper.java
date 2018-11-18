
package com.ningpai.marketing.dao;

import java.util.Map;

import com.ningpai.marketing.bean.RushCustomer;

/**
 * 每个账户限购中间表
 */
public interface RushCustomerMapper {

    /**
     * 查询当前会员可以购买的数量
     * @param map
     * @return
     */
    Integer selectByParamMap(Map<String, Object> map);

    /**
     * 插入当前会员购买限购 的货品
     * @param rushCustomer
     * @return
     */
    int insertCustomerRush(RushCustomer rushCustomer);
}
