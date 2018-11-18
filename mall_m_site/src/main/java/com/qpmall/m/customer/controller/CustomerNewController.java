package com.qpmall.m.customer.controller;

import com.ningpai.common.util.ConstantUtil;
import com.ningpai.coupon.service.CouponService;
import com.ningpai.customer.bean.Customer;
import com.ningpai.customer.bean.CustomerAddress;
import com.ningpai.customer.dao.CustomerFollowMapper;
import com.ningpai.customer.service.CustomerFollowServiceMapper;
import com.ningpai.customer.service.CustomerPointServiceMapper;
import com.ningpai.customer.service.CustomerServiceMapper;
import com.ningpai.freighttemplate.service.SysProvinceService;
import com.ningpai.goods.dao.GoodsProductMapper;
import com.ningpai.goods.service.ProductWareService;
import com.ningpai.m.common.service.SeoService;
import com.ningpai.m.customer.service.BrowserecordService;
import com.ningpai.m.customer.service.CustomerAddressService;
import com.ningpai.m.customer.service.CustomerOrderService;
import com.ningpai.m.customer.service.CustomerService;
import com.ningpai.m.customer.vo.CustomerConstants;
import com.ningpai.m.login.service.LoginService;
import com.ningpai.m.util.LoginUtil;
import com.ningpai.other.util.IPAddress;
import com.ningpai.system.service.DefaultAddressService;
import com.qpmall.address.service.SimpleGetAddressService;
import com.qpmall.m.customer.service.EnterpriseCertificationInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 手机端会员控制层（新16年 qpmall 年初）
 * Created by ly-qpmall on 2016/1/18.
 */
@Controller
public class CustomerNewController {
    // spring 注解 会员service
    @Resource(name = "customerServiceM")
    private CustomerService customerService;

    @Resource(name = "SeoService")
    private SeoService seoService;
    @Resource(name = "customerFollowServiceMapper")
    private CustomerFollowServiceMapper customerFollowServiceMapper;
    @Resource(name = "customerFollowMapper")
    private CustomerFollowMapper customerFollowMapper;
    @Resource(name = "GoodsProductMapper")
    private GoodsProductMapper goodsProductMapper;
    /**
     * 查询默认地址id
     */
    @Resource(name = "DefaultAddressService")
    private DefaultAddressService defaultAddressService;
    /**
     * 查询分仓库存
     */
    @Resource(name = "ProductWareService")
    private ProductWareService productWareService;

    @Resource(name = "enterpriseCertificationInfoService")
    private EnterpriseCertificationInfoService enterpriseCertificationInfoService;

    @Resource(name = "SysProvinceService")
    private SysProvinceService sysProvinceService;

    @Resource(name = "simpleGetAddressService")
    private SimpleGetAddressService simpleGetAddressService;

    @Resource(name = "customerAddressServiceM")
    private CustomerAddressService addressService;

    @Resource(name = "browserecordServiceM")
    private BrowserecordService browserecordService;

    @Resource(name = "CouponService")
    private CouponService couponService;

    // spring注解 会员订单service
    @Resource(name = "customerOrderServiceM")
    private CustomerOrderService customerOrderService;

    @Resource(name = "customerServiceMapper")
    private CustomerServiceMapper customerServiceMapper;

    @Resource(name = "loginServiceM")
    private LoginService loginService;

    @Resource(name = "customerPointServiceMapper")
    private CustomerPointServiceMapper customerPointServiceMapper;



