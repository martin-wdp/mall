package com.ningpai.goods.dao;

/**
 * 开关控制dao
 * 
 * @author zhoux
 *
 */
public interface GetOnOffMapper {

    /**
     * 获取审核商品开关标记
     * 
     * @return
     */
    String getOnOffFlag();

    /**
     * 改变开关状态
     * 
     * @param isThirdAuditUsed
     * @return
     */
    int updateOnOffFlag(String isThirdAuditUsed);

}
