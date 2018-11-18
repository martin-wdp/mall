package com.ningpai.goods.service;

import com.ningpai.goods.util.GoodsSearchBean;
import com.ningpai.util.PageBean;

/**
 * 第三方商品审核service
 * 
 * @author zhouxu
 *
 */
public interface GoodsAuditService {

    /**
     * 获取审核商品信息
     * 
     * @param pageBean
     * @param searchBean
     * @return
     */
    PageBean selectAuditGoods(PageBean pageBean, GoodsSearchBean searchBean);

    /**
     * 获取审核开关标记
     * 
     * @return
     */
    String selectAuditAction();

    /**
     * 开、关控制
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
    int refuseAuditByGoodsId(Long goodsId, String refuseReason);

}