    /**
     * 跳转会员中心
     *
     * @return {@link org.springframework.web.servlet.ModelAndView}
     */
    @RequestMapping("/customer/newindexm")
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView mav = null;
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            // 验证登录
            if (LoginUtil.checkLoginStatus(request)) {
                request.getSession().setAttribute("cust", customerService.selectByPrimaryKey((Long) request.getSession().getAttribute(CustomerConstants.CUSTOMERID)));
                mav = new ModelAndView(CustomerConstants.NEWCUSTOMERINDEX);
                mav.addAllObjects(resultMap);
            } else {
                mav = new ModelAndView("redirect:/login.html?url=/customer/index.html?tag=4");
            }
            mav.addObject(ConstantUtil.PAGENAME, "个人中心");
            return seoService.getCurrSeo(mav);
        } finally {
            mav = null;
            resultMap = null;
        }
    }
    //-----------------------

    /**
     * 跳转账户管理
     *
     * @return
     */
    @RequestMapping("/customer/toAccountManage")
    public ModelAndView toAccountManage(HttpServletRequest request) {
        ModelAndView mav = null;
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            // 验证登录
            if (LoginUtil.checkLoginStatus(request)) {
                request.getSession().setAttribute("cust", customerService.selectByPrimaryKey((Long) request.getSession().getAttribute(CustomerConstants.CUSTOMERID)));
                mav = new ModelAndView(CustomerConstants.ACCOUNTMANAGE);
                mav.addAllObjects(resultMap);
            } else {
                mav = new ModelAndView("redirect:/login.html?url=/customer/index.html?tag=4");
            }
            mav.addObject(ConstantUtil.PAGENAME, "账户管理");
            mav.addObject(ConstantUtil.BACKLEVURL,"customer/index.html?tag=4");
            return seoService.getCurrSeo(mav);
        } finally {
            mav = null;
            resultMap = null;
        }
    }

    /**
     * 跳转浏览记录
     *
     * @return {@link org.springframework.web.servlet.ModelAndView}
     */
    @RequestMapping("/customer/toBrowerGoodsList")
    public ModelAndView toBrowerGoodsList(HttpServletRequest request) {
        ModelAndView mav = null;
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            // 验证登录
            if (LoginUtil.checkLoginStatus(request)) {
                Long customerId = (Long) request.getSession().getAttribute(CustomerConstants.CUSTOMERID);
                mav = new ModelAndView(CustomerConstants.BROWSERECORDS);
                mav.addObject("browseRecordList", browserecordService.selectBrowserecord(customerId));
                mav.addAllObjects(resultMap);
            } else {
                mav = new ModelAndView("redirect:/login.html?url=/customer/index.html?tag=4");
            }
            mav.addObject(ConstantUtil.PAGENAME, "浏览记录");
            mav.addObject(ConstantUtil.BACKLEVURL,"/customer/index.html?tag=4");
            return seoService.getCurrSeo(mav);
        } finally {
            mav = null;
            resultMap = null;
        }
    }


    /**
     * 跳转修改密码的页面
     *
     * @return {@link org.springframework.web.servlet.ModelAndView}
     */
    @RequestMapping("/customer/toUpdatePwd")
    public ModelAndView toUpdatePwd(HttpServletRequest request) {
        ModelAndView mav = null;
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            // 验证登录
            mav = new ModelAndView(CustomerConstants.RESETPASSWORD);
            if (LoginUtil.checkLoginStatus(request)) {
                Long customerId = (Long) request.getSession().getAttribute(CustomerConstants.CUSTOMERID);
                /*mav = new ModelAndView(CustomerConstants.RESETPASSWORD);*/
                mav.addAllObjects(resultMap);
            }
            mav.addObject(ConstantUtil.PAGENAME, "修改密码");
            mav.addObject(ConstantUtil.BACKLEVURL, "/customer/toAccountManage.html");
            return seoService.getCurrSeo(mav);
        } finally {
            mav = null;
            resultMap = null;
        }
    }

    /**
     * 跳转收货地址的页面
     *
     * @param request
     * @return
     */
    @RequestMapping("/customer/showOrderAddressList")
    public ModelAndView showOrderAddressList(HttpServletRequest request) {
        if (!LoginUtil.checkLoginStatus(request)) {
            return new ModelAndView(CustomerConstants.REDIRECTLOGINTOINDEX);
        }
        //地址列表
        List<Object> addresses = addressService.queryCustomerAddress((Long) request.getSession().getAttribute(CustomerConstants.CUSTOMERID));
        return seoService.getCurrSeo(new ModelAndView(CustomerConstants.RECEIPTADDR).addObject("addresses", addresses))
                .addObject(ConstantUtil.PAGENAME, "收货地址管理").addObject(ConstantUtil.BACKLEVURL,"/customer/toAccountManage.html");
    }

    /**
     * 跳转添加新收货地址的页面
     *
     * @param request
     * @return
     */
    @RequestMapping("/customer/toAddNewAddress")
    public ModelAndView toAddNewAddress(HttpServletRequest request) {
        if (!LoginUtil.checkLoginStatus(request)) {
            return new ModelAndView(CustomerConstants.REDIRECTLOGINTOINDEX);
        }
        return seoService.getCurrSeo(new ModelAndView(CustomerConstants.REVISEADDR).addObject(ConstantUtil.PAGENAME, "添加收货地址"));
    }

    /**
     * 添加收货地址
     *
     * @return {@link org.springframework.web.servlet.ModelAndView}
     */
    @RequestMapping("/customer/addAddressNewM")
    public ModelAndView addAddressNewM(HttpServletRequest request, CustomerAddress address, String checkbox,String url) {
        if (!LoginUtil.checkLoginStatus(request)) {
            return new ModelAndView(CustomerConstants.REDIRECTLOGINTOINDEX);
        }
        Long custId = (Long) request.getSession().getAttribute(CustomerConstants.CUSTOMERID);
        if (checkbox != null && "1".equals(checkbox)) {
            addressService.updateAddressDef(custId);
            address.setIsDefault("1");
        }
        addressService.addAddress(address, custId);
        if(url!=null&&url.length()>0&&"1".equals(address.getIsDefault())){
            return new ModelAndView("redirect:/order/myshoppingmcart.html");
        }
        return new ModelAndView(CustomerConstants.TOADDRESSLISTHTML);
    }

    /**
     * 跳转修改
     *
     * @return {@link org.springframework.web.servlet.ModelAndView}
     */
    @RequestMapping("/customer/toUpdateAddrNewM")
    public ModelAndView toUpdateAddrNewM(HttpServletRequest request, Long addressId) {
        if (!LoginUtil.checkLoginStatus(request)) {
            return new ModelAndView(CustomerConstants.REDIRECTLOGINTOINDEX);
        }
        Long customerId = (Long) request.getSession().getAttribute(CustomerConstants.CUSTOMERID);
        CustomerAddress address = addressService.queryCustomerAddressById(addressId, customerId);
        ModelAndView mav = new ModelAndView(CustomerConstants.REVISEADDR);
        mav.addObject("orderaddress", address);
        mav.addObject(ConstantUtil.PAGENAME, "修改收货地址");
        return seoService.getCurrSeo(mav);
    }

    /**
     * 修改收货地址
     *
     * @return {@link org.springframework.web.servlet.ModelAndView}
     */
    @RequestMapping("/customer/uodateAddressNewM")
    public ModelAndView uodateAddressNewM(HttpServletRequest request, CustomerAddress address, String checkbox) {
        if (!LoginUtil.checkLoginStatus(request)) {
            return new ModelAndView(CustomerConstants.REDIRECTLOGINTOINDEX);
        }
        Long custId = (Long) request.getSession().getAttribute(CustomerConstants.CUSTOMERID);
        if (checkbox != null && "1".equals(checkbox)) {
            addressService.updateAddressDef(custId);
            address.setIsDefault("1");
        }
        addressService.updateAddress(address, custId);
        return new ModelAndView(CustomerConstants.TOADDRESSLISTHTML);
    }

    //----------------------AJAX

    /**
     * 浏览记录数目
     *
     * @param request
     * @return
     */
    @RequestMapping("/getBrowserecordCount")
    @ResponseBody
    public Integer getBrowserecordCount(HttpServletRequest request) {
        // 验证登录
        if (LoginUtil.checkLoginStatus(request)) {
            Long customerId = (Long) request.getSession().getAttribute(CustomerConstants.CUSTOMERID);
            return browserecordService.selectBrowserecordCount(customerId);
        } else {
            return 0;
        }
    }
    /**
     * 浏览记录清空
     *
     * @param request
     * @return
     */
    @RequestMapping("/clearBrowserecord")
    @ResponseBody
    public Integer clearBrowserecord(HttpServletRequest request) {
        // 验证登录
        if (LoginUtil.checkLoginStatus(request)) {
            Long customerId = (Long) request.getSession().getAttribute(CustomerConstants.CUSTOMERID);
            return browserecordService.deleteByCustomerId(customerId);
        } else {
            return 0;
        }
    }

    /**
     * 我的收藏数目
     *
     * @param request
     * @return
     */
    @RequestMapping("/getByCustomerIdCount")
    @ResponseBody
    public Integer getByCustomerIdCount(HttpServletRequest request) {
        // 验证登录
        if (LoginUtil.checkLoginStatus(request)) {
            Long customerId = (Long) request.getSession().getAttribute(CustomerConstants.CUSTOMERID);
            return customerFollowServiceMapper.selectByCustomerIdCount(customerId);
        } else {
            return 0;
        }
    }

    /**
     * 我的优惠券数目
     *
     * @param request
     * @return
     */
    @RequestMapping("/getCouponByCustomerIdCount")
    @ResponseBody
    public Integer getCouponByCustomerIdCount(HttpServletRequest request) {
        // 验证登录
        if (LoginUtil.checkLoginStatus(request)) {
            Long customerId = (Long) request.getSession().getAttribute(CustomerConstants.CUSTOMERID);
            return couponService.myCouponNoCount(customerId);
        } else {
            return 0;
        }
    }

    /**
     * 我的积分数目
     *
     * @param request
     * @return
     */
    @RequestMapping("/getCustomerPointByCustomerIdCount")
    @ResponseBody
    public Long getCustomerPointByCustomerIdCount(HttpServletRequest request) {
        // 验证登录
        if (LoginUtil.checkLoginStatus(request)) {
            Long customerId = (Long) request.getSession().getAttribute(CustomerConstants.CUSTOMERID);
            return couponService.selectCustomerPointByCustomerId(customerId).getPointSum();
        } else {
            return 0l;
        }
    }

    /**
     * 我的待收货订单数目
     *
     * @param request
     * @return
     */
    @RequestMapping("/searchTotalCount")
    @ResponseBody
    public Long searchTotalCount(HttpServletRequest request) {
        // 验证登录
        if (LoginUtil.checkLoginStatus(request)) {
            Map<String, Object> paramMap =
                    paramMap = new HashMap<String, Object>();
            Long customerId = (Long) request.getSession().getAttribute(CustomerConstants.CUSTOMERID);
            paramMap.put(CustomerConstants.CUSTOMERID, customerId);
            paramMap.put(CustomerConstants.TYPE, "3");
            return customerOrderService.searchTotalCount(paramMap);
        } else {
            return 0l;
        }
    }

    /**
     * 修改个人信息
     *
     * @param request
     * @return
     */
    @RequestMapping("/updateCustomerAjaxM")
    @ResponseBody
    public int updateCustomerAjaxM(HttpServletRequest request, Customer customer) {
        // 验证登录
        if (!LoginUtil.checkLoginStatus(request)) {
            return 0;
        }
        Map<String, Object> paramMap =
                paramMap = new HashMap<String, Object>();
        Long customerId = (Long) request.getSession().getAttribute(CustomerConstants.CUSTOMERID);
        /*if (customerId != customer.getCustomerId()) {
            return 0;
        }*/
        customer.setCustomerId((Long) request.getSession().getAttribute(CustomerConstants.CUSTOMERID));
        if (customer.getCustomerNickname() == null || customer.getCustomerNickname().trim().length() == 0) {
            return 0;
        }
        return customerService.updateCustomerAjax(customer);
    }

    /**
     * 修改个人信息
     *
     * @param request
     * @return
     */
    @RequestMapping("/updateCustomerSexM")
    @ResponseBody
    public int updateCustomerSexM(HttpServletRequest request, Long customerId, String infoGender) {
        // 验证登录
        if (!LoginUtil.checkLoginStatus(request)) {
            return 0;
        }
        Map<String, Object> paramMap =
                paramMap = new HashMap<String, Object>();
        Long customerId1 = (Long) request.getSession().getAttribute(CustomerConstants.CUSTOMERID);
       /* if (customerId != customerId1) {
            return 0;
        }*/
        if (infoGender == null || !("0".equals(infoGender) || "1".equals(infoGender) || "2".equals(infoGender))) {
            return 0;
        }
        paramMap.put(CustomerConstants.CUSTOMERID, customerId1);
        paramMap.put("infoGender", infoGender);
        return customerService.updateCustomerSex(paramMap);
    }

    /**
     * 重置密码
     *
     * @param request
     * @return
     */
    @RequestMapping("/modifiedUserKeyM")
    @ResponseBody
    public int modifiedUserKeyM(HttpServletRequest request, String customerUsername, String newPassWord, String newPassWordR,
                                String code) {
        if (newPassWord == null || newPassWordR == null || !newPassWord.equals(newPassWordR)) {
            return 0;
        }
        int flag = customerService.getMCode(request, code, customerUsername);
        if (flag == 0) {
            return 0;
        }
        //验证通过可以进行修改密码的操作
        return customerService.updateCusomerPwd(request, newPassWord, customerUsername);
    }

    /**
     * AJAX删除收货地址
     *
     * @param addressId
     * @return
     */
    @RequestMapping("/delAddressAJAX")
    @ResponseBody
    public int delAddressAJAX(HttpServletRequest request, Long addressId) {
        if (!LoginUtil.checkLoginStatus(request)) {
            return 0;
        }
        // 获得用户Id
        Long customerId = (Long) request.getSession().getAttribute(CustomerConstants.CUSTOMERID);
        // 创建容器
        CustomerAddress address = new CustomerAddress();
        // 地址Id
        address.setAddressId(addressId);
        // 用户Id
        address.setCustomerId(customerId);
        // 1为删除状态
        address.setDelFlag("1");
        // 修改地址状态 为删除
        return addressService.updateAddress(address, customerId);
    }

    /**
     * AJAX注册
     *
     * @param request
     * @param allInfo
     * @return
     */
    @RequestMapping("/customer/addcustomerAJAXM")
    @ResponseBody
    public int registerAJAX(HttpServletRequest request, @Valid com.ningpai.other.bean.CustomerAllInfo allInfo, String code) {
        if (allInfo.getCustomerUsername() == null || allInfo.getCustomerPassword() == null || code == null || (code != null && code.trim().length() == 0)) {
            return 0;
        }
        int flag = customerService.getMCode(request, code, allInfo.getCustomerUsername());
        if (flag == 0) {
            return 0;
        }
        allInfo.setLoginIp(IPAddress.getIpAddr(request));
        allInfo.setIsSeller("0");
        allInfo.setIsMobile("1");
        allInfo.setIsEnterprise("0");
        int falg1 = customerServiceMapper.addCustomer(allInfo);
        int falg2 = loginService.checkCustomerExists(request, allInfo.getCustomerUsername(), allInfo.getCustomerPassword());
        customerPointServiceMapper.addIntegralByType((Long) request.getSession().getAttribute("customerId"), "0");
        if(falg1==1&&falg2==1){
            return 1 ;
        }else {
            return 0 ;
        }
    }

    //----------------------AJAX
    //---------------------- class end
}
