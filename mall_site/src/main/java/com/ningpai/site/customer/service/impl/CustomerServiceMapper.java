/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.site.customer.service.impl;

import com.ningpai.common.bean.Sms;
import com.ningpai.common.dao.SmsMapper;
import com.ningpai.common.util.ConstantUtil;
import com.ningpai.common.util.GenerateLinkUtils;
import com.ningpai.common.util.SmsPost;
import com.ningpai.coupon.service.CouponService;
import com.ningpai.customer.bean.Customer;
import com.ningpai.customer.bean.CustomerAddress;
import com.ningpai.customer.bean.CustomerPoint;
import com.ningpai.customer.dao.CustomerInfoMapper;
import com.ningpai.customer.dao.InsideLetterMapper;
import com.ningpai.customer.service.CustomerPointServiceMapper;
import com.ningpai.goods.vo.GoodsProductVo;
import com.ningpai.order.bean.BackOrder;
import com.ningpai.order.bean.Order;
import com.ningpai.order.dao.BackOrderMapper;
import com.ningpai.order.dao.OrderMapper;
import com.ningpai.site.customer.mapper.CustomerFollowMapper;
import com.ningpai.site.customer.mapper.CustomerMapper;
import com.ningpai.site.customer.mapper.CustomerMapperSite;
import com.ningpai.site.customer.service.CustomerServiceInterface;
import com.ningpai.site.customer.vo.*;
import com.ningpai.site.order.bean.ExchangeCusmomerPoint;
import com.ningpai.system.bean.SystemsSet;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author NINGPAI-zhangqiang
 * @version 0.0.1
 * @since 2014年1月13日 下午5:43:32
 */
@Service("customerServiceSite")
public class CustomerServiceMapper implements CustomerServiceInterface {

    public static final MyLogger LOGGER = new MyLogger(CustomerServiceMapper.class);

    private static final String CUSTOMERID = "customerId";

    /**
     * 会员Mapper
     */
    private CustomerMapper customerMapper;

    /**
     * 会员详细信息接口
     */
    private CustomerInfoMapper customerInfoMapper;

    /**
     * 会员积分接口
     */
    private CustomerPointServiceMapper customerPointServiceMapper;

    /**
     * 订单接口
     */
    private OrderMapper orderMapper;

    /**
     * 优惠券接口
     */
    private CouponService couponService;

    /**
     * 邮箱服务器数据接口层
     */
    private SmsMapper mapper;

    /**
     * 会员MapperSite
     */
    private CustomerMapperSite customerMapperSite;

    /**
     * spring注解站内信息
     */
    @Resource(name = "insideletter")
    private InsideLetterMapper insideletter;

    @Resource(name = "BackOrderMapper")
    private BackOrderMapper backOrderMapper;
    /**
     * 会员收藏Mapper
     */
    @Resource(name = "customerFollowMapperSite")
    private CustomerFollowMapper customerFollowMapper;

    /**
     * 按照主键编号查找
     * 
     * @param customerId
     *            会员编号
     * @return
     */
    public Customer queryCustomerById(Long customerId) {
        // 按照主键编号查找
        return customerMapper.queryCustomerById(customerId);
    }

    /**
     * 查询可以投诉的订单
     *
     * @param paramMap
     *            条件查询参数
     * @param pb
     *            分页参数
     * @return
     * @auther zhangsl
     */
    @Override
    public PageBean queryOrdersForComplain(Map<String, Object> paramMap, PageBean pb) {
        // 查询可以投诉的订单总数
        Long count = customerMapper.queryCountForComplain(paramMap);
        pb.setRows(Integer.parseInt(count == null ? "0" : count.toString()));
        if (pb.getPageNo() > pb.getLastPageNo()) {
            pb.setPageNo(pb.getLastPageNo());
        }
        if (pb.getPageNo() == 0) {
            pb.setPageNo(1);
        }
        paramMap.put(CustomerConstantStr.STARTNUM, pb.getStartRowNum());
        paramMap.put(CustomerConstantStr.ENDNUM, pb.getEndRowNum());
        // 查询可以投诉的订单
        pb.setList(customerMapper.queryordersforcomplain(paramMap));
        return pb;
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
        return this.customerMapper.querySumByCustId(customerId);
    }

