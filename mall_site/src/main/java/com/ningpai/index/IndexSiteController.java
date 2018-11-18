package com.ningpai.index;

import com.alibaba.fastjson.JSON;
import com.ningpai.channel.bean.ChannelBar;
import com.ningpai.channel.service.ChannelAdverService;
import com.ningpai.channel.service.ChannelBarService;
import com.ningpai.channel.service.ChannelGoodsService;
import com.ningpai.comment.service.ShareService;
import com.ningpai.customer.bean.Customer;
import com.ningpai.hotsearch.bean.HotSearch;
import com.ningpai.hotsearch.service.HotSearchService;
import com.ningpai.index.bean.HelpCateBean;
import com.ningpai.index.bean.IndexClassifyBar;
import com.ningpai.index.service.IndexSiteService;
import com.ningpai.index.service.TopAndBottomService;
import com.ningpai.information.service.InformationOnePageService;
import com.ningpai.information.service.InformationService;
import com.ningpai.information.vo.InformationVo;
import com.ningpai.site.customer.service.CustomerServiceInterface;
import com.ningpai.site.customer.vo.CustomerConstantStr;
import com.ningpai.site.shoppingcart.service.ShoppingCartService;
import com.ningpai.system.bean.HelpCate;
import com.ningpai.system.bean.Province;
import com.ningpai.system.mobile.bean.MobSiteBasic;
import com.ningpai.system.mobile.service.MobSiteBasicService;
import com.ningpai.system.service.*;
import com.ningpai.system.util.SelectBean;
import com.ningpai.system.vo.CityVo;
import com.ningpai.system.vo.DistrictVo;
import com.ningpai.temp.bean.SysTemp;
import com.ningpai.temp.service.TempService;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 首页控制器
 *
 * @author Songhl
 * @Description 首页控制器
 * @since 2015年8月28日 15:23:10
 */
@Controller
public class IndexSiteController {

    /**
     * 记录日志对象
     */
    private static final MyLogger LOGGER = new MyLogger(IndexSiteController.class);

    private static final String EMAILCHECKPWD = "emailCheckPwd";
    private static final String VALIDPWD = "validPwd";

    private static final Long ATID1 = 157L;
    private static final Long ATID2 = 159L;
    private static final Long ATID3 = 161L;
    private static final Long ADVERTTYPE = 151L;

    /**
     * 模板设置业务类
     */
    @Resource(name = "TempService")
    private TempService tempService;

    /**
     * 首页内容业务类
     */
    @Resource(name = "IndexSiteService")
    private IndexSiteService indexSiteService;

    /**
     * 导航菜单业务类
     */
    @Resource(name = "ChannelBarService")
    private ChannelBarService channelBarService;


    /**
     * 快商通
     */
    @Resource(name = "ShopKuaiShangService")
    private ShopKuaiShangService shopKuaiShangService;

    /**
     * 热门搜索
     */
    @Resource(name = "hotSearchService")
    private HotSearchService hotSearchService;

    /**
     * 文章业务类
     */
    @Resource(name = "InformationService")
    private InformationService infoService;

    /**
     * 修改会员积分
     */
    @Resource(name = "ShoppingCartService")
    private ShoppingCartService shoppingcat;

    /**
     * 查询站点信息
     */
    @Resource(name = "basicSetService")
    private BasicSetService basicSetService;
    /**
     * 帮助类型业务接口
     */
    @Resource(name = "helpCateService")
    private HelpCateService helpCateService;
    /**
     * 帮助业务接口
     */
    @Resource(name = "helpCenterService")
    private HelpCenterService helpCenterService;
    /**
     * 网站认证业务接口
     */
    @Resource(name = "WebCertService")
    private WebCertService webCertService;
    /**
     * 单页业务接口
     */
    @Resource(name = "InformationOnePageService")
    private InformationOnePageService onePageService;
    /**
     * 晒单业务接口
     */
    @Resource(name = "shareServiceNew")
    private ShareService shareService;

    /**
     * 广告业务接口
     */
    @Resource(name = "ChannelAdverService")
    private ChannelAdverService channelAdverService;
    /**
     * 热销推荐业务接口
     */
    @Resource(name = "ChannelGoodsService")
    private ChannelGoodsService channelGoodsService;

    /**
     * SERVICE-获取头部、底部信息
     */
    @Resource(name = "TopAndBottomService")
    private TopAndBottomService topAndBottomService;

    // @Resource(name = "TempTokenService")
    // private TempTokenService tokenService;

