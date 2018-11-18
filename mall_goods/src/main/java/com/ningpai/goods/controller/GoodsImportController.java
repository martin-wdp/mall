/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.controller;

import com.ningpai.common.safe.CSRFTokenManager;
import com.ningpai.excel.ImportGoods;
import com.ningpai.goods.service.GoodsBrandService;
import com.ningpai.goods.service.GoodsImportService;
import com.ningpai.goods.service.GoodsTagService;
import com.ningpai.goods.service.WareHouseService;
import com.ningpai.goods.util.SelectBean;
import com.ningpai.goods.util.ValueUtil;
import com.ningpai.system.service.ServiceSupportMapperService;
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
 * 商品导入控制器
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年10月20日 下午1:57:39
 * @version 1.0
 */
@Controller
public class GoodsImportController {

    /**
     * 日志
     * */
    public static final MyLogger LOGGER = new MyLogger(GoodsImportController.class);

    private static final String QUERYALLIMPORT_HTM = "queryAllImport.htm";
    // 商品导入Service
    private GoodsImportService goodsImportService;
    // 导入商品
    private ImportGoods importGoods;
    // 商品品牌Service
    private GoodsBrandService goodsBrandService;
    // 商品标签Service
    private GoodsTagService goodsTagService;
    // 库房Service
    private WareHouseService wareHouseService;
    @Resource(name = "serviceSupportMapperService")
    private ServiceSupportMapperService serviceSupportMapperService;

    /**
     * 下载导入商品的模板
     * 
     * @param response
     *            响应对象
     */
    @RequestMapping("/downImportExcel")
    public void downImportExcel(HttpServletResponse response) {
        this.importGoods.exportProductList(response);
    }

    /**
     * 根据参数查询分页的商品导入列表
     * 
     * @param pageBean
     *            分页Bean {@link PageBean}
     * @param selectBean
     *            查询参数Bean {@link SelectBean}
     */
    @RequestMapping("/queryAllImport")
    public ModelAndView queryAllImport(PageBean pageBean, SelectBean selectBean, HttpServletRequest request,String flag) {
        // 设置pageBean的URL
        pageBean.setUrl(QUERYALLIMPORT_HTM + ValueUtil.TOKENPARAM + CSRFTokenManager.getTokenFromRequest(request));
        // 定义一个HashMap集合
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // 查询所有的导入数据,传递null表示查询所有
            map.put("pageBean", this.goodsImportService.selectAllGoodsImport(pageBean, selectBean, null));
            // 把selectBean放进map集合中
            map.put("selectBean", selectBean);
            //标记
            map.put("flag", flag);
            // 返回结果
            return new ModelAndView("jsp/goods/goods_import", "map", map);
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
    @RequestMapping("/toPubImportGoods")
    public ModelAndView toPubGoods(HttpServletRequest request, Long id, String type) {
        ModelAndView mav = null;
        // 判断传入的参数是否为Null并且是否等于1
        if (type != null && "1".equals(type)) {
            request.getSession().setAttribute("importGoodsId", id);
            mav = new ModelAndView("jsp/goods/newupload");
        } else {
            mav = new ModelAndView("jsp/goods/import_upload");
        }
        try {
            // 把查询出来的所有品牌信息放进map集合中
            mav.addObject("brandList", this.goodsBrandService.queryAllBrand());
            // 把查询出来的所有商品标签放进map集合中
            mav.addObject("tagList", this.goodsTagService.queryAllTag());
            // 把查询出来的所有仓库记录放进map集合中
            mav.addObject("wareHouse", this.wareHouseService.queryAllWareHouse());
            // 把查询结果放进map集合中
            mav.addObject("support", serviceSupportMapperService.selectAll());
            // 把查询出来的导入bean放进map集合中
            mav.addObject("importGoods", this.goodsImportService.selectByPrimaryKey(id));
            // 返回结果
            return mav;
        } finally {
            mav = null;
        }
    }

    /**
     * 批量删除导入商品
     * 
     * @param importCheck
     *            商品id数组 {@link Long}
     */
    @RequestMapping("/batchDelImport")
    public ModelAndView batchDelImport(Long[] importCheck, HttpServletRequest request) {
        // 执行批量删除导入商品的方法
        this.goodsImportService.batchDelGoodsImport(importCheck);
        // 返回结果
        return new ModelAndView(new RedirectView(QUERYALLIMPORT_HTM + ValueUtil.TOKENPARAM + CSRFTokenManager.getTokenFromRequest(request)));
    }

    /**
     * 更新导入商品为已发布状态
     * 
     * @param id
     *            主键ID {@link Long}
     * @return 成功true 失败false
     */
    @RequestMapping("/updateImportAdded")
    @ResponseBody
    public boolean updateImportAdded(Long id) {
        // 返回结果
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
    @RequestMapping("/importGoods")
    public ModelAndView importGoods(HttpServletRequest request, HttpServletResponse response, Long goodsCateId, MultipartHttpServletRequest request2) {
        int num=0;
        try {
            request.setAttribute("imThirdId", 0L);
            request.setAttribute("imThirdName", "BOSS");
            // 执行商品导入方法
            if(!this.goodsImportService.importGoodsByExcel(request, response, request2)){
                num=1;
            }
        } catch (Exception e) {
            LOGGER.error(""+e);
            // 返回错误后的结果
            return new ModelAndView(new RedirectView(QUERYALLIMPORT_HTM + ValueUtil.TOKENPARAM + CSRFTokenManager.getTokenFromRequest(request)+"&flag="+num));
        }
        // 返回结果
        return new ModelAndView(new RedirectView(QUERYALLIMPORT_HTM + ValueUtil.TOKENPARAM + CSRFTokenManager.getTokenFromRequest(request)+"&flag="+num));
    }

    public GoodsImportService getGoodsImportService() {
        return goodsImportService;
    }

    @Resource(name = "GoodsImportService")
    public void setGoodsImportService(GoodsImportService goodsImportService) {
        this.goodsImportService = goodsImportService;
    }

    @Resource(name = "ImportGoods")
    public void setImportGoods(ImportGoods importGoods) {
        this.importGoods = importGoods;
    }

    public GoodsBrandService getGoodsBrandService() {
        return goodsBrandService;
    }

    @Resource(name = "GoodsBrandService")
    public void setGoodsBrandService(GoodsBrandService goodsBrandService) {
        this.goodsBrandService = goodsBrandService;
    }

    public GoodsTagService getGoodsTagService() {
        return goodsTagService;
    }

    @Resource(name = "GoodsTagServiceImpl")
    public void setGoodsTagService(GoodsTagService goodsTagService) {
        this.goodsTagService = goodsTagService;
    }

    @Resource(name = "WareHouseService")
    public void setWareHouseService(WareHouseService wareHouseService) {
        this.wareHouseService = wareHouseService;
    }
}
