/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.site.information;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ningpai.util.MyLogger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.common.util.ConstantUtil;
import com.ningpai.index.service.TopAndBottomService;
import com.ningpai.information.bean.Information;
import com.ningpai.information.bean.InformationOnePage;
import com.ningpai.information.bean.InformationType;
import com.ningpai.information.service.InfoOPTagService;
import com.ningpai.information.service.InfoUserDefinedService;
import com.ningpai.information.service.InformationOnePageService;
import com.ningpai.information.service.InformationService;
import com.ningpai.information.service.InformationTypeService;
import com.ningpai.information.vo.InformationTypeVo;
import com.ningpai.system.service.BasicSetService;
import com.ningpai.util.PageBean;
import com.ningpai.util.SelectBean;

/**
 * 控制器-文章
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年4月8日下午2:33:24
 */
@Controller
public class InformationSiteController {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(InformationSiteController.class);

    private static final String BASICSET = "basicSet";

    /* 引用的SERVICE */
    private InformationService infoService;
    private InformationTypeService infoTypeService;
    private InfoUserDefinedService infoUDService;
    private InformationOnePageService infoOPService;
    private InfoOPTagService infoOPTagService;
    private BasicSetService basicSetService;
    @Resource(name = "TopAndBottomService")
    private TopAndBottomService topAndBottomService;

    /**
     * 根据模板和单页标签获取单页
     * 
     * @param request
     * @param response
     * @param infoOPTagId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryInfoOPByTagId")
    public List<InformationOnePage> queryInfoOPByTagId(HttpServletRequest request, HttpServletResponse response, Long tempId, Long infoOPTagId) {

        return this.infoOPService.selectInfoOPByTempAndTag(tempId, infoOPTagId);
    }

    /**
     * 根据文章栏目和文章名称分页查询文章
     * 
     * @param request
     * @param response
     * @param pb
     * @param typeId
     * @param infoName
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/queryInfoList")
    public ModelAndView queryInfoList(HttpServletRequest request, HttpServletResponse response, PageBean pb, Long typeId, String infoName, Integer pageNo)
            throws UnsupportedEncodingException {
        Map<String, Object> map = new HashMap<String, Object>();
        SelectBean selectBean = new SelectBean();
        // 判断文章名称是否为空，不为空设置到查询工具类里
        if (null != infoName && !"".equals(infoName)) {
            String name = new String(infoName.getBytes("ISO-8859-1"), ConstantUtil.UTF);
            if (!"文章名称".equals(name)) {
                selectBean.setSearchText(name);
                selectBean.setCondition("1");
                map.put("infoName", name);
            }
        } else {
            selectBean.setCondition("");
        }
        // 获取左侧栏目列表的数据
        List<InformationTypeVo> infoTypes = infoTypeService.selectAll();
        for (int i = 0; i < infoTypes.size(); i++) {
            InformationTypeVo infoType = infoTypes.get(i);
            if (infoType.getGrade() != 1) {
                infoTypes.remove(infoType);
                i--;
                continue;
            }
        }
        map.put("infoTypes", infoTypes);
        // 获取选择的栏目
        if (null != typeId && typeId != -1) {
            InformationType cInfoType = infoTypeService.selectByPrimaryKey(typeId);
            if (null != cInfoType) {
                map.put("cInfoType", cInfoType);
            }
            pb.setUrl("information/list/typeId=" + typeId);
        } else {
            pb.setUrl("information/list/");
        }
        if (null != pageNo) {
            pb.setPageNo(pageNo);
        }
        map.put("pb", infoService.queryByPageBeanForSite(pb, selectBean, typeId));
        map.put(BASICSET, basicSetService.findBasicSet());
        ModelAndView mav = new ModelAndView();
        mav.setViewName("information/infolist");
        mav.addObject("map", map);
        return topAndBottomService.getTopAndBottom(mav);
    }

    /**
     * 显示文章
     * 
     * @return ModelAndView
     */
    @RequestMapping("/showInfo")
    public ModelAndView toHelpCenter(HttpServletRequest request, HttpServletResponse response, Long infoId) {
        ModelAndView mav = new ModelAndView();
        List<InformationTypeVo> list = infoTypeService.selectAll();
        for (int i = 0; i < list.size(); i++) {
            InformationTypeVo infoType = list.get(i);
            if (infoType.getGrade() != 1) {
                list.remove(infoType);
                i--;
                continue;
            }
        }
        Information info = new Information();
        InformationType cInfoType = new InformationType();
        if (infoId != null) {
            info = infoService.selectByPrimaryKey(infoId);
            if (null != info) {
                cInfoType = infoTypeService.selectByPrimaryKey(info.getInfoTypeId());
            } else {
                return new ModelAndView(new RedirectView("list/"));
            }
        }
        mav.setViewName("information/infodetail");
        mav.addObject(BASICSET, basicSetService.findBasicSet());
        mav.addObject("infoTypes", list);
        mav.addObject("info", info);
        mav.addObject("cInfoType", cInfoType);
        // 非空验证 文章标题
        if (null != info.getTitle()) {
            LOGGER.info("显示标题为【" + info.getTitle() + "】文章");
        }
        return topAndBottomService.getTopAndBottom(mav);
    }

    /**
     * 显示单页
     * 
     * @param infoOPId
     * @return
     */
    @RequestMapping("/showOnePage")
    public ModelAndView showOnePage(Long infoOPId) {
        if (infoOPId != null) {
            // 根据主键 获取实体类-资讯单页
            InformationOnePage informationOnePage = infoOPService.getInfoOnePageByID(infoOPId);
            ModelAndView mav = new ModelAndView();
            mav.setViewName("information/one_page_detail");
            mav.addObject("onePage", informationOnePage);
            mav.addObject(BASICSET, basicSetService.findBasicSet());
            // 非空验证 资讯单页名称
            if (null != informationOnePage.getTitle()) {
                LOGGER.info("显示标题为【" + informationOnePage.getTitle() + "】单页");
            }
            return topAndBottomService.getTopAndBottom(mav);
        } else {
            return new ModelAndView(new RedirectView("index.html"));
        }
    }

    public InformationService getInfoService() {
        return infoService;
    }

    @Resource(name = "InformationService")
    public void setInfoService(InformationService infoService) {
        this.infoService = infoService;
    }

    public InformationTypeService getInfoTypeService() {
        return infoTypeService;
    }

    @Resource(name = "InformationTypeService")
    public void setInfoTypeService(InformationTypeService infoTypeService) {
        this.infoTypeService = infoTypeService;
    }

    public InfoUserDefinedService getInfoUDService() {
        return infoUDService;
    }

    @Resource(name = "InfoUserDefinedService")
    public void setInfoUDService(InfoUserDefinedService infoUDService) {
        this.infoUDService = infoUDService;
    }

    public BasicSetService getBasicSetService() {
        return basicSetService;
    }

    @Resource(name = "basicSetService")
    public void setBasicSetService(BasicSetService basicSetService) {
        this.basicSetService = basicSetService;
    }

    public InfoOPTagService getInfoOPTagService() {
        return infoOPTagService;
    }

    @Resource(name = "InfoOPTagService")
    public void setInfoOPTagService(InfoOPTagService infoOPTagService) {
        this.infoOPTagService = infoOPTagService;
    }

    public InformationOnePageService getInfoOPService() {
        return infoOPService;
    }

    @Resource(name = "InformationOnePageService")
    public void setInfoOPService(InformationOnePageService infoOPService) {
        this.infoOPService = infoOPService;
    }
}
