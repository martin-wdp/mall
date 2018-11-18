package com.ningpai.site.threepart.controller;

import com.ningpai.customer.bean.Customer;
import com.ningpai.customer.service.CustomerServiceMapper;
import com.ningpai.other.bean.CustomerAllInfo;
import com.ningpai.other.util.IPAddress;
import com.ningpai.site.customer.mapper.CustomerMapper;
import com.ningpai.site.customer.service.CustomerServiceInterface;
import com.ningpai.site.threepart.bean.ThreePart;
import com.ningpai.site.threepart.service.ThreePartService;
import com.ningpai.system.bean.Auth;
import com.ningpai.system.service.AuthService;
import com.ningpai.util.MyLogger;
import com.taobao.api.internal.util.WebUtils;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Date: 12-12-4 Time: 下午4:36
 */
@Controller
public class AfterLoginTaoBaoController extends HttpServlet {

    /** 记录日志对象 */
     private static final MyLogger LOGGER = new MyLogger(AfterLoginTaoBaoController.class);

    private AuthService authService;

    private CustomerServiceInterface customerServiceInterface;

    private CustomerServiceMapper customerServiceMapper;

    private CustomerMapper customerMapper;

    private ThreePartService threePartService;

    /**
     * QQ登录
     * 
     * @param request
     * @param response
     * @return
     * @throws IOException
     *             afterloginqq
     */
    @RequestMapping("afterlogintaobao")
    public ModelAndView afterLoginQQ(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=utf-8");

        // 获取淘宝接口信息
        Auth auth = authService.findAuthByAuthType("11");
        if (auth != null) {
            String appID = auth.getAuthClientId();
            String appKEY = auth.getAuthClientSecret();
            String redirectURI = auth.getAuthRedirectUri();
            String code = request.getParameter("code");
            String url = "https://oauth.taobao.com/token";
            Map<String, String> props = new HashMap<String, String>();
            props.put("grant_type", "authorization_code");
            /* 测试时，需把test参数换成自己应用对应的值 */
            props.put("code", code);
            props.put("client_id", appID);
            props.put("client_secret", appKEY);
            props.put("redirect_uri", redirectURI);
            props.put("view", "web");
            String s = "";
            JSONObject jsonData = null;
            try {
                s = WebUtils.doPost(url, props, 30000, 30000);

                if (s != null) {
                    jsonData = JSONObject.fromObject(s);
                }
                //System.out.println(s);
            } catch (IOException e) {
                LOGGER.error("",e);
            }

            if (jsonData != null) {
                String openid = jsonData.getString("taobao_user_id").toString();
                String accessToken = jsonData.getString("access_token").toString();
                ThreePart tp = threePartService.selectThreePart(openid);

                if (tp != null) {
                    // 获取用户信息
                    Customer cus = customerServiceInterface.queryCustomerById(tp.getThreePartMemberId());
                    request.getSession().setAttribute("cust", cus);
                    request.getSession().setAttribute("customerId", cus.getCustomerId());
                    request.getSession().setAttribute("isThirdLogin", "1");
                } else {
                    // 注册流程
                    // 获取QQ信息
                    CustomerAllInfo allInfo = new CustomerAllInfo();
                    allInfo.setLoginIp(IPAddress.getIpAddr(request));
                    allInfo.setPointLevelId(2L);
                    allInfo.setCustomerUsername(accessToken);
                    allInfo.setCustomerPassword("");
                    allInfo.setCustomerNickname("淘宝" + openid);
                    allInfo.setInfoGender("1");
                    allInfo.setCustomerImg("");
                    int f = customerServiceMapper.addCustomer(allInfo);
                    if (f == 1) {
                        Map<String, Object> paramMap = new HashMap<String, Object>();
                        paramMap.put("username", accessToken);
                        paramMap.put("password", "");
                        Customer customer = customerMapper.selectCustomerByNamePwd(paramMap);
                        tp = new ThreePart();
                        tp.setThreePartUid(openid);
                        tp.setThreePartToken(accessToken);
                        tp.setThreePartMemberId(customer.getCustomerId());
                        threePartService.insertThreePart(tp);
                        Customer cus = customerServiceInterface.queryCustomerById(tp.getThreePartMemberId());
                        request.getSession().setAttribute("cust", cus);
                        request.getSession().setAttribute("customerId", cus.getCustomerId());
                        request.getSession().setAttribute("isThirdLogin", "1");
                    }
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
