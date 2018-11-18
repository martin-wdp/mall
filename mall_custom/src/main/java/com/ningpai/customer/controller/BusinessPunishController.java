/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.customer.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.comment.bean.ValueUtil;
import com.ningpai.customer.bean.CustomerPunish;
import com.ningpai.customer.service.CustomerPunishService;
import com.ningpai.customer.service.PunishRecordService;
import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.other.util.CustomerConstantStr;
import com.ningpai.util.PageBean;
import com.ningpai.util.SelectBean;

/**
 * 用户处罚控制器
 * */
@Controller
public class BusinessPunishController {

    @Resource(name = "CustomerPunishService")
    private CustomerPunishService customerPunishService;
    @Resource(name = "PunishRecordService")
    private PunishRecordService punishRecordService;

    /**
     * 查询处罚规则列表
     * 
     * @param pageBean
     *            分页
     * @return
     */
    @RequestMapping("/queryAllPenaltylist")
    public ModelAndView queryAllPenaltylist(PageBean pageBean) {
        return new ModelAndView(CustomerConstantStr.PENALTYLIST, "pb", customerPunishService.selectPunishInfoByPage(pageBean));
    }

    /**
     * 添加处罚规则
     * 
     * @param customerPunish
     *            规则对象{@link com.ningpai.customer.bean.CustomerPunish}
     * 
     * @return
     */
    @RequestMapping("/addPunishInfo")
    public ModelAndView addPunishInfo(CustomerPunish customerPunish) {

        customerPunishService.addPunishInfo(customerPunish);

        return new ModelAndView(new RedirectView(CustomerConstantStr.QUERYALLPENALTYLIST));
    }

    /**
     * 根据规则id 查询规则详情
     * 
     * @param id
     *            规则ID
     * @return
     */
    @RequestMapping("/queryPunishInfoById")
    @ResponseBody
    public CustomerPunish queryPunishInfoById(Long id) {
        return customerPunishService.queryPunishInfoById(id);
    }

    /**
     * 修改处罚规则
     * 
     * @param customerPunish
     *            规则对象{@link com.ningpai.customer.bean.CustomerPunish}
     * @return
     */
    @RequestMapping("/updatePunishInfo")
    public ModelAndView updatePunishInfo(CustomerPunish customerPunish) {

        customerPunishService.updatePunishInfoById(customerPunish);

        return new ModelAndView(new RedirectView(CustomerConstantStr.QUERYALLPENALTYLIST));
    }

    /**
     * 删除处罚规则
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/deletePunishInfo")
    @ResponseBody
    public void deletePunishInfo(HttpServletRequest request, HttpServletResponse response) {
        // 获取规则Id封装成Sting数组
        String[] ids = request.getParameterValues("ids[]");
        // 遍历id
        for (int i = 0; i < ids.length; i++) {
            // 把String类型转为Long类型
            long id = Long.valueOf(ids[i]);
            // 执行删除
            customerPunishService.updateDelflagById(id);
        }

    }

    /**
     * 新删除处罚规则
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/deletenewPunishInfo")
    @ResponseBody
    public void deleteNewPunishInfo(HttpServletRequest request, HttpServletResponse response) {
        // 获取规则Id封装成Sting数组
        String[] ids = request.getParameterValues("id");
        // 遍历id
        for (int i = 0; i < ids.length; i++) {
            // 把String类型转为Long类型
            long id = Long.valueOf(ids[i]);
            // 执行删除
            customerPunishService.updateDelflagById(id);
        }
        // 操作日志
        OperaLogUtil.addOperaLog(request, (String) request.getSession().getAttribute(ValueUtil.NAME), "批量删除规则", (String) request.getSession().getAttribute(ValueUtil.OPERAPATH)
                + "批量删除规则,用户名：" + (String) request.getSession().getAttribute(ValueUtil.NAME));

    }

    /**
     * 查询已被处罚的商家列表
     * 
     * @param pageBean
     * @return
     */
    @RequestMapping("/queryAllPunishedBusiness")
    public ModelAndView queryAllPunishedBusiness(PageBean pageBean, SelectBean selectBean, HttpServletRequest request) {
        if ("".equals(selectBean.getSearchText())) {
            selectBean.setCondition("");
        }
        request.setAttribute("selectBean", selectBean);
        return new ModelAndView(CustomerConstantStr.PUNISHBUSINESSLIST, "pb", punishRecordService.selectPunishedRecordByPage(pageBean, selectBean));
    }
}
