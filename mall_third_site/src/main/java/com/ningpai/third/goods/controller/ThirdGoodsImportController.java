/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.third.goods.controller;

import com.ningpai.excel.ImportGoods;
import com.ningpai.goods.service.GoodsBrandService;
import com.ningpai.goods.service.GoodsImportService;
import com.ningpai.goods.service.GoodsTagService;
import com.ningpai.goods.util.SelectBean;
import com.ningpai.system.service.ServiceSupportMapperService;
import com.ningpai.third.util.MenuOperationUtil;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 商品导入临时数据表控制器
 */
@Controller
public class ThirdGoodsImportController {

    private static final String REDIRECT = "thirdImport.html";

    /**
     * 日志
     * */
    public static final MyLogger LOGGER = new MyLogger(ThirdGoodsImportController.class);

    /**
     * 商品导入Service
     */
    private GoodsImportService goodsImportService;
    /**
     * 导入商品
     */
    private ImportGoods importGoods;
    /**
     * 商品品牌Service
     */
    private GoodsBrandService goodsBrandService;
    /**
     * 商品标签Service
     */
    private GoodsTagService goodsTagService;
    /**
     * 库房Service
     */
    @Resource(name = "serviceSupportMapperService")
    private ServiceSupportMapperService serviceSupportMapperService;

    /**
     * 下载导入商品的模板
     * 
     * @param response
     *            响应对象
     */
    @RequestMapping("/thirdDownImportExcel")
    public void downImportExcel(HttpServletResponse response) {
        this.importGoods.exportProductList(response);
    }

    /**
     * 根据参数查询分页的商品导入列表
     * 
     * @param pageBean
     *            分页Bean {@link com.ningpai.util.PageBean}
     * @param selectBean
     *            查询参数Bean {@link com.ningpai.goods.util.SelectBean}
     */
    @RequestMapping("/thirdImport")
    public ModelAndView queryAllImport(PageBean pageBean, SelectBean selectBean, HttpServletRequest request, String n, String l) {
        if (null != n && null != l) {
            MenuOperationUtil.fillSessionMenuIndex(request, n, l);
        }
        /**
         * 设置pageBean的URL
         */
        pageBean.setUrl("thirdImport.htm");
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("pageBean", this.goodsImportService.selectAllGoodsImport(pageBean, selectBean, (Long) request.getSession().getAttribute("thirdId")));
            /**
             * 装载 分页显示的bean
             */
            map.put("selectBean", selectBean);
            return new ModelAndView("goods/thirdgoodsimport", "map", map);
        } finally {
            map = null;
        }
    }

    /**
     * 去发布商品
     * 
     * @param id
     *            主键ID {@link Long}
     */
    @RequestMapping("/thirdToPubImportGoods")
    public ModelAndView toPubGoods(Long id) {
        ModelAndView mav = new ModelAndView("goods/pubimportgoods");
        try {
            /**
             * 查询所有的商品品牌
             */
            mav.addObject("brandList", this.goodsBrandService.queryAllBrand());
            /**
             * 查询所有的商品标签
             */
            mav.addObject("tagList", this.goodsTagService.queryAllTag());
            /**
             * 查询所有支持
             */
            mav.addObject("support", serviceSupportMapperService.selectAll());
            /**
             * 根据主键查询导入Bean
             */
            mav.addObject("importGoods", this.goodsImportService.selectByPrimaryKey(id));
            return mav;
        } finally {
            mav = null;
        }
    }

    /**
     * 批量删除商品导入数据
     * 
     * @param importCheck
     *            商品id数组 {@link Long}
     */
    @RequestMapping("/thirdBatchDelImport")
    public ModelAndView batchDelImport(Long[] importCheck, HttpServletRequest request) {
        /**
         * 批量删除商品导入数据
         */
        this.goodsImportService.batchDelGoodsImport(importCheck);
        return new ModelAndView(new RedirectView(REDIRECT));
    }

    /**
     * 更新导入商品为已发布状态
     * 
     * @param id
     *            主键ID {@link Long}
     * @return 成功true 失败false
     */
    @RequestMapping("/thirdUpdateImportAdded")
    @ResponseBody
    public boolean updateImportAdded(Long id) {
        return this.goodsImportService.updateGoodsImportAdded(id) > 0 ? true : false;
    }

    /**
     * 导入商品
     * 
     * @param request
     *            请求
     * @param response
     *            响应对象
     * @param goodsCateId
     *            导入的分类ID
     */
    @RequestMapping("/thirdImportGoods")
    public ModelAndView importGoods(HttpServletRequest request, HttpServletResponse response, Long goodsCateId, MultipartHttpServletRequest request2) {
        try {
            /**
             * 装载商家ID
             */
            request.setAttribute("imThirdId", (Long) request.getSession().getAttribute("thirdId"));
            /**
             * 装载商家名称
             */
            request.setAttribute("imThirdName", (String) request.getSession().getAttribute("storeName"));
            /**
             * 导入商品列表
             */
            this.goodsImportService.importGoodsByExcel(request, response, request2);
        } catch (Exception e) {
            LOGGER.error(""+e);
            return new ModelAndView(new RedirectView(REDIRECT));
        }
        return new ModelAndView(new RedirectView(REDIRECT));
    }

    /**
     * 商品导入临时数据表Service接口 get方法
     * 
     * @return
     */
    public GoodsImportService getGoodsImportService() {
        return goodsImportService;
    }

    /**
     * 商品导入临时数据表Service接口 set方法
     * 
     * @param goodsImportService
     */
    @Resource(name = "GoodsImportService")
    public void setGoodsImportService(GoodsImportService goodsImportService) {
        this.goodsImportService = goodsImportService;
    }

    /**
     * 商品品牌service层接口 SET方法
     * 
     * @param importGoods
     */
    @Resource(name = "ImportGoods")
    public void setImportGoods(ImportGoods importGoods) {
        this.importGoods = importGoods;
    }

    /**
     * 商品品牌service层接口GET方法
     * 
     * @return
     */
    public GoodsBrandService getGoodsBrandService() {
        return goodsBrandService;
    }

    /**
     * 商品标签业务层接口SET方法
     * 
     * @param goodsBrandService
     */
    @Resource(name = "GoodsBrandService")
    public void setGoodsBrandService(GoodsBrandService goodsBrandService) {
        this.goodsBrandService = goodsBrandService;
    }

    /**
     * 商品标签业务层接口GET方法
     * 
     * @return
     */
    public GoodsTagService getGoodsTagService() {
        return goodsTagService;
    }

    /**
     * 商品标签业务层接口SET方法
     * 
     * @param goodsTagService
     */
    @Resource(name = "GoodsTagServiceImpl")
    public void setGoodsTagService(GoodsTagService goodsTagService) {
        this.goodsTagService = goodsTagService;
    }
}
