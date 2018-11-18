/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.fastdfs.controller;

import com.ningpai.fastdfs.bean.FastDFSInfo;
import com.ningpai.fastdfs.service.FastDFSInfoService;
import com.ningpai.logcore.util.OperaLogUtil;
import com.ningpai.util.MyLogger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * 控制器-FastDFS路径
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年6月6日下午6:19:52
 */
@Controller
public class FastDFSInfoController {

    /** Spring注入 */
    @Resource(name = "FastDFSInfoService")
    private FastDFSInfoService fastDFSService;

    /**
     * 用户名称
     */
    public static final String NAME = "name";

    /**
     * "operaPath"
     */
    public static final String OPERAPATH = "operaPath";

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(FastDFSInfoController.class);

    /**
     * 查询当前FastDFS信息
     * 
     * @return FastDFS信息
     */
    @ResponseBody
    @RequestMapping("/getFastDFSPath")
    public FastDFSInfo getFastDFSPath() {
        // 查询FastDFSl路径
        return fastDFSService.getFastDFSInfoByCurr();
    }

    /**
     * 修改FastDFS路径
     * 
     * @param fastDFSInfo
     *            FastDFSl路径
     * @return FastDFS信息页面
     */
    @ResponseBody
    @RequestMapping("/updateFastDFSPathAjax")
    public Map<String, Object> updateFastDFSPathAjax(@Valid FastDFSInfo fastDFSInfo, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            throw new RuntimeException("FastDFS字段输入错误！");
        }
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // 修改FastDfs
            int n = fastDFSService.updateFastDFSInfo(fastDFSInfo);
            if (n > 0) {
                // 插入日志
                String customerName = (String) request.getSession().getAttribute(NAME);
                OperaLogUtil.addOperaLog(request, customerName, "修改短信接口设置", (String) request.getSession().getAttribute(OPERAPATH));
                // 设置MAP
                map.put("flag", n > 0 ? true : false);
                map.put("fastDFSPath", fastDFSInfo.getServerPath());
                map.put("fastDFSPort", fastDFSInfo.getHttpPort());
                map.put("mysqlpath", fastDFSInfo.getResultPath());
                map.put("open", fastDFSInfo.getUserd());
            } else {
                LOGGER.info("FastDFS修改失败");
            }

        } catch (Exception e) {
            map = null;
            // 插入日志
            LOGGER.info("FastDFS修改错误：=>" + e.getLocalizedMessage());
            String customerName = (String) request.getSession().getAttribute(NAME);
            OperaLogUtil.addOperaException(customerName, e, request);
            throw new RuntimeException("FastDFS修改错误", e);
        }
        // 返回Map
        return map;
    }
}
