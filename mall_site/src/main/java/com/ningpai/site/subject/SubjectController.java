/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.site.subject;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.ningpai.util.MyLogger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.index.service.TopAndBottomService;
import com.ningpai.information.bean.InforSubject;
import com.ningpai.information.service.InforSubjectServcie;
import com.ningpai.system.mobile.bean.MobSiteBasic;
import com.ningpai.system.mobile.service.MobSiteBasicService;

/**
 * 控制器-专题页面
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年7月28日下午6:25:20
 */
@Controller
public class SubjectController {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(SubjectController.class);

    private static final String INDEX_HTML = "../index.html";

    @Resource(name = "InforSubjectServcie")
    private InforSubjectServcie subjectService;

    @Resource(name = "TopAndBottomService")
    private TopAndBottomService topAndBottomService;

    @Resource(name = "MobSiteBasicService")
    private MobSiteBasicService mobSiteBasicService;

    /**
     * 显示专题页<br/>
     * 根据专题页是否显示头部和尾部调用获取头部尾部信息的service<br/>
     * 数据出错时跳转到首页
     * 
     * @param subjectId
     * @return
     */
    @RequestMapping("/showSubject")
    public ModelAndView showSubject(Long subjectId, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        InforSubject subject = new InforSubject();
        MobSiteBasic mobSiteBasic = mobSiteBasicService.selectCurrMobSiteBasic(request);

        if (subjectId != null) {
            subject = subjectService.selectByIsShowAndId(subjectId);
            if (null != subject) {
                LOGGER.info("显示标题【" + subject.getTitle() + "】的专题");
                mav.addObject("subject", subject);
                // 不去除头部尾部
                if ("0".equals(subject.getTemp1())) {
                    mav.setViewName("subject/subject_top_bottom");
                    return topAndBottomService.getTopAndBottom(mav);
                    // 去除头部尾部
                } else if ("1".equals(subject.getTemp1())) {
                    // 获取移动版项目地址
                    if (null != mobSiteBasic) {
                        String mobProjectUrl = mobSiteBasic.getSiteAddress();
                        mav.addObject("mobProjectUrl", mobProjectUrl);
                    }
                    //添加VIP（会员属性）信息 2015.12.03 wuyanbo add
                    String isVip = "0";
                    Object object = request.getSession().getAttribute("vip");
                    if(null != object){
                        isVip = (String)object;
                    }else{
                        request.getSession().setAttribute("vip", isVip);
                        request.setAttribute("vip", isVip);
                    }
                    mav.setViewName("subject/subject");
                    return mav;
                } else {
                    return new ModelAndView(new RedirectView(INDEX_HTML));
                }
            } else {
                return new ModelAndView(new RedirectView(INDEX_HTML));
            }
        } else {
            return new ModelAndView(new RedirectView(INDEX_HTML));
        }
    }
}
