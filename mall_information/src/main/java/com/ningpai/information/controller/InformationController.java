/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.information.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.ningpai.util.*;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.common.util.ConstantUtil;
import com.ningpai.information.bean.InfoUserDefined;
import com.ningpai.information.bean.Information;
import com.ningpai.information.bean.InformationType;
import com.ningpai.information.service.InfoUserDefinedService;
import com.ningpai.information.service.InformationService;
import com.ningpai.information.service.InformationTypeService;
import com.ningpai.information.vo.InformationTypeVo;
import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.publicpackage.InfoImageManagePublic;

/**
 * 资讯控制层
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年02月17日 18:55:38
 * @version
 */
@Controller
public class InformationController {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(InformationController.class);

    public static final String NAME = "name";

    public static final String OPERAPATH = "operaPath";

    private static final String QUERYINFOVOLIST_HTM = "queryInfoVoList.htm?CSRFToken=";
    private static final String LOGGERINFO1 = ",用户名:";
    private static final String LOGGERINFO2 = "==============================";

    /** 资讯SERVICE */
    @Resource(name = "InformationService")
    private InformationService infoService;
    /** 资讯类型SERVICE */
    @Resource(name = "InformationTypeService")
    private InformationTypeService infoTypeService;
    /** SERVICE-文章自定义属性 */
    @Resource(name = "InfoUserDefinedService")
    private InfoUserDefinedService infoUDService;
    /** 资讯图片管理接口 */
    @Resource(name = "InfoImageManagePublic")
    private InfoImageManagePublic infoImageManagePublic;

    /**
     * 查询资讯列表和分页数据
     * 
     * @param request
     * @param response
     * @param pb
     * @param selectBean
     * @param typeId
     * @param infoName
     * @return
     */
    @RequestMapping(value = "/queryInfoVoList")
    public ModelAndView queryInfoVoList(HttpServletRequest request, HttpServletResponse response, PageBean pb, SelectBean selectBean, Long typeId, String infoName) {
        MenuSession.sessionMenu(request);
        LOGGER.debug("==============================查询文章列表==============================");
        /** 判断查询文本是否为空 若为空 将条件也设置为空 */
        if (!"".equals(infoName) && infoName != null) {
            /** 不为空放入查询Bean */
            selectBean.setSearchText(infoName);
        } else {
            /** 查询条件为空时，则条件置空 */
            if ("".equals(selectBean.getSearchText())) {
                /** 标记置空 */
                selectBean.setCondition("");
            }
        }
        /** 参数设置到作用域中 */
        request.setAttribute("selectBean", selectBean);
        /** 获取可发布文章的资讯栏目 */
        List<InformationTypeVo> infoTypes = infoTypeService.selectInfoTypeByAttrForAddInfo();
        /** 自定义属性 */
        List<InfoUserDefined> infoUDs = infoUDService.findAllUserDefined();
        /** 返回结果 */
        return new ModelAndView("jsp/information/information", "pb", infoService.queryByPageBean(pb, selectBean, typeId)).addObject("infoTypes", infoTypes).addObject("infoUDs",
                infoUDs);
    }

    /**
     * 跳转到显示资讯<br/>
     * 资讯编号不为空时查询资讯并绑定到页面，修改资讯<br/>
     * 资讯编号为空时，添加资讯
     * 
     * @param request
     * @param response
     * @param infoId
     * @return ModelAndView
     */
    @RequestMapping("/showInformation")
    public ModelAndView showInfo(HttpServletRequest request, HttpServletResponse response, Long infoId) {
        /**
         * 判断是否是修改 判断id是否为空
         */
        if (infoId != null) {
            LOGGER.debug("==============================查询文章并跳转到查看或修改页面==============================");
            /** 根据id查询infomation */
            Information info = infoService.selectByPrimaryKey(infoId);
            /** info放在cookie中 */
            request.setAttribute("info", info);
            /** 获取自定义属性 */
            String userDefined = info.getUserDefined();
            /** 判断是否为空 */
            if (null != userDefined) {
                /** 分割数组 */
                request.setAttribute("userDefineds", userDefined.split(","));

            }
        }
        /** 查询所有文章自定义属性 */
        List<InfoUserDefined> infoUDs = infoUDService.findAllUserDefined();
        /** 获取可发布文章的资讯栏目 */
        List<InformationTypeVo> infoTypes = infoTypeService.selectInfoTypeByAttrForAddInfo();
        /** 返回结果 */
        return new ModelAndView("jsp/information/showInfo", "infoTypes", infoTypes).addObject("infoUDs", infoUDs);
    }

    /**
     * 跳转到显示资讯<br/>
     * 资讯编号不为空时查询资讯并绑定到页面，修改资讯<br/>
     * 资讯编号为空时，添加资讯
     * 
     * @param request
     * @param response
     * @param infoId
     * @return ModelAndView
     */
    @RequestMapping("/showInformationajax")
    @ResponseBody
    public Information showInfoajax(HttpServletRequest request, HttpServletResponse response, Long infoId) {
        /** 判断是否是修改 */
        Information info = null;
        /** 判断id是否为空 */
        if (infoId != null) {
            LOGGER.debug("==============================查询文章并跳转到查看或修改页面==============================");
            /** 根据id查询infomation */
            info = infoService.selectByPrimaryKey(infoId);
        }
        /** 返回结果 */
        return info;
    }

