package com.ningpai.site.threepart.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.customer.bean.Customer;
import com.ningpai.customer.service.CustomerServiceMapper;
import com.ningpai.other.bean.CustomerAllInfo;
import com.ningpai.other.util.IPAddress;
import com.ningpai.site.customer.mapper.CustomerMapper;
import com.ningpai.site.customer.service.CustomerServiceInterface;
import com.ningpai.site.threepart.bean.ThreePart;
import com.ningpai.site.threepart.service.ThreePartService;
import com.ningpai.site.threepart.util.SinaMessage;
import com.ningpai.system.bean.Auth;
import com.ningpai.system.service.AuthService;

/**
 * 新浪登陆回调
 * 
 * @author ggn
 * 
 */
@Controller
public class AfterSinaLoginController {

    private AuthService authService;

    private CustomerServiceInterface customerServiceInterface;

    private CustomerServiceMapper customerServiceMapper;

    private CustomerMapper customerMapper;

    private ThreePartService threePartService;

    /**
     * sina回调函数
     * 
     * @throws IOException
     * @throws HttpException
     */
    @RequestMapping("afterloginsina")
    public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response, String[] args) throws IOException {
        response.setContentType("text/html; charset=utf-8");
        Auth auth = authService.findAuthByAuthType("2");
        if (auth != null) {
            String appID = auth.getAuthClientId();
            String appKEY = auth.getAuthClientSecret();
            String redirectURI = auth.getAuthRedirectUri();
            String code = request.getParameter("code");
            String url = "https://api.weibo.com/oauth2/access_token?client_id=" + appID + "&client_secret=" + appKEY + "&grant_type=authorization_code&redirect_uri=" + redirectURI
                    + "&code=" + code;
            String userInfo;
            PostMethod getMethod = new PostMethod(url);
            HttpClient client = new HttpClient();
            client.executeMethod(getMethod);
            userInfo = getMethod.getResponseBodyAsString();
            JSONObject jsonData = JSONObject.fromObject(userInfo);
            if (jsonData == null) {
                return new ModelAndView(new RedirectView("404.html"));
            }
            if (jsonData.get("access_token") == null) {
                return new ModelAndView("redirect:/");
            }
            String accessToken = jsonData.get("access_token").toString();
            String uid = jsonData.get("uid").toString();
            ThreePart tp = threePartService.selectThreePart(uid);
            if (tp != null) {
                // 获取用户信息
                Customer cus = customerServiceInterface.queryCustomerById(tp.getThreePartMemberId());
                request.getSession().setAttribute("cust", cus);
                request.getSession().setAttribute("isThirdLogin", "1");
                request.getSession().setAttribute("customerId", cus.getCustomerId());
            } else {
                // 注册流程
                // 获取QQ信息
                Map<String, String> userData = SinaMessage.getSinaMessage(accessToken, uid);
                CustomerAllInfo allInfo = new CustomerAllInfo();
                allInfo.setLoginIp(IPAddress.getIpAddr(request));
                allInfo.setPointLevelId(2L);
                allInfo.setCustomerUsername(accessToken);
                allInfo.setCustomerPassword("");
                allInfo.setCustomerNickname(userData.get("screen_name"));
                allInfo.setInfoGender("m".equals(userData.get("gender")) ? "1" : "2");
                allInfo.setCustomerImg(userData.get("headimg").toString());
                int f = customerServiceMapper.addCustomer(allInfo);
                if (f == 1) {
                    Map<String, Object> paramMap = new HashMap<String, Object>();
                    paramMap.put("username", accessToken);
                    paramMap.put("password", "");
                    Customer customer = customerMapper.selectCustomerByNamePwd(paramMap);
                    tp = new ThreePart();
                    tp.setThreePartUid(uid);
                    tp.setThreePartToken(accessToken);
                    tp.setThreePartMemberId(customer.getCustomerId());
                    threePartService.insertThreePart(tp);
                    Customer cus = customerServiceInterface.queryCustomerById(tp.getThreePartMemberId());
                    request.getSession().setAttribute("cust", cus);
                    request.getSession().setAttribute("isThirdLogin", "1");
                    request.getSession().setAttribute("customerId", cus.getCustomerId());
                }
            }
        }
        return new ModelAndView("redirect:/");
    }

    public AuthService getAuthService() {
        return authService;
    }

    @Resource(name = "authService")
    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }

    public CustomerMapper getCustomerMapper() {
        return customerMapper;
    }

    @Resource(name = "customerMapperSite")
    public void setCustomerMapper(CustomerMapper customerMapper) {
        this.customerMapper = customerMapper;
    }

    public CustomerServiceMapper getCustomerServiceMapper() {
        return customerServiceMapper;
    }

    @Resource(name = "customerServiceMapper")
    public void setCustomerServiceMapper(CustomerServiceMapper customerServiceMapper) {
        this.customerServiceMapper = customerServiceMapper;
    }

    public CustomerServiceInterface getCustomerServiceInterface() {
        return customerServiceInterface;
    }

    @Resource(name = "customerServiceSite")
    public void setCustomerServiceInterface(CustomerServiceInterface customerServiceInterface) {
        this.customerServiceInterface = customerServiceInterface;
    }

    public ThreePartService getThreePartService() {
        return threePartService;
    }

    @Resource(name = "ThreePartService")
    public void setThreePartService(ThreePartService threePartService) {
        this.threePartService = threePartService;
    }

}
