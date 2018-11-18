package com.ningpai.api.controller;

import com.ningpai.api.service.IGoodsProductService;
import com.ningpai.api.util.OpenUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 商品接口
 * 
 * @author lih
 * @version 2.0
 * @since 15/8/25
 */
@Controller
@RequestMapping("goods")
public class OGoodsProductController {

    /**
     * 开放接口-- 货品列表
     */
    @Resource(name = "OpenGoodsProductService")
    private IGoodsProductService goodsProductService;

    /**
     * 获取货品列表 调用方式 goods/list.htm
     * 
     * @param thirdId
     *            第三方编号
     * @param goodsId
     *            商品id
     * @param pageNo
     *            页数(默认1)
     * @param pageSize
     *            每页条数（默认15条）
     * @param sign
     *            签名验证 userName+timestamp+token+key通过Md5进行加密
     * @param timestamp
     *            时间戳 格式：YYYYMMddHHmm 与系统时间不能相差6分钟
     * @param token
     *            请求token
     * @return 货品列表 失败返回签名错误
     */
    @RequestMapping(value = "list", produces = "application/json; charset=utf-8")
    @ResponseBody
    public String list(String thirdId, Long goodsId, Integer pageNo, Integer pageSize, String sign, String timestamp, String userName, String token) {
        // 验证安全
        String msg = OpenUtil.isCheck(timestamp, userName, sign, token, "获取货品列表");
        // 判断是否通过
        if (!"1".equals(msg)) {
            return msg;
        }
        return goodsProductService.list(thirdId, goodsId, pageNo, pageSize);
    }

    /**
     * 根据货品编号查询货品信息 调用方式 goods/get.htm
     * 
     * @param sign
     *            签名验证 userName+timestamp+token+key通过Md5进行加密
     * @param timestamp
     *            时间戳 格式：YYYYMMddHHmm 与系统时间不能相差6分钟
     * @param token
     *            请求token
     * @param goodsInfoItemNo
     *            货品编号
     * @param userName
     *            用户名
     * @return 货品信息 失败返回签名错误
     */
    @RequestMapping(value = "get", produces = "application/json; charset=utf-8")
    @ResponseBody
    public String get(String goodsInfoItemNo, String sign, String timestamp, String userName, String token) {
        // 验证安全
        String msg = OpenUtil.isCheck(timestamp, userName, sign, token, "查询编号为" + goodsInfoItemNo + "商品信息");
        // 判断是否通过
        if (!"1".equals(msg)) {
            return msg;
        }
        return goodsProductService.get(goodsInfoItemNo);
    }

}
