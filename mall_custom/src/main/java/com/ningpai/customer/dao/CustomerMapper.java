/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.customer.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.customer.bean.Customer;
import com.ningpai.customer.vo.CustomerStatisticVo;
import com.ningpai.other.bean.CityBean;
import com.ningpai.other.bean.CustomerAllInfo;
import com.ningpai.other.bean.DistrictBean;
import com.ningpai.other.bean.OrderInfoBean;
import com.ningpai.other.bean.ProvinceBean;
import com.ningpai.other.bean.StreetBean;

/**
 * 会员底层Mapper
 * 
 * @author NINGPAI-zhangqiang
 * @since 2013年12月16日 下午6:48:35
 * @version 0.0.1
 */
@Repository
public interface CustomerMapper {

    /**
     * 禁用/启用商家员工
     *
     * @return 0 失败 1成功
     */
    int modifyEmpToDisableThird(Map<String, Object> paramMap);

    /**
     * 删除商家员工 paramMap 状态要操作条件
     * 
     * @return 成功1 失败0
     */
    int deleteCustomerThird(Map<String, Object> paramMap);

    /***
     * 修改会员信息 登陆错误的次数
     * 
     * @param customer
     * @return
     */
    int updateCusErrorCount(Customer customer);

    /***
     * 修改会员信息 达到登陆错误次数 就插入锁定时间
     * 
     * @param customer
     * @return
     */
    int updateCusLock(Customer customer);

    /***
     * 根据会员登录名获取单个的会员信息（用户 会员登陆次数限制）
     * 
     * @param username
     *            会员登陆名称
     * @return
     */
    Customer getCustomerByUsername(String username);

    /**
     * 删除商家所有商品
     * 
     * @param customerId
     * @return
     */
    int deleteGoods(String customerId);

    /**
     * 删除商家为普通会员
     * 
     * @param map
     * @return
     */
    int deleteStore(Map<String, Object> map);

    /**
     * 修改会员状态为商家
     * 
     * @param cId
     * @return
     */
    int updateStatus(int cId);

    /**
     * 删除商家员工
     * 
     * @param thirdId
     * @return
     */
    int deleteEmp(Long thirdId);

    /**
     * 更新单个会员信息
     * 
     * @param customer
     * @return
     */

    int updateCustomer(Customer customer);

    /***
     * 根据主键获取单个的会员信息
     * 
     * @param customerId
     *            会员ID
     * @return
     */
    Customer getCustomerByCusId(Long customerId);

    /***
     * 修改会员信息
     * 
     * @param customer
     * @return
     */
    int updateCus(Customer customer);

    /**
     * 根据主键获取会员
     * 
     * @param customerId
     *            会员编号
     * @return 会员 com.ningpai.customer.bean.Customer
     *         {@link com.ningpai.customer.bean.Customer}
     */
    CustomerAllInfo selectByPrimaryKey(Long customerId);

    /**
     * 根据会员id查询出所有订单信息
     * 
     * @param customerIds
     * @return
     */
    List<CustomerAllInfo> selectBycustomerIds(Map<String, Object> customerIds);

    /**
     * 查询所有会员
     * 
     * @return 会员列表集合
     */
    List<Object> selectAllCustomer();

    /**
     * 分页查询
     * 
     * @return 会员列表集合
     */
    List<Object> selectCustomerByLimit(Map<String, Integer> paramMap);

    /**
     * 过滤掉第三方的用户 会员列表
     * @param paramMap
     * @return
     */
    List<Object> selectCustomerByLimitFilterThird(Map<String, Integer> paramMap);

    /**
     * 插入会员信息
     * 
     * @see 会员bean com.ningpai.customer.bean.Customer
     *      {@link com.ningpai.customer.bean.Customer}
     * @see 会员详细信息bean com.ningpai.customer.bean.CustomerInfo
     *      {@link com.ningpai.customer.bean.CustomerInfo}
     */
    int addCustomer(CustomerAllInfo customerinfo);

    /**
     * 查询商家id
     * 
     * @param customerId
     * @return
     */
    String findStoreId(String customerId);

    /**
     * 根据会员编号删除会员
     * 
     * @param customerId
     *            会员编号
     * @return java.lang.Integer {@link java.lang.Integer}
     */
    int deleteCustomerById(Long customerId);

