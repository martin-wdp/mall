/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.index.service;

import com.ningpai.channel.bean.ChannelAdver;
import com.ningpai.channel.bean.ChannelBar;
import com.ningpai.channel.service.ChannelAdverService;
import com.ningpai.channel.service.ChannelBarService;
import com.ningpai.channel.service.ChannelGoodsService;
import com.ningpai.channel.service.ChannelTrademarkService;
import com.ningpai.coupon.service.CouponService;
import com.ningpai.customer.bean.Customer;
import com.ningpai.hotsearch.bean.HotSearch;
import com.ningpai.hotsearch.service.HotSearchService;
import com.ningpai.index.bean.HelpCateBean;
import com.ningpai.index.bean.IndexClassifyBar;
import com.ningpai.index.util.IndexStaticizeUtil;
import com.ningpai.information.service.InformationService;
import com.ningpai.site.customer.service.CustomerServiceInterface;
import com.ningpai.site.customer.vo.CustomerConstantStr;
import com.ningpai.system.bean.HelpCate;
import com.ningpai.system.bean.SeoConf;
import com.ningpai.system.service.*;
import com.ningpai.temp.bean.SysTemp;
import com.ningpai.temp.bean.TempToken;
import com.ningpai.temp.service.TempService;
import com.ningpai.temp.service.TempTokenService;
import com.ningpai.util.MyLogger;
import com.qpmall.unit.memcached.MemCached;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.*;

/**
 * SERVICE-获取头部、底部信息
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年7月5日下午1:44:50
 */
@Service("TopAndBottomService")
public class TopAndBottomService {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(TopAndBottomService.class);

    private static final Long ATID1 = 157L;
    private static final Long ATID2 = 159L;
    private static final Long ATID3 = 161L;
    private static final Long ADVERTTYPE = 151L;

    private static final String SYSTEMBASE = "systembase";
    private static final String TOPMAP = "topmap";
    private static final String HELPCATES = "helpCates";
    private static final String HELPCENTERS = "helpCenters";
    private static final String BOTTOM = "bottom";
    private static final String VIEW_INDEX = "/view/index";
    private static final String INDEX14_HTML = "/index14.html";
    private static final String TOKEN = "token";
    private static final String REQUEST = "request";
    private static final String LOGGERINFO1 = "生成静态头部错误：=>";
    private static final String LOGGERINFO2 = "生成静态首页错误：=>";

    @Autowired
    private HttpServletRequest request;

    /** 站点信息业务接口 */
    @Resource(name = "basicSetService")
    private BasicSetService basicSetService;

    /** 站点SEO业务接口 */
    @Resource(name = "seoConfBizImpl")
    private ISeoConfBiz seoconfbiz;

    /** 模板设置业务类 */
    @Resource(name = "TempService")
    private TempService tempService;

    /** 首页内容业务类 */
    @Resource(name = "IndexSiteService")
    private IndexSiteService indexSiteService;

    /** 导航菜单业务类 */
    @Resource(name = "ChannelBarService")
    private ChannelBarService channelBarService;

    /** 热门搜索 */
    @Resource(name = "hotSearchService")
    private HotSearchService hotSearchService;

    /** 帮助类型业务接口 */
    @Resource(name = "helpCateService")
    private HelpCateService helpCateService;
    /** 帮助业务接口 */
    @Resource(name = "helpCenterService")
    private HelpCenterService helpCenterService;

    /** 广告业务接口 */
    @Resource(name = "ChannelAdverService")
    private ChannelAdverService channelAdverService;

    /** 文章业务类 */
    @Resource(name = "InformationService")
    private InformationService infoService;
    /** 热销推荐业务接口 */
    @Resource(name = "ChannelGoodsService")
    private ChannelGoodsService channelGoodsService;

    @Resource(name = "TempTokenService")
    private TempTokenService tokenService;

    /**
     * spring 注入属性
     */
    @Resource(name = "customerServiceSite")
    private CustomerServiceInterface customerServiceInterface;
    /**
     * 在线客服service
     */
    @Resource(name = "onLineServiceItemBizImpl")
    private IOnLineServiceItemBiz onLineServiceItemService;
    @Resource(name = "onLineServiceBizImpl")
    private IOnLineServiceBiz onLineServiceBiz;

    /** 优惠券service */
    private CouponService couponService;

    @Resource(name = "ChannelTrademarkService")
    private ChannelTrademarkService channelTrademarkService;

    /**
     * 新加载头部和底部信息<br/>
     * 传入ModelAndView对象，获取到头部信息和尾部信息后添加进ModelAndView中<br/>
     * topmap.systembase：头部信息map中的站点信息对象
     *
     * @param mav
     *            ModelAndView对象
     * @return
     */
    public ModelAndView getTopAndBottom(ModelAndView mav) {
        // 模板id
        int tempId = getIndexDefalutTemp().getTempId();
        if (tempId == 8) {
            // 验证模板内容变更three
            checkChangeTempThree();
        } else if (tempId == 9) {
            // 验证模板内容变更Four
            checkChangeTempFour();
        } else if (tempId == 10) {
            // 验证模板内容变更five
            checkChangeTempFive();
        } else if (tempId == 11) {
            // //验证模板内容变更six
            checkChangeTempSix();
        } else if (tempId == 12) {
            // 验证模板内容变更seven
            checkChangeTempSeven();
        } else if (tempId == 13) {
            // 验证模板内容变更eight
            checkChangeTempEight();
        } else if (tempId == 14) {
            // 验证模板内容变更nine
            checkChangeTempNine();
        } else if (tempId == 15) {
            // 验证模板内容变更ten
            checkChangeTempTen();
        } else if (tempId == 16) {
            // 验证模板内容变更eleven
            checkChangeTempEleven();
        } else if (tempId == 17) {
            // 验证模板内容变更twelve
            checkChangeTempTwelve();
        } else if (tempId == 18) {
            // 验证模板内容变更thirteen
            checkChangeTempThirteen();
        } else {
            // 验证模板内容变更
            checkChangeTemp();
        }

        Map<String, Object> topmap = (Map<String, Object>)MemCached.getInstance().get("topmap");
        if(topmap==null){
             /* 头部信息 */
            topmap = new HashMap<String, Object>();
            // 1.加载启用的模板信息
            topmap.put("temp", getIndexDefalutTemp());
            topmap.put("seo", getSeo());
            // 2.加载热门搜索
            topmap.put("hotsearch", getRandomHotSearch(hotSearchService.selectHotBySelectiveForSite(Long.valueOf(tempId), null)));
            // 4.加载站点信息
            topmap.put(SYSTEMBASE, basicSetService.findBasicSet());
            MemCached.getInstance().add("topmap",topmap,new Date(new Date().getTime()+86400000));
        }

        mav.addObject(TOPMAP, topmap);

        Map<String, Object> bottom = (Map<String, Object>)MemCached.getInstance().get("bottom");
        if(bottom==null){
             /* 尾部信息 */
            bottom = new HashMap<String, Object>();
            // 帮助模块
            bottom.put(HELPCATES, getHelpCateList());
            // 底部单独帮助
            bottom.put(HELPCENTERS, this.helpCenterService.selectAllIsFoot());
            MemCached.getInstance().add("bottom",bottom,new Date(new Date().getTime()+86400000));

        }

        mav.addObject(BOTTOM, bottom);
        return mav;
    }

