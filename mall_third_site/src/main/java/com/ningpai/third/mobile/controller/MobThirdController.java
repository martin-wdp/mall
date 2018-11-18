package com.ningpai.third.mobile.controller;

import com.ningpai.common.util.ConstantUtil;
import com.ningpai.goods.service.GoodsProductService;
import com.ningpai.mobile.bean.MobHomePage;
import com.ningpai.mobile.service.MobHomePageService;
import com.ningpai.system.mobile.service.MobSiteBasicService;
import com.ningpai.third.util.MenuOperationUtil;
import com.ningpai.util.PageBean;
import com.ningpai.util.xml.XmlElementUtil;
import com.ningpai.util.xml.XmlUtil;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ningpai.third.seller.mapper.StoreInfoMapper;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 控制器-移动版首页设置
 * </p>
 *
 * @author QIANMI-JiaoDongZhi
 * @version 2.0
 * @since 2015年6月05日下午
 */
@Controller
public class MobThirdController {

    private static final String THIRDID = "thirdId";
    private static final String APPCONT = "app_cont";
    private static final String ROLLADV = "roll_adv";
    private static final String FULLROLL = "fullRoll";
    private static final String GOODSMOD = "goodsmod";
    private static final String BLANKBOX = "blankbox";
    private static final String DIVIDING = "dividing";
    private static final String STYLE = "style";
    private static final String WIDTHB = "widthB";
    private static final String HEIGHTB = "heightB";
    private static final String MUSICNAME = "musicname";
    private static final String BLANK = "blank-box";

    /**
     * xml工具类
     */
    XmlUtil xmlUtil = new XmlUtil();

    /**
     * getNodeList
     */
    XmlElementUtil xmlElementUtil = new XmlElementUtil();

    /**
     * 商家ID
     */
    static final Long STOREID = -1L;

    /**
     * 移动端
     */
    static final String xmlFilePath = "mobile_home_page/xml/9gdemo.xml";

    /**
     * 移动版可视化配置首页接口
     */
    @Resource(name = "MobHomePageService")
    private MobHomePageService mobHomePageService;

    /**
     * 移动版站点基础设置接口
     */
    @Resource(name = "MobSiteBasicService")
    private MobSiteBasicService mobSiteBasicService;

    /**
     * 店铺信息接口
     */
    @Resource(name = "sotreInfoMapper")
    public StoreInfoMapper sotreInfoMapper;

    /**
     * 货品信息Service接口
     */
    @Resource(name = "GoodsProductService")
    private GoodsProductService goodsProductService;

    /**
     * 日志对象
     */
    private static final Logger LOGGER = Logger.getLogger(MobThirdController.class);

    /**
     * 根据首页ID查看移动版首页，并进行编辑
     *
     * @param request
     * @return
     * @Title: setMobMain
     * @Description: 根据首页ID查看移动版首页，并进行编辑
     *               <p>
     *               获取首页对象后，通过XmlUtil工具类把该对象中保存的xml字符串转换成XML Document。
     *               </p>
     *               <p>
     *               再根据商家ID和首页ID生成xml文件，供后台调用xml生成html
     *               </p>
     */
    @RequestMapping("/setThirdMobHomePage")
    public ModelAndView setMobMain(String n, String l, HttpServletRequest request) {
        // log.debug("============进入移动版新首页配置控制器："+new
        // Date().toLocaleString()+"=================");
        request.getSession().setAttribute("rsv", mkRSV());
        // 填充导航/左侧菜单索引 用于页面控制css选中
        MenuOperationUtil.fillSessionMenuIndex(request, n, l);
        ModelAndView mav = new ModelAndView();
        // 当前登录的第三方店铺ID
        Long storeId = (Long) request.getSession().getAttribute(THIRDID);
        // 查询第三方移动模板信息
        MobHomePage homePage = mobHomePageService.selectThirdMob(storeId);
        if (null == homePage) {
            // homePage =
            // this.mobHomePageService.selectHomePageByMerchantId(merchantId);
            String isThird = "1";
            // 初始化商家的HomePage
            homePage = this.mobHomePageService.initHomePage(STOREID, isThird, storeId);
        }
        // xml字符串转Document
        Document doc = xmlUtil.str2Document(homePage.getDoc());
        String filePath = request.getSession().getServletContext().getRealPath("/");
        String xmlFileName = xmlFilePath.replace(".xml", "_" + homePage.getMerchantId() + "_" + homePage.getHomepageId() + ".xml");
        String fileName = filePath + xmlFileName;
        // 把doc写入用户的文件
        xmlUtil.createXml(fileName, doc);
        mav.addObject("xmlFilePath", xmlFileName);
        mav.addObject("merchantId", homePage.getMerchantId());
        // mav.addObject("homePageId", homePageId);
        mav.addObject("homePage", homePage);
        mav.addObject("mobSiteBasic", this.mobSiteBasicService.selectCurrMobSiteBasic(request));
        mav.setViewName("mobile/set_mob_home_page");
        // log.debug("============退出移动版新首页配置控制器："+new
        // Date().toLocaleString()+"=================");
        return mav;
    }

