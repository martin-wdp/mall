/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.site.customer.mapper;

import com.ningpai.customer.bean.Customer;
import com.ningpai.customer.bean.CustomerAddress;
import com.ningpai.customer.bean.CustomerPoint;
import com.ningpai.site.customer.vo.*;
import com.ningpai.site.order.bean.ExchangeCusmomerPoint;
import com.ningpai.system.bean.SystemsSet;

import java.util.List;
import java.util.Map;

/**
 * 会员Mapper
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年1月13日 下午1:07:58
 * @version 0.0.1
 */
public interface CustomerMapper {
    /**
     * 查询该会员下面的 所有退单信息
     *
     * @param paramMap
     *            查询订单条件
     * @return
     */
    List<Object> queryAllMyBackOrders(Map<String, Object> paramMap);

    /**
     * 获取当前会员 退单的数据条数 zhanghl
     * @param map
     * @return
     */
    Long searchTotalCountBack(Map<String,Object> map);

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
     * 根据会员ID获取会员对象
     * @param customerId
     * @return
     */
    CustomerPoint selectCustomerPointByCustomerId(Long customerId);
    /**
     * 根据订单号获取积分兑换对象
     * @param orderCode
     * @return
     */
    ExchangeCusmomerPoint selectExchangeByOrderCode(String orderCode);
    /***
     * 是否允许退单
     * @return
     */
    SystemsSet getIsBackOrder();
    
    /**
     * 根据主键删除会员
     * 
     * @param customerId
     *            会员编号
     * @return 0失败 1成功
     */
    int deleteByPrimaryKey(Long customerId);

    /**
     * 按条件插入数据
     * 
     * @param record
     *            要插入的数据的对象
     * @return 0失败 1成功
     */
    int insertSelective(Customer record);

    /**
     * 按照主键编号查找 基本信息
     * 
     * @param customerId
     *            会员主键编号
     * @return CustomerAllInfo {@link com.ningpai.site.customer.vo.CustomerAllInfo}
     */
    CustomerAllInfo selectByPrimaryKey(Long customerId);

    /**
     * 按照主键编号查找
     * 
     * @param customerId
     *            会员主键编号
     * @return CustomerAllInfo {@link com.ningpai.site.customer.vo.CustomerAllInfo}
     */
    Customer queryCustomerById(Long customerId);

    /**
     * 按条件修改会员信息
     * 
     * @param record
     *            要修改的会员对象
     * @return 0失败 1成功
     */
    int updateByPrimaryKeySelective(Customer record);

    /**
     * 根据主键编号修改会员信息
     * 
     * @param record
     *            要修改的会员对象
     * @return 0失败 1成功
     */
    int updateByPrimaryKey(Customer record);

    /**
     * 根据会员编号 和 订单状态 查找订单信息
     * 
     * @param paramMap
     *            Map<String,Long>
     * @return List<Object>
     */
    List<Object> queryMyOrders(Map<String, Object> paramMap);

    /**
     * 根据订单编号查找订单信息
     * 
     * @param orderId
     *            订单编号
     * @return OrderInfoBean
     */
    Object queryOrderByOrderId(Long orderId);

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
    int modifyCustomerInfo(CustomerAllInfo allInfo);

    /**
     * 确认收货
     * 
     * @param orderId
     *            订单编号 {@link java.lang.Long}
     * @return 0 失败 1成功
     */
    int comfirmOfGooods(Long orderId);

    /**
     * 修改订单 订单完成
     * 
     * @param orderId
     *            订单编号
     * @return 0失败 1成功
     */
    Integer modifyOrderStatusToSuccess(Long orderId);

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
     * 根据用户编号查找用户的收货地址
     * 
     * @param customerId
     *            用户编号
     * @return CustomerAllInfo {@link com.ningpai.site.customer.vo.CustomerAllInfo}
     */
    CustomerAllInfo queryAddressByCustomerId(Long customerId);
    /**
     * 根据用户编号查找用户的收货地址
     * @param map  customerId
     *            用户编号
     * @return PageBean
     */
    List<Object> queryAddressByCustomerId(Map<String,Object> map);
    /**
     * 根据用户编号查找用户的收货地址的数量
     *
     * @param custId
     *            用户编号
     * @return Long   用户收货地址的数量
     */
    Long queryAddressCountByCustomerId(Long custId);

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
     * 取消当前用户的默认地址
     * 
     * @param customerId
     *            用户编号
     * @return 0失败 >1成功
     */
    int cancelDefaultAddress(String customerId);

    /**
     * 修改默认地址 将当前地址改为默认地址
     * 
     * @param paramMap
     *             查询订单条件
     *
     * @return 0失败 1成功
     */
    int setDefaultAddress(Map<String, Object> paramMap);

    /**
     * 查询所有订单
     * 
     * @param paramMap
     *            查询订单条件
     * @return
     */
    List<Object> queryAllMyOrders(Map<String, Object> paramMap);

    /**
     * 查询所有订单数量
     * 
     * @param paramMap
     *            查询订单条件
     * @return
     */
    Long searchTotalCount(Map<String, Object> paramMap);

