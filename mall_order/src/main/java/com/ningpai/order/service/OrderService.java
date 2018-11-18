/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.order.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.ningpai.customer.bean.CustomerAddress;
import com.ningpai.goods.vo.GoodsProductReleSpecVo;
import com.ningpai.goods.vo.GoodsProductVo;
import com.ningpai.order.bean.Order;
import com.ningpai.order.bean.OrderContainer;
import com.ningpai.order.bean.OrderContainerRelation;
import com.ningpai.order.bean.OrderCoupon;
import com.ningpai.order.bean.OrderExpress;
import com.ningpai.order.bean.OrderGift;
import com.ningpai.order.bean.OrderGoods;
import com.ningpai.order.bean.OrderMarketing;
import com.ningpai.util.PageBean;

/**
 * 订单service
 * 
 * @author NINGPAI-LIH
 * @since 2014年6月27日14:07:18
 */
public interface OrderService {

    /**
     * 删除单个退单信息
     * 
     * @param backOrderId
     *            退单Id
     * @param customerId
     *            当前会员ID
     * @return
     */
    int deleteBackOrderById(Long backOrderId, Long customerId);

    /**
     * 根据时间查询订单信息
     * 
     * @param starTime
     * @param endTime
     * @return 订单信息 b b
     */
    List<Order> selectOrderListByTime(Date starTime, Date endTime);

    /**
     * 查询每天前十条数量
     * 
     * @return
     */
    List<OrderGoods> selectTopOrderGoods();

    /**
     * 根据时间查询订单总数量
     *
     * @return
     */
    int selectOrderCountByCurdate();

    /**
     * 返回提交订单时的第一个商品名称
     * 
     * @return
     */
    public String queryGoodsInfoName(Long orderId);

    /**
     * 更具订单id数组查询订单列表
     * 
     * @param order
     *            {@link com.ningpai.order.bean.Order}
     * @param pageBean
     *            {@link com.ningpai.util.PageBean}
     * @return PageBean
     */
    PageBean searchOrderList(Order order, PageBean pageBean, Long[] orderIds);
    /**
     * 更具订单id数组查询订单列表
     *
     * @param order
     *            {@link com.ningpai.order.bean.Order}
     * @param pageBean
     *            {@link com.ningpai.util.PageBean}
     * @return PageBean
     */
    PageBean searchSimpleOrderList(Order order, PageBean pageBean, Long[] orderIds);

    /**
     * 新订单列表页面分页
     * 
     * @param status
     * @param order
     * @param pageBean
     * @return
     */
    PageBean newajaxgetpagefoot(String status, Order order, PageBean pageBean);

    /**
     * 查询订单列表
     * 
     * @param order
     *            {@link com.ningpai.order.bean.Order}
     * @param pageBean
     *            {@link com.ningpai.util.PageBean}
     * @return PageBean
     */
    PageBean searchOrderList(Order order, PageBean pageBean);

    /**
     * 新订单查询列表页面
     * 
     * @param status
     * @param order
     * @param pageBean
     * @return
     */
    Map<String, Object> newsearchOrderList(String status, Order order,
            PageBean pageBean);


    /**
     * 根据商品id查询个数 boss后台删除商品用
     * @param goodsIds
     * @return
     */
    int queryOrderCountBygoodsIds(Long[] goodsIds);



    /**
     * 根据货品id查询个数 boss后台删除货品用
     * @param goodsInfoIds
     * @return
     */
    int queryOrderCountBygoodsInfoIds(Long[] goodsInfoIds);

    /**
     * 查询订单详细
     * 
     * @param orderId
     *            {@link Long}
     * @return Order
     */
    Order orderDetail(Long orderId);

    /**
     * 查询订单详细
     *
     * @param orderId
     *            {@link Long}
     * @return Order
     */
    Order orderSimpleDetail(Long orderId);

    /**
     * 查询物流信息
     * 
     * @param orderId
     *            {@link Long}
     * @return OrderExpress
     */
    OrderExpress expressDetail(Long orderId);

    /**
     * 发货
     * 
     * @param orderExpress
     *            {@link com.ningpai.order.bean.OrderExpress}
     * @return int
     */
    int sendOrder(OrderExpress orderExpress);

    /**
     * 发货
     *
     * @param pickName
     *            发货人名称
     * @param thirdId
     *            第三方标示
     * @return
     */
    int sendOrderByP(Long orderId, String status, String pickName,
            String pickingStatus, Long thirdId);

