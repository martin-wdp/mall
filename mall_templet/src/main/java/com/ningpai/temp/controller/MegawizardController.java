/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.temp.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.common.util.ConstantUtil;
import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.temp.bean.Megawizard;
import com.ningpai.temp.service.MegawizardService;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;
import com.ningpai.util.StringCommonUtil;

/**
 * 控制器-页面说明
 * 
 * @author NINGPAI-ZhuoYu
 * @since 2014年7月23日上午9:46:09
 */
@Controller
public class MegawizardController {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(MegawizardController.class);

    private static final String LOGINUSERID = "loginUserId";

    public static final String NAME = "name";

    public static final String OPERAPATH = "operaPath";

    public static final String TYPES = "types";
    public static final String QUERYMEGAWIZARDBYPAGEBEAN_HTM = "queryMegawizardByPageBean.htm?tempId=";
    public static final String LOGGERINFO1 = ",用户名:";

    @Resource(name = "MegawizardService")
    private MegawizardService megawizardService;

    /**
     * 分页显示
     * 
     * @param pb
     * @param tempId
     * @return
     */
    @RequestMapping("/queryMegawizardByPageBean")
    public ModelAndView queryMegawizardByPageBean(PageBean pb, Long tempId) {
        megawizardService.selectByTempId(pb, tempId);
        return new ModelAndView("jsp/temp/temp_megawizardList", "pb", pb).addObject("tempId", tempId).addObject(TYPES, getPropertiesValue());
    }

    /**
     * 分页显示
     *
     * @param pb
     * @param tempId
     * @return
     */
    @RequestMapping("/querymegawizardbypagebeanbyajax")
    @ResponseBody
    public Map<String, Object> queryMegawizardByPageBeanByAjax(PageBean pb, Long tempId) {
        // 查询页面说明列表
        megawizardService.selectByTempId(pb, tempId);
        // 返回参数
        Map<String, Object> map = new HashMap<String, Object>();
        // 设置分页参数及集合
        map.put("pb", pb);
        // 设置类型
        map.put(TYPES, getPropertiesValue());
        // 返回
        return map;
    }

    /**
     * 根据id查询单个
     * 
     * @param id
     * @return
     */
    @RequestMapping("/updateSelectMegawizardById")
    public ModelAndView selectMegawizardById(Long id) {
        Megawizard meg = megawizardService.selectById(id);
        return new ModelAndView("jsp/temp/show_megawizard", "meg", meg).addObject(TYPES, getPropertiesValue());
    }

