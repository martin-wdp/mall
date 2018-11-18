/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.site.customer.controller;

import com.ningpai.customer.bean.CustomerAddress;
import com.ningpai.freighttemplate.bean.SysCity;
import com.ningpai.freighttemplate.service.SysDistrictService;
import com.ningpai.index.service.TopAndBottomService;
import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.site.customer.service.CustomerServiceInterface;
import com.ningpai.site.customer.vo.*;
import com.ningpai.system.service.BasicSetService;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * 会员中心控制类
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年1月13日 下午1:05:18
 * @version 0.0.1
 */
@Controller
public class AddressSiteController {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(AddressSiteController.class);

    private static final String ADDRESSURL = "customer/address.html";
    private static final String LOGIN = "/login";
    private static final String INDEX = "index.html";
    private static final String LOGGERINFO1 = "新添加的地址收货人为【";
    private static final String LOGGERINFO2 = "新增收货地址";

    /**
     * 站点设置服务层接口
     */
    @Resource(name = "basicSetService")
    private BasicSetService basicSetService;

    /**
     * 获取头尾
     */
    private TopAndBottomService topAndBottomService;

    private SysDistrictService sysDistrictService;

    /**
     * 会员服务接口
     */
    private CustomerServiceInterface customerServiceInterface;

    /**
     * 查询收货地址
     * 
     * @return 跳转地址页面
     */
    @RequestMapping("/queryCustomerAddress")
    public ModelAndView queryCustomerAddress(HttpServletRequest request, PageBean pb) {
        ModelAndView mav = new ModelAndView();
        pb.setUrl("customer/address.html");
        try {
            // 验证用户是否已经登录
            if (checkLoginStatus(request)) {
                // 获取当前登录的用户的ID
                Long customerId = getLoginUserId(request);
                // 根据主键获取会员详细信息
                CustomerAllInfo customerAllInfo = customerServiceInterface.queryAddressByCustomerId(customerId);
                // 非空验证 用户名
                if (null != customerAllInfo.getCustomerUsername()) {
                    LOGGER.info("查询会员【" + customerAllInfo.getCustomerUsername() + "】收货地址");
                }
                // 收货地址
                mav.addObject("pb", customerServiceInterface.queryAddressByCustomerId(customerId, pb));
                mav.addObject(CustomerConstantStr.CUSTOMER, customerAllInfo);
                // 跳转 收货地址页面
                mav.addObject("basicSet", basicSetService.findBasicSet());
                mav.setViewName("customer/newaddress");
            } else {
                // 没登录的用户跳转到登录页面
                mav = new ModelAndView(CustomerConstantStr.LOGINREDIRECT)
                        .addObject(CustomerConstantStr.URL, CustomerConstantStr.CUSTOMERS + CustomerConstantStr.CUSTOMERADDRESSHTM);
            }
            return topAndBottomService.getTopAndBottom(mav);
        } finally {
            mav = null;
        }
    }

    /**
     * 根据前台所传编号查找会员地址
     * 
     * @return 跳转地址页面
     */
    @RequestMapping("/queryCustAddressByCustId")
    @ResponseBody
    public CustomerAllInfo queryCustAddressByCustId(String customerId, HttpServletRequest request) {
        // 验证用户是否已经登录
        if (checkLoginStatus(request)  && getLoginUserId(request).equals(Long.valueOf(customerId))){
                // 根据主键获取会员详细信息
                CustomerAllInfo customerAllInfo = customerServiceInterface.queryAddressByCustomerId(Long.valueOf(customerId));
                // 非空验证 用户名
                if (null != customerAllInfo.getCustomerUsername()) {
                    LOGGER.info("查询会员【" + customerAllInfo.getCustomerUsername() + "】收货地址");
                }
                return customerAllInfo;
        }
        return null;
    }