    /**
     * 根据会员编号修改会员
     * 
     * @param customerId
     *            会员编号
     * @return java.lang.Integer {@link java.lang.Integer}
     */
    int updateByPrimaryKeySelective(CustomerAllInfo record);


    /**
     * 更新第三方的密码
     * @param map
     * @return
     */
    int updateThirdPassword(Map<String, Object> map);

    /**
     * 检查会员用户名是否存在
     * 
     * @param customerName
     *            等级名称
     * @return 1 存在 0 不存在
     * @see java.lang.Long {@link java.lang.Long}
     */
    Long selectCustomerByName(String customerName);

    /**
     * 第三方忘记密码时 判定用户名存在与否时使用
     * @param customerName
     * @return
     */
    Long selectCustomerByNameForThird(String customerName);

    /**
     * 根据订单编号查找订单信息
     * 
     * @param orderId
     *            订单编号 java.lang.Long {@link java.lang.Long}
     * @return
     */
    CustomerAllInfo selectCustomerOrder(Long orderId);

    /**
     * 查询所有省份
     * 
     * @return
     */
    List<ProvinceBean> selectAllProvince();

    /**
     * 根据省份编号 查询所有城市
     * 
     * @param provinceId
     *            省份编号 java.lang.Long {@link java.lang.Long}
     * @return
     */
    List<CityBean> selectAllCityByPid(Long provinceId);

    /**
     * 查询所有城市 by yuankk
     * 
     * @return
     */
    List<CityBean> selectAllCity();

    /**
     * 查询所有城市
     * 
     * @return
     * @author NINGPAI-LIH
     */
    List<CityBean> selectAllCityByDistrict(List<Long> list);

    /**
     * 根据城市编号 查询所有区县
     * 
     * @param cityId
     *            城市编号 java.lang.Long {@link java.lang.Long}
     * @return
     */
    List<DistrictBean> selectAllDistrictByCid(Long cityId);

    /**
     * 查询所有区县
     * 
     * @param cityId
     *            城市编号 java.lang.Long {@link java.lang.Long}
     * @return
     */
    List<DistrictBean> selectAllDistrict();

    /**
     * 
     * 查询区县
     * 
     * @param map
     *            不需要查询的地区id
     * @return
     * @author NINGPAI-LIH
     */
    List<DistrictBean> selectDistrictInId(List<Long> list);

    /**
     * 按条件查询会员
     * 
     * @param customerAllInfo
     *            {@link com.ningpai.customer.bean.CustomerAllInfo}
     * @return
     */
    List<Object> selectCustmerByAllInfo(CustomerAllInfo customerAllInfo);


    /**
     * 过滤掉第三方商家的,用户列表
     * @param customerAllInfo
     * @return
     */
    List<Object> selectCustmerByAllInfoFilterThird(CustomerAllInfo customerAllInfo);

    /**
     * 按条件查询会员 返回数量
     * 
     * @param customerAllInfo
     *            {@link com.ningpai.customer.bean.CustomerAllInfo}
     * @return
     */
    Long selectCustmerSize(CustomerAllInfo customerAllInfo);


    /**
     * 过滤掉第三方商家的,用户列表
     * @param customerAllInfo
     * @return
     */
    Long selectCustmerSizeFilterThird(CustomerAllInfo customerAllInfo);

    /**
     * 按区县编号获取对应街道集合
     * 
     * @param dId
     *            区县编号 java.lang.Long {@link java.lang.Long}
     * @return
     */
    List<StreetBean> getAllStreetByDid(Long dId);

    /**
     * 查询订单信息
     * 
     * @param orderId
     *            java.lang.Long {@link java.lang.Long}
     * @return OrderInfoBean {@link com.ningpai.other.bean.OrderInfoBean}
     */
    OrderInfoBean queryByDetail(Long orderId);

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
    CustomerAllInfo queryCustomerInfo(Long customerId);

    /**
     * 删除会员 批量
     * 
     * @param paramMap
     *            需要删除的会员ID集合
     * @return int {@link java.util.Integer}
     */
    int deleteCustomerByIds(Map<String, Object> paramMap);

