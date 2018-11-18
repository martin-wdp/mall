/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.third.auth.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import com.ningpai.customer.service.CustomerServiceMapper;
import com.ningpai.other.bean.CustomerAllInfo;
import com.ningpai.third.auth.bean.ThirdAuthority;
import com.ningpai.third.auth.bean.ThirdManagerAuthority;
import com.ningpai.third.auth.service.ThirdAuthorityService;
import com.ningpai.third.util.MenuOperationUtil;
import com.ningpai.third.util.SellerConstants;
import com.ningpai.util.PageBean;

/**
 * <p>
 * 商家权限Controller
 * </p>
 * 
 * @author zhanghl
 * @since 20150730
 * @version 2.0
 */
@Controller
public final class ThirdAuthorityController {

    private static final String THIRDID = "thirdId";

    private static final String ROLES = "roles";

    // 商家权限sevice接口
    private ThirdAuthorityService thirdAuthorityService;
    // 会员service接口
    private CustomerServiceMapper customerServiceMapper;

    /**
     * 获取角色列表
     * 
     * @param request
     * @param n
     *            导航栏索引
     * @param l
     *            导航栏索引
     * @return
     */
    @RequestMapping("third/managerlist")
    public ModelAndView managerList(HttpServletRequest request, String n, String l) {
        // 填充导航/左侧菜单索引 用于页面控制css选中
        MenuOperationUtil.fillSessionMenuIndex(request, n, l);
        // 获取角色列表
        return new ModelAndView(SellerConstants.MANAGERLIST).addObject(ROLES,
                thirdAuthorityService.queryThirdAuthorityByStotreId((Long) request.getSession().getAttribute(THIRDID)));
    }

    /**
     * 加载管理员
     * 
     * @param request
     * @return
     */
    @RequestMapping("/loadmanager")
    @ResponseBody
    public Map<String, Object> loadmanager(HttpServletRequest request) {
        // 用来装载查询到的结果
        Map<String, Object> resultMap = new HashMap<String, Object>();
        // 查询出来的结果
        resultMap.put(ROLES, thirdAuthorityService.queryThirdAuthorityByStotreId((Long) request.getSession().getAttribute(THIRDID)));
        return resultMap;
    }

    /**
     * 获取员工列表
     * 
     * @param request
     * @param n
     *            导航栏索引
     * @param l
     *            导航栏索引
     * @param pb
     *            分页对象
     * @return
     */
    @RequestMapping("third/employeelist")
    public ModelAndView employeeList(HttpServletRequest request, String n, String l, PageBean pb) {
        String nNew = n;
        String lNew = l;
        // 判断导航栏索引
        if (nNew == null) {
            nNew = String.valueOf(request.getSession().getAttribute("n"));
        }
        // 判断导航栏索引
        if (lNew == null) {
            lNew = String.valueOf(request.getSession().getAttribute("l"));
        }
        // 填充导航/左侧菜单索引 用于页面控制css选中
        MenuOperationUtil.fillSessionMenuIndex(request, nNew, lNew);
        // 设置跳转路径
        pb.setUrl("employeelist.html?n=" + nNew + "&l=" + lNew);
        return new ModelAndView(SellerConstants.EMPLOYEELIST).addObject("pb",
                thirdAuthorityService.queryEmployeeListByStotreId((Long) request.getSession().getAttribute(THIRDID), pb)).addObject(ROLES,
                thirdAuthorityService.queryThirdAuthorityByStotreId((Long) request.getSession().getAttribute(THIRDID)));
    }

    /**
     * 禁用用户
     * 
     * @param request
     * @param custId
     *            会员ID
     * @param flag
     *            状态
     * @return
     */
    @RequestMapping("/modifyemptodisable")
    public ModelAndView modifyEmpToDisable(HttpServletRequest request, Long custId, String flag) {
        // 根据条件修改单个会员的信息
        // 禁用第三方的用户
        customerServiceMapper.modifyEmpToDisableThird(custId, flag, (Long) request.getSession().getAttribute(THIRDID));
        return new ModelAndView(new RedirectView(request.getContextPath() + SellerConstants.EMPLIST));
    }

    /**
     *
     * @param request
     * @param author
     *            第三方管理员权限关系
     * @return
     */
    @RequestMapping("/updatebycustid")
    public ModelAndView updateBycustId(HttpServletRequest request, ThirdManagerAuthority author) {
        // 当前商家Id
        Long thirdId = (Long) request.getSession().getAttribute(THIRDID);
        // 设置修改时间未当前系统日期
        author.setModTime(new Date());
        // 修改管理员权限关系
        thirdAuthorityService.updateByPrimaryKeySelective(author, thirdId);
        return new ModelAndView(new RedirectView(request.getContextPath() + SellerConstants.EMPLIST));
    }

