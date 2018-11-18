/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.system.controller;

import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.system.bean.BasicSet;
import com.ningpai.system.service.BasicSetService;
import com.ningpai.system.service.SysBasicService;
import com.ningpai.uploadfileset.service.UploadFileSetService;
import com.ningpai.util.MenuSession;
import com.ningpai.util.MyLogger;
import com.ningpai.util.UploadUtil;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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
import java.util.HashMap;
import java.util.Map;

/**
 * 站点设置控制层
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2013年12月16日 下午5:11:27
 * @version 1.0
 */
@Controller
public class BasicSetController {

    /**
     * 日志
     */
    private static final MyLogger LOGGER = new MyLogger(BasicSetController.class);

    /**
     * 用户名称
     */
    public static final String NAME = "name";
    /**
     * 验证码session值
     */
    private static final String PATCHCA = "PATCHCA";
    /**
     * "operaPath"
     */
    public static final String OPERAPATH = "operaPath";

    private static final String LOGGERINFO1 = ",用户名:";

    private static final String LOGGERINFO2 = "修改站点设置错误：=>";

    /** 基本设置service */
    @Resource(name = "basicSetService")
    private BasicSetService basicSetService;
    /** 后台Logo设置service */
    @Resource(name = "SysBasicService")
    private SysBasicService sysBasicService;
    /** 上传文件设置类service */
    @Resource(name = "UploadFileSetService")
    private UploadFileSetService uploadFileSetService;

    /**
     * 查询站点信息
     * 
     * @param request
     * @param response
     * @return ModelAndView
     */
    @RequestMapping("/basicset")
    public ModelAndView basicSet(HttpServletRequest request, HttpServletResponse response) {
        MenuSession.sessionMenu(request);
        return new ModelAndView("jsp/system/parameter", "parameter", basicSetService.findBasicSet()).addObject("sysBasic", sysBasicService.getSysBasic())
                .addObject("ufs", uploadFileSetService.getCurrUploadFileSet());
    }

    /**
     * 查询站点信息
     * 
     * 
     * @return ModelAndView
     */
    @RequestMapping("getBasicSetName")
    @ResponseBody
    public BasicSet getBasicSetName(HttpServletRequest request) {
        // 查询站点信息
        BasicSet set = basicSetService.findBasicSet();
        // 将网站地址设置到session中
        request.getSession().setAttribute("bsetaddress", set.getBsetAddress());
        return set;
    }

    /**
     * 查询前台站点地址
     * 
     * @return ModelAndView
     */
    @RequestMapping("/getBasicset")
    @ResponseBody
    public String basicSet() {

        return basicSetService.findBasicSet().getBsetAddress();
    }

    /**
     * 查询后台登陆是否启用验证码
     * 
     * @return ModelAndView
     */
    @RequestMapping("/newgetLoginPatcha")
    @ResponseBody
    public String getLoginPatcha(HttpServletRequest request) {

        BasicSet basicSet= basicSetService.findBasicSet();
        if(basicSet!=null && "0".equals(basicSet.getLoginPatcha())){
                return request.getSession().getAttribute(PATCHCA)==null?null:request.getSession().getAttribute(PATCHCA).toString();
        }

        return  null;
    }

