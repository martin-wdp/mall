/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.customer.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.ningpai.customer.util.DateUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.customer.bean.CustomerConsume;
import com.ningpai.customer.service.CustomerConsumeServiceMapper;
import com.ningpai.other.util.CustomerConstantStr;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;

/**
 * 会员充值消费Controller
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年3月21日 下午2:57:58
 * @version 0.0.1
 */
@Controller
public class CustomerConsumeController {

    // 记录日志对象
    private static final MyLogger LOGGER = new MyLogger(CustomerConsumeController.class);

    private static final String REDIRECT = "querycustomerconsume.htm";

    // spring 注解
    private CustomerConsumeServiceMapper customerConsumeServiceMapper;

    /**
     * 查询消费
     * 
     * @return
     * @throws ParseException
     */
    @RequestMapping("/querycustomerconsume")
    public ModelAndView queryCustomerConsume(@Valid CustomerConsume consume, PageBean pageBean, String createTimeF, String createTimeT, String showFlag, String[] attr)
            throws ParseException {
        // 设置页面跳转路径
        pageBean.setUrl(REDIRECT);
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
        // 结果集
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            // 将时间按照指定格式转换
            consume.setCreateTime(createTimeF == null || "".equals(createTimeF) ? null : formatDate.parse(createTimeF));
            consume.setCreateTimeTo(createTimeT == null || "".equals(createTimeT) ? null : DateUtil.getLastTime(formatDate.parse(createTimeT)));
            resultMap.put("pageBean", customerConsumeServiceMapper.selectCustConsumeByCustConsume(consume, pageBean, "1"));
            resultMap.put("showFlag", showFlag);
            resultMap.put("attr", attr);
            resultMap.put("consume", consume);
            return new ModelAndView(CustomerConstantStr.CUSTOMERCONSUME).addAllObjects(resultMap);
        } finally {
            formatDate = null;
            resultMap = null;
        }
    }

    /**
     * 删除会员消费记录
     * 
     * @return ModelAndView
     * @throws IOException
     */
    @RequestMapping("/deletecustomerconsume")
    public ModelAndView deleteCustomerConsume(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter pr = null;
        try {
            pr = response.getWriter();
            // 删除会员信息 输出信息 0 失败 1成功
            pr.print(customerConsumeServiceMapper.deleteCustomerConsume(request.getParameterValues("parameterIds[]")));
        } finally {
            LOGGER.debug(CustomerConstantStr.DELECUSTCONSUME);
            pr = null;
        }
        return null;
    }

    /**
     * 删除会员消费记录
     * 
     * @return ModelAndView
     * @throws IOException
     */
    @RequestMapping("/deletenewcustomerconsume")
    public ModelAndView deleteNewCustomerConsume(HttpServletRequest request, HttpServletResponse response, Long balanceId) throws IOException {
        String[] balanceIds = new String[] { "" + balanceId + "" };
        // 删除会员信息 输出信息 0 失败 1成功
        customerConsumeServiceMapper.deleteCustomerConsume(balanceIds);
        return new ModelAndView(new RedirectView(REDIRECT));

    }

    /**
     * 批量删除会员消费记录
     * 
     * @return ModelAndView
     * @throws IOException
     */
    @RequestMapping("/deleteallnewcustomerconsume")
    public ModelAndView deleteAllNewCustomerConsume(HttpServletRequest request, HttpServletResponse response, Long[] balanceId, int delFlag) throws IOException {
        String[] balanceIds = request.getParameterValues("balanceId");
        // 删除会员信息 输出信息 0 失败 1成功
        customerConsumeServiceMapper.deleteCustomerConsume(balanceIds);
        if (delFlag == 2) {
            return new ModelAndView(new RedirectView("initrecharge.htm"));
        } else {
            return new ModelAndView(new RedirectView(REDIRECT));
        }

    }

    public CustomerConsumeServiceMapper getCustomerConsumeServiceMapper() {
        return customerConsumeServiceMapper;
    }

    @Resource(name = "customerConsumeServiceMapper")
    public void setCustomerConsumeServiceMapper(CustomerConsumeServiceMapper customerConsumeServiceMapper) {
        this.customerConsumeServiceMapper = customerConsumeServiceMapper;
    }

}
