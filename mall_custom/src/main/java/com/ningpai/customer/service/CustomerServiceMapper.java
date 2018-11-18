/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.customer.service;

import com.ningpai.customer.bean.Customer;
import com.ningpai.customer.bean.CustomerAddress;
import com.ningpai.customer.bean.CustomerInfo;
import com.ningpai.customer.vo.CustomerStatisticVo;
import com.ningpai.other.bean.*;
import com.ningpai.other.util.SelectBean;
import com.ningpai.util.PageBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 会员服务处理接口
 * 
 * @author NINGPAI-zhangqiang
 * @since 2013年12月14日 下午2:57:09
 * @version 0.0.1
 */
public interface CustomerServiceMapper {

    /**
     * 禁用/启用商家员工
     *
     * @param custId
     *            用户编号
     * @param flag
     *            disable 禁用 否则 启用
     * @return 0 失败 1成功
     */
    int modifyEmpToDisableThird(Long custId, String flag, Long thirdId);

    /**
     * 删除商家员工
     * 
     * @param custId
     *            要删除的员工ID
     * @param thirdId
     *            当前商家ID
     * @return 成功1 失败0
     */
    int deleteCustomerThird(String[] custId, Long thirdId);

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
     * 删除商家会员为普通会员
     * 
     * @param ids
     * @return
     */
    int deleteStore(String[] ids);

    /**
     * 修改会员状态为商家
     * 
     * @param cId
     * @return
     */
    int updateStatus(int cId);

    /**
     * 查询单个会员信息 详细
     * 
     * @param customerId
     *            用户编号
     * @see java.lang.Long {@link java.lang.Long}
     * @see com.ningpai.customer.bean.Customer
     *      {@link com.ningpai.customer.bean.Customer}
     * @return @see com.ningpai.customer.bean.Customer
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
     * 查询所用会员用户 分页
     * 
     * @see com.ningpai.util.PageBean {@link com.ningpai.util.PageBean}
     * @see com.ningpai.customer.bean.Customer
     *      {@link com.ningpai.customer.bean.Customer}
     * @return @see com.ningpai.util.PageBean {@link com.ningpai.util.PageBean}
     */
    PageBean selectAllCustmer(PageBean pageBean);

    /**
     * <strong> 添加会员</strong> <br>
     * 注意事项：<br>
     * 1.可用于添加会员所有信息 所有信息参考 {@link CustomerAllInfo}<br>
     * 2.添加普通会员时,不用填充第三方字段属性<br>
     * 3.添加第三方会员或者店铺管理员时,需要填充会员信息中的第三方信息<br>
     * 
     * @param allinfo
     *            {@link CustomerAllInfo} 查询、添加、修改等操作时用到的辅助类 包含会员的所有信息
     * @return 返回结果：int 0 添加失败 1 添加成功
     */
    int addCustomer(CustomerAllInfo allinfo);


    /**
     * 第三方注册用户
     * @param allinfo
     * @return
     */
    @Transactional
    int addThirdCustomer(CustomerAllInfo allinfo);

    /**
     * 查询会员对应的商家id
     * 
     * @param customerId
     * @return
     */
    String findStore(String customerId);

    /**
     * 删除会员
     * 
     * @see
     * @see java.lang.Integer {@link java.lang.Integer}
     * @return 0 失败 1 成功
     */
    int deleteCustomer(String[] customerId);

    /**
     * 查询会员类型
     * 
     * @param customerId
     * @return
     */
    String selectStatus(String customerId);

    /**
     * 修改会员信息
     * 
     * @see
     * @see java.lang.Integer {@link java.lang.Integer}
     * @return 0 失败 1 成功
     */
    int updateCustomer(CustomerAllInfo allinfo);

    /**
     * 检查会员名是否存在
     * 
     * @param customerName
     *            等级名称
     * @return 1 存在 0 不存在
     * @see java.lang.Long {@link java.lang.Long}
     */
    Long selectCustomerByName(String customerName);

    /**
     * 第三方忘记密码  判定用户存在与否时使用
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
     * 查询所有省份 用于添加会员页面填充省份
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
     * @param
     * @return
     */
    List<CityBean> selectAllCity();

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
     * @param
     * @return
     */
    List<DistrictBean> selectAllDistrict();

