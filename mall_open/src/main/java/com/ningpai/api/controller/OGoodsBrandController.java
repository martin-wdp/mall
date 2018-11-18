package com.ningpai.api.controller;

import com.ningpai.api.service.IGoodsBrandService;
import com.ningpai.api.util.OpenUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 商品分类接口
 * 
 * @author lih
 * @version 2.0
 * @since 15/9/2
 */
@Controller
@RequestMapping("goodsbrand")
public class OGoodsBrandController {

    /**
     * 商品品牌
     */
    @Resource(name = "openGoodsBrandService")
    private IGoodsBrandService iGoodsBrandService;

    /**
     * 查询商品品牌
     * 
     * @param sign
     *            签名验证 userName+timestamp+token+key通过Md5进行加密
     * @param timestamp
     *            时间戳 格式：YYYYMMddHHmm 与系统时间不能相差6分钟
     * @param userName
     *            用户名
     * @param token
     *            请求token
     * @param pageNo
     *            页数
     * @param pageSize
     *            每页大小
     * @return 获取商品品牌详情 失败返回签名错误
     */
    @RequestMapping(value = "list", produces = "application/json; charset=utf-8")
    @ResponseBody
    public String list(String sign, Integer pageNo, Integer pageSize, String timestamp, String userName, String token) {
        // 验证安全
        String msg = OpenUtil.isCheck(timestamp, userName, sign, token, "获取订单列表");
        // 判断是否通过
        if (!"1".equals(msg)) {
            return msg;
        }
        return iGoodsBrandService.list(pageNo, pageSize);
    }
}
