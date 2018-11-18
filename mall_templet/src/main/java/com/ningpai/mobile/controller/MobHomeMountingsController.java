/*
 * Copyright 2014 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.mobile.controller;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.ningpai.common.util.ConstantUtil;
import com.ningpai.mobile.bean.MobHomePage;
import com.ningpai.mobile.service.MobHomePageService;
import com.ningpai.util.xml.XmlElementUtil;
import com.ningpai.util.xml.XmlUtil;

/**
 * 移动版可视化首页配件控制器
 * 
 * @ClassName: MobHomeMountingsController
 * @Description: 移动版可视化首页配件的控制，包括：空白占位、分隔线
 * @author Wanghy
 * @date 2014年10月30日 上午11:57:58
 * 
 */
@Controller
public class MobHomeMountingsController {

    private static final Logger LOGGER = Logger.getLogger(MobHomeMountingsController.class);

    private static final String SETMOBHOMEPAGE_HTM = "setMobHomePage.htm";
    private static final String BLANKBOX = "blankbox";
    private static final String DIVIDING = "dividing";

    XmlUtil xmlUtil = new XmlUtil();
    XmlElementUtil xmlElementUtil = new XmlElementUtil();

    static final String xmlFilePath = "mobile_home_page/xml/9gdemo.xml";

    @Resource(name = "MobHomePageService")
    private MobHomePageService mobHomePageService;

    /**
     * 保存空白占位模块
     * 
     * @Title: saveBlankbox
     * @Description: 保存空白占位模块
     * @param merchantId
     * @param request
     * @return
     */
    @RequestMapping("/saveBlankbox")
    public ModelAndView saveBlankbox(Long merchantId, HttpServletRequest request) {
        String rsv = request.getParameter("rsv");
        // request中的rsv不等于null并且不等于session中的rsv，为重复提交
        if (null != rsv && !rsv.equals(request.getSession().getAttribute("rsv"))) {
            LOGGER.info("==========保存空白占位重复提交===========");
            return new ModelAndView(new RedirectView(SETMOBHOMEPAGE_HTM));
        }
        request.getSession().setAttribute("rsv", mkRSV());
        MobHomePage homePage = this.mobHomePageService.selectHomePageByMerchantId(merchantId);
        Document document = xmlUtil.str2Document(homePage.getDoc());
        Element root = document.getDocumentElement();

        // 获取空白占位信息
        String blankBoxId = request.getParameter("blankBoxId");
        String height = request.getParameter(ConstantUtil.HEIGHT);
        if (null != blankBoxId && !"".equals(blankBoxId)) {
            // 修改文字节点
            updateBlankboxElement(root, blankBoxId, height);
        } else {
            // 添加文字节点
            createBlankboxElement(root, height);
        }
        homePage.setDoc(xmlUtil.document2Str(document));
        homePage.setTemp1(mkToken());
        mobHomePageService.updateHomePage(homePage);
        return new ModelAndView(new RedirectView(SETMOBHOMEPAGE_HTM));
    }

    /*
     * 添加空白占位节点
     */
    private void createBlankboxElement(Element root, String height) {
        // 计算空白占位的id
        Element[] blankboxs = xmlElementUtil.getElementsByName(root, BLANKBOX);
        int max = 0;
        for (Element el : blankboxs) {
            int temp = Integer.valueOf(el.getAttribute("id"));
            if (temp > max) {
                max = temp;
            }
        }
        // 创建空白占位节点
        Element newBlankbox = root.getOwnerDocument().createElement(BLANKBOX);
        xmlElementUtil.setElementAttr(newBlankbox, "id", String.valueOf(max + 1));
        xmlElementUtil.setElementAttr(newBlankbox, ConstantUtil.HEIGHT, height);
        root.appendChild(newBlankbox);
    }

    /*
     * 修改空白占位节点
     */
    private void updateBlankboxElement(Element root, String blankBoxId, String height) {
        // 根据id找到节点
        Element oldBlankbox = null;
        Element[] blankboxs = xmlElementUtil.getElementsByName(root, BLANKBOX);
        for (Element el : blankboxs) {
            if (el.getAttribute("id").equals(blankBoxId)) {
                oldBlankbox = el;
            }
        }
        xmlElementUtil.setElementAttr(oldBlankbox, ConstantUtil.HEIGHT, height);
    }

    /**
     * 删除空白占位模块
     * 
     * @Title: deleteBlankbox
     * @Description: 根据商家ID查找模板对象，根据空白占位ID删除空白占位模块
     * @param merchantId
     *            商家ID
     * @param request
     * @return
     */
    @RequestMapping("/deleteBlankbox")
    public ModelAndView deleteBlankbox(Long merchantId, HttpServletRequest request) {
        String rsv = request.getParameter("rsv");
        // request中的rsv不等于null并且不等于session中的rsv，为重复提交
        if (null != rsv && !rsv.equals(request.getSession().getAttribute("rsv"))) {
            LOGGER.info("==========删除空白占位重复提交===========");
            return new ModelAndView(new RedirectView(SETMOBHOMEPAGE_HTM));
        }
        request.getSession().setAttribute("rsv", mkRSV());
        MobHomePage homePage = this.mobHomePageService.selectHomePageByMerchantId(merchantId);
        Document document = xmlUtil.str2Document(homePage.getDoc());
        // 获取blankbox的ID
        String id = request.getParameter("id");

        Element root = document.getDocumentElement();
        Element[] blankboxs = xmlElementUtil.getElementsByName(root, BLANKBOX);
        for (Element el : blankboxs) {
            if (xmlElementUtil.getElementAttr(el, "id").equals(id)) {
                root.removeChild(el);
            }
        }

        homePage.setDoc(xmlUtil.document2Str(document));
        homePage.setTemp1(mkToken());
        mobHomePageService.updateHomePage(homePage);
        return new ModelAndView(new RedirectView(SETMOBHOMEPAGE_HTM));
    }

