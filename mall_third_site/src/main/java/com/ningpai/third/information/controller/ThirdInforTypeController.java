/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.third.information.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.information.bean.InformationType;
import com.ningpai.information.service.ThirdInforService;
import com.ningpai.information.service.ThirdInforTypeService;
import com.ningpai.information.vo.InformationTypeVo;
import com.ningpai.third.util.MenuOperationUtil;
import com.ningpai.util.PageBean;

/**
 * <p>资讯类型控制层</p>
 * @author NINGPAI-WangHaiYang
 * @since 2014年02月15日10:55:38
 * @version 2.0
 */
@Controller
public class ThirdInforTypeController {

    //资讯类型SERVICE 接口
    @Resource(name = "ThirdInforTypeService")
    private ThirdInforTypeService thirdInforTypeService;
    //资讯SERVICE接口
    @Resource(name = "ThirdInforService")
    private ThirdInforService infoService;

    //跳转到InformationType管理的视图常量
    private static final String JUMPURL = "jumpForThirdInfoTypeView.htm";
    //当前登录成功的用户
    private static final String LOGINUSERID = "loginUserId";
    //商家id
    private static final String THIRDID = "thirdId";

    /**
     * 删除InformationType
     * @param infoTypeId
     *            待删除的infoTypeId
     */
    @RequestMapping("/delThirdInfoType")
    public ModelAndView delThirdInfoType(HttpServletRequest request, Long infoTypeId) {
        //当前登录的用户ID
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        if (null == loginUserId) {
            loginUserId = 1L;
        }
        //根据主键删除文章栏目
        this.thirdInforTypeService.delInformation(infoTypeId, loginUserId);
        //重定向到文章列表
        return new ModelAndView(new RedirectView(JUMPURL));
    }

    /**
     * 批量删除InformationType
     * @param infoTypeIds
     *            待删除的infoTypeId数组
     */
    @RequestMapping("/batchDelThirdInfoType")
    public ModelAndView batchDelThirdInfoType(HttpServletRequest request, Long[] infoTypeIds) {
        //当前登录成功的用户ID
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        if (null == loginUserId) {
            loginUserId = 1L;
        }
        //批量删除资讯类型
        this.thirdInforTypeService.batchDelInformation(infoTypeIds, loginUserId);
        //重定向到文章列表
        return new ModelAndView(new RedirectView(JUMPURL));
    }

    /**
     * 跳转到显示资讯栏目<br/>
     * 栏目编号不为空时查询栏目并绑定到页面，修改栏目<br/>
     * 栏目编号为空时，添加栏目
     * @return ModelAndView
     */
    @RequestMapping("/showThirdInfoType")
    public ModelAndView showThirdInfo(HttpServletRequest request, HttpServletResponse response, Long infoTypeId) {
        ModelAndView mav = new ModelAndView();
        //资讯类型实体
        InformationType infoType = new InformationType();
        //类型ID不为空
        if (null != infoTypeId) {
            //根据主键查找资讯类型
            infoType = thirdInforTypeService.selectByPrimaryKey(infoTypeId);
        }
        //装载数据 咨询类型
        mav.addObject("infoType", infoType);
        //商家id
        String thirdId = ((Long) request.getSession().getAttribute(THIRDID)).toString();
        if (null == thirdId || "".equals(thirdId)) {
            thirdId = "1";
        }
        // 获取所有第三方的资讯栏目
        List<InformationTypeVo> infoTypes = thirdInforTypeService.selectAllByThirdId(thirdId);
        //咨询类型ID
        mav.addObject("infoTypes", infoTypes);
        //设置需要跳转的页面路径
        mav.setViewName("information/show_third_infotype");
        return mav;
    }

    /**
     * 根据ID获取文章栏目信息
     * @param infoTypeId 文章栏目ID
     * @return 文章栏目信息
     */
    @RequestMapping("/selectInfoTypeByPrimaryKey")
    @ResponseBody
    public InformationType selectInfoTypeByPrimaryKey(Long infoTypeId){
        //根据主键查找资讯类型
        return thirdInforTypeService.selectByPrimaryKey(infoTypeId);
    }

    /**
     * 增加InformationType
     * 
     * @param infotype
     *            待增加的InformationType实体
     */
    @RequestMapping("/saveThirdInfoType")
    public ModelAndView saveThirdInfoType(HttpServletRequest request, InformationType infotype) {
        //当前登录成功的用户ID
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        if (null == loginUserId) {
            loginUserId = 1L;
        }
        //设置创建人
        infotype.setCreateUserId(loginUserId);
        //设置修改人
        infotype.setUpdateUserId(loginUserId);
        //商家ID
        String thirdId = ((Long) request.getSession().getAttribute(THIRDID)).toString();
        if (null == thirdId || "".equals(thirdId)) {
            thirdId = "1";
        }
        //添加资讯类型
        this.thirdInforTypeService.saveInformation(infotype, thirdId);
        //重定向到咨询列表
        return new ModelAndView(new RedirectView(JUMPURL));
    }

