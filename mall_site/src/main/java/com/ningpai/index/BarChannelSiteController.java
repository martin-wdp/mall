package com.ningpai.index;

import com.ningpai.channel.bean.Channel;
import com.ningpai.channel.service.ChannelAdverService;
import com.ningpai.channel.service.ChannelSalesGoodsService;
import com.ningpai.channel.service.ChannelService;
import com.ningpai.index.bean.IndexGoodsBean;
import com.ningpai.index.service.ChannelSiteService;
import com.ningpai.information.service.InformationService;
import com.ningpai.system.service.BasicSetService;
import com.ningpai.util.MyLogger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 频道分类导航控制器
 * @Description 频道分类导航控制器
 * @author Songhl
 * @since 2015年8月28日 15:24:46
 */
@Controller
public class BarChannelSiteController {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(BarChannelSiteController.class);

    private static final Long ATID1 = 157L;
    private static final Long ATID2 = 159L;
    private static final Long ATID3 = 161L;
    private static final Long ADVERTTYPE = 151L;
    private static final int ADVERTNUM = 3;

    /** 频道设置业务接口 */
    @Resource(name = "ChannelService")
    private ChannelService channelService;
    /** 频道内容业务接口 */
    @Resource(name = "ChannelSiteService")
    private ChannelSiteService channelSiteService;
    /** 广告业务接口 */
    @Resource(name = "ChannelAdverService")
    private ChannelAdverService channelAdverService;
    /** 文章业务接口 */
    @Resource(name = "InformationService")
    private InformationService infoService;
    /**
     * 商品销售频道接口
     */
    @Resource(name = "ChannelSalesGoodsService")
    private ChannelSalesGoodsService channelSalesGoodsService;
    /**
     * 站点设置服务层接口
     */
    @Resource(name = "basicSetService")
    private BasicSetService basicSetService;

    /**
     * 获取频道分类导航Json数据测试
     * 
     * @param request
     * @param tempId
     * @return
     */
    @ResponseBody
    @RequestMapping("/barchannelCateNavViewText")
    public Object channelCateNavViewText(HttpServletRequest request, Long channelId, Long tempId) {
        // 初始化频道分类导航数据
        return channelSiteService.getClassifyBar(channelId, tempId);
    }

    /**
     * 获取频道楼层导航Json数据测试
     * 
     * @param request
     * @param tempId
     * @return
     */
    @ResponseBody
    @RequestMapping("/barchannelStoreyViewText")
    public Object channelStoreyViewText(HttpServletRequest request, Long channelId, Long tempId) {
        // 初始化首页楼层数据
        return channelSiteService.getStoreys(channelId, tempId);
    }

    /**
     * 加载频道信息
     * 
     * @param request
     * @param barId
     *            商品分类ID：一级分类、二级分类
     * @return
     */
    @RequestMapping("/barchannelView")
    public ModelAndView channelView(HttpServletRequest request, String barId) {
        ModelAndView mav = new ModelAndView();
        Channel channel = getChannel(barId);
        if (null != channel) {

            mav.setViewName("channel/bar_channelIndex");
            // 站点信息
            mav.addObject("sys", basicSetService.findBasicSet());
            // 频道信息
            mav.addObject("channel", channel);
            // 楼层信息
            mav.addObject("floor", channelSiteService.getStoreys(channel.getChannelId(), null));
            // 新闻公告信息
            mav.addObject("infoList", infoService.selectByInfoType(channel.getInfoTypeId()));
            // 获取页面轮播大广告
            mav.addObject("avc", channelAdverService.selectchannelAdverByParamForSite(channel.getChannelId(), null, null, null, ATID1, ADVERTTYPE, null, "0", null, null));
            // 获取页面轮播小广告
            mav.addObject("avs", channelAdverService.selectchannelAdverByParamForSite(channel.getChannelId(), null, null, null, ATID2, ADVERTTYPE, null, "0", null, null));
            // 获取页面广告
            mav.addObject("pageAdvs", channelAdverService.selectchannelAdverByParamForSite(channel.getChannelId(), null, null, null, ATID3, ADVERTTYPE, null, "0", null, null));

            // 今日推荐商品
            mav.addObject("channelGoodsFlag0", channelSalesGoodsService.selectChannelGoodsByFlag(channel.getChannelId(), "0", ADVERTNUM));
            mav.addObject("channelGoodsFlag1", channelSalesGoodsService.selectChannelGoodsByFlag(channel.getChannelId(), "1", ADVERTNUM));
            mav.addObject("channelGoodsFlag2", channelSalesGoodsService.selectChannelGoodsByFlag(channel.getChannelId(), "2", ADVERTNUM));
            return mav;
        } else {
            mav.setView(new RedirectView(request.getContextPath() + "/index.html"));
            return mav;
        }
    }

    /**
     * Ajax加载根据商品分类ID获得商品列表
     * 
     * @param tagId
     *            楼层标签id
     * @return List<IndexGoodsBean>
     */
    @RequestMapping("/barloadstoreytagproduct")
    @ResponseBody
    public List<IndexGoodsBean> loadStoreyTagProduct(Long tagId) {
        // 通过楼层标签id查询商品列表
        return channelSiteService.selectStoreyTagProductsByTagId(tagId);
    }

    /**
     * 根据商品分类ID获得频道
     * 
     * @param barId
     *            商品分类ID：一级分类、二级分类
     * @return 频道 @see com.ningpai.channel.bean.Channel
     */
    private Channel getChannel(String barId) {
        // 根据页面导航ID查询频道
        Channel channel = this.channelService.selectByBarId(barId);
        if (channel != null && channel.getChannelName() != null) {
            LOGGER.debug("===============加载导航频道,频道名称是：" + channel.getChannelName());
            return channel;
        }
        return null;
    }
}
