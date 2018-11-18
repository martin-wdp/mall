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

import com.ningpai.mobile.bean.MobHomePage;
import com.ningpai.mobile.service.MobHomePageService;
import com.ningpai.util.xml.XmlElementUtil;
import com.ningpai.util.xml.XmlUtil;

/**
 * 移动版可视化首页文字控制器
 * 
 * @ClassName: MobHomeTextController
 * @Description: 移动版可视化首页文字控制器
 * @author Wanghy
 * @date 2014年10月30日 上午11:57:58
 * 
 */
@Controller
public class MobHomeTextController {

    private static final Logger LOGGER = Logger.getLogger(MobHomeTextController.class);

    private static final String REDIRECT = "setMobHomePage.htm";

    XmlUtil xmlUtil = new XmlUtil();

    XmlElementUtil xmlElementUtil = new XmlElementUtil();

    /** 商家ID */
    static final String xmlFilePath = "mobile_home_page/xml/9gdemo.xml";

    @Resource(name = "MobHomePageService")
    private MobHomePageService mobHomePageService;

    /**
     * 保存文字模块
     * 
     * @Title: saveText
     * @Description: 保存文字模块
     * @param merchantId
     * @param request
     * @return
     */
    @RequestMapping("/saveText")
    public ModelAndView saveText(Long merchantId, HttpServletRequest request) {
        String rsv = request.getParameter("rsv");
        // request中的rsv不等于null并且不等于session中的rsv，为重复提交
        if (null != rsv && !rsv.equals(request.getSession().getAttribute("rsv"))) {
            LOGGER.info("==========保存文字重复提交===========");
            return new ModelAndView(new RedirectView(REDIRECT));
        }
        request.getSession().setAttribute("rsv", mkRSV());
        MobHomePage homePage = this.mobHomePageService.selectHomePageByMerchantId(merchantId);
        Document document = xmlUtil.str2Document(homePage.getDoc());
        Element root = document.getDocumentElement();

        // 获取文字信息
        String textId = request.getParameter("textId");
        String content = request.getParameter("content");
        // 拼接内容
        // content = "<![CDATA[" + content + "]]>";
        if (null != textId && !"".equals(textId)) {
            // 修改文字节点
            updateTextElement(root, textId, content);
        } else {
            // 添加文字节点
            createTextElement(root, content);
        }
        homePage.setDoc(xmlUtil.document2Str(document));
        homePage.setTemp1(mkToken());
        mobHomePageService.updateHomePage(homePage);
        return new ModelAndView(new RedirectView(REDIRECT));
    }

    /*
     * 添加文字节点
     */
    private void createTextElement(Element root, String content) {
        // 计算文字的id
        Element[] texts = xmlElementUtil.getElementsByName(root, "text");
        int max = 0;
        for (Element el : texts) {
            int temp = Integer.valueOf(el.getAttribute("id"));
            if (temp > max) {
                max = temp;
            }
        }
        // 创建文字节点
        Element newText = root.getOwnerDocument().createElement("text");
        newText.setNodeValue(content);
        xmlElementUtil.setElementValue(newText, content);
        xmlElementUtil.setElementAttr(newText, "id", String.valueOf(max + 1));
        root.appendChild(newText);
    }

    /*
     * 修改文字节点
     */
    private void updateTextElement(Element root, String textId, String content) {
        // 根据id找到节点
        Element oldText = null;
        Element[] texts = xmlElementUtil.getElementsByName(root, "text");
        for (Element el : texts) {
            if (el.getAttribute("id").equals(textId)) {
                oldText = el;
            }
        }
        oldText.setNodeValue(content);
        xmlElementUtil.setElementValue(oldText, content);
    }

    /**
     * 删除文字模块
     * 
     * @Title: deleteText
     * @Description: 根据商家ID查找模板对象，根据轮播模块ID删除文字模块
     * @param merchantId
     *            商家ID
     * @param request
     * @return
     */
    @RequestMapping("/deleteText")
    public ModelAndView deleteText(Long merchantId, HttpServletRequest request) {
        String rsv = request.getParameter("rsv");
        // request中的rsv不等于null并且不等于session中的rsv，为重复提交
        if (null != rsv && !rsv.equals(request.getSession().getAttribute("rsv"))) {
            LOGGER.info("==========删除文字重复提交===========");
            return new ModelAndView(new RedirectView(REDIRECT));
        }
        request.getSession().setAttribute("rsv", mkRSV());
        MobHomePage homePage = this.mobHomePageService.selectHomePageByMerchantId(merchantId);
        Document document = xmlUtil.str2Document(homePage.getDoc());
        // 获取texts的ID
        String id = request.getParameter("id");

        Element root = document.getDocumentElement();
        Element[] texts = xmlElementUtil.getElementsByName(root, "text");
        for (Element el : texts) {
            if (xmlElementUtil.getElementAttr(el, "id").equals(id)) {
                root.removeChild(el);
            }
        }

        homePage.setDoc(xmlUtil.document2Str(document));
        homePage.setTemp1(mkToken());
        mobHomePageService.updateHomePage(homePage);
        return new ModelAndView(new RedirectView(REDIRECT));
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
