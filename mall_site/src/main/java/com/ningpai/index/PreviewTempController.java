package com.ningpai.index;

import com.ningpai.channel.bean.ChannelAdver;
import com.ningpai.channel.bean.ChannelBar;
import com.ningpai.channel.service.ChannelAdverService;
import com.ningpai.channel.service.ChannelBarService;
import com.ningpai.channel.service.ChannelGoodsService;
import com.ningpai.channel.service.ChannelTrademarkService;
import com.ningpai.comment.service.ShareService;
import com.ningpai.hotsearch.bean.HotSearch;
import com.ningpai.hotsearch.service.HotSearchService;
import com.ningpai.index.bean.HelpCateBean;
import com.ningpai.index.bean.IndexClassifyBar;
import com.ningpai.index.service.IndexSiteService;
import com.ningpai.information.service.InformationOnePageService;
import com.ningpai.information.service.InformationService;
import com.ningpai.site.customer.service.CustomerServiceInterface;
import com.ningpai.site.customer.vo.CustomerConstantStr;
import com.ningpai.system.bean.HelpCate;
import com.ningpai.system.bean.SeoConf;
import com.ningpai.system.mobile.bean.MobSiteBasic;
import com.ningpai.system.mobile.service.MobSiteBasicService;
import com.ningpai.system.service.*;
import com.ningpai.temp.bean.SysTemp;
import com.ningpai.temp.service.TempService;
import com.ningpai.util.MyLogger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 模版控制器
 * 
 * @Description:模版控制器
 * @author Songhl
 * @since 2015年8月27日 19:41:03
 */
@Controller
public class PreviewTempController {

    private static final Long ATID1 = 157L;
    private static final Long ATID2 = 159L;
    private static final Long ATID3 = 161L;
    private static final Long ADVERTTYPE = 151L;

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(PreviewTempController.class);

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

    /** 文章业务类 */
    @Resource(name = "InformationService")
    private InformationService infoService;
    /**
     * 站点设置服务层接口
     */
    @Resource(name = "basicSetService")
    private BasicSetService basicSetService;
    /** 帮助类型业务接口 */
    @Resource(name = "helpCateService")
    private HelpCateService helpCateService;
    /** 帮助业务接口 */
    @Resource(name = "helpCenterService")
    private HelpCenterService helpCenterService;
    /** 单页业务接口 */
    @Resource(name = "InformationOnePageService")
    private InformationOnePageService onePageService;
    /** 晒单业务接口 */
    @Resource(name = "shareServiceNew")
    private ShareService shareService;

    /** 广告业务接口 */
    @Resource(name = "ChannelAdverService")
    private ChannelAdverService channelAdverService;
    /** 热销推荐业务接口 */
    @Resource(name = "ChannelGoodsService")
    private ChannelGoodsService channelGoodsService;

    // @Resource(name = "TempTokenService")
    // private TempTokenService tokenService;
    /**
     * SERVICE-移动版站点基础设置
     */
    @Resource(name = "MobSiteBasicService")
    private MobSiteBasicService mobSiteBasicService;

    /** 统计代码业务类 */
    @Resource(name = "statisticsCodeBizImpl")
    private IStatisticsCodeBiz statisticsCodeBizImpl;

    /**
     * 会员服务接口
     */
    @Resource(name = "customerServiceSite")
    private CustomerServiceInterface customerServiceInterface;
    /**
     * SERVICE-频道品牌
     */
    @Resource(name = "ChannelTrademarkService")
    private ChannelTrademarkService channelTrademarkService;

    /**
     * 在线客服service
     */
    @Resource(name = "onLineServiceItemBizImpl")
    private IOnLineServiceItemBiz onLineServiceItemService;
    /**
     * 在线客服业务接口
     */
    @Resource(name = "onLineServiceBizImpl")
    private IOnLineServiceBiz onLineServiceBiz;

    /**
     * 模板预览
     * 
     * @param request
     * @return
     */
    @RequestMapping("preview")
    public ModelAndView indexView(HttpServletRequest request, Long tempId) {
        ModelAndView mav = new ModelAndView();
        SysTemp temp = getTempByTempId(tempId);
        mav.setViewName("preview/" + temp.getTempUrl());
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
        // 获取当前统计代码
        mav.addObject("sCodeList", statisticsCodeBizImpl.getCurrStatisticsCode());
        // 查询通知内容数量
        mav.addObject("notice", customerServiceInterface.selectNotice((Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID)));
        // mav = topAndBottomService.getBottom(mav);
        // 获取移动版项目地址
        MobSiteBasic mobSiteBasic = mobSiteBasicService.selectCurrMobSiteBasic(request);
        if (null != mobSiteBasic) {
            String mobProjectUrl = mobSiteBasic.getSiteAddress();
            mav.addObject("mobProjectUrl", mobProjectUrl);
        }

        /* 尾部信息 */
        Map<String, Object> bottom = new HashMap<String, Object>();
        Map<String, Object> topmap = new HashMap<String, Object>();
        // 1.加载启用的模板信息
        mav.addObject("temp", temp);
        topmap.put("temp", temp);
        topmap.put("seo", getSeo());
        // 4.加载站点信息
        topmap.put("systembase", basicSetService.findBasicSet());
        // 帮助模块
        bottom.put("helpCates", getHelpCateList());
        // 底部单独帮助
        bottom.put("helpCenters", this.helpCenterService.selectAllIsFoot());
        mav.addObject("bottom", bottom);

        /* 头部信息 */
        Map<String, Object> top = new HashMap<String, Object>();
        // 1.加载启用的模板信息
        top.put("temp", temp);

        // 获取logo旁边的页面广告
        List<ChannelAdver> pageAdvList = channelAdverService.selectchannelAdverByParamForSite(null, Long.valueOf(temp.getTempId()), null, null, ATID3, ADVERTTYPE, null, "0", null,
                "0");
        if (!pageAdvList.isEmpty()) {
            top.put("logoAdv", pageAdvList.get(0));
        }
        // 2.加载热门搜索
        topmap.put("hotsearch", getRandomHotSearch(hotSearchService.selectHotBySelectiveForSite(Long.valueOf(temp.getTempId()), null)));
        // 3.加载分类导航
        // //如果分类导航关闭，则不加载分类导航信息
        // if(temp.getExpFleid5().equals("1")){
        // IndexClassifyBar classifyBar =
        // this.indexSiteService.getClassifyBar(Long.valueOf(temp.getTempId()));
        // top.put("classifyBar", classifyBar);
        // }
        IndexClassifyBar classifyBar = this.indexSiteService.getClassifyBar(Long.valueOf(temp.getTempId()));
        topmap.put("classifyBar", classifyBar);
        // 4.加载站点信息
        topmap.put("systembase", basicSetService.findBasicSet());
        topmap.put("seo", getSeo());
        // 5.加载导航
        topmap.put("navList", getNav(temp.getTempId()));

        // 品牌设置
        mav.addObject("trademarkList", channelTrademarkService.selectTrademarkByTempId(Long.valueOf(temp.getTempId())));
        // 客服
        mav.addObject("QQs", onLineServiceItemService.selectItemsByType(0));
        mav.addObject("customerService", onLineServiceBiz.selectSetting());

        mav.addObject("topmap", topmap);
        return mav;
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
     * 获得首页默认模板
     * 
     * @return 模板
     */
    private SysTemp getTempByTempId(Long tempId) {
        try {
            // 根据主键查询模板设置对象
            return tempService.getSystempById(tempId);
        } catch (Exception e) {
            LOGGER.error("加载首页默认模板异常！",e);
            return new SysTemp();
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

}
