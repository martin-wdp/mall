/*
 * Copyright 2014 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.mobile.controller;

import java.math.BigInteger;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.ningpai.mobile.bean.MobHomePage;
import com.ningpai.mobile.service.MobHomePageService;
import com.ningpai.util.xml.XmlElementUtil;
import com.ningpai.util.xml.XmlUtil;

/**
 * 移动版可视化首页全屏轮播控制器
 * 
 * @ClassName: MobHomeFullRollController
 * @Description: 移动版可视化首页全屏轮播模块的控制
 * @author Wanghy
 * @date 2014年10月28日 上午11:23:17
 * 
 */
@Controller
public class MobHomeFullRollController {

    private static final Logger LOGGER = Logger.getLogger(MobHomeFullRollController.class);

    private static final String SETMOBHOMEPAGE_HTM = "setMobHomePage.htm";
    private static final String MUSIC = "music";
    private static final String MUSICNAME = "musicname";
    private static final String FULLROLL = "fullRoll";
    private static final String ROLL_A = "roll_a";

    XmlUtil xmlUtil = new XmlUtil();

    XmlElementUtil xmlElementUtil = new XmlElementUtil();

    /** 商家ID */
    static final Long STOREID = -1L;
    static final String xmlFilePath = "mobile_home_page/xml/9gdemo.xml";
    static final String xslFilePath = "mobile_home_page/xsl/9gdemo_save.xsl";
    static final String htmlFilePath = "mobile_home_page/html/9gdemo.html";

    @Resource(name = "MobHomePageService")
    private MobHomePageService mobHomePageService;

    /**
     * 保存全屏轮播模块
     * 
     * @Title: saveFullRoll
     * @Description: 保存全屏轮播模块，包括添加、修改
     * @param merchantId
     *            商家ID
     * @param request
     * @return
     */
    @RequestMapping("/saveFullRoll")
    public ModelAndView saveFullRoll(Long merchantId, HttpServletRequest request) {
        String rsv = request.getParameter("rsv");
        // request中的rsv不等于null并且不等于session中的rsv，为重复提交
        if (null != rsv && !rsv.equals(request.getSession().getAttribute("rsv"))) {
            LOGGER.info("==========保存全屏轮播重复提交===========");
            return new ModelAndView(new RedirectView(SETMOBHOMEPAGE_HTM));
        }
        request.getSession().setAttribute("rsv", mkRSV());
        MobHomePage homePage = this.mobHomePageService.selectHomePageByMerchantId(merchantId);
        Document document = xmlUtil.str2Document(homePage.getDoc());
        Element root = document.getDocumentElement();

        // 获取轮播全屏轮播信息
        String fullRollId = request.getParameter("fullRollId");
        // 轮播方式
        String sd = request.getParameter("sd");
        String music = request.getParameter(MUSIC);
        String musicname = request.getParameter(MUSICNAME);
        String[] src = request.getParameterValues("fullRollImgSrc");
        String[] href = request.getParameterValues("fullRollHref");

        if (null != fullRollId && !"".equals(fullRollId)) {
            // 修改全屏轮播节点
            updateAdvElement(root, fullRollId, sd, music, musicname, src, href);
        } else {
            // 添加全屏轮播节点
            createAdvElement(root, sd, music, musicname, src, href);
        }
        // 写入xml
        homePage.setDoc(xmlUtil.document2Str(document));
        homePage.setTemp1(mkToken());
        mobHomePageService.updateHomePage(homePage);
        return new ModelAndView(new RedirectView(SETMOBHOMEPAGE_HTM));
    }

    /*
     * 添加全屏轮播节点
     */
    private void createAdvElement(Element root, String sd, String music, String musicname, String[] src, String[] href) {
        // 计算广告图片的id
        Element[] fullRolls = xmlElementUtil.getElementsByName(root, FULLROLL);
        int max = 0;
        for (Element el : fullRolls) {
            int temp = Integer.valueOf(el.getAttribute("id"));
            if (temp > max) {
                max = temp;
            }
        }
        // 创建全屏轮播节点
        Element newFullRoll = root.getOwnerDocument().createElement(FULLROLL);
        xmlElementUtil.setElementAttr(newFullRoll, "id", String.valueOf(max + 1));
        xmlElementUtil.setElementAttr(newFullRoll, "sd", sd);
        xmlElementUtil.setElementAttr(newFullRoll, MUSIC, music);
        xmlElementUtil.setElementAttr(newFullRoll, MUSICNAME, musicname);
        root.appendChild(newFullRoll);
        for (int i = 0; i < src.length; i++) {
            // 创建a标签元素
            Element newA = newFullRoll.getOwnerDocument().createElement(ROLL_A);
            xmlElementUtil.setElementAttr(newA, "href", href[i]);
            // 创建img元素
            Element newImg = newA.getOwnerDocument().createElement("img");
            xmlElementUtil.setElementAttr(newImg, "src", src[i]);
            xmlElementUtil.setElementAttr(newImg, "widthB", "100%");
            xmlElementUtil.setElementAttr(newImg, "heightB", "100%");

            newA.appendChild(newImg);
            newFullRoll.appendChild(newA);
        }
    }

