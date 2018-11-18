package com.ningpai.api.dao.impl;

import com.ningpai.api.bean.OOrder;
import com.ningpai.api.bean.OOrderAllInfo;
import com.ningpai.api.dao.IOrderMapper;
import com.ningpai.manager.base.BasicSqlSupport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 开放接口--订单开发
 * @author lih
 * @version 2.0
 * @since 15/8/28
 */
@Repository("openOrderMapper")
public class OrderMapperImpl extends BasicSqlSupport implements IOrderMapper{
    /**
     * 获取订单列表
     *
     * @param map 分页参数
     * @return 订单列表
     */
    @Override
    public List<OOrder> list(Map<String, Object> map) {
        return this.selectList("com.ningpai.order.dao.OOrderMappe.list",map);
    }

    /**
     * 查询订单详情
     *
     * @param orderCode 订单编号
     * @return 订单详情
     */
    @Override
    public OOrderAllInfo get(String orderCode) {
        return this.selectOne("com.ningpai.order.dao.OOrderMappe.get",orderCode);
    }

    /**
     * 查询总行数
     *
     * @return
     */
    @Override
    public int count() {
        return this.selectOne("com.ningpai.order.dao.OOrderMappe.count");
    }
}