    /**
     * 查询移动版商品
     *
     * @param pname
     *            货品名称
     * @return
     * @Title: ajaxQueryMobProductForInnerJoin
     * @Description: 根据分类ID、货品名称来查询货品列表，以支持内连接
     */
    @RequestMapping("/ajaxQueryThirdMobProduct")
    @ResponseBody
    public Map<String, Object> ajaxQueryThirdMobProduct(PageBean pb, String pname, HttpServletRequest request) {
        // 装载查询条件
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> mapparam = new HashMap<>();
        // 商家ID
        Long storeId = (Long) request.getSession().getAttribute(THIRDID);
        // 商家名称
        mapparam.put("goodsInfoName", pname);
        // 商家Id
        mapparam.put("goodsBelo", storeId);
        pb.setObjectBean(mapparam);
        // 根据条件获取商家商品
        map.put("pb", goodsProductService.queryThirdProduct(pb, mapparam));
        return map;
    }

    /**
     * 查询移动版货品
     *
     * @param pb
     *            分页工具bean,可不传
     * @param goodsInfoNo
     *            商品编号,可为null
     * @param goodsInfoName
     *            货品名称,可为null,可模糊查询
     * @param request
     * @return 选择货品页面，此页面应该为弹出框
     * @Title: queryMobProductForGoods
     * @Description: 根据分类和名称查询移动版货品，用于移动版可视化配置首页商品模块选取
     */
    @RequestMapping("/queryThirdMobProductForGoods")
    public ModelAndView queryMobProductForGoods(PageBean pb, String goodsInfoName, String goodsInfoNo, Long size, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> mapparam = new HashMap<>();
        // 商家ID
        Long storeId = (Long) request.getSession().getAttribute(THIRDID);
        // 货品名称
        mapparam.put("goodsInfoName", goodsInfoName);
        // 商家ID
        mapparam.put("goodsBelo", storeId);
        // 商品编号
        mapparam.put("goodsInfoNo", goodsInfoNo);
        // 手机端显示
        mapparam.put("showMobile", "0");
        map.put("size", size);
        pb.setObjectBean(mapparam);
        // 根据条件查询商家的商品
        map.put("pb", goodsProductService.queryThirdProduct(pb, mapparam));
        // 设置需要跳转的页面
        return new ModelAndView("mobile_html/choose_product", "map", map);
    }

