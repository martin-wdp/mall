package com.ningpai.site.giftshop.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.site.giftshop.bean.GiftOrder;
import com.ningpai.site.giftshop.vo.GiftOrderVo;

/**
 * 积分商城订单DAO接口
 * @author qiyuanyuan
 *
 */
public interface GiftOrderMapper {
     /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2015-01-07 15:54:22
     */
    int deleteByPrimaryKey(Long giftOrderId);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-01-07 15:54:22
     */
    int insert(GiftOrder giftOrder);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2015-01-07 15:54:22
     */
    int insertSelective(GiftOrder giftOrder);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2015-01-07 15:54:22
     */
    GiftOrder selectByPrimaryKey(Long giftOrderId);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-01-07 15:54:22
     */
    int updateByPrimaryKeySelective(GiftOrder giftOrder);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2015-01-07 15:54:22
     */
    int updateByPrimaryKey(GiftOrder giftOrder);
    
    /**
     * 查询积分兑换商品排行
     * @return list
     */
    List<GiftOrderVo> orderVoList();
    
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
     * 订单详情
     * @param orderId
     *         订单Id
     * @return 订单对象
     */
    GiftOrderVo selectByOrderId(Long orderId);
}
