package com.ningpai.api.controller;

import com.ningpai.api.service.IOrderService;
import com.ningpai.api.util.OpenUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 订单接口
 * 
 * @author lih
 * @version 2.0
 * @since 15/8/31
 */
@Controller
@RequestMapping("order")
public class OOrderController {

    /**
     * 开放接口--订单实现
     */
    @Resource(name = "openOrderService")
    private IOrderService orderService;

    /**
     * 订单列表 调用方式 order/list.htm
     *
     * @param pageNo
     *            页数 默认1
     * @param pageSize
     *            每页大小 默认15
     * @param sign
     *            签名验证 userName+timestamp+token+key通过Md5进行加密
     * @param timestamp
     *            时间戳 格式：YYYYMMddHHmm 与系统时间不能相差6分钟
     * @param token
     *            请求token
     * @param userName
     *            用户名
     * @return 查询集合 失败返回签名错误
     */
    @RequestMapping(value = "list", produces = "application/json; charset=utf-8")
    @ResponseBody
    public String list(Integer pageNo, Integer pageSize, String sign, String timestamp, String userName, String token) { // 验证安全
        String msg = OpenUtil.isCheck(timestamp, userName, sign, token, "获取订单列表");
        // 判断是否通过
        if (!"1".equals(msg)) {
            return msg;
        }

        return orderService.list(pageNo, pageSize);
    }

    /**
     * 查询订单详情 调用方式 order/get.htm
     *
     * @param orderCode
     *            订单编号
     * @param sign
     *            签名验证 userName+timestamp+token+key通过Md5进行加密
     * @param timestamp
     *            时间戳 格式：YYYYMMddHHmm 与系统时间不能相差6分钟
     * @param token
     *            请求token
     * @param userName
     *            用户名
     * @return 订单详情 失败返回签名错误
     */
    @RequestMapping(value = "get", produces = "application/json; charset=utf-8")
    @ResponseBody
    public String get(String orderCode, String sign, String timestamp, String userName, String token) {
        // 验证安全
        String msg = OpenUtil.isCheck(timestamp, userName, sign, token, "获取订单编号为" + orderCode + "的订单信息");
        // 判断是否通过
        if (!"1".equals(msg)) {
            return msg;
        }
        return orderService.get(orderCode);
    }

}