    /**
     * 插入订单
     * 
     * @param order
     * @return int
     */
    int insertOrder(Order order);

    /**
     * 查询刚刚插入的订单ID
     * 
     * @return
     */
    Long selectLastId();

    /**
     * 确认支付
     * 
     * @param orderId
     * @return int
     */
    int payOrder(Long orderId);

    /**
     * 已提交至银行等待处理
     *
     * @param orderId
     * @return orderId
     */
    int subBankPayOrder(Long orderId);

    /**
     * 查询订单
     * 
     * @param orderId
     * @return Order
     */
    Order getPayOrder(Long orderId);

    /**
     * 查询订单
     *
     * @param orderId
     * @return Order
     */
    Order getSimplePayOrder(Long orderId);

    /**
     * 查询订单根据COde
     * 
     * @param orderCode
     * @return Order
     */
    Order getPayOrderByCode(String orderCode);

    /**
     * 修改订单信息状态根据Id
     * 
     * @param orderId
     * @param status
     * @return int
     */
    int modifyOrderByKey(Long orderId, String status);

    /**
     * 修改订单信息状态根据Id
     *
     * @param orderId
     * @param thirdId
     * @param status
     * @return int
     */
    int modifyOrderByKey(Long orderId, Long thirdId, String status);

    /**
     * 根据状态查询订单总数
     * 
     * @param buinessId
     *            商家ID
     * @param orderStatus
     *            订单状态 0未付款 1已付款未发货 2已发货未确认收获 3已经收货 4作废
     * @return int
     */
    int businessOrderCount(Long buinessId, String orderStatus);

    /**
     * 添加订单促销信息
     * 
     * @param orderMarketing
     *            参数
     * @author NINGPAI-LIH
     */
    void insertSelective(OrderMarketing orderMarketing);

    /**
     * 查看最后加入的订单促销id
     * 
     * @return
     */
    Long selectOrderMarketLastId();

    /**
     * 插入所有订单赠品
     * 
     * @param gift
     */
    void insertOrderInfoGift(List<OrderGift> gift);

    /**
     * 插入所有的订单优惠劵
     */
    void insertCouponInfoGift(List<OrderCoupon> coupons);

    /**
     * 拣货
     * 
     * @param orderId
     * @param thirdId
     * @return gifts:赠品信息和赠品数量 orderPicking：拣货单信息 orderGoodsInfos商品信息和商品数量
     *         goodsGifts:赠品信息和赠品数量
     */
    Map<String, Object> queryByPincking(Long[] orderId, Long thirdId,
            String pinckingName, String status);

    /**
     * 拣货
     *
     * @param orderId
     * @param thirdId
     * @return gifts:赠品信息和赠品数量 orderPicking：拣货单信息 orderGoodsInfos商品信息和商品数量
     *         goodsGifts:赠品信息和赠品数量
     */
    Map<String, Object> querySimpleByPincking(Long[] orderId, Long thirdId,
            String pinckingName, String status);

    /**
     * 出库
     * 
     * @param orderId
     *            订单编号
     * @param thirdId
     *            第三方标示
     * @return
     */
    List<Order> selectOrderListsByOrderIds(Long[] orderId, Long thirdId);

    /**
     * 为订单添加默认包裹
     * 
     * @param orderId
     *            订单id
     * @return
     */
    void initContainerRelation(Long orderId);

    /**
     * 查询订单包裹
     * 
     * @param orderId
     * @return
     */
    List<OrderContainerRelation> queryContainerRalation(Long orderId);
    /**
     * 查询订单包裹
     *
     * @param orderId
     * @return
     */
    List<OrderContainerRelation> querySimpleContainerRalation(Long orderId);

    /**
     * 新增装箱单
     * 
     * @param orderId
     *            订单id
     */
    void addContainerRalation(Long orderId);

    /**
     * 更改包裹
     */
    void updateRelation(OrderContainer container);

    /**
     * 添加包裹商品
     */
    void addRelation(OrderContainer container);

    /**
     * 根据Id 获得包裹中商品的信息
     * 
     * @param cId
     * @return
     */
    OrderContainer queryOrderContainerById(Long cId);
    /**
     * 根据货品ID查询关联的规格值列表
     *
     * @param productId
     *            货品ID {@link java.lang.Long}
     * @return 查询到的关联列表 {@link java.util.List}
     *         {@link com.ningpai.goods.bean.GoodsProductReleSpec}
     */
    List<GoodsProductReleSpecVo> queryAllSimpleByProductId(Long productId);

