/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.customer.service.impl;

import com.ningpai.customer.bean.ComplainVo;
import com.ningpai.customer.bean.OrderComplainBack;
import com.ningpai.customer.dao.OrderComplainBackMapper;
import com.ningpai.customer.service.OrderComplainBackService;
import com.ningpai.util.PageBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @see com.ningpai.customer.service.OrderComplainBackService
 * @author jiping
 * @since 2014年7月22日 下午5:24:56
 * @version 0.0.1
 */
@Service("orderComplainBackService")
public class OrderComplainBackServiceImpl implements OrderComplainBackService {

    // spring注入
    private OrderComplainBackMapper complainMapper;

    /*
     * 
     * 查询未处理投诉记录
     * @see
     * com.ningpai.customer.service.OrderComplainBackService#queryComplainList
     * (java.util.Map, com.ningpai.util.PageBean)
     */
    @Override
    public PageBean queryComplainList(PageBean pageBean, ComplainVo orderCB) {
        Map<String, Object> paramMap = null;

        int no = 0;
        try {
            // 设置pageBean的总行数
            pageBean.setRows(Integer.parseInt(complainMapper.searchComplainCount(orderCB).toString()));
            no = pageBean.getRows() % pageBean.getPageSize() == 0 ? pageBean.getRows() / pageBean.getPageSize() : (pageBean.getRows() / pageBean.getPageSize() + 1);
            no = no == 0 ? 1 : no;
            // 页码大于最后一页 则设为最好一页的页码
            if (pageBean.getPageNo() >= no) {
                pageBean.setPageNo(no);
                pageBean.setStartRowNum((no - 1) * pageBean.getPageSize());
            }
            // 设置查询对象的起始编号和结束编号
            paramMap = new HashMap<String, Object>();
            paramMap.put("order", orderCB);
            paramMap.put("startRowNum", pageBean.getStartRowNum());
            paramMap.put("endRowNum", pageBean.getEndRowNum());
            pageBean.setList(complainMapper.selectComplainList(paramMap));
        } finally {
            // 置空对象
            paramMap = null;
        }
        return pageBean;
    }

    /*
     * 
     * 根据投诉id查询投诉记录
     * @see
     * com.ningpai.customer.service.OrderComplainBackService#selectByPrimaryKey
     * (java.lang.Long)
     */
    @Override
    public OrderComplainBack selectByPrimaryKey(Long complainId) {
        return complainMapper.selectByPrimaryKey(complainId);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.customer.service.OrderComplainBackService#replayCom(java.
     * util.Map)
     */
    @Override
    public int replayCom(Map<String, Object> paramMap) {
        return complainMapper.replayCom(paramMap);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.customer.service.OrderComplainBackService#queryComplainHadList
     * (com.ningpai.util.PageBean, com.ningpai.customer.bean.OrderComplainBack)
     */
    @Override
    public PageBean queryComplainHadList(PageBean pageBean, ComplainVo orderCB) {
        Map<String, Object> paramMap = null;

        int no = 0;
        try {
            // 设置pageBean的总行数
            pageBean.setRows(Integer.parseInt(complainMapper.searchComplainHadCount(orderCB).toString()));
            no = pageBean.getRows() % pageBean.getPageSize() == 0 ? pageBean.getRows() / pageBean.getPageSize() : (pageBean.getRows() / pageBean.getPageSize() + 1);
            no = no == 0 ? 1 : no;
            // 页码大于最后一页 则设为最好一页的页码
            if (pageBean.getPageNo() >= no) {
                pageBean.setPageNo(no);
                pageBean.setStartRowNum((no - 1) * pageBean.getPageSize());
            }
            // 设置查询对象的起始编号和结束编号
            paramMap = new HashMap<String, Object>();
            paramMap.put("order", orderCB);
            paramMap.put("startRowNum", pageBean.getStartRowNum());
            paramMap.put("endRowNum", pageBean.getEndRowNum());
            pageBean.setList(complainMapper.selectComplainHadList(paramMap));
        } finally {
            // 置空对象
            paramMap = null;
        }
        return pageBean;

    }

    public OrderComplainBackMapper getComplainMapper() {
        return complainMapper;
    }

    @Resource(name = "OrderComplainBackMapper")
    public void setComplainMapper(OrderComplainBackMapper complainMapper) {
        this.complainMapper = complainMapper;
    }

}
