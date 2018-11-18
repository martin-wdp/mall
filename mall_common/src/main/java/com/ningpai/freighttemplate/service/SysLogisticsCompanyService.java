package com.ningpai.freighttemplate.service;

import java.util.List;

import com.ningpai.freighttemplate.bean.SysLogisticsCompany;

/**
 * 快递公司接口 2014-12-17
 * @author ggn
 *
 */
public interface SysLogisticsCompanyService {

    /**
     * 查询快递公司列表
     * @return List
     */
    List<SysLogisticsCompany> selectAllCompnay();

    /**
     * 查询快递公司列表
     * @return List
     */
    SysLogisticsCompany selectCompanyById(Long logComId);

}
