package com.ningpai.site.customer.mapper;

import com.ningpai.site.customer.bean.OrderComplain;

import java.util.List;
import java.util.Map;

/**
 * 订单申诉Mapper
 * @Description 订单申诉Mapper
 * @author Songhl
 * @since 2015年8月27日 20:53:21
 */
public interface OrderComplainMapper {

    /**
     * 根据申诉Id删除
     * @param complainId
     * @return
     */
    int deleteByPrimaryKey(Long complainId);

    /**
     * 新增申诉
     * @param record
     * @return
     */
    int insert(OrderComplain record);

    /**
     * 新增申诉
     * @param record
     * @return
     */
    int insertSelective(OrderComplain record);

    /**
     * 根据申诉Id查询
     * @param complainId
     * @return
     */
    OrderComplain selectByPrimaryKey(Long complainId);

    /**
     * 修改订单申诉信息
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(OrderComplain record);

    /**
     * 修改订单申诉信息
     * @param record
     * @return
     */
    int updateByPrimaryKey(OrderComplain record);

    /**
     * 查询投诉记录条数
     * 
     * @param paramMap
     * @return Long {@link java.lang.Long}
     */
    Long searchComplainCount(Map<String, Object> paramMap);

    /**
     * 查询投诉记录列表
     * 
     * @param paramMap
     * @return List
     */
    List<Object> selectComplainList(Map<String, Object> paramMap);
}
