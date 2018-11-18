package com.ningpai.system.service;

import com.ningpai.system.bean.LogisticsSingle;
import com.ningpai.util.PageBean;

/**
 * 物流单模板Service
 * 
 * @author rennn
 *
 */
public interface LogisticsSingleService {
    /**
     * 获得物流单模板列表
     * 
     * @param pb
     * @param thirdId
     * @return PageBean
     */
    PageBean getLogisticsSingleList(PageBean pb, Long thirdId);

    /**
     * 添加物流单模板信息
     * 
     * @param logisticsSingle
     * @return int
     */
    int addLogisticsSingle(LogisticsSingle logisticsSingle);

    /**
     * 根据条件查询模板信息
     * 
     * @param thirdId
     * @param companyId
     * @return
     */
    LogisticsSingle selectLogisticsSingle(Long thirdId, Long companyId);

    /**
     * 删除物流单模板
     * 
     * @param logisticsSingleId
     * @return int
     */
    int delLogisticsSingleById(Long logisticsSingleId);

    /**
     * 删除物流单模板
     * 
     * @param logisticsSingleId
     *            ,thirdId
     * @return int
     */
    int delLogisticsSingle(Long logisticsSingleId, Long thirdId);

    /**
     * 根据Id 查询物流单模板信息
     * 
     * @param logisticsSingleId
     * @param thirdId
     * @return
     */
    LogisticsSingle selectLogisticsSingleById(Long logisticsSingleId,
            Long thirdId);

    /**
     * 修改物流单模板信息
     * 
     * @param logisticsSingle
     * @return
     */
    int updateLogisticsSingle(LogisticsSingle logisticsSingle);
}
