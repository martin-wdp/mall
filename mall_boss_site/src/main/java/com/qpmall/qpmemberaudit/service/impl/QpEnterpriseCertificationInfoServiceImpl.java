package com.qpmall.qpmemberaudit.service.impl;

import com.qpmall.qpmemberaudit.bean.QpEnterpriseCertificationAllInfo;
import com.qpmall.qpmemberaudit.bean.QpEnterpriseCertificationInfo;
import com.qpmall.qpmemberaudit.dao.QpEnterpriseCertificationInfoMapper;
import com.qpmall.qpmemberaudit.service.QpEnterpriseCertificationInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by ly-qpmall on 2015/10/14.
 */
@Service("qpEnterpriseCertificationInfoService")
public class QpEnterpriseCertificationInfoServiceImpl implements QpEnterpriseCertificationInfoService {
    /**
     * 保存企业验证信息
     *
     * @param qpEnterpriseCertificationInfo
     * @return
     */
    @Resource(name = "qpEnterpriseCertificationInfoMapper")
    private QpEnterpriseCertificationInfoMapper qpEnterpriseCertificationInfoMapper;

    /**
     * 查询所有
     * @return
     */
    @Override
    public List<QpEnterpriseCertificationInfo> selectAllInfos() {
        return qpEnterpriseCertificationInfoMapper.selectAllInfos();
    }

    /**
     * 根据ID查询认证信息
     * @param enterpriseId
     * @return
     */
    @Override
    public QpEnterpriseCertificationInfo selectInfoById(Long enterpriseId) {
        return qpEnterpriseCertificationInfoMapper.selectInfoById(enterpriseId);
    }

    /**
     * 根据条件查询
     * @param qpEnterpriseCertificationInfo
     * @return
     */
    @Override
    public List<QpEnterpriseCertificationInfo> selectInfosByQpEn(QpEnterpriseCertificationInfo qpEnterpriseCertificationInfo) {
        return qpEnterpriseCertificationInfoMapper.selectInfosByQpEn(qpEnterpriseCertificationInfo);
    }

    /**
     * 审核
     * @param param
     * @return
     */
    @Override
    public int updateCheckStatus(Map<String, Object> param) {
        return qpEnterpriseCertificationInfoMapper.updateCheckStatus(param);
    }

    /**
     * 删除
     * @param enterpriseId
     * @return
     */
    @Override
    public int deleteEnterpriseAllInfoById(Long enterpriseId) {
        return qpEnterpriseCertificationInfoMapper.deleteEnterpriseAllInfoById(enterpriseId);
    }

    @Override
    public List<QpEnterpriseCertificationAllInfo> selectEsAndCustomerAllInfos(Map<String,Integer> param) {
        return qpEnterpriseCertificationInfoMapper.selectEsAndCustomerAllInfos(param);
    }

    @Override
    public QpEnterpriseCertificationAllInfo selectEsAndCustomerAllInfo(Long enterpriseId) {
        return qpEnterpriseCertificationInfoMapper.selectEsAndCustomerAllInfo(enterpriseId);
    }

    @Override
    public List<QpEnterpriseCertificationAllInfo> selectEsAndCustomerAllInfosByStatus(Map<String,Object> param) {
        return qpEnterpriseCertificationInfoMapper.selectEsAndCustomerAllInfosByStatus(param);
    }

    @Override
    public int selectEsAndCustomerAllInfosCount(String checkStatus) {
        return qpEnterpriseCertificationInfoMapper.selectEsAndCustomerAllInfosCount(checkStatus);
    }
}
