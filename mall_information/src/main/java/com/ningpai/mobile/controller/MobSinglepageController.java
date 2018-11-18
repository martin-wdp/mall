/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.mobile.controller;

import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.ningpai.common.util.ConstantUtil;
import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.mobile.bean.MobHomeSinglePage;
import com.ningpai.mobile.bean.MobSinglepage;
import com.ningpai.mobile.service.MobHomeSinglePageService;
import com.ningpai.mobile.service.MobSinglepageMarkService;
import com.ningpai.mobile.service.MobSinglepageService;
import com.ningpai.system.util.SelectBean;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;
import com.ningpai.util.xml.XmlElementUtil;
import com.ningpai.util.xml.XmlUtil;

/**
 * 移动版单页控制器
 * 
 * @author zhangsl
 * @since 2014年11月21日 下午4:26:20
 * @version 0.0.1
 */
@Controller
public class MobSinglepageController {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(MobSinglepageController.class);

    public static final String NAME = "name";
    public static final String OPERAPATH = "operaPath";
    private static final String LOGINUSERID = "loginUserId";
    static final String xmlFilePath = "mobile_home_page/xml/9gdemo2.xml";
    static final String xslFilePath = "mobile_home_page/xsl/9gdemo_save.xsl";

    public static final String SHOWALLMOBINFOBYPAGE_HTM = "showAllMobInfoByPage.htm?CSRFToken=";
    public static final String LOGGERINFO1 = ",用户名：";
    public static final String APP_CONT = "app_cont";
    public static final String ROLL_ADV = "roll_adv";
    public static final String FULLROLL = "fullRoll";
    public static final String GOODSMOD = "goodsmod";
    public static final String BLANKBOX = "blankbox";
    public static final String DIVIDING = "dividing";
    public static final String STYLE = "style";
    public static final String WIDTHB = "widthB";
    public static final String HEIGHTB = "heightB";
    public static final String MUSICNAME = "musicname";

    XmlUtil xmlUtil = new XmlUtil();
    XmlElementUtil xmlElementUtil = new XmlElementUtil();

    /** 移动版单页服务层接口 */
    @Resource(name = "MobSinglepageService")
    private MobSinglepageService mobSinglepageService;

    /** 移动版单页标签service层接口 */
    @Resource(name = "MobSinglepageMarkService")
    private MobSinglepageMarkService mobSinglepageMarkService;

    /** 移动版单页可视化配置 service层接口 */
    @Resource(name = "MobHomeSinglePageService")
    private MobHomeSinglePageService mobHomeSinglePageService;

