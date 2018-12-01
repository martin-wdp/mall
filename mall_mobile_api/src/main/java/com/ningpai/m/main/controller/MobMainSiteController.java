package com.ningpai.m.main.controller;

import com.alibaba.fastjson.JSONObject;
import com.ningpai.customer.service.InsideLetterServiceMapper;
import com.ningpai.m.main.vo.MainInfo;
import com.ningpai.m.main.vo.MobStoreyInfo;
import com.ningpai.m.util.AuthUtil;
import com.ningpai.m.util.IndexStaticizeUtil;
import com.ningpai.mobile.bean.MobHomePage;
import com.ningpai.mobile.bean.MobStorey;
import com.ningpai.mobile.service.MobAdverService;
import com.ningpai.mobile.service.MobCateBarService;
import com.ningpai.mobile.service.MobHomePageService;
import com.ningpai.mobile.service.MobStoreyService;
import com.ningpai.system.bean.SeoConf;
import com.ningpai.system.mobile.bean.MobSiteBasic;
import com.ningpai.system.mobile.service.MobSiteBasicService;
import com.ningpai.system.service.BasicSetService;
import com.ningpai.system.service.CityService;
import com.ningpai.system.service.ISeoConfBiz;
import com.ningpai.system.service.IStatisticsCodeBiz;
import com.ningpai.temp.bean.TempToken;
import com.ningpai.temp.service.TempTokenService;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;
import com.ningpai.util.xml.XmlElementUtil;
import com.ningpai.util.xml.XmlUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.w3c.dom.Document;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 控制器-移动版首页接口
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年8月22日下午1:55:24
 */
@Controller
@RequestMapping("/home")
public class MobMainSiteController {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(MobMainSiteController.class);

    /**
     * 用于新首页
     */
    XmlUtil xmlUtil = new XmlUtil();
    XmlElementUtil xmlElementUtil = new XmlElementUtil();

    /** 商家ID */
    static final Long STOREID = -1L;

    static final String xmlFilePath = "mobile_home_page/xml/9gdemo.xml";

    static final String xslFilePath = "mobile_home_page/xsl/9gdemo_save.xsl";

    static final String htmlFilePath = "view/main/9gdemo.html";

    private static final String MOBSITEBASIC = "mobSiteBasic";
    private static final String SITEPROJECTURL = "siteProjectUrl";
    private static final String SCODELIST = "sCodeList";
    private static final String MAIN_STORE = "/main/store";
    private static final String HOMEPAGETOKEN = "homePageToken";
    private static final String TOKEN = "token";
    private static final String VIEW_MAIN_STORE = "view/main/store";

    @Resource(name = "MobAdverService")
    private MobAdverService mobAdverService;

    /**
     * 行程service
     */
//    @Resource(name = "TravelService")
//    private TravelService travelService;

    @Resource(name = "MobStoreyService")
    private MobStoreyService mobStoreyService;

    @Resource(name = "basicSetService")
    private BasicSetService basicSetService;

    @Resource(name = "TempTokenService")
    private TempTokenService tokenService;

    /** 站点SEO业务接口 */
    @Resource(name = "seoConfBizImpl")
    private ISeoConfBiz seoconfbiz;

    /** 统计代码业务类 */
    @Resource(name = "statisticsCodeBizImpl")
    private IStatisticsCodeBiz statisticsCodeBizImpl;

    @Resource(name = "MobSiteBasicService")
    private MobSiteBasicService mobSiteBasicService;

    @Resource(name = "MobCateBarService")
    private MobCateBarService mobCateBarService;

    /* 新首页业务接口 */
    @Resource(name = "MobHomePageService")
    private MobHomePageService mobHomePageService;

    @Resource(name = "CityService")
    private CityService cityService;
    @Resource(name = "authUtil")
    private AuthUtil authUtil;
    @Resource(name = "insideLetterServiceMapper")
    private InsideLetterServiceMapper insiderLetterService;


    @RequestMapping(value = "/banners",produces="html/text;charset=UTF-8")
    @ResponseBody
    public String getBanners(HttpServletRequest request){

        Map result = new ConcurrentHashMap();

        try{
            if(authUtil.isPassAuth(request)){
                result.put("code",0);
                result.put("msg","success");
                result.put("data",mobCateBarService.selectStoreListImage("1"));
            }else{
                result.put("code",100);
                result.put("msg","appcode不合法");
            }

        }catch (Exception e){
            result.put("code",-1);
            result.put("msg","查询异常");
            LOGGER.error(Arrays.asList(e.getStackTrace()).toString());
            result.put("data",null);
        }

        return JSONObject.toJSONString(result);
    }

