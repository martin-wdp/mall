package com.ningpai.freighttemplate.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ningpai.freighttemplate.bean.SysLogisticsCompany;
import com.ningpai.freighttemplate.dao.SysLogisticsCompanyMapper;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * 物流公司实现类
 * 
 * @author ggn
 *
 */
@Repository("SysLogisticsCompanyMapper")
public class SysLogisticsCompanyMapperImpl extends BasicSqlSupport implements
        SysLogisticsCompanyMapper {

    /*
     * 查询物流公司信息根据Id
     * @see
     * com.ningpai.freighttemplate.dao.SysLogisticsCompanyMapper#selectCompanyById
     * (java.lang.Long)
     */
    @Override
    public SysLogisticsCompany selectCompanyById(Long logComId) {

        return this
                .selectOne(
                        "com.qpmall.web.dao.SysLogisticsCompanyMapper.selectCompanyById",
                        logComId);
    }

    /**
     * 查询所有快递公司
     * @return
     */
    @Override
    public List<SysLogisticsCompany> selectAllCompnay() {

        return this
                .selectList("com.qpmall.web.dao.SysLogisticsCompanyMapper.selectAllCompnay");
    }

}
