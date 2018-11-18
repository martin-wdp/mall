/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.m.shoppingcart.service.impl;

import com.ningpai.common.util.ConstantUtil;
import com.ningpai.coupon.bean.Coupon;
import com.ningpai.coupon.bean.CouponRange;
import com.ningpai.coupon.bean.ParamIds;
import com.ningpai.coupon.service.CouponService;
import com.ningpai.customer.bean.Customer;
import com.ningpai.customer.bean.CustomerAddress;
import com.ningpai.customer.bean.CustomerPoint;
import com.ningpai.freighttemplate.bean.FreightExpress;
import com.ningpai.freighttemplate.bean.FreightExpressAll;
import com.ningpai.freighttemplate.bean.FreightTemplate;
import com.ningpai.freighttemplate.dao.FreightExpressMapper;
import com.ningpai.freighttemplate.dao.FreightTemplateMapper;
import com.ningpai.goods.bean.GoodsProduct;
import com.ningpai.goods.bean.ProductWare;
import com.ningpai.goods.dao.ProductWareMapper;
import com.ningpai.goods.service.GoodsGroupService;
import com.ningpai.goods.service.ProductWareService;
import com.ningpai.goods.vo.GoodsGroupReleProductVo;
import com.ningpai.goods.vo.GoodsGroupVo;
import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.m.goods.bean.GoodsDetailBean;
import com.ningpai.m.goods.dao.GoodsProductMapper;
import com.ningpai.m.goods.service.GoodsProductService;
import com.ningpai.m.goods.vo.GoodsProductVo;
import com.ningpai.m.order.util.OrderUtil;
import com.ningpai.m.shoppingcart.bean.ShopCarUtil;
import com.ningpai.m.shoppingcart.bean.ShoppingCart;
import com.ningpai.m.shoppingcart.bean.ShoppingCartWareUtil;
import com.ningpai.m.shoppingcart.dao.ShoppingCartMapper;
import com.ningpai.m.shoppingcart.service.ShoppingCartService;
import com.ningpai.m.shoppingcart.util.ShopCartValueUtil;
import com.ningpai.marketing.bean.*;
import com.ningpai.marketing.dao.GrouponMapper;
import com.ningpai.marketing.dao.MarketingMapper;
import com.ningpai.marketing.dao.PreDiscountMarketingMapper;
import com.ningpai.marketing.dao.RushCustomerMapper;
import com.ningpai.marketing.service.MarketingService;
import com.ningpai.order.service.OrderService;
import com.ningpai.other.bean.IntegralSet;
import com.ningpai.other.util.CustomerConstantStr;
import com.ningpai.system.bean.PointSet;
import com.ningpai.system.service.DefaultAddressService;
import com.ningpai.system.service.DistrictService;
import com.ningpai.system.util.AddressUtil;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.*;

/**
 * @author ggn
 */
@Service("ShoppingCartService")
public class ShoppingCartServiceImpl implements ShoppingCartService {

    /**
     * 日志
     */
    public static final MyLogger LOGGER = new MyLogger(ShoppingCartServiceImpl.class);

    private static final String CUSTOMERID = "customerId";
    private static final String NPSTORE_MID = "_npstore_mId";
    private static final String NPSTORE_SHOPCAR = "_npstore_shopcar";
    private static final String CODE001 = "110012";
    private static final String NPSTORE_SHOPSTATUS = "_npstore_shopstatus";
    private static final String DISTINCTID = "distinctId";
    private static final String MARKETINGID = "marketingId";
    private static final String GOODSID = "goodsId";
    private static final String STOCK = "stock";
    private static final String BOSSSUMPRICE = "bossSumPrice";
    private static final String SUMPRICE = "sumPrice";

    @Resource(name = "MarketingService")
    private MarketingService marketService;

    @Resource(name = "HsiteGoodsProductService")
    private GoodsProductService goodsProductService;

    @Resource(name = "MarketingMapper")
    private MarketingMapper marketingMapper;

    @Resource(name = "FreightTemplateMapper")
    private FreightTemplateMapper freightTemplateMapper;

    @Resource(name = "CouponService")
    private CouponService couponService;

    @Resource(name = "GoodsGroupService")
    private GoodsGroupService goodsGroupService;

    @Resource(name = "HsiteGoodsProductMapper")
    private GoodsProductMapper goodsProductMapper;

    // 货品库存表
    @Resource(name = "ProductWareMapper")
    private ProductWareMapper productWareMapper;
    private String num = "";

    @Resource(name = "ProductWareService")
    private ProductWareService productWareService;

    @Resource(name = "ShoppingCartMapper")
    private ShoppingCartMapper shoppingCartMapper;

    @Resource(name = "OrderService")
    private OrderService orderser;

    @Resource(name = "DefaultAddressService")
    private DefaultAddressService addressService;

    @Resource(name = "DistrictService")
    private DistrictService districtService;

    @Resource(name = "GrouponMapper")
    private GrouponMapper grouponMapper;

    @Resource(name = "RushCustomerMapper")
    private RushCustomerMapper rushCustomerMapper;

    @Resource(name = "PreDiscountMarketingMapper")
    private PreDiscountMarketingMapper preDiscountMarketingMapper;

    @Resource(name = "FreightExpressMapper")
    private FreightExpressMapper freightExpressMapper;

    /**
     * 删除购物车商品
     *
     * @param shoppingCartId
     * @param goodsInfoId
     * @param shoppingCartId
     * @return int
     */
    @Override
    public int delShoppingCartById(Long shoppingCartId, Long goodsInfoId, HttpServletRequest request, HttpServletResponse response) {
        Long customerId = (Long) request.getSession().getAttribute(CUSTOMERID);
        Map<String, Object> map = new HashMap<String, Object>();
        if (customerId != null) {
            map.put("shoppingCartId", shoppingCartId);
            map.put(CUSTOMERID, customerId);
            return shoppingCartMapper.delShoppingCartById(map);
        } else {
            // 调用cookie删除接
            try {
                delCookShopCar(goodsInfoId, request, response);
            } catch (UnsupportedEncodingException e) {
                LOGGER.error("", e);
                Customer cust = (Customer) request.getSession().getAttribute("cust");
                OperaLogUtil.addOperaException(cust.getCustomerUsername(), e, request);
            }
            return 1;
        }
    }

    /**
     * 修改购买数量
     *
     * @param shoppingCartId
     * @param num
     * @return int
     */
    @Override
    public int changeShoppingCartById(Long shoppingCartId, Long num) {
        Long numNew = num;
        ShoppingCart sc = new ShoppingCart();
        sc.setShoppingCartId(shoppingCartId);
        if (numNew != null && numNew == 0L) {
            numNew = 1L;
        }
        sc.setGoodsNum(numNew);
        return shoppingCartMapper.changeShoppingCartById(sc);
    }

    /**
     * 修改优惠
     *
     * @param shoppingCartId      购物车id
     * @param marketingId         单品优惠分组id
     * @param marketingActivityId 活动优惠分组id
     * @param goodsGroupId        团购优惠分组id
     * @return int
     */
    @Override
    public int changeShoppingCartMarket(Long shoppingCartId, Long marketingId, Long marketingActivityId, Long goodsGroupId) {
        Long marketingIdNew = marketingId;
        Long marketingActivityIdNew = marketingActivityId;
        Long goodsGroupIdNew = goodsGroupId;//2015-12-30 wuyanbo add
        ShoppingCart sc = new ShoppingCart();

        sc.setShoppingCartId(shoppingCartId);
        // 如果单品优惠id为0，则为不选中单品优惠
        if (marketingIdNew == 0) {
            marketingIdNew = null;
        }
        sc.setMarketingId(marketingIdNew);
        // 如果活动优惠id为0，则为不选活动优惠
        if (marketingActivityIdNew == 0) {
            marketingActivityIdNew = null;
        }
        // 如果团购优惠id为0，则为不选团购优惠 2015-12-30 wuyanbo add
        if (goodsGroupId == 0) {
            goodsGroupId = null;
        }
        sc.setGoodsGroupId(goodsGroupId);
        sc.setMarketingActivityId(marketingActivityIdNew);
        return shoppingCartMapper.changeShoppingCartMarket(sc);
    }

    /**
     * 修改优惠
     *
     * @param shoppingCartId      购物车id
     * @param marketingId         单品优惠分组id
     * @param marketingActivityId 活动优惠分组id
     * @param goodsGroupId        团购优惠分组id
     * @return int
     */
    @Override
    public int changeShoppingCartMarket(Long shoppingCartId, Long marketingId, Long marketingActivityId, Long goodsGroupId, HttpServletRequest request, HttpServletResponse response) {
        Long customerId = (Long) request.getSession().getAttribute(CUSTOMERID);
        Long marketingIdNew = marketingId;
        Long marketingActivityIdNew = marketingActivityId;
        Long goodsGroupIdNew = goodsGroupId;//2015-12-30 wuyanbo add
        // 判断用户是否登录
        if (customerId != null) {
            ShoppingCart sc = new ShoppingCart();

            sc.setShoppingCartId(shoppingCartId);
            // 如果单品优惠id为0，则为不选中单品优惠
            if (marketingIdNew == 0) {
                marketingIdNew = null;
            }
            sc.setMarketingId(marketingIdNew);
            // 如果活动优惠id为0，则为不选活动优惠
            if (marketingActivityIdNew == 0) {
                marketingActivityIdNew = null;
            }
            sc.setMarketingActivityId(marketingActivityIdNew);
            // 如果团购优惠id为0，则为不选团购优惠 2015-12-30 wuyanbo add
            if (goodsGroupId == 0) {
                goodsGroupId = null;
            }
            sc.setGoodsGroupId(goodsGroupId);
            return shoppingCartMapper.changeShoppingCartMarket(sc);
        } else {
            Cookie[] cookies = request.getCookies();

            StringBuffer newMid = new StringBuffer();

            if (null != cookies) {
                for (Cookie cookie : cookies) {
                    if (cookie != null && NPSTORE_MID.equals(cookie.getName()) && cookie.getValue() != null && !"".equals(cookie.getValue())) {
                        String[] mIds = cookie.getValue().split("-");
                        // 取得所有cookie
                        for (int j = 0; j < mIds.length; j++) {
                            String[] mid = mIds[j].split("e");
                            // 判断是否该商品
                            if (mid[0] != null) {
                                if (mid[0].equals(shoppingCartId.toString())) {
                                    newMid.append(shoppingCartId);
                                    newMid.append("e");
                                    newMid.append(marketingIdNew);
                                    newMid.append("e");
                                    newMid.append(marketingActivityIdNew);
                                    newMid.append("e");
                                    newMid.append(goodsGroupIdNew);
                                    newMid.append("e");
                                    newMid.append("1");
                                    newMid.append("-");
                                } else {
                                    newMid.append(mIds[j]);
                                    newMid.append("-");
                                }
                            }
                        }
                    }
                }
                Cookie cookie = new Cookie(NPSTORE_MID, newMid.toString());
                cookie.setMaxAge(15 * 24 * 3600);
                cookie.setPath("/");
                response.addCookie(cookie);
            }
            return 0;
        }

    }

    /**
     * 查询购物车购买的商品信息
     *
     * @param request
     * @param box
     * @return List
     */
    @Override
    public List<ShoppingCart> searchByProduct(HttpServletRequest request, Long[] box) {
        List<Long> list = new ArrayList<Long>();
        if (box != null && box.length != 0) {
            for (Long bo : box) {
                list.add(bo);
            }
        }
        List<ShoppingCart> shoplist = shoppingCartMapper.shoppingCartListByIds(list);
        if (shoplist != null && !shoplist.isEmpty()) {

            for (int i = 0; i < shoplist.size(); i++) {
                // 判断是否是套装
                if (shoplist.get(i).getFitId() == null) {
                    // 如果是套装，设置商品
                    shoplist.get(i).setGoodsDetailBean(goodsProductService.queryDetailBeanByProductId(shoplist.get(i).getGoodsInfoId(), shoplist.get(i).getDistinctId()));
                } else {
                    // 进入此处标示该购物车为套装，执行套装方法
                    GoodsGroupVo goodsGroupVo = goodsGroupService.queryVoByPrimaryKey(shoplist.get(i).getFitId());
                    shoplist.get(i).setGoodsGroupVo(goodsGroupVo);
                }

                // 判断是否是套装
                if (shoplist.get(i).getFitId() == null) {
                    // 如果是套装，设置优惠
                    Marketing marketing = null;
                    boolean isT = true;
                    // 设置单品优惠
                    if (shoplist.get(i).getMarketingId() != null && shoplist.get(i).getMarketingId() > 0) {
                        marketing = marketService.marketingDetail(shoplist.get(i).getMarketingId());
                        isT = false;
                    }
                    // 设置活动优惠
                    if (shoplist.get(i).getMarketingActivityId() != null && shoplist.get(i).getMarketingActivityId() > 0) {
                        marketing = marketService.marketingDetailByActive(marketing, shoplist.get(i).getMarketingActivityId(), isT);
                    }
                    shoplist.get(i).setMarketing(marketing);
                }
            }

        }
        return shoplist;

    }

