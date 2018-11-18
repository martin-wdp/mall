/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.channel.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.ningpai.goods.service.GoodsBrandService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.channel.bean.Channel;
import com.ningpai.channel.bean.GoodsCate;
import com.ningpai.channel.service.ChannelAdverService;
import com.ningpai.channel.service.ChannelService;
import com.ningpai.channel.service.GoodsCateService;
import com.ningpai.channel.service.ISysDictionaryBiz;
import com.ningpai.common.util.ConstantUtil;
import com.ningpai.information.service.InformationTypeService;
import com.ningpai.information.vo.InformationTypeVo;
import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.temp.bean.SysTemp;
import com.ningpai.temp.service.TempService;
import com.ningpai.util.PageBean;

/**
 * 控制器-频道
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年3月27日下午2:25:56
 */
@Controller
public class ChannelController {

    private static final String LOGINUSERID = "loginUserId";
    // private static final String LIST_ACTION = "queryChannelByPageBean.htm";
    private static final Long TEMPTYPE = 139L;
    private static final String INFOTYPELIST = "infoTypeList";
    private static final String CHANNEL = "channel";
    private static final String QUERYCHANNELBYPAGEBEAN = "queryChannelByPageBean.htm?tempId=";
    private static final String USERNAME = ",用户名:";
    public static final String NAME = "name";
    public static final String OPERAPATH = "operaPath";
    private static final String GOODSCATELIST = "goodsCateList";
    private static final String SYSTEMPLIST = "sysTempList";

    /** 频道业务接口 */
    private ChannelService channelService;
    /** 广告业务接口 */
    private ChannelAdverService channelAdverService;
    /** 文章栏目业务接口 */
    private InformationTypeService infoTypeService;
    /** 商品分类业务接口 */
    private GoodsCateService goodsCateService;
    /** 系统字典业务接口 */
    private ISysDictionaryBiz sysDicBiz;
    /** 频道模板设置业务类 */
    private TempService tempService;
    @Resource(name = "GoodsBrandService")
    private GoodsBrandService goodsBrandService;

    /**
     * 分页查询频道
     * 
     * @param pb
     * @param searchText
     * @return
     */
    @RequestMapping("/queryChannelByPageBean")
    public ModelAndView queryChannelByPageBean(PageBean pb, String searchText, Long tempId) {
        Map<String, Object> map = new HashMap<String, Object>();
        /* 获取所有商品分类，遍历取出一级分类 */
        List<GoodsCate> goodsCateList = goodsCateService.queryAllFirstGradeGoosCate();
        map.put(GOODSCATELIST, goodsCateList);
        // 获取模板列表
        List<SysTemp> sysTempList = tempService.querySystempByType(TEMPTYPE);
        map.put(SYSTEMPLIST, sysTempList);
        PageBean pageBean=channelService.findChannelByPageBean(pb, searchText, tempId);
        // 返回查询的结果
        // 设置集合
        // 设置模板id
        return new ModelAndView("jsp/channel/channel_list", "pb", pageBean).addObject("tempId", tempId).addObject("map", map);
    }

    /**
     * 通过频道Id查询频道
     * 
     * @param channelId
     * @return
     */
    @RequestMapping("/queryChannelByChannelId")
    @ResponseBody
    public Map<String, Object> queryChannelByChannelId(Long channelId) {
        Map<String, Object> map = new HashMap<>();
        /* 获取所有商品分类，遍历取出一级分类 */
        List<GoodsCate> goodsCateList = goodsCateService.queryAllFirstGradeGoosCate();
        map.put(GOODSCATELIST, goodsCateList);
        // 获取模板列表
        List<SysTemp> sysTempList = tempService.querySystempByType(TEMPTYPE);
        map.put(SYSTEMPLIST, sysTempList);
        Channel channel = channelService.findChannelByID(channelId);
        map.put("channel", channel);
        return map;
    }

