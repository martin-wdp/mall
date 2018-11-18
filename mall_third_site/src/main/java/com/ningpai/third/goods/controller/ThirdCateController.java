/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.third.goods.controller;

import com.ningpai.customer.bean.Customer;
import com.ningpai.third.goods.bean.ThirdCate;
import com.ningpai.third.goods.service.ThirdCateService;
import com.ningpai.third.goods.util.ThirdValueBean;
import com.ningpai.third.goods.vo.ThirdCateVo;
import com.ningpai.third.util.MenuOperationUtil;
import com.ningpai.util.MyLogger;
import com.ningpai.util.SelectBean;
import com.ningpai.util.UploadUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * 第三方店铺分类Controller
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年5月6日 下午3:25:48
 * @version 1.0
 */
@Controller
public class ThirdCateController {
    private ThirdCateService thirdCateService;

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(
            ThirdCateController.class);

    /**
     * 跳转到分类管理的控制器
     * 
     * @param request
     * @param n
     *            导航索引
     * @param l
     *            导航索引
     */
    @RequestMapping("/cateManager")
    public ModelAndView cateManager(HttpServletRequest request, String n,
            String l) {
        MenuOperationUtil.fillSessionMenuIndex(request, n, l);
        try {
            return new ModelAndView("goods/thirdcate");
        } finally {
            LOGGER.info(ThirdValueBean.CATEMANAGER);
        }
    }

    /**
     * 查询用于列表显示的数据
     * 
     * @param selectBean
     *            查询参数Bean {@link com.ningpai.util.SelectBean}
     * @return 计算好的分类列表数据
     */
    @RequestMapping("getAllThirdCateForList")
    @ResponseBody
    public List<Object> queryThirdCateForList(SelectBean selectBean,
            HttpServletRequest request) {
        // 日志记录
        LOGGER.info(ThirdValueBean.QUERYTHIRDCATEFORLIST);
        return this.thirdCateService.getAllCalcThirdCate(selectBean,
                (Long) request.getSession()
                        .getAttribute(ThirdValueBean.THIRDID));
    }

    /**
     * 查询所有的分类数据
     *
     * @return 所有的分类列表 {@link java.util.List}
     */
    @RequestMapping("/getAllThirdCate")
    @ResponseBody
    public List<ThirdCate> getAllThirdCate(HttpServletRequest request) {
        LOGGER.info(ThirdValueBean.GETALLTHIRDCATE);
        // 日志记录
        return this.thirdCateService.queryAllCate((Long) request.getSession()
                .getAttribute(ThirdValueBean.THIRDID));
    }

    /**
     * 保存第三方分类信息
     *
     * @param cate
     *            带保存的实体
     * @param request
     *            请求对象
     */
    @RequestMapping("/saveThirdCate")
    public ModelAndView saveThirdCate(ThirdCate cate,
            HttpServletRequest request, MultipartHttpServletRequest request2) {
        // 日志记录
        LOGGER.info(ThirdValueBean.SAVETHIRDCATE);
        // 上传的分类图片信息
        MultipartFile imageFile = request2.getFile("upCatImg");
        if (!imageFile.isEmpty()) {
            // 设置老的分类图片信息
            cate.setCatImg(UploadUtil.uploadFile(imageFile, request).get(
                    "oldimg"));
        }
        // 设置商家ID
        cate.setThirdId((Long) request.getSession().getAttribute(
                ThirdValueBean.THIRDID));
        // 商家店铺的名称
        cate.setThirdName((String) request.getSession().getAttribute(
                "storeName"));
        // 保存上传的分类信息
        this.thirdCateService.insertThirdCate(
                cate,
                ((Customer) request.getSession().getAttribute(
                        ThirdValueBean.CUST)).getCustomerUsername());
        return new ModelAndView(new RedirectView(ThirdValueBean.CATELISTCON));
    }

