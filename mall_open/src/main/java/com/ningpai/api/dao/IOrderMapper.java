package com.ningpai.api.dao;

import com.ningpai.api.bean.OOrder;
import com.ningpai.api.bean.OOrderAllInfo;

import java.util.List;
import java.util.Map;

/**
 * 开放接口---订单接口
 * @author lih
 * @version 2.0
 * @since 15/8/27
 */
public interface IOrderMapper {

    /**
     *
     * 获取订单列表
     * @param map 分页参数
     * @return 订单列表
     */
    List<OOrder> list(Map<String,Object> map);

    /**
     * 查询订单详情
     * @param orderCode 订单编号
     * @return 订单详情
     */
    OOrderAllInfo get(String orderCode);

    /**
     * 查询总行数
     * @return
     */
    int count();
}