    /**
     * 查看频道
     * 
     * @param channelID
     * @return
     */
    @RequestMapping("/showChannel")
    public ModelAndView showChannel(Long channelID) {
        Map<String, Object> map = new HashMap<String, Object>();
        /* 获取所有商品分类，遍历取出一级分类 */
        List<GoodsCate> goodsCateList = goodsCateService.queryAllFirstGradeGoosCate();
        map.put(GOODSCATELIST, goodsCateList);
        // 获取模板列表
        List<SysTemp> sysTempList = tempService.querySystempByType(TEMPTYPE);
        map.put(SYSTEMPLIST, sysTempList);
        // 获取可发布文章的文章类型
        List<InformationTypeVo> infoTypeList = infoTypeService.selectInfoTypeByAttrForTemp();
        map.put(INFOTYPELIST, infoTypeList);
        /* 判断频道ID是否是空，不是空查询频道 */
        if (null != channelID) {
            Channel channel = channelService.findChannelByID(channelID);
            map.put(CHANNEL, channel);
        }
        return new ModelAndView("jsp/channel/showChannel", "map", map);
    }

    /**
     * 添加频道 添加成功后记录日志
     * 
     * @param request
     *            请求参数
     * @param channel
     *            添加的信息
     * @return
     */
    @RequestMapping("/createChannel")
    public ModelAndView createChannel(HttpServletRequest request, @Valid Channel channel, BindingResult bindingResult) {
        // 判断参数是否符合规范
        if (bindingResult.hasErrors()) {
            // 返回到页面
            return new ModelAndView(new RedirectView(QUERYCHANNELBYPAGEBEAN + channel.getTempId() + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN)));
        }
        // 查询登录id
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        // 设置创建人id
        channel.setCreateUserId(loginUserId);
        // 设置修改人id
        channel.setUpdateUserId(loginUserId);
        // 设置频道的url
        channel.setChannelUrl("channelview/" + channel.getGoodsCatId() + ".html");
        // 添加频道信息
        channelService.createChannel(channel);
        // 用户名
        String customerName = (String) request.getSession().getAttribute(NAME);
        // 记录日志
        OperaLogUtil.addOperaLog(request, customerName, "添加频道", request.getSession().getAttribute(OPERAPATH) + USERNAME + customerName);
        return new ModelAndView(new RedirectView(QUERYCHANNELBYPAGEBEAN + channel.getTempId() + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN)));
    }

    /**
     * 修改频道
     * 
     * @param request
     *            请求参数
     * @param channel
     *            修改实体
     * @param bindingResult
     * @return
     */
    @RequestMapping("/updateChannel")
    public ModelAndView updateChannel(HttpServletRequest request, @Valid Channel channel, BindingResult bindingResult) {
        // 判断修改参数是否符合规范
        if (bindingResult.hasErrors()) {
            // 返回到页面
            return new ModelAndView(new RedirectView(QUERYCHANNELBYPAGEBEAN + channel.getTempId() + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN)));
        }
        // 登录id
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        // 设置修改人id
        channel.setUpdateUserId(loginUserId);
        // 设置修改人id
        channelService.updateChannel(channel);
        // 获取操作人
        String customerName = (String) request.getSession().getAttribute(NAME);
        // 记录操作日志
        OperaLogUtil.addOperaLog(request, customerName, "修改频道", request.getSession().getAttribute(OPERAPATH) + USERNAME + customerName);
        // 返回到页面
        return new ModelAndView(new RedirectView(QUERYCHANNELBYPAGEBEAN + channel.getTempId() + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN)));
    }

    /**
     * 频道配置中分类导航的保存设置
     * 
     * @param request
     * @param channel
     * @param expFleid5
     * @param tempType
     * @return
     */
    @RequestMapping("/updateChannelAndTemp")
    @ResponseBody
    public int updateChannelTemp(HttpServletRequest request, Channel channel, String expFleid5, Long tempType) {
        SysTemp temp = new SysTemp();
        // temp.setTempType(139L);
        temp.setExpFleid5(expFleid5);
        temp.setTempId(Integer.parseInt(channel.getTempId() + ""));
        // temp.setTempType(tempType);
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        channel.setUpdateUserId(loginUserId);
        int num1 = this.tempService.updateSystemp(temp);
        int num2 = channelService.updateChannel(channel);
        String customerName = (String) request.getSession().getAttribute(NAME);
        OperaLogUtil.addOperaLog(request, customerName, "修改频道", request.getSession().getAttribute(OPERAPATH) + USERNAME + customerName);
        return num1 + num2;
    }

    /**
     * 1、根据id删除频道
     *
     * 2、记录日志
     *
     * @param request
     *            请求
     * @param response
     *            请求
     */
    @RequestMapping("/deleteChannel")
    public ModelAndView deleteChannel(HttpServletRequest request, HttpServletResponse response, Long tempId) {
        // 获取登录id
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        // 获取要删除的id
        String[] channelIDs = request.getParameterValues("channelIDs");
        String[] channelids = channelIDs[0].split(",");
        // 循环删除
        for (int i = 0; i < channelids.length; i++) {
            // 获取id
            Long id = Long.valueOf(channelids[i]);
            // 删除信息
            channelService.deleteChannel(id, loginUserId);
        }
        // 获取用户名
        String customerName = (String) request.getSession().getAttribute(NAME);
        // 记录日志
        OperaLogUtil.addOperaLog(request, customerName, "删除频道", request.getSession().getAttribute(OPERAPATH) + USERNAME + customerName);
        return new ModelAndView(new RedirectView("queryChannelByPageBean.htm?tempId=" + tempId));
    }

    /**
     * 根据频道ID开启频道
     * 
     * @param channelId
     *            频道id
     * @param tempId
     *            模板id
     * @param request
     *            请求
     * @param response
     *            请求
     * @return
     */
    @RequestMapping("/openChannelById")
    public ModelAndView openChannelById(Long channelId, Long tempId, HttpServletRequest request, HttpServletResponse response) {
        // 获取登录id
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        // 根据频道id开启频道
        channelService.openChannelByID(channelId, loginUserId);
        // 获取用户名
        String customerName = (String) request.getSession().getAttribute(NAME);
        // 添加日志
        OperaLogUtil.addOperaLog(request, customerName, "开启频道", request.getSession().getAttribute(OPERAPATH) + USERNAME + customerName);
        // 返回
        return new ModelAndView(new RedirectView(QUERYCHANNELBYPAGEBEAN + tempId + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN)));
    }

    /**
     * 根据频道ID关闭频道
     * 
     * @param channelId
     *            频道id
     * @param tempId
     *            模板id
     * @param request
     *            请求
     * @param response
     *            请求
     * @return
     */
    @RequestMapping("/closeChannelById")
    public ModelAndView closeChannelById(Long channelId, Long tempId, HttpServletRequest request, HttpServletResponse response) {
        // 登录id
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        // 关闭频道末班
        channelService.closeChannelByID(channelId, loginUserId);
        // 获取用户名
        String customerName = (String) request.getSession().getAttribute(NAME);
        // 记录日志
        OperaLogUtil.addOperaLog(request, customerName, "关闭频道", request.getSession().getAttribute(OPERAPATH) + USERNAME + customerName);
        // 返回页面
        return new ModelAndView(new RedirectView(QUERYCHANNELBYPAGEBEAN + tempId + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN)));
    }

    /**
     * 开启所有频道
     * 
     * @param request
     *            请求
     * @param tempId
     *            请求
     * @return
     */
    @RequestMapping("/openAllChannel")
    public ModelAndView openAllChannel(HttpServletRequest request, Long tempId) {
        // 开启频道
        channelService.openAllChannel();
        // 获取用户名
        String customerName = (String) request.getSession().getAttribute(NAME);
        // 记录日志
        OperaLogUtil.addOperaLog(request, customerName, "开启所有频道", request.getSession().getAttribute(OPERAPATH) + USERNAME + customerName);
        // 返回页面
        return new ModelAndView(new RedirectView(QUERYCHANNELBYPAGEBEAN + tempId + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN)));
    }

    /**
     * 关闭所有频道
     * 
     * @param request
     *            请求
     * @param tempId
     *            模板id
     * @return
     */
    @RequestMapping("/closeAllChannel")
    public ModelAndView closeAllChannel(HttpServletRequest request, Long tempId) {
        // 关闭所有频道
        channelService.closeAllChannel();
        // 获取用户名
        String customerName = (String) request.getSession().getAttribute(NAME);
        // 记录日志
        OperaLogUtil.addOperaLog(request, customerName, "关闭所有频道", request.getSession().getAttribute(OPERAPATH) + USERNAME + customerName);
        // 返回页面
        return new ModelAndView(new RedirectView(QUERYCHANNELBYPAGEBEAN + tempId + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN)));
    }

    /**
     * 查看频道设置
     * 
     * @param channelId
     *            频道id
     * @return
     */
    @RequestMapping("/showChannelSet")
    public ModelAndView showChannelSet(Long channelId,Long goodsCatId) {
        // 视图对象
        ModelAndView mav = new ModelAndView();
        Channel channel = this.channelService.findChannelByID(channelId);
        mav.addObject(CHANNEL, channel);
        SysTemp sysTemp = tempService.getSystempById(channel.getTempId());
        mav.addObject("sysTemp", sysTemp);
        // 获取可发布文章的文章类型
        List<InformationTypeVo> infoTypeList = infoTypeService.selectInfoTypeByAttrForTemp();
        mav.addObject(INFOTYPELIST, infoTypeList);
        mav.addObject("cate", goodsCateService.queryAllFirstGradeGoosCate());
        mav.addObject("brand", goodsBrandService.queryAllBrand());
        mav.addObject("goodsCatId",goodsCatId);
        mav.setViewName("jsp/channel/setChannel");
        return mav;
    }

    /**
     * 查看导航频道设置
     * 
     * @param barId
     *            页面导航ID
     * @return
     */
    @RequestMapping("/showBarChannelSet")
    public ModelAndView showBarChannelSet(String barId) {
        // 视图控制器
        ModelAndView mav = new ModelAndView();
        // 频道实体类
        Channel channel = this.channelService.selectByBarId(barId);
        // 设置实体类
        mav.addObject(CHANNEL, channel);
        // 获取末班设置
        SysTemp sysTemp = tempService.getSystempById(channel.getTempId());
        mav.addObject("sysTemp", sysTemp);
        // 获取可发布文章的文章类型
        List<InformationTypeVo> infoTypeList = infoTypeService.selectInfoTypeByAttrForTemp();
        mav.addObject(INFOTYPELIST, infoTypeList);
        mav.setViewName("jsp/channel/set_bar_channel");
        return mav;
    }

    public ChannelService getChannelService() {
        return channelService;
    }

    @Resource(name = "ChannelService")
    public void setChannelService(ChannelService channelService) {
        this.channelService = channelService;
    }

    public InformationTypeService getInfoTypeService() {
        return infoTypeService;
    }

    @Resource(name = "InformationTypeService")
    public void setInfoTypeService(InformationTypeService infoTypeService) {
        this.infoTypeService = infoTypeService;
    }

    public GoodsCateService getGoodsCateService() {
        return goodsCateService;
    }

    @Resource(name = "ChannelGoodsCateService")
    public void setGoodsCateService(GoodsCateService goodsCateService) {
        this.goodsCateService = goodsCateService;
    }

    public ISysDictionaryBiz getSysDicBiz() {
        return sysDicBiz;
    }

    @Resource(name = "channelSysDictionaryBizImpl")
    public void setSysDicBiz(ISysDictionaryBiz sysDicBiz) {
        this.sysDicBiz = sysDicBiz;
    }

    public TempService getTempService() {
        return tempService;
    }

    @Resource(name = "TempService")
    public void setTempService(TempService tempService) {
        this.tempService = tempService;
    }

    public ChannelAdverService getChannelAdverService() {
        return channelAdverService;
    }

    @Resource(name = "ChannelAdverService")
    public void setChannelAdverService(ChannelAdverService channelAdverService) {
        this.channelAdverService = channelAdverService;
    }

}
