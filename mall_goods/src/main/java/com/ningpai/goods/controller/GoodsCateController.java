/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.controller;

import com.ningpai.common.safe.CSRFTokenManager;
import com.ningpai.goods.bean.GoodsCate;
import com.ningpai.goods.service.GoodsCateService;
import com.ningpai.goods.service.GoodsTypeService;
import com.ningpai.goods.util.PathUtil;
import com.ningpai.goods.util.SelectBean;
import com.ningpai.goods.util.ValueUtil;
import com.ningpai.goods.vo.GoodsCateVo;
import com.ningpai.logger.util.OperaLogUtil;
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
import javax.validation.Valid;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * 商品分类控制器
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月17日 下午5:41:34
 * @version 1.0
 */
@Controller
public class GoodsCateController {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(GoodsCateController.class);

    private static final String LOGGERINFO1 = "-->分类名称【";
    private static final String LOGGERINFO2 = "】,用户名：";
    private static final String LOGGERINFO3 = ",分类名称为：";

    private GoodsCateService goodsCateService;
    // 商品类型Service
    private GoodsTypeService goodsTypeService;

    public GoodsTypeService getGoodsTypeService() {
        return goodsTypeService;
    }

    @Resource(name = "GoodsTypeService")
    public void setGoodsTypeService(GoodsTypeService goodsTypeService) {
        this.goodsTypeService = goodsTypeService;
    }

    public GoodsCateService getGoodsCateService() {
        return goodsCateService;
    }

    @Resource(name = "GoodsCateService")
    public void setGoodsCateService(GoodsCateService goodsCateService) {
        this.goodsCateService = goodsCateService;
    }

    /**
     * 根据pageBean分页查询所有的商品分类数据
     * 
     * @param pb
     *            分页对象
     * @return {@link org.springframework.web.servlet.ModelAndView;}
     */
    @RequestMapping("/findAllCate")
    public ModelAndView findAllGoodsCate(PageBean pb) {
        LOGGER.info(ValueUtil.FINDALLCATEINFO);
        // 分页查询并进行子分类的递归排序
        List<Object> typelist=this.goodsTypeService.queryAllType();
        return new ModelAndView(PathUtil.GOODSCATE, "cateList", this.goodsCateService.queryAllCate()).addObject("pb", pb).addObject("typeList",
                typelist);
    }

    /**
     * 查询所有商品分类
     * 
     * @return
     */
    @RequestMapping("/queryallgoodcate")
    @ResponseBody
    public List<GoodsCate> queryAllGoodCate() {
        // 打印日志
        LOGGER.info(ValueUtil.QUERYALLGOODCATEINFO);
        // 执行方法并返回结果
        return goodsCateService.queryAllGoodCate();
    }

    /**
     * 删除商品分类(单个)
     * 
     * @param cateId
     *            商品分类ID {@link java.lang.Long}
     * @return {@link org.springframework.web.servlet.ModelAndView;}
     */
    @RequestMapping("/delGoodsCate")
    public ModelAndView delGoodsCate(Long cateId, HttpServletRequest request) {
        // 删除商品分类，删除人名称应该是当前登录用户名称
        this.goodsCateService.delGoodsCate(cateId, (String) request.getSession().getAttribute(ValueUtil.NAME));
        // 查询单个的商品分类信息
        GoodsCateVo goodsCateVo = this.goodsCateService.queryGoodsCateById(cateId);
        // 非空验证 分类名称
        if (null != goodsCateVo.getCatName()) {
            // 操作日志
            OperaLogUtil.addOperaLog(request, (String) request.getSession().getAttribute(ValueUtil.NAME), "删除商品分类", (String) request.getSession().getAttribute(ValueUtil.OPERAPATH)
                    + LOGGERINFO1 + goodsCateVo.getCatName() + LOGGERINFO2 + (String) request.getSession().getAttribute(ValueUtil.NAME));
            LOGGER.info(ValueUtil.DELGOODSCATEINFO + ",分类名称为:" + goodsCateVo.getCatName());
        }
        return new ModelAndView(new RedirectView(PathUtil.ALLCATECONTROLLER + ValueUtil.TOKENPARAM + CSRFTokenManager.getTokenFromRequest(request)));
    }

