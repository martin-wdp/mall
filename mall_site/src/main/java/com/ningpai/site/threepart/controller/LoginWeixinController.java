package com.ningpai.site.threepart.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ningpai.system.bean.Auth;
import com.ningpai.system.service.AuthService;
import com.ningpai.util.MyLogger;
import com.qq.connect.QQConnectException;

/**
 * 微信登录
 * 
 * @author ggn
 *
 */
@Controller
public class LoginWeixinController {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(LoginQQController.class);

    private AuthService authService;

    @RequestMapping("loginweixin")
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws QQConnectException {
        Auth auth = authService.findAuthByAuthType("7");
        if (auth != null) {
            String appID = auth.getAuthClientId();
            String redirectURI = auth.getAuthRedirectUri();
            String url = "https://open.weixin.qq.com/connect/qrconnect?appid=" + appID + "&redirect_uri=" + redirectURI
                    + "&response_type=code&scope=snsapi_login&state=STATE#wechat_redirect";
            // String url = "https://graph.qq.com/oauth2.0/authorize?client_id="
            // + app_ID + "&redirect_uri=" + redirect_URI +
            // "&response_type=code&state=test&scope=get_user_info,add_topic,add_one_blog,add_album,upload_pic,list_album,add_share,check_page_fans,add_t,add_pic_t,del_t,get_repost_list,get_info,get_other_info,get_fanslist,get_idollist,add_idol,del_ido,get_tenpay_addr";
            try {
                response.sendRedirect(url);
            } catch (IOException e) {
                LOGGER.error("微信登录" + e);
            }
        }

    }

    public AuthService getAuthService() {
        return authService;
    }

    @Resource(name = "authService")
    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }

}