    /**
     * 新加载底部信息<br/>
     * 传入ModelAndView对象，获取到头部信息和尾部信息后添加进ModelAndView中<br/>
     * topmap.systembase：头部信息map中的站点信息对象
     *
     * @param mav
     *            ModelAndView对象
     * @return
     */
    public ModelAndView getBottom(ModelAndView mav) {
        // 模板Id
        int tempId = getIndexDefalutTemp().getTempId();
        if (tempId == 8) {
            // 验证模板内容变更three
            checkChangeTempThree();
        } else if (tempId == 9) {
            // 验证模板内容变更four
            checkChangeTempFour();
        } else if (tempId == 10) {
            // 验证模板内容变更five
            checkChangeTempFive();
        } else if (tempId == 11) {
            // 验证模板内容变更six
            checkChangeTempSix();
        } else if (tempId == 12) {
            // 验证模板内容变更seven
            checkChangeTempSeven();
        } else if (tempId == 13) {
            // 验证模板内容变更eight
            checkChangeTempEight();
        } else if (tempId == 14) {
            // 验证模板内容变更nine
            checkChangeTempNine();
        } else if (tempId == 15) {
            // 验证模板内容变更ten
            checkChangeTempTen();
        } else if (tempId == 16) {
            // 验证模板内容变更eleven
            checkChangeTempEleven();
        } else if (tempId == 17) {
            // 验证模板内容变更twelve
            checkChangeTempTwelve();
        } else if (tempId == 18) {
            // 验证模板内容变更thirteen
            checkChangeTempThirteen();
        } else if (tempId == 19) {
            // 验证模板内容变更fourteen
            checkChangeTempFourteen();
        } else if (tempId == 20) {
            // 验证模板内容变更fifteen
            checkChangeTempFifteen();
        } else {
            // 验证模板内容变更
            checkChangeTemp();
        }
        /* 尾部信息 */
        Map<String, Object> bottom = new HashMap<String, Object>();
        Map<String, Object> topmap = new HashMap<String, Object>();
        // 1.加载启用的模板信息
        SysTemp temp = getIndexDefalutTemp();
        mav.addObject("temp", temp);
        topmap.put("temp", temp);
        topmap.put("seo", getSeo());
        // 4.加载站点信息
        topmap.put(SYSTEMBASE, basicSetService.findBasicSet());
        // 帮助模块
        bottom.put(HELPCATES, getHelpCateList());
        // 底部单独帮助
        bottom.put(HELPCENTERS, this.helpCenterService.selectAllIsFoot());
        // 获取logo旁边的页面广告
        List<ChannelAdver> pageAdvList = channelAdverService.selectchannelAdverByParamForSite(null, Long.valueOf(temp.getTempId()), null, null, ATID3, ADVERTTYPE, null, "0", null,
                "0");
        if (!pageAdvList.isEmpty()) {
            topmap.put("logoAdv", pageAdvList.get(0));
        }
        mav.addObject(BOTTOM, bottom);
        mav.addObject(TOPMAP, topmap);
        return mav;
    }

    /**
     * 新加载简易头部和尾部信息<br/>
     * 传入ModelAndView对象，获取到头部信息和尾部信息后添加进ModelAndView中<br/>
     * topmap.systembase：头部信息map中的站点信息对象
     *
     * @param mav
     *            ModelAndView对象
     * @return
     */
    public ModelAndView getSimpleTopAndBottom(ModelAndView mav) {
        /* 头部信息 */
        Map<String, Object> top = new HashMap<String, Object>();
        // 1.加载启用的模板信息
        SysTemp temp = getIndexDefalutTemp();
        top.put("temp", temp);

        // 4.加载站点信息
        top.put(SYSTEMBASE, basicSetService.findBasicSet());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("usedStatus", "1");
        map.put("deleteStatus", "0");
        top.put("seo", getSeo());
        mav.addObject(TOPMAP, top);

        /* 尾部信息 */
        Map<String, Object> bottom = new HashMap<String, Object>();
        // 帮助模块
        bottom.put(HELPCATES, getHelpCateList());
        // 底部单独帮助
        bottom.put(HELPCENTERS, this.helpCenterService.selectAllIsFoot());
        mav.addObject(BOTTOM, bottom);
        return mav;
    }

    /**
     * 验证模板内容变更
     */
    @SuppressWarnings("deprecation")
    private void checkChangeTemp() {

        if (checkToken(request)) {
            // 检查头部静态页面是否存在，不存在执行头部静态化方法
            String templatePath = request.getRealPath("/") + VIEW_INDEX;
            String topHtmlPath = templatePath + "/newheader.html";
            File topHtml = new File(topHtmlPath);
            if (!topHtml.exists()) {
                try {
                    // 静态化头部
                    this.staticizeTop(request);
                } catch (Exception e) {
                    LOGGER.error("", e);
                }
            }
            // 检查首页静态页面是否存在，不存在执行首页静态化方法
            String indexHtmlPath = templatePath + "/index2.html";
            File indexHtml = new File(indexHtmlPath);
            if (!indexHtml.exists()) {
                try {
                    // 静态化首页
                    this.staticizeIndex(request);
                } catch (Exception e) {
                    LOGGER.info(e);
                }
            }
        } else {
            this.staticizeTop(request);
            this.staticizeIndex(request);
        }
    }

    /**
     * 验证模板内容变更
     *
     */
    @SuppressWarnings("deprecation")
    private void checkChangeTempThree() {

        if (checkToken(request)) {
            // 检查头部静态页面是否存在，不存在执行头部静态化方法
            String templatePath = request.getRealPath("/") + VIEW_INDEX;
            String topHtmlPath = templatePath + "/newheader3.html";
            File topHtml = new File(topHtmlPath);
            if (!topHtml.exists()) {
                try {
                    // 静态化头部
                    this.staticizeTopThree(request);
                } catch (Exception e) {
                    LOGGER.info(e);
                }
            }
            // 检查首页静态页面是否存在，不存在执行首页静态化方法
            String indexHtmlPath = templatePath + "/index3.html";
            File indexHtml = new File(indexHtmlPath);
            if (!indexHtml.exists()) {
                try {
                    // 静态化首页
                    this.staticizeIndexThree(request);
                } catch (Exception e) {
                    LOGGER.info(e);
                }
            }

        } else {
            // 静态化头部
            this.staticizeTopThree(request);
            // 静态化首页
            this.staticizeIndexThree(request);
        }
    }