    /**
     * 根据rId 和货品Id获得包裹中商品的信息
     * 
     * @param rId
     * @return OrderContainer
     */
    OrderContainer queryOrderContainerByGoodInfoId(Long rId, Long goodInfoId);

    /**
     * 验证包裹下是否有商品
     * 
     * @param relationId
     *            包裹id
     * @return
     */
    Long verifyCount(Long relationId);

    /**
     * 删除包裹
     * 
     * @param relationId
     *            包裹id
     */
    void delRelationById(Long relationId);

    /**
     * 删除包裹商品
     *
     * @param cId
     *            包裹id
     */
    void delContainerByCId(Long cId);

    /**
     * 发货
     * 
     * @param relationIds
     * @param expressNo
     */
    void updateSendOrderGoods(Long[] relationIds, String[] expressNo,
            int[] expressId);

    /**
     * 第三方发货
     * 
     * @param relationIds
     * @param expressNo
     */
    void updateThirdSendOrderGoods(Long[] relationIds, String[] expressNo,
            int[] expressId);

    /**
     * 分解包裹下的商品
     * 
     * @param cId
     *            包裹id
     * @param goodsSum
     *            要分割的数量
     */
    void splitOrderGoods(Long cId, Long goodsSum);

    /**
     * 查询近一个月新增订单
     * 
     * @return map list：前十条近一个月新增订单 count：近一个新增订单总数
     */
    Map<String, Object> selectNewOrderByParam();

    /**
     * 查询所有包裹
     * 
     * @param orderId
     *            订单id
     * @return 包裹信息
     */
    List<OrderContainerRelation> selectListByOrderIds(Long orderId);

    /**
     * 
     * @return
     * @throws java.text.ParseException
     */
    PageBean queryYOrder(HttpServletRequest request, Order order,
            PageBean pageBean, List<String> list) throws ParseException;

    /**
     *
     * @return
     * @throws java.text.ParseException
     */
    PageBean queryYSimpleOrder(HttpServletRequest request, Order order,
            PageBean pageBean, List<String> list) throws ParseException;

    /**
     * 查询某个订单下的所有商品
     * 
     * @param orderId
     *            订单id
     * @return
     */
    List<OrderGoods> queryOrderGoods(Long orderId);

    /**
     * 更改订单状态
     * 
     * @param orderId
     *            订单id数组
     * @param status
     *            要修改的订单状态
     * @return
     */
    int changeOrderIds(Long[] orderId, String status);

    /**
     * 修改订单金额
     * 
     * @param price
     *            要修改的订单金额
     * @param orderId
     *            订单编号
     */
    void modifyOrderPrice(BigDecimal price, Long orderId);

    /**
     * 中断订单
     * 
     * @param orderId
     *            订单id
     * @param status
     *            订单状态
     * @param reson
     *            订单中断原因
     * @return
     */
    int modifyOrderByKey(Long orderId, String status, String reson);

    /**
     * 修改订单的出库状态
     * 
     * @param orderId
     * @param status
     * @return
     */
    int updateSetCargoStatusByOrderId(Long orderId, String status);

    /**
     * 修改第三方订单的出库状态
     *
     * @param orderId
     * @param status
     * @return
     */
    int updateSetCargoStatusByThirdOrderId(Long orderId, String status,Long businessId);

    /**
     * 批量更改订单状态
     * 
     * @param orderId
     *            订单id数组
     * @param status
     *            要修改的订单状态
     * @return int
     */
    int updateOrderCargoStatusByOrderIds(Long[] orderId, String status);

    /**
     * 批量更改第三方订单状态
     *
     * @param orderId
     *            订单id数组
     * @param status
     *            要修改的订单状态
     * @param businessId
     *            商家Id
     * @return int
     */
    int updateOrderCargoStatusByThirdOrderIds(Long[] orderId, String status,Long businessId);

    /**
     * 判断订单的出库状态是否符合
     * 
     * @param status
     *            出库状态
     * @param orderId
     *            订单id
     * @return
     */
    int judgeStatus(String status, Long orderId);

    /**
     * 根据商品id查询购买商品记录
     * 
     * @return
     */
    List<GoodsProductVo> queryGoodsProductVoByOrderGoods(Long goodsInfoId);

    /**
     * 根据商品id和客户id查询最近一段时间的订单
     * 
     * @param goodsInfoId
     *            商品id
     * @param custId
     *            客户id
     * @param starTime
     *            查询开始时间
     * @return
     */
    Long selectGoodsInfoCount(Long goodsInfoId, Long custId, Date starTime);