    /**
     * 删除分类信息
     *
     * @param thirdCateId
     *            待删除的分类ID
     * @param request
     *            请求对象
     */
    @RequestMapping("/delThirdCate")
    public ModelAndView delThirdCate(Long thirdCateId,
            HttpServletRequest request) {
        // 根据商品分类ID删除分类信息
        this.thirdCateService.delThirdCateNew(
                thirdCateId,
                ((Customer) request.getSession().getAttribute(
                        ThirdValueBean.CUST)).getCustomerUsername(),
                (Long) request.getSession().getAttribute("thirdId"));
        // 记录日志
        LOGGER.info(ThirdValueBean.DELTHIRDCATE);
        return new ModelAndView(new RedirectView(ThirdValueBean.CATELISTCON));
    }

    /**
     * 批量删除分类信息
     *
     * @param thirdCateIds
     *            待删除的分类ID数组
     * @param request
     *            请求对象
     */
    @RequestMapping("/batchDelThirdCate")
    public ModelAndView batchDelThirdCate(Long[] thirdCateIds,
            HttpServletRequest request) {
        // 根据分类ID批量删删除分类信息
        this.thirdCateService.batchDelThirdCate(
                thirdCateIds,
                ((Customer) request.getSession().getAttribute(
                        ThirdValueBean.CUST)).getCustomerUsername());
        // 记录操作日志
        LOGGER.info(ThirdValueBean.BATCHDELTHIRDCATE);
        return new ModelAndView(new RedirectView(ThirdValueBean.CATELISTCON));
    }

    /**
     * 更新分类信息
     *
     * @param cate
     *            待更新的分类信息
     * @param request
     *            请求对象
     */
    @RequestMapping("/updateThirdCate")
    public ModelAndView updateThirdCate(ThirdCate cate,
            HttpServletRequest request, MultipartHttpServletRequest request2) {
        // 上传的分类商品的图片
        MultipartFile imageFile = request2.getFile("upCatImg");
        // 如果没有上传新图片 就选之前的老图片
        if (!imageFile.isEmpty()) {
            cate.setCatImg(UploadUtil.uploadFile(imageFile, request).get(
                    "oldimg"));
        }
        cate.setThirdId((Long) request.getSession().getAttribute("thirdId"));
        // 更新分类信息
        this.thirdCateService.updateThirdCate(
                cate,
                ((Customer) request.getSession().getAttribute(
                        ThirdValueBean.CUST)).getCustomerUsername());
        // 记录更新分类信息的操作日志
        LOGGER.info(ThirdValueBean.UPDATETHIRDCATE);
        return new ModelAndView(new RedirectView(ThirdValueBean.CATELISTCON));
    }

    /**
     * 快速更新分类信息
     *
     * @param cate
     *            分类实体
     * @param request
     *            请求对象
     * @return 更新的行数
     */
    @RequestMapping("/fastUpdateThirdCate")
    @ResponseBody
    public int fastUpdateThirdCate(ThirdCate cate, HttpServletRequest request) {
        // 记录操作日志
        LOGGER.info(ThirdValueBean.FASTUPDATETHIRDCATE);
        // 快速更新分类信息
        return this.thirdCateService.updateThirdCate(
                cate,
                ((Customer) request.getSession().getAttribute(
                        ThirdValueBean.CUST)).getCustomerUsername());
    }

    /**
     * 验证分类名称是否可用
     *
     * @param cateName
     *            待验证的分类名称
     * @param request
     *            请求对象
     * @return 验证结果 如果可用返回true 否则返回false
     */
    @RequestMapping("/checkThirdCateName")
    @ResponseBody
    public boolean checkThirdCateName(String cateName,
            HttpServletRequest request) {
        // 记录日志
        LOGGER.info(ThirdValueBean.CHECKTHIRDCATENAME);
        // 验证分类名称是否可用
        return this.thirdCateService.queyCateByCateName(cateName,
                (Long) request.getSession()
                        .getAttribute(ThirdValueBean.THIRDID)) == null ? true
                : false;
    }

    /**
     * 验证分类是否可以删除
     * 
     * @param thirdCateId
     *            需要验证的分类ID
     * @return 如果传递过来的分类ID下的子分类的数量不等于空返回false表示不可删除
     */
    @RequestMapping("/checkThirdCateDel")
    @ResponseBody
    public boolean checkThirdDel(Long thirdCateId) {
        // 记录操作日志
        LOGGER.info(ThirdValueBean.CHECKTHIRDDEL);
        // 验证分类是否可以删除
        return this.thirdCateService.checkDelWithCateId(thirdCateId);
    }