    /**
     * 添加购物车
     *
     * @param shoppingCart
     * @return int
     * @throws UnsupportedEncodingException
     */
    @Override
    @Transactional
    public int addShoppingCart(ShoppingCart shoppingCart, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Long custId = (Long) request.getSession().getAttribute(CUSTOMERID);
        if (null != custId) {
            int sum = shoppingCartMapper.selectSumByCustomerId(custId);
            if (sum >= 20) {
                return 0;
            }
            shoppingCart.setCustomerId(custId);
            shoppingCart.setDelFlag("0");
            shoppingCart.setShoppingCartTime(new Date());
            int count = shoppingCartMapper.selectCountByReady(shoppingCart);

            if (count == 0) {
                GoodsDetailBean goodsDetailBean = null;
                if (shoppingCart.getFitId() == null) {
                    goodsDetailBean = goodsProductService.queryDetailBeanByProductId(shoppingCart.getGoodsInfoId(), Long.parseLong("0"));

                    // 活动优惠id
                    /*Long activeMarketId = marketService.queryByCreatimeMarketings(shoppingCart.getGoodsInfoId(), (long) 2, goodsDetailBean.getProductVo().getGoods().getCatId(),
                            goodsDetailBean.getBrand().getBrandId());*/
                    Marketing marketing = null;
                    List<Marketing> markList = marketService.selectMarketingByGoodsInfoId(shoppingCart.getGoodsInfoId(), goodsDetailBean.getBrand().getBrandId(), goodsDetailBean
                            .getProductVo().getGoods().getCatId());
                    // 单品优惠id
                    Long marketId = 0L;
                    // 单品折扣促销只能有一个
                    for (Marketing mark : markList) {
                        if ("15".equals(mark.getCodexType())) {
                            marketId = mark.getMarketingId();
                            break;
                        }
                    }

                    //团购促销Id
                    Long goodsGroupId = 0L;
                    // 单品团购促销只能有一个
                    for (Marketing mark : markList) {
                        if ("10".equals(mark.getCodexType())) {
                            goodsGroupId = mark.getMarketingId();
                            break;
                        }
                    }

                    // 活动促销
                    Long activeMarketId = 0L;
                    for (Marketing mark : markList) {
                        //活动促销不包括折扣促销、包邮、团购
                        if (!"15".equals(mark.getCodexType()) && !"12".equals(mark.getCodexType()) && !"10".equals(mark.getCodexType())) {
                            activeMarketId = mark.getMarketingId();
                            break;
                        }
                    }

                    // 判断是否有单品优惠
                    if (marketId != 0) {
                        shoppingCart.setMarketingId(marketId);
                    }
                    // 判断是否有活动优惠
                    if (activeMarketId != 0) {
                        shoppingCart.setMarketingActivityId(activeMarketId);
                    }
                    //团购促销
                    if (goodsGroupId != 0) {
                        shoppingCart.setGoodsGroupId(goodsGroupId);
                    }

                    shoppingCart.setMarketing(marketing);
                }
                return shoppingCartMapper.addShoppingCart(shoppingCart);
            } else {

                ShoppingCart sc = shoppingCartMapper.selectShopingByParam(shoppingCart);
                // 限购
                if (limtShopping(sc, custId) == 0) {
                    return 0;
                }
                if (sc.getGoodsNum() == 99) {
                    return 0;
                }
                return shoppingCartMapper.updateShoppingCart(shoppingCart);

            }
        } else {
            num = "";
            Cookie[] cookies = request.getCookies();
            String oldCar = "";
            String mId = "";
            Cookie cook;
            if (null != cookies) {
                for (Cookie cookie : cookies) {
                    if (null != cookie && NPSTORE_SHOPCAR.equals(cookie.getName())) {
                        oldCar = URLDecoder.decode(cookie.getValue(), ConstantUtil.UTF);
                        if (oldCar.indexOf("," + shoppingCart.getGoodsInfoId() + "-") != -1) {
                            num = oldCar.substring(oldCar.indexOf("," + shoppingCart.getGoodsInfoId() + "-"), oldCar.indexOf("," + shoppingCart.getGoodsInfoId() + "-")
                                    + oldCar.substring(oldCar.indexOf("," + shoppingCart.getGoodsInfoId() + "-"), oldCar.length() - 1).indexOf("&"));
                            num = num.substring(num.indexOf("-") + 1, num.length());
                            oldCar = oldCar.replace("," + shoppingCart.getGoodsInfoId() + "-" + num + "&" + shoppingCart.getDistinctId() + "e", "");
                            if (oldCar.indexOf("," + shoppingCart.getGoodsInfoId() + "-" + num + "&" + shoppingCart.getDistinctId()) != -1) {
                                oldCar = oldCar.replace("," + shoppingCart.getGoodsInfoId() + "-" + num + "&" + shoppingCart.getDistinctId(), "");

                            }
                        }
                    }
                    if (cookie != null && NPSTORE_MID.equals(cookie.getName()) && cookie.getValue() != null && !"".equals(cookie.getValue())) {
                        String[] mIds = cookie.getValue().split("-");
                        // 取得所有cookie
                        for (int j = 0; j < mIds.length; j++) {
                            String[] mid = mIds[j].split("e");
                            // 判断是否该商品
                            if (mid[0] != null && "".equals(mIds[0]) && !mid[0].equals(shoppingCart.getGoodsInfoId().toString())) {
                                mId = cookie.getValue();
                            }

                        }
                    }

                }
            }
            if (!"".equals(num)) {
                num = String.valueOf(Long.parseLong(num) + shoppingCart.getGoodsNum());
            } else {
                num = String.valueOf(shoppingCart.getGoodsNum());
            }
            oldCar += "," + shoppingCart.getGoodsInfoId() + "-" + num + "&" + shoppingCart.getDistinctId() + "e";
            GoodsDetailBean goodsDetailBean = null;
            if (shoppingCart.getFitId() == null) {
                goodsDetailBean = goodsProductService.queryDetailBeanByProductId(shoppingCart.getGoodsInfoId(), Long.parseLong("0"));

            }
            // 单品优惠id
            Long marketId = marketService.queryByCreatimeMarketings(shoppingCart.getGoodsInfoId(), (long) 1, goodsDetailBean.getProductVo().getGoods().getCatId(), goodsDetailBean
                    .getBrand().getBrandId());
            // 活动优惠id
            Long activeMarketId = marketService.queryByCreatimeMarketings(shoppingCart.getGoodsInfoId(), (long) 2, goodsDetailBean.getProductVo().getGoods().getCatId(),
                    goodsDetailBean.getBrand().getBrandId());
            //2015-12-30 wuyanbo add 抢购优惠
            if (activeMarketId == null || activeMarketId == 0) {//抢购优惠
                activeMarketId = marketService.queryByCreatimeMarketings(shoppingCart.getGoodsInfoId(), (long) 5, goodsDetailBean.getProductVo().getGoods().getCatId(),
                        goodsDetailBean.getBrand().getBrandId());
            }
            //2015-12-30 wuyanbo add 团购优惠
            Long goodsGroupId = marketService.queryByCreatimeMarketings(shoppingCart.getGoodsInfoId(), 4L, goodsDetailBean.getProductVo().getGoods().getCatId(),
                    goodsDetailBean.getBrand().getBrandId());

            mId += "-" + shoppingCart.getGoodsInfoId() + "e" + marketId + "e" + activeMarketId + "e" + goodsGroupId + "e" + "0";
            cook = new Cookie(NPSTORE_SHOPCAR, URLEncoder.encode(oldCar, ConstantUtil.UTF));
            cook.setMaxAge(15 * 24 * 3600);
            cook.setPath("/");
            response.addCookie(cook);
            Cookie cookie = new Cookie(NPSTORE_MID, mId);
            cookie.setMaxAge(15 * 24 * 3600);
            cookie.setPath("/");
            response.addCookie(cookie);
            return 1;
        }
    }

    /**
     * 添加购物车
     *
     * @param shoppingCart
     * @return int
     * @throws UnsupportedEncodingException
     */
    @Override
    @Transactional
    public Map<String, Object> addShoppingCartAJAX(ShoppingCart shoppingCart, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Long custId = (Long) request.getSession().getAttribute(CUSTOMERID);
        Map<String, Object> result = new HashMap<String, Object>();
        if (null != custId) {
//            int sum = shoppingCartMapper.selectSumByCustomerId(custId);
//            if (sum >= 20) {
//                result.put("addFlag", 0);
//                result.put("sumNum", 0);
//                return result;
//            }
            shoppingCart.setCustomerId(custId);
            shoppingCart.setDelFlag("0");
            shoppingCart.setShoppingCartTime(new Date());
           // int count = shoppingCartMapper.selectCountByReady(shoppingCart);
            int count = shoppingCartMapper.selectCountByReady(shoppingCart);

            if (count == 0) {
                GoodsDetailBean goodsDetailBean = null;
                if (shoppingCart.getFitId() == null) {
                    goodsDetailBean = goodsProductService.queryDetailBeanByProductId(shoppingCart.getGoodsInfoId(), Long.parseLong("0"));

                    // 活动优惠id
                    /*Long activeMarketId = marketService.queryByCreatimeMarketings(shoppingCart.getGoodsInfoId(), (long) 2, goodsDetailBean.getProductVo().getGoods().getCatId(),
                            goodsDetailBean.getBrand().getBrandId());*/
                    Marketing marketing = null;
                    List<Marketing> markList = marketService.selectMarketingByGoodsInfoId(shoppingCart.getGoodsInfoId(), goodsDetailBean.getBrand().getBrandId(), goodsDetailBean
                            .getProductVo().getGoods().getCatId());
                    // 单品优惠id
                    Long marketId = 0L;
                    // 单品折扣促销只能有一个
                    for (Marketing mark : markList) {
                        if ("15".equals(mark.getCodexType())) {
                            marketId = mark.getMarketingId();
                            break;
                        }
                    }

                    //团购促销Id
                    Long goodsGroupId = 0L;
                    // 单品团购促销只能有一个
                    for (Marketing mark : markList) {
                        if ("10".equals(mark.getCodexType())) {
                            goodsGroupId = mark.getMarketingId();
                            break;
                        }
                    }

                    // 活动促销
                    Long activeMarketId = 0L;
                    for (Marketing mark : markList) {
                        //活动促销不包括折扣促销、包邮、团购
                        if (!"15".equals(mark.getCodexType()) && !"12".equals(mark.getCodexType()) && !"10".equals(mark.getCodexType())) {
                            activeMarketId = mark.getMarketingId();
                            break;
                        }
                    }

                    // 判断是否有单品优惠
                    if (marketId != 0) {
                        shoppingCart.setMarketingId(marketId);
                    }
                    // 判断是否有活动优惠
                    if (activeMarketId != 0) {
                        shoppingCart.setMarketingActivityId(activeMarketId);
                    }
                    //团购促销
                    if (goodsGroupId != 0) {
                        shoppingCart.setGoodsGroupId(goodsGroupId);
                    }

                    shoppingCart.setMarketing(marketing);
                }
                result.put("addFlag", shoppingCartMapper.addShoppingCart(shoppingCart));
                result.put("sumNum", getShoppingCartGoodsSum(request));
                return result;
            } else {

                ShoppingCart sc = shoppingCartMapper.selectShopingByParam(shoppingCart);
                // 限购
                if (limtShopping(sc, custId) == 0) {
                    result.put("addFlag", 0);
                    result.put("sumNum", 0);
                    return result;
                }
                if (sc.getGoodsNum() == 99) {
                    result.put("addFlag", 0);
                    result.put("sumNum", 0);
                    return result;
                }
                result.put("addFlag", shoppingCartMapper.updateShoppingCart(shoppingCart));
                //result.put("sumNum", count + shoppingCart.getGoodsNum());
                result.put("sumNum", getShoppingCartGoodsSum(request));
                return result;
            }
        } else {
            num = "";
            Cookie[] cookies = request.getCookies();
            String oldCar = "";
            String mId = "";
            Cookie cook;
            if (null != cookies) {
                for (Cookie cookie : cookies) {
                    if (null != cookie && NPSTORE_SHOPCAR.equals(cookie.getName())) {
                        oldCar = URLDecoder.decode(cookie.getValue(), ConstantUtil.UTF);
                        if (oldCar.indexOf("," + shoppingCart.getGoodsInfoId() + "-") != -1) {
                            num = oldCar.substring(oldCar.indexOf("," + shoppingCart.getGoodsInfoId() + "-"), oldCar.indexOf("," + shoppingCart.getGoodsInfoId() + "-")
                                    + oldCar.substring(oldCar.indexOf("," + shoppingCart.getGoodsInfoId() + "-"), oldCar.length() - 1).indexOf("&"));
                            num = num.substring(num.indexOf("-") + 1, num.length());
                            oldCar = oldCar.replace("," + shoppingCart.getGoodsInfoId() + "-" + num + "&" + shoppingCart.getDistinctId() + "e", "");
                            if (oldCar.indexOf("," + shoppingCart.getGoodsInfoId() + "-" + num + "&" + shoppingCart.getDistinctId()) != -1) {
                                oldCar = oldCar.replace("," + shoppingCart.getGoodsInfoId() + "-" + num + "&" + shoppingCart.getDistinctId(), "");

                            }
                        }
                    }
                    if (cookie != null && NPSTORE_MID.equals(cookie.getName()) && cookie.getValue() != null && !"".equals(cookie.getValue())) {
                        String[] mIds = cookie.getValue().split("-");
                        // 取得所有cookie
                        for (int j = 0; j < mIds.length; j++) {
                            String[] mid = mIds[j].split("e");
                            // 判断是否该商品
                            if (mid[0] != null && "".equals(mIds[0]) && !mid[0].equals(shoppingCart.getGoodsInfoId().toString())) {
                                mId = cookie.getValue();
                            }

                        }
                    }

                }
            }
            if (!"".equals(num)) {
                num = String.valueOf(Long.parseLong(num) + shoppingCart.getGoodsNum());
            } else {
                num = String.valueOf(shoppingCart.getGoodsNum());
            }
            oldCar += "," + shoppingCart.getGoodsInfoId() + "-" + num + "&" + shoppingCart.getDistinctId() + "e";
            GoodsDetailBean goodsDetailBean = null;
            if (shoppingCart.getFitId() == null) {
                goodsDetailBean = goodsProductService.queryDetailBeanByProductId(shoppingCart.getGoodsInfoId(), Long.parseLong("0"));

            }
            // 单品优惠id
            Long marketId = marketService.queryByCreatimeMarketings(shoppingCart.getGoodsInfoId(), (long) 1, goodsDetailBean.getProductVo().getGoods().getCatId(), goodsDetailBean
                    .getBrand().getBrandId());
            // 活动优惠id
            Long activeMarketId = marketService.queryByCreatimeMarketings(shoppingCart.getGoodsInfoId(), (long) 2, goodsDetailBean.getProductVo().getGoods().getCatId(),
                    goodsDetailBean.getBrand().getBrandId());
            //2015-12-30 wuyanbo add 抢购优惠
            if (activeMarketId == null || activeMarketId == 0) {//抢购优惠
                activeMarketId = marketService.queryByCreatimeMarketings(shoppingCart.getGoodsInfoId(), (long) 5, goodsDetailBean.getProductVo().getGoods().getCatId(),
                        goodsDetailBean.getBrand().getBrandId());
            }
            //2015-12-30 wuyanbo add 团购优惠
            Long goodsGroupId = marketService.queryByCreatimeMarketings(shoppingCart.getGoodsInfoId(), 4L, goodsDetailBean.getProductVo().getGoods().getCatId(),
                    goodsDetailBean.getBrand().getBrandId());

            mId += "-" + shoppingCart.getGoodsInfoId() + "e" + marketId + "e" + activeMarketId + "e" + goodsGroupId + "e" + "0";
            cook = new Cookie(NPSTORE_SHOPCAR, URLEncoder.encode(oldCar, ConstantUtil.UTF));
            cook.setMaxAge(15 * 24 * 3600);
            cook.setPath("/");
            response.addCookie(cook);
            Cookie cookie = new Cookie(NPSTORE_MID, mId);
            cookie.setMaxAge(15 * 24 * 3600);
            cookie.setPath("/");
            response.addCookie(cookie);
            result.put("addFlag", 1);
            result.put("sumNum", shoppingCart.getGoodsNum()+getShoppingCartGoodsSum(request));
            return result;
        }
    }

    /**
     * 立即购买--将商品添加到购物车表中
     *@param shoppingCart
     * @return
     * @throws UnsupportedEncodingException
     */
    public Long immediatelyBuyAddCart(ShoppingCart shoppingCart){
        int count = shoppingCartMapper.selectCountByReady(shoppingCart);

        if (count == 0) {
            GoodsDetailBean goodsDetailBean = null;
            if (shoppingCart.getFitId() == null) {
                goodsDetailBean = goodsProductService.queryDetailBeanByProductId(shoppingCart.getGoodsInfoId(), Long.parseLong("0"));

                // 活动优惠id
                    /*Long activeMarketId = marketService.queryByCreatimeMarketings(shoppingCart.getGoodsInfoId(), (long) 2, goodsDetailBean.getProductVo().getGoods().getCatId(),
                            goodsDetailBean.getBrand().getBrandId());*/
                Marketing marketing = null;
                List<Marketing> markList = marketService.selectMarketingByGoodsInfoId(shoppingCart.getGoodsInfoId(), goodsDetailBean.getBrand().getBrandId(), goodsDetailBean
                        .getProductVo().getGoods().getCatId());
                // 单品优惠id
                Long marketId = 0L;
                // 单品折扣促销只能有一个
                for (Marketing mark : markList) {
                    if ("15".equals(mark.getCodexType())) {
                        marketId = mark.getMarketingId();
                        break;
                    }
                }

                //团购促销Id
                Long goodsGroupId = 0L;
                // 单品团购促销只能有一个
                for (Marketing mark : markList) {
                    if ("10".equals(mark.getCodexType())) {
                        goodsGroupId = mark.getMarketingId();
                        break;
                    }
                }

                // 活动促销
                Long activeMarketId = 0L;
                for (Marketing mark : markList) {
                    //活动促销不包括折扣促销、包邮、团购
                    if (!"15".equals(mark.getCodexType()) && !"12".equals(mark.getCodexType()) && !"10".equals(mark.getCodexType())) {
                        activeMarketId = mark.getMarketingId();
                        break;
                    }
                }

                // 判断是否有单品优惠
                if (marketId != 0) {
                    shoppingCart.setMarketingId(marketId);
                }
                // 判断是否有活动优惠
                if (activeMarketId != 0) {
                    shoppingCart.setMarketingActivityId(activeMarketId);
                }
                //团购促销
                if (goodsGroupId != 0) {
                    shoppingCart.setGoodsGroupId(goodsGroupId);
                }

                shoppingCart.setMarketing(marketing);
            }
            shoppingCartMapper.addShoppingCart(shoppingCart);

        } else {

            //ShoppingCart sc = shoppingCartMapper.selectShopingByParam(shoppingCart);
            shoppingCartMapper.updateShoppingCartNum(shoppingCart);

        }
        return shoppingCartMapper.selectLastId(shoppingCart);
    }


