package com.ningpai.index;

import com.ningpai.channel.bean.Channel;
import com.ningpai.channel.bean.GoodsSiteSearchBean;
import com.ningpai.channel.service.ChannelAdverService;
import com.ningpai.channel.service.ChannelSalesGoodsService;
import com.ningpai.channel.service.ChannelService;
import com.ningpai.index.bean.IndexFloor;
import com.ningpai.index.bean.IndexGoodsBean;
import com.ningpai.index.service.ChannelSiteService;
import com.ningpai.index.service.TopAndBottomService;
import com.ningpai.information.service.InformationService;
import com.ningpai.site.goods.service.GoodsCateService;
import com.ningpai.site.goods.vo.GoodsCateVo;
import com.ningpai.system.service.BasicSetService;
import com.ningpai.system.service.DefaultAddressService;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 频道控制器
 */
@Controller
public class ChannelSiteController {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(ChannelSiteController.class);

    private static final String DISTINCTID = "distinctId";

    private static final String CHANNEL_VIEW = "subtopic/cateChannel";

    private static final Long ATID1 = 157L;

    private static final Long ATID2 = 159L;

    private static final Long ATID3 = 161L;

    private static final Long ADVERTTYPE = 151L;

    private static final int ADVERTNUM = 3;

    /** 频道设置业务接口 */
    @Resource(name = "ChannelService")
    private ChannelService channelService;

    /** 商品分类业务接口 */
    @Resource(name = "HsiteGoodsCateService")
    private GoodsCateService goodsCateService;

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
     * SERVICE-获取头部、底部信息
     */
    @Resource(name = "TopAndBottomService")
    private TopAndBottomService topAndBottomService;

    /**
     * 默认地址
     */
    @Resource(name = "DefaultAddressService")
    private DefaultAddressService addressService;

    /**
     * 获取频道分类导航Json数据测试
     * 
     * @param request
     * @param tempId
     * @return
     */
    @ResponseBody
    @RequestMapping("/channelCateNavViewText")
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
    @RequestMapping("/channelStoreyViewText")
    public Object channelStoreyViewText(HttpServletRequest request, Long channelId, Long tempId) {
        // 初始化首页楼层数据
        return channelSiteService.getStoreys(channelId, tempId);
    }

    /**
     * ajax加载频道楼层商品信息
     *
     * @param request
     * @param goodsCateId
     *            商品分类ID：一级分类、二级分类
     * @return
     */
    @RequestMapping("/channelViewByAjax")
    @ResponseBody
    public Map channelViewByAjax(HttpServletRequest request, Long goodsCateId, GoodsSiteSearchBean searchBean, PageBean pageBean) {
        Map<String, Object> maps = new HashMap<>();
        // 根据商品分类ID获得频道
        Channel channel = getChannel(goodsCateId);
        Long distinctId = null;
        if (null != request.getSession().getAttribute(DISTINCTID)) {
            distinctId = Long.parseLong(request.getSession().getAttribute(DISTINCTID).toString());
        }
        if (null == distinctId) {
            // 获取默认地址id
            Long dId = this.addressService.getDefaultIdService();
            if (dId == null) {
                // 获取默认地址id
                distinctId = this.addressService.getDefaultIdService();
                if (distinctId == null) {
                    distinctId = 1103L;
                }
            }
            maps.put(DISTINCTID, dId);
        } else {
            maps.put(DISTINCTID, distinctId);
        }
        pageBean.setPageSize(8);
        maps.put("searchBean", searchBean);
        // 初始化频道页楼层数据
        IndexFloor indexFloor = channelSiteService.getChannelStoreys(request, channel.getChannelId(), channel.getTempId(), searchBean, pageBean);
        maps.put("indexFloor", indexFloor);
        maps.put("goodsCateId", goodsCateId);
        return maps;
    }

