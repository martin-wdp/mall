package com.ningpai.goods.dao;

import java.util.List;
import java.util.Map;

/**
 * 第三放商品审核DAO
 * 
 * @author zhouxu
 *
 */
public interface GoodsAuditMapper {

    /**
     * 获取审核数
     * 
     * @return
     */
    int selectAuditRows(Map<String, Object> map);

    /**
     * 获取审核列表
     * 
     * @param map
     * @return
     */
    List<Object> selectAuditList(Map<String, Object> map);

    /**
     * 获取审核开关标记
     * 
     * @return
     */
    String selectAuditAction();

    /**
     * 审核开、关控制
     * 
     * @param isThirdAuditUsed
     * @return
     */
    int updateAuditAction(String isThirdAuditUsed);

    /**
     * 审核通过
     * 
     * @param goodsId
     * @return
     */
    int auditByGoodsId(Long goodsId);

    /**
     * 拒绝审核
     * 
     * @param goodsId
     * @return
     */
    int refuseAuditByGoodsId(Map<String, Object> map);

}