    /**
     * 删除员工
     * 
     * @param request
     * @param custId
     *            批量操作的员工编号
     * @return
     */
    @RequestMapping("/delemp")
    public ModelAndView delCustomer(HttpServletRequest request, String[] custId) {
        // 当前商家ID
        Long thirdId = (Long) request.getSession().getAttribute(THIRDID);
        // 删除商家员工
        customerServiceMapper.deleteCustomerThird(custId, thirdId);
        return new ModelAndView(new RedirectView(request.getContextPath() + SellerConstants.EMPLIST));
    }

    /**
     * 添加员工
     * 
     * @param request
     * @param allInfo
     *            员工信息 {@link CustomerAllInfo}
     * @param authId
     *            角色编号
     * @return
     */
    @RequestMapping("/addemp")
    public ModelAndView addEmp(HttpServletRequest request, CustomerAllInfo allInfo, Long authId) {
        // 要新增的用户名非空验证
        if (null != allInfo.getCustomerUsername() && 0 == customerServiceMapper.selectCustomerByName(allInfo.getCustomerUsername())) {
            // 用户名是否存在
                // 新增一条会员信息
                thirdAuthorityService.addEmp(request, allInfo, authId);
        }

        return new ModelAndView(new RedirectView(request.getContextPath() + SellerConstants.EMPLIST));
    }

    /**
     * 跳转添加员工
     * 
     * @return
     */
    @RequestMapping("/toaddemp")
    public ModelAndView toAddEmp() {
        return new ModelAndView(SellerConstants.TOADDEMP);
    }

    /**
     * 保存员工信息
     * 
     * @param request
     * @param thirdAuthority
     *            第三方权限Bean
     * @return 0失败 1成功
     */
    @RequestMapping("third/addauthority")
    @ResponseBody
    public int addAuthority(HttpServletRequest request, ThirdAuthority thirdAuthority) {
        // 验证角色是否存在
        int result = thirdAuthorityService.checkAuthorityExist(request, thirdAuthority);
        // 不存在就保存新角色
        if (result == 0) {
            return thirdAuthorityService.addAuthority(request, thirdAuthority);
        }
        return 0;
    }

    /**
     * 删除权限角色
     * 
     * @param request
     * @param thirdAuthority
     *            第三方权限Bean
     * @return 0失败 1成功
     */
    @RequestMapping("third/delauthority")
    @ResponseBody
    public int delAuthority(HttpServletRequest request, ThirdAuthority thirdAuthority) {
        return thirdAuthorityService.delAuthority(thirdAuthority, (Long) request.getSession().getAttribute(THIRDID));
    }

    /**
     * 查询权限角色存在性
     * 
     * @param request
     * @param thirdAuthority
     *            第三方权限Bean
     * @return 0不存在 1存在
     */
    @RequestMapping("third/checkauthorityexist")
    @ResponseBody
    public int checkAuthorityExist(HttpServletRequest request, ThirdAuthority thirdAuthority) {
        return thirdAuthorityService.checkAuthorityExist(request, thirdAuthority);
    }

    /**
     * 修改权限名称 --重命名
     * 
     * @param request
     * @param thirdAuthority
     * @return int 0 失败 1成功
     */
    @RequestMapping("/updateauthorityname")
    @ResponseBody
    public int updateAuthorityName(HttpServletRequest request, ThirdAuthority thirdAuthority) {
        // 根据主键获取单个的职位对象
        ThirdAuthority authority = thirdAuthorityService.selectAuthorById(thirdAuthority.getId());
        // 判断职位名称是否更改
        if (!authority.getDesignation().equals(thirdAuthority.getDesignation())) {
            // 验证修改的职位名是否存在
            int result = this.checkAuthorityExist(request, thirdAuthority);
            // 不存在
            if (result == 0) {
                // 获取当商品ID
                Long thirdId = (Long) request.getSession().getAttribute(THIRDID);
                // 设置权限的商铺ID
                thirdAuthority.setStoreId(thirdId);
                // 返回修改的结果
                int i = thirdAuthorityService.updateAuthorityName(thirdAuthority);
                // 0表示修改失败
                if (i == 0) {
                    // i赋值-1
                    i = -1;
                }
                // 返回i
                return i;
            }
            return 0;
        }
        return 1;
    }

    /**
     * 验证用户名是否存在
     * 
     * @param customerUsername
     *            要检测的会员名称
     * @return
     */
    @RequestMapping("checkUsernameExitOrNot")
    @ResponseBody
    public Long checkUsernameExitOrNot(String customerUsername) {
        return customerServiceMapper.selectCustomerByName(customerUsername);
    }

    public ThirdAuthorityService getThirdAuthorityService() {
        return thirdAuthorityService;
    }

    @Resource(name = "thirdAuthorityService")
    public void setThirdAuthorityService(ThirdAuthorityService thirdAuthorityService) {
        this.thirdAuthorityService = thirdAuthorityService;
    }

    public CustomerServiceMapper getCustomerServiceMapper() {
        return customerServiceMapper;
    }

    @Resource(name = "customerServiceMapper")
    public void setCustomerServiceMapper(CustomerServiceMapper customerServiceMapper) {
        this.customerServiceMapper = customerServiceMapper;
    }
}