    /**
     * 验证模板内容变更Four
     * 
     *
     */
    @SuppressWarnings("deprecation")
    private void checkChangeTempFour() {

        if (checkToken(request)) {
            // 检查头部静态页面是否存在，不存在执行头部静态化方法
            String templatePath = request.getRealPath("/") + VIEW_INDEX;
            String topHtmlPath = templatePath + "/newheader4.html";
            File topHtml = new File(topHtmlPath);
            if (!topHtml.exists()) {
                try {
                    // 静态化头部
                    this.staticizeTopFour(request);
                } catch (Exception e) {
                    LOGGER.info(e);
                }
            }
            // 检查首页静态页面是否存在，不存在执行首页静态化方法
            String indexHtmlPath = templatePath + "/index4.html";
            File indexHtml = new File(indexHtmlPath);
            if (!indexHtml.exists()) {
                try {
                    // 静态化首页
                    this.staticizeIndexFour(request);
                } catch (Exception e) {
                    LOGGER.info(e);
                }
            }

        } else {
            // 静态化头部
            this.staticizeTopFour(request);
            // 静态化首页
            this.staticizeIndexFour(request);
        }
    }

    /**
     * 验证模板内容变更Five
     * 
     *
     */
    @SuppressWarnings("deprecation")
    private void checkChangeTempFive() {

        if (checkToken(request)) {
            // 检查头部静态页面是否存在，不存在执行头部静态化方法
            String templatePath = request.getRealPath("/") + VIEW_INDEX;
            String topHtmlPath = templatePath + "/newheader5.html";
            File topHtml = new File(topHtmlPath);
            if (!topHtml.exists()) {
                try {
                    // 静态化头部
                    this.staticizeTopFive(request);
                } catch (Exception e) {
                    LOGGER.info(e);
                }
            }
            // 检查首页静态页面是否存在，不存在执行首页静态化方法
            String indexHtmlPath = templatePath + "/index5.html";
            File indexHtml = new File(indexHtmlPath);
            if (!indexHtml.exists()) {
                try {
                    // 静态化首页
                    this.staticizeIndexFive(request);
                } catch (Exception e) {
                    LOGGER.info(e);
                }
            }

        } else {
            // 静态化头部
            this.staticizeTopFive(request);
            // 静态化首页
            this.staticizeIndexFive(request);
        }
    }

    /**
     * 验证模板内容变更Six
     * 
     *
     */
    @SuppressWarnings("deprecation")
    private void checkChangeTempSix() {

        if (checkToken(request)) {
            // 检查头部静态页面是否存在，不存在执行头部静态化方法
            String templatePath = request.getRealPath("/") + VIEW_INDEX;
            String topHtmlPath = templatePath + "/newheader6.html";
            File topHtml = new File(topHtmlPath);
            if (!topHtml.exists()) {
                try {
                    // 静态化头部
                    this.staticizeTopSix(request);
                } catch (Exception e) {
                    LOGGER.info(e);
                }
            }
            // 检查首页静态页面是否存在，不存在执行首页静态化方法
            String indexHtmlPath = templatePath + "/index6.html";
            File indexHtml = new File(indexHtmlPath);
            if (!indexHtml.exists()) {
                try {
                    // 静态化首页
                    this.staticizeIndexSix(request);
                } catch (Exception e) {
                    LOGGER.info(e);
                }
            }

        } else {
            // 静态化头部
            this.staticizeTopSix(request);
            // 静态化首页
            this.staticizeIndexSix(request);
        }

    }

    /**
     * 验证模板内容变更Seven
     * 
     *
     */
    @SuppressWarnings("deprecation")
    private void checkChangeTempSeven() {

        if (checkToken(request)) {
            // 检查头部静态页面是否存在，不存在执行头部静态化方法
            String templatePath = request.getRealPath("/") + VIEW_INDEX;
            String topHtmlPath = templatePath + "/newheader7.html";
            File topHtml = new File(topHtmlPath);
            //2015-12-12 wuyanbo 开启静态数据页面加载
            //if (!topHtml.exists()) {
                try {
                    // 静态化头部
                    this.staticizeTopSeven(request);
                } catch (Exception e) {
                    LOGGER.info(e);
                }
            //}
            // 检查首页静态页面是否存在，不存在执行首页静态化方法
            String indexHtmlPath = templatePath + "/index7.html";
            File indexHtml = new File(indexHtmlPath);
            //if (!indexHtml.exists()) {
                try {
                    // 静态化首页
                    this.staticizeIndexSeven(request);
                } catch (Exception e) {
                    LOGGER.info(e);
                }
            //}

        } else {
            // 静态化头部
            this.staticizeTopSeven(request);
            // 静态化首页
            this.staticizeIndexSeven(request);
        }

    }

    /**
     * 验证模板内容变更Eight
     * 
     *
     */
    @SuppressWarnings("deprecation")
    private void checkChangeTempEight() {

        if (checkToken(request)) {
            // 检查头部静态页面是否存在，不存在执行头部静态化方法
            String templatePath = request.getRealPath("/") + VIEW_INDEX;
            String topHtmlPath = templatePath + "/newheader8.html";
            File topHtml = new File(topHtmlPath);
            if (!topHtml.exists()) {
                try {
                    // 静态化头部
                    this.staticizeTopEight(request);
                } catch (Exception e) {
                    LOGGER.info(e);
                }
            }
            // 检查头部静态页面是否存在，不存在执行头部静态化方法
            String templatePaths = request.getRealPath("/") + VIEW_INDEX;
            String topHtmlPaths = templatePaths + "/newheader8new.html";
            File topHtmls = new File(topHtmlPaths);
            if (!topHtmls.exists()) {
                try {
                    // 静态化首页
                    this.staticizeTopNewEight(request);
                } catch (Exception e) {
                    LOGGER.info(e);
                }
            }
            // 检查首页静态页面是否存在，不存在执行首页静态化方法
            String indexHtmlPath = templatePath + "/index8.html";
            File indexHtml = new File(indexHtmlPath);
            if (!indexHtml.exists()) {
                try {
                    // 静态化首页
                    this.staticizeIndexEight(request);
                } catch (Exception e) {
                    LOGGER.info(e);
                }
            }

        } else {
            // 静态化头部
            this.staticizeTopEight(request);
            this.staticizeTopNewEight(request);
            // 静态化首页
            this.staticizeIndexEight(request);
        }

    }

    /**
     * 验证模板内容变更Nine
     * 
     *
     */
    @SuppressWarnings("deprecation")
    private void checkChangeTempNine() {

        if (checkToken(request)) {
            // 检查头部静态页面是否存在，不存在执行头部静态化方法
            String templatePath = request.getRealPath("/") + VIEW_INDEX;
            String topHtmlPath = templatePath + "/newheader9.html";
            File topHtml = new File(topHtmlPath);
            if (!topHtml.exists()) {
                try {
                    // 静态化头部
                    this.staticizeTopNine(request);
                } catch (Exception e) {
                    LOGGER.info(e);
                }
            }
            // 检查首页静态页面是否存在，不存在执行首页静态化方法
            String indexHtmlPath = templatePath + "/index9.html";
            File indexHtml = new File(indexHtmlPath);
            if (!indexHtml.exists()) {
                try {
                    // 静态化首页
                    this.staticizeIndexNine(request);
                } catch (Exception e) {
                    LOGGER.info(e);
                }
            }

        } else {
            // 静态化头部
            this.staticizeTopNine(request);
            // 静态化首页
            this.staticizeIndexNine(request);
        }

    }

