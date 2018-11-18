package com.ningpai.site.returns.service;


/***
 * 退货记录
 * @author zhanghailong
 *
 */
public interface ReturnGoodsService {
    
    /***
     * 保存退单物流信息
     * @param wlname  物流名称
     * @param wlno    物流单号
     * @param orderNo  订单号
     */
    int saveBackOrderGeneral(String wlname,String wlno,String orderNo);
    

    /**
     * 新增一条退货记录 ，更改交易表状态退单金额，更新商品信息表
     * @param returnGoods
     * @return
     */
    Boolean saveReturnGoodsDetail(Long orderId,String returnYuanyin,Long id); 
}