    /**
     * 按条件查询所有订单数量
     * 
     * @param paramMap
     *            查询订单条件
     * @return
     */
    Long searchTotalCountO(Map<String, Object> paramMap);

    /**
     * 根据用户ID和用户密码查询密码用户
     * 
     * @param map
     *            参数
     * @return 查询到的用户对象
     */
    Customer checkCustomerPwd(Map<String, Object> map);

    /**
     * 查询积分记录总数
     * 
     * @param paramMap
     *            查询条件
     * @return 记录总数
     */
    Long queryPointRcCount(Map<String, Object> paramMap);

    /**
     * 查询所有积分记录
     * 
     * @param paramMap
     *            查询条件
     * @return 记录
     */
    List<Object> queryAllPointRc(Map<String, Object> paramMap);

    /**
     * 查询积分总和
     * 
     * @param paramMap
     *            查询条件
     * @return Long
     */
    Long selectTotalPointByCid(Map<String, Object> paramMap);

    /**
     * 收藏总数
     * 
     * @param paramMap
     *            查询条件
     * @return Long
     */
    Long queryFollowRcCount(Map<String, Object> paramMap);

    /**
     * 查询所有收藏记录
     * 
     * @param paramMap
     *            查询条件
     * @return List<Object>
     */
    List<Object> queryAllFollowRc(Map<String, Object> paramMap);

    /**
     * 查询待评论的订单
     * 
     * @param paramMap
     *            查询条件
     * @return Long {@link java.lang.Long}
     */
    Long selectpendingOrderNotice(Map<String, Object> paramMap);

    /**
     * 查询降价通知数量
     * 
     * @param paramMap
     *            查询条件
     * @return Long {@link java.lang.Long}
     */
    Long selectReducePriceNum(Map<String, Object> paramMap);

    /**
     * 查询到货通知的数量
     * 
     * @param paramMap
     *            查询条件
     * @return Long {@link java.lang.Long}
     */
    Long selectGoodsArriveNum(Map<String, Object> paramMap);

    /**
     * 查询活动商品
     * 
     * @param paramMap
     *            查询条件
     * @return Long {@link java.lang.Long}
     */
    Long selectActivityGoodsNum(Map<String, Object> paramMap);

    /**
     * 查询未读的咨询数量
     * 
     * @param paramMap
     *            查询条件
     * @return Long {@link java.lang.Long}
     */
    Long selectNoReadNum(Map<String, Object> paramMap);

    /**
     * 取消订单
     * 
     * @param paramMap
     *            查询条件
     * @return 0失败 1 成功
     */
    int cancelOrder(Map<String, Object> paramMap);

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
     * 修改昵称
     * 
     * @param allInfo
     *            {@link com.ningpai.site.customer.vo.CustomerAllInfo}
     * @return 0失败 1成功
     */
    int modifyCustNickName(CustomerAllInfo allInfo);

    /**
     * 删除订单
     * 
     * @param orderId
     *            订单编号
     * @return 0失败 1 成功
     */
    int delOrder(Long orderId);

    /**
     * 查询验证码和失效时间
     * 
     * @param customerId
     *            会员编号
     * @return
     */
    Customer selectCaptcha(Long customerId);

    /**
     * 修改验证码
     * 
     * @param customer
     * @return
     */
    int updateSmsCaptcha(Customer customer);

    /**
     * 
     * 修改会员详细信息
     * 
     * @return Integer {@link java.lang.Integer}
     */
    int updateCustInfoByPrimaryKeySelective(CustomerAllInfo customerInfo);

    /**
     * 检查用户是否存在
     * 
     * @param paramMap
     *            参数Map {@link Map}
     * @return 0 不存在 1 存在 {@link Long}
     */
    Long checkExistsByCustNameAndType(Map<String, Object> paramMap);

    /**
     * 根据会员名和密码验证用户
     * 
     * @param paramMap
     *            验证条件
     * @return null 密码错误 Customer 验证成功
     */
    Customer selectCustomerByNamePwdAndType(Map<String, Object> paramMap);

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
     * 根据订单 会员编号查找订单信息
     * 
     * @param paramMap
     *            查询编号
     * @return OrderInfoBean
     */
    Object queryOrderByParamMap(Map<String, Object> paramMap);
    
    /**
     * 验证会员是否存在某订单
     * @param paramMap
     * @return
     */
    Long checkexistsByIdAndCode(Map<String, Object> paramMap);

    /**
     * 查询可以投诉的订单
     * @auther zhangsl
     * @param paramMap 存放参数
     * @return 返回列表
     */
    public List<Object> queryordersforcomplain(Map<String, Object> paramMap);

    /**
     * 查询可以投诉的订单总数
     *  @auther zhangsl
     * @param paramMap 存放参数
     * @return 返回个数
     */
    Long queryCountForComplain(Map<String, Object> paramMap);

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
     * @param map pointLevelId 等级id
     * @param map pointLevelName  等级名称
     * @param map customerId  客户id
     * @return 修改结果
     * @author houyichang 2015/9/25
     * */
    int updCustLevel(Map<String,Object> map);
  }
