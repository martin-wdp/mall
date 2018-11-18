package com.ningpai.goods.service;

/**
 * 开关控制service
 * 
 * @author zhoux
 *
 */
public interface GetOnOffService {

    /**
     * 获取商品审核开关标记
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
