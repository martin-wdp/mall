package com.ningpai.system.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.ningpai.system.exception.NPException;

/**
 * Controller异常捕获类
 * 
 * @Description:
 * @author NINGPAI_xiaomm
 * @since 2014-03-20 11:03:23
 * @version V1.0
 */
public class BaseController {

    /**
     * 捕获控制层的异常错误
     * 
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(value = { NPException.class })
    public String exp(HttpServletRequest request, NPException ex) {
        request.setAttribute("exNumber", ex.getErrNo());
        request.setAttribute("exMessage", ex.getErrNoMsg());
        // 根据不同错误转向不同页面
        return "jsp/public/error";
    }

}
