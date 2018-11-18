/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.information.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.common.util.ConstantUtil;
import com.ningpai.information.bean.InformationType;
import com.ningpai.information.service.InformationService;
import com.ningpai.information.service.InformationTypeService;
import com.ningpai.information.vo.InformationTypeVo;
import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.util.MenuSession;
import com.ningpai.util.PageBean;

/**
 * 资讯类型控制层
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年02月15日10:55:38
 * @version
 */
@Controller
public class InformationTypeController {

    /** 资讯类型SERVICE */
    private InformationTypeService infoTypeService;

    /** 资讯SERVICE */
    private InformationService infoService;

    private static final String JUMPURL = "jumpForInfoTypeView.htm";

    private static final String LOGINUSERID = "loginUserId";

    /**
     * 用户名称
     */
    public static final String NAME = "name";

    /**
     * "operaPath"
     */
    public static final String OPERAPATH = "operaPath";

    public static final String LOGGERINFO1 = ",用户名:";

    public static final String CSRFToken = "?CSRFToken=";

    /**
     * 删除InformationType
     * 
     * @param infoTypeId
     *            待删除的infoTypeId
     */
    @RequestMapping("/delInfoType")
    public ModelAndView delInfoType(HttpServletRequest request, Long infoTypeId) {
        /** 获取session中存储的登录Id */
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        /**
         * 判断登录id是否为null 为null就设置为1
         */
        if (null == loginUserId) {
            loginUserId = 1L;
        }
        /** 执行删除方法 */
        this.infoTypeService.delInformation(infoTypeId, loginUserId);
        /** 获取存储在session中的用户名称 */
        String customerName = (String) request.getSession().getAttribute(NAME);
        /** 记录更新日志 */
        OperaLogUtil.addOperaLog(request, customerName, "删除文章栏目", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
        /** 返回结果 */
        return new ModelAndView(new RedirectView(JUMPURL + CSRFToken + request.getParameter(ConstantUtil.CSRFTOKEN)));
    }

    /**
     * 删除InformationType
     * 
     * @param infoTypeId
     *            待删除的infoTypeId
     */
    @RequestMapping("/delInfoTypePro")
    public ModelAndView delInfoTypePro(HttpServletRequest request, Long infoTypeId) {
        /** 调用删除方法 */
        this.infoTypeService.delInformationPro(infoTypeId);
        /** 获取存储在session 中的用户名称 */
        String customerName = (String) request.getSession().getAttribute(NAME);
        /** 记录操作日志 */
        OperaLogUtil.addOperaLog(request, customerName, "删除文章栏目", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
        /** 返回结果 */
        return new ModelAndView(new RedirectView(JUMPURL + CSRFToken + request.getParameter(ConstantUtil.CSRFTOKEN)));
    }

    /**
     * 批量删除InformationType
     * 
     * @param infoTypeIds
     *            待删除的infoTypeId数组
     */
    @RequestMapping("/batchDelInfoType")
    public ModelAndView batchDelInfoType(HttpServletRequest request, Long[] infoTypeIds) {
        /** 获取存储在session中的登录id */
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        /**
         * 判断登录id是否为null 为null就设置为1
         */
        if (null == loginUserId) {
            loginUserId = 1L;
        }
        /** 执行删除方法 */
        this.infoTypeService.batchDelInformation(infoTypeIds, loginUserId);
        /** 获取存储在session中的用户名称 */
        String customerName = (String) request.getSession().getAttribute(NAME);
        /** 记录操作日志 */
        OperaLogUtil.addOperaLog(request, customerName, "批量删除文章栏目", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
        /** 返回结果 */
        return new ModelAndView(new RedirectView(JUMPURL + CSRFToken + request.getParameter(ConstantUtil.CSRFTOKEN)));
    }

    /**
     * 跳转到显示资讯栏目<br/>
     * 栏目编号不为空时查询栏目并绑定到页面，修改栏目<br/>
     * 栏目编号为空时，添加栏目
     * 
     * @param request
     * @param response
     * @param infoTypeId
     * @return ModelAndView
     */
    @RequestMapping("/showInfoType")
    public ModelAndView showInfo(HttpServletRequest request, HttpServletResponse response, Long infoTypeId) {
        /** 创建一个资讯类型对象 */
        InformationType infoType = new InformationType();
        if (null != infoTypeId) {
            /** 根据主键查找资讯类型 */
            infoType = infoTypeService.selectByPrimaryKey(infoTypeId);
        }
        /** 获取可发布文章的资讯栏目 */
        List<InformationTypeVo> infoTypes = infoTypeService.selectAll();
        /** 返回结果 */
        return new ModelAndView("jsp/information/showInfoType", "infoTypes", infoTypes).addObject("infoType", infoType);
    }

    /**
     * 跳转到显示资讯栏目<br/>
     * 栏目编号不为空时查询栏目并绑定到页面，修改栏目<br/>
     * 栏目编号为空时，添加栏目
     * 
     * @param infoTypeId
     * @return
     */
    @RequestMapping("/showInfoTypeajax")
    @ResponseBody
    public InformationType showInfoajax(Long infoTypeId) {
        /** 创建一个资讯类型对象 */
        InformationType infoType = new InformationType();
        if (null != infoTypeId) {
            /** 根据主键查找资讯类型 */
            infoType = infoTypeService.selectByPrimaryKey(infoTypeId);
        }
        /** 返回结果 */
        return infoType;
    }

    /**
     * 增加InformationType
     * 
     * @param infotype
     *            待增加的InformationType实体
     */
    @RequestMapping("/saveInfoType")
    public ModelAndView saveInfoType(HttpServletRequest request, @Valid InformationType infotype, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView(new RedirectView(JUMPURL + CSRFToken + request.getParameter(ConstantUtil.CSRFTOKEN)));
        }
        /** 获取存储在session中的登录id */
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        /**
         * 判断登录id是否为null 如果为null就设置为1
         */
        if (null == loginUserId) {
            loginUserId = 1L;
        }
        /** 设置创建者id */
        infotype.setCreateUserId(loginUserId);
        /** 设置修改者id */
        infotype.setUpdateUserId(loginUserId);
        infotype.setTemp3("1");
        /** 执行保存方法 */
        this.infoTypeService.saveInformation(infotype);
        /** 获取session中存储的用户名称 */
        String customerName = (String) request.getSession().getAttribute(NAME);
        /** 记录操作日志 */
        OperaLogUtil.addOperaLog(request, customerName, "添加文章栏目", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
        /** 返回结果 */
        return new ModelAndView(new RedirectView(JUMPURL + CSRFToken + request.getParameter(ConstantUtil.CSRFTOKEN)));
    }

    /**
     * 更新InformationType
     * 
     * @param infotype
     *            待更新的InformationType实体
     */
    @RequestMapping("/updateInfoType")
    public ModelAndView updateInfoType(HttpServletRequest request, @Valid InformationType infotype, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView(new RedirectView(JUMPURL + CSRFToken + request.getParameter(ConstantUtil.CSRFTOKEN)));
        }
        /** 获取session中存储的登录id */
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        /**
         * 判断登录id是否为Null 如果为null就设置为1
         */
        if (null == loginUserId) {
            loginUserId = 1L;
        }
        /** 设置更新者Id */
        infotype.setUpdateUserId(loginUserId);
        /** 执行更新方法 */
        this.infoTypeService.updateInformation(infotype);
        /** 获取存储在session中的用户名称 */
        String customerName = (String) request.getSession().getAttribute(NAME);
        /** 记录操作日志 */
        OperaLogUtil.addOperaLog(request, customerName, "修改文章栏目", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
        /** 返回结果 */
        return new ModelAndView(new RedirectView(JUMPURL + CSRFToken + request.getParameter(ConstantUtil.CSRFTOKEN)));
    }

    /**
     * 验证是否可以删除
     * 
     * @param infoTypeId
     *            待验证的资讯类型ID
     * @return 验证的结果
     */
    @RequestMapping("/checkDelInfoType")
    @ResponseBody
    public Map<String, Object> checkDelInfoType(Long infoTypeId) {
        /** 定义HashMap集合 */
        Map<String, Object> map = new HashMap<String, Object>();
        /** 申明一个布尔变量 */
        boolean flag = false;
        /** 调用service层方法并接收返回结果 */
        flag = this.infoTypeService.checkDelWithInfoTypeId(infoTypeId);
        if (flag) {
            /** 根据栏目ID获取文章数量验证栏目是否能删除 */
            flag = this.infoService.checkDelInfoTypeByInfoCount(infoTypeId);
            if (flag) {
                map.put("flag", flag);
            } else {
                map.put("flag", flag);
                map.put("msg", "该栏目下有文章，删除它会删除所有关联文章，是否确定删除？");
            }
        } else {
            map.put("flag", flag);
            map.put("msg", "该栏目下有子栏目，删除它会删除子栏目及所有关联文章，是否确定删除？");
        }
        return map;
    }

    /**
     * 查询所有的InformationType
     * 
     * @return 查询到的列表
     */
    @RequestMapping("/selectAllInfoType")
    @ResponseBody
    public List<InformationTypeVo> selectAllInfoType() {
        /** 查找所有的资讯类型 */
        return this.infoTypeService.selectAll();
    }

    /**
     * 根据 参数查询InformationType并通过AJAX返回
     * 
     * @param pb
     *            分页帮助类
     * @param searchText
     *            查询帮助类
     * @return PageBean
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("/queryInfoTypeVoList")
    @ResponseBody
    public PageBean queryInfoTypeVoList(PageBean pb, String searchText, String newFlag) throws UnsupportedEncodingException {
        String searchTextNew = searchText;
        /**
         * 如果参数newFlag不为null 就对参数searchText进行转码
         */
        if (newFlag != null) {
            searchTextNew = java.net.URLDecoder.decode(searchTextNew, "UTF-8");
            /** 查询栏目分类 */
            return this.infoTypeService.queryByPageBeanList(pb, searchTextNew);
        } else {
            /** 根据分页参数查询资讯类型列表 */
            return this.infoTypeService.queryByPageBean(pb, searchTextNew);
        }

    }

    /**
     * 跳转到InformationType管理的视图
     * 
     * @param request
     * @return ModelAndView
     */
    @RequestMapping("/jumpForInfoTypeView")
    public ModelAndView jumpForInfoTypeView(HttpServletRequest request) {
        /** 设置当前左侧显示导航 */
        MenuSession.sessionMenu(request);
        /** 返回结果 */
        return new ModelAndView("jsp/information/information_type");
    }

    /**
     * 验证栏目名称是否存在
     * 
     * @param name
     *            待验证的栏目名称
     * @param infoTypeId
     *            待验证的栏目名称
     * @return 验证的结果
     */
    @RequestMapping("/checkInfoTypeName")
    @ResponseBody
    public boolean checkInfoTypeName(String name, Long infoTypeId) {
        if (null != infoTypeId) {
            /** 修改栏目-根据栏目名称和栏目ID验证是否可修改 */
            return this.infoTypeService.checkAddInfoTypeByName(name, infoTypeId);
        } else {
            /** 根据栏目名称验证是否可添加 */
            return this.infoTypeService.checkAddInfoTypeByName(name);
        }
    }

    /**
     * 获取可发布文章的文章栏目
     * 
     * @return 验证的结果
     */
    @RequestMapping("/getInfoTypeWhenHaveInfo")
    @ResponseBody
    public List<InformationTypeVo> getInfoTypeWhenHaveInfo() {
        /** 根据栏目属性查找所有可发布文章的资讯类型，添加文章用 */
        return infoTypeService.selectInfoTypeByAttrForAddInfo();
    }

    public InformationTypeService getInfoTypeService() {
        return infoTypeService;
    }

    @Resource(name = "InformationTypeService")
    public void setInfoTypeService(InformationTypeService infoTypeService) {
        this.infoTypeService = infoTypeService;
    }

    public InformationService getInfoService() {
        return infoService;
    }

    @Resource(name = "InformationService")
    public void setInfoService(InformationService infoService) {
        this.infoService = infoService;
    }
}