    @RequestMapping(value = "/travel/top5",produces="html/text;charset=UTF-8")
    @ResponseBody
    public String getTop5Travel(HttpServletRequest request){

        Map result = new ConcurrentHashMap();

        try{
            if(authUtil.isPassAuth(request)){
                result.put("code",0);
                result.put("msg","success");
//            result.put("data",travelService.queryCityByProvinceId(48L));
            }else{
                result.put("code",100);
                result.put("msg","appcode不合法");
            }

        }catch (Exception e){
            result.put("code",-1);
            result.put("msg","查询异常");
            LOGGER.error(Arrays.asList(e.getStackTrace()).toString());
            result.put("data",null);
        }

        return JSONObject.toJSONString(result);
    }


    @RequestMapping(value = "/stationletter",produces="html/text;charset=UTF-8")
    @ResponseBody
    public String getStationletter(HttpServletRequest request){

        Map result = new ConcurrentHashMap();

        try{
            if(authUtil.isPassAuth(request)){
                result.put("code",0);
                result.put("msg","success");
                PageBean pageBean = new PageBean();
                Map<String, Object> paramMap = new ConcurrentHashMap();
                paramMap.put("customerId",2031L);
                result.put("data",insiderLetterService.queryInsideLetter(paramMap,pageBean));
            }else{
                result.put("code",100);
                result.put("msg","appcode不合法");
            }

        }catch (Exception e){
            result.put("code",-1);
            result.put("msg","查询异常");
            LOGGER.error(Arrays.asList(e.getStackTrace()).toString());
            result.put("data",null);
        }

        return JSONObject.toJSONString(result);
    }

    /**
     * 附近免税店铺
     * @return
     */
    @RequestMapping(value = "/shop/near/top5",produces="html/text;charset=UTF-8")
    @ResponseBody
    public String getTop5NearShop(HttpServletRequest request){

        Map result = new ConcurrentHashMap();

        try{
            if(authUtil.isPassAuth(request)){
                result.put("code",0);
                result.put("msg","success");
                result.put("data",cityService.queryCityByProvinceId(48L));
            }else{
                result.put("code",100);
                result.put("msg","appcode不合法");
            }

        }catch (Exception e){
            result.put("code",-1);
            result.put("msg","查询异常");
            LOGGER.error(Arrays.asList(e.getStackTrace()).toString());
            result.put("data",null);
        }

        return JSONObject.toJSONString(result);
    }


    /**
     * 附近人气商品
     * @return
     */
    @RequestMapping(value = "/goods/near/top5",produces="html/text;charset=UTF-8")
    @ResponseBody
    public String getTop5NearGoods(HttpServletRequest request){

        Map result = new ConcurrentHashMap();

        try{

            if(authUtil.isPassAuth(request)){
                result.put("code",0);
                result.put("msg","success");
                result.put("data",cityService.queryCityByProvinceId(48L));
            }else{
                result.put("code",100);
                result.put("msg","appcode不合法");
            }

        }catch (Exception e){
            result.put("code",-1);
            result.put("msg","查询异常");
            LOGGER.error(Arrays.asList(e.getStackTrace()).toString());
            result.put("data",null);
        }

        return JSONObject.toJSONString(result);
    }

    /**
     * 热搜词top5
     * @return
     */
    @RequestMapping(value = "/search/keywords/top5",produces="html/text;charset=UTF-8")
    @ResponseBody
    public String getTop5SearchKeywords(HttpServletRequest request){

        Map result = new ConcurrentHashMap();

        try{

            if(authUtil.isPassAuth(request)){
                result.put("code",0);
                result.put("msg","success");
                result.put("data",cityService.queryCityByProvinceId(48L));
            }else{
                result.put("code",100);
                result.put("msg","appcode不合法");
            }


        }catch (Exception e){
            result.put("code",-1);
            result.put("msg","查询异常");
            LOGGER.error(Arrays.asList(e.getStackTrace()).toString());
            result.put("data",null);
        }

        return JSONObject.toJSONString(result);
    }