    /**
     * 验证模板内容变更Ten
     * 
     *
     */
    @SuppressWarnings("deprecation")
    private void checkChangeTempTen() {

        if (checkToken(request)) {
            // 检查头部静态页面是否存在，不存在执行头部静态化方法
            String templatePath = request.getRealPath("/") + VIEW_INDEX;
            String topHtmlPath = templatePath + "/newheader10.html";
            File topHtml = new File(topHtmlPath);
            if (!topHtml.exists()) {
                try {
                    // 静态化头部
                    this.staticizeTopTen(request);
                } catch (Exception e) {
                    LOGGER.info(e);
                }
            }
            // 检查首页静态页面是否存在，不存在执行首页静态化方法
            String indexHtmlPath = templatePath + "/index10.html";
            File indexHtml = new File(indexHtmlPath);
            if (!indexHtml.exists()) {
                try {
                    // 静态化首页
                    this.staticizeIndexTen(request);
                } catch (Exception e) {
                    LOGGER.info(e);
                }
            }

        } else {
            // 静态化头部
            this.staticizeTopTen(request);
            // 静态化首页
            this.staticizeIndexTen(request);
        }

    }

    /**
     * 验证模板内容变更Eleven
     * 
     *
     */
    @SuppressWarnings("deprecation")
    private void checkChangeTempEleven() {

        if (checkToken(request)) {
            // 检查头部静态页面是否存在，不存在执行头部静态化方法
            String templatePath = request.getRealPath("/") + VIEW_INDEX;
            String topHtmlPath = templatePath + "/newheader11.html";
            File topHtml = new File(topHtmlPath);
            if (!topHtml.exists()) {
                try {
                    // 静态化头部
                    this.staticizeTopEleven(request);
                } catch (Exception e) {
                    LOGGER.info(e);
                }
            }
            // 检查首页静态页面是否存在，不存在执行首页静态化方法
            String indexHtmlPath = templatePath + "/index11.html";
            File indexHtml = new File(indexHtmlPath);
            if (!indexHtml.exists()) {
                try {
                    // 静态化首页
                    this.staticizeIndexEleven(request);
                } catch (Exception e) {
                    LOGGER.info(e);
                }
            }

        } else {
            // 静态化头部
            this.staticizeTopEleven(request);
            // 静态化首页
            this.staticizeIndexEleven(request);
        }

    }

    /**
     * 验证模板内容变更Twelve
     * 
     *
     */
    @SuppressWarnings("deprecation")
    private void checkChangeTempTwelve() {

        if (checkToken(request)) {
            // 检查头部静态页面是否存在，不存在执行头部静态化方法
            String templatePath = request.getRealPath("/") + VIEW_INDEX;
            String topHtmlPath = templatePath + "/newheader12.html";
            File topHtml = new File(topHtmlPath);
            if (!topHtml.exists()) {
                try {
                    // 静态化头部
                    this.staticizeTopTwelve(request);
                } catch (Exception e) {
                    LOGGER.info(e);
                }
            }
            // 检查首页静态页面是否存在，不存在执行首页静态化方法
            String indexHtmlPath = templatePath + "/index12.html";
            File indexHtml = new File(indexHtmlPath);
            if (!indexHtml.exists()) {
                try {
                    // 静态化首页
                    this.staticizeIndexTwelve(request);
                } catch (Exception e) {
                    LOGGER.info(e);
                }
            }
        } else {
            // 静态化头部
            this.staticizeTopTwelve(request);
            // 静态化首页
            this.staticizeIndexTwelve(request);
        }
    }

    /**
     * 验证模板内容变更Thirteen
     * 
     *
     */
    @SuppressWarnings("deprecation")
    private void checkChangeTempThirteen() {

        if (checkToken(request)) {
            // 检查头部静态页面是否存在，不存在执行头部静态化方法
            String templatePath = request.getRealPath("/") + VIEW_INDEX;
            String topHtmlPath = templatePath + "/newheader13.html";
            File topHtml = new File(topHtmlPath);
            if (!topHtml.exists()) {
                try {
                    // 静态化头部
                    this.staticizeTopThirteen(request);
                } catch (Exception e) {
                    LOGGER.info(e);
                }
            }
            // 检查首页静态页面是否存在，不存在执行首页静态化方法
            String indexHtmlPath = templatePath + "/index13.html";
            File indexHtml = new File(indexHtmlPath);
            if (!indexHtml.exists()) {
                try {
                    // 静态化首页
                    this.staticizeIndexThirteen(request);
                } catch (Exception e) {
                    LOGGER.info(e);
                }
            }
        } else {
            // 静态化头部
            this.staticizeTopThirteen(request);
            // 静态化首页
            this.staticizeIndexThirteen(request);
        }
    }

    /**
     * 验证模板内容变更Fourteen
     * 
     *
     */
    @SuppressWarnings("deprecation")
    private void checkChangeTempFourteen() {
        if (checkToken(request)) {
            // 检查头部静态页面是否存在，不存在执行头部静态化方法
            String templatePath = request.getRealPath("/") + VIEW_INDEX;
            String topHtmlPath = templatePath + "/newheader14.html";
            File topHtml = new File(topHtmlPath);
            if (!topHtml.exists()) {
                try {
                    // 静态化头部
                    this.staticizeTopFourteen(request);
                } catch (Exception e) {
                    LOGGER.info(e);
                }
            }
            // 检查首页静态页面是否存在，不存在执行首页静态化方法
            String indexHtmlPath = templatePath + INDEX14_HTML;
            File indexHtml = new File(indexHtmlPath);
            if (!indexHtml.exists()) {
                try {
                    // 静态化首页
                    this.staticizeIndexFourteen(request);
                } catch (Exception e) {
                    LOGGER.info(e);
                }
            }
        } else {
            // 静态化头部
            this.staticizeTopFourteen(request);
            // 静态化首页
            this.staticizeIndexFourteen(request);
        }
    }

    /**
     * 验证模板内容变更Fifteen
     *
     *
     */
    @SuppressWarnings("deprecation")
    private void checkChangeTempFifteen() {
        if (checkToken(request)) {
            // 检查头部静态页面是否存在，不存在执行头部静态化方法
            String templatePath = request.getRealPath("/") + VIEW_INDEX;
            String topHtmlPath = templatePath + "/newheader15.html";
            File topHtml = new File(topHtmlPath);
            if (!topHtml.exists()) {
                try {
                    // 静态化头部
                    this.staticizeTopFifteen(request);
                } catch (Exception e) {
                    LOGGER.info(e);
                }
            }
            // 检查首页静态页面是否存在，不存在执行首页静态化方法
            String indexHtmlPath = templatePath + INDEX14_HTML;
            File indexHtml = new File(indexHtmlPath);
            if (!indexHtml.exists()) {
                try {
                    // 静态化首页
                    this.staticizeIndexFifteen(request);
                } catch (Exception e) {
                    LOGGER.info(e);
                }
            }
        } else {
            // 静态化头部
            this.staticizeTopFifteen(request);
            // 静态化首页
            this.staticizeIndexFifteen(request);
        }
    }

