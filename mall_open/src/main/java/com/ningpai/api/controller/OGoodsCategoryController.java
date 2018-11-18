package com.ningpai.api.controller;

import com.ningpai.api.service.IGoodsCategoryService;
import com.ningpai.api.util.OpenUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 商品品牌
 * 
 * @author lih
 * @version 2.0
 * @since 15/9/6
 */
@Controller
@RequestMapping("goodscategory")
public class OGoodsCategoryController {

    /**
     * 商品分类service
     */
    @Resource(name = "openGoodsCategoryService")
    private IGoodsCategoryService goodsCategoryService;

    /**
     * 获取商品分类列表 调用方式 goodscategory/list.htm
     *
     * @param sign
     *            获取签名 "goodscategory.list"+key 通过MD5进行加密
     * @param pageNo
     *            页数
     * @param pageSize
     *            每页大小
     * @return 商品分类列表 签名错误 失败返回签名错误
     */
    @RequestMapping(value = "list", produces = "application/json; charset=utf-8")
    @ResponseBody
    public String list(String sign, Integer pageNo, Integer pageSize, String timestamp, String userName, String token) {
        // 验证安全
        String msg = OpenUtil.isCheck(timestamp, userName, sign, token, "获取商品分类列表");
        // 判断是否通过
        if (!"1".equals(msg)) {
            return msg;
        }
        return goodsCategoryService.list(pageNo, pageSize);
    }

}
