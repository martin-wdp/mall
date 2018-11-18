/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.m.shoppingcart.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ningpai.common.util.ConstantUtil;
import com.ningpai.other.util.CustomerConstantStr;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.m.common.service.SeoService;
import com.ningpai.m.shoppingcart.bean.ShoppingCart;
import com.ningpai.m.shoppingcart.bean.ShoppingCartWareUtil;
import com.ningpai.m.shoppingcart.service.ShoppingCartService;
import com.ningpai.marketing.service.MarketingService;
import com.ningpai.util.PageBean;
import com.ningpai.util.ShoppingCartConstants;

/**
 * 购物车控制器
 * 
 * @author NINGPAI-LIH
 *
 */
@Controller
public class ShoppingCartMController {

    @Resource(name = "ShoppingCartService")
    private ShoppingCartService shoppingCartService;

    @Resource(name = "MarketingService")
    private MarketingService marketingService;

    @Resource(name = "SeoService")
    private SeoService seoService;

    /**
     * 查询购物车
     * 
     * @return ModelAndView
     */
    @RequestMapping("myshoppingmcart")
    public ModelAndView shoppingCart(HttpServletRequest request, PageBean pageBean, HttpServletResponse response) {
        ShoppingCartWareUtil wareUtil = null;
        ModelAndView mav = null;
        request.getSession().setAttribute("tok", UUID.randomUUID().toString());
        try {
            // 获取地址信息
            wareUtil = shoppingCartService.selectPNameByParam(request);
            mav = new ModelAndView(ShoppingCartConstants.SHOPPINGMCARTLIST).addObject("pb", shoppingCartService.selectShoppingCart(request, wareUtil, pageBean, response))
                    .addObject("pro", marketingService.selectAll()).addObject("sx", request.getSession().getAttribute("tok").toString());
            mav.addObject(ConstantUtil.PAGENAME,"我的购物车");
            return seoService.getCurrSeo(mav);
        } finally {
            wareUtil = null;
            mav = null;
        }
    }

    /**
     * 修改购物数量
     * 
     * @param shoppingCartId
     * @param num
     * @return int
     */
    @RequestMapping("changeshoppingmcartbyid")
    @ResponseBody
    public int changeShoppingCartById(Long shoppingCartId, Long num) {
        return shoppingCartService.changeShoppingCartById(shoppingCartId, num);
    }

    /**
     * 获取购物车的货品的总数AJAX
     *
     * @return int
     */
    @RequestMapping("getShoppingCartGoodsSum")
    @ResponseBody
    public int getShoppingCartGoodsSum(HttpServletRequest request) {
        //未登录
        return shoppingCartService.getShoppingCartGoodsSum(request);
    }

    /**
     * 删除购物车商品
     * 
     * @param request
     * @return int
     */
    @RequestMapping("delshoppingcartbyid")
    @ResponseBody
    public int delShoppingCartById(Long shoppingCartId, Long goodsInfoId, HttpServletRequest request, HttpServletResponse response) {
        return shoppingCartService.delShoppingCartById(shoppingCartId, goodsInfoId, request, response);
    }

