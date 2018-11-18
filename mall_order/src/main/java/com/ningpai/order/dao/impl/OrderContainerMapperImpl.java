package com.ningpai.order.dao.impl;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.order.bean.OrderContainer;
import com.ningpai.order.dao.OrderContainerMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 装箱单dao
 * 
 * @author NINGPAI-LIH
 * @since 2014年7月1日15:35:05
 * 
 */
@Repository("OrderContainerMapper")
public class OrderContainerMapperImpl extends BasicSqlSupport implements
        OrderContainerMapper {

    /**
     * 插入内容
     * @param container
     *            参数
     */
    public void insertSelective(OrderContainer container) {
        this.insert("com.ningpai.web.dao.OrderContainerMapper.insertSelective",
                container);
    }

    /**
     * 批量插入内容
     * @param list
     */
    @Transactional
    public void insertContainers(List<OrderContainer> list) {
        this.insert(
                "com.ningpai.web.dao.OrderContainerMapper.insertContainers",
                list);
    }

    /**
     * 查询包裹下的所有商品
     * @param relationId
     *            关系id
     * @return
     */
    public List<OrderContainer> queryContainerByRelationId(Long relationId) {
        return this
                .selectList(
                        "com.ningpai.web.dao.OrderContainerMapper.queryContainerByRelationId",
                        relationId);
    }

    /**
     * 更该包裹
     * @param container
     */
    public void updateRelation(OrderContainer container) {
        this.update("com.ningpai.web.dao.OrderContainerMapper.updateRelation",
                container);
    }

    /**
     * 验证包裹下是有商品
     * @param relationId
     *            包裹id
     * @return
     */
    public Long verifyCount(Long relationId) {
        return this.selectOne(
                "com.ningpai.web.dao.OrderContainerMapper.verifyCount",
                relationId);
    }

    /**
     * 根据包裹内商品的id查询其详细内容
     * @param containerId
     * @return
     */
    public OrderContainer queryContainerByParam(Long containerId) {
        return this
                .selectOne(
                        "com.ningpai.web.dao.OrderContainerMapper.queryGoodsInfosByParam",
                        containerId);
    }

    /**
     * 根据包裹内id修改
     * @param container
     */
    public void updateGoodsNum(OrderContainer container) {
        this.selectOne(
                "com.ningpai.web.dao.OrderContainerMapper.updateGoodsNum",
                container);
    }

    /**
     * 根据rId 和货品Id获得包裹中商品的信息
     * @param map
     * @return
     */
    @Override
    public OrderContainer queryOrderContainerByGoodInfoId(
            Map<String, Object> map) {
        return this
                .selectOne(
                        "com.ningpai.web.dao.OrderContainerMapper.queryOrderContainerByGoodInfoId",
                        map);
    }
}
