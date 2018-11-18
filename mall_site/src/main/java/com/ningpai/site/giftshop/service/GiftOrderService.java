package com.ningpai.site.giftshop.service;

import java.util.List;
import java.util.Map;

import com.ningpai.site.giftshop.bean.GiftOrder;
import com.ningpai.site.giftshop.vo.GiftOrderVo;
import com.ningpai.util.PageBean;

/**
 * 积分商城赠品订单Service接口
 * @author qiyuanyuan
 *
 */
public interface GiftOrderService {
    /**
     * 提交订单
     * @param giftOrder
     * @return
     */
    int subOrder(GiftOrder giftOrder,Long giftStock);
    
    /**
     * 查询积分兑换商品排行
     * @return list
     */
    List<GiftOrderVo> orderList();
    
    /**
     * 前台查询积分兑换列表
     * @param pb
     *                 分页
     * @param paramMap
     *             查询参数
     * @return
     */
    PageBean queryGiftOrder(PageBean pb,Map<String, Object> paramMap);
    
      /**
        * 修改订单信息
        * 
        * @param GiftOrder
        *            要修改的信息
        * @return 修改结果
        */
    int updateOrderVice(Long giftOrdeId);
    
    
     /**
        * 订单详情
        * @param orderId
        * @return
        */
     GiftOrderVo orderDetail(Long orderId);
     
     
}
