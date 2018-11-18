package com.qpmall.m.customer.service;


import com.qpmall.m.customer.bean.EnterpriseCertificationAllInfo;
import com.qpmall.m.customer.bean.EnterpriseCertificationInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by ly-qpmall on 2015/10/14.
 */
public interface EnterpriseCertificationInfoService {
    /**
     * 查询所有的认证信息
     *
     * @return
     */
    public List<EnterpriseCertificationInfo> selectAllInfos();

    /**
     * 根据ID查询认证信息
     *
     * @param enterpriseId
     * @return
     */
    public EnterpriseCertificationInfo selectInfoById(Long enterpriseId);

    /**
     * 根据qpEnterpriseCertificationInfo条件查询
     *
     * @param enterpriseCertificationInfo
     * @return
     */
    public List<EnterpriseCertificationInfo> selectInfosByQpEn(EnterpriseCertificationInfo enterpriseCertificationInfo);

    /**
     * 审核
     *
     * @return
     */
    public int updateCheckStatus(Map<String, Object> param);

    /**
     * 删除信息
     *
     * @param enterpriseId
     * @return
     */
    public int deleteEnterpriseAllInfoById(Long enterpriseId);

    public List<EnterpriseCertificationAllInfo> selectEsAndCustomerAllInfos(Map<String, Integer> param);

    public EnterpriseCertificationAllInfo selectEsAndCustomerAllInfo(Long enterpriseId);

    public List<EnterpriseCertificationAllInfo> selectEsAndCustomerAllInfosByStatus(Map<String, Object> param);

    /**
     * 查询认证信息列表信息条数
     *
     * @param
     * @return
     */
    public int selectEsAndCustomerAllInfosCount(String checkStatus);

    public EnterpriseCertificationInfo selectuserEnperseByCustomerId(Long customerId);

    /**
     * 保存企业验证信息
     * @param enterpriseCertificationInfo
     * @return
     */
    public int saveEnterpriseCertificationInfo(EnterpriseCertificationInfo enterpriseCertificationInfo);

    /**
     * 根据用户ID查询用户的认证信息最近的
     * @param customerId
     * @return
     */
    List<EnterpriseCertificationInfo> selectEnterpriseCertificationByCustomerId(Long customerId);
}