    /**
     * 加载频道信息
     *
     * @param request
     * @param goodsCateId
     *            商品分类ID：一级分类、二级分类
     * @return
     */
    @RequestMapping("/channelView")
    public ModelAndView channelView(HttpServletRequest request, Long goodsCateId, GoodsSiteSearchBean searchBean, PageBean pageBean) {
        ModelAndView mav = new ModelAndView();
        // 根据商品分类ID获得频道
        Channel channel = getChannel(goodsCateId);
        // 根据类型ID查询VO信息,仅是查询当前分类本身以及父分类
        GoodsCateVo cate = this.goodsCateService.queryCateByCatId(goodsCateId);
        // 如果没有频道，就到商品列表页
        if (null == channel) {
            String goodsListView;
            if (cate.getCatGrade() == 1) {
                goodsListView = request.getContextPath() + "/list/" + cate.getCatId() + "-" + cate.getCatId() + ".html";

            } else {
                goodsListView = request.getContextPath() + "/list/" + cate.getCatId() + "-" + cate.getCatParentId() + ".html";
            }
            mav.setView(new RedirectView(goodsListView));
            return mav;
        } else {
            mav.setViewName(CHANNEL_VIEW);
            mav.addObject("cate", cate);
            // 站点信息
            mav.addObject("sys", basicSetService.findBasicSet());
            // 频道信息
            mav.addObject("channel", channel);
            pageBean.setPageSize(8);
            // 楼层信息
            Long distinctId = null;
            if (null != request.getSession().getAttribute(DISTINCTID)) {
                distinctId = Long.parseLong(request.getSession().getAttribute(DISTINCTID).toString());
            }
            if (null == distinctId) {
                // 获取默认地址id
                Long dId = this.addressService.getDefaultIdService();
                if (dId == null) {
                    // 获取默认地址id
                    distinctId = this.addressService.getDefaultIdService();
                    if (distinctId == null) {
                        distinctId = 1103L;
                    }
                }
                mav.addObject(DISTINCTID, dId);
            } else {
                mav.addObject(DISTINCTID, distinctId);
            }
            // 初始化频道页楼层数据
            mav.addObject("floor", channelSiteService.getChannelStoreys(request, channel.getChannelId(), channel.getTempId(), searchBean, pageBean));
            mav.addObject("searchBean", searchBean);
            mav.addObject("goodsCateId", goodsCateId);
            // 新闻公告信息
            mav.addObject("infoList", infoService.selectByInfoType(channel.getInfoTypeId()));
            // 获取页面轮播大广告
            mav.addObject("avc",
                    channelAdverService.selectchannelAdverByParamForSite(channel.getChannelId(), channel.getTempId(), null, null, ATID1, ADVERTTYPE, null, "0", null, null));
            // 获取页面轮播小广告
            mav.addObject("avs",
                    channelAdverService.selectchannelAdverByParamForSite(channel.getChannelId(), channel.getTempId(), null, null, ATID2, ADVERTTYPE, null, "0", null, null));
            // 获取页面广告
            mav.addObject("pageAdvs",
                    channelAdverService.selectchannelAdverByParamForSite(channel.getChannelId(), channel.getTempId(), null, null, ATID3, ADVERTTYPE, null, "0", null, null));

            // 加载分类导航
            mav.addObject("classifyBar", channelSiteService.getClassifyBar(channel.getChannelId(), channel.getTempId()));
            // 今日推荐商品
            mav.addObject("channelGoodsFlag0", channelSalesGoodsService.selectChannelGoodsByFlag(channel.getChannelId(), "0", ADVERTNUM));
            mav.addObject("channelGoodsFlag1", channelSalesGoodsService.selectChannelGoodsByFlag(channel.getChannelId(), "1", ADVERTNUM));
            mav.addObject("channelGoodsFlag2", channelSalesGoodsService.selectChannelGoodsByFlag(channel.getChannelId(), "2", ADVERTNUM));
            return topAndBottomService.getTopAndBottom(mav);
        }
    }

    /**
     * Ajax加载根据商品分类ID获得商品列表
     * 
     * @param tagId
     *            楼层标签id
     * @return List<IndexGoodsBean>
     */
    @RequestMapping("/loadstoreytagproduct")
    @ResponseBody
    public List<IndexGoodsBean> loadStoreyTagProduct(Long tagId) {
        // 通过楼层标签id查询商品列表
        return channelSiteService.selectStoreyTagProductsByTagId(tagId);
    }

    /**
     * 根据商品分类ID获得频道
     * 
     * @param goodsCateId
     *            商品分类ID：一级分类、二级分类
     * @return 频道 @see com.ningpai.channel.bean.Channel
     */
    private Channel getChannel(Long goodsCateId) {
        try {
            // 根据类型ID查询VO信息,仅是查询当前分类本身以及父分类
            GoodsCateVo cate = this.goodsCateService.queryCateByCatId(goodsCateId);
            if (cate.getCatGrade() == 1) {
                // 根据商品分类ID查询频道
                return this.channelService.selectByCateId(cate.getCatId());
            } else if (cate.getCatGrade() == 2) {
                // 根据商品分类ID查询频道
                return this.channelService.selectByCateId(cate.getCatParentId());
            } else {
                LOGGER.debug("==============================商品分类不是一级、二级分类");
                return null;
            }
        } catch (Exception e) {
            LOGGER.error("==============================加载频道模板异常！",e);
            return new Channel();
        }
    }
}