    /**
     * 根据分类ID查询分类信息
     *
     * @param catId
     *            分类ID
     * @return 查询到的分类实体
     */
    @RequestMapping("/queryThirdCateByCateId")
    @ResponseBody
    public ThirdCateVo queryThirdCateByCateId(Long catId) {
        // 记录操作日志
        LOGGER.info(ThirdValueBean.QUERYTHIRDCATEBYCATEID);
        // 根据分类ID
        return this.thirdCateService.queryThirdCateById(catId);
    }

    /**
     * 查询cookie中存储的最近使用的分类信息
     *
     * @return 查询到的分类列表
     * @throws java.io.UnsupportedEncodingException
     */
    @RequestMapping("/getCookieThirdCate")
    @ResponseBody
    public List<ThirdCate> getCookieThirdCate(HttpServletRequest request,
            HttpServletResponse response) throws UnsupportedEncodingException {
        return this.thirdCateService.takeFormCookie(request, response);
    }

    /**
     * 搜索第三方分类信息
     *
     * @param cateName
     *            带搜索的分类信息
     * @return 查询到的分类列表
     */
    @RequestMapping("/qureyGradeLastThitsCate")
    @ResponseBody
    public List<ThirdCate> queryGradeLastThirdCate(String cateName,
            HttpServletRequest request) {
        return this.thirdCateService.getThirdCateByCateNameAndGrade(cateName,
                (Long) request.getSession()
                        .getAttribute(ThirdValueBean.THIRDID));
    }

    /**
     * 根据父分类ID查询子分类集合
     *
     * @param cateId
     *            父分类ID
     * @return 查询到的子分类集合
     */
    @RequestMapping("/getThirdCateByParentCatId")
    @ResponseBody
    public List<ThirdCate> getThirdCateByParentCatId(Long cateId,
            HttpServletRequest request) {
        return this.thirdCateService.getThirdCateByParentId(cateId,
                (Long) request.getSession()
                        .getAttribute(ThirdValueBean.THIRDID));
    }

    /**
     * 查询所有的第三方签约的分类
     * 
     * @return 查询到的子分类集合
     */
    @RequestMapping("/getThirdCateByParentCatIdtwo")
    @ResponseBody
    public List<ThirdCate> getThirdCateByParentCatIdtwo(
            HttpServletRequest request) {
        return this.thirdCateService.getThirdCateByParentIdtwo((Long) request
                .getSession().getAttribute(ThirdValueBean.THIRDID));
    }

    /**
     * 清除cookie中的最近使用的分类信息
     *
     * @param request
     *            请求对象 {@link javax.servlet.http.HttpServletRequest}
     * @param response
     *            请求响应对象 {@link javax.servlet.http.HttpServletResponse }
     * @return 删除是否成功的标记 {@link Boolean}
     */
    @RequestMapping("/clearThirdCateFromCookie")
    @ResponseBody
    public boolean clearThirdCateFormCookie(HttpServletRequest request,
            HttpServletResponse response) {
        return this.thirdCateService
                .clearThirdCateFromCookie(request, response);
    }

    public ThirdCateService getThirdCateService() {
        return thirdCateService;
    }

    @Resource(name = "ThirdCateService")
    public void setThirdCateService(ThirdCateService thirdCateService) {
        this.thirdCateService = thirdCateService;
    }

    /**
     * 根据父分类id和分类名称查询商家分类信息
     * 
     * @param request
     * @param thirdCateId
     *            父分类id
     * @param thirdCateName
     *            分类名称
     * @return
     */
    @RequestMapping("/queryThirdSonCateByCateIdAndName")
    @ResponseBody
    public List<ThirdCate> queryThirdSonCateByCateIdAndName(
            HttpServletRequest request, Long thirdCateId, String thirdCateName) {
        return this.thirdCateService.queryThirdSonCateByCateIdAndName(
                thirdCateId, thirdCateName, (Long) request.getSession()
                        .getAttribute(ThirdValueBean.THIRDID));
    }
}
