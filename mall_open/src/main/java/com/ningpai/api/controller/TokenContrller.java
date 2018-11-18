package com.ningpai.api.controller;

import com.ningpai.api.service.IEmpowerService;
import com.ningpai.api.util.OpenUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 获取 token 控制器
 *
 *      该控制器用于获取token
 *      使用用户名和分配给用户的key进行获取
 *      获取token之后，使用token获取数据
 *      token有效期为1天，失效重新获取
 *      如果在有效期内重新申请，则返回新的token值
 *
 * @author lih
 * @version 2.0
 * @since 15/9/14
 */

@Controller
public class TokenContrller {
    // 开放接口-获取key
    @Resource(name = "openEmpowerService")
    private IEmpowerService iEmpowerService;

    /**
     * 获取请求token
     * @param userName 用户名
     * @param sign 签名验证 userName+timestamp+key 通过md5方式进行加密
     * @param timestamp 请求时间戳 YYYYMMddHm
     * @return 获取数据用的token
     */
    @RequestMapping(value = "gettoken" ,produces = OpenUtil.PRODUCESSTR)
    @ResponseBody
    public String getToken(String userName,String sign,String timestamp){
        return iEmpowerService.getKey( userName, sign, timestamp);
    }


}
