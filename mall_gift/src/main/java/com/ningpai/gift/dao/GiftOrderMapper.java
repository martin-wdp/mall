package com.ningpai.gift.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.gift.bean.GiftOrder;
import com.ningpai.gift.vo.GiftOrderVo;

/**
 * 积分订单DAO
 * @author qiyuanyuan
 *
 */
public interface GiftOrderMapper {

     /**
     * 查询积分兑换订单数目
     * @param paramMap
     *             查询参数
     * @return int
     */
    int giftOrderCount(Map<String,Object> paramMap);
    
    /**
     * 查询积分兑换订单列表
     * @param paramMap
     *             查询参数
     * @return List
     */
    List<Object> giftOrderList(Map<String,Object> paramMap);
    
    /**
     * 修改积分订单
     * @param giftOrder 
     *             积分订单{@link com.ningpai.gift.bean.GiftOrder}
     * @return int
     */
     int updateGiftOrder(GiftOrder giftOrder);
     
     /**
      * 订单详情
      * @param orderId
      *         订单Id
      * @return 订单对象
      */
     GiftOrderVo selectByOrderId(Long orderId);
     
     /**
      * 根据订单编号查询订单详情
      * @param orderCode  订单编号
      * @return 订单对象
      */
     GiftOrderVo selectByOrderCode(String orderCode);
     
     /**
      *  查询订单是否存在
      * @param orderCode
      *             订单编号
      * @return 查询到的条数
      */
     Long existOrderCode(String orderCode);
     
     /**
      * 修改订单
      * @param giftOrder
      *              订单对象
      * @return
      *     int
      */
     int updateByPrimaryKeySelective(GiftOrder giftOrder);
}