    /**
     * 添加会员收货地址
     * 
     * @param address
     *            地址信息
     * @return ModelAndView
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("/addCustomerAddress")
    public ModelAndView addCustomerAddress(@Valid CustomerAddress address, BindingResult bindingResult, HttpServletRequest request) throws UnsupportedEncodingException {
        ModelAndView mav = new ModelAndView();
        if (bindingResult.hasErrors()) {
            return new ModelAndView(new RedirectView(request.getContextPath() + "/" + INDEX));
        }
        try {
            // 验证用户是否已经登录
            if (checkLoginStatus(request)) {
                // 获取当前登录的用户的ID
                Long customerId = getLoginUserId(request);
                address.setCustomerId(customerId);
                // 添加地址
                customerServiceInterface.addCustomerAddress(address);
                // 根据主键获取会员详细信息
                CustomerAllInfo customerAllInfo = customerServiceInterface.queryAddressByCustomerId(customerId);
                // 非空验证 会员用户名
                if (null != customerAllInfo.getCustomerUsername()) {
                    // 操作日志
                    OperaLogUtil.addOperaLog(request, customerAllInfo.getCustomerUsername(), LOGGERINFO2, LOGGERINFO1 + address.getAddressName() + "】");
                    // 记录日志
                    LOGGER.info("会员【" + customerAllInfo.getCustomerUsername() + "】新增收货信息成功！");
                }
                // 跳转 收货地址页面
                mav.setView(new RedirectView(request.getContextPath() + "/" + ADDRESSURL));
            } else {
                // 没登录的用户跳转到登录页面
                mav = new ModelAndView(new RedirectView(request.getContextPath() + LOGIN));
            }
            return topAndBottomService.getTopAndBottom(mav);
        } finally {
            mav = null;
        }
    }

    /**
     * Ajax添加会员收货地址
     * 
     * @author NINGPAI-ykk
     * @param address
     *            地址信息
     * @return ModelAndView
     * @throws UnsupportedEncodingException
     */
    @ResponseBody
    @RequestMapping("/ajaxAddCustomerAddress")
    public boolean ajaxAddCustomerAddress(@Valid CustomerAddress address, BindingResult bindingResult, HttpServletRequest request) throws UnsupportedEncodingException {
        if (bindingResult.hasErrors()) {
            return false;
        }
        if (checkLoginStatus(request)) {
            Long customerId = getLoginUserId(request);
            // 获取当前登录的用户的ID
            address.setCustomerId(customerId);
            address.setAddressName(new String(address.getAddressName().getBytes(CustomerConstantStr.ISO), CustomerConstantStr.UTF));
            address.setAddressDetail(new String(address.getAddressDetail().getBytes(CustomerConstantStr.ISO), CustomerConstantStr.UTF));
            if (null != address.getAddressAlias()) {
                address.setAddressAlias(new String(address.getAddressAlias().getBytes(CustomerConstantStr.ISO), CustomerConstantStr.UTF));
            }
            // 添加地址
            int n = customerServiceInterface.addCustomerAddress(address);
            // 根据主键获取会员详细信息
            CustomerAllInfo customerAllInfo = customerServiceInterface.queryAddressByCustomerId(customerId);
            // 非空验证 会员用户名
            if (null != customerAllInfo.getCustomerUsername()) {
                // 操作日志
                OperaLogUtil.addOperaLog(request, customerAllInfo.getCustomerUsername(), LOGGERINFO2, LOGGERINFO1 + address.getAddressName() + "】");
                LOGGER.info("会员【" + customerAllInfo.getCustomerUsername() + "】新增收货信息成功！");
            }
           return n > 0;
        } else {
            return false;
        }
    }

    /**
     * Ajax添加会员收货地址From subOrder
     * 
     * @param address
     *            地址信息
     * @return ModelAndView
     * @throws UnsupportedEncodingException
     */
    @ResponseBody
    @RequestMapping("/ajaxAddCustomerAddressFromOrder")
    public boolean ajaxAddCustomerAddressFromOrder(@Valid CustomerAddress address, BindingResult bindingResult, HttpServletRequest request) throws UnsupportedEncodingException {
        if (bindingResult.hasErrors()) {
            return false;
        }
        if (checkLoginStatus(request)) {
            // 获取当前登录的用户的ID
            Long customerId = getLoginUserId(request);
            address.setCustomerId(customerId);
            // 添加地址
            int n = customerServiceInterface.addCustomerAddress(address);
            // 根据主键获取会员详细信息
            CustomerAllInfo customerAllInfo = customerServiceInterface.queryAddressByCustomerId(customerId);
            // 非空验证 会员用户名
            if (null != customerAllInfo.getCustomerUsername()) {
                // 操作日志
                OperaLogUtil.addOperaLog(request, customerAllInfo.getCustomerUsername(), LOGGERINFO2, LOGGERINFO1 + address.getAddressName() + "】");
                LOGGER.info("会员【" + customerAllInfo.getCustomerUsername() + "】保存地址成功！");
            }
            return n > 0;
        } else {
            return false;
        }
    }

