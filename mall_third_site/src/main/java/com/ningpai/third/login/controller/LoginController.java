/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.third.login.controller;

import com.ningpai.customer.bean.Customer;
import com.ningpai.customer.dao.CustomerMapper;
import com.ningpai.customer.service.CustomerServiceMapper;
import com.ningpai.other.bean.CustomerAllInfo;
import com.ningpai.other.util.IPAddress;
import com.ningpai.publicpackage.InformationPublic;
import com.ningpai.system.service.BasicSetService;
import com.ningpai.system.service.HelpCenterService;
import com.ningpai.system.service.SysBasicService;
import com.ningpai.system.vo.HelpCenterVo;
import com.ningpai.third.auth.bean.ThirdPage;
import com.ningpai.third.auth.service.ThirdAuthorityPageService;
import com.ningpai.third.login.service.LoginService;
import com.ningpai.third.login.util.LoginValueUtil;
import com.ningpai.third.seller.bean.StoreInfo;
import com.ningpai.third.seller.mapper.StoreInfoMapper;
import com.ningpai.third.seller.service.SellerService;
import com.ningpai.third.util.MenuOperationUtil;
import com.ningpai.third.util.SellerConstants;
import com.ningpai.thirdaudit.service.AuditService;
import com.ningpai.util.MyLogger;
import org.elasticsearch.common.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;
import java.util.List;

/**
 * 第三方登录Controller
 *
 * @author NINGPAI-zhangqiang
 * @version 2.0
 * @since 2014年5月4日 下午2:50:16
 */
@Controller
public class LoginController extends HttpServlet {

    /**
     * 商家帮助中心分类名称
     */
    private static final String HEPLCATE_NAME = "帮助中心";
    private static final String PATCHCA = "PATCHCA";
    private static final String CUSTOMERID = "customerId";
    private static final String CUSTOMER = "customer";
    private static final String CUSTOMERALLINFO = "customerallInfo";
    private static final String SITEURL = "siteUrl";
    private static final String THIRDID = "thirdId";
    private static final String STATUS = "status";
    private static final String LOGIN = "login";
    private static final String PEX_HTML = ".html";
    private static final String NPSTORE_USERNAME = "_npstore_username";
    private static final String CREATEURL = "createUrl";
    private static final String BASICSET = "basicSet";
    private static final String USERMOBILE = "userMobile";

    /**
     * 日志
     * */
    public static final MyLogger LOGGER = new MyLogger(LoginController.class);

    @Resource(name = "auditService")
    private AuditService auditService;

    /**
     * spring 注解 登录service
     */
    private LoginService loginService;
    /**
     * 第三方商家权限页面Service
     */
    private ThirdAuthorityPageService thirdAuthorityPageService;
    /**
     * 站点设置服务层接口接口
     */
    @Resource(name = "basicSetService")
    private BasicSetService basicSetService;
    /**
     * 帮助分类
     */
    @Resource(name = "helpCenterService")
    private HelpCenterService helpCenterService;
    /**
     * 资讯文章接口
     */
    private InformationPublic informationPublic;

    /**
     * SERVICE-后台Logo设置
     */
    @Resource(name = "SysBasicService")
    private SysBasicService sysBasicService;
    /**
     * 商家信息Service
     */
    private SellerService sellerService;

    @Resource(name = "thirdAuthorityPageService")
    private ThirdAuthorityPageService thirdManagerService;
    /**
     * spring 注解 会员service
     */
    @Resource(name = "customerServiceMapper")
    private CustomerServiceMapper customerServiceMapper;
    /**
     * 商铺接口
     */
    @Resource(name = "sotreInfoMapper")
    public StoreInfoMapper sotreInfoMapper;
    /**
     * 会员接口
     */
    @Resource(name = "customerMapper")
    public CustomerMapper customerMapper;

