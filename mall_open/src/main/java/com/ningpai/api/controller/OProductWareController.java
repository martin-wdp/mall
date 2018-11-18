package com.ningpai.api.controller;

import com.ningpai.api.service.IProductWareService;
import com.ningpai.api.util.OpenUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 商品库存接口
 * 
 * @author lih
 * @version 2.0
 * @since 15/9/7
 */
@Controller
@RequestMapping("productware")
public class OProductWareController {

    /**
     * 开放接口-商品库存service
     */
    @Resource(name = "openProductWareService")
    private IProductWareService service;

    /**
     * 根据货号查询库存 调用方式 productware/get.htm
     *
     * @param goodsInfoItemNo
     *            货品编号
     * @param sign
     *            签名验证 userName+timestamp+token+key通过Md5进行加密
     * @param timestamp
     *            时间戳 格式：YYYYMMddHHmm 与系统时间不能相差6分钟
     * @param token
     *            请求token
     * @param userName
     *            用户名
     * @return 失败返回签名错误 成功返回库存信息
     */
    @RequestMapping(value = "get", produces = "application/json; charset=utf-8")
    @ResponseBody
    public String get(String goodsInfoItemNo, String sign, String timestamp, String userName, String token) { // 验证安全
        String msg = OpenUtil.isCheck(timestamp, userName, sign, token, "查询编号为" + goodsInfoItemNo + OpenUtil.GOODSSTR);
        // 判断是否通过
        if (!"1".equals(msg)) {
            return msg;
        }

        return service.get(goodsInfoItemNo);
    }

    /**
     * 添加库存 调用方式 productware/addstock.htm
     * 
     * @param goodsInfoItemNo
     *            货品编号
     * @param identifyNo
     *            仓库标识
     * @param count
     *            减去的数量
     * @param sign
     *            签名验证 userName+timestamp+token+key通过Md5进行加密
     * @param timestamp
     *            时间戳 格式：YYYYMMddHHmm 与系统时间不能相差6分钟
     * @param token
     *            请求token
     * @param userName
     *            用户名
     * @return 1；成功 2：商品编号为空
     */
    @RequestMapping(value = "addstock", produces = "application/json; charset=utf-8")
    @ResponseBody
    public String addStock(String goodsInfoItemNo, String identifyNo, Long count, String sign, String timestamp, String userName, String token) {
        String msg = OpenUtil.isCheck(timestamp, userName, sign, token, "添加" + goodsInfoItemNo + OpenUtil.GOODSSTR);
        // 判断是否通过
        if (!"1".equals(msg)) {
            return msg;
        }
        return String.valueOf(service.addStock(goodsInfoItemNo, identifyNo, count));
    }

    /**
     * 减库存 调用方式 productware/minstock.htm
     * 
     * @param goodsInfoItemNo
     *            货品编号
     * @param identifyNo
     *            仓库标识
     * @param count
     *            减去的数量
     * @param sign
     *            签名验证 userName+timestamp+token+key通过Md5进行加密
     * @param timestamp
     *            时间戳 格式：YYYYMMddHHmm 与系统时间不能相差6分钟
     * @param token
     *            请求token
     * @param userName
     *            用户名
     * @return 1；成功 2：商品编号为空
     */
    @RequestMapping(value = "minstock", produces = "application/json; charset=utf-8")
    @ResponseBody
    public String minStock(String goodsInfoItemNo, String identifyNo, Long count, String sign, String timestamp, String userName, String token) {
        String msg = OpenUtil.isCheck(timestamp, userName, sign, token, "减去" + goodsInfoItemNo + OpenUtil.GOODSSTR);
        // 判断是否通过
        if (!"1".equals(msg)) {
            return msg;
        }
        // 设置容器
        return String.valueOf(service.minStock(goodsInfoItemNo, identifyNo, count));
    }
}
