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
 * 移动版可视化首页轮播广告控制器
 * 
 * @ClassName: MobHomeRollAdverController
 * @Description: 移动版可视化首页轮播广告模块的控制
 * @author Wanghy
 * @date 2014年10月28日 上午11:23:17
 * 
 */
@Controller
public class MobHomeRollAdverController {

    private static final Logger LOGGER = Logger.getLogger(MobHomeRollAdverController.class);

    XmlUtil xmlUtil = new XmlUtil();
    XmlElementUtil xmlElementUtil = new XmlElementUtil();

    /** 商家ID */
    static final Long STOREID = -1L;
    static final String xmlFilePath = "mobile_home_page/xml/9gdemo.xml";
    static final String xslFilePath = "mobile_home_page/xsl/9gdemo_save.xsl";
    static final String htmlFilePath = "mobile_home_page/html/9gdemo.html";

    private static final String SETMOBHOMEPAGE_HTM = "setMobHomePage.htm";
    private static final String ROLL_ADV = "roll_adv";
    private static final String ROLL_A = "roll_a";

    @Resource(name = "MobHomePageService")
    private MobHomePageService mobHomePageService;

    /**
     * 保存轮播广告模块
     * 
     * @Title: saveRollAdv
     * @Description: 保存轮播广告模块，包括添加、修改
     * @param merchantId
     *            商家ID
     * @param request
     * @return
     */
    @RequestMapping("/saveRollAdv")
    public ModelAndView saveRollAdv(Long merchantId, HttpServletRequest request) {
        String rsv = request.getParameter("rsv");
        // request中的rsv不等于null并且不等于session中的rsv，为重复提交
        if (null != rsv && !rsv.equals(request.getSession().getAttribute("rsv"))) {
            LOGGER.info("==========保存轮播广告重复提交===========");
            return new ModelAndView(new RedirectView(SETMOBHOMEPAGE_HTM));
        }
        request.getSession().setAttribute("rsv", mkRSV());
        MobHomePage homePage = this.mobHomePageService.selectHomePageByMerchantId(merchantId);
        Document document = xmlUtil.str2Document(homePage.getDoc());
        Element root = document.getDocumentElement();

        // 获取轮播广告图片信息
        String rollImgAdvId = request.getParameter("rollImgAdvId");
        String[] src = request.getParameterValues("rollImgAdvSrc");
        String[] href = request.getParameterValues("rollImgAdvHref");

        if (null != rollImgAdvId && !"".equals(rollImgAdvId)) {
            // 修改轮播广告节点
            updateAdvElement(root, rollImgAdvId, src, href);
        } else {
            // 添加轮播广告节点
            createAdvElement(root, src, href);
        }
        // 写入xml
        // xmlUtil.createXml(fileName);
        homePage.setDoc(xmlUtil.document2Str(document));
        homePage.setTemp1(mkToken());
        mobHomePageService.updateHomePage(homePage);
        return new ModelAndView(new RedirectView(SETMOBHOMEPAGE_HTM));
    }

    /*
     * 添加轮播广告节点
     */
    private void createAdvElement(Element root, String[] src, String[] href) {
        // 计算轮播广告的id
        Element[] rollAdvs = xmlElementUtil.getElementsByName(root, ROLL_ADV);
        int max = 0;
        for (Element el : rollAdvs) {
            int temp = Integer.valueOf(el.getAttribute("id"));
            if (temp > max) {
                max = temp;
            }
        }
        // 创建轮播广告节点
        Element newImgAdv = root.getOwnerDocument().createElement(ROLL_ADV);
        xmlElementUtil.setElementAttr(newImgAdv, "id", String.valueOf(max + 1));
        root.appendChild(newImgAdv);
        for (int i = 0; i < src.length; i++) {
            // 创建a标签元素
            Element newA = newImgAdv.getOwnerDocument().createElement(ROLL_A);
            xmlElementUtil.setElementAttr(newA, "href", href[i]);
            // 创建img元素
            Element newImg = newA.getOwnerDocument().createElement("img");
            xmlElementUtil.setElementAttr(newImg, "src", src[i]);
            xmlElementUtil.setElementAttr(newImg, ConstantUtil.WIDTH, "100%");
            xmlElementUtil.setElementAttr(newImg, "widthB", "100%");
            xmlElementUtil.setElementAttr(newImg, ConstantUtil.HEIGHT, "155px");
            xmlElementUtil.setElementAttr(newImg, "heightB", "50%");

            newA.appendChild(newImg);
            newImgAdv.appendChild(newA);
        }
    }

    /*
     * 修改轮播广告节点
     */
    private void updateAdvElement(Element root, String rollImgAdvId, String[] src, String[] href) {
        // 根据id找到节点
        Element oldImgAdv = null;
        Element[] rollAdvs = xmlElementUtil.getElementsByName(root, ROLL_ADV);
        for (Element el : rollAdvs) {
            if (el.getAttribute("id").equals(rollImgAdvId)) {
                oldImgAdv = el;
                // 删除它的子节点
                Element[] as = xmlElementUtil.getElementsByName(el, ROLL_A);
                for (Element a : as) {
                    el.removeChild(a);
                }
            }
        }
        for (int i = 0; i < src.length; i++) {
            // 创建a标签元素
            Element newA = oldImgAdv.getOwnerDocument().createElement(ROLL_A);
            xmlElementUtil.setElementAttr(newA, "href", href[i]);
            // 创建img元素
            Element newImg = newA.getOwnerDocument().createElement("img");
            xmlElementUtil.setElementAttr(newImg, "src", src[i]);
            xmlElementUtil.setElementAttr(newImg, ConstantUtil.WIDTH, "100%");
            xmlElementUtil.setElementAttr(newImg, "widthB", "100%");
            xmlElementUtil.setElementAttr(newImg, ConstantUtil.HEIGHT, "155px");
            xmlElementUtil.setElementAttr(newImg, "heightB", "50%");

            newA.appendChild(newImg);
            oldImgAdv.appendChild(newA);
        }
    }

    /**
     * 删除轮播广告模块
     * 
     * @Title: deleteRollAdv
     * @Description: 根据商家ID查找模板对象，根据轮播模块ID删除轮播广告模块
     * @param merchantId
     *            商家ID
     * @param request
     * @return
     */
    @RequestMapping("/deleteRollAdv")
    public ModelAndView deleteRollAdv(Long merchantId, HttpServletRequest request) {
        String rsv = request.getParameter("rsv");
        // request中的rsv不等于null并且不等于session中的rsv，为重复提交
        if (null != rsv && !rsv.equals(request.getSession().getAttribute("rsv"))) {
            LOGGER.info("==========删除轮播广告重复提交===========");
            return new ModelAndView(new RedirectView(SETMOBHOMEPAGE_HTM));
        }
        request.getSession().setAttribute("rsv", mkRSV());
        MobHomePage homePage = this.mobHomePageService.selectHomePageByMerchantId(merchantId);
        Document document = xmlUtil.str2Document(homePage.getDoc());
        // 获取轮播广告的ID
        String id = request.getParameter("id");

        Element root = document.getDocumentElement();
        Element[] rollAdvs = xmlElementUtil.getElementsByName(root, ROLL_ADV);
        for (Element el : rollAdvs) {
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
