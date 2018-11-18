package com.qpmall.site.register.service;

import com.ningpai.freighttemplate.bean.SysProvince;
import com.ningpai.other.bean.CityBean;
import com.ningpai.other.bean.DistrictBean;
import com.qpmall.site.register.bean.QpEnterpriseCertificationInfo;

import java.util.List;

/**
 * Created by ly-qpmall on 2015/10/14.
 */
public interface QpEnterpriseCertificationInfoService {
    /**
     * 保存企业验证信息
     * @param qpEnterpriseCertificationInfo
     * @return
     */
    public int saveQpEnterpriseCertificationInfo(QpEnterpriseCertificationInfo qpEnterpriseCertificationInfo);
    /**
     * 根据主键查询城市信息
     * @param cityId
     * @return
     */
    public CityBean selectCityByPid(Long cityId);

    /**
     * 根据主键查询区信息
     * @param districtId
     * @return
     */
    public DistrictBean selectDistrictByCid(Long districtId);

    /**
     * 根据用户ID查询用户的认证信息最近的
     * @param customerId
     * @return
     */
     List<QpEnterpriseCertificationInfo> selectEnterpriseCertificationByCustomerId(Long customerId);
    /**
     * 修改个人认证信息
     * @param qpEnterpriseCertificationInfo
     * @return
     */
    public int updateQpEnterpriseCertificationInfo(QpEnterpriseCertificationInfo qpEnterpriseCertificationInfo);

    public QpEnterpriseCertificationInfo selectuserEnperseByCustomerId(Long customerId);


}
