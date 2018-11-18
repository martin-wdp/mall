/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.site.customer.mapper.impl;

import com.ningpai.customer.bean.Customer;
import com.ningpai.customer.bean.CustomerAddress;
import com.ningpai.customer.bean.CustomerPoint;
import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.site.customer.mapper.CustomerMapper;
import com.ningpai.site.customer.vo.*;
import com.ningpai.site.order.bean.ExchangeCusmomerPoint;
import com.ningpai.system.bean.SystemsSet;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @see
 * @author NINGPAI-zhangqiang
 * @since 2014年1月13日 下午1:25:04
 * @version 0.0.1
 */
@Repository("customerMapperSite")
public class CustomerMapperImpl extends BasicSqlSupport implements
        CustomerMapper {
    /**
     * 删除
     * @param customerId
     *            会员编号
     * @return
     */
    public int deleteByPrimaryKey(Long customerId) {
        return 0;
    }

    /**
     * 插入
     * @param record
     *            要插入的数据的对象
     * @return
     */
    public int insertSelective(Customer record) {
        return 0;
    }


    /**
     * 查询会员信息
     * @param customerId
     *            会员主键编号
     * @return
     */
    public CustomerAllInfo selectByPrimaryKey(Long customerId) {
        return this
                .selectOne(
                        "com.ningpai.site.customer.dao.CustomerMapper.selectByPrimaryKey",
                        customerId);
    }

    /**
     * 修改验证码
     * @param customer
     * @return
     */
    @Override
    public int updateSmsCaptcha(Customer customer) {
        return this.update(
                "com.ningpai.customer.dao.CustomerMapper.updateSmsCaptcha",
                customer);
    }

    /**
     * 按照主键编号查找
     * @param customerId
     *            会员主键编号
     * @return
     */
    public Customer queryCustomerById(Long customerId) {
        return this
                .selectOne(
                        "com.ningpai.site.customer.dao.CustomerMapper.queryCustomerById",
                        customerId);
    }

    /**
     * 按条件修改会员信息
     * @param record
     *            要修改的会员对象
     * @return
     */
    public int updateByPrimaryKeySelective(Customer record) {
        return this
                .update("com.ningpai.site.customer.dao.CustomerMapper.updateByPrimaryKeySelective",
                        record);
    }

    /**
     * 根据主键编号修改会员信息
     * @param record
     *            要修改的会员对象
     * @return
     */
    public int updateByPrimaryKey(Customer record) {
        return 0;
    }

    /**
     * 根据会员编号 和 订单状态 查找订单信息
     * @param paramMap
     *            Map<String,Long>
     * @return
     */
    public List<Object> queryMyOrders(Map<String, Object> paramMap) {
        return this
                .selectList(
                        "com.ningpai.site.customer.dao.CustomerMapper.selectOrderByCustomerIdAndStatus",
                        paramMap);
    }


    /**
     * 查询所有订单
     * @param paramMap
     *            查询订单条件
     * @return
     */
    public List<Object> queryAllMyOrders(Map<String, Object> paramMap) {
        return this
                .selectList(
                        "com.ningpai.site.customer.dao.CustomerMapper.selectOrderByCustomerIdAndStatus",
                        paramMap);
    }

    /**
     * 根据订单编号查找订单信息
     * @param orderId
     *            订单编号
     * @return
     */
    public Object queryOrderByOrderId(Long orderId) {
        return this
                .selectOne(
                        "com.ningpai.site.customer.dao.CustomerMapper.queryOrderByOrderId",
                        orderId);
    }

    /**
     * 根据订单编号查找订单收货地址
     * @param orderId
     *            订单编号
     * @return
     */
    public Object queryCustomerAddressById(Long orderId) {
        return this
                .selectOne(
                        "com.ningpai.site.customer.dao.CustomerAddressMapper.queryCustomerAddressByOrderId",
                        orderId);
    }

    /**
     * 根据会员编号查找会员详细信息
     * @param parseLong
     *            会员编号
     * @return
     */
    public Object queryCustomerInfoById(long parseLong) {
        return this
                .selectOne(
                        "com.ningpai.customer.dao.CustomerInfoMapper.queryCustomerInfoById",
                        parseLong);
    }

    /**
     * 修改会员信息
     * @param allInfo
     *            新信息对象
     * @return
     */
    public int modifyCustomerInfo(CustomerAllInfo allInfo) {
        return this
                .update("com.ningpai.site.customer.dao.CustomerMapper.modifyCustomerInfo",
                        allInfo);
    }

    /**
     * 确认收货
     * @param orderId
     *            订单编号 {@link java.lang.Long}
     * @return
     */
    public int comfirmOfGooods(Long orderId) {
        return this.update(
                "com.ningpai.site.customer.dao.CustomerMapper.comfirmOfGooods",
                orderId);
    }

    /**
     * 修改订单 订单完成
     * @param orderId
     *            订单编号
     * @return
     */
    public Integer modifyOrderStatusToSuccess(Long orderId) {
        return this
                .update("com.ningpai.site.customer.dao.CustomerMapper.modifyOrderStatusToSuccess",
                        orderId);
    }

    /**
     * 查询所有省份 用于添加会员页面填充省份
     * @return
     */
    public List<ProvinceBean> selectAllProvince() {
        return this
                .selectList("com.ningpai.site.customer.dao.CustomerMapper.selectAllProvince");
    }

    /**
     * 根据省份编号 查询所有城市
     * @param provinceId
     *            省份编号 java.lang.Long {@link java.lang.Long}
     * @return
     */
    public List<CityBean> selectAllCityByPid(Long provinceId) {
        return this
                .selectList(
                        "com.ningpai.site.customer.dao.CustomerMapper.selectAllCityByPid",
                        provinceId);
    }

    /**
     * 根据城市编号 查询所有区县
     * @param cityId
     *            城市编号 java.lang.Long {@link java.lang.Long}
     * @return
     */
    public List<DistrictBean> selectAllDistrictByCid(Long cityId) {
        return this
                .selectList(
                        "com.ningpai.site.customer.dao.CustomerMapper.selectAllDistrictByCid",
                        cityId);
    }

    /**
     * 按区县编号获取对应街道集合
     * @param dId
     *            区县编号 java.lang.Long {@link java.lang.Long}
     * @return
     */
    public List<StreetBean> getAllStreetByDid(Long dId) {
        return this
                .selectList(
                        "com.ningpai.site.customer.dao.CustomerMapper.getAllStreetByDid",
                        dId);
    }

    /**
     * 根据会员编号查找会员详细信息
     * @param parseLong
     *            会员编号
     * @return
     */
    public Object queryCustomerByCustomerId(long parseLong) {
        return this
                .selectOne(
                        "com.ningpai.site.customer.dao.CustomerMapper.selectByPrimaryKey",
                        parseLong);
    }

    /**
     * 根据用户编号查找用户的收货地址
     * @param customerId
     *            用户编号
     * @return
     */
    public CustomerAllInfo queryAddressByCustomerId(Long customerId) {
        return this
                .selectOne(
                        "com.ningpai.site.customer.dao.CustomerMapper.queryAddressByCustomerId",
                        customerId);
    }

    /**
     * 根据用户编号查找用户的收货地址
     * @see com.ningpai.site.customer.mapper.CustomerMapper#queryAddressByCustomerId(java.util.Map)
     */
    @Override
    public List<Object> queryAddressByCustomerId(Map<String, Object> map) {
        return this
                .selectList(
                        "com.ningpai.site.customer.dao.CustomerAddressMapper.queryAddressByCustomerId",
                        map);
    }

    /**
     * 根据用户编号查找用户的收货地址的数量
     *
     * @param custId
     *            用户编号
     * @return Long 用户收货地址的数量
     */
    @Override
    public Long queryAddressCountByCustomerId(Long custId) {
        return this
                .selectOne(
                        "com.ningpai.site.customer.dao.CustomerAddressMapper.queryAddressCountByCustomerId",
                        custId);
    }

    /**
     * 添加收货地址
     * @param address
     *            地址信息
     * @return
     */
    public int addCustomerAddress(CustomerAddress address) {
        return this
                .insert("com.ningpai.site.customer.dao.CustomerAddressMapper.insertSelective",
                        address);
    }

    /**
     * 删除收货地址
     * @param addressId
     *            地址编号
     * @return
     */
    public int deleteCustAddress(Long addressId) {
        return this
                .delete("com.ningpai.site.customer.dao.CustomerAddressMapper.deleteCustAddress",
                        addressId);
    }

    /**
     * 根据地址编号查找收货地址
     * @param addressId
     *            地址编号
     * @return
     */
    public CustomerAddress queryCustAddress(Long addressId) {
        return this
                .selectOne(
                        "com.ningpai.site.customer.dao.CustomerAddressMapper.selectByPrimaryKey",
                        addressId);
    }

    /**
     * 修改会员收货地址
     * @param address
     *            新地址信息
     * @return
     */
    public int modifyCustAddress(CustomerAddress address) {
        return this
                .update("com.ningpai.site.customer.dao.CustomerAddressMapper.updateByPrimaryKeySelective",
                        address);
    }

    /**
     * 取消当前用户的默认地址
     * @param customerId
     *            用户编号
     * @return
     */
    public int cancelDefaultAddress(String customerId) {
        return this
                .update("com.ningpai.site.customer.dao.CustomerAddressMapper.cancelDefaultAddress",
                        customerId);
    }

    /**
     * 修改默认地址 将当前地址改为默认地址
     * @param paramMap
     *             查询订单条件
     *
     * @return
     */
    public int setDefaultAddress(Map<String, Object> paramMap) {
        return this
                .update("com.ningpai.site.customer.dao.CustomerAddressMapper.setDefaultAddress",
                        paramMap);
    }

    /**
     * 查询所有订单数量
     * @param paramMap
     *            查询订单条件
     * @return
     */
    public Long searchTotalCount(Map<String, Object> paramMap) {
        return this
                .selectOne(
                        "com.ningpai.site.customer.dao.CustomerMapper.searchTotalCount",
                        paramMap);
    }

    /**
     * 按条件查询所有订单数量
     * @param paramMap
     *            查询订单条件
     * @return
     */
    public Long searchTotalCountO(Map<String, Object> paramMap) {
        return this
                .selectOne(
                        "com.ningpai.site.customer.dao.CustomerMapper.searchTotalCountO",
                        paramMap);
    }

    /**
     * 根据用户ID和用户密码查询密码用户
     * @param map
     *            参数
     * @return
     */
    @Override
    public Customer checkCustomerPwd(Map<String, Object> map) {
        return this
                .selectOne(
                        "com.ningpai.site.customer.dao.CustomerMapper.checkCustomerPwd",
                        map);
    }

    /**
     * 查询积分记录总数
     * @param paramMap
     *            查询条件
     * @return
     */
    @Override
    public Long queryPointRcCount(Map<String, Object> paramMap) {
        return this
                .selectOne(
                        "com.ningpai.site.customer.dao.CustomerPointMapper.queryPointRcCount",
                        paramMap);
    }

    /**
     * 查询所有积分记录
     * @param paramMap
     *            查询条件
     * @return
     */
    @Override
    public List<Object> queryAllPointRc(Map<String, Object> paramMap) {
        return this
                .selectList(
                        "com.ningpai.site.customer.dao.CustomerPointMapper.queryAllPointRc",
                        paramMap);
    }

    /**
     * 查询积分总和
     * @param paramMap
     *            查询条件
     * @return
     */
    @Override
    public Long selectTotalPointByCid(Map<String, Object> paramMap) {
        return this
                .selectOne(
                        "com.ningpai.site.customer.dao.CustomerPointMapper.selectTotalPointByCid",
                        paramMap);
    }

    /**
     * 收藏总数
     * @param paramMap
     *            查询条件
     * @return
     */
    @Override
    public Long queryFollowRcCount(Map<String, Object> paramMap) {
        return this
                .selectOne(
                        "com.ningpai.site.customer.dao.CustomerFollowMapper.queryFollowRcCount",
                        paramMap);
    }

    /**
     * 查询所有收藏记录
     * @param paramMap
     *            查询条件
     * @return
     */
    @Override
    public List<Object> queryAllFollowRc(Map<String, Object> paramMap) {
        return this
                .selectList(
                        "com.ningpai.site.customer.dao.CustomerFollowMapper.queryAllFollowRc",
                        paramMap);
    }

    /**
     * 查询待评论的订单
     * @param paramMap
     *            查询条件
     * @return
     */
    @Override
    public Long selectpendingOrderNotice(Map<String, Object> paramMap) {
        return this
                .selectOne(
                        "com.ningpai.site.customer.dao.CustomerMapper.selectpendingOrderNotice",
                        paramMap);
    }

    /**
     * 查询降价通知数量
     * 
     * @see
     * com.ningpai.site.customer.mapper.CustomerMapper#selectReducePriceNum(
     * java.util.Map)
     */
    @Override
    public Long selectReducePriceNum(Map<String, Object> paramMap) {
        return this
                .selectOne(
                        "com.ningpai.site.customer.dao.CustomerMapper.selectReducePriceNum",
                        paramMap);
    }

    /**
     * 查询到货通知的数量
     * @see
     * com.ningpai.site.customer.mapper.CustomerMapper#selectGoodsArriveNum(
     * java.util.Map)
     */
    @Override
    public Long selectGoodsArriveNum(Map<String, Object> paramMap) {
        return this
                .selectOne(
                        "com.ningpai.site.customer.dao.CustomerMapper.selectGoodsArriveNum",
                        paramMap);
    }

    /**
     * 查询活动商品
     * 
     * @see
     * com.ningpai.site.customer.mapper.CustomerMapper#selectActivityGoodsNum
     * (java.util.Map)
     */
    @Override
    public Long selectActivityGoodsNum(Map<String, Object> paramMap) {
        return this
                .selectOne(
                        "com.ningpai.site.customer.dao.CustomerMapper.selectActivityGoodsNum",
                        paramMap);
    }

    /**
     * 查询未读的咨询数量
     * @param paramMap
     *            查询条件
     * @return
     */
    @Override
    public Long selectNoReadNum(Map<String, Object> paramMap) {
        return this.selectOne(
                "com.ningpai.site.customer.dao.CustomerMapper.selectNoReadNum",
                paramMap);
    }

    /**
     * 取消订单
     * @param paramMap
     *            查询条件
     * @return
     */
    @Override
    public int cancelOrder(Map<String, Object> paramMap) {
        return this.update(
                "com.ningpai.site.customer.dao.CustomerMapper.cancelOrder",
                paramMap);
    }

    /**
     * 删除订单
     * 
     * @see
     * com.ningpai.site.customer.mapper.CustomerMapper#delOrder(java.lang.Long)
     */
    @Override
    public int delOrder(Long orderId) {
        return this.update(
                "com.ningpai.site.customer.dao.CustomerMapper.delOrder",
                orderId);
    }

    /**
     * 按照会员名查找会员
     * 
     * @see
     * com.ningpai.site.customer.mapper.CustomerMapper#checkexistsByCustName
     * (java.lang.String)
     */
    @Override
    public Long checkexistsByCustName(String username) {
        return this
                .selectOne(
                        "com.ningpai.site.customer.dao.CustomerMapper.checkexistsByCustName",
                        username);
    }

    /**
     * 检查用户是否存在
     * 
     * @see
     * com.ningpai.site.customer.mapper.CustomerMapper#checkExistsByCustNameAndType
     * (java.util.Map)
     */
    @Override
    public Long checkExistsByCustNameAndType(Map<String, Object> paramMap) {
        return this
                .selectOne(
                        "com.ningpai.site.customer.dao.CustomerMapper.checkExistsByCustNameAndType",
                        paramMap);
    }

    /**
     * 根据会员名和密码验证用户
     * 
     * @see
     * com.ningpai.site.customer.mapper.CustomerMapper#selectCustomerByNamePwd
     * (java.util.Map)
     */
    @Override
    public Customer selectCustomerByNamePwd(Map<String, Object> paramMap) {
        return this
                .selectOne(
                        "com.ningpai.site.customer.dao.CustomerMapper.selectCustomerByNamePwd",
                        paramMap);
    }

    /**
     * 根据会员名和密码验证用户
     * 
     * @see com.ningpai.site.customer.mapper.CustomerMapper#
     * selectCustomerByNamePwdAndType(java.util.Map)
     */
    @Override
    public Customer selectCustomerByNamePwdAndType(Map<String, Object> paramMap) {
        return this
                .selectOne(
                        "com.ningpai.site.customer.dao.CustomerMapper.selectCustomerByNamePwdAndType",
                        paramMap);
    }

    /**
     * 修改昵称
     * @param allInfo
     *            {@link com.ningpai.site.customer.vo.CustomerAllInfo}
     * @return
     */
    @Override
    public int modifyCustNickName(CustomerAllInfo allInfo) {
        return this
                .update("com.ningpai.site.customer.dao.CustomerMapper.modifyCustNickName",
                        allInfo);
    }

    /**
     * 查询验证码和失效时间
     * @param customerId
     *            会员编号
     * @return
     */
    @Override
    public Customer selectCaptcha(Long customerId) {
        return this.selectOne(
                "com.ningpai.customer.dao.CustomerMapper.selectCaptcha",
                customerId);
    }

    /**
     * 修改会员详细信息
     * 
     * @see com.ningpai.site.customer.mapper.CustomerMapper#
     * updateCustInfoByPrimaryKeySelective
     * (com.ningpai.site.customer.vo.CustomerAllInfo)
     */
    @Override
    public int updateCustInfoByPrimaryKeySelective(CustomerAllInfo customerInfo) {
        return this
                .update("com.ningpai.site.customer.dao.CustomerMapper.updateCustInfoByPrimaryKeySelective",
                        customerInfo);
    }

    /**
     * 验证邮箱存在性
     * @param email
     *            目标邮箱 {@link String}
     * @return
     */
    @Override
    public Long checkEmailExist(String email) {
        return this.selectOne(
                "com.ningpai.customer.dao.CustomerMapper.checkEmailExist",
                email);
    }

    /**
     * 验证手机存在性
     * @param mobile
     *            目标手机 {@link String}
     * @return
     */
    @Override
    public Long checkMobileExist(String mobile) {
        return this.selectOne(
                "com.ningpai.customer.dao.CustomerMapper.checkMobileExist",
                mobile);
    }

    /**
     * 根据订单 会员编号查找订单信息
     * 
     * @see
     * com.ningpai.site.customer.mapper.CustomerMapper#queryOrderByParamMap(
     * java.util.Map)
     */
    @Override
    public Object queryOrderByParamMap(Map<String, Object> paramMap) {
        return this
                .selectOne(
                        "com.ningpai.site.customer.dao.CustomerMapper.queryOrderByCustIdAndOid",
                        paramMap);
    }

    
    /**
     * 是否允许退单
     * @see com.ningpai.site.customer.mapper.CustomerMapper#getIsBackOrder()
     */
    @Override
    public SystemsSet getIsBackOrder() {
        return this
                .selectOne("com.ningpai.system.dao.AdvertisementMapper.getIsBackOrder");
    }

    /**
     * 根据订单号获取积分兑换对象
     * @see com.ningpai.site.customer.mapper.CustomerMapper#selectExchangeByOrderCode(java.lang.String)
     */
    @Override
    public ExchangeCusmomerPoint selectExchangeByOrderCode(String orderCode) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("orderCode", orderCode);
        return this
                .selectOne(
                        "com.ningpaihsite.goods.dao.GoodsProductMapper.selectExchangeByOrderCode",
                        map);
    }
    /**
     * 查询该会员下面的 所有退单信息
     * @see com.ningpai.site.customer.mapper.CustomerMapper#queryAllMyBackOrders(java.util.Map)
     */
    @Override
    public List<Object> queryAllMyBackOrders(Map<String, Object> paramMap) {
        return this
                .selectList(
                        "com.ningpai.site.returns.ReturnGoodsMapper.queryAllMyBackOrders",
                        paramMap);
    }
    /**
     * 获取当前会员 退单的数据条数 zhanghl
     * @see com.ningpai.site.customer.mapper.CustomerMapper#searchTotalCountBack(java.util.Map)
     */
    @Override
    public Long searchTotalCountBack(Map<String, Object> map) {
        return this
                .selectOne(
                        "com.ningpai.site.returns.ReturnGoodsMapper.searchTotalCountBack",
                        map);
    }

    /**
     * 根据ID获取单个区县对象
     * @see com.ningpai.site.customer.mapper.CustomerMapper#selectDistrictBeanById(java.lang.Long)
     */
    @Override
    public DistrictBean selectDistrictBeanById(Long districtId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("districtId", districtId);
        return this
                .selectOne(
                        "com.ningpai.site.customer.dao.CustomerMapper.selectDistrictBeanById",
                        map);
    }

    /**
     * 根据主键获取单个的省份信息
     * @see com.ningpai.site.customer.mapper.CustomerMapper#selectprovinceByPid(java.lang.Long)
     */
    @Override
    public ProvinceBean selectprovinceByPid(Long provinceId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("provinceId", provinceId);
        return this
                .selectOne(
                        "com.ningpai.site.customer.dao.CustomerMapper.selectprovinceByPid",
                        map);
    }

    /**
     * 根据会员ID获取会员对象
     * @param customerId
     * @return
     */
    @Override
    public CustomerPoint selectCustomerPointByCustomerId(Long customerId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("customerId", customerId);
        return this
                .selectOne(
                        "com.ningpai.web.dao.CouponMapper.selectCustomerPointByCustomerId",
                        map);
    }

    /**
     * 验证会员是否存在某订单
     * @see
     * com.ningpai.site.customer.mapper.CustomerMapper#checkexistsByIdAndCode
     * (java.util.Map)
     */
    @Override
    public Long checkexistsByIdAndCode(Map<String, Object> paramMap) {
        return this
                .selectOne(
                        "com.ningpai.site.customer.dao.CustomerMapper.checkexistsByIdAndCode",
                        paramMap);
    }

    /**
     * 查询可以投诉的订单
     *
     * @param paramMap
     *            存放参数
     * @return 返回列表
     * @auther zhangsl
     */
    @Override
    public List<Object> queryordersforcomplain(Map<String, Object> paramMap) {
        return this
                .selectList(
                        "com.ningpai.site.customer.dao.CustomerMapper.selectOrdersForComplain",
                        paramMap);
    }

    /**
     * 查询可以投诉的订单总数
     *
     * @param paramMap
     *            存放参数
     * @return 返回个数
     * @auther zhangsl
     */
    @Override
    public Long queryCountForComplain(Map<String, Object> paramMap) {
        return this
                .selectOne(
                        "com.ningpai.site.customer.dao.CustomerMapper.queryCountForComplain",
                        paramMap);
    }

    /**
     * 查询用户所有获得积分总和
     *
     * @param customerId 用户Id
     * @return 该用户所有获得的积分总和
     * @author houyichang 2015/9/25
     */
    @Override
    public int querySumByCustId(Long customerId) {
        Integer point=this.selectOne("com.ningpai.site.customer.dao.CustomerPointMapper.selectPointSum",customerId);
        return point==null?0:point;
    }

    /**
     * 根据客户id修改客户等级
     *
     * @param map pointLevelId 等级id
     * @return 修改结果
     * @author houyichang 2015/9/25
     */
    @Override
    public int updCustLevel(Map<String, Object> map) {
        return this.update("com.ningpai.site.customer.dao.CustomerMapper.updCustLevel",map);
    }


}
