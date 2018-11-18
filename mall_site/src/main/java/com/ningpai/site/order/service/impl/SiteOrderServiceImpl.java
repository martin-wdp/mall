/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.site.order.service.impl;

import com.ningpai.common.bean.Sms;
import com.ningpai.common.dao.SmsMapper;
import com.ningpai.common.kuaidi.KuaiDiUtil;
import com.ningpai.coupon.bean.Coupon;
import com.ningpai.coupon.bean.CouponNo;
import com.ningpai.coupon.service.CouponNoService;
import com.ningpai.coupon.service.CouponService;
import com.ningpai.customer.bean.Customer;
import com.ningpai.customer.bean.CustomerAddress;
import com.ningpai.customer.bean.CustomerPoint;
import com.ningpai.customer.bean.CustomerPointLevel;
import com.ningpai.customer.dao.CustomerPointLevelMapper;
import com.ningpai.freighttemplate.bean.Express;
import com.ningpai.freighttemplate.bean.FreightExpress;
import com.ningpai.freighttemplate.bean.FreightTemplate;
import com.ningpai.freighttemplate.dao.ExpressInfoMapper;
import com.ningpai.freighttemplate.dao.FreightExpressMapper;
import com.ningpai.freighttemplate.dao.FreightTemplateMapper;
import com.ningpai.freighttemplate.dao.SysLogisticsCompanyMapper;
import com.ningpai.freighttemplate.service.FreightTemplateService;
import com.ningpai.gift.bean.Gift;
import com.ningpai.goods.bean.ProductWare;
import com.ningpai.goods.bean.WareHouse;
import com.ningpai.goods.dao.ProductWareMapper;
import com.ningpai.goods.service.GoodsGroupService;
import com.ningpai.goods.service.ProductWareService;
import com.ningpai.goods.util.CalcStockUtil;
import com.ningpai.goods.vo.GoodsGroupReleProductVo;
import com.ningpai.goods.vo.GoodsGroupVo;
import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.marketing.bean.*;
import com.ningpai.marketing.dao.GrouponMapper;
import com.ningpai.marketing.dao.PreDiscountMarketingMapper;
import com.ningpai.marketing.dao.RushCustomerMapper;
import com.ningpai.marketing.service.MarketingService;
import com.ningpai.order.bean.*;
import com.ningpai.order.dao.*;
import com.ningpai.order.service.OrderService;
import com.ningpai.order.service.SynOrderService;
import com.ningpai.other.util.CustomerConstantStr;
import com.ningpai.salesman.service.CustomerRelaSalesmanService;
import com.ningpai.site.customer.service.CustomerServiceInterface;
import com.ningpai.site.customer.util.OrderContainerUtil;
import com.ningpai.site.customer.vo.CustomerAllInfo;
import com.ningpai.site.directshop.bean.DirectShop;
import com.ningpai.site.directshop.service.DirectShopService;
import com.ningpai.site.goods.dao.GoodsProductMapper;
import com.ningpai.site.goods.service.GoodsProductService;
import com.ningpai.site.goods.vo.GoodsProductVo;
import com.ningpai.site.login.service.LoginService;
import com.ningpai.site.order.service.SiteOrderService;
import com.ningpai.site.shoppingcart.bean.ShoppingCart;
import com.ningpai.site.shoppingcart.bean.ShoppingCartWareUtil;
import com.ningpai.site.shoppingcart.dao.ShoppingCartMapper;
import com.ningpai.site.shoppingcart.service.ShoppingCartService;
import com.ningpai.system.bean.DeliveryPoint;
import com.ningpai.system.bean.PointSet;
import com.ningpai.system.service.BasicSetService;
import com.ningpai.system.service.DeliveryPointService;
import com.ningpai.system.service.IExpressConfBiz;
import com.ningpai.system.service.impl.ExpressConfBizImpl;
import com.ningpai.util.MyLogger;
import com.ningpai.util.UtilDate;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;

/**
 * @author ggn
 */
@Service("SiteOrderService")
public class SiteOrderServiceImpl extends BasicSqlSupport implements SiteOrderService {

    private static final String SUMOLDPRICE = "sumOldPrice";
    private static final String SUMPRICE = "sumPrice";
    private static final String STOCK = "stock";
    private static final String CUSTOMERID = "customerId";
    private static final String IS_TEMP_CUST = "is_temp_cust";
    private static final String PASSWORD = "000000";
    /**
     * 记录日志对象
     */
    private static final MyLogger LOGGER = new MyLogger(SiteOrderServiceImpl.class);
    private ShoppingCartService shoppingCartService;
    private ProductWareMapper productWareMapper;
    private OrderContainerRelationMapper relationMapper;
    private IExpressConfBiz iExpressConfBiz;
    private GoodsProductService siteGoodsProductService;
    private ProductWareService productWareService;
    @Resource(name = "FreightTemplateMapper")
    private FreightTemplateMapper freightTemplateMapper;
    private com.ningpai.goods.service.GoodsProductService goodsProductService;
    @Resource(name = "FreightTemplateService")
    private FreightTemplateService freightTemplateService;
    @Resource(name = "SysLogisticsCompanyMapper")
    private SysLogisticsCompanyMapper sysLogisticsCompanyMapper;
    @Resource(name = "FreightExpressMapper")
    private FreightExpressMapper freightExpressMapper;
    @Resource(name = "ShoppingCartMapper")
    private ShoppingCartMapper shoppingCartMapper;
    @Resource(name = "GrouponMapper")
    private GrouponMapper grouponMapper;
    @Resource(name = "PreDiscountMarketingMapper")
    private PreDiscountMarketingMapper preDiscountMarketingMapper;
    @Resource(name = "expressInfoMapperThird")
    private ExpressInfoMapper expressInfoMapperThird;
    @Resource(name = "RushCustomerMapper")
    private RushCustomerMapper rushCustomerMapper;
    @Resource(name = "customerRelaSalesmanService")
    private CustomerRelaSalesmanService qpCustomerRelaSalesmanService;

    /**
     * 自提点
     */
    @Resource(name = "DeliveryPointService")
    private DeliveryPointService deliveryPointService;
    @Resource(name = "loginServiceSite")
    private LoginService loginService;
    private CouponService couponService;
    private CouponNoService couponNoService;
    private OrderGoodsInfoGiftMapper orderGoodsInfoGiftMapper;
    private OrderGoodsInfoCouponMapper orderGoodsInfoCouponMapper;
    private OrderGoodsMapper orderGoodsMapper;
    private OrderExpressMapper orderExpressMapper;
    private OrderService orderService;
    private CustomerServiceInterface customerServiceInterface;
    private ExpressConfBizImpl expressConfBizImpl;
    @Resource(name = "smsMapperSite")
    private SmsMapper mapper;
    @Resource(name = "SynOrderService")
    private SynOrderService synOrderService;
    @Resource(name = "MarketingService")
    private MarketingService marketService;
    @Resource(name = "customerPointLevelMapper")
    private CustomerPointLevelMapper customerPointLevelMapper;
    @Resource(name = "GoodsGroupService")
    private GoodsGroupService goodsGroupService;
    @Resource(name = "HsiteGoodsProductMapper")
    private GoodsProductMapper goodsProductMapper;
    @Resource(name = "basicSetService")
    private BasicSetService basicService;
    @Resource(name = "SiteDirectShopService")
    private DirectShopService directShopService;

    /**
     * 删除单个退单信息
     *
     * @param backOrderId 退单Id
     * @param customerId  当前会员ID
     * @return
     */
    @Override
    public int deleteBackOrderById(Long backOrderId, Long customerId) {
        return orderService.deleteBackOrderById(backOrderId, customerId);
    }

    /**
     * 得到总的优惠金额,以及总金额
     *
     * @param shopCartIds
     * @param distinctId
     * @return
     */
    @Override
    public Map<String, Object> getEveryparamMap(HttpServletRequest requests, Long[] shopCartIds, Long distinctId) {
        Object object = requests.getSession().getAttribute("vip");
        String vip = "0";
        if (null != object) {
            vip = (String) object;
        }

        List<ShoppingCart> shoplist = shoppingCartMapper.shopCartListByIds(Arrays.asList(shopCartIds));
        Map<String, Object> paramMap = new HashMap<>();
        for (ShoppingCart sp : shoplist) {
            if (sp.getFitId() != null) {
                // 如果商品是套装
                GoodsGroupVo goodsGroupVo = goodsGroupService.queryVoByPrimaryKey(sp.getFitId());
                sp.setThirdId(goodsGroupVo.getThirdId());
            }
        }

        // 交易总金额
        BigDecimal sumPrice = BigDecimal.valueOf(0);
        BigDecimal sumVipPrice = BigDecimal.valueOf(0);
        // 原始总金额
        BigDecimal sumOldPrice = BigDecimal.valueOf(0);
        BigDecimal sumOldVipPrice = BigDecimal.valueOf(0);
        // 中间变量
        BigDecimal flag = BigDecimal.ZERO;
        BigDecimal flagVip = BigDecimal.ZERO;
        // 优惠金额
        BigDecimal prePrice = BigDecimal.valueOf(0);
        BigDecimal preVipPrice = BigDecimal.valueOf(0);
        // boss总金额
        BigDecimal bossSumPrice = BigDecimal.ZERO;
        BigDecimal bossSumVipPrice = BigDecimal.ZERO;
        // 套装优惠金额
        BigDecimal taozhuan = BigDecimal.ZERO;
        BigDecimal taozhuanVip = BigDecimal.ZERO;
        // 第三方id的map
        Map<Long, Object> thirdIdMap = new HashMap<>();
        Map<String, Object> para = new HashMap<>();
        if (CollectionUtils.isNotEmpty(shoplist)) {
            for (int i = 0; i < shoplist.size(); i++) {
                if (shoplist.get(i).getFitId() == null) {

                    thirdIdMap.put(shoplist.get(i).getThirdId(), "");
                    // 查询商品详细
                    shoplist.get(i).setGoodsDetailBean(siteGoodsProductService.querySimpleDetailBeanWithWareByProductId(((ShoppingCart) shoplist.get(i)).getGoodsInfoId(), distinctId));
                    // 查询购物车里选择的促销信息
                    /*shoplist.get(i).setMarketing(marketService.marketingDetail(shoplist.get(i).getMarketingActivityId(), shoplist.get(i).getGoodsInfoId()));*/
                    // 2015-12-10 wuyanbo 修改促销信息
                    if(shoplist.get(i).getGoodsGroupId() != null && shoplist.get(i).getGoodsGroupId() != 0){//团购
                        shoplist.get(i).setMarketing(marketService.marketingDetail(shoplist.get(i).getGoodsGroupId(), shoplist.get(i).getGoodsInfoId()));
                    }else if(shoplist.get(i).getMarketingActivityId() != null && shoplist.get(i).getMarketingActivityId() != 0){//抢购、满减、满折、直降
                        shoplist.get(i).setMarketing(marketService.marketingDetail(shoplist.get(i).getMarketingActivityId(), shoplist.get(i).getGoodsInfoId()));
                    }else if(shoplist.get(i).getMarketingId() != null){//折扣
                        shoplist.get(i).setMarketing(marketService.marketingDetail(shoplist.get(i).getMarketingId(), shoplist.get(i).getGoodsInfoId()));
                    }

                } else {
                    // 如果商品是套装
                    GoodsGroupVo goodsGroupVo = goodsGroupService.queryVoByPrimaryKey(((ShoppingCart) shoplist.get(i)).getFitId());
                    if (null != goodsGroupVo) {

                        shoplist.get(i).setGoodsGroupVo(goodsGroupVo);
                        // 该套装下所有的商品
                        List<GoodsGroupReleProductVo> goodsGroupReleProductVos = ((ShoppingCart) shoplist.get(i)).getGoodsGroupVo().getProductList();
                        // 获取此套装下的所有货品
                        for (int j = 0; j < goodsGroupReleProductVos.size(); j++) {
                            // 原总金额加上套装优惠前费用
                            sumOldPrice = sumOldPrice.add(goodsGroupReleProductVos.get(j).getProductDetail().getGoodsInfoPreferPrice()
                                    .multiply(BigDecimal.valueOf(shoplist.get(i).getGoodsNum())));
                            flag = flag.add(goodsGroupReleProductVos.get(j).getProductDetail().getGoodsInfoPreferPrice()
                                    .multiply(BigDecimal.valueOf(shoplist.get(i).getGoodsNum())));

                            sumOldVipPrice = sumOldVipPrice.add(goodsGroupReleProductVos.get(j).getProductDetail().getGoodsInfoVipPrice()
                                    .multiply(BigDecimal.valueOf(shoplist.get(i).getGoodsNum())));
                            flagVip = flagVip.add(goodsGroupReleProductVos.get(j).getProductDetail().getGoodsInfoVipPrice()
                                    .multiply(BigDecimal.valueOf(shoplist.get(i).getGoodsNum())));
                        }

                        // 得到套装优惠费用
                        taozhuan = taozhuan.add(BigDecimal.valueOf(shoplist.get(i).getGoodsNum()).multiply(goodsGroupVo.getGroupPreferamount()));
                        taozhuanVip = taozhuanVip.add(BigDecimal.valueOf(shoplist.get(i).getGoodsNum()).multiply(goodsGroupVo.getGroupPreferamount()));
                    }
                }
            }
            Long goodssum = 0L;

            BigDecimal goodsprice = BigDecimal.ZERO;
            BigDecimal totalprice = BigDecimal.ZERO;

            BigDecimal goodsVipprice = BigDecimal.ZERO;
            BigDecimal totalVipprice = BigDecimal.ZERO;
            String discountFlag = "";
            for (int v = 0; v < shoplist.size(); v++) {
                if (shoplist.get(v).getFitId() == null) {

                    // 货品价格
                    goodsprice = shoplist.get(

                            v).getGoodsDetailBean().getProductVo().getGoodsInfoPreferPrice();
                    goodsVipprice = shoplist.get(v).getGoodsDetailBean().getProductVo().getGoodsInfoVipPrice();

                    //价格中间变量
                    BigDecimal goodsPriceflag = shoplist.get(v).getGoodsDetailBean().getProductVo().getGoodsInfoPreferPrice();
                    BigDecimal goodsVipPriceflag = shoplist.get(v).getGoodsDetailBean().getProductVo().getGoodsInfoVipPrice();
                    DecimalFormat myformat = null;
                    myformat = new DecimalFormat("0.00");
                    //单该货品同时参与了团购和折扣时,优先级团购优先
                    if (shoplist.get(v).getGoodsGroupId() != null) {
                        // 从购物车里得到促销id重新从数据库查询,防止当前(团购促销)已经过期;
                        Marketing mark = marketService.querySimpleMarketingById(shoplist.get(v).getGoodsGroupId());
                        if (mark != null) {
                            Groupon groupon = grouponMapper.selectByMarketId(mark.getMarketingId());
                            if (groupon != null) {
                                goodsprice = groupon.getGrouponPrice();
                                goodsVipprice = groupon.getGrouponVipPrice();
                            }
                        }
                    }
                    // 折扣、直降促销
                    if (shoplist.get(v).getGoodsGroupId() == null && shoplist.get(v).getMarketingId() != null && 0 != shoplist.get(v).getMarketingId()) {

                        // 从购物车里得到促销id重新从数据库查询,防止当前(折扣促销)已经过期;
                        Marketing mark = marketService.querySimpleMarketingById(shoplist.get(v).getMarketingId());
                        if (mark != null) {
                            para.put("marketingId", mark.getMarketingId());
                            para.put("goodsId", shoplist.get(v).getGoodsInfoId());
                            PreDiscountMarketing premark = preDiscountMarketingMapper.selectByMarketId(para);
                            if (premark != null) {
                                // 货品价格
                                goodsprice = goodsPriceflag.multiply(premark.getDiscountInfo());
                                goodsVipprice = goodsVipPriceflag.multiply(premark.getVipdiscountInfo());
                                discountFlag = premark.getDiscountFlag();
                            }

                        }
                        // 抹掉分
                        if ("1".equals(discountFlag)) {
                            myformat = new DecimalFormat("0.0");
                        } else if ("2".equals(discountFlag)) {
                            myformat = new DecimalFormat("0");
                        } else {
                            myformat = new DecimalFormat("0.00");
                        }

                    }
                    // 不四舍五入
                    myformat.setRoundingMode(RoundingMode.FLOOR);
                    goodsprice = BigDecimal.valueOf(Double.valueOf(myformat.format(goodsprice)));
                    goodsVipprice = BigDecimal.valueOf(Double.valueOf(myformat.format(goodsVipprice)));
                    shoplist.get(v).getGoodsDetailBean().getProductVo().setGoodsInfoPreferPrice(goodsprice);
                    shoplist.get(v).getGoodsDetailBean().getProductVo().setGoodsInfoVipPrice(goodsVipprice);
                    // 货品购买件数
                    goodssum = shoplist.get(v).getGoodsNum();
                    // 计算boss价格页面计算用
                    if (shoplist.get(v).getThirdId() == 0) {
                        bossSumPrice = bossSumPrice.add(goodsprice.multiply(BigDecimal.valueOf(goodssum)));
                        bossSumVipPrice = bossSumVipPrice.add(goodsVipprice.multiply(BigDecimal.valueOf(goodssum)));
                    }
                    // 计算原始总金额
                    sumOldPrice = sumOldPrice.add(goodsprice.multiply(BigDecimal.valueOf(goodssum)));
                    flag = flag.add(goodsprice.multiply(BigDecimal.valueOf(goodssum)));
                    sumOldVipPrice = sumOldVipPrice.add(goodsVipprice.multiply(BigDecimal.valueOf(goodssum)));
                    flagVip = flagVip.add(goodsVipprice.multiply(BigDecimal.valueOf(goodssum)));
                }

            }
            goodssum = 0L;
            goodsprice = BigDecimal.ZERO;
            totalprice = BigDecimal.ZERO;
            goodsVipprice = BigDecimal.ZERO;
            totalVipprice = BigDecimal.ZERO;
            BigDecimal marketflag = BigDecimal.ZERO;
            BigDecimal marketflagVip = BigDecimal.ZERO;
            List<ShoppingCart> cartList = null;
            for (Long thirdId : thirdIdMap.keySet()) {
                // 根据第三方id分组得到新的购物车集合
                cartList = new ArrayList<>();
                for (ShoppingCart sc : shoplist) {
                    if (sc.getFitId() == null && thirdId.equals(sc.getThirdId())) {
                        cartList.add(sc);
                    }
                }
                // 促销分组
                Map<Long, Object> markMap = new HashMap<>();
                for (ShoppingCart sc : cartList) {
                    if (sc.getThirdId().equals(thirdId) && sc.getMarketingActivityId() != null) {
                        markMap.put(sc.getMarketingActivityId(), sc.getMarketing());
                    }
                }
                // 各个同一商家的促销总价
                Map<Long, Object> priceMap = new HashMap<>();
                Map<Long, Object> vipPriceMap = new HashMap<>();
                for (Long obd : markMap.keySet()) {
                    // 计算各个商家不同促销的总价格
                    for (ShoppingCart car : cartList) {
                        if (obd.equals(car.getMarketingActivityId()) && car.getThirdId().equals(thirdId)) {
                            if (null != distinctId && distinctId > 0) {
                                totalprice = totalprice.add(BigDecimal.valueOf(car.getGoodsNum()).multiply(car.getGoodsDetailBean().getProductVo().getGoodsInfoPreferPrice()));
                                totalVipprice = totalVipprice.add(BigDecimal.valueOf(car.getGoodsNum()).multiply(car.getGoodsDetailBean().getProductVo().getGoodsInfoVipPrice()));
                            }
                            priceMap.put(obd, totalprice);
                            vipPriceMap.put(obd, totalVipprice);
                        }
                    }
                    // 循环结束促销价格置零
                    totalprice = BigDecimal.ZERO;
                    totalVipprice = BigDecimal.ZERO;
                }
                for (Long ob : markMap.keySet()) {
                    Marketing mark = (Marketing) markMap.get(ob);
                    if (mark != null) {

                        for (Long markId : priceMap.keySet()) {
                            if (markId.equals(ob)) {
                                // map 封装各个商家促销的价格
                                totalprice = (BigDecimal) priceMap.get(markId);
                                totalVipprice = (BigDecimal) vipPriceMap.get(markId);
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
                                        if (fpm.getVipFullPrice().compareTo(totalVipprice) == -1 || fpm.getVipFullPrice().compareTo(totalVipprice) == 0) {
                                            preVipPrice = fpm.getVipReducePrice();
                                            // 把最大值付给marketfalg
                                            if (preVipPrice.compareTo(marketflagVip) == 1 || preVipPrice.compareTo(marketflagVip) == 0) {
                                                marketflagVip = preVipPrice;
                                            }
                                        }
                                    }
                                    prePrice = marketflag;
                                    marketflag = BigDecimal.ZERO;
                                    preVipPrice = marketflagVip;
                                    marketflagVip = BigDecimal.ZERO;
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
                                        if (fdm.getVipFullPrice().compareTo(totalVipprice) == -1 || fdm.getVipFullPrice().compareTo(totalVipprice) == 0) {
                                            preVipPrice = (BigDecimal.valueOf(1).subtract(fdm.getVipFullbuyDiscount())).multiply(totalVipprice);
                                            // 把最大值付给marketfalg
                                            if (preVipPrice.compareTo(marketflagVip) == 1 || preVipPrice.compareTo(marketflagVip) == 0) {
                                                marketflagVip = preVipPrice;
                                            }
                                        }
                                    }
                                    prePrice = marketflag;
                                    marketflag = BigDecimal.ZERO;
                                    preVipPrice = marketflagVip;
                                    marketflagVip = BigDecimal.ZERO;
                                }

                                // 实际付款金额
                                flag = flag.subtract(prePrice);
                                prePrice = BigDecimal.ZERO;

                                flagVip = flagVip.subtract(preVipPrice);
                                preVipPrice = BigDecimal.ZERO;
                            }
                        }
                    }
                }