    /**
     * 根据帮助类型获取帮助
     *
     * @param request
     * @return
     */
    @RequestMapping("third/waitaudit")
    public ModelAndView waitAudit(HttpServletRequest request) {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        // 当前登录的会员ID
        Long customerId = (Long) request.getSession().getAttribute(CUSTOMERID);
        // 获取该会员的详细信息
        Customer customer = customerServiceMapper.selectByPrimaryKey(customerId);
        // 获取帮助中心分类下面的子分类
        List<HelpCenterVo> helpCate = helpCenterService.selectCateByHelpname(HEPLCATE_NAME);
        resultMap.put("storeinfo", sellerService.selectRefuseInfo((Long) request.getSession().getAttribute(CUSTOMERID)));
        // resultMap.put(CUSTOMER,customer);//会员信息
        request.getSession().setAttribute(CUSTOMER, customer);
        // 获取排序最靠前的 分类的id 获取该ID下面的分裂列表
        if (null != helpCate && !helpCate.isEmpty()) {
            HelpCenterVo helpCatess = helpCate.get(0);
            // 获取所有的帮助 装载得session中
            request.getSession().setAttribute("help", helpCenterService.findByCateId(helpCatess.getHelpcateId()));
        }
        // 装载站点信息
        request.getSession().setAttribute(SITEURL, basicSetService.findBasicSet());// 前台首页
        // 装载数据 以及设置要跳转的页面路径
        return new ModelAndView(SellerConstants.WAITAUDIT).addAllObjects(resultMap);

    }

    /**
     * 跳转到店铺审核退回页面
     *
     * @param request
     * @return
     */
    @RequestMapping("third/waitauditcheck")
    public ModelAndView waitauditcheck(HttpServletRequest request) {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        // 当前登录的会员ID
        Long customerId = (Long) request.getSession().getAttribute(CUSTOMERID);
        // 获取该会员的详细信息
        Customer customer = customerServiceMapper.selectByPrimaryKey(customerId);
        // 获取帮助中心分类下面的子分类
        List<HelpCenterVo> helpCate = helpCenterService.selectCateByHelpname(HEPLCATE_NAME);
        // 装载店铺信息
        resultMap.put("storeinfo", sellerService.selectRefuseInfo((Long) request.getSession().getAttribute(CUSTOMERID)));
        request.getSession().setAttribute(CUSTOMER, customer);
        // 获取排序最靠前的 分类的id 获取该ID下面的分裂列表
        if (null != helpCate && !helpCate.isEmpty()) {
            HelpCenterVo helpCatess = helpCate.get(0);
            // 根据帮助类型获取帮助
            request.getSession().setAttribute("help", helpCenterService.findByCateId(helpCatess.getHelpcateId()));
        }
        // 查询站点信息
        request.getSession().setAttribute(SITEURL, basicSetService.findBasicSet());// 前台首页
        // 设置跳转路径 装载返回到页面的数据
        return new ModelAndView(SellerConstants.WAITAUDITCHECK).addAllObjects(resultMap);

    }

