package com.qpmall.qpmemberaudit.dao;

import com.qpmall.qpmemberaudit.bean.QpEnterpriseCertificationAllInfo;
import com.qpmall.qpmemberaudit.bean.QpEnterpriseCertificationInfo;

import java.util.List;
import java.util.Map;

public interface QpEnterpriseCertificationInfoMapper {
    /**
     * 查询所有的认证信息
     * @return
     */
    List<QpEnterpriseCertificationInfo> selectAllInfos();

    /**
     * 根据条件查询认证信息
     * @param enterpriseId
     * @return
     */
    QpEnterpriseCertificationInfo selectInfoById(Long enterpriseId);

    /**
     * 根据qpEnterpriseCertificationInfo条件查询
     * @param qpEnterpriseCertificationInfo
     * @return
     */
    public List<QpEnterpriseCertificationInfo> selectInfosByQpEn(QpEnterpriseCertificationInfo qpEnterpriseCertificationInfo);

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
    public List<QpEnterpriseCertificationAllInfo> selectEsAndCustomerAllInfos(Map<String,Integer> param);

    /**
     *
     * @param enterpriseId
     * @return
     */
    public QpEnterpriseCertificationAllInfo selectEsAndCustomerAllInfo(Long enterpriseId);

    /**
     *
     * @param
     * @return
     */
    public List<QpEnterpriseCertificationAllInfo> selectEsAndCustomerAllInfosByStatus(Map<String,Object> param);

    /**
     * 查询认证信息列表信息条数
     * @param
     * @return
     */
    public int selectEsAndCustomerAllInfosCount(String checkStatus);

}