                for (int k = 0; k < cartList.size(); k++) {
                    // 直降
                    if (cartList.get(k).getMarketing() != null && cartList.get(k).getMarketing().getPriceOffMarketing() != null) {
                        // sumOldPrice =
                        // sumOldPrice.subtract(cartList.get(k).getMarketing().getPriceOffMarketing().getOffValue
                        // ().multiply(BigDecimal.valueOf(cartList.get(k).getGoodsNum())));
                        flag = flag.subtract(cartList.get(k).getMarketing().getPriceOffMarketing().getOffValue().multiply(BigDecimal.valueOf(cartList.get(k).getGoodsNum())));
                        flagVip = flagVip.subtract(cartList.get(k).getMarketing().getPriceOffMarketing().getOffVipValue().multiply(BigDecimal.valueOf(cartList.get(k).getGoodsNum())));
                    }
                    //团购
                    if (cartList.get(k).getMarketing() != null && cartList.get(k).getMarketing().getGroupon() != null) {
                        /*flag = flag.subtract((BigDecimal.ONE.subtract(cartList.get(k).getMarketing().getGroupon().getGrouponDiscount())).multiply(cartList.get(k).getGoodsDetailBean().getProductVo().getGoodsInfoPreferPrice()).multiply(BigDecimal.valueOf(cartList.get(k).getGoodsNum())));*/
                        // 2015-12-09 wuyanbo 修改团购价计算方式
                        flag = flag.subtract((cartList.get(k).getGoodsDetailBean().getProductVo().getGoodsInfoPreferPrice().subtract(cartList.get(k).getMarketing().getGroupon().getGrouponPrice())).multiply(BigDecimal.valueOf(cartList.get(k).getGoodsNum())));
                        flagVip = flagVip.subtract((cartList.get(k).getGoodsDetailBean().getProductVo().getGoodsInfoVipPrice().subtract(cartList.get(k).getMarketing().getGroupon().getGrouponVipPrice())).multiply(BigDecimal.valueOf(cartList.get(k).getGoodsNum())));
                        sumOldPrice = flag;
                        sumOldVipPrice = flagVip;
                    }
                    //抢购
                    if (cartList.get(k).getMarketing() != null && cartList.get(k).getMarketing().getRushs() != null && !cartList.get(k).getMarketing().getRushs().isEmpty()) {
                        /*flag = flag.subtract((BigDecimal.ONE.subtract(cartList.get(k).getMarketing().getRushs().get(0).getRushDiscount())).multiply(cartList.get(k).getGoodsDetailBean().getProductVo().getGoodsInfoPreferPrice()).multiply(BigDecimal.valueOf(cartList.get(k).getGoodsNum())));
                        flagVip = flagVip.subtract((BigDecimal.ONE.subtract(cartList.get(k).getMarketing().getRushs().get(0).getRushVipDiscount())).multiply(cartList.get(k).getGoodsDetailBean().getProductVo().getGoodsInfoVipPrice()).multiply(BigDecimal.valueOf(cartList.get(k).getGoodsNum())));*/
                        // 2015-12-09 wuyanbo 修改抢购价计算方式
                        flag = flag.subtract((cartList.get(k).getGoodsDetailBean().getProductVo().getGoodsInfoPreferPrice().subtract(cartList.get(k).getMarketing().getRushs().get(0).getRushPrice())).multiply(BigDecimal.valueOf(cartList.get(k).getGoodsNum())));
                        flagVip = flagVip.subtract((cartList.get(k).getGoodsDetailBean().getProductVo().getGoodsInfoVipPrice().subtract(cartList.get(k).getMarketing().getRushs().get(0).getRushVipPrice())).multiply(BigDecimal.valueOf(cartList.get(k).getGoodsNum())));
                        //  add by 付陈林。修改抢购价格，抢购价格就是实际价格 2015年12月9日
                        sumOldPrice = flag;
                        sumOldVipPrice = flagVip;
                        //end edit
                    }
                }

            }
        }
        sumPrice = flag.subtract(taozhuan);
        sumVipPrice = flagVip.subtract(taozhuanVip);

        if ("1".equals(vip)) {
            paramMap.put("shoplist", shoplist);
            paramMap.put(SUMOLDPRICE, sumOldVipPrice);
            paramMap.put("bossSumPrice", bossSumVipPrice);
            paramMap.put(SUMPRICE, sumVipPrice);
            paramMap.put("thirdIdMap", thirdIdMap);
        } else {
            paramMap.put("shoplist", shoplist);
            paramMap.put(SUMOLDPRICE, sumOldPrice);
            paramMap.put("bossSumPrice", bossSumPrice);
            paramMap.put(SUMPRICE, sumPrice);
            paramMap.put("thirdIdMap", thirdIdMap);
        }


        return paramMap;
    }

    /**
     * 得到各个商家的金额
     *
     * @param businessId
     * @param shopdata
     * @param distinctId
     * @param vip        1表示企业会员 0表示一般会员 2015.11.02 wuyanbo add
     * @return
     */
    @Override
    public Map<String, Object> getEveryThirdPriceMap(Long businessId, List<ShoppingCart> shopdata, Long distinctId, String vip) {
        Map<String, Object> paramMap = new HashMap<>();
        // 1表示不同地区存在库存 0则表示库存不足直接跳到购物车
        paramMap.put(STOCK, "1");
        List<ShoppingCart> shoplist = new ArrayList<>();
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
        // 优惠金额
        BigDecimal prePrice = BigDecimal.valueOf(0);
        BigDecimal preVipPrice = BigDecimal.valueOf(0);
        // 中间变量
        BigDecimal flag = BigDecimal.ZERO;
        BigDecimal flagVip = BigDecimal.ZERO;
        // boss总金额
        BigDecimal bossSumPrice = BigDecimal.ZERO;
        BigDecimal bossSumVipPrice = BigDecimal.ZERO;
        // 套装优惠金额
        BigDecimal taozhuan = BigDecimal.ZERO;
        BigDecimal taozhuanVip = BigDecimal.ZERO;

        ProductWare productWare;
        Map<String, Object> para = new HashMap<>();
        if (CollectionUtils.isNotEmpty(shoplist)) {
            Long goodssum = 0L;
            BigDecimal goodsprice = BigDecimal.ZERO;
            BigDecimal totalprice = BigDecimal.ZERO;
            BigDecimal goodsVipprice = BigDecimal.ZERO;
            BigDecimal totalVipprice = BigDecimal.ZERO;

            for (int v = 0; v < shoplist.size(); v++) {

                if (shoplist.get(v).getFitId() != null) {
                    // 如果商品是套装
                    GoodsGroupVo goodsGroupVo = shoplist.get(v).getGoodsGroupVo();
                    if (null != goodsGroupVo) {

                        shoplist.get(v).setGoodsGroupVo(goodsGroupVo);
                        // 获取此套装下的所有货品
                        List<GoodsProductVo> goodsProducts = siteGoodsProductService.queryDetailByGroupId(shoplist.get(v).getFitId());
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

                        // 得到套装优惠费用
                        taozhuan = taozhuan.add(BigDecimal.valueOf(shoplist.get(v).getGoodsNum()).multiply(goodsGroupVo.getGroupPreferamount()));
                        taozhuanVip = taozhuanVip.add(BigDecimal.valueOf(shoplist.get(v).getGoodsNum()).multiply(goodsGroupVo.getGroupPreferamount()));
                    }
                }
                if (shoplist.get(v).getFitId() == null) {

                    // 货品价格
                    goodsprice = shoplist.get(v).getGoodsDetailBean().getProductVo().getGoodsInfoPreferPrice();
                    goodsVipprice = shoplist.get(v).getGoodsDetailBean().getProductVo().getGoodsInfoVipPrice();
                    // 不同地区的货品价格
                    if (businessId == 0) {
                        if (null != distinctId && distinctId > 0) {
                            productWare = this.productWareService.queryProductWareByProductIdAndDistinctId(shoplist.get(v).getGoodsInfoId(), distinctId);

                            // 不同地区的价格不同
                            if (null != productWare) {
                                goodsprice = productWare.getWarePrice();
                                goodsVipprice = productWare.getWareVipPrice();
                                //把不同地区的价格重新赋值给商品销售价
                                shoplist.get(v).getGoodsDetailBean().getProductVo().setGoodsInfoPreferPrice(productWare.getWarePrice());
                                shoplist.get(v).getGoodsDetailBean().getProductVo().setGoodsInfoVipPrice(productWare.getWareVipPrice());
                                if (productWare.getWareStock() - shoplist.get(v).getGoodsNum() <= 0) {
                                    paramMap.put(STOCK, "0");
                                }

                            }
                        }
                    } else {
                        if (shoplist.get(v).getGoodsDetailBean().getProductVo().getGoodsInfoStock() - shoplist.get(v).getGoodsNum() <= 0) {
                            // 第三方货存不足
                            paramMap.put("stock", "0");
                        }
                    }
                    // 是否抹掉分角
                    String discountFlag = "";
                    DecimalFormat myformat = null;
                    myformat = new DecimalFormat("0.00");
                    // 货品价格中间变量
                    BigDecimal goodspriceflag = shoplist.get(v).getGoodsDetailBean().getProductVo().getGoodsInfoPreferPrice();
                    BigDecimal goodsVippriceflag = shoplist.get(v).getGoodsDetailBean().getProductVo().getGoodsInfoVipPrice();

                    //单该货品同时参与了团购和折扣时,优先级团购优先
                    if (shoplist.get(v).getGoodsGroupId() != null) {
                        // 从购物车里得到促销id重新从数据库查询,防止当前(团购促销)已经过期;
                        Marketing mark = marketService.querySimpleMarketingById(shoplist.get(v).getGoodsGroupId());
                        if (mark != null) {
                            Groupon groupon = grouponMapper.selectByMarketId(mark.getMarketingId());
                            if (groupon != null) {
                                /*goodsprice = goodspriceflag.multiply(groupon.getGrouponDiscount());
                                goodsVipprice = goodsVippriceflag.multiply(groupon.getGrouponVipDiscount());*/
                                // 2015-12-09 wuyanbo 修改团购价计算方式
                                goodsprice = groupon.getGrouponPrice();
                                goodsVipprice = groupon.getGrouponVipPrice();
                            }
                        }
                    }
                    // 折扣促销
                    if (shoplist.get(v).getGoodsGroupId() == null && shoplist.get(v).getMarketingId() != null && 0 != shoplist.get(v).getMarketingId()) {
                        // 从购物车里得到促销id重新从数据库查询,防止当前(折扣促销)已经过期;
                        Marketing mark = marketService.marketingDetail(shoplist.get(v).getMarketingId(), shoplist.get(v).getGoodsInfoId());
                        if (mark != null) {
                            para.put("marketingId", mark.getMarketingId());
                            para.put("goodsId", shoplist.get(v).getGoodsInfoId());
                            PreDiscountMarketing premark = preDiscountMarketingMapper.selectByMarketId(para);
                            if (premark != null) {
                                // 货品价格
                                goodsprice = goodspriceflag.multiply(premark.getDiscountInfo());
                                discountFlag = premark.getDiscountFlag();
                                goodsVipprice = goodsVippriceflag.multiply(premark.getVipdiscountInfo());

                                // 抹掉分
                                if ("1".equals(discountFlag)) {
                                    myformat = new DecimalFormat("0.0");
                                } else if ("2".equals(discountFlag)) {
                                    myformat = new DecimalFormat("0");
                                } else {
                                    myformat = new DecimalFormat("0.00");
                                }
                            }
                        }

                    }


                    //不四舍五入
                    myformat.setRoundingMode(RoundingMode.FLOOR);
                    goodsprice = BigDecimal.valueOf(Double.valueOf(myformat.format(goodsprice)));
                    goodsVipprice = BigDecimal.valueOf(Double.valueOf(myformat.format(goodsVipprice)));
                    //单该货品同时参与了团购和折扣时,优先级团购优先
                    if (shoplist.get(v).getMarketing() != null && shoplist.get(v).getMarketing().getGroupon() != null) {
                        /*goodsprice= shoplist.get(v).getGoodsDetailBean().getProductVo().getGoodsInfoPreferPrice().multiply(shoplist.get(v).getMarketing().getGroupon().getGrouponDiscount());
                        goodsVipprice= shoplist.get(v).getGoodsDetailBean().getProductVo().getGoodsInfoVipPrice().multiply(shoplist.get(v).getMarketing().getGroupon().getGrouponVipDiscount());*/
                        // 2015-12-09 wuyanbo 修改团购价格计算方式
                        goodsprice = shoplist.get(v).getMarketing().getGroupon().getGrouponPrice();
                        goodsVipprice = shoplist.get(v).getMarketing().getGroupon().getGrouponVipPrice();
                    }
                    // 处理货品的价格()
                    shoplist.get(v).getGoodsDetailBean().getProductVo().setGoodsInfoPreferPrice(goodsprice);
                    shoplist.get(v).getGoodsDetailBean().getProductVo().setGoodsInfoVipPrice(goodsVipprice);
                    // 货品购买件数
                    goodssum = shoplist.get(v).getGoodsNum();
                    // 计算boss价格页面计算用
                    if (shoplist.get(v).getThirdId() == 0) {
                        bossSumPrice = bossSumPrice.add(goodsprice.multiply(BigDecimal.valueOf(goodssum)));
                        bossSumVipPrice = bossSumVipPrice.add(goodsVipprice.multiply(BigDecimal.valueOf(goodssum)));
                    }
                    // 计算原始总金额
                    sumOldPrice = sumOldPrice.add(goodsprice.multiply(BigDecimal.valueOf(goodssum)));
                    flag = flag.add(goodsprice.multiply(BigDecimal.valueOf(goodssum)));
                    sumOldVipPrice = sumOldVipPrice.add(goodsVipprice.multiply(BigDecimal.valueOf(goodssum)));
                    flagVip = flagVip.add(goodsVipprice.multiply(BigDecimal.valueOf(goodssum)));
                }

            }

            BigDecimal marketflag = BigDecimal.ZERO;
            BigDecimal marketflagVip = BigDecimal.ZERO;
            List<ShoppingCart> cartList = null;
            // 根据第三方id分组得到新的购物车集合
            cartList = new ArrayList<>();
            for (ShoppingCart sc : shoplist) {
                if (sc.getFitId() == null) {
                    cartList.add(sc);

                }
            }

            // 促销分组
            Map<Long, Object> markMap = new HashMap<>();
            for (ShoppingCart sc : cartList) {
                if (sc.getThirdId().equals(businessId) && sc.getMarketingActivityId() != null) {
                    markMap.put(sc.getMarketingActivityId(), sc.getMarketing());
                }
            }
            // 各个同一商家的促销总价
            Map<Long, Object> priceMap = new HashMap<>();
            Map<Long, Object> vipPriceMap = new HashMap<>();
            for (Long obd : markMap.keySet()) {
                // 计算各个商家不同促销的总价格
                for (ShoppingCart car : cartList) {
                    if (obd.equals(car.getMarketingActivityId()) && car.getThirdId().equals(businessId)) {
                        if (null != distinctId && distinctId > 0) {

                            totalprice = totalprice.add(BigDecimal.valueOf(car.getGoodsNum()).multiply(car.getGoodsDetailBean().getProductVo().getGoodsInfoPreferPrice()));
                            totalVipprice = totalVipprice.add(BigDecimal.valueOf(car.getGoodsNum()).multiply(car.getGoodsDetailBean().getProductVo().getGoodsInfoVipPrice()));

                        }
                        priceMap.put(obd, totalprice);
                        vipPriceMap.put(obd, totalVipprice);
                    }
                }
                // 循环结束促销价格置零
                totalprice = BigDecimal.ZERO;
                totalVipprice = BigDecimal.ZERO;
            }
            for (Long ob : markMap.keySet()) {
                Marketing mark = (Marketing) markMap.get(ob);

                if (mark != null) {

                    for (Long markId : priceMap.keySet()) {
                        if (markId.equals(ob)) {
                            // map 封装各个商家促销的价格
                            totalprice = (BigDecimal) priceMap.get(markId);
                            totalVipprice = (BigDecimal) vipPriceMap.get(markId);
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
                                    if (fpm.getVipFullPrice().compareTo(totalVipprice) == -1 || fpm.getVipFullPrice().compareTo(totalVipprice) == 0) {
                                        preVipPrice = fpm.getVipReducePrice();
                                        // 把最大值付给marketfalg
                                        if (preVipPrice.compareTo(marketflagVip) == 1 || preVipPrice.compareTo(marketflagVip) == 0) {
                                            marketflagVip = preVipPrice;
                                        }
                                    }
                                }
                                prePrice = marketflag;
                                marketflag = BigDecimal.ZERO;
                                preVipPrice = marketflagVip;
                                marketflagVip = BigDecimal.ZERO;
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
                                    if (fdm.getVipFullPrice().compareTo(totalVipprice) == -1 || fdm.getVipFullPrice().compareTo(totalVipprice) == 0) {
                                        preVipPrice = (BigDecimal.valueOf(1).subtract(fdm.getVipFullbuyDiscount())).multiply(totalVipprice);
                                        // 把最大值付给marketfalg
                                        if (preVipPrice.compareTo(marketflagVip) == 1 || preVipPrice.compareTo(marketflagVip) == 0) {
                                            marketflagVip = preVipPrice;
                                        }
                                    }
                                }
                                prePrice = marketflag;
                                marketflag = BigDecimal.ZERO;
                                preVipPrice = marketflagVip;
                                marketflagVip = BigDecimal.ZERO;
                            }

                            // 实际付款金额
                            flag = flag.subtract(prePrice);
                            prePrice = BigDecimal.ZERO;
                            flagVip = flagVip.subtract(preVipPrice);
                            preVipPrice = BigDecimal.ZERO;
                        }
                    }

                }
            }

            for (int k = 0; k < cartList.size(); k++) {
                // 直降
                if (cartList.get(k).getMarketing() != null && cartList.get(k).getMarketing().getPriceOffMarketing() != null) {
                    // sumOldPrice =
                    // sumOldPrice.subtract(cartList.get(k).getMarketing().getPriceOffMarketing().getOffValue
                    // ().multiply(BigDecimal.valueOf(cartList.get(k).getGoodsNum())));
                    flag = flag.subtract(cartList.get(k).getMarketing().getPriceOffMarketing().getOffValue().multiply(BigDecimal.valueOf(cartList.get(k).getGoodsNum())));
                    flagVip = flagVip.subtract(cartList.get(k).getMarketing().getPriceOffMarketing().getOffVipValue().multiply(BigDecimal.valueOf(cartList.get(k).getGoodsNum())));
                }

                //团购
                if (cartList.get(k).getMarketing() != null && cartList.get(k).getMarketing().getGroupon() != null) {
                    /*flag = flag.subtract((BigDecimal.ONE.subtract(cartList.get(k).getMarketing().getGroupon().getGrouponDiscount())).multiply(cartList.get(k).getGoodsDetailBean().getProductVo().getGoodsInfoPreferPrice()).multiply(BigDecimal.valueOf(cartList.get(k).getGoodsNum())));
                    flagVip = flagVip.subtract((BigDecimal.ONE.subtract(cartList.get(k).getMarketing().getGroupon().getGrouponVipDiscount())).multiply(cartList.get(k).getGoodsDetailBean().getProductVo().getGoodsInfoVipPrice()).multiply(BigDecimal.valueOf(cartList.get(k).getGoodsNum())));*/
                    //2015-12-09 wuyanbo 修改团购价格的计算方式
                    flag = flag.subtract((cartList.get(k).getGoodsDetailBean().getProductVo().getGoodsInfoPreferPrice().subtract(cartList.get(k).getMarketing().getGroupon().getGrouponPrice())).multiply(BigDecimal.valueOf(cartList.get(k).getGoodsNum())));
                    flagVip = flagVip.subtract((cartList.get(k).getGoodsDetailBean().getProductVo().getGoodsInfoVipPrice().subtract(cartList.get(k).getMarketing().getGroupon().getGrouponVipPrice())).multiply(BigDecimal.valueOf(cartList.get(k).getGoodsNum())));
                    sumOldPrice = flag;
                    sumOldVipPrice = flagVip;
                }
                //抢购
                if (cartList.get(k).getMarketing() != null && cartList.get(k).getMarketing().getRushs() != null && !cartList.get(k).getMarketing().getRushs().isEmpty()) {
                    /*flag = flag.subtract((BigDecimal.ONE.subtract(cartList.get(k).getMarketing().getRushs().get(0).getRushDiscount())).multiply(cartList.get(k).getGoodsDetailBean().getProductVo().getGoodsInfoPreferPrice()).multiply(BigDecimal.valueOf(cartList.get(k).getGoodsNum())));
                    flagVip = flagVip.subtract((BigDecimal.ONE.subtract(cartList.get(k).getMarketing().getRushs().get(0).getRushVipDiscount())).multiply(cartList.get(k).getGoodsDetailBean().getProductVo().getGoodsInfoVipPrice()).multiply(BigDecimal.valueOf(cartList.get(k).getGoodsNum())));*/
                    //2015-12-09 wuyanbo 修改团购价格的计算方式
                    flag = flag.subtract((cartList.get(k).getGoodsDetailBean().getProductVo().getGoodsInfoPreferPrice().subtract(cartList.get(k).getMarketing().getRushs().get(0).getRushPrice())).multiply(BigDecimal.valueOf(cartList.get(k).getGoodsNum())));
                    flagVip = flagVip.subtract((cartList.get(k).getGoodsDetailBean().getProductVo().getGoodsInfoVipPrice().subtract(cartList.get(k).getMarketing().getRushs().get(0).getRushVipPrice())).multiply(BigDecimal.valueOf(cartList.get(k).getGoodsNum())));
                    /**
                     * edit by 付陈林 修改抢购时的商品价格 2015年12月6日
                     * */

                    sumOldPrice = flag;
                    sumOldVipPrice = flagVip;

//                    edit end
                }
            }

        }
        sumPrice = flag.subtract(taozhuan);
        sumVipPrice = flagVip.subtract(taozhuanVip);

        if ("1".equals(vip)) {
            paramMap.put(SUMOLDPRICE, sumOldVipPrice);
            paramMap.put(SUMPRICE, sumVipPrice);
        } else {
            paramMap.put(SUMOLDPRICE, sumOldPrice);
            paramMap.put(SUMPRICE, sumPrice);
        }

        return paramMap;
    }

    /**
     * 获取第三方订单金额
     * <p/>
     * 计算订单总的应付价、原价、优惠价、运费
     *
     * @param thirdId
     * @param codeNo
     * @param amount
     * @param customerId
     * @param orderCode
     * @param cityId
     * @param distinctId
     * @param shoplist
     * @param typeIdflag
     * @param vip        1表示企业会员 0表示一般会员 2015.11.02 wuyanbo add
     * @return
     */
    public Order getThirdOrderPrice(Long thirdId, String codeNo, Long amount, Long customerId, String orderCode, Long cityId, Long distinctId, List<ShoppingCart> shoplist,
                                    boolean typeIdflag, String vip) {

        Order order = new Order();
        Map<String, Object> map = getEveryThirdPriceMap(thirdId, shoplist, distinctId, vip);
        // 订单交易总金额
        BigDecimal sumPrice = BigDecimal.valueOf(Double.valueOf(map.get(SUMPRICE).toString()));
        // 原总金额
        BigDecimal sumOldPrice = BigDecimal.valueOf(Double.valueOf(map.get(SUMOLDPRICE).toString()));
        // 总优惠金额
        BigDecimal prePrice = sumOldPrice.subtract(sumPrice);
        Customer cus = customerServiceInterface.queryCustomerById(customerId);
        CustomerPointLevel cuspointLevel = customerPointLevelMapper.selectByPrimaryKey(cus.getPointLevelId());
        // 会员折扣
        BigDecimal discountPrice = cuspointLevel.getPointDiscount().multiply(sumOldPrice);
        discountPrice = sumOldPrice.subtract(discountPrice);
        // 判断库存
        String stock = map.get(STOCK).toString();
        // 有商品存在无货返回
        if ("0".equals(stock)) {
            return null;
        }
        if (codeNo != null && !"".equals(codeNo)) { //优惠券直降、满减
            Coupon coupon = couponService.selectCouponByCodeNo(codeNo);

            if (coupon != null && thirdId.equals(coupon.getBusinessId())) {
                // 直降
                if ("1".equals(coupon.getCouponRulesType())) {
                    // 计算交易价格减去金额
                    sumPrice = sumPrice.subtract(coupon.getCouponStraightDown().getDownPrice());
                    // 优惠金额+金额
                    prePrice = prePrice.add(coupon.getCouponStraightDown().getDownPrice());

                }
                // 满减
                if ("2".equals(coupon.getCouponRulesType())) {
                    // 计算交易价格减去金额
                    sumPrice = sumPrice.subtract(coupon.getCouponFullReduction().getReductionPrice());
                    // 优惠金额+金额
                    prePrice = prePrice.add(coupon.getCouponFullReduction().getReductionPrice());

                }
            }
        }
        // boss商品兑换积分

        /* 积分兑换订单金额 */
        if (0 == thirdId && null != amount && amount != 0) {
            // 获得当前用户的积分信息
            CustomerPoint customerPoint = couponService.selectCustomerPointByCustomerId(customerId);
            // 判断当前积分是否够兑换
            if (customerPoint.getPointSum() < amount) {
                return new Order();
            }
            // 积分兑换规则
            PointSet pointSet = this.couponService.selectPointSet();
            if (null != pointSet && "1".equals(pointSet.getIsOpen())) {
                // 转换类型
                BigDecimal zhuanhuan = new BigDecimal(amount);
                // 根据积分兑换规则 计算积分兑换金额
                BigDecimal disparityPrice = zhuanhuan.multiply(pointSet.getConsumption());
                //
                disparityPrice = disparityPrice.divide(new BigDecimal(10));
                // 对兑换处的价格进行四舍五入
                BigDecimal jiFenDuiHuan = disparityPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
                // 计算最后订单金额
                sumPrice = sumPrice.subtract(jiFenDuiHuan);
                // 对兑换处的价格进行四舍五入
                sumPrice = sumPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
                // 修改会员的积分
                this.updatePoint(customerId, amount);
                this.insertExchangeCusmomer(customerId, orderCode, amount, disparityPrice, pointSet.getConsumption());
            }
        }
        BigDecimal expressPrice = BigDecimal.ZERO;
        // 在线支付没有运费
        if (typeIdflag) {
            // 得到没有包邮或者没有达到条件的购物车集合
            List<ShoppingCart> cartList = shoppingCartService.getNobaoyouShoppingcarts(shoplist, distinctId, vip);
            // 计算运费
            expressPrice = shoppingCartService.getEverythirdExpressPrice(thirdId, cityId, cartList);

        }
        order.setOrderPrePrice(prePrice);//优惠的价格
        // 计算交易价格加上运费
        sumPrice = sumPrice.add(expressPrice);
        if (0 == thirdId) {
            // 只有boss的商品存在会员折扣 实际付款减去会员折扣的钱
            sumPrice = sumPrice.subtract(discountPrice);
            // 订单优惠金额 加上会员折扣的金额
            order.setOrderPrePrice(prePrice.add(discountPrice));
        }
        if (sumPrice.compareTo(BigDecimal.valueOf(0.02)) == -1) {
            sumPrice = BigDecimal.valueOf(0.01);
        }
        order.setOrderPrice(sumPrice);//最终的价格（应付价）
        order.setOrderOldPrice(sumOldPrice);//原价

        order.setExpressPrice(expressPrice);//运费

        return order;
    }

    /**
     * 提交订单(新)
     *
     * @param request
     * @param shoppingCartId
     * @param customerRemark
     * @param chInvoiceTitle
     * @param chInvoiceContent
     * @return
     * @throws IOException
     */
    @Override
    @Transactional
    public List<Order> newsubmitOrder(HttpServletRequest request, Long[] shoppingCartId, String customerRemark, String chInvoiceTitle, String chInvoiceContent) throws IOException {
        Long custAddress = Long.parseLong(request.getParameter("custAddress"));
        String payflag = request.getParameter("ch_pay");
        String thirdpayflag = request.getParameter("ch_paythird");
        String deliflag = request.getParameter("deliveryPointId");
        String typeflag = request.getParameter("typeId");

        Object object = request.getSession().getAttribute("vip");
        String vip = "0";
        if (null != object) {
            vip = (String) object;
        }

        // 平台支付方式初始化
        Long chPay = 1L;
        // 第三方支付方式初始化,目前只支持在线支付
        Long chPaythird = 1L;
        // 配送方式0:默认模板配送,1:上门自提
        Long typeId = null;
        if (StringUtils.isNotEmpty(typeflag)) {
            typeId = Long.valueOf(typeflag);
        }
        Long deliveryPointId = null;
        if (StringUtils.isNotEmpty(payflag)) {
            chPay = Long.valueOf(payflag);
        }
        if (StringUtils.isNotEmpty(deliflag)) {
            deliveryPointId = Long.valueOf(deliflag);
        }

        if (StringUtils.isNotEmpty(thirdpayflag)) {
            chPaythird = Long.valueOf(thirdpayflag);
        }
        String chVoinceType = request.getParameter("ch_voinceType");
        // 优惠劵码
        String codeNo = request.getParameter("codeNo");
        Long amount = 0L;
        // 兑换积分
        if (StringUtils.isNotEmpty(request.getParameter("amount"))) {
            amount = Long.parseLong(request.getParameter("amount"));
        }

        // 获取所有所有选中商品信息
        List<ShoppingCart> shoplist = shoppingCartMapper.shopCartListByIds(Arrays.asList(shoppingCartId));
        if (CollectionUtils.isEmpty(shoplist)) {
            return Collections.emptyList();
        }
        CustomerAddress ca = customerServiceInterface.queryCustAddress(custAddress);

        Long distinctId = null;
        if (ca != null && ca.getDistrict() != null) {
            distinctId = ca.getDistrict().getDistrictId();
        }
        Map<Long, Object> thirdIdMap = new HashMap<>();
        for (int i = 0; i < shoplist.size(); i++) {
            if (shoplist.get(i).getFitId() == null) {
                thirdIdMap.put(shoplist.get(i).getThirdId(), "");
            } else {

                // 如果商品是套装
                GoodsGroupVo goodsGroupVo = goodsGroupService.queryVoByPrimaryKey(((ShoppingCart) shoplist.get(i)).getFitId());
                if (null != goodsGroupVo) {

                    thirdIdMap.put(goodsGroupVo.getThirdId(), "");
                    shoplist.get(i).setGoodsGroupVo(goodsGroupVo);
                    shoplist.get(i).setThirdId(goodsGroupVo.getThirdId());
                }
            }
        }
        // 当前登录成功的会员
        Long customerId = (Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID);
        ShoppingCartWareUtil cartWareUtil = shoppingCartService.selectPNameByParam(request);
        // 仓库信息
        List<CalcStockUtil> calcStockUtils = new ArrayList<CalcStockUtil>();

        // 主订单号
        String orderOldCode = UtilDate.mathString(new Date());
        // 查询折扣map
        // Map<String, Object> marketMap = new HashMap<>();
        List<Order> maps = new ArrayList<Order>();
        // 循环商家ID
        for (Long thirdId : thirdIdMap.keySet()) {
            // 查询购买的商品
            List<OrderGoods> oglist = new ArrayList<OrderGoods>();
            // 申明Order
            Order order = new Order();
            // 判断所选商品是否为Null
            if (shoplist != null && !shoplist.isEmpty()) {
                // 循环所购买的商品
                for (int i = 0; i < shoplist.size(); i++) {
                    if (shoplist.get(i).getFitId() != null) { //套装商品
                        if (thirdId.equals(shoplist.get(i).getGoodsGroupVo().getThirdId())) {

                            OrderGoods og = null;
                            // 获取此套装下的所有货品
                            List<GoodsProductVo> goodsProducts = siteGoodsProductService.queryDetailByGroupId(shoplist.get(i).getFitId());
                            for (int j = 0; j < goodsProducts.size(); j++) {
                                CalcStockUtil calcStockUtil = new CalcStockUtil();
                                og = new OrderGoods();
                                og.setGoodsInfoNum(shoplist.get(i).getGoodsNum());
                                // 查询库存
                                ProductWare productWare = productWareService.queryProductWareByProductIdAndDistinctId(goodsProducts.get(i).getGoodsInfoId(), distinctId);
                                if (productWare != null && productWare.getWareStock() - shoplist.get(i).getGoodsNum() <= 0) {
                                    // 设置商品库存
                                    return Collections.emptyList();

                                }
                                calcStockUtil.setIsThird(goodsProducts.get(j).getIsThird());
                                calcStockUtil.setDistinctId(cartWareUtil.getDistrictId());
                                og.setGoodsInfoNum(shoplist.get(i).getGoodsNum());
                                calcStockUtil.setIsThird(goodsProducts.get(j).getIsThird());
                                calcStockUtil.setDistinctId(cartWareUtil.getDistrictId());
                                // 商品id
                                calcStockUtil.setProductId(goodsProducts.get(j).getGoodsInfoId());
                                // 减去库存
                                calcStockUtil.setStock(Integer.parseInt(og.getGoodsInfoNum().toString()));
                                calcStockUtils.add(calcStockUtil);
                                og.setDelFlag("0");
                                og.setBuyTime(new Date());
                                og.setEvaluateFlag("0");
                                og.setGoodsInfoPrice(BigDecimal.valueOf(0));
                                if ("1".equals(vip)) {
                                    og.setGoodsInfoOldPrice(goodsProducts.get(j).getGoodsInfoVipPrice());
                                } else {
                                    og.setGoodsInfoOldPrice(goodsProducts.get(j).getGoodsInfoPreferPrice());
                                }
                                og.setGoodsInfoId(goodsProducts.get(j).getGoodsInfoId());
                                og.setGoodsId(goodsProducts.get(j).getGoodsId());
                                // 最终售价
                                og.setGoodsInfoPrice(og.getGoodsInfoOldPrice().subtract(shoplist.get(i).getGoodsGroupVo().getGroupPreferamount()));
                                // 小计金额
                                og.setGoodsInfoSumPrice(og.getGoodsInfoPrice().multiply(BigDecimal.valueOf(shoplist.get(i).getGoodsNum())));
                                oglist.add(og);

                            }

                        }
                    } else {
                        // 查询商品详细
                        shoplist.get(i).setGoodsDetailBean(siteGoodsProductService.querySimpleDetailBeanByProductId(((ShoppingCart) shoplist.get(i)).getGoodsInfoId()));
                        // 查询购物车里选择的促销信息
                        Marketing mark = marketService.marketingDetail(shoplist.get(i).getMarketingActivityId(), shoplist.get(i).getGoodsInfoId());
                        shoplist.get(i).setMarketing(mark);

                        // 判断购买商品是否属于此商家
                        if (thirdId.equals(shoplist.get(i).getGoodsDetailBean().getProductVo().getThirdId())) {

                            if (thirdId == 0 && distinctId != null) {
                                // 查询库存
                                ProductWare productWare = productWareService.queryProductWareByProductIdAndDistinctId(shoplist.get(i).getGoodsDetailBean().getProductVo()
                                        .getGoodsInfoId(), distinctId);
                                if (productWare != null) {
                                    // 设置商品库存
                                    ((ShoppingCart) shoplist.get(i)).getGoodsDetailBean().getProductVo().setGoodsInfoStock(productWare.getWareStock());
                                    ((ShoppingCart) shoplist.get(i)).getGoodsDetailBean().getProductVo().setGoodsInfoPreferPrice(productWare.getWarePrice());
                                    ((ShoppingCart) shoplist.get(i)).getGoodsDetailBean().getProductVo().setGoodsInfoVipPrice(productWare.getWareVipPrice());//企业会员价
                                    if (productWare.getWareStock() <= 0) {
                                        return Collections.emptyList();
                                    }

                                }
                            }
                            // 初始化订单商品信息
                            OrderGoods og = new OrderGoods();
                            // 设置数量
                            og.setGoodsInfoNum(shoplist.get(i).getGoodsNum());
                            // 设置删除标记
                            og.setDelFlag("0");
                            // 设置购买时间
                            og.setBuyTime(new Date());
                            // 设置收货地区
                            og.setDistinctId(cartWareUtil.getDistrictId());
                            og.setEvaluateFlag("0");
                            // 设置商品原价
                            if ("1".equals(vip)) {
                                og.setGoodsInfoOldPrice(shoplist.get(i).getGoodsDetailBean().getProductVo().getGoodsInfoVipPrice());
                            } else {
                                og.setGoodsInfoOldPrice(shoplist.get(i).getGoodsDetailBean().getProductVo().getGoodsInfoPreferPrice());
                            }
                            // 设置货品Id
                            og.setGoodsInfoId(shoplist.get(i).getGoodsDetailBean().getProductVo().getGoodsInfoId());
                            // 设置商品ID
                            og.setGoodsId(shoplist.get(i).getGoodsDetailBean().getProductVo().getGoodsId());
                            // 订单货品与参与的折扣绑定关系
                            og.setGoodsMarketingId(shoplist.get(i).getMarketingId());
                            // 订单货品参与的其他促销绑定关系
                            og.setGoodsActiveMarketingId(shoplist.get(i).getMarketingActivityId());
                            //货品参与团购促销绑定关系
                            og.setGoodsGroupMarketingId(shoplist.get(i).getGoodsGroupId());
                            // 货品价格
                            if ("1".equals(vip)) {
                                og.setGoodsInfoOldPrice(shoplist.get(i).getGoodsDetailBean().getProductVo().getGoodsInfoVipPrice());
                            } else {
                                og.setGoodsInfoOldPrice(shoplist.get(i).getGoodsDetailBean().getProductVo().getGoodsInfoPreferPrice());
                            }
                            shoplist.get(i).setGoodsDetailBean(
                                    siteGoodsProductService.queryDetailBeanByProductId(((ShoppingCart) shoplist.get(i)).getGoodsInfoId(), ((ShoppingCart) shoplist.get(i)).getDistinctId()));

                            List<Marketing> markList = marketService.selectMarketingByGoodsInfoId(shoplist.get(i).getGoodsInfoId(), shoplist.get(i).getGoodsDetailBean().getBrand().getBrandId(), shoplist
                                    .get(i).getGoodsDetailBean().getProductVo().getGoods().getCatId());
                            BigDecimal goodsInfoPrice = og.getGoodsInfoOldPrice();
                            BigDecimal goodsInfoSumPrice = og.getGoodsInfoOldPrice().multiply(BigDecimal.valueOf(shoplist.get(i).getGoodsNum()));
                            if (markList != null && markList.size() > 0) {
                                Marketing marketing = markList.get(0);
                                if (marketing != null) {
                                    //直降  付陈林 2015年12月11日
                                    if("1".equals(marketing.getCodexType())){
                                        if("1".equals(vip)){
                                            if(marketing.getPriceOffMarketing()!=null){
                                                goodsInfoPrice = goodsInfoPrice.subtract(marketing.getPriceOffMarketing().getOffVipValue());
                                            }
                                        }else{
                                            if(marketing.getPriceOffMarketing()!=null){
                                                goodsInfoPrice = goodsInfoPrice.subtract(marketing.getPriceOffMarketing().getOffValue());
                                            }
                                        }
                                    }
                                    //团购
                                    if ("10".equals(marketing.getCodexType())) {
                                        if ("1".equals(vip)) {
                                            if (marketing.getGroupon() != null) {
                                                goodsInfoPrice = marketing.getGroupon().getGrouponVipPrice();
                                            }
                                        } else {
                                            if (marketing.getGroupon() != null) {
                                                goodsInfoPrice = marketing.getGroupon().getGrouponPrice();
                                            }

                                        }
                                    }
                                    //抢购
                                    else if ("11".equals(marketing.getCodexType())) {
                                        if ("1".equals(vip)) {
                                            if (marketing.getRushs() != null && marketing.getRushs().size() > 0) {
                                                goodsInfoPrice = marketing.getRushs().get(0).getRushVipPrice();
                                            }
                                        } else {
                                            if (marketing.getRushs() != null && marketing.getRushs().size() > 0) {
                                                goodsInfoPrice = marketing.getRushs().get(0).getRushPrice();
                                            }
                                        }
                                    }
                                    //折扣
                                    else if ("15".equals(marketing.getCodexType())) {
                                        if (marketing.getPreDiscountMarketings() != null && !marketing.getPreDiscountMarketings().isEmpty() /*&& isgroup==0*/) {
                                            BigDecimal goodsPrice =shoplist.get(i).getGoodsDetailBean().getProductVo().getGoodsInfoPreferPrice();
                                            BigDecimal goodsVipPrice= shoplist.get(i).getGoodsDetailBean().getProductVo().getGoodsInfoVipPrice();
                                            for (PreDiscountMarketing premark : marketing.getPreDiscountMarketings()) {
                                                // 参与了折扣
                                                if (premark.getGoodsId().equals(shoplist.get(i).getGoodsInfoId())) {
                                                    String discountFlag = "";// 抹掉分角标识
                                                    discountFlag = premark.getDiscountFlag();
                                                    DecimalFormat myformat = null;

                                                    // 抹掉分
                                                    if ("1".equals(discountFlag)) {
                                                        myformat = new DecimalFormat("0.0");
                                                    } else if ("2".equals(discountFlag)) {
                                                        myformat = new DecimalFormat("0");
                                                    } else {
                                                        myformat = new DecimalFormat("0.00");
                                                    }
                                                    // 不四舍五入
                                                    myformat.setRoundingMode(RoundingMode.FLOOR);
                                                    if ("1".equals(vip)) {
                                                        //VIP
                                                        goodsVipPrice= goodsVipPrice.multiply(premark.getVipdiscountInfo());
                                                        goodsInfoPrice = BigDecimal.valueOf(Double.valueOf(myformat.format(goodsVipPrice)));
                                                    } else {
                                                        goodsPrice= goodsPrice.multiply(premark.getDiscountInfo());
                                                        goodsInfoPrice = BigDecimal.valueOf(Double.valueOf(myformat.format(goodsPrice)));
                                                        }
                                                }
                                            }
                                        }


                                    }
                                }
                            }
                            goodsInfoSumPrice = goodsInfoPrice.multiply(BigDecimal.valueOf(shoplist.get(i).getGoodsNum()));
                            og.setGoodsInfoPrice(goodsInfoPrice);
                            // 设置货品总价格（数量*价格）
                            og.setGoodsInfoSumPrice(goodsInfoSumPrice);
                            // 获取促销信息
                            Marketing market = shoplist.get(i).getMarketing();
                            CalcStockUtil calcStockUtil = new CalcStockUtil();
                            calcStockUtil.setIsThird(shoplist.get(i).getGoodsDetailBean().getProductVo().getIsThird());
                            calcStockUtil.setDistinctId(cartWareUtil.getDistrictId());
                            // 商品id
                            calcStockUtil.setProductId(shoplist.get(i).getGoodsDetailBean().getProductVo().getGoodsInfoId());
                            // 减去库存
                            calcStockUtil.setStock(Integer.parseInt(og.getGoodsInfoNum().toString()));
                            calcStockUtils.add(calcStockUtil);

                            if (market != null) {
                                //当为抢购时
                                if (market.getRushs() != null && !market.getRushs().isEmpty()) {
                                    og.setMarketing(market);
                                }
                            }
                            oglist.add(og);
                        }
                    }
                }

                WareHouse ware = productWareMapper.findWare(ca.getDistrict().getDistrictId());
                // 封装物流信息
                OrderExpress oe = new OrderExpress();
                // 当为在线支付且是上门自提时运费是没有的
                boolean typeIdflag = true;
                // 查询物流模板信息 根据thirdId 查询默认的模板
                Map<String, Object> paramMap = new HashMap<String, Object>();
                paramMap.put("freightIsDefault", "1");
                paramMap.put("freightThirdId", thirdId);
                // 获取默认模板
                FreightTemplate ft = freightTemplateMapper.selectFreightTemplateSubOrder(paramMap);
                Long comId = null;
                String comName = "";
                // 获取物流信息
                FreightExpress fe = freightExpressMapper.selectTemplateExpress(ft.getFreightTemplateId()).get(0);
                if (thirdId.equals(0L)) {

                    fe.setSysLogisticsCompany(sysLogisticsCompanyMapper.selectCompanyById(fe.getLogComId()));
                    comId = fe.getSysLogisticsCompany().getLogComId();
                    comName = fe.getSysLogisticsCompany().getName();
                } else {
                    Express express = expressInfoMapperThird.selectByshoreExpId(fe.getLogComId());
                    if (null != express) {
                        comId = express.getShoreExpId();
                        comName = express.getExpName();
                    }

                }
                if (fe != null) {
                    oe.setExpressId(comId);
                    oe.setExpressName(comName);
                }

                if (typeId != null && typeId == 1 && "0".equals(thirdId.toString()) && chPay != null && chPay.equals(1L)) {
                    typeIdflag = false;
                    // 上门自提
                    oe.setExpressTypeId(1L);
                    // 上门自提地址
                    DeliveryPoint point = deliveryPointService.getDeliveryPoint(deliveryPointId);
                    order.setShoppingAddrId(deliveryPointId);
                    order.setShippingProvince(point.getTemp1());
                    order.setShippingCity(point.getTemp2());
                    order.setShippingCounty(point.getTemp3());
                    order.setShippingAddress(point.getAddress());
                    order.setShippingPerson(point.getName());
                    order.setShippingCountyId(point.getDistrictId());
                    // 设置配送方式
                    order.setOrderExpressType("1");
                    order.setExpressPrice(null);
                } else {
                    // 设置配送方式
                    order.setOrderExpressType("0");
                    typeIdflag = true;
                    // 用户默认收货地址ID
                    order.setShoppingAddrId(custAddress);
                    // 省
                    order.setShippingProvince(ca.getProvince().getProvinceName());
                    // 市
                    order.setShippingCity(ca.getCity().getCityName());
                    // 区
                    order.setShippingCounty(ca.getDistrict().getDistrictName());
                    // 详细地址
                    order.setShippingAddress(ca.getAddressDetail());
                    // 区ID
                    order.setShippingCountyId(ca.getDistrict().getDistrictId());

                    // 配送方式名称
                    oe.setExpressTypeName(ft.getFreightTemplateName());

                    // 配送方式 0 快递配送 1 上门自提
                    oe.setExpressTypeId(0L);
                }
                oe.setDelFlag("0");
                if (ware != null) {
                    // 收获地址信息
                    order.setWareName(ware.getWareName());
                    // 库存ID
                    order.setWareId(ware.getWareId());
                } else {
                    // 返回购物车
                    return Collections.emptyList();
                }

                // 收货人
                order.setShippingPerson(ca.getAddressName());
                // 电话
                order.setShippingPhone(ca.getAddressPhone());
                // 手机
                order.setShippingMobile(ca.getAddressMoblie());
                // 备注
                order.setCustomerRemark(customerRemark);
                // 邮编
                order.setShippingPostcode(ca.getAddressZip());
                // 临时账号
                isTempCust(request, ca);
                // 随机数
                Random random1 = new Random();
                int randomNum = random1.nextInt(101);
                // 子订单号
                String orderCode = UtilDate.mathString(new Date()) + randomNum;
                // 插入订单促销id
                order.setOrderCode(orderCode);
                order.setOrderOldCode(orderOldCode);

                // 发票信息
                order.setInvoiceType(chVoinceType);
                // 如果为1，普通发票，设置发票抬头和内容
                if ("1".equals(chVoinceType)) {
                    // 发票抬头
                    order.setInvoiceTitle(chInvoiceTitle);
                    // 发票内容
                    order.setInvoiceContent(chInvoiceContent);
                }
                order.setDelFlag("0");

                // 订单状态
                order.setOrderStatus("0");

                Order orderpirce = getThirdOrderPrice(thirdId, codeNo, amount, customerId, orderCode, ca.getCity().getCityId(), ca.getDistrict().getDistrictId(), shoplist,
                        typeIdflag, vip);
                if (orderpirce == null) {
                    return Collections.emptyList();
                }
                //订单积分
                order.setOrderIntegral(amount);
                // 订单总金额
                order.setOrderPrice(orderpirce.getOrderPrice());
                // 原始总额
                order.setOrderOldPrice(orderpirce.getOrderOldPrice());
                // 总优惠金额
                order.setOrderPrePrice(orderpirce.getOrderPrePrice());

                // 运费
                order.setExpressPrice(orderpirce.getExpressPrice());
                // 如果为2，表示货到付款
                // 如果为2,未付款（货到付款）
                if (thirdId == 0) {
                    // 支付方式
                    order.setPayId(chPay);
                    if (chPay == 2) {
                        // 货到付款
                        order.setOrderLinePay("0");
                    } else {
                        // 在线支付
                        order.setOrderLinePay("1");
                        // 上门自提
                        if (typeId != null && typeId == 1L) {

                            order.setExpressPrice(null);
                        }
                    }

                } else {
                    // 第三方支付方式
                    order.setPayId(chPaythird);
                    // 目前只支持在线支付
                    order.setOrderLinePay("1");
                }

                order.setBusinessId(thirdId);
                // 判断是否是平台商品
                if (thirdId == 0) {
                    // 查询直营店开启状态
                    String status = basicService.getStoreStatus();
                    // 判断是否开启
                    if (("0").equals(status)) {
                        List<DirectShop> directShops = null;
                        if (typeId != null && typeId == 1 && "0".equals(thirdId.toString()) && chPay != null && chPay.equals(1L)) {// 上门自提地址
                            DeliveryPoint point = deliveryPointService.getDeliveryPoint(deliveryPointId);
                            directShops = directShopService.queryDirectShopList(point.getDistrictId());
                        } else {
                            // 快递配送查询
                            directShops = directShopService.queryDirectShopList(ca.getDistrict().getDistrictId());

                        }

                        // 判断是否为空
                        if (!directShops.isEmpty() && directShops.size() > 0) {

                            Random random = new Random();
                            // 获取随机数
                            int rNum = random.nextInt(directShops.size());
                            //发送短信
                            Sms sms = mapper.selectSms();
                            //手机号
                            sms.setSendSim(directShops.get(rNum).getDirectShopTel());
                            //短信内容
                            sms.setMsgContext("您有一笔新订单，单号为：" + orderCode);
                            /*try {
                                // 短信发送
                                *//*SmsPost.sendPostOrder(sms);*//*
                            } catch (IOException e) {
                                LOGGER.error("",e);
                            }*/
                            // 获取直营店订单id
                            // 重新设置订单商家id
                            order.setBusinessId(directShops.get(rNum).getDirectShopId());
                            // 设置为直营店订单
                            order.setDirectType("1");
                        }
                    }

                }

                // 订单使用的优惠券
                order.setCouponNo(codeNo);
                Long customerId1 = (Long) request.getSession().getAttribute(CUSTOMERID);
                order.setCustomerId(customerId1);
                order.setCreateTime(new Date());
                Long salesmanId =qpCustomerRelaSalesmanService.getSalesmanIdByCustId(customerId1);
                if(salesmanId != null){
                    order.setSalesmanId(salesmanId);
                }
                // 插入订单主表
                int f = orderService.insertOrder(order);

                // 插叙返回的ID
                if (f == 1) {

                    // 获取刚刚插入的订单ID
                    Long orderId = orderService.selectLastId();
                    // 修改优惠券已经使用
                    couponNoService.updateCodeIsUse(codeNo, orderCode);
                    order.setOrderId(orderId);
                    maps.add(order);

                    // 插入物流信息
                    oe.setOrderId(orderId);
                    orderExpressMapper.insertOrderExpress(oe);
                    // 循环设置货品级联ID信息
                    if (oglist != null && !oglist.isEmpty()) {
                        for (int i = 0; i < oglist.size(); i++) {
                            //存在抢购促销时
                            if (oglist.get(i).getMarketing() != null && oglist.get(i).getMarketing().getRushs() != null && !oglist.get(i).getMarketing().getRushs().isEmpty()) {
                                RushCustomer rushCustomer = new RushCustomer();
                                rushCustomer.setRushId(oglist.get(i).getMarketing().getRushs().get(0).getRushId());
                                rushCustomer.setGoodsInfoId(oglist.get(i).getGoodsInfoId());
                                rushCustomer.setCustomerId(customerId);
                                rushCustomer.setGoodsNum(Integer.valueOf(oglist.get(i).getGoodsInfoNum().toString()));
                                rushCustomerMapper.insertCustomerRush(rushCustomer);
                            }
                            oglist.get(i).setOrderId(orderId);
                            // 插入货品
                            orderGoodsMapper.insertOrderGoodsInfo(oglist.get(i));
                        }
                    }
                    if ("0".equals(order.getOrderLinePay())) {
                        synOrderService.SynOrder(orderId);
                    }
                }

            }

        }

        // 修改所有购买商品为已经删除
        shoppingCartService.deleteShoppingCartByIds(request, shoppingCartId);
        request.getSession().setAttribute(IS_TEMP_CUST, "1");
        try {
            goodsProductService.minStock(calcStockUtils);

            return maps;
        } finally {
            calcStockUtils = null;
            shoplist = null;
            maps = null;
        }

    }

    /**
     * 下单方法法
     *
     * @param request
     * @param shoppingCartId   商品购物车IDS
     * @param codeNo           使用的优惠券
     * @param custAddress      地址ID
     * @param chPay            支付方式Id
     * @param chExpress        配送方式ID
     * @param chVoinceType
     * @param chInvoiceTitle
     * @param chInvoiceContent
     * @param marketingId
     * @param thirdIds
     * @param cartWareUtil
     * @param customerRemark
     * @param duiHuanJiFen
     * @param result
     * @return
     * @throws IOException
     */
    @Override
    @Transactional
    public List<Order> submitOrder(HttpServletRequest request, Long[] shoppingCartId, String codeNo, Long custAddress, Long chPay, Long chExpress, String chVoinceType,
                                   String chInvoiceTitle, String chInvoiceContent, Long[] marketingId, Long[] thirdIds, ShoppingCartWareUtil cartWareUtil, String customerRemark, Long duiHuanJiFen,
                                   BigDecimal result, String vip) throws IOException {

        Long[] marketingIdNew = marketingId;
        // 获取所有所有选中商品信息
        List<ShoppingCart> cartlist1 = extracted(request, shoppingCartId);
        // 购物车第三方id
        List<Object> list = new ArrayList<Object>();
        // 仓库信息
        List<CalcStockUtil> calcStockUtils = new ArrayList<CalcStockUtil>();
        if (cartlist1 != null && !cartlist1.isEmpty()) {
            for (ShoppingCart sc : cartlist1) {
                // 判断商品是否是套装
                if (sc.getFitId() == null) {
                    // 判断库存是否为0
                    if (sc.getGoodsDetailBean().getProductVo().getGoodsInfoStock() <= 0) {
                        // 如果为0，返回到购物车
                        return Collections.emptyList();
                    }
                    list.add(sc.getGoodsDetailBean().getProductVo().getThirdId());
                } else {
                    if (sc.getGoodsGroupVo().getThirdId() != null) {

                        list.add(sc.getGoodsGroupVo().getThirdId());
                    } else {
                        list.add(0);
                    }
                }
            }

            // 此方法为踢除重复的第三方商家ID
            for (int q = 0; q < list.size() - 1; q++) {
                for (int p = list.size() - 1; p > q; p--) {
                    if (list.get(p).equals(list.get(q))) {
                        list.remove(p);
                    }
                }

            }
        }
        // 主订单号
        String orderOldCode = UtilDate.mathString(new Date());
        List<Order> maps = new ArrayList<Order>();
        // 循环商家ID
        for (Object thirdId : list) {
            // 购物车内所有的商家id
            List<Gift> gifts = new ArrayList<Gift>();
            List<Coupon> coupons = new ArrayList<Coupon>();
            // 查询购买的商品
            List<OrderGoods> oglist = new ArrayList<OrderGoods>();
            // 申明Order
            Order order = new Order();
            // 获取所有商品
            List<ShoppingCart> cartlist = extracted(request, shoppingCartId);
            // 判断所选商品是否为Null
            if (cartlist != null && !cartlist.isEmpty()) {
                // 循环所购买的商品
                for (int i = 0; i < cartlist.size(); i++) {
                    if (cartlist.get(i).getFitId() != null) {
                        if (thirdId.equals(cartlist.get(i).getGoodsGroupVo().getThirdId())) {
                            OrderGoods og = null;
                            // 获取此套装下的所有货品
                            List<GoodsProductVo> goodsProducts = siteGoodsProductService.queryDetailByGroupId(cartlist.get(i).getFitId());
                            for (int j = 0; j < goodsProducts.size(); j++) {
                                og = new OrderGoods();
                                og.setGoodsInfoNum(cartlist.get(i).getGoodsNum());
                                og.setDelFlag("0");
                                og.setBuyTime(new Date());
                                og.setEvaluateFlag("0");
                                og.setGoodsInfoPrice(BigDecimal.valueOf(0));
                                if (j > 0) {
                                    og.setGoodsInfoOldPrice(BigDecimal.valueOf(0));
                                } else {
                                    og.setGoodsInfoOldPrice(cartlist.get(i).getGoodsGroupVo().getGroupPreferamount());
                                }
                                og.setGoodsInfoId(goodsProducts.get(j).getGoodsInfoId());
                                og.setGoodsId(goodsProducts.get(j).getGoodsId());
                                og.setGoodsInfoPrice(og.getGoodsInfoOldPrice());
                                // 最终售价
                                og.setGoodsInfoPrice(og.getGoodsInfoOldPrice());
                                // 小计金额
                                og.setGoodsInfoSumPrice(og.getGoodsInfoPrice().multiply(BigDecimal.valueOf(cartlist.get(i).getGoodsNum())));
                                oglist.add(og);

                            }
                        }
                    } else {
                        // 判断购买商品是否属于此商家
                        if (thirdId.equals(cartlist.get(i).getGoodsDetailBean().getProductVo().getThirdId())) {
                            // 初始化订单商品信息
                            OrderGoods og = new OrderGoods();
                            // 设置数量
                            og.setGoodsInfoNum(cartlist.get(i).getGoodsNum());
                            // 设置删除标记
                            og.setDelFlag("0");
                            // 设置购买时间
                            og.setBuyTime(new Date());
                            // 设置收货地区
                            og.setDistinctId(cartWareUtil.getDistrictId());
                            og.setEvaluateFlag("0");
                            // 设置商品原价
                            if ("1".equals(vip)) {
                                og.setGoodsInfoOldPrice(cartlist.get(i).getGoodsDetailBean().getProductVo().getGoodsInfoVipPrice());
                            } else {
                                og.setGoodsInfoOldPrice(cartlist.get(i).getGoodsDetailBean().getProductVo().getGoodsInfoPreferPrice());
                            }
                            // 设置货品Id
                            og.setGoodsInfoId(cartlist.get(i).getGoodsDetailBean().getProductVo().getGoodsInfoId());
                            // 设置商品ID
                            og.setGoodsId(cartlist.get(i).getGoodsDetailBean().getProductVo().getGoodsId());
                            // 设置促销ID
                            og.setGoodsMarketingId(cartlist.get(i).getMarketingId());
                            // 设置活动ID
                            og.setGoodsActiveMarketingId(cartlist.get(i).getMarketingActivityId());

                            // 设置货品总价格（数量*价格）
                            og.setGoodsInfoSumPrice(og.getGoodsInfoOldPrice().multiply(BigDecimal.valueOf(cartlist.get(i).getGoodsNum())));
                            // 获取促销信息
                            Marketing market = cartlist.get(i).getMarketing();

                            CalcStockUtil calcStockUtil = new CalcStockUtil();
                            calcStockUtil.setIsThird(cartlist.get(i).getGoodsDetailBean().getProductVo().getIsThird());
                            calcStockUtil.setDistinctId(cartWareUtil.getDistrictId());
                            // 商品id
                            calcStockUtil.setProductId(cartlist.get(i).getGoodsDetailBean().getProductVo().getGoodsInfoId());
                            // 减去库存
                            calcStockUtil.setStock(Integer.parseInt(og.getGoodsInfoNum().toString()));
                            calcStockUtils.add(calcStockUtil);
                            // 货品赠送的优惠券
                            List<OrderGoodsInfoCoupon> orderGoodsInfoCouponList = new ArrayList<OrderGoodsInfoCoupon>();

                            // 货品赠送的赠品
                            List<OrderGoodsInfoGift> orderGoodsInfoGiftList = new ArrayList<OrderGoodsInfoGift>();
                            if (market != null) {
                                // 促销
                                og.setGoodsMarketingId(market.getMarketingId());
                                // 直降
                                if ("1".equals(market.getCodexType())) {
                                    PriceOffMarketing pm = market.getPriceOffMarketing();
                                    // 数量
                                    BigDecimal num = BigDecimal.valueOf(og.getGoodsInfoNum());

                                    // 优惠价格
                                    og.setGoodsCouponPrice(BigDecimal.valueOf(0));

                                    if ("1".equals(vip)) {
                                        if (og.getGoodsInfoOldPrice().subtract(pm.getOffVipValue()).signum() <= 0) {
                                            og.setGoodsInfoOldPrice(new BigDecimal("0.01"));
                                        } else {
                                            og.setGoodsInfoOldPrice(og.getGoodsInfoOldPrice().subtract(pm.getOffVipValue()));
                                        }
                                    } else {
                                        if (og.getGoodsInfoOldPrice().subtract(pm.getOffValue()).signum() <= 0) {
                                            og.setGoodsInfoOldPrice(new BigDecimal("0.01"));
                                        } else {
                                            og.setGoodsInfoOldPrice(og.getGoodsInfoOldPrice().subtract(pm.getOffValue()));
                                        }
                                    }

                                    // 最终售价
                                    og.setGoodsInfoPrice(og.getGoodsInfoOldPrice());
                                    // 小计金额
                                    og.setGoodsInfoSumPrice(og.getGoodsInfoPrice().multiply(num));
                                    og.setHaveCouponStatus("0");
                                    og.setHaveGiftStatus("0");
                                }

                                // 买折扣 折扣多少
                                if ("4".equals(market.getCodexType())) {
                                    DiscountMarketing dm = market.getDiscountMarketing();
                                    // 数量
                                    BigDecimal num = BigDecimal.valueOf(og.getGoodsInfoNum());
                                    BigDecimal a = BigDecimal.valueOf(1);
                                    // 总优惠 优惠金额=原价*折扣*数量
                                    og.setGoodsCouponPrice(BigDecimal.valueOf(0));
                                    // 最终售价 = 原价-原价*(1-折扣)
                                    og.setGoodsInfoOldPrice(og.getGoodsInfoOldPrice().subtract(og.getGoodsInfoOldPrice().multiply(a.subtract(dm.getDiscountValue()))));
                                    if ((og.getGoodsInfoOldPrice().subtract(new BigDecimal(0.01))).signum() <= 0) {
                                        og.setGoodsInfoOldPrice(new BigDecimal(0.01));
                                    }
                                    og.setGoodsInfoPrice(og.getGoodsInfoOldPrice());

                                    // 小计金额
                                    og.setGoodsInfoSumPrice(og.getGoodsInfoPrice().multiply(num));
                                    // 设置是否有优惠券
                                    og.setHaveCouponStatus("0");
                                    // 设置是否有赠品
                                    og.setHaveGiftStatus("0");
                                }
                                // 满减优惠
                                if ("5".equals(market.getCodexType())) {
                                    FullbuyReduceMarketing fm = market.getFullbuyReduceMarketing();

                                    // 数量
                                    BigDecimal num = BigDecimal.valueOf(og.getGoodsInfoNum());
                                    // 最终售价
                                    og.setGoodsInfoPrice(og.getGoodsInfoOldPrice());

                                    if ("1".equals(vip) && og.getGoodsInfoPrice().multiply(num).subtract(fm.getVipFullPrice()).longValue() >= 0) {

                                        if (og.getGoodsInfoPrice().multiply(num).subtract(fm.getVipReducePrice()).signum() > 0) {
                                            // 优惠金额
                                            og.setGoodsCouponPrice(fm.getVipReducePrice());
                                            // 小计金额
                                            og.setGoodsInfoSumPrice(og.getGoodsInfoPrice().multiply(num).subtract(fm.getVipReducePrice()));
                                        } else {
                                            // 优惠金额
                                            og.setGoodsCouponPrice(og.getGoodsInfoPrice().multiply(num).subtract(new BigDecimal(0.01)));
                                            og.setGoodsInfoSumPrice(new BigDecimal(0.01));
                                        }

                                    } else if ("0".equals(vip) && og.getGoodsInfoPrice().multiply(num).subtract(fm.getFullPrice()).longValue() >= 0) {

                                        if (og.getGoodsInfoPrice().multiply(num).subtract(fm.getReducePrice()).signum() > 0) {
                                            // 优惠金额
                                            og.setGoodsCouponPrice(fm.getReducePrice());
                                            // 小计金额
                                            og.setGoodsInfoSumPrice(og.getGoodsInfoPrice().multiply(num).subtract(fm.getReducePrice()));
                                        } else {
                                            // 优惠金额
                                            og.setGoodsCouponPrice(og.getGoodsInfoPrice().multiply(num).subtract(new BigDecimal(0.01)));
                                            og.setGoodsInfoSumPrice(new BigDecimal(0.01));

                                        }

                                    } else {
                                        // 优惠金额
                                        og.setGoodsCouponPrice(BigDecimal.valueOf(0));
                                        // 小计金额
                                        og.setGoodsInfoSumPrice(og.getGoodsInfoPrice().multiply(num));
                                    }

                                    // 设置是否有优惠券
                                    og.setHaveCouponStatus("0");
                                    // 设置是否有赠品
                                    og.setHaveGiftStatus("0");

                                }

                                // 满折
                                if ("8".equals(market.getCodexType())) {
                                    FullbuyDiscountMarketing fdm = market.getFullbuyDiscountMarketing();
                                    // 数量
                                    BigDecimal num = BigDecimal.valueOf(og.getGoodsInfoNum());
                                    BigDecimal a = BigDecimal.valueOf(1);
                                    // 最终售价 原价-（原价 * 折扣）
                                    BigDecimal sums = og.getGoodsInfoOldPrice().multiply(num);
                                    if (sums.subtract(fdm.getFullPrice()).longValue() >= 0) {
                                        // 价格
                                        og.setGoodsInfoPrice(og.getGoodsInfoOldPrice());
                                        // 计算总价
                                        BigDecimal sumPrice = og.getGoodsInfoPrice().multiply(num);
                                        // 计算优惠价格
                                        BigDecimal yhprice = (og.getGoodsInfoPrice().multiply(num)).multiply(a.subtract(fdm.getFullbuyDiscount()));
                                        yhprice = yhprice.setScale(2, BigDecimal.ROUND_HALF_UP);
                                        // 小计
                                        if ((sumPrice.subtract(yhprice).subtract(new BigDecimal("0.01"))).signum() <= 0) {
                                            og.setGoodsInfoSumPrice(new BigDecimal("0.01"));
                                            og.setGoodsCouponPrice(og.getGoodsInfoPrice().multiply(num).subtract(new BigDecimal(0.01)));
                                        } else {
                                            og.setGoodsInfoSumPrice(sumPrice.subtract(yhprice));
                                            // 优惠金额 优惠金额 = (（1-折扣）*总价格)
                                            og.setGoodsCouponPrice(yhprice);
                                        }
                                    } else {
                                        // 价格
                                        og.setGoodsInfoPrice(og.getGoodsInfoOldPrice());
                                        // 小计
                                        og.setGoodsInfoSumPrice(og.getGoodsInfoPrice().multiply(num));

                                        // 优惠金额
                                        og.setGoodsCouponPrice(BigDecimal.valueOf(0));
                                    }
                                    // 设置是否有优惠券
                                    og.setHaveCouponStatus("0");
                                    // 设置是否有赠品
                                    og.setHaveGiftStatus("0");

                                }

                                // 满购不同商品件 打折
                                if ("13".equals(market.getCodexType())) {
                                    FullbuyNoDiscountMarketing fndm = market.getFullbuyNoDiscountMarketing();
                                    // 数量
                                    BigDecimal num = BigDecimal.valueOf(og.getGoodsInfoNum());
                                    BigDecimal a = BigDecimal.valueOf(1);
                                    // 最终售价 原价-（原价 * 折扣）
                                    if (fndm.getIsMeetCondition() != null && Integer.parseInt(fndm.getIsMeetCondition()) == 1) {
                                        // 价格
                                        og.setGoodsInfoPrice(og.getGoodsInfoOldPrice());
                                        BigDecimal sumPrice = og.getGoodsInfoPrice().multiply(num);
                                        BigDecimal yhprice = (og.getGoodsInfoPrice().multiply(num)).multiply(a.subtract(fndm.getPackagebuyDiscount()));
                                        yhprice = yhprice.setScale(2, BigDecimal.ROUND_HALF_UP);
                                        // 小计
                                        if ((sumPrice.subtract(yhprice).subtract(new BigDecimal("0.01"))).signum() <= 0) {
                                            og.setGoodsInfoSumPrice(new BigDecimal("0.01"));
                                            og.setGoodsCouponPrice(og.getGoodsInfoPrice().multiply(num).subtract(new BigDecimal(0.01)));
                                        } else {
                                            og.setGoodsInfoSumPrice(sumPrice.subtract(yhprice));
                                            // 优惠金额 优惠金额 = (（1-折扣）*总价格)
                                            og.setGoodsCouponPrice(yhprice);
                                        }
                                    } else {
                                        // 价格
                                        og.setGoodsInfoPrice(og.getGoodsInfoOldPrice());
                                        // 小计
                                        og.setGoodsInfoSumPrice(og.getGoodsInfoPrice().multiply(num));

                                        // 优惠金额
                                        og.setGoodsCouponPrice(BigDecimal.valueOf(0));
                                    }

                                    og.setHaveCouponStatus("0");
                                    og.setHaveGiftStatus("0");
                                }

                                // 折扣促销
                                if ("15".equals(market.getCodexType())) {
                                    PreDiscountMarketing pdm = market.getPreDiscountMarketing();
                                    // 数量
                                    BigDecimal num = BigDecimal.valueOf(og.getGoodsInfoNum());
                                    og.setGoodsInfoPrice(og.getGoodsInfoOldPrice());
                                    // 最终售价 原价-折后价
                                    if (pdm.getDiscountPrice() != null) {
                                        if (og.getGoodsInfoPrice().subtract(pdm.getDiscountPrice()).signum() >= 0) {
                                            BigDecimal sumPrice = og.getGoodsInfoPrice().multiply(num);
                                            BigDecimal yhprice = pdm.getDiscountPrice().multiply(num);
                                            yhprice = yhprice.setScale(2, BigDecimal.ROUND_HALF_UP);
                                            // 小计
                                            if ((yhprice.subtract(new BigDecimal("0.01"))).signum() <= 0) {
                                                og.setGoodsInfoSumPrice(new BigDecimal("0.01"));
                                                og.setGoodsCouponPrice(og.getGoodsInfoPrice().multiply(num).subtract(new BigDecimal(0.01)));
                                            } else {
                                                og.setGoodsInfoSumPrice(yhprice);
                                                // 优惠金额 优惠金额 = (（1-折扣）*总价格)
                                                og.setGoodsCouponPrice(sumPrice.subtract(yhprice));
                                            }
                                        } else {
                                            // 价格
                                            og.setGoodsInfoPrice(og.getGoodsInfoOldPrice());
                                            // 小计
                                            og.setGoodsInfoSumPrice(og.getGoodsInfoPrice().multiply(num));

                                            // 优惠金额
                                            og.setGoodsCouponPrice(BigDecimal.valueOf(0));
                                        }
                                    } else {
                                        // 价格
                                        og.setGoodsInfoPrice(og.getGoodsInfoOldPrice());
                                        BigDecimal sumPrice = og.getGoodsInfoPrice().multiply(num);
                                        BigDecimal zhprice = (og.getGoodsInfoPrice().multiply(num)).multiply(pdm.getDiscountInfo());
                                        zhprice = zhprice.setScale(2, BigDecimal.ROUND_HALF_UP);
                                        // 小计
                                        if ((zhprice).subtract(new BigDecimal("0.01")).signum() <= 0) {
                                            og.setGoodsInfoSumPrice(new BigDecimal("0.01"));
                                            og.setGoodsCouponPrice(og.getGoodsInfoPrice().multiply(num).subtract(new BigDecimal(0.01)));
                                        } else {
                                            og.setGoodsInfoSumPrice(zhprice);
                                            // 优惠金额 优惠金额 = (（1-折扣）*总价格)
                                            og.setGoodsCouponPrice(sumPrice.subtract(zhprice));
                                        }
                                    }

                                    og.setHaveCouponStatus("0");
                                    og.setHaveGiftStatus("0");
                                }

                                // 满赠赠品
                                if (market.getFullbuyPresentMarketing() != null) {
                                    if ("6".equals(market.getCodexType())) {
                                        // 数量
                                        BigDecimal num = BigDecimal.valueOf(og.getGoodsInfoNum());
                                        BigDecimal bd = BigDecimal.valueOf(0);
                                        // 优惠价格
                                        og.setGoodsCouponPrice(bd);
                                        // 最终售价
                                        og.setGoodsInfoPrice(og.getGoodsInfoOldPrice().subtract(bd));
                                        // 小计金额
                                        og.setGoodsInfoSumPrice(og.getGoodsInfoPrice().multiply(num));

                                    }
                                    if (og.getGoodsInfoSumPrice().subtract(market.getFullbuyPresentMarketing().getFullPrice()).longValue() >= 0) {
                                        List<Gift> giftlist = market.getGiftList();
                                        if (giftlist != null && !giftlist.isEmpty()) {
                                            og.setHaveGiftStatus("1");
                                            // 如果有赠品
                                            for (Gift gift : giftlist) {
                                                // 获取赠品
                                                OrderGoodsInfoGift ogi = new OrderGoodsInfoGift();
                                                ogi.setGiftId(gift.getGiftId());
                                                orderGoodsInfoGiftList.add(ogi);
                                            }
                                        } else {
                                            og.setHaveGiftStatus("0");
                                        }
                                    } else {
                                        og.setHaveGiftStatus("0");
                                    }
                                    og.setHaveCouponStatus("0");

                                }
                                // 满赠优惠券
                                if (market.getFullbuyCouponMarketing() != null) {
                                    if ("7".equals(market.getCodexType())) {
                                        // 数量
                                        BigDecimal num = BigDecimal.valueOf(og.getGoodsInfoNum());
                                        BigDecimal bd = BigDecimal.valueOf(0);
                                        // 优惠价格
                                        og.setGoodsCouponPrice(bd);
                                        // 最终售价
                                        og.setGoodsInfoPrice(og.getGoodsInfoOldPrice().subtract(bd));
                                        // 小计金额
                                        og.setGoodsInfoSumPrice(og.getGoodsInfoPrice().multiply(num));

                                    }
                                    if (og.getGoodsInfoSumPrice().subtract(market.getFullbuyCouponMarketing().getFullPrice()).longValue() >= 0) {
                                        // 查询所有此促销的赠品级联list
                                        List<Coupon> clist = market.getCouponList();

                                        if (clist != null && !clist.isEmpty()) {
                                            og.setHaveCouponStatus("1");
                                            for (Coupon coupon : clist) {
                                                OrderGoodsInfoCoupon oic = new OrderGoodsInfoCoupon();
                                                oic.setCouponId(coupon.getCouponId());
                                                Coupon cp = couponService.selectOneCouponNoByCouponIdAndUpdateNoIsGet(coupon.getCouponId(), (Long) request.getSession

                                                        ().getAttribute(CUSTOMERID));
                                                oic.setCouponNo(cp.getCodeNo());
                                                orderGoodsInfoCouponList.add(oic);
                                            }
                                        } else {
                                            og.setHaveCouponStatus("0");
                                        }

                                    } else {
                                        og.setHaveCouponStatus("0");
                                    }
                                    og.setHaveGiftStatus("0");

                                }
                                // 买送赠品
                                if (market.getGiftList() != null && market.getFullbuyPresentMarketing() == null) {

                                    if ("2".equals(market.getCodexType())) {
                                        // 数量
                                        BigDecimal num = BigDecimal.valueOf(og.getGoodsInfoNum());

                                        BigDecimal bd = BigDecimal.valueOf(0);
                                        // 优惠价格
                                        og.setGoodsCouponPrice(bd);
                                        // 最终售价
                                        og.setGoodsInfoPrice(og.getGoodsInfoOldPrice().subtract(bd));
                                        // 小计金额
                                        og.setGoodsInfoSumPrice(og.getGoodsInfoPrice().multiply(num));

                                    }
                                    List<Gift> giftlist = market.getGiftList();
                                    if (giftlist != null && !giftlist.isEmpty()) {
                                        og.setHaveGiftStatus("1");
                                        for (Gift gift : giftlist) {
                                            OrderGoodsInfoGift ogi = new OrderGoodsInfoGift();
                                            ogi.setGiftId(gift.getGiftId());
                                            orderGoodsInfoGiftList.add(ogi);
                                        }
                                    } else {
                                        og.setHaveGiftStatus("0");
                                    }
                                    og.setHaveCouponStatus("0");

                                }

                                // 买送优惠券
                                if (market.getCouponList() != null && market.getFullbuyCouponMarketing() == null) {
                                    if ("3".equals(market.getCodexType())) {
                                        // 数量
                                        BigDecimal num = BigDecimal.valueOf(og.getGoodsInfoNum());

                                        BigDecimal bd = BigDecimal.valueOf(0);
                                        // 优惠价格
                                        og.setGoodsCouponPrice(bd);
                                        // 最终售价
                                        og.setGoodsInfoPrice(og.getGoodsInfoOldPrice().subtract(bd));
                                        // 小计金额
                                        og.setGoodsInfoSumPrice(og.getGoodsInfoPrice().multiply(num));

                                    }
                                    // 查询所有此促销的赠品级联list
                                    List<Coupon> clist = market.getCouponList();
                                    if (clist != null && !clist.isEmpty()) {
                                        og.setHaveCouponStatus("1");
                                        for (Coupon coupon : clist) {
                                            OrderGoodsInfoCoupon oic = new OrderGoodsInfoCoupon();
                                            oic.setCouponId(coupon.getCouponId());
                                            Coupon cp = couponService.selectOneCouponNoByCouponIdAndUpdateNoIsGet(coupon.getCouponId(), (Long) request.getSession

                                                    ().getAttribute(CUSTOMERID));
                                            if (cp != null) {
                                                oic.setCouponNo(cp.getCodeNo());
                                                orderGoodsInfoCouponList.add(oic);
                                            }
                                        }
                                    } else {
                                        og.setHaveCouponStatus("0");
                                    }

                                    og.setHaveGiftStatus("0");
                                }

                            } else {
                                // 判断该商品是否参加折扣
                                if ("1".equals(((ShoppingCart) cartlist.get(i)).getGoodsDetailBean().getProductVo().getIsCustomerDiscount())) {
                                    // 查询
                                    CustomerPointLevel customerPointLevel = customerPointLevelMapper.selectCustomerLevelById((Long) request.getSession().getAttribute

                                            (CustomerConstantStr.CUSTOMERID));
                                    ((ShoppingCart) cartlist.get(i)).setPointDiscount(customerPointLevel.getPointDiscount());
                                    og.setGoodsInfoOldPrice(og.getGoodsInfoOldPrice().multiply(customerPointLevel.getPointDiscount()));
                                }
                                // 最终售价 原价-（原价 * 折扣）
                                og.setGoodsInfoPrice(og.getGoodsInfoOldPrice());
                                BigDecimal bd = BigDecimal.valueOf(0);
                                // 数量
                                BigDecimal num = BigDecimal.valueOf(og.getGoodsInfoNum());

                                // 小计金额
                                og.setGoodsInfoSumPrice(og.getGoodsInfoPrice().multiply(num));

                                // 优惠金额 优惠金额 = 数量 *（原价*折扣）
                                og.setGoodsCouponPrice(bd);
                                og.setHaveCouponStatus("0");
                                og.setHaveGiftStatus("0");
                            }
                            og.setOrderGoodsInfoCouponList(orderGoodsInfoCouponList);
                            og.setOrderGoodsInfoGiftList(orderGoodsInfoGiftList);
                            oglist.add(og);
                        }
                    }
                }
                // 订单交易总金额
                BigDecimal sumPrice = BigDecimal.valueOf(0);
                // 原始总计额
                BigDecimal sumOldPrice = BigDecimal.valueOf(0);
                // 总优惠金额
                BigDecimal prePrice = BigDecimal.valueOf(0);
                // 满购不同商品件数 金额
                BigDecimal preFullPrice = BigDecimal.valueOf(0);
                if (oglist != null && !oglist.isEmpty()) {
                    for (int i = 0; i < oglist.size(); i++) {
                        sumPrice = sumPrice.add(oglist.get(i).getGoodsInfoSumPrice());
                        BigDecimal num = BigDecimal.valueOf(oglist.get(i).getGoodsInfoNum());
                        sumOldPrice = sumOldPrice.add(oglist.get(i).getGoodsInfoOldPrice().multiply(num));
                        if (oglist.get(i).getGoodsCouponPrice() != null) {
                            prePrice = prePrice.add(oglist.get(i).getGoodsCouponPrice());
                        }
                    }
                }
                // 判断是否有满购不同商品件数 金额的促销 “14”
                if (result.compareTo(BigDecimal.ZERO) != 0) {
                    preFullPrice = preFullPrice.add(result);
                }
                OrderMarketing orderMarketing = null;
                // 订单促销
                for (int i = 0; i < thirdIds.length; i++) {
                    if ((Long) thirdId == (long) thirdIds[i]) {
                        boolean isT = false;
                        if (isT) {
                            orderMarketing = new OrderMarketing();
                            orderMarketing.setMarketingId(marketingIdNew[i]);

                            Marketing market = marketService.marketingDetail(marketingIdNew[i]);
                            orderMarketing.setMarketingType(market.getCodexType());
                            if (market != null) {
                                // 直降
                                if ("1".equals(market.getCodexType())) {
                                    // 金额计算
                                    PriceOffMarketing pm = market.getPriceOffMarketing();
                                    sumPrice = sumPrice.subtract(pm.getOffValue());
                                    prePrice = prePrice.add(pm.getOffValue());
                                }

                                // 买折扣 折扣多少
                                if ("4".equals(market.getCodexType())) {
                                    // 金额计算
                                    DiscountMarketing fm = market.getDiscountMarketing();
                                    prePrice = prePrice.add(sumPrice.subtract(sumPrice.multiply(fm.getDiscountValue())));
                                    sumPrice = sumOldPrice.multiply(fm.getDiscountValue());
                                }
                                // 满减优惠
                                if ("5".equals(market.getCodexType())) {
                                    // 金额计算
                                    FullbuyReduceMarketing bm = market.getFullbuyReduceMarketing();
                                    BigDecimal sum = BigDecimal.valueOf(0);
                                    sum = sum.add(sumPrice);
                                    if (sum.subtract(bm.getFullPrice()).longValue() > 0) {
                                        sumPrice = sumPrice.subtract(bm.getReducePrice());
                                        prePrice = prePrice.add(prePrice);

                                    }
                                }

                                // 满折
                                if ("8".equals(market.getCodexType())) {
                                    // 金额计算
                                    FullbuyDiscountMarketing fd = market.getFullbuyDiscountMarketing();
                                    BigDecimal sum = BigDecimal.valueOf(0);
                                    sum = sum.add(sumPrice);
                                    if (sum.subtract(fd.getFullPrice()).longValue() > 0) {
                                        prePrice = prePrice.add(sumPrice.subtract(sumPrice.multiply(fd.getFullbuyDiscount())));
                                        sumPrice = sumOldPrice.multiply(fd.getFullbuyDiscount());
                                    }
                                }
                                // 买送赠品
                                if (market.getGiftList() != null) {
                                    // 有赠品
                                    gifts = market.getGiftList();
                                    orderMarketing.setHaveGiftStatus("1");
                                }

                                // 买送优惠券
                                if (market.getCouponList() != null) {
                                    // 有优惠券
                                    coupons = market.getCouponList();
                                    orderMarketing.setHaveCouponStatus("1");
                                }
                                // 满赠赠品
                                if (market.getFullbuyPresentMarketing() != null) {
                                    // 计算满赠
                                    BigDecimal sum = BigDecimal.valueOf(0);
                                    sum = sum.add(sumPrice);
                                    if (sum.subtract(market.getFullbuyPresentMarketing().getFullPrice()).longValue() > 0) {
                                        gifts = market.getGiftList();
                                        orderMarketing.setHaveGiftStatus("1");

                                    } else {
                                        gifts = null;
                                        orderMarketing.setHaveGiftStatus("0");
                                    }

                                }
                                // 满赠优惠券
                                if (market.getFullbuyCouponMarketing() != null) {
                                    BigDecimal sum = BigDecimal.valueOf(0);
                                    sum = sum.add(sumPrice);
                                    if (sum.subtract(market.getFullbuyCouponMarketing().getFullPrice()).longValue() > 0) {
                                        coupons = market.getCouponList();
                                        orderMarketing.setHaveCouponStatus("1");

                                    } else {
                                        gifts = null;
                                        orderMarketing.setHaveGiftStatus("0");
                                    }

                                }

                            }

                        }

                    }
                }

                if (codeNo != null && !"".equals(codeNo)) {
                    Coupon coupon = couponService.selectCouponByCodeNo(codeNo);

                    if (coupon != null) {
                        // 直降
                        if ("1".equals(coupon.getCouponRulesType())) {
                            // 计算交易价格减去金额
                            sumPrice = sumPrice.subtract(coupon.getCouponStraightDown().getDownPrice());
                            // 优惠金额+金额
                            prePrice = prePrice.add(coupon.getCouponStraightDown().getDownPrice());
                            order.setOrderPrePriceOrder(coupon.getCouponStraightDown().getDownPrice());
                        }
                        // 满减
                        if ("2".equals(coupon.getCouponRulesType())) {
                            // 计算交易价格减去金额
                            sumPrice = sumPrice.subtract(coupon.getCouponFullReduction().getReductionPrice());
                            // 优惠金额+金额
                            prePrice = prePrice.add(coupon.getCouponFullReduction().getReductionPrice());
                            order.setOrderPrePriceOrder(coupon.getCouponFullReduction().getReductionPrice());
                        }

                    }

                }

                CustomerAddress ca = customerServiceInterface.queryCustAddress(custAddress);
                WareHouse ware = productWareMapper.findWare(ca.getDistrict().getDistrictId());
                // 计算商品运费模板金额
                int num = 0;
                BigDecimal weight = new BigDecimal(0);
                Long distributionId = null;
                if (request.getParameter("distributionId") != null) {
                    distributionId = ("0").equals(thirdId.toString()) ? Long.valueOf(request.getParameter("distributionId")) : null;
                } else {
                    distributionId = null;
                }

                // 判断是否包邮
                Long baoyou = 0L;
                // 计算运费
                BigDecimal expressPrice = new BigDecimal(0);
                if (cartlist1 != null && !cartlist1.isEmpty()) {
                    for (ShoppingCart sc : cartlist1) {
                        if (sc.getFitId() == null) {
                            if (thirdId.equals(sc.getGoodsDetailBean().getProductVo().getThirdId())) {
                                // 判断是否包邮
                                baoyou = marketService.queryByCreatimeMarketings(sc.getGoodsInfoId(), 6L, sc.getGoodsDetailBean().getProductVo().getGoods().getCatId(), sc
                                        .getGoodsDetailBean().getBrand().getBrandId());
                                if (baoyou == 0) {
                                    num += Integer.parseInt(sc.getGoodsNum().toString());
                                    GoodsProductVo g = siteGoodsProductService.queryProductByProductId(sc.getGoodsInfoId());
                                    weight = weight.add(g.getGoodsInfoWeight().multiply(new BigDecimal(sc.getGoodsNum())));
                                    // 1：此商品卖家包邮 0：此商品买家自费
                                    if (g.getIsMailBay() == 0) {
                                        // 获取商品的快递价格
                                        expressPrice = freightTemplateService.getExpressPrice(distributionId, ca.getCity().getCityId(), Long.valueOf(thirdId.toString()), num,
                                                weight);
                                        // 所有的商品快递价格相加
                                    }
                                }
                            }
                        } else {
                            if (thirdId.equals(sc.getGoodsGroupVo().getThirdId())) {
                                // 套装运费计算
                                GoodsGroupVo goodsGroupVo = goodsGroupService.queryVoByPrimaryKey(sc.getFitId());
                                // 遍历套装下的商品
                                for (int j = 0; j < goodsGroupVo.getProductList().size(); j++) {
                                    weight = weight.add(goodsGroupVo.getProductList().get(j).getProductDetail().getGoodsInfoWeight().multiply(new BigDecimal(sc.getGoodsNum())));
                                    num += Integer.parseInt(sc.getGoodsNum().toString());
                                }
                                expressPrice = freightTemplateService.getExpressPrice(distributionId, ca.getCity().getCityId(), Long.valueOf(thirdId.toString()), num, weight);
                            }
                        }
                    }

                }

                FreightExpress fe = null;
                Long comId = null;
                String comName = "";
                if (distributionId != null) {
                    // 获取物流信息
                    fe = freightExpressMapper.selectFreightExpressByDistributionId(distributionId);
                    comId = fe.getSysLogisticsCompany().getLogComId();
                    comName = fe.getSysLogisticsCompany().getName();
                } else {
                    if (!"0".equals(thirdId.toString())) {
                        // 获取物流信息 获取第三方默认物流信息
                        fe = freightTemplateService.selectFreightExpressByDistriThirdId((Long) thirdId);
                        if (fe != null && fe.getExpress() != null) {
                            comId = fe.getExpress().getShoreExpId();
                            comName = fe.getExpress().getExpName();
                        }
                    }
                }
                // 封装物流信息
                OrderExpress oe = new OrderExpress();
                if (fe != null) {
                    oe.setExpressId(comId);
                    oe.setExpressName(comName);
                }
                oe.setDelFlag("0");
                // 配送方式 0 快递配送 1 上门自提
                oe.setExpressTypeId(0L);

                order.setExpressPrice(expressPrice);
                // 再次计算金额
                sumOldPrice = sumOldPrice.add(order.getExpressPrice());
                sumPrice = sumPrice.add(order.getExpressPrice());
                if (ware != null) {
                    // 收获地址信息
                    order.setWareName(ware.getWareName());
                    // 库存ID
                    order.setWareId(ware.getWareId());
                } else {
                    // 返回购物车
                    return new ArrayList<Order>();
                }

                // 用户默认收货地址ID
                order.setShoppingAddrId(custAddress);
                // 省
                order.setShippingProvince(ca.getProvince().getProvinceName());
                // 市
                order.setShippingCity(ca.getCity().getCityName());
                // 区
                order.setShippingCounty(ca.getDistrict().getDistrictName());
                // 区ID
                order.setShippingCountyId(ca.getDistrict().getDistrictId());
                // 详细地址
                order.setShippingAddress(ca.getAddressDetail());
                // 收货人
                order.setShippingPerson(ca.getAddressName());
                // 电话
                order.setShippingPhone(ca.getAddressPhone());
                // 手机
                order.setShippingMobile(ca.getAddressMoblie());
                // 备注
                order.setCustomerRemark(customerRemark);
                // 邮编
                order.setShippingPostcode(ca.getAddressZip());
                // 临时账号
                isTempCust(request, ca);
                // 随机数
                Random random2 = new Random();
                int randomNum = random2.nextInt(101);
                // 子订单号
                String orderCode = UtilDate.mathString(new Date()) + randomNum;
                // 插入订单促销id
                order.setOrderCode(orderCode);
                order.setOrderOldCode(orderOldCode);
                // 支付方式
                order.setPayId(chPay);
                // 发票信息
                order.setInvoiceType(chVoinceType);
                // 如果为1，普通发票，设置发票抬头和内容
                if ("1".equals(chVoinceType)) {
                    // 发票抬头
                    order.setInvoiceTitle(chInvoiceTitle);
                    // 发票内容
                    order.setInvoiceContent(chInvoiceContent);
                }
                order.setDelFlag("0");
                // 如果为2，表示货到付款，订单状态改为已付款
                // 如果为2,未付款（货到付款）
                if (chPay == 2) {
                    // 订单状态改为未支付
                    order.setOrderStatus("0");
                    // 货到付款
                    order.setOrderLinePay("0");
                } else {
                    // 订单状态
                    // 在线支付
                    order.setOrderLinePay("1");
                    order.setOrderStatus("0");
                }

                // 订单优惠金额 判断值降优惠金额大于订单总金额 设置订单金额为1分钱
                if (1 == prePrice.compareTo(sumOldPrice)) {
                    BigDecimal bigDecimal = new BigDecimal("0.01");
                    order.setOrderPrice(bigDecimal);
                }

                // boss商品兑换积分
                if (thirdId.equals(0L) && null != duiHuanJiFen) {
                    /* 积分兑换订单金额 */
                    // 更新当前会员的总积分信息 当前登录成功的会员
                    Long customerId = (Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID);
                    // 获得当前用户的积分信息
                    CustomerPoint customerPoint = couponService.selectCustomerPointByCustomerId(customerId);
                    // 判断当前积分是否够兑换
                    if (customerPoint.getPointSum() < duiHuanJiFen) {
                        return new ArrayList<Order>();
                    }
                    // 积分兑换规则
                    PointSet pointSet = this.couponService.selectPointSet();
                    if (null != pointSet) {
                        // 转换类型
                        BigDecimal zhuanhuan = new BigDecimal(duiHuanJiFen);
                        // 根据积分兑换规则 计算积分兑换金额
                        BigDecimal disparityPrice = zhuanhuan.multiply(pointSet.getConsumption());
                        //
                        disparityPrice = disparityPrice.divide(new BigDecimal(10));
                        // 对兑换处的价格进行四舍五入
                        BigDecimal jiFenDuiHuan = disparityPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
                        // 计算最后订单金额
                        sumPrice = sumPrice.subtract(jiFenDuiHuan);
                        // 对兑换处的价格进行四舍五入
                        sumPrice = sumPrice.setScale(2, BigDecimal.ROUND_HALF_UP);

                        // 修改会员的积分
                        this.updatePoint(customerId, duiHuanJiFen);
                        this.insertExchangeCusmomer(customerId, orderCode, duiHuanJiFen, disparityPrice, pointSet.getConsumption());
                    }
                }

                order.setOrderIntegral(duiHuanJiFen);
                // 订单总金额
                order.setOrderPrice(sumPrice.subtract(preFullPrice));
                // 原始总额
                order.setOrderOldPrice(sumOldPrice);
                // 总优惠金额
                order.setOrderPrePrice(prePrice);
                order.setBusinessId(Long.valueOf(thirdId.toString()));
                // 订单使用的优惠券
                order.setCouponNo(codeNo);
                order.setCustomerId((Long) request.getSession().getAttribute(CUSTOMERID));
                order.setCreateTime(new Date());

                // 插入订单主表
                int f = orderService.insertOrder(order);

                // 插叙返回的ID
                if (f == 1) {
                    // 获取刚刚插入的订单ID
                    Long orderId = orderService.selectLastId();
                    // 判断是否有订单促销
                    if (orderMarketing != null) {
                        orderMarketing.setOrderId(orderId);
                        orderService.insertSelective(orderMarketing);
                        if (gifts != null && !gifts.isEmpty()) {
                            Long lastOrderMarketId = orderService.selectOrderMarketLastId();
                            List<OrderGift> orderG = new ArrayList<OrderGift>();
                            OrderGift orderGift = null;
                            for (int i = 0; i < gifts.size(); i++) {
                                orderGift = new OrderGift();
                                orderGift.setGiftId(gifts.get(i).getGiftId());
                                orderGift.setOrderMarketingId(lastOrderMarketId);
                                orderG.add(orderGift);
                            }
                            orderService.insertOrderInfoGift(orderG);
                        }
                        if (coupons != null && !coupons.isEmpty()) {
                            Long lastOrderMarketId = orderService.selectOrderMarketLastId();
                            List<OrderCoupon> orderCoupons = new ArrayList<OrderCoupon>();
                            OrderCoupon orderCoupon = null;
                            for (int i = 0; i < coupons.size(); i++) {
                                orderCoupon = new OrderCoupon();

                                CouponNo couponNo = couponNoService.selectNoByCouponIdByStatus(coupons.get(i).getCouponId());
                                if (couponNo != null) {
                                    orderCoupon.setCouponId(coupons.get(i).getCouponId());
                                    orderCoupon.setOrderMarketingId(lastOrderMarketId);
                                    orderCoupon.setCouponNo(couponNo.getCodeNo());
                                    orderCoupons.add(orderCoupon);

                                }
                            }
                            if (orderCoupons != null && !orderCoupons.isEmpty()) {
                                orderService.insertCouponInfoGift(orderCoupons);
                            }
                        }
                    }
                    // 修改优惠券已经使用
                    couponNoService.updateCodeIsUse(codeNo, orderCode);
                    order.setOrderId(orderId);
                    maps.add(order);

                    // 插入物流信息
                    oe.setOrderId(orderId);
                    orderExpressMapper.insertOrderExpress(oe);
                    // 循环设置货品级联ID信息
                    if (oglist != null && !oglist.isEmpty()) {
                        for (int i = 0; i < oglist.size(); i++) {
                            oglist.get(i).setOrderId(orderId);
                            // 插入货品
                            orderGoodsMapper.insertOrderGoodsInfo(oglist.get(i));
                            // 获取货品级联ID
                            Long orderGoodsId = orderGoodsMapper.selectLastId();
                            if ("1".equals(oglist.get(i).getHaveCouponStatus())) {
                                List<OrderGoodsInfoCoupon> clist = oglist.get(i).getOrderGoodsInfoCouponList();
                                if (clist != null && !clist.isEmpty()) {
                                    for (int j = 0; j < clist.size(); j++) {
                                        clist.get(j).setOrderGoodsId(orderGoodsId);
                                    }
                                    // 批量插入订单商品的赠送优惠券信息
                                    orderGoodsInfoCouponMapper.insertOrderInfoCoupon(clist);
                                }
                            }

                            if ("1".equals(oglist.get(i).getHaveGiftStatus())) {
                                List<OrderGoodsInfoGift> glist = oglist.get(i).getOrderGoodsInfoGiftList();
                                if (glist != null && !glist.isEmpty()) {
                                    for (int j = 0; j < glist.size(); j++) {
                                        glist.get(j).setOrderGoodsId(orderGoodsId);
                                    }
                                    // 批量插入订单商品的赠送赠品信息
                                    orderGoodsInfoGiftMapper.insertOrderInfoGift(glist);
                                }
                            }

                        }
                    }
                    if ("0".equals(order.getOrderLinePay())) {
                        synOrderService.SynOrder(orderId);
                    }
                }

            }

        }

        // 修改所有购买商品为已经删除
        shoppingCartService.deleteShoppingCartByIds(request, shoppingCartId);
        request.getSession().setAttribute(IS_TEMP_CUST, "1");
        try {
            goodsProductService.minStock(calcStockUtils);

            return maps;
        } finally {
            calcStockUtils = null;
            cartlist1 = null;
            list = null;
            maps = null;
            marketingIdNew = null;

        }

    }

    /**
     * @param customerId     会员ID
     * @param orderCode      订单单号
     * @param duiHuanJiFen   兑换的积分
     * @param disparityPrice 兑换的几个
     * @param consumption    积分兑换规则
     * @return
     */
    public int insertExchangeCusmomer(Long customerId, String orderCode, Long duiHuanJiFen, BigDecimal disparityPrice, BigDecimal consumption) {
        int result = 0; // 保存执行的结果
        // 新增积分兑换信息
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(CUSTOMERID, customerId);
            map.put("exchangePoint", duiHuanJiFen);
            map.put("exchangeTime", new Date());
            map.put("orderCode", orderCode);
            map.put("disparityPrice", disparityPrice);
            map.put("consumption", consumption);
            result = siteGoodsProductService.insertExchangeCusmomer(map);
        } catch (Exception e) {
            LOGGER.error("新增积分兑换记录错误:" + e);
        }
        return result;
    }

    /**
     * 修改会员总积分
     *
     * @param customerId
     * @param point
     * @return
     */
    public int updatePoint(Long customerId, Long point) {
        int result = 0; // 保存执行的结果
        // 根据会员ID获取积分对象
        CustomerPoint customerPoint = couponService.selectCustomerPointByCustomerId(customerId);
        if (null != customerPoint) {
            // 更新当前登录会员的总积分
            customerPoint.setPointSum(customerPoint.getPointSum() - point);
            // 更新积分对象
            result = couponService.updateCustomerPoint(customerPoint);
        }
        return result;
    }

    /**
     * 查询购物车购买的商品信息
     *
     * @param request
     * @param shoppingCartId
     * @return
     */
    private List<ShoppingCart> extracted(HttpServletRequest request, Long[] shoppingCartId) {
        return shoppingCartService.searchByProduct(request, shoppingCartId);
    }

    /**
     * 判断是否是临时用户
     *
     * @param request
     * @param ca
     * @throws IOException
     */
    public void isTempCust(HttpServletRequest request, CustomerAddress ca) throws IOException {
        Object isTempCust = request.getSession().getAttribute(IS_TEMP_CUST);
        // 判断是否是临时用户
        if (isTempCust != null && "0".equals(isTempCust)) {
            if (loginService.checkCustomerExists(request, ca.getAddressMoblie(), PASSWORD) == 2) {
                CustomerAllInfo user = new CustomerAllInfo();
                user.setCustomerUsername(ca.getAddressMoblie());
                user.setCustomerNickname(ca.getAddressMoblie());
                user.setCustomerId((Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID));
                user.setInfoMobile(ca.getAddressMoblie());
                user.setIsTempCust("0");
                // 修改会员的手机号为当前手机
                customerServiceInterface.updateByPrimaryKey(user);
                // 绑定手机
                customerServiceInterface.updateCustInfoByPrimaryKey(user);
            } else if (loginService.checkCustomerExists(request, ca.getAddressMoblie(), PASSWORD) == 1) {
                loginService.checkCustomerExists(request, ca.getAddressMoblie(), PASSWORD);
            }
        }
    }

    /**
     * 确认支付
     *
     * @param orderId
     * @return
     */
    @Override
    public int payOrder(Long orderId) {
        return orderService.payOrder(orderId);
    }

    /**
     * 已提交至银行等待处理
     *
     * @param orderId
     * @return orderId
     */
    public int subBankPayOrder(Long orderId) {
        return orderService.subBankPayOrder(orderId);
    }

    /**
     * 查询订单包裹表
     *
     * @param orderId 订单id
     * @return
     */
    public List<OrderContainerUtil> getExpressNo(Long orderId) {
        List<OrderContainerUtil> containerUtils = new ArrayList<OrderContainerUtil>();
        // 返回运送方式
        List<OrderContainerRelation> expressList = relationMapper.getExpressNo(orderId);
        // 订单所属的配送方式
        OrderExpress express = orderExpressMapper.selectOrderExpress(orderId);
        String kuaiDiName = iExpressConfBiz.queryKuaidi100CodeByExpressId(express.getExpressId());
        // 订单包裹和物流信息
        List<OrderContainerRelation> containerRelations = orderService.queryContainerRalation(orderId);
        // 订单单个包裹
        OrderContainerUtil order = null;
        OrderExpress orderExpress = orderExpressMapper.selectOrderExpress(orderId);
        // 订单所属的配送信息
        for (int i = 0; i < expressList.size(); i++) {
            String kuaidi = KuaiDiUtil.execLookKuaiDi(kuaiDiName, expressList.get(i).getExpressNo());
            order = new OrderContainerUtil();
            order.setContainerRelations(containerRelations.get(i));
            order.setExpressName(orderExpress.getExpressName());
            order.setExpress(kuaidi);
            containerUtils.add(order);
        }

        try {
            return containerUtils;
        } finally {
            order = null;
            containerUtils = null;
            expressList = null;
            kuaiDiName = null;
            express = null;
        }
    }

    /**
     * 查询订单信息
     *
     * @param orderId
     * @return
     */
    @Override
    public Order getPayOrder(Long orderId) {
        return orderService.getPayOrder(orderId);
    }

    /**
     * 查询订单根据COde
     *
     * @param orderCode
     * @return
     */
    @Override
    public Order getPayOrderByCode(String orderCode) {
        return orderService.getPayOrderByCode(orderCode);
    }

    /**
     * 根据订单编号订单下所有商品并随机返回一个
     *
     * @param orderId
     * @param status  当前已经选中的索引位置
     * @return
     */
    @Override
    public Map<String, Object> queryGoodsProduceByOrderId(Long orderId, Long status) {
        Map<String, Object> map = new HashMap<String, Object>();
        int rodomId = 0;
        List<OrderGoods> orders = orderService.queryOrderGoods(orderId);
        if (status == null) {
            rodomId = 0;
        } else {
            if (status == (orders.size() - 1)) {
                rodomId = 0;
            } else if (orders.size() > 1) {
                rodomId++;
            }
        }
        map.put("list", siteGoodsProductService.queryTopSalesByProductId(orders.get(rodomId).getGoodsInfoId(), 6));
        map.put("status", rodomId);
        return map;
    }

    /**
     * 根据总单编号查询订单信息
     *
     * @param orderOldCode
     * @return
     */
    @Override
    public List<Order> getPayOrderByOldCode(String orderOldCode) {
        return orderService.getPayOrderByOldCode(orderOldCode);
    }

    public CouponNoService getCouponNoService() {
        return couponNoService;
    }

    @Resource(name = "CouponNoService")
    public void setCouponNoService(CouponNoService couponNoService) {
        this.couponNoService = couponNoService;
    }

    public CouponService getCouponService() {
        return couponService;
    }

    @Resource(name = "CouponService")
    public void setCouponService(CouponService couponService) {
        this.couponService = couponService;
    }

    public OrderGoodsInfoGiftMapper getOrderGoodsInfoGiftMapper() {
        return orderGoodsInfoGiftMapper;
    }

    @Resource(name = "OrderGoodsInfoGiftMapper")
    public void setOrderGoodsInfoGiftMapper(OrderGoodsInfoGiftMapper orderGoodsInfoGiftMapper) {
        this.orderGoodsInfoGiftMapper = orderGoodsInfoGiftMapper;
    }

    public OrderGoodsInfoCouponMapper getOrderGoodsInfoCouponMapper() {
        return orderGoodsInfoCouponMapper;
    }

    @Resource(name = "OrderGoodsInfoCouponMapper")
    public void setOrderGoodsInfoCouponMapper(OrderGoodsInfoCouponMapper orderGoodsInfoCouponMapper) {
        this.orderGoodsInfoCouponMapper = orderGoodsInfoCouponMapper;
    }

    public OrderGoodsMapper getOrderGoodsMapper() {
        return orderGoodsMapper;
    }

    @Resource(name = "OrderGoodsMapper")
    public void setOrderGoodsMapper(OrderGoodsMapper orderGoodsMapper) {
        this.orderGoodsMapper = orderGoodsMapper;
    }

    public OrderExpressMapper getOrderExpressMapper() {
        return orderExpressMapper;
    }

    @Resource(name = "OrderExpressMapper")
    public void setOrderExpressMapper(OrderExpressMapper orderExpressMapper) {
        this.orderExpressMapper = orderExpressMapper;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    @Resource(name = "OrderService")
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    public CustomerServiceInterface getCustomerServiceInterface() {
        return customerServiceInterface;
    }

    @Resource(name = "customerServiceSite")
    public void setCustomerServiceInterface(CustomerServiceInterface customerServiceInterface) {
        this.customerServiceInterface = customerServiceInterface;
    }

    public ProductWareMapper getProductWareMapper() {
        return productWareMapper;
    }

    @Resource(name = "ProductWareMapper")
    public void setProductWareMapper(ProductWareMapper productWareMapper) {
        this.productWareMapper = productWareMapper;
    }

    public ExpressConfBizImpl getExpressConfBizImpl() {
        return expressConfBizImpl;
    }

    @Resource(name = "expressConfBizImpl")
    public void setExpressConfBizImpl(ExpressConfBizImpl expressConfBizImpl) {
        this.expressConfBizImpl = expressConfBizImpl;
    }

    public ShoppingCartService getShoppingCartService() {
        return shoppingCartService;
    }

    @Resource(name = "ShoppingCartService")
    public void setShoppingCartService(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    public OrderContainerRelationMapper getRelationMapper() {
        return relationMapper;
    }

    @Resource(name = "OrderContainerRelationMapper")
    public void setRelationMapper(OrderContainerRelationMapper relationMapper) {
        this.relationMapper = relationMapper;
    }

    public IExpressConfBiz getiExpressConfBiz() {
        return iExpressConfBiz;
    }

    @Resource(name = "expressConfBizImpl")
    public void setiExpressConfBiz(IExpressConfBiz iExpressConfBiz) {
        this.iExpressConfBiz = iExpressConfBiz;
    }

    public com.ningpai.site.goods.service.GoodsProductService getSiteGoodsProductService() {
        return siteGoodsProductService;
    }

    @Resource(name = "HsiteGoodsProductService")
    public void setSiteGoodsProductService(com.ningpai.site.goods.service.GoodsProductService siteGoodsProductService) {
        this.siteGoodsProductService = siteGoodsProductService;
    }

    public ProductWareService getProductWareService() {
        return productWareService;
    }

    @Resource(name = "ProductWareService")
    public void setProductWareService(ProductWareService productWareService) {
        this.productWareService = productWareService;
    }

    public com.ningpai.goods.service.GoodsProductService getGoodsProductService() {
        return goodsProductService;
    }

    @Resource(name = "GoodsProductService")
    public void setGoodsProductService(com.ningpai.goods.service.GoodsProductService goodsProductService) {
        this.goodsProductService = goodsProductService;
    }

    /**
     * 得到详细的购物车集合
     *
     * @param cartId
     * @return
     */
    public List<ShoppingCart> getDetailShoppingcart(Long[] cartId) {
        List<ShoppingCart> shoplist = shoppingCartMapper.shopCartListByIds(Arrays.asList(cartId));
        for (int i = 0; i < shoplist.size(); i++) {
            if (shoplist.get(i).getFitId() == null) {

                // 查询商品详细
                shoplist.get(i).setGoodsDetailBean(
                        siteGoodsProductService.queryDetailBeanByProductId(((ShoppingCart) shoplist.get(i)).getGoodsInfoId(), ((ShoppingCart) shoplist.get(i)).getDistinctId()));
                // 查询购物车里选择的促销信息
                shoplist.get(i).setMarketing(marketService.marketingDetail(shoplist.get(i).getMarketingActivityId(), shoplist.get(i).getGoodsInfoId()));
            } else {

                // 如果商品是套装
                GoodsGroupVo goodsGroupVo = goodsGroupService.queryVoByPrimaryKey(((ShoppingCart) shoplist.get(i)).getFitId());
                if (null != goodsGroupVo) {
                    shoplist.get(i).setGoodsGroupVo(goodsGroupVo);
                    shoplist.get(i).setThirdId(goodsGroupVo.getThirdId());
                }
            }
        }
        return shoplist;
    }

    /**
     * 查询收货地区仓库是否有库存
     *
     * @param request
     * @param shoppingCartId
     * @param distinctId
     * @return
     */
    @Override
    public Map<String, Object> checkProduct(HttpServletRequest request, Long[] shoppingCartId, Long distinctId) {
        Map<String, Object> map = new HashMap<String, Object>();
        // 库存
        List<Long> warestockList = new ArrayList<Long>();
        // productId
        List<Long> productIdList = new ArrayList<Long>();
        // 获取所有所有选中商品信息
        List<ShoppingCart> cartlist1 = getDetailShoppingcart(shoppingCartId);

        // 货品仓库信息
        ProductWare product = new ProductWare();
        if (cartlist1 != null && !cartlist1.isEmpty()) {
            for (ShoppingCart sc : cartlist1) {
                // 判断商品是否是套装
                if (sc.getFitId() == null) {
                    if (0 == sc.getThirdId()) {
                        product = productWareService.queryProductWareByProductIdAndDistinctId(sc.getGoodsInfoId(), distinctId);
                        productIdList.add(sc.getGoodsInfoId());
                        if (product != null) {
                            warestockList.add(product.getWareStock());
                        } else {
                            warestockList.add(0L);
                        }
                    } else {
                        productIdList.add(sc.getGoodsInfoId());
                        // 第三方货品
                        GoodsProductVo goodsvo = goodsProductMapper.queryDetailByProductId(sc.getGoodsInfoId());
                        if (null != goodsvo) {
                            warestockList.add(goodsvo.getGoodsInfoStock());
                        } else {
                            warestockList.add(0L);
                        }
                    }
                } else {

                    // 套装商品
                    GoodsGroupVo goodsGroupVo = goodsGroupService.queryVoByPrimaryKey(sc.getFitId());
                    // 遍历套装下的商品
                    for (int j = 0; j < goodsGroupVo.getProductList().size(); j++) {
                        product = productWareService.queryProductWareByProductIdAndDistinctId(goodsGroupVo.getProductList().get(j).getProductId(), distinctId);
                        productIdList.add(goodsGroupVo.getProductList().get(j).getProductId());
                        if (product != null) {
                            warestockList.add(product.getWareStock());
                        } else {
                            warestockList.add(0L);
                        }
                    }

                }

            }
            map.put("warestockList", warestockList);
            map.put("productIdList", productIdList);
        }

        return map;
    }

    /**
     * 定时任务
     */
    @Override
    public void receiptConfirmation() {
        // 得到所有已发货的订单信息
        orderService.receiptConfirmation();
    }

}