    /**
     * 分页购物
     */
    public int limtShopping(ShoppingCart shoppingCart, Long custId) {
        // 进入此处标示该购物车为商品，执行商品方法
        GoodsDetailBean gb = goodsProductService.queryDetailBeanByProductId(shoppingCart.getGoodsInfoId(), shoppingCart.getDistinctId());
        // 限购
        Marketing mk = marketService.marketingDetail(marketService.queryByCreatimeMarketings(gb.getProductVo().getGoodsInfoId(), 3L, gb.getProductVo().getGoods().getCatId(), gb
                .getBrand().getBrandId()));
        // 存在限购
        if (mk != null) {
            Long stock = mk.getLimitBuyMarketing().getLimitCount();
            // 最近是否购买过该商品
            Long num = orderser.selectGoodsInfoCount(shoppingCart.getGoodsInfoId(), custId, mk.getMarketingBegin());
            if (num != null && stock - num - shoppingCart.getGoodsNum() <= 0) {
                return 0;
            }
        }
        return 1;
    }

    /**
     * 加载cookie中的购物车信息
     *
     * @return 加载好的列表
     * @throws UnsupportedEncodingException
     */
    public List<ShopCarUtil> loadCookShopCar(HttpServletRequest request) throws UnsupportedEncodingException {
        List<ShopCarUtil> list = new ArrayList<ShopCarUtil>();
        Cookie[] cookies = request.getCookies();
        String oldCar = "";
        String[] cars = null;
        String[] car = null;
        String[] car2 = null;
        ShopCarUtil carUtil = null;
        boolean checkExists = false;
        try {
            if (null != cookies) {
                for (Cookie cookie : cookies) {

                    if (null != cookie && NPSTORE_SHOPCAR.equals(cookie.getName()) && cookie.getValue() != null && !"".equals(cookie.getValue())) {
                        oldCar = URLDecoder.decode(cookie.getValue(), ConstantUtil.UTF);
                        oldCar = oldCar.substring(1, oldCar.length());
                        oldCar = oldCar.substring(0, oldCar.length() - 1);
                        cars = oldCar.split("e,");
                        if (null != cars && cars.length > 0) {
                            for (int i = 0; i < cars.length; i++) {
                                car = cars[i].split("-");
                                carUtil = new ShopCarUtil();

                                    /* 如果符合套装的规则,说明是套装,否则就是货品 */
                                if (car[0].length() > 6 && CODE001.equals(car[0].substring(0, 6))) {
                                    carUtil.setFitId(Long.parseLong(car[0].substring(6, car[0].length())));
                                    carUtil.setProductId(Long.parseLong(car[0]));
                                } else {
                                    carUtil.setProductId(Long.parseLong(car[0]));
                                    for (Cookie cook : cookies) {
                                        // 设置优惠
                                        if (cook != null && NPSTORE_MID.equals(cook.getName()) && cook.getValue() != null && !"".equals(cook.getValue())) {
                                            String[] mIds = cook.getValue().split("-");
                                            // 取得所有cookie
                                            for (int j = 0; j < mIds.length; j++) {
                                                String[] mid = mIds[j].split("e");
                                                // 判断是否该商品 2015-12-30 wuyanbo modified
                                                if (mid[0] != null && mid.length == 5 && car[0].equals(mid[0])) {
                                                    carUtil.setMarketId(Long.parseLong(mid[1]));
                                                    carUtil.setMarketActiveId(Long.parseLong(mid[2]));
                                                    carUtil.setGoodsGroupId(Long.parseLong(mid[3]));//2015-12-30 wuyanbo add 团购优惠
                                                    carUtil.setStatus(Long.parseLong(mid[4]));
                                                }
                                            }
                                        }
                                    }
                                }
                                car2 = car[1].split("&");
                                carUtil.setGoodsNum(Integer.parseInt(car2[0]));
                                carUtil.setDistinctId(Long.parseLong(car2[1]));
                                for (int j = 0; j < list.size(); j++) {
                                    if (list.get(j).getProductId().equals(carUtil.getProductId())) {
                                        checkExists = true;
                                    }
                                }
                                if (!checkExists) {
                                    list.add(carUtil);
                                    checkExists = false;
                                }
                            }
                        }
                    }

                }
            }
            return list;
        } finally {
            list = null;
            cookies = null;
            oldCar = null;
            cars = null;
            car = null;
        }
    }

    /**
     * 删除cookie中的购物车数据
     *
     * @param request  请求对象
     * @param response 相应对象
     * @return 删除的数量
     * @throws UnsupportedEncodingException
     */
    public int delCookShopCar(Long productId, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Integer count = 0;
        Cookie[] cookies = request.getCookies();
        String oldCar = "";
        String[] cars = null;
        String[] car = null;
        Cookie cook;
        String newMid = "";
        StringBuffer bufOldCar = new StringBuffer();
        StringBuffer bufNewMid = new StringBuffer();

        try {
            if (null != cookies) {
                for (Cookie cookie : cookies) {
                    if (null != cookie && NPSTORE_SHOPCAR.equals(cookie.getName())) {
                        oldCar = URLDecoder.decode(cookie.getValue(), ConstantUtil.UTF);
                        if (oldCar.indexOf("," + productId + "-") != -1) {
                            oldCar = oldCar.substring(1, oldCar.length());
                            oldCar = oldCar.substring(0, oldCar.length() - 1);
                            cars = oldCar.split("e,");
                            oldCar = "";
                            for (int j = 0; j < cars.length; j++) {
                                car = cars[j].split("-");
                                if (!car[0].equals(productId.toString())) {
                                    bufOldCar.append(oldCar);
                                    bufOldCar.append(",");
                                    bufOldCar.append(car[0]);
                                    bufOldCar.append("-");
                                    bufOldCar.append(car[1]);
                                    bufOldCar.append("e");
                                    oldCar += bufOldCar.toString();
                                }
                            }
                        }
                    }
                    if (cookie != null && NPSTORE_MID.equals(cookie.getName()) && cookie.getValue() != null && !"".equals(cookie.getValue())) {
                        String[] mIds = cookie.getValue().split("-");
                        // 取得所有cookie
                        for (int j = 0; j < mIds.length; j++) {
                            String[] mid = mIds[j].split("e");
                            // 判断是否该商品
                            if (mid[0] != null && !mid[0].equals(productId.toString())) {
                                bufNewMid.append(mIds[j]);
                                bufNewMid.append("-");
                                newMid += bufNewMid.toString();
                            }

                        }
                    }
                }
            }
            cook = new Cookie(NPSTORE_SHOPCAR, URLEncoder.encode(oldCar, ConstantUtil.UTF));
            cook.setMaxAge(15 * 24 * 3600);
            cook.setPath("/");
            response.addCookie(cook);
            Cookie cookie = new Cookie(NPSTORE_MID, URLEncoder.encode(newMid, ConstantUtil.UTF));
            cookie.setMaxAge(15 * 24 * 3600);
            cookie.setPath("/");
            response.addCookie(cookie);
            return count;
        } finally {
            cook = null;
            cars = null;
            car = null;
            cookies = null;
            oldCar = null;
        }
    }

    /*
     * 
     * @see com.ningpai.site.shoppingcart.service.ShoppingCartService#
     * deleteShoppingCartByIds(javax.servlet.http .HttpServletRequest,
     * java.lang.Long[])
     */
    @Override
    public int deleteShoppingCartByIds(HttpServletRequest request, Long[] shoppingCartId) {
        List<Long> list = new ArrayList<Long>();
        if (shoppingCartId.length != 0) {
            for (Long bo : shoppingCartId) {
                list.add(bo);
            }
        }
        return shoppingCartMapper.deleteShoppingCartByIds(list);
    }

    /**
     * 从cook添加到购物车
     *
     * @param request
     * @return int
     */
    @Override
    public int loadCoodeShopping(HttpServletRequest request) {
        Long custId = (Long) request.getSession().getAttribute(CUSTOMERID);
        List<ShopCarUtil> list = null;
        try {
            list = loadCookShopCar(request);
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("", e);
            Customer cust = (Customer) request.getSession().getAttribute("cust");
            OperaLogUtil.addOperaException(cust.getCustomerUsername(), e, request);
        }
        if (list != null && !list.isEmpty()) {
            for (ShopCarUtil su : list) {
                ShoppingCart shoppingCart = new ShoppingCart();
                if (su.getFitId() == null) {
                    shoppingCart.setGoodsInfoId(su.getProductId());
                } else {
                    // 设置套装商品id
                    shoppingCart.setGoodsInfoId(Long.parseLong(CODE001 + su.getFitId()));
                    // 设置套装id
                    shoppingCart.setFitId(su.getFitId());
                }
                shoppingCart.setCustomerId(custId);
                shoppingCart.setDelFlag("0");
                shoppingCart.setShoppingCartTime(new Date());
                shoppingCart.setGoodsNum(Long.valueOf(su.getGoodsNum()));
                shoppingCart.setMarketingId(su.getMarketId());
                shoppingCart.setMarketingActivityId(su.getMarketActiveId());
                shoppingCart.setGoodsGroupId(su.getGoodsGroupId());// 2015-12-30 wuyanbo add团购活动
                shoppingCart.setGoodsNum(Long.valueOf(su.getGoodsNum()));
                int count = shoppingCartMapper.selectCountByReady(shoppingCart);
                if (count == 0) {
                    shoppingCartMapper.addShoppingCart(shoppingCart);
                }/*else {
                    shoppingCart.setGoodsNum(count+Long.valueOf(su.getGoodsNum()));
                    shoppingCartMapper.updateShoppingCart(shoppingCart);
                }*/
            }

        }

        return 1;
    }

