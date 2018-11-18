/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.site.customer.service.impl;

import com.ningpai.site.customer.bean.OrderComplain;
import com.ningpai.site.customer.mapper.OrderComplainMapper;
import com.ningpai.site.customer.service.OrderComplainService;
import com.ningpai.site.customer.vo.CustomerConstantStr;
import com.ningpai.util.PageBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 订单投诉ServiceImpl
 * 
 * @see com.ningpai.site.customer.service.OrderComplainService
 * @author NINGPAI-zhangqiang
 * @since 2014年4月22日 下午3:14:46
 * @version 0.0.1
 */
@Service("orderComplainService")
public class OrderComplainServiceImpl implements OrderComplainService {

    /**
     * 订单申诉Mapper
     */
    private OrderComplainMapper complainMapper;

    /**
     * 添加订单投诉
     * 
     * @param orderComplain
     * @return
     */
    @Override
    public int addComplain(OrderComplain orderComplain) {
        // 新增申诉
        return complainMapper.insertSelective(orderComplain);
    }

    /**
     * 查询投诉列表
     * 
     * @param paramMap
     * @param pb
     * @return
     */
    @Override
    public PageBean queryComplainList(Map<String, Object> paramMap, PageBean pb) {
        // 查询投诉记录条数
        Long count = complainMapper.searchComplainCount(paramMap);
        pb.setRows(Integer.parseInt(count == null ? "0" : count.toString()));
        paramMap.put(CustomerConstantStr.STARTNUM, pb.getStartRowNum());
        paramMap.put(CustomerConstantStr.ENDNUM, pb.getEndRowNum());
        // 查询投诉记录列表
        pb.setList(complainMapper.selectComplainList(paramMap));
        return pb;
    }

    public OrderComplainMapper getComplainMapper() {
        return complainMapper;
    }

    @Resource(name = "orderComplainMapper")
    public void setComplainMapper(OrderComplainMapper complainMapper) {
        this.complainMapper = complainMapper;
    }

}
