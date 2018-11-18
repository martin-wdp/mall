/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.third.util;

import com.ningpai.system.service.BasicSetService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;

/**
 * 控制器-第三方店铺首页URL
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年5月26日下午4:36:02
 */
@Controller
public class ThirdStoreURLController {
    @Resource(name = "basicSetService")
    /**
     * 站点设置服务层接口
     */
    private BasicSetService basicSetService;
    /**
     * 显示第三方店铺首页URL
     * 
     * @return
     */
    @RequestMapping("/showThirdStoreURL")
    public ModelAndView showThirdStoreURL() {
        return new ModelAndView("seller/thirdstoreurl", "thirdStoreURL", ThirdStoreURLUtil.getThirdStoreURL());
    }

    /**
     * 修改第三方店铺首页URL
     *            分页行数
     * @return
     */
    @RequestMapping("/updateThirdStoreURL")
    public ModelAndView updateThirdStoreURL(String url) {
        //设置路径
        ThirdStoreURLUtil.setThirdStoreURL(url);
        //跳转到列表页
        return new ModelAndView(new RedirectView("gofuncsetview.htm"));
    }

    /**
     * 获取第三方店铺首页URL
     * 
     * @return
     */
    @ResponseBody
    @RequestMapping("/getThirdStoreURL")
    public String getThirdStoreURL() {
        return basicSetService.findBasicSet().getBsetAddress();
    }
}
