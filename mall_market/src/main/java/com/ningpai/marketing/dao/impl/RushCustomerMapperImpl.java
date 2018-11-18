
package com.ningpai.marketing.dao.impl;


import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.marketing.bean.RushCustomer;
import com.ningpai.marketing.dao.RushCustomerMapper;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository("RushCustomerMapper")
public class RushCustomerMapperImpl extends BasicSqlSupport implements RushCustomerMapper{


    @Override
    public Integer selectByParamMap(Map<String, Object> map) {
        Integer countRush=this.selectOne("com.ningpai.marketing.dao.RushCustomerMapper.selectByParamMap", map);
        return countRush==null?0:countRush;
    }

    @Override
    public int insertCustomerRush(RushCustomer rushCustomer) {
        return this
                .insert("com.ningpai.marketing.dao.RushCustomerMapper.insertCustomerRush",
                        rushCustomer);
    }
}
