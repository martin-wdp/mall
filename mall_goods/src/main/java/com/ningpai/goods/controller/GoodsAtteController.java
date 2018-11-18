/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.common.safe.CSRFTokenManager;
import com.ningpai.goods.service.GoodsAtteService;
import com.ningpai.goods.util.ValueUtil;
import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;
import com.ningpai.util.SelectBean;

/**
 * 商品关注Controller
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年3月18日 下午5:20:13
 * @version 1.0
 */
@Controller
public class GoodsAtteController {
    private GoodsAtteService goodsAtteService;

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(GoodsAtteController.class);

    public GoodsAtteService getGoodsAtteService() {
        return goodsAtteService;
    }

    @Resource(name = "GoodsAtteService")
    public void setGoodsAtteService(GoodsAtteService goodsAtteService) {
        this.goodsAtteService = goodsAtteService;
    }

    /**
     * 查询所有的商品集合
     * 
     * @param pageBean
     *            分页辅助 {@link com.ningpai.util.PageBean}
     */
    @RequestMapping("/findallgoodsatte")
    public ModelAndView findAllGoodsAtte(PageBean pageBean, SelectBean selectBean) {
        // 定义一个HashMap集合
        Map<String, Object> map = new HashMap<String, Object>();
        // 查询数据集合塞到map中
        map.put("pb", this.goodsAtteService.queryByPageAndParam(pageBean, selectBean));
        // 把参数塞到map集合中
        map.put("searchBean", selectBean);
        // 打印日志
        LOGGER.info(ValueUtil.FINDALLGOODSATTEINFO);
        // 返回结果
        return new ModelAndView(ValueUtil.GOODSATTEINFO).addObject("map", map);
    }

    /**
     * 根据货品ID查询
     */
    @RequestMapping("/findGoodsAtteByProductId")
    public ModelAndView findGoodsAtteByProductId(Long productId, PageBean pageBean) {
        // 打印日志
        LOGGER.info(ValueUtil.FINALGOODSATTEINFO);
        // 返回结果
        return new ModelAndView(ValueUtil.PRODUCTINFO).addObject("pb", this.goodsAtteService.querybyProductId(pageBean, productId));
    }

    /**
     * 删除商品关注
     * 
     * @param atteId
     *            待删除的商品记录主键ID {@link java.lang.Long}
     */
    @RequestMapping("/deletegoodsatte")
    public ModelAndView deleteGoodsAtte(Long atteId, HttpServletRequest request) {
        // 调用删除方法
        this.goodsAtteService.deleteAtte(atteId);
        // 打印日志
        LOGGER.info(ValueUtil.DELGOODSATTEINFO + "，删除的关注ID为：" + atteId);
        // 记录操作日志
        OperaLogUtil.addOperaLog(request, (String) request.getSession().getAttribute(ValueUtil.NAME), ValueUtil.DELGOODSATTEINFO,
                (String) request.getSession().getAttribute(ValueUtil.OPERAPATH) + ",用户名：" + request.getSession().getAttribute(ValueUtil.NAME));
        // 返回结果视图
        return new ModelAndView(new RedirectView(ValueUtil.FINDALLGOODSATTE + ValueUtil.TOKENPARAM + CSRFTokenManager.getTokenFromRequest(request)));
    }

    /**
     * 批量删除商品关注
     * 
     * @param atteIds
     *            待删除的主键数组 {@link java.lang.Long}
     */
    @RequestMapping("/batchdeletegoodsatte")
    public ModelAndView batchDeleteGoodsAtte(Long[] atteIds, HttpServletRequest request) {
        // 执行批量删除操作
        this.goodsAtteService.batchDelAtte(atteIds);
        // 打印日志
        LOGGER.info(ValueUtil.BATCHDELGOODSATTEINFO);
        // 记录操作日志
        OperaLogUtil.addOperaLog(request, (String) request.getSession().getAttribute(ValueUtil.NAME), ValueUtil.BATCHDELGOODSATTEINFO,
                (String) request.getSession().getAttribute(ValueUtil.OPERAPATH) + ",用户名：" + (String) request.getSession().getAttribute(ValueUtil.NAME));
        // 返回结果视图
        return new ModelAndView(new RedirectView(ValueUtil.BATCHFINDALLGOODSATTE + ValueUtil.TOKENPARAM + CSRFTokenManager.getTokenFromRequest(request)));
    }

    /**
     * 根据货品ID发送邮件给关注该货品的用户
     */
    @RequestMapping("/sendMailToAtteCustomer")
    public ModelAndView sendMailToAtteCustomer(Long productId) {
        return null;
    }

    /**
     * 查询商品关注总数
     * 
     * @return
     */
    @RequestMapping("/selectGoodsAtteCount")
    @ResponseBody
    public int selectGoodsAtteCount() {
        return goodsAtteService.selectGoodsAtteCount();
    }

}
