/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.site.shoppingcart.controller;

import com.ningpai.common.util.ConstantUtil;
import com.ningpai.index.service.TopAndBottomService;
import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.marketing.service.MarketingService;
import com.ningpai.site.customer.controller.CustomerController;
import com.ningpai.site.customer.service.CustomerServiceInterface;
import com.ningpai.site.customer.vo.CustomerAllInfo;
import com.ningpai.site.customer.vo.CustomerConstantStr;
import com.ningpai.site.goods.service.GoodsProductService;
import com.ningpai.site.shoppingcart.bean.ShoppingCart;
import com.ningpai.site.shoppingcart.bean.ShoppingCartWareUtil;
import com.ningpai.site.shoppingcart.service.ShoppingCartService;
import com.ningpai.system.service.BasicSetService;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;
import com.ningpai.util.ShoppingCartConstants;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.UUID;

/**
 * 购物车控制器
 *
 * @author NINGPAI-LIH
 * @since 2014年11月4日15:54:30
 */
@Controller
public class ShoppingCartController {

    /**
     * 记录日志对象
     */
    private static final MyLogger LOGGER = new MyLogger(CustomerController.class);

    /**
     * 购物车路径
     */
    private static final String REDIRECT = "myshoppingcart.html";
    private static final String LOGIN = "login.html";

    /**
     * 站点信息service
     */
    @Resource(name = "basicSetService")
    private BasicSetService basicSetService;

    @Resource(name = "ShoppingCartService")
    private ShoppingCartService shoppingCartService;

    @Resource(name = "MarketingService")
    private MarketingService marketingService;

    @Resource(name = "TopAndBottomService")
    private TopAndBottomService topAndBottomService;

    private GoodsProductService productService;

    /**
     * spring 注解 会员service
     */
    private CustomerServiceInterface customerServiceInterface;

    /**
     * 查询购物车
     *
     * @return ModelAndView
     */
    @RequestMapping("myshoppingcart")
    public ModelAndView shoppingCart(HttpServletRequest request, PageBean pageBean, HttpServletResponse response) {
        ShoppingCartWareUtil wareUtil = null;
        ModelAndView mav = null;
        request.getSession().setAttribute("tok", UUID.randomUUID().toString());
        try {
            // 获取地址信息
            wareUtil = shoppingCartService.selectPNameByParam(request);
            Map<String, Object> shopMap = shoppingCartService.shopCartMap(request, wareUtil, response);

            mav = new ModelAndView(ShoppingCartConstants.SHOPPINGCARTLIST).addObject("cartMap", shopMap).addObject("pro", marketingService.selectAll())
                    .addObject("wareUtil", wareUtil).addObject("sx", request.getSession().getAttribute("tok").toString());
            // 站点信息
            mav.addObject("basicSet", basicSetService.findBasicSet());
            return topAndBottomService.getSimpleTopAndBottom(mav);
        } finally {
            wareUtil = null;
            mav = null;
        }
    }

    /**
     * 更改购物车选中状态
     *
     * @param shoppingId
     *            购物车id
     * @param status
     *            要修改的购物车状态
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/changeshopstatus")
    @ResponseBody
    public String changeShopStatus(Long shoppingId, String status, HttpServletRequest request, HttpServletResponse response) {

        return shoppingCartService.changeShopStatus(shoppingId, status, request, response);
    }

    /**
     * 更改购物车选中状态
     *
     * @param shoppingId
     *            购物车全部id
     * @param status
     *            要修改的购物车状态
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/changeshopstatusbyparams")
    @ResponseBody
    public String changeShopStatusByParams(Long[] shoppingId, String status, HttpServletRequest request, HttpServletResponse response) {
        return shoppingCartService.changeShopStatusByParam(shoppingId, status, request, response);
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
        // 当前登录会员id
        // Long customerId = (Long)
        // request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID);
        return shoppingCartService.delShoppingCartById(shoppingCartId, goodsInfoId, request, response);
    }

    /**
     * 修改购物数量
     *
     * @param shoppingCartId
     * @param num
     * @return int
     */
    @RequestMapping("changeshoppingcartbyid")
    @ResponseBody
    public int changeShoppingCartById(HttpServletRequest request, Long shoppingCartId, Long num) {
        // 当前登录会员id
        Long customerId = (Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID);
        if (customerId != null) {
            // 当前登录成功的会员信息
            CustomerAllInfo customerAllInfo = customerServiceInterface.selectByPrimaryKey(customerId);
            // 操作日志
            OperaLogUtil.addOperaLog(request, customerAllInfo.getCustomerUsername(), "修改购物数量", "修改购物数量-->用户名：" + customerAllInfo.getCustomerUsername());
            // 日志记录
            LOGGER.info("修改购物车ID为：" + shoppingCartId + "的购物数量！");
            return shoppingCartService.changeShoppingCartById(shoppingCartId, num);
        } else {
            return 0;
        }

    }

