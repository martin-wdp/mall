/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.site.login.controller;

import com.ningpai.common.util.ConstantUtil;
import com.ningpai.customer.bean.Customer;
import com.ningpai.customer.service.CustomerServiceMapper;
import com.ningpai.index.service.TopAndBottomService;
import com.ningpai.site.customer.vo.CustomerConstantStr;
import com.ningpai.site.login.service.LoginService;
import com.ningpai.system.service.AuthService;
import com.ningpai.util.MyLogger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录Controller
 *
 * @author NINGPAI-zhangqiang
 * @version 0.0.1
 * @since 2014年4月15日 下午3:06:26
 */
@Controller
public class LoginController {

    /**
     * 记录日志对象
     */
    private static final MyLogger LOGGER = new MyLogger(LoginController.class);

    private static final String LOGIN_LOGIN_JD = "/login/login_jd";
    private static final String INDEX_HTML = "index.html";
    private static final String _NPSTORE_USERNAME = "_npstore_username";

    /**
     * 会员登陆限制输入密码错误的次数
     */
    private static final Long LOGINMAXERRORCOUNT = 5L;

    /**
     * 每次达到错误次数 要求锁定的时间(分钟)
     */
    private static final Long LOCKMAXTIME = 30L;

    /**
     * spring 注解 登录service
     */
    private LoginService loginService;

    /**
     * 获取已启用的第三方登录接口
     */
    @Resource(name = "authService")
    private AuthService authService;

    /**
     * 获取头尾
     */
    private TopAndBottomService topAndBottomService;

    /**
     * 会员
     */
    private CustomerServiceMapper customerService;