    /**
     * SERVICE-移动版站点基础设置
     */
    @Resource(name = "MobSiteBasicService")
    private MobSiteBasicService mobSiteBasicService;

    /**
     * 统计代码业务类
     */
    @Resource(name = "statisticsCodeBizImpl")
    private IStatisticsCodeBiz statisticsCodeBizImpl;

    /**
     * 会员服务接口
     */
    @Resource(name = "customerServiceSite")
    private CustomerServiceInterface customerServiceInterface;
    /**
     * 地区管理Service
     */
    @Resource(name = "ProvinceService")
    private ProvinceService provinceService;
    /**
     * 城市信息Service
     */
    @Resource(name = "CityService")
    private CityService cityService;

    /**
     * 区县Service
     */
    @Resource(name = "DistrictService")
    private DistrictService districtService;

    /**
     * 友情链接
     */
    @Resource(name = "friendLinkService")
    private FriendLinkService friendLinkService;

    /**
     * 加载首页
     *
     * @return
     */
    @RequestMapping("/indexview")
    public ModelAndView indexView(HttpServletRequest request) {
        if (request.getSession().getAttribute(EMAILCHECKPWD) != null && !"".equals(request.getSession().getAttribute(EMAILCHECKPWD))) {
            request.getSession().setAttribute(EMAILCHECKPWD, "");
        }
        if (request.getSession().getAttribute(VALIDPWD) != null && !"".equals(request.getSession().getAttribute(VALIDPWD))) {
            request.getSession().setAttribute(VALIDPWD, "");
        }
        ModelAndView mav = new ModelAndView();
        // 获得首页默认模板
        SysTemp temp = getIndexDefalutTemp();
        mav.setViewName(temp.getTempUrl());
        if ("index/index".equals(temp.getTempUrl())) {
            // 初始化模版
            mav = initOldTemp(mav, temp);
        } else {
            if (null != temp.getStandby4()) {
                // 根据模板ID和标签ID查询单页
                mav.addObject("onepages", onePageService.selectInfoOPByTempAndTag(Long.valueOf(temp.getTempId()), Long.valueOf(temp.getStandby4())));
            }
            // 查询前size个热门晒单
            mav.addObject("shares", shareService.getTopShare(10));
            // 获取当前统计代码
            mav.addObject("sCodeList", statisticsCodeBizImpl.getCurrStatisticsCode());
            mav.addObject("shopKuaiShang", shopKuaiShangService.selectInitone());
            // 查询通知内容数量
            mav.addObject("notice", customerServiceInterface.selectNotice((Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID)));
            // 新加载底部信息
            mav = topAndBottomService.getBottom(mav);

            // 获取移动版项目地址
            MobSiteBasic mobSiteBasic = mobSiteBasicService.selectCurrMobSiteBasic(request);
            if (null != mobSiteBasic) {
                String mobProjectUrl = mobSiteBasic.getSiteAddress();
                mav.addObject("mobProjectUrl", mobProjectUrl);
            }
        }
        return mav;
    }

    /**
     * 获得首页默认模板
     *
     * @return 模板
     */
    private SysTemp getIndexDefalutTemp() {
        try {
            // 获取当前使用的首页模板
            return tempService.getCurrTemp();
        } catch (Exception e) {
            LOGGER.error("加载首页默认模板异常！",e);
            return null;
        }
    }

    /**
     * 初始化模版
     *
     * @param mav
     *            视图
     * @param temp
     *            模板
     * @return 视图
     * @Description 初始化模版
     * @author Songhl
     * @since 2015年8月27日 19:27:48
     */
    private ModelAndView initOldTemp(ModelAndView mav, SysTemp temp) {
        // 查询站点信息
        mav.addObject("sys", basicSetService.findBasicSet());
        mav.addObject("temp", temp);
        // 初始化首页楼层数据
        mav.addObject("floor", indexSiteService.getStoreys(Long.valueOf(temp.getTempId())));
        // 根据栏目ID查询文章
        mav.addObject("infoList", infoService.selectByInfoType(Long.valueOf(temp.getExpFleid1())));
        // 获取页面轮播大广告
        mav.addObject("avc", channelAdverService.selectchannelAdverByParamForSite(null, Long.valueOf(temp.getTempId()), null, null, ATID1, ADVERTTYPE, null, "0", null, "0"));
        // 获取页面轮播小广告
        mav.addObject("avs", channelAdverService.selectchannelAdverByParamForSite(null, Long.valueOf(temp.getTempId()), null, null, ATID2, ADVERTTYPE, null, "0", null, "0"));
        // 获取页面广告
        mav.addObject("pageAdvs", channelAdverService.selectchannelAdverByParamForSite(null, Long.valueOf(temp.getTempId()), null, null, ATID3, ADVERTTYPE, null, "0", null, "0"));
        // 热销推荐
        mav.addObject("hotSale", channelGoodsService.selectchannelStoreyGoodsByParamForSite(String.valueOf(temp.getTempId()), null, null));
        // 页面标签
        mav.addObject("tags", indexSiteService.getTagListForTempId(Long.valueOf(temp.getTempId())));
        if (null != temp.getStandby4()) {
            // 根据模板ID和标签ID查询单页
            mav.addObject("onepages", onePageService.selectInfoOPByTempAndTag(Long.valueOf(temp.getTempId()), Long.valueOf(temp.getStandby4())));
        }
        // 查询前size个热门晒单
        mav.addObject("shares", shareService.getTopShare(10));
        // return mav;
        return topAndBottomService.getTopAndBottom(mav);
    }

