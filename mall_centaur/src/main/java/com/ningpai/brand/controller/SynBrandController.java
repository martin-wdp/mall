package com.ningpai.brand.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ningpai.brand.service.SynBrandService;
import com.ningpai.category.bean.ReturnObject;

/**
 * 同步e店宝品牌
 * 
 * @author ggn
 * 
 */
@Controller
public class SynBrandController {

    @Resource(name = "SynBrandService")
    private SynBrandService synBrandService;

    /**
     * 同步商品品牌数据
     * 
     * @param request
     * @return ReturnObject
     */
    @RequestMapping("sysgoodsbrand")
    @ResponseBody
    public ReturnObject SynchronousGoodsBrand(HttpServletRequest request) {
        // 执行同步商品品牌的操作
        return synBrandService.SynchronousGoodsBrand();
    }

}
