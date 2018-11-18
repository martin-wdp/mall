/**
 * Copyright 2014 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.adv.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.ningpai.adv.bean.Adv;
import com.ningpai.adv.service.AdvService;
import com.ningpai.logcore.util.OperaLogUtil;
import com.ningpai.util.PageBean;
import com.ningpai.util.UploadUtil;
import org.springframework.web.servlet.view.RedirectView;

/**
 * @Description: 广告controller
 * @author zhangyue
 * @date 2014年8月15日
 * @version V1.0
 */
@Controller
public class AdvController {
    /**
     * 列表页
     */
    private static String page_list = "jsp/adv/adv_list";
    /**
     * 编辑页
     */
    private static String page_edit = "jsp/adv/adv_edit";

    // private static String select_url = "/selectbbadvlist.htm";

    /** AdvService */
    @Resource(name = "AdvService")
    private AdvService advService;

    /** 记录日志对象 */
    private static Logger logger;

    /** 广告名称 */
    private static final String NAME = "name";

    /** 操作路径 */
    public static final String OPERAPATH = "operaPath";

    /**
     * 查询广告列表
     * 
     * @param pageBean
     *            {@link com.ningpai.util.PageBean}
     * @return 广告列表页面
     */
    @RequestMapping("/selectbbadvlist")
    public ModelAndView initMethod(PageBean pageBean, Adv adv) {
        // 设置跳转页面
        pageBean.setUrl("selectBbadvList");
        // 查询广告列表
        return new ModelAndView(page_list, "pageBean", advService.selectPageList(pageBean, adv));
    }

    /**
     * 添加或编辑
     * 
     * @param advId
     *            广告ID
     * @return ModelAndView 广告添加或编辑页面
     */
    @RequestMapping("/toeditbbadv")
    public ModelAndView toEdit(Long advId) {
        ModelAndView mav = new ModelAndView(page_edit);
        if (advId != null) {
            // 查询广告详细
            mav.addObject("adv", advService.select(advId));
        }
        return mav;
    }

    /**
     * 新boss添加或编辑
     * 
     * @param advId
     *            广告ID
     * @return ModelAndView 广告添加或编辑页面
     */
    @RequestMapping("/toeditbbadvNew")
    @ResponseBody
    public Adv toEditNew(Long advId) {
        Adv adv = new Adv();
        if (advId != null) {
            // 查询广告详细
            adv = advService.select(advId);
        }
        return adv;
    }

    /**
     * 后台添加广告图片
     * 
     * @param adv
     *            广告参数
     * @return ModelAndView 广告列表
     */
    @RequestMapping("/addbbadv")
    public ModelAndView add(Adv adv, HttpServletRequest request, MultipartHttpServletRequest partRequest) {
        // 获取图片域
        MultipartFile file = partRequest.getFile("advImgInput");
        // 上传图片
        if (file != null && file.getSize() > 0) {
            adv.setAdvImg(UploadUtil.uploadFileOne(file, request));
        }
        // 插入图片
        if (advService.insert(adv) == 1) {
            // 写入日志
            logger.debug("添加广告成功");
            OperaLogUtil.addOperaLog(request, (String) request.getSession().getAttribute(NAME), "添加广告", request.getSession().getAttribute(OPERAPATH) + "添加广告id:" + adv.getAdvId());
        } else {
            logger.debug("添加广告失败");
        }
        return new ModelAndView(new RedirectView("selectbbadvlist.htm?CSRFToken=" + request.getParameter("CSRFToken")));
    }

    /**
     * 修改广告
     * 
     * @param adv
     *            广告参数
     * @param request
     *            HttpServletRequest
     * @param partRequest
     *            MultipartHttpServletRequest
     * @return ModelAndView 广告列表页面
     */
    @RequestMapping("/updatebbadv")
    public ModelAndView update(Adv adv, HttpServletRequest request, MultipartHttpServletRequest partRequest) {
        // 获取图片
        MultipartFile file = partRequest.getFile("advImgInput");
        if (file != null && file.getSize() > 0) {
            // 上传图片
            adv.setAdvImg(UploadUtil.uploadFileOne(file, request));
        }
        // 修改广告
        int flag = advService.update(adv);
        if (flag == 1) {
            if (null != adv.getAdvName()) {
                logger.debug("修改广告成功，广告名称为：" + adv.getAdvName());
            }
            // 插入日志
            OperaLogUtil.addOperaLog(request, (String) request.getSession().getAttribute(NAME), "修改广告", request.getSession().getAttribute(OPERAPATH) + "修改广告" + adv.getAdvId());
        } else {
            if (null != adv.getAdvName()) {
                logger.debug("修改广告失败，广告名称为：" + adv.getAdvName());
            }
        }
        return new ModelAndView(new RedirectView("selectbbadvlist.htm?CSRFToken=" + request.getParameter("CSRFToken")));
    }

    /**
     * 批量删除
     * 
     * @param advIds
     *            广告ID
     * @param request
     *            HttpServletRequest
     * @return ModelAndView 广告列表页面
     */
    @RequestMapping("/deleteMuiltiBbadv")
    @ResponseBody
    public int delete(@RequestParam("advIds[]") Long[] advIds, HttpServletRequest request) {
        // 批量删除广告
        int flag = advService.deleteAll(advIds);
        if (flag > 0) {
            logger.debug("删除广告" + advIds.length + "条记录成功");
            // 插入日志
            OperaLogUtil.addOperaLog(request, (String) request.getSession().getAttribute(NAME), "删除广告",
                    request.getSession().getAttribute(OPERAPATH) + "删除广告" + advIds[0].toString() + "条记录");
        } else {
            logger.debug("删除广告失败");
        }
        return flag;
    }

    /**
     * 通过位置flag查询广告列表
     * 
     * @param flag
     *            位置flag
     * @return adv 广告列表
     */
    @RequestMapping("/selectadvlist")
    @ResponseBody
    public List<Adv> selectAdvListByPosition(@RequestParam String flag) {
        // 查询广告列表 通过类型
        return advService.selectAdvListByPosition(flag);
    }

    static {
        logger = Logger.getLogger(AdvController.class);
    }
}