    /**
     * 修改站点信息
     * 
     * @param request
     * @param response
     * @return ModelAndView
     */
    @RequestMapping("/updatebasic")
    public ModelAndView updateBasicset(MultipartHttpServletRequest request, HttpServletResponse response, @Valid BasicSet basicSet, BindingResult bindingResult) {
        // 是否有错误
        if (bindingResult.hasErrors()) {
            // 重定向：基本设置
            return new ModelAndView(new RedirectView("basicset.htm"));
        }
        try {
            // 待文件上传区
            MultipartFile file = request.getFile("netLogo");
            // 客服热线文件
            MultipartFile hotLineFile = request.getFile("hotLine");
            // 前台登录文件
            MultipartFile siteLoginImg = request.getFile("siteLogin");
            // 第三方文件
            MultipartFile thirdLoginImg = request.getFile("thirdLogin");
            // 若有数据则上传文件
            if (!file.isEmpty()) {
                basicSet.setBsetLogo(UploadUtil.uploadFileOne(file, request));
            }

            // 上传客服热线
            if (!hotLineFile.isEmpty()) {
                basicSet.setBsetHotline(UploadUtil.uploadFileOne(hotLineFile, request));
            }
            // 判断前台登录文件是否为空
            if (!siteLoginImg.isEmpty()) {
                basicSet.setSiteLoginImg(UploadUtil.uploadFileOne(siteLoginImg, request));
            }
            // 判断第三方登录文件是否为空
            if (!thirdLoginImg.isEmpty()) {
                basicSet.setThirdLoginImg(UploadUtil.uploadFileOne(thirdLoginImg, request));
            }

            // 提交数据
            basicSetService.updateBasicSet(basicSet);
            // 获得session中的用户名
            String customerName = (String) request.getSession().getAttribute(NAME);
            // 添加操作日志
            OperaLogUtil.addOperaLog(request, customerName, "修改站点设置", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
        } catch (Exception e) {
            // 错误日志输出
            LOGGER.error("",e);
        }
        return new ModelAndView(new RedirectView("basicset.htm"));
    }

    /**
     * 修改站点信息(新)
     * 
     * @param request
     * @param response
     * @return ModelAndView
     */
    @RequestMapping("updatebasicnew")
    @ResponseBody
    public int updateBasicsetNew(HttpServletRequest request, HttpServletResponse response, @Valid BasicSet basicSet, BindingResult bindingResult) {
        // 是否有错
        if (bindingResult.hasErrors()) {
            return -1;
        }
        try {
            // 提交数据
            basicSetService.updateBasicSet(basicSet);
            // 获得session中的用户名
            String customerName = (String) request.getSession().getAttribute(NAME);
            // 添加操作日志
            OperaLogUtil.addOperaLog(request, customerName, "修改站点设置", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
        } catch (Exception e) {
            // 错误日志输出
            LOGGER.error("",e);
        }
        return 1;
    }

    /**
     * 修改登录验证码开关设置
     * 
     * @param request
     * @param response
     * @return ModelAndView
     */
    @RequestMapping("updateLogPatcha")
    @ResponseBody
    public Map<String, Object> updateLogPatcha(HttpServletRequest request, HttpServletResponse response, String loginPatcha, Long bsetId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // 初始化基本设置
            BasicSet basicSet = new BasicSet();
            // 登录验证
            basicSet.setLoginPatcha(loginPatcha);
            // 基本id
            basicSet.setBsetId(bsetId);
            // 修改基本设置
            basicSetService.updateBasicSet(basicSet);
            map.put("msg", "操作成功！");
            // 获取session中用户名
            String customerName = (String) request.getSession().getAttribute(NAME);
            // 添加操作日志
            OperaLogUtil.addOperaLog(request, customerName, "修改登录验证码开关设置", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
        } catch (Exception e) {
            // 输出错误日志
            LOGGER.error("",e);
            map.put("msg", "操作失败！");
        }
        return map;
    }

    /**
     * 下载图片
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/uploadImg")
    public void uploadImg(MultipartHttpServletRequest request, HttpServletResponse response) {
        PrintWriter out = null;
        try {
            out = response.getWriter();
            // 待文件上传区
            MultipartFile file = request.getFile("picFile");
            // 若有数据则上传文件
            if (!file.isEmpty()) {
                String picPath = UploadUtil.uploadFileOne(file, request);
                out.append("<script>parent.callback('" + picPath + "');</script>");
            }
        } catch (Exception e) {
            // 错误日志输出
            LOGGER.error("上传图片错误：=>" ,e);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
}
