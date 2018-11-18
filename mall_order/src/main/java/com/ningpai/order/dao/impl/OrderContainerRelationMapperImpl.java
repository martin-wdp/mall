package com.ningpai.order.dao.impl;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.order.bean.OrderContainerRelation;
import com.ningpai.order.dao.OrderContainerRelationMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 装箱单关系dao
 * 
 * @author NINGPAI-LIH
 * @since 2014年7月1日15:43:09
 * 
 */
@Repository("OrderContainerRelationMapper")
public class OrderContainerRelationMapperImpl extends BasicSqlSupport implements
        OrderContainerRelationMapper {

    /**
     * 根据定单插入相应的装箱单
     * @param relation
     */
    @Transactional
    public void insertSelective(OrderContainerRelation relation) {
        this.insert(
                "com.ningpai.web.dao.OrderContainerRelationMapper.insertSelective",
                relation);
    }

    /**
     * 查询最后一次插入的id
     * @return
     */
    public Long selectLastId() {
        return this
                .selectOne("com.ningpai.web.dao.OrderContainerRelationMapper.selectLastId");
    }

    /**
     * 根据订单id查询这笔订单下面所有的包裹
     * @param orderId
     *            订单id
     * @return
     */
    public List<OrderContainerRelation> selectListByOrderIds(Long orderId) {
        return this
                .selectList(
                        "com.ningpai.web.dao.OrderContainerRelationMapper.selectListByOrderIds",
                        orderId);
    }

    /**
     * 删除包裹
     * @param relationId
     */
    public void delRelationById(Long relationId) {
        this.insert(
                "com.ningpai.web.dao.OrderContainerRelationMapper.delRelationById",
                relationId);
    }

    /**
     * 更新运货单
     * @param relation
     */
    public void updateRelation(OrderContainerRelation relation) {
        this.update(
                "com.ningpai.web.dao.OrderContainerRelationMapper.updateRelation",
                relation);

    }

    /**
     * 查询包裹
     * @param orderId
     *            订单
     * @return
     */
    public List<OrderContainerRelation> getExpressNo(Long orderId) {
        return this
                .selectList(
                        "com.ningpai.web.dao.OrderContainerRelationMapper.getExpressNo",
                        orderId);
    }

    /**
     * 删除包裹商品
     *
     * @param cId
     *            包裹id
     */
    @Override
    public void delContainerByCId(Long cId) {
        this.insert(
                "com.ningpai.web.dao.OrderContainerRelationMapper.delContainerByCId",
                cId);
    }
}
