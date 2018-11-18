/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.site.util;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ningpai.system.bean.SysErrorPage;
import com.ningpai.system.service.ISysErrorPageBiz;

/**
 * @author NINGPAI-WangHaiYang
 * @since 2014年7月24日下午7:57:11
 */
@Controller
public class ExceptionController {
    @Resource(name = "sysErrorPageBizImpl")
    private ISysErrorPageBiz sysErrorPageBizImpl;

    /**
     * 根据ID获取异常页面信息
     * 
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/getErrorPageInfo")
    public SysErrorPage getErrorPageInfo(int id) {
        return sysErrorPageBizImpl.getSysErrorPageById(id);
    }
}
