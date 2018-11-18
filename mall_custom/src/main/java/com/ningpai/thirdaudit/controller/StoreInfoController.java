/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.thirdaudit.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.ningpai.customer.service.CustomerServiceMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ningpai.comment.bean.ValueUtil;
import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.other.util.CustomerConstantStr;
import com.ningpai.thirdaudit.service.StoreInfoService;
/**
 * 商家信息控制器
 *
 * */
@Controller
public class StoreInfoController {

    @Resource(name = "StoreService")
    private StoreInfoService storeInfoService;
    @Resource(name = "customerServiceMapper")
    private CustomerServiceMapper customerServiceMapper;

    /*
     * @RequestMapping("/queryAllStoreInfo") public ModelAndView
     * queryAllStoreInfo(PageBean pageBean,StoreInfo storeInfo){
     * pageBean.setObjectBean(storeInfo); Map<String, Object> map = new
     * HashMap<String, Object>(); storeInfoService.queryAllStoreInfo(pageBean);
     * map.put("pb", pageBean); return new
     * ModelAndView("jsp/decoratemall/choose_decorate").addObject("map",map); }
     */

    /**
     * 删除
     * 
     * @param request
     * @return
     */
    @RequestMapping("/delectstore")
    @ResponseBody
    public int delectStore(HttpServletRequest request) {
        String[] ids = null;
        // 获取店铺Id封装成Sting数组
        ids = request.getParameterValues("storeIds[]");
        customerServiceMapper.deleteStore(ids);
        // 获取session里面的用户
        String username = request.getSession().getAttribute("name").toString();
        return storeInfoService.delStoreInfoById(ids, username);
    }

    /**
     * 新批量删除店铺
     * 
     * @param request
     * @param customerId
     *            用户ID
     * @return
     */
    @RequestMapping("/delnewallstore")
    @ResponseBody
    public int deleteNewAllStore(HttpServletRequest request, Long customerId) {
        // 获取店铺Id封装成Sting数组
        String[] storeIds = request.getParameterValues("storeId");
        // 获取session里面的用户
        String username = request.getSession()
                .getAttribute(CustomerConstantStr.NAME).toString();
        // 操作日志
        OperaLogUtil.addOperaLog(request, (String) request.getSession()
                .getAttribute(ValueUtil.NAME), "批量删除店铺", (String) request
                .getSession().getAttribute(ValueUtil.OPERAPATH)
                + "批量删除店铺,用户名："
                + (String) request.getSession().getAttribute(ValueUtil.NAME));
        // 执行删除
        return storeInfoService.delStoreInfoById(storeIds, username);

    }
}