    /**
     * 根据客户id修改客户等级
     *
     * @param pointLevelId   等级id
     * @param pointLevelName 等级名称
     * @param customerId     客户id
     * @return 修改结果
     * @author houyichang 2015/9/25
     */
    @Override
    public int updCustLevel(Long pointLevelId, String pointLevelName, Long customerId) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("pointLevelId",pointLevelId);
        map.put("pointLevelName",pointLevelName);
        map.put("customerId",customerId);
        return this.customerMapper.updCustLevel(map);
    }

    /**
     * 查询会员基本信息
     * 
     * @param customerId
     *            会员编号
     * @return
     */
    @Override
    public CustomerAllInfo selectByPrimaryKey(Long customerId) {
        return customerMapper.selectByPrimaryKey(customerId);
    }

    /**
     * 验证邮箱有效
     * 
     * @param request
     * @param d
     * @param checkCode
     * @return
     */
    @Override
    public int verifyCheckCode(HttpServletRequest request, Long d, String checkCode) {
        GenerateLinkUtils.md5(String.valueOf(new Date().getTime()));
        // 按照主键编号查找
        Customer customer = customerMapper.selectByPrimaryKey((Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID));

        if (!"1".equals(customer.getIsEmail())) {
            // 根据类型添加积分
            customerPointServiceMapper.addIntegralByType((Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID), "2");
        }

        Customer cust = new Customer();
        cust.setCustomerId(d);
        cust.setIsEmail("1");
        cust.setCustomerId((Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID));

        // 按条件修改会员信息
        customerMapper.updateByPrimaryKeySelective(cust);

        return 0;
    }

    /**
     * 根据会员编号查找订单信息
     * 
     * @param paramMap
     *            查询条件
     * @param pb
     * @return
     */
    public PageBean queryMyOrders(Map<String, Object> paramMap, PageBean pb) {
        // 按条件查询所有订单数量
        Long count = customerMapper.searchTotalCountO(paramMap);
        pb.setRows(Integer.parseInt(count == null ? "0" : count.toString()));
        paramMap.put(CustomerConstantStr.STARTNUM, pb.getStartRowNum());
        paramMap.put(CustomerConstantStr.ENDNUM, pb.getEndRowNum());
        // 根据会员编号 和 订单状态 查找订单信息
        pb.setList(customerMapper.queryMyOrders(paramMap));
        return pb;
    }

    /**
     * 查询所有订单
     * 
     * @param paramMap
     *            查询订单条件
     * @param pb
     * @return
     */
    @Override
    public PageBean queryAllMyOrders(Map<String, Object> paramMap, PageBean pb) {
        // 查询所有订单数量
        Long count = customerMapper.searchTotalCount(paramMap);
        pb.setRows(Integer.parseInt(count == null ? "0" : count.toString()));
        if (pb.getPageNo() > pb.getLastPageNo()) {
            pb.setPageNo(pb.getLastPageNo());
        }
        if (pb.getPageNo() == 0) {
            pb.setPageNo(1);
        }
        paramMap.put(CustomerConstantStr.STARTNUM, pb.getStartRowNum());
        paramMap.put(CustomerConstantStr.ENDNUM, pb.getEndRowNum());
        // 查询所有订单
        pb.setList(customerMapper.queryAllMyOrders(paramMap));
        return pb;
    }

    /**
     * 查询当前会员的退单信息
     * 
     * @param paramMap
     *            查询订单条件
     * @param pb
     * @return
     */
    @Override
    public PageBean queryAllBackMyOrders(Map<String, Object> paramMap, PageBean pb) {
        // 退单的数据条数
        Long count = customerMapper.searchTotalCountBack(paramMap);
        pb.setRows(Integer.parseInt(count == null ? "0" : count.toString()));
        if (pb.getPageNo() > pb.getLastPageNo()) {
            pb.setPageNo(pb.getLastPageNo());
        }
        if (pb.getPageNo() == 0) {
            pb.setPageNo(1);
        }
        paramMap.put(CustomerConstantStr.STARTNUM, pb.getStartRowNum());
        paramMap.put(CustomerConstantStr.ENDNUM, pb.getEndRowNum());
        // 查询该会员下面的
        List<Object> backOrders = customerMapper.queryAllMyBackOrders(paramMap);
        if (null != backOrders && !backOrders.isEmpty()) {
            for (int i = 0; i < backOrders.size(); i++) {
                BackOrder bo = (BackOrder) backOrders.get(i);
                if (!"".equals(bo.getBackGoodsIdAndSum())) {
                    // 获取退单对象 下面的退单的 商品Id
                    String[] strs = bo.getBackGoodsIdAndSum().split("-");
                    // 处理数据
                    if (strs.length > 0) {
                        // 遍历退单对象下面的商品Id
                        for (int j = 0; j < strs.length; j++) {
                            String strss = strs[j];
                            // 获取第J个商品的Id
                            Long goodsId = Long.valueOf(strss.substring(0, strss.indexOf(",")));
                            // 根据ID获取单个商品的详细信息
                            GoodsProductVo orderProductVo = backOrderMapper.selectGoodsById(goodsId);
                            // 循环把查询获取到的商品信息放入到退单对象的商品集合中
                            bo.getOrderGoodslistVo().add(orderProductVo);
                        }
                    }
                }
            }
            pb.setList(backOrders);
        }
        return pb;
    }

    /**
     * 根据订单编号查找订单信息
     * 
     * @param orderId
     *            订单编号
     * @param customerId
     * @return
     */
    @Override
    public Object queryOrderByCustIdAndOrderId(Long orderId, Long customerId) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        try {
            paramMap.put(CUSTOMERID, customerId);
            paramMap.put(ConstantUtil.ORDERID, orderId);
            // 根据订单 会员编号查找订单信息
            return customerMapper.queryOrderByParamMap(paramMap);
        } finally {
            paramMap = null;
        }
    }

    /**
     * 根据订单编号查找订单收货地址
     * 
     * @param orderId
     *            订单编号
     * @return
     */
    @Override
    public Object queryCustomerAddressById(Long orderId) {
        // 根据订单编号查找订单收货地址
        return customerMapper.queryCustomerAddressById(orderId);
    }

    /**
     * 根据会员编号查找会员详细信息
     * 
     * @param parseLong
     *            会员编号
     * @return
     */
    @Override
    public Object queryCustomerInfoById(long parseLong) {
        // 根据会员编号查找会员详细信息
        return customerMapper.queryCustomerInfoById(parseLong);
    }

    /**
     * 修改会员信息
     * 
     * @param allInfo
     *            新信息对象
     * @param flag
     * @return
     */
    @Override
    public int modifyCustomerInfo(CustomerAllInfo allInfo, String flag) {
        if ("1".equals(flag)) {
            // 修改昵称
            customerMapper.modifyCustNickName(allInfo);
        }
        // 修改会员信息
        return customerMapper.modifyCustomerInfo(allInfo);
    }

    /**
     * 确认收货
     * 
     * @param orderId
     *            订单编号 {@link java.lang.Long}
     * @return
     */
    @Override
    public Integer comfirmOfGooods(Long orderId) {
        // 修改收货状态
        Integer result = customerMapper.comfirmOfGooods(orderId);
            // 成功后修改订单状态为已完成
        if (result == 1) {
            result += customerMapper.modifyOrderStatusToSuccess(orderId);
        }
        return result;
    }

    /**
     * 查询所有省份 用于添加会员页面填充省份
     * 
     * @return
     */
    @Override
    public List<ProvinceBean> selectAllProvince() {
        // 查询所有省份 用于添加会员页面填充省份
        return customerMapper.selectAllProvince();
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
        // 根据省份编号 查询所有城市
        return customerMapper.selectAllCityByPid(provinceId);
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
        // 根据城市编号 查询所有区县
        return customerMapper.selectAllDistrictByCid(cityId);
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
        // 按区县编号获取对应街道集合
        return customerMapper.getAllStreetByDid(dId);
    }

    /**
     * 根据会员编号查找会员详细信息
     * 
     * @param parseLong
     *            会员编号
     * @return
     */
    @Override
    public Object queryCustomerByCustomerId(long parseLong) {
        // 根据会员编号查找会员详细信息
        return customerMapper.queryCustomerByCustomerId(parseLong);
    }

    /**
     * 验证用户密码
     * 
     * @param customerId
     *            会员编号
     * @param password
     *            当前输入密码
     * @return
     */
    @Override
    public int checkCustomerPassword(Long customerId, String password) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put(CustomerConstantStr.CUSTOMERID, customerId);
            map.put("password", password);
            // 检查用户输入的密码和原密码是否相同 相投返回1 不同返回0
            if (null != customerMapper.checkCustomerPwd(map)) {
                return 1;
            }
            return 0;
        } finally {
            map = null;
        }
    }

    /**
     * 修改用户密码
     * 
     * @param allInfo
     *            用户信息
     * @return
     */
    @Override
    public int updateByPrimaryKey(CustomerAllInfo allInfo) {
        // 修改用户密码
        return customerMapper.updateByPrimaryKeySelective(allInfo);
    }

    /**
     * 修改会员详细信息
     * 
     * @param user
     * @return
     */
    @Override
    public int updateCustInfoByPrimaryKey(CustomerAllInfo user) {
        // 修改会员详细信息
        return customerMapper.updateCustInfoByPrimaryKeySelective(user);
    }

    /**
     * 根据用户编号查找用户的收货地址
     * 
     * @param customerId
     *            用户编号
     * @return
     */
    @Override
    public CustomerAllInfo queryAddressByCustomerId(Long customerId) {
        // 根据用户编号查找用户的收货地址
        return customerMapper.queryAddressByCustomerId(customerId);
    }

    /**
     * 根据用户编号查找用户的收货地址
     *
     * @param pb
     *            分页条件
     * @param customerId
     *            用户编号
     * @return PageBean
     */
    @Override
    public PageBean queryAddressByCustomerId(Long customerId, PageBean pb) {
        // 封装查询条件
        Map<String, Object> map = new HashMap<>();
        // 根据用户编号查找用户的收货地址的数量
        Long count = customerMapper.queryAddressCountByCustomerId(customerId);
        pb.setRows(Integer.parseInt(count == null ? "0" : count.toString()));
        if (pb.getPageNo() > pb.getLastPageNo()) {
            pb.setPageNo(pb.getLastPageNo());
        }
        if (pb.getPageNo() == 0) {
            pb.setPageNo(1);
        }
        map.put(CUSTOMERID, customerId);
        map.put(CustomerConstantStr.STARTNUM, pb.getStartRowNum());
        map.put(CustomerConstantStr.ENDNUM, pb.getEndRowNum());
        // 根据用户编号查找用户的收货地址
        pb.setList(customerMapper.queryAddressByCustomerId(map));
        return pb;
    }

    /**
     * 添加收货地址
     * 
     * @param address
     *            地址信息
     * @return
     */
    @Override
    public int addCustomerAddress(CustomerAddress address) {
        // 添加收货地址
        return customerMapper.addCustomerAddress(address);
    }

    /**
     * 删除收货地址
     * 
     * @param addressId
     *            地址编号
     * @return
     */
    @Override
    public int deleteCustAddress(Long addressId) {
        // 删除收货地址
        return customerMapper.deleteCustAddress(addressId);
    }

    /**
     * 根据地址编号查找收货地址
     * 
     * @param addressId
     *            地址编号
     * @return
     */
    @Override
    public CustomerAddress queryCustAddress(Long addressId) {
        // 根据地址编号查找收货地址
        return customerMapper.queryCustAddress(addressId);
    }

    /**
     * 修改会员收货地址
     * 
     * @param address
     *            新地址信息
     * @return
     */
    @Override
    public int modifyCustAddress(CustomerAddress address) {
        // 修改会员收货地址
        return customerMapper.modifyCustAddress(address);
    }

    /**
     * 修改默认地址 将之前的默认地址改为非默认 并且将当前地址改为默认地址
     * 
     * @param request
     * @param customerId
     *            会员编号
     * @param addressId
     *            地址编号 要设置为默认的编号
     * @return
     */
    @Override
    public int modifyIsDefaultAddress(HttpServletRequest request, String customerId, String addressId) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        try {
            // 取消当前用户的所有默认地址
            if (customerMapper.cancelDefaultAddress(customerId) >= 1) {
                // 根据地址编号查找收货地址
                CustomerAddress address = customerMapper.queryCustAddress(Long.parseLong(addressId));
                if (address != null) {
                    // 设置收货地址
                    // 区id
                    request.getSession().setAttribute("distinctId", address.getDistrict().getDistrictId());
                    // 省
                    request.getSession().setAttribute("chProvince", address.getProvince().getProvinceName());
                    // 市
                    request.getSession().setAttribute("chCity", address.getCity().getCityName());
                    // 地址
                    request.getSession().setAttribute("chDistinct", address.getDistrict().getDistrictName());
                }
                // 封装参数
                paramMap.put("addressId", addressId);
                paramMap.put(CustomerConstantStr.CUSTOMERID, customerId);
                // 设置新的默认地址
                customerMapper.setDefaultAddress(paramMap);
                return 1;
            }
        } finally {
            paramMap = null;
        }
        return 0;
    }

    /**
     * 根据会员编号查询相应的会员积分明细
     * 
     * @param paramMap
     *            查询条件
     * @param pb
     * @return
     */
    @Override
    public PageBean selectAllCustomerPoint(Map<String, Object> paramMap, PageBean pb) {
        pb.setRows(Integer.parseInt(customerMapper.queryPointRcCount(paramMap) + ""));
        if (pb.getPageNo() > pb.getLastPageNo()) {
            pb.setPageNo(pb.getLastPageNo());
        }
        if (pb.getPageNo() == 0) {
            pb.setPageNo(1);
        }
        paramMap.put(CustomerConstantStr.STARTNUM, pb.getStartRowNum());
        paramMap.put(CustomerConstantStr.ENDNUM, pb.getEndRowNum());
        // 查询所有积分记录
        pb.setList(customerMapper.queryAllPointRc(paramMap));
        return pb;
    }

    /**
     * 根据会员编号查询相应的总会员积分
     * 
     * @param customerId
     *            会员编号
     * @return
     */
    @Override
    public Long selectTotalPointByCid(Long customerId) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        try {
            paramMap.put(CustomerConstantStr.CUSTOMERID, customerId);
            // 查询积分总和
            return customerMapper.selectTotalPointByCid(paramMap);
        } finally {
            paramMap = null;
        }
    }

    /**
     * 根据会员编号查询相应的会员收藏
     * 
     * @param paramMap
     *            查询条件
     * @param pb
     * @return
     */
    @Override
    public PageBean selectAllCustomerFollow(Map<String, Object> paramMap, PageBean pb) {
        pb.setRows(Integer.parseInt(customerMapper.queryFollowRcCount(paramMap) + ""));
        paramMap.put(CustomerConstantStr.STARTNUM, pb.getStartRowNum());
        paramMap.put(CustomerConstantStr.ENDNUM, pb.getEndRowNum());
        // 查询所有收藏记录
        pb.setList(customerMapper.queryAllFollowRc(paramMap));
        return pb;
    }

    /**
     * 查询通知内容数量
     * 
     * @param customerId
     *            会员编号
     * @return
     */
    @Override
    public Map<String, Object> selectNotice(Long customerId) {

        Map<String, Object> resultMap = new HashMap<String, Object>();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        try {
            paramMap.put(CustomerConstantStr.CUSTOMERID, customerId);
            // 待付款订单数量
            paramMap.put("flag", 3);
            resultMap.put("onpayNum", customerMapper.selectpendingOrderNotice(paramMap));
            // 待收货 订单数量
            paramMap.put("flag", 4);
            resultMap.put("onReceiptNum", customerMapper.selectpendingOrderNotice(paramMap));
            // 待自提订单数量
            paramMap.put("flag", 5);
            resultMap.put("onMyNum", customerMapper.selectpendingOrderNotice(paramMap));
            // 待处理订单数量
            paramMap.put("flag", 0);
            resultMap.put("pendingNum", customerMapper.selectpendingOrderNotice(paramMap));
            // 待评论订单数量
            paramMap.put("flag", 1);
            // 查询待评论的订单
            resultMap.put("commentNum", customerMapper.selectpendingOrderNotice(paramMap));
            // 查询降价通知数量
            resultMap.put("reduceNum", customerMapper.selectReducePriceNum(paramMap));
            // 查询到货通知的数量
            resultMap.put("goodsArriveNum", customerMapper.selectGoodsArriveNum(paramMap));
            // 查询活动商品
            //Long num = customerMapper.selectActivityGoodsNum(paramMap);
            // 查询收藏总数
            Long count = 0L;
            if (customerId != null) {
                count = customerFollowMapper.selectCustomerFollowCount(customerId);
            }
            resultMap.put("activityGoodsNum", count);
            // 查询未读的咨询数量
            resultMap.put("noReadNum", customerMapper.selectNoReadNum(paramMap));
            // 查询为查看的信息数量
            resultMap.put("noReadInsideNum", insideletter.findInsideCount(customerId));
            return resultMap;
        } finally {
            resultMap = null;
            paramMap = null;
        }
    }

    /**
     * 取消订单
     * 
     * @param orderId
     *            订单Id
     * @param reason
     * @return
     */
    @Override
    public int cancelOrder(Long orderId, String reason) {
        // 执行返回的结果
        int result = 0;
        Map<String, Object> paramMap = new HashMap<String, Object>();
        try {
            paramMap.put(ConstantUtil.ORDERID, orderId);
            paramMap.put("reason", reason);
            // 取消订单
            result = customerMapper.cancelOrder(paramMap);
            if (result == 1) {
                // 退还用户的积分 根据id获取订单对象
                Order order = orderMapper.orderDetail(orderId);
                // 根据订单单号 查询积分兑换对象
                ExchangeCusmomerPoint cusmomerPoint = customerMapper.selectExchangeByOrderCode(order.getOrderCode());
                // 判断是否有积分兑换
                if (null != cusmomerPoint) {
                    // 更新会员的积分
                    CustomerPoint customerPoint = customerMapper.selectCustomerPointByCustomerId(cusmomerPoint.getCustomerId());
                    customerPoint.setPointSum(customerPoint.getPointSum() + cusmomerPoint.getExchangePoint());
                    // 修改指定会员总积分
                    result = couponService.updateCustomerPoint(customerPoint);
                }

            }
            return result;
        } finally {
            paramMap = null;
        }

    }

    /**
     * 删除订单
     * 
     * @param orderId
     *            订单Id
     * @return
     */
    @Override
    public int delOrder(Long orderId) {
        // 删除订单
        return customerMapper.delOrder(orderId);
    }

    /**
     * 修改密码 邮箱 手机
     * 
     * @param request
     * @param newStr
     *            新字段
     * @param type
     *            pwd email mobile
     * @return
     */
    @Override
    public int modifyPem(HttpServletRequest request, String newStr, String type) {
        CustomerAllInfo cust = new CustomerAllInfo();
        cust.setCustomerId((Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID));
        if ("pwd".equals(type)) {
            cust.setCustomerPassword(newStr);
            // 按条件修改会员信息
            return customerMapper.updateByPrimaryKeySelective(cust);
        } else if ("mobile".equals(type)) {
            cust.setIsMobile("1");
            cust.setInfoMobile(newStr);
        } else if ("email".equals(type)) {
            // cust.setIsEmail("1");
            cust.setInfoMobile(newStr);
        }
        // 按条件修改会员信息
        customerMapper.updateByPrimaryKeySelective(cust);
        // 修改会员信息
        return customerMapper.modifyCustomerInfo(cust);
    }

    /**
     * 发送邮件
     * 
     * @param request
     * @param email
     *            邮件地址
     * @return
     */
    @Override
    public int sendEamil(HttpServletRequest request, String email) {
        return 0;
    }

    /**
     * 发送手机验证码
     * 
     * @param request
     * @param moblie
     *            目标手机
     * @return
     */
    @Override
    public int sendPost(HttpServletRequest request, String moblie) {
        Customer customer = null;
        // 查询短信信息
        Sms sms = mapper.selectSms();
        if (sms == null) {
            return 0;
        }
        Object cId = request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID);
        if (cId == null) {
            cId = request.getSession().getAttribute("uId");
        }
        Object custEmp = null;
        // 查询验证码和失效时间
        customer = customerMapper.selectCaptcha((Long) cId);
        if ((System.currentTimeMillis() - customer.getAeadTime().getTime()) > (60 * 1000)) {

            sms.setSendSim(moblie);
            int num = (int) ((Math.random() * 9 + 1) * 100000);
            // 手机验证码
            request.getSession().setAttribute("codeMobileNum", num);
            sms.setMsgContext(((Integer) num).toString());
            custEmp = request.getSession().getAttribute("cust");
            if (custEmp == null) {
                custEmp = request.getSession().getAttribute("user");
            }
            customer = (Customer) custEmp;
            customer.setCaptcha(((Integer) num).toString());
            customer.setAeadTime(new Date());
            try {
                // 短信发送
                if (SmsPost.sendPost(sms,"")) {
                    // 修改验证码
                    customerMapper.updateSmsCaptcha(customer);
                    return 1;
                }
                return 0;
            } catch (IOException e) {
                LOGGER.error("",e);
                return 0;
            }
        }
        return -1;
    }

    /**
     * 修改找回密码Code
     * 
     * @param user
     * @return
     */
    @Override
    public int updateFindPwdCode(CustomerAllInfo user) {
        // 修改验证码
        return customerMapper.updateSmsCaptcha(user);
    }

    /**
     * 验证手机验证码
     * 
     * @param request
     * @param code
     * @return
     */
    @Override
    public int getMCode(HttpServletRequest request, String code) {
        Object cId = request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID);
        if (cId == null) {
            cId = request.getSession().getAttribute("uId");
        }
        // 查询验证码和失效时间
        Customer customer = customerMapper.selectCaptcha((Long) cId);
        if ((System.currentTimeMillis() - customer.getAeadTime().getTime()) > 30 * 60 * 1000) {
            return -1;
        }
        if (code.equals(customer.getCaptcha())) {
            customer.setAeadTime(new Date(customer.getAeadTime().getTime() - (11 * 60 * 1000)));
            customer.setCaptcha(null);
            customer.setCustomerId((Long) cId);
            // 修改验证码
            customerMapper.updateSmsCaptcha(customer);
            // 按照主键编号查找 基本信息
            Customer cust = customerMapper.selectByPrimaryKey((Long) cId);
            request.getSession().setAttribute("cFlag", 1);

            if (!"1".equals(cust.getIsMobile()) && request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID) != null) {
                // 根据类型添加积分
                customerPointServiceMapper.addIntegralByType((Long) cId, "3");
            }
            return 1;
        }
        return 0;
    }

    /**
     * 验证邮件
     * 
     * @param request
     * @param checkCode
     * @param d
     * @return
     */
    @Override
    public int validPwdEmail(HttpServletRequest request, String checkCode, Long d) {
        // 查询验证码和失效时间
        Customer customer = customerMapper.selectCaptcha(d);
        if (customer != null && customer.getPwdCaptcha() != null) {
            if ((System.currentTimeMillis() - customer.getPwdAeadTime().getTime()) > 120 * 60 * 1000) {
                return -1;
            }
            if (checkCode.equals(customer.getPwdCaptcha())) {
                CustomerAllInfo cust = this.selectCustomerByUname(customer.getCustomerUsername());
                request.getSession().setAttribute("user", cust);
                request.getSession().setAttribute("uId", cust.getCustomerId());
                customer.setPwdAeadTime(new Date(customer.getPwdAeadTime().getTime() - (120 * 60 * 1000)));
                customer.setPwdCaptcha("");
                customer.setCustomerId(d);
                // 修改验证码
                customerMapper.updateSmsCaptcha(customer);
                request.getSession().setAttribute("cFlag", 1);
                return 1;
            }
            return -2;
        } else {
            return -2;
        }
    }

    /**
     * 确认收货
     * 
     * @param orderId
     *            订单编号
     * @return
     */
    @Override
    public int comfirmofGoods(Long orderId) {
        // 确认收货
        return customerMapper.comfirmOfGooods(orderId);
    }

    /**
     * 检查用户名存在性
     * 
     * @param username
     *            用户名
     * @return
     */
    @Override
    public Long checkUsernameFlag(String username) {
        // 检查用户名存在性
        return customerMapper.checkexistsByCustName(username);
    }

    /**
     * 根据用户名查询用户简单信息
     * 
     * @param username
     *            用户名
     * @return
     */
    @Override
    public CustomerAllInfo selectCustomerByUname(String username) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        try {
            paramMap.put("username", username);
            // 根据用户名查询用户简单信息
            return customerMapperSite.selectCustomerByUname(paramMap);
        } finally {
            paramMap = null;
        }
    }

    /**
     * 验证邮箱存在性
     * 
     * @param email
     *            目标邮箱 {@link String}
     * @return
     */
    @Override
    public Long checkEmailExist(String email) {
        // 验证邮箱存在性
        return customerMapper.checkEmailExist(email);
    }

    /**
     * 验证手机存在性
     * 
     * @param mobile
     *            目标手机 {@link String}
     * @return
     */
    @Override
    public Long checkMobileExist(String mobile) {
        // 验证手机存在性
        return customerMapper.checkMobileExist(mobile);
    }

    public CustomerMapperSite getCustomerMapperSite() {
        return customerMapperSite;
    }

    @Resource(name = "customerMapperFindPwd")
    public void setCustomerMapperSite(CustomerMapperSite customerMapperSite) {
        this.customerMapperSite = customerMapperSite;
    }

    public SmsMapper getMapper() {
        return mapper;
    }

    @Resource(name = "smsMapperSite")
    public void setMapper(SmsMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * 获取customerMaper
     *
     * @return CustomerMapper
     */
    public CustomerMapper getCustomerMapper() {
        return customerMapper;
    }

    /**
     * 设置属性 spring注入
     *
     * @param customerMapper
     */
    @Resource(name = "customerMapperSite")
    public void setCustomerMapper(CustomerMapper customerMapper) {
        this.customerMapper = customerMapper;
    }

    public CustomerInfoMapper getCustomerInfoMapper() {
        return customerInfoMapper;
    }

    @Resource(name = "customerInfoMapper")
    public void setCustomerInfoMapper(CustomerInfoMapper customerInfoMapper) {
        this.customerInfoMapper = customerInfoMapper;
    }

    public CustomerPointServiceMapper getCustomerPointServiceMapper() {
        return customerPointServiceMapper;
    }

    @Resource(name = "customerPointServiceMapper")
    public void setCustomerPointServiceMapper(CustomerPointServiceMapper customerPointServiceMapper) {
        this.customerPointServiceMapper = customerPointServiceMapper;
    }

    public OrderMapper getOrderMapper() {
        return orderMapper;
    }

    @Resource(name = "OrderMapper")
    public void setOrderMapper(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    public CouponService getCouponService() {
        return couponService;
    }

    @Resource(name = "CouponService")
    public void setCouponService(CouponService couponService) {
        this.couponService = couponService;
    }

    @Override
    public DistrictBean selectDistrictBeanById(Long did) {
        return customerMapper.selectDistrictBeanById(did);
    }

    @Override
    public ProvinceBean selectprovinceByPid(Long provinceId) {
        return customerMapper.selectprovinceByPid(provinceId);
    }

    @Override
    public SystemsSet getIsBackOrder() {
        return customerMapper.getIsBackOrder();
    }

    /**
     * 验证会员是否存在某订单
     * 
     * @param customerId
     * @param orderCode
     * @return
     */
    @Override
    public Long checkexistsByIdAndCode(Long customerId, String orderCode) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(CUSTOMERID, customerId);
        paramMap.put("orderCode", orderCode);
        // 验证会员是否存在某订单
        return customerMapper.checkexistsByIdAndCode(paramMap);
    }
}
