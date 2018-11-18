package com.qpmall.m.customer.dao;


import com.qpmall.m.customer.bean.EnterpriseCertificationAllInfo;
import com.qpmall.m.customer.bean.EnterpriseCertificationInfo;

import java.util.List;
import java.util.Map;

public interface EnterpriseCertificationInfoMapper {
    /**
     * 查询所有的认证信息
     * @return
     */
    List<EnterpriseCertificationInfo> selectAllInfos();

    /**
     * 根据条件查询认证信息
     * @param enterpriseId
     * @return
     */
    EnterpriseCertificationInfo selectInfoById(Long enterpriseId);

    /**
     * 根据qpEnterpriseCertificationInfo条件查询
     * @param enterpriseCertificationInfo
     * @return
     */
    public List<EnterpriseCertificationInfo> selectInfosByQpEn(EnterpriseCertificationInfo enterpriseCertificationInfo);

    /**
     * 审核
     * @return
     */
    public int updateCheckStatus(Map<String, Object> param);

    /**
     * 删除信息
     * @param enterpriseId
     * @return
     */
    public int deleteEnterpriseAllInfoById(Long enterpriseId);

    /**
     *
     * @return
     */
    public List<EnterpriseCertificationAllInfo> selectEsAndCustomerAllInfos(Map<String, Integer> param);

    /**
     *
     * @param enterpriseId
     * @return
     */
    public EnterpriseCertificationAllInfo selectEsAndCustomerAllInfo(Long enterpriseId);

    /**
     *
     * @param
     * @return
     */
    public List<EnterpriseCertificationAllInfo> selectEsAndCustomerAllInfosByStatus(Map<String, Object> param);

    /**
     * 查询认证信息列表信息条数
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