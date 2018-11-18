package com.ningpai.cloudshop;

import com.ningpai.cloudshop.bean.CloudshopAuthorInfo;
import com.ningpai.cloudshop.common.Commons;
import com.ningpai.cloudshop.service.CloudshopAuthorInfoService;
import com.ningpai.system.service.BasicSetService;
import com.qianmi.open.api.ApiException;
import com.qianmi.open.api.response.TokenResponse;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 云销contrller Created by liangck on 15/6/23.
 */
@Controller
public class ShopMainController {

    /**
     * 日志记录
     */
    private Logger logger = Logger.getLogger(ShopMainController.class);

    // private static final String NAME = "name";
    private static final String JSP_CLOUDSHOP_AUTHORERROR = "jsp/cloudshop/authorError";
    private static final String ERROR = "error";

    /** 授权信息service **/
    @Resource(name = "CloudshopAuthorInfoService")
    private CloudshopAuthorInfoService authorInfoService;

    /**
     * 云销货源市场首页
     * 
     * @param request
     * @return
     */
    @RequestMapping("/toShopMainView")
    public ModelAndView toShopMainView(HttpServletRequest request) {
        CloudshopAuthorInfo authorInfo = authorInfoService.queryAuthoredInfo();
        if (authorInfo == null) {
            return new ModelAndView("jsp/cloudshop/author").addObject("siteUrl",
                    request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()).addObject("state", Commons.STATE);
        }
        return new ModelAndView("jsp/cloudshop/authorInfo").addObject("authorId", authorInfo.getCloudshopUserId());
    }

    /**
     * 授权回调地址
     * 
     * @param code
     * @param state
     * @return
     * @throws ApiException
     */
    @RequestMapping("/authorSuccess")
    public ModelAndView authorSuccess(String code, String state, String error, HttpServletRequest request) {
        if (error == null) {
            TokenResponse response = null;
            try {
                response = authorInfoService.getAuthodAccessInfo(code);
                if (!"0".equals(response.getErrorCode())) {
                    return new ModelAndView(JSP_CLOUDSHOP_AUTHORERROR).addObject(ERROR, response.getMsg());
                }
            } catch (ApiException e) {
                logger.info("获取授权失败..msg: " ,e);
                /* 跳转到授权失败页面 */
                return new ModelAndView(JSP_CLOUDSHOP_AUTHORERROR).addObject(ERROR, e.getErrMsg());
            }
            /* 处理授权结果 */
            authorInfoService.processTokenResponse(request, response);
            return new ModelAndView("jsp/cloudshop/authorSuccess").addObject("userId", response.getUserId());
        }
        /* 跳转到授权失败页面 */
        return new ModelAndView(JSP_CLOUDSHOP_AUTHORERROR).addObject(ERROR, error);
    }

    /**
     * 清空授权账户信息，并跳转账户信息主页面
     * 
     * @param request
     * @return
     */
    @RequestMapping("/clearAuthorInfo")
    public ModelAndView clearAuthorInfo(HttpServletRequest request) {
        authorInfoService.clearAuthorInfo();
        return new ModelAndView(new RedirectView("toShopMainView.htm"));
    }

    /**
     * 未授权页面
     *
     * @return
     */
    @RequestMapping("/noAuthorPage")
    public ModelAndView noAuthorPage() {
        return new ModelAndView("jsp/cloudshop/noauthor");
    }

}
