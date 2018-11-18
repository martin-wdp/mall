/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.third.information.controller;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import com.ningpai.information.bean.Information;
import com.ningpai.information.bean.InformationType;
import com.ningpai.information.service.ThirdInforService;
import com.ningpai.information.service.ThirdInforTypeService;
import com.ningpai.third.util.MenuOperationUtil;
import com.ningpai.util.PageBean;

/**
 * <p>
 * 资讯控制层
 * </p>
 * 
 * @author zhanghl
 * @since 2014年02月17日 18:55:38
 * @version 2.0
 */
@Controller
public class ThirdInforController {

    // 商家id
    private static final String THIRDID = "thirdId";
    private static final String QUERYTHIRDINFOVOLIST_HTM = "queryThirdInfoVoList.htm";

    @Resource(name = "ThirdInforTypeService")
    // 资讯类型SERVICE接口
    private ThirdInforTypeService thirdInforTypeService;

    // 资讯SERVICE接口
    @Resource(name = "ThirdInforService")
    private ThirdInforService infoService;

    /**
     * 获取第三方可发布文章的栏目数量
     * 
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getThirdInfoTypeCount")
    public int getThirdInfoTypeCount(HttpServletRequest request) {
        // 商家ID
        String thirdId = ((Long) request.getSession().getAttribute(THIRDID)).toString();
        // 获取可发布文章的资讯栏目
        return thirdInforTypeService.selectInfoTypeByAttr(thirdId).size();
    }

    /**
     * 查询资讯列表和分页数据
     */
    @RequestMapping(value = "/queryThirdInfoVoList")
    public ModelAndView queryThirdInfoVoList(HttpServletRequest request, String n, String l, PageBean pb, Long typeId, String infoName) {
        MenuOperationUtil.fillSessionMenuIndex(request, n, l);
        // 获取可发布文章的资讯栏目 商家ID
        String thirdId = ((Long) request.getSession().getAttribute(THIRDID)).toString();
        if (null == thirdId || "".equals(thirdId)) {
            thirdId = "1";
        }
        // 根据第三方商家ID查找所有可发布文章的栏目<br/>
        List<InformationType> infoTypes = thirdInforTypeService.selectInfoTypeByAttr(thirdId);

        return new ModelAndView("information/third_info_list", "pb",
        // 根据分页参数查询资讯列表
                infoService.queryByPageBean(pb, infoName, typeId, thirdId)).addObject("infoTypes", infoTypes)
        // 类型ID
                .addObject("typeId", typeId)
                // 咨询名称
                .addObject("infoName", infoName);
    }

    /**
     * 跳转到显示资讯<br/>
     * 资讯编号不为空时查询资讯并绑定到页面，修改资讯<br/>
     * 资讯编号为空时，添加资讯
     * 
     * @return ModelAndView
     */
    @RequestMapping("/showThirdInformation")
    public ModelAndView showThirdInfo(HttpServletRequest request, Long infoId) {
        ModelAndView mav = new ModelAndView();
        if (infoId != null) {
            // 根据ID获取实体类-资讯文章
            Information info = infoService.selectByPrimaryKey(infoId);
            mav.addObject("info", info);
        }
        // 获取第三方商家ID
        String thirdId = ((Long) request.getSession().getAttribute(THIRDID)).toString();
        if (null == thirdId || "".equals(thirdId)) {
            thirdId = "1";
        }
        // 根据第三方商家ID查找所有可发布文章的栏目<br/>
        List<InformationType> infoTypes = thirdInforTypeService.selectInfoTypeByAttr(thirdId);
        // 装载所有可发布文章的栏目
        mav.addObject("infoTypes", infoTypes);
        // 设置跳转路径
        mav.setViewName("information/show_third_info");
        return mav;
    }

    /**
     * 添加资讯
     * 
     * @param request
     * @param response
     * @param information
     *            实体类-资讯文章
     * @return
     */
    @RequestMapping("/addThirdInformation")
    public ModelAndView addThirdInfo(HttpServletRequest request, HttpServletResponse response, Information information) {
        // 创建人ID
        information.setCreateUserId(1L);
        // 商家ID
        String thirdId = ((Long) request.getSession().getAttribute(THIRDID)).toString();
        if (null == thirdId || "".equals(thirdId)) {
            thirdId = "1";
        }
        // 设置第三方商家ID
        information.setTemp2(thirdId);
        // 添加咨询
        infoService.saveInfo(information);
        // 重定向到咨询列表
        return new ModelAndView(new RedirectView(QUERYTHIRDINFOVOLIST_HTM));
    }

    /**
     * 修改资讯
     * 
     * @param request
     * @param response
     * @param information
     *            实体类-资讯文章
     * @return
     */
    @RequestMapping("/updateThirdInformation")
    public ModelAndView updateThirdInfo(HttpServletRequest request, HttpServletResponse response, Information information) {
        // //创建人ID
        information.setUpdateUserId(1L);
        // 更新资讯文章
        infoService.updateInfo(information);
        // 重定向到咨询列表
        return new ModelAndView(new RedirectView(QUERYTHIRDINFOVOLIST_HTM));
    }

    /**
     * 删除资讯
     * 
     * @param request
     * @param infoIds
     *            咨询文章ID
     * @return
     */
    @RequestMapping("/delThirdInformation")
    public ModelAndView delThirdInfo(HttpServletRequest request, Long[] infoIds) {
        // 根据咨询ID循环删除咨询
        for (int i = 0; i < infoIds.length; i++) {
            infoService.delInfo(infoIds[i]);
        }
        // 重定向到咨询列表
        return new ModelAndView(new RedirectView(QUERYTHIRDINFOVOLIST_HTM));
    }

    /**
     * 验证文章标题是否存在
     * 
     * @param title
     *            待验证的文章标题
     * @return 验证的结果
     */
    @RequestMapping("/checkThirdInformationByTitle")
    @ResponseBody
    public boolean checkThirdInfoByTitle(HttpServletRequest request, String title, Long infoId) {
        // 商家ID
        String thirdId = ((Long) request.getSession().getAttribute(THIRDID)).toString();
        if (null == thirdId || "".equals(thirdId)) {
            thirdId = "1";
        }
        // 验证文章标题是否存在
        if (null != infoId) {
            return this.infoService.checkAddInfoByTitle(title, thirdId, infoId);
        } else {
            return this.infoService.checkAddInfoByTitle(title, thirdId);
        }
    }
}
