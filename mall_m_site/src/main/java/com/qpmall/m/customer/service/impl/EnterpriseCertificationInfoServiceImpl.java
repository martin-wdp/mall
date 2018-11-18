package com.qpmall.m.customer.service.impl;

import com.qpmall.m.customer.bean.EnterpriseCertificationAllInfo;
import com.qpmall.m.customer.bean.EnterpriseCertificationInfo;
import com.qpmall.m.customer.dao.EnterpriseCertificationInfoMapper;
import com.qpmall.m.customer.service.EnterpriseCertificationInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by ly-qpmall on 2015/10/14.
 */
@Service("enterpriseCertificationInfoService")
public class EnterpriseCertificationInfoServiceImpl implements EnterpriseCertificationInfoService {
    /**
     * 保存企业验证信息
     *
     * @param qpEnterpriseCertificationInfo
     * @return
     */
    @Resource(name = "enterpriseCertificationInfoMapper")
    private EnterpriseCertificationInfoMapper enterpriseCertificationInfoMapper;

    /**
     * 查询所有
     * @return
     */
    @Override
    public List<EnterpriseCertificationInfo> selectAllInfos() {
        return enterpriseCertificationInfoMapper.selectAllInfos();
    }

    /**
     * 根据ID查询认证信息
     * @param enterpriseId
     * @return
     */
    @Override
    public EnterpriseCertificationInfo selectInfoById(Long enterpriseId) {
        return enterpriseCertificationInfoMapper.selectInfoById(enterpriseId);
    }

    /**
     * 根据条件查询
     * @param enterpriseCertificationInfo
     * @return
     */
    @Override
    public List<EnterpriseCertificationInfo> selectInfosByQpEn(EnterpriseCertificationInfo enterpriseCertificationInfo) {
        return enterpriseCertificationInfoMapper.selectInfosByQpEn(enterpriseCertificationInfo);
    }

    /**
     * 审核
     * @param param
     * @return
     */
    @Override
    public int updateCheckStatus(Map<String, Object> param) {
        return enterpriseCertificationInfoMapper.updateCheckStatus(param);
    }

    /**
     * 删除
     * @param enterpriseId
     * @return
     */
    @Override
    public int deleteEnterpriseAllInfoById(Long enterpriseId) {
        return enterpriseCertificationInfoMapper.deleteEnterpriseAllInfoById(enterpriseId);
    }

    @Override
    public List<EnterpriseCertificationAllInfo> selectEsAndCustomerAllInfos(Map<String,Integer> param) {
        return enterpriseCertificationInfoMapper.selectEsAndCustomerAllInfos(param);
    }

    @Override
    public EnterpriseCertificationAllInfo selectEsAndCustomerAllInfo(Long enterpriseId) {
        return enterpriseCertificationInfoMapper.selectEsAndCustomerAllInfo(enterpriseId);
    }

    @Override
    public List<EnterpriseCertificationAllInfo> selectEsAndCustomerAllInfosByStatus(Map<String,Object> param) {
        return enterpriseCertificationInfoMapper.selectEsAndCustomerAllInfosByStatus(param);
    }

    @Override
    public int selectEsAndCustomerAllInfosCount(String checkStatus) {
        return enterpriseCertificationInfoMapper.selectEsAndCustomerAllInfosCount(checkStatus);
    }

    @Override
    public EnterpriseCertificationInfo selectuserEnperseByCustomerId(Long customerId) {
        return enterpriseCertificationInfoMapper.selectuserEnperseByCustomerId(customerId);
    }

    @Override
    public int saveEnterpriseCertificationInfo(EnterpriseCertificationInfo enterpriseCertificationInfo) {
        return enterpriseCertificationInfoMapper.saveEnterpriseCertificationInfo(enterpriseCertificationInfo);
    }

    @Override
    public List<EnterpriseCertificationInfo> selectEnterpriseCertificationByCustomerId(Long customerId) {
        return null;
    }
}
