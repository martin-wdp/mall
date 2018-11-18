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
 * 移动版可视化首页商品模块控制器
 * 
 * @ClassName: MobHomeGoodsController
 * @Description: 移动版可视化首页商品模块的控制
 * @author Wanghy
 * @date 2014年10月28日 上午11:23:17
 * 
 */
@Controller
public class MobHomeGoodsController {

    private static final Logger LOGGER = Logger.getLogger(MobHomeGoodsController.class);

    private static final String SETMOBHOMEPAGE_HTM = "setMobHomePage.htm";
    private static final String PRICE = "price";
    private static final String GOODSMOD = "goodsmod";

    XmlUtil xmlUtil = new XmlUtil();
    XmlElementUtil xmlElementUtil = new XmlElementUtil();

    /** 商家ID */
    static final String xmlFilePath = "mobile_home_page/xml/9gdemo.xml";

    @Resource(name = "MobHomePageService")
    private MobHomePageService mobHomePageService;

    /**
     * 保存商品模块模块
     * 
     * @Title: saveRollAdv
     * @Description: 保存商品模块模块，包括添加、修改
     * @param merchantId
     *            商家ID
     * @param request
     * @return
     */
    @RequestMapping("/saveGoodsMob")
    public ModelAndView saveGoodsMob(Long merchantId, HttpServletRequest request) {
        String rsv = request.getParameter("rsv");
        // request中的rsv不等于null并且不等于session中的rsv，为重复提交
        if (null != rsv && !rsv.equals(request.getSession().getAttribute("rsv"))) {
            LOGGER.info("==========保存商品模块重复提交===========");
            return new ModelAndView(new RedirectView(SETMOBHOMEPAGE_HTM));
        }
        request.getSession().setAttribute("rsv", mkRSV());
        MobHomePage homePage = this.mobHomePageService.selectHomePageByMerchantId(merchantId);
        Document document = xmlUtil.str2Document(homePage.getDoc());
        Element root = document.getDocumentElement();

        // 获取商品图片信息
        String goodsmodId = request.getParameter("goodsmodId");
        String style = request.getParameter("style");
        String[] href = request.getParameterValues("href");
        String[] src = request.getParameterValues("src");
        String[] name = request.getParameterValues("name");
        String[] price = request.getParameterValues(PRICE);

        if (null != goodsmodId && !"".equals(goodsmodId)) {
            // 修改商品节点
            updateGoodsElement(root, goodsmodId, style, href, src, name, price);
        } else {
            // 添加商品节点
            createGoodsElement(root, style, href, src, name, price);
        }
        // 写入xml
        // xmlUtil.createXml(fileName);
        homePage.setDoc(xmlUtil.document2Str(document));
        homePage.setTemp1(mkToken());
        mobHomePageService.updateHomePage(homePage);
        return new ModelAndView(new RedirectView(SETMOBHOMEPAGE_HTM));
    }

    /*
     * 添加商品节点
     */
    private void createGoodsElement(Element root, String style, String[] href, String[] src, String[] name, String[] price) {
        // 计算商品模块的id
        Element[] goodsmods = xmlElementUtil.getElementsByName(root, GOODSMOD);
        int max = 0;
        for (Element el : goodsmods) {
            int temp = Integer.valueOf(el.getAttribute("id"));
            if (temp > max) {
                max = temp;
            }
        }
        // 创建商品节点
        Element newGoodsmod = root.getOwnerDocument().createElement(GOODSMOD);
        xmlElementUtil.setElementAttr(newGoodsmod, "id", String.valueOf(max + 1));
        xmlElementUtil.setElementAttr(newGoodsmod, "class", style);
        root.appendChild(newGoodsmod);
        for (int i = 0; i < href.length; i++) {
            // 创建li标签元素
            Element newLi = newGoodsmod.getOwnerDocument().createElement("li");
            // 创建a标签元素
            Element newA = newLi.getOwnerDocument().createElement("goods_a");
            xmlElementUtil.setElementAttr(newA, "href", href[i]);
            xmlElementUtil.setElementAttr(newA, "name", name[i]);
            xmlElementUtil.setElementAttr(newA, PRICE, price[i]);
            // 创建img元素
            Element newImg = newA.getOwnerDocument().createElement("img");
            xmlElementUtil.setElementAttr(newImg, "src", src[i]);

            newA.appendChild(newImg);
            newLi.appendChild(newA);
            newGoodsmod.appendChild(newLi);
        }
    }

    /*
     * 修改商品节点
     */
    private void updateGoodsElement(Element root, String goodsmodId, String style, String[] href, String[] src, String[] name, String[] price) {
        // 根据id找到节点
        Element oldGoods = null;
        Element[] goodsmods = xmlElementUtil.getElementsByName(root, GOODSMOD);
        for (Element el : goodsmods) {
            if (el.getAttribute("id").equals(goodsmodId)) {
                oldGoods = el;
                // 删除它的子节点
                Element[] as = xmlElementUtil.getElementsByName(el, "li");
                for (Element a : as) {
                    el.removeChild(a);
                }
            }
        }
        xmlElementUtil.setElementAttr(oldGoods, "class", style);
        for (int i = 0; i < href.length; i++) {
            // 创建li标签元素
            Element newLi = oldGoods.getOwnerDocument().createElement("li");
            // 创建a标签元素
            Element newA = newLi.getOwnerDocument().createElement("goods_a");
            xmlElementUtil.setElementAttr(newA, "href", href[i]);
            xmlElementUtil.setElementAttr(newA, "name", name[i]);
            xmlElementUtil.setElementAttr(newA, PRICE, price[i]);
            // 创建img元素
            Element newImg = newA.getOwnerDocument().createElement("img");
            xmlElementUtil.setElementAttr(newImg, "src", src[i]);

            newA.appendChild(newImg);
            newLi.appendChild(newA);
            oldGoods.appendChild(newLi);
        }
    }

    /**
     * 删除商品模块
     * 
     * @Title: deleteRollAdv
     * @Description: 根据商家ID查找模板对象，根据商品模块ID删除商品模块
     * @param merchantId
     *            商家ID
     * @param request
     * @return
     */
    @RequestMapping("/deleteGoodsMob")
    public ModelAndView deleteGoodsMob(Long merchantId, HttpServletRequest request) {
        String rsv = request.getParameter("rsv");
        // request中的rsv不等于null并且不等于session中的rsv，为重复提交
        if (null != rsv && !rsv.equals(request.getSession().getAttribute("rsv"))) {
            LOGGER.info("==========删除商品模块重复提交===========");
            return new ModelAndView(new RedirectView(SETMOBHOMEPAGE_HTM));
        }
        request.getSession().setAttribute("rsv", mkRSV());
        MobHomePage homePage = this.mobHomePageService.selectHomePageByMerchantId(merchantId);
        Document document = xmlUtil.str2Document(homePage.getDoc());
        // 获取商品模块的ID
        String id = request.getParameter("id");

        Element root = document.getDocumentElement();
        Element[] goodsmods = xmlElementUtil.getElementsByName(root, GOODSMOD);
        for (Element el : goodsmods) {
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