    /**
     * 跳转登录
     *
     * @return ModelAndView
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("/customer/login")
    public ModelAndView login(HttpServletRequest request, String url, String isTemp, Long[] box) throws UnsupportedEncodingException {
        // 删除临时用户的session
        request.getSession().removeAttribute("is_temp_cust");
        Map<String, Object> resultMap = new HashMap<String, Object>();
        // 临时用户提交的参数
        resultMap.put("isTemp", isTemp);
        resultMap.put("box", box);
        String status = request.getParameter("status");
        String urlEmp = url;
        String preUrl = request.getHeader("Referer");
        if ("1".equals(status)) {
            setResultMap(request, resultMap, urlEmp);
            resultMap.put("t", authService.findByShow());
            // return topAndBottomService.getBottom(new
            // ModelAndView("/login/login").addAllObjects(resultMap));
            return topAndBottomService.getBottom(new ModelAndView(LOGIN_LOGIN_JD).addAllObjects(resultMap));
        }
        if (preUrl != null) {
            String strRegex = "^((https|http|ftp|rtsp|mms)?://)"
            // ftp的user@
                    + "?(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?"
                    // IP形式的URL- 199.194.52.184
                    // 允许IP和DOMAIN（域名）
                    + "(([0-9]{1,3}\\.){3}[0-9]{1,3}" + "|"
                    // 域名- www.
                    + "([0-9a-z_!~*'()-]+\\.)*"
                    // 二级域名
                    + "([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\\."
                    // first level domain- .com or .museum
                    + "[a-z]{2,6}|"
                    // 测试用 : 本地localhost.
                    + "([0-9a-z][0-9a-z-]{0,61}))"
                    // 端口- :80
                    + "(:[0-9]{1,4})?" +
                    // 项目名称
                    request.getContextPath() + "/";
            urlEmp = preUrl.replaceFirst(strRegex, "");
        } else {
            preUrl = (String) request.getSession().getAttribute("preferUrl");
            preUrl = preUrl == null ? urlEmp : preUrl;
            if (preUrl != null) {
                urlEmp = preUrl;
            } else {
                urlEmp = INDEX_HTML;
            }
        }
        if (urlEmp.indexOf("register") != -1 || urlEmp.indexOf("success") != -1 || urlEmp.length() == 0 || urlEmp.indexOf("updatesucess") != -1) {
            urlEmp = INDEX_HTML;
        }
        if (urlEmp.indexOf("validateidentity") != -1 || urlEmp.indexOf("reirectpem") != -1) {
            urlEmp = "customer/securitycenter.html";
        }
        if (urlEmp.indexOf(".html") == -1) {
            urlEmp = urlEmp + ".html";
        }
        setResultMap(request, resultMap, urlEmp);
        resultMap.put("t", authService.findByShow());
        // return topAndBottomService.getBottom(new
        // ModelAndView("/login/login").addAllObjects(resultMap));
        return topAndBottomService.getBottom(new ModelAndView(LOGIN_LOGIN_JD).addAllObjects(resultMap));

    }

    /**
     * 退出 跳转登录
     *
     * @return ModelAndView
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("/customer/logout")
    public ModelAndView loginOut(HttpServletRequest request, String url) throws UnsupportedEncodingException {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String url1 = url;
        if (url1 == null) {
            url1 = INDEX_HTML;
        }
        // 清空session已登录数据
        request.getSession().removeAttribute("cust");
        /*request.getSession().removeAttribute("vip");*/
        request.getSession().setAttribute("vip", "0");//退出后，重设会员状态为普通会员 2015-12-11 wuyanbo修改
        request.getSession().removeAttribute("customerId");
        request.getSession().removeAttribute("isThirdLogin");
        request.getSession().removeAttribute("bsetDomain");
        resultMap.put("t", authService.findByShow());
        setResultMap(request, resultMap, url1);
        return topAndBottomService.getBottom(new ModelAndView(LOGIN_LOGIN_JD).addAllObjects(resultMap));

    }

    /**
     * 验证登录
     *
     * @param request
     * @param username
     *            用户名
     * @param password
     *            密码
     * @param url
     *            跳转路径
     * @return
     * @throws UnsupportedEncodingException
     */
    /*@RequestMapping("/checklogin")*/
    @ResponseBody
    public Object checkLogin_Back(HttpServletRequest request, HttpServletResponse response, String username, String password, String url, String type)
            throws UnsupportedEncodingException {
        String url1 = url;
        int status = loginService.checkCustomerExists(request, username, password);
        if (status == 1) {
            // 根据用户名 获取单个的会员对象
            Customer customer = customerService.getCustomerByUsername(username);
            // 判断是否锁定 锁定时间是否为空 账户锁定期间 输入正确密码 也要等锁定时间到期
            if (customer != null && null != customer.getLoginLockTime()) {
                // 判断是否锁定 锁定时间是否为空
                    // 计算是否锁定时间
                    status = getTimeDifference(customer.getLoginLockTime(), customer.getLoginTime(), customer, request, username, password);
                    if (status == 4 || status == 8) {
                        return status;
                    }

                    /*
                     * else { return checkUser(type, username, password,
                     * response, customer, url); }
                     */
            }
            if ("0".equals(type)) {
                Cookie cookie = new Cookie(_NPSTORE_USERNAME, URLEncoder.encode(username, CustomerConstantStr.UTF));
                // 设置七天
                cookie.setMaxAge(7 * 24 * 3600);
                response.addCookie(cookie);
            } else if ("1".equals(type)) {
                Cookie nameCookie = new Cookie(_NPSTORE_USERNAME, URLEncoder.encode(username, CustomerConstantStr.UTF));
                // 设置七天
                nameCookie.setMaxAge(7 * 24 * 3600);
                response.addCookie(nameCookie);

                Cookie pwdCookie = new Cookie("_npstore_pwd", URLEncoder.encode(password, CustomerConstantStr.UTF));
                // 设置七天
                pwdCookie.setMaxAge(7 * 24 * 3600);
                response.addCookie(pwdCookie);

            }
            // 删除cookie
            Cookie cook = new Cookie("_npstore_shopcar", null);
            cook.setMaxAge(-1);
            cook.setPath("/");
            response.addCookie(cook);
            //ADD BY LY START URL &amp;转化成 &
            if(url1.indexOf("&amp;")>0){
                url1=url1.replaceAll("&amp;","&");
            }
            //ADD BY LY END
            if ("order/suborder.html".equals(url1) || "suborder.html".equals(url1)) {
                url1 = INDEX_HTML;
            }
            if ("order/subgrouponorder.html".equals(url1) || "subgrouponorder.html".equals(url1)) {
                url1 = INDEX_HTML;
            }
            if ("order/submrorder.html".equals(url1) || "submrorder.html".equals(url1)) {
                url1 = INDEX_HTML;
            }

            LOGGER.info("验证会员【" + username + "】登陆");
            customer.setLoginErrorCount(0L);
            customerService.updateCusErrorCount(customer);
            return url1;
        }else if(status==3){
           //用户被冻结
            return status;
        } else if (status == 2) {
            // 用户名不存在
            return status;
        } else {
            // 如果密码错误 就记录当前会员登陆的错误次数
            return this.updateOrrerCount(username, request, password);
        }
    }

    /**
     * 验证登录
     *
     * @param request
     * @param username
     *            用户名
     * @param password
     *            密码
     * @param url
     *            跳转路径
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("/checklogin")
    @ResponseBody
    public Object checkLogin(HttpServletRequest request, HttpServletResponse response, String username, String password, String url, String type)
            throws UnsupportedEncodingException {
        String url1 = url;
        int status = loginService.checkCustomerExists(request, username, password);
        if (status == 1) {
            // 根据用户名 获取单个的会员对象
            Customer customer = customerService.getCustomerByUsername(username);

            if ("0".equals(type)) {
                Cookie cookie = new Cookie(_NPSTORE_USERNAME, URLEncoder.encode(username, CustomerConstantStr.UTF));
                // 设置七天
                cookie.setMaxAge(7 * 24 * 3600);
                response.addCookie(cookie);
            } else if ("1".equals(type)) {
                Cookie nameCookie = new Cookie(_NPSTORE_USERNAME, URLEncoder.encode(username, CustomerConstantStr.UTF));
                // 设置七天
                nameCookie.setMaxAge(7 * 24 * 3600);
                response.addCookie(nameCookie);

                Cookie pwdCookie = new Cookie("_npstore_pwd", URLEncoder.encode(password, CustomerConstantStr.UTF));
                // 设置七天
                pwdCookie.setMaxAge(7 * 24 * 3600);
                response.addCookie(pwdCookie);

            }
            // 删除cookie
            Cookie cook = new Cookie("_npstore_shopcar", null);
            cook.setMaxAge(-1);
            cook.setPath("/");
            response.addCookie(cook);
            //ADD BY LY START URL &amp;转化成 &
            if(url1.indexOf("&amp;")>0){
                url1=url1.replaceAll("&amp;","&");
            }
            //ADD BY LY END
            if ("order/suborder.html".equals(url1) || "suborder.html".equals(url1)) {
                url1 = INDEX_HTML;
            }
            if ("order/subgrouponorder.html".equals(url1) || "subgrouponorder.html".equals(url1)) {
                url1 = INDEX_HTML;
            }
            if ("order/submrorder.html".equals(url1) || "submrorder.html".equals(url1)) {
                url1 = INDEX_HTML;
            }

            LOGGER.info("验证会员【" + username + "】登陆");
            return url1;
        }else if(status==3){
            //用户被冻结
            return status;
        } else if (status == 2) {
            // 用户名不存在
            return status;
        } else {
            // 如果密码错误 就记录当前会员登陆的错误次数
            //return this.updateOrrerCount(username, request, password);
            return 0;//this.updateOrrerCount(username, request, password);
        }
    }

    /**
     * 设置当前会员登陆的错误次数
     *
     * @param username
     *            用户名
     */
    public Object updateOrrerCount(String username, HttpServletRequest request, String password) {
        // 根据用户名 获取单个的会员对象
        Customer customer = customerService.getCustomerByUsername(username);
        // 判断用户名是否有效 限制登陆的次数是否有值 锁定的时间是否有值
        if (null != customer) {
            // 判断是否锁定 锁定时间是否为空
            if (null == customer.getLoginLockTime()) {
                // 修改会员登陆错误的次数加1
                Long loginErrorCount = Long.valueOf(customer.getLoginErrorCount()) + 1;
                customer.setLoginErrorCount(loginErrorCount);
                // 修改单个会员的信息
                customerService.updateCusErrorCount(customer);
            }
            // 判断当前会员登陆的错误次数是否大于限制的登陆次数
            if (customer.getLoginErrorCount() > LOGINMAXERRORCOUNT) {
                // 判断是否锁定 锁定时间是否为空
                if (null != customer.getLoginLockTime()) {
                    return getTimeDifference(customer.getLoginLockTime(), new Date(), customer, request, username, password);
                }
                // 如果超过限制的登陆次数就保存当前时间 用来作为锁定的时间
                customer.setLoginLockTime(new Date());
                customerService.updateCusLock(customer);
            }
        }
        return 0;
    }

    /**
     * 计算两个Date时间差
     *
     * @param loginLockTime
     *            账户锁定时间
     * @param loginTime
     *            登陆时间
     */
    public int getTimeDifference(Date loginLockTime, Date loginTime, Customer customer, HttpServletRequest request, String username, String password) {
        // 创建一个用来处理时间的函数
        Calendar c1 = Calendar.getInstance();
        c1.clear();
        Calendar c2 = Calendar.getInstance();
        c2.clear();
        // 锁定日期
        String lockTimeYear = loginLockTime.toString();
        // 登陆日期
        String loginTimeYear = loginTime.toString();
        // 锁定日期年份
        lockTimeYear = lockTimeYear.substring(lockTimeYear.length() - 4, lockTimeYear.length());
        // 登陆日期年份
        loginTimeYear = loginTimeYear.substring(loginTimeYear.length() - 4, loginTimeYear.length());
        // 设置需要计算的时间
        c1.set(Integer.valueOf(lockTimeYear), loginLockTime.getMonth() + 1, loginLockTime.getDate(), loginLockTime.getHours(), loginLockTime.getMinutes());
        c2.set(Integer.valueOf(loginTimeYear), loginTime.getMonth() + 1, loginTime.getDate(), loginTime.getHours(), loginTime.getMinutes());

        // Print out the dates
        // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd:HH:mm");

        long time1 = c1.getTimeInMillis();
        long time2 = c2.getTimeInMillis();

        // 相差的毫秒
        long diff = time2 - time1;
        /*
         * // 相差的秒数 long diffSec = diff / 1000;
         * 
         * // 相差的小时数 long diffHours = diff / (60 * 60 * 1000); // 相差的天书 long
         * diffDays = diff / (24 * 60 * 60 * 1000); //判断达到账户锁定时间
         */
        // 相差的分钟数
        long diffMin = diff / (60 * 1000);
        // 此次登陆时间与账户锁定的时间 差距是小于 系统规定的最大限制锁定时间
        if (diffMin < LOCKMAXTIME) {
            // 如果锁定时间没到 客户发送登陆请求 重新插入当前时间 如锁定时间 如果超过限制的登陆次数就保存当前时间 用来作为锁定的时间
            customer.setLoginLockTime(new Date());
            customerService.updateCusLock(customer);
            return 8;
        } else {
            // 大于规定的限制锁定时间 就清空 错误次数与锁定时间 （状态清零）
            customer.setLoginLockTime(null);
            customer.setLoginErrorCount(0L);
            customerService.updateCusErrorCount(customer);
            // 超过锁定限制时间 在重新查一次账户 如果用户密码正确 直接返回
            int status = loginService.checkCustomerExists(request, username, password);
            if (status == 1) {
                return 1;
            } else {
                return 4;
            }
        }
    }

    /**
     * 设置结果集
     * 
     * @param request
     * @param resultMap
     * @param url
     * @throws UnsupportedEncodingException
     */
    public void setResultMap(HttpServletRequest request, Map<String, Object> resultMap, String url) throws UnsupportedEncodingException {
        String username = "";
        // 读取cookie中的信息
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie != null && _NPSTORE_USERNAME.equals(cookie.getName())) {
                    username = URLDecoder.decode(cookie.getValue(), ConstantUtil.UTF);
                    break;
                }
            }
        }
        resultMap.put("username", username);
        resultMap.put("url", url);
    }

    public LoginService getLoginService() {
        return loginService;
    }

    @Resource(name = "loginServiceSite")
    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    public TopAndBottomService getTopAndBottomService() {
        return topAndBottomService;
    }

    @Resource(name = "TopAndBottomService")
    public void setTopAndBottomService(TopAndBottomService topAndBottomService) {
        this.topAndBottomService = topAndBottomService;
    }

    public CustomerServiceMapper getCustomerServiceMapper() {
        return customerService;
    }

    @Resource(name = "customerServiceMapper")
    public void setCustomerServiceMapper(CustomerServiceMapper customerService) {
        this.customerService = customerService;
    }
}
