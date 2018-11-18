/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.common.safe.CSRFTokenManager;
import com.ningpai.goods.bean.GoodsSpec;
import com.ningpai.goods.service.GoodsSpecService;
import com.ningpai.goods.util.PathUtil;
import com.ningpai.goods.util.SelectBean;
import com.ningpai.goods.util.ValueUtil;
import com.ningpai.goods.vo.GoodsSpecVo;
import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;
import com.ningpai.util.UploadUtil;

/**
 * 规格值控制器
 *
 * @author NINGPAI-YuanKangKang
 * @version 1.0
 * @since 2013年12月18日 下午5:02:21
 */
@Controller
public class GoodsSpecController {

    /**
     * 记录日志对象
     */
    private static final MyLogger LOGGER = new MyLogger(GoodsSpecController.class);

    private static final String SPECDETAILNAME = "specDetailName";
    private static final String SPECDETAILSORT = "specDetailSort";
    private static final String LOGGERINFO1 = ",用户名：";
    private static final String LOGGERINFO2 = ",规格名称为：";
    private static final String LOGGERINFO3 = "规格值名称【";
    private static final String LOGGERINFO4 = "】,用户名：";

    private GoodsSpecService goodsSpecService;

    public GoodsSpecService getGoodsSpecService() {
        return goodsSpecService;
    }

    @Resource(name = "GoodsSpecService")
    public void setGoodsSpecService(GoodsSpecService goodsSpecService) {
        this.goodsSpecService = goodsSpecService;
    }

    /**
     * 保存规格和批量保存规格值
     *
     * @param request
     *            请求对象
     * @return 视图 {@link org.springframework.web.servlet.ModelAndView;}
     */
    @RequestMapping("/saveSpec")
    public ModelAndView saveSpec(@Valid GoodsSpec goodsSpec, HttpServletRequest request, MultipartHttpServletRequest request2) {
        // 获取所有的规格值数组
        String[] detailnames = request.getParameterValues(SPECDETAILNAME);
        String[] specDetailNicknames = request.getParameterValues("specDetailNickname");
        String[] specDetailSorts = request.getParameterValues(SPECDETAILSORT);
        // 获取图片的数组
        List<MultipartFile> fileList = request2.getFiles("specImage");
        String[] specDetailImgs = new String[fileList.size()];
        // 批量上传图片
        for (int i = 0; i < fileList.size(); i++) {
            if (!fileList.get(i).isEmpty()) {
                specDetailImgs[i] = UploadUtil.uploadFile(fileList.get(i), request).get("oldimg");
            } else {
                specDetailImgs[i] = "";
            }
        }
        try {
            // 添加规格值，传递商品规格实体和规格值列表，操作人名称应该是当前登陆的用户名称，暂且设置为admins
            this.goodsSpecService.saveGoodsSpec(goodsSpec, detailnames, specDetailNicknames, specDetailImgs, specDetailSorts,
                    (String) request.getSession().getAttribute(ValueUtil.NAME));
            OperaLogUtil.addOperaLog(request, (String) request.getSession().getAttribute(ValueUtil.NAME), "保存规格和批量保存规格值",
                    (String) request.getSession().getAttribute(ValueUtil.OPERAPATH) + LOGGERINFO1 + (String) request.getSession().getAttribute(ValueUtil.NAME));
            return new ModelAndView(new RedirectView(PathUtil.ALLSPECCONTROLLER + ValueUtil.TOKENPARAM + CSRFTokenManager.getTokenFromRequest(request)));
        } finally {
            // 置空所有参数
            detailnames = null;
            specDetailNicknames = null;
            specDetailImgs = null;
            specDetailSorts = null;
        }
    }

    /**
     * 保存规格
     *
     * @param request
     *            请求对象
     * @return 视图 {@link org.springframework.web.servlet.ModelAndView;}
     */
    @RequestMapping("/saveSpecNew")
    public ModelAndView saveSpecNew(@Valid GoodsSpec goodsSpec, HttpServletRequest request) {
        try {
            // 添加规格值，传递商品规格实体和规格值列表，操作人名称应该是当前登陆的用户名称，暂且设置为admins
            this.goodsSpecService.saveGoodsSpec(goodsSpec, (String) request.getSession().getAttribute(ValueUtil.NAME));
            // 记录当前登录者的操作日志
            OperaLogUtil.addOperaLog(request, (String) request.getSession().getAttribute(ValueUtil.NAME), "保存规格", (String) request.getSession().getAttribute(ValueUtil.OPERAPATH)
                    + LOGGERINFO1 + (String) request.getSession().getAttribute(ValueUtil.NAME));
            // 返回结果
            return new ModelAndView(new RedirectView(PathUtil.ALLSPECCONTROLLER + ValueUtil.TOKENPARAM + CSRFTokenManager.getTokenFromRequest(request)));
        } finally {
        }
    }

