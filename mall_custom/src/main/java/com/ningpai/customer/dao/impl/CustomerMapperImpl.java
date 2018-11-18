/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.customer.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.customer.bean.Customer;
import com.ningpai.customer.dao.CustomerMapper;
import com.ningpai.customer.vo.CustomerStatisticVo;
import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.other.bean.CityBean;
import com.ningpai.other.bean.CustomerAllInfo;
import com.ningpai.other.bean.DistrictBean;
import com.ningpai.other.bean.OrderInfoBean;
import com.ningpai.other.bean.ProvinceBean;
import com.ningpai.other.bean.StreetBean;

/**
 * 会员功能接口实现类
 * 
 * @author NINGPAI-zhangqiang
 * @since 2013年12月16日 下午5:19:42
 * @version
 */
@Repository("customerMapper")
public class CustomerMapperImpl extends BasicSqlSupport implements
        CustomerMapper {
    /***
     * 根据会员登录名获取单个的会员信息（用户 会员登陆次数限制）
     *
     * @param customerUsername
     *            会员登陆名称
     * @return
     */
    @Override
    public Customer getCustomerByUsername(String customerUsername) {
        return this
                .selectOne(
                        "com.ningpai.customer.dao.CustomerMapper.getCustomerByUsernamestie",
                        customerUsername);
    }
    /**
     * 删除商家所有商品
     *
     * @param customerId
     * @return
     */
    @Override
    public int deleteGoods(String customerId) {
        return 0;
    }
    /**
     * 删除商家为普通会员
     *
     * @param map
     * @return
     */
    @Override
    public int deleteStore(Map<String, Object> map) {
        return this.update(
                "com.ningpai.customer.dao.CustomerMapper.deleteStore", map);
    }
    /**
     * 修改会员状态为商家
     *
     * @param cId
     * @return
     */
    @Override
    public int updateStatus(int customerId) {
        return this.update(
                "com.ningpai.customer.dao.CustomerMapper.updateStatus",
                customerId);
    }
    /**
     * 删除商家员工
     *
     * @param thirdId
     * @return
     */
    @Override
    public int deleteEmp(Long thirdId) {
        return this.update("com.ningpai.customer.dao.CustomerMapper.deleteEmp",
                thirdId);
    }
    /**
     * 更新单个会员信息
     *
     * @param customer
     * @return
     */
    @Override
    public int updateCustomer(Customer customer) {
        return this.update(
                "com.ningpai.customer.dao.CustomerMapper.updateCustomer",
                customer);
    }

    /***
     * 根据主键获取单个的会员信息
     *
     * @param customerId
     *            会员ID
     * @return
     */
    @Override
    public Customer getCustomerByCusId(Long customerId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("customerId", customerId);
        return this.selectOne(
                "com.ningpai.customer.dao.CustomerMapper.getCustomerByCusId",
                map);
    }

    /**
     * 根据主键获取会员
     *
     * @param customerId
     *            会员编号
     * @return 会员 com.ningpai.customer.bean.Customer
     *         {@link com.ningpai.customer.bean.Customer}
     */
    @Override
    public CustomerAllInfo selectByPrimaryKey(Long customerId) {
        return this.selectOne(
                "com.ningpai.customer.dao.CustomerMapper.selectByPrimaryKey",
                customerId);
    }

    /**
     *
     * 根据会员id查询出所有订单信息
     * 
     * @param customerIds
     * @return
     */
    @Override
    public List<CustomerAllInfo> selectBycustomerIds(
            Map<String, Object> customerIds) {
        return this.selectList(
                "com.ningpai.customer.dao.CustomerMapper.selectBycustomerIds",
                customerIds);
    }

    /**
     * 查询所有会员
     *
     * @return 会员列表集合
     */
    @Override
    public List<Object> selectAllCustomer() {
        return this
                .selectList("com.ningpai.customer.dao.CustomerMapper.selectAllCustmerInfo");
    }

    /**
     * 分页查询
     *
     * @return 会员列表集合
     */
    @Override
    public List<Object> selectCustomerByLimit(Map<String, Integer> paramMap) {
        return this
                .selectList(
                        "com.ningpai.customer.dao.CustomerMapper.selectCustomerByLimit",
                        paramMap);
    }

    @Override
    public List<Object> selectCustomerByLimitFilterThird(Map<String, Integer> paramMap) {
        return this
                .selectList(
                        "com.ningpai.customer.dao.CustomerMapper.selectCustomerByLimitFilterThird",
                        paramMap);
    }

    /**
     * 插入会员信息
     *
     */
    @Override
    public int addCustomer(CustomerAllInfo customerinfo) {
        int i = this.insert(
                "com.ningpai.customer.dao.CustomerMapper.insertSelective",
                customerinfo);
        if (i == 1) {
            Long customerId = this
                    .selectOne("com.ningpai.customer.dao.CustomerMapper.selectLastId");
            customerinfo.setCustomerId(customerId);
            return this
                    .insert("com.ningpai.customer.dao.CustomerInfoMapper.insertSelective",
                            customerinfo);
        } else {
            return 0;
        }
    }
    /**
     * 查询商家id
     *
     * @param customerId
     * @return
     */
    @Override
    public String findStoreId(String customerId) {
        return selectOne("com.ningpai.customer.dao.CustomerMapper.findStoreId",
                customerId);
    }

    /**
     * 根据会员编号删除会员
     *
     * @param customerId
     *            会员编号
     * @return java.lang.Integer {@link java.lang.Integer}
     */
    @Override
    public int deleteCustomerById(Long customerId) {
        return this.delete(
                "com.ningpai.customer.dao.CustomerMapper.deleteCustomerById",
                customerId);
    }

    /**
     * 根据会员编号修改会员
     *
     * @param customer
     *            会员编号
     * @return java.lang.Integer {@link java.lang.Integer}
     */
    @Override
    public int updateByPrimaryKeySelective(CustomerAllInfo customer) {
        return this
                .update("com.ningpai.customer.dao.CustomerMapper.updateByPrimaryKeySelective",
                        customer);
    }

    @Override
    public int updateThirdPassword(Map<String, Object> map) {
        return this
                .update("com.ningpai.customer.dao.CustomerMapper.updateThirdPassword",
                        map);
    }

    /**
     * 检查会员用户名是否存在
     *
     * @param customerName
     *            等级名称
     * @return 1 存在 0 不存在
     * @see java.lang.Long {@link java.lang.Long}
     */
    @Override
    public Long selectCustomerByName(String customerName) {
        return this.selectOne(
                "com.ningpai.customer.dao.CustomerMapper.selectByUserName",
                customerName);
    }

    @Override
      public Long selectCustomerByNameForThird(String customerName) {
        return this.selectOne(
                "com.ningpai.customer.dao.CustomerMapper.selectCustomerByNameForThird",
                customerName);
    }

    /**
     * 根据订单编号查找订单信息
     *
     * @param orderId
     *            订单编号 java.lang.Long {@link java.lang.Long}
     * @return
     */
    @Override
    public CustomerAllInfo selectCustomerOrder(Long orderId) {
        return this.selectOne(
                "com.ningpai.customer.dao.CustomerMapper.selectCustomerOrder",
                orderId);
    }

    /**
     * 查询所有省份
     *
     * @return
     */
    @Override
    public List<ProvinceBean> selectAllProvince() {
        return this
                .selectList("com.ningpai.customer.dao.CustomerMapper.selectAllProvince");
    }

    /**
     * 根据省份编号 查询所有城市
     *
     * @param provinceId
     *            省份编号 java.lang.Long {@link java.lang.Long}
     * @return
     */
    @Override
    public List<CityBean> selectAllCityByPid(Long provinceId) {
        return this.selectList(
                "com.ningpai.customer.dao.CustomerMapper.selectAllCityByPid",
                provinceId);
    }

    /**
     * 根据城市编号 查询所有区县
     *
     * @param cityId
     *            城市编号 java.lang.Long {@link java.lang.Long}
     * @return
     */
    @Override
    public List<DistrictBean> selectAllDistrictByCid(Long cityId) {
        return this
                .selectList(
                        "com.ningpai.customer.dao.CustomerMapper.selectAllDistrictByCid",
                        cityId);
    }

    /**
     * 按条件查询会员
     *
     * @param customerAllInfo
     *            {@link com.ningpai.customer.bean.CustomerAllInfo}
     * @return
     */
    @Override
    public List<Object> selectCustmerByAllInfo(CustomerAllInfo customerAllInfo) {
        return this
                .selectList(
                        "com.ningpai.customer.dao.CustomerMapper.selectCustmerByAllInfo",
                        customerAllInfo);
    }


   @Override
    public List<Object> selectCustmerByAllInfoFilterThird(CustomerAllInfo customerAllInfo) {
        return this
                .selectList(
                        "com.ningpai.customer.dao.CustomerMapper.selectCustmerByAllInfoFilterThird",
                        customerAllInfo);
    }

    /**
     * 按条件查询会员 返回数量
     *
     * @param customerAllInfo
     * @return
     */
    @Override
    public Long selectCustmerSize(CustomerAllInfo customerAllInfo) {
        return this.selectOne(
                "com.ningpai.customer.dao.CustomerMapper.selectCustmerSize",
                customerAllInfo);
    }

    @Override
    public Long selectCustmerSizeFilterThird(CustomerAllInfo customerAllInfo) {
        return this.selectOne(
                "com.ningpai.customer.dao.CustomerMapper.selectCustmerSizeFilterThird",
                customerAllInfo);
    }

    /**
     * 按区县编号获取对应街道集合
     *
     * @param dId
     *            区县编号 java.lang.Long {@link java.lang.Long}
     * @return
     */
    @Override
    public List<StreetBean> getAllStreetByDid(Long dId) {
        return this.selectList(
                "com.ningpai.customer.dao.CustomerMapper.getAllStreetByDid",
                dId);
    }

    /**
     * 查询订单信息
     *
     * @param orderId
     *            java.lang.Long {@link java.lang.Long}
     * @return OrderInfoBean {@link com.ningpai.other.bean.OrderInfoBean}
     */
    @Override
    public OrderInfoBean queryByDetail(Long orderId) {
        return this.selectOne(
                "com.ningpai.customer.dao.CustomerMapper.queryByDetail",
                orderId);
    }

    /**
     * 查询会员信息
     *
     * @param customerId
     *            用户编号
     * @see java.lang.Long {@link java.lang.Long}
     * @see com.ningpai.customer.bean.Customer
     *      {@link com.ningpai.customer.bean.Customer}
     * @return @see com.ningpai.customer.bean.Customer
     *         {@link com.ningpai.customer.bean.Customer}
     */
    @Override
    public CustomerAllInfo queryCustomerInfo(Long customerId) {
        return this.selectOne(
                "com.ningpai.customer.dao.CustomerMapper.queryCustomerInfo",
                customerId);
    }

    /**
     * 删除会员 批量
     *
     * @param paramMap
     *            需要删除的会员ID集合
     * @return int {@link java.util.Integer}
     */
    @Override
    public int deleteCustomerByIds(Map<String, Object> paramMap) {
        return this.delete(
                "com.ningpai.customer.dao.CustomerMapper.deleteCustomerByIds",
                paramMap);
    }
    /**
     * 查询会员类型
     *
     * @param customerId
     * @return
     */
    @Override
    public String selectStatus(String customerId) {
        return this.selectOne(
                "com.ningpai.customer.dao.CustomerMapper.selectStatus",
                customerId);
    }

    /**
     * 查询所有会员的数量
     *
     * @return
     */
    @Override
    public Integer selectAllCustomerCount() {
        return this
                .selectOne("com.ningpai.customer.dao.CustomerMapper.selectAllCustomerCount");
    }

    /**
     * 查询所有城市 by yuankk
     *
     * @return
     */
    public List<CityBean> selectAllCity() {
        return this
                .selectList("com.ningpai.customer.dao.CustomerMapper.selectAllCity");
    }

    /**
     * 查询所有区县
     *
     * @param
     *            城市编号 java.lang.Long {@link java.lang.Long}
     * @return
     */
    public List<DistrictBean> selectAllDistrict() {
        return this
                .selectList("com.ningpai.customer.dao.CustomerMapper.selectAllDistrict");
    }

    /**
     * 按照会员名查找会员
     *
     * @param username
     *            会员名
     * @return 0 不存在 1 存在
     */
    @Override
    public Long checkexistsByCustName(String username) {
        return this
                .selectOne(
                        "com.ningpai.customer.dao.CustomerMapper.checkexistsByCustName",
                        username);
    }

    /**
     * 根据会员名和密码验证用户
     *
     * @param paramMap
     *            验证条件
     * @return null 密码错误 Customer 验证成功
     */
    @Override
    public Customer selectCustomerByNamePwd(Map<String, Object> paramMap) {
        return this
                .selectOne(
                        "com.ningpai.customer.dao.CustomerMapper.selectCustomerByNamePwd",
                        paramMap);
    }

    /**
     * 修改登录时间 IP
     *
     * @param
     *            {@link com.ningpai.customer.bean.Customer}
     * @return 0失败 1成功
     */
    @Override
    public int updateCustomerLoginTime(Customer customer) {
        return this
                .update("com.ningpai.customer.dao.CustomerMapper.updateCustomerLoginTime",
                        customer);
    }

    /**
     * 禁用用户
     *
     * @param
     *            custId 用户编号 flag :disable禁用 否则启用
     * @return 0 失败 1成功
     */
    @Override
    public int modifyEmpToDisable(Map<String, Object> paramMap) {
        return this.update(
                "com.ningpai.customer.dao.CustomerMapper.modifyEmpToDisable",
                paramMap);
    }

    /**
     * 根据订单编号查找订单信息
     *
     * @param orderId
     *            订单编号
     * @return OrderInfoBean
     */
    @Override
    public Object queryOrderByOrderId(Long orderId) {
        return this.selectOne(
                "com.ningpai.customer.dao.CustomerMapper.queryOrderByOrderId",
                orderId);
    }

    /**
     * 检查用户是否存在
     *
     * @param
     *            用户名 {@link String}
     * @return 0 不存在 1 存在 {@link Long}
     */
    @Override
    public Long checkExistsByCustNameAndType(Map<String, Object> paramMap) {
        return this
                .selectOne(
                        "com.ningpai.customer.dao.CustomerMapper.checkExistsByCustNameAndType",
                        paramMap);
    }

    /**
     * 根据用户名查找用户
     *
     * @param
     *            {@link java.lang.String}
     * @return
     */
    @Override
    public Customer customer(String customerUsername) {
        return this.selectOne(
                "com.ningpai.customer.dao.CustomerMapper.selectByName",
                customerUsername);
    }
    /**
     * 根据输入的用户名查询用户
     *
     * @param customerUsername
     *            用户名{@link java.lang.String}
     * @return List
     */
    @Override
    public List<Customer> customerList(String customerUsername) {
        return this.selectList(
                "com.ningpai.customer.dao.CustomerMapper.customerlist",
                customerUsername);
    }

    /**
     * 查询所有的会员信息
     *
     * @return
     */
    @Override
    public List<CustomerAllInfo> selectCustomerAll() {
        return this
                .selectList("com.ningpai.customer.dao.CustomerMapper.selectCustomerAll");
    }
    /**
     * 禁用/启用商家员工
     *
     * @return 0 失败 1成功
     */
    @Override
    public int modifyEmpToDisableThird(Map<String, Object> paramMap) {
        return this
                .update("com.ningpai.customer.dao.CustomerMapper.modifyEmpToDisableThird",
                        paramMap);
    }
    /**
     * 删除商家员工 paramMap 状态要操作条件
     *
     * @return 成功1 失败0
     */
    @Override
    public int deleteCustomerThird(Map<String, Object> paramMap) {
        return this.delete(
                "com.ningpai.customer.dao.CustomerMapper.deleteCustomerThird",
                paramMap);
    }

    /**
     * 修改会员信息登陆错误的次数
     */
    @Override
    public int updateCusErrorCount(Customer customer) {
        return this
                .update("com.ningpai.site.customer.dao.CustomerMapper.updateCusErrorCount",
                        customer);
    }

    /**
     * 修改会员信息
     */
    @Override
    public int updateCus(Customer customer) {
        return this.update(
                "com.ningpai.site.customer.dao.CustomerMapper.updateCus",
                customer);
    }

    /**
     * 修改会员信息 修改会员登陆达到指定的次数就锁定账户
     */
    @Override
    public int updateCusLock(Customer customer) {
        return this.update(
                "com.ningpai.site.customer.dao.CustomerMapper.updateCusLock",
                customer);
    }
    /**
     * 根据用户名查询用户信息
     *
     * @param userName
     * @return
     */
    @Override
    public Customer selectCustomerByUserName(String userName) {
        return this
                .selectOne(
                        "com.ningpai.customer.dao.CustomerMapper.selectCustomerByUserName",
                        userName);
    }

    /**
     *
     * 查询区县
     *
     * @param list
     *            不需要查询的地区id
     * @return
     * @author NINGPAI-LIH
     */
    @Override
    public List<DistrictBean> selectDistrictInId(List<Long> list) {
        return this.selectList(
                "com.ningpai.customer.dao.CustomerMapper.selectDistrictInId",
                list);
    }

    /**
     * 查询所有城市
     *
     * @return
     * @author NINGPAI-LIH
     */
    @Override
    public List<CityBean> selectAllCityByDistrict(List<Long> list) {
        return this
                .selectList(
                        "com.ningpai.customer.dao.CustomerMapper.selectAllCityByDistrict",
                        list);
    }

    /**
     * 根据时间统计会员个数
     *
     * @return
     */
    @Override
    public List<CustomerStatisticVo> selectCountByTime() {
        return this
                .selectList("com.ningpai.customer.dao.CustomerMapper.selectCountByTime");
    }

    /**
     * 根据地区统计会员个数
     *
     * @return
     */
    @Override
    public List<CustomerStatisticVo> selectCountByAddress() {
        return this
                .selectList("com.ningpai.customer.dao.CustomerMapper.selectCountByAddress");
    }
    /**
     * 删除会员 批量
     *
     * @param paramMap
     *            需要删除的会员ID集合
     * @return int
     */
    @Override
    public int setCustomerByIds(Map<String, Object> paramMap) {

        return this.update(
                "com.ningpai.customer.dao.CustomerMapper.setCustomerByIds",
                paramMap);
    }
    /**
     * 查询会员消费排行
     *
     * @param map
     * @return
     */
    @Override
    public List<Object> queryCustomerRank(Map<String, Object> map) {
        return this.selectList(
                "com.ningpai.customer.dao.CustomerMapper.queryCustomerRank",
                map);
    }
    /**
     * 查询总数
     *
     * @param paraMap
     * @return
     */
    @Override
    public int selectAllSize(Map<String, Object> paraMap) {
        return this.selectOne(
                "com.ningpai.customer.dao.CustomerMapper.selectAllSize",
                paraMap);
    }
    /**
     * 查询会员等级情况
     *
     * @return
     */
    @Override
    public List<Customer> queryCusLevleInfo() {

        return this
                .selectList("com.ningpai.customer.dao.CustomerMapper.queryCusLevleInfo");
    }
    /**
     * 查询会员的订单情况
     *
     * @return
     */
    @Override
    public List<Object> queryCusAndOrderInfo(Map<String, Object> paraMap) {

        return this.selectList(
                "com.ningpai.customer.dao.CustomerMapper.queryCusAndOrderInfo",
                paraMap);
    }
    /**
     *
     */
    @Override
    public int selectCusCount(Map<String, Object> paraMap) {

        return this.selectOne(
                "com.ningpai.customer.dao.CustomerMapper.selectCusCount",
                paraMap);
    }
    /**
     * 查寻
     *
     * @return
     */
    @Override
    public Integer checkUsernameExitOrNot(Map<String, Object> map) {
        return this
                .selectOne(
                        "com.ningpai.customer.dao.CustomerMapper.checkUsernameExitOrNot",
                        map);
    }

    /**
     * 验证手机存在性
     *
     * @param
     *
     * @return 0不存在 1存在
     */
    @Override
    public Long checkMobileExist(String mobile) {
        return this.selectOne(
                "com.ningpai.customer.dao.CustomerMapper.checkMobileExist",
                mobile);
    }

    /**
     * 验证邮箱存在性
     *
     * @param email
     *            目标邮箱 {@link String}
     * @return 0不存在 1存在
     */
    @Override
    public Long checkEmailExist(String email) {
        return this.selectOne(
                "com.ningpai.customer.dao.CustomerMapper.checkEmailExist",
                email);
    }

    /**
     * 查询新增的会员信息
     *
     * @author lih
     * @return
     */
    @Override
    public List<Customer> selectCustmerNewLimit() {
        return this
                .selectList("com.ningpai.customer.dao.CustomerMapper.selectCustmerNewLimit");
    }

    /**
     * 查询所有会员信息
     *
     * @return List
     */
    @Override
    public List<CustomerAllInfo> selectCustomerAllInfomation() {
        return this
                .selectList("com.ningpai.customer.dao.CustomerMapper.selectCustomerAllInfomation");
    }

    @Override
    public int updataIsEnterprise(Long customerId) {
        return this.update("com.ningpai.customer.dao.CustomerMapper.updataIsEnterprise" ,customerId);
    }
}
