/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.controller;

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
import com.ningpai.goods.bean.GoodsTag;
import com.ningpai.goods.service.GoodsTagService;
import com.ningpai.goods.util.PathUtil;
import com.ningpai.goods.util.SelectBean;
import com.ningpai.goods.util.ValueUtil;
import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;
import com.ningpai.util.UploadUtil;

/**
 * 商品标签控制器
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月14日 上午11:21:19
 * @version 1.0
 */
@Controller
public class GoodsTagController {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(GoodsTagController.class);

    private static final String LOGGERINFO1 = "-->标签名称【";
    private static final String LOGGERINFO2 = "】,用户名：";

    // 注入标签数据层实现类
    @Resource(name = "GoodsTagServiceImpl")
    private GoodsTagService goodsTagService;

    public GoodsTagService getGoodsTagService() {
        return goodsTagService;
    }

    public void setGoodsTagService(GoodsTagService goodsTagService) {
        this.goodsTagService = goodsTagService;
    }

    /**
     * 根据标签ID查询标签
     * 
     * @param tagId
     *            标签ID
     * @return
     */
    @RequestMapping("/findGoodsTag")
    public ModelAndView findGoodsTag(Long tagId) {
        // 根据主键获取单个商品标签对象
        GoodsTag goodsTag = goodsTagService.selectByPaimarykey(tagId);
        // 非空验证 规格名称
        if (null != goodsTag.getTagName()) {
            // 打印日志
            LOGGER.info(ValueUtil.FINDGOODSTAGINFO + ",标签名称为:" + goodsTag.getTagName());
        }
        // 返回视图 并把查询到的标签放在视图中
        // 查询完成后返回到视图
        return new ModelAndView(PathUtil.GOODSTAG, "tag", goodsTag);
    }

    /**
     * 查询所有的标签列表
     * 
     * @return
     */
    @RequestMapping("/findAllTag")
    public ModelAndView findAllTag(HttpServletRequest request, HttpServletResponse response, PageBean pb, SelectBean selectBean) {
        // 打印日志
        LOGGER.info(ValueUtil.FINDALLTAGINFO);
        // 参数设置到作用域中
        request.setAttribute("selectBean", selectBean);
        // 把查询到的标签列表放在视图中
        return new ModelAndView(PathUtil.GOODSTAG, "pb", goodsTagService.selectAllTag(pb, selectBean));
    }

    /**
     * 删除标签
     * 
     * @param tagId
     *            待删除的标签ID {@link java.lang.Long}
     * @return
     */
    @RequestMapping("/deltag")
    public ModelAndView deltag(Long tagId, HttpServletRequest request) {
        // 根据标签ID查询单个的标签对象
        GoodsTag goodsTag = goodsTagService.selectByPaimarykey(tagId);
        // 传递需要删除的标签ID以及删除的用户名称
        this.goodsTagService.deleteByPrimaryKey(tagId, (String) request.getSession().getAttribute(ValueUtil.NAME));
        OperaLogUtil.addOperaLog(
                request,
                (String) request.getSession().getAttribute(ValueUtil.NAME),
                ValueUtil.DELTAGINFO,
                (String) request.getSession().getAttribute(ValueUtil.OPERAPATH) + LOGGERINFO1 + goodsTag.getTagName() + LOGGERINFO2
                        + (String) request.getSession().getAttribute(ValueUtil.NAME));
        // 重定向到查询标签列表控制器
        return new ModelAndView(new RedirectView(PathUtil.ALLTAGCONTROLLER + ValueUtil.TOKENPARAM + CSRFTokenManager.getTokenFromRequest(request)));
    }

    /**
     * 添加标签
     * 
     * @param record
     *            从页面传过来的标签实体 {@link com.ningpai.goods.bean.GoodsTag}
     * @return
     */
    @RequestMapping("/addTag")
    public ModelAndView addTag(@Valid GoodsTag record, HttpServletRequest request, MultipartHttpServletRequest request2) {
        // 获取页面中name为tagImage的资源文件
        MultipartFile imageFile = request2.getFile("tagImage");
        // 判断资源文件是否为null
        // 如果不为null就获取name为picFile的资源文件并赋值给imageFIle
        if (imageFile == null) {
            imageFile = request2.getFile("picFile");
        }
        // 判断资源文件是否为空
        // 不为空就设置标签图片
        if (!imageFile.isEmpty()) {
            record.setTagImg(UploadUtil.uploadFile(imageFile, request).get("oldimg"));
        }
        // 保存商品标签
        this.goodsTagService.insertSelective(record);
        // 记录当前登录者的操作记录
        OperaLogUtil.addOperaLog(
                request,
                (String) request.getSession().getAttribute(ValueUtil.NAME),
                ValueUtil.ADDTAGINFO,
                (String) request.getSession().getAttribute(ValueUtil.OPERAPATH) + LOGGERINFO1 + record.getTagName() + LOGGERINFO2
                        + (String) request.getSession().getAttribute(ValueUtil.NAME));
        // 重定向到查询标签列表控制器
        return new ModelAndView(new RedirectView(PathUtil.ALLTAGCONTROLLER + ValueUtil.TOKENPARAM + CSRFTokenManager.getTokenFromRequest(request)));
    }