    /**
     * 更新规格信息
     *
     * @param goodsSpec
     *            商品规格的实体 {@link com.ningpai.goods.bean.GoodsSpec}
     * @param request
     *            请求对象
     * @return 视图 {@link org.springframework.web.servlet.ModelAndView;}
     */
    @RequestMapping("/updateSpec")
    public ModelAndView updateSpec(@Valid GoodsSpec goodsSpec, HttpServletRequest request, MultipartHttpServletRequest request2) {
        // 根据ID获取商品规格对象
        GoodsSpecVo goodsSpecVos = this.goodsSpecService.queryBySpecPrimaryKey(goodsSpec.getSpecId());
        // 操作日志
        LOGGER.info(ValueUtil.UPDATESPECINFO + LOGGERINFO2 + goodsSpecVos.getSpecName());
        // 获取规格id并保存
        String[] specDetailIds = request.getParameterValues("specDetailId");
        // 获取规格删除标记并保存
        String[] specDetailDelflag = request.getParameterValues("specDetailDelflag");
        // 获取规格名称并保存
        String[] specDetailName = request.getParameterValues(SPECDETAILNAME);
        // 获取规格别名并保存
        String[] specDetailNickname = request.getParameterValues("specDetailNickname");
        // 获取规格图片并保存
        String[] specDetailImg = request.getParameterValues("specDetailImg");
        // 获取规格排序并保存
        String[] specDetailSort = request.getParameterValues(SPECDETAILSORT);
        // 获取上传的图片
        List<MultipartFile> fileList = request2.getFiles("specImage");
        // 批量上传图片
        if (null != fileList && !fileList.isEmpty()) {
            for (int i = 0; i < fileList.size(); i++) {
                if (!fileList.get(i).isEmpty()) {
                    specDetailImg[i] = UploadUtil.uploadFile(fileList.get(i), request).get("oldimg");
                }
            }
        }
        try {
            // 执行更新操作，操作人应该是当前登录人名称，暂且设置为admin2
            this.goodsSpecService.updateGoodsSpec(goodsSpec, goodsSpecService.changeSpecDetail(specDetailIds, specDetailDelflag, specDetailName, specDetailNickname, specDetailImg,
                    specDetailSort), (String) request.getSession().getAttribute(ValueUtil.NAME));
            // 记录当前登录者的操作日志
            OperaLogUtil.addOperaLog(request, (String) request.getSession().getAttribute(ValueUtil.NAME), ValueUtil.UPDATESPECINFO,
                    (String) request.getSession().getAttribute(ValueUtil.OPERAPATH) + LOGGERINFO3 + goodsSpec.getSpecName() + LOGGERINFO4
                            + (String) request.getSession().getAttribute(ValueUtil.NAME));
            // 返回结果
            return new ModelAndView(new RedirectView(PathUtil.ALLSPECCONTROLLER + ValueUtil.TOKENPARAM + CSRFTokenManager.getTokenFromRequest(request)));
        } finally {
            // 所有参数置空
            specDetailIds = null;
            specDetailDelflag = null;
            specDetailName = null;
            specDetailNickname = null;
            specDetailImg = null;
            specDetailSort = null;
        }
    }