    /**
     * 修改页面说明
     * 
     * @param meg
     * @param request
     * @return
     */
    @RequestMapping("/updateMegawizard")
    public ModelAndView updateMegawizard(@Valid Megawizard meg, BindingResult bindingResult, HttpServletRequest request) {
        // 判断是否符合规范
        if (bindingResult.hasErrors()) {
            return new ModelAndView(new RedirectView(QUERYMEGAWIZARDBYPAGEBEAN_HTM + meg.getTempId() + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN)));
        }
        // 获取登录id
        Long updateUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        // 设置登录id
        meg.setUpdateUserId(updateUserId);
        // 更新页面说明
        megawizardService.updateByIdToContent(meg);
        // 获取用户名
        String customerName = (String) request.getSession().getAttribute(NAME);
        // 记录日志
        OperaLogUtil.addOperaLog(request, customerName, "修改页面说明", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
        // 返回结果
        return new ModelAndView(new RedirectView(QUERYMEGAWIZARDBYPAGEBEAN_HTM + meg.getTempId() + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN)));
    }

    /**
     * 修改页面说明（新）
     *
     * @param meg
     * @param request
     * @return
     */
    @RequestMapping("/updatemegawizardajax")
    @ResponseBody
    public int updateMegawizardAjax(@Valid Megawizard meg, BindingResult bindingResult, HttpServletRequest request) {
        // 判断是否符合规范
        if (bindingResult.hasErrors()) {
            return 0;
        }
        // 获取登录id
        Long updateUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        // 设置登录id
        meg.setUpdateUserId(updateUserId);
        // 获取用户名
        String customerName = (String) request.getSession().getAttribute(NAME);
        // 记录日志
        OperaLogUtil.addOperaLog(request, customerName, "修改页面说明", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
        // 返回结果
        // 成功返回1
        // 失败返回0
        return megawizardService.updateByIdToContent(meg);
    }

    /**
     * 跳转新增页面
     * 
     * @param tempId
     * @return
     */
    @RequestMapping("/showMegawizardNew")
    public ModelAndView showMegawizardNew(Long tempId) {
        Megawizard meg = new Megawizard();
        meg.setTempId(tempId);
        return new ModelAndView("jsp/temp/show_megawizard", "meg", meg).addObject(TYPES, getPropertiesValue());
    }

    /**
     * 新增页面说明
     * 
     * @param meg
     * @param request
     * @return
     */
    @RequestMapping("/newMegawizard")
    public ModelAndView createMegawizard(@Valid Megawizard meg, BindingResult bindingResult, HttpServletRequest request) {
        // 判断参数是否符合规范
        if (bindingResult.hasErrors()) {
            return new ModelAndView(new RedirectView(QUERYMEGAWIZARDBYPAGEBEAN_HTM + meg.getTempId() + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN)));
        }
        // 获取登录id
        Long updateUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        // 设置修改id
        meg.setUpdateUserId(updateUserId);
        // 修改
        megawizardService.insert(meg);
        // 获取用户名
        String customerName = (String) request.getSession().getAttribute(NAME);
        // 记录日志
        OperaLogUtil.addOperaLog(request, customerName, "新增模板信息", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
        // 返回结果
        return new ModelAndView(new RedirectView(QUERYMEGAWIZARDBYPAGEBEAN_HTM + meg.getTempId() + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN)));
    }

    /**
     * 新增页面说明（新）
     *
     * @param meg
     * @param request
     * @return
     */
    @RequestMapping("/newmegawizardajax")
    @ResponseBody
    public int createMegawizardAjax(@Valid Megawizard meg, BindingResult bindingResult, HttpServletRequest request) {
        // 判断参数是否符合规范
        if (bindingResult.hasErrors()) {
            return 0;
        }
        // 获取登录id
        Long updateUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        // 设置修改id
        meg.setUpdateUserId(updateUserId);
        // 获取用户名
        String customerName = (String) request.getSession().getAttribute(NAME);
        // 记录日志
        OperaLogUtil.addOperaLog(request, customerName, "新增模板信息", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
        // 返回结果
        // 成功返回1
        // 失败返回0
        return megawizardService.insert(meg);
    }

    /**
     * 删除
     * 
     * @param request
     * @param request
     * @return
     */
    @RequestMapping("/delectMegawizard")
    @ResponseBody
    public ModelAndView delectMegawizard(HttpServletRequest request) {
        // 获取删除参数
        String[] ids = request.getParameterValues("id[]");
        // 删除
        megawizardService.updateById(ids);
        // 获取用户名
        String customerName = (String) request.getSession().getAttribute(NAME);
        // 记录日志
        OperaLogUtil.addOperaLog(request, customerName, "删除模板信息", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
        return null;
    }

    /**
     * 删除
     *
     * @param request
     * @param request
     * @return
     */
    @RequestMapping("/delectmegawizardajax")
    @ResponseBody
    public int delectMegawizardAjax(HttpServletRequest request) {
        // 获取删除参数
        String[] ids = request.getParameterValues("id");
        // 获取用户名
        String customerName = (String) request.getSession().getAttribute(NAME);
        // 记录日志
        OperaLogUtil.addOperaLog(request, customerName, "删除模板信息", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
        // 返回结果
        // 成功返回1
        // 失败返回0
        return megawizardService.updateById(ids);
    }

    /**
     * 读取页面说明配置文件
     * 
     * @return
     * @throws UnsupportedEncodingException
     */
    private Map<String, String> getPropertiesValue() {
        Map<String, String> map = new HashMap<String, String>();
        Properties properties = new Properties();
        InputStream inputStream = null;
        try {
            String classPath = new File(MegawizardController.class.getResource("/").getFile()).getCanonicalPath();
            String configFilePath = classPath + "/com/ningpai/web/config/megawizard.properties";
            inputStream = new FileInputStream(configFilePath);
            properties.load(inputStream);
        } catch (IOException e) {
            LOGGER.error("",e);
        } finally {
            // 关闭流
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    LOGGER.error("",e);
                }
            }
        }
        // 取出所有的key
        @SuppressWarnings("rawtypes")
        Enumeration enu = properties.propertyNames();
        while (enu.hasMoreElements()) {
            Object obj = enu.nextElement();
            try {
                map.put(obj.toString(), StringCommonUtil.changeToUtf8(properties.getProperty(obj.toString())));
            } catch (UnsupportedEncodingException e) {
                LOGGER.error("字符串转换错误：=>" ,e);
            }
        }

        return map;
    }

}
