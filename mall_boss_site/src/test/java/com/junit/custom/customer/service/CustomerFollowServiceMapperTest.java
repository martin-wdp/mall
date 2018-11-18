package com.junit.custom.customer.service;

import java.util.List;

import org.junit.Test;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.io.annotation.FileContent;
import org.unitils.mock.Mock;

import com.alibaba.fastjson.JSON;
import com.ningpai.customer.bean.CustomerFollow;
import com.ningpai.customer.dao.CustomerFollowMapper;
import com.ningpai.customer.service.CustomerFollowServiceMapper;
import com.ningpai.customer.service.impl.CustomerFollowServiceMapperImpl;

/**
 * 会员商品关注Service接口单元测试
 * @author qiyuanyuan
 *
 */
public class CustomerFollowServiceMapperTest extends UnitilsJUnit3{
    
    /**
     * 需要测试的Service
     */
    @TestedObject
    private CustomerFollowServiceMapper customerFollowServiceMapper = new CustomerFollowServiceMapperImpl();

    
    /**
     * 模拟
     */
    @InjectIntoByType
    Mock<CustomerFollowMapper> customerFollowMapperMock;
    
    /**
     * JS数据
     */
    @FileContent("customerFollowList.js")
    private String customerFollowListJS;
    
    
    /**
     * 共享数据
     */
    List<CustomerFollow> customerFollowList;
    
    public void setUp(){
        customerFollowList = JSON.parseArray(customerFollowListJS, CustomerFollow.class);
    }
    
    /**
     * 测试查询按条件查询消费记录
     */
    @Test
    public void testSelectByCustomerId(){
        customerFollowMapperMock.returns(customerFollowList).selectCustFollowByCustId(1L);
        assertEquals(1, customerFollowServiceMapper.selectByCustomerId(1L).size());
    }
    
    /**
     * 测试根据货品id查询关注的会员id
     */
    @Test
    public void testSelectSendId(){
        customerFollowMapperMock.returns(customerFollowList).selectSendId(1L);
        assertEquals(1, customerFollowServiceMapper.selectSendId(1L).size());
    }
}
