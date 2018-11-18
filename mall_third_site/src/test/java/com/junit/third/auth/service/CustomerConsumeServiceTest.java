/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.junit.third.auth.service;

import java.math.BigDecimal;
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
import com.ningpai.third.auth.bean.CustomerConsume;
import com.ningpai.third.auth.mapper.CustomerConsumeMapper;
import com.ningpai.third.auth.service.CustomerConsumeService;
import com.ningpai.third.auth.service.impl.CustomerConsumeServiceImpl;
import com.ningpai.util.PageBean;

/**
 * SERVICE实体类-会员消费记录--单元测试
 * 
 * @author jiping
 * @since 2015年9月23日 下午4:22:11
 * @version 2.0
 */
public class CustomerConsumeServiceTest extends UnitilsJUnit3 {
    /**
     * 需要测试的类
     */
    @TestedObject
    private CustomerConsumeService customerConsumeService = new CustomerConsumeServiceImpl();

    /**
     * 模拟
     */
    @InjectIntoByType
    Mock<CustomerConsumeMapper> customerConsumeMapperMock;

    /**
     * JS数据
     */
    @FileContent("customerConsumeList.js")
    private String customerConsumeListJs;

    /**
     * 共享数据
     */
    List<CustomerConsume> customerConsumeList;

    /**
     * 初始化
     */
    public void setUp() {
        customerConsumeList = JSON.parseArray(customerConsumeListJs,
                CustomerConsume.class);
    }

    /**
     * 删除会员消费记录
     */
    @Test
    public void testDdeleteConsume() {
        customerConsumeMapperMock.returns(1).deleteByPrimaryKey(1L);
        assertEquals(1, customerConsumeService.deleteConsume(1L));
    }

    /**
     * 添加会员消费记录
     */
    @Test
    public void testSaveConsume() {
        customerConsumeMapperMock.returns(1).insertSelective(
                customerConsumeList.get(0));
        assertEquals(1,
                customerConsumeService.saveConsume(customerConsumeList.get(0)));
    }

    /**
     * 根据会员消费记录编号修改会员等级
     */
    @Test
    public void testUpdateConsume() {
        customerConsumeMapperMock.returns(1).updateByPrimaryKeySelective(
                customerConsumeList.get(0));
        assertEquals(
                1,
                customerConsumeService.updateConsume(customerConsumeList.get(0)));
    }

    /**
     * 根据会员消费记录编号获取会员消费记录
     */
    @Test
    public void testGetConsumeById() {
        customerConsumeMapperMock.returns(customerConsumeList.get(0))
                .selectByPrimaryKey(1L);
        assertNotNull(customerConsumeService.getConsumeById(1L));
    }

    /**
     * 按会员编号和时间标记查询消费记录的分页数据
     */
    @Test
    public void testQueryAllConsumeByCid() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("customerId", 1L);
        map.put("date", 2);
        customerConsumeMapperMock.returns(1).queryConsumeByCidCount(map);
        // 查询数据的总行数并设置到PageBean中
        map.put("startRowNum", 0);
        map.put("endRowNum", 15);
        customerConsumeMapperMock.returns(customerConsumeList)
                .queryAllConsumeByCid(map);
        assertNotNull(customerConsumeService.queryAllConsumeByCid(
                new PageBean(), 1L, 2));
    }

    /**
     * 根据会员编号查询消费总和
     */
    @Test
    public void testSelectTotalNumByCid() {
        customerConsumeMapperMock.returns(new BigDecimal(1)).selectTotalNumByCid(1L);
        assertEquals(new BigDecimal(1), customerConsumeService.selectTotalNumByCid(1L));
    }
}
