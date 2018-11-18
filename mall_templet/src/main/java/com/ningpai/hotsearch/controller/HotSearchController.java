/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.hotsearch.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.common.util.ConstantUtil;
import com.ningpai.hotsearch.bean.HotSearch;
import com.ningpai.hotsearch.service.HotSearchService;
import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.util.PageBean;

import java.util.HashMap;
import java.util.Map;

/**
 * 热门搜索控制层
 * 
 * @author YANhb
 * 
 */
@Controller
public class HotSearchController {

    /**
     * 用户名称
     */
    public static final String NAME = "name";

    /**
     * "operaPath"
     */
    public static final String OPERAPATH = "operaPath";

    private static final String ERRORVIEW = "jsp/hotsearch/error";

    private static final String LOGGERINFO1 = ",用户名:";
    private static final String QUERYHOTPAGESIZE_TEMPID = "queryHotPageSize.htm?tempId=";
    private static final String QUERYHOTPAGESIZE_CHANNELID = "queryHotPageSize.htm?channelId=";

    /**
     * 注入热门搜索业务层
     */
    @Resource
    private HotSearchService hotSearchService;

    /**
     * 热门搜索分页查询
     * 
     * @param pb
     *            分页组件
     * @param channelId
     *            频道ID
     * @param tempId
     *            模板ID
     * @param keyword
     *            搜索关键字
     * @return
     */
    @RequestMapping(value = "/queryHotPageSize")
    public ModelAndView queryHotPageSize(PageBean pb, Long channelId, Long tempId, String keyword) {
        ModelAndView mv = new ModelAndView("jsp/hotsearch/hot_search");
        mv.addObject("pb", hotSearchService.queryHotBySelectivePageSize(keyword, tempId, channelId, pb));
        mv.addObject("tempId", tempId);
        mv.addObject("channelId", channelId);
        return mv;
    }

    /**
     * 热门搜索分页查询（新）
     *
     * @param pb
     *            分页组件
     * @param channelId
     *            频道ID
     * @param tempId
     *            模板ID
     * @param keyword
     *            搜索关键字
     * @return
     */
    @RequestMapping(value = "/queryhotpagesizeajax")
    @ResponseBody
    public Map<String, Object> queryHotPageSizeAjax(PageBean pb, Long channelId, Long tempId, String keyword) {
        // 设置参数
        Map<String, Object> map = new HashMap<String, Object>();
        // 设置集合
        map.put("pb", hotSearchService.queryHotBySelectivePageSize(keyword, tempId, channelId, pb));
        // 返回结果
        return map;
    }

    /**
     * 添加热门搜索关键字(新)
     *
     * @param hotSearch
     * @param request
     * @return
     */
    @RequestMapping(value = "/createhotselectiveajax")
    @ResponseBody
    public int createHotSelectiveAjax(@Valid HotSearch hotSearch, BindingResult bindingResult, HttpServletRequest request) {
        // 判断参数是否符合规范
        if (bindingResult.hasErrors()) {
            // 返回结果
            return 0;
        }
        // 添加信息
        int count = hotSearchService.addHotSearchSelective(hotSearch);
        // 判断是否插入成功
        if (count > 0) {
            // 获取登录名称
            String customerName = (String) request.getSession().getAttribute(NAME);
            // 记录日志
            OperaLogUtil.addOperaLog(request, customerName, "添加热门搜索", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);

        }
        // 返回结果
        return count;

    }

