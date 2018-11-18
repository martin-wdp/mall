package com.junit.custom.customer.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.io.annotation.FileContent;
import org.unitils.mock.Mock;

import com.alibaba.fastjson.JSON;
import com.ningpai.customer.bean.CustomerConsume;
import com.ningpai.customer.dao.CustomerConsumeMapper;
import com.ningpai.customer.service.CustomerConsumeServiceMapper;
import com.ningpai.customer.service.impl.CustomerConsumeServiceMapperImpl;
import com.ningpai.util.PageBean;

/**
 * 会员充值消费Service单元测试
 * @author qiyuanyuan
 *
 */
public class CustomerConsumeServiceMapperTest extends UnitilsJUnit3{

    /**
     * 需要测试的Service
     */
    @TestedObject
    private CustomerConsumeServiceMapper consumeServiceMapper = new CustomerConsumeServiceMapperImpl();

    /**
     * 模拟
     */
    @InjectIntoByType
    Mock<CustomerConsumeMapper> customerConsumeMapperMock;
    
    /**
     * JS数据
     */
    @FileContent("customerConsumeList.js")
    private String customerConsumeListJS;
    
    /**
     * 共享数据
     */
    List<CustomerConsume> customerConsumeList;
    
    /**
     * 初始化
     */
    public void setUp(){
        customerConsumeList = JSON.parseArray(customerConsumeListJS,CustomerConsume.class);
    }
    
    /**
     * 测试删除会员积分记录 
     * 
     */
    @Test
    public void testDeleteCustomerConsume(){
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("parameterValues", new String[]{"1"});
        customerConsumeMapperMock.returns(1).deleteCustomerConsumeByBids(paramMap);
        assertEquals(1, consumeServiceMapper.deleteCustomerConsume(new String[]{"1"}));
        
    }
    

    /**
     * 测试按条件查询积分记录
     * 
     */
    @Test
    public void testSelectCustConsumeByCustConsume(){
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("consume", new CustomerConsume());
        paramMap.put("startRowNum", 0);
        paramMap.put("endRowNum", 15);
        paramMap.put("type", "1");
        customerConsumeMapperMock.returns(1).selectCustmerConsumeSize(new CustomerConsume());
        customerConsumeMapperMock.returns(customerConsumeList).selectCustConsumeByParamMap(paramMap);
        assertEquals(1, consumeServiceMapper.selectCustConsumeByCustConsume(new CustomerConsume(), new PageBean(), "1").getList().size());
    }
}
