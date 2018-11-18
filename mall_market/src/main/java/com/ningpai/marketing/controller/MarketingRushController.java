/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.marketing.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ningpai.common.safe.CSRFTokenManager;
import com.ningpai.common.util.ConstantUtil;
import com.ningpai.marketing.bean.MarketingRushTime;
import com.ningpai.marketing.service.MarketingService;
import com.ningpai.util.PageBean;

/**
 * 抢购控制器
 * 
 * @author NINGPAI-LIH
 * @since 2014年11月24日15:32:10
 * 
 */
@Controller
public class MarketingRushController {

    private static final String REDIRECT = "redirect:queryrushtimelist.htm";

    @Resource(name = "MarketingService")
    private MarketingService marketingService;

    /**
     * 抢购时间段列表
     * 
     * @param pageBean
     *            分页参数
     * @return 抢购时间列表
     */
    @RequestMapping("queryrushtimelist")
    public ModelAndView queryRushTimeList(PageBean pageBean, HttpServletRequest request) {
        return new ModelAndView("jsp/marketing/rushtimelist").addObject("pageBean", marketingService.queryRushTime(pageBean)).addObject(ConstantUtil.CSRFTOKEN,
                request.getSession().getAttribute(CSRFTokenManager.CSRF_TOKEN_FOR_SESSION_ATTR_NAME).toString());

    }

    /**
     * 添加抢购时间
     * 
     * @param rushTime
     *            添加参数
     * @return 抢购时间列表
     */
    @RequestMapping("addmarketrushtime")
    public ModelAndView addMarketRushTime(MarketingRushTime rushTime) {
        marketingService.addRushTime(rushTime);
        return new ModelAndView("redirect:queryrushtimelist.htm");
    }

    /**
     * 修改抢购时间
     * 
     * @param rushTime
     *            修改参数
     * @return
     */
    @RequestMapping("updatemarketrushtime")
    public ModelAndView updateMarketRushTime(MarketingRushTime rushTime) {
        marketingService.updateRushTime(rushTime);
        return new ModelAndView(REDIRECT);
    }

    /**
     * 删除抢购时间
     * 
     * @param tId
     *            要删除 抢购时间id
     * @return
     */
    @RequestMapping("delrushtime")
    public ModelAndView delRushTime(Long tId) {
        marketingService.delRushTime(tId);
        return new ModelAndView(REDIRECT);
    }

    /**
     * 批量删除抢购时间
     * 
     * @param tId
     *            抢购时间数组
     * @return
     */
    @RequestMapping("delrushtimes")
    public ModelAndView delRushTimes(Long[] tId) {
        marketingService.delRushTimes(tId);
        return new ModelAndView(REDIRECT);
    }

    /**
     * 查询可用的抢购时间
     * 
     * @return
     */
    @RequestMapping("queryrushtimebyfalg")
    @ResponseBody
    public List<MarketingRushTime> queryRushTimeByFalg() {
        return marketingService.queryRushByFlag();
    }
}
