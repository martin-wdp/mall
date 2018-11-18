package com.ningpai.third.analysis.service;

import java.util.List;
import com.ningpai.third.analysis.bean.OCustomerFollow;
import com.ningpai.third.analysis.bean.OOrder;

/**
 *<p>数据分析</p>
 *@author zhanghl
 *@versuin 2.0
 *@since 2015.07.30
 */
public interface AnalysisService {

    /**
     * 查询收藏商品列表
     * @param thirdId   商家ID
     * @param startTime 起始日期
     * @param endTime   终止日期
     * @param catId     分类ID
     * @return List  收藏商品的集合
     */
    List<OCustomerFollow> selectThirdFollowGoods(Long thirdId, String startTime, String endTime, Long catId);

    /**
     * 根据商家ID查询订单量
     * @param businessId 商家ID
     * @return List  订单集合
     */
    List<OOrder> queryCountByDay(Long businessId);

    /**
     * 根据商家ID查询一段时间内不成功的订单
     * @param startTime  起始时间
     * @param endTime    结束日期
     * @param businessId 商家Id
     * @return  单个订单对象
     */
    OOrder queryNoSuccCountByDay(String startTime, String endTime, Long businessId);

    /**
     * 根据商家ID查询一段时间内成功的订单
     * @param startTime  起始日期
     * @param endTime    结束日期
     * @param businessId 商家ID
     * @return  单个订单对象
     */
    OOrder querySuccCountByTime(String startTime, String endTime, Long businessId);

}
