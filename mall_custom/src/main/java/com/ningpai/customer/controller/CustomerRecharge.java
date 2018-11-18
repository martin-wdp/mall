/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.customer.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import com.ningpai.util.MyLogger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ningpai.customer.bean.CustomerConsume;
import com.ningpai.customer.service.CustomerConsumeServiceMapper;
import com.ningpai.other.util.CustomerConstantStr;
import com.ningpai.util.PageBean;

/**
 * 会员充值Controller
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年3月21日 下午5:03:53
 * @version 0.0.1
 */
@Controller
public class CustomerRecharge {
    // spring 注解
    private CustomerConsumeServiceMapper customerConsumeServiceMapper;
    private static final MyLogger LOGGER = new MyLogger(CustomerRecharge.class);

    /**
     * 查询会员充值记录
     * 
     * @param consume
     *            查询条件
     * @param pageBean
     *            分页辅助类
     * @param createTimeF
     *            查询开始时间
     * @param createTimeT
     *            查询结束时间
     * @param showFlag
     *            显示标记
     * @param attr
     *            内容
     * @return
     * @throws ParseException
     */
    @RequestMapping("/initrecharge")
    public ModelAndView queryCustomerConsume(CustomerConsume consume,
            PageBean pageBean, String createTimeF, String createTimeT,
            String showFlag, String[] attr) throws ParseException {
        // 设置页面跳转路径
        pageBean.setUrl("initrecharge.htm");
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
        // 结果集
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            // 将时间按照指定格式转换
            consume.setCreateTime(createTimeF == null || "".equals(createTimeF) ? null
                    : formatDate.parse(createTimeF));
            consume.setCreateTimeTo(createTimeT == null
                    || "".equals(createTimeT) ? null : formatDate
                    .parse(createTimeT));
            resultMap.put("pageBean", customerConsumeServiceMapper
                    .selectCustConsumeByCustConsume(consume, pageBean, "2"));
            resultMap.put("showFlag", showFlag);
            resultMap.put("attr", attr);
            resultMap.put("consume", consume);
            if (null != consume.getCustomerUsername()) {
                LOGGER.info("获取会员：" + consume.getCustomerUsername() + "的充值记录！");
            }
            return new ModelAndView(CustomerConstantStr.CUSTOMERECHARGE)
                    .addAllObjects(resultMap);
        } finally {
            formatDate = null;
            resultMap = null;
        }
    }

    public CustomerConsumeServiceMapper getCustomerConsumeServiceMapper() {
        return customerConsumeServiceMapper;
    }

    @Resource(name = "customerConsumeServiceMapper")
    public void setCustomerConsumeServiceMapper(
            CustomerConsumeServiceMapper customerConsumeServiceMapper) {
        this.customerConsumeServiceMapper = customerConsumeServiceMapper;
    }

}