    /**
     * 添加热门搜索关键字
     * 
     * @param hotSearch
     * @param request
     * @return
     */
    @RequestMapping(value = "/createHotSelective")
    public ModelAndView createHotSelective(@Valid HotSearch hotSearch, BindingResult bindingResult, HttpServletRequest request) {
        // 判断是否符合规范
        if (bindingResult.hasErrors()) {
            // 判断是否是频道页
            if (null == hotSearch.getChannelid()) {
                // 返回到页面
                return new ModelAndView(new RedirectView(QUERYHOTPAGESIZE_TEMPID + hotSearch.getTempid() + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN)));
            } else {
                // 返回到页面
                return new ModelAndView(new RedirectView(QUERYHOTPAGESIZE_CHANNELID + hotSearch.getChannelid() + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN)));
            }
        }
        // 插入数据
        int count = hotSearchService.addHotSearchSelective(hotSearch);
        // 判断是否成功
        if (count > 0) {
            // 获取用户名
            String customerName = (String) request.getSession().getAttribute(NAME);
            // 记录日志
            OperaLogUtil.addOperaLog(request, customerName, "添加热门搜索", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
            if (null == hotSearch.getChannelid()) {
                return new ModelAndView(new RedirectView(QUERYHOTPAGESIZE_TEMPID + hotSearch.getTempid() + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN)));
            } else {
                return new ModelAndView(new RedirectView(QUERYHOTPAGESIZE_CHANNELID + hotSearch.getChannelid() + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN)));
            }
        }
        // 返回结果
        return new ModelAndView(ERRORVIEW);
    }

    /**
     * 修改热门搜索关键字
     * 
     * @param hotSearch
     * @param request
     * @return
     */
    @RequestMapping(value = "/modifyHot")
    public ModelAndView modifyHotSelective(@Valid HotSearch hotSearch, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            if (null == hotSearch.getChannelid()) {
                return new ModelAndView(new RedirectView(QUERYHOTPAGESIZE_TEMPID + hotSearch.getTempid() + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN)));
            } else {
                return new ModelAndView(new RedirectView(QUERYHOTPAGESIZE_CHANNELID + hotSearch.getChannelid() + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN)));
            }
        }
        int count = hotSearchService.modifyHostSearchSelectiveById(hotSearch);
        if (count > 0) {
            String customerName = (String) request.getSession().getAttribute(NAME);
            OperaLogUtil.addOperaLog(request, customerName, "修改热门搜索", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
            if (null == hotSearch.getChannelid()) {
                return new ModelAndView(new RedirectView(QUERYHOTPAGESIZE_TEMPID + hotSearch.getTempid() + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN)));
            } else {
                return new ModelAndView(new RedirectView(QUERYHOTPAGESIZE_CHANNELID + hotSearch.getChannelid() + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN)));
            }
        }
        return new ModelAndView(ERRORVIEW);
    }

    /**
     * 修改热门搜索关键字（新）
     *
     * @param hotSearch
     * @param request
     * @return
     */
    @RequestMapping(value = "/modifyhotajax")
    @ResponseBody
    public int modifyHotSelectiveAjax(@Valid HotSearch hotSearch, BindingResult bindingResult, HttpServletRequest request) {
        // 判断参数是否符合规范
        if (bindingResult.hasErrors()) {
            return 0;
        }
        // 修改参数
        int count = hotSearchService.modifyHostSearchSelectiveById(hotSearch);
        // 判断是否修改成功
        if (count > 0) {
            // 获取登录名
            String customerName = (String) request.getSession().getAttribute(NAME);
            // 记录日志
            OperaLogUtil.addOperaLog(request, customerName, "修改热门搜索", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
        }
        return count;
    }

    /**
     * 根据搜索ID删除
     * 
     * @param hotSearchId
     * @return
     */
    @RequestMapping(value = "/removeHotByKey")
    public ModelAndView removeHotByKey(Long hotSearchId, Long tempId, Long channelId, HttpServletRequest request) {
        int count = hotSearchService.removeHotSearchById(hotSearchId);
        if (count > 0) {
            String customerName = (String) request.getSession().getAttribute(NAME);
            OperaLogUtil.addOperaLog(request, customerName, "删除热门搜索", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
            if (null == channelId) {
                return new ModelAndView(new RedirectView(QUERYHOTPAGESIZE_TEMPID + tempId + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN)));
            } else {
                return new ModelAndView(new RedirectView(QUERYHOTPAGESIZE_CHANNELID + channelId + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN)));
            }
        }
        return new ModelAndView(ERRORVIEW);
    }

    /**
     * 根据搜索ID删除(新)
     *
     * @param hotSearchId
     * @return
     */
    @RequestMapping(value = "/removehotbykeyajax")
    @ResponseBody
    public int removeHotByKeyAjax(Long hotSearchId, HttpServletRequest request) {
        // 删除
        int count = hotSearchService.removeHotSearchById(hotSearchId);
        // 判断结果
        if (count > 0) {
            // 获取用户名
            String customerName = (String) request.getSession().getAttribute(NAME);
            // 记录日志
            OperaLogUtil.addOperaLog(request, customerName, "删除热门搜索", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
        }
        // 返回结果
        return count;
    }

    /**
     * 批量删除热门搜索记录
     *
     * @param hots
     * @param request
     * @return
     */
    @RequestMapping(value = "/batchDelHot")
    public ModelAndView batchRemoveHot(Long[] hots, HttpServletRequest request) {
        int count = hotSearchService.batchDelHotSearch(hots);
        if (count > 0) {
            String customerName = (String) request.getSession().getAttribute(NAME);
            OperaLogUtil.addOperaLog(request, customerName, "批量删除热门搜索", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
            return new ModelAndView(new RedirectView("queryHotPageSize.htm?CSRFToken=" + request.getParameter(ConstantUtil.CSRFTOKEN)));
        }
        return new ModelAndView(ERRORVIEW);
    }

    /**
     * 批量删除热门搜索记录（新）
     *
     * @param hots
     * @param request
     * @return
     */
    @RequestMapping(value = "/batchdelhotajax")
    @ResponseBody
    public int batchRemoveHotAjax(Long[] hots, HttpServletRequest request) {
        // 批量删除
        int count = hotSearchService.batchDelHotSearch(hots);
        // 返回结果
        if (count > 0) {
            // 获取用户名
            String customerName = (String) request.getSession().getAttribute(NAME);
            // 记录日志
            OperaLogUtil.addOperaLog(request, customerName, "批量删除热门搜索", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
        }
        return count;
    }
}
