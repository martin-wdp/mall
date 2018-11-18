package com.ningpai.api.controller;

import com.ningpai.api.service.ICustomerService;
import com.ningpai.api.util.OpenUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 会员接口
 * 
 * @author lih
 * @version 2.0
 * @since 15/8/26
 */
@Controller
@RequestMapping("customer")
public class OCustomerController {

    @Resource(name = "openCustomerService")
    private ICustomerService iCustomerService;

    /**
     * 查询会员列表
     * 
     * @param sign
     *            签名验证 userName+timestamp+key通过Md5进行加密
     * @param pageNo
     *            页数
     * @param pageSize
     *            每页大小
     * @param timestamp
     *            时间戳 格式：YYYYMMddHHmm 与系统时间不能相差6分钟
     * @param userName
     *            用户名
     * @param token
     *            请求token
     * @retur 会员列表
     */
    @RequestMapping(value = "list", produces = OpenUtil.PRODUCESSTR)
    @ResponseBody
    public String list(String sign, Integer pageNo, Integer pageSize, String timestamp, String userName, String token) {
        // 验证安全
        String msg = OpenUtil.isCheck(timestamp, userName, sign, token, "查询会员列表");
        // 判断是否通过
        if (!"1".equals(msg)) {
            return msg;
        }
        return iCustomerService.list(pageNo, pageSize);
    }

    /**
     * 查询会员列表
     * 
     * @param sign
     *            签名验证 userName+timestamp+token+key通过Md5进行加密
     * @param timestamp
     *            时间戳 格式：YYYYMMddHHmm 与系统时间不能相差6分钟
     * @param userName
     *            用户名
     * @param token
     *            请求token
     * @param customerUserName
     *            会员名
     * @param customerPassword
     *            密码
     * @param customerNickName
     *            会员别名
     * @param phone
     *            手机
     * @param email
     *            邮箱
     * @return 0:异常 1：添加成功 2：会员已存在 3：手机或邮箱已存在 4：名称以及密码为空或格式不正确
     */
    @RequestMapping(value = "/add", produces = OpenUtil.PRODUCESSTR)
    @ResponseBody
    public String addCustomer(String customerUserName, String customerPassword, String customerNickName, String phone, String email, String sign, String timestamp,
            String userName, String token) {
        // 验证安全
        String msg = OpenUtil.isCheck(timestamp, userName, sign, token, "添加  会员");
        // 判断是否通过
        if (!"1".equals(msg)) {
            return msg;
        }
        return String.valueOf(iCustomerService.addCustomer(customerUserName, customerPassword, customerNickName, phone, email));
    }

    /**
     * 查询会员详情
     * 
     * @param sign
     *            签名验证 userName+timestamp+token+key通过Md5进行加密
     * @param timestamp
     *            时间戳 格式：YYYYMMddHHmm 与系统时间不能相差6分钟
     * @param userName
     *            用户名
     * @param token
     *            请求token
     * @param customerUserName
     *            会员名
     * @return 会员详情 失败返回签名错误
     */
    @RequestMapping(value = "get", produces = OpenUtil.PRODUCESSTR)
    @ResponseBody
    public String get(String customerUserName, String sign, String timestamp, String userName, String token) {
        // 验证安全
        String msg = OpenUtil.isCheck(timestamp, userName, sign, token, "获取会员详情");
        // 判断是否通过
        if (!"1".equals(msg)) {
            return msg;
        }
        return iCustomerService.get(customerUserName);
    }

}
