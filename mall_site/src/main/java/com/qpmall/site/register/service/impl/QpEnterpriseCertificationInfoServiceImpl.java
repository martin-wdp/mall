package com.qpmall.site.register.service.impl;

import com.ningpai.other.bean.CityBean;
import com.ningpai.other.bean.CustomerAllInfo;
import com.ningpai.other.bean.DistrictBean;
import com.qpmall.site.register.bean.QpEnterpriseCertificationInfo;
import com.qpmall.site.register.dao.QpEnterpriseCertificationInfoMapper;
import com.qpmall.site.register.service.QpEnterpriseCertificationInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

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

    @Override
    public int saveQpEnterpriseCertificationInfo(QpEnterpriseCertificationInfo qpEnterpriseCertificationInfo) {
        return qpEnterpriseCertificationInfoMapper.saveQpEnterpriseCertificationInfo(qpEnterpriseCertificationInfo);
    }

    /**
     * 根据编号查询城市名字
     *
     * @param cityId
     * @return
     */
    @Override
    public CityBean selectCityByPid(Long cityId) {
        return qpEnterpriseCertificationInfoMapper.selectCityByPid(cityId);
    }

    /**
     * 查询区
     * @param districtId
     * @return
     */
    @Override
    public DistrictBean selectDistrictByCid(Long districtId) {
        return qpEnterpriseCertificationInfoMapper.selectDistrictByCid(districtId);
    }

    /**
     * 根据用户ID查询用户认证信息
     * @param customerId
     * @return
     */
    @Override
    public List<QpEnterpriseCertificationInfo> selectEnterpriseCertificationByCustomerId(Long customerId) {
        return qpEnterpriseCertificationInfoMapper.selectEnterpriseCertificationByCustomerId(customerId);
    }

    /**
     * 修改个人认证信息
     * @param qpEnterpriseCertificationInfo
     * @return
     */
    @Override
    public int updateQpEnterpriseCertificationInfo(QpEnterpriseCertificationInfo qpEnterpriseCertificationInfo) {
        return qpEnterpriseCertificationInfoMapper.updateQpEnterpriseCertificationInfo(qpEnterpriseCertificationInfo);
    }

    @Override
    public QpEnterpriseCertificationInfo selectuserEnperseByCustomerId(Long customerId) {
        return qpEnterpriseCertificationInfoMapper.selectuserEnperseByCustomerId(customerId);
    }


}
