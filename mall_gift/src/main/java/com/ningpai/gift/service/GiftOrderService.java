package com.ningpai.gift.service;

import com.ningpai.gift.bean.GiftOrder;
import com.ningpai.gift.vo.GiftOrderVo;
import com.ningpai.util.PageBean;

/**
 * 积分订单查询service
 * @author qiyuanyuan
 *
 */
public interface GiftOrderService {
    /**
     * 查询积分订单
     * @param giftOrder
     * @param pb
     * @return
     */
   PageBean queryGiftOrder(GiftOrderVo giftOrder,PageBean pb);
   
   /**
    * 订单详情
    * @param orderId
    * @return
    */
   GiftOrderVo orderDetail(Long orderId);
   
   /**
    * 查询订单号是否存在
    * 
    * @param orderCode
    *            订单编号
    * @return 订单信息
    */
   int existOrderCode(String orderCode);
   
   /**
    * 修改订单信息
    * 
    * @param GiftOrder
    *            要修改的信息
    * @return 修改结果
    */
   int updateOrderVice(GiftOrder giftOrde);
   
   /**
    * 根据订单编号查询订单信息
    * @param orderCode
    *           订单编号
    * @return
    */
   GiftOrderVo queryByOrderCode(String orderCode);
}