    /**
     * 登陆成功 进入进入页面 判断该登陆的用户是否有开过店铺
     *
     * @return ModelAndView
     */
    @RequestMapping("/index/goenterpage")
    public ModelAndView goenterpage(HttpServletRequest request) {
        Map<String, Object> objectMap = new HashMap<String, Object>();
        // 店铺信息
        StoreInfo storeInfo = null;
        // 当前登录的会员ID
        Long customerId = (Long) request.getSession().getAttribute(CUSTOMERID);
        // 获取该会员的详细信息
        Customer customer = customerServiceMapper.selectByPrimaryKey(customerId);
        if (customer.getThirdId() != null) {
            // 如果是员工登陆 就根据员工ID去查店铺信息
            if ("2".equals(customer.getIsSeller())) {
                storeInfo = sotreInfoMapper.selectByEmployeeId(customer.getCustomerId());
            } else {
                // 不是员工就正常查询店铺信息
                storeInfo = sotreInfoMapper.selectByCustomerId(customer.getCustomerId());
            }
            if (storeInfo != null) {
                // 有店铺
                if ("1".equals(storeInfo.getCheckStatus()) && "1".equals(storeInfo.getIsSubmit())) {
                    // 商家ID
                    request.getSession().setAttribute(THIRDID, customer.getThirdId());
                    // 店铺名称
                    request.getSession().setAttribute("storeName", storeInfo.getStoreName());
                    // 是否显示商家首页 0否 1是
                    request.getSession().setAttribute("isStoreIndex", storeInfo.getIsStoreIndex());
                    // //是否有店铺
                    objectMap.put(STATUS, 1);
                    // 到期天数
                    Long days = (storeInfo.getExpiryTime().getTime() - new Date().getTime()) / (24 * 60 * 60 * 1000);
                    objectMap.put("days", days);
                } else {
                    // 没店铺
                    objectMap.put(STATUS, 0);
                }
                // 无店铺
            } else if ("0".equals(customer.getIsSeller()) || "3".equals(customer.getIsSeller())) {
                objectMap.put(STATUS, 0);
            }
        }else{
            objectMap.put(STATUS, 0);
        }
        // 获取帮助中心分类下面的子分类
        List<HelpCenterVo> helpCate = helpCenterService.selectCateByHelpname(HEPLCATE_NAME);
        // 店铺信息
        objectMap.put("storeInfo", storeInfo);
        // 会员信息
        objectMap.put(CUSTOMER, customer);
        // 装载会员信息
        request.getSession().setAttribute(CUSTOMER, customer);
        // 获取排序最靠前的 分类的id 获取该ID下面的分裂列表
        if (null != helpCate && !helpCate.isEmpty()) {
            HelpCenterVo helpCatess = helpCate.get(0);
            // 根据帮助类型获取帮助
            request.getSession().setAttribute("help", helpCenterService.findByCateId(helpCatess.getHelpcateId()));
        }
        // 查询站点信息
        request.getSession().setAttribute(SITEURL, basicSetService.findBasicSet());
        List<ThirdPage> thirdPages=thirdManagerService.queryMenuByManager(customerId);
        //防止没有店铺权限 从而越权
        String firstUrl="";
        int str=0;
        if(thirdPages!=null&&!thirdPages.isEmpty()){
            for(int j=0;j<thirdPages.size();j++){
                if(thirdPages.get(j).getMenuVos()!=null&&!thirdPages.get(j).getMenuVos().isEmpty()){
                    for(int i=0;i<thirdPages.get(j).getMenuVos().size();i++){
                        if("sellerinfo.html".equals(thirdPages.get(j).getMenuVos().get(i).getUrl())){
                            firstUrl=thirdPages.get(j).getMenuVos().get(i).getUrl()+"?n="+thirdPages.get(j).getId()+"&l="+thirdPages.get(j).getMenuVos().get(i).getId();
                            str=1;
                            break;
                        }else{
                            //如果没有店铺的权限 则取第一个权限进入
                            firstUrl=thirdPages.get(0).getMenuVos().get(0).getUrl()+"?n="+thirdPages.get(0).getId()+"&l="+thirdPages.get(0).getMenuVos().get(0).getId();
                            break;
                        }

                    }
                    if(str==1){
                        break;
                    }
                }

            }
        }

        // 设置跳转路径 装载跳转到页面的数据
        return new ModelAndView("/index/goenterpage").addObject("objectMap", objectMap).addObject("firstUrl",firstUrl);
    }

    /**
     * 跳转到登陆页面
     *
     * @return
     */
    @RequestMapping("/jumplogin")
    public ModelAndView jumpLogin() {
        return new ModelAndView(new RedirectView(LOGIN));
    }

    /**
     * 设置字母的大小,大小
     *
     * @throws ServletException
     */
    public void init() {
        try {
            super.init();
        } catch (ServletException e) {
            LOGGER.error(""+e);
        }
    }

