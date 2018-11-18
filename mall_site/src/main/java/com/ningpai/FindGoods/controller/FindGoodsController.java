package com.ningpai.FindGoods.controller;

import com.alibaba.fastjson.JSON;
import com.ningpai.FindGoods.bean.FindGoodsBean;
import com.ningpai.FindGoods.service.FindGoodsService;
import org.junit.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by pl on 2015/9/15.
 * Desc:
 */
@Controller
public class FindGoodsController {
    public FindGoodsService findGoodsService;

    @Resource(name = "findGoodsService")
    public void setFindGoodsService(FindGoodsService findGoodsService) {
        this.findGoodsService = findGoodsService;
    }

    public FindGoodsService getFindGoodsService() {
        return findGoodsService;
    }
    @RequestMapping("/tested")
    @ResponseBody
    public String Test()
    {
        List<FindGoodsBean> list=findGoodsService.findGoodsList();
        String str= JSON.toJSONString(list);
        return "/jsp/index.html";
       // System.out.println("hah");
    }
}
