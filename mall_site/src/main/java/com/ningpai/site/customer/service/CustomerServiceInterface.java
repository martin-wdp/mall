/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.site.customer.service;

import com.ningpai.customer.bean.Customer;
import com.ningpai.customer.bean.CustomerAddress;
import com.ningpai.site.customer.vo.*;
import com.ningpai.system.bean.SystemsSet;
import com.ningpai.util.PageBean;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 会员服务接口
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年1月13日 下午5:40:19
 * @version 0.0.1
 */
public interface CustomerServiceInterface {

    /**
     * 查询当前会员的退单信息
     *
     * @param paramMap
     *            查询订单条件
     * @return
     */
    PageBean queryAllBackMyOrders(Map<String, Object> paramMap, PageBean pb);
    /***
     * 根据ID获取单个区县对象
     * @return
     */
    DistrictBean selectDistrictBeanById(Long did);

    /***
     * 根据主键获取单个的省份信息
     * @param provinceId
     * @return
     */
    ProvinceBean selectprovinceByPid(Long provinceId );
    
    /***
     * 是否允许退单
     * @return
     */
    SystemsSet getIsBackOrder();
    /**
     * 根据会员编号查询会员信息
     * 
     * @param customerId
     *            会员编号
     * @return Customer @{@link com.ningpai.customer.bean.Customer}
     */
    Customer queryCustomerById(Long customerId);

    /**
     * 根据会员编号查找订单信息
     * 
     * @param paramMap
     *            查询条件
     * @return List<Object>
     */
    PageBean queryMyOrders(Map<String, Object> paramMap, PageBean pb);

    /**
     * 查询所有订单
     * 
     * @param paramMap
     *            查询订单条件
     * @return
     */
    PageBean queryAllMyOrders(Map<String, Object> paramMap, PageBean pb);

    /**
     * 根据订单编号查找订单信息
     * 
     * @param orderId
     *            订单编号
     * @return OrderInfoBean
     */
    Object queryOrderByCustIdAndOrderId(Long orderId, Long customerId);

    /**
     * 根据订单编号查找订单收货地址
     * 
     * @param orderId
     *            订单编号
     * @return Object
     */
    Object queryCustomerAddressById(Long orderId);

    /**
     * 根据会员编号查找会员详细信息
     * 
     * @param parseLong
     *            会员编号
     * @return CustomerInfo {@link com.ningpai.customer.bean.CustomerInfo}
     */
    Object queryCustomerInfoById(long parseLong);

    /**
     * 修改会员信息
     * 
     * @param allInfo
     *            新信息对象
     * @return 0失败 1成功
     */
    int modifyCustomerInfo(CustomerAllInfo allInfo, String flag);

    /**
     * 确认收货
     * 
     * @param orderId
     *            订单编号 {@link java.lang.Long}
     * @return 0 失败 1成功
     */
    Integer comfirmOfGooods(Long orderId);

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
     * 根据城市编号 查询所有区县
     * 
     * @param cityId
     *            城市编号 java.lang.Long {@link java.lang.Long}
     * @return
     */
    List<DistrictBean> selectAllDistrictByCid(Long cityId);

    /**
     * 按区县编号获取对应街道集合
     * 
     * @param dId
     *            区县编号 java.lang.Long {@link java.lang.Long}
     * @return
     */
    List<StreetBean> getAllStreetByDid(Long dId);

    /**
     * 根据会员编号查找会员详细信息
     * 
     * @param parseLong
     *            会员编号
     * @return Customer {@link com.ningpai.customer.bean.Customer}
     */
    Object queryCustomerByCustomerId(long parseLong);

    /**
     * 验证用户密码
     * 
     * @param customerId
     *            会员编号
     * @param password
     *            当前输入密码
     * @return 0不相同 1相同
     */
    int checkCustomerPassword(Long customerId, String password);

    /**
     * 修改用户密码
     * 
     * @param allInfo
     *            用户信息
     * @return 0失败 1成功
     */
    int updateByPrimaryKey(CustomerAllInfo allInfo);

    /**
     * 根据用户编号查找用户的收货地址
     * 
     * @param customerId
     *            用户编号
     * @return CustomerAllInfo {@link com.ningpai.site.customer.vo.CustomerAllInfo}
     */
    CustomerAllInfo queryAddressByCustomerId(Long customerId);
    /**
     * 根据用户编号查找用户的收货地址
     * @param pb 分页条件
     * @param customerId
     *            用户编号
     * @return PageBean
     */
    PageBean queryAddressByCustomerId(Long customerId,PageBean pb);


    /**
     * 添加收货地址
     * 
     * @param address
     *            地址信息
     * @return 0添加失败 添加成功
     */
    int addCustomerAddress(CustomerAddress address);

    /**
     * 删除收货地址
     * 
     * @param addressId
     *            地址编号
     * @return 0失败 1成功
     */
    int deleteCustAddress(Long addressId);

    /**
     * 根据地址编号查找收货地址
     * 
     * @param addressId
     *            地址编号
     * @return CustomerAddress {@link com.ningpai.customer.bean.CustomerAddress}
     */
    CustomerAddress queryCustAddress(Long addressId);