    /**
     * 更新规格信息
     *
     * @param goodsSpec
     *            商品规格的实体 {@link com.ningpai.goods.bean.GoodsSpec}
     * @param request
     *            请求对象
     * @return 视图 {@link org.springframework.web.servlet.ModelAndView;}
     */
    @RequestMapping("/updateSpecNew")
    public ModelAndView updateSpecNew(@Valid GoodsSpec goodsSpec, HttpServletRequest request) {
        // 根据ID获取商品规格对象
        GoodsSpecVo goodsSpecVos = this.goodsSpecService.queryBySpecPrimaryKey(goodsSpec.getSpecId());
        // 操作日志
        LOGGER.info(ValueUtil.UPDATESPECINFO + LOGGERINFO2 + goodsSpecVos.getSpecName());
        try {
            // 执行更新操作，操作人应该是当前登录人名称，暂且设置为admin2
            this.goodsSpecService.updateGoodsSpec(goodsSpec, (String) request.getSession().getAttribute(ValueUtil.NAME));
            // 记录当前登录者的操作日志
            OperaLogUtil.addOperaLog(request, (String) request.getSession().getAttribute(ValueUtil.NAME), ValueUtil.UPDATESPECINFO,
                    (String) request.getSession().getAttribute(ValueUtil.OPERAPATH) + LOGGERINFO3 + goodsSpec.getSpecName() + LOGGERINFO4
                            + (String) request.getSession().getAttribute(ValueUtil.NAME));
            // 返回结果
            return new ModelAndView(new RedirectView(PathUtil.ALLSPECCONTROLLER + ValueUtil.TOKENPARAM + CSRFTokenManager.getTokenFromRequest(request)));
        } finally {

        }
    }

    /**
     * 更新规格值信息
     *
     * @param goodsSpec
     *            商品规格的实体 {@link com.ningpai.goods.bean.GoodsSpec}
     * @param request
     *            请求对象
     * @return 视图 {@link org.springframework.web.servlet.ModelAndView;}
     */
    @RequestMapping("/updateSpecDetail")
    public ModelAndView updateSpecDetail(GoodsSpec goodsSpec, HttpServletRequest request) {
        // 根据ID获取商品规格对象
        GoodsSpecVo goodsSpecVos = this.goodsSpecService.queryBySpecPrimaryKey(goodsSpec.getSpecId());
        // 操作日志
        LOGGER.info(ValueUtil.UPDATESPECINFO + LOGGERINFO2 + goodsSpecVos.getSpecName());
        // 获取规格id并保存
        String[] specDetailIds = request.getParameterValues("specDetailId");
        // 获取规格删除标记并保存
        String[] specDetailDelflag = request.getParameterValues("specDetailDelflag");
        // 获取规格名称并保存
        String[] specDetailName = request.getParameterValues(SPECDETAILNAME);
        // 获取规格排序并保存
        String[] specDetailSort = request.getParameterValues(SPECDETAILSORT);
        try {
            // 执行更新操作，操作人应该是当前登录人名称，暂且设置为admin2
            this.goodsSpecService.updateGoodsSpec(goodsSpec, goodsSpecService.changeSpecDetail(specDetailIds, specDetailDelflag, specDetailName, null, null, specDetailSort),
                    (String) request.getSession().getAttribute(ValueUtil.NAME));
            // 记录当前登录者的操作日志
            OperaLogUtil.addOperaLog(request, (String) request.getSession().getAttribute(ValueUtil.NAME), ValueUtil.UPDATESPECINFO,
                    (String) request.getSession().getAttribute(ValueUtil.OPERAPATH) + LOGGERINFO3 + goodsSpec.getSpecName() + LOGGERINFO4
                            + (String) request.getSession().getAttribute(ValueUtil.NAME));
            // 返回结果
            return new ModelAndView(new RedirectView(PathUtil.ALLSPECCONTROLLER + ValueUtil.TOKENPARAM + CSRFTokenManager.getTokenFromRequest(request)));
        } finally {
            // 所有参数置空
            specDetailIds = null;
            specDetailDelflag = null;
            specDetailName = null;
            specDetailSort = null;
        }
    }

    /**
     * 根据分页参数查询规格
     *
     * @param pb
     *            分页帮助类{@link com.ningpai.util.PageBean}
     * @return 视图 {@link org.springframework.web.servlet.ModelAndView;}
     */
    @RequestMapping("/findAllSpec")
    public ModelAndView findAllSpec(HttpServletRequest request, HttpServletResponse response, PageBean pb, SelectBean selectBean) {
        // 打印日志
        LOGGER.info(ValueUtil.FINDALLSPECINFO);
        // 参数设置到作用域中
        request.setAttribute("selectBean", selectBean);
        // 返回结果
        return new ModelAndView(PathUtil.GOODSSPEC, "pb", this.goodsSpecService.searchSpecListByPageBean(pb, selectBean));
    }

