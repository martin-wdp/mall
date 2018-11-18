package com.ningpai.third.analysis.dao;
import java.util.List;
import java.util.Map;
import com.ningpai.third.analysis.bean.OCustomerFollow;
import com.ningpai.third.analysis.bean.OOrder;
/**
 *<p>数据分析</p>
 *@author zhanghl
 *@versuin 2.0
 *@since 2015.07.30
 */
public interface AnalysisServiceMapper {

    /**
     * 查询货品 第三方下载最多的5个
     * @param paramMap
     * @return List
     */
    List<OCustomerFollow> selectThirdFollowGoods(Map<String, Object> paramMap);

    /**
     * 查询当前商品 在不同时间内的 收藏量
     * @param paramMap
     * @return List
     */
    List<OCustomerFollow> selectThirdFollowGoodsCount(Map<String, Object> paramMap);

    /**
     * 根据时间分组查询每天的订单数
     * @return
     */
    List<OOrder> queryCountByDay(Map<String, Object> paramMap);

    /**
     * 查询一段时间内的成功订单数
     * @param paramMap
     * @return
     */
    OOrder querySuccCountByTime(Map<String, Object> paramMap);

    /**
     * 查询一段时间内不成功的订单数
     * @param paramMap
     * @return
     */
    OOrder queryNoSuccCountByDay(Map<String, Object> paramMap);

}
