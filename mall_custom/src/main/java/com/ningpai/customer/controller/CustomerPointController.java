/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.customer.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.ningpai.customer.util.DateUtil;
import com.ningpai.other.bean.CustomerAllInfo;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.customer.bean.Customer;
import com.ningpai.customer.bean.CustomerPoint;
import com.ningpai.customer.bean.CustomerPointLevel;
import com.ningpai.customer.bean.RegisterPoint;
import com.ningpai.customer.service.CustomerPointServiceMapper;
import com.ningpai.customer.service.CustomerServiceMapper;
import com.ningpai.customer.service.PointLevelServiceMapper;
import com.ningpai.other.util.CustomerConstantStr;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;

/**
 * 会员积分记录Controller
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年3月20日 下午1:43:00
 * @version 0.0.1
 */
@Controller
public class CustomerPointController {

    private static final String PAGEBEAN = "pageBean";
    private static final String QUERYCUSTPOINTBYCUSTPOINT = "querycustpointbycustpoint.htm";
    private static final String LOGGERINFO1 = "查询推荐人：";
    private static final String LOGGERINFO2 = "的推广返积分记录";

    // spring注解
    private CustomerPointServiceMapper customerPointServiceMapper;

    @Resource(name = "customerServiceMapper")
    private CustomerServiceMapper customerServiceMapper;

    @Resource(name = "pointLevelServiceMapper")
    private PointLevelServiceMapper pointLevelServiceMapper;

    // 记录日志对象
    private static final MyLogger LOGGER = new MyLogger(CustomerPointController.class);

    /**
     * 查询所有积分记录
     * 
     * @return ModelAndView
     */
    @RequestMapping("/initcustomerpoint")
    public ModelAndView initCustomerPoint(PageBean pageBean) {
        // 设置页面跳转路径
        pageBean.setUrl("initcustomerpoint.htm");
        return new ModelAndView(CustomerConstantStr.CUSTOMERPOINT).addObject(PAGEBEAN, customerPointServiceMapper.selectAllCustomerPoint(pageBean));
    }

    /**
     * 删除会员积分记录
     * 
     * @return ModelAndView
     * @throws IOException
     */
    @RequestMapping("/deletecustomerpoint")
    public ModelAndView deleteCustomerPoint(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter pr = null;
        try {
            pr = response.getWriter();
            // 删除会员信息 输出信息 0 失败 1成功
            pr.print(customerPointServiceMapper.deleteCustomerPoint(request.getParameterValues("parameterIds[]")));
        } finally {
            LOGGER.debug(CustomerConstantStr.DELPOINT);
            pr = null;
        }
        return null;
    }

    /**
     * 删除会员积分记录
     * 
     * @return ModelAndView
     * @throws IOException
     */
    @RequestMapping("/deletenewcustomerpoint")
    public ModelAndView deleteNewCustomerPoint(HttpServletRequest request, HttpServletResponse response, Long pointId) throws IOException {
        // 删除会员积分记录 1 成功 0 失败
        String[] pointIds = new String[] { "" + pointId + "" };
        customerPointServiceMapper.deleteCustomerPoint(pointIds);
        return new ModelAndView(new RedirectView(QUERYCUSTPOINTBYCUSTPOINT));
    }

    /**
     * 批量删除会员积分记录
     * 
     * @return ModelAndView
     * @throws IOException
     */
    @RequestMapping("/deletenewallcustomerpoint")
    public ModelAndView deleteNewAllCustomerPoint(HttpServletRequest request, HttpServletResponse response, Long pointId) throws IOException {
        // 删除会员积分记录 1 成功 0 失败
        String[] pointIds = request.getParameterValues("pointId");
        customerPointServiceMapper.deleteCustomerPoint(pointIds);
        return new ModelAndView(new RedirectView(QUERYCUSTPOINTBYCUSTPOINT));
    }