    /**
     * 单个删除规格
     *
     * @param specId
     *            规格ID {@link java.lang.Long}
     * @return 视图 {@link org.springframework.web.servlet.ModelAndView;}
     */
    @RequestMapping("/delSpec")
    public ModelAndView delSpec(Long specId, HttpServletRequest request) {
        // 根据ID获取商品规格对象
        GoodsSpecVo goodsSpecVos = this.goodsSpecService.queryBySpecPrimaryKey(specId);
        // 非空验证 商品规格名称
        if (null != goodsSpecVos.getSpecName()) {
            // 操作日志
            LOGGER.info(ValueUtil.DELSPECINFO + ",商品规格名称为:" + goodsSpecVos.getSpecName());
        }
        // 删除规格，删除人名称应该是当前登陆用户的用户名，暂且设置为admin
        this.goodsSpecService.delGoodsSpec(specId, (String) request.getSession().getAttribute(ValueUtil.NAME));
        // 记录当前登录者的操作日志
        OperaLogUtil.addOperaLog(request, (String) request.getSession().getAttribute(ValueUtil.NAME), ValueUtil.DELSPECINFO,
                (String) request.getSession().getAttribute(ValueUtil.OPERAPATH) + LOGGERINFO3 + goodsSpecVos.getSpecName() + LOGGERINFO4
                        + (String) request.getSession().getAttribute(ValueUtil.NAME));
        // 返回结果
        return new ModelAndView(new RedirectView(PathUtil.ALLSPECCONTROLLER + ValueUtil.TOKENPARAM + CSRFTokenManager.getTokenFromRequest(request)));
    }

    /**
     * 批量删除商品规格
     *
     * @param specIds
     *            需要删除的规格的ID数组 {@link java.lang.Long}
     * @return 视图 {@link org.springframework.web.servlet.ModelAndView;}
     */
    @RequestMapping("/batchDelSpec")
    public ModelAndView batchDelSpec(Long[] specIds, HttpServletRequest request) {
        // 打印日志
        LOGGER.info(ValueUtil.BATCHDELSPECINFO);
        // 进行批量删除,删除人名称应该是当前登录的用户的名称，暂且设置为admin
        this.goodsSpecService.batchDelGoodsSpec(specIds, (String) request.getSession().getAttribute(ValueUtil.NAME));
        // 记录当前登录者的操作日志
        OperaLogUtil.addOperaLog(request, (String) request.getSession().getAttribute(ValueUtil.NAME), ValueUtil.BATCHDELSPECINFO,
                (String) request.getSession().getAttribute(ValueUtil.OPERAPATH) + LOGGERINFO1 + (String) request.getSession().getAttribute(ValueUtil.NAME));
        // 返回结果
        return new ModelAndView(new RedirectView(PathUtil.ALLSPECCONTROLLER + ValueUtil.TOKENPARAM + CSRFTokenManager.getTokenFromRequest(request)));
    }

    /**
     * 根据分类ID查询分类的Vo 返回json对象
     *
     * @param specId
     *            分类ID {@link java.lang.Long}
     * @return 查询到的SpecVo {@link com.ningpai.goods.vo.GoodsSpecVo}
     */
    @RequestMapping("/queryCateVoById")
    @ResponseBody
    public GoodsSpecVo findCateVoById(Long specId) {
        // 打印日志
        LOGGER.info(ValueUtil.QUERYCATEVOBYIDINFO + "规格ID为:" + specId);
        // 返回结果
        return this.goodsSpecService.queryBySpecPrimaryKey(specId);
    }

    /**
     * 查询所有的规格列表
     *
     * @return 查询到的规格列表 {@link java.util.List}
     *         {@link com.ningpai.goods.bean.GoodsSpec}
     */
    @RequestMapping("/queryAllSpec")
    @ResponseBody
    public List<GoodsSpec> queryAllSpec() {
        // 打印日志
        LOGGER.info(ValueUtil.QUERYALLSPECINFO);
        // 返回结果
        return this.goodsSpecService.queryAllSpec();
    }

    /**
     * 验证规格值是否可用
     *
     * @param specName
     *            待验证的规格名称
     * @return 可用返回true 不可用返回false
     */
    @RequestMapping("/checkSpecName")
    @ResponseBody
    public boolean checkSpecName(String specName) {
        if (null != specName) {
            // 打印日志
            LOGGER.info(ValueUtil.CHECKSPECNAMEINFO + "规格值为:" + specName);
        } else {
            // 打印日志
            LOGGER.info(ValueUtil.CHECKSPECNAMEINFO);
        }
        // 返回结果
        return this.goodsSpecService.checkSpecName(specName);
    }

}
