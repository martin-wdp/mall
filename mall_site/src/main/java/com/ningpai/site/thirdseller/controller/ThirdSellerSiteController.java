package com.ningpai.site.thirdseller.controller;

import com.ningpai.channel.service.ChannelAdverService;
import com.ningpai.channel.service.ChannelGoodsService;
import com.ningpai.common.util.ConstantUtil;
import com.ningpai.common.util.NpCookieUtil;
import com.ningpai.customer.bean.PunishRecord;
import com.ningpai.customer.service.PunishRecordService;
import com.ningpai.goods.bean.EsGoodsInfo;
import com.ningpai.goods.util.SearchPageBean;
import com.ningpai.hotsearch.service.HotSearchService;
import com.ningpai.index.service.TopAndBottomService;
import com.ningpai.site.customer.service.GoodsCommentService;
import com.ningpai.site.goods.service.GoodsService;
import com.ningpai.site.thirdseller.bean.ThirdGoodsSearchBean;
import com.ningpai.site.thirdseller.service.ThirdCateService;
import com.ningpai.site.thirdseller.service.ThirdSellerSiteService;
import com.ningpai.site.thirdseller.vo.ThirdCateVo;
import com.ningpai.site.util.SearchCookieController;
import com.ningpai.system.service.impl.BasicSetServiceImpl;
import com.ningpai.temp.bean.SysTemp;
import com.ningpai.temp.service.ClassifyBarService;
import com.ningpai.temp.service.ThirdTempService;
import com.ningpai.thirdaudit.service.AuditService;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;
import com.ningpai.util.StringCommonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商家控制器
 *
 * @author qiyuanyuan
 */
@Controller
public class ThirdSellerSiteController {

    /**
     * 记录日志对象
     */
    private static final MyLogger LOGGER = new MyLogger(ThirdSellerSiteController.class);

    private static final String THIRDID = "thirdId";
    private static final String CLASSBAR = "classBar";
    private static final String PAGEADVS = "pageAdvs";
    private static final String THIRDCATE = "thirdCate";
    private static final String NOWCATE = "nowcate";
    private static final String COMMENT = "comment";
    private static final String STOREINFO = "storeInfo";
    private static final String SEARCH = "search";

    @Resource(name = "TopAndBottomService")
    private TopAndBottomService topAndBottomService;

    @Resource(name = "ClassifyBarService")
    private ClassifyBarService classifyBarService;

    /* 业务逻辑层依赖 */
    @Resource(name = "ThirdTempService")
    private ThirdTempService thirdTempService;

    @Resource(name = "ChannelAdverService")
    private ChannelAdverService channelAdverService;

    private ThirdSellerSiteService siteService;

    /**
     * 热销推荐业务接口
     */
    @Resource(name = "ChannelGoodsService")
    private ChannelGoodsService channelGoodsService;

    /**
     * 第三方店铺商家
     */
    @Resource(name = "ThirdCateService")
    private ThirdCateService thirdCateService;

    @Resource(name = "HsiteGoodsService")
    private GoodsService goodsService;

    @Resource(name = "goodsCommentService")
    private GoodsCommentService commentService;

    @Resource(name = "PunishRecordService")
    private PunishRecordService punishRecordService;

    @Resource(name = "auditService")
    private AuditService auditService;

    @Resource(name = "basicSetService")
    private BasicSetServiceImpl basicSetService;

    // 没有配置店铺首页 跳转的首页
    private static final String THIRDSTOREINDEX_FALSE = "thirdseller/thirdstoreindex_false";

    /**
     * 获取第三方店铺首页URL
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/getThirdStoreURL")
    public String getThirdStoreURL() {
        return basicSetService.findBasicSet().getBsetAddress();
    }

    /**
     * 获取首页Json数据测试
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/checkThirdIndex")
    public Map<String, Object> checkThirdIndex(HttpServletRequest request, Long thirdId, String checkThirdIndex) {
        Map<String, Object> map = new HashMap<String, Object>();
        Long storeIndex = thirdCateService.selectByCustomerId(thirdId);
        map.put("state", 0 == storeIndex ? false : true);
        map.put("checkThirdIndex", checkThirdIndex);
        return map;
    }

    /**
     * 如果商家没有配置 首页模板也出一个提示页
     */
    @RequestMapping("/thirdstoreindexfalse")
    public ModelAndView thirdstoreindexfalse(HttpServletRequest request, Long thirdId) {
        request.getSession().setAttribute(THIRDID, thirdId);
        return new ModelAndView(THIRDSTOREINDEX_FALSE);
    }

