package com.qpmall.site.qpsys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ly-qpmall on 2015/10/28.
 */
@Controller
public class QpSysUtilController {

    /**
     * 跟踪首页导航点击选择目标
     * @param request
     * @return
     */
    @RequestMapping("/getNavsortinfo")
    @ResponseBody
    String getNavsortinfo(HttpServletRequest request) {
        String result="";
        if(request.getSession().getAttribute("navsort")==null||"".equals(request.getSession().getAttribute("navsort"))){
            result="1";
        }else {
            result=(String) request.getSession().getAttribute("navsort");;
        }
        return result;
    }
}