    /**
     * 验证token
     * 
     *
     * @return true 不用自动生成；false 需要自动生成
     */
    public boolean checkToken(HttpServletRequest request) {
        boolean flag = true;
        ServletContext application = request.getSession().getServletContext();
        String token = (String) application.getAttribute(TOKEN);
        // application中没有token
        if (null == token || "".equals(token)) {
            TempToken tk = tokenService.selectTokenByType("1");
            if (null != tk) {
                application.setAttribute(TOKEN, tk.getToken());
            } else {
                // 生成tokin
                MessageDigest md;
                try {
                    // 生成一个MD5加密计算摘要
                    md = MessageDigest.getInstance("MD5");
                    // 计算md5函数
                    md.update(new Date().toString().getBytes());
                    // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，
                    // 实际上就是8位的字符
                    // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
                    String tokenValue = new BigInteger(1, md.digest()).toString(16);
                    tk = new TempToken();
                    tk.setTemp1("1");
                    tk.setToken(tokenValue);
                    tokenService.createToken(tk);
                    application.setAttribute(TOKEN, tokenValue);
                } catch (Exception e) {
                    LOGGER.error("生成token码错误：=>", e);
                }
            }
            flag = false;
        } else {
            // application中的token和数据库中的不一样
            String tokenValue = tokenService.selectTokenByType("1").getToken();
            if (!token.equals(tokenValue)) {
                flag = false;
                application.setAttribute(TOKEN, tokenValue);
            }
        }
        return flag;
    }

    /**
     * 静态化头部
     * 
     *
     * @return
     */
    @SuppressWarnings("deprecation")
    public void staticizeTop(HttpServletRequest request) {
        String templatePath = request.getRealPath("/") + VIEW_INDEX;
        String templateName = "/newheader_ftl.ftl";
        String fileName = templatePath + "/newheader.html";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(REQUEST, request);
        try {
            Map<String, Object> topmap = this.getTop();
            map.put(TOPMAP, topmap);
            // FreeMarker生成静态页面
            IndexStaticizeUtil.analysisTemplate(templatePath, templateName, fileName, map);
        } catch (Exception e) {
            LOGGER.error(LOGGERINFO1, e);
        }
    }

    /**
     * 静态化头部 Three
     * 
     *
     * @return
     */
    @SuppressWarnings("deprecation")
    public void staticizeTopThree(HttpServletRequest request) {
        String templatePath = request.getRealPath("/") + VIEW_INDEX;
        String templateName = "/newheader3_ftl.ftl";
        String fileName = templatePath + "/newheader3.html";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(REQUEST, request);
        try {
            Map<String, Object> topmap = this.getTop();
            map.put(TOPMAP, topmap);
            // FreeMarker生成静态页面
            IndexStaticizeUtil.analysisTemplate(templatePath, templateName, fileName, map);
        } catch (Exception e) {
            LOGGER.error(LOGGERINFO1, e);
        }
    }

    /**
     * 静态化头部four
     *
     */
    @SuppressWarnings("deprecation")
    public void staticizeTopFour(HttpServletRequest request) {
        String templatePath = request.getRealPath("/") + VIEW_INDEX;
        String templateName = "/newheader4_ftl.ftl";
        String fileName = templatePath + "/newheader4.html";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(REQUEST, request);
        try {
            Map<String, Object> topmap = this.getTop();
            map.put(TOPMAP, topmap);
            // FreeMarker生成静态页面
            IndexStaticizeUtil.analysisTemplate(templatePath, templateName, fileName, map);
        } catch (Exception e) {
            LOGGER.error(LOGGERINFO1, e);
        }
    }

    /**
     * 静态化头部five
     *
     */
    @SuppressWarnings("deprecation")
    public void staticizeTopFive(HttpServletRequest request) {
        String templatePath = request.getRealPath("/") + VIEW_INDEX;
        String templateName = "/newheader5_ftl.ftl";
        String fileName = templatePath + "/newheader5.html";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(REQUEST, request);
        try {
            Map<String, Object> topmap = this.getTop();
            map.put(TOPMAP, topmap);
            // FreeMarker生成静态页面
            IndexStaticizeUtil.analysisTemplate(templatePath, templateName, fileName, map);
        } catch (Exception e) {
            LOGGER.error(LOGGERINFO1, e);
        }
    }

    /**
     * 静态化头部Six
     *
     */
    @SuppressWarnings("deprecation")
    public void staticizeTopSix(HttpServletRequest request) {
        String templatePath = request.getRealPath("/") + VIEW_INDEX;
        String templateName = "/newheader6_ftl.ftl";
        String fileName = templatePath + "/newheader6.html";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(REQUEST, request);
        try {
            Map<String, Object> topmap = this.getTop();
            map.put(TOPMAP, topmap);
            // FreeMarker生成静态页面
            IndexStaticizeUtil.analysisTemplate(templatePath, templateName, fileName, map);
        } catch (Exception e) {
            LOGGER.error(LOGGERINFO1, e);
        }
    }

    /**
     * 静态化头部Seven
     *
     */
    @SuppressWarnings("deprecation")
    public void staticizeTopSeven(HttpServletRequest request) {
        String templatePath = request.getRealPath("/") + VIEW_INDEX;
        String templateName = "/newheader7_ftl.ftl";
        String fileName = templatePath + "/newheader7.html";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(REQUEST, request);
        try {
            Map<String, Object> topmap = this.getTop();
            map.put(TOPMAP, topmap);
            // FreeMarker生成静态页面
            IndexStaticizeUtil.analysisTemplate(templatePath, templateName, fileName, map);
        } catch (Exception e) {
            LOGGER.error(LOGGERINFO1, e);
        }
    }

    /**
     * 静态化头部Eight
     *
     */
    @SuppressWarnings("deprecation")
    public void staticizeTopEight(HttpServletRequest request) {
        String templatePath = request.getRealPath("/") + VIEW_INDEX;
        String templateName = "/newheader8_ftl.ftl";
        String fileName = templatePath + "/newheader8.html";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(REQUEST, request);
        try {
            Map<String, Object> topmap = this.getTop();
            map.put(TOPMAP, topmap);
            // FreeMarker生成静态页面
            IndexStaticizeUtil.analysisTemplate(templatePath, templateName, fileName, map);
        } catch (Exception e) {
            LOGGER.error(LOGGERINFO1, e);
        }
    }

    /**
     * 静态化头部NewEight
     *
     */
    @SuppressWarnings("deprecation")
    public void staticizeTopNewEight(HttpServletRequest request) {
        String templatePath = request.getRealPath("/") + VIEW_INDEX;
        String templateName = "/newheader8_newftl.ftl";
        String fileName = templatePath + "/newheader8new.html";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(REQUEST, request);
        try {
            Map<String, Object> topmap = this.getTop();
            map.put(TOPMAP, topmap);
            // FreeMarker生成静态页面
            IndexStaticizeUtil.analysisTemplate(templatePath, templateName, fileName, map);
        } catch (Exception e) {
            LOGGER.error(LOGGERINFO1, e);
        }
    }

