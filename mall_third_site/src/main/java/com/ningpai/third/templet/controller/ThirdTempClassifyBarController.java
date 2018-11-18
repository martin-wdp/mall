/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.third.templet.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.common.util.ConstantUtil;
import com.ningpai.temp.bean.ClassifyBar;
import com.ningpai.temp.bean.SysTemp;
import com.ningpai.temp.service.ClassifyBarService;
import com.ningpai.temp.service.ThirdTempService;
import com.ningpai.third.goods.bean.ThirdCate;
import com.ningpai.third.goods.service.ThirdCateService;
import com.ningpai.util.PageBean;

/**
 * 控制器-分类导航
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年5月6日下午4:23:51
 */
@Controller
public class ThirdTempClassifyBarController {

    // session中保存的登录用户的id
    private static final String LOGINUSERID = "loginUserId";

    private static final String THIRDID = "thirdId";

    private static final String JUMPFORTHIRDTEMPCLASSIFYBARVIEW_HTM = "jumpForThirdTempClassifyBarView.htm?tempId=";

    /* 业务逻辑层依赖 */
    @Resource(name = "ThirdTempService")
    private ThirdTempService tempService;

    @Resource(name = "ClassifyBarService")
    private ClassifyBarService classifyBarService;

    @Resource(name = "ThirdCateService")
    private ThirdCateService goodsCateService;

    /**
     * 跳转到分类导航列表视图
     */
    @RequestMapping("/jumpForThirdTempClassifyBarView")
    public ModelAndView jumpForThirdTempClassifyBarView(HttpServletRequest request, Long tempId) {
        SysTemp temp = this.tempService.getSystempById(tempId);
        // Long thirdId = (Long) request.getSession().getAttribute(THIRDID);
        ModelAndView modelAndView = new ModelAndView("temp/temp_classifybar_list");
        modelAndView.addObject("temp", temp);
        // modelAndView.addObject(THIRDID, thirdId);
        return modelAndView;
    }

    /**
     * 查询模板分类导航列表
     * 
     * @param pb
     * @param tempId
     * @return
     */
    @ResponseBody
    @RequestMapping("/queryThirdTempClassifyBar")
    public PageBean queryThirdTempClassifyBar(HttpServletRequest request, PageBean pb, Long tempId) {
        // 获取第三方商家ID
        Long thirdId = (Long) request.getSession().getAttribute(THIRDID);
        return classifyBarService.selectClassifyBarByParam(pb, tempId, null, thirdId.toString());
    }

    /**
     * 查看分类导航
     * 
     * @param classifyBarId
     * @return
     */
    @RequestMapping("/showThirdTempClassifyBar")
    public ModelAndView showThirdTempClassifyBar(HttpServletRequest request, Long classifyBarId, Long tempId, Long parentId) {
        Map<String, Object> map = new HashMap<String, Object>();
        /*
         * 获取商品分类
         */
        List<ThirdCate> cateList = null;
        // 获取第三方商家ID
        Long thirdId = (Long) request.getSession().getAttribute(THIRDID);
        // 修改
        if (null != classifyBarId) {
            ClassifyBar classifyBar = classifyBarService.getClassifyBarById(classifyBarId);
            map.put("classifyBar", classifyBar);
            // 判断分类导航是否有父分类
            if (null != classifyBar.getParentId() && classifyBar.getParentId() > 0) {
                // 根据父分类关联的商品分类查找它的子商品分类列表
                Long parentIdCate = classifyBarService.getClassifyBarById(classifyBar.getParentId()).getGoodsCatId();
                cateList = this.goodsCateService.getThirdCateByParentId(parentIdCate, thirdId);
            } else {
                cateList = this.goodsCateService.getThirdCateByParentId(0L, thirdId);
            }
            // 添加
        } else {
            if (null != parentId) {
                // 根据父分类关联的商品分类查找它的子商品分类列表
                Long parentIdCate = classifyBarService.getClassifyBarById(parentId).getGoodsCatId();
                cateList = this.goodsCateService.getThirdCateByParentId(parentIdCate, thirdId);
            } else {
                cateList = this.goodsCateService.getThirdCateByParentId(0L, thirdId);
            }
        }
        map.put("cateList", cateList);
        map.put("tempId", tempId);
        map.put("parentId", parentId);
        map.put(THIRDID, thirdId);
        return new ModelAndView("temp/show_temp_classifybar", "map", map);
    }

    /**
     * 添加分类导航
     * 
     * @param request
     * @param classifyBar
     * @return
     */
    @RequestMapping("/createThirdTempClassifyBar")
    public ModelAndView createThirdTempClassifyBar(HttpServletRequest request, ClassifyBar classifyBar) {
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        if (null == loginUserId) {
            loginUserId = 1L;
        }
        if (null == classifyBar.getParentId()) {
            classifyBar.setGrade(1);
        } else {
            classifyBar.setGrade(classifyBarService.getClassifyBarById(classifyBar.getParentId()).getGrade() + 1);
        }
        classifyBar.setCreateUserId(loginUserId);
        classifyBarService.saveClassifyBar(classifyBar);
        return new ModelAndView(new RedirectView(JUMPFORTHIRDTEMPCLASSIFYBARVIEW_HTM + classifyBar.getTempId() + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN)));
    }

    /**
     * 修改分类导航
     * 
     * @param request
     * @param classifyBar
     * @return
     */
    @RequestMapping("/updateThirdTempClassifyBar")
    public ModelAndView updateThirdTempClassifyBar(HttpServletRequest request, ClassifyBar classifyBar) {
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        if (null == loginUserId) {
            loginUserId = 1L;
        }
        classifyBar.setUpdateUserId(loginUserId);
        Long thirdId = (Long) request.getSession().getAttribute(THIRDID);
        classifyBar.setTemp1(thirdId.toString());
        classifyBarService.updateClassifyBar(classifyBar);
        return new ModelAndView(new RedirectView(JUMPFORTHIRDTEMPCLASSIFYBARVIEW_HTM + classifyBar.getTempId() + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN)));
    }

    /**
     * 删除分页导航
     * 
     * @param request
     */
    @RequestMapping("/deleteThirdTempClassifyBar")
    public ModelAndView deleteThirdTempClassifyBar(HttpServletRequest request, Long[] classifyBarId, Long tempId) {
        Long thirdId = (Long) request.getSession().getAttribute(THIRDID);
        for (int i = 0; i < classifyBarId.length; i++) {
            classifyBarService.deleteClassBarById(classifyBarId[i], thirdId);
        }
        return new ModelAndView(new RedirectView(JUMPFORTHIRDTEMPCLASSIFYBARVIEW_HTM + tempId + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN)));
    }

    /**
     * 根据分类导航id查询分类导航信息
     * 
     * @param classifyBarId
     * @return ClassifyBar
     */
    @RequestMapping("/getClassifyBarById")
    @ResponseBody
    public ClassifyBar getClassifyBarById(Long classifyBarId) {
        return classifyBarService.getClassifyBarById(classifyBarId);
    }

}
