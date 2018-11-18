package com.ningpai.manager.mapper;

import java.util.List;
import java.util.Map;

import com.ningpai.manager.bean.EchartsTimeCount;
import com.ningpai.manager.bean.IndexCountBean;

/**
 * 待办接口
 * 
 * @author ggn
 *
 */
public interface IndexCountBeanMapper {

    /**
     * 查询首页待办信息
     * 
     * @return IndexCountBean
     */
    IndexCountBean selectIndexCount(Map<String, Object> paramMap);

    /**
     * 查询下单笔数 一周
     * 
     * @param map
     * @return List
     */
    List<EchartsTimeCount> selectSubOrderList(Map<String, Object> map);

    /**
     * 查询支付笔数
     * 
     * @param map
     * @return List
     */
    List<EchartsTimeCount> payorderlist(Map<String, Object> map);

    /**
     * 查询发货笔数
     * 
     * @param map
     * @return List
     */
    List<EchartsTimeCount> sendorderlist(Map<String, Object> map);
}
