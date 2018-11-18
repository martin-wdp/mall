package com.qpmall.m.customer.dao.impl;

import com.ningpai.manager.base.BasicSqlSupport;
import com.qpmall.m.customer.bean.EnterpriseCertificationAllInfo;
import com.qpmall.m.customer.bean.EnterpriseCertificationInfo;
import com.qpmall.m.customer.dao.EnterpriseCertificationInfoMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by ly-qpmall on 2015/10/14.
 */
@Repository("enterpriseCertificationInfoMapper")
public class EnterpriseCertificationInfoMapperImpl extends BasicSqlSupport implements EnterpriseCertificationInfoMapper {

    /**
     * 查询所有认证信息
     * @return
     */
    @Override
    public List<EnterpriseCertificationInfo> selectAllInfos() {
        return this.selectList("com.qpmall.m.customer.dao.EnterpriseCertificationInfoMapper.selectAllInfos");
    }

    /**
     * 根据ID查询认证信息
     * @param enterpriseId
     * @return
     */
    @Override
    public EnterpriseCertificationInfo selectInfoById(Long enterpriseId) {
        return  this.selectOne("com.qpmall.m.customer.dao.EnterpriseCertificationInfoMapper.selectInfoById", enterpriseId);
    }
    @Override
    public List<EnterpriseCertificationInfo> selectInfosByQpEn(EnterpriseCertificationInfo enterpriseCertificationInfo){
        return this.selectList("com.qpmall.m.customer.dao.EnterpriseCertificationInfoMapper.selectInfosByQpEn", enterpriseCertificationInfo);
    }

    /**
     * 审核
     * @return
     */
    @Override
    public int updateCheckStatus(Map<String,Object> param) {
        return this.update("com.qpmall.m.customer.dao.EnterpriseCertificationInfoMapper.updateCheckStatus",param);
    }

    /**
     * 删除
     * @param enterpriseId
     * @return
     */
    @Override
    public int deleteEnterpriseAllInfoById(Long enterpriseId) {
        return this.delete("com.qpmall.m.customer.dao.EnterpriseCertificationInfoMapper.deleteEnterpriseAllInfoById",enterpriseId);
    }

    @Override
    public List<EnterpriseCertificationAllInfo> selectEsAndCustomerAllInfos(Map<String,Integer> param) {
        return this.selectList("com.qpmall.m.customer.dao.EnterpriseCertificationInfoMapper.selectEsAndCustomerAllInfos",param);
    }

    @Override
    public EnterpriseCertificationAllInfo selectEsAndCustomerAllInfo(Long enterpriseId) {
        return this.selectOne("com.qpmall.m.customer.dao.EnterpriseCertificationInfoMapper.selectEsAndCustomerAllInfo",enterpriseId);
    }

    @Override
    public List<EnterpriseCertificationAllInfo> selectEsAndCustomerAllInfosByStatus(Map<String,Object> param) {
        return this.selectList("com.qpmall.m.customer.dao.EnterpriseCertificationInfoMapper.selectEsAndCustomerAllInfosByStatus",param);
    }

    @Override
    public int selectEsAndCustomerAllInfosCount(String checkStatus) {
        return this.selectOne("com.qpmall.m.customer.dao.EnterpriseCertificationInfoMapper.selectEsAndCustomerAllInfosCount",checkStatus);
    }

    @Override
    public EnterpriseCertificationInfo selectuserEnperseByCustomerId(Long customerId) {
        return this.selectOne("com.qpmall.m.customer.dao.EnterpriseCertificationInfoMapper.selectuserEnperseByCustomerId",customerId);
    }

    @Override
    public int saveEnterpriseCertificationInfo(EnterpriseCertificationInfo enterpriseCertificationInfo) {
        return this.insert("com.qpmall.m.customer.dao.EnterpriseCertificationInfoMapper.saveEnterpriseCertificationInfo", enterpriseCertificationInfo);
    }

    @Override
    public List<EnterpriseCertificationInfo> selectEnterpriseCertificationByCustomerId(Long customerId) {
        return this.selectList("com.qpmall.m.customer.dao.EnterpriseCertificationInfoMapper.selectEnterpriseCertificationByCustomerId",customerId);
    }

}
