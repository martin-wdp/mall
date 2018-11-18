/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.site.customer.controller;

import com.ningpai.common.util.EmailUtils;
import com.ningpai.coupon.bean.Coupon;
import com.ningpai.coupon.service.CouponService;
import com.ningpai.customer.bean.Customer;
import com.ningpai.des.DESCoder;
import com.ningpai.goods.service.GoodsProductService;
import com.ningpai.goods.vo.GoodsProductDetailViewVo;
import com.ningpai.index.service.TopAndBottomService;
import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.order.bean.Order;
import com.ningpai.order.bean.OrderContainerRelation;
import com.ningpai.order.service.OrderCouponService;
import com.ningpai.order.service.OrderService;
import com.ningpai.order.service.SynOrderService;
import com.ningpai.site.customer.bean.OrderComplain;
import com.ningpai.site.customer.service.BrowserecordService;
import com.ningpai.site.customer.service.CustomerFollowService;
import com.ningpai.site.customer.service.CustomerServiceInterface;
import com.ningpai.site.customer.service.OrderComplainService;
import com.ningpai.site.customer.util.OrderContainerUtil;
import com.ningpai.site.customer.vo.CustomerAllInfo;
import com.ningpai.site.customer.vo.CustomerConstantStr;
import com.ningpai.site.customer.vo.OrderInfoBean;
import com.ningpai.site.order.service.SiteOrderService;
import com.ningpai.system.bean.PointSet;
import com.ningpai.system.bean.SystemsSet;
import com.ningpai.system.service.PointSetService;
import com.ningpai.temp.service.MegawizardService;
import com.ningpai.temp.service.TempService;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Pattern;

/**
 * 前台会员控制层
 *
 * @author NINGPAI-zhangqiang
 * @version 0.0.1
 * @since 2014年4月8日 下午2:11:48
 */
@Controller("customerControllerSite")
public class CustomerController {

    private static final String EXPLAIN = "explain";
    private static final String REFERER = "referer";
    private static final String CUSTINFO = "custInfo";
    private static final String PATCHCA = "PATCHCA";
    private static final String EMAIL = "email";
    private static final String EFLAG = "eFlag";
    private static final String EMAILCHECKPWD = "emailCheckPwd";
    private static final String VALIDPWD = "validPwd";

    private static final String ERRORINFO = "非法操作,地址栏输入链接或者是其他网站的链接,即将跳转首页";
    private static final String LOGGERINFO1 = "验证邮件 --安全中心 验证身份<===========当前登录会员为：";
    private static final String LOGGERINFO2 = "验证邮件 --安全中心 验证身份";
    private static final String LOGGERINFO3 = "验证邮箱<===========当前登录会员为：";
    private static final String LOGGERINFO4 = "发送手机验证码,手机号码为：";
    private static final String LOGGERINFO5 = "】的订单！";
    private static final String LOGGERINFO6 = "查询单号为：【";

    private static final String INDEX = "/index.html";
    private static final String MYORDER = "/myorder.html";
    private static final String SENDSUCCESS = "/customer/sendsuccess";
    private static final String REFUNDLIST = "/refundlist.html";
    private static final String BROWSERECORD = "/browserecord.html";
    private static final String MYFOLLW = "/myfollw.html";
    /**
     * 记录日志对象
     */
    private static final MyLogger LOGGER = new MyLogger(CustomerController.class);
    /**
     * spring 注解 会员service
     */
    private CustomerServiceInterface customerServiceInterface;
    /**
     * 优惠劵service
     */
    private CouponService couponService;
    /**
     * 浏览记录service
     */
    private BrowserecordService browserecordService;
    /**
     * 收藏service
     */
    private CustomerFollowService customerFollowService;
    /**
     * 订单投诉
     */
    private OrderComplainService orderComplainService;
    /**
     * 邮件
     */
    private EmailUtils emailUtils;
    /**
     * 订单
     */
    private SiteOrderService siteOrderService;
    /**
     * 订单优惠劵
     */
    private OrderCouponService orderCouponService;

    /**
     * 获取头尾
     */
    private TopAndBottomService topAndBottomService;

    @Resource(name = "SynOrderService")
    private SynOrderService synOrderService;

    /**
     * 页面说明Service
     */
    private MegawizardService megawizardSerivce;

    /**
     * 模板设置业务类
     */
    private TempService tempService;

    /**
     * 订单service
     */
    @Resource(name = "OrderService")
    private OrderService orderService;
    /**
     * 货品信息Service
     */
    @Resource(name = "GoodsProductService")
    private GoodsProductService goodsProductService;
//
//    /**
//     * 积分设置
//     * */
    @Resource(name = "pointSetService")
    private PointSetService pointSetService;

     /**
     * 跳转会员中心首页
     *
     * @param request
     * @return ModelAndView
     */
    @RequestMapping("customer/index")
    public ModelAndView customerIndex(HttpServletRequest request, HttpServletResponse response, PageBean pb) {
        // 跳转个人中心
        ModelAndView mav;
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Long customerId;
        // 设置对象
        SystemsSet isSet;

        // 是否同意退单
        isSet = customerServiceInterface.getIsBackOrder();
        // 验证登录
        if (request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID) != null) {
            customerId = (Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID);
            //查询会员获得总积分
            int pointSum = this.customerServiceInterface.querySumByCustId((Long)request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID));

            //根据查询到的用户总积分来更新其用户等级
          /*  Long levelId=0L;
            String levelName="";
            if(pointSum>=5001 && pointSum<=10000){
                levelId = 3L;
                levelName = "黄金会员";
            }else if(pointSum >= 10001 && pointSum <= 20000){
                levelId = 4L;
                levelName = "白金会员";
            }else if(pointSum >= 20001 && pointSum <= 50000){
                levelId = 5L;
                levelName = "钻石会员";
            }else if(pointSum >= 50001 && pointSum <= 150000){
                levelId = 15L;
                levelName = "金牌会员";
            }else if(pointSum > 150000){
                levelId = 38L;
                levelName = "超级会员";
            }else{
                levelId = 2L;
                levelName = "普通会员";
            }
            //执行修改会员等级方法
            this.customerServiceInterface.updCustLevel(levelId,levelName,customerId);*/
            // 查询会员基本信息
            request.getSession().setAttribute("cust", customerServiceInterface.selectByPrimaryKey((Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID)));

