package com.ningpai.index;

import com.ningpai.index.service.ThirdIndexSiteService;
import com.ningpai.system.service.BasicSetService;
import com.ningpai.temp.bean.SysTemp;
import com.ningpai.temp.service.ThirdTempService;
import com.ningpai.thirdaudit.bean.StoreInfo;
import com.ningpai.thirdaudit.service.AuditService;
import com.ningpai.util.MyLogger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 控制器-第三方店铺首页数据获取
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年5月23日下午5:08:24
 */
@Controller
public class ThirdIndexSiteController {

    private static final String INDEX_VIEW = "index/main1";

    // private static final Long ATID1 = 157L;
    // private static final Long ATID2 = 159L;
    // private static final Long ATID3 = 161L;
    // private static final Long ADVERTTYPE = 151L;

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(
            ThirdIndexSiteController.class);

    /** 模板设置业务类 */
    @Resource(name = "ThirdTempService")
    private ThirdTempService tempService;

    /** 首页内容业务类 */
    @Resource(name = "ThirdIndexSiteService")
    private ThirdIndexSiteService indexSiteService;

    /** 第三方商家业务接口 */
    @Resource(name = "auditService")
    private AuditService auditService;
    /**
     * 站点设置服务层接口
     */
    @Resource(name = "basicSetService")

    private BasicSetService basicSetService;

    /**
     * 获取首页Json数据测试
     * 
     * @param request
     * @param tempId
     * @return
     */
    @ResponseBody
    @RequestMapping("/indexThirdViewText")
    public Object indexThirdViewText(HttpServletRequest request, Long tempId,
            String thirdId) {
        //初始化首页分类导航数据
        return indexSiteService.getClassifyBar(tempId, thirdId);
    }

    /**
     * 获取首页Json数据测试
     * 
     * @param request 请求
     * @param thirdId 第三方Id
     * @return
     */
    @ResponseBody
    @RequestMapping("/getThirdIndex")
    public boolean getThirdIndex(HttpServletRequest request, String thirdId) {
        //根据商家编号 查询商家店铺名称 和是否开启商家首页
        StoreInfo storeinfo = auditService.selectNameAndIsStoreBySId(Long
                .valueOf(thirdId));
        if (null == storeinfo) {
            return false;
        } else {
            return "0".equals(storeinfo.getIsStoreIndex());
        }
    }

    /**
     * 获取第三方商家首页的信息
     * 
     * @param request
     * @return
     */
    @RequestMapping("/indexThirdView")
    public ModelAndView indexThirdView(HttpServletRequest request,
            String thirdId) {
        ModelAndView mav = new ModelAndView();
        //根据商家编号 查询商家店铺名称 和是否开启商家首页
        StoreInfo storeinfo = auditService.selectNameAndIsStoreBySId(Long
                .valueOf(thirdId));
        //获取第三方店铺首页模板
        SysTemp thirdTemp = getIndexDefalutTemp(thirdId);

        mav.setViewName(INDEX_VIEW);
        mav.addObject("thirdTemp", thirdTemp);
        mav.addObject("storeinfo", storeinfo);
        //查询站点信息
        mav.addObject("sys", basicSetService.findBasicSet());
        // mav.addObject("floor",
        // indexSiteService.getStoreys(Long.valueOf(thirdTemp.getTempId()),
        // thirdId));
        // mav.addObject("infoList",
        // infoService.selectByInfoType(Long.valueOf(thirdTemp.getExpFleid1())));
        // // 获取页面轮播大广告
        // mav.addObject("avc",
        // channelAdverService.selectchannelAdverByParamForSite(null,
        // Long.valueOf(thirdTemp.getTempId()), null, null, ATID1, ADVERTTYPE,
        // null, "0", thirdId, null));
        // // 获取页面轮播小广告
        // mav.addObject("avs",
        // channelAdverService.selectchannelAdverByParamForSite(null,
        // Long.valueOf(thirdTemp.getTempId()), null, null, ATID2, ADVERTTYPE,
        // null, "0", thirdId, null));
        // // 获取页面广告
        // mav.addObject("pageAdvs",
        // channelAdverService.selectchannelAdverByParamForSite(null,
        // Long.valueOf(thirdTemp.getTempId()), null, null, ATID3, ADVERTTYPE,
        // null, "0", thirdId, null));
        // // 获取分类导航s
        // mav.addObject("classifyBar",
        // indexSiteService.getClassifyBar(Long.valueOf(thirdTemp.getTempId()),
        // thirdId));
        return mav;
    }

    /**
     * 获取第三方店铺首页模板
     * 
     * @param thirdId
     *            第三方ID
     * @return
     */
    private SysTemp getIndexDefalutTemp(String thirdId) {
        try {
            //获取当前使用的首页模板
            return tempService.getCurrTemp();
        } catch (Exception e) {
            LOGGER.error("加载首页默认模板异常！", e);
            return null;
        }
    }

}
