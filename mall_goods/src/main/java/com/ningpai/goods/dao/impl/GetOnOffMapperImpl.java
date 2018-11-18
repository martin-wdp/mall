package com.ningpai.goods.dao.impl;

import org.springframework.stereotype.Repository;

import com.ningpai.goods.dao.GetOnOffMapper;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * 开关控制dao的impl
 * 
 * @author zhoux
 *
 */
@Repository("GetOnOffMapper")
public class GetOnOffMapperImpl extends BasicSqlSupport implements
        GetOnOffMapper {

    /**
     * 获取审核商品开关标记
     * 
     * @return
     */
    @Override
    public String getOnOffFlag() {
        return this
                .selectOne("com.ningpai.goods.dao.GetOnOffMapper.getOnOffFlag");
    }

    /**
     * 改变开关状态
     * 
     * @param isThirdAuditUsed
     * @return
     */
    @Override
    public int updateOnOffFlag(String isThirdAuditUsed) {
        return this.update(
                "com.ningpai.goods.dao.GetOnOffMapper.updateOnOffFlag",
                isThirdAuditUsed);
    }

}