            // 查询我可以使用 未过期的所有优惠券总数
            resultMap.put("couponNum", couponService.myCouponNoCount(customerId));
            // 查询浏览记录
            resultMap.put("browses", browserecordService.selectBrowserecord(customerId));
            // 查询通知内容数量 如 :待处理订单数量...
            resultMap.put("notice", customerServiceInterface.selectNotice(customerId));
            // 根据会员编号查询会员信息
            resultMap.put(CustomerConstantStr.CUSTOMER, customerServiceInterface.queryCustomerById(customerId));
            // 是否允许退单
            resultMap.put("isBackOrder", isSet.getIsBackOrder());
            mav = new ModelAndView(CustomerConstantStr.NEWCUSTOMERINDEX);
            mav.addAllObjects(resultMap);
            // 记录日志
            LOGGER.info("跳转会员中心,当前会员的用户名是为:" + this.getCustomer(customerId));
        } else {
            mav = new ModelAndView(CustomerConstantStr.LOGINREDIRECT).addObject(CustomerConstantStr.URL, CustomerConstantStr.CUSTOMERS + "INDEX");
        }
        return topAndBottomService.getTopAndBottom(mav);
    }

    /**
     * 获取当前登录会员的用户名
     *
     * @return
     */
    public String getCustomer(Long customerId) {
        if (null != customerId) {
            LOGGER.info("当前会员的用户名是：" + customerServiceInterface.selectByPrimaryKey(customerId).getCustomerUsername());
            // 查询会员基本信息
            return customerServiceInterface.selectByPrimaryKey(customerId).getCustomerUsername();
        }
        return null;

    }

    /**
     * 跳转账户安全
     *
     * @param request
     * @return ModelAndView
     */
    @RequestMapping("/securitycenter")
    public ModelAndView securityCenter(HttpServletRequest request) {
        // 跳转安全中心
        ModelAndView mav;
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Long customerId;
        // 验证登录
        if (checkLoginStatus(request)) {
            customerId = (Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID);
            // 根据会员编号查询会员信息
            resultMap.put(CustomerConstantStr.CUSTOMER, customerServiceInterface.queryCustomerById(customerId));
            // 根据页面类型和模板ID查询单个页面说明
            resultMap.put(EXPLAIN, megawizardSerivce.selectByType(3, Long.parseLong(tempService.getCurrTemp().getTempId() + "")));
            mav = new ModelAndView(CustomerConstantStr.NEWSECURITYCENTER);
            mav.addAllObjects(resultMap);
            // 记录日志
            LOGGER.info("跳转账户安全,当前会员的用户名是为【" + this.getCustomer(customerId) + "】");
        } else {
            mav = new ModelAndView(CustomerConstantStr.LOGINREDIRECT).addObject(CustomerConstantStr.URL, CustomerConstantStr.CUSTOMERS + CustomerConstantStr.CENTERHTML);
        }
        return topAndBottomService.getTopAndBottom(mav);

    }

    /**
     * 邮箱 发过来的请求
     *
     * @param request
     * @return ModelAndView
     */
    @RequestMapping("/validate/emailcheck")
    public ModelAndView emailcheck(HttpServletRequest request, String type) {

        // 获取网站开头
        // 获取访问过来的url
        String url = request.getHeader(REFERER);
        Long customerId;
        // 跳转安全中心
        ModelAndView mav;
        Map<String, Object> resultMap = new HashMap<String, Object>();

        // 验证如果不是从正常途径跳转过来的就返回到首页
        if (url == null || "".equals(url)) {
            LOGGER.info(ERRORINFO);
            return new ModelAndView(new RedirectView(CustomerConstantStr.STARTINDEX));
        } else {
            // 验证登录
            if (checkLoginStatus(request)) {
                // 当前登录成功的会员
                customerId = (Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID);
                // 查询会员基本信息
                request.getSession().setAttribute("cust", customerServiceInterface.selectByPrimaryKey((Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID)));
                // 查询会员基本信息
                resultMap.put(CUSTINFO, customerServiceInterface.selectByPrimaryKey((Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID)));
                resultMap.put(CustomerConstantStr.TYPE, type);
                mav = new ModelAndView(CustomerConstantStr.NEWMODIFYPEM).addAllObjects(resultMap);
                // 记录日志
                LOGGER.info("跳转修改密码、邮箱、手机,当前会员的用户名是为:" + this.getCustomer(customerId));
            } else {
                mav = new ModelAndView(CustomerConstantStr.LOGINREDIRECT).addObject(CustomerConstantStr.URL, CustomerConstantStr.CUSTOMERS + CustomerConstantStr.CENTERHTML);
            }
        }
        return topAndBottomService.getTopAndBottom(mav);

    }


    /**
     * 验证跳转 控制器
     * @param request
     * @param type
     * @param ut
     * @param status
     * @return
     */
    @RequestMapping("/validate/reirectpem")
    public ModelAndView modifyPwd(HttpServletRequest request, String type, String ut, String status) {
        String status1 = status;
        if (!isAccessValid(ut, request)) {
            if (status == null || "".equals(status)) {
                status1 = ut;
            }
            return new ModelAndView(new RedirectView("/validate/validateidentity.htm?type=" + type + "&ut=" + status1 + "&checkedUsing=0"));
        }

        // 获取网站开头
        // 获取访问过来的url
        String url = request.getHeader(REFERER);
        Long customerId;
        // 跳转安全中心
        ModelAndView mav = null;
        Map<String, Object> resultMap = new HashMap<String, Object>();

        // 验证如果不是从正常途径跳转过来的就返回到首页
        if (url == null || "".equals(url)) {
            LOGGER.info(ERRORINFO);
            return new ModelAndView(new RedirectView(CustomerConstantStr.STARTINDEX));
        } else {
            // 验证登录
            if (checkLoginStatus(request)) {
                // 当前登录成功的会员
                customerId = (Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID);
                // 查询会员基本信息
                request.getSession().setAttribute("cust", customerServiceInterface.selectByPrimaryKey((Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID)));
                // 查询会员基本信息
                resultMap.put(CUSTINFO, customerServiceInterface.selectByPrimaryKey((Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID)));
                resultMap.put(CustomerConstantStr.TYPE, type);
                mav = new ModelAndView(CustomerConstantStr.NEWMODIFYPEM).addAllObjects(resultMap);
                // 记录日志
                LOGGER.info("跳转修改密码、邮箱、手机,当前会员的用户名是为:" + this.getCustomer(customerId));
            } else {
                mav = new ModelAndView(CustomerConstantStr.LOGINREDIRECT).addObject(CustomerConstantStr.URL, CustomerConstantStr.CUSTOMERS + CustomerConstantStr.CENTERHTML);
            }
        }
        return topAndBottomService.getTopAndBottom(mav);

    }

    /**
     * 判断访问是否合法
     *
     * @param ut
     * @param request
     * @return
     */
    private boolean isAccessValid(String ut, HttpServletRequest request) {
        if (ut == null || "".equals(ut)) {
            return false;
        }
        // 验证验证码是否合法
        String code = request.getParameter("code");
        if (!((String) request.getSession().getAttribute(PATCHCA)).equals(code)) {
            return false;
        }
        // 如果是通过密码修改，比对密码
        if ("pwd".equals(ut)) {
            String password = request.getParameter("pwd");
            if (password == null) {
                return false;
            }
            Long customerId = (Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID);
            // 验证用户密码
            int emailCheckPwd = customerServiceInterface.checkCustomerPassword(customerId, password);
            if (emailCheckPwd != 1) {
                return false;
            }
            return true;
        }

        // 如果通过手机修改，比对验证码
        if ("mobile".equals(ut)) {
            String mcode = request.getParameter("mcode");
            if (mcode == null) {
                return false;
            }
            // 验证手机验证码
            int f = customerServiceInterface.getMCode(request, mcode);
            if (f != 1) {
                return false;
            }
            return true;
        }
        return true;
    }

    /**
     * 修改密码 邮箱 手机
     *
     * @param request
     * @return ModelAndView
     */
    @RequestMapping("/validate/modifypem")
    public ModelAndView modifyPem(HttpServletRequest request, String newStr, String type, String csrFToken) {
        ModelAndView mav;
        // 验证验证码是否合法
        String code = request.getParameter("code");
        if (!((String) request.getSession().getAttribute(PATCHCA)).equals(code)) {

            Map<String, Object> resultMap = new HashMap<String, Object>();
            // 查询会员基本信息
            request.getSession().setAttribute("cust", customerServiceInterface.selectByPrimaryKey((Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID)));
            // 查询会员基本信息
            resultMap.put(CUSTINFO, customerServiceInterface.selectByPrimaryKey((Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID)));
            resultMap.put(CustomerConstantStr.TYPE, type);
            mav = new ModelAndView(CustomerConstantStr.NEWMODIFYPEM).addAllObjects(resultMap).addObject("checkedUsing", "0");
            return topAndBottomService.getTopAndBottom(mav);
        }
        Object tokenInSession = request.getSession().getAttribute("CSRFToken");
        if (csrFToken == null || !csrFToken.equals(tokenInSession)) {
            LOGGER.info("非法操作,CSRFToken不匹配,即将跳转首页");
            return new ModelAndView(new RedirectView(CustomerConstantStr.STARTINDEX));
        }

        // 当前登录的用户
        Long customerId = (Long) request.getSession().getAttribute("customerId");
        // 根据主键 获取会员详细信息
        Customer customer = customerServiceInterface.queryCustomerById(customerId);
        if (checkLoginStatus(request)) {
            // 操作日志
            OperaLogUtil.addOperaLog(request, customer.getCustomerUsername(), "修改资料", "修改密码 邮箱 手机");
            // 修改密码 邮箱 手机
            if (customerServiceInterface.modifyPem(request, newStr, type) == 1) {
                // 成功跳转到修改成功页面
                mav = new ModelAndView(new RedirectView("sucess.html?type=" + type));
            } else {
                mav = new ModelAndView(new RedirectView("fail.htm?type=" + type));
            }
        } else {
            mav = new ModelAndView(CustomerConstantStr.LOGINREDIRECT).addObject(CustomerConstantStr.URL, CustomerConstantStr.CUSTOMERS + INDEX);
        }
        return topAndBottomService.getBottom(mav);

    }

    /**
     * 发送邮件
     *
     * @param request
     */
    @RequestMapping("/validate/sendeamil")
    @ResponseBody
    public int sendEamil(HttpServletRequest request, String email, String type) {
        // 验证验证码是否合法
        String code = request.getParameter("code");
        // 没有输入验证码
        if ("".equals(code)) {
            return 2;
        }
        // 验证码不正确
        if (!((String) request.getSession().getAttribute(PATCHCA)).equals(code)) {
            return 0;
        }
        if (checkLoginStatus(request)) {
            CustomerAllInfo user = (CustomerAllInfo) request.getSession().getAttribute("cust");
            user.setCustomerId((Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID));
            user.setInfoEmail(email);
            request.setAttribute(EMAIL, email);

            // 发送邮件成功
            if (emailUtils.sendBindEmail(request, user) == 1) {
                // 修改用户的信息(邮箱等,以及链接的有效时常等)
                return customerServiceInterface.updateFindPwdCode(user);
            } else {
                return 3;
            }
        } else {
            return -1;
        }

    }

    /**
     * 发送邮件 验证身份
     *
     * @param request
     */
    @RequestMapping("/validate/sendcheckidemail")
    @ResponseBody
    public int sendCheckIdEmail(HttpServletRequest request, String email, String type) {
        // 验证验证码
        if (!isAccessValid("email", request)) {
            return -1;
        }
        if (checkLoginStatus(request)) {
            // 根据用户名查询用户简单信息
            CustomerAllInfo user = customerServiceInterface.selectCustomerByUname(((Customer) request.getSession().getAttribute("cust")).getCustomerUsername());
            request.getSession().setAttribute("utype", type);
            // 日志记录
            LOGGER.info("发送邮件验证身份，当前登录会员是:" + user.getCustomerUsername());
            if (emailUtils.sendCheckIdEmail(request, user) == 1) {
                // 修改找回密码Code
                return customerServiceInterface.updateFindPwdCode(user);
            } else {
                return 0;
            }
        } else {
            return -1;
        }

    }

    /**
     * 验证邮件 --安全中心 验证身份
     *
     * @param request
     */
    @RequestMapping("/validate/valididemail")
    public ModelAndView validPwdEmail(HttpServletRequest request, String checkCode, String d, String type, String k) {
        ModelAndView mav = null;
        String d1 = d;
        String type1 = type;
        String k1 = k;
        Long customerId;
        Map<String, Object> resultMap = new HashMap<String, Object>();
        // 特殊符号转码
        if (d.indexOf(CustomerConstantStr.KEY) >= 0) {
            d1 = d.replaceAll(CustomerConstantStr.KEY, "\\+");
        }
        if (type.indexOf(CustomerConstantStr.KEY) >= 0) {
            type1 = type.replaceAll(CustomerConstantStr.KEY, "\\+");
        }
        if (k.indexOf(CustomerConstantStr.KEY) >= 0) {
            k1 = k.replaceAll(CustomerConstantStr.KEY, "\\+");
        }
        String outputStr1 = "";
        String outputStr2 = "";
        int err = 0;
        // 解码
        try {
            byte[] inputData1 = DESCoder.decryptBASE64(d1);
            byte[] outputData1 = DESCoder.decrypt(inputData1, k1);
            outputStr1 = new String(outputData1);
            byte[] inputData2 = DESCoder.decryptBASE64(type1);
            byte[] outputData2 = DESCoder.decrypt(inputData2, k1);
            outputStr2 = new String(outputData2);
        } catch (Exception e) {
            LOGGER.error("", e);
            err++;
        }

        if (checkLoginStatus(request)) {
            // 当前登录成功的会员
            customerId = (Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID);
            // 验证邮件
            int result = customerServiceInterface.validPwdEmail(request, checkCode, Long.valueOf(outputStr1));
            // 解码出错的情况下,跳转参数异常页面
            if (err > 0) {
                result = -2;
            }
            if (result == 1) {
                return topAndBottomService.getBottom(new ModelAndView(new RedirectView("/validate/emailcheck.htm?type=" + outputStr2 + "&ut=email")));
            }
            resultMap.put(EFLAG, result);
            // 根据页面类型和模板ID查询单个页面说明
            resultMap.put(EXPLAIN, megawizardSerivce.selectByType(3, Long.parseLong(tempService.getCurrTemp().getTempId() + "")));
            mav = new ModelAndView(SENDSUCCESS);
            mav.addAllObjects(resultMap);
            LOGGER.info(LOGGERINFO1 + this.getCustomer(customerId));
            // 操作日志
            OperaLogUtil.addOperaLog(request, this.getCustomer(customerId), "验证身份", LOGGERINFO2);
            return topAndBottomService.getBottom(mav);
        } else {
            return new ModelAndView("redirect:/" + "login.html?url=validate/valididemail-" + checkCode + "-" + type1 + "-" + d1 + ".html");
        }
    }

    /**
     * 绑定成功
     * 
     * @return
     */
    @RequestMapping("/validate/bindsucess")
    public ModelAndView bindsucess() {
        // 根据页面类型和模板ID查询单个页面说明
        return topAndBottomService.getTopAndBottom(new ModelAndView("customer/newmodifysuccess")).addObject(CustomerConstantStr.TYPE, EMAIL)
                .addObject(EXPLAIN, megawizardSerivce.selectByType(3, Long.parseLong(tempService.getCurrTemp().getTempId() + "")));
    }

    /**
     * 验证邮箱
     *
     * @param d
     * @param checkCode
     * @return
     */
    @RequestMapping("validate/validbindemail")
    public ModelAndView validbindemail(HttpServletRequest request, String d, String checkCode, String mail, String k) {
        ModelAndView mav = null;
        String d1 = d;
        String k1 = k;
        String mail1 = mail;
        Map<String, Object> resultMap = new HashMap<String, Object>();
        // 获取用户Id
        Long customerId = (Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID);
        String outputStr1 = "";
        String outputStr2 = "";
        int err = 0;
        // 特殊字符转码
        if (d.indexOf(CustomerConstantStr.KEY) >= 0) {
            d1 = d.replaceAll(CustomerConstantStr.KEY, "\\+");
        }
        if (k.indexOf(CustomerConstantStr.KEY) >= 0) {
            k1 = k.replaceAll(CustomerConstantStr.KEY, "\\+");
        }
        if (mail.indexOf(CustomerConstantStr.KEY) >= 0) {
            mail1 = mail.replaceAll(CustomerConstantStr.KEY, "\\+");
        }
        // 解码
        try {
            byte[] inputData1 = DESCoder.decryptBASE64(d1);
            byte[] outputData1 = DESCoder.decrypt(inputData1, k1);
            outputStr1 = new String(outputData1);
            byte[] inputData2 = DESCoder.decryptBASE64(mail1);
            byte[] outputData2 = DESCoder.decrypt(inputData2, k1);
            outputStr2 = new String(outputData2);
        } catch (Exception e) {
            LOGGER.error("", e);
            err++;
        }
        // 解码出错的情况下,跳转参数异常页面
        if (err > 0) {
            resultMap.put(EFLAG, -2);
            resultMap.put(EXPLAIN, megawizardSerivce.selectByType(3, Long.parseLong(tempService.getCurrTemp().getTempId() + "")));
            mav = new ModelAndView(SENDSUCCESS);
            mav.addAllObjects(resultMap);
            LOGGER.info(LOGGERINFO1 + this.getCustomer(customerId));
            // 操作日志
            OperaLogUtil.addOperaLog(request, this.getCustomer(customerId), "验证身份", LOGGERINFO2);
            return topAndBottomService.getBottom(mav);
        }
        if (customerId != null && Long.valueOf(outputStr1).equals(customerId)) {
            // 验证邮件
            int result = customerServiceInterface.validPwdEmail(request, checkCode, Long.valueOf(outputStr1));
            if (result != 1) {
                resultMap.put(EFLAG, result);
                // 根据页面类型和模板ID查询单个页面说明
                resultMap.put(EXPLAIN, megawizardSerivce.selectByType(3, Long.parseLong(tempService.getCurrTemp().getTempId() + "")));
                mav = new ModelAndView(SENDSUCCESS);
                mav.addAllObjects(resultMap);
                LOGGER.info(LOGGERINFO1 + this.getCustomer(customerId));
                // 操作日志
                OperaLogUtil.addOperaLog(request, this.getCustomer(customerId), "验证身份", LOGGERINFO2);
                return topAndBottomService.getBottom(mav);
            }
            // 验证邮箱
            customerServiceInterface.verifyCheckCode(request, customerId, checkCode);
            // 获得用户信息
            CustomerAllInfo user = new CustomerAllInfo();
            user.setCustomerId(customerId);
            user.setInfoEmail(outputStr2);
            // 修改用户邮箱信息
            customerServiceInterface.updateCustInfoByPrimaryKey(user);
            // 查询会员基本信息
            request.getSession().setAttribute("cust", customerServiceInterface.selectByPrimaryKey(customerId));
            mav = new ModelAndView(CustomerConstantStr.NEWMODIFYSUCCESS).addObject(CustomerConstantStr.TYPE, EMAIL).addObject("ok", "ok");
            LOGGER.info(LOGGERINFO3 + this.getCustomer(customerId));
        } else {
            mav = new ModelAndView(CustomerConstantStr.LOGINREDIRECT).addObject(CustomerConstantStr.URL, CustomerConstantStr.CUSTOMERS + INDEX);
        }
        return topAndBottomService.getTopAndBottom(mav);
    }

    /**
     * 忘记密码时，邮箱点击跳转更改密码页面
     * 
     * @param d
     * @return
     */
    @RequestMapping("validate/newvalidbindemail")
    public ModelAndView newvalidbindemail(HttpServletRequest request, String d, String mail, String k) {
        ModelAndView mav = null;
        String d1 = d;
        String k1 = k;
        String mail1 = mail;
        CustomerAllInfo user = (CustomerAllInfo) request.getSession().getAttribute("user");
        String outputStr1 = "";
        Long customerId = null;
        if (user != null) {
            customerId = user.getCustomerId();
        }

        int err = 0;
        // 特殊字符转码
        if (d.indexOf(CustomerConstantStr.KEY) >= 0) {
            d1 = d.replaceAll(CustomerConstantStr.KEY, "\\+");
        }
        if (k.indexOf(CustomerConstantStr.KEY) >= 0) {
            k1 = k.replaceAll(CustomerConstantStr.KEY, "\\+");
        }
        if (mail.indexOf(CustomerConstantStr.KEY) >= 0) {
            mail1 = mail.replaceAll(CustomerConstantStr.KEY, "\\+");
        }
        // 解码-安全性问题：err统计是否有异常隐患
        try {
            byte[] inputData1 = DESCoder.decryptBASE64(d1);
            byte[] outputData1 = DESCoder.decrypt(inputData1, k1);
            outputStr1 = new String(outputData1);
        } catch (Exception e) {
            LOGGER.error("", e);
            err++;
        }
        if (err == 0 && customerId != null && Long.valueOf(outputStr1).equals(customerId)) {
            mav = new ModelAndView(new RedirectView("../resetpwd.htm"));
            LOGGER.info("更改登陆密码的用户id为:" + this.getCustomer(customerId));
        } else {
            mav = new ModelAndView(CustomerConstantStr.LOGINREDIRECT).addObject(CustomerConstantStr.URL, CustomerConstantStr.CUSTOMERS + "/index.html");
        }
        return topAndBottomService.getTopAndBottom(mav);
    }

    /**
     * 修改密码 邮箱 手机
     *
     * @param request
     * @return ModelAndView
     */
    @RequestMapping("validate/sucess")
    public ModelAndView modifySucess(HttpServletRequest request, String newStr, String type) {
        // 跳转安全中心
        ModelAndView mav;
        Long customerId;

        // 当前登录成功的会员
        customerId = (Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID);
        // 验证登录
        if (checkLoginStatus(request)) {
            // 查询会员基本信息
            request.getSession().setAttribute("cust", customerServiceInterface.selectByPrimaryKey((Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID)));
            mav = new ModelAndView(CustomerConstantStr.NEWMODIFYSUCCESS).addObject(CustomerConstantStr.TYPE, type);
        } else {
            mav = new ModelAndView(CustomerConstantStr.LOGINREDIRECT).addObject(CustomerConstantStr.URL, CustomerConstantStr.CUSTOMERS + CustomerConstantStr.CENTERHTML);
        }
        LOGGER.info(LOGGERINFO3 + this.getCustomer(customerId));
        return topAndBottomService.getTopAndBottom(mav);

    }

    /**
     * 修改密码 邮箱 手机 失败
     *
     * @param request
     * @return ModelAndView
     */
    @RequestMapping("validate/fail")
    public ModelAndView modifyFail(HttpServletRequest request, String newStr, String type) {
        // 跳转安全中心
        ModelAndView mav;
        Long customerId;
        // 当前登录成功的会员
        customerId = (Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID);
        // 验证登录
        if (checkLoginStatus(request)) {
            // 查询会员基本信息
            request.getSession().setAttribute("cust", customerServiceInterface.selectByPrimaryKey((Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID)));
            mav = new ModelAndView("customer/modifyfail").addObject(CustomerConstantStr.TYPE, type);
        } else {
            mav = new ModelAndView(CustomerConstantStr.LOGINREDIRECT).addObject(CustomerConstantStr.URL, CustomerConstantStr.CUSTOMERS + CustomerConstantStr.CENTERHTML);
        }
        LOGGER.info(LOGGERINFO3 + this.getCustomer(customerId));
        return topAndBottomService.getTopAndBottom(mav);
    }

    /**
     * 验证身份
     *
     * @param request
     * @return ModelAndView
     */
    @RequestMapping("validate/validateidentity")
    public ModelAndView validateIdentity(HttpServletRequest request, String type, String ut, String checkedUsing) {
        // 获取网站开头
        // String path = request.getContextPath();
        // String basePath = request.getScheme() + "://" +
        // request.getServerName() + ":" + request.getServerPort() + path + "/";
        // 获取访问过来的地址
        String url = request.getHeader(REFERER);
        ModelAndView mav = null;
        Map<String, Object> resultMap = new HashMap<String, Object>();
        CustomerAllInfo custInfo = null;
        int emailCheckPwd = -1;
        String checkPwd = "";
        // 如果绑定邮箱,输入密码不为空
        if (request.getSession().getAttribute(EMAILCHECKPWD) != null && !"".equals(request.getSession().getAttribute(EMAILCHECKPWD))) {
            emailCheckPwd = Integer.valueOf(request.getSession().getAttribute(EMAILCHECKPWD).toString());
        }
        // 如果验证密码不为空
        if (request.getSession().getAttribute(VALIDPWD) != null && !"".equals(request.getSession().getAttribute(VALIDPWD))) {
            checkPwd = request.getSession().getAttribute(VALIDPWD).toString();
        }
        // 验证如果不是从正常途径跳转过来的就返回到首页
        if ((url == null || "".equals(url)/* ||!url.startsWith(basePath) */|| ("successPwd".equals(checkPwd) && emailCheckPwd != 1)) && type.equals(EMAIL)) {
            LOGGER.info(ERRORINFO);
            return new ModelAndView(new RedirectView(CustomerConstantStr.STARTINDEX));
        } else {
            // 验证登录
            if (checkLoginStatus(request)) {
                custInfo = customerServiceInterface.selectByPrimaryKey((Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID));
                resultMap.put(CUSTINFO, custInfo);
                request.getSession().setAttribute("cust", custInfo);
                resultMap.put(CustomerConstantStr.TYPE, type);
                resultMap.put(EXPLAIN, megawizardSerivce.selectByType(4, Long.parseLong(tempService.getCurrTemp().getTempId() + "")));
                // 账户安全默认选择验证方式判定
                if (ut == null) {
                    // 已验证手机
                    if ("1".equals(custInfo.getIsMobile()) && custInfo.getInfoMobile() != null && custInfo.getInfoMobile().length() > 3) {
                        resultMap.put(CustomerConstantStr.UT, "mobile");
                        // 已验证邮箱
                    } else if ("1".equals(custInfo.getIsEmail()) && custInfo.getInfoEmail() != null && custInfo.getInfoEmail().length() > 3) {
                        resultMap.put(CustomerConstantStr.UT, EMAIL);
                        // 都没验证 使用密码验证
                    } else {
                        resultMap.put(CustomerConstantStr.UT, "pwd");
                    }
                } else {
                    resultMap.put(CustomerConstantStr.UT, ut);
                }
                request.getSession().setAttribute("CSRFToken", UUID.randomUUID().toString());
                mav = new ModelAndView(CustomerConstantStr.NEWVALIDATEIDENTITY).addAllObjects(resultMap).addObject("checkedUsing", checkedUsing);
            } else {
                mav = new ModelAndView(CustomerConstantStr.LOGINREDIRECT).addObject(CustomerConstantStr.URL, CustomerConstantStr.CUSTOMERS + CustomerConstantStr.CENTERHTML);
            }
        }
        LOGGER.info(" 会员身份验证");
        return topAndBottomService.getTopAndBottom(mav);

    }

    /**
     * 确认手机
     *
     * @param request
     * @return ModelAndView
     */
    @RequestMapping("validate/validatemobile")
    public ModelAndView validateMobile(HttpServletRequest request) {
        // 跳转安全中心
        ModelAndView mav;

        // 验证登录
        if (checkLoginStatus(request)) {
            mav = new ModelAndView();
        } else {
            mav = new ModelAndView(CustomerConstantStr.LOGINREDIRECT).addObject(CustomerConstantStr.URL, CustomerConstantStr.CUSTOMERS + CustomerConstantStr.CENTERHTML);
        }
        LOGGER.info("确认手机");
        return topAndBottomService.getTopAndBottom(mav);

    }

    /**
     * 验证密码
     *
     * @return 0不同 1相同
     */
    @RequestMapping("/checkcustomerpassword")
    @ResponseBody
    public int checkCustomerPassword(Long customerId, String password, HttpServletRequest request) {
        Long customerId1 = customerId;
        customerId1 = (Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID);
        // 检查登陆状态
        if (checkLoginStatus(request)) {
            LOGGER.info("验证密码");
            // 验证用户密码
            int emailCheckPwd = customerServiceInterface.checkCustomerPassword(customerId1, password);
            request.getSession().setAttribute(EMAILCHECKPWD, emailCheckPwd);
            return emailCheckPwd;
        } else {
            return -1;
        }
    }

    /**
     * 验证邮箱重复
     *
     * @return 0不存在 1存在
     */
    @RequestMapping("/checkemailexist")
    @ResponseBody
    public Long checkEmailExist(HttpServletRequest request, String email) {
        // 检查登陆状态
        if (checkLoginStatus(request)) {
            // 验证邮箱存在性
            return customerServiceInterface.checkEmailExist(email);
        } else {
            return -1L;
        }
    }

    /**
     * 验证手机重复
     *
     * @return 0不存在 1存在
     */
    @RequestMapping("/checkmobileexist")
    @ResponseBody
    public Long checkMobileExist(HttpServletRequest request, String mobile) {
        // 检查登陆状态
        if (checkLoginStatus(request)) {
            // 验证手机存在性
            return customerServiceInterface.checkMobileExist(mobile);
        } else {
            return -1L;
        }
    }

    /**
     * 绑定手机，发送手机验证码
     *
     * @throws IOException
     */
    @RequestMapping("/sendcode")
    @ResponseBody
    public int sendcode(HttpServletRequest request, String moblie) throws IOException {
        // 检查登陆状态
        if (checkLoginStatus(request)) {
            LOGGER.info(LOGGERINFO4 + moblie);
            // 发送手机验证码
            return customerServiceInterface.sendPost(request, moblie);
        } else {
            return -10010;
        }
    }

    /**
     * 找回密码，发送手机验证码
     *
     * @throws IOException
     */
    @RequestMapping("/sendcodefindpwd")
    @ResponseBody
    public int sendcodeToFindPwd(HttpServletRequest request) throws IOException {
        // 找回密码，输入用户名后放入session中的值
        CustomerAllInfo user = (CustomerAllInfo) request.getSession().getAttribute("user");
        if (user == null) {
            return -1;
        }
        LOGGER.info(LOGGERINFO4 + user.getInfoMobile());
        // 发送手机验证码
        return customerServiceInterface.sendPost(request, user.getInfoMobile());
    }

    /**
     * 根据手机号码验证身份
     *
     * @throws IOException
     */
    @RequestMapping("/sendcodetovalidate")
    @ResponseBody
    public int sendcodeToValidate(HttpServletRequest request) throws IOException {
        Long customerId = (Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID);
        // 根据session中的customerId查询用户信息
        CustomerAllInfo user = customerServiceInterface.selectByPrimaryKey(customerId);
        if (user == null) {
            return -1;
        }
        LOGGER.info(LOGGERINFO4 + user.getInfoMobile());
        // 发送手机验证码
        return customerServiceInterface.sendPost(request, user.getInfoMobile());
    }

    /**
     * 检查手机验证码
     *
     * @return 0不同 1相同
     * @throws IOException
     */
    @RequestMapping("validate/getMCode")
    @ResponseBody
    public int getMCode(HttpServletRequest request, String code) throws IOException {
        if (checkLoginStatus(request)) {
            // 验证手机验证码
            return customerServiceInterface.getMCode(request, code);
        } else {
            return -10010;
        }
    }

    /**
     * 跳转我的订单页面
     *
     * @param request
     * @return ModelAndview
     */
    @RequestMapping("/myorder")
    public ModelAndView queryAllOrders(HttpServletRequest request, PageBean pb, String date, String type, String paramString) {
        Map<String, Object> paramMap = null;
        Map<String, Object> resultMap = new HashMap<String, Object>();
        pb.setPageSize(5);
        ModelAndView mav;
        // 设置对象
        SystemsSet isSet;

        // 是否同意退单
        isSet = customerServiceInterface.getIsBackOrder();
        // 检查用户是否登录
        if (request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID) != null) {
            paramMap = new HashMap<String, Object>();
            paramMap.put(CustomerConstantStr.CUSTOMERID, (Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID));
            paramMap.put(CustomerConstantStr.DATE, date);
            paramMap.put(CustomerConstantStr.TYPE, type);
            paramMap.put("paramString", paramString);

            if (type != null && date != null) {
                pb.setUrl("customer/myorder-" + date + "-" + type);
            } else {
                pb.setUrl("customer/myorder");
            }
            resultMap.put(CustomerConstantStr.TYPE, type);
            resultMap.put("paramString", paramString);
            resultMap.put(CustomerConstantStr.DATE, date);
            // 获取当GoodsServiceImpl前会员的退单信息
            if (null != type && "6".equals(type)) {
                resultMap.put(CustomerConstantStr.PB, customerServiceInterface.queryAllBackMyOrders(paramMap, pb));
            } else {
                // 查询所有订单
                resultMap.put(CustomerConstantStr.PB, customerServiceInterface.queryAllMyOrders(paramMap, pb));
            }
            // 是否允许退单
            resultMap.put("isBackOrder", isSet.getIsBackOrder());
            mav = new ModelAndView(CustomerConstantStr.MYORDER).addAllObjects(resultMap);
        } else {
            // 没登录的用户跳转到登录页面
            mav = new ModelAndView(CustomerConstantStr.LOGINREDIRECT).addObject(CustomerConstantStr.URL, CustomerConstantStr.CUSTOMERS + MYORDER).addObject("type", type);
        }
        // 跳转订单页面
        return topAndBottomService.getTopAndBottom(mav);

    }

    /**
     * 根据订单编号查询订单信息
     *
     * @param orderId
     *            订单编号
     * @return ModelAndView
     */
    @RequestMapping("/orderdetails")
    public ModelAndView queryOrderByOrderId(HttpServletRequest request, Long orderId) {
        ModelAndView mav = null;
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if (checkLoginStatus(request)) {
            mav = new ModelAndView(CustomerConstantStr.ORDERDETAIL);
            Long customerId = (Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID);
            // 订单信息
            OrderInfoBean order = (OrderInfoBean) customerServiceInterface.queryOrderByCustIdAndOrderId(orderId, customerId);
            Coupon coupon = couponService.selectCouponByCodeNo(order.getCouponNo());
            if (coupon != null) {
                order.setCouponName(coupon.getCouponName());
            }
            for (int i = 0; i < order.getGoods().size(); i++) {
                // 查询货品详细信息
                GoodsProductDetailViewVo goodsProductDetailViewVo = goodsProductService.queryViewVoByProductId(order.getGoods().get(i).getGoodsId());
                // 规格信息
                order.getGoods().get(i).setSpecVo(goodsProductDetailViewVo.getSpecVo());
            }
            /**
             * edit by 付陈林 2015年12月6日
             * 将积分减的金额，加入到返现中
             * **/
            if(order.getOrderIntegral()!=null){
//                获取积分兑换规则。
                PointSet ps = pointSetService.findPointSet();
                BigDecimal consumption = ps.getConsumption();
                BigDecimal orderIntegral =BigDecimal.valueOf(order.getOrderIntegral());
                BigDecimal orderIntegralValue = consumption.multiply(orderIntegral).divide(new BigDecimal(10));
                order.setPrePrice(order.getPrePrice().add(orderIntegralValue));
            }
            /**
             * edit end
             * */
            // 显示的订单信息
            resultMap.put("order", order);
            // 根据页面类型和模板ID查询单个页面说明
            resultMap.put("unpay", megawizardSerivce.selectByType(5, Long.parseLong(tempService.getCurrTemp().getTempId() + "")));
            // 根据页面类型和模板ID查询单个页面说明
            resultMap.put("pay", megawizardSerivce.selectByType(0, Long.parseLong(tempService.getCurrTemp().getTempId() + "")));
            resultMap.put("relations", queryContainerRelations(request, orderId));
            mav.addAllObjects(resultMap);
            // 根据主键获取单个订单的详细信息

            // 非空验证 订单单号
            if (null != order) {
                LOGGER.info("获取单号为：【" + order.getOrderNo() + "】订单的详细信息！");
            }

            // 订单详细页添加头部和尾部
            return topAndBottomService.getTopAndBottom(mav);
        } else {
            // 登录页面
            return new ModelAndView(new RedirectView(request.getContextPath() + "/login.html?url=customer/detail-" + orderId + ".html"));
        }
    }

    /**
     * 取消订单
     *
     * @param request
     * @param pb
     *            页面数据
     * @param orderId
     *            订单编号
     * @param fromUrl
     *            目标路径
     * @return ModelAndView
     */
    @RequestMapping("/cancelorder")
    public ModelAndView cancelOrder(HttpServletRequest request, PageBean pb, Long orderId, String fromUrl, String reason) {
        ModelAndView mav = null;
        Long customerId = (Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID);
        if (!Pattern.compile("[^\\<\\>]+$").matcher(reason).find()) {
            throw new IllegalArgumentException();
        }
        // 根据主键获取单个订单的详细信息
        Order order = siteOrderService.getPayOrder(orderId);
        // 检查用户是否登录

        if (customerId != null) {
            // 判断是否越权
            if (order != null && order.getCustomerId().equals(customerId)) {
                // 取消订单
                customerServiceInterface.cancelOrder(orderId, reason);
                // 根据订单id查询到优惠劵劵码，修改优惠劵
                orderCouponService.modifyCouponStatus(orderId);
                // 同步订单到Ｅ点宝
                synOrderService.SynOrder(orderId);
            }
            // 控制跳转
            if ("index".equals(fromUrl)) {// 首页
                mav = new ModelAndView(new RedirectView(request.getContextPath() + CustomerConstantStr.CUSTOMERS + INDEX));
            } else if ("myorder".equals(fromUrl)) {
                mav = new ModelAndView(new RedirectView(request.getContextPath() + CustomerConstantStr.CUSTOMERS + MYORDER));
            }
        } else {
            // 没登录的用户跳转到登录页面
            mav = new ModelAndView(CustomerConstantStr.LOGINREDIRECT).addObject(CustomerConstantStr.URL, CustomerConstantStr.CUSTOMERS + MYORDER);
        }

        // 非空验证 订单单号
        if (null != order && null != order.getOrderCode()) {
            OperaLogUtil.addOperaLog(request, this.getCustomer(customerId), "取消订单", "取消订单单号【" + order.getOrderCode() + "】");
            LOGGER.info("取消单号为：【" + order.getOrderCode() + LOGGERINFO5);
        }
        // 跳转订单页面
        return mav;

    }

    /**
     * 确认订单
     *
     * @param request
     * @param pb
     *            页面数据
     * @param orderId
     *            订单编号
     * @param fromUrl
     *            目标路径
     * @return ModelAndView
     */
    @RequestMapping("/comfirmofgooods")
    public ModelAndView comfirmofGoods(HttpServletRequest request, PageBean pb, Long orderId, String fromUrl) {
        ModelAndView mav = null;

        Long customerId = (Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID);
        // 检查用户是否登录
        if (checkLoginStatus(request)) {
            // 确认收货
            customerServiceInterface.comfirmofGoods(orderId);
            // 根据订单id赠送优惠劵信息
            orderCouponService.modifyCouponByOrderId(orderId, (Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID));
            // 控制跳转
            if ("index".equals(fromUrl)) {// 首页
                mav = new ModelAndView(new RedirectView(request.getContextPath() + CustomerConstantStr.CUSTOMERS + INDEX));
            } else if ("myorder".equals(fromUrl)) {
                mav = new ModelAndView(new RedirectView(request.getContextPath() + CustomerConstantStr.CUSTOMERS + MYORDER));
            }
        } else {
            // 没登录的用户跳转到登录页面
            mav = new ModelAndView(CustomerConstantStr.LOGINREDIRECT).addObject(CustomerConstantStr.URL, CustomerConstantStr.CUSTOMERS + MYORDER);
        }
        // 根据主键获取单个订单的详细信息
        Order order = siteOrderService.getPayOrder(orderId);
        // 非空验证 订单单号
        if (null != order.getOrderCode()) {
            OperaLogUtil.addOperaLog(request, this.getCustomer(customerId), "确认订单", "确认订单单号【" + order.getOrderCode() + "】");
            LOGGER.info("确认单号为：【" + order.getOrderCode() + LOGGERINFO5);
        }
        // 跳转订单页面
        return mav;

    }

    /**
     * 跳转我的订单页面
     *
     * @param request
     * @return ModelAndview
     */
    @RequestMapping("/refundlist")
    public ModelAndView refundList(HttpServletRequest request, PageBean pb) {
        Map<String, Object> paramMap = null;
        Map<String, Object> resultMap = new HashMap<String, Object>();
        // 设置显示行数
        pb.setPageSize(4);
        ModelAndView mav = null;
        // 当前登录成功的会员的ID
        Long customerId = (Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID);

        // 检查用户是否登录
        if (checkLoginStatus(request)) {
            paramMap = new HashMap<String, Object>();
            paramMap.put(CustomerConstantStr.CUSTOMERID, customerId);
            paramMap.put(CustomerConstantStr.TYPE, "4");
            pb.setUrl("customer/refundlist");
            // 查询所有订单
            resultMap.put(CustomerConstantStr.PB, customerServiceInterface.queryAllMyOrders(paramMap, pb));
            mav = new ModelAndView(CustomerConstantStr.NEWREFUNDLIST).addAllObjects(resultMap);
            // 根据ID获取会员信息
            Customer customer = customerServiceInterface.queryCustomerById(customerId);
            // 非空验证 会员用户名
            if (null != customer.getCustomerUsername()) {
                LOGGER.info("跳转到会员【" + customer.getCustomerUsername() + "】的个人订单页面！");
            }
        } else {
            // 没登录的用户跳转到登录页面
            mav = new ModelAndView(CustomerConstantStr.LOGINREDIRECT).addObject(CustomerConstantStr.URL, CustomerConstantStr.CUSTOMERS + REFUNDLIST);
        }

        // 跳转订单页面
        return topAndBottomService.getTopAndBottom(mav);

    }

    /**
     * 删除订单
     *
     * @param request
     * @param pb
     *            页面数据
     * @param orderId
     *            订单编号
     * @param orderId
     *            目标路径
     * @return ModelAndView
     */
    @RequestMapping(CustomerConstantStr.CUSTOMERS + "/delorder")
    public ModelAndView delOrder(HttpServletRequest request, PageBean pb, Long orderId) {
        ModelAndView mav;

        Long customerId = (Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID);
        // 检查用户是否登录
        if (checkLoginStatus(request)) {
            // 删除订单
            customerServiceInterface.delOrder(orderId);
            mav = new ModelAndView(new RedirectView(request.getContextPath() + CustomerConstantStr.CUSTOMERS + REFUNDLIST));
        } else {
            // 没登录的用户跳转到登录页面
            mav = new ModelAndView(CustomerConstantStr.LOGINREDIRECT).addObject(CustomerConstantStr.URL, CustomerConstantStr.CUSTOMERS + REFUNDLIST);
        }
        // 根据主键获取单个订单的详细信息
        Order order = siteOrderService.getPayOrder(orderId);
        // 非空验证 订单单号
        if (null != order.getOrderCode()) {
            OperaLogUtil.addOperaLog(request, this.getCustomer(customerId), "删除订单", "删除订单单号【" + order.getOrderCode() + "】");
            LOGGER.info("删除单号为：【" + order.getOrderCode() + LOGGERINFO5);
        }

        // 跳转订单页面
        return topAndBottomService.getTopAndBottom(mav);

    }

    /**
     * 会员积分页
     *
     * @return ModelAndView
     */
    @RequestMapping("/myintegral")
    public ModelAndView queryCustomerIntegral(HttpServletRequest request, PageBean pb, Long date) {
        ModelAndView mav;
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        pb.setUrl("1".equals(date.toString()) ? CustomerConstantStr.CUSTOMER + "/myintegral" : CustomerConstantStr.CUSTOMER + "/myintegral-" + date);
        Long customerId = null;
        // 设置显示的行数
        pb.setPageSize(8);

        // 验证登录
        if (checkLoginStatus(request)) {
            customerId = (Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID);
            paramMap.put(CustomerConstantStr.DATE, date);
            paramMap.put(CustomerConstantStr.CUSTOMERID, customerId);
            // 根据会员编号查询相应的会员积分明细
            resultMap.put(CustomerConstantStr.PB, customerServiceInterface.selectAllCustomerPoint(paramMap, pb));
            // 根据会员编号查找会员详细信息
            resultMap.put(CustomerConstantStr.CUSTOMER, customerServiceInterface.queryCustomerByCustomerId(customerId));
            resultMap.put(CustomerConstantStr.DATE, date);
            mav = new ModelAndView(CustomerConstantStr.NEWMYINTEGRAL).addAllObjects(resultMap);
        } else {
            mav = new ModelAndView(CustomerConstantStr.LOGINREDIRECT).addObject(CustomerConstantStr.URL, CustomerConstantStr.CUSTOMERS + "/myintegral.html");
        }
        // 记录日志
        LOGGER.info("跳转会员积分页,当前登录成功的会员为：" + this.getCustomer(customerId));
        return topAndBottomService.getTopAndBottom(mav);

    }

    /**
     * 会员信息页
     *
     * @return ModelAndView
     */
    @RequestMapping("/myinfo")
    public ModelAndView showMyInfo(HttpServletRequest request, PageBean pb, Double date) {
        ModelAndView mav;

        // 验证登录
        if (checkLoginStatus(request)) {
            mav = new ModelAndView(CustomerConstantStr.NEWOWNERINFO);
            // 查询会员基本信息
            mav.addObject(CustomerConstantStr.CUSTOMER, customerServiceInterface.selectByPrimaryKey((Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID)));
        } else {
            mav = new ModelAndView(CustomerConstantStr.LOGINREDIRECT).addObject(CustomerConstantStr.URL, CustomerConstantStr.CUSTOMERS + "/myinfo.html");
        }
        return topAndBottomService.getTopAndBottom(mav);

    }

    /**
     * 跳转浏览记录页面
     *
     * @param request
     * @param pb
     * @return ModelAndView
     */
    @RequestMapping("/browserecord")
    public ModelAndView showMyBrowserecord(HttpServletRequest request, PageBean pb) {
        ModelAndView mav;
        Map<String, Object> resultMap = new HashMap<String, Object>();

        // 验证登录
        if (checkLoginStatus(request)) {
            // 查询浏览记录
            resultMap.put("browses", browserecordService.selectBrowserecord((Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID)));
            resultMap.put("today", new Date());
            mav = new ModelAndView(CustomerConstantStr.NEWBROWSERECORD);
            mav.addAllObjects(resultMap);
        } else {
            mav = new ModelAndView(CustomerConstantStr.LOGINREDIRECT).addObject(CustomerConstantStr.URL, CustomerConstantStr.CUSTOMERS + BROWSERECORD);
        }
        return topAndBottomService.getTopAndBottom(mav);

    }

    /**
     * 跳转我的收藏页面
     *
     * @param request
     * @param pb
     * @return ModelAndView
     */
    @RequestMapping("/myfollw")
    public ModelAndView showMyFollow(HttpServletRequest request, PageBean pb) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        pb.setUrl(CustomerConstantStr.CUSTOMER + "/myfollw");
        ModelAndView mav;

        // 验证登录
        if (checkLoginStatus(request)) {
            paramMap.put(CustomerConstantStr.CUSTOMERID, (Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID));
            // 查询收藏记录
            mav = new ModelAndView(CustomerConstantStr.MYFOLLOW).addObject(CustomerConstantStr.PB, customerFollowService.selectCustomerFollow(paramMap, pb));
        } else {
            mav = new ModelAndView(CustomerConstantStr.LOGINREDIRECT).addObject(CustomerConstantStr.URL, CustomerConstantStr.CUSTOMERS + MYFOLLW);
        }
        return topAndBottomService.getTopAndBottom(mav);

    }

    /**
     * 取消关注
     *
     * @param request
     * @param followId
     *            关注编号
     * @return ModelAndView
     */
    @RequestMapping("/cancelfollow")
    public ModelAndView cancelFollow(HttpServletRequest request, Long followId) {
        ModelAndView mav;

        // 验证登录
        if (checkLoginStatus(request)) {
            // 用户Id
            Long customerId = (Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID);
            // 取消关注
            customerFollowService.deleteFollow(followId, customerId);
            mav = new ModelAndView(new RedirectView(request.getContextPath() + CustomerConstantStr.CUSTOMERS + MYFOLLW));
        } else {
            mav = new ModelAndView(CustomerConstantStr.LOGINREDIRECT).addObject(CustomerConstantStr.URL, CustomerConstantStr.CUSTOMERS + MYFOLLW);
        }
        // 记录日志
        LOGGER.info("取消商品关注，关注ID为：" + followId);
        return topAndBottomService.getTopAndBottom(mav);
    }

    /**
     * 取消关注
     *
     * @param request
     * @param likeId
     *            关注编号
     * @return ModelAndView
     */
    @RequestMapping("/cancelbrowse")
    public ModelAndView cancelBrowse(HttpServletRequest request, Long likeId) {
        ModelAndView mav;

        // 验证登录
        if (checkLoginStatus(request)) {
            // 用户Id
            Long customerId = (Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID);
            // 取消关注
            browserecordService.deleteByPrimaryKey(likeId, customerId);
            mav = new ModelAndView(new RedirectView(request.getContextPath() + CustomerConstantStr.CUSTOMERS + BROWSERECORD));
        } else {
            mav = new ModelAndView(CustomerConstantStr.LOGINREDIRECT).addObject(CustomerConstantStr.URL, CustomerConstantStr.CUSTOMERS + BROWSERECORD);
        }
        return topAndBottomService.getTopAndBottom(mav);
    }

    /**
     * 修改个人信息
     *
     * @return 0失败 1成功
     */
    @RequestMapping(CustomerConstantStr.CUSTOMERS + "/modifyInfo")
    @ResponseBody
    public int modifyCustomerInfo(HttpServletRequest request, CustomerAllInfo allInfo, String flag) {
        // 验证登录
        if (checkLoginStatus(request)) {
            // 当前登录的会员
            Long customerId = (Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID);
            // flag 为1 则修改会员昵称 反之不修改 此处保存修改后的昵称 前台会显示改变后的昵称
            if ("1".equals(flag)) {
                request.getSession().setAttribute("cust", allInfo);
            }
            allInfo.setCustomerId(customerId);
            // 修改会员信息
            return customerServiceInterface.modifyCustomerInfo(allInfo, flag);
        } else {
            return -1;
        }
    }

    /**
     * 跳转我的投诉页面
     *
     * @param request
     * @param pb
     * @return ModelAndView
     */
    @RequestMapping(CustomerConstantStr.CUSTOMERS + "/ordercomplain")
    public ModelAndView orderComplain(HttpServletRequest request, PageBean pb) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        Map<String, Object> resultMap = new HashMap<String, Object>();
        pb.setUrl(CustomerConstantStr.CUSTOMER + "/ordercomplain");
        ModelAndView mav;

        // 验证登录
        if (checkLoginStatus(request)) {
            paramMap.put(CustomerConstantStr.CUSTOMERID, (Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID));
            // 查询可以投诉的订单
            resultMap.put(CustomerConstantStr.PB, customerServiceInterface.queryOrdersForComplain(paramMap, pb));
            resultMap.put("id", "0");
            mav = new ModelAndView(CustomerConstantStr.NEWORDERCOMPLAIN).addAllObjects(resultMap);
        } else {
            mav = new ModelAndView(CustomerConstantStr.LOGINREDIRECT).addObject(CustomerConstantStr.URL, CustomerConstantStr.CUSTOMERS + "/ordercomplain.html");
        }
        // 日志记录
        LOGGER.info("跳转我的投诉页面");
        return topAndBottomService.getTopAndBottom(mav);

    }

    /**
     * 跳转 填写投诉内容页面
     *
     * @param request
     * @param orderNo
     * @return ModelAndView
     */
    @RequestMapping(CustomerConstantStr.CUSTOMERS + "/tocomplain")
    public ModelAndView toComplain(HttpServletRequest request, String orderNo) {
        ModelAndView mav;
        Map<String, Object> resultMap = new HashMap<String, Object>();

        // 验证登录
        if (checkLoginStatus(request)) {
            resultMap.put("orderNo", orderNo);
            mav = new ModelAndView(CustomerConstantStr.NEWTOCOMPLAIN).addAllObjects(resultMap);
        } else {
            mav = new ModelAndView(CustomerConstantStr.LOGINREDIRECT).addObject(CustomerConstantStr.URL, CustomerConstantStr.CUSTOMERS + INDEX);
        }
        LOGGER.info("跳转 填写投诉内容页面成功！");
        return topAndBottomService.getTopAndBottom(mav);

    }

    /**
     * 添加投诉
     *
     * @param request
     * @param orderComplain
     * @return ModelAndView
     */
    @RequestMapping(CustomerConstantStr.CUSTOMERS + "/addcomplain")
    @ResponseBody
    public int addComplain(HttpServletRequest request, @Valid OrderComplain orderComplain) {
        // 保存投诉人
        CustomerAllInfo customerlain = null;
        // 当前登录的会员
        Long customerId = (Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID);
        // 获取当前登录会员信息
        CustomerAllInfo customerAllInfo = customerServiceInterface.selectByPrimaryKey(customerId);
        if (orderComplain != null) {
            // 判断会员是否越权
            if (!orderComplain.getCustomerId().equals(customerId)) {
                return -1;
            }
            // 判断订单号是否是该会员订单
            Long orderFlag = customerServiceInterface.checkexistsByIdAndCode(customerId, orderComplain.getOrderNo());
            if (orderFlag <= 0) {
                return -1;
            }
        }
        // 获取投诉人信息
        if (null != customerId) {
            customerlain = customerServiceInterface.selectByPrimaryKey(customerId);
        }
        // 非空验证 用户名
        if (null != customerAllInfo.getCustomerUsername()) {
            OperaLogUtil.addOperaLog(request, customerAllInfo.getCustomerUsername(), "添加投诉", "投诉人为【" + customerlain.getCustomerUsername() + "】");
        }
        // 检查登陆状态
        if (checkLoginStatus(request)) {
            // 添加订单投诉
            return orderComplainService.addComplain(orderComplain);
        } else {
            return -1;
        }
    }

    /**
     * 添加投诉
     *
     * @param request
     * @return ModelAndView
     */
    @RequestMapping(CustomerConstantStr.CUSTOMERS + "/complainsuccess")
    public ModelAndView complainSuccess(HttpServletRequest request) {
        // 检查登陆状态
        if (checkLoginStatus(request)) {
            return topAndBottomService.getTopAndBottom(new ModelAndView(CustomerConstantStr.CUSTOMERS + "/success"));
        } else {
            return new ModelAndView(new RedirectView(request.getContextPath() + "/login.html?url=index.html"));
        }
    }

    /**
     * 投诉列表
     *
     * @param request
     * @param pb
     * @return ModelAndView
     */
    @RequestMapping(CustomerConstantStr.CUSTOMERS + "/complainlist")
    public ModelAndView complainList(HttpServletRequest request, PageBean pb) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        Map<String, Object> resultMap = new HashMap<String, Object>();
        pb.setUrl(CustomerConstantStr.CUSTOMER + "/complainlist");
        ModelAndView mav;

        // 验证登录
        if (checkLoginStatus(request)) {
            paramMap.put(CustomerConstantStr.CUSTOMERID, (Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID));
            // 查询投诉列表
            resultMap.put(CustomerConstantStr.PB, orderComplainService.queryComplainList(paramMap, pb));
            resultMap.put("id", "1");
            mav = new ModelAndView(CustomerConstantStr.NEWORDERCOMPLAIN).addAllObjects(resultMap);
        } else {
            mav = new ModelAndView(CustomerConstantStr.LOGINREDIRECT).addObject(CustomerConstantStr.URL, CustomerConstantStr.CUSTOMERS + "/ordercomplain.html");
        }
        return topAndBottomService.getTopAndBottom(mav);

    }

    /**
     * 根据订单编号查询订单物流信息
     *
     * @param orderId
     *            订单编号
     * @return
     * @author NINGPAI-LIH
     */
    @RequestMapping("/queryorderexpress")
    @ResponseBody
    public List<OrderContainerUtil> queryOrderExpress(HttpServletRequest request, Long orderId) {
        // 返回订单的物流信息
        if (checkLoginStatus(request)) {
            // 根据主键获取单个订单的详细信息
            Order order = siteOrderService.getPayOrder(orderId);
            // 非空验证 订单单号
            if (null != order.getOrderCode()) {
                LOGGER.info(LOGGERINFO6 + order.getOrderCode() + "】的物流信息！");
            }
            // 查询订单包裹表
            return siteOrderService.getExpressNo(orderId);
        } else {
            return new ArrayList<OrderContainerUtil>();
        }

    }

    /**
     * 查询订单包裹
     *
     * @param orderId
     * @return
     */
    @RequestMapping("queryContainerRelations")
    @ResponseBody
    public List<OrderContainerRelation> queryContainerRelations(HttpServletRequest request, Long orderId) {
        // 查询订单包裹
        List<OrderContainerRelation> relations = orderService.queryContainerRalation(orderId);
        for (OrderContainerRelation relation : relations) {
            try {
                // 从快递100接口获取物流信息html链接
                relation.setExpressInfoUrl(orderService.queryExpressInfoUrl(relation));
                // 根据主键获取单个订单的详细信息
                Order order = siteOrderService.getPayOrder(orderId);
                // 非空验证 订单单号
                if (null != order.getOrderCode()) {
                    LOGGER.info(LOGGERINFO6 + order.getOrderCode() + "】的的包裹！");
                }
            } catch (Exception e) {
                LOGGER.error("查询包裹出错！", e);
            }
        }
        return relations;

    }

    /**
     * 查询订单包裹
     *
     * @param orderId
     * @return
     */
    @RequestMapping("customer/queryContainerRelations")
    @ResponseBody
    public List<OrderContainerRelation> queryContainerRelation(HttpServletRequest request, Long orderId) {
        // 查询订单包裹
        List<OrderContainerRelation> relations = orderService.queryContainerRalation(orderId);
        for (OrderContainerRelation relation : relations) {
            try {
                // 从快递100接口获取物流信息html链接
                relation.setExpressInfoUrl(orderService.queryExpressInfoUrl(relation));
                // 根据主键获取单个订单的详细信息
                Order order = siteOrderService.getPayOrder(orderId);
                // 非空验证 订单单号
                if (null != order.getOrderCode()) {
                    LOGGER.info(LOGGERINFO6 + order.getOrderCode() + "】的的包裹！");
                }
            } catch (Exception e) {
                LOGGER.error("查询包裹出错！", e);
            }
        }
        return relations;

    }

    /**
     * 删除退单
     *
     * @param backOrderId
     *            :要操作的退单ID
     * @return
     */
    @RequestMapping("customer/deleteBackOrderById")
    @ResponseBody
    public int deleteBackOrderById(HttpServletRequest request, Long backOrderId) {
        Long customerId = (Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID);
        // 删除单个退单信息
        return siteOrderService.deleteBackOrderById(backOrderId, customerId);
    }

    /**
     * 删除订单
     *
     * @param orderId
     * @return
     */
    @RequestMapping("customer/deleteOrderById")
    @ResponseBody
    public int deleteOrderById(HttpServletRequest request, Long orderId) {
        Long customerId = (Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID);
        int count = 0;
        if (customerId != null) {
            // 根据主键获取单个订单的详细信息
            Order order = siteOrderService.getPayOrder(orderId);
            // 判断是否越权
            if (order != null && order.getCustomerId().equals(customerId)) {
                // 删除订单
                count = orderService.deleteOrderById(orderId);
            }
        }

        return count;
    }

    /**
     * 跳转登录
     *
     * @param mav
     *            ModelAndView
     */
    public void jumpLogin(HttpServletRequest request, ModelAndView mav, String url) {
        mav.setView(new RedirectView(CustomerConstantStr.CUSTOMERS + "/login.html"));
    }

    /**
     * 检查登陆状态
     *
     * @param request
     * @return
     */
    private boolean checkLoginStatus(HttpServletRequest request) {
        if (request.getSession().getAttribute("cust") == null && request.getSession().getAttribute("user") == null) {
            return false;
        }
        return true;
    }

    public OrderCouponService getOrderCouponService() {
        return orderCouponService;
    }

    @Resource(name = "OrderCouponService")
    public void setOrderCouponService(OrderCouponService orderCouponService) {
        this.orderCouponService = orderCouponService;
    }

    public SiteOrderService getSiteOrderService() {
        return siteOrderService;
    }

    @Resource(name = "SiteOrderService")
    public void setSiteOrderService(SiteOrderService siteOrderService) {
        this.siteOrderService = siteOrderService;
    }

    public CustomerServiceInterface getCustomerServiceInterface() {
        return customerServiceInterface;
    }

    /**
     * spring 注入属性
     * 
     * @param customerServiceInterface
     */
    @Resource(name = "customerServiceSite")
    public void setCustomerServiceInterface(CustomerServiceInterface customerServiceInterface) {
        this.customerServiceInterface = customerServiceInterface;
    }

    public CouponService getCouponService() {
        return couponService;
    }

    @Resource(name = "CouponService")
    public void setCouponService(CouponService couponService) {
        this.couponService = couponService;
    }

    public BrowserecordService getBrowserecordService() {
        return browserecordService;
    }

    @Resource(name = "browserecordService")
    public void setBrowserecordService(BrowserecordService browserecordService) {
        this.browserecordService = browserecordService;
    }

    public CustomerFollowService getCustomerFollowService() {
        return customerFollowService;
    }

    @Resource(name = "customerFollowServiceSite")
    public void setCustomerFollowService(CustomerFollowService customerFollowService) {
        this.customerFollowService = customerFollowService;
    }

    public OrderComplainService getOrderComplainService() {
        return orderComplainService;
    }

    @Resource(name = "orderComplainService")
    public void setOrderComplainService(OrderComplainService orderComplainService) {
        this.orderComplainService = orderComplainService;
    }

    public EmailUtils getEmailUtils() {
        return emailUtils;
    }

    @Resource(name = "emailUtilsSite")
    public void setEmailUtils(EmailUtils emailUtils) {
        this.emailUtils = emailUtils;
    }

    public TopAndBottomService getTopAndBottomService() {
        return topAndBottomService;
    }

    @Resource(name = "TopAndBottomService")
    public void setTopAndBottomService(TopAndBottomService topAndBottomService) {
        this.topAndBottomService = topAndBottomService;
    }

    public MegawizardService getMegawizardSerivce() {
        return megawizardSerivce;
    }

    @Resource(name = "MegawizardService")
    public void setMegawizardSerivce(MegawizardService megawizardSerivce) {
        this.megawizardSerivce = megawizardSerivce;
    }

    public TempService getTempService() {
        return tempService;
    }

    @Resource(name = "TempService")
    public void setTempService(TempService tempService) {
        this.tempService = tempService;
    }

}