    @RequestMapping(value = "/travel/his",produces="html/text;charset=UTF-8")
    @ResponseBody
    public String getMyHisTravel(HttpServletRequest request){

        Map result = new ConcurrentHashMap();

        try{

            if(authUtil.isPassAuth(request)){
                result.put("code",0);
                result.put("msg","success");
                result.put("data",cityService.queryCityByProvinceId(48L));
            }else{
                result.put("code",100);
                result.put("msg","appcode不合法");
            }


        }catch (Exception e){
            result.put("code",-1);
            result.put("msg","查询异常");
            LOGGER.error(Arrays.asList(e.getStackTrace()).toString());
            result.put("data",null);
        }

        return JSONObject.toJSONString(result);
    }




    /**
     * 第三方店铺首页
     * 
     * @param request
     * @return
     */
    @RequestMapping(value = "/thirdStoreIndex")
    public ModelAndView thirdStoreIndex(HttpServletRequest request, Long storeId) throws Exception {
        ModelAndView mav = new ModelAndView();
        // 获取移动版站点设置
        MobSiteBasic msb = mobSiteBasicService.selectCurrMobSiteBasic(request);
        if ("1".equals(msb.getTemp2())) {

            mav.addObject("seo", getSeo());
            mav.addObject("sys", basicSetService.findBasicSet());
            mav.addObject(MOBSITEBASIC, msb);
            // 获取大前台首页的地址
            String siteProjectUrl = basicSetService.findBasicSet().getBsetAddress();
            mav.addObject(SITEPROJECTURL, siteProjectUrl);
            mav.addObject(SCODELIST, statisticsCodeBizImpl.getCurrStatisticsCode());
            // 判断用户首页路径是否存在，不存在创建一个
            checkFileExistsAndMkdirs(request, "store_main");
            // 新首页html
            int flag = showNewHomePage(request, storeId);
            mav.setViewName(MAIN_STORE + STOREID + "/store_main");
            // 标记是否配置模板
            mav.addObject("flag", flag);
        } else {
            // 旧首页cates
            // 验证首页内容是否变更
            checkChangeTemp();
            mav.setViewName("main/main");
        }

        mav.addObject(SCODELIST, statisticsCodeBizImpl.getCurrStatisticsCode());
        mav.addObject("storeId", storeId);
        return mav;
    }

    /**
     * 获取轮播大广告，楼层，楼层广告
     * 
     * @return
     * @throws Exception
     */
    @RequestMapping("/initMain")
    public ModelAndView initMain(HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView();
        // 获取移动版站点设置
        MobSiteBasic msb = mobSiteBasicService.selectCurrMobSiteBasic(request);
        if ("1".equals(msb.getTemp2())) {

            mav.addObject("seo", getSeo());
            mav.addObject("sys", basicSetService.findBasicSet());
            mav.addObject(MOBSITEBASIC, msb);
            // 获取大前台首页的地址
            String siteProjectUrl = basicSetService.findBasicSet().getBsetAddress();
            mav.addObject(SITEPROJECTURL, siteProjectUrl);
            mav.addObject(SCODELIST, statisticsCodeBizImpl.getCurrStatisticsCode());
            // 判断用户首页路径是否存在，不存在创建一个
            checkFileExistsAndMkdirs(request, "new_main");
            // 新首页html
            int flag = showNewHomePage(request, null);
            mav.addObject("flag", flag);
            mav.setViewName("main/store" + STOREID + "/new_main");
        } else {
            // 旧首页
            // 验证首页内容是否变更
            checkChangeTemp();
            mav.setViewName("main/main");
        }

        mav.addObject(SCODELIST, statisticsCodeBizImpl.getCurrStatisticsCode());
        return mav;
    }

