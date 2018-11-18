/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.system.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.system.bean.CurrencyBase;
import com.ningpai.system.bean.CurrencyInfo;
import com.ningpai.system.service.CurrencyInfoService;
import com.ningpai.system.vo.CurrencyInfoVo;
import com.ningpai.util.PageBean;

/**
 * 货币信息控制层
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年02月26日 17:37:38
 * @version
 */
@Controller
public class CurrencyInfoController {
    /** 货币信息注入 */
    private CurrencyInfoService currencyInfoService;

    public CurrencyInfoService getCurrencyInfoService() {
        return currencyInfoService;
    }

    @Resource(name = "CurrencyInfoService")
    public void setCurrencyInfoService(CurrencyInfoService currencyInfoService) {
        this.currencyInfoService = currencyInfoService;
    }

    /**
     * 查询货币信息分页数据
     */
    @RequestMapping(value = "/queryCurrencyInfoByPageBean")
    public ModelAndView queryCurrencyInfoByPageBean(PageBean pb) {
        return new ModelAndView("jsp/system/currencyInfoList", "pb", currencyInfoService.queryCurrencyInfoByPageBean(pb));
    }

    /**
     * 跳转到显示货币信息<br/>
     * 货币信息编号不为空时查询货币信息并绑定到页面，修改货币信息<br/>
     * 货币信息编号为空时，添加货币信息
     * 
     * @return ModelAndView
     */
    @RequestMapping("/showCurrencyInfo")
    public ModelAndView showCurrencyInfo(Long currencyInfoId) {
        // 初始化货币信息
        CurrencyInfoVo currencyInfoVo = new CurrencyInfoVo();
        if (null != currencyInfoId) {
            // 获取单个货币信息
            currencyInfoVo = currencyInfoService.getCurrencyInfo(currencyInfoId);
        }
        // 货币基础信息list
        List<CurrencyBase> currencyBaseList = currencyInfoService.findAllCurrencyBase();
        return new ModelAndView("jsp/system/showCurrencyInfo", "currencyInfoVo", currencyInfoVo).addObject("currencyBaseList", currencyBaseList);
    }

    /**
     * 添加货币信息
     * 
     * @return ModelAndView
     */
    @RequestMapping("/addCurrencyInfo")
    public ModelAndView addCurrencyInfo(CurrencyInfo currencyInfo) {
        // 删除标记
        currencyInfo.setDelflag("0");
        // 创建人
        currencyInfo.setCreateUserId(1L);
        // 创建日期
        currencyInfo.setCreateDate(new Date());
        // 保存货币信息
        currencyInfoService.saveCurrencyInfo(currencyInfo);
        return new ModelAndView(new RedirectView("queryCurrencyInfoByPageBean.htm"));
    }

    /**
     * 修改货币信息
     * 
     * @return ModelAndView
     */
    @RequestMapping("/updateCurrencyInfo")
    public ModelAndView updateCurrencyInfo(CurrencyInfo currencyInfo) {
        // 修改人
        currencyInfo.setUpdateUserId(1L);
        // 修改时间
        currencyInfo.setUpdateDate(new Date());
        // 修改货币信息
        currencyInfoService.updateCurrencyInfo(currencyInfo);
        return new ModelAndView(new RedirectView("queryCurrencyInfoByPageBean.htm"));
    }

    /**
     * 删除货币信息
     * 
     * @return ModelAndView
     */
    @RequestMapping("/delCurrencyInfo")
    public void delCurrencyInfo(HttpServletRequest request, HttpServletResponse response) {
        // 获取货币信息id
        String[] ids = request.getParameterValues("currencyInfoIds[]");
        // 批量删除货币信息
        for (int i = 0; i < ids.length; i++) {
            Long id = Long.valueOf(ids[i]);
            currencyInfoService.deleteCurrencyInfo(id);
        }
    }

    /**
     * 根据编号获取货币基础信息
     * 
     * @param currencyBaseId
     */
    @RequestMapping("/queryCurrencyBaseById")
    @ResponseBody
    public CurrencyBase queryCurrencyBaseById(Long currencyBaseId) {
        return currencyInfoService.getCurrencyBaseById(currencyBaseId);
    }

}