    /**
     * 添加购物车的控制器
     * 
     * @param goodsNum
     *            购买货品的数量
     * @param productId
     *            货品ID
     * @param request
     *            请求对象
     * @return true 添加成功 false 添加失败
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("/addProductToShopmCar")
    @ResponseBody
    public boolean addProductToShopCar(@RequestParam(defaultValue = "1")Long goodsNum, Long productId, HttpServletRequest request, Long distinctId, HttpServletResponse response, Long fitId)
            throws UnsupportedEncodingException {
        ShoppingCart shoppingCart = new ShoppingCart();
        try {
            shoppingCart.setDistinctId(null == distinctId ? 1103L : distinctId);
            shoppingCart.setGoodsInfoId(productId);
            shoppingCart.setGoodsNum(goodsNum);
            return this.shoppingCartService.addShoppingCart(shoppingCart, request, response) >= 1 ? true : false;
        } finally {
            shoppingCart = null;
        }
    }

    /**
     * 立即加入购物车
     * 
     * @param goodsNum
     *            购买货品的数量
     * @param productId
     *            货品ID
     * @param request
     *            请求对象
     * @return true 添加成功 false 添加失败
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("/addproducttoshopmcarl")
    public ModelAndView addProductToShopCarL(@RequestParam(defaultValue = "1")Long goodsNum, Long productId, HttpServletRequest request, Long distinctId, HttpServletResponse response, Long fitId)
            throws UnsupportedEncodingException {
        ShoppingCart shoppingCart = new ShoppingCart();
        try {
            shoppingCart.setDistinctId(null == distinctId ? 1103L : distinctId);
            shoppingCart.setGoodsInfoId(productId);
            shoppingCart.setGoodsNum(goodsNum);
            Long custId = (Long) request.getSession().getAttribute("customerId");
            ModelAndView mav = null;
            if (custId == null) {
                mav = new ModelAndView("redirect:/login.html?url=/item/" + productId + ".html");
                return seoService.getCurrSeo(mav);
            }
            shoppingCart.setCustomerId(custId);
            Long shopping = shoppingCartService.selectLastId(shoppingCart, response, request);
            //if (shopping == 0) {
                mav = seoService.getCurrSeo(new ModelAndView(new RedirectView("order/myshoppingmcart.html")));
            //} else {
               // mav = seoService.getCurrSeo(new ModelAndView(new RedirectView("order/suborder.html")).addObject("box", shopping));
           // }
            return seoService.getCurrSeo(mav);
        } finally {
            shoppingCart = null;
        }
    }

    /**
     * 添加购物车的控制器
     * 
     * @param goodsNum
     *            购买货品的数量
     * @param productId
     *            货品ID
     * @param request
     *            请求对象
     * @return 添加购物车成功页面
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("/addproducttoshopcarnew")
    public ModelAndView addProductToShopCarNew(@RequestParam(defaultValue = "1")Long goodsNum, Long productId, HttpServletRequest request, Long distinctId, HttpServletResponse response, Long fitId)
            throws UnsupportedEncodingException {
        ShoppingCart shoppingCart = new ShoppingCart();
        try {
            shoppingCart.setDistinctId(null == distinctId ? 1103L : distinctId);
            shoppingCart.setGoodsInfoId(productId);
            shoppingCart.setGoodsNum(goodsNum);
            this.shoppingCartService.addShoppingCart(shoppingCart, request, response);
            return seoService.getCurrSeo(new ModelAndView(new RedirectView("addcartsuc.htm")).addObject("goodsInfoId", productId));
        } finally {
            shoppingCart = null;
        }
    }


    /**
     * 添加购物车的控制器
     *
     * @param goodsNum
     *            购买货品的数量
     * @param productId
     *            货品ID
     * @param request
     *            请求对象
     * @return 添加购物车成功页面
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("/addproducttoshopcarnewAJAX")
    @ResponseBody
    public Map<String,Object> addproducttoshopcarnewAJAX(@RequestParam(defaultValue = "1")Long goodsNum, Long productId, HttpServletRequest request, Long distinctId, HttpServletResponse response, Long fitId)
            throws UnsupportedEncodingException {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setDistinctId(null == distinctId ? 1103L : distinctId);
        shoppingCart.setGoodsInfoId(productId);
        shoppingCart.setGoodsNum(goodsNum);
        return  this.shoppingCartService.addShoppingCartAJAX(shoppingCart, request, response);
           // return seoService.getCurrSeo(new ModelAndView(new RedirectView("addcartsuc.htm")).addObject("goodsInfoId", productId));
    }

    /**
     * 立即结算--先添加到购物车后跳转到提交订单页
     * @param goodsNum
     * @param productId
     * @param request
     * @param distinctId
     * @param attr
     * @return
     * @throws UnsupportedEncodingException
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("/immediatelyBuy")
    public ModelAndView immediatelyBuy(@RequestParam(defaultValue = "1")Long goodsNum, Long productId, HttpServletRequest request,Long distinctId,RedirectAttributes attr) throws UnsupportedEncodingException {
        Long customerId = (Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID);
        if (customerId == null) {
            return new ModelAndView("redirect:/login.html?url=/item/" + productId + ".html");
        }

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setDistinctId(null == distinctId ? 1103L : distinctId);
        shoppingCart.setGoodsInfoId(productId);
        shoppingCart.setGoodsNum(goodsNum);
        shoppingCart.setCustomerId(customerId);
        shoppingCart.setDelFlag("0");
        shoppingCart.setShoppingCartTime(new Date());
        Long cartId =shoppingCartService.immediatelyBuyAddCart(shoppingCart);
        attr.addAttribute("box",cartId);
        return new ModelAndView("redirect:/order/suborder.html");
    }

    /**
     *
     * */
    @RequestMapping("thridShoppingCart")
    public ModelAndView thridShoppingCart(HttpServletRequest request, PageBean pageBean, HttpServletResponse response) {
        ShoppingCartWareUtil wareUtil = null;
        ModelAndView mav = null;
        request.getSession().setAttribute("tok", UUID.randomUUID().toString());
        try {
            // 获取地址信息
            wareUtil = shoppingCartService.selectPNameByParam(request);
            mav = new ModelAndView(ShoppingCartConstants.SHOPPINGMCARTLIST).addObject("pb", shoppingCartService.selectShoppingCart(request, wareUtil, pageBean, response))
                    .addObject("pro", marketingService.selectAll()).addObject("sx", request.getSession().getAttribute("tok").toString());
            mav.addObject(ConstantUtil.PAGENAME,"我的购物车");
            return seoService.getCurrSeo(mav);
        } finally {
            wareUtil = null;
            mav = null;
        }
    }

