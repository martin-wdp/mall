package com.ningpai.api.service.impl;

import com.alibaba.fastjson.JSON;
import com.ningpai.api.bean.OOrder;
import com.ningpai.api.dao.IOrderMapper;
import com.ningpai.api.service.IOrderService;
import com.ningpai.api.util.OpenPageBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lih
 * @version 2.0
 * @since 15/8/31
 */
@Service("openOrderService")
public class OrderServiceImpl implements IOrderService{


    /**
     * 开放接口--订单接口
     */
    @Resource(name="openOrderMapper")
    private IOrderMapper orderMapper;


    /**
     * 订单列表
     *
     * @param pageNo   页数   默认1
     * @param pageSize 每页大小 默认15
     * @return 查询集合
     */
    @Override
    public String list( Integer pageNo, Integer pageSize) {
        Integer pageNoNew = pageNo;
        Integer pageSizeNew = pageSize;
        // 设置参数容器
        Map<String, Object> map = new HashMap<String, Object>();
        //获取总数量
        int count=orderMapper.count();

        if(pageNoNew==null){
            pageNoNew=1;
        }
        //判断每页数量是否为空
        if(pageSizeNew==null){
            pageSizeNew=15;
        }

        OpenPageBean pageBean=new OpenPageBean();
        //设置每页大小
        pageBean.setPageSize(pageSizeNew);
        //设置页数
        pageBean.setPageNo(pageNoNew);
        //设置总行数
        pageBean.setRows(count);
        //设置开始行数
        map.put("start",pageBean.getStartRowNum());
        //设置结束行数
        map.put("end", pageBean.getEndRowNum());

        try{
            //设置容器
            Map<String,Object> items=new HashMap<String,Object>();
            //接受集合
            List<OOrder> orderList=orderMapper.list(map);
            //设置集合
            items.put("item",orderList);
            //设置总数量
            items.put("total_results",orderList.size());
            //返回数据
            return JSON.toJSONString(items);
        }finally {
            map=null;
        }
    }

    /**
     * 订单详情
     *
     * @param orderCode 订单编号
     * @return
     */
    @Override
    public String get(String orderCode) {
        //获取订单编号并返回
        return JSON.toJSONString(orderMapper.get(orderCode));
    }

}
