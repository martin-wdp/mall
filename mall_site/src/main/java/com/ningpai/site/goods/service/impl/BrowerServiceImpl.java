/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.site.goods.service.impl;

import com.ningpai.common.util.ConstantUtil;
import com.ningpai.site.customer.controller.CustomerController;
import com.ningpai.site.customer.service.BrowserecordService;
import com.ningpai.site.goods.dao.GoodsProductMapper;
import com.ningpai.site.goods.service.BrowerService;
import com.ningpai.site.goods.vo.GoodsProductVo;
import com.ningpai.util.MyLogger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 浏览历史Service实现
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年4月17日 下午2:27:40
 * @version 1.0
 */
@Service("BrowerService")
public class BrowerServiceImpl implements BrowerService {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(CustomerController.class);

    private static final String COOKIE = "_npstore_browpro";

    private BrowserecordService browserecordService;

    private GoodsProductMapper goodsProductMapper;

    /**
     * 保存浏览记录
     * 
     * @param request
     *            请求对象
     * @param response
     * @param productId
     *            货品ID
     * @return
     * @throws UnsupportedEncodingException
     */
    @Transactional
    public boolean saveBrowerHis(HttpServletRequest request, HttpServletResponse response, Long productId) throws UnsupportedEncodingException {
        // 取出当期那登陆的用户ID
        Long custId = (Long) request.getSession().getAttribute("customerId");
        Cookie cookie;
        String allBroPro = "";
        boolean cookieExits = false;
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            if (null != custId) {
                map.put("custId", custId);
                map.put("productId", productId);
                goodsProductMapper.updateGoodsBrowStatus(map);
                this.goodsProductMapper.saveGoodsBrow(map);
            } else {
                // 先取出来cookie中的值,然后拼接,重新保存到cookie中
                Cookie[] oldCookie = request.getCookies();
                if (null != oldCookie) {
                    for (Cookie cookie2 : oldCookie) {
                        if (null != cookie2 && COOKIE.equals(cookie2.getName())) {
                            allBroPro = URLDecoder.decode(cookie2.getValue(), ConstantUtil.UTF);
                            if (allBroPro.indexOf(",s" + productId + "e") != -1) {
                                allBroPro = allBroPro.replace(",s" + productId + "e", "");
                            }
                            if (allBroPro.indexOf(",s" + productId) != -1) {
                                allBroPro = allBroPro.replace(",s" + productId, "");
                            }
                            // 说明存储浏览记录的cookie已经存在
                            cookieExits = true;
                        }
                    }
                    // 如果存在标记为false
                    allBroPro += ",s" + productId + "e";
                }
                // 如果存储浏览记录的cookie不存在
                if (!cookieExits) {
                    allBroPro = String.valueOf(",s" + productId + "e");
                }
                // 如果当前用户是未登录写状态就保存浏览记录到cookie中
                cookie = new Cookie(COOKIE, URLEncoder.encode(allBroPro, ConstantUtil.UTF));
                cookie.setMaxAge(15 * 24 * 3600);
                // 保存Cookie
                cookie.setPath("/");
                response.addCookie(cookie);
            }
            return false;
        } finally {
            custId = null;
            allBroPro = null;
            map = null;
        }
    }

    /**
     * 获取浏览的历史记录
     * 
     * @param request
     *            请求对象
     * @return
     */
    public Map<String, Object> loadBrowHist(HttpServletRequest request) {
        // 取出当期那登陆的用户ID
        Long custId = (Long) request.getSession().getAttribute("customerId");
        Map<String, Object> map = new HashMap<String, Object>();
        String cookiestr = "";
        try {
            if (null != custId) {
                map.put("type", 1);
                map.put("browHist", this.browserecordService.selectBrowserecord(custId));
            } else {
                map.put("type", 2);
                // 先取出来cookie中的值
                Cookie[] cookie = request.getCookies();
                if (null != cookie) {
                    for (Cookie cookie2 : cookie) {
                        if (null != cookie2 && COOKIE.equals(cookie2.getName())) {
                            try {
                                cookiestr = URLDecoder.decode(cookie2.getValue(), ConstantUtil.UTF);
                            } catch (UnsupportedEncodingException e) {
                                LOGGER.error("获取浏览记录报错：" + e);
                            }
                        }
                    }
                    // 判断取到的cookie值是不是为空
                    if (!"".equals(cookiestr)) {
                        cookiestr = cookiestr.replace(",s", ",");
                        cookiestr = cookiestr.replace("e", "");
                        // 截取第一个,
                        cookiestr = cookiestr.substring(1, cookiestr.length());
                        Map<String, Object> proMap = new HashMap<String, Object>();
                        List<Long> productIds = new ArrayList<Long>();
                        List<GoodsProductVo> proList = new ArrayList<GoodsProductVo>();
                        List<GoodsProductVo> calcList = new ArrayList<GoodsProductVo>();
                        String[] ids = cookiestr.split(",");
                        try {
                            for (int i = 0; i < ids.length; i++) {
                                productIds.add(Long.parseLong(ids[i]));
                            }
                            proMap.put("productIds", productIds);
                            proList = this.goodsProductMapper.queryProductsByProductIds(proMap);
                            // 循环匹配并排序
                            for (int i = productIds.size() - 1; i > -1; i--) {
                                for (GoodsProductVo product : proList) {
                                    if (productIds.get(i).equals(product.getGoodsInfoId())) {
                                        calcList.add(product);
                                    }
                                }
                            }
                            map.put("productList", calcList);
                        } finally {
                            productIds = null;
                            ids = null;
                            proMap = null;
                        }
                    }
                }
            }
            return map;
        } finally {
            map = null;
            custId = null;
        }
    }

    public BrowserecordService getBrowserecordService() {
        return browserecordService;
    }

    @Resource(name = "browserecordService")
    public void setBrowserecordService(BrowserecordService browserecordService) {
        this.browserecordService = browserecordService;
    }

    public GoodsProductMapper getGoodsProductMapper() {
        return goodsProductMapper;
    }

    @Resource(name = "HsiteGoodsProductMapper")
    public void setGoodsProductMapper(GoodsProductMapper goodsProductMapper) {
        this.goodsProductMapper = goodsProductMapper;
    }

}
