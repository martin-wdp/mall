package com.ningpai.customer.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.customer.bean.ComplainVo;
import com.ningpai.customer.bean.OrderComplainBack;

/**
 * DAO-投诉接口
 * 
 * @author jiping
 * @since 2014年7月22日 下午5:14:50
 * @version 0.0.1
 */
public interface OrderComplainBackMapper {

    /**
     * 根据主键进行查询
     *
     * @param complainId
     * */
    OrderComplainBack selectByPrimaryKey(Long complainId);

    /**
     * 查询未处理投诉记录条数
     * 
     * @param orderCB
     * @return Long {@link java.lang.Long}
     */
    Long searchComplainCount(ComplainVo orderCB);

    /**
     * 查询未处理投诉记录列表
     * 
     * @param paramMap
     * @return List
     */
    List<Object> selectComplainList(Map<String, Object> paramMap);

    /**
     * 回复投诉
     */
    int replayCom(Map<String, Object> paramMap);

    /**
     * 查询已处理投诉记录条数
     * 
     * @param orderCB
     * @return Long {@link java.lang.Long}
     */
    Long searchComplainHadCount(ComplainVo orderCB);

    /**
     * 查询已处理投诉记录列表
     * 
     * @param paramMap
     * @return List
     */
    List<Object> selectComplainHadList(Map<String, Object> paramMap);

}
