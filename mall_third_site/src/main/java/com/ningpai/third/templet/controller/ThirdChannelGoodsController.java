package com.ningpai.third.templet.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.channel.bean.ChannelStoreyGoods;
import com.ningpai.channel.service.ChannelGoodsService;
import com.ningpai.channel.service.ChannelStoreyGoodsService;
import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.temp.bean.SysTemp;
import com.ningpai.temp.service.ThirdTempService;
import com.ningpai.third.goods.service.ThirdOtherService;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;

/**
 * 第三方后台热销商品控制器
 *
 * @author qiyuanyuan
 */
@Controller
public class ThirdChannelGoodsController {

    /**
     * 记录日志对象
     */
    private static final MyLogger LOGGER = new MyLogger(ThirdChannelGoodsController.class);

    private static final String LOGINUSERID = "loginUserId";

    private static final String THIRDID = "thirdId";

    private static final String QUERYTHIRDCHANNELGOODS_HTM = "querythirdchannelgoods.htm?tempId=";

    /**
     * 用户名称
     */
    public static final String NAME = "name";

    /**
     * "operaPath"
     */
    public static final String OPERAPATH = "operaPath";

    /* 业务逻辑层依赖 */
    @Resource(name = "ThirdTempService")
    private ThirdTempService tempService;
    /**
     * 模板商品业务接口
     */
    @Resource(name = "ChannelGoodsService")
    private ChannelGoodsService channelGoodsService;

    /**
     * 楼层商品业务接口
     */
    @Resource(name = "ChannelStoreyGoodsService")
    private ChannelStoreyGoodsService channelStoreyGoodsService;
    @Resource(name = "ThirdOtherService")
    private ThirdOtherService thirdOtherService;

    /**
     * 第三方后台查询热销推荐商品
     *
     * @param request
     * @param pb
     * @param tempId
     * @return
     */
    @RequestMapping("/querythirdchannelgoods")
    public ModelAndView queryThirdChannelGoods(HttpServletRequest request, PageBean pb, Long tempId) {
        Map<String, Object> map = new HashMap<String, Object>();
        SysTemp temp = this.tempService.getSystempById(tempId);
        String thirdId = ((Long) request.getSession().getAttribute(THIRDID)).toString();
        if (thirdId != null && !"".equals(thirdId)) {
            map.put("sellThirdId", thirdId);
        }
        map.put("brandlist", thirdOtherService.queryGrandBrandByThirdId((Long) request.getSession().getAttribute(THIRDID)));
        map.put("temp", temp);
        map.put("pb", channelGoodsService.selectchannelStoreyGoodsByParam(pb, tempId.toString(), null, thirdId));
        return new ModelAndView("temp/temp_hotgoods_list").addObject("map", map);
    }

    /**
     * 查看商家货品
     *
     * @param goodsProductId
     * @param tempId
     * @param request
     * @return
     */
    @RequestMapping("/showthirdhotgoods")
    public ModelAndView showThirdHotGoods(Long goodsProductId, Long tempId, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("temp/show_temp_hotgoods");
        SysTemp temp = this.tempService.getSystempById(tempId);
        Long thirdId = (Long) request.getSession().getAttribute(THIRDID);
        if (null != goodsProductId) {
            ChannelStoreyGoods channelStoreyGoods = channelStoreyGoodsService.getChannelStoreyGoodsById(goodsProductId);
            mav.addObject("channelStoreyGoods", channelStoreyGoods);
        }
        if (thirdId != null) {
            mav.addObject("sellThirdId", thirdId);
        }
        mav.addObject("temp", temp);
        mav.addObject("brandlist", thirdOtherService.queryGrandBrandByThirdId((Long) request.getSession().getAttribute(THIRDID)));
        return mav;
    }