    /**
     * 批量删除的控制器
     * 
     * @param tagCheck
     *            从页面传过来的标签ID的列表 {@link java.util.Map}
     * @return
     */
    @RequestMapping("/batchDelTag")
    public ModelAndView batchDelTag(Long[] tagCheck, HttpServletRequest request) {
        LOGGER.info(ValueUtil.BATCHDELTAGINFO);
        // 批量删除 传递需要删除的标签ID的数组以及删除人名称
        this.goodsTagService.batchDeleteTag(tagCheck, (String) request.getSession().getAttribute(ValueUtil.NAME));
        // 记录当前登录者的操作日志
        OperaLogUtil.addOperaLog(request, (String) request.getSession().getAttribute(ValueUtil.NAME), ValueUtil.BATCHDELTAGINFO,
                (String) request.getSession().getAttribute(ValueUtil.OPERAPATH) + ",用户名：" + (String) request.getSession().getAttribute(ValueUtil.NAME));
        // 重定向到查询标签列表控制器
        return new ModelAndView(new RedirectView(PathUtil.ALLTAGCONTROLLER + ValueUtil.TOKENPARAM + CSRFTokenManager.getTokenFromRequest(request)));
    }

    /**
     * 根据参数进行模糊查询 {@link java.util.Map}
     * 
     * @param columnName
     *            参数的列名
     * @param value
     *            参数值
     * @return
     */
    @RequestMapping("/queryTagByParam")
    public ModelAndView queryTagByParam(String columnName, String paramvalue) {
        if (null != columnName && null != paramvalue) {
            // 打印日志
            LOGGER.info(ValueUtil.QUERYTAGBYPARAMINFO + ",参数 列名：" + columnName + ",参数的值为:" + paramvalue);
        } else {
            // 打印日志
            LOGGER.info(ValueUtil.QUERYTAGBYPARAMINFO);
        }
        // 返回结果
        return new ModelAndView(PathUtil.GOODSTAG, "tagList", goodsTagService.queryTagByParam(columnName, paramvalue));
    }

    /**
     * 更新商品标签
     * 
     * @param tag
     *            {@link com.ningpai.goods.bean.GoodsTag}
     * @return
     */
    @RequestMapping("/updateTag")
    public ModelAndView updateTag(@Valid GoodsTag tag, HttpServletRequest request, MultipartHttpServletRequest request2) {
        // 根据主键获取单个的商品标签对象
        GoodsTag goodsTag = goodsTagService.selectByPaimarykey(tag.getTagId());
        // 获取页面中name为tagImage的资源文件并赋值给imageFIle
        MultipartFile imageFile = request2.getFile("tagImage");
        // 判断imageFile是否为null
        // 不为null就获取页面中name为picFile的值并赋值给imageFIle
        if (imageFile == null) {
            imageFile = request2.getFile("picFile");
        }
        // 判断imgeFile是否为空
        // 不为空就进行上传并赋值
        if (!imageFile.isEmpty()) {
            tag.setTagImg(UploadUtil.uploadFile(imageFile, request).get("oldimg"));
        }
        // 修改标签方法
        this.goodsTagService.updateTagSelective(tag, (String) request.getSession().getAttribute(ValueUtil.NAME));
        // 记录当前登录者的操作日志
        OperaLogUtil.addOperaLog(
                request,
                (String) request.getSession().getAttribute(ValueUtil.NAME),
                ValueUtil.UPDATETAGINFO,
                (String) request.getSession().getAttribute(ValueUtil.OPERAPATH) + LOGGERINFO1 + goodsTag.getTagName() + LOGGERINFO2
                        + (String) request.getSession().getAttribute(ValueUtil.NAME));
        // 返回结果
        return new ModelAndView(new RedirectView(PathUtil.ALLTAGCONTROLLER + ValueUtil.TOKENPARAM + CSRFTokenManager.getTokenFromRequest(request)));
    }

    /**
     * 验证标签名称是否可用
     * 
     * @param tagName
     *            待验证的标签名称
     * @return 可用返回true 不可用返回false
     */
    @RequestMapping("/checkTagName")
    @ResponseBody
    public boolean checkTagName(String tagName) {
        // 判断标签名称
        if (null != tagName) {
            // 打印日志
            LOGGER.info(ValueUtil.CHECKTAGNAMEINFO + "标签名称为：" + tagName);
        } else {
            // 打印日志
            LOGGER.info(ValueUtil.CHECKTAGNAMEINFO);
        }
        // 返回结果
        return this.goodsTagService.checkTagName(tagName);
    }

}
