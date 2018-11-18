package com.qpmall.qpmemberaudit.dao.impl;

import com.ningpai.manager.base.BasicSqlSupport;
import com.qpmall.qpmemberaudit.bean.QpEnterpriseCertificationAllInfo;
import com.qpmall.qpmemberaudit.bean.QpEnterpriseCertificationInfo;
import com.qpmall.qpmemberaudit.dao.QpEnterpriseCertificationInfoMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by ly-qpmall on 2015/10/14.
 */
@Repository("qpEnterpriseCertificationInfoMapper")
public class QpEnterpriseCertificationInfoMapperImpl extends BasicSqlSupport implements QpEnterpriseCertificationInfoMapper {

    /**
     * 查询所有认证信息
     * @return
     */
    @Override
    public List<QpEnterpriseCertificationInfo> selectAllInfos() {
        return this.selectList("com.qpmall.qpmemberaudit.dao.QpEnterpriseCertificationInfoMapper.selectAllInfos");
    }

    /**
     * 根据ID查询认证信息
     * @param enterpriseId
     * @return
     */
    @Override
    public QpEnterpriseCertificationInfo selectInfoById(Long enterpriseId) {
        return  this.selectOne("com.qpmall.qpmemberaudit.dao.QpEnterpriseCertificationInfoMapper.selectInfoById", enterpriseId);
    }
    @Override
    public List<QpEnterpriseCertificationInfo> selectInfosByQpEn(QpEnterpriseCertificationInfo qpEnterpriseCertificationInfo){
        return this.selectList("com.qpmall.qpmemberaudit.dao.QpEnterpriseCertificationInfoMapper.selectInfosByQpEn",qpEnterpriseCertificationInfo);
    }

    /**
     * 审核
     * @return
     */
    @Override
    public int updateCheckStatus(Map<String,Object> param) {
        return this.update("com.qpmall.qpmemberaudit.dao.QpEnterpriseCertificationInfoMapper.updateCheckStatus",param);
    }

    /**
     * 删除
     * @param enterpriseId
     * @return
     */
    @Override
    public int deleteEnterpriseAllInfoById(Long enterpriseId) {
        return this.delete("com.qpmall.qpmemberaudit.dao.QpEnterpriseCertificationInfoMapper.deleteEnterpriseAllInfoById",enterpriseId);
    }

    @Override
    public List<QpEnterpriseCertificationAllInfo> selectEsAndCustomerAllInfos(Map<String,Integer> param) {
        return this.selectList("com.qpmall.qpmemberaudit.dao.QpEnterpriseCertificationInfoMapper.selectEsAndCustomerAllInfos",param);
    }

    @Override
    public QpEnterpriseCertificationAllInfo selectEsAndCustomerAllInfo(Long enterpriseId) {
        return this.selectOne("com.qpmall.qpmemberaudit.dao.QpEnterpriseCertificationInfoMapper.selectEsAndCustomerAllInfo",enterpriseId);
    }

    @Override
    public List<QpEnterpriseCertificationAllInfo> selectEsAndCustomerAllInfosByStatus(Map<String,Object> param) {
        return this.selectList("com.qpmall.qpmemberaudit.dao.QpEnterpriseCertificationInfoMapper.selectEsAndCustomerAllInfosByStatus",param);
    }

    @Override
    public int selectEsAndCustomerAllInfosCount(String checkStatus) {
        return this.selectOne("com.qpmall.qpmemberaudit.dao.QpEnterpriseCertificationInfoMapper.selectEsAndCustomerAllInfosCount",checkStatus);
    }

}
