package com.ningpai.site.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ningpai.util.MyLogger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 设置菜单排序
 * 
 * @author ggn
 *
 */
@Controller
public class NavSortController {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(NavSortController.class);

    /**
     * 导航默认选中。
     * 
     * @param navSort
     * @param request
     */
    @RequestMapping("/navsort")
    @ResponseBody
    public void navSort(String navsort, HttpServletRequest request) {
        if (navsort != null && !"".equals(navsort)) {
            request.getSession().setAttribute("navsort", navsort);
        }

    }

    /**
     * 获取导航排序
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/getnavsort")
    public void getnavSort(HttpServletRequest request,
            HttpServletResponse response) {
        PrintWriter pw = null;
        try {
            pw = response.getWriter();
            Object sort = request.getSession().getAttribute("navsort");
            if (sort != null) {
                pw.print(sort.toString());
            } else {
                pw.print("-2");
            }
        } catch (IOException e) {
            LOGGER.error("",e);
            pw = null;
        } finally {
            if (pw != null) {
                pw.flush();
                pw.close();
            }

        }

    }

}
