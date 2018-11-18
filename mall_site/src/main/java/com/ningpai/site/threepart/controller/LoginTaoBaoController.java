package com.ningpai.site.threepart.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ningpai.util.MyLogger;
import com.ningpai.system.bean.Auth;
import com.ningpai.system.service.AuthService;
import com.qq.connect.QQConnectException;

/**
 * Date: 12-12-4 Time: 上午10:28
 */
@Controller
public class LoginTaoBaoController {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(LoginTaoBaoController.class);

    private AuthService authService;

    /**
     * 淘宝登录
     * 
     * @param request
     * @param response
     */
    @RequestMapping("logintaobao")
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws QQConnectException {
        Auth auth = authService.findAuthByAuthType("11");
        if (auth != null) {
            String appID = auth.getAuthClientId();
            String redirectURI = auth.getAuthRedirectUri();
            String url = "https://oauth.taobao.com/authorize?response_type=code&client_id=" + appID + "&redirect_uri=" + redirectURI + "&state=1212&view=web";
            try {
                response.sendRedirect(url);
            } catch (IOException e) {
                LOGGER.error("淘宝回调" + e);
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
