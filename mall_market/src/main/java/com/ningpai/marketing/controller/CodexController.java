/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.marketing.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ningpai.marketing.bean.Codex;
import com.ningpai.marketing.common.MarketingPath;
import com.ningpai.marketing.service.CodexService;
import com.ningpai.util.PageBean;

/**
 * @author ggn 2014-03-21 促销规则控制器
 */
@Controller
public class CodexController {

    // Spring注入
    @Resource(name = "CodexService")
    private CodexService codexService;

    /**
     * 查询规则列表
     * 
     * @param pageBean
     * @param codex
     * @return ModelAndView
     */
    @RequestMapping("/searchcodexlist")
    public ModelAndView selectCodexList(PageBean pageBean, Codex codex) {
        // 设置跳转URL
        pageBean.setUrl(MarketingPath.INIT_CODEX_LIST);
        // 查询规则列表
        return new ModelAndView(MarketingPath.CODEX_LIST, "pageBean", codexService.selectCodexList(codex, pageBean));
    }

    /**
     * 插入规则信息
     * 
     * @param codex
     * @return xx2ks
     */
    @RequestMapping("/addcodexs")
    public ModelAndView addCodex(Codex codex) {
        // 添加规则
        codexService.addCodex(codex);
        return new ModelAndView();
    }

    /**
     * 查询规则详细
     * 
     * @param codexId
     * @return Codex
     */
    @RequestMapping("/queryCodexDetail")
    @ResponseBody
    public Codex queryCodexDetail(Long codexId) {
        // 查询规则明细
        return codexService.queryCodexDetail(codexId);
    }

}