    /**
     * Ajax加载头部信息（当前使用）
     *
     * @param request
     * @param flag
     * @return
     */
    @RequestMapping("tops")
    @ResponseBody
    public Map<String, Object> top2(HttpServletRequest request, String flag) {

        Map<String, Object> returnmap = new HashMap<String, Object>();

        // 1.加载启用的模板信息
        SysTemp temp = getIndexDefalutTemp();
        returnmap.put("temp", temp);
        // 2.加载热门搜索
        returnmap.put("hotsearch", getRandomHotSearch(hotSearchService.selectHotBySelectiveForSite(Long.valueOf(temp.getTempId()), null)));
        // 3.加载分类导航
        IndexClassifyBar classifyBar = this.indexSiteService.getClassifyBar(Long.valueOf(temp.getTempId()));
        returnmap.put("classifyBar", classifyBar);
        // 4.加载站点信息
        returnmap.put("systembase", basicSetService.findBasicSet());
        // 5.加载导航
        returnmap.put("navList", getNav(temp.getTempId()));
        returnmap.put("cust", request.getSession().getAttribute("cust"));
        returnmap.put("flag", request.getParameter("flag"));

        return returnmap;
    }

    /**
     * Ajax加载底部信息（当前使用）
     *
     * @param request
     * @param request
     * @return Map
     */
    @RequestMapping("getBottom")
    @ResponseBody
    public Map<String, Object> getBottom(HttpServletRequest request) {

        Map<String, Object> returnmap = new HashMap<String, Object>();
        // 查询站点信息
        returnmap.put("sys", basicSetService.findBasicSet());
        // 帮助模块
        returnmap.put("helpCates", getHelpCateList());
        // 底部单独帮助
        returnmap.put("helpCenters", this.helpCenterService.selectAllIsFoot());
        // 网站认证
        returnmap.put("webCerts", this.webCertService.selectAll());
        return returnmap;
    }

    /**
     * mini购物车
     *
     * @param request
     * @param flag
     * @return
     */
    @RequestMapping("/minicart")
    @ResponseBody
    public Map<String, Object> miniCart(HttpServletRequest request, String flag) {
        Map<String, Object> returnmap = new HashMap<String, Object>();
        // mini购物车
        returnmap.put("shopcart", shoppingcat.miniShoppingCart(request));
        returnmap.put("cust", request.getSession().getAttribute("cust"));
        return returnmap;

    }

    /**
     * mini购物车
     *
     * @param request
     *            请求
     * @param flag
     *            bool变量
     * @return map
     * @Description mini购物车
     * @author Songhl
     * @since 2015年8月27日 19:33:46
     */
    @RequestMapping("/minisscart")
    @ResponseBody
    public Map<String, Object> minissCart(HttpServletRequest request, String flag) {
        Map<String, Object> returnmap = new HashMap<String, Object>();
        // mini购物车
        returnmap.put("shopcart", shoppingcat.miniShoppingCart(request));
        returnmap.put("cust", request.getSession().getAttribute("cust") == null ? null : new Customer());
        return returnmap;

    }

    /**
     * 删除迷你购物车内优惠套装的商品
     *
     * @param request
     * @param shoppingCartId
     * @return int
     */
    @RequestMapping("delminicartfit")
    @ResponseBody
    public int delMiniCartFit(HttpServletRequest request, Long shoppingCartId, Long goodsInfoId, Long fitId, HttpServletResponse response) {
        try {
            // 删除优惠分组下单个商品并添加其他商品到购物车内
            shoppingcat.delGoodsGroupByS(shoppingCartId, goodsInfoId, fitId, request, response);
            return 1;
        } catch (Exception e) {
            LOGGER.error("",e);
            return 0;
        }

    }