    /**
     * 设置移动端首页
     * 
     * @param homePageId
     * @param request
     * @return ModelAndView
     * @throws Exception
     */
    @RequestMapping("/showHomePage")
    public ModelAndView showHomePage(Long homePageId, HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView();
        // 获取移动版站点设置
        MobSiteBasic msb = mobSiteBasicService.selectCurrMobSiteBasic(request);
        mav.addObject("seo", getSeo());
        mav.addObject("sys", basicSetService.findBasicSet());
        mav.addObject(MOBSITEBASIC, msb);
        // 获取大前台首页的地址
        String siteProjectUrl = basicSetService.findBasicSet().getBsetAddress();
        mav.addObject(SITEPROJECTURL, siteProjectUrl);
        mav.addObject(SCODELIST, statisticsCodeBizImpl.getCurrStatisticsCode());
        // 判断用户首页路径是否存在，不存在创建一个
        checkFileExistsAndMkdirs(request, "new_main");
        // 新首页html
        showNewHomePage(homePageId, request);
        mav.setViewName("main/store" + STOREID + "/new_main");
        mav.addObject(SCODELIST, statisticsCodeBizImpl.getCurrStatisticsCode());
        return mav;
    }

    /**
     * 显示新首页 获取数据库的xml，写入服务器的用户xml文件，并根据xsl生成html
     * 
     * @param homePageId
     * @param request
     * @throws Exception
     */
    private void showNewHomePage(Long homePageId, HttpServletRequest request) throws Exception {

        // MobHomePage homePage =
        // this.mobHomePageService.selectHomePageByMerchantId(STOREID);
        MobHomePage homePage = this.mobHomePageService.getHomePageById(homePageId);
        Document document = xmlUtil.str2Document(homePage.getDoc());
        String filePath = request.getSession().getServletContext().getRealPath("/");

        String htmlFileName = filePath + htmlFilePath;
        htmlFileName = htmlFileName.replace("/main", MAIN_STORE + STOREID);
        // 判断是否有html
        if (new File(htmlFileName).exists()) {
            // 文件存在时
            // 判断token是否一致，token不一致重新生成
            if (!checkHomePageToken(request, homePage)) {
                // 生成html
                String xmlFileName = xmlFilePath.replace(".xml", "_" + STOREID + ".xml");
                String fileName = filePath + xmlFileName;
                // 把doc写入用户的文件
                xmlUtil.createXml(fileName, document);
                String xslFileName = filePath + xslFilePath;
                xmlUtil.Transform(fileName, xslFileName, htmlFileName);
            }
        } else {
            // 文件不存在，生成html
            String xmlFileName = xmlFilePath.replace(".xml", "_" + STOREID + ".xml");
            String fileName = filePath + xmlFileName;
            // 把doc写入用户的文件
            xmlUtil.createXml(fileName, document);
            String xslFileName = filePath + xslFilePath;
            xmlUtil.Transform(fileName, xslFileName, htmlFileName);
            ServletContext application = request.getSession().getServletContext();
            application.setAttribute(HOMEPAGETOKEN, homePage.getTemp1());
        }
    }

    /**
     * 显示新首页 获取数据库的xml，写入服务器的用户xml文件，并根据xsl生成html
     * 
     * @param request
     * @param storeId
     * @return int
     * @throws Exception
     */
    private int showNewHomePage(HttpServletRequest request, Long storeId) throws Exception {
        MobHomePage homePage = null;
        if (storeId != null) {
            homePage = this.mobHomePageService.getCurrHomePageByStoreId(storeId);
            if (homePage == null) {
                // 该店铺没有配置手机端首页模板
                return 1;
            }
        } else {
            homePage = this.mobHomePageService.getCurrHomePageByMerchantId(STOREID);
            if (homePage == null) {
                // 没有配置手机端首页模板
                return 2;
            }
        }

        Document document = xmlUtil.str2Document(homePage.getDoc());
        String filePath = request.getSession().getServletContext().getRealPath("/");

        String htmlFileName = filePath + htmlFilePath;
        if (storeId != null) {
            htmlFileName = htmlFileName.replace("9gdemo", storeId + "_store");
        }

        htmlFileName = htmlFileName.replace("/main", MAIN_STORE + STOREID);
        // 判断是否有html
        if (new File(htmlFileName).exists()) {
            // 文件存在时
            // 判断token是否一致，token不一致重新生成
            if (!checkHomePageToken(request, homePage)) {
                // 生成html
                String xmlFileName = xmlFilePath.replace(".xml", "_" + STOREID + ".xml");
                String fileName = filePath + xmlFileName;
                // 把doc写入用户的文件
                xmlUtil.createXml(fileName, document);
                String xslFileName = filePath + xslFilePath;
                xmlUtil.Transform(fileName, xslFileName, htmlFileName);
            }
        } else {
            // 文件不存在，生成html
            String xmlFileName = xmlFilePath.replace(".xml", "_" + STOREID + ".xml");
            String fileName = filePath + xmlFileName;
            // 把doc写入用户的文件
            xmlUtil.createXml(fileName, document);
            String xslFileName = filePath + xslFilePath;
            xmlUtil.Transform(fileName, xslFileName, htmlFileName);
            ServletContext application = request.getSession().getServletContext();
            application.setAttribute(HOMEPAGETOKEN, homePage.getTemp1());
        }

        return 0;
    }

