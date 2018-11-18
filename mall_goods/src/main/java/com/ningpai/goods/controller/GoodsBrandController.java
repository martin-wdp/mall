/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.controller;

import com.ningpai.common.safe.CSRFTokenManager;
import com.ningpai.common.util.ConstantUtil;
import com.ningpai.goods.bean.GoodsBrand;
import com.ningpai.goods.service.GoodsBrandService;
import com.ningpai.goods.util.PathUtil;
import com.ningpai.goods.util.SelectBean;
import com.ningpai.goods.util.ValueUtil;
import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.uploadfileset.service.UploadFileSetService;
import com.ningpai.util.MenuSession;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;
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
import javax.validation.Valid;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * 商品品牌控制器
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月17日 上午9:09:30
 * @version 1.0
 */
@Controller
public class GoodsBrandController {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(GoodsBrandController.class);

    private static final String LOGOFILE = "logoFile";
    private static final String LOGGERINFO1 = "-->品牌名称【";
    private static final String LOGGERINFO2 = "】,用户名：";

    private GoodsBrandService goodsBrandService;

    @Resource(name = "UploadFileSetService")
    private UploadFileSetService uploadFileSetService;

    public GoodsBrandService getGoodsBrandService() {
        return goodsBrandService;
    }

    @Resource(name = "GoodsBrandService")
    public void setGoodsBrandService(GoodsBrandService goodsBrandService) {
        this.goodsBrandService = goodsBrandService;
    }

    /**
     * 查询所有的商品品牌
     * 
     * @return {@link org.springframework.web.servlet.ModelAndView;}
     */
    @RequestMapping("/findAllBrand")
    public ModelAndView findAllBrand(HttpServletRequest request, HttpServletResponse response, PageBean pb, SelectBean selectBean) {
        MenuSession.sessionMenu(request);
        ModelAndView mav = new ModelAndView();
        mav.setViewName(PathUtil.GOODSBRAND);
        mav.addObject("pb", goodsBrandService.searchByPageBean(pb, selectBean));
        mav.addObject("ufs", uploadFileSetService.getCurrUploadFileSet());
        // 参数设置到作用域中
        request.setAttribute(ValueUtil.SELECTBEAN, selectBean);
        LOGGER.info(ValueUtil.SEARCHGOODSBRAND);
        return mav;
    }