    /**
     * 分页查询移动版单页信息列表
     * 
     * @param pageBean
     * @param selectBean
     *            查询参数
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/showAllMobInfoByPage")
    public ModelAndView showAllMobInfoByPage(PageBean pageBean, SelectBean selectBean, HttpServletRequest request, HttpServletResponse response) {
        /**
         * 判断selectBean 中的searchText是否为空 如果为空就把selectBean的condition设置为空
         */
        if ("".equals(selectBean.getSearchText())) {
            selectBean.setCondition("");
        }
        request.setAttribute("selectBean", selectBean);
        return new ModelAndView("jsp/mobile/mobSinglePageList", "pb", mobSinglepageService.queryMobByPage(pageBean, selectBean));

    }

    /**
     * 跳转到修改或添加移动版单页的页面
     * 
     * @param singlepageId
     * @return
     */
    @RequestMapping("/toshowMobSinglePage")
    public ModelAndView toShowMobSinglePage(Long singlepageId, Long homepageId, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        /** 创建实体类--移动版单页xml内容 */
        MobHomeSinglePage mobHomeSinglePage = null;
        if (singlepageId != null) {
            /** 根据主键查询移动版单页信息 */
            mav.addObject("mobSinglepage", mobSinglepageService.selectByPrimaryKey(singlepageId));
            /** 根据移动版单页ID获取移动版模板信息 */
            mobHomeSinglePage = mobHomeSinglePageService.queryInfoBySinglepageId(singlepageId);
        } else {
            /** 初始化商家的HomePage */
            mobHomeSinglePage = this.mobHomeSinglePageService.initHomePage();
        }
        /** 获取doc并赋值给Document */
        Document doc = xmlUtil.str2Document(mobHomeSinglePage.getDoc());
        /** 获取文档路径 */
        String filePath = request.getSession().getServletContext().getRealPath("/");
        /** 获取文档名称 */
        String xmlFileName = xmlFilePath.replace(".xml", "_" + homepageId + ".xml");
        /** 得到文件名 */
        String fileName = filePath + xmlFileName;
        /** 创建、修改xml文件 */
        xmlUtil.createXml(fileName, doc);
        mav.addObject("xmlFilePath", xmlFileName);
        mav.addObject("mobHomeSinglePage", mobHomeSinglePage);
        mav.setViewName("jsp/mobile/showMobSinglePage");
        /** 查询所有移动版单页标签信息列表 */
        mav.addObject("mobSinglepageMark", mobSinglepageMarkService.selectAllMarkInfo());
        return mav;
    }

    /**
     * 添加移动版单页信息
     * 
     * @param mobSinglepage
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/insertMobInfo")
    @ResponseBody
    public ModelAndView insertMobInfo(@Valid MobSinglepage mobSinglepage, HttpServletRequest request, HttpServletResponse response, BindingResult bindingResult, long homespId,
            String ipCont) {
        /** 验证不通过就重定向到移动版单页信息列表页面 */
        if (bindingResult.hasErrors()) {
            return new ModelAndView(new RedirectView(SHOWALLMOBINFOBYPAGE_HTM + request.getParameter(ConstantUtil.CSRFTOKEN)));
        }

        LOGGER.debug("========添加移动版单页============");
        /** 验证单页辩题不为空 */
        if (null != mobSinglepage.getTitle()) {
            LOGGER.info("添加移动版单页信息成功，标题是：" + mobSinglepage.getTitle());
        }
        try {
            /** 获取session存储的登录id */
            Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
            /** 设置创建人 */
            mobSinglepage.setCreateUserId(loginUserId);
            /** 添加移动版单页 */
            mobSinglepageService.insertSelective(mobSinglepage);
            /** 根据ID获取模板信息 */
            MobHomeSinglePage mHomeSinglePage = mobHomeSinglePageService.getHomePageById(homespId);
            /** 设置单页ID */
            mHomeSinglePage.setSinglepageId(mobSinglepage.getSinglepageId());
            /** 保存移动版单页模板信息 */
            saveAllView(mHomeSinglePage, ipCont);
            /** 从session中获取用户名称 */
            String customerName = (String) request.getSession().getAttribute(NAME);
            /** 添加操作日志记录 */
            OperaLogUtil.addOperaLog(request, customerName, "添加移动版单页", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
        } catch (Exception e) {
            LOGGER.error("============添加移动版单页失败：" ,e);

        }
        /** 添加后重定向到移动版单页信息列表页面 */
        return new ModelAndView(new RedirectView(SHOWALLMOBINFOBYPAGE_HTM + request.getParameter(ConstantUtil.CSRFTOKEN)));
    }

    /**
     * 更新移动版单页信息
     * 
     * @param mobSinglepage
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/updateMobInfo")
    public ModelAndView updateMobInfo(@Valid MobSinglepage mobSinglepage, HttpServletRequest request, HttpServletResponse response, BindingResult bindingResult, String ipCont,
            long homespId) {
        /** 验证不通过就重定向到移动版单页信息列表页面 */
        if (bindingResult.hasErrors()) {
            return new ModelAndView(new RedirectView(SHOWALLMOBINFOBYPAGE_HTM + request.getParameter(ConstantUtil.CSRFTOKEN)));
        }
        LOGGER.debug("========更新移动版单页============");
        /** 验证单页辩题不为空 */
        if (null != mobSinglepage.getTitle()) {
            LOGGER.info("更新移动版单页信息，标题是：" + mobSinglepage.getTitle());
        }
        /** 获取session存储的登录id */
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        try {
            /** 设置更新人 */
            mobSinglepage.setUpdateUserId(loginUserId);
            /** 更新移动版单页 */
            mobSinglepageService.updateByPrimaryKeySelective(mobSinglepage);
            /** 根据ID获取模板信息 */
            MobHomeSinglePage mHomeSinglePage = mobHomeSinglePageService.getHomePageById(homespId);
            /** 保存移动版单页模板信息 */
            saveAllView(mHomeSinglePage, ipCont);
            /** 获取session存储的用户名称 */
            String customerName = (String) request.getSession().getAttribute(NAME);
            /** 记录操作日志 */
            OperaLogUtil.addOperaLog(request, customerName, "更新移动版单页标签", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
        } catch (Exception e) {
            LOGGER.error("============更新移动版单页失败：",e);
        }
        /** 添加后重定向到移动版单页信息列表页面 */
        return new ModelAndView(new RedirectView(SHOWALLMOBINFOBYPAGE_HTM + request.getParameter(ConstantUtil.CSRFTOKEN)));
    }

    /**
     * 逻辑删除移动版单页
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/updateMobDelStatus")
    @ResponseBody
    public void updateMobDelStatus(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.debug("========删除移动版单页============");
        try {
            String[] singlepageIds = request.getParameterValues("singlepageIds[]");
            for (int i = 0; i < singlepageIds.length; i++) {
                Long singlepageId = Long.valueOf(singlepageIds[i]);
                /** 逻辑删除 根据主键ID修改delflag的状态 0：未删除 1：已删除 */
                mobSinglepageService.updatedelstatus(singlepageId);
            }
            /** 获取session存储的用户名称 */
            String customerName = (String) request.getSession().getAttribute(NAME);
            /** 记录操作日志 */
            OperaLogUtil.addOperaLog(request, customerName, "删除移动版单页", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
        } catch (Exception e) {
            LOGGER.error("============删除移动版单页失败：" ,e);
        }

    }

    /**
     * 保存移动版单页模板信息
     * 
     * @param mHomeSinglePage
     * @param ipCont
     */
    public void saveAllView(MobHomeSinglePage mHomeSinglePage, String ipCont) {
        /** xml字符串转Document */
        Document document = xmlUtil.str2Document(mHomeSinglePage.getDoc());
        Element root = document.getDocumentElement();
        /** 清空 */
        xmlElementUtil.removeElement(root, APP_CONT);
        xmlElementUtil.removeElement(root, "img_adv");
        xmlElementUtil.removeElement(root, ROLL_ADV);
        xmlElementUtil.removeElement(root, FULLROLL);
        xmlElementUtil.removeElement(root, "text");
        xmlElementUtil.removeElement(root, GOODSMOD);
        xmlElementUtil.removeElement(root, BLANKBOX);
        xmlElementUtil.removeElement(root, DIVIDING);

        org.jsoup.nodes.Document doc = Jsoup.parse(ipCont);
        Elements items = doc.getElementsByClass("item");

        for (int i = 0; i < items.size(); i++) {
            org.jsoup.nodes.Element item = items.get(i);
            String itemId = item.attr("id");
            String id = itemId.substring(8);
            if (itemId.indexOf(APP_CONT) != -1) {// 魔方
                /** 创建新魔方模块 */
                Element newAppCont = root.getOwnerDocument().createElement(APP_CONT);
                /** 设置结点Element的属性 */
                xmlElementUtil.setElementAttr(newAppCont, "id", id);
                root.appendChild(newAppCont);

                Elements imgs = item.getElementsByClass("imgapp");
                for (int j = 0; j < imgs.size(); j++) {
                    org.jsoup.nodes.Element img = imgs.get(j);
                    String imgId = img.attr("id").substring(2);
                    String src = img.attr("src");
                    String href = img.parent().attr("href");
                    String sRow = imgId.substring(3, 4);
                    String sCol = imgId.substring(5, 6);
                    String rows = imgId.substring(7, 8);
                    String cols = imgId.substring(9);

                    int width = Integer.valueOf(cols) * 160;
                    int height = Integer.valueOf(rows) * 160;

                    /** 获取图片信息，并处理成后台预览用属性 */
                    Double widthvalue = (new Double(width)) / 2;
                    Double heightvalue = (new Double(height)) / 2;
                    String left = "left:" + ((Integer.valueOf(sCol) - 1) * 160 / 2 - 1) + "px;";
                    String top = "top:" + ((Integer.valueOf(sRow) - 1) * 160 / 2) + "px;";
                    String style = "position:absolute;" + left + top;
                    /** 前台展示用的属性 */
                    String widthB = (widthvalue / 320) * 100 + "%";
                    String heightB = (heightvalue / 320) * 100 + "%";
                    String leftB = (((new Double(sCol) - 1) * 160 / 2) / 320) * 100 + "%";
                    String topB = (((new Double(sRow) - 1) * 160 / 2) / 320) * 100 + "%";
                    String styleB = "position:absolute;left:" + leftB + ";top:" + topB + ";";
                    /** 创建a标签元素 */
                    Element newA = newAppCont.getOwnerDocument().createElement("a");
                    xmlElementUtil.setElementAttr(newA, "href", href);
                    /** 创建img元素 */
                    Element newImg = newA.getOwnerDocument().createElement("img");
                    xmlElementUtil.setElementAttr(newImg, "id", imgId);
                    xmlElementUtil.setElementAttr(newImg, "src", src);
                    xmlElementUtil.setElementAttr(newImg, "class", "imgapp");
                    xmlElementUtil.setElementAttr(newImg, ConstantUtil.WIDTH, widthvalue.toString());
                    xmlElementUtil.setElementAttr(newImg, ConstantUtil.HEIGHT, heightvalue.toString());
                    xmlElementUtil.setElementAttr(newImg, STYLE, style);
                    xmlElementUtil.setElementAttr(newImg, WIDTHB, widthB);
                    xmlElementUtil.setElementAttr(newImg, HEIGHTB, heightB);
                    xmlElementUtil.setElementAttr(newImg, "styleB", styleB);

                    newA.appendChild(newImg);
                    newAppCont.appendChild(newA);
                }
            } else if (itemId.indexOf("img_adv_") != -1) {// 广告图片
                org.jsoup.nodes.Element a = item.getElementsByTag("a").get(0);
                org.jsoup.nodes.Element img = a.getElementsByTag("img").get(0);
                String href = a.attr("href");
                String src = img.attr("src");

                /** 创建广告图片节点 */
                Element newImgAdv = root.getOwnerDocument().createElement("img_adv");
                xmlElementUtil.setElementAttr(newImgAdv, "id", id);
                root.appendChild(newImgAdv);

                /** 创建a标签元素 */
                Element newA = newImgAdv.getOwnerDocument().createElement("a");
                xmlElementUtil.setElementAttr(newA, "href", href);
                /** 创建img元素 */
                Element newImg = newA.getOwnerDocument().createElement("img");
                xmlElementUtil.setElementAttr(newImg, "src", src);
                xmlElementUtil.setElementAttr(newImg, ConstantUtil.WIDTH, "100%");
                xmlElementUtil.setElementAttr(newImg, WIDTHB, "100%");

                newA.appendChild(newImg);
                newImgAdv.appendChild(newA);
            } else if (itemId.indexOf(ROLL_ADV) != -1) {
                /** 轮播广告 */
                Elements slides = item.getElementsByClass("swiper-slide");
                /** 创建轮播广告节点 */
                Element newImgAdv = root.getOwnerDocument().createElement(ROLL_ADV);
                xmlElementUtil.setElementAttr(newImgAdv, "id", id);
                root.appendChild(newImgAdv);
                for (int j = 1; j < (slides.size() - 1); j++) {
                    org.jsoup.nodes.Element slide = slides.get(j);
                    org.jsoup.nodes.Element a = slide.getElementsByTag("a").get(0);
                    org.jsoup.nodes.Element img = a.getElementsByTag("img").get(0);
                    String href = a.attr("href");
                    String src = img.attr("src");
                    /** 创建a标签元素 */
                    Element newA = newImgAdv.getOwnerDocument().createElement("roll_a");
                    xmlElementUtil.setElementAttr(newA, "href", href);
                    /** 创建img元素 */
                    Element newImg = newA.getOwnerDocument().createElement("img");
                    xmlElementUtil.setElementAttr(newImg, "src", src);
                    xmlElementUtil.setElementAttr(newImg, ConstantUtil.WIDTH, "100%");
                    xmlElementUtil.setElementAttr(newImg, WIDTHB, "100%");
                    xmlElementUtil.setElementAttr(newImg, ConstantUtil.HEIGHT, "155px");
                    xmlElementUtil.setElementAttr(newImg, HEIGHTB, "50%");

                    newA.appendChild(newImg);
                    newImgAdv.appendChild(newA);
                }

            } else if (itemId.indexOf(FULLROLL) != -1) {
                /** 全屏轮播 */
                String sd = item.getElementsByClass("fullRollSD").get(0).val();
                String music = "";
                if (!item.getElementsByTag("audio").isEmpty()) {
                    music = item.getElementsByTag("audio").get(0).attr("src");
                }
                String musicname = "";
                if (!item.getElementsByClass(MUSICNAME).isEmpty()) {
                    musicname = item.getElementsByClass(MUSICNAME).get(0).val();
                }

                /** 创建全屏轮播节点 */
                Element newFullRoll = root.getOwnerDocument().createElement(FULLROLL);
                xmlElementUtil.setElementAttr(newFullRoll, "id", id);
                xmlElementUtil.setElementAttr(newFullRoll, "sd", sd);
                xmlElementUtil.setElementAttr(newFullRoll, "music", music);
                xmlElementUtil.setElementAttr(newFullRoll, MUSICNAME, musicname);
                root.appendChild(newFullRoll);
                Elements slides = item.getElementsByClass("swiper-slide");
                for (int j = 1; j < (slides.size() - 1); j++) {
                    org.jsoup.nodes.Element slide = slides.get(j);
                    org.jsoup.nodes.Element a = slide.getElementsByTag("a").get(0);
                    org.jsoup.nodes.Element img = a.getElementsByTag("img").get(0);
                    String href = a.attr("href");
                    String src = img.attr("src");
                    /** 创建a标签元素 */
                    Element newA = newFullRoll.getOwnerDocument().createElement("roll_a");
                    xmlElementUtil.setElementAttr(newA, "href", href);
                    /** 创建img元素 */
                    Element newImg = newA.getOwnerDocument().createElement("img");
                    xmlElementUtil.setElementAttr(newImg, "src", src);
                    xmlElementUtil.setElementAttr(newImg, WIDTHB, "100%");
                    xmlElementUtil.setElementAttr(newImg, HEIGHTB, "100%");

                    newA.appendChild(newImg);
                    newFullRoll.appendChild(newA);
                }
            } else if (itemId.indexOf("text_app") != -1) {
                /** 文字 */
                String content = item.html();
                /** 创建文字节点 */
                Element newText = root.getOwnerDocument().createElement("text");
                newText.setNodeValue(content);
                xmlElementUtil.setElementValue(newText, content);
                xmlElementUtil.setElementAttr(newText, "id", id);
                root.appendChild(newText);
            } else if (itemId.indexOf(GOODSMOD) != -1) {
                /** 商品 */
                String styleClass = item.attr("styleClass");
                /** 创建商品节点 */
                Element newGoodsmod = root.getOwnerDocument().createElement(GOODSMOD);
                xmlElementUtil.setElementAttr(newGoodsmod, "id", id);
                xmlElementUtil.setElementAttr(newGoodsmod, "class", styleClass);
                root.appendChild(newGoodsmod);
                Elements as = item.getElementsByTag("a");
                for (int j = 0; j < as.size(); j++) {
                    org.jsoup.nodes.Element a = as.get(j);
                    String href = a.attr("href");
                    String src = a.getElementsByTag("img").get(0).attr("src");
                    String name = a.getElementsByTag("h3").get(0).html();
                    String price = a.getElementsByTag("span").get(0).html();
                    /** 创建li标签元素 */
                    Element newLi = newGoodsmod.getOwnerDocument().createElement("li");
                    /** 创建a标签元素 */
                    Element newA = newLi.getOwnerDocument().createElement("goods_a");
                    xmlElementUtil.setElementAttr(newA, "href", href);
                    xmlElementUtil.setElementAttr(newA, "name", name);
                    xmlElementUtil.setElementAttr(newA, "price", price);
                    // 创建img元素
                    Element newImg = newA.getOwnerDocument().createElement("img");
                    xmlElementUtil.setElementAttr(newImg, "src", src);

                    newA.appendChild(newImg);
                    newLi.appendChild(newA);
                    newGoodsmod.appendChild(newLi);
                }
            } else if (itemId.indexOf(BLANKBOX) != -1) {
                /** 空白占位 */
                // String height =
                // item.getElementsByClass("blank-box").get(0).attr(STYLE).split(":")[1];
                String height = null;

                if (!"".equals(item.getElementsByClass("blank-box").get(0).attr(STYLE))) {
                    height = item.getElementsByClass("blank-box").get(0).attr(STYLE).split(":")[1];
                } else {
                    height = "30px";
                }
                /** 创建空白占位节点 */
                Element newBlankbox = root.getOwnerDocument().createElement(BLANKBOX);
                xmlElementUtil.setElementAttr(newBlankbox, "id", id);
                xmlElementUtil.setElementAttr(newBlankbox, ConstantUtil.HEIGHT, height);
                root.appendChild(newBlankbox);
            } else if (itemId.indexOf(DIVIDING) != -1) {
                /** 分隔线 */
                /** 创建分隔线节点 */
                Element newBlankbox = root.getOwnerDocument().createElement(DIVIDING);
                xmlElementUtil.setElementAttr(newBlankbox, "id", id);
                root.appendChild(newBlankbox);
            }

        }
        mHomeSinglePage.setDoc(xmlUtil.document2Str(document));

        // homePage.setTemp1(mkToken());
        // homePage.setHomepageId(null);
        // homePage.setTemp2("0");
        // mobHomePageService.createHomePage(homePage);
        mobHomeSinglePageService.updateHomePage(mHomeSinglePage);

    }

    /**
     * 获取MP3文件用于选择
     * 
     * @Title: searchMp3
     * @Description: 获取项目mobile_home_page文件夹下的MP3文件，和它们的属性
     * @param request
     * @return
     */
    @RequestMapping("/searchSingleMp3")
    @ResponseBody
    public List<Map<String, String>> searchMp3(HttpServletRequest request) {
        List<Map<String, String>> list = new ArrayList<>();
        /** 获取项目地址栏路径 */
        String rootPath = request.getContextPath();
        /** 获取ip地址 */
        /** 获取项目物理路径 */
        String root = request.getSession().getServletContext().getRealPath("/");
        Path dir = Paths.get(root + "mobile_single_home_page");
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir, "*.mp3")) {
            for (Path path : stream) {
                Map<String, String> map = new HashMap<>();
                /** 获取文件名 */
                map.put("mp3name", path.getFileName().toString());
                /** 获取文件地址 */
                map.put("mp3address", rootPath + "/mobile_single_home_page/" + path.getFileName().toString());
                /** 获取文件长度，单位kb */
                Long fileLength = path.toFile().length() / 1024;
                map.put("mp3length", String.valueOf(fileLength));
                /** 获取文件时长 */
                MP3File f = (MP3File) AudioFileIO.read(path.toFile());
                MP3AudioHeader audioHeader = (MP3AudioHeader) f.getAudioHeader();
                map.put("mp3time", audioHeader.getTrackLengthAsString());
                list.add(map);
            }
        } catch (final Exception e) {
            LOGGER.error("", e);
        }
        return list;
    }

}