    /**
     * 删除收货地址
     * 
     * @param addressId
     *            地址编号
     * @return ModelAndView
     */
    @RequestMapping("/deleteCustAddress")
    public ModelAndView deleteCustAddress(HttpServletRequest request, Long addressId) {
        ModelAndView mav = new ModelAndView();
        try {
            // 验证用户是否已经登录
            if (checkLoginStatus(request)) {
                // 根据地址编号查找收货地址
                CustomerAddress address = customerServiceInterface.queryCustAddress(addressId);
                // 判断要删除的地址是否是当前用户的
                if (address != null && getLoginUserId(request).equals(address.getCustomerId())) {
                    // 根据编号删除收货地址
                    customerServiceInterface.deleteCustAddress(addressId);
                    // 当前登陆的会员
                    Long customerId = getLoginUserId(request);
                    // 根据主键获取会员详细信息
                    CustomerAllInfo customerAllInfo = customerServiceInterface.queryAddressByCustomerId(customerId);
                    // 非空验证 会员用户名 地址收货人
                    if (null != customerAllInfo.getCustomerUsername() && null != address.getAddressName()) {
                        // 操作日志
                        OperaLogUtil.addOperaLog(request, customerAllInfo.getCustomerUsername(), "删除收货地址", "删除的地址收货人为【" + address.getAddressName() + "】");
                    }
                }
                // 跳转 收货地址页面
                mav.setView(new RedirectView(request.getContextPath() + "/" + ADDRESSURL));
                // } else {
                // // 不是当前用户的跳转到登录页面
                // mav = new ModelAndView(new
                // RedirectView(request.getContextPath() + LOGIN));
                // }

            } else {
                // 没登录的用户跳转到登录页面
                mav = new ModelAndView(new RedirectView(request.getContextPath() + LOGIN));
            }
            return topAndBottomService.getTopAndBottom(mav);
        } finally {
            mav = null;
        }
    }

    /**
     * 查找收货地址 用于ajax查询 填充到修改form
     * 
     * @param addressId
     *            地址编号
     * @return CustomerAddress
     */
    @RequestMapping("/queryCustAddress")
    @ResponseBody
    public CustomerAddress queryCustAddress(Long addressId, HttpServletRequest request) {
        // 验证用户是否已经登录
        CustomerAddress address = customerServiceInterface.queryCustAddress(addressId);
        if (checkLoginStatus(request)) {
            // 获取当前登录的用户的ID
            Long customerId = getLoginUserId(request);
            if (customerId.equals(address.getCustomerId())) {

                return address;
            }
        }
        return null;
    }

    /**
     * 根据登陆的会员编号查找会员地址
     * 
     * @author NINGPAI-ykk
     * @return 跳转地址页面
     */
    @RequestMapping("/queryCustAddressForSubOrder")
    @ResponseBody
    public CustomerAllInfo queryCustAddressByCustId(HttpServletRequest request) {
        // 查询指定会员的收货地址
        CustomerAllInfo customerAllInfo = customerServiceInterface.queryAddressByCustomerId((Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID));
        if (customerAllInfo != null) {
            // 隐藏用户密码
            customerAllInfo.setCustomerPassword(null);
            // 隐藏用户信箱
            customerAllInfo.setInfoEmail(null);
            // 隐藏用户手机号码
            customerAllInfo.setInfoMobile(null);
            // 非空验证 用户名
            if (null != customerAllInfo.getCustomerUsername()) {
                LOGGER.info("获取会员【" + customerAllInfo.getCustomerUsername() + "】的地址");
            }
        }
        // 根据编号查找会员地址 全部
        return customerAllInfo;
    }