    /**
     * 静态化头部Nine
     *
     */
    @SuppressWarnings("deprecation")
    public void staticizeTopNine(HttpServletRequest request) {
        String templatePath = request.getRealPath("/") + VIEW_INDEX;
        String templateName = "/newheader9_ftl.ftl";
        String fileName = templatePath + "/newheader9.html";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(REQUEST, request);
        try {
            Map<String, Object> topmap = this.getTop();
            map.put(TOPMAP, topmap);
            // FreeMarker生成静态页面
            IndexStaticizeUtil.analysisTemplate(templatePath, templateName, fileName, map);
        } catch (Exception e) {
            LOGGER.error(LOGGERINFO1, e);
        }
    }

    /**
     * 静态化头部Ten
     *
     */
    @SuppressWarnings("deprecation")
    public void staticizeTopTen(HttpServletRequest request) {
        String templatePath = request.getRealPath("/") + VIEW_INDEX;
        String templateName = "/newheader10_ftl.ftl";
        String fileName = templatePath + "/newheader10.html";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(REQUEST, request);
        try {
            Map<String, Object> topmap = this.getTop();
            map.put(TOPMAP, topmap);
            // FreeMarker生成静态页面
            IndexStaticizeUtil.analysisTemplate(templatePath, templateName, fileName, map);
        } catch (Exception e) {
            LOGGER.error(LOGGERINFO1, e);
        }
    }

    /**
     * 静态化头部Eleven
     *
     */
    @SuppressWarnings("deprecation")
    public void staticizeTopEleven(HttpServletRequest request) {
        String templatePath = request.getRealPath("/") + VIEW_INDEX;
        String templateName = "/newheader11_ftl.ftl";
        String fileName = templatePath + "/newheader11.html";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(REQUEST, request);
        try {
            Map<String, Object> topmap = this.getTop();
            map.put(TOPMAP, topmap);
            // FreeMarker生成静态页面
            IndexStaticizeUtil.analysisTemplate(templatePath, templateName, fileName, map);
        } catch (Exception e) {
            LOGGER.error(LOGGERINFO1, e);
        }
    }

    /**
     * 静态化头部Twelve
     *
     */
    @SuppressWarnings("deprecation")
    public void staticizeTopTwelve(HttpServletRequest request) {
        String templatePath = request.getRealPath("/") + VIEW_INDEX;
        String templateName = "/newheader12_ftl.ftl";
        String fileName = templatePath + "/newheader12.html";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(REQUEST, request);
        try {
            Map<String, Object> topmap = this.getTop();
            map.put(TOPMAP, topmap);
            // FreeMarker生成静态页面
            IndexStaticizeUtil.analysisTemplate(templatePath, templateName, fileName, map);
        } catch (Exception e) {
            LOGGER.error(LOGGERINFO1, e);
        }
    }

    /**
     * 静态化头部Thirteen
     *
     */
    @SuppressWarnings("deprecation")
    public void staticizeTopThirteen(HttpServletRequest request) {
        String templatePath = request.getRealPath("/") + VIEW_INDEX;
        String templateName = "/newheader13_ftl.ftl";
        String fileName = templatePath + "/newheader13.html";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(REQUEST, request);
        try {
            Map<String, Object> topmap = this.getTop();
            map.put(TOPMAP, topmap);
            // FreeMarker生成静态页面
            IndexStaticizeUtil.analysisTemplate(templatePath, templateName, fileName, map);
        } catch (Exception e) {
            LOGGER.error(LOGGERINFO1, e);
        }
    }

    /**
     * 静态化头部Fourteen
     *
     */
    @SuppressWarnings("deprecation")
    public void staticizeTopFourteen(HttpServletRequest request) {
        String templatePath = request.getRealPath("/") + VIEW_INDEX;
        String templateName = "/newheader14_ftl.ftl";
        String fileName = templatePath + "/newheader14.html";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(REQUEST, request);
        try {
            Map<String, Object> topmap = this.getTop();
            map.put(TOPMAP, topmap);
            // FreeMarker生成静态页面
            IndexStaticizeUtil.analysisTemplate(templatePath, templateName, fileName, map);
        } catch (Exception e) {
            LOGGER.error(LOGGERINFO1, e);
        }
    }

    /**
     * 静态化头部Fifteen
     *
     */
    @SuppressWarnings("deprecation")
    public void staticizeTopFifteen(HttpServletRequest request) {
        String templatePath = request.getRealPath("/") + VIEW_INDEX;
        String templateName = "/newheader15_ftl.ftl";
        String fileName = templatePath + "/newheader15.html";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(REQUEST, request);
        try {
            Map<String, Object> topmap = this.getTop();
            map.put(TOPMAP, topmap);
            // FreeMarker生成静态页面
            IndexStaticizeUtil.analysisTemplate(templatePath, templateName, fileName, map);
        } catch (Exception e) {
            LOGGER.error(LOGGERINFO1, e);
        }
    }

    /**
     * 静态化首页<br/>
     * 广告、热门推荐、楼层
     * 
     *
     */
    @SuppressWarnings("deprecation")
    public void staticizeIndex(HttpServletRequest request) {
        String templatePath = request.getRealPath("/") + VIEW_INDEX;
        String templateName = "/index2_ftl.ftl";
        String fileName = templatePath + "/index2.html";
        try {
            // FreeMarker生成静态页面
            IndexStaticizeUtil.analysisTemplate(templatePath, templateName, fileName, this.getIndexInfo(request));
        } catch (Exception e) {
            LOGGER.error(LOGGERINFO2, e);
        }
    }

    /**
     * 静态化首页<br/>
     * Three 广告、热门推荐、楼层
     * 
     *
     */
    @SuppressWarnings("deprecation")
    public void staticizeIndexThree(HttpServletRequest request) {
        String templatePath = request.getRealPath("/") + VIEW_INDEX;
        String templateName = "/index3_ftl.ftl";
        String fileName = templatePath + "/index3.html";
        try {
            // FreeMarker生成静态页面
            IndexStaticizeUtil.analysisTemplate(templatePath, templateName, fileName, this.getIndexInfo(request));
        } catch (Exception e) {
            LOGGER.error(LOGGERINFO2, e);
        }
    }

    /**
     * 静态化首页Four
     *
     */
    @SuppressWarnings("deprecation")
    public void staticizeIndexFour(HttpServletRequest request) {
        String templatePath = request.getRealPath("/") + VIEW_INDEX;
        String templateName = "/index4_ftl.ftl";
        String fileName = templatePath + "/index4.html";
        try {
            // FreeMarker生成静态页面
            IndexStaticizeUtil.analysisTemplate(templatePath, templateName, fileName, this.getIndexInfo(request));
        } catch (Exception e) {
            LOGGER.error(LOGGERINFO2, e);
        }
    }