    /**
     * 更新InformationType
     * @param infotype
     *            待更新的InformationType实体
     */
    @RequestMapping("/updateThirdInfoType")
    public ModelAndView updateThirdInfoType(HttpServletRequest request, InformationType infotype) {
        //当前登录成功的用户ID
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        if (null == loginUserId) {
            loginUserId = 1L;
        }
        //设置修改人
        infotype.setUpdateUserId(loginUserId);
        //更新资讯类型
        this.thirdInforTypeService.updateInformation(infotype);
        //重定向到资讯列表
        return new ModelAndView(new RedirectView(JUMPURL));
    }

    /**
     * 验证是否可以删除
     * @param infoTypeId
     *            待验证的资讯类型ID
     * @return 验证的结果
     */
    @RequestMapping("/checkDelThirdInfoType")
    @ResponseBody
    public Map<String, Object> checkDelThirdInfoType(Long infoTypeId) {
        Map<String, Object> map = new HashMap<String, Object>();
        boolean flag = false;
        //判断资讯类型是否可删除
        flag = this.thirdInforTypeService.checkDelWithInfoTypeId(infoTypeId);
        if (flag) {
            //根据栏目ID获取文章数量验证栏目是否能删除
            flag = this.infoService.checkDelInfoTypeByInfoCount(infoTypeId);
            if (flag) {
                map.put("flag", flag);
            } else {
                map.put("flag", flag);
                //设置提示语句
                map.put("msg", "该栏目下有文章，不能删除");
            }
        } else {
            map.put("flag", flag);
            //设置提示语句
            map.put("msg", "该栏目下有子栏目，不能删除");
        }
        return map;
    }

    /**
     * 查询所有的InformationType
     * @return 查询到的列表
     */
    @RequestMapping("/selectAllThirdInfoType")
    @ResponseBody
    public List<InformationTypeVo> selectAllThirdInfoType(HttpServletRequest request) {
        //商家ID
        String thirdId = ((Long) request.getSession().getAttribute(THIRDID)).toString();
        if (null == thirdId || "".equals(thirdId)) {
            thirdId = "1";
        }
        // 获取可发布文章的资讯栏目
        return thirdInforTypeService.selectAllByThirdId(thirdId);
    }

    /**
     * 根据 参数查询InformationType并通过AJAX返回
     * 
     * @param pb
     *            分页帮助类
     * @param searchText
     *            查询的条件
     * @return PageBean
     */
    @RequestMapping("/queryThirdInfoTypeVoList")
    @ResponseBody
    public PageBean queryThirdInfoTypeVoList(HttpServletRequest request, PageBean pb, String searchText) {
        //商家ID
        String thirdId = ((Long) request.getSession().getAttribute(THIRDID)).toString();
        if (null == thirdId || "".equals(thirdId)) {
            thirdId = "1";
        }
        //根据分页参数和第三方商家ID查询资讯类型列表
        return this.thirdInforTypeService.queryByPageBean(pb, searchText, thirdId);
    }

    /**
     *  跳转到InformationType管理的视图
     * @param request
     * @param n 头部导航栏索引
     * @param l 左侧导航栏索引
     * @return
     */
    @RequestMapping("/jumpForThirdInfoTypeView")
    public ModelAndView jumpForThirdInfoTypeView(HttpServletRequest request, String n, String l) {
        MenuOperationUtil.fillSessionMenuIndex(request, n, l);
        //跳转到咨询列表
        return new ModelAndView("information/third_infotype_list");
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
    @RequestMapping("/checkThirdInfoTypeName")
    @ResponseBody
    public boolean checkThirdInfoTypeName(HttpServletRequest request, String name, Long infoTypeId) {
        //商家Id
        String thirdId = ((Long) request.getSession().getAttribute(THIRDID)).toString();
        if (null == thirdId || "".equals(thirdId)) {
            thirdId = "1";
        }
        if (null != infoTypeId) {
            //修改栏目-根据栏目名称和栏目ID验证是否可修改
            return this.thirdInforTypeService.checkAddInfoTypeByName(name, thirdId, infoTypeId);
        } else {
            //根据第三方商家ID和栏目名称验证是否可添加
            return this.thirdInforTypeService.checkAddInfoTypeByName(name, thirdId);
        }
    }

    /**
     * 获取可发布文章的文章栏目
     * @return 验证的结果
     */
    @RequestMapping("/getThirdInfoTypeWhenHaveInfo")
    @ResponseBody
    public List<InformationType> getThirdInfoTypeWhenHaveInfo(HttpServletRequest request) {
        //商家ID
        String thirdId = ((Long) request.getSession().getAttribute(THIRDID)).toString();
        if (null == thirdId || "".equals(thirdId)) {
            thirdId = "1";
        }
        //根据第三方商家ID查找所有可发布文章的栏目<br/>
        return thirdInforTypeService.selectInfoTypeByAttr(thirdId);
    }
}