    /**
     * 按条件查询会员
     * 
     * @param customerAllInfo
     *
     * @return
     */
    PageBean selectCustmerByAllInfo(CustomerAllInfo customerAllInfo,
            PageBean pageBean);

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
     * 根据会员编号查找对应的默认收货地址
     * 
     * @param customerId
     *            会员编号
     * @return 会员收货地址
     */
    CustomerAddress queryDefaultAddr(Long customerId);

    /**
     * 禁用/启用用户
     * 
     * @param custId
     *            用户编号
     * @param flag
     *            disable 禁用 否则 启用
     * @return 0 失败 1成功
     */
    int modifyEmpToDisable(Long custId, String flag);

    /**
     * 查询用户上一次收货地址
     * 
     * @param customerId
     * @return
     */
    CustomerAddress selectByCIdFirst(Long customerId);

    /**
     * 根据会员编号修改会员
     * 
     * @param
     *
     * @return java.lang.Integer {@link java.lang.Integer}
     */
    int updateByPrimaryKeySelective(CustomerAllInfo record);

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
    Long checkExistsByCustNameAndType(String userName);

    /**
     * 根据用户名查询用户
     * 
     * @param customerUserName
     * @return 对象
     */
    Customer customer(String customerUserName);

    /**
     * 根据用户名查询用户
     * 
     * @param customerUserName
     * @return list
     */
    List<Customer> customerList(String customerUserName);

    /**
     * 查询邮箱
     * 
     * @return
     */
    CustomerInfo email(Long customerId);

    /**
     * 查询手机
     * 
     * @return
     */
    CustomerInfo mobile(Long customerId);

    /***
     * 修改会员信息
     * 
     * @param customer
     * @return
     */
    int updateCus(Customer customer);

    /**
     * 根据用户名查询用户信息
     * 
     * @param userName
     * @return
     */
    Customer selectCustomerByUserName(String userName);

    // int updateCusSumPoint(Map<String, Object> paraMap);

    /**
     * 根据时间统计会员个数
     */
    List<CustomerStatisticVo> selectCountByTime();

    /**
     * 根据地区统计会员个数
     */
    List<CustomerStatisticVo> selectCountByAddress();

    /**
     * 修改会员信息
     * 
     * @see
     * @see java.lang.Integer {@link java.lang.Integer}
     * @return 0 失败 1 成功
     */
    int setCustomer(CustomerAllInfo allinfo);

    /**
     * 更新第三方的密码
     * @param map
     * @return
     */
    int updatePassword(Map<String, Object> map);

    /**
     * 设置会员类型
     * 
     * @see
     * @see java.lang.Integer {@link java.lang.Integer}
     * @return 0 失败 1 成功
     */
    int setCustomer(String[] customerId, String isSiteManager);

    /**
     * 查询会员消费排行
     * 
     * @param startTime
     * @param endTime
     * @return
     */
    PageBean queryCustomerRank(PageBean pageBean, SelectBean selectBean,
            String startTime, String endTime);

    /**
     * 查询会员等级情况
     * 
     * @return
     */
    List<Customer> queryCusLevleInfo();
    /**
     * 查询信息
     * */
    PageBean queryCusAndOrderInfo(PageBean pageBean, Long pointLevelId,
            String customerNickname);

    /**
     * 查询会员，咨询，评论，晒单的数量
     * 
     * @return
     */
    Map<String, Object> getCustomerCount();

    /**
     * 手动更新会员等级
     * 
     * @return
     */
    int upCusLevel(Map<String, Object> paraMap);

    /**
     * 查询商家员工用户名是否存在
     * 
     * @return
     */
    Integer checkUsernameExitOrNot(Map<String, Object> map);

    /**
     * 验证邮箱存在性
     * 
     * @param email
     *            目标邮箱 {@link String}
     * @return 0不存在 1存在
     */
    Long checkEmailExist(String email);

    /**
     * 验证手机存在性
     * 
     * @param mobile
     *            目标手机 {@link String}
     * @return 0不存在 1存在
     */
    Long checkMobileExist(String mobile);

    /**
     * 查询最新的会员信息
     * 
     * @author lih
     * @return
     */
    List<Customer> selectNewCustoer();

    /**
     * 查询所有会员
     * 
     * @return List
     */
    List<CustomerAllInfo> selectCustomerAllInfomation();

    /**
     * 根据主键获取单个的会员信息
     *
     * @param customerId
     * @return
     */
    Customer getCustomerByCusId(Long customerId);
}