    /**
     * Ajax加载公告
     */
    @RequestMapping("ajaxLoadInfor")
    @ResponseBody
    public List<InformationVo> ajaxLoadInfor(HttpServletRequest request) {
        // 获得首页默认模板
        SysTemp temp = getIndexDefalutTemp();
        // 根据栏目ID查询文章
        return infoService.selectByInfoType(Long.valueOf(temp.getExpFleid1()));
    }

    /**
     * Ajax实现选择城市
     */
    @RequestMapping("/selectCity")
    @ResponseBody
    public int updateProvince(Long provinceId, HttpServletRequest request) {
        // 根据主键ID查询省份信息
        Province province = provinceService.findProvinceByPrimaryKey(provinceId);
        // 省
        request.getSession().setAttribute("chProvince", province.getProvinceName());
        // 根据省份ID查询城市的列表
        List<CityVo> cityVos = cityService.queryCityByProvinceId(provinceId);
        CityVo city = !cityVos.isEmpty() ? cityVos.get(0) : new CityVo();
        // 市
        request.getSession().setAttribute("chCity", city.getCityName());
        // 根据城市信息查询所有的区县信息
        List<DistrictVo> districtVos = districtService.queryDistrictByCityId(city.getCityId());
        DistrictVo district = !districtVos.isEmpty() ? districtVos.get(0) : new DistrictVo();

        // 县区ID
        request.getSession().setAttribute("distinctId", district.getDistrictId());

        // 详细地址
        request.getSession().setAttribute("chAddress", province.getProvinceName() + city.getCityName() + district.getDistrictName());

        // 地址
        request.getSession().setAttribute("chDistinct", district.getDistrictName());

        return 1;

    }

    /**
     * 默认地址
     *
     * @param request
     *            请求
     * @return 成功
     * @Description 默认地址
     * @author Songhl
     * @since 2015年8月27日 19:36:23
     */
    @RequestMapping("/defaulteProvince")
    @ResponseBody
    public int defaulteProvince(HttpServletRequest request) {
        request.getSession().setAttribute("chProvince", "江苏");
        request.getSession().setAttribute("chCity", "南京市");
        request.getSession().setAttribute("chDistinct", "建邺区");
        // 县区ID
        request.getSession().setAttribute("distinctId", "1103");
        // 详细地址
        request.getSession().setAttribute("chAddress", "江苏省南京市建邺区");
        return 1;
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
            LOGGER.error("加载导航信息异常！",e);
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

    /**
     * 友情链接
     */
    @RequestMapping("toFriendLink")
    public ModelAndView friendLink(PageBean pageBean, SelectBean selectBean) {
        pageBean.setPageSize(100);
        Map<String, Object> map = new HashMap<String, Object>();
        // 分页查询友情链接信息
        map.put("pb", friendLinkService.findByPageBean(pageBean, selectBean));
        // 查询所有友情链接
        map.put("fls", friendLinkService.selectAllFriendLink());
        ModelAndView mav = new ModelAndView("help/friendlink");
        mav.addObject("map", map);
        // 新加载头部和底部信息
        return topAndBottomService.getTopAndBottom(mav);
    }

    /***
     * 获取首页分类
     * @return
     */
    @RequestMapping(value = "/getIndexClassification",produces="text/plain;charset=UTF-8")
    @ResponseBody
    public String getIndexClassification(){
        IndexClassifyBar classifyBar = this.topAndBottomService.getIndexClassification();
        IndexClassifyBar classifyBar2 = this.topAndBottomService.getIndexClassificationByfir(classifyBar.getClassifyBarList().get(0).getClassifyBarId());
        IndexClassifyBar classifyBar3 = this.topAndBottomService.getIndexClassificationByfir(classifyBar2.getClassifyBarList().get(0).getClassifyBarId());
        List<IndexClassifyBar> list=new ArrayList<>();
        list.add(classifyBar);
        list.add(classifyBar2);
        list.add(classifyBar3);
        return JSON.toJSONString(list);
    }

    /***
     * 获取首页分类
     * @return
     */
    @RequestMapping(value = "/getIndexClassificationByfir",produces="text/plain;charset=UTF-8")
    @ResponseBody
    public String getIndexClassificationByfir(Long parentID){
        IndexClassifyBar classifyBar = this.topAndBottomService.getIndexClassificationByfir(parentID);
        return JSON.toJSONString(classifyBar);
    }
}