    /*
     * 修改全屏轮播节点
     */
    private void updateAdvElement(Element root, String fullRollId, String sd, String music, String musicname, String[] src, String[] href) {
        // 根据id找到节点
        Element oldFullRoll = null;
        Element[] fullRolls = xmlElementUtil.getElementsByName(root, FULLROLL);
        for (Element el : fullRolls) {
            if (el.getAttribute("id").equals(fullRollId)) {
                oldFullRoll = el;
                // 删除它的子节点
                Element[] as = xmlElementUtil.getElementsByName(el, ROLL_A);
                for (Element a : as) {
                    el.removeChild(a);
                }
            }
        }
        xmlElementUtil.setElementAttr(oldFullRoll, "sd", sd);
        xmlElementUtil.setElementAttr(oldFullRoll, MUSIC, music);
        xmlElementUtil.setElementAttr(oldFullRoll, MUSICNAME, musicname);
        for (int i = 0; i < src.length; i++) {
            // 创建a标签元素
            Element newA = oldFullRoll.getOwnerDocument().createElement(ROLL_A);
            xmlElementUtil.setElementAttr(newA, "href", href[i]);
            // 创建img元素
            Element newImg = newA.getOwnerDocument().createElement("img");
            xmlElementUtil.setElementAttr(newImg, "src", src[i]);
            xmlElementUtil.setElementAttr(newImg, "widthB", "100%");
            xmlElementUtil.setElementAttr(newImg, "heightB", "100%");

            newA.appendChild(newImg);
            oldFullRoll.appendChild(newA);
        }
    }

    /**
     * 删除全屏轮播模块
     * 
     * @Title: deleteFullRoll
     * @Description: 根据商家ID查找模板对象，根据模块ID删除全屏轮播模块
     * @param merchantId
     *            商家ID
     * @param request
     * @return
     */
    @RequestMapping("/deleteFullRoll")
    public ModelAndView deleteFullRoll(Long merchantId, HttpServletRequest request) {
        String rsv = request.getParameter("rsv");
        // request中的rsv不等于null并且不等于session中的rsv，为重复提交
        if (null != rsv && !rsv.equals(request.getSession().getAttribute("rsv"))) {
            LOGGER.info("==========删除全屏轮播重复提交===========");
            return new ModelAndView(new RedirectView(SETMOBHOMEPAGE_HTM));
        }
        request.getSession().setAttribute("rsv", mkRSV());
        MobHomePage homePage = this.mobHomePageService.selectHomePageByMerchantId(merchantId);
        Document document = xmlUtil.str2Document(homePage.getDoc());
        // 获取fullRoll的ID
        String id = request.getParameter("id");

        Element root = document.getDocumentElement();
        Element[] fullRolls = xmlElementUtil.getElementsByName(root, FULLROLL);
        for (Element el : fullRolls) {
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
     * 清除所有移动版首页模块
     * 
     * @Title: clearAll
     * @Description: 清除所有移动版首页模块，以便添加全屏轮播
     * @param merchantId
     * @param request
     */
    @RequestMapping("/clearAll")
    public void clearAll(Long merchantId, HttpServletRequest request) {
        MobHomePage homePage = this.mobHomePageService.selectHomePageByMerchantId(merchantId);
        Document document = xmlUtil.str2Document(homePage.getDoc());
        Element root = document.getDocumentElement();
        xmlElementUtil.getElements(root);
        Element[] els = xmlElementUtil.getElements(root);
        for (Element el : els) {
            root.removeChild(el);
        }

        homePage.setDoc(xmlUtil.document2Str(document));
        homePage.setTemp1(mkToken());
        mobHomePageService.updateHomePage(homePage);
    }

    /**
     * 获取MP3文件用于选择
     * 
     * @Title: searchMp3
     * @Description: 获取项目mobile_home_page文件夹下的MP3文件，和它们的属性
     * @param request
     * @return
     */
    @RequestMapping("/searchMp3")
    @ResponseBody
    public List<Map<String, String>> searchMp3(HttpServletRequest request) {
        List<Map<String, String>> list = new ArrayList<>();
        // 获取项目地址栏路径
        String rootPath = request.getContextPath();
        // 获取ip地址
        // 获取项目物理路径
        String root = request.getSession().getServletContext().getRealPath("/");
        Path dir = Paths.get(root + "mobile_home_page");
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir, "*.mp3")) {
            for (Path path : stream) {
                Map<String, String> map = new HashMap<>();
                // 获取文件名
                map.put("mp3name", path.getFileName().toString());
                // 获取文件地址
                map.put("mp3address", rootPath + "/mobile_home_page/" + path.getFileName().toString());
                // 获取文件长度，单位kb
                Long fileLength = path.toFile().length() / 1024;
                map.put("mp3length", String.valueOf(fileLength));
                // 获取文件时长
                MP3File f = (MP3File) AudioFileIO.read(path.toFile());
                MP3AudioHeader audioHeader = (MP3AudioHeader) f.getAudioHeader();
                map.put("mp3time", audioHeader.getTrackLengthAsString());
                list.add(map);
            }
        } catch (final Exception e) {
            LOGGER.error(e.getLocalizedMessage(), e);
        }
        return list;
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