    /**
     * 修改收货地址
     * 
     * @param address
     *            地址信息
     * @return ModelAndView
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/modifyCustAddress")
    public ModelAndView modifyCustAddress(HttpServletRequest request, @Valid CustomerAddress address, BindingResult bindingResult) throws UnsupportedEncodingException {
        ModelAndView mav = new ModelAndView();
        if (bindingResult.hasErrors()) {
            return new ModelAndView(new RedirectView(request.getContextPath() + "/" + INDEX));
        }
        try {
            // 验证用户是否已经登录
            if (checkLoginStatus(request)) {
                // 判断要删除的地址是否是当前用户的
                if (getLoginUserId(request).equals(address.getCustomerId())) {
                    customerServiceInterface.modifyCustAddress(address);
                    // 跳转 收货地址页面
                    mav.setView(new RedirectView(request.getContextPath() + "/" + ADDRESSURL));
                } else {
                    // 不是当前用户的跳转到登录页面
                    mav = new ModelAndView(new RedirectView(request.getContextPath() + LOGIN));
                }
            } else {
                // 没登录的用户跳转到登录页面
                mav = new ModelAndView(new RedirectView(request.getContextPath() + LOGIN));
            }
            // 当前登陆的会员
            Long customerId = getLoginUserId(request);
            // 根据主键获取会员详细信息
            CustomerAllInfo customerAllInfo = customerServiceInterface.queryAddressByCustomerId(customerId);
            // 非空验证 会员用户名 地址收货人
            if (null != customerAllInfo.getCustomerUsername() && null != address.getAddressName()) {
                // 操作日志
                OperaLogUtil.addOperaLog(request, customerAllInfo.getCustomerUsername(), "修改收货地址", "更新的地址收货人为【" + address.getAddressName() + "】");
            }
            return topAndBottomService.getTopAndBottom(mav);
        } finally {
            mav = null;
        }
    }

    /**
     * 修改默认地址 将之前的默认地址改为非默认 并且将当前地址改为默认地址<br/>
     * 确认订单选择地址时调用把地址设置为默认地址
     */
    @ResponseBody
    @RequestMapping("/modifyIsDefaultAddressForOrder")
    public void modifyIsDefaultAddressForOrder(HttpServletRequest request, String addressId) {
        // 获取当前登录的用户的ID
        Long customerId = getLoginUserId(request);
        // 修改默认地址 将之前的默认地址改为非默认 并且将当前地址改为默认地址
        customerServiceInterface.modifyIsDefaultAddress(request, customerId.toString(), addressId);
    }

    /**
     * 修改默认地址 将之前的默认地址改为非默认 并且将当前地址改为默认地址
     * 
     * @return ModelAndView
     */
    @RequestMapping("/modifyIsDefaultAddress")
    public ModelAndView modifyIsDefaultAddress(HttpServletRequest request, String customerId, String addressId) {
        ModelAndView mav = new ModelAndView();
        try {
            // 验证用户是否已经登录
            if (checkLoginStatus(request)) {
                // 根据地址编号查找收货地址
                CustomerAddress address = customerServiceInterface.queryCustAddress(Long.valueOf(addressId));
                // 判断要删除的地址是否是当前用户的
                if (getLoginUserId(request).equals(address.getCustomerId())) {
                    // 修改默认地址 将之前的默认地址改为非默认 并且将当前地址改为默认地址
                    customerServiceInterface.modifyIsDefaultAddress(request, customerId, addressId);
                    // 跳转 收货地址页面
                    mav.setView(new RedirectView(request.getContextPath() + "/" + ADDRESSURL));
                } else {
                    // 不是当前用户的跳转到登录页面
                    mav = new ModelAndView(new RedirectView(request.getContextPath() + LOGIN));
                }
            } else {
                // 没登录的用户跳转到登录页面
                mav = new ModelAndView(new RedirectView(request.getContextPath() + LOGIN));
            }
            return topAndBottomService.getTopAndBottom(mav);
        } finally {
            mav = null;
        }
    }

