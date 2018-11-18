/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.customer.dao.impl;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.ningpai.customer.bean.CustomerPoint;
import com.ningpai.customer.dao.CustomerPointMapper;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * @see com.ningpai.customer.dao.CustomerPointMapper
 * @author NINGPAI-zhangqiang
 * @since 2014年3月20日 上午11:37:01
 * @version 0.0.1
 */
@Repository("customerPointMapper")
public class CustomerPointMapperIml extends BasicSqlSupport implements
        CustomerPointMapper {

    @Override
    public List<Object> selectRegisterPont(Map<String, Object> map) {
        return this
                .selectList(
                        "com.ningpai.customer.dao.CustomerPointMapper.selectRegisterPont",
                        map);
    }

    @Override
    public int selectRegisterPointSize(Map<String, Object> map) {
        return this
                .selectOne(
                        "com.ningpai.customer.dao.CustomerPointMapper.selectRegisterPointSize",
                        map);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.customer.dao.CustomerPointMapper#deleteByPrimaryKey(java.
     * lang.Long)
     */
    @Override
    public int deleteByPrimaryKey(Long pointId) {
        return 0;
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.customer.dao.CustomerPointMapper#insert(com.ningpai.customer
     * .bean.CustomerPoint)
     */
    @Override
    public int insert(CustomerPoint record) {
        return 0;
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.customer.dao.CustomerPointMapper#insertSelective(com.ningpai
     * .customer.bean.CustomerPoint)
     */
    @Override
    public int insertSelective(CustomerPoint record) {
        return this.insert(
                "com.ningpai.customer.dao.CustomerPointMapper.insertSelective",
                record);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.customer.dao.CustomerPointMapper#selectByPrimaryKey(java.
     * lang.Long)
     */
    @Override
    public CustomerPoint selectByPrimaryKey(Long pointId) {
        return null;
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.customer.dao.CustomerPointMapper#updateByPrimaryKeySelective
     * (com.ningpai.customer.bean.CustomerPoint)
     */
    @Override
    public int updateByPrimaryKeySelective(CustomerPoint record) {
        return 0;
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.customer.dao.CustomerPointMapper#updateByPrimaryKey(com.ningpai
     * .customer.bean.CustomerPoint)
     */
    @Override
    public int updateByPrimaryKey(CustomerPoint record) {
        return 0;
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.customer.dao.CustomerPointMapper#selectAllCustomerPoint()
     */
    @Override
    public List<Object> selectAllCustomerPoint(Map<String, Integer> paramMap) {

        return this
                .selectList(
                        "com.ningpai.customer.dao.CustomerPointMapper.selectAllCustomerPoint",
                        paramMap);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.customer.dao.CustomerPointMapper#selectAllCustomerCount()
     */
    @Override
    public int selectAllCustomerCount() {
        return this
                .selectOne("com.ningpai.customer.dao.CustomerPointMapper.selectAllCustomerCount");
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.customer.dao.CustomerPointMapper#deleteCustomerPointByPids
     * (java.util.Map)
     */
    @Override
    public int deleteCustomerPointByPids(Map<String, Object> paramMap) {
        return this
                .delete("com.ningpai.customer.dao.CustomerPointMapper.deleteCustomerPointByPids",
                        paramMap);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.customer.dao.CustomerPointMapper#selectCustmerPointSize(com
     * .ningpai.customer.bean.CustomerPoint)
     */
    @Override
    public int selectCustmerPointSize(CustomerPoint point) {
        return this
                .selectOne(
                        "com.ningpai.customer.dao.CustomerPointMapper.selectCustmerPointSize",
                        point);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.customer.dao.CustomerPointMapper#selectCustPointByCustPoint
     * (com.ningpai.customer.bean.CustomerPoint)
     */
    @Override
    public List<Object> selectCustPointByCustPoint(CustomerPoint point) {
        return this
                .selectList(
                        "com.ningpai.customer.dao.CustomerPointMapper.selectCustPointByCustPoint",
                        point);
    }

}
