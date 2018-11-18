package com.ningpai.order.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.order.bean.OrderPicking;
import com.ningpai.order.dao.OrderPickingMapper;

/**
 * 拣货出库dao
 * 
 * @author NINGPAI-LIH
 * @since 2014年6月25日10:06:15
 * 
 */
@Repository("OrderPickingMapper")
public class OrderPickingMapperImpl extends BasicSqlSupport implements
        OrderPickingMapper {

    /**
     * 插入拣货or出库单信息
     * @param orPicking
     *            .picking_status 0：拣货 1：出库
     */
    @Transactional
    public void insertSelective(OrderPicking orPicking) {
        this.insert("com.ningpai.web.dao.OrderPickingMapper.insertSelective",
                orPicking);
    }

}