    /**
     * 修改要选中的订单优惠
     *
     * @param cart
     * @return 结果
     */
    @Override
    public int changeShoppingCartOrderMarket(ShoppingCart cart) {
        shoppingCartMapper.changeShoppingCartOrderMarket(cart);
        return 0;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    @Override
    /**
     * 查询购物车内容
     * @return
     */
    public PageBean selectShoppingCart(HttpServletRequest request, ShoppingCartWareUtil cartWareUtil, PageBean pb, HttpServletResponse response) {
        Long customerId = (Long) request.getSession().getAttribute(CUSTOMERID);
        // 如果已经登录
        if (customerId != null) {
            pb.setUrl("myshoppingcart");
            pb.setPageSize(20);
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put(CUSTOMERID, customerId);
            Integer totalCount = shoppingCartMapper.shoppingCartCount(paramMap);
            pb.setRows(Integer.parseInt(totalCount == null ? 0 + "" : totalCount + ""));
            if (pb.getPageNo() > pb.getLastPageNo()) {
                pb.setPageNo(pb.getLastPageNo());
            }
            if (pb.getPageNo() == 0) {
                pb.setPageNo(1);
            }
            paramMap.put(CustomerConstantStr.STARTNUM, pb.getStartRowNum());
            paramMap.put(CustomerConstantStr.ENDNUM, pb.getEndRowNum());
            List<Object> shoplist = shoppingCartMapper.shoppingCart(paramMap);
            if (shoplist != null && !shoplist.isEmpty()) {
                for (int i = 0; i < shoplist.size(); i++) {
                    try {
                        try {
                            // 进入此处标示该购物车为商品，执行商品方法
                            GoodsDetailBean goodsDetailBean = goodsProductService.queryDetailBeanByProductId(((ShoppingCart) shoplist.get(i)).getGoodsInfoId(),
                                    cartWareUtil.getDistrictId());
                            if (goodsDetailBean == null) {
                                continue;
                            }
                            ((ShoppingCart) shoplist.get(i)).setGoodsDetailBean(goodsDetailBean);
                            // 限购
                            Marketing marketing = marketService.marketingDetail(marketService.queryByCreatimeMarketings(goodsDetailBean.getProductVo().getGoodsInfoId(), 3L,
                                    goodsDetailBean.getProductVo().getGoods().getCatId(), goodsDetailBean.getBrand().getBrandId()));
                            // 存在限购
                            if (marketing != null) {
                                Long stock = marketing.getLimitBuyMarketing().getLimitCount();
                                // 最近是否购买过该商品
                                Long num = orderser.selectGoodsInfoCount(((ShoppingCart) shoplist.get(i)).getGoodsDetailBean().getProductVo().getGoodsInfoId(), customerId,
                                        marketing.getMarketingBegin());
                                if (num != null) {
                                    stock = stock - num;
                                    if (stock < 0) {
                                        stock = 0L;
                                    }
                                }
                                if (((ShoppingCart) shoplist.get(i)).getGoodsDetailBean().getProductVo().getGoodsInfoStock() - stock >= 0) {
                                    // 如果存在限购，则把库存改为限购数量
                                    ((ShoppingCart) shoplist.get(i)).getGoodsDetailBean().getProductVo().setGoodsInfoStock(stock);
                                }
                            }
                        } catch (Exception e) {
                            LOGGER.error("", e);
                            Map<String, Object> map = new HashMap<String, Object>();
                            // 删除商品根据id和customerId，防止越权
                            map.put("shoppingCartId", ((ShoppingCart) shoplist.get(i)).getShoppingCartId());
                            map.put(CUSTOMERID, customerId);
                            shoppingCartMapper.delShoppingCartById(map);
                        }
                    } catch (Exception e) {
                        LOGGER.error("", e);
                        Map<String, Object> map = new HashMap<String, Object>();
                        // 删除商品根据id和customerId，防止越权
                        map.put("shoppingCartId", ((ShoppingCart) shoplist.get(i)).getShoppingCartId());
                        map.put(CUSTOMERID, customerId);
                        // 如果商品已经从数据库中删除，则把该商品从购物车内删除
                        shoppingCartMapper.delShoppingCartById(map);
                    }

                    // 查询促销
                    if (((ShoppingCart) shoplist.get(i)).getFitId() == null) {
                        ((ShoppingCart) shoplist.get(i)).setMarketingList(marketService.selectMarketingByGoodsInfoId(((ShoppingCart) shoplist.get(i)).getGoodsDetailBean()
                                .getProductVo().getGoodsInfoId(), ((ShoppingCart) shoplist.get(i)).getGoodsDetailBean().getBrand().getBrandId(), ((ShoppingCart) shoplist.get(i))
                                .getGoodsDetailBean().getProductVo().getGoods().getCatId()));
                    }

                    if (((ShoppingCart) shoplist.get(i)).getFitId() == null) {
                        Marketing marketing = null;
                        boolean isT = true;
                        ShoppingCart sc = (ShoppingCart) shoplist.get(i);
                        // 设置单品优惠
                        if (sc.getMarketingId() != null && sc.getMarketingId() != 0) {
                            marketing = marketService.marketingDetail(sc.getMarketingId());
                            isT = false;
                        }
                        // 设置活动优惠
                        if (sc.getMarketingActivityId() != null && sc.getMarketingActivityId() != 0) {
                            marketing = marketService.marketingDetail(sc.getMarketingActivityId());
                        }
                        // 设置团购优惠 2015-12-30 wuyanbo add
                        if (sc.getGoodsGroupId() != null && sc.getGoodsGroupId() != 0) {
                            marketing = marketService.marketingDetail(sc.getGoodsGroupId());
                        }
                        ((ShoppingCart) shoplist.get(i)).setMarketing(marketing);
                    }

                }
            }
            pb.setList(shoplist);

        } else {
            // 如果未登录状态获取购物车
            try {
                List<ShopCarUtil> list = loadCookShopCar(request);
                if (list != null && !list.isEmpty()) {
                    pb.setUrl("myshoppingcart");
                    pb.setPageSize(5);

                    Integer totalCount = 1;// list.size();

                    pb.setRows(Integer.parseInt(totalCount == null ? 0 + "" : totalCount + ""));
                    if (pb.getPageNo() > pb.getLastPageNo()) {
                        pb.setPageNo(pb.getLastPageNo());
                    }
                    if (pb.getPageNo() == 0) {
                        pb.setPageNo(1);
                    }

                    List<Object> shoplist = new ArrayList<Object>();
                    for (int i = 0; i < list.size(); i++) {// list.size();
                        ShoppingCart sc = new ShoppingCart();
                        sc.setGoodsInfoId(list.get(i).getProductId());
                        sc.setMarketingId(list.get(i).getMarketId());
                        sc.setMarketingActivityId(list.get(i).getMarketActiveId());
                        sc.setGoodsGroupId(list.get(i).getGoodsGroupId());//2015-12-30 wuyanbo add团购活动
                        sc.setGoodsNum(Long.valueOf(list.get(i).getGoodsNum()));
                        sc.setMarketing(null);
                        sc.setDistinctId(list.get(i).getDistinctId());
                        sc.setShoppingCartId(Long.valueOf(i));
                        shoplist.add(sc);
                    }

                    if (shoplist != null && !shoplist.isEmpty()) {
                        for (int i = 0; i < shoplist.size(); i++) {
                            Object sessionStatus = request.getSession().getAttribute(NPSTORE_SHOPSTATUS);
                            boolean bool = true;
                            if (sessionStatus != null) {

                                String[] status = (sessionStatus.toString()).split("-");
                                for (String str : status) {
                                    String[] shoppingStatus = str.split("e");
                                    if (shoppingStatus[1].equals(((ShoppingCart) shoplist.get(i)).getShoppingCartId().toString())) {
                                        ((ShoppingCart) shoplist.get(i)).setShoppingStatus(shoppingStatus[0]);
                                        bool = false;
                                    }
                                }
                            } else {
                                sessionStatus = "1" + "e" + ((ShoppingCart) shoplist.get(i)).getShoppingCartId() + "-";
                                ((ShoppingCart) shoplist.get(i)).setShoppingStatus("1");
                            }

                            if (bool) {
                                sessionStatus = "1" + "e" + ((ShoppingCart) shoplist.get(i)).getShoppingCartId() + "-" + sessionStatus;
                                ((ShoppingCart) shoplist.get(i)).setShoppingStatus("1");
                            }
                            request.getSession().setAttribute(NPSTORE_SHOPSTATUS, sessionStatus);
                            GoodsDetailBean goodsDetailBean = goodsProductService.queryDetailBeanByProductId(((ShoppingCart) shoplist.get(i)).getGoodsInfoId(),
                                    cartWareUtil.getDistrictId());
                            if (goodsDetailBean == null) {
                                continue;
                            }
                            ((ShoppingCart) shoplist.get(i)).setGoodsDetailBean(goodsDetailBean);
                            // 限购
                            Marketing marketing = marketService.marketingDetail(marketService.queryByCreatimeMarketings(goodsDetailBean.getProductVo().getGoodsInfoId(), 3L,
                                    goodsDetailBean.getProductVo().getGoods().getCatId(), goodsDetailBean.getBrand().getBrandId()));
                            // 存在限购
                            if (marketing != null) {
                                Long stock = marketing.getLimitBuyMarketing().getLimitCount();
                                // 最近是否购买过该商品
                                Long num = orderser.selectGoodsInfoCount(((ShoppingCart) shoplist.get(i)).getGoodsDetailBean().getProductVo().getGoodsInfoId(), null,
                                        marketing.getMarketingBegin());
                                if (num != null) {
                                    stock = stock - num;
                                    if (stock < 0) {
                                        stock = 0L;
                                    }
                                }
                                if (((ShoppingCart) shoplist.get(i)).getGoodsDetailBean().getProductVo().getGoodsInfoStock() - stock >= 0) {
                                    // 如果存在限购，则把库存改为限购数量
                                    ((ShoppingCart) shoplist.get(i)).getGoodsDetailBean().getProductVo().setGoodsInfoStock(stock);
                                    ((ShoppingCart) shoplist.get(i)).setMarketingList(marketService.selectMarketingByGoodsInfoId(((ShoppingCart) shoplist.get(i))
                                                    .getGoodsDetailBean().getProductVo().getGoodsInfoId(), ((ShoppingCart) shoplist.get(i)).getGoodsDetailBean().getBrand().getBrandId(),
                                            ((ShoppingCart) shoplist.get(i)).getGoodsDetailBean().getProductVo().getGoods().getCatId()));
                                }
                            }

                            if (((ShoppingCart) shoplist.get(i)).getFitId() == null) {
                                Marketing market = null;
                                boolean isT = true;
                                ShoppingCart sc = (ShoppingCart) shoplist.get(i);
                                // 设置单品优惠
                                if (sc.getMarketingId() != null && sc.getMarketingId() != 0) {
                                    market = marketService.marketingDetail(sc.getMarketingId());
                                    isT = false;
                                }
                                // 设置活动优惠
                                if (sc.getMarketingActivityId() != null && sc.getMarketingActivityId() != 0) {
                                    market = marketService.marketingDetail(sc.getMarketingActivityId());
                                }
                                // 设置团购优惠 2015-12-30 wuyanbo add
                                if (sc.getGoodsGroupId() != null && sc.getGoodsGroupId() != 0) {
                                    market = marketService.marketingDetail(sc.getGoodsGroupId());
                                }

                                //2015-12-28 wuyanbo 注释
                                /*Marketing market = null;
                                boolean isT = true;
                                // 设置单品优惠
                                if (((ShoppingCart) shoplist.get(i)).getMarketingId() != null) {
                                    market = marketService.marketingDetail(((ShoppingCart) shoplist.get(i)).getMarketingId());
                                    isT = false;
                                }
                                // 设置活动优惠
                                if (((ShoppingCart) shoplist.get(i)).getMarketingActivityId() != null) {
                                    market = marketService.marketingDetailByActive(marketing, ((ShoppingCart) shoplist.get(i)).getMarketingActivityId(), isT);
                                }*/
                                ((ShoppingCart) shoplist.get(i)).setMarketing(market);
                            }
                        }

                    }
                    pb.setList(shoplist);

                }

            } catch (UnsupportedEncodingException e) {
                LOGGER.error("", e);
                Customer cust = (Customer) request.getSession().getAttribute("cust");
                OperaLogUtil.addOperaException(cust.getCustomerUsername(), e, request);
            }
        }
        return pb;
    }

    /**
     * 获取商品库存
     */
    public void getGoodsStock(ShoppingCartWareUtil cartWareUtil, List<Object> shoplist, int i) {
        // 判断是否存在
        // 查询库存
        ProductWare productWare = productWareService.queryProductWareByProductIdAndDistinctId(
                ((ShoppingCart) shoplist.get(i)).getGoodsDetailBean().getProductVo().getGoodsInfoId(), cartWareUtil.getDistrictId());

        if (productWare != null) {
            // 设置商品库存
            ((ShoppingCart) shoplist.get(i)).getGoodsDetailBean().getProductVo().setGoodsInfoStock(productWare.getWareStock());
            ((ShoppingCart) shoplist.get(i)).getGoodsDetailBean().getProductVo().setGoodsInfoPreferPrice(productWare.getWarePrice());
            ((ShoppingCart) shoplist.get(i)).getGoodsDetailBean().getProductVo().setGoodsInfoVipPrice(productWare.getWareVipPrice());
        } else {
            // 如果没有，则设置库存为0
            ((ShoppingCart) shoplist.get(i)).getGoodsDetailBean().getProductVo().setGoodsInfoStock(ShopCartValueUtil.WARECOUNT);
        }
    }

    /**
     * 查询省名称和地区id
     *
     * @param request
     * @return
     */
    @Override
    public ShoppingCartWareUtil selectPNameByParam(HttpServletRequest request) {
        Long dId = null;
        // 获取地区id
        if (request.getSession().getAttribute(DISTINCTID) != null && !"".equals(request.getSession().getAttribute(DISTINCTID))) {
            String obj = request.getSession().getAttribute(DISTINCTID).toString();
            dId = Long.parseLong(obj);
        }
        ShoppingCartWareUtil wareUtil = new ShoppingCartWareUtil();

        // 如果地区id为空，则设置为建邺区
        if (dId == null) {
            dId = addressService.getDefaultIdService();
            if (dId == null) {
                dId = 1103L;
                wareUtil.setProvinceName("江苏");
                wareUtil.setCityName("南京");
                wareUtil.setDistinctName("建邺区");
            } else {
                AddressUtil addressUtil = districtService.queryAddressNameByDistrictId(dId);
                wareUtil.setProvinceName(addressUtil.getProvinceName());
                wareUtil.setCityName(addressUtil.getCityName());
                wareUtil.setDistinctName(addressUtil.getDistrictName());
            }
            wareUtil.setDistrictId(dId);
        } else {
            wareUtil.setDistrictId(dId);
            // 省
            String provinceName = request.getSession().getAttribute("chProvince").toString();
            wareUtil.setProvinceName(provinceName);

            // 市
            String chCity = request.getSession().getAttribute("chCity").toString();
            wareUtil.setCityName(chCity);
            // 地址
            String chDistinct = request.getSession().getAttribute("chDistinct").toString();
            wareUtil.setDistinctName(chDistinct);
        }

        // 区id
        return wareUtil;
    }

    /**
     * 删除优惠分组下单个商品并添加其他商品到购物车内
     *
     * @return 结果
     */
    @Override
    public int delGoodsGroupByS(Long shoppingCartId, Long goodsInfoId, Long fitId, HttpServletRequest request, HttpServletResponse response) {
        Long customerId = (Long) request.getSession().getAttribute(CUSTOMERID);
        Long gId = null;
        if (customerId == null) {
            gId = Long.parseLong(CODE001 + fitId);
        }
        // 先查询到分组下的所有商品
        // 所有的优惠分组
        GoodsGroupVo goodsGroupVo = goodsGroupService.queryVoByPrimaryKey(fitId);
        // 优惠分组下的所有商品
        List<GoodsGroupReleProductVo> vos = goodsGroupVo.getProductList();
        Long dId = (Long) request.getSession().getAttribute(DISTINCTID);
        if (dId == null) {
            dId = (long) 0;
        }
        // 去除要删除的商品
        for (int i = 0; i < vos.size(); i++) {
            Long id = vos.get(i).getProductDetail().getGoodsInfoId();
            // 如果已经登录
            // 循环查找要删除的商品
            if (goodsInfoId.longValue() != id.longValue()) {
                ShoppingCart cart = new ShoppingCart();
                cart.setGoodsInfoId(vos.get(i).getProductDetail().getGoodsInfoId());
                cart.setDistinctId(dId);
                if (customerId != null) {
                    cart.setCustomerId(customerId);
                }
                cart.setGoodsNum(Long.parseLong("1"));
                try {
                    addShoppingCart(cart, request, response);
                } catch (UnsupportedEncodingException e) {
                    LOGGER.error("", e);
                    return 1;
                }

            }
        }
        // 删除优惠分组
        delShoppingCartById(shoppingCartId, gId, request, response);

        return 0;
    }

    /**
     * 更改商品选中状态
     *
     * @param shoppingId 购物车id
     * @param status     要修改的状态
     * @param request
     * @param response
     * @return
     */
    @Override
    public String changeShopStatus(Long shoppingId, String status, HttpServletRequest request, HttpServletResponse response) {
        // 获取当前选中状态的session
        Object obj = request.getSession().getAttribute(NPSTORE_SHOPSTATUS);
        String newStr = "";
        StringBuffer buf = new StringBuffer();

        // 判断session是否有值
        if (obj != null) {
            // 获取所有的购物车状态
            String[] strs = (obj.toString()).split("-");
            for (String str : strs) {
                // 根据标示分割字符，获取这个购物车商品内的id和状态;
                String[] shopStatus = str.split("e");
                if (shopStatus[1].equals(shoppingId.toString())) {
                    buf.append(status);
                    buf.append("e");
                    buf.append(shoppingId);
                    buf.append("-");
                    buf.append(newStr);
                } else {
                    buf.append(str);
                    buf.append("-");
                }
                newStr += buf.toString();
            }
        }
        request.getSession().setAttribute(NPSTORE_SHOPSTATUS, newStr);

        return status;
    }

    /**
     * 批量更改商品选中状态
     *
     * @param shoppingId 购物车id
     * @param status     要修改的状态
     * @param request
     * @param response
     * @return
     */
    @Override
    public String changeShopStatusByParam(Long[] shoppingId, String status, HttpServletRequest request, HttpServletResponse response) {
        String newStr = "";
        StringBuffer buf = new StringBuffer();
        for (Long id : shoppingId) {
            buf.append(status);
            buf.append("e");
            buf.append(id);
            buf.append("-");
            newStr += buf.toString();
        }
        request.getSession().setAttribute(NPSTORE_SHOPSTATUS, newStr);
        return status;
    }

    /**
     * 移除相同的促销
     *
     * @param shopplist
     * @return
     */
    public List<Marketing> marketingIdsListUtil(List<ShoppingCart> shopplist) {
        List<Marketing> list = new ArrayList<Marketing>();
        // 把所购买的所有促销全部放进list里面
        if (shopplist != null && !shopplist.isEmpty()) {
            for (int i = 0; i < shopplist.size(); i++) {
                List<Marketing> alist = shopplist.get(i).getMarketingList();
                if (alist != null && !alist.isEmpty()) {
                    for (int j = 0; j < alist.size(); j++) {
                        // 排除包邮促销和折扣促销
                        if (!"15".equals(alist.get(j).getCodexType()) && !"12".equals(alist.get(j).getCodexType())) {
                            list.add(alist.get(j));
                        }
                    }
                }
            }

        }

        // 移除重复的促销
        for (int q = 0; q < list.size() - 1; q++) {
            for (int p = list.size() - 1; p > q; p--) {
                if (list.get(p).getMarketingId().equals(list.get(q).getMarketingId())) {
                    list.remove(p);
                }
            }

        }

        return list;
    }

    /**
     * 得到可以使用的优惠券
     *
     * @param request
     * @param box
     * @return
     */
    @Override
    public List<Coupon> getUsedCouponlist(HttpServletRequest request, Long[] box) {
        Object object = request.getSession().getAttribute("vip");
        String vip = "0";
        if (null != object) {
            vip = (String) object;
        }

        // 当前购物车所拥有的购物券
        List<Coupon> couponList = null;
        // 过滤购物券得到满足条件优惠券
        List<Coupon> userdCoupon = new ArrayList<>();
        // 查询所有购物车商品
        List<ShoppingCart> shoplist = shoppingCartMapper.shopCartListByIds(Arrays.asList(box));
        if (CollectionUtils.isNotEmpty(shoplist)) {
            List<ParamIds> infoIds = new LinkedList<>();
            // 去除套装,套装不允许使用优惠券
            for (ShoppingCart sp : shoplist) {
                if (sp.getFitId() != null) {
                    // 如果商品是套装,套装不允许使用优惠券
                    shoplist.remove(sp);
                }
            }

            // 商家分组后的中间变量金额
            BigDecimal sumprice = BigDecimal.ZERO;
            BigDecimal sumVipPrice = BigDecimal.ZERO;
            // 货品价格判断是否参与折扣
            BigDecimal goodsprice = BigDecimal.ZERO;
            BigDecimal goodsVipPrice = BigDecimal.ZERO;
            // 第三方id的map
            Map<Long, Object> thirdIdMap = new HashMap<>();
            Map<String, Object> para = new HashMap<>();
            for (int i = 0; i < shoplist.size(); i++) {

                thirdIdMap.put(shoplist.get(i).getThirdId(), "");
                GoodsDetailBean detailBean = goodsProductService.queryDetailBeanByProductId(shoplist.get(i).getGoodsInfoId(),
                        shoplist.get(i).getDistinctId());
                // 查询商品详细
                shoplist.get(i).setGoodsDetailBean(detailBean);
                // 查询购物车里选择的促销信息
                // shoplist.get(i).setMarketing(marketService.marketingDetail(shoplist.get(i).getMarketingActivityId
                // (), shoplist.get(i).getGoodsInfoId()));

                ParamIds p = new ParamIds();
                p.setCouponRangeFkId(shoplist.get(i).getGoodsDetailBean().getProductVo().getGoodsInfoId());
                p.setCouponRangeType("2");
                infoIds.add(p);
                ParamIds p1 = new ParamIds();
                p1.setCouponRangeFkId(shoplist.get(i).getGoodsDetailBean().getBrand().getBrandId());
                p1.setCouponRangeType("1");
                infoIds.add(p1);

                ParamIds p2 = new ParamIds();
                p2.setCouponRangeFkId(shoplist.get(i).getGoodsDetailBean().getProductVo().getGoods().getCatId());
                p2.setCouponRangeType("0");
                infoIds.add(p2);
                // 货品价格
                goodsprice = shoplist.get(i).getGoodsDetailBean().getProductVo().getGoodsInfoPreferPrice();
                goodsVipPrice = shoplist.get(i).getGoodsDetailBean().getProductVo().getGoodsInfoVipPrice();
                // 折扣促销
                if (shoplist.get(i).getMarketingId() != null && 0 != shoplist.get(i).getMarketingId()) {
                    // 从购物车里得到促销id重新从数据库查询,防止当前(折扣促销)已经过期;
                    Marketing mark = marketService.marketingDetail(shoplist.get(i).getMarketingId(), shoplist.get(i).getGoodsInfoId());
                    if (mark != null) {
                        para.put(MARKETINGID, mark.getMarketingId());
                        para.put(GOODSID, shoplist.get(i).getGoodsInfoId());
                        PreDiscountMarketing premark = preDiscountMarketingMapper.selectByMarketId(para);
                        if (premark != null) {
                            // 货品价格
                            goodsprice = premark.getDiscountPrice();
                            goodsVipPrice = premark.getVipdiscountPrice();
                        }
                    }
                }
            }
            couponList = couponService.selectCouponListByIds(infoIds, request);

            if (CollectionUtils.isNotEmpty(couponList)) {

                for (Coupon cou : couponList) {

                    List<CouponRange> ranList = cou.getCouponrangList();
                    for (CouponRange ran : ranList) {
                        for (int i = 0; i < shoplist.size(); i++) {
                            if (cou.getCouponId().equals(ran.getCouponId())) {
                                // 按分类
                                if ("0".equals(ran.getCouponRangeType()) && ran.getCouponRangeFkId().equals(shoplist.get(i).getGoodsDetailBean().getProductVo().getGoods().getCatId())) {
                                    // 商家的总金额
                                    sumprice = sumprice.add(goodsprice.multiply(BigDecimal.valueOf(shoplist.get(i).getGoodsNum())));
                                    sumVipPrice = sumVipPrice.add(goodsVipPrice.multiply(BigDecimal.valueOf(shoplist.get(i).getGoodsNum())));
                                }
                                // 按品牌
                                if ("1".equals(ran.getCouponRangeType()) && ran.getCouponRangeFkId().equals(shoplist.get(i).getGoodsDetailBean().getBrand().getBrandId())) {
                                    // 商家的总金额
                                    sumprice = sumprice.add(goodsprice.multiply(BigDecimal.valueOf(shoplist.get(i).getGoodsNum())));
                                    sumVipPrice = sumVipPrice.add(goodsVipPrice.multiply(BigDecimal.valueOf(shoplist.get(i).getGoodsNum())));
                                }
                                // 按货品
                                if ("2".equals(ran.getCouponRangeType()) && ran.getCouponRangeFkId().equals(shoplist.get(i).getGoodsDetailBean().getProductVo().getGoodsInfoId())) {
                                    // 商家的总金额
                                    sumprice = sumprice.add(goodsprice.multiply(BigDecimal.valueOf(shoplist.get(i).getGoodsNum())));
                                    sumVipPrice = sumVipPrice.add(goodsVipPrice.multiply(BigDecimal.valueOf(shoplist.get(i).getGoodsNum())));
                                }
                            }
                        }
                        // 满减
                        if ("2".equals(cou.getCouponRulesType())) {
                            if (!userdCoupon.contains(cou)) {

                                // 满多少减金额
                                BigDecimal priceflag = cou.getCouponFullReduction().getFullPrice();
                                if ("1".equals(vip)) {
                                    if (priceflag.compareTo(sumVipPrice) == -1) {
                                        userdCoupon.add(cou);
                                    }
                                } else {
                                    if (priceflag.compareTo(sumprice) == -1) {
                                        userdCoupon.add(cou);
                                    }
                                }
                            }
                        } else {
                            // 直降的优惠券没有限制都允许
                            if (!userdCoupon.contains(cou)) {
                                userdCoupon.add(cou);
                            }
                        }
                        sumprice = BigDecimal.ZERO;
                        sumVipPrice = BigDecimal.ZERO;
                    }

                }

            }
        }
        return userdCoupon;
    }

    /**
     * 得到各个商家的金额
     *
     * @param businessId
     * @param shopdata
     * @return
     */
    @Override
    public Map<String, Object> getEveryThirdPriceMap(Long businessId, List<ShoppingCart> shopdata, Long distinctId, String vip) {

        List<ShoppingCart> shoplist = new ArrayList<>();
        Map<String, Object> paramMap = new HashMap<>();
        // 1表示不同地区存在库存 0则表示库存不足直接跳到购物车
        paramMap.put(STOCK, "1");
        if (CollectionUtils.isNotEmpty(shopdata)) {

            for (int i = 0; i < shopdata.size(); i++) {
                if (businessId.equals(shopdata.get(i).getThirdId())) {
                    shoplist.add(shopdata.get(i));
                }
            }
        }
        // 交易总金额
        BigDecimal sumPrice = BigDecimal.valueOf(0);
        BigDecimal sumVipPrice = BigDecimal.valueOf(0);
        // 原始总金额
        BigDecimal sumOldPrice = BigDecimal.valueOf(0);
        BigDecimal sumOldVipPrice = BigDecimal.valueOf(0);
        // 中间变量
        BigDecimal flag = BigDecimal.valueOf(0);
        BigDecimal flagVip = BigDecimal.valueOf(0);
        // 优惠金额
        BigDecimal prePrice = BigDecimal.valueOf(0);
        BigDecimal preVipPrice = BigDecimal.valueOf(0);
        // boss总金额
        BigDecimal bossSumPrice = BigDecimal.ZERO;
        BigDecimal bossSumVipPrice = BigDecimal.ZERO;
        // 套装优惠金额
        BigDecimal taozhuan = BigDecimal.ZERO;
        ProductWare productWare;
        Map<String, Object> para = new HashMap<>();
        BigDecimal sum = BigDecimal.ZERO;

        if (CollectionUtils.isNotEmpty(shoplist)) {
            Long goodssum = 0L;
            BigDecimal goodsprice = BigDecimal.ZERO;
            BigDecimal totalprice = BigDecimal.ZERO;
            BigDecimal goodsVipPrice = BigDecimal.ZERO;
            BigDecimal totalVipPrice = BigDecimal.ZERO;

            for (int v = 0; v < shoplist.size(); v++) {

                if (shoplist.get(v).getFitId() != null) {
                    // 如果商品是套装
                    GoodsGroupVo goodsGroupVo = shoplist.get(v).getGoodsGroupVo();
                    if (null != goodsGroupVo) {

                        shoplist.get(v).setGoodsGroupVo(goodsGroupVo);
                        // 获取此套装下的所有货品
                        List<GoodsProductVo> goodsProducts = goodsProductMapper.queryDetailByGroupId(shoplist.get(v).getFitId());
                        for (int j = 0; j < goodsProducts.size(); j++) {
                            if (goodsGroupVo.getThirdId() == 0 && null != distinctId && distinctId > 0) {
                                productWare = this.productWareService.queryProductWareByProductIdAndDistinctId(goodsProducts.get(j).getGoodsInfoId(), distinctId);
                                // 不同地区的价格不同
                                if (null != productWare && productWare.getWareStock() <= 0) {
                                    paramMap.put(STOCK, "0");
                                }
                            }

                            // 原总金额加上套装优惠前费用
                            sumOldPrice = sumOldPrice.add(goodsProducts.get(j).getGoodsInfoPreferPrice().multiply(BigDecimal.valueOf(shoplist.get(v).getGoodsNum())));
                            flag = flag.add(goodsProducts.get(j).getGoodsInfoPreferPrice().multiply(BigDecimal.valueOf(shoplist.get(v).getGoodsNum())));
                            sumOldVipPrice = sumOldVipPrice.add(goodsProducts.get(j).getGoodsInfoVipPrice().multiply(BigDecimal.valueOf(shoplist.get(v).getGoodsNum())));
                            flagVip = flagVip.add(goodsProducts.get(j).getGoodsInfoVipPrice().multiply(BigDecimal.valueOf(shoplist.get(v).getGoodsNum())));
                        }

                        BigDecimal currtyPrice = null;
                        if ("1".equals(vip)) {//企业会员
                            if (shoplist.get(v).getMarketingList() != null) {
                                String codexType = shoplist.get(v).getMarketingList().get(0).getCodexType();
                                if ("10".equals(codexType)) {
                                    currtyPrice = shoplist.get(v).getMarketingList().get(0).getGroupon().getGrouponVipPrice();
                                } else if ("11".equals(codexType)) {
                                    currtyPrice = shoplist.get(v).getMarketingList().get(0).getRushs().get(0).getRushVipPrice();
                                } else if ("15".equals(codexType)) {
                                    currtyPrice = shoplist.get(v).getMarketingList().get(0).getPreDiscountMarketings().get(0).getVipdiscountPrice();
                                } else {
                                    currtyPrice = shoplist.get(v).getGoodsDetailBean().getProductVo().getGoodsInfoVipPrice();
                                }
                            } else {
                                currtyPrice = shoplist.get(v).getGoodsDetailBean().getProductVo().getGoodsInfoVipPrice();
                            }
                        } else {//普通会员
                            if (shoplist.get(v).getMarketingList() != null) {
                                String codexType = shoplist.get(v).getMarketingList().get(0).getCodexType();
                                if ("10".equals(codexType)) {
                                    currtyPrice = shoplist.get(v).getMarketingList().get(0).getGroupon().getGrouponPrice();
                                } else if ("11".equals(codexType)) {
                                    currtyPrice = shoplist.get(v).getMarketingList().get(0).getRushs().get(0).getRushPrice();
                                } else if ("15".equals(codexType)) {
                                    currtyPrice = shoplist.get(v).getMarketingList().get(0).getPreDiscountMarketings().get(0).getDiscountPrice();
                                } else {
                                    currtyPrice = shoplist.get(v).getGoodsDetailBean().getProductVo().getGoodsInfoPreferPrice();
                                }
                            } else {
                                currtyPrice = shoplist.get(v).getGoodsDetailBean().getProductVo().getGoodsInfoPreferPrice();
                            }
                        }


                        // 得到套装优惠费用
                        taozhuan = taozhuan.add(BigDecimal.valueOf(shoplist.get(v).getGoodsNum()).multiply(currtyPrice));
                    }
                }

                if (shoplist.get(v).getFitId() == null) {

                    // 货品价格
                    goodsprice = shoplist.get(v).getGoodsDetailBean().getProductVo().getGoodsInfoPreferPrice();
                    goodsVipPrice = shoplist.get(v).getGoodsDetailBean().getProductVo().getGoodsInfoVipPrice();
                    // 不同地区的货品价格
                    if (businessId == 0 && null != distinctId && distinctId > 0) {
                        productWare = this.productWareService.queryProductWareByProductIdAndDistinctId(shoplist.get(v).getGoodsInfoId(), distinctId);
                        // 不同地区的价格不同
                        if (null != productWare) {
                            goodsprice = productWare.getWarePrice();
                            goodsVipPrice = productWare.getWareVipPrice();
                            if (productWare.getWareStock() <= 0) {
                                paramMap.put(STOCK, "0");
                            }
                        }
                    }

                    //单该货品同时参与了团购和折扣时,优先级团购优先
                    if (shoplist.get(v).getGoodsGroupId() != null) {
                        // 从购物车里得到促销id重新从数据库查询,防止当前(团购促销)已经过期;
                        Marketing mark = marketService.querySimpleMarketingById(shoplist.get(v).getGoodsGroupId());
                        if (mark != null) {
                            Groupon groupon = grouponMapper.selectByMarketId(mark.getMarketingId());
                            if (groupon != null) {
                                goodsprice = groupon.getGrouponPrice();
                                goodsVipPrice = groupon.getGrouponVipPrice();
                            }
                        }
                    }

                    // 折扣促销
                    if ((shoplist.get(v).getGoodsGroupId() == null || shoplist.get(v).getGoodsGroupId() == 0) && shoplist.get(v).getMarketingId() != null && 0 != shoplist.get(v).getMarketingId()) {
                        // 从购物车里得到促销id重新从数据库查询,防止当前(折扣促销)已经过期;
                        Marketing mark = marketService.marketingDetail(shoplist.get(v).getMarketingId(), shoplist.get(v).getGoodsInfoId());
                        if (mark != null) {
                            para.put("marketingId", mark.getMarketingId());
                            para.put("goodsId", shoplist.get(v).getGoodsInfoId());
                            PreDiscountMarketing premark = preDiscountMarketingMapper.selectByMarketId(para);
                            if (premark != null) {
                                // 是否抹掉分角
                                String discountFlag = "";
                                DecimalFormat myformat = null;
                                myformat = new DecimalFormat("0.00");
                                // 货品价格
                                goodsprice = goodsprice.multiply(premark.getDiscountInfo());
                                discountFlag = premark.getDiscountFlag();
                                goodsVipPrice = goodsVipPrice.multiply(premark.getVipdiscountInfo());

                                // 抹掉分
                                if ("1".equals(discountFlag)) {
                                    myformat = new DecimalFormat("0.0");
                                } else if ("2".equals(discountFlag)) {
                                    myformat = new DecimalFormat("0");
                                } else {
                                    myformat = new DecimalFormat("0.00");
                                }
                                //不四舍五入
                                myformat.setRoundingMode(RoundingMode.FLOOR);
                                goodsprice = BigDecimal.valueOf(Double.valueOf(myformat.format(goodsprice)));
                                goodsVipPrice = BigDecimal.valueOf(Double.valueOf(myformat.format(goodsVipPrice)));
                            }
                        }
                    }

                    //单该货品同时参与了团购和折扣时,优先级团购优先
                    if (shoplist.get(v).getMarketing() != null && shoplist.get(v).getMarketing().getGroupon() != null) {
                        goodsprice = shoplist.get(v).getMarketing().getGroupon().getGrouponPrice();
                        goodsVipPrice = shoplist.get(v).getMarketing().getGroupon().getGrouponVipPrice();
                    }

                    //如果参加抢购
                    if (shoplist.get(v).getMarketing() != null && shoplist.get(v).getMarketing().getRushs() != null) {
                        goodsprice = shoplist.get(v).getMarketing().getRushs().get(0).getRushPrice();
                        goodsVipPrice = shoplist.get(v).getMarketing().getRushs().get(0).getRushVipPrice();
                    }

                    /*// 折扣促销
                    if (shoplist.get(v).getMarketingId() != null && 0 != shoplist.get(v).getMarketingId()) {
                        // 从购物车里得到促销id重新从数据库查询,防止当前(折扣促销)已经过期;
                        Marketing mark = marketService.marketingDetail(shoplist.get(v).getMarketingId(), shoplist.get(v).getGoodsInfoId());
                        if (mark != null) {
                            para.put(MARKETINGID, mark.getMarketingId());
                            para.put(GOODSID, shoplist.get(v).getGoodsInfoId());
                            PreDiscountMarketing premark = preDiscountMarketingMapper.selectByMarketId(para);
                            if (premark != null) {
                                // 货品价格
                                goodsprice = goodsprice.multiply(premark.getDiscountInfo());
                                goodsVipPrice = goodsVipPrice.multiply(premark.getVipdiscountInfo());
                                // 促销有效
                                shoplist.get(v).setIsMarketing(0);
                            }
                        }
                    }*/
                    // 货品购买件数
                    goodssum = shoplist.get(v).getGoodsNum();
                    // 计算boss价格页面计算用
                    if (shoplist.get(v).getThirdId() == 0) {
                        bossSumPrice = bossSumPrice.add(goodsprice.multiply(BigDecimal.valueOf(goodssum)));
                        bossSumVipPrice = bossSumVipPrice.add(goodsVipPrice.multiply(BigDecimal.valueOf(goodssum)));
                    }
                    // 计算原始总金额
                    sumOldPrice = sumOldPrice.add(goodsprice.multiply(BigDecimal.valueOf(goodssum)));
                    flag = flag.add(goodsprice.multiply(BigDecimal.valueOf(goodssum)));
                    sumOldVipPrice = sumOldVipPrice.add(goodsVipPrice.multiply(BigDecimal.valueOf(goodssum)));
                    flagVip = flagVip.add(goodsVipPrice.multiply(BigDecimal.valueOf(goodssum)));
                }

                //2015-12-29 wuyanbo close
                /*BigDecimal currtyPrice = BigDecimal.ZERO;
                List<Marketing> marketingList = shoplist.get(v).getMarketingList();
                if("1".equals(vip)){//企业会员
                    if(marketingList!=null && marketingList.size()>0){
                        String codexType =  marketingList.get(0).getCodexType();
                        if("10".equals(codexType)){
                            currtyPrice =  marketingList.get(0).getGroupon().getGrouponVipPrice();
                        }else if("11".equals(codexType)){
                            currtyPrice = marketingList.get(0).getRushs().get(0).getRushVipPrice();
                        }else if("15".equals(codexType)){
                            currtyPrice = marketingList.get(0).getPreDiscountMarketings().get(0).getVipdiscountPrice();
                        }else{
                            currtyPrice =  shoplist.get(v).getGoodsDetailBean().getProductVo().getGoodsInfoVipPrice();
                        }
                    }else{
                        currtyPrice =  shoplist.get(v).getGoodsDetailBean().getProductVo().getGoodsInfoVipPrice();
                    }
                }else{//普通会员
                    if(marketingList!=null && marketingList.size()>0){
                        String codexType =  marketingList.get(0).getCodexType();
                        if("10".equals(codexType)){
                            currtyPrice =  marketingList.get(0).getGroupon().getGrouponPrice();
                        }else if("11".equals(codexType)){
                            currtyPrice = marketingList.get(0).getRushs().get(0).getRushPrice();
                        }else if("15".equals(codexType)){
                            currtyPrice = marketingList.get(0).getPreDiscountMarketings().get(0).getDiscountPrice();
                        }else{
                            currtyPrice =  shoplist.get(v).getGoodsDetailBean().getProductVo().getGoodsInfoPreferPrice();
                        }
                    }else{
                        currtyPrice =  shoplist.get(v).getGoodsDetailBean().getProductVo().getGoodsInfoPreferPrice();
                    }
                }
                sum = sum.add(BigDecimal.valueOf(shoplist.get(v).getGoodsNum()).multiply(currtyPrice));*/

            }
            BigDecimal marketflag = BigDecimal.ZERO;
            BigDecimal marketVipFlag = BigDecimal.ZERO;
            List<ShoppingCart> cartList = null;
            // 根据第三方id分组得到新的购物车集合
            cartList = new ArrayList<>();
            // 第三方分组后的购物车id集合
            List<Long> thirdGroupIds = new ArrayList<>();
            for (ShoppingCart sc : shoplist) {
                if (sc.getFitId() == null) {

                    cartList.add(sc);
                    thirdGroupIds.add(sc.getShoppingCartId());

                }
            }
            // 根据购物车id集合先从第三方分组,再促销分组,数据库已经处理价格
            List<ShoppingCart> groupmarketList = shoppingCartMapper.shopCartListGroupByMarkettingId(thirdGroupIds);
            if (CollectionUtils.isNotEmpty(groupmarketList)) {
                // 促销分组
                Map<Long, Object> markMap = new HashMap<>();
                for (ShoppingCart sc : cartList) {
                    markMap.put(sc.getMarketingActivityId(), sc.getMarketing());
                }
                for (ShoppingCart shop : groupmarketList) {
                    if (markMap.containsKey(shop.getMarketingActivityId())) {
                        Marketing mark = (Marketing) markMap.get(shop.getMarketingActivityId());
                        // 货品分组总价格数据库已经做过处理
                        totalprice = shop.getGoodsPrice();
                        totalVipPrice = shop.getGoodsPrice();
                        if (shop.getMarketgoodsPrice() != null) {
                            // 折扣促销在有效期内
                            totalprice = shop.getMarketgoodsPrice();
                            totalVipPrice = shop.getMarketgoodsPrice();
                        }
                        // 满减
                        if (CollectionUtils.isNotEmpty(mark.getFullbuyReduceMarketings())) {
                            for (FullbuyReduceMarketing fpm : mark.getFullbuyReduceMarketings()) {
                                // 达到满减条件
                                if (fpm.getFullPrice().compareTo(totalprice) == -1 || fpm.getFullPrice().compareTo(totalprice) == 0) {
                                    prePrice = fpm.getReducePrice();
                                    // 把最大值付给marketfalg
                                    if (prePrice.compareTo(marketflag) == 1 || prePrice.compareTo(marketflag) == 0) {
                                        marketflag = prePrice;
                                    }
                                }
                                if (fpm.getVipFullPrice().compareTo(totalVipPrice) == -1 || fpm.getVipFullPrice().compareTo(totalVipPrice) == 0) {
                                    preVipPrice = fpm.getVipReducePrice();
                                    // 把最大值付给marketfalg
                                    if (preVipPrice.compareTo(marketflag) == 1 || preVipPrice.compareTo(marketflag) == 0) {
                                        marketVipFlag = preVipPrice;
                                    }
                                }
                            }
                            prePrice = marketflag;
                            marketflag = BigDecimal.ZERO;
                            preVipPrice = marketVipFlag;
                            marketVipFlag = BigDecimal.ZERO;
                        }
                        // 满折促销
                        if (CollectionUtils.isNotEmpty(mark.getFullbuyDiscountMarketings())) {

                            for (FullbuyDiscountMarketing fdm : mark.getFullbuyDiscountMarketings()) {
                                if (fdm.getFullPrice().compareTo(totalprice) == -1 || fdm.getFullPrice().compareTo(totalprice) == 0) {
                                    prePrice = (BigDecimal.valueOf(1).subtract(fdm.getFullbuyDiscount())).multiply(totalprice);
                                    // 把最大值付给marketfalg
                                    if (prePrice.compareTo(marketflag) == 1 || prePrice.compareTo(marketflag) == 0) {
                                        marketflag = prePrice;
                                    }
                                }
                                if (fdm.getVipFullPrice().compareTo(totalVipPrice) == -1 || fdm.getVipFullPrice().compareTo(totalVipPrice) == 0) {
                                    preVipPrice = (BigDecimal.valueOf(1).subtract(fdm.getVipFullbuyDiscount())).multiply(totalVipPrice);
                                    // 把最大值付给marketfalg
                                    if (preVipPrice.compareTo(marketVipFlag) == 1 || preVipPrice.compareTo(marketVipFlag) == 0) {
                                        marketVipFlag = preVipPrice;
                                    }
                                }
                            }
                            prePrice = marketflag;
                            marketflag = BigDecimal.ZERO;
                            preVipPrice = marketVipFlag;
                            marketVipFlag = BigDecimal.ZERO;
                        }

                        // 实际付款金额
                        flag = flag.subtract(prePrice);
                        flagVip = flagVip.subtract(preVipPrice);
                    }
                }
                for (int k = 0; k < cartList.size(); k++) {
                    // 直降
                    if (cartList.get(k).getMarketing() != null && cartList.get(k).getMarketing().getPriceOffMarketing() != null) {
                        // sumOldPrice =
                        // sumOldPrice.subtract(cartList.get(k).getMarketing().getPriceOffMarketing()
                        // .getOffValue().multiply(BigDecimal.valueOf(cartList.get(k).getGoodsNum())));
                        flag = flag.subtract(cartList.get(k).getMarketing().getPriceOffMarketing().getOffValue().multiply(BigDecimal.valueOf(cartList.get(k).getGoodsNum())));
                        flagVip = flagVip.subtract(cartList.get(k).getMarketing().getPriceOffMarketing().getOffVipValue().multiply(BigDecimal.valueOf(cartList.get(k).getGoodsNum())));
                    }
                }

            }
        }
        sumPrice = flag.subtract(taozhuan);
        sumVipPrice = flagVip.subtract(taozhuan);

        if ("1".equals(vip)) {
            paramMap.put("sumOldPrice", sumOldVipPrice);
            paramMap.put(BOSSSUMPRICE, bossSumVipPrice);
            /*paramMap.put(SUMPRICE, sum);*/
            paramMap.put(SUMPRICE, sumVipPrice);
        } else {
            paramMap.put("sumOldPrice", sumOldPrice);
            paramMap.put(BOSSSUMPRICE, bossSumPrice);
            /*paramMap.put(SUMPRICE, sum);*/
            paramMap.put(SUMPRICE, sumPrice);
        }

        return paramMap;
    }

    /**
     * 新购物车结算
     *
     * @param request
     * @param box
     * @return
     */
    @Override
    public Map<String, Object> newsubOrder(HttpServletRequest request, Long[] box, CustomerAddress customerAddress) {

        Object object = request.getSession().getAttribute("vip");
        String vip = "0";
        if (null != object) {
            vip = (String) object;
        }

        Map<String, Object> cartMap = new HashMap<String, Object>();
        Long distinctId = null;
        if (customerAddress != null && customerAddress.getDistrict() != null) {
            distinctId = customerAddress.getDistrict().getDistrictId();
        }
        // 查询所有购物车商品
        List<ShoppingCart> shoplist;
        List<ParamIds> infoIds = new LinkedList<>();
        shoplist = shoppingCartMapper.shopCartListByIds(Arrays.asList(box));
        // 第三方id的map
        Map<Long, Object> thirdMap = new HashMap<>();
        // 查询购物车商品详细
        // 判断是否为null
        if (shoplist != null && !shoplist.isEmpty()) {
            for (int i = 0; i < shoplist.size(); i++) {

                if (shoplist.get(i).getFitId() == null) {
                    // 查询商品详细
                    GoodsDetailBean detailBean = goodsProductService.queryDetailBeanByProductId(shoplist.get(i).getGoodsInfoId(),
                            shoplist.get(i).getDistinctId());
                    thirdMap.put(detailBean.getProductVo().getThirdId(), detailBean.getProductVo().getThirdName());
                    shoplist.get(i).setGoodsDetailBean(detailBean);
                    // 查询商品参加的促销信息
                    shoplist.get(i).setMarketingList(
                            marketService.selectMarketingByGoodsInfoId(shoplist.get(i).getGoodsInfoId(), shoplist.get(i).getGoodsDetailBean().getBrand().getBrandId(), shoplist
                                    .get(i).getGoodsDetailBean().getProductVo().getGoods().getCatId()));
                    Long marketingActivityId = shoplist.get(i).getMarketingActivityId();
                    Long marketingId = shoplist.get(i).getMarketingId();
                    Long goodsGroupId = shoplist.get(i).getGoodsGroupId();
                    if (marketingActivityId != null && marketingActivityId != 0) {//抢购、满减、满折、直降
                        shoplist.get(i).setMarketing(marketService.marketingDetail(marketingActivityId, shoplist.get(i).getGoodsInfoId()));
                    }
                    if (marketingId != null && marketingId != 0) {//抢购
                        shoplist.get(i).setMarketing(marketService.marketingDetail(marketingId, shoplist.get(i).getGoodsInfoId()));
                    }
                    if (goodsGroupId != null && goodsGroupId != 0) {//团购
                        shoplist.get(i).setMarketing(marketService.marketingDetail(goodsGroupId, shoplist.get(i).getGoodsInfoId()));
                    }

                    ParamIds p = new ParamIds();
                    p.setCouponRangeFkId(shoplist.get(i).getGoodsDetailBean().getProductVo().getGoodsInfoId());
                    p.setCouponRangeType("2");
                    infoIds.add(p);
                    ParamIds p1 = new ParamIds();
                    p1.setCouponRangeFkId(shoplist.get(i).getGoodsDetailBean().getBrand().getBrandId());
                    p1.setCouponRangeType("1");
                    infoIds.add(p1);

                    ParamIds p2 = new ParamIds();
                    p2.setCouponRangeFkId(shoplist.get(i).getGoodsDetailBean().getProductVo().getGoods().getCatId());
                    p2.setCouponRangeType("0");
                    infoIds.add(p2);

                    if (shoplist.get(i).getThirdId() == 0 && distinctId != null) {
                        // 根据地区id重新查询不同仓库的价格
                        ProductWare productWare = productWareService.queryProductWareByProductIdAndDistinctId(shoplist.get(i).getGoodsDetailBean().getProductVo().getGoodsInfoId(),
                                distinctId);
                        if (productWare != null) {
                            // 设置商品库存
                            shoplist.get(i).getGoodsDetailBean().getProductVo().setGoodsInfoStock(productWare.getWareStock());
                            shoplist.get(i).getGoodsDetailBean().getProductVo().setGoodsInfoPreferPrice(productWare.getWarePrice());
                            shoplist.get(i).getGoodsDetailBean().getProductVo().setGoodsInfoVipPrice(productWare.getWareVipPrice());
                        }
                    }

                } else {
                    Long stock = null;
                    // 如果商品是套装
                    GoodsGroupVo goodsGroupVo = goodsGroupService.queryVoByPrimaryKey(shoplist.get(i).getFitId());

                    shoplist.get(i).setGoodsGroupVo(goodsGroupVo);
                    thirdMap.put(goodsGroupVo.getThirdId(), goodsGroupVo.getThirdName());
                    // 给套装购物车塞上套装的第三方id
                    shoplist.get(i).setThirdId(goodsGroupVo.getThirdId());
                    // 该套装下所有的商品
                    List<GoodsGroupReleProductVo> goodsGroupReleProductVos = shoplist.get(i).getGoodsGroupVo().getProductList();
                    for (int j = 0; j < goodsGroupReleProductVos.size(); j++) {

                        GoodsDetailBean goodsDetailBean = goodsProductService.queryDetailBeanByProductId(goodsGroupReleProductVos.get(j).getProductDetail().getGoodsInfoId(),
                                distinctId);

                        if (goodsDetailBean != null) {
                            if (stock == null) {
                                stock = goodsDetailBean.getProductVo().getGoodsInfoStock();
                            } else {
                                if (stock > goodsDetailBean.getProductVo().getGoodsInfoStock()) {
                                    stock = goodsDetailBean.getProductVo().getGoodsInfoStock();
                                }
                            }
                        }
                        // 重新设置套装下商品库存
                        shoplist.get(i).getGoodsGroupVo().getProductList().get(j).getProductDetail().setGoodsInfoStock(goodsDetailBean.getProductVo().getGoodsInfoStock());
                    }
                    shoplist.get(i).getGoodsGroupVo().setStock(stock);
                    // 重新设置list
                    shoplist.get(i).getGoodsGroupVo().setProductList(goodsGroupReleProductVos);
                    // 设置套装商品库存end
                }

            }
        }
        BigDecimal sumPrice = BigDecimal.ZERO;
        List<OrderUtil> orderMarketings = new ArrayList<OrderUtil>();
        for (Long thirdId : thirdMap.keySet()) {
            OrderUtil ou = new OrderUtil();
            ou.setThirdId(thirdId);
            // 商家名称
            ou.setInfoRealname(thirdMap.get(thirdId).toString());
            orderMarketings.add(ou);
            Map<String, Object> maps = getEveryThirdPriceMap(thirdId, shoplist, distinctId, vip);
            // boss商品
            if (0 == thirdId) {
                cartMap.put(BOSSSUMPRICE, maps.get(BOSSSUMPRICE));
            }
            // 应付款的金额
            sumPrice = sumPrice.add(BigDecimal.valueOf(Double.valueOf(maps.get(SUMPRICE).toString())));
        }
        List<FreightTemplate> freight = new ArrayList<>();
        FreightTemplate ft = null;
        // 循环得到快递方式
        for (OrderUtil st : orderMarketings) {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("freightIsDefault", "1");
            paramMap.put("freightThirdId", st.getThirdId());
            ft = freightTemplateMapper.selectFreightTemplateSubOrder(paramMap);
            if (ft != null) {
                freight.add(ft);
            }

        }
        // 获取积分消费规则
        PointSet pointSet = this.couponService.selectPointSet();
        // 根据会员ID获取积分对象
        Long customerId = (Long) request.getSession().getAttribute(CUSTOMERID);
        CustomerPoint customerPoint = couponService.selectCustomerPointByCustomerId(customerId);
        List<Coupon> couponList = null;
        if (!infoIds.isEmpty()) {
            couponList = couponService.selectCouponListByIds(infoIds, request);
        }

        if (null != customerPoint && null != pointSet) {
            // 保存当前登录会员总积分
            cartMap.put("customerPoint", customerPoint.getPointSum());
            // 保存积分兑换金额的比例 （消费规则）
            cartMap.put("pointSet", pointSet.getConsumption());
        }
        cartMap.put("freightlist", freight);
        cartMap.put("couponlist", couponList);
        cartMap.put("shoplist", shoplist);
        cartMap.put(SUMPRICE, sumPrice);
        cartMap.put("orderMarketings", orderMarketings);
        return cartMap;
    }

    /**
     * 根据所选择的商品进入订单确认查询
     *
     * @param request
     * @param box
     * @return List
     */
    @Override
    public Map<String, Object> subOrder(HttpServletRequest request, Long[] box, Long[] marketingId, Long[] thirdId, ShoppingCartWareUtil wareUtil) {

        // 用来存放数据
        Map<String, Object> paramMap = new HashMap<String, Object>();

        List<Long> list = new ArrayList<Long>();
        List<ParamIds> infoIds = new ArrayList<ParamIds>();
        // 购物车id数量不为0
        if (box.length != 0) {
            for (Long bo : box) {
                list.add(bo);
            }
        }
        List<ShoppingCart> shoplist = searchByProduct(request, box);
        // 存放第三方店铺标示
        List<Object> thirdIds = new ArrayList<Object>();

        // 添加第三方id
        if (shoplist != null && !shoplist.isEmpty()) {
            for (ShoppingCart sc : shoplist) {
                if (sc.getFitId() == null) {
                    thirdIds.add(sc.getGoodsDetailBean().getProductVo().getThirdId());
                } else {
                    if (sc.getGoodsGroupVo().getIsThird() != null) {
                        thirdIds.add(Long.parseLong(sc.getGoodsGroupVo().getIsThird()));
                    } else {
                        thirdIds.add((long) 0);
                    }
                }
                if (sc.getFitId() == null) {
                    // 判断是否包邮
                    Long baoyou = marketService.queryByCreatimeMarketings(sc.getGoodsInfoId(), 6L, sc.getGoodsDetailBean().getProductVo().getGoods().getCatId(), sc
                            .getGoodsDetailBean().getBrand().getBrandId());
                    if (baoyou != 0) {
                        sc.setIsBaoyou("1");
                    } else {
                        sc.setIsBaoyou("0");
                    }
                }
            }
            // 去除重复第三方id
            for (int q = 0; q < thirdIds.size() - 1; q++) {
                for (int p = thirdIds.size() - 1; p > q; p--) {
                    if (thirdIds.get(p).equals(thirdIds.get(q))) {
                        thirdIds.remove(p);
                    }
                }

            }
        }
        // 记录店铺名称
        Map<Object, String> thirdName = new HashMap<Object, String>();
        List<Long> goodsInfoIds = new ArrayList<Long>();
        if (shoplist != null && !shoplist.isEmpty()) {
            for (int i = 0; i < shoplist.size(); i++) {
                if (shoplist.get(i).getFitId() != null) {

                    // 该套装下所有的商品
                    List<GoodsGroupReleProductVo> goodsGroupReleProductVos = shoplist.get(i).getGoodsGroupVo().getProductList();

                    // 设置套装商品库存
                    // 套装下商品
                    for (int j = 0; j < goodsGroupReleProductVos.size(); j++) {
                        Map<String, Long> map = new HashMap<String, Long>();
                        // 根据商品id和地区id取得商品库存欣喜
                        ProductWare productWare = productWareMapper.queryProductWareByProductIdAndDistinctId(map);
                        // 判断是否存在
                        if (productWare != null) {
                            // 设置库存
                            goodsGroupReleProductVos.get(j).getProductDetail().setGoodsInfoStock(productWare.getWareStock());
                            // 设置该库存下商品价格
                            goodsGroupReleProductVos.get(j).getProductDetail().setGoodsInfoPreferPrice(productWare.getWarePrice());
                            goodsGroupReleProductVos.get(j).getProductDetail().setGoodsInfoVipPrice(productWare.getWareVipPrice());
                        } else {
                            // 设置库存为0
                            goodsGroupReleProductVos.get(j).getProductDetail().setGoodsInfoStock((long) 0);
                            // 设置该套装不可用
                            shoplist.get(i).getGoodsGroupVo().setGroupDelflag("1");
                        }
                    }
                    // 重新设置list
                    shoplist.get(i).getGoodsGroupVo().setProductList(goodsGroupReleProductVos);
                    // 设置套装商品库存end

                } else {
                    shoplist.get(i).setGoodsDetailBean(goodsProductService.queryDetailBeanByProductId(shoplist.get(i).getGoodsInfoId(), shoplist.get(i).getDistinctId()));
                    ParamIds p = new ParamIds();
                    p.setCouponRangeFkId(shoplist.get(i).getGoodsDetailBean().getProductVo().getGoodsInfoId());
                    p.setCouponRangeType("2");
                    infoIds.add(p);
                    for (int j = 0; j < thirdIds.size(); j++) {
                        if (shoplist.get(i).getGoodsDetailBean().getProductVo().getThirdId().equals(thirdIds.get(j))) {
                            thirdName.put(thirdIds.get(j), shoplist.get(i).getGoodsDetailBean().getProductVo().getThirdName());
                        }
                    }
                    goodsInfoIds.add(shoplist.get(i).getGoodsDetailBean().getProductVo().getGoodsInfoId());
                    ParamIds p1 = new ParamIds();
                    p1.setCouponRangeFkId(shoplist.get(i).getGoodsDetailBean().getBrand().getBrandId());
                    p1.setCouponRangeType("1");
                    infoIds.add(p1);

                    ParamIds p2 = new ParamIds();
                    p2.setCouponRangeFkId(shoplist.get(i).getGoodsDetailBean().getProductVo().getGoods().getCatId());
                    p2.setCouponRangeType("0");
                    infoIds.add(p2);
                    // 设置订单优惠
                    // 设置选中的订单优惠
                    if (shoplist.get(i).getOrderMarketingId() != null && shoplist.get(i).getOrderMarketingId() > 0) {
                        shoplist.get(i).setOrderMarket(marketService.marketingDetail(shoplist.get(i).getOrderMarketingId()));
                    }
                }
            }
        }
        List<OrderUtil> orderMarketings = new ArrayList<OrderUtil>();
        // 根据每个商家加入商家信息
        for (int i = 0; i < thirdIds.size(); i++) {
            OrderUtil orderUtil = new OrderUtil();
            Object obj = thirdIds.get(i);
            if (obj == null) {
                obj = 0;
            }
            // 存放商家信息
            orderUtil.setThirdId(Long.parseLong(obj.toString()));
            if ("0".equals(obj.toString())) {
                orderUtil.setInfoRealname("BOSS");
            } else {
                orderUtil.setInfoRealname(thirdName.get(thirdIds.get(i)));
            }
            // 判断商品数量是否大于0，不包含套装
            if (!goodsInfoIds.isEmpty()) {

                List<Marketing> marketings = marketService.queryOrderMarketingByGoodsId(goodsInfoIds, (Long) thirdIds.get(i));
                orderUtil.setMarketings(marketings);
                if (marketings != null) {
                    if (marketingId == null && thirdId != null) {
                        orderUtil.setMarketing(null);
                    } else {
                        // 加入所有的订单信息
                        // 判断是否有选中订单优惠
                        if (marketingId != null && thirdId != null) {
                            if (marketingId[i] != 0) {
                                orderUtil.setMarketing(marketService.marketingDetail(marketingId[i]));
                            } else {
                                orderUtil.setMarketing(null);
                            }
                        } else {
                            if (!marketings.isEmpty()) {
                                orderUtil.setMarketing(marketService.marketingDetail(marketings.get(0).getMarketingId()));
                            }
                        }

                    }
                }
            }

            orderMarketings.add(orderUtil);
        }
        List<Coupon> couponList = null;
        if (!infoIds.isEmpty()) {
            couponList = couponService.selectCouponListByIds(infoIds, request);
        }
        paramMap.put("shoplist", shoplist);
        paramMap.put("couponlist", couponList);
        // 商品的店铺标示
        paramMap.put("thirdIds", thirdIds);
        paramMap.put("orderMarketings", orderMarketings);
        // 根据会员ID获取积分对象
        CustomerPoint customerPoint = couponService.selectCustomerPointByCustomerId((Long) request.getSession().getAttribute(CUSTOMERID));
        // 获取积分消费规则
        PointSet pointSet = this.couponService.selectPointSet();
        if (null != customerPoint && null != pointSet) {
            // 保存当前登录会员总积分
            paramMap.put("customerPoint", customerPoint.getPointSum());
            // 保存积分兑换金额的比例 （消费规则）
            paramMap.put("pointSet", pointSet.getConsumption());
        }

        return paramMap;
    }

    /**
     * 查询最后加入的商品id
     *
     * @param response
     * @param request
     * @return
     */
    @Override
    public Long selectLastId(ShoppingCart shoppingCart, HttpServletResponse response, HttpServletRequest request) {
        try {
            ShoppingCart sc = shoppingCartMapper.selectShopingByParam(shoppingCart);
            if (sc != null) {
                Long customerId = (Long) request.getSession().getAttribute(CUSTOMERID);
                Map<String, Object> map = new HashMap<String, Object>();
                // 删除商品根据id和customerId，防止越权
                map.put("shoppingCartId", sc.getShoppingCartId());
                map.put(CUSTOMERID, customerId);
                shoppingCartMapper.delShoppingCartById(map);
            }
            long count = addShoppingCart(shoppingCart, request, response);
            if (count == 0) {
                return count;
            }
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("", e);
            Customer cust = (Customer) request.getSession().getAttribute("cust");
            OperaLogUtil.addOperaException(cust.getCustomerUsername(), e, request);
        }
        return shoppingCartMapper.selectLastId(shoppingCart);
    }

    /**
     * 限购
     *
     * @param goodsDetailBean 商品
     * @return
     */
    @Override
    public GoodsDetailBean forPurchasing(GoodsDetailBean goodsDetailBean, Long customerId) {
        Marketing marketing = marketService.marketingDetail(marketService.queryByCreatimeMarketings(goodsDetailBean.getProductVo().getGoodsInfoId(), 3L, goodsDetailBean
                .getProductVo().getGoods().getCatId(), goodsDetailBean.getBrand().getBrandId()));
        // 存在限购
        if (marketing != null) {
            Long stock = marketing.getLimitBuyMarketing().getLimitCount();

            // 最近是否购买过该商品
            Long num = orderser.selectGoodsInfoCount(goodsDetailBean.getProductVo().getGoodsInfoId(), customerId, marketing.getMarketingBegin());
            if (num != null) {
                stock = stock - num;
                if (stock < 0) {
                    stock = 0L;
                }
            }
            if (goodsDetailBean.getProductVo().getGoodsInfoStock() - stock >= 0) {
                // 如果存在限购，则把库存改为限购数量
                goodsDetailBean.getProductVo().setGoodsInfoStock(stock);
            }
        }
        return goodsDetailBean;
    }

    /**
     * 计算全国价格
     *
     * @param motheds
     * @param fe
     * @param num
     * @param weight
     * @return BigDecimal
     */
    public BigDecimal computeFreight(String motheds, FreightExpress fe, Integer num, BigDecimal weight) {
        BigDecimal price = new BigDecimal(0);
        if (num == 0) {
            return price;
        }
        // 计件
        if ("0".equals(motheds)) {
            // 如果购买的数量在首件之内
            if (num < Integer.parseInt(fe.getExpressStart().toString())) {
                // 价格 = 首件价格 + （ 总件 -首件）*续件价格
                int a = 0;
                BigDecimal temp = fe.getExpressPostageplus().multiply(new BigDecimal(a));
                price = fe.getExpressPostage().add(temp);
            } else {
                // 价格 = 首件价格 + （ 总件 -首件）*续件价格
                int a = num - Integer.parseInt(fe.getExpressStart().toString());
                BigDecimal temp = fe.getExpressPostageplus().multiply((new BigDecimal(a)).divide(new BigDecimal(fe.getExpressPlusN1()), 0, BigDecimal.ROUND_UP));
                price = fe.getExpressPostage().add(temp);
            }

            return price;
        } else {
            // 如果购买重量大于或者等与会
            if (weight.compareTo(new BigDecimal(fe.getExpressStart())) == -1) {
                // 计重 价格= 首重价格 + （总重-首重）*续重价格
                BigDecimal a = new BigDecimal(0);
                BigDecimal temp = fe.getExpressPostageplus().multiply(a);
                price = fe.getExpressPostage().add(temp);
            } else {
                // 计重 价格= 首重价格 + （总重-首重）*续重价格
                BigDecimal a = weight.subtract(new BigDecimal(fe.getExpressStart()));
                BigDecimal temp = fe.getExpressPostageplus().multiply(a.divide(new BigDecimal(fe.getExpressPlusN1()), 0, BigDecimal.ROUND_UP));
                price = fe.getExpressPostage().add(temp);
            }

            return price;
        }
    }

    /**
     * 计算其他地区价格
     *
     * @param motheds
     * @param frall
     * @param num
     * @param weight
     * @return BigDecimal
     */
    public BigDecimal computeFreightAll(String motheds, FreightExpressAll frall, Integer num, BigDecimal weight) {
        BigDecimal price = new BigDecimal(0);
        if (num == 0) {
            return price;
        }
        // 计件
        if ("0".equals(motheds)) {
            // 如果购买的数量在首件之内
            if (num < Integer.parseInt(frall.getExpressStart().toString())) {
                // 价格 = 首件价格 + （ 总件 -首件）*续件价格
                int a = 0;
                BigDecimal temp = frall.getExpressPostageplus().multiply(new BigDecimal(a));
                price = frall.getExpressPostage().add(temp);
            } else {
                // 价格 = 首件价格 + （ 总件 -首件）*续件价格
                int a = num - Integer.parseInt(frall.getExpressStart().toString());
                BigDecimal temp = frall.getExpressPostageplus().multiply((new BigDecimal(a)).divide(new BigDecimal(frall.getExpressPlusN1()), 0, BigDecimal.ROUND_UP));
                price = frall.getExpressPostage().add(temp);
            }

            return price;
        } else {
            // 如果购买重量大于或者等与会
            if (weight.compareTo(new BigDecimal(frall.getExpressStart())) == -1) {
                // 计重 价格= 首重价格 + （总重-首重）*续重价格
                BigDecimal a = new BigDecimal(0);
                BigDecimal temp = frall.getExpressPostageplus().multiply(a);
                price = frall.getExpressPostage().add(temp);
            } else {
                // 计重 价格= 首重价格 + （总重-首重）*续重价格
                BigDecimal a = weight.subtract(new BigDecimal(frall.getExpressStart()));
                BigDecimal temp = frall.getExpressPostageplus().multiply(a.divide(new BigDecimal(frall.getExpressPlusN1()), 0, BigDecimal.ROUND_UP));
                price = frall.getExpressPostage().add(temp);
            }

            return price;
        }

    }

    /**
     * 得到各个商家的运费
     *
     * @param thirdId
     * @param cityId
     * @param cartList
     * @return
     */

    @Override
    public BigDecimal getEverythirdExpressPrice(Long thirdId, Long cityId, List<ShoppingCart> cartList) {
        // 查询物流模板信息 根据thirdId 查询默认的模板
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("freightIsDefault", "1");
        paramMap.put("freightThirdId", thirdId);
        BigDecimal freightmoney = BigDecimal.ZERO;
        Integer goodsnum = 0;
        BigDecimal goodsweight = BigDecimal.valueOf(0);

        for (ShoppingCart sc : cartList) {
            if (sc.getThirdId().equals(thirdId)) {
                // 判断是否是套装
                if (sc.getFitId() == null) {
                    // 如果是普通商品，执行普通商品的方法
                    GoodsProductVo goodsProduct = goodsProductService.queryProductByProductId(sc.getGoodsInfoId());
                    goodsweight = goodsweight.add(goodsProduct.getGoodsInfoWeight().multiply(new BigDecimal(sc.getGoodsNum())));
                    goodsnum += Integer.parseInt(sc.getGoodsNum().toString());

                } else {
                    // 套装运费计算
                    // 获取此套装下的所有货品
                    List<GoodsProductVo> goodsProducts = goodsProductMapper.queryDetailByGroupId(sc.getFitId());
                    // 遍历套装下的商品
                    for (int j = 0; j < goodsProducts.size(); j++) {

                        goodsweight = goodsweight.add(goodsProducts.get(j).getGoodsInfoWeight().multiply(new BigDecimal(sc.getGoodsNum())));
                        goodsnum += Integer.parseInt(sc.getGoodsNum().toString());

                    }

                }
            }

        }
        // 获取默认模板
        FreightTemplate ft = freightTemplateMapper.selectFreightTemplateSubOrder(paramMap);

        if (ft != null) {
            // 查询默认全国设置
            List<FreightExpress> fe = freightExpressMapper.selectTemplateExpress(ft.getFreightTemplateId());

            // 如果全国设置不为空
            if (fe != null && !fe.isEmpty()) {
                for (int i = 0; i < fe.size(); i++) {

                    // 获取其他地区设置
                    List<FreightExpressAll> fall = fe.get(i).getFreightExpressAll();
                    // 其他地区设置不为空
                    if (fall != null && !fall.isEmpty()) {
                        for (int j = 0; j < fall.size(); j++) {
                            // 获取其他地区设置
                            String area = fall.get(j).getExpressArea();
                            String[] cityIds = area.split(",");
                            // 标识
                            int flag = 0;
                            // 判断是否存在此城市单独设置
                            for (String ciId : cityIds) {
                                if (ciId.equals(cityId.toString())) {
                                    flag = 1;
                                    break;
                                }

                            }

                            if (flag == 1) {

                                freightmoney = computeFreightAll(ft.getFreightMethods(), fall.get(j), goodsnum, goodsweight);
                                break;
                            }
                            // 如果上述判断没有返回return
                            // 那么去默认全国设置
                            freightmoney = computeFreight(ft.getFreightMethods(), fe.get(i), goodsnum, goodsweight);

                        }

                    } else {
                        // 如果默认其他地区没有
                        freightmoney = computeFreight(ft.getFreightMethods(), fe.get(i), goodsnum, goodsweight);
                    }

                }

            }

        }

        return freightmoney;
    }

    /**
     * 参数是需要运费购物车id 第三方id根据第三方来分组得到各个商家的运费-
     *
     * @param cityId
     * @return
     */

    @Override
    public Map<String, Object> getNewExpressPrice(Long cityId, List<Long> cartIds, String vip) {
        List<ShoppingCart> shopdata = shoppingCartMapper.shopCartListByIds(cartIds);
        // List<ShoppingCart> shopthird = new ArrayList<>();
        Map<Long, Object> thirdMap = new HashMap<>();
        BigDecimal freightmoney = BigDecimal.ZERO;
        // boss运费为上门自提用
        BigDecimal bossfreight = BigDecimal.ZERO;
        // 得到没有包邮或者没有达到条件的购物车集合
        List<ShoppingCart> cartList = getNobaoyouShoppingcarts(shopdata, vip);
        if (CollectionUtils.isNotEmpty(cartList)) {

            // 商家id集合
            for (ShoppingCart sh : cartList) {
                if (sh.getThirdId() != null) {
                    thirdMap.put(sh.getThirdId(), "");

                } else {
                    if (sh.getFitId() != null) {
                        // 如果商品是套装
                        GoodsGroupVo goodsGroupVo = goodsGroupService.queryVoByPrimaryKey(sh.getFitId());
                        sh.setThirdId(goodsGroupVo.getThirdId());
                        thirdMap.put(goodsGroupVo.getThirdId(), "");
                    }
                }
            }
            for (Long thirdId : thirdMap.keySet()) {
                freightmoney = freightmoney.add(getEverythirdExpressPrice(thirdId, cityId, cartList));
                if (thirdId == 0) {
                    bossfreight = bossfreight.add(getEverythirdExpressPrice(thirdId, cityId, cartList));
                }
            }

        }
        Map<String, Object> freightMap = new HashMap<>();
        // 总运费
        freightMap.put("freightmoney", freightmoney);
        // boss平台运费
        freightMap.put("bossfreight", bossfreight);
        return freightMap;

    }

    /**
     * 根据购物车里面的货品查询是否存在包邮的促销活动 返回list集合,不包邮的购物车
     *
     * @param cartList
     * @return
     * @author jiaodongzhi
     */
    @Override
    public List<ShoppingCart> getNobaoyouShoppingcarts(List<ShoppingCart> cartList, String vip) {
        // 不包邮购物车或者参与包邮活动但是没有达到条件
        List<ShoppingCart> nobaoyou = new ArrayList<>();
        Marketing marketing = null;
        if (CollectionUtils.isNotEmpty(cartList)) {
            Map<String, Object> map = new HashMap<>();
            // 参与包邮,生成新的购物车
            List<ShoppingCart> baoyou = new ArrayList<>();
            for (int i = 0; i < cartList.size(); i++) {
                if (cartList.get(i) != null && cartList.get(i).getGoodsInfoId() != null) {
                    // 从购物车里得到商品id,和包邮促销类型重新从数据库查询,防止当前(包邮促销)已经过期;
                    map.put(GOODSID, cartList.get(i).getGoodsInfoId());
                    map.put("codeType", "12");
                    marketing = marketingMapper.queryMarketingByGoodIdAndtype(map);
                    // 包邮
                    if (null != marketing) {
                        cartList.get(i).setMarketing(marketing);
                        baoyou.add(cartList.get(i));
                    } else {
                        // 购物车里的包邮促销过期或者没有参与包邮促销
                        nobaoyou.add(cartList.get(i));
                    }

                }
            }
            // 存放第三方id
            Map<Long, String> groups = new HashMap<Long, String>();
            // 根据第三方分组购物车 boss商品第三方id为0
            List<List<ShoppingCart>> shopThirdList = new ArrayList<>();
            Long thirdId = null;
            if (CollectionUtils.isNotEmpty(baoyou)) {
                for (ShoppingCart pd : baoyou) {
                    thirdId = pd.getThirdId();
                    groups.put(thirdId, "");
                }
                List<ShoppingCart> scart = null;
                if (groups != null && !groups.isEmpty()) {
                    for (Long rawTypeId : groups.keySet()) {
                        scart = new ArrayList<>();
                        for (ShoppingCart sc : baoyou) {
                            if (rawTypeId.equals(sc.getThirdId())) {
                                // 根据thirdId分组
                                scart.add(sc);
                            }
                        }
                        shopThirdList.add(scart);
                    }
                }
                BigDecimal aftermoney = BigDecimal.valueOf(0);
                BigDecimal afterVipMoney = BigDecimal.valueOf(0);
                Long countgoods = 0L;
                List<ShoppingCart> shop = new ArrayList<>();
                for (int m = 0; m < shopThirdList.size(); m++) {
                    // 按third分组判断是否包邮
                    baoyou = shopThirdList.get(m);
                    ShoppingCart sc = new ShoppingCart();
                    for (int j = 0; j < baoyou.size(); j++) {
                        GoodsProduct goodsProduct = goodsProductMapper.queryByGoodsInfoDetail(baoyou.get(j).getGoodsInfoId());
                        BigDecimal goodsMoney = goodsProduct.getGoodsInfoPreferPrice();
                        BigDecimal goodsVipMoney = goodsProduct.getGoodsInfoVipPrice();
                        Map<String, Object> mapGoods = new HashMap<String, Object>();
                        // 从购物车里得到促销id重新从数据库查询,防止当前(折扣促销)已经过期;
                        Marketing mark = marketingMapper.marketingDetail(baoyou.get(j).getMarketingId());
                        // 参与折扣促销
                        if (null != mark) {
                            mapGoods.put(MARKETINGID, mark.getMarketingId());
                            mapGoods.put(GOODSID, baoyou.get(j).getGoodsInfoId());
                            // 折扣促销详细信息
                            PreDiscountMarketing preDiscountMarketing = preDiscountMarketingMapper.selectByMarketId(mapGoods);
                            goodsMoney = preDiscountMarketing.getDiscountPrice();
                            goodsVipMoney = preDiscountMarketing.getVipdiscountPrice();
                        }

                        countgoods = baoyou.get(j).getGoodsNum();
                        // 购物车里商品的总价格
                        aftermoney = aftermoney.add(goodsMoney.multiply(BigDecimal.valueOf(countgoods)));
                        afterVipMoney = afterVipMoney.add(goodsVipMoney.multiply(BigDecimal.valueOf(countgoods)));
                        if ("1".equals(vip)) {
                            sc.setMarketgoodsPrice(afterVipMoney);
                        } else {
                            sc.setMarketgoodsPrice(aftermoney);
                        }
                        sc.setThirdId(baoyou.get(j).getThirdId());
                        shop.add(sc);
                    }
                    // 分组后的价格

                    // Map<String, Object> app = new HashMap<>();
                    for (int k = 0; k < baoyou.size(); k++) {
                        // 计算第三方分组后以及参与包邮分组
                        for (ShoppingCart scra : shop) {
                            if ("1".equals(vip)) {
                                if (scra.getThirdId().equals(baoyou.get(k).getThirdId()) && scra.getMarketgoodsPrice().compareTo(baoyou.get(k).getMarketing().getVipShippingMoney()) == -1) {
                                    // 参与包邮但没有达到条件包邮
                                    nobaoyou.add(baoyou.get(k));
                                }
                            } else {
                                if (scra.getThirdId().equals(baoyou.get(k).getThirdId()) && scra.getMarketgoodsPrice().compareTo(baoyou.get(k).getMarketing().getShippingMoney()) == -1) {
                                    // 参与包邮但没有达到条件包邮
                                    nobaoyou.add(baoyou.get(k));
                                }
                            }
                        }
                    }

                }

            }
        }
        return nobaoyou;

    }

    /**
     * 获取购物车的货品的总数
     *
     * @param
     * @return
     */
    @Override
    public int getShoppingCartGoodsSum(HttpServletRequest request) {
        int totleCount = 0;
        Long customerId = (Long) request.getSession().getAttribute("customerId");
        //未登录
        if (customerId == null) {
            List<ShopCarUtil> list = null;
            try {
                list = loadCookShopCar(request);
                if (list != null && !list.isEmpty()) {
                    List<Object> shoplist = new ArrayList<Object>();
                    for (int i = 0; i < list.size(); i++) {// list.size();
                        ShoppingCart sc = new ShoppingCart();
                        totleCount += list.get(i).getGoodsNum();
                    }
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return 0;
            }
        } else {
            //已登录
            Integer coun = shoppingCartMapper.shoppingCartGoodsSum(customerId);
           totleCount = coun==null?0:coun;
        }
        return totleCount;
    }

    /**
     * 添加第三方购物车（第三方不参与促销活动）
     * 2016-01-28 wuyanbo add
     * @param shoppingCart
     * @return int
     * @throws UnsupportedEncodingException
     */
    @Override
    @Transactional
    public int addThirdShoppingCart(ShoppingCart shoppingCart, Long custId) throws UnsupportedEncodingException {
        int resultVal = 0;
        if (null != custId) {
            //查询当前用户所有有效购物车的商品货品数量
            /*int sum = shoppingCartMapper.selectSumByCustomerId(custId);
            if (sum >= 20) {
                return 0;
            }*/
            shoppingCart.setCustomerId(custId);
            shoppingCart.setDelFlag("0");
            shoppingCart.setShoppingCartTime(new Date());
            //获取当前购物车的商品数量
            int count = shoppingCartMapper.selectCountByReady(shoppingCart);

            if (count == 0) {
                resultVal = shoppingCartMapper.addShoppingCart(shoppingCart);
            } else {
                ShoppingCart sc = shoppingCartMapper.selectShopingByParam(shoppingCart);

                resultVal = shoppingCartMapper.updateShoppingCart(shoppingCart);
            }
        }

        return resultVal;
    }

    /**
     * 查询第三方接口购物车内容
     * 2016-01-28 wuyanbo add
     * @return
     */
    @Override
    public PageBean selectThirdShoppingCart(ShoppingCartWareUtil cartWareUtil, PageBean pb, Long custId) {
        // 如果已经登录
        if (custId != null) {
            pb.setUrl("myshoppingcart");
            pb.setPageSize(20);
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put(CUSTOMERID, custId);
            Integer totalCount = shoppingCartMapper.shoppingCartCount(paramMap);
            pb.setRows(Integer.parseInt(totalCount == null ? 0 + "" : totalCount + ""));
            if (pb.getPageNo() > pb.getLastPageNo()) {
                pb.setPageNo(pb.getLastPageNo());
            }
            if (pb.getPageNo() == 0) {
                pb.setPageNo(1);
            }
            paramMap.put(CustomerConstantStr.STARTNUM, pb.getStartRowNum());
            paramMap.put(CustomerConstantStr.ENDNUM, pb.getEndRowNum());
            List<Object> shoplist = shoppingCartMapper.shoppingCart(paramMap);
            if (shoplist != null && !shoplist.isEmpty()) {
                for (int i = 0; i < shoplist.size(); i++) {
                    try {
                        try {
                            // 进入此处标示该购物车为商品，执行商品方法
                            GoodsDetailBean goodsDetailBean = goodsProductService.queryDetailBeanByProductId(((ShoppingCart) shoplist.get(i)).getGoodsInfoId(),
                                    cartWareUtil.getDistrictId());
                            if (goodsDetailBean == null) {
                                continue;
                            }
                            ((ShoppingCart) shoplist.get(i)).setGoodsDetailBean(goodsDetailBean);
                        } catch (Exception e) {
                            LOGGER.error("", e);
                            Map<String, Object> map = new HashMap<String, Object>();
                            // 删除商品根据id和customerId，防止越权
                            map.put("shoppingCartId", ((ShoppingCart) shoplist.get(i)).getShoppingCartId());
                            map.put(CUSTOMERID, custId);
                            shoppingCartMapper.delShoppingCartById(map);
                        }
                    } catch (Exception e) {
                        LOGGER.error("", e);
                        Map<String, Object> map = new HashMap<String, Object>();
                        // 删除商品根据id和customerId，防止越权
                        map.put("shoppingCartId", ((ShoppingCart) shoplist.get(i)).getShoppingCartId());
                        map.put(CUSTOMERID, custId);
                        // 如果商品已经从数据库中删除，则把该商品从购物车内删除
                        shoppingCartMapper.delShoppingCartById(map);
                    }

                }
            }
            pb.setList(shoplist);

        }
        return pb;
    }
}