    /**
     * 修改优惠
     *
     * @param shoppingCartId
     * @param marketingId
     * @return int
     */
    @RequestMapping("changeshoppingcartmarket")
    @ResponseBody
    public int changeShoppingCartMarket(Long shoppingCartId, Long marketingActivityId, Long marketingId) {
        return -1;
    }

    /**
     * 修改商品优惠
     *
     * @param shoppingCartId
     * @param marketingActivityId
     * @param marketingId
     * @return
     */
    @RequestMapping("/changeshoppingcartmarts")
    public ModelAndView changeShoppingCartMarts(Long shoppingCartId, Long marketingActivityId, Long marketingId, Long goodsGroupMarketingId, HttpServletRequest request, HttpServletResponse response) {
        shoppingCartService.changeShoppingCartMarket(shoppingCartId, marketingId, marketingActivityId, goodsGroupMarketingId, request, response);
        return new ModelAndView(new RedirectView(REDIRECT));
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
    @RequestMapping("/addProductToShopCar")
    @ResponseBody
    public boolean addProductToShopCar(@RequestParam(defaultValue = "1")Long goodsNum, Long productId, HttpServletRequest request, Long distinctId, HttpServletResponse response, Long fitId)
            throws UnsupportedEncodingException {
        ShoppingCart shoppingCart = new ShoppingCart();
        try {
            if(null == distinctId){
                shoppingCart.setDistinctId(1103L);
            }else{
                shoppingCart.setDistinctId(distinctId);
            }

            shoppingCart.setGoodsInfoId(productId);
            shoppingCart.setGoodsNum(goodsNum);
            return this.shoppingCartService.addShoppingCart(shoppingCart, request, response) >= 1 ? true : false;
        } finally {
            shoppingCart = null;
        }
    }

    /**
     * 购买优惠套装
     *
     * @param productIds
     *            套装内的货品ID数组 {@link Long}
     * @param groupId
     *            组合ID {@link Long}
     * @return 插入是否成功 1 成功 -1 未登录 0 失败
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("/buyPackProduct")
    @ResponseBody
    public int buyPackPro(Long[] productIds, Long groupId, Long distinctId, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Long custId = this.takeCustIdFromRequest(request);
        ShoppingCart shop = new ShoppingCart();
        shop.setDistinctId(null == distinctId ? 1103L : distinctId);
        if (null != custId) {
            shop.setFitId(groupId);
            if (null != productIds && productIds.length > 0) {
                for (int i = 0; i < productIds.length; i++) {
                    shop.setGoodsInfoId(productIds[i]);
                    shop.setGoodsNum(1L);
                    this.shoppingCartService.addShoppingCart(shop, request, response);
                }
            }
            return 1;
        } else {
            /* 未登录返回 */
            return -1;
        }
    }

    /**
     * 购买优惠套装
     *
     * @param fitId
     *            套装ID {@link Long}
     * @return 购买标记 {@link Integer} 0:失败 1:成功
     */
    @RequestMapping("/buyPrePackage")
    @ResponseBody
    public int buyaPrePackage(Long fitId, Long distinctId, HttpServletRequest request, HttpServletResponse response) {
        ShoppingCart shoppingCart = new ShoppingCart();
        if (null != fitId && fitId > 0L) {
            shoppingCart.setGoodsInfoId(Long.parseLong("110012" + fitId));
            shoppingCart.setDistinctId(distinctId);
            shoppingCart.setGoodsNum(1L);
            shoppingCart.setFitId(fitId);
            try {
                shoppingCartService.addShoppingCart(shoppingCart, request, response);
                return 1;
            } catch (UnsupportedEncodingException e) {
                LOGGER.error("添加购物车报错：" + e);
                return 0;
            }

        }
        return 0;
    }

    /**
     * 修改地址
     *
     * @param distinctId
     *            地址id
     * @param chAddress
     *            详细地址
     * @param chProvince
     *            省
     * @param chCity
     *            市
     * @param chDistinct
     *            区
     * @param request
     * @return ModelAndView
     */
    @RequestMapping("/updateprovince")
    public ModelAndView updateProvince(Long distinctId, String chAddress, String chProvince, String chCity, String chDistinct, HttpServletRequest request) {
        // 省
        request.getSession().setAttribute("chProvince", chProvince);
        // 详细地址
        request.getSession().setAttribute("chAddress", chAddress);
        // 市
        request.getSession().setAttribute("chCity", chCity);
        // 地址
        request.getSession().setAttribute("chDistinct", chDistinct);
        // 区id
        request.getSession().setAttribute("distinctId", distinctId);
        return new ModelAndView(new RedirectView(REDIRECT));
    }

    /**
     * 删除分组下单个商品并且为添加商品
     *
     * @param shoppingCartId
     *            购物车id
     * @param goodsInfoId
     *            商品id
     * @param fitId
     *            优惠分组id
     * @return
     */
    @RequestMapping("/delshoppingcatgoodsgroup")
    public ModelAndView delShoppingCatGoodsGroup(Long shoppingCartId, Long goodsInfoId, Long fitId, HttpServletRequest request, HttpServletResponse response) {
        shoppingCartService.delGoodsGroupByS(shoppingCartId, goodsInfoId, fitId, request, response);
        return new ModelAndView(new RedirectView(REDIRECT));
    }

    /**
     * 添加购物车,并跳转到购物车
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
    @RequestMapping("/addproducttoshoppingcar")
    public ModelAndView addProductToShoppingCar(@RequestParam(defaultValue = "1")Long goodsNum, Long productId, HttpServletRequest request, Long distinctId, HttpServletResponse response, Long fitId)
            throws UnsupportedEncodingException {
        ShoppingCart shoppingCart = new ShoppingCart();
        try {
            if(null == distinctId){
                shoppingCart.setDistinctId(1103L);
            }else{
                shoppingCart.setDistinctId(distinctId);
            }
            // 商品id
            shoppingCart.setGoodsInfoId(productId);
            // 商品数量
            shoppingCart.setGoodsNum(goodsNum);
            // 添加到购物车
            this.shoppingCartService.addShoppingCart(shoppingCart, request, response);
            return new ModelAndView(REDIRECT);
        } finally {
            shoppingCart = null;
        }
    }

    /**
     * 第三方查询购物车接口
     * 2016-01-28 wuyanbo add
     *
     * @return ModelAndView
     */
    @RequestMapping("thirdShoppingmcart")
    public ModelAndView thirdShoppingmcart(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        ShoppingCartWareUtil wareUtil = null;
        ModelAndView mav = null;
        request.getSession().setAttribute("tok", UUID.randomUUID().toString());
        String[] goodsInfoIdArr = request.getParameterValues("goodsInfoId");
        String[] goodsNumberArr = request.getParameterValues("goodsNumber");
        String customerUsername = request.getParameter("customerUsername");
        Long custId = null;
        try {
            if (customerUsername == null) {
                return new ModelAndView(new RedirectView(LOGIN));
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
            Map<String, Object> shopMap = shoppingCartService.shopCartMap(request, wareUtil, response);

            mav = new ModelAndView(ShoppingCartConstants.SHOPPINGCARTLIST).addObject("cartMap", shopMap).addObject("pro", marketingService.selectAll())
                    .addObject("wareUtil", wareUtil).addObject("sx", request.getSession().getAttribute("tok").toString());
            // 站点信息
            mav.addObject("basicSet", basicSetService.findBasicSet());
            return topAndBottomService.getSimpleTopAndBottom(mav);
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
            return this.shoppingCartService.addThirdShoppingCart(shoppingCart,custId) >= 1 ? true : false;
        } finally {
            shoppingCart = null;
        }
    }

    /**
     * 从请求中取出登陆的会员iD
     *
     * @param request
     *            请求对象
     * @return 拿出的会员Id
     */
    public final Long takeCustIdFromRequest(HttpServletRequest request) {
        return (Long) request.getSession().getAttribute("customerId");
    }
    /**
     * get方法
     * */
    public GoodsProductService getProductService(Long askId) {
        return productService;
    }
    /**
     * Spring 注入
     * */
    @Resource(name = "HsiteGoodsProductService")
    public void setProductService(GoodsProductService productService) {
        this.productService = productService;
    }

    /**
     * get方法
     * */
    public CustomerServiceInterface getCustomerServiceInterface() {
        return customerServiceInterface;
    }

    /**
     * spring 注解 会员service
     * 
     * @param customerServiceInterface
     */
    @Resource(name = "customerServiceSite")
    public void setCustomerServiceInterface(CustomerServiceInterface customerServiceInterface) {
        this.customerServiceInterface = customerServiceInterface;
    }
}
