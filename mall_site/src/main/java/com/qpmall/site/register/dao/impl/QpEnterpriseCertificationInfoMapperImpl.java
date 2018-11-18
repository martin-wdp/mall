package com.qpmall.site.register.dao.impl;

import com.ningpai.freighttemplate.bean.SysProvince;
import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.other.bean.CityBean;
import com.ningpai.other.bean.DistrictBean;
import com.qpmall.site.register.bean.QpEnterpriseCertificationInfo;
import com.qpmall.site.register.dao.QpEnterpriseCertificationInfoMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ly-qpmall on 2015/10/14.
 */
@Repository("qpEnterpriseCertificationInfoMapper")
public class QpEnterpriseCertificationInfoMapperImpl extends BasicSqlSupport implements QpEnterpriseCertificationInfoMapper {
    /**
     * 保存企业认证信息
     *
     * @param qpEnterpriseCertificationInfo
     * @return
     */
    @Override
    public int saveQpEnterpriseCertificationInfo(QpEnterpriseCertificationInfo qpEnterpriseCertificationInfo) {
        return this.insert("com.qpmall.site.register.dao.QpEnterpriseCertificationInfoMapper.saveQpEnterpriseCertificationInfo", qpEnterpriseCertificationInfo);
    }

    @Override
    public List<QpEnterpriseCertificationInfo> selectEnterpriseCertificationByCustomerId(Long customerId) {
        return this.selectList("com.qpmall.site.register.dao.QpEnterpriseCertificationInfoMapper.selectEnterpriseCertificationByCustomerId",customerId);
    }

    /**
     * 根据主键查询城市信息
     * @param cityId
     * @return
     */
    @Override
    public CityBean selectCityByPid(Long cityId) {
        return this.selectOne(
                "com.qpmall.site.register.dao.QpEnterpriseCertificationInfoMapper.selectCityByPid",cityId
                );
    }

    @Override
    public DistrictBean selectDistrictByCid(Long districtId) {
        return this.selectOne(
                "com.qpmall.site.register.dao.QpEnterpriseCertificationInfoMapper.selectDistrictByCid",districtId
        );
    }

    /**
     * 修改个人信息
     * @param qpEnterpriseCertificationInfo
     * @return
     */
    @Override
    public int updateQpEnterpriseCertificationInfo(QpEnterpriseCertificationInfo qpEnterpriseCertificationInfo) {
        return this.update("com.qpmall.site.register.dao.QpEnterpriseCertificationInfoMapper.updateQpEnterpriseCertificationInfo",qpEnterpriseCertificationInfo);
    }

    @Override
    public QpEnterpriseCertificationInfo selectuserEnperseByCustomerId(Long customerId) {
        return this.selectOne("com.qpmall.site.register.dao.QpEnterpriseCertificationInfoMapper.selectuserEnperseByCustomerId",customerId);
    }


}