    /**
     * 静态化首页Five
     *
     */
    @SuppressWarnings("deprecation")
    public void staticizeIndexFive(HttpServletRequest request) {
        String templatePath = request.getRealPath("/") + VIEW_INDEX;
        String templateName = "/index5_ftl.ftl";
        String fileName = templatePath + "/index5.html";
        try {
            // FreeMarker生成静态页面
            IndexStaticizeUtil.analysisTemplate(templatePath, templateName, fileName, this.getIndexInfo(request));
        } catch (Exception e) {
            LOGGER.error(LOGGERINFO2, e);
        }
    }

    /**
     * 静态化首页Six
     *
     */
    @SuppressWarnings("deprecation")
    public void staticizeIndexSix(HttpServletRequest request) {
        String templatePath = request.getRealPath("/") + VIEW_INDEX;
        String templateName = "/index6_ftl.ftl";
        String fileName = templatePath + "/index6.html";
        try {
            // FreeMarker生成静态页面
            IndexStaticizeUtil.analysisTemplate(templatePath, templateName, fileName, this.getIndexInfo(request));
        } catch (Exception e) {
            LOGGER.error(LOGGERINFO2, e);
        }
    }

    /**
     * 静态化首页Seven
     *
     */
    @SuppressWarnings("deprecation")
    public void staticizeIndexSeven(HttpServletRequest request) {
        String templatePath = request.getRealPath("/") + VIEW_INDEX;
        String templateName = "/index7_ftl.ftl";
        String fileName = templatePath + "/index7.html";
        try {
            // FreeMarker生成静态页面
            IndexStaticizeUtil.analysisTemplate(templatePath, templateName, fileName, this.getIndexInfo(request));
        } catch (Exception e) {
            LOGGER.error(LOGGERINFO2, e);
        }
    }

    /**
     * 静态化首页Eight
     *
     */
    @SuppressWarnings("deprecation")
    public void staticizeIndexEight(HttpServletRequest request) {
        String templatePath = request.getRealPath("/") + VIEW_INDEX;
        String templateName = "/index8_ftl.ftl";
        String fileName = templatePath + "/index8.html";
        try {
            // FreeMarker生成静态页面
            IndexStaticizeUtil.analysisTemplate(templatePath, templateName, fileName, this.getIndexInfo(request));
        } catch (Exception e) {
            LOGGER.error(LOGGERINFO2, e);
        }
    }

    /**
     * 静态化首页Nine
     *
     */
    @SuppressWarnings("deprecation")
    public void staticizeIndexNine(HttpServletRequest request) {
        String templatePath = request.getRealPath("/") + VIEW_INDEX;
        String templateName = "/index9_ftl.ftl";
        String fileName = templatePath + "/index9.html";
        try {
            // FreeMarker生成静态页面
            IndexStaticizeUtil.analysisTemplate(templatePath, templateName, fileName, this.getIndexInfo(request));
        } catch (Exception e) {
            LOGGER.error(LOGGERINFO2, e);
        }
    }

    /**
     * 静态化首页Ten
     *
     */
    @SuppressWarnings("deprecation")
    public void staticizeIndexTen(HttpServletRequest request) {
        String templatePath = request.getRealPath("/") + VIEW_INDEX;
        String templateName = "/index10_ftl.ftl";
        String fileName = templatePath + "/index10.html";
        try {
            // FreeMarker生成静态页面
            IndexStaticizeUtil.analysisTemplate(templatePath, templateName, fileName, this.getIndexInfo(request));
        } catch (Exception e) {
            LOGGER.error(LOGGERINFO2, e);
        }
    }

    /**
     * 静态化首页Eleven
     *
     */
    @SuppressWarnings("deprecation")
    public void staticizeIndexEleven(HttpServletRequest request) {
        String templatePath = request.getRealPath("/") + VIEW_INDEX;
        String templateName = "/index11_ftl.ftl";
        String fileName = templatePath + "/index11.html";
        try {
            // FreeMarker生成静态页面
            IndexStaticizeUtil.analysisTemplate(templatePath, templateName, fileName, this.getIndexInfo(request));
        } catch (Exception e) {
            LOGGER.error(LOGGERINFO2, e);
        }
    }

    /**
     * 静态化首页Twelve
     *
     */
    @SuppressWarnings("deprecation")
    public void staticizeIndexTwelve(HttpServletRequest request) {
        String templatePath = request.getRealPath("/") + VIEW_INDEX;
        String templateName = "/index12_ftl.ftl";
        String fileName = templatePath + "/index12.html";
        try {
            // FreeMarker生成静态页面
            IndexStaticizeUtil.analysisTemplate(templatePath, templateName, fileName, this.getIndexInfo(request));
        } catch (Exception e) {
            LOGGER.error(LOGGERINFO2, e);
        }
    }

    /**
     * 静态化首页Thirteen
     *
     */
    @SuppressWarnings("deprecation")
    public void staticizeIndexThirteen(HttpServletRequest request) {
        String templatePath = request.getRealPath("/") + VIEW_INDEX;
        String templateName = "/index13_ftl.ftl";
        String fileName = templatePath + "/index13.html";
        try {
            // FreeMarker生成静态页面
            IndexStaticizeUtil.analysisTemplate(templatePath, templateName, fileName, this.getIndexInfo(request));
        } catch (Exception e) {
            LOGGER.error(LOGGERINFO2, e);
        }
    }

    /**
     * 静态化首页Fourteen
     *
     */
    @SuppressWarnings("deprecation")
    public void staticizeIndexFourteen(HttpServletRequest request) {
        String templatePath = request.getRealPath("/") + VIEW_INDEX;
        String templateName = "/index14_ftl.ftl";
        String fileName = templatePath + INDEX14_HTML;
        try {
            // FreeMarker生成静态页面
            IndexStaticizeUtil.analysisTemplate(templatePath, templateName, fileName, this.getIndexInfo(request));
        } catch (Exception e) {
            LOGGER.error(LOGGERINFO2, e);
        }
    }

    /**
     * 静态化首页Fifteen
     *
     */
    @SuppressWarnings("deprecation")
    public void staticizeIndexFifteen(HttpServletRequest request) {
        String templatePath = request.getRealPath("/") + VIEW_INDEX;
        String templateName = "/index15_ftl.ftl";
        String fileName = templatePath + "/index15.html";
        try {
            // FreeMarker生成静态页面
            IndexStaticizeUtil.analysisTemplate(templatePath, templateName, fileName, this.getIndexInfo(request));
        } catch (Exception e) {
            LOGGER.error(LOGGERINFO2, e);
        }
    }

