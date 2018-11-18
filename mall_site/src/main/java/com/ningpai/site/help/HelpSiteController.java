/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.site.help;

import com.ningpai.index.service.TopAndBottomService;
import com.ningpai.system.bean.HelpCate;
import com.ningpai.system.bean.HelpCenter;
import com.ningpai.system.service.BasicSetService;
import com.ningpai.system.service.HelpCateService;
import com.ningpai.system.service.HelpCenterService;
import com.ningpai.system.util.SelectBean;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 控制器-帮助
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年4月8日下午2:33:24
 */
@Controller
public class HelpSiteController {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(HelpSiteController.class);

    private static final String HELPCATES = "helpCates";

    private static final String INDEX_HTML = "../index.html";

    @Resource(name = "helpCateService")
    private HelpCateService helpCateService;

    @Resource(name = "helpCenterService")
    private HelpCenterService helpCenterService;

    @Resource(name = "basicSetService")
    private BasicSetService basicSetService;

    @Resource(name = "TopAndBottomService")
    private TopAndBottomService topAndBottomService;

    /**
     * 根据帮助分类分页查询帮助
     * 
     * @param request
     * @param response
     * @param pb
     * @param typeId
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/queryHelpList")
    public ModelAndView queryHelpList(HttpServletRequest request, HttpServletResponse response, PageBean pb, Long typeId) throws UnsupportedEncodingException {
        Map<String, Object> map = new HashMap<String, Object>();
        SelectBean selectBean = new SelectBean();
        selectBean.setCondition("");
        selectBean.setSearchText("");
        // 获取左侧栏目列表的数据
        List<HelpCate> list = helpCateService.findAll();
        for (HelpCate helpCate : list) {
            helpCate.setChilds(helpCenterService.findByCateId(helpCate.getHelpcateId()));
        }
        map.put(HELPCATES, list);
        // 获取选择的栏目
        if (null != typeId && typeId != -1) {
            HelpCate cHelpCate = helpCateService.findByHelpcateId(typeId);
            if (null != cHelpCate && "0".equals(cHelpCate.getDelFlag())) {
                map.put("cHelpCate", cHelpCate);
                map.put("pb", helpCenterService.findByPageBean(pb, selectBean, typeId));
                map.put("basicSet", basicSetService.findBasicSet());
                pb.setUrl("help/list/" + typeId);
                return topAndBottomService.getTopAndBottom(new ModelAndView("help/helplist", "map", map));
            } else {
                return new ModelAndView(new RedirectView("index.html"));
            }
        } else {
            return new ModelAndView(new RedirectView("index.html"));
        }

    }

    /**
     * 帮助中心首页
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/helpfirstpage")
    public ModelAndView toHelpFirstPage(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<HelpCate> list = helpCateService.findAll();
        for (HelpCate helpCate : list) {
            helpCate.setChilds(helpCenterService.findByCateId(helpCate.getHelpcateId()));
        }
        map.put(HELPCATES, list);
        List<HelpCenter> help = helpCenterService.selectAll();
        map.put("help", help);
        return topAndBottomService.getTopAndBottom(new ModelAndView("help/helpfirstpage").addObject("map", map));
    }

    /**
     * 显示帮助
     * 
     * @param request
     * @param response
     * @param helpId
     * @return
     */
    @RequestMapping("/helpcenter")
    public ModelAndView toHelpCenter(HttpServletRequest request, HttpServletResponse response, Long helpId, String thirdId) {
        // 获得商家id
        if (helpId == 112 && thirdId == null) {
            return new ModelAndView(new RedirectView(INDEX_HTML));
        }
        Map<String, Object> map = new HashMap<String, Object>();
        List<HelpCate> list = helpCateService.findAll();
        for (HelpCate helpCate : list) {
            helpCate.setChilds(helpCenterService.findByCateId(helpCate.getHelpcateId()));
        }
        map.put(HELPCATES, list);
        if (helpId != null) {
            HelpCenter help = helpCenterService.findByHelpId(helpId);
            if (null != help) {
                HelpCate cHelpCate = helpCateService.findByHelpcateId(help.getHelpcateId());
                map.put("help", help);
                map.put("cHelpCate", cHelpCate);
                map.put("basicSet", basicSetService.findBasicSet());
                map.put("thirdId", thirdId);
                // 非空验证 帮助标题
                if (null != help.getHelpTitle()) {
                    LOGGER.info("显示帮助【" + help.getHelpTitle() + "】");
                }
                return topAndBottomService.getTopAndBottom(new ModelAndView("help/newhelpdetail", "map", map));
            } else {
                return new ModelAndView(new RedirectView(INDEX_HTML));
            }
        } else {
            return new ModelAndView(new RedirectView(INDEX_HTML));
        }
    }
}
