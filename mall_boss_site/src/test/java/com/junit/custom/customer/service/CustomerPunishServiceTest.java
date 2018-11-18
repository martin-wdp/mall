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
import com.ningpai.customer.bean.CustomerPunish;
import com.ningpai.customer.dao.CustomerPunishMapper;
import com.ningpai.customer.service.CustomerPunishService;
import com.ningpai.customer.service.impl.CustomerPunishServiceImpl;
import com.ningpai.util.PageBean;

/**
 * 会员惩罚service接口单元测试
 * @author qiyuanyuan
 *
 */
public class CustomerPunishServiceTest extends UnitilsJUnit3{
    
    /**
     * 需要测试的Service
     */
    @TestedObject
    private CustomerPunishService customerPunishService = new CustomerPunishServiceImpl();
    
    /**
     * 模拟
     */
    @InjectIntoByType
    Mock<CustomerPunishMapper> customerPunishMapperMock;
    
    /**
     * 共享数据
     */
    List<CustomerPunish> customerPunishList;
    
    /**
     * JS数据
     */
    @FileContent("customerPunishList.js")
    private String customerPunishListJS;
    
    /**
     * 初始化数据
     */
    public void setUp(){
        customerPunishList = JSON.parseArray(customerPunishListJS, CustomerPunish.class);
    }
    
    /**
     * 测试分页查询会员惩罚
     */
    @Test
    public void testSelectPunishInfoByPage(){
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("startRowNum", 0);
        paramMap.put("endRowNum", 15);
        customerPunishMapperMock.returns(1).selectPunishInfoCount();
        customerPunishMapperMock.returns(customerPunishList).selectPunishInfoByPage(paramMap);
        assertEquals(1, customerPunishService.selectPunishInfoByPage(new PageBean()).getList().size());
    }
    
    /**
     * 测试添加会员惩罚
     */
    @Test
    public void testAddPunishInfo(){
        customerPunishMapperMock.returns(1).insertSelective(customerPunishList.get(0));
        assertEquals(1, customerPunishService.addPunishInfo(customerPunishList.get(0)));
    }
    
    /**
     * 测试根据id查询会员惩罚
     */
    @Test
    public void testQueryPunishInfoById(){
        customerPunishMapperMock.returns(customerPunishList.get(0)).selectByPrimaryKey(1L);
        assertNotNull(customerPunishService.queryPunishInfoById(1L));
    }
    
    /**
     * 测试根据ID修改会员惩罚
     */
    @Test
    public void testUpdatePunishInfoById(){
        customerPunishMapperMock.returns(1).updateByPrimaryKeySelective(customerPunishList.get(0));
        assertEquals(1, customerPunishService.updatePunishInfoById(customerPunishList.get(0)));
    }
    
    /**
     * 测试删除会员惩罚
     */
    @Test
    public void testUpdateDelflagById(){
        customerPunishMapperMock.returns(1).updateDelflag(1L);
        assertEquals(1, customerPunishService.updateDelflagById(1L));
    }
    
    /**
     * 测试查询所有会员惩罚
     */
    @Test
    public void testQueryAllRules(){
        customerPunishMapperMock.returns(customerPunishList).queryAllRules();
        assertEquals(1, customerPunishService.queryAllRules().size());
    }
    
    /**
     * 测试查询单个会员惩罚
     */
    @Test
    public void testQueryIdByRule(){
        customerPunishMapperMock.returns(customerPunishList.get(0)).queryIdByRule(customerPunishList.get(0));
        assertNotNull(customerPunishService.queryIdByRule(customerPunishList.get(0)));
    }
     
}
