package com.ningpai.site.returns.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ningpai.customer.bean.Customer;
import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.order.bean.BackOrder;
import com.ningpai.order.bean.Order;
import com.ningpai.order.bean.OrderGoods;
import com.ningpai.site.returns.bean.BackOrderGeneral;
import com.ningpai.site.returns.dao.ReturnGoodsMapper;
/**
 * 退货明细
 * @author zhanghailong
 *
 */
@Repository("ReturnGoodsMapper")
public class ReturnGoodsMapperImpl extends BasicSqlSupport implements ReturnGoodsMapper{
    
    
    
    /***
     * 保存退单物流信息
     */
    @Override
    public int saveGeneral(BackOrderGeneral general) {
        return this.insert("com.ningpai.site.returns.ReturnGoodsMapper.saveGeneral",general);
    }
    /***
     * 根据订单号获取退单信息
     */
    @Override
    public List<BackOrder> selectBackOrderId(String orderNo) {
        return this.selectList("com.ningpai.site.returns.ReturnGoodsMapper.selectBackOrderByOrderNo",orderNo);
    }
    /**
     * 新增一条退货记录
     * @param returnGoods
     * @return
     */
    @Override
    public int saveBackOrder(BackOrder backOrder) {
        return this.insert("com.ningpai.site.returns.ReturnGoodsMapper.saveBackOrder",backOrder);
    }
    
    /**
     * 根据id获取单个的交易记录对象
     * @param orderId
     * @return
     */
    @Override
    public Order selectOrderById(Long orderId) {
        return this.selectOne("com.ningpai.site.returns.ReturnGoodsMapper.selectOrderById",orderId);
    }
    /**
     * 根据ID查询会员用户名
     * @param userId
     * @return
     */
    @Override
    public Customer selectCustomerById(Long userId) {
        return this.selectOne("com.ningpai.site.returns.ReturnGoodsMapper.selectCustomerById",userId);
    }

    
    /***
     * 更改交易记录(Order)的状态
     * @param order
     * @return
     */
    @Override
    public int updateOrder(Order order) {
        return  this.update("com.ningpai.site.returns.ReturnGoodsMapper.updateOrder",order);
    }

    
    /***
     * 更新订单商品信息对象
     * @param orderGoods
     * @return
     */
    public int updateOrderGoods(OrderGoods orderGoods) {
        return this.update("com.ningpai.site.returns.ReturnGoodsMapper.updateOrderGoods",orderGoods);
    }
    
    /***
     * 根据订单ID获取订单商品信息对象
     * @param orderGoodsId
     * @return
     */
    @Override
    public List<OrderGoods> selectOrderGoodsById(Long orderGoodsId) {
        return this.selectList("com.ningpai.site.returns.ReturnGoodsMapper.selectOrderGoodsById",orderGoodsId);
    }

}