    /**
     * 获取首页信息封装为map
     * 
     * @return map
     */
    public Map<String, Object> getIndexInfo(HttpServletRequest request) {
        SysTemp temp = getIndexDefalutTemp();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(REQUEST, request);
        // 查询站点信息
        map.put("sys", basicSetService.findBasicSet());
        map.put("temp", temp);
        // 初始化首页楼层数据
        map.put("floor", indexSiteService.getStoreys(Long.valueOf(temp.getTempId())));
        // 根据栏目ID查询文章
        map.put("infoList", infoService.selectByInfoType(Long.valueOf(temp.getExpFleid1())));
        // 获取页面轮播大广告
        map.put("avc", channelAdverService.selectchannelAdverByParamForSite(null, Long.valueOf(temp.getTempId()), null, null, ATID1, ADVERTTYPE, null, "0", null, "0"));
        // 获取页面轮播小广告
        map.put("avs", channelAdverService.selectchannelAdverByParamForSite(null, Long.valueOf(temp.getTempId()), null, null, ATID2, ADVERTTYPE, null, "0", null, "0"));
        // 获取页面广告
        map.put("pageAdvs", channelAdverService.selectchannelAdverByParamForSite(null, Long.valueOf(temp.getTempId()), null, null, ATID3, ADVERTTYPE, null, "0", null, "0"));
        // 热销推荐
        map.put("hotSale", channelGoodsService.selectchannelStoreyGoodsByParamForSite(String.valueOf(temp.getTempId()), null, null));
        // 页面标签
        map.put("tags", indexSiteService.getTagListForTempId(Long.valueOf(temp.getTempId())));
        // 品牌设置
        map.put("trademarkList", channelTrademarkService.selectTrademarkByTempId(Long.valueOf(temp.getTempId())));

        // 客服
        map.put("QQs", onLineServiceItemService.selectItemsByType(0));
        // 查询在线客服设置
        map.put("customerService", onLineServiceBiz.selectSetting());
        return map;
    }

    /**
     * 获取头部信息封装为map
     * 
     * @return
     */
    public Map<String, Object> getTop() {
        /* 头部信息 */
        Map<String, Object> top = new HashMap<String, Object>();
        // 1.加载启用的模板信息
        SysTemp temp = getIndexDefalutTemp();
        top.put("temp", temp);

        // 获取logo旁边的页面广告
        List<ChannelAdver> pageAdvList = channelAdverService.selectchannelAdverByParamForSite(null, Long.valueOf(temp.getTempId()), null, null, ATID3, ADVERTTYPE, null, "0", null,
                "0");
        if (!pageAdvList.isEmpty()) {
            top.put("logoAdv", pageAdvList.get(0));
        }
        // 2.加载热门搜索
        top.put("hotsearch", hotSearchService.selectHotBySelectiveForSite(Long.valueOf(temp.getTempId()), null));
        // 3.加载分类导航
        // 如果分类导航关闭，则不加载分类导航信息

        IndexClassifyBar classifyBar = this.indexSiteService.getClassifyBar(Long.valueOf(temp.getTempId()));
        top.put("classifyBar", classifyBar);
        // 4.加载站点信息
        top.put(SYSTEMBASE, basicSetService.findBasicSet());
        top.put("seo", getSeo());
        // 5.加载导航
        top.put("navList", getNav(temp.getTempId()));
        // 6.加载个人中心信息
        top.put("notice", customerServiceInterface.selectNotice((Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID)));
        // 查询我可以使用 未过期的所有优惠券总数
        top.put("couponNum", couponService.myCouponNoCount((Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID)));

        //设置会员信息(是否是企业会员) 2015.11.01 wuyanbo 修改是否企业会员信息
        Object obj = request.getSession().getAttribute("cust");
        if(null != obj){
            Customer customer = (Customer)obj;
            top.put("vip", customer.getIsEnterprise());
            request.getSession().setAttribute("vip", customer.getIsEnterprise());
        }else{
            top.put("vip", "0");
            request.getSession().setAttribute("vip", "0");
        }
        top.put("cust", obj);//用户信息

        // 根据栏目ID查询文章
        top.put("infoList", infoService.selectByInfoType(Long.valueOf(temp.getExpFleid1())));
        return top;
    }

    /**
     * 获取SEO信息
     * 
     * @return
     */
    public SeoConf getSeo() {
        // 获取已启用的SEO设置对象
        SeoConf seo = seoconfbiz.querySeoByUsedStatus();
        if (null == seo) {
            seo = new SeoConf();
            seo.setMeteKey("");
            seo.setMeteDes("");
        }
        return seo;
    }

    /**
     * 获得首页默认模板
     * 
     * @return 模板
     */
    public SysTemp getIndexDefalutTemp() {
        try {
            // 获取当前使用的首页模板
            return tempService.getCurrTemp();
        } catch (Exception e) {
            LOGGER.error("加载首页默认模板异常！",e);
            return null;
        }
    }

    /**
     * 把List热门搜索转换为无序的Set集合
     * 
     * @param hotSearchList
     * @return
     */
    private Set<HotSearch> getRandomHotSearch(List<HotSearch> hotSearchList) {
        Set<HotSearch> hotSearchSet = new HashSet<HotSearch>();
        for (HotSearch hotSearch : hotSearchList) {
            hotSearchSet.add(hotSearch);
        }
        return hotSearchSet;
    }

    /**
     * 获得导航菜单
     * 
     * @param tempId
     *            模板id
     * @return 导航菜单集合
     */
    private List<ChannelBar> getNav(int tempId) {
        try {
            // 根据频道ID、模板ID查询频道导航
            return channelBarService.selectAllChannelBarByParam(null, Long.valueOf(tempId), null);
        } catch (Exception e) {
            LOGGER.error("加载导航信息异常！", e);
        }
        return new ArrayList<ChannelBar>();
    }

    /**
     * 获取所有的帮助分类及它的帮助信息
     * 
     * @return
     */
    private List<HelpCateBean> getHelpCateList() {
        List<HelpCateBean> helpCateBeans = new ArrayList<HelpCateBean>();
        // 查询所有帮助分类
        List<HelpCate> helpcates = helpCateService.findAll();
        if (null != helpcates && !helpcates.isEmpty()) {
            for (HelpCate helpCate : helpcates) {
                HelpCateBean helpCateBean = new HelpCateBean();
                helpCateBean.setHelpcateId(helpCate.getHelpcateId());
                helpCateBean.setHelpcateName(helpCate.getHelpcateName());
                helpCateBean.setHelpcateImg(helpCate.getHelpcateImg());
                // 根据帮助类型获取帮助
                helpCateBean.setHelpCenters(this.helpCenterService.findByCateId(helpCate.getHelpcateId()));
                helpCateBeans.add(helpCateBean);
            }
        }
        return helpCateBeans;
    }

    public CouponService getCouponService() {
        return couponService;
    }

    @Resource(name = "CouponService")
    public void setCouponService(CouponService couponService) {
        this.couponService = couponService;
    }

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

    /***
     *查询分类一级
     * @return
     */
    public IndexClassifyBar getIndexClassification()
    {
        //IndexClassifyBar classifyBar = this.indexSiteService.getClassifyBar(Long.valueOf("12"));
        IndexClassifyBar classifyBar = this.indexSiteService.getClassifyBar2(Long.valueOf("12"));
        return classifyBar;
    }

    /***
     * 根据parentID查询子类（三级分类）
     * @return
     */
    public IndexClassifyBar getIndexClassificationByfir(Long parentID){
        //IndexClassifyBar classifyBar = this.indexSiteService.getClassifyBar(Long.valueOf("12"));
        IndexClassifyBar classifyBar = this.indexSiteService.getIndexClassificationByfir(parentID);
        return classifyBar;
    }

}