    /**
     * 按条件查找积分记录
     * 
     * @param point
     *            积分条件
     * @param pageBean
     *            分页PageBean
     * @return ModelAndView
     * @throws ParseException
     */
    @RequestMapping("/querycustpointbycustpoint")
    public ModelAndView queryCustPointByCustPoint(@Valid CustomerPoint point, PageBean pageBean, String createTimeF, String createTimeT, String showFlag, String[] attr)
            throws ParseException {
        // 设置页面跳转路径
        pageBean.setUrl(QUERYCUSTPOINTBYCUSTPOINT);
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
        // 结果集
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            // 将时间按照指定格式转换
            point.setCreateTime(createTimeF == null || "".equals(createTimeF) ? null : formatDate.parse(createTimeF));
            point.setCreateTimeTo(createTimeT == null || "".equals(createTimeT) ? null : DateUtil.getLastTime(formatDate.parse(createTimeT)));
            resultMap.put(PAGEBEAN, customerPointServiceMapper.selectCustPointByCustPoint(point, pageBean));
            resultMap.put("showFlag", showFlag);
            resultMap.put("attr", attr);
            resultMap.put("point", point);
            return new ModelAndView(CustomerConstantStr.CUSTOMERPOINT).addAllObjects(resultMap).addObject("logindatet", createTimeF).addObject("logindateto", createTimeT);
        } finally {
            formatDate = null;
            resultMap = null;
        }
    }

    /***
     * 会员推广积分记录
     * 
     * @param pageBean
     * @return ModelAndView
     * @throws ParseException
     */
    @RequestMapping("/queryregisterpoint")
    public ModelAndView queryregisterpoint(@Valid RegisterPoint registerPoint, PageBean pageBean) throws ParseException {
        // 设置页面跳转路径
        pageBean.setUrl("queryregisterpoint.htm");
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
        Map<String, Object> resultMap = new HashMap<String, Object>();

        try {
            if (null != registerPoint.getCreateTimeF() && !"".equals(registerPoint.getCreateTimeF())) {
                Date createTimeF = formatDate.parse(registerPoint.getCreateTimeF());
                registerPoint.setStartTime(createTimeF);
            }
            if (null != registerPoint.getCreateTimeT() && !"".equals(registerPoint.getCreateTimeT())) {
                Date createTimeT = DateUtil.getLastTime(formatDate.parse(registerPoint.getCreateTimeT()));
                registerPoint.setEndTime(createTimeT);
            }
            resultMap.put("point", registerPoint);
            resultMap.put(PAGEBEAN, customerPointServiceMapper.queryregisterpoint(registerPoint, pageBean));
            // 非空验证 推荐人
            if (null != registerPoint.getRegPointReferee()) {
                LOGGER.info(LOGGERINFO1 + registerPoint.getRegPointReferee() + LOGGERINFO2);
            }
            // 非空验证 被推荐人
            if (null != registerPoint.getRegPointRecom()) {
                LOGGER.info(LOGGERINFO1 + registerPoint.getRegPointRecom() + LOGGERINFO2);
            }
            // 非空验证 推荐人
            if (null != registerPoint.getRegPointReferee()) {
                LOGGER.info(LOGGERINFO1 + registerPoint.getRegPointReferee() + LOGGERINFO2);
            }
            return new ModelAndView(CustomerConstantStr.REGISTERPOINT).addAllObjects(resultMap);
        } finally {
            formatDate = null;
            resultMap = null;
        }
    }

    /**
     * 会员等级统计
     * 
     * @param request
     * @param pageBean
     * @param pointLevelId
     * @param customerNickname
     * @return
     */
    @RequestMapping("/tomemberLevel")
    public ModelAndView tomemberLevel(HttpServletRequest request, PageBean pageBean, Long pointLevelId, String customerNickname) {
        pageBean.setUrl("tomemberLevel.htm");
        List<CustomerPointLevel> levelList = pointLevelServiceMapper.selectAllPointLevel();
        List<String> lList = new ArrayList<String>();
        for (int i = 0; i < levelList.size(); i++) {
            CustomerPointLevel cpl = levelList.get(i);
            lList.add(cpl.getPointLevelName());
        }
        JSONArray levelLists = JSONArray.fromObject(lList);

        List<Customer> cusList = customerServiceMapper.queryCusLevleInfo();
        List<String> cList = new ArrayList<String>();
        for (int i = 0; i < cusList.size(); i++) {
            Customer cus = cusList.get(i);
            String levelName = cus.getPointLevelName();
            if (levelName == null) {
                levelName = "其他";
            }
            cList.add("{value:" + cus.getLevelCount() + ",name:'" + levelName + "'}");
        }
        JSONArray cusLists = JSONArray.fromObject(cList);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        // 等级对象列表
        resultMap.put("levelList", levelList);
        resultMap.put("cusList", customerServiceMapper.queryCusLevleInfo());
        resultMap.put("pb", customerServiceMapper.queryCusAndOrderInfo(pageBean, pointLevelId, customerNickname));
        // 等级名称列表供饼图使用的
        resultMap.put("levelLists", levelLists);
        resultMap.put("cusLists", cusLists);
        request.setAttribute("pId", pointLevelId);
        request.setAttribute("customerNickname", customerNickname);
        return new ModelAndView("jsp/customer/member_level").addAllObjects(resultMap);
    }

    /**
     * 手动更新会员等级
     * 
     * @return
     */
    @RequestMapping("/upCusLevel")
    public ModelAndView upCusLevel(Long customerId, String pointLevelId, String pointLevelName) {
        // 非空验证 会员ID
        if (null != customerId) {
            CustomerAllInfo customerAllInfo = customerServiceMapper.queryCustomerInfo(customerId);
            LOGGER.info("手动更新会员【" + customerAllInfo.getInfoRealname() + "】等级");
        }
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("customerId", customerId);
        paramMap.put("pointLevelId", pointLevelId);
        paramMap.put("pointLevelName", pointLevelName);
        customerServiceMapper.upCusLevel(paramMap);
        return new ModelAndView(new RedirectView("tomemberLevel.htm"));
    }

    public CustomerPointServiceMapper getCustomerPointServiceMapper() {
        return customerPointServiceMapper;
    }

    @Resource(name = "customerPointServiceMapper")
    public void setCustomerPointServiceMapper(CustomerPointServiceMapper customerPointServiceMapper) {
        this.customerPointServiceMapper = customerPointServiceMapper;
    }

}
