/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.third.goods.controller;

import java.util.HashMap;
import java.util.Map;
import com.ningpai.third.goods.service.ThirdWarnGoodService;
import com.ningpai.third.goods.util.ThirdGoodsSearchBean;
import com.ningpai.third.goods.util.ThirdPathUtil;
import com.ningpai.third.goods.vo.StockWarningVo;
import com.ningpai.third.util.MenuOperationUtil;
import com.ningpai.util.PageBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 货品预警的控制器
 */
@Controller
public class ThirdWarnGoodController {

    private static final String THIRDID = "thirdId";

    /**
     * 第三方货品预警service接口
     */
    private ThirdWarnGoodService thirdWarnGoodService;

    /**
     * 预警service接口get方法
     * 
     * @return
     */
    public ThirdWarnGoodService getThirdWarnGoodService() {
        return thirdWarnGoodService;
    }

    /**
     * 预警service接口set方法
     * 
     * @return
     */
    @Resource(name = "ThirdWarnGoodService")
    public void setThirdWarnGoodService(ThirdWarnGoodService thirdWarnGoodService) {
        this.thirdWarnGoodService = thirdWarnGoodService;
    }

    /**
     * 货品预警的值
     * 
     * @param request
     * @param n
     *            导航栏（头部）的索引
     * @param l
     *            导航栏（左侧）的索引
     * @return
     */
    @RequestMapping("/selectstock")
    public ModelAndView selectstock(HttpServletRequest request, String n, String l) {
        MenuOperationUtil.fillSessionMenuIndex(request, n, l);
        /**
         * 查询第三方库存预警值
         */
        StockWarningVo stockWarningVo = thirdWarnGoodService.selectstock((Long) request.getSession().getAttribute(THIRDID));
        return new ModelAndView("goods/thirdwarngoods").addObject("warnstock", stockWarningVo);

    }

    /**
     * 修改 货品预警的值
     * 
     * @param stockWarningVo
     *            货品预警对象
     * @param n
     *            导航栏（头部）的索引
     * @param l
     *            导航栏（左侧）的索引
     * @return
     */
    @RequestMapping("/updatestockgoods")
    public ModelAndView updatestockgoods(StockWarningVo stockWarningVo, HttpServletRequest request, String n, String l) {
        /**
         * 商家id
         */
        stockWarningVo.setStoreid((long) request.getSession().getAttribute(THIRDID));
        /**
         * 修改预警值
         */
        this.thirdWarnGoodService.updatestockgoods(stockWarningVo);
        /**
         * 重定向到查询列表
         */
        return new ModelAndView(new RedirectView("selectstock.htm"));

    }

    /**
     * 查询预警货品信息
     * 
     * @param stockWarningVo
     *            库存警告的实体类
     * @param pb
     *            分页对象
     * @return
     */
    @RequestMapping("/selectwarngoods")
    @ResponseBody
    public ModelAndView selectwarngoods(StockWarningVo stockWarningVo, PageBean pb, String n, String l, HttpServletRequest request, ThirdGoodsSearchBean searchBean) {
        MenuOperationUtil.fillSessionMenuIndex(request, n, l);
        pb.setUrl("selectwarngoods.htm");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("searchBean", searchBean);
        map.put("flag", 2);
        /**
         * 设置分页辅助bean的地址为当前控制器的地址
         */
        pb.setUrl(ThirdPathUtil.SELECTWARNGOODS);
        /**
         * 设置商家ID
         */
        stockWarningVo.setThirdid((long) request.getSession().getAttribute(THIRDID));
        /**
         * 设置跳转的页面
         */
        return new ModelAndView("goods/thirdwarning")
        /**
         * 返回到页面的分页对象
         */
        .addObject("pb", thirdWarnGoodService.selectwarngoods(stockWarningVo, pb))
        /**
         * 货品预警对象
         */
        .addObject("stockWarningVo", stockWarningVo).addObject("map", map);
    }

    /**
     * 更新仓库库存
     * 
     * @param stockWarningVo
     *            货品预警对象
     * @param request
     * @param n
     *            导航栏（头部）的索引
     * @param l
     *            导航栏（左侧）的索引
     * @return
     */
    @RequestMapping("/updatewarnstock")
    public ModelAndView updatewarnstock(StockWarningVo stockWarningVo, HttpServletRequest request, String n, String l) {
        stockWarningVo.setThirdid((Long) request.getSession().getAttribute(THIRDID));
        /**
         * 更新货品预警信息
         */
        this.thirdWarnGoodService.updatewarnstock(stockWarningVo);
        return new ModelAndView(new RedirectView("selectwarngoods.htm"));

    }

}