    /**
     * 根据参数查询订单列表
     * 
     * @param paramMap
     *            可以放各种参数，只要在xml里添加相应的参数
     * @return
     */
    List<Order> selectByParam(Map<String, Object> paramMap);

    /**
     * 从快递100接口获取物流信息html链接
     * 
     * @param relation
     *            包裹信息
     * @return
     */
    String queryExpressInfoUrl(OrderContainerRelation relation);

    /**
     * 插入物流信息(E店宝专用)
     * @param expressNo
     * @param orderId
     * @return
     */
    int addExpress(String expressNo, Long orderId);

    /**
     * 从快递100接口获取物流信息html链接
     * @param logComId
     * @param expressNo
     * @return
     */
    String queryExpressInfoUrl(int logComId, String expressNo);

    /**
     * 根据时间分组查询每天销售量
     * 
     * @param startTime
     * @param endTime
     * @return
     */
    List<Order> querySaleCountByDay(String startTime, String endTime);

    /**
     * 根据时间分组查询每天销售额
     * 
     * @param startTime
     * @param endTime
     * @return
     */
    List<Order> querySaleMoneyByDay(String startTime, String endTime);

    /**
     * 返回订单列表总数量 积分订单列表 退单列表总数量
     * 
     * @return
     */
    Map<String, Object> getIndexOrderCount();

    /**
     * 删除订单
     * 
     * @param orderId
     * @return
     */
    int deleteOrderById(Long orderId);

    /**
     * 查询第三方订单
     * 
     * @param order
     * @param pageBean
     * @return
     */
    PageBean searchThirdOrderList(Order order, PageBean pageBean);

    /**
     * 定时器，定时去掉一定时间内未付款的订单
     */
    void cancelOrderByTime();

    /**
     * 根据总订单号查询订单信息
     * 
     * @param orderOldCode
     * @return
     */
    List<Order> getPayOrderByOldCode(String orderOldCode);

    /**
     * 更新订单货品表 标记为退单货品
     * 
     * @param goodsInfoId
     * @param orderId
     * @return
     */
    int updateOrderGoodsBack(Long goodsInfoId, Long orderId,
            String backOrderCode);

    /**
     * 根据订单id更改订单状态
     * 
     * @param orderId
     * @param orderStatus
     * @return
     */
    int updateStatusBackById(Long orderId, String orderStatus,
            BigDecimal backPrice);

    /**
     * 查询退货商品信息（根据orderId和退单编号查询）
     * 
     * @param orderId
     * @param backOrderCode
     * @return
     */
    List<OrderGoods> queryOrderGoodsByOrderIdAndBackCode(Long orderId,
            String backOrderCode);

    /**
     * 查询首页统计
     * 
     * @auth lih
     * @return 统计数据
     */
    Map<String, Object> queryStatistics();

    /**
     * 前台 货品发送后自动收货
     */
    void receiptConfirmation();

    /**获取订单详细
     * @param request
     * @param goodsNum
     * @param goodsIdP
     * @param customerName
     * @return Map
     */
    Map<String ,Object> ajaxGetorderDetail(HttpServletRequest request, Long[] goodsNum, Long[] goodsIdP, String customerName);

    /**
     *     后台保存添加的订单
     * @param goodsAllPrice
     * @param freightPrice
     * @param companyInfo
     * @param customerRemark
     * @param goodsIdP
     * @param goodsNum
     * @param distinctId
     * @param payType
     * @param invoiceTitle
     * @param invoiceType
     * @param invoiceContent
     * @param customerAddress
     * @return
     */
    int saveAddOrder(BigDecimal goodsAllPrice, BigDecimal freightPrice, String companyInfo, String customerRemark,
                     Long[] goodsIdP, Long[] goodsNum, Long distinctId, Long payType, String invoiceTitle,
                     String invoiceType, String invoiceContent, CustomerAddress customerAddress,String isvip);

    /**
     * 查询所有订单信息 此方法供后台订单列表中的导出订单列表功能使用
     *
     * @author houyichang 2015/8/14
     * @return
     * */
    List<Order> queryAllOrderList();

    /**
     * 新查询第三方订单
     * 
     * @param status
     *            订单状态
     * @param order
     *            订单
     * @param pageBean
     *            分页
     * @return
     */
    Map<String, Object> newsearchThirdOrderList(String status, Order order,
            PageBean pageBean);
}