    /**
     * 第三方查询购物车接口
     *
     * @return ModelAndView
     */
    @RequestMapping("thirdShoppingmcart")
    public ModelAndView thirdShoppingmcart(HttpServletRequest request, PageBean pageBean, HttpServletResponse response) throws UnsupportedEncodingException {
        ShoppingCartWareUtil wareUtil = null;
        ModelAndView mav = null;
        request.getSession().setAttribute("tok", UUID.randomUUID().toString());
        String[] goodsInfoIdArr = request.getParameterValues("goodsInfoId");
        String[] goodsNumberArr = request.getParameterValues("goodsNumber");
        String customerUsername = request.getParameter("customerUsername");
        Long custId = null;
        try {
            if (customerUsername == null) {
                mav = new ModelAndView("redirect:/login.html?url=/" + ShoppingCartConstants.SHOPPINGMCARTLIST + ".html");
                return seoService.getCurrSeo(mav);
            }else{
                custId = Long.parseLong(customerUsername);
            }

            for(int i=0; i<goodsInfoIdArr.length;i++){
                Long goodsInfoId = Long.parseLong(goodsInfoIdArr[i]);
                Long goodsNumber = Long.parseLong(goodsNumberArr[i]);
                ShoppingCart shoppingCart = new ShoppingCart();
                shoppingCart.setDistinctId(1103L);
                shoppingCart.setGoodsInfoId(goodsInfoId);
                shoppingCart.setGoodsNum(goodsNumber);
                shoppingCartService.addThirdShoppingCart(shoppingCart, custId);
            }

            //获取地址信息
            wareUtil = shoppingCartService.selectPNameByParam(request);
            mav = new ModelAndView(ShoppingCartConstants.SHOPPINGMCARTLIST).addObject("pb", shoppingCartService.selectShoppingCart(request, wareUtil, pageBean, response))
                    .addObject("pro", marketingService.selectAll()).addObject("sx", request.getSession().getAttribute("tok").toString());
            mav.addObject(ConstantUtil.PAGENAME,"我的购物车");
            return seoService.getCurrSeo(mav);
        } finally {
            wareUtil = null;
            mav = null;
        }
    }

    /**
     * 添加第三方接口的购物车的控制器
     *
     * 2016-01-28 wuyanbo add
     *
     * @param goodsNum
     *            购买货品的数量
     * @param productId
     *            货品ID
     * @param request
     *            请求对象
     * @return true 添加成功 false 添加失败
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("/addThirdShoppingCart")
    @ResponseBody
    public boolean addThirdShoppingCart(@RequestParam(defaultValue = "1")Long goodsNum, Long productId, HttpServletRequest request, Long distinctId, HttpServletResponse response, Long fitId)
            throws UnsupportedEncodingException {
        ShoppingCart shoppingCart = new ShoppingCart();
        String customerUsername = request.getParameter("customerUsername");
        Long custId = Long.parseLong(customerUsername);
        try {
            shoppingCart.setDistinctId(null == distinctId ? 1103L : distinctId);
            shoppingCart.setGoodsInfoId(productId);
            shoppingCart.setGoodsNum(goodsNum);
            return this.shoppingCartService.addThirdShoppingCart(shoppingCart, custId) >= 1 ? true : false;
        } finally {
            shoppingCart = null;
        }
    }
}
