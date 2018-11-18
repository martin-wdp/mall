/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.util;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 * 控制器-默认分页行数设置
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年5月26日下午4:36:02
 */
@Controller
public class PageRowsController {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(PageRowsController.class);

    /**
     * 显示默认分页行数
     * 
     * @return
     */
    @RequestMapping("/showPageRows")
    public ModelAndView showPageRows() {
        return new ModelAndView("jsp/system/page_rows", "pageRows", PageRowsUtil.getPageRows());
    }

    /**
     * 修改默认分页行数
     * 
     * @param pageRows
     *            分页行数
     * @return
     */
    @RequestMapping("/updatePageRows")
    public ModelAndView updatePageRows(Integer pageRows) {
        PageRowsUtil.setPageRows(pageRows);
        return new ModelAndView(new RedirectView("showPageRows.htm"));
    }

    /**
     * AJAX获取默认分页行数
     * 
     * @return
     */
    @ResponseBody
    @RequestMapping("/getPageRows")
    public Integer getPageRows() {
        return PageRowsUtil.getPageRows();
    }

    /**
     * 修改默认分页行数
     * 
     * @param pageRows
     *            分页行数
     * @return
     */
    @ResponseBody
    @RequestMapping("/updatePageRowsAjax")
    public Integer updatePageRowsAjax(Integer pageRows) {
        try {
            PageRowsUtil.setPageRows(pageRows);
        } catch (Exception e) {
            LOGGER.error("修改默认分页[" + pageRows + "]行数失败：=>",e);
        }
        return pageRows;
    }

}
