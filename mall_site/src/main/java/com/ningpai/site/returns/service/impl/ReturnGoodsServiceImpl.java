package com.ningpai.site.returns.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.annotation.Resource;

import com.ningpai.order.bean.BackOrderLog;
import com.ningpai.order.dao.BackOrderLogMapper;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ningpai.order.bean.BackOrder;
import com.ningpai.order.bean.Order;
import com.ningpai.order.bean.OrderGoods;
import com.ningpai.order.dao.BackOrderMapper;
import com.ningpai.order.service.OrderService;
import com.ningpai.site.returns.bean.BackOrderGeneral;
import com.ningpai.site.returns.dao.ReturnGoodsMapper;
import com.ningpai.site.returns.service.ReturnGoodsService;

/***
 * 退货记录
 * 
 * @author zhanghailong
 *
 */
@Service("ReturnGoodsService")
public class ReturnGoodsServiceImpl implements ReturnGoodsService {

    private static final Logger LOGGER = Logger.getLogger(ReturnGoodsServiceImpl.class);

    // 注入dao层
    private ReturnGoodsMapper returngoods;
    // 注入订单dao层
    private OrderService orderservice;
    //
    private BackOrderMapper backOrderMapper;
    // 退单操作日志
    @Resource(name = "BackOrderLogMapper")
    private BackOrderLogMapper backOrderLogMapper;

