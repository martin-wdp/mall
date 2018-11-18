package com.ningpai.goods.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ningpai.goods.dao.GetOnOffMapper;
import com.ningpai.goods.service.GetOnOffService;

/**
 * 开关控制service的Impl
 * 
 * @author zhoux
 *
 */
@Service("GetOnOffService")
public class GetOnOffServiceImpl implements GetOnOffService {

    private GetOnOffMapper getOnOffMapper;

    public GetOnOffMapper getGetOnOffMapper() {
        return getOnOffMapper;
    }

    @Resource(name = "GetOnOffMapper")
    public void setGetOnOffMapper(GetOnOffMapper getOnOffMapper) {
        this.getOnOffMapper = getOnOffMapper;
    }

    /**
     * 获取商品审核开关标记
     * 
     * @return
     */
    @Override
    public String getOnOffFlag() {
        // 获取商品审核开关标记
        return getOnOffMapper.getOnOffFlag();
    }

    /**
     * 改变开关状态
     * 
     * @param isThirdAuditUsed
     * @return
     */
    @Override
    public int updateOnOffFlag(String isThirdAuditUsed) {
        // 改变开关状态
        return getOnOffMapper.updateOnOffFlag(isThirdAuditUsed);
    }

}
