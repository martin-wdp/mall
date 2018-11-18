/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.index;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ningpai.channel.service.ChannelAdverService;
import com.ningpai.index.service.TopAndBottomService;
import com.ningpai.site.customer.service.GoodsCommentService;
import com.ningpai.site.thirdseller.bean.ThirdGoodsSearchBean;
import com.ningpai.site.thirdseller.service.ThirdSellerSiteService;
import com.ningpai.temp.bean.SysTemp;
import com.ningpai.temp.service.ClassifyBarService;
import com.ningpai.temp.service.ThirdTempService;
import com.ningpai.thirdproject.service.ThirdProjectService;

/**
 * 第三方专题控制器
 * 
 * @author zhangsl
 * @since 2015年1月20日 下午5:25:08
 * @version
 */
@Controller
public class ThirdProjectSiteController {
    /**
     * 第三方专题管理service
     */
    @Resource(name = "ThirdProjectService")
    private ThirdProjectService thirdProjectService;

    /**
     * SERVICE-获取头部、底部信息
     */
    @Resource(name = "TopAndBottomService")
    private TopAndBottomService topAndBottomService;

    /**
     * SERVICE-分类导航
     */
    @Resource(name = "ClassifyBarService")
    private ClassifyBarService classifyBarService;

    /**
     * 业务逻辑层依赖
     */
    @Resource(name = "ThirdTempService")
    private ThirdTempService thirdTempService;
    /**
     * SERVICE-频道广告
     */
    @Resource(name = "ChannelAdverService")
    private ChannelAdverService channelAdverService;
    /**
     * 商品评论Service
     */
    @Resource(name = "goodsCommentService")
    private GoodsCommentService commentService;

    /**
     * 第三方店铺首页service
     */
    private ThirdSellerSiteService siteService;

    /**
     * 查询专题详细
     * 
     * @param thirdProjectId
     * @return
     */
    @RequestMapping("thirdprojectdetail")
    public ModelAndView thirdProjectDetail(Long thirdProjectId, ThirdGoodsSearchBean searchBean) {
        ModelAndView mav = new ModelAndView();
        Map<String, Object> resultMap = new HashMap<String, Object>();
        // 根据第三方商家ID和模板类型查询模板
        SysTemp temp = thirdTempService.querySystempByType(137L).get(0);
        if (searchBean.getCateId() == null) {
            // 第一次默认所有
            searchBean.setCateId(0L);
        }
        searchBean.setTitle(searchBean.getTitle().replace("\"", "").replace("'", "").trim());
        mav.setViewName("third/projectdetail");
        // 根据Id查询专题信息
        resultMap.put("thirdProject", thirdProjectService.selectProjectById(thirdProjectId));
        // 根据Id查询专题信息
        Long thirdId = thirdProjectService.selectProjectById(thirdProjectId).getThirdId();
        // 导航
        resultMap.put("classBar", classifyBarService.selectClassifyBarByParamSite(Long.valueOf(temp.getTempId()), null, thirdId.toString()));
        // 轮播图
        resultMap.put("channelAdvs",
                channelAdverService.selectchannelAdverByParamForSite(null, Long.valueOf(temp.getTempId()), null, null, 157L, 151L, null, null, thirdId.toString(), null));
        // 轮播小广告
        resultMap.put("channelSadvs",
                channelAdverService.selectchannelAdverByParamForSite(null, Long.valueOf(temp.getTempId()), null, null, 159L, 151L, null, null, thirdId.toString(), null));
        // 页面广告
        resultMap.put("pageAdvs",
                channelAdverService.selectchannelAdverByParamForSite(null, Long.valueOf(temp.getTempId()), null, null, 161L, 151L, null, null, thirdId.toString(), null));
        // 店铺评级
        resultMap.put("comment", commentService.selectSellerComment(thirdId));
        resultMap.put("storeInfo", siteService.selectByThirdId(thirdId));
        resultMap.put("thirdId", thirdId);
        resultMap.put("search", searchBean);
        return topAndBottomService.getTopAndBottom(mav).addObject("map", resultMap);
    }

    @Resource(name = "ThirdSellerSiteService")
    public void setSiteService(ThirdSellerSiteService siteService) {
        this.siteService = siteService;
    }

}