    /**
     * 根据ID获取热销商品信息
     *
     * @param hotGoodsId
     *            热销商品ID
     * @return 热销商品信息
     */
    @RequestMapping("/getHotGoodsById")
    @ResponseBody
    public ChannelStoreyGoods getHotGoodsById(Long hotGoodsId) {
        return channelStoreyGoodsService.getChannelStoreyGoodsById(hotGoodsId);
    }

    /**
     * 添加热销推荐商品
     *
     * @param request
     * @param channelStoreyGoods
     * @return
     */
    @RequestMapping("/createthirdhotgoods")
    public ModelAndView createThirdHotGoods(HttpServletRequest request, @Valid ChannelStoreyGoods channelStoreyGoods, BindingResult bindingResult, Long tempId) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView(new RedirectView(QUERYTHIRDCHANNELGOODS_HTM + tempId));
        }
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        ModelAndView mav = new ModelAndView();
        try {
            if (null == loginUserId) {
                channelStoreyGoods.setCreateUserId(1L);
            } else {
                channelStoreyGoods.setCreateUserId(loginUserId);
            }
            channelStoreyGoods.setTemp1(tempId.toString());
            int n = channelGoodsService.saveChannelStoreyGoods(channelStoreyGoods);
            if (n > 0) {
                String customerName = (String) request.getSession().getAttribute(NAME);
                OperaLogUtil.addOperaLog(request, customerName, "添加热销推荐商品", request.getSession().getAttribute(OPERAPATH) + ",用户名:" + customerName);
                LOGGER.debug("添加热销推荐商品成功！");
            } else {
                LOGGER.debug("添加热销推荐商品失败！");
            }
        } catch (Exception e) {
            LOGGER.error("添加热销推荐商品异常！", e);
        }
        mav.setView(new RedirectView(QUERYTHIRDCHANNELGOODS_HTM + tempId));
        // 返回楼层设置列表
        return mav;
    }

    /**
     * 修改热销推荐商品
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/updatethirdhotgoods")
    public ModelAndView updateThirdHotGoods(HttpServletRequest request, HttpServletResponse response, @Valid ChannelStoreyGoods channelStoreyGoods, BindingResult bindingResult,
            Long tempId, Long channelId) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView(new RedirectView(QUERYTHIRDCHANNELGOODS_HTM + tempId));
        }
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        ModelAndView mav = new ModelAndView();
        try {
            if (null == loginUserId) {
                channelStoreyGoods.setUpdateUserId(1L);
            } else {
                channelStoreyGoods.setUpdateUserId(loginUserId);
            }
            int n = channelGoodsService.updateChannelStoreyGoods(channelStoreyGoods);
            if (n > 0) {
                String customerName = (String) request.getSession().getAttribute(NAME);
                OperaLogUtil.addOperaLog(request, customerName, "修改热销推荐商品", request.getSession().getAttribute(OPERAPATH) + ",用户名:" + customerName);
                LOGGER.debug("修改热销推荐商品成功！");
            } else {
                LOGGER.debug("修改热销推荐商品失败！");
            }
        } catch (Exception e) {
            LOGGER.error("修改热销推荐商品异常！", e);
        }
        mav.setView(new RedirectView(QUERYTHIRDCHANNELGOODS_HTM + tempId));
        return mav;
    }

    /**
     * 删除热销推荐商品
     *
     * @param request
     * @param storeyGoodsIds
     * @param tempId
     * @return
     */
    @RequestMapping("/deletethirdhotgoods")
    public ModelAndView deleteThirdHotGoods(HttpServletRequest request, Long[] storeyGoodsIds, Long tempId) {
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        if (null == loginUserId) {
            loginUserId = 1L;
        }
        for (int i = 0; i < storeyGoodsIds.length; i++) {
            channelGoodsService.deleteChannelStoreyGoods(storeyGoodsIds[i], loginUserId);
        }
        return new ModelAndView(new RedirectView(QUERYTHIRDCHANNELGOODS_HTM + tempId));
    }
}