    /**
     * 前台调用静态化首页
     * 
     * @param request
     * @return ModelAndView
     */
    @RequestMapping("/toStaticizeIndex")
    public ModelAndView toStaticizeIndex(HttpServletRequest request) {
        this.staticizeIndex(request);
        return new ModelAndView(new RedirectView("initMain.htm"));
    }

    /*
     * 初始化楼层数据，并封装到楼层数据封装类中
     * 
     * @return List
     */
    private List<MobStoreyInfo> initStorey() {
        List<MobStoreyInfo> storeyInfos = new ArrayList<MobStoreyInfo>();
        List<MobStorey> storeys = this.mobStoreyService.selectMobStoreyForSite();
        for (MobStorey mobStorey : storeys) {
            MobStoreyInfo storeyInfo = new MobStoreyInfo();
            storeyInfo.setMobStorey(mobStorey);
            storeyInfo.setStoreyAdvers(this.mobAdverService.selectByStoreyIdForSite(mobStorey.getMobStoreyId()));
            storeyInfos.add(storeyInfo);
        }
        return storeyInfos;
    }

    /*
     * 验证首页内容变更
     * 
     * @param request
     */
    @SuppressWarnings("deprecation")
    private void checkChangeTemp() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        if (checkToken(request)) {
            // 检查首页静态页面是否存在，不存在执行首页静态化方法
            String indexHtmlPath = request.getRealPath("/") + "view/main/main.html";
            File indexHtml = new File(indexHtmlPath);
            if (!indexHtml.exists()) {
                try {
                    this.staticizeIndex(request);
                } catch (final Exception e) {
                    LOGGER.error("静态化首页错误", e);
                }
            }
        } else {
            try {
                this.staticizeIndex(request);
            } catch (final Exception e) {
                LOGGER.error("静态化首页错误", e);
            }
        }
    }

    /*
     * 验证token
     * 
     * @param request
     * 
     * @return true 不用自动生成；false 需要自动生成
     */
    private boolean checkToken(HttpServletRequest request) {
        boolean flag = true;
        ServletContext application = request.getSession().getServletContext();
        String token = (String) application.getAttribute(TOKEN);
        // application中没有token
        if (null == token || "".equals(token)) {
            TempToken tk = tokenService.selectTokenByType("2");
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
                    tk.setTemp1("2");
                    tk.setToken(tokenValue);
                    tokenService.createToken(tk);
                    application.setAttribute(TOKEN, tokenValue);
                    // 设置首页访问次数为0
                    request.getSession().setAttribute("indexcount", 0);
                } catch (Exception e) {
                    LOGGER.error("生成token码错误：=>", e);
                }
            }
            flag = false;
        } else {
            // application中的token和数据库中的不一样
            if (!token.equals(tokenService.selectTokenByType("2").getToken())) {
                flag = false;
            }
        }
        return flag;
    }

    /*
     * 获取首页数据
     * 
     * @return
     */
    private Map<String, Object> getIndex() {
        Map<String, Object> map = new HashMap<String, Object>();
        MainInfo mainInfo = new MainInfo();
        mainInfo.setBigMobAdvers(this.mobAdverService.selectByStoreyIdForSite(-1L));
        List<MobStoreyInfo> storeyInfos = initStorey();
        mainInfo.setMobStoreyInfos(storeyInfos);
        map.put("mainInfo", mainInfo);
        // 获取前台项目地址
        String siteProjectUrl = basicSetService.findBasicSet().getBsetAddress();
        map.put(SITEPROJECTURL, siteProjectUrl);
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        map.put("request", request);
        map.put("seo", getSeo());
        map.put("sys", basicSetService.findBasicSet());
        map.put(MOBSITEBASIC, mobSiteBasicService.selectCurrMobSiteBasic(request));
        return map;
    }

    /*
     * 静态化首页
     * 
     * @param request
     * 
     * @return
     */
    @SuppressWarnings("deprecation")
    private void staticizeIndex(HttpServletRequest request) {
        String templatePath = request.getRealPath("/") + "view/main";
        String templateName = "/main_ftl.ftl";
        String fileName = templatePath + "/main.html";
        try {
            Map<String, Object> indexmap = getIndex();
            IndexStaticizeUtil.analysisTemplate(templatePath, templateName, fileName, indexmap);
        } catch (final Exception e) {
            LOGGER.error("生成静态头部错误：=>", e);
        }
    }

    /*
     * 获取SEO信息
     */
    private SeoConf getSeo() {
        SeoConf seo = seoconfbiz.querySeoByUsedStatus();
        if (null == seo) {
            seo = new SeoConf();
            String bsetName = basicSetService.findBasicSet().getBsetName();
            if (null != bsetName && (!"".equals(bsetName))) {
                seo.setMete(bsetName);
            } else {
                seo.setMete("");
            }
            seo.setMeteKey("");
            seo.setMeteDes("");
        }
        return seo;
    }

    /*
     * 判断路径是否存在，不存在创建一个
     */
    private void checkFileExistsAndMkdirs(HttpServletRequest request, String url) throws Exception {
        // 获取用户首页文件夹路径
        String rootPath = request.getSession().getServletContext().getRealPath("/");
        File storeFilePath = new File(rootPath + VIEW_MAIN_STORE + STOREID);

        File storeFile = new File(rootPath + VIEW_MAIN_STORE + STOREID + "/" + url + ".ftl");

        if (!storeFile.exists()) {
            try {
                // 创建目录文件夹
                storeFilePath.mkdirs();
                // 复制new_main.ftl到用户首页文件夹
                String oldPath = rootPath + "view/main/" + url + ".ftl";
                String newPath = rootPath + VIEW_MAIN_STORE + STOREID + "/" + url + ".ftl";
                File oldfile = new File(rootPath + "view/main/" + url + ".ftl");
                if (oldfile.exists()) { // 文件存在时
                    try (InputStream inStream = new FileInputStream(oldPath); FileOutputStream fs = new FileOutputStream(newPath)) {
                        int bytesum = 0;
                        int byteread = 0;

                        byte[] buffer = new byte[2048];
                        while ((byteread = inStream.read(buffer)) != -1) {
                            // 字节数文件大小
                            bytesum += byteread;
                            fs.write(buffer, 0, byteread);
                        }
                        LOGGER.debug(url + ".ftl文件大小=" + bytesum);
                    } catch (final Exception e) {
                        LOGGER.error("复制new_main.ftl操作错误", e);
                        throw e;
                    }
                } else {
                    LOGGER.error("没找到" + url + ".ftl");
                    throw new RuntimeException("没找到" + url + ".ftl");
                }
            } catch (final Exception e) {
                LOGGER.error("创建用户首页文件夹失败", e);
                throw e;
            }
        }
    }

    /*
     * 验证新首页token
     * 
     * @param request
     * 
     * @return true 不用自动生成；false 需要自动生成
     */
    private boolean checkHomePageToken(HttpServletRequest request, MobHomePage homePage) {
        boolean flag = true;
        ServletContext application = request.getSession().getServletContext();
        String token = (String) application.getAttribute(HOMEPAGETOKEN);
        // application中没有token
        if (null == token || "".equals(token)) {
            // 获取数据库中的token
            String tk = homePage.getTemp1();
            if (null != tk) {
                application.setAttribute(HOMEPAGETOKEN, tk);
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
                    application.setAttribute(HOMEPAGETOKEN, tokenValue);
                    homePage.setTemp1(tokenValue);
                    this.mobHomePageService.updateHomePage(homePage);
                    // 设置首页访问次数为0
                    request.getSession().setAttribute("indexcount", 0);
                } catch (Exception e) {
                    LOGGER.error("生成token码错误：=>", e);
                }
            }
            flag = false;
        } else {
            // application中的token和数据库中的不一样
            if (!token.equals(homePage.getTemp1())) {
                application.setAttribute(HOMEPAGETOKEN, homePage.getTemp1());
                flag = false;
            }
        }
        return flag;
    }
}
