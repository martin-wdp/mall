package com.ningpai.freighttemplate.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ningpai.freighttemplate.bean.SysLogisticsCompany;
import com.ningpai.freighttemplate.dao.SysLogisticsCompanyMapper;
import com.ningpai.freighttemplate.service.SysLogisticsCompanyService;

/**
 * 快递公司service
 * 
 * @author ggn
 *
 */
@Service("SysLogisticsCompanyService")
public class SysLogisticsCompanyServiceImpl implements
        SysLogisticsCompanyService {

    // spring注入
    @Resource(name = "SysLogisticsCompanyMapper")
    private SysLogisticsCompanyMapper sysLogisticsCompanyMapper;

    /*
     * 查询快递公司列表
     * @see com.ningpai.freighttemplate.service.SysLogisticsCompanyService#
     * selectAllCompnay()
     */
    @Override
    public List<SysLogisticsCompany> selectAllCompnay() {
        // 查询所有物流公司
        return sysLogisticsCompanyMapper.selectAllCompnay();
    }

    @Override
    public SysLogisticsCompany selectCompanyById(Long logComId) {
        return sysLogisticsCompanyMapper.selectCompanyById(logComId);
    }

}