    /**
     * 批量删除商品分类
     * 
     * @param cateIds
     *            {@link java.lang.Long}
     * @return {@link org.springframework.web.servlet.ModelAndView;}
     */
    @RequestMapping("/batchDelCate")
    public ModelAndView batchDelCate(Long[] cateIds, HttpServletRequest request) {
        // 批量删除商品类型，删除人名称应该为当前登录的用户名称，暂且使用admin
        this.goodsCateService.batchDelGoodsCate(cateIds, (String) request.getSession().getAttribute(ValueUtil.NAME));
        // 打印日志
        LOGGER.info(ValueUtil.BATCHDELCATEINFO);
        // 记录操作日志
        OperaLogUtil.addOperaLog(request, (String) request.getSession().getAttribute(ValueUtil.NAME), ValueUtil.BATCHDELCATEINFO,
                (String) request.getSession().getAttribute(ValueUtil.OPERAPATH) + ",用户名：" + (String) request.getSession().getAttribute(ValueUtil.NAME));
        // 返回视图
        return new ModelAndView(new RedirectView(PathUtil.ALLCATECONTROLLER + ValueUtil.TOKENPARAM + CSRFTokenManager.getTokenFromRequest(request)));
    }

    /**
     * 添加商品分类
     * 
     * @param goodsCate
     *            待添加的商品分类实体 {@link com.ningpai.goods.bean.GoodsCate}
     * @return @return {@link org.springframework.web.servlet.ModelAndView;}
     */
    @RequestMapping("/addGoodsCate")
    public ModelAndView addGoodsCate(@Valid GoodsCate goodsCate, HttpServletRequest request) {
        // 添加商品类型，设置创建人名称为当前登录用户的名称，暂且设置为admin2
        int result = this.goodsCateService.insertGoodsCate(goodsCate, (String) request.getSession().getAttribute(ValueUtil.NAME));
        // 非空验证 分类名称 是否保存成功
        if (null != goodsCate.getCatName() && 1 == result) {
            // 操作日志
            OperaLogUtil.addOperaLog(request, (String) request.getSession().getAttribute(ValueUtil.NAME), "添加商品分类", (String) request.getSession().getAttribute(ValueUtil.OPERAPATH)
                    + LOGGERINFO1 + goodsCate.getCatName() + LOGGERINFO2 + (String) request.getSession().getAttribute(ValueUtil.NAME));
            LOGGER.info(ValueUtil.ADDGOODSCATEINFO + LOGGERINFO3 + goodsCate.getCatName());
        }
        return new ModelAndView(new RedirectView(PathUtil.ALLCATECONTROLLER + ValueUtil.TOKENPARAM + CSRFTokenManager.getTokenFromRequest(request)));
    }

    /**
     * 更新商品分类
     * 
     * @param goodsCate
     * @return
     */
    @RequestMapping("/updateGoodsCate")
    public ModelAndView updateGoodsCate(@Valid GoodsCate goodsCate, HttpServletRequest request) {
        // 更新商品分类实体，设置修改人名称为当前登陆的用户名称，暂且设置为admin2
        int result = this.goodsCateService.updateGoodsCate(goodsCate, (String) request.getSession().getAttribute(ValueUtil.NAME));
        // 非空验证 商品分类信息 是否更新成功
        if (null != goodsCate.getCatName() && 1 == result) {
            // 操作日志
            OperaLogUtil.addOperaLog(request, (String) request.getSession().getAttribute(ValueUtil.NAME), "更新商品分类", (String) request.getSession().getAttribute(ValueUtil.OPERAPATH)
                    + LOGGERINFO1 + goodsCate.getCatName() + LOGGERINFO2 + (String) request.getSession().getAttribute(ValueUtil.NAME));
            // 日志记录
            LOGGER.info(ValueUtil.UPDATEGOODSCATEINFO + LOGGERINFO3 + goodsCate.getCatName());
        }
        return new ModelAndView(new RedirectView(PathUtil.ALLCATECONTROLLER + ValueUtil.TOKENPARAM + CSRFTokenManager.getTokenFromRequest(request)));
    }

    /**
     * 根据商品ID查询商品分类信息 AJAX
     * 
     * @param cateId
     *            分类主键ID {@link java.lang.Long}
     * @return 商品分类 {@link com.ningpai.goods.bean.GoodsCate}
     */
    @RequestMapping("/queryCateById")
    @ResponseBody
    public GoodsCateVo queryByCateId(Long cateId) {
        GoodsCateVo goodsCateVo = this.goodsCateService.queryGoodsCateById(cateId);
        // 非空验证 商品分类名称
        if (null != goodsCateVo.getCatName()) {
            LOGGER.info(ValueUtil.QUERYCATEBYIDINFO + LOGGERINFO3 + goodsCateVo.getCatName());
        }
        return goodsCateVo;
    }

