/**
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.logger.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ningpai.logger.bean.OperationLog;
import com.ningpai.logger.service.OperaLogService;
import com.ningpai.logger.util.ConstantUtil;
import com.ningpai.util.PageBean;
import org.springframework.web.servlet.view.RedirectView;

/**
 * 日志操作Controller
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年6月27日 上午10:15:26
 * @version 0.0.1
 */
@Controller
public class OperaLogController {
    // 属性注入
    private OperaLogService operaLogService;
    public static final String INITOPERALOG = "initoperalog.htm";

    /**
     * 初始化日志列表
     * 
     * @param pageBean
     *            分页辅助类 {@link PageBean}
     * @param log
     *            日志对象 {@link OperationLog}
     * @return {@link ModelAndView}
     */
    @RequestMapping("/initoperalog")
    public ModelAndView initOperaLog(HttpServletRequest request,
            PageBean pageBean, String startTime, String endTime,
            OperationLog log) {
        // 设置跳转URL
        pageBean.setUrl("initoperalog.htm");
        request.setAttribute("startTime", startTime);
        request.setAttribute("endTime", endTime);
        // 查询操作日志
        return new ModelAndView(ConstantUtil.OPREALIST, "pageBean",
                operaLogService.selectAllOpera(pageBean, log, startTime,
                        endTime)).addObject("operationLog", log).addObject(
                "operationLogList", operaLogService.selectDistinctOpName());
    }

    /**
     * ajax查询日志
     * 
     * @param pb
     * @return PageBean
     */
    @RequestMapping("/operalogajax")
    @ResponseBody
    public PageBean operaLogAjax(PageBean pb) {
        return operaLogService.queryNewLog(pb);
    }

    /**
     * 根据时间段 导出日志
     * 
     * @param days
     *            天 {@link Long}
     */
    @RequestMapping("/exportexcel")
    public void exportExcel(HttpServletResponse response, Long days) {
        operaLogService.exportExcel(response, days);
    }

    /**
     * deleteLog
     * @param request
     * @param response
     * @param opName
     * @return
     */
    @RequestMapping("/deletelog")
    public ModelAndView deleteLog(HttpServletRequest request,
            HttpServletResponse response, String opName) {
        operaLogService.deleteLog(request.getParameterValues("parameterIds"));

        return new ModelAndView(new RedirectView(INITOPERALOG)).addObject(
                "opName", opName);

    }

    /**
     * getOperaLogService
     * @return
     */
    public OperaLogService getOperaLogService() {
        return operaLogService;
    }

    /**
     * setOperaLogService
     * @param operaLogService
     */
    @Resource(name = "operaLogService")
    public void setOperaLogService(OperaLogService operaLogService) {
        this.operaLogService = operaLogService;
    }

}
