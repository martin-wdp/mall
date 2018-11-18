/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ningpai.goods.service.GoodsElasticSearchService;
import com.ningpai.goods.service.GoodsService;
import com.ningpai.util.PageBean;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.common.safe.CSRFTokenManager;
import com.ningpai.goods.bean.GoodsImage;
import com.ningpai.goods.service.GoodsImageService;
import com.ningpai.goods.util.PathUtil;
import com.ningpai.goods.util.ValueUtil;
import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.util.MyLogger;
import com.ningpai.util.UploadUtil;

/**
 * 货品图片控制器
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年1月3日 下午5:11:38
 * @version 1.0
 */
@Controller
public class GoodsImageController {
    // 商品图片Service
    private GoodsImageService goodsImageService;
    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(
            GoodsImageController.class);
    // 商品的Service
    private GoodsService goodsService;

    //商品索引
    @Resource(name="GoodsElasticSearchService")
    private GoodsElasticSearchService goodsElasticSearchService;
    
    public GoodsService getGoodsService() {
        return goodsService;
    }

    @Resource(name = "GoodsService")
    public void setGoodsService(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    public GoodsImageService getGoodsImageService() {
        return goodsImageService;
    }

    @Resource(name = "GoodsImageService")
    public void setGoodsImageService(GoodsImageService goodsImageService) {
        this.goodsImageService = goodsImageService;
    }

    /**
     * 根据货品ID查询关联的图片列表
     * 
     * @param productId
     *            货品列表{@link java.lang.Long}
     * @return 查询到的列表
     */
    @RequestMapping("/queryImageListByProductId")
    @ResponseBody
    public List<GoodsImage> queryByProductId(Long productId) {
        // 根据ID获取单个的商品信息
        // GoodsMoifiedVo goodsMoifiedVo =
        // this.goodsService.queryModeifiedVoByGoodsId(productId);
        // 非空验证 商品名称
        // if(null != goodsMoifiedVo.getGoodsName()){
        // LOGGER.info("获取货品【"+goodsMoifiedVo.getGoodsName()+"】,所关联的图片列表");
        // }
        // 返回结果
        return this.goodsImageService.queryImageListByProductId(productId);
    }

    /**
     * 更新货品图片
     * 
     * @param productId
     *            货品ID {@link java.lang.Long}
     * @param goodsId
     *            商品ID {@link java.lang.Long}
     * @param request
     *            会话
     * @param request2
     *            包含上传文件的会话
     * @return 视图
     */
    @RequestMapping("/updateProductImage")
    public ModelAndView updateProductImage(Long productId, Long goodsId,
            Integer flag, HttpServletRequest request,
            HttpServletResponse response, MultipartHttpServletRequest request2,
            Long goodsImgId, PageBean pb) {
        // 设置默认图片
        String defaultImage = request.getParameter("defaultImage");
        // 根据货品id和图片id调用设置默认图片方法
        goodsImageService.setDefaultImage(productId, goodsImgId);
        // 获取删除的图片的ID的数组
        String[] delIamges = request.getParameterValues("delImages");
        // 删除
        LOGGER.info(ValueUtil.UPDATEPRODUCTIMAGEINFO);
        // 传入参数执行批量删除方法
        this.goodsImageService.batchDelImage(delIamges, (String) request
                .getSession().getAttribute(ValueUtil.NAME));
        // 设置默认图片
        this.goodsImageService.setDefaultImage(productId, defaultImage,
                (String) request.getSession().getAttribute(ValueUtil.NAME));
        // 获取所有的File控件
        List<MultipartFile> fileList = request2.getFiles("filedata");
        // 到Service进行添加
        this.goodsImageService.uploadImage(productId, (String) request
                .getSession().getAttribute(ValueUtil.NAME), fileList, request);
        //更新索引
        goodsElasticSearchService.updateOneGoodsIndexToEs(goodsId);
        
        if (null == flag && null != goodsId) {
            // 返回到货品的列表
            OperaLogUtil.addOperaLog(
                    request,
                    (String) request.getSession().getAttribute(ValueUtil.NAME),
                    ValueUtil.UPDATEPRODUCTIMAGEINFO,
                    (String) request.getSession().getAttribute(
                            ValueUtil.OPERAPATH)
                            + ",用户名："
                            + (String) request.getSession().getAttribute(
                                    ValueUtil.NAME));
            // 返回结果
            return new ModelAndView(new RedirectView(
                    PathUtil.QUERYPRODUCTBYGOODSID + goodsId
                            + ValueUtil.TOKENPARAM2
                            + CSRFTokenManager.getTokenFromRequest(request)
                            + ValueUtil.PAGENO + pb.getPageNo()
                            + ValueUtil.PAGESIZE + pb.getPageSize()));
        } else {
            // 返回结果
            return new ModelAndView(new RedirectView(
                    PathUtil.QUERYPRODUCTLISTBYSOMEPARAM + flag
                            + ValueUtil.TOKENPARAM2
                            + CSRFTokenManager.getTokenFromRequest(request)));
        }
    }

    /**
     * // * 批量上传货品图片 // * // * @param productId // * 货品ID {@link java.lang.Long}
     * // * @param goodsId // * 商品ID {@link java.lang.Long}
     * // * @param request // * 会话 // * @param request2 // * 包含上传文件的会话 // * @return
     * 视图 //
     */
    // @RequestMapping("/batchSaveProductImage")
    // @ResponseBody
    // public GoodsImage batchSaveProductImage(Long productId,
    // HttpServletRequest request, MultipartHttpServletRequest request2) {
    // // 获取所有的File控件
    // List<MultipartFile> fileList = request2.getFiles("filedata");
    // LOGGER.info(ValueUtil.BATCHSAVEPRODUCTIMAGEINFO);
    // OperaLogUtil.addOperaLog(request, (String)
    // request.getSession().getAttribute(ValueUtil.NAME),ValueUtil.BATCHSAVEPRODUCTIMAGEINFO,
    // (String)
    // request.getSession().getAttribute(ValueUtil.OPERAPATH)+",用户名："+(String)
    // request.getSession().getAttribute(ValueUtil.NAME));
    // // 到Service进行添加
    // return this.goodsImageService.uploadImage(productId, (String)
    // request.getSession().getAttribute(ValueUtil.NAME),
    // fileList, request);
    // }

    /**
     * 上传单张图片 保存原图
     * 
     * @param request
     *            请求对象
     * @param resp
     *            响应对象
     * @throws IOException
     */
    @RequestMapping("/uploadImgSingle")
    public void uploadImgSingle(MultipartHttpServletRequest request,
            HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        // 定义一个字符串变量初始值为Null
        String msg = null;
        // 获取页面中name为specImg的file文件流
        MultipartFile file = request.getFile("specImg");
        // 获取资源文件的名称
        file.getOriginalFilename();
        // 判断资源文件是否属于图片资源
        // 如果不是msg就赋值为111
        // 如果是就上传资源文件
        if (!checkExtendsName(file.getOriginalFilename())) {
            msg = "111";
        } else {
            msg = UploadUtil.uploadFile(file, request).get("0");
            msg += "-" + request.getParameter("specValId").toString();
        }
        // 调用页面的js方法
        out.append("<script>parent.specImgSucc('" + msg + "');</script>");
    }

    /**
     * 单张上传货品图片
     */
    @RequestMapping("/uploadProductImageSingle")
    public void uploadProductImage(MultipartHttpServletRequest request,
            HttpServletResponse resp, HttpServletRequest req)
            throws IOException {
        PrintWriter out = resp.getWriter();
        // 定义一个字符串变量并赋初始值为Null
        String msg = null;
        GoodsImage image = null;
        // 获取页面中name 为prouctImage的资源文件
        MultipartFile file = request.getFile("prouctImage");
        try {
            // 获取资源文件的后缀名
            file.getOriginalFilename();
            // 判断获取的资源文件是否是图片文件
            // 如果不是就把msg赋值为111
            // 如果是就执行上传图片操作
            if (!checkExtendsName(file.getOriginalFilename())) {
                msg = "111";
            } else {
                image = this.goodsImageService.uploadImageSingle(
                        0L,
                        (String) request.getSession().getAttribute(
                                ValueUtil.NAME), file, req);
                msg = image.getGoodsImgId().toString();
                msg += "-" + image.getImageThumName();
            }
            // 调用页面的js方法
            out.append("<script>parent.productImgSucc('" + msg + "');</script>");
        } finally {
            out.flush();
            msg = null;
            image = null;
            file = null;
        }
    }

    /**
     * 批量保存货品图片
     * */
    @RequestMapping("/batchSaveProductImage")
    @ResponseBody
    public void batchSaveProImageForChoose(HttpServletResponse response,
            String image, int productId, HttpServletRequest request)
            throws IOException {
        PrintWriter out = response.getWriter();
        // 判断传入的image是否为NUll
        if (null != image && !"".equals(image)) {
            // 定义一个字符串类型的数组
            String[] images = null;
            try {
                if (image.indexOf(",") != -1) {
                    images = image.split(",");
                } else {
                    images = new String[1];
                    images[0] = image;
                }
                // 执行上传方法
                this.goodsImageService.batchSaveImage(
                        images,
                        productId,
                        (String) request.getSession().getAttribute(
                                ValueUtil.NAME));
                out.print("1");
            } finally {
                images = null;
            }
        }
    }

    /**
     * 检查文件扩展名是否为图片
     * 
     * @param fileName
     *            上传的文件的文件名
     * @return
     */
    private boolean checkExtendsName(String fileName) {
        if (fileName.indexOf(".") < 0) {
            return false;
        }
        String extend = fileName.substring(fileName.lastIndexOf(".") + 1);
        String[] extendNames = { "jpg", "jpeg", "bmp", "png", "gif" };
        for (String extendName : extendNames) {
            if (extend.equals(extendName)) {
                return true;
            }
        }
        return false;
    }

}
