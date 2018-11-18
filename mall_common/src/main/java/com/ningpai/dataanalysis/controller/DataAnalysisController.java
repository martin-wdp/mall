/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.dataanalysis.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ningpai.dataanalysis.service.DataAnalysisService;
import com.ningpai.util.PageBean;
import com.ningpai.util.SelectBean;
import com.ningpai.util.UtilDate;

/**
 * 数据分析控制层
 * 
 * @author zhangsl
 * @since 2014年12月19日 下午1:49:11
 * @version
 */
@Controller
public class DataAnalysisController {
    @Resource(name = "DataAnalysisService")
    private DataAnalysisService dataAnalysisService;

    /**
     * 显示所有数据分析数据
     * 
     * @param pb
     * @param selectBean
     *            查询条件
     * @param request
     * @param response
     * @param startTime
     *            开始时间
     * @param endTime
     *            结束时间
     * @return ModelAndView
     */
    @RequestMapping("/showThirdInfo")
    public ModelAndView showThirdInfo(PageBean pb, SelectBean selectBean,
            HttpServletRequest request, HttpServletResponse response,
            String startTime, String endTime) throws ParseException {
        String startTime1 = startTime;
        String endTime1 = endTime;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        // 如果查询内容为空，就把查询条件也置空
        if ("".equals(selectBean.getSearchText())) {
            selectBean.setCondition("");
        }
        // 如果时间为空，就初始化一个时间
        if (startTime1 == null || endTime1 == null || "".equals(startTime1)
                || "".equals(endTime1)) {
            endTime1 = UtilDate.todayFormat(new Date());
            startTime1 = UtilDate.addDay(endTime1, -7);

        }
        // 时间格式化
        Date startDate = format.parse(startTime1);
        Date endDate = format.parse(endTime1);
        ModelAndView mav = new ModelAndView();
        // 跳转页面
        mav.setViewName("jsp/dataAnalysis/dataAnalysislist");
        // 查询数据信息 并把开始时间和结束时间返回
        mav.addObject(
                "pb",
                dataAnalysisService.selectAllData(pb, selectBean, startDate,
                        endDate)).addObject("startTime", startTime1)
                .addObject("endTime", endTime1);
        return mav;
    }

}