    /**
     * saveAllThirdMod
     * 
     * @param homePageId
     * @param request
     * @return
     */
    @RequestMapping("/saveAllThirdMod")
    public ModelAndView saveAllMod(Long homePageId, HttpServletRequest request) {
        request.getSession().setAttribute("rsv", mkRSV());
        MobHomePage homePage = this.mobHomePageService.getHomePageById(homePageId);
        Document document = xmlUtil.str2Document(homePage.getDoc());
        Element root = document.getDocumentElement();
        // 清空 将父结点parent下的名称为tagName的结点移除
        xmlElementUtil.removeElement(root, APPCONT);
        xmlElementUtil.removeElement(root, "img_adv");
        xmlElementUtil.removeElement(root, ROLLADV);
        xmlElementUtil.removeElement(root, FULLROLL);
        xmlElementUtil.removeElement(root, "text");
        xmlElementUtil.removeElement(root, GOODSMOD);
        xmlElementUtil.removeElement(root, BLANKBOX);
        xmlElementUtil.removeElement(root, DIVIDING);

        // 获取html内容
        String ipCont = request.getParameter("ipCont");
        org.jsoup.nodes.Document doc = Jsoup.parse(ipCont);
        Elements items = doc.getElementsByClass("item");

        for (int i = 0; i < items.size(); i++) {
            org.jsoup.nodes.Element item = items.get(i);
            String itemId = item.attr("id");
            String id = itemId.substring(8);
            if (itemId.indexOf(APPCONT) != -1) {// 魔方
                // 创建新魔方模块
                Element newAppCont = root.getOwnerDocument().createElement(APPCONT);
                // 设置结点Element的属性
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

                    // 获取图片信息，并处理成后台预览用属性
                    Double widthvalue = (new Double(width)) / 2;
                    Double heightvalue = (new Double(height)) / 2;
                    String left = "left:" + ((Integer.valueOf(sCol) - 1) * 160 / 2 - 1) + "px;";
                    String top = "top:" + ((Integer.valueOf(sRow) - 1) * 160 / 2) + "px;";
                    String style = "position:absolute;" + left + top;
                    // 前台展示用的属性
                    String widthB = (widthvalue / 320) * 100 + "%";
                    String heightB = (heightvalue / 320) * 100 + "%";
                    String leftB = (((new Double(sCol) - 1) * 160 / 2) / 320) * 100 + "%";
                    String topB = (((new Double(sRow) - 1) * 160 / 2) / 320) * 100 + "%";
                    String styleB = "position:absolute;left:" + leftB + ";top:" + topB + ";";
                    // 创建a标签元素
                    Element newA = newAppCont.getOwnerDocument().createElement("a");
                    // 设置结点Element的属性
                    xmlElementUtil.setElementAttr(newA, "href", href);
                    // 创建img元素
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

                // 创建广告图片节点
                Element newImgAdv = root.getOwnerDocument().createElement("img_adv");
                // 设置结点Element的属性
                xmlElementUtil.setElementAttr(newImgAdv, "id", id);
                root.appendChild(newImgAdv);

                // 创建a标签元素
                Element newA = newImgAdv.getOwnerDocument().createElement("a");
                xmlElementUtil.setElementAttr(newA, "href", href);
                // 创建img元素
                Element newImg = newA.getOwnerDocument().createElement("img");
                // 设置结点Element的属性
                xmlElementUtil.setElementAttr(newImg, "src", src);
                xmlElementUtil.setElementAttr(newImg, ConstantUtil.WIDTH, "100%");
                xmlElementUtil.setElementAttr(newImg, WIDTHB, "100%");

                newA.appendChild(newImg);
                newImgAdv.appendChild(newA);
            } else if (itemId.indexOf(ROLLADV) != -1) {// 轮播广告
                Elements slides = item.getElementsByClass("swiper-slide");
                // 创建轮播广告节点
                Element newImgAdv = root.getOwnerDocument().createElement(ROLLADV);
                // 设置结点Element的属性
                xmlElementUtil.setElementAttr(newImgAdv, "id", id);
                root.appendChild(newImgAdv);
                for (int j = 1; j < (slides.size() - 1); j++) {
                    org.jsoup.nodes.Element slide = slides.get(j);
                    org.jsoup.nodes.Element a = slide.getElementsByTag("a").get(0);
                    org.jsoup.nodes.Element img = a.getElementsByTag("img").get(0);
                    String href = a.attr("href");
                    String src = img.attr("src");
                    // 创建a标签元素
                    Element newA = newImgAdv.getOwnerDocument().createElement("roll_a");
                    xmlElementUtil.setElementAttr(newA, "href", href);
                    // 创建img元素
                    Element newImg = newA.getOwnerDocument().createElement("img");
                    xmlElementUtil.setElementAttr(newImg, "src", src);
                    xmlElementUtil.setElementAttr(newImg, ConstantUtil.WIDTH, "100%");
                    xmlElementUtil.setElementAttr(newImg, WIDTHB, "100%");
                    xmlElementUtil.setElementAttr(newImg, ConstantUtil.HEIGHT, "155px");
                    xmlElementUtil.setElementAttr(newImg, HEIGHTB, "50%");

                    newA.appendChild(newImg);
                    newImgAdv.appendChild(newA);
                }

            } else if (itemId.indexOf(FULLROLL) != -1) {// 全屏轮播
                String sd = item.getElementsByClass("fullRollSD").get(0).val();
                String music = "";
                if (!item.getElementsByTag("audio").isEmpty()) {
                    music = item.getElementsByTag("audio").get(0).attr("src");
                }
                String musicname = "";
                if (!item.getElementsByClass(MUSICNAME).isEmpty()) {
                    musicname = item.getElementsByClass(MUSICNAME).get(0).val();
                }

                // 创建全屏轮播节点
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
                    // 创建a标签元素
                    Element newA = newFullRoll.getOwnerDocument().createElement("roll_a");
                    xmlElementUtil.setElementAttr(newA, "href", href);
                    // 创建img元素
                    Element newImg = newA.getOwnerDocument().createElement("img");
                    xmlElementUtil.setElementAttr(newImg, "src", src);
                    xmlElementUtil.setElementAttr(newImg, WIDTHB, "100%");
                    xmlElementUtil.setElementAttr(newImg, HEIGHTB, "100%");

                    newA.appendChild(newImg);
                    newFullRoll.appendChild(newA);
                }
            } else if (itemId.indexOf("text_app") != -1) {// 文字
                String content = item.html();
                // 创建文字节点
                Element newText = root.getOwnerDocument().createElement("text");
                newText.setNodeValue(content);
                xmlElementUtil.setElementValue(newText, content);
                xmlElementUtil.setElementAttr(newText, "id", id);
                root.appendChild(newText);
            } else if (itemId.indexOf(GOODSMOD) != -1) {// 商品
                String styleClass = item.attr("styleClass");
                // 创建商品节点
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
                    // 创建li标签元素
                    Element newLi = newGoodsmod.getOwnerDocument().createElement("li");
                    // 创建a标签元素
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
            } else if (itemId.indexOf(BLANKBOX) != -1) {// 空白占位
                String height = "30px;";
                if (item.getElementsByClass(BLANK) != null && item.getElementsByClass(BLANK).get(0) != null && item.getElementsByClass(BLANK).get(0).attr(STYLE) != null && item.getElementsByClass(BLANK).get(0).attr(STYLE).split(":").length > 1) {
                        height = item.getElementsByClass(BLANK).get(0).attr(STYLE).split(":")[1];
                }

                // 创建空白占位节点
                Element newBlankbox = root.getOwnerDocument().createElement(BLANKBOX);
                xmlElementUtil.setElementAttr(newBlankbox, "id", id);
                xmlElementUtil.setElementAttr(newBlankbox, ConstantUtil.HEIGHT, height);
                root.appendChild(newBlankbox);
            } else if (itemId.indexOf(DIVIDING) != -1) {// 分隔线
                // 创建分隔线节点
                Element newBlankbox = root.getOwnerDocument().createElement(DIVIDING);
                xmlElementUtil.setElementAttr(newBlankbox, "id", id);
                root.appendChild(newBlankbox);
            }

        }
        homePage.setDoc(xmlUtil.document2Str(document));
        // homePage.setTemp1(mkToken());
        // homePage.setHomepageId(null);
        // homePage.setTemp2("0");
        // mobHomePageService.createHomePage(homePage);
        // 获取当商品ID
        Long thirdId = (Long) request.getSession().getAttribute("thirdId");
        homePage.setStoreId(thirdId);
        mobHomePageService.updateHomePage(homePage);
        return new ModelAndView(new RedirectView("setThirdMobHomePage.htm"));
    }

    /**
     * 修改移动版站点设置分享信息
     *
     * @param homePage
     * @param request
     * @return
     * @Title: updateMobHomePageForShare
     * @Description: 修改移动版站点设置分享信息
     */
    @RequestMapping("/updateThirdMobHomePageSet")
    public ModelAndView updateThirdMobHomePageSet(MobHomePage homePage, HttpServletRequest request) {
        String rsv = request.getParameter("rsv");
        // request中的rsv不等于null并且不等于session中的rsv，为重复提交
        if (null != rsv && !rsv.equals(request.getSession().getAttribute("rsv"))) {
            LOGGER.info("==========删除空白占位重复提交===========");
            return new ModelAndView(new RedirectView("setThirdMobHomePage.html"));
        }
        request.getSession().setAttribute("rsv", mkRSV());
        // 更新店铺信息和转发信息
        this.mobHomePageService.updateHomePage(homePage);
        return new ModelAndView(new RedirectView("setThirdMobHomePage.html"));
    }

    /**
     * 生成重复提交token
     * 
     * @return
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