    /**
     * 第三方店铺首页
     *
     * @param request
     * @return
     */
    // @RequestMapping("/thirdstoreindex")
    public ModelAndView thirdStoreIndex(String top, HttpServletRequest request, Long thirdId, PageBean pb, ThirdGoodsSearchBean searchBean) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        PageBean pb1 = pb;
        String delflag = thirdCateService.findStoreFlag(thirdId);
        if (delflag != null && "1".equals(delflag)) {
            return new ModelAndView("/indexview");
        }
        // 滚动条的高度
        if (top != null && !"".equals(top)) {
            resultMap.put("top", top);
        } else {
            resultMap.put("top", "0");
        }
        List<PunishRecord> punishRecordList = punishRecordService.queryInfoByTidandDate(thirdId);
        try {
            if (searchBean != null) {
                searchBean.setTitle(new String(searchBean.getTitle().getBytes("ISO-8859-1"), ConstantUtil.UTF));
            }
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("第三方首页报错", e);
        }
        if (punishRecordList != null) {
            for (int i = 0; i < punishRecordList.size(); i++) {
                PunishRecord punishRecord = punishRecordList.get(i);
                if (punishRecord.getPunishId() == 1) {
                    return topAndBottomService.getBottom(new ModelAndView("/register/closestoretip"));
                }
            }
        }
        int count = auditService.selectStoreTimeByThirdId(thirdId);
        if (count == 0) {
            return topAndBottomService.getBottom(new ModelAndView("/register/overtimetip"));
        }
        // ModelAndView mav = new ModelAndView("thirdseller/storeIndex");
        ModelAndView mav = new ModelAndView("thirdseller/newStoreIndex");
        SysTemp temp = thirdTempService.querySystempByType(137L).get(0);

        // 导航
        resultMap.put(CLASSBAR, classifyBarService.selectClassifyBarByParamSite(Long.valueOf(temp.getTempId()), null, thirdId.toString()));
        // 轮播图
        resultMap.put("channelAdvs",
                channelAdverService.selectchannelAdverByParamForSite(null, Long.valueOf(temp.getTempId()), null, null, 157L, 151L, null, null, thirdId.toString(), null));
        // 轮播小广告
        resultMap.put("channelSadvs",
                channelAdverService.selectchannelAdverByParamForSite(null, Long.valueOf(temp.getTempId()), null, null, 159L, 151L, null, null, thirdId.toString(), null));
        // 页面广告
        resultMap.put(PAGEADVS,
                channelAdverService.selectchannelAdverByParamForSite(null, Long.valueOf(temp.getTempId()), null, null, 161L, 151L, null, null, thirdId.toString(), null));
        // 热销推荐
        resultMap.put("hotSale", channelGoodsService.selectchannelStoreyGoodsByParamForSite(String.valueOf(temp.getTempId()), null, thirdId.toString()));
        // 楼层
        resultMap.put("floor", siteService.getStoreys(Long.valueOf(temp.getTempId()), thirdId));
        resultMap.put(THIRDID, thirdId);
        List<ThirdCateVo> thirdCate = thirdCateService.getAllCalcThirdCate(thirdId);
        resultMap.put(THIRDCATE, thirdCate);

        if (searchBean.getCateId() == null) {
            // 第一次默认所有
            searchBean.setCateId(0L);
        }

        resultMap.put(NOWCATE, thirdCateService.queryThirdCateById(searchBean.getCateId()));