    /**
     * 生成随机颜色
     *
     * @param fc
     * @param bc
     * @return
     */
    Color getRandColor(int fc, int bc) {
        int fcNew = fc;
        int bcNew = bc;
        Random random = new Random();
        if (fcNew > 255) {
            fcNew = 255;
        }
        if (bcNew > 255) {
            bcNew = 255;
        }
        int r = fcNew + random.nextInt(bcNew - fcNew);
        int g = fcNew + random.nextInt(bcNew - fcNew);
        int b = fcNew + random.nextInt(bcNew - fcNew);
        return new Color(r, g, b);
    }

    /**
     * 生成验证码
     *
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    @RequestMapping("/writeCaptcha")
    public void writeCaptcha(HttpServletResponse response) throws ServletException{
        // 设置页面不缓存
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        // 在内存中创建图象
        int width = 60, height = 20;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // 获取图形上下文
        Graphics g = image.getGraphics();
        // 生成随机类
        Random random = new Random();
        // 设定背景色
        g.setColor(getRandColor(220, 250));
        g.fillRect(0, 0, width, height);
        // 设定字体
        g.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        // 画边框
        // g.drawRect(0,0,width-1,height-1);
        g.draw3DRect(0, 0, width - 1, height - 1, true);
        // 随机产生155条干扰线，使图象中的认证码不易被其它程序探测到
        // g.setColor(getRandColor(160, 200));
        for (int i = 0; i < 155; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }
        // 取随机产生的认证码(6位数字)
        String sRand = "";
        String s = "012345678901234567890123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ012345678901234567890123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 0; i < 4; i++) {
            char rand = s.charAt(random.nextInt(s.length()));
            sRand += rand;
            // 将认证码显示到图象中
            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            // 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
            g.drawString(String.valueOf(rand), 13 * i + 6, 16);
        }
        g.drawOval(0, 12, 60, 11);
        // 降验证码保存到cookie中
        Cookie cookie = new Cookie("sRand", sRand);
        cookie.setMaxAge(10000);
        response.addCookie(cookie);
        // 图象生效
        g.dispose();
        ServletOutputStream output;
        try {
            output = response.getOutputStream();
            // 输出图象到页面
            ImageIO.write(image, "JPEG", output);
        } catch (IOException e) {
            LOGGER.error(""+e);
        }

    }

    /**
     * 跳转登录
     *
     * @return ModelAndView
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("/customer/login")
    public ModelAndView login(HttpServletRequest request, String url) throws UnsupportedEncodingException {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String urlEmp = url;
        String preUrl = request.getHeader("Referer");
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
            if (preUrl != null) {
                urlEmp = preUrl;
            } else {
                urlEmp = LoginValueUtil.INDEX;
            }
        }
        if (urlEmp.indexOf("register") != -1 || urlEmp.indexOf("success") != -1 || urlEmp.indexOf(LOGIN) != -1 || urlEmp.indexOf("storetip") != -1) {
            urlEmp = LoginValueUtil.INDEX;
        }
        if (urlEmp.indexOf(PEX_HTML) == -1) {
            urlEmp = urlEmp + PEX_HTML;
        }
        String requestURI = request.getRequestURI();
        if (requestURI.indexOf(PEX_HTML) == -1 && requestURI.indexOf("/newthird") != -1) {
            urlEmp = "index.html";
        }
        if (requestURI.indexOf(PEX_HTML) == -1 && requestURI.indexOf("/paistore_newthird_site") != -1) {
            urlEmp = "index.html";
        }
        // 获取登陆错误次数
        resultMap.put("errCount", loginService.getErrCount(request));
        // 查询站点信息
        resultMap.put(BASICSET, basicSetService.findBasicSet());
        MenuOperationUtil.fillSessionHttpUrl(request, urlEmp);
        setResultMap(request, resultMap, urlEmp);
        // 底部信息
        request.getSession().setAttribute("sysBasic", sysBasicService.getSysBasic());
        // 设置跳转页面的路径 装载跳转到页面需要的数据
        return new ModelAndView("/login/newlogin").addAllObjects(resultMap);
    }

    /**
     * 退出 跳转登录
     *
     * @return ModelAndView
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("/customer/logout")
    public ModelAndView loginOut(HttpServletRequest request, String url) throws UnsupportedEncodingException {
        String urlNew = url;
        if (urlNew == null) {
            urlNew = LoginValueUtil.INDEX;
        }
        // 清空session已登录数据 用户信息
        request.getSession().removeAttribute("cust");
        // 会员ID
        request.getSession().removeAttribute(CUSTOMERID);
        // 查询站点信息
        request.getSession().removeAttribute("bsetDomain");
        // 跳转到登陆页面
        return new ModelAndView(new RedirectView(LOGIN));
    }

    /**
     * 验证登录
     *
     * @param request
     * @param username
     *            用户名
     * @param password
     *            密码
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("/checklogin")
    @ResponseBody
    public Object checkLogin(HttpServletRequest request, HttpServletResponse response, String username, String password, String type) throws UnsupportedEncodingException {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        int status = 0;
        if (null != username && null != password) {
            // 获取单个的会员信息
            Customer customer = this.customerServiceMapper.getCustomerByUsername(username);
            // 用户名不存在
            if (customer == null) {
                resultMap.put(STATUS, 2);
                return resultMap;
            }
            StoreInfo storeinfo = sotreInfoMapper.selectByEmployeeId(customer.getCustomerId());
            // 判断店铺的有效期是否到期
            if ("1".equals(customer.getIsSeller()) && storeinfo != null && "1".equals(storeinfo.getIsSubmit())) {
                    int count = auditService.selectStoreTimeByThirdId(customer.getThirdId());
                    if (count == 0) {
                        resultMap.put(STATUS, 66);
                        return resultMap;
                    }
            }

            // IsSeller： 2 是员工
            if ("2".equals(customer.getIsSeller())) {
                /*
                 * 如果当前登录的会员是商家角色下面创建的员工，改员工 对应的角色信息如果已经删除，就禁止该会员登录商家 结
                 * 果大于1就是角色还存在可以正常登陆小于1就限制其登陆商家
                 */
                int result = loginService.checkThirdAuthority(username);
                if (result == 0) {
                    resultMap.put(STATUS, 11);
                    return resultMap;
                }
            }

        }

        if (!"".equals(password)) {
            // 验证用户
            status = loginService.checkCustomerExists(request, username, password);
        }
        if (status == 1) {
            if ("0".equals(type)) {
                Cookie cookie = new Cookie(NPSTORE_USERNAME, URLEncoder.encode(username, LoginValueUtil.UTF8));
                // 设置七天
                cookie.setMaxAge(7 * 24 * 3600);
                response.addCookie(cookie);
            } else if ("1".equals(type)) {
                Cookie nameCookie = new Cookie(NPSTORE_USERNAME, URLEncoder.encode(username, LoginValueUtil.UTF8));
                // 设置七天
                nameCookie.setMaxAge(7 * 24 * 3600);
                response.addCookie(nameCookie);

                Cookie pwdCookie = new Cookie("_npstore_pwd", URLEncoder.encode(password, LoginValueUtil.UTF8));
                // 设置七天
                pwdCookie.setMaxAge(7 * 24 * 3600);
                response.addCookie(pwdCookie);
            }
            // 设置跳转路径
            resultMap.put("url", (String) request.getSession().getAttribute("url"));
            return resultMap;
        } else if (status == 3) {
            resultMap.put(STATUS, status);
            // 跳转路径
            resultMap.put(CREATEURL, "storetip.html");
            return resultMap;
        } else if (status == 4) {
            resultMap.put(STATUS, status);
            // 跳转路径
            resultMap.put(CREATEURL, "waitaudit.html");
            return resultMap;
        } else if (status == 6) {
            resultMap.put(STATUS, status);
            return resultMap;
        } else if (status == 10) {
            // 跳转路径
            resultMap.put(CREATEURL, "waitauditcheck.html");
            resultMap.put(STATUS, status);
            return resultMap;
        } else if (status == 7) {
            resultMap.put(STATUS, status);
            return resultMap;
        } else {
            // 获取错误次数
            resultMap.put("errCount", loginService.getErrCount(request));
            resultMap.put(STATUS, status);
            return resultMap;
        }
    }

    /**
     * 模拟登录
     */
    @RequestMapping("/isloginthird")
    public ModelAndView getLogin(HttpServletRequest request, HttpServletResponse response, Long customerId, String loginKey) {
        StoreInfo storeInfo = null;
        // 查询单个会员信息 详细
        Customer customer = customerServiceMapper.selectByPrimaryKey(customerId);
        if (loginKey.equals(customer.getLoginKey())) {
            customer.setCustomerPassword(null);
            // 设置登录时间
            customer.setLoginTime(new Date());
            // 设置登录Ip
            customer.setLoginIp(IPAddress.getIpAddr(request));
            // 设置登录key
            UUID uuid = UUID.randomUUID();
            customer.setLoginKey(uuid.toString());
            // 修改登录时间 IP
            customerMapper.updateCustomerLoginTime(customer);
            // 会员ID
            request.getSession().setAttribute(CUSTOMERID, customer.getCustomerId());
            // 当前会员
            request.getSession().setAttribute("cust", customer);
            // 查询站点信息
            request.getSession().setAttribute("bsetDomain", basicSetService.findBasicSet().getBsetDomain());
            if (customer.getThirdId() != null) {
                // 根据会员ID查询当前的店铺信息
                storeInfo = sotreInfoMapper.selectByCustomerId(customer.getCustomerId());
                // 判断该会员是否有店铺
                if (storeInfo != null) {
                    if ("0".equals(storeInfo.getCheckStatus())
                            && ("1".equals(storeInfo.getIsSubmit()) || storeInfo.getRefuseContent() != null && storeInfo.getRefuseContent().length() != 0)) {
                        return new ModelAndView("/openstore/waitaudit");
                    }
                    // 如果有店铺 就跳转到店铺首页
                    if ("1".equals(storeInfo.getCheckStatus()) && "1".equals(storeInfo.getIsSubmit())) {
                        // 商家ID
                        request.getSession().setAttribute(THIRDID, customer.getThirdId());
                        // 商铺名称
                        request.getSession().setAttribute("storeName", storeInfo.getStoreName());
                        // 是否现在店铺首页
                        request.getSession().setAttribute("isStoreIndex", storeInfo.getIsStoreIndex());
                        return new ModelAndView(new RedirectView("index.html?n=1&l=15"));
                    }
                    // 已经提交了店铺审核 在走审批流程
                    if ("1".equals(storeInfo.getCheckStatus())) {
                        return new ModelAndView("/openstore/waitaudit");
                    }
                } else {
                    if ("0".equals(customer.getIsSeller())) {
                        // 此用户不是商家 也不是商家管理人员
                        return new ModelAndView("/seller/storetip");
                    }
                }
            }
            if ("0".equals(customer.getIsSeller())) {
                // 此用户不是商家 也不是商家管理人员
                return new ModelAndView("/seller/storetip");
            }
            // 商家ID
            request.getSession().setAttribute(THIRDID, customer.getThirdId());
            // 重定向到店铺首页
            return new ModelAndView(new RedirectView("index.html?n=1&l=15"));
        } else {
            // 重定向到登陆页面
            return new ModelAndView(new RedirectView("customer/login.htm"));
        }
    }

    /**
     * @param request
     * @param resultMap
     * @param url
     *            页面跳转的路径
     * @throws UnsupportedEncodingException
     */
    public void setResultMap(HttpServletRequest request, Map<String, Object> resultMap, String url) throws UnsupportedEncodingException {
        String username = "";
        // 读取cookie中的信息
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie != null && NPSTORE_USERNAME.equals(cookie.getName())) {
                    username = URLDecoder.decode(cookie.getValue(), LoginValueUtil.UTF8);
                    break;
                }
            }
        }
        resultMap.put("username", username);
        /* resultMap.put("url", url); */
    }


    /**
     * 忘记密码第一步
     * @return
     */
    @RequestMapping("/findCodeOne")
    public ModelAndView findCodeOne() {
        return new ModelAndView("login/find_code1").addObject(BASICSET, basicSetService.findBasicSet());
    }

    /**
     * 忘记密码第二步
     * @return
     */
    @RequestMapping("/findCodeTwo")
    public ModelAndView findCodeTwo(HttpServletRequest request,String varification,String userName) {
    //    Long count=  customerServiceMapper.checkExistsByCustNameAndType(userName);


        if (!(request.getSession().getAttribute(PATCHCA)).equals(varification)|| StringUtils.isEmpty(userName)){
            return  new ModelAndView(new RedirectView("findCodeOne.htm"));
        }
        // 获取单个的会员信息
        Customer customer = this.customerServiceMapper.getCustomerByUsername(userName);
        CustomerAllInfo customerInfo=null;
        if(customer!=null){
            customerInfo  =customerServiceMapper.queryCustomerInfo(customer.getCustomerId());
           if(customerInfo!=null){
               //手机号
               request.getSession().setAttribute(USERMOBILE,customerInfo.getInfoMobile());
               // 会员ID
               request.getSession().setAttribute(CUSTOMERID, customer.getCustomerId());
               request.getSession().setAttribute(CUSTOMERALLINFO,customerInfo);
           }
        }
        return new ModelAndView("login/find_code2").addObject(BASICSET, basicSetService.findBasicSet()).addObject("user",customerInfo);
    }

    /**
     * 忘记密码第三步
     * @return
     */
    @RequestMapping("/findCodeThree")
    public ModelAndView findCodeThree(HttpServletRequest request,String mobile,String codetext) {
        if(!request.getSession().getAttribute(USERMOBILE).toString().equals(mobile)||!request.getSession().getAttribute("mcCode").toString().equals(codetext)){
            return  new ModelAndView(new RedirectView("findCodeOne.htm"));
        }
        return new ModelAndView("login/find_code3").addObject(BASICSET, basicSetService.findBasicSet()).addObject("mobile",mobile).addObject("codetext",codetext);
    }
    /**
     * 查询密码
     * */
    @RequestMapping("/findCodeFour")
    public ModelAndView findCodeFour(HttpServletRequest request,String mobile,String codetext) {
        return new ModelAndView("login/find_code4").addObject(BASICSET, basicSetService.findBasicSet());
    }
    /**
     * 修改密码
     * */
    @ResponseBody
    @RequestMapping("/updatePassword")
    public int updatePassword(HttpServletRequest request,String mobile,String codetext,String password) {
        if(!request.getSession().getAttribute(USERMOBILE).toString().equals(mobile)||!request.getSession().getAttribute("mcCode").toString().equals(codetext)){
            return  2;
        }
        // 获取单个的会员信息
       Long customerId=(Long)request.getSession().getAttribute(CUSTOMERID);

        if(customerId!=null){

            Map<String,Object> map=new HashMap<>();
            map.put("customerPassword",password);
           map.put("customerId",customerId);
            return customerServiceMapper.updatePassword(map);
        }
        return 3;
    }


    public LoginService getLoginService() {
        return loginService;
    }

    @Resource(name = "loginServiceThird")
    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    public ThirdAuthorityPageService getThirdAuthorityPageService() {
        return thirdAuthorityPageService;
    }

    @Resource(name = "thirdAuthorityPageService")
    public void setThirdAuthorityPageService(ThirdAuthorityPageService thirdAuthorityPageService) {
        this.thirdAuthorityPageService = thirdAuthorityPageService;
    }

    public InformationPublic getInformationPublic() {
        return informationPublic;
    }

    @Resource(name = "InformationPublic")
    public void setInformationPublic(InformationPublic informationPublic) {
        this.informationPublic = informationPublic;
    }

    public SellerService getSellerService() {
        return sellerService;
    }

    @Resource(name = "sellerService")
    public void setSellerService(SellerService sellerService) {
        this.sellerService = sellerService;
    }
}
