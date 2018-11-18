/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.customer.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.ningpai.util.MyLogger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ningpai.customer.bean.ComplainVo;
import com.ningpai.customer.bean.OrderComplainBack;
import com.ningpai.customer.service.OrderComplainBackService;
import com.ningpai.other.util.CustomerConstantStr;
import com.ningpai.util.PageBean;
import com.ningpai.util.UtilDate;

/**
 * 投诉控制层
 * 
 * @author jiping
 * @since 2014年7月22日 下午5:38:17
 * @version 0.0.1
 */
@Controller
public class OrderComplainBackController {
    private OrderComplainBackService orderComplainBackService;

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(
            OrderComplainBackController.class);

    /**
     * 初始化未处理投诉列表
     * 
     * @param pageBean
     *            {@link com.ningpai.util.PageBean}
     * @return
     */
    @RequestMapping("/ordercomplainback")
    public ModelAndView orderComplainBack(PageBean pageBean,
            ComplainVo orderCB, String[] attr, String create, String createT) {
        // 设置跳转路径
        pageBean.setUrl("ordercomplainback.htm");

        // 结果集
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            orderCB.setComplainTime(create == null || "".equals(create) ? null
                    : UtilDate.stringToDateMM(create));
            orderCB.setCreateTimeTo(createT == null || "".equals(createT) ? null
                    : UtilDate.stringToDateMM(createT));
            resultMap.put("attr", attr);
            resultMap.put("orderCB", orderCB);
            resultMap.put(CustomerConstantStr.PAGEBEAN,
                    orderComplainBackService.queryComplainList(pageBean,
                            orderCB));
            return new ModelAndView("jsp/customer/ordercomplainback")
                    .addAllObjects(resultMap);
        } finally {
            resultMap = null;
        }
    }

    /**
     * 初始化已处理投诉列表
     * 
     * @param pageBean
     *            {@link com.ningpai.util.PageBean}
     * @return
     */
    @RequestMapping("/complainhad")
    public ModelAndView orderComplainHadBack(PageBean pageBean,
            ComplainVo orderCB, String[] attr, String create, String createT) {
        // 设置跳转路径
        pageBean.setUrl("ordercomplainback.htm");

        // 结果集
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            orderCB.setComplainTime(create == null || "".equals(create) ? null
                    : UtilDate.stringToDateMM(create));
            orderCB.setCreateTimeTo(createT == null || "".equals(createT) ? null
                    : UtilDate.stringToDateMM(createT));
            resultMap.put("attr", attr);
            resultMap.put("orderCB", orderCB);
            resultMap.put(CustomerConstantStr.PAGEBEAN,
                    orderComplainBackService.queryComplainHadList(pageBean,
                            orderCB));
            return new ModelAndView("jsp/customer/ordercomplainhadback")
                    .addAllObjects(resultMap);
        } finally {
            resultMap = null;
        }
    }

    /**
     * 根据id查询投诉记录
     * 
     * @param request
     * @param complainId
     * @return OrderComplainBack
     */
    @RequestMapping("/selectcombyid")
    public ModelAndView selecComById(HttpServletRequest request, long complainId) {
        OrderComplainBack complain = orderComplainBackService
                .selectByPrimaryKey(complainId);
        // 非空验证投诉编号
        if (null != complain.getComplainId()) {
            // 日志记录
            LOGGER.info("获取编号为:" + complain.getComplainId() + "的投诉记录！");
        }
        return new ModelAndView("jsp/customer/ordercomplaindetail").addObject(
                "complain", complain);
    }

    /**
     * 回复投诉
     * 
     * @param complain
     * @param request
     * @return
     */
    @RequestMapping("/replaycomplain")
    @ResponseBody
    public Object replayComplain(@Valid ComplainVo complain,
            HttpServletRequest request) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("replayUsername", request.getSession()
                .getAttribute("name"));
        paramMap.put("replayContext", complain.getReplayContext());
        paramMap.put("complainId", complain.getComplainId());
        // 非空验证投诉编号
        if (null != complain.getComplainId()) {
            // 日志记录
            LOGGER.info("回复投诉编号为:" + complain.getComplainId() + "的投诉记录！");
        }
        return orderComplainBackService.replayCom(paramMap);
    }

    public OrderComplainBackService getOrderComplainBackService() {
        return orderComplainBackService;
    }

    @Resource(name = "orderComplainBackService")
    public void setOrderComplainBackService(
            OrderComplainBackService orderComplainBackService) {
        this.orderComplainBackService = orderComplainBackService;
    }

}