    @Override
    @Transactional
    public int saveBackOrderGeneral(String wlname, String wlno, String orderNo) {
        // 保存物流信息返回值
        int result = 0;
        try {
            BackOrderGeneral general = new BackOrderGeneral();
            List<BackOrder> orders = returngoods.selectBackOrderId(orderNo);// 根据订单号获取退单对象
            for (int i = 0; i < orders.size(); i++) {
                BackOrder backOrder = orders.get(i);
                general.setOgisticsNo(wlno); // 物流单号
                general.setOgisticsName(wlname); // 物流名称
                general.setBackOrderId(backOrder.getBackOrderId()); // 退单ID
                general.setCreatTime(new Date()); // 时间
                result = returngoods.saveGeneral(general);
                if (1 == result) {
                    // 修改订单状态为 退货中 ：10
                    Order order = orderservice.getPayOrderByCode(orderNo);
                    order.setBackPrice(order.getBackPrice());
                    order.setOrderStatus("10");// 订单状态
                    result = returngoods.updateOrder(order); // 修改订单对象
                    if (1 == result) {

                        if (order.getBusinessId() == 0) {
                            backOrder.setBackCheck("1"); // 同意退货平台订单
                        } else {
                            backOrder.setBackCheck("3"); // 待商家收货商家订单
                        }
                        result = backOrderMapper.updateByPrimaryKeySelective(backOrder);
                    }
                    if (1 == result) {
                        // 插入退单操作日志
                        BackOrderLog backOrderLog = new BackOrderLog();
                        backOrderLog.setBackLogStatus("4");
                        backOrderLog.setBackOrderId(backOrder.getBackOrderId());
                        backOrderLog.setBackLogPerson("customer");
                        backOrderLog.setBackLogTime(new Date());
                        result = backOrderLogMapper.insert(backOrderLog);
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error("",e);
            result = 0;
        }
        return result;

    }

    /**
     * 新增一条退货记录 ，更改交易表状态退单金额，更新商品信息表
     * 
     * @param orderId
     *            id:0 退款 id:1 退货
     * @return
     */
    @Override
    @Transactional
    public Boolean saveReturnGoodsDetail(Long orderId, String returnyuanyin, Long id) {
        int result = 0;// 保存退单记录返回的结果
        boolean bool = true; // 操作状态
        BackOrder backOrder = null; // 退单信息
        Order order = null; // 交易对象
        // Customer customer = null; //当前登录会员
        try {
            order = this.returngoods.selectOrderById(orderId);
            // customer =
            // this.returngoods.selectCustomerById(order.getCustomerId());
            // 根据会员ID查询单个会员对象
            backOrder = new BackOrder(); // 退单对象
            backOrder.setBackOrderCode(this.createBackOrderNo());// 退单单号
                                                                 // 按照日期自动生成
            backOrder.setOrderCode(order.getOrderCode()); // 订单单号（从交易记录获取）
            backOrder.setBackTime(new Date()); // 退单时间（当前系统时间）
            backOrder.setBackRemark(returnyuanyin); // 退单原因（前台传过来）
            backOrder.setBackPrice(order.getOrderPrice()); // 退款总金额（交易记录获取）
            backOrder.setBackRealName(order.getShippingPerson()); // 退单人真是姓名
            backOrder.setBusinessId(order.getBusinessId()); // 商家Id(交易记录获取)
            backOrder.setOrderstatus(id); // 0:退款1：退货
            backOrder.setSalesmanId(order.getSalesmanId());
            if (id == 0) {
                backOrder.setBackCheck("6"); // 状态需退款
            }
            // 非空验证退单单号
            if (null != backOrder.getBackOrderCode()) {
                LOGGER.info("退单成功，退单单号为：" + backOrder.getBackOrderCode());
            }
            result = returngoods.saveBackOrder(backOrder);// 保存退单记录
        } catch (Exception e) {
            LOGGER.error("",e);
            bool = false;
        }

        if (result == 1 && bool && updateOrder(backOrder, orderId, id)) {
            bool = true;
        } else {
            bool = false;
        }
        return bool;
    }

    /***
     * //更新交易表的状态
     * 
     * @param backOrder
     *            退单表
     * @param orderId
     *            交易表记录id
     * @return
     */
    public Boolean updateOrder(BackOrder backOrder, Long orderId, Long id) {
        Order order = null; // 交易对象
        boolean bool = true; // 操作状态
        int result = 0; // 更新数据返回的结果
        // 更改交易记录的状态
        try {
            order = new Order();
            // order = this.returngoods.selectOrderById(orderId);
            order.setOrderId(orderId);
            if (id == 0) {
                order.setOrderStatus("12"); // 12退款审核中
            } else {
                order.setOrderStatus("7"); // 7退货审核中
            }
            order.setBackPrice(backOrder.getBackPrice()); // 退单金额
            result = returngoods.updateOrder(order);
        } catch (Exception e) {
            LOGGER.error("",e);
            bool = false;
        }
        if (result == 1 && bool && updateOrderGoods(order, backOrder)) {
            bool = true;
        } else {
            bool = false;
        }
        return bool;
    }

    /***
     * 更新记录商品对象
     * 
     * @param order
     *            交易对象
     * @param backOrder
     *            退单表对象
     * @return
     */
    public Boolean updateOrderGoods(Order order, BackOrder backOrder) {
        List<OrderGoods> orderGoods = null; // 订单商品信息表
        boolean bool = true; // 操作状态
        int updateOrderGoodsResult = 0;// 更新订单商品表返回的结果
        try {
            orderGoods = returngoods.selectOrderGoodsById(order.getOrderId());
            OrderGoods goods = orderGoods.get(0);
            // 退单单号
            goods.setBackOrderCode(backOrder.getBackOrderCode());
            // 更改订单商品表表
            updateOrderGoodsResult = returngoods.updateOrderGoods(goods);

        } catch (Exception e) {
            LOGGER.error("更新记录商品对象失败！", e);
        }

        if (updateOrderGoodsResult == 1) {
            bool = true;
        } else {
            bool = false;
        }
        return bool;

    }

    /***
     * 生成退单单号 系统时间+随机六位数字
     * 
     * @return
     */
    public String createBackOrderNo() {
        String d = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
        Random r = new Random();
        Double d1 = r.nextDouble();
        String s = d1 + "";
        s = d + s.substring(3, 3 + 6);
        return s;
    }

    public ReturnGoodsMapper getReturngoods() {
        return returngoods;
    }

    @Resource(name = "ReturnGoodsMapper")
    public void setReturngoods(ReturnGoodsMapper returngoods) {
        this.returngoods = returngoods;
    }

    public OrderService getOrderservice() {
        return orderservice;
    }

    @Resource(name = "OrderService")
    public void setOrderservice(OrderService orderservice) {
        this.orderservice = orderservice;
    }

    public BackOrderMapper getBackOrderMapper() {
        return backOrderMapper;
    }

    @Resource(name = "BackOrderMapper")
    public void setBackOrderMapper(BackOrderMapper backOrderMapper) {
        this.backOrderMapper = backOrderMapper;
    }

}