    /**
     * 查询会员类型
     * 
     * @param customerId
     * @return
     */
    String selectStatus(String customerId);

    /**
     * 查询所有会员的数量
     * 
     * @return
     */
    Integer selectAllCustomerCount();

    /**
     * 按照会员名查找会员
     * 
     * @param username
     *            会员名
     * @return 0 不存在 1 存在
     */
    Long checkexistsByCustName(String username);

    /**
     * 根据会员名和密码验证用户
     * 
     * @param paramMap
     *            验证条件
     * @return null 密码错误 Customer 验证成功
     */
    Customer selectCustomerByNamePwd(Map<String, Object> paramMap);

    /**
     * 修改登录时间 IP
     * 
     * @param Customer
     *            {@link com.ningpai.customer.bean.Customer}
     * @return 0失败 1成功
     */
    int updateCustomerLoginTime(Customer customer);

    /**
     * 禁用用户
     * 
     * @param Map
     *            custId 用户编号 flag :disable禁用 否则启用
     * @return 0 失败 1成功
     */
    int modifyEmpToDisable(Map<String, Object> paramMap);

    /**
     * 根据订单编号查找订单信息
     * 
     * @param orderId
     *            订单编号
     * @return OrderInfoBean
     */
    Object queryOrderByOrderId(Long orderId);

    /**
     * 检查用户是否存在
     * 
     * @param userName
     *            用户名 {@link String}
     * @return 0 不存在 1 存在 {@link Long}
     */
    Long checkExistsByCustNameAndType(Map<String, Object> paramMap);

    /**
     * 根据用户名查找用户
     * 
     * @param customerUsername用户名
     *            {@link java.lang.String}
     * @return
     */
    Customer customer(String customerUsername);

    /**
     * 根据输入的用户名查询用户
     * 
     * @param customerUsername
     *            用户名{@link java.lang.String}
     * @return List
     */
    List<Customer> customerList(String customerUsername);

    /**
     * 查询所有的会员信息
     * 
     * @return
     */
    List<CustomerAllInfo> selectCustomerAll();

    /**
     * 根据用户名查询用户信息
     * 
     * @param userName
     * @return
     */
    Customer selectCustomerByUserName(String userName);

    /**
     * 根据时间统计会员个数
     * 
     * @return
     */
    List<CustomerStatisticVo> selectCountByTime();

    /**
     * 根据地区统计会员个数
     * 
     * @return
     */
    List<CustomerStatisticVo> selectCountByAddress();

    /**
     * 删除会员 批量
     * 
     * @param paramMap
     *            需要删除的会员ID集合
     * @return int {@link java.util.Integer}
     */
    int setCustomerByIds(Map<String, Object> paramMap);

    /**
     * 查询会员消费排行
     * 
     * @param paramMap
     * @return
     */
    List<Object> queryCustomerRank(Map<String, Object> map);

    /**
     * 查询总数
     * 
     * @param paraMap
     * @return
     */
    int selectAllSize(Map<String, Object> paraMap);

    /**
     * 查询会员等级情况
     * 
     * @return
     */
    List<Customer> queryCusLevleInfo();

    /**
     * 查询会员的订单情况
     * 
     * @return
     */
    List<Object> queryCusAndOrderInfo(Map<String, Object> paraMap);

    /**
        * 
        */
    int selectCusCount(Map<String, Object> paraMap);

    /**
     * 查寻
     * 
     * @return
     */
    Integer checkUsernameExitOrNot(Map<String, Object> map);

    /**
     * 验证手机存在性
     * 
     * @param email
     *            目标手机 {@link String}
     * @return 0不存在 1存在
     */
    Long checkMobileExist(String mobile);

    /**
     * 验证邮箱存在性
     * 
     * @param email
     *            目标邮箱 {@link String}
     * @return 0不存在 1存在
     */
    Long checkEmailExist(String email);

    /**
     * 查询新增的会员信息
     * 
     * @author lih
     * @return
     */
    List<Customer> selectCustmerNewLimit();

    /**
     * 查询所有会员信息
     * 
     * @return List
     */
    List<CustomerAllInfo> selectCustomerAllInfomation();

    /**
     * 修改会员为企业用户
     * @return
     */
    int updataIsEnterprise(Long customerId);

}