    /**
     * 查询所有省份
     * 
     * @return List<ProvinceBean> 省份集合
     * @see {@link com.ningpai.other.bean.ProvinceBean}
     */
    @RequestMapping("/getSiteAllProvince")
    @ResponseBody
    public List<ProvinceBean> getAllProvince() {
        // 查询所有省份 用于添加会员页面填充省份
        return customerServiceInterface.selectAllProvince();
    }

    /**
     * 根据省份编号 查询所有城市
     * 
     * @return List<CityBean> 城市集合
     * @see {@link com.ningpai.other.bean.CityBean}
     */
    @RequestMapping("/getSiteAllCityByPid")
    @ResponseBody
    public List<CityBean> getAllCityByPid(Long provinceId) {
        // 根据主键 获取单个的省份信息
        ProvinceBean province = customerServiceInterface.selectprovinceByPid(provinceId);
        // 省份名称 非空验证
        if (null != province.getProvinceName()) {
            LOGGER.info("查询【" + province.getProvinceName() + "】省下面所对应的城市！");
        }
        // 根据省份编号 查询所有城市
        return customerServiceInterface.selectAllCityByPid(provinceId);
    }

    /**
     * 根据城市编号 查询所有区县
     * 
     * @return List<CityBean> 区县集合
     * @see {@link com.ningpai.other.bean.DistrictBean}
     */
    @RequestMapping("/getSiteAllDistrictByCid")
    @ResponseBody
    public List<DistrictBean> getAllDistrictByCid(Long cityId) {
        // 非空验证城市id
        if (null != cityId) {
            // 获取指定城市下面的所有区县ID
            SysCity sysCity = sysDistrictService.selectCityById(cityId);
            if (null != sysCity.getCityName()) {
                LOGGER.info("获取【" + sysCity.getCityName() + "】下面所有的区县");
            }
        }
        // 根据城市编号 查询所有区县
        return customerServiceInterface.selectAllDistrictByCid(cityId);
    }

    /**
     * 根据区县编号 查询所有街道信息
     * 
     * @return List<StreetBean> 街道集合
     * @see {@link com.ningpai.other.bean.StreetBean}
     */
    @RequestMapping("/getSiteAllStreetByDid")
    @ResponseBody
    public List<StreetBean> getAllStreetByDid(Long dId) {
        // 获取单个的区县对象
        DistrictBean districtBean = customerServiceInterface.selectDistrictBeanById(dId);
        // 非空验证 区县名称
        if (null != districtBean.getDistrictName()) {
            LOGGER.info("获取【" + districtBean.getDistrictName() + "】下面所有的街道信息");
        }
        // 按区县编号获取对应街道集合
        return customerServiceInterface.getAllStreetByDid(dId);
    }

    // 验证用户是否登录
    private boolean checkLoginStatus(HttpServletRequest request) {
        if (request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID) == null) {
            return false;
        }
        return true;
    }

    /**
     * 获取当前登录的用户的ID
     * 
     * @param request
     * @return
     */
    private Long getLoginUserId(HttpServletRequest request) {
        return (Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID);
    }

    public TopAndBottomService getTopAndBottomService() {
        return topAndBottomService;
    }

    @Resource(name = "TopAndBottomService")
    public void setTopAndBottomService(TopAndBottomService topAndBottomService) {
        this.topAndBottomService = topAndBottomService;
    }

    public SysDistrictService getSysDistrictService() {
        return sysDistrictService;
    }

    /**
     * 获取单个城市对象 根据主键
     */
    @Resource(name = "SysDistrictService")
    public void setSysDistrictService(SysDistrictService sysDistrictService) {
        this.sysDistrictService = sysDistrictService;
    }

    public CustomerServiceInterface getCustomerServiceInterface() {
        return customerServiceInterface;
    }

    @Resource(name = "customerServiceSite")
    public void setCustomerServiceInterface(CustomerServiceInterface customerServiceInterface) {
        this.customerServiceInterface = customerServiceInterface;
    }

}