        Long distinctId = null;
        searchBean.setTitle(searchBean.getTitle().replace("\"", "").replace("'", "").trim());
        pb1 = this.goodsService.thirdGoodsList(pb1, searchBean, distinctId, thirdId);
        resultMap.put("pb", pb1);
        // 店铺评级
        resultMap.put(COMMENT, commentService.selectSellerComment(thirdId));
        resultMap.put(STOREINFO, siteService.selectByThirdId(thirdId));
        resultMap.put(SEARCH, searchBean);
        return topAndBottomService.getTopAndBottom(mav).addObject("map", resultMap);
    }

    /**
     * 第三方店铺首页
     *
     * @param request
     * @return
     */
    @RequestMapping("/thirdstoreindex")
    public ModelAndView thirdStoreIndexEs(String top, HttpServletRequest request, Long thirdId, SearchPageBean<EsGoodsInfo> pb, ThirdGoodsSearchBean searchBean) {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        String delflag = thirdCateService.findStoreFlag(thirdId);
        if (delflag != null && "1".equals(delflag)) {
            return new ModelAndView("/indexview");
        }
        // 滚动条的高度
        if (top != null && !"".equals(top)) {
            resultMap.put("top", top);
        } else {
            resultMap.put("top", "0");
        }
        List<PunishRecord> punishRecordList = punishRecordService.queryInfoByTidandDate(thirdId);
        if (punishRecordList != null) {
            for (int i = 0; i < punishRecordList.size(); i++) {
                PunishRecord punishRecord = punishRecordList.get(i);
                if (punishRecord.getPunishId() == 1) {
                    return topAndBottomService.getBottom(new ModelAndView("/register/closestoretip"));
                }
            }
        }
        int count = auditService.selectStoreTimeByThirdId(thirdId);
        if (count == 0) {
            return topAndBottomService.getBottom(new ModelAndView("/register/overtimetip"));
        }

        Long storeIndex = thirdCateService.selectByCustomerId(thirdId);
        if (0 == storeIndex) {
            return new ModelAndView(new RedirectView("thirdstoreindexfalse.htm?thirdId=" + thirdId));
        }

        // ModelAndView mav = new ModelAndView("thirdseller/storeIndex");
        ModelAndView mav = new ModelAndView("thirdseller/es_newStoreIndex");
        SysTemp temp = thirdTempService.querySystempByType(137L).get(0);

        // 导航
        resultMap.put(CLASSBAR, classifyBarService.selectClassifyBarByParamSite(Long.valueOf(temp.getTempId()), null, thirdId.toString()));
        // 轮播图
        resultMap.put("channelAdvs",
                channelAdverService.selectchannelAdverByParamForSite(null, Long.valueOf(temp.getTempId()), null, null, 157L, 151L, null, null, thirdId.toString(), null));
        // 轮播小广告
        resultMap.put("channelSadvs",
                channelAdverService.selectchannelAdverByParamForSite(null, Long.valueOf(temp.getTempId()), null, null, 159L, 151L, null, null, thirdId.toString(), null));
        // 页面广告
        resultMap.put(PAGEADVS,
                channelAdverService.selectchannelAdverByParamForSite(null, Long.valueOf(temp.getTempId()), null, null, 161L, 151L, null, null, thirdId.toString(), null));
        // 热销推荐
        resultMap.put("hotSale", channelGoodsService.selectchannelStoreyGoodsByParamForSite(String.valueOf(temp.getTempId()), null, thirdId.toString()));
        // 楼层
        resultMap.put("floor", siteService.getStoreys(Long.valueOf(temp.getTempId()), thirdId));
        resultMap.put(THIRDID, thirdId);
        List<ThirdCateVo> thirdCate = thirdCateService.getAllCalcThirdCate(thirdId);
        resultMap.put(THIRDCATE, thirdCate);
        if (searchBean.getCateId() == null) {
            // 第一次默认所有
            searchBean.setCateId(0L);
        }

        resultMap.put(NOWCATE, thirdCateService.queryThirdCateById(searchBean.getCateId()));

        Long distinctId = null;
        searchBean.setTitle(searchBean.getTitle().replace("\"", "").replace("'", "").trim());
        Map<String, Object> dataMap = this.goodsService.thirdGoodsListEs(pb, searchBean, distinctId, thirdId, null);
        // 店铺评级
        resultMap.put(COMMENT, commentService.selectSellerComment(thirdId));
        resultMap.put(STOREINFO, siteService.selectByThirdId(thirdId));
        searchBean.setCateId(null);
        resultMap.put(SEARCH, searchBean);
        return topAndBottomService.getTopAndBottom(mav).addObject("map", resultMap).addObject("data", dataMap);
    }

    /**
     * 第三方店铺列表页
     *
     * @param request
     * @param thirdId
     * @param pb
     * @param searchBean
     * @return
     */
    @RequestMapping("/storegoodslist")
    public ModelAndView storeGoodsList(HttpServletRequest request, HttpServletResponse response, Long thirdId, SearchPageBean<EsGoodsInfo> pb, ThirdGoodsSearchBean searchBean) {
        ModelAndView mav = new ModelAndView("thirdseller/storeGoodsList");
        // 检查是否有要转义字符，没有，在cookie中保存搜索记录
        if (StringCommonUtil.checkSpecialCharacter(searchBean.getTitle())) {
            addCookieToSearchProduct(searchBean.getTitle(), request, response);
        }
        SysTemp temp = thirdTempService.querySystempByType(137L).get(0);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        // 导航
        resultMap.put(CLASSBAR, classifyBarService.selectClassifyBarByParamSite(Long.valueOf(temp.getTempId()), null, thirdId.toString()));
        // 页面广告
        resultMap.put(PAGEADVS,
                channelAdverService.selectchannelAdverByParamForSite(null, Long.valueOf(temp.getTempId()), null, null, 161L, 151L, null, null, thirdId.toString(), null));
        // 店铺分类
        resultMap.put(THIRDCATE, thirdCateService.getAllCalcThirdCate(thirdId));
        if (searchBean.getCateId() != null) {
            resultMap.put(NOWCATE, thirdCateService.queryThirdCateById(searchBean.getCateId()));
        }
        Long distinctId = null;
        /*
         * 如果session中的地区ID为空,就设置为默认江苏南京 if (null
         * !=request.getSession().getAttribute("distinctId") ) { distinctId =
         * Long
         * .parseLong(request.getSession().getAttribute("distinctId").toString
         * ()); }else{ distinctId = 1103L; }
         * searchBean.setDistinctId(distinctId);
         */
        // 店铺评级
        resultMap.put(COMMENT, commentService.selectSellerComment(thirdId));
        // 店铺信息
        resultMap.put(STOREINFO, siteService.selectByThirdId(thirdId));
        resultMap.put("distinctId", distinctId);
        // 查询店铺货品
        Map<String, Object> dataMap = this.goodsService.thirdGoodsListEs(pb, searchBean, distinctId, thirdId, null);
        resultMap.put("pb", pb);
        resultMap.put(SEARCH, searchBean);
        resultMap.put(THIRDID, thirdId);
        return topAndBottomService.getTopAndBottom(mav).addObject("map", resultMap).addObject("data", dataMap);
    }

    /**
     * 在cookie里添加搜索记录
     *
     * @param title
     * @param request
     * @param response
     * @author NINGPAI-WangHaiYang
     */
    private void addCookieToSearchProduct(String title, HttpServletRequest request, HttpServletResponse response) {

        LOGGER.debug("==================================" + title);
        // 生命周期
        int maxAge = 60 * 60 * 24 * 3;
        // 获取搜索历史
        Cookie cookie = NpCookieUtil.getCookieByName(request, SearchCookieController.COOKIENAME);
        String list = null;
        // 添加搜索历史
        if (null != cookie) {
            try {
                list = URLDecoder.decode(cookie.getValue(), ConstantUtil.UTF);
                // 判断要添加的搜索历史是否存在
                if (list.indexOf(title) == -1) {
                    list = title + "," + list;
                }

            } catch (UnsupportedEncodingException e) {
                LOGGER.error("",e);
            }
        } else {
            list = title + ",";
        }
        try {
            NpCookieUtil.addCookie(response, SearchCookieController.COOKIENAME, list, maxAge);
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("",e);
        }
    }

    @Resource(name = "ThirdSellerSiteService")
    public void setSiteService(ThirdSellerSiteService siteService) {
        this.siteService = siteService;
    }

}
