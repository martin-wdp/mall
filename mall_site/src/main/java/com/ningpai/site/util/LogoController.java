/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.site.util;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ningpai.system.service.BasicSetService;

/**
 * 获取Logo
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年5月8日 下午5:01:41
 * @version
 */
@Controller
public class LogoController {

    private BasicSetService basicSetService;

    /**
     * 获取Logo
     * 
     * @return
     */
    @RequestMapping("/loadlogo")
    @ResponseBody
    public Map<String, Object> loadLogo() {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("logo", basicSetService.findBasicSet());
        return result;
    }

    public BasicSetService getBasicSetService() {
        return basicSetService;
    }

    @Resource(name = "basicSetService")
    public void setBasicSetService(BasicSetService basicSetService) {
        this.basicSetService = basicSetService;
    }

}