    /**
     * 修改会员收货地址
     * 
     * @param address
     *            新地址信息
     * @return 0失败 1成功
     */
    int modifyCustAddress(CustomerAddress address);

    /**
     * 修改默认地址 将之前的默认地址改为非默认 并且将当前地址改为默认地址
     * 
     * @param customerId
     *            会员编号
     * @param addressId
     *            地址编号 要设置为默认的编号
     * @return 0失败 1成功
     */
    int modifyIsDefaultAddress(HttpServletRequest request,String customerId, String addressId);

    /**
     * 根据会员编号查询相应的会员积分明细
     * 
     * @param paramMap
     *            查询条件
     * @return PageBean
     */
    PageBean selectAllCustomerPoint(Map<String, Object> paramMap, PageBean pb);

    /**
     * 根据会员编号查询相应的总会员积分
     * 
     * @param customerId
     *            会员编号
     * @return Long
     */
    Long selectTotalPointByCid(Long customerId);

    /**
     * 根据会员编号查询相应的会员收藏
     * 
     * @param paramMap
     *            查询条件
     * @return PageBean
     */
    PageBean selectAllCustomerFollow(Map<String, Object> paramMap, PageBean pb);

    /**
     * 查询通知内容数量 如 :待处理订单数量...
     * 
     * @param customerId
     *            会员编号
     * @return Map<String,Object> {@link java.util.Map}
     */
    Map<String, Object> selectNotice(Long customerId);

    /**
     * 取消订单
     * 
     * @param orderId
     *            订单Id
     * @return 0失败 1成功
     */
    int cancelOrder(Long orderId, String reason);

    /**
     * 查询会员基本信息
     * 
     * @param customerId
     *            会员编号
     * @return CustomerAllInfo {@link com.ningpai.site.customer.vo.CustomerAllInfo}
     */
    CustomerAllInfo selectByPrimaryKey(Long customerId);

    /**
     * 删除订单
     * 
     * @param orderId
     *            订单Id
     * @return 0失败 1成功
     */
    int delOrder(Long orderId);

    /**
     * 修改密码 邮箱 手机
     * 
     * @param type
     *            pwd email mobile
     * @param newStr
     *            新字段
     * @return
     */
    int modifyPem(HttpServletRequest request, String newStr, String type);

    /**
     * 发送邮件
     * 
     * @param request
     * @param email
     *            邮件地址
     * @return 0 失败 1成功
     */
    int sendEamil(HttpServletRequest request, String email);

    /**
     * 发送手机验证码
     * 
     * @param request
     * @param moblie
     *            目标手机
     * @return 1 失败 0网络连接超时 -1没过90秒
     */
    int sendPost(HttpServletRequest request, String moblie);

    /**
     * 验证手机验证码
     * 
     * @param request
     * @return 0失败 1成功 -1失效
     */
    int getMCode(HttpServletRequest request, String code);

    /**
     * 验证邮箱有效
     * 
     * @param d
     * @param checkCode
     * @return
     */
    int verifyCheckCode(HttpServletRequest request, Long d, String checkCode);

    /**
     * 修改会员详细信息
     * @param user
     * @return
     */
    int updateCustInfoByPrimaryKey(CustomerAllInfo user);

    /**
     * 确认收货
     * 
     * @param orderId
     *            订单编号
     * @return
     */
    int comfirmofGoods(Long orderId);

    /**
     * 检查用户名存在性
     * 
     * @param username
     *            用户名
     * @return 0不存在 1存在
     */
    Long checkUsernameFlag(String username);

    /**
     * 根据用户名查询用户简单信息
     * 
     * @param username
     *            用户名
     * @return 用户信息 {@link Customer}
     */
    CustomerAllInfo selectCustomerByUname(String username);

    /**
     * 修改找回密码Code
     * 
     * @param user
     * @return
     */
    int updateFindPwdCode(CustomerAllInfo user);

    /**
     * 验证邮件
     * 
     * @param request
     * @param checkCode
     * @param d
     * @return
     */
    int validPwdEmail(HttpServletRequest request, String checkCode, Long d);

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
     * 验证会员是否存在某订单
     * @param customerId
     * @return
     */
    Long checkexistsByIdAndCode(Long customerId,String orderCode);

    /**
     * 查询可以投诉的订单
     * @param paramMap  条件查询参数
     * @param pb 分页参数
     * @auther zhangsl
     * @return
     */
    PageBean queryOrdersForComplain(Map<String, Object> paramMap, PageBean pb);

    /**
     * 查询用户所有获得积分总和
     *
     *@param  customerId   用户Id
     * @return 该用户所有获得的积分总和
     * @author houyichang 2015/9/25
     * */
    int querySumByCustId(Long customerId);

    /**
     * 根据客户id修改客户等级
     *
     * @param pointLevelId 等级id
     * @param pointLevelName  等级名称
     * @param customerId  客户id
     * @return 修改结果
     * @author houyichang 2015/9/25
     * */
    int updCustLevel(Long pointLevelId,String pointLevelName,Long customerId );
}
