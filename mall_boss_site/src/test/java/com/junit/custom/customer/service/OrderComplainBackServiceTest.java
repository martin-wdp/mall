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
import com.ningpai.customer.bean.ComplainVo;
import com.ningpai.customer.bean.OrderComplainBack;
import com.ningpai.customer.dao.OrderComplainBackMapper;
import com.ningpai.customer.service.OrderComplainBackService;
import com.ningpai.customer.service.impl.OrderComplainBackServiceImpl;
import com.ningpai.util.PageBean;

/**
 * 订单投诉service接口单元测试
 * @author qiyuanyuan
 *
 */
public class OrderComplainBackServiceTest extends UnitilsJUnit3{

    
    /**
     * 需要测试的service
     */
    @TestedObject
    private OrderComplainBackService orderComplainBackService = new OrderComplainBackServiceImpl();

    /**
     * 模拟
     */
    @InjectIntoByType
    Mock<OrderComplainBackMapper>OrderComplainBackMapperMock;
    
    /**
     * JS数据
     */
    @FileContent("complainVoList.js")
    private String complainVoListJS;
    
    @FileContent("orderComplainBack.js")
    private String orderComplainBackJS;
    
    /**
     * 共享数据
     */
    List<ComplainVo> complainVoList;
    
    OrderComplainBack orderComplainBack;
      
    /**
     * 初始化
     */
    public void setUp(){
        complainVoList = JSON.parseArray(complainVoListJS, ComplainVo.class);
        orderComplainBack = JSON.parseObject(orderComplainBackJS, OrderComplainBack.class);
    }
    
    /**
     * 测试查询未处理投诉记录
     */
    @Test
    public void testqueryComplainList(){
        Map<String, Object> paramMap = new HashMap<String, Object>();
        OrderComplainBackMapperMock.returns(1L).searchComplainCount(new ComplainVo());
        paramMap.put("order",new ComplainVo());
        paramMap.put("startRowNum", 0);
        paramMap.put("endRowNum", 15);
        OrderComplainBackMapperMock.returns(complainVoList).selectComplainList(paramMap);
        assertEquals(1, orderComplainBackService.queryComplainList(new PageBean(), new ComplainVo()).getList().size());
        
    }
    
    /**
     * 根据投诉id查询投诉记录
     */
    @Test
    public void testSelectByPrimaryKey(){
        OrderComplainBackMapperMock.returns(orderComplainBack).selectByPrimaryKey(1L);
        assertNotNull(orderComplainBackService.selectByPrimaryKey(1L));
    }
    
    /**
     * 回复投诉
     */
    @Test
    public void testReplayCom(){
        Map<String, Object> paramMap = new HashMap<String, Object>();
        OrderComplainBackMapperMock.returns(1).replayCom(paramMap);
        assertEquals(1, orderComplainBackService.replayCom(paramMap));
    }
    
    /**
     * 查询已投诉记录
     */
    @Test
    public void testQueryComplainHadList(){
        Map<String, Object> paramMap = new HashMap<String, Object>();
        OrderComplainBackMapperMock.returns(1L).searchComplainHadCount(new ComplainVo());
        paramMap.put("order",new ComplainVo());
        paramMap.put("startRowNum", 0);
        paramMap.put("endRowNum", 15);
        OrderComplainBackMapperMock.returns(complainVoList).selectComplainHadList(paramMap);
        assertEquals(1, orderComplainBackService.queryComplainHadList(new PageBean(), new ComplainVo()).getList().size());
    }
}