    /**
     * 添加分隔线模块
     * 
     * @Title: addLine
     * @Description: 添加分隔线模块
     * @param merchantId
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/addLine")
    public void addLine(Long merchantId, HttpServletRequest request) {
        // String rsv = request.getParameter("rsv");
        // request中的rsv不等于null并且不等于session中的rsv，为重复提交
        // if(null != rsv &&
        // !rsv.equals(request.getSession().getAttribute("rsv"))){
        // log.info("==========添加分隔线重复提交===========");
        // return new ModelAndView(new RedirectView(SETMOBHOMEPAGE_HTM));
        // }
        // request.getSession().setAttribute("rsv", mkRSV());
        request.getSession().setAttribute("reload", 1);
        MobHomePage homePage = this.mobHomePageService.selectHomePageByMerchantId(merchantId);
        Document document = xmlUtil.str2Document(homePage.getDoc());
        Element root = document.getDocumentElement();

        // 计算分隔线的id
        Element[] lineApps = xmlElementUtil.getElementsByName(root, DIVIDING);
        int max = 0;
        for (Element el : lineApps) {
            int temp = Integer.valueOf(el.getAttribute("id"));
            if (temp > max) {
                max = temp;
            }
        }
        // 创建分隔线节点
        Element newBlankbox = root.getOwnerDocument().createElement(DIVIDING);
        xmlElementUtil.setElementAttr(newBlankbox, "id", String.valueOf(max + 1));
        root.appendChild(newBlankbox);

        homePage.setDoc(xmlUtil.document2Str(document));
        homePage.setTemp1(mkToken());
        mobHomePageService.updateHomePage(homePage);
        // return new ModelAndView(new RedirectView(SETMOBHOMEPAGE_HTM));
    }

    /**
     * 删除分隔线模块
     * 
     * @Title: deleteLine
     * @Description: 根据商家ID查找模板对象，根据分隔线ID删除分隔线模块
     * @param merchantId
     *            商家ID
     * @param request
     * @return
     */
    @RequestMapping("/deleteLine")
    public ModelAndView deleteLine(Long merchantId, HttpServletRequest request) {
        request.getSession().setAttribute("reload", 1);
        String rsv = request.getParameter("rsv");
        // request中的rsv不等于null并且不等于session中的rsv，为重复提交
        if (null != rsv && !rsv.equals(request.getSession().getAttribute("rsv"))) {
            LOGGER.info("==========删除分隔线重复提交===========");
            return new ModelAndView(new RedirectView(SETMOBHOMEPAGE_HTM));
        }
        request.getSession().setAttribute("rsv", mkRSV());
        MobHomePage homePage = this.mobHomePageService.selectHomePageByMerchantId(merchantId);
        Document document = xmlUtil.str2Document(homePage.getDoc());
        // 获取blankbox的ID
        String id = request.getParameter("id");

        Element root = document.getDocumentElement();
        Element[] blankboxs = xmlElementUtil.getElementsByName(root, DIVIDING);
        for (Element el : blankboxs) {
            if (xmlElementUtil.getElementAttr(el, "id").equals(id)) {
                root.removeChild(el);
            }
        }

        homePage.setDoc(xmlUtil.document2Str(document));
        homePage.setTemp1(mkToken());
        mobHomePageService.updateHomePage(homePage);
        return new ModelAndView(new RedirectView(SETMOBHOMEPAGE_HTM));
    }

    /*
     * 生成移动版新首页token
     */
    private String mkToken() {
        String tokenValue = null;
        // 生成tokin
        MessageDigest md;
        try {
            // 生成一个MD5加密计算摘要
            md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(new Date().toString().getBytes());
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            tokenValue = new BigInteger(1, md.digest()).toString(16);
            LOGGER.debug("==========token码为" + tokenValue);

        } catch (Exception e) {
            LOGGER.error("生成token码错误：=>", e);
        }
        return tokenValue;
    }

    /*
     * 生成重复提交token
     */
    private String mkRSV() {
        String tokenValue = null;
        // 生成tokin
        MessageDigest md;
        try {
            // 生成一个MD5加密计算摘要
            md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(new Date().toString().getBytes());
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            tokenValue = new BigInteger(1, md.digest()).toString(16);
            LOGGER.debug("==========token码为" + tokenValue);

        } catch (Exception e) {
            LOGGER.error("生成token码错误：=>", e);
        }
        return tokenValue;
    }
}