    /**
     * 查询所有的商品品牌
     * 
     * @return {@link org.springframework.web.servlet.ModelAndView;}
     */
    @RequestMapping("/chooseTrademark")
    public ModelAndView chooseTrademark(HttpServletRequest request, HttpServletResponse response, PageBean pb, SelectBean selectBean) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName(ValueUtil.CHOOSEBRANDLIST);
        mav.addObject("pageBean", goodsBrandService.searchByPageBean(pb, selectBean));
        // 参数设置到作用域中
        request.setAttribute("selectBean", selectBean);
        return mav;
    }

    /**
     * 查询所有的商品品牌
     * 
     * @author NINGPAI-WangHaiYang
     * @return {@link org.springframework.web.servlet.ModelAndView;}
     */
    @RequestMapping("/chooseBrand")
    public ModelAndView chooseBrand(HttpServletRequest request, HttpServletResponse response, PageBean pb, SelectBean selectBean) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName(ValueUtil.CHOOSEBRANDLIST);
        mav.addObject("pageBean", goodsBrandService.searchByPageBean(pb, selectBean));
        // 参数设置到作用域中
        request.setAttribute("selectBean", selectBean);

        return mav;
    }

    /**
     * 查询所有品牌信息
     * 
     * @return
     */
    @RequestMapping("/queryallbrand")
    @ResponseBody
    public List<GoodsBrand> queryAllBrandList() {
        // 打印日志
        LOGGER.info(ValueUtil.SEARCHBRANDLIST);
        // 执行方法并返回结果
        return goodsBrandService.queryAllBrand();
    }

    /**
     * 根据关键字查询品牌信息
     * 
     * @return
     */
    @RequestMapping("/queryallbrandbyName")
    @ResponseBody
    public List<GoodsBrand> queryallbrandbyName(String brandName) {
        // 非空验证 品牌关键字
        if (null != brandName) {
            LOGGER.info("根据" + brandName + "关键字" + ValueUtil.SEARCHBRANDLIST);
        }
        return goodsBrandService.queryallbrandbyName(brandName);
    }

    /**
     * 插入一条品牌数据
     * 
     * @param goodsBrand
     *            待插入的商品品牌实体 {@link com.ningpai.goods.bean.GoodsBrand}
     * @return {@link org.springframework.web.servlet.ModelAndView;}
     */
    @RequestMapping("/saveBrand")
    public ModelAndView saveBrand(@Valid GoodsBrand goodsBrand, MultipartHttpServletRequest request) {
        // 获取品牌LOGO的路径
        MultipartFile imageFile = null;
        // 新后台取picFile
        if (request.getFile(LOGOFILE) == null) {
            imageFile = request.getFile("picFile");
        } else {
            imageFile = request.getFile(LOGOFILE);
        }

        if (imageFile != null && !imageFile.isEmpty()) {
            goodsBrand.setBrandLogo(UploadUtil.uploadFile(imageFile, request).get("oldimg"));
        }
        // 判断是否重名
        if (goodsBrandService.selectByBrandName(goodsBrand.getBrandName()) == 0) {
            // 插入商品品牌，插入人名称应该为当前登录的用户名称
            int result = this.goodsBrandService.insertGoodsBrand(goodsBrand, (String) request.getSession().getAttribute(ValueUtil.NAME));
            // 非空验证 品牌名称 和是否保存成功
            if (null != goodsBrand.getBrandName() && 1 == result) {
                // 操作日志
                OperaLogUtil.addOperaLog(request, (String) request.getSession().getAttribute(ValueUtil.NAME), "保存商品品牌",
                        request.getSession().getAttribute(ValueUtil.OPERAPATH) + LOGGERINFO1 + goodsBrand.getBrandName() + LOGGERINFO2
                                + request.getSession().getAttribute(ValueUtil.NAME));
                LOGGER.info(ValueUtil.SAVEBRANDINFO + "保存品牌为" + goodsBrand.getBrandNickname() + "成功");
            }
        }

        // 返回到查询所有商品品牌的控制器
        return new ModelAndView(new RedirectView(PathUtil.ALLBRANDCONTROLLER + ValueUtil.TOKENPARAM + CSRFTokenManager.getTokenFromRequest(request)));
    }

    /**
     * 根据商品品牌ID删除商品品牌信息
     * 
     * @param brandId
     *            商品品牌主键id {@link Long}
     * @return {@link org.springframework.web.servlet.ModelAndView;}
     */
    @RequestMapping("/delBrand")
    @ResponseBody
    public String delBrand(Long brandId, HttpServletRequest request,PageBean pb) {
        // 根据主键获取品牌对象
        GoodsBrand goodsBrand = this.goodsBrandService.queryBrandById(brandId);
        // 删除品牌
        int result = this.goodsBrandService.deleteGoodsBrand(brandId, (String) request.getSession().getAttribute(ValueUtil.NAME));
        // 非空验证 品牌名称 和是否删除成功
        if (null != goodsBrand.getBrandName() && 1 == result) {
            // 操作日志
            OperaLogUtil.addOperaLog(request, (String) request.getSession().getAttribute(ValueUtil.NAME), "删除商品品牌", request.getSession().getAttribute(ValueUtil.OPERAPATH)
                    + LOGGERINFO1 + goodsBrand.getBrandName() + LOGGERINFO2 + request.getSession().getAttribute(ValueUtil.NAME));
            // 记录日志
            LOGGER.info(ValueUtil.DELBRANDINFO + "删除品牌为" + goodsBrand.getBrandNickname() + "品牌信息");
        }


        //重新查实时数据
        PageBean pbDb= goodsBrandService.searchByPageBean(pb, new SelectBean());

        int sum=pbDb.getRows();
        int totalpage=0;
        if(sum%pbDb.getPageSize()==0){
            totalpage=sum/pbDb.getPageSize();
        }else{
            totalpage=sum/pbDb.getPageSize()+1;
        }

        return PathUtil.ALLBRANDCONTROLLER + ValueUtil.TOKENPARAM + CSRFTokenManager.getTokenFromRequest(request)+ ValueUtil.PAGEBEANPARAM
                + (pb.getPageNo()>totalpage?totalpage:pb.getPageNo());
    }

    /**
     * 批量删除商品品牌
     *
     * @param brandIds
     *            待删除的商品品牌的id的数组 {@link Long}
     * @return {@link org.springframework.web.servlet.ModelAndView;}
     */
    @RequestMapping("/batchDelBrand")
    public ModelAndView batchDelBrand(Long[] brandIds, HttpServletRequest request, PageBean pb) {
        // 执行批量删除操作，删除人名称应该是当前登陆的用户名称
        this.goodsBrandService.batchDeleteGodosBrand(brandIds, (String) request.getSession().getAttribute(ValueUtil.NAME));
        LOGGER.info(ValueUtil.BATCHDELBRAND);
        // 获取当前登录的用户名
        // String name = (String)
        // request.getSession().getAttribute(ValueUtil.NAME);
        // 操作日志
        OperaLogUtil.addOperaLog(request, (String) request.getSession().getAttribute(ValueUtil.NAME), "批量删除商品品牌", request.getSession().getAttribute(ValueUtil.OPERAPATH)
                + "用户名：" + request.getSession().getAttribute(ValueUtil.NAME));

        //重新查实时数据
       PageBean pbDb= goodsBrandService.searchByPageBean(pb, new SelectBean());
        // 重定向到查询所有商品列表
        return new ModelAndView(new RedirectView(PathUtil.ALLBRANDCONTROLLER + ValueUtil.TOKENPARAM + CSRFTokenManager.getTokenFromRequest(request) + ValueUtil.PAGEBEANPARAM
                + (pb.getPageNo()>pbDb.getTotalPages()?pbDb.getTotalPages():pb.getPageNo())));
    }

    /**
     * 根据ID查询商品品牌对象 Ajax方法
     *
     * @param brandId
     *            商品品牌ID {@link Long}
     * @return {@link com.ningpai.goods.bean.GoodsBrand}
     */
    @RequestMapping("/queryBrandById")
    @ResponseBody
    public GoodsBrand queryById(Long brandId) {
        GoodsBrand goodsBrand = this.goodsBrandService.queryBrandById(brandId);
        // 非空验证 品牌名称
        if (null != goodsBrand.getBrandNickname()) {
            LOGGER.info(ValueUtil.SEARCHBRANINFOBYID + "所查询的品牌名称为" + goodsBrand.getBrandNickname());
        }
        return goodsBrand;
    }

    /**
     * 更新商品实体
     *
     * @param pb
     *            分页
     * @param goodsBrand
     *            待更新的商品实体 {@link com.ningpai.goods.bean.GoodsBrand}
     * @return {@link org.springframework.web.servlet.ModelAndView;}
     */
    @RequestMapping("/updateBrand")
    public ModelAndView updateBrand(@Valid GoodsBrand goodsBrand, MultipartHttpServletRequest request, PageBean pb) {
        // 获取品牌LOGO的路径
        MultipartFile imageFile = null;
        // 新后台取picFile
        if (request.getFile(LOGOFILE) == null) {
            imageFile = request.getFile("picFile");
        } else {
            imageFile = request.getFile(LOGOFILE);
        }
        if (imageFile != null && !imageFile.isEmpty()) {
            goodsBrand.setBrandLogo(UploadUtil.uploadFile(imageFile, request).get("oldimg"));
        }
        // 更新商品品牌，修改人名称应该是当前登录用户的名称
        int result = this.goodsBrandService.updateGoodsBrad(goodsBrand, (String) request.getSession().getAttribute(ValueUtil.NAME));
        // 非空验证品牌名称 是否修改成功
        if (null != goodsBrand.getBrandNickname() && 1 == result) {
            // 操作日志
            OperaLogUtil.addOperaLog(request, (String) request.getSession().getAttribute(ValueUtil.NAME), "更新商品品牌", request.getSession().getAttribute(ValueUtil.OPERAPATH)
                    + LOGGERINFO1 + goodsBrand.getBrandName() + LOGGERINFO2 + request.getSession().getAttribute(ValueUtil.NAME));
            LOGGER.info(ValueUtil.UPDATEBRANDINFO + "更新" + goodsBrand.getBrandDelName() + "成功");
        }
        return new ModelAndView(new RedirectView(PathUtil.ALLBRANDCONTROLLER + "?pageNo=" + pb.getPageNo() + "&pageSize=" + pb.getPageSize()));
    }

    /**
     * 查询所有的商品品牌并转换为JSON返回
     *
     * @return 所有的品牌集合 {@link java.util.List}
     *         {@link com.ningpai.goods.bean.GoodsBrand}
     */
    @RequestMapping("/loadAllBrand")
    @ResponseBody
    public List<GoodsBrand> queryAllBrand() {
        LOGGER.info(ValueUtil.SEARCHBRANDLISTINFO);
        return this.goodsBrandService.queryAllBrand();
    }

    /**
     * 验证品牌名称名称是否已经存在
     *
     * @param brandName
     *            待验证的品牌名称 {@link String}
     * @return 可用返回true 不可用就返回false
     */
    @RequestMapping("/checkBrandName")
    @ResponseBody
    public boolean checkBrandName(String brandName) {
        // 非空验证 品牌名称
        if (null != brandName) {
            LOGGER.info(ValueUtil.CHECKBRANDINFO + ":判断" + brandName + "是否已经存在！");
        }
        return this.goodsBrandService.checkBrandName(brandName);
    }

    /**
     * 验证品牌名称名称是否已经存在
     *
     * @param brandName
     *            待验证的品牌名称 {@link String}
     * @return 可用返回true 不可用就返回false
     */
    @RequestMapping("/checkThirdBrandName")
    @ResponseBody
    public boolean checkThirdBrandName(String brandName) {
        String brandNameNew = brandName;
        LOGGER.info(ValueUtil.CHECKBRANDINFO);
        try {
            brandNameNew = java.net.URLDecoder.decode(brandNameNew, ConstantUtil.UTF);
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("转码失败！", e);
        }
        return this.goodsBrandService.checkBrandName(brandNameNew);
    }

    /**
     * 添加品牌不可以重复
     *
     * @param brandName
     *            待验证的品牌名称 {@link String}
     * @return 可用返回true 不可用就返回false
     */
    @RequestMapping("/selectByBrandName")
    @ResponseBody
    public int selectByBrandName(String brandName) {
        LOGGER.info(ValueUtil.CHECKBRANDINFO);
        // String name="";
        // try {
        // // name = new String(brandName.getBytes("ISO-8859-1"), "UTF-8");
        // } catch (UnsupportedEncodingException e) {
        // LOGGER.error("转码失败！", e);
        // }
        return this.goodsBrandService.selectByBrandName(brandName);
    }

    /**
     * 去添加商品品牌
     */
    @RequestMapping("/toAddBrand")
    public ModelAndView toAddBrand() {
        LOGGER.info(ValueUtil.ADDBRANDINFO);
        return new ModelAndView(ValueUtil.GOODSBRANADDINFO);
    }

    /**
     * @param pb
     *            去修改商品品牌
     */
    @RequestMapping("/toModifyBrand")
    public ModelAndView toModifyBrand(Long brandId, PageBean pb) {
        LOGGER.info(ValueUtil.MODIFYBRANDINFO);
        return new ModelAndView(ValueUtil.GOODSBRANDMODIFYINFO, "brandId", brandId).addObject("pb", pb);
    }

    /**
     * 导出商品品牌
     * 
     * @param response
     */
    @RequestMapping("exportGoodsBrand")
    public void exportGoodsCate(HttpServletResponse response) {
        goodsBrandService.exportGoodsBrand(response);
    }

    /**
     * 导出商品品牌模板
     * 
     * @param response
     */
    @RequestMapping("exportGoodsBrandTemp")
    public void exportGoodsCateTemp(HttpServletResponse response) {
        goodsBrandService.exportGoodsBrandTemp(response);
    }

    /**
     * 导入商品品牌
     * 
     * @param request
     *            请求
     * @param response
     *            响应对象
     * @param goodsCateId
     *            导入的分类ID
     */
    @RequestMapping("/importGoodsBrand")
    public void importGoodsCate(HttpServletRequest request, HttpServletResponse response, Long goodsCateId, MultipartHttpServletRequest multiRequest) {
        String result = "200";
        PrintWriter out = null;
        try {
            out = response.getWriter();
            result = this.goodsBrandService.importGoodsBrandByExcel(request, response, multiRequest);
            out.append("<script>parent.import_callback('" + result + "');</script>");
            out.flush();
        } catch (Exception e) {
            LOGGER.error("导入商品失败", e);
        }
    }

}
