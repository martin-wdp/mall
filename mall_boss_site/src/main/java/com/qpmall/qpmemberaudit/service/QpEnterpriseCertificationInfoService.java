package com.qpmall.qpmemberaudit.service;


import com.qpmall.qpmemberaudit.bean.QpEnterpriseCertificationAllInfo;
import com.qpmall.qpmemberaudit.bean.QpEnterpriseCertificationInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by ly-qpmall on 2015/10/14.
 */
public interface QpEnterpriseCertificationInfoService {
    /**
     * 查询所有的认证信息
     * @return
     */
    public List<QpEnterpriseCertificationInfo> selectAllInfos();
    /**
     * 根据ID查询认证信息
     * @param enterpriseId
     * @return
     */
    public QpEnterpriseCertificationInfo selectInfoById(Long enterpriseId);

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
    public List<QpEnterpriseCertificationAllInfo> selectEsAndCustomerAllInfos(Map<String,Integer> param);
    public QpEnterpriseCertificationAllInfo selectEsAndCustomerAllInfo(Long enterpriseId);
    public List<QpEnterpriseCertificationAllInfo> selectEsAndCustomerAllInfosByStatus(Map<String,Object> param);
    /**
     * 查询认证信息列表信息条数
     * @param
     * @return
     */
    public int selectEsAndCustomerAllInfosCount(String checkStatus);
}