    /**
     * 查询商品分类
     * 
     * @param pb
     * @return 视图 {@link org.springframework.web.servlet.ModelAndView}
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("/queryGoodsCateVo")
    @ResponseBody
    public PageBean queryGoodsCateVo(PageBean pb, SelectBean selectBean) throws UnsupportedEncodingException {
        // 打印日志
        LOGGER.info(ValueUtil.QUERYGOODSCATEVOINFO);
        // 设置PageBean的数据集合
        pb.setList(this.goodsCateService.getCateList(pb, selectBean));
        // 返回结果
        return pb;
    }

    /**
     * 根据传递的分类ID验证是否存在子类
     * 
     * @param cateId
     *            分类ID {@link java.lang.Long}
     * @return 是否可以删除标记
     */
    @RequestMapping("/checkDel")
    @ResponseBody
    public boolean checkDel(Long cateId) {
        // 打印日志
        LOGGER.info(ValueUtil.CHECKDELINFO);
        // 执行方法并返回结果
        return this.goodsCateService.checkDelWithCateId(cateId);
    }

    /**
     * 查询所有的商品分类
     * 
     * @return 查询到的商品分类
     */
    @RequestMapping("/queryAllCate")
    @ResponseBody
    public List<GoodsCateVo> queryAllCate() {
        // 打印日志
        LOGGER.info(ValueUtil.QUERYALLCATEINFO);
        // 执行方法并返回结果
        return this.goodsCateService.queryAllCate();
    }

    /**
     * 根据父分类ID查询子分类集合
     * 
     * @param catId
     *            父分类ID {@link Long}
     * @param catId
     *            父分类ID {@link Long}
     * @return 子分类集合{@link List}
     */
    @RequestMapping("/querySonCateByCatId")
    @ResponseBody
    public List<GoodsCate> querySonCateByParentId(Long catId) {
        // 执行方法并返回结果
        return this.goodsCateService.querySonCateByParentId(catId);
    }

    /**
     * 根据父分类ID查询子分类集合
     *
     * @param catId
     *            父分类ID {@link Long}
     * @param cateName
     *            分类名称
     * @return 子分类集合{@link List}
     */
    @RequestMapping("/querySonCateByCatIdAndName")
    @ResponseBody
    public List<GoodsCateVo> querySonCateByParentIdAndName(Long catId, String cateName) {
        // 执行方法并返回结果
        return this.goodsCateService.querySonCateVoByParentIdAndName(catId, cateName);
    }

    /**
     * 验证分类名称是否可以添加
     * 
     * @return 是否可以添加的标记 可以添加返回true 否则返回 false
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("/checkCateName")
    @ResponseBody
    public boolean checkCateName(HttpServletRequest request) throws UnsupportedEncodingException {
        // 获取页面中name为cateName的集合
        String cateName = request.getParameter("cateName");
        // 判断cateName是否为空
        if (null != cateName) {
            // 打印日志
            LOGGER.info(ValueUtil.CHECKCATENAMEINFO + LOGGERINFO3 + cateName);
        }
        // 如果查询到的为空 返回true 否则返回false
        return this.goodsCateService.queryCateByCateName(cateName) == null ? true : false;
    }

    /**
     * 导出商品分类
     * 
     * @param response
     */
    @RequestMapping("exportGoodsCate")
    public void exportGoodsCate(HttpServletResponse response) {
        goodsCateService.exportGoodsCate(response);
    }

    /**
     * 导出商品分类模板
     * 
     * @param response
     */
    @RequestMapping("exportGoodsCateTemp")
    public void exportGoodsCateTemp(HttpServletResponse response) {
        goodsCateService.exportGoodsCateTemp(response);
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
    @RequestMapping("/importGoodsCate")
    public void importGoodsCate(HttpServletRequest request, HttpServletResponse response, Long goodsCateId, MultipartHttpServletRequest multiRequest) {
        // 声明变量result并赋初始值
        String result = "200";
        try {
            // 调用service层方法并返回结果赋值给result
            result = this.goodsCateService.importGoodsCateByExcel(request, response, multiRequest);
            PrintWriter out = response.getWriter();
            // 把结果传给回调函数
            out.append("<script>parent.import_callback('" + result + "');</script>");
            out.flush();
        } catch (Exception e) {
            LOGGER.error(""+e);
            result = null;

        }
    }

}