    /**
     * 添加资讯
     * 
     * @param request
     * @param response
     * @param information
     * @param bindingResult
     * @return ModelAndView
     */
    @RequestMapping("/addInformation")
    public ModelAndView addInfo(MultipartHttpServletRequest request, HttpServletResponse response, @Valid Information information, BindingResult bindingResult) {
        /** 如果验证不通过则重定向到查询资讯列表和分页数据 */
        if (bindingResult.hasErrors()) {
            return new ModelAndView(new RedirectView(QUERYINFOVOLIST_HTM + request.getParameter(ConstantUtil.CSRFTOKEN)));
        }
        LOGGER.debug("==============================添加文章==============================");
        try {
            /** 获取imageSrc的文件流 */
            MultipartFile muFile = request.getFile("imageSrc");
            /** 如果MuFile不为空 */
            if (muFile != null && muFile.getSize() > 0) {
                /** 如果Mufile的size大于0 */
                    /** 设置imgSrc的值 */
                    information.setImgSrc(UploadUtil.uploadFileOne(muFile, request));
                    /** 保存图片信息，以供以后选择 */
                    infoImageManagePublic.saveImage(information.getImgSrc());
            }
            /** 设置文章类型：普通后台文章、移动版文章 */
            InformationType currType = infoTypeService.selectByPrimaryKey(information.getInfoTypeId());
            /**
             * 如果文章类型不等于2就设置为0 否则就设置为1
             */
            if (!"2".equals(currType.getTemp3())) {
                information.setTemp3("0");
            } else {
                information.setTemp3("1");
            }
            /** 设置创建人Id为1 */
            information.setCreateUserId(1L);
            /** 执行保存方法　 */
            infoService.saveInfo(information);
            /** 获取存储在session中的用户名称 */
            String customerName = (String) request.getSession().getAttribute(NAME);
            /** 记录日志 */
            OperaLogUtil.addOperaLog(request, customerName, "添加文章", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
        } catch (Exception e) {
            LOGGER.error("==============================添加文章失败：",e);
        }
        /** 返回结果 */
        return new ModelAndView(new RedirectView(QUERYINFOVOLIST_HTM + request.getParameter(ConstantUtil.CSRFTOKEN)));
    }

    /**
     * 修改资讯
     * 
     * @param request
     * @param response
     * @param information
     * @param bindingResult
     * @return ModelAndView
     */
    @RequestMapping("/updateInformation")
    public ModelAndView updateInfo(MultipartHttpServletRequest request, HttpServletResponse response, @Valid Information information, BindingResult bindingResult) {
        /** 如果验证不通过则重定向到查询资讯列表和分页数据 */
        if (bindingResult.hasErrors()) {
            return new ModelAndView(new RedirectView(QUERYINFOVOLIST_HTM + request.getParameter(ConstantUtil.CSRFTOKEN)));
        }
        LOGGER.debug("==============================修改文章==============================");
        try {
            /** 根据名称获取文件流 */
            MultipartFile muFile = request.getFile("imageSrc");
            /** 判断获得的文件流是否为空 */
            if (muFile != null && muFile.getSize() > 0) {
                /** 判断获取的文件流的size是否大于0 */
                    /** 设置图片路径 */
                    information.setImgSrc(UploadUtil.uploadFileOne(muFile, request));
                    /** 保存图片信息，以供以后选择 */
                    infoImageManagePublic.saveImage(information.getImgSrc());
            }
            /** 设置文章类型：普通后台文章、移动版文章 */
            InformationType currType = infoTypeService.selectByPrimaryKey(information.getInfoTypeId());
            /**
             * 如果文章类型不等于2就设置为0 否则就设置为1
             */
            if (!"2".equals(currType.getTemp3())) {
                information.setTemp3("0");
            } else {
                information.setTemp3("1");
            }
            /** 设置创建人Id为1 */
            information.setUpdateUserId(1L);
            /** 执行修改方法 */
            infoService.updateInfo(information);
            /** 获取存储在session中的用户名称 */
            String customerName = (String) request.getSession().getAttribute(NAME);
            /** 记录更新日志 */
            OperaLogUtil.addOperaLog(request, customerName, "修改文章", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
        } catch (Exception e) {
            LOGGER.error("==============================修改文章失败：" ,e);
        }
        /** 返回结果 */
        return new ModelAndView(new RedirectView(QUERYINFOVOLIST_HTM + request.getParameter(ConstantUtil.CSRFTOKEN)));
    }

    /**
     * 删除资讯
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/delInformation")
    public void delInfo(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.debug("==============================删除文章==============================");
        /** 获取id集合 */
        String[] ids = request.getParameterValues("infoIds[]");
        try {
            /** 循环获取id */
            for (int i = 0; i < ids.length; i++) {
                Long id = Long.valueOf(ids[i]);
                /** 根据id删除文章 */
                infoService.delInfo(id);
            }
            /** 获取登陆的用户名 */
            String customerName = (String) request.getSession().getAttribute(NAME);
            /** 记录操作 */
            OperaLogUtil.addOperaLog(request, customerName, "删除文章", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
        } catch (NumberFormatException e) {
            LOGGER.error("==============================删除文章失败：" ,e);
        }
    }

    /**
     * 验证文章标题是否存在
     * 
     * @param title
     *            待验证的文章标题
     * @return 验证的结果
     */
    @RequestMapping("/checkInformationByTitle")
    @ResponseBody
    public boolean checkInfoByTitle(String title, Long infoId) {
        LOGGER.debug("==============================验证文章是否存在==============================");
        /** 判断id是否为空 */
        if (null != infoId) {
            /** 根据id标题查询 **/
            return this.infoService.checkAddInfoByTitle(title, infoId);
        } else {
            /** 根据文章标题查询文章数量判断标题是否存在 */
            return this.infoService.checkAddInfoByTitle(title);
        }
    }

}
