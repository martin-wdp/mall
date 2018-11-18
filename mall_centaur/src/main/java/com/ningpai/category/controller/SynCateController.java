package com.ningpai.category.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ningpai.category.bean.ReturnObject;
import com.ningpai.category.services.SynCateServices;

/**
 * 同步商品分类 从E店宝
 * 
 * @author ggn
 * 
 */
@Controller
public class SynCateController {

    @Resource(name = "SynCateServices")
    private SynCateServices synCateServices;

    /**
     * 同步商品分类数据
     * 
     * @param request
     * @return int
     */
    @RequestMapping("sysgoodscategory")
    @ResponseBody
    public ReturnObject SynchronousGoodsCateGory(HttpServletRequest request) {
        // 执行同步商品分类操作
        return synCateServices.SynchronousGoodsCateGory();
    }
}
