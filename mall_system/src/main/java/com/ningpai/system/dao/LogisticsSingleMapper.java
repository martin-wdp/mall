package com.ningpai.system.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.system.bean.LogisticsSingle;

/**
 * 物流单模板Dao
 * 
 * @author Rennn
 *
 */
public interface LogisticsSingleMapper {
    /**
     * 添加
     * 
     * @param logisticsSingle
     * @return int
     */
    int addLogisticsSingle(LogisticsSingle logisticsSingle);

    /**
     * 修改
     * 
     * @param logisticsSingle
     * @return int
     */
    int updateLogisticsSingle(LogisticsSingle logisticsSingle);

    /**
     * 根据物流单模板编号删除物流单模板
     * 
     * @param id
     * @return int
     */
    int delLogisticsSingleById(Long id);

    /**
     * 根据物流单模板编号和thirdId删除物流单模板
     * 
     * @param map
     * @return int
     */
    int delLogisticsSingle(Map<String, Object> map);

    /**
     * 根据商家编号查询所有物流单模板(分页显示)
     * 
     * @return List<LogisticsSingle>
     */
    List<Object> selectAll(Map<String, Object> map);

    /**
     * 根据商家编号查询物流模板个数
     * 
     * @param map
     * @return int
     */
    int selectLogisticsSingleCount(Map<String, Object> map);

    /**
     * 根据条件查询模板
     * 
     * @param map
     *            thirdId 商家编号 companyId 物流公司编号 logisticsSingleId 模板编号
     * @return LogisticsSingle
     */
    LogisticsSingle selectLogisticsSingle(Map<String, Object> map);

}
