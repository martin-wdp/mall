package com.ningpai.util;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Controller
public class VerifyLoginServlet {

    /**
     * 登录验证
     */
    @RequestMapping("verifyLoginServlet")
    @ResponseBody
    public int doPost(HttpServletRequest request, HttpServletResponse response) {

        // 秘钥
        String privateKey = null;

        Properties pop = new Properties();
        InputStream inputStream = this
                .getClass()
                .getClassLoader()
                .getResourceAsStream(
                        "com/ningpai/web/config/geetest.properties");

        try {
            pop.load(inputStream);
        } catch (IOException e) {
            inputStream = null;
            pop = null;
        }
        privateKey = pop.getProperty("PRIVATEKEY");

        GeetestLib geetest = new GeetestLib(privateKey);

        String gtResult = "fail";

        if (geetest.resquestIsLegal(request)) {
            gtResult = geetest.enhencedValidateRequest(request);
            System.out.println(gtResult);
        } else {
            gtResult = "fail";

        }

        if (gtResult.equals("success")) {
            // 验证通过
            return 1;

        } else if (gtResult.equals("forbidden")) {
            // 安装版本不匹配
            return 0;
        } else {
            // 验证失败
            return -1;
        }

    }
}
