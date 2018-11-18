package com.ningpai.site.shoppingcart.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.ningpai.common.util.ConstantUtil;
import com.ningpai.common.util.wxap.util.TenpayUtil;
import com.ningpai.coupon.bean.Coupon;
import com.ningpai.coupon.bean.CouponRange;
import com.ningpai.coupon.bean.ParamIds;
import com.ningpai.coupon.service.CouponService;
import com.ningpai.customer.bean.Customer;
import com.ningpai.customer.bean.CustomerPoint;
import com.ningpai.customer.bean.CustomerPointLevel;
import com.ningpai.customer.dao.CustomerPointLevelMapper;
import com.ningpai.customer.service.CustomerServiceMapper;
import com.ningpai.freighttemplate.bean.FreightExpress;
import com.ningpai.freighttemplate.bean.FreightExpressAll;
import com.ningpai.freighttemplate.bean.FreightTemplate;
import com.ningpai.freighttemplate.dao.FreightExpressMapper;
import com.ningpai.freighttemplate.dao.FreightTemplateMapper;
import com.ningpai.goods.bean.GoodsProduct;
import com.ningpai.goods.bean.ProductWare;
import com.ningpai.goods.dao.GoodsProductMapper;
import com.ningpai.goods.dao.ProductWareMapper;
import com.ningpai.goods.service.GoodsGroupService;
import com.ningpai.goods.service.ProductWareService;
import com.ningpai.goods.vo.GoodsGroupReleProductVo;
import com.ningpai.goods.vo.GoodsGroupVo;
import com.ningpai.marketing.bean.Groupon;
import com.ningpai.marketing.bean.Marketing;
import com.ningpai.marketing.bean.PreDiscountMarketing;
import com.ningpai.marketing.dao.*;
import com.ningpai.marketing.service.MarketingService;
import com.ningpai.order.service.OrderService;
import com.ningpai.other.bean.CustomerAllInfo;
import com.ningpai.site.customer.service.CustomerServiceInterface;
import com.ningpai.site.customer.vo.CustomerConstantStr;
import com.ningpai.site.goods.bean.GoodsDetailBean;
import com.ningpai.site.goods.service.GoodsProductService;
import com.ningpai.site.goods.vo.GoodsProductVo;
import com.ningpai.site.login.service.LoginService;
import com.ningpai.site.order.service.SiteOrderService;
import com.ningpai.site.order.util.OrderUtil;
import com.ningpai.site.shoppingcart.bean.*;
import com.ningpai.site.shoppingcart.dao.ShoppingCartMapper;
import com.ningpai.site.shoppingcart.service.ShoppingCartService;
import com.ningpai.site.shoppingcart.util.ShopCartValueUtil;
import com.ningpai.system.bean.BasicSet;
import com.ningpai.system.bean.PointSet;
import com.ningpai.system.dao.BasicSetMapper;
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
 * 购物车方法实现
 *
 * @author NingPai-lih
 * @since 2014年10月27日15:47:08
 */
@Service("ShoppingCartService")
public class ShoppingCartServiceImpl implements ShoppingCartService {

    /**
     * 记录日志对象
     */
    private static final MyLogger LOGGER = new MyLogger(ShoppingCartServiceImpl.class);
    /**
     * customerId
     */
    private static final String CUSTOMERID = "customerId";
    /**
     * myshoppingcart
     */
    private static final String MYSHOPPINGCART = "myshoppingcart";
    /**
     * 加载cookie中的购物车信息
     */
    private static final String COOKIE = "加载cookie中的购物车信息";
    /**
     * 110012
     */
    private static final String PROINFO = "110012";
    /**
     * _npstore_mId
     */
    private static final String NPSTOREMID = "_npstore_mId";
    /**
     * goodsId
     */
    private static final String GOODSID = "goodsId";
    /**
     * marketingId
     */
    private static final String MARKETINGID = "marketingId";
    /**
     * distinctId
     */
    private static final String DISTINCTID = "distinctId";
    /**
     * shoplist
     */
    private static final String SHOPLIST = "shoplist";
    /**
     * countCondition
     */
    private static final String COUNTCONDITION = "countCondition";
    /**
     * _npstore_shopcar
     */
    private static final String NPSTORESHOPCAR = "_npstore_shopcar";
    /**
     * _npstore_shopstatus
     */
    private static final String NPSTORESHOPSTATUS = "_npstore_shopstatus";
    /**
     * 000000
     */
    private static final String PASSWORD = "000000";
    /**
     * thirds
     */
    private static final String THIRDS = "thirds";
    /**
     * marketinglist
     */
    private static final String MARKETINGLIST = "marketinglist";

    /**
     * bossSumPrice
     */
    private static final String BOSSSUMPRICE = "bossSumPrice";
    private static final String SUMOLDPRICE = "sumOldPrice";
    private static final String SUMPRICE = "sumPrice";
    /**
     * 数量
     */
    private String num = "";
    /**
     * 购物车service
     */
    @Resource(name = "ShoppingCartService")
    private ShoppingCartService shoppingCartService;


    @Resource(name = "GrouponMapper")
    private GrouponMapper grouponMapper;

    /**
     * 会员信息
     */
    @Resource(name = "customerServiceSite")
    private CustomerServiceInterface customerServiceInterface;

    /**
     * 注册
     */
    @Resource(name = "customerServiceMapper")
    private CustomerServiceMapper customerServiceMapper;

    /**
     * 登陆
     */
    @Resource(name = "loginServiceSite")
    private LoginService loginService;
    /**
     * 购物车mapper
     */
    @Resource(name = "ShoppingCartMapper")
    private ShoppingCartMapper shoppingCartMapper;


    @Resource(name = "RushCustomerMapper")
    private RushCustomerMapper rushCustomerMapper;
    /**
     * 促销serivce
     */
    @Resource(name = "MarketingService")
    private MarketingService marketService;
    /**
     * 促销mapper
     */
    @Resource(name = "MarketingMapper")
    private MarketingMapper marketingMapper;
    /**
     * 折扣促销mapper
     */
    @Resource(name = "PreDiscountMarketingMapper")
    private PreDiscountMarketingMapper preDiscountMarketingMapper;
    /**
     * 货品Service
     */
    @Resource(name = "HsiteGoodsProductService")
    private GoodsProductService goodsProductService;
    /**
     * 优惠券service
     */
    @Resource(name = "CouponService")
    private CouponService couponService;
    /**
     * 商品组合Service
     */
    @Resource(name = "GoodsGroupService")
    private GoodsGroupService goodsGroupService;

    /**
     * 货品库存表
     */
    @Resource(name = "ProductWareMapper")
    private ProductWareMapper productWareMapper;
    /**
     * 货品信息mapper
     */
    @Resource(name = "GoodsProductMapper")
    private GoodsProductMapper goodsProductMapper;
    /**
     * 货品分仓Service
     */
    @Resource(name = "ProductWareService")
    private ProductWareService productWareService;
    /**
     * 订单Service
     */
    @Resource(name = "SiteOrderService")
    private SiteOrderService siteOrderService;
    /**
     * 订单service
     */
    @Resource(name = "OrderService")
    private OrderService orderser;
    /**
     * 默认地址service
     */
    @Resource(name = "DefaultAddressService")
    private DefaultAddressService addressService;
    /**
     * 区县Service
     */
    @Resource(name = "DistrictService")
    private DistrictService districtService;
    /**
     * 会员积分等级mapper
     */
    @Resource(name = "customerPointLevelMapper")
    private CustomerPointLevelMapper customerPointLevelMapper;

    /**
     * 满件数多少钱
     */
    @Resource(name = "FullbuyNoCountMarketingMapper")
    private FullbuyNoCountMarketingMapper fullbuyNoCountMarketingMapper;

    /**
     * 满x件打y折
     */
    @Resource(name = "FullbuyNoDiscountMarketingMapper")
    private FullbuyNoDiscountMarketingMapper fullbuyNoDiscountMarketingMapper;
    /**
     * 运费模板mapper
     */
    @Resource(name = "FreightTemplateMapper")
    private FreightTemplateMapper freightTemplateMapper;
    /**
     * 运送方式mapper
     */
    @Resource(name = "FreightExpressMapper")
    private FreightExpressMapper freightExpressMapper;
    /**
     * 站点基本信息mapper
     */
    @Resource(name = "basicSetMapper")
    private BasicSetMapper basicSetMapper;

    /**
     * 修改会员积分
     *
     * @param customerId
     * @param point
     * @return
     */
    @Override
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
     * 查询购物车
     *
     * @param pb
     * @param request
     * @return
     */
    public PageBean shoppingCart(PageBean pb, HttpServletRequest request) {
        Long customerId = (Long) request.getSession().getAttribute(CUSTOMERID);
        // 如果已经登录
        if (customerId != null) {
            pb.setUrl(MYSHOPPINGCART);
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
                    ((ShoppingCart) shoplist.get(i)).setGoodsDetailBean(goodsProductService.queryDetailBeanByProductId(((ShoppingCart) shoplist.get(i)).getGoodsInfoId(),
                            ((ShoppingCart) shoplist.get(i)).getDistinctId()));

                }

                for (int i = 0; i < shoplist.size(); i++) {
                    ((ShoppingCart) shoplist.get(i)).setMarketingList(marketService.selectMarketingByGoodsInfoId(((ShoppingCart) shoplist.get(i)).getGoodsDetailBean()
                            .getProductVo().getGoodsInfoId(), ((ShoppingCart) shoplist.get(i)).getGoodsDetailBean().getBrand().getBrandId(), ((ShoppingCart) shoplist.get(i))
                            .getGoodsDetailBean().getProductVo().getGoods().getCatId()));
                }

                for (int i = 0; i < shoplist.size(); i++) {
                    Marketing marketing = null;
                    boolean isT = true;
                    if (((ShoppingCart) shoplist.get(i)).getMarketingId() != null) {
                        marketing = marketService.marketingDetail(((ShoppingCart) shoplist.get(i)).getMarketingId());
                        isT = false;
                    }
                    if (((ShoppingCart) shoplist.get(i)).getMarketingActivityId() != null) {
                        marketing = marketService.marketingDetailByActive(marketing, ((ShoppingCart) shoplist.get(i)).getMarketingActivityId(), isT);
                    }
                    ((ShoppingCart) shoplist.get(i)).setMarketing(marketing);
                }

            }
            pb.setList(shoplist);

        } else {
            // 如果未登录状态获取购物车
            try {
                List<ShopCarUtil> list = loadCookShopCar(request);
                if (list != null && !list.isEmpty()) {
                    pb.setUrl(MYSHOPPINGCART);
                    pb.setPageSize(5);

                    Integer totalCount = 1;

                    pb.setRows(Integer.parseInt(totalCount == null ? 0 + "" : totalCount + ""));
                    if (pb.getPageNo() > pb.getLastPageNo()) {
                        pb.setPageNo(pb.getLastPageNo());
                    }
                    if (pb.getPageNo() == 0) {
                        pb.setPageNo(1);
                    }

                    List<Object> shoplist = new ArrayList<Object>();
                    for (int i = 0; i < list.size(); i++) {
                        ShoppingCart sc = new ShoppingCart();
                        sc.setGoodsInfoId(list.get(i).getProductId());
                        sc.setGoodsNum(Long.valueOf(list.get(i).getGoodsNum()));
                        sc.setMarketing(null);
                        sc.setDistinctId(list.get(i).getDistinctId());
                        sc.setShoppingCartId(Long.valueOf(i));
                        shoplist.add(sc);
                    }
                    if (shoplist != null && !shoplist.isEmpty()) {
                        for (int i = 0; i < shoplist.size(); i++) {
                            ((ShoppingCart) shoplist.get(i)).setGoodsDetailBean(goodsProductService.queryDetailBeanByProductId(((ShoppingCart) shoplist.get(i)).getGoodsInfoId(),
                                    list.get(i).getDistinctId()));
                        }

                        for (int i = 0; i < shoplist.size(); i++) {
                            ((ShoppingCart) shoplist.get(i)).setMarketingList(marketService.selectMarketingByGoodsInfoId(((ShoppingCart) shoplist.get(i)).getGoodsDetailBean()
                                            .getProductVo().getGoodsInfoId(), ((ShoppingCart) shoplist.get(i)).getGoodsDetailBean().getBrand().getBrandId(),
                                    ((ShoppingCart) shoplist.get(i)).getGoodsDetailBean().getProductVo().getGoods().getCatId()));
                        }

                        for (int i = 0; i < shoplist.size(); i++) {
                            Marketing marketing = null;
                            boolean isT = true;
                            if (((ShoppingCart) shoplist.get(i)).getMarketingId() != null && ((ShoppingCart) shoplist.get(i)).getMarketingId() > 0) {
                                marketing = marketService.marketingDetail(((ShoppingCart) shoplist.get(i)).getMarketingId());
                                isT = false;
                            }
                            if (((ShoppingCart) shoplist.get(i)).getMarketingActivityId() != null && ((ShoppingCart) shoplist.get(i)).getMarketingActivityId() > 0) {
                                marketing = marketService.marketingDetailByActive(marketing, ((ShoppingCart) shoplist.get(i)).getMarketingActivityId(), isT);
                            }
                            ((ShoppingCart) shoplist.get(i)).setMarketing(marketing);
                        }
                    }
                    pb.setList(shoplist);

                }

            } catch (UnsupportedEncodingException e) {
                LOGGER.error(COOKIE + e);
            }
        }

        return pb;
    }

    /**
     * mini购物车
     *
     * @param request
     * @return
     */
    @Override
    public MiniShoppingCart miniShoppingCart(HttpServletRequest request) {

        MiniShoppingCart miniShoppingCart = new MiniShoppingCart();
        Long customerId = (Long) request.getSession().getAttribute(CUSTOMERID);
        List<ShoppingCart> shoplist = new ArrayList<ShoppingCart>();
        // 如果已经登录
        if (customerId != null) {
            shoplist = shoppingCartMapper.shoppingCartMini(customerId);
            if (shoplist != null && !shoplist.isEmpty()) {
                for (int i = 0; i < shoplist.size(); i++) {
                    if (shoplist.get(i).getFitId() == null) {
                        shoplist.get(i).setGoodsDetailBean(goodsProductService.queryDetailBeanByProductId(shoplist.get(i).getGoodsInfoId(), shoplist.get(i).getDistinctId()));
                        if (shoplist.get(i).getGoodsDetailBean() != null && shoplist.get(i).getGoodsDetailBean().getProductVo() != null) {
                            shoplist.get(i).setMarketingList(
                                    marketService.selectMarketingByGoodsInfoId(shoplist.get(i).getGoodsDetailBean().getProductVo().getGoodsInfoId(), shoplist.get(i)
                                            .getGoodsDetailBean().getBrand().getBrandId(), shoplist.get(i).getGoodsDetailBean().getProductVo().getGoods().getCatId()));
                            if (shoplist.get(i).getMarketingId() != null && shoplist.get(i).getMarketingId() > 0) {
                                shoplist.get(i).setMarketing(marketService.marketingDetail(shoplist.get(i).getMarketingId()));
                            }
                        }
                    } else {
                        // 进入此处标示该购物车为套装，执行套装方法
                        GoodsGroupVo goodsGroupVo = goodsGroupService.queryVoByPrimaryKey(shoplist.get(i).getFitId());
                        shoplist.get(i).setGoodsGroupVo(goodsGroupVo);
                    }
                }
            }
        } else {
            List<ShopCarUtil> list = null;
            try {
                list = loadCookShopCar(request);
            } catch (UnsupportedEncodingException e) {
                LOGGER.error(COOKIE + e);
            }
            if (list != null && !list.isEmpty()) {
                for (int i = 0; i < list.size(); i++) {
                    ShoppingCart sc = new ShoppingCart();
                    if (list.get(i).getFitId() == null) {
                        sc.setGoodsInfoId(list.get(i).getProductId());
                    } else {
                        // 设置套装商品id
                        sc.setGoodsInfoId(Long.parseLong(PROINFO + list.get(i).getFitId()));
                        // 设置套装id
                        sc.setFitId(list.get(i).getFitId());
                    }
                    sc.setGoodsNum(Long.valueOf(list.get(i).getGoodsNum()));
                    sc.setMarketing(null);
                    sc.setShoppingCartId(Long.valueOf(i));
                    sc.setDistinctId(list.get(i).getDistinctId());
                    shoplist.add(sc);
                }
                if (shoplist != null && !shoplist.isEmpty()) {
                    for (int i = 0; i < shoplist.size(); i++) {
                        if (shoplist.get(i).getFitId() != null) {
                            // 进入此处标示该购物车为套装，执行套装方法
                            GoodsGroupVo goodsGroupVo = goodsGroupService.queryVoByPrimaryKey(shoplist.get(i).getFitId());
                            shoplist.get(i).setGoodsGroupVo(goodsGroupVo);
                        } else {
                            shoplist.get(i).setGoodsDetailBean(goodsProductService.queryDetailBeanByProductId(shoplist.get(i).getGoodsInfoId(),
                                    list.get(i).getDistinctId()));
                            /**
                             * add by 付陈林 2015年12月9 日
                             *增在在未登录的情况下也增加营销信息
                             * */
                            if (shoplist.get(i).getGoodsDetailBean() != null && shoplist.get(i).getGoodsDetailBean().getProductVo() != null) {
                                shoplist.get(i).setMarketingList(
                                        marketService.selectMarketingByGoodsInfoId(shoplist.get(i).getGoodsDetailBean().getProductVo().getGoodsInfoId(), shoplist.get(i)
                                                .getGoodsDetailBean().getBrand().getBrandId(), shoplist.get(i).getGoodsDetailBean().getProductVo().getGoods().getCatId()));
                                if (shoplist.get(i).getMarketingId() != null && shoplist.get(i).getMarketingId() > 0) {
                                    shoplist.get(i).setMarketing(marketService.marketingDetail(shoplist.get(i).getMarketingId()));
                                }
                            }
                            /**
                             * edit end
                             * */
                        }
                    }

                }
            }
        }
        List<MiniGoods> minilist = new ArrayList<MiniGoods>();

        if (shoplist != null && !shoplist.isEmpty()) {
            for (int i = 0; i < shoplist.size(); i++) {
                if (shoplist.get(i).getFitId() == null) {
                    if (shoplist.get(i).getGoodsDetailBean() != null && shoplist.get(i).getGoodsDetailBean().getProductVo() != null) {
                        MiniGoods mg = new MiniGoods();
                        mg.setShoppingCartId(shoplist.get(i).getShoppingCartId());
                        mg.setBuNum(shoplist.get(i).getGoodsNum());
                        if (shoplist.get(i).getGoodsDetailBean().getProductVo().getGoodsInfoImgId() != null
                                && !"".equals(shoplist.get(i).getGoodsDetailBean().getProductVo().getGoodsInfoImgId())) {
                            mg.setProductPic(shoplist.get(i).getGoodsDetailBean().getProductVo().getGoodsInfoImgId());
                        } else {
                            if (shoplist.get(i).getGoodsDetailBean().getProductVo().getImageList() != null
                                    && !shoplist.get(i).getGoodsDetailBean().getProductVo().getImageList().isEmpty()) {
                                mg.setProductPic(shoplist.get(i).getGoodsDetailBean().getProductVo().getImageList().get(0).getImageThumName());
                            }

                        }

                        mg.setMarketing(shoplist.get(i).getMarketing());
                        mg.setGoodsId(shoplist.get(i).getGoodsDetailBean().getProductVo().getGoodsId());
                        mg.setGoodsInfoId(shoplist.get(i).getGoodsDetailBean().getProductVo().getGoodsInfoId());
                        mg.setProductName(shoplist.get(i).getGoodsDetailBean().getProductVo().getProductName());
                        /**
                         * 修改商品价格
                         *add by 付陈林 2015年12月9日
                         * */
                         if(shoplist.get(i).getMarketingList()!=null&&shoplist.get(i).getMarketingList().size()>0){
                             for(Marketing marketing :shoplist.get(i).getMarketingList()){
                                //团购
                                if("10".equals(marketing.getCodexType())){
                                    if(marketing.getGroupon()!=null){
                                        mg.setProductPrice(marketing.getGroupon().getGrouponPrice());
                                        mg.setProductVipPrice(marketing.getGroupon().getGrouponVipPrice());
                                    }else{
                                        mg.setProductPrice(shoplist.get(i).getGoodsDetailBean().getProductVo().getGoodsInfoPreferPrice());
                                        mg.setProductVipPrice(shoplist.get(i).getGoodsDetailBean().getProductVo().getGoodsInfoVipPrice());
                                    }
                                }
                                //抢购
                                else if("11".equals(marketing.getCodexType())){
                                    if(marketing.getRushs()!=null&&marketing.getRushs().size()>0){
                                        mg.setProductPrice(marketing.getRushs().get(0).getRushPrice());
                                        mg.setProductVipPrice(marketing.getRushs().get(0).getRushVipPrice());
                                    }else{
                                        mg.setProductPrice(shoplist.get(i).getGoodsDetailBean().getProductVo().getGoodsInfoPreferPrice());
                                        mg.setProductVipPrice(shoplist.get(i).getGoodsDetailBean().getProductVo().getGoodsInfoVipPrice());
                                    }
                                }
                                //单品折扣
                                else if("15".equals(marketing.getCodexType())){
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
                                                goodsPrice= goodsPrice.multiply(premark.getDiscountInfo());
                                                goodsPrice = BigDecimal.valueOf(Double.valueOf(myformat.format(goodsPrice)));
                                                mg.setProductPrice(goodsPrice);
                                                //VIP
                                                goodsVipPrice= goodsVipPrice.multiply(premark.getVipdiscountInfo());
                                                goodsVipPrice = BigDecimal.valueOf(Double.valueOf(myformat.format(goodsVipPrice)));
                                            }
                                        }
                                        mg.setProductPrice(goodsPrice);
                                        mg.setProductVipPrice(goodsVipPrice);
                                    }else{
                                        mg.setProductPrice(shoplist.get(i).getGoodsDetailBean().getProductVo().getGoodsInfoPreferPrice());
                                        mg.setProductVipPrice(shoplist.get(i).getGoodsDetailBean().getProductVo().getGoodsInfoVipPrice());
                                    }
                                }else{
                                    mg.setProductPrice(shoplist.get(i).getGoodsDetailBean().getProductVo().getGoodsInfoPreferPrice());
                                    mg.setProductVipPrice(shoplist.get(i).getGoodsDetailBean().getProductVo().getGoodsInfoVipPrice());
                                }
                            }
                         }else{
                             mg.setProductPrice(shoplist.get(i).getGoodsDetailBean().getProductVo().getGoodsInfoPreferPrice());
                             mg.setProductVipPrice(shoplist.get(i).getGoodsDetailBean().getProductVo().getGoodsInfoVipPrice());
                         }
                        /**
                         * add end 付陈林 2015年12月9日
                         * **/
//                        mg.setProductPrice(shoplist.get(i).getGoodsDetailBean().getProductVo().getGoodsInfoPreferPrice());
//                        mg.setProductVipPrice(shoplist.get(i).getGoodsDetailBean().getProductVo().getGoodsInfoVipPrice());
                        minilist.add(mg);
                    }

                } else {
                    if (shoplist.get(i).getGoodsGroupVo() != null) {
                        MiniGoods mg = new MiniGoods();
                        MiniFit miniFit = new MiniFit();
                        miniFit.setFitName(shoplist.get(i).getGoodsGroupVo().getGroupName());
                        miniFit.setFitPrice(shoplist.get(i).getGoodsGroupVo().getGroupPreferamount());
                        mg.setBuNum(shoplist.get(i).getGoodsNum());
                        List<MiniGoods> lists = new ArrayList<MiniGoods>();
                        for (int j = 0; j < shoplist.get(i).getGoodsGroupVo().getProductList().size(); j++) {
                            if (shoplist.get(i).getGoodsGroupVo().getProductList().get(j).getProductDetail() != null) {
                                MiniGoods miniGoods = new MiniGoods();
                                miniGoods.setGoodsInfoId(shoplist.get(i).getGoodsGroupVo().getProductList().get(j).getProductDetail().getGoodsInfoId());
                                miniGoods.setBuNum(shoplist.get(i).getGoodsNum());
                                miniGoods.setProductName(shoplist.get(i).getGoodsGroupVo().getProductList().get(j).getProductDetail().getGoodsInfoName());
                                miniGoods.setProductPic(shoplist.get(i).getGoodsGroupVo().getProductList().get(j).getProductDetail().getGoodsInfoImgId());
                                miniGoods.setProductPrice(shoplist.get(i).getGoodsGroupVo().getProductList().get(j).getProductDetail().getGoodsInfoPreferPrice());
                                miniGoods.setProductVipPrice(shoplist.get(i).getGoodsGroupVo().getProductList().get(j).getProductDetail().getGoodsInfoVipPrice());
                                lists.add(miniGoods);
                            }
                        }
                        miniFit.setMiniGoods(lists);
                        mg.setMiniFit(miniFit);
                        mg.setGoodsInfoId(shoplist.get(i).getGoodsInfoId());
                        mg.setShoppingCartId(shoplist.get(i).getShoppingCartId());
                        mg.setFitId(shoplist.get(i).getFitId());
                        minilist.add(mg);
                    }
                }
            }
            miniShoppingCart.setMiniGoodsList(minilist);
        }

        return miniShoppingCart;
    }

    /**
     * 删除购物车商品
     *
     * @param shoppingCartId
     * @param goodsInfoId
     * @param request
     * @param response
     * @return
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
                LOGGER.error("删除cookie中的购物车信息错误" + e);
            }
            return 1;
        }
    }

    /**
     * 修改购买数量
     *
     * @param shoppingCartId
     * @param num
     * @return
     */
    @Override
    public int changeShoppingCartById(Long shoppingCartId, Long num) {
        ShoppingCart sc = new ShoppingCart();
        sc.setShoppingCartId(shoppingCartId);
        if (num != null && num == 0L) {
            sc.setGoodsNum(1L);
        } else {
            sc.setGoodsNum(num);
        }
        return shoppingCartMapper.changeShoppingCartById(sc);
    }

    /**
     * 修改优惠
     *
     * @param shoppingCartId
     *            购物车id
     * @param marketingId
     *            单品优惠分组id
     * @param marketingActivityId
     *            活动优惠分组id
     * @return
     */
    @Override
    public int changeShoppingCartMarket(Long shoppingCartId, Long marketingId, Long marketingActivityId, Long goodsGroupId) {
        ShoppingCart sc = new ShoppingCart();

        sc.setShoppingCartId(shoppingCartId);
        // 如果单品优惠id为0，则为不选中单品优惠
        if (marketingId == 0) {
            sc.setMarketingId(null);
        } else {
            sc.setMarketingId(marketingId);
        }
        // 如果活动优惠id为0，则为不选活动优惠
        if (marketingActivityId == 0) {
            sc.setMarketingActivityId(null);
        } else {
            sc.setMarketingActivityId(marketingActivityId);
        }
        // 如果团购优惠id为0，则为不选团购优惠 2015-12-30 wuyanbo add
        if (goodsGroupId == 0) {
            sc.setGoodsGroupId(null);
        } else {
            sc.setGoodsGroupId(marketingActivityId);
        }
        return shoppingCartMapper.changeShoppingCartMarket(sc);
    }

    /**
     * 修改优惠
     *
     * @param shoppingCartId
     *            购物车id
     * @param marketingId
     *            单品优惠分组id
     * @param marketingActivityId
     *            活动优惠分组id
     * @param request
     * @param response
     * @return
     */
    @Override
    public int changeShoppingCartMarket(Long shoppingCartId, Long marketingId, Long marketingActivityId, Long goodsGroupId, HttpServletRequest request, HttpServletResponse response) {
        Long customerId = (Long) request.getSession().getAttribute(CUSTOMERID);
        Long marketingId1 = marketingId;
        // 判断用户是否登录
        if (customerId != null) {
            ShoppingCart sc = new ShoppingCart();
            sc.setShoppingCartId(shoppingCartId);
            // 如果活动优惠id为0，则为不选活动优惠
            if (marketingActivityId == 0) {
                sc.setMarketingActivityId(null);
            } else {
                sc.setMarketingActivityId(marketingActivityId);
            }
            // 如果团购优惠id为0，则为不选团购优惠 2015-12-30 wuyanbo add
            if (goodsGroupId == 0) {
                sc.setGoodsGroupId(null);
            } else {
                sc.setGoodsGroupId(marketingActivityId);
            }
            // 如果单品优惠id为0，则为不选单品优惠 2015-12-30 wuyanbo add
            if (marketingId == 0) {
                sc.setMarketingId(null);
            } else {
                sc.setMarketingId(marketingId);
            }
            return shoppingCartMapper.changeShoppingCartMarket(sc);
        } else {
            if (marketingId == null) {
                marketingId1 = 0L;
            }
            String str=request.getParameter("goodsGroupMarketingId");

            if(!StringUtils.isEmpty(str)){
                goodsGroupId=Long.valueOf(str);
            }
            Cookie[] cookies = request.getCookies();
            StringBuilder newMid = new StringBuilder();
            if (null != cookies) {
                for (Cookie cookie : cookies) {
                    if (cookie != null && NPSTOREMID.equals(cookie.getName()) && cookie.getValue() != null && !"".equals(cookie.getValue())) {
                        String[] mIds = cookie.getValue().split("-");
                        // 取得所有cookie
                        for (int j = 0; j < mIds.length; j++) {
                            String[] mid = mIds[j].split("e");
                            // 判断是否该商品
                            if (mid[0] != null) {
                                if (mid[0].equals(shoppingCartId.toString())) {
                                    newMid.append(shoppingCartId);
                                    newMid.append("e");
                                    newMid.append(marketingId1);
                                    newMid.append("e");
                                    newMid.append(marketingActivityId);
                                    newMid.append("e");
                                    newMid.append(goodsGroupId);
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
                Cookie cookie = new Cookie(NPSTOREMID, newMid.toString());
                cookie.setMaxAge(15 * 24 * 3600);
                cookie.setPath("/");
                response.addCookie(cookie);
            }
            return 0;
        }

    }

    /**
     * 根据购物车里面的货品查询是否存在包邮的促销活动 返回list集合,不包邮的购物车
     *
     * @param cartList
     * @return
     * @author jiaodongzhi
     */
    @Override
    public List<ShoppingCart> getNobaoyouShoppingcarts(List<ShoppingCart> cartList, Long distinctId, String vip) {
        // 不包邮购物车或者参与包邮活动但是没有达到条件
        List<ShoppingCart> nobaoyou = new ArrayList<>();

        if (CollectionUtils.isNotEmpty(cartList)) {
            Map<Long, Object> markMap = new HashMap<>();
            Map<String, Object> map = new HashMap<>();
            // 参与包邮,生成新的购物车
            List<ShoppingCart> baoyou = new ArrayList<>();
            for (int i = 0; i < cartList.size(); i++) {
                Marketing marketing = null;
                if (cartList.get(i) != null && cartList.get(i).getGoodsInfoId() != null) {
                    // 从购物车里得到商品id,和包邮促销类型重新从数据库查询,防止当前(包邮促销)已经过期;
                    map.put(GOODSID, cartList.get(i).getGoodsInfoId());
                    map.put("codeType", "12");
                    List<Marketing> marketingList = marketingMapper.queryMarketingByGoodIdAndtypeList(map);
                    if (marketingList != null && !marketingList.isEmpty()) {
                        marketing = marketingList.get(0);
                    }
                    // 包邮
                    if (null != marketing) {
                        cartList.get(i).setMarketing(marketing);
                        baoyou.add(cartList.get(i));
                        // 促销分组
                        markMap.put(marketing.getMarketingId(), cartList.get(i).getThirdId());
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
                BigDecimal afterVipMoney = BigDecimal.valueOf(0);//VIP
                Long countgoods = 0L;
                List<ShoppingCart> shop = new ArrayList<>();
                for (int m = 0; m < shopThirdList.size(); m++) {
                    // 按third分组判断是否包邮
                    baoyou = shopThirdList.get(m);

                    for (Long markId : markMap.keySet()) {
                        ShoppingCart sc = new ShoppingCart();

                        for (int j = 0; j < baoyou.size(); j++) {
                            if (markId.equals(baoyou.get(j).getMarketing().getMarketingId()) && baoyou.get(j).getThirdId().toString().equals(markMap.get(markId).toString())) {
                                GoodsProduct goodsProduct = goodsProductMapper.queryByGoodsInfoDetail(baoyou.get(j).getGoodsInfoId());
                                BigDecimal goodsMoney = goodsProduct.getGoodsInfoPreferPrice();
                                BigDecimal goodsVipMoney = goodsProduct.getGoodsInfoVipPrice();
                                // 货品价格中间变量
                                BigDecimal goodspriceflag = goodsProduct.getGoodsInfoPreferPrice();
                                // 货品Vip价格中间变量
                                BigDecimal goodsVipPriceFlag = goodsProduct.getGoodsInfoVipPrice();

                                if (0 == baoyou.get(j).getThirdId()) {
                                    /* 根据选择的地区查询库存及价格信息 */
                                    ProductWare productWare = this.productWareService.queryProductWareByProductIdAndDistinctId(baoyou.get(j).getGoodsInfoId(), distinctId);
                                    if (null != productWare) {
                                        goodsMoney = productWare.getWarePrice();
                                        goodspriceflag = productWare.getWarePrice();
                                        goodsVipMoney = productWare.getWareVipPrice();
                                        goodsVipPriceFlag = productWare.getWareVipPrice();
                                    }
                                }

                                Map<String, Object> mapGoods = new HashMap<String, Object>();

                                //单该货品同时参与了团购和折扣时,优先级团购优先
                                if(baoyou.get(j).getGoodsGroupId()!=null) {
                                    // 从购物车里得到促销id重新从数据库查询,防止当前(团购促销)已经过期;
                                    Marketing mark = marketService.querySimpleMarketingById(baoyou.get(j).getGoodsGroupId());
                                    if (mark != null) {
                                        Groupon groupon = grouponMapper.selectByMarketId(mark.getMarketingId());
                                        if (groupon != null) {
                                            /*goodsMoney = goodspriceflag.multiply(groupon.getGrouponDiscount());
                                            goodsVipMoney = goodsVipPriceFlag.multiply(groupon.getGrouponVipDiscount());*/
                                            // 2015-12-09 wuyanbo 修改团购价的计算方式（直接取团购价）
                                            goodsMoney = groupon.getGrouponPrice();
                                            goodsVipMoney = groupon.getGrouponVipPrice();
                                        }
                                    }
                                }
                                // 折扣促销
                                if (baoyou.get(j).getGoodsGroupId()==null&&baoyou.get(j).getMarketingId() != null && 0 != baoyou.get(j).getMarketingId()) {
                                    // 从购物车里得到促销id重新从数据库查询,防止当前(折扣促销)已经过期;
                                    Marketing mark = marketingMapper.marketingDetail(baoyou.get(j).getMarketingId());
                                    // 参与折扣促销
                                    if (null != mark) {
                                        mapGoods.put(MARKETINGID, mark.getMarketingId());
                                        mapGoods.put(GOODSID, baoyou.get(j).getGoodsInfoId());
                                        // 折扣促销详细信息
                                        PreDiscountMarketing preDiscountMarketing = preDiscountMarketingMapper.selectByMarketId(mapGoods);
                                        if (null != preDiscountMarketing && preDiscountMarketing.getDiscountPrice() != null) {
                                            DecimalFormat myformat = null;
                                            // 抹掉分
                                            if ("1".equals(preDiscountMarketing.getDiscountFlag())) {
                                                myformat = new DecimalFormat("0.0");
                                            } else if ("2".equals(preDiscountMarketing.getDiscountFlag())) {
                                                myformat = new DecimalFormat("0");
                                            } else {
                                                myformat = new DecimalFormat("0.00");
                                            }
                                            // 不四舍五入
                                            myformat.setRoundingMode(RoundingMode.FLOOR);
                                            goodsMoney = BigDecimal.valueOf(Double.valueOf(myformat.format(goodspriceflag.multiply(preDiscountMarketing.getDiscountInfo()))));

                                        }
                                        //VIP
                                        if (null != preDiscountMarketing && preDiscountMarketing.getVipdiscountPrice() != null) {
                                            DecimalFormat myformat = null;
                                            // 抹掉分
                                            if ("1".equals(preDiscountMarketing.getDiscountFlag())) {
                                                myformat = new DecimalFormat("0.0");
                                            } else if ("2".equals(preDiscountMarketing.getDiscountFlag())) {
                                                myformat = new DecimalFormat("0");
                                            } else {
                                                myformat = new DecimalFormat("0.00");
                                            }
                                            // 不四舍五入
                                            myformat.setRoundingMode(RoundingMode.FLOOR);
                                            goodsVipMoney = BigDecimal.valueOf(Double.valueOf(myformat.format(goodsVipPriceFlag.multiply(preDiscountMarketing.getVipdiscountInfo()))));

                                        }
                                    }
                                }
                                countgoods = baoyou.get(j).getGoodsNum();
                                // 购物车里商品的总价格
                                aftermoney = aftermoney.add(goodsMoney.multiply(BigDecimal.valueOf(countgoods)));
                                afterVipMoney = afterVipMoney.add(goodsVipMoney.multiply(BigDecimal.valueOf(countgoods)));
                                if("1".equals(vip)){
                                    sc.setMarketgoodsPrice(afterVipMoney);
                                }else{
                                    sc.setMarketgoodsPrice(aftermoney);
                                }
                                sc.setThirdId(baoyou.get(j).getThirdId());
                                sc.setMarketing(baoyou.get(j).getMarketing());
                                shop.add(sc);

                            }

                        }

                        // 分组后的价格
                        for (int k = 0; k < baoyou.size(); k++) {
                            // 计算第三方分组后以及参与包邮分组
                            for (ShoppingCart scra : shop) {
                                // 分组后的包邮促销id和原有的包邮促销id一致
                                // 否则会导致只要有一个没有达到包邮促的条件,后面的包邮促销都会加载到没有包邮里面
                                if (scra.getMarketing().getMarketingId().equals(baoyou.get(k).getMarketing().getMarketingId())
                                        && markId.equals(scra.getMarketing().getMarketingId()) && scra.getThirdId().toString().equals(markMap.get(markId).toString())
                                        && scra.getThirdId().equals(baoyou.get(k).getThirdId())) {
                                    if("1".equals(vip) && scra.getMarketgoodsPrice().compareTo(baoyou.get(k).getMarketing().getVipShippingMoney()) == -1){
                                        // 参与包邮但没有达到条件包邮
                                        nobaoyou.add(baoyou.get(k));
                                    }else if("0".equals(vip) && scra.getMarketgoodsPrice().compareTo(baoyou.get(k).getMarketing().getShippingMoney()) == -1){
                                        // 参与包邮但没有达到条件包邮
                                        nobaoyou.add(baoyou.get(k));
                                    }
                                }
                            }
                        }
                        aftermoney = BigDecimal.ZERO;
                        afterVipMoney = BigDecimal.ZERO;
                    }

                }

            }
        }
        return nobaoyou;

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
                    List<GoodsProductVo> goodsProducts = goodsProductService.queryDetailByGroupId(sc.getFitId());
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
                                } else {
                                    continue;
                                }

                            }
                            // 有设置其他城市运费，并且包含城市的收货城市
                            if (flag == 1) {
                                freightmoney = computeFreightAll(ft.getFreightMethods(), fall.get(j), goodsnum, goodsweight);
                                break;
                                // 有设置其他城市运费，没有城市的收货城市
                            } else {
                                // 如果上述判断没有返回return
                                // 那么去默认全国设置
                                freightmoney = computeFreight(ft.getFreightMethods(), fe.get(i), goodsnum, goodsweight);
                                continue;
                            }

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
     * 参数是需要运费购物车id 第三方id根据第三方来分组得到各个商家的运费-不同地区的货品价格以及库存
     *
     * @param cityId
     * @return
     */
    @Override
    public Map<String, Object> getNewExpressPrice(HttpServletRequest request, Long addressId, Long cityId, Long[] cartIds) {
        Object object = request.getSession().getAttribute("vip");
        String vip = "0";
        if(null != object){
            vip = (String)object;
        }

        List<ShoppingCart> shopdata = shoppingCartMapper.shopCartListByIds(Arrays.asList(cartIds));
        List<GoodsDetailBean> detList = new ArrayList<>();
        Map<Long, Object> thirdMap = new HashMap<>();
        BigDecimal freightmoney = BigDecimal.ZERO;
        // boss运费为上门自提用
        BigDecimal bossfreight = BigDecimal.ZERO;
        // 得到没有包邮或者没有达到条件的购物车集合
        List<ShoppingCart> cartList = getNobaoyouShoppingcarts(shopdata, addressId, vip);

        if (CollectionUtils.isNotEmpty(shopdata)) {
            // 商家id集合
            for (ShoppingCart sh : shopdata) {

                if (sh.getFitId() == null) {
                    // 不是套装的thirdId不为空,否则为空
                    /**
                     *
                     * 增加营销信息的处理。
                     * edit by 付陈林 2015年12月9日
                     * */
                    thirdMap.put(sh.getThirdId(), "");
                    sh.setGoodsDetailBean(
                            goodsProductService.queryDetailBeanByProductId(sh.getGoodsInfoId(), sh.getDistinctId()));
                    // 查询商品参加的促销信息
                    List<Marketing> tempMarketList = marketService.selectMarketingByGoodsInfoId(sh.getGoodsInfoId(), sh.getGoodsDetailBean().getBrand().getBrandId(), sh.getGoodsDetailBean().getProductVo().getGoods().getCatId());
                    sh.setMarketingList(tempMarketList);
                    if(null == tempMarketList || tempMarketList.size() == 0){
                        detList.add(goodsProductService.queryGoodsByproductIdAndDistinctId(sh.getGoodsInfoId(), addressId, null, sh.getGoodsGroupId(), tempMarketList));
                    }else{
                        detList.add(goodsProductService.queryGoodsByproductIdAndDistinctId(sh.getGoodsInfoId(), addressId, sh.getMarketingList().get(0).getMarketingId(), sh.getGoodsGroupId(), tempMarketList));
                    }

                    /**
                     * edit end
                     * */
                } else {
                    // 如果商品是套装
                    GoodsGroupVo goodsGroupVo = goodsGroupService.queryVoByPrimaryKey(sh.getFitId());
                    // 获取此套装下的所有货品
                    List<GoodsProductVo> goodsProducts = goodsProductService.queryDetailByGroupId(sh.getFitId());
                    sh.setThirdId(goodsGroupVo.getThirdId());
                    thirdMap.put(goodsGroupVo.getThirdId(), "");
                    if (sh.getThirdId() == 0) {
                        for (GoodsProductVo gp : goodsProducts) {
                            detList.add(goodsProductService.queryGoodsByproductIdAndDistinctId(gp.getGoodsInfoId(), addressId, null,null,null));
                        }

                    }
                }
            }
        }

        if (CollectionUtils.isNotEmpty(cartList)) {
            for (Long thirdId : thirdMap.keySet()) {
                BigDecimal money = getEverythirdExpressPrice(thirdId, cityId, cartList);
                freightmoney = freightmoney.add(money);
                if (thirdId == 0) {
                    bossfreight = bossfreight.add(money);
                }
            }
        }
        Map<String, Object> freightMap = new HashMap<>();
        Map<String, Object> map = siteOrderService.getEveryparamMap(request, cartIds, addressId);
        // 总价格(不包含运费)
        freightMap.put(SUMOLDPRICE, map.get(SUMOLDPRICE));
        // 实际价格
        freightMap.put(SUMPRICE, map.get(SUMPRICE));
        // boss价格
        freightMap.put(BOSSSUMPRICE, map.get(BOSSSUMPRICE));
        // 总价格
        BigDecimal sumOldPrice = BigDecimal.valueOf(Double.valueOf(map.get(SUMOLDPRICE).toString()));
        // 实际价格
        BigDecimal sumPrice = BigDecimal.valueOf(Double.valueOf(map.get(SUMPRICE).toString()));
        // 返现金额
        BigDecimal reducePrice = sumOldPrice.subtract(sumPrice);
        // 计算会员折扣价
        Long customerId = (Long) request.getSession().getAttribute(CUSTOMERID);
        Customer cus = customerServiceInterface.queryCustomerById(customerId);
        CustomerPointLevel cuspointLevel = customerPointLevelMapper.selectByPrimaryKey(cus.getPointLevelId());
        // 会员等级折扣价
        BigDecimal discountPrice1 = cuspointLevel.getPointDiscount().multiply(BigDecimal.valueOf(Double.valueOf(map.get(BOSSSUMPRICE).toString())));
        // 减掉返现金额的总价格
        BigDecimal bossSumPrice2 = discountPrice1.subtract(reducePrice);
        // 会员等级折扣的价格(减掉的金额)
        BigDecimal discountPrice = BigDecimal.valueOf(Double.valueOf(map.get(BOSSSUMPRICE).toString())).subtract(discountPrice1);
        // 会员等级折扣价
        freightMap.put("discountPrice", discountPrice);
        freightMap.put(BOSSSUMPRICE, bossSumPrice2);
        // 总运费
        freightMap.put("freightmoney", freightmoney);
        // boss平台运费
        freightMap.put("bossfreight", bossfreight);
        // 货品对象
        freightMap.put("detailBean", detList);

        return freightMap;

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
     * 根据购物车里面的货品查询是否存在包邮的促销活动 返回list集合,不包邮的购物车
     *
     * @param cartList
     * @return
     * @author jiaodongzhi
     */
    public List<ShoppingCart> checkIsFreight(List<ShoppingCart> cartList, String vip) {
        // 不包邮购物车或者参与包邮活动但是没有达到条件
        List<ShoppingCart> nobaoyou = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(cartList)) {
            // 参与包邮,生成新的购物车
            List<ShoppingCart> baoyou = new ArrayList<>();
            for (int i = 0; i < cartList.size(); i++) {
                if (cartList.get(i) != null && cartList.get(i).getGoodsInfoId() != null) {
                    // 从购物车里得到促销id重新从数据库查询,防止当前(包邮促销)已经过期;
                    Marketing marketing = marketingMapper.marketingDetail(cartList.get(i).getMarketingActivityId());
                    // 包邮
                    if (null != marketing) {
                        if ("12".equals(marketing.getCodexType())) {
                            baoyou.add(cartList.get(i));
                        } else {
                            nobaoyou.add(cartList.get(i));
                        }
                        // 购物车里的包邮促销过期
                    } else {
                        nobaoyou.add(cartList.get(i));
                    }

                }
            }
            if (CollectionUtils.isNotEmpty(baoyou)) {
                BigDecimal beforemoney = BigDecimal.valueOf(0);
                BigDecimal aftermoney = BigDecimal.valueOf(0);
                BigDecimal beforeVipMoney = BigDecimal.valueOf(0);
                BigDecimal afterVipMoney = BigDecimal.valueOf(0);
                Long countgoods = 0L;
                // 遍历参与包邮的购物车且折扣没有过期集合
                for (int j = 0; j < baoyou.size(); j++) {
                    GoodsProduct goodsProduct = goodsProductMapper.selectByGoodsInfoId(baoyou.get(j).getGoodsInfoId());
                    BigDecimal goodsMoney = goodsProduct.getGoodsInfoPreferPrice();
                    BigDecimal goodsVipMoney = goodsProduct.getGoodsInfoVipPrice();
                    Map<String, Object> mapGoods = new HashMap<String, Object>();
                    // 从购物车里得到促销id重新从数据库查询,防止当前(折扣促销)已经过期;
                    Marketing mark = marketingMapper.marketingDetail(baoyou.get(j).getMarketingActivityId());

                    // 参与折扣促销
                    if (null != mark.getMarketingId()) {
                        mapGoods.put(MARKETINGID, mark.getMarketingId());
                        mapGoods.put(GOODSID, baoyou.get(j).getGoodsInfoId());
                        // 折扣促销详细信息
                        PreDiscountMarketing preDiscountMarketing = preDiscountMarketingMapper.selectByMarketId(mapGoods);
                        goodsMoney = preDiscountMarketing.getDiscountPrice();
                        goodsVipMoney = preDiscountMarketing.getVipdiscountPrice();
                    }
                    beforemoney = beforemoney.add(goodsMoney);
                    beforeVipMoney = beforeVipMoney.add(goodsVipMoney);
                    countgoods = countgoods + baoyou.get(j).getGoodsNum();
                    // 购物车里商品的总价格
                    BigDecimal totalGoods = beforemoney.multiply(BigDecimal.valueOf(countgoods));
                    BigDecimal totalVipGoods = beforeVipMoney.multiply(BigDecimal.valueOf(countgoods));
                    aftermoney = aftermoney.add(totalGoods);
                    afterVipMoney = afterVipMoney.add(totalVipGoods);
                    // 参与包邮但没有达到条件包邮
                    if("1".equals(vip)){
                        if (mark.getVipShippingMoney().compareTo(afterVipMoney) == -1) {
                            nobaoyou.add(cartList.get(j));
                        }
                    }else if("0".equals(vip)){
                        if (mark.getShippingMoney().compareTo(aftermoney) == -1) {
                            nobaoyou.add(cartList.get(j));
                        }
                    }

                }
            }
        }
        return nobaoyou;

    }

    /**
     * 根据所选择的商品进入订单确认查询
     *
     * @param request
     * @param box
     * @param marketingId
     * @param thirdId
     * @param cartWareUtil
     * @param customerId
     * @return
     */
    @Override
    public Map<String, Object> subOrder(HttpServletRequest request, Long[] box, Long[] marketingId, Long[] thirdId, ShoppingCartWareUtil cartWareUtil, Long customerId) {
        Object object = request.getSession().getAttribute("vip");
        String vip = "0";
        if(null != object){
            vip = (String)object;
        }

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
        Long baoyou = 0L;
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
                    baoyou = marketService.queryByCreatimeMarketings(sc.getGoodsInfoId(), 6L, sc.getGoodsDetailBean().getProductVo().getGoods().getCatId(), sc.getGoodsDetailBean()
                            .getBrand().getBrandId());
                    if (baoyou != 0) {
                        sc.setIsBaoyou("1");
                    } else {
                        sc.setIsBaoyou("0");
                    }
                }
            }
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
        List<Long> lista = new ArrayList<Long>();
        List<Long> listA = new ArrayList<Long>();

        List<Long> goodsInfoIds = new ArrayList<Long>();
        BigDecimal result = new BigDecimal(0);
        if (shoplist != null && !shoplist.isEmpty()) {
            for (int i = 0; i < shoplist.size(); i++) {
                if (shoplist.get(i).getFitId() != null) {

                    // 该套装下所有的商品
                    List<GoodsGroupReleProductVo> goodsGroupReleProductVos = shoplist.get(i).getGoodsGroupVo().getProductList();

                    // 设置套装商品库存
                    // 套装下商品
                    for (int j = 0; j < goodsGroupReleProductVos.size(); j++) {
                        Map<String, Long> map = new HashMap<String, Long>();
                        // 商品id
                        map.put("productId", goodsGroupReleProductVos.get(j).getProductDetail().getGoodsInfoId());
                        // 地区id
                        map.put(DISTINCTID, cartWareUtil.getDistrictId());
                        // 根据商品id和地区id取得商品库存欣喜
                        ProductWare productWare = productWareMapper.queryProductWareByProductIdAndDistinctId(map);
                        // 设置套装商家id
                        shoplist.get(i).getGoodsGroupVo().setThirdId(goodsGroupReleProductVos.get(j).getProductDetail().getThirdId());
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
                    shoplist.get(i).setGoodsDetailBean(goodsProductService.queryDetailBeanByProductId(shoplist.get(i).getGoodsInfoId(), cartWareUtil.getDistrictId()));

                    // 判断库存是否已空，如果库存为空，则跳转到购物车
                    if (shoplist.get(i).getGoodsDetailBean().getProductVo().getGoodsInfoStock() <= 0) {
                        // 清空值
                        paramMap.clear();
                        return paramMap;
                    }
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
                    // 会员折扣
                    if (shoplist.get(i).getMarketing() == null
                            && "1".equals(shoplist.get(i).getGoodsDetailBean().getProductVo().getIsCustomerDiscount())) {
                        // 判断该商品是否参加折扣
                        // 查询
                        CustomerPointLevel customerPointLevel = customerPointLevelMapper.selectCustomerLevelById((Long) request.getSession().getAttribute(
                                CustomerConstantStr.CUSTOMERID));
                        shoplist.get(i).setPointDiscount(customerPointLevel.getPointDiscount());
                    }
                    // 满件 减金额促销
                    if (shoplist.get(i).getMarketing() != null && shoplist.get(i).getMarketing().getFullbuyNoCountMarketing() != null
                            && shoplist.get(i).getMarketing().getFullbuyNoCountMarketing().getIsMeetCondition() != null
                            && Integer.parseInt(shoplist.get(i).getMarketing().getFullbuyNoCountMarketing().getIsMeetCondition()) == 1) {
                        lista.add(shoplist.get(i).getMarketing().getFullbuyNoCountMarketing().getMarketingId());
                    }
                }
            }
            // 区分重复的满购件数促销
            Iterator<Long> it = lista.iterator();
            while (it.hasNext()) {
                Long t = it.next();
                if (listA.contains(t)) {
                    it.remove();
                } else {
                    listA.add(t);
                }
            }
            if (listA != null) {
                for (int k = 0; k < listA.size(); k++) {
                    BigDecimal bd = new BigDecimal(0);
                    BigDecimal countMoney = null;
                    int c = 0;
                    for (int i = 0; i < shoplist.size(); i++) {
                        Marketing marketing = null;
                        marketing = marketService.marketingDetail(listA.get(k));
                        if (shoplist.get(i).getMarketing() != null && shoplist.get(i).getMarketing().getFullbuyNoCountMarketing() != null
                                && shoplist.get(i).getMarketing().getFullbuyNoCountMarketing().getMarketingId().intValue() == listA.get(k).intValue()
                                && shoplist.get(i).getGoodsNum().intValue() == 1) {
                            c += 1;// 计算满购件数金额买的件数不能超过限定件数
                            if (marketing.getFullbuyNoCountMarketing().getCountNo() >= c) {
                                countMoney = shoplist.get(i).getMarketing().getFullbuyNoCountMarketing().getCountMoney();
                                if("1".equals(vip)){
                                    bd = bd.add(shoplist.get(i).getGoodsDetailBean().getProductVo().getGoodsInfoVipPrice());
                                }else if("0".equals(vip)){
                                    bd = bd.add(shoplist.get(i).getGoodsDetailBean().getProductVo().getGoodsInfoPreferPrice());
                                }
                            }
                        }
                    }
                    if (bd != null) {
                        result = result.add(bd.subtract(countMoney));
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
        paramMap.put("result", result);
        paramMap.put(SHOPLIST, shoplist);
        paramMap.put("couponlist", couponList);
        // 商品的店铺标示
        paramMap.put("thirdIds", thirdIds);
        paramMap.put("orderMarketings", orderMarketings);
        // 根据会员ID获取积分对象
        CustomerPoint customerPoint = couponService.selectCustomerPointByCustomerId(customerId);
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
     * 查询购物车购买的商品信息
     *
     * @param request
     * @param box
     * @return
     */
    @Override
    public List<ShoppingCart> searchByProduct(HttpServletRequest request, Long[] box) {
        List<Long> list = new ArrayList<Long>();
        if (box.length != 0) {
            for (Long bo : box) {
                list.add(bo);
            }
        }
        // 计算“满购件数打折”的件数
        int countnum = 0;
        // 计算“满购件数多少钱”的件数
        int countno = 0;
        String isMeetCondition = null;
        String isCondition = null;
        List<ShoppingCart> shoplist = shoppingCartMapper.shoppingCartListByIds(list);
        ShoppingCartWareUtil cartWareUtil = selectPNameByParam(request);
        List<Long> listadd = new ArrayList<Long>();
        List<Long> lists = new ArrayList<Long>();
        if (shoplist != null && !shoplist.isEmpty()) {

            for (int i = 0; i < shoplist.size(); i++) {
                // 判断是否是套装
                if (shoplist.get(i).getFitId() == null) {
                    // 如果不是套装
                    shoplist.get(i).setGoodsDetailBean(goodsProductService.queryDetailBeanByProductId(shoplist.get(i).getGoodsInfoId(), cartWareUtil.getDistrictId()));
                } else {
                    // 进入此处标示该购物车为套装，执行套装方法
                    GoodsGroupVo goodsGroupVo = goodsGroupService.queryVoByPrimaryKey(shoplist.get(i).getFitId());
                    // 设置套装的所属商家
                    if (goodsGroupVo.getProductList() != null && goodsGroupVo.getProductList().get(0) != null) {
                        // 设置商家id
                        goodsGroupVo.setThirdId(goodsGroupVo.getProductList().get(0).getProductDetail().getThirdId());
                    }
                    shoplist.get(i).setGoodsGroupVo(goodsGroupVo);
                }

                // 判断是否是套装
                if (shoplist.get(i).getFitId() == null) {
                    // 如果是套装，设置优惠
                    Marketing marketing = null;
                    boolean isT = true;
                    Map<String, Object> param = new HashMap<String, Object>();
                    // 设置单品优惠
                    if (shoplist.get(i).getMarketingId() != null && shoplist.get(i).getMarketingId() > 0) {
                        marketing = marketService.marketingDetail(shoplist.get(i).getMarketingId(), shoplist.get(i).getGoodsInfoId());

                        // 满够件数打折 "13"
                        if (marketing != null) {
                            // 如果这种促销不为空
                            if (marketing.getFullbuyNoDiscountMarketing() != null) {
                                // 如果查询出的“满够件数打折”促销id与购物车里商品促销id相等
                                if (marketing.getFullbuyNoDiscountMarketing().getMarketingId().intValue() == shoplist.get(i).getMarketingId().intValue()) {
                                    // 查询满足“满够件数打折”促销的个数 (初始默认为0)
                                    countnum = marketing.getFullbuyNoDiscountMarketing().getCountCondition().intValue();
                                    // +1
                                    countnum += 1;
                                    param.put(MARKETINGID, shoplist.get(i).getMarketingId());
                                    param.put(COUNTCONDITION, countnum);
                                    // 更新满足“满够件数打折”促销的个数为countnum
                                    fullbuyNoDiscountMarketingMapper.insertCountConditionByMarketing(param);
                                }
                                // 如果购买件数大于需要满购的件数
                                if (marketing.getFullbuyNoDiscountMarketing().getPackagesNo() <= countnum) {
                                    // 满足条件
                                    isMeetCondition = "1";
                                } else {
                                    // 不满足
                                    isMeetCondition = "0";
                                }
                                // 如果满足条件则加入到listadd
                                if ("1".equals(isMeetCondition)) {
                                    listadd.add(shoplist.get(i).getMarketingId());
                                }
                            }
                            // 满购件数 给多少钱 "14"
                            if (marketing.getFullbuyNoCountMarketing() != null) {
                                // 如果查询出的“满购件数 多少金额”促销id与购物车里商品促销id相等
                                if (marketing.getFullbuyNoCountMarketing().getMarketingId().intValue() == shoplist.get(i).getMarketingId().intValue()
                                        && shoplist.get(i).getGoodsNum().intValue() == 1) {
                                    // 查询满足该促销的个数 (初始默认为0)
                                    countno = marketing.getFullbuyNoCountMarketing().getCountCondition().intValue();
                                    // 个数+1
                                    countno += 1;
                                    param.put(MARKETINGID, shoplist.get(i).getMarketingId());
                                    param.put(COUNTCONDITION, countno);
                                    // 更新满足该促销条件的个数
                                    fullbuyNoCountMarketingMapper.insertCountConditionByMarketing(param);
                                }
                                // 如果购买件数大于需要满购的件数
                                if (marketing.getFullbuyNoCountMarketing().getCountNo() <= countno) {
                                    // 满足条件
                                    isCondition = "1";
                                } else {
                                    // 不满足条件
                                    isCondition = "0";
                                }
                                // 如果蛮子条件则把该商品促销id加入到lists
                                if ("1".equals(isCondition)) {
                                    lists.add(shoplist.get(i).getMarketingId());
                                }

                            }
                        }
                        isT = false;
                    }
                    // 设置活动优惠
                    if (shoplist.get(i).getMarketingActivityId() != null && shoplist.get(i).getMarketingActivityId() > 0) {
                        marketing = marketService.marketingDetailByActive(marketing, shoplist.get(i).getMarketingActivityId(), isT);
                    }
                    shoplist.get(i).setMarketing(marketing);
                }
            }
            // 将满购件数打折促销与满购件数多少金额初始化，方便下次计算该促销
            fullbuyNoDiscountMarketingMapper.update();
            fullbuyNoCountMarketingMapper.update();
            if (listadd != null) {
                // 循环listadd
                for (int k = 0; k < listadd.size(); k++) {
                    // 循环购物车，将满足满购件数打折促销的商品的isMeetCondition状态改为"1",方便页面计算
                    for (int i = 0; i < shoplist.size(); i++) {
                        Marketing marketing = null;
                        marketing = marketService.marketingDetail(listadd.get(k));
                        if (marketing != null && marketing.getFullbuyNoDiscountMarketing() != null && shoplist.get(i).getMarketingId() != null
                                && shoplist.get(i).getMarketingId().intValue() == listadd.get(k).intValue()) {
                            shoplist.get(i).getMarketing().getFullbuyNoDiscountMarketing().setIsMeetCondition("1");
                        }
                    }
                }
            }
            if (lists != null) {
                // 循环lists
                for (int k = 0; k < lists.size(); k++) {
                    int b = 0;
                    // 循环购物车，将满足满购件数多少金额促销的商品的isMeetCondition状态改为"1",方便页面计算
                    for (int i = 0; i < shoplist.size(); i++) {
                        Marketing marketing = null;
                        marketing = marketService.marketingDetail(lists.get(k));
                        if (marketing != null && marketing.getFullbuyNoCountMarketing() != null && shoplist.get(i).getMarketingId() != null
                                && shoplist.get(i).getMarketingId().intValue() == lists.get(k).intValue()
                                && shoplist.get(i).getGoodsNum().intValue() == 1) {
                            // 满够件数多少金额 买的件数不能大于限定的件数
                            // 例只能"买2种件数100元，不能买3中件数100元"
                            b += 1;
                            if (marketing.getFullbuyNoCountMarketing().getCountNo() >= b) {
                                shoplist.get(i).getMarketing().getFullbuyNoCountMarketing().setIsMeetCondition("1");
                            }

                        }
                    }
                }
            }

        }
        return shoplist;

    }

    /**
     * 添加购物车
     *
     * @param shoppingCart
     * @param request
     * @param response
     * @return
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
            int count = shoppingCartMapper.selectCountByReady(shoppingCart);//查询购物车中指定的商品数量
            // 货品信息
            if (count == 0) {
                if (shoppingCart.getFitId() == null) {
                    GoodsProductVo productVo = goodsProductService.getGoodsProductVoWithGoods(shoppingCart.getGoodsInfoId());
                    // 促销信息
                    List<Marketing> markList = marketService.selectSimpleMarketingByGoodsInfoId(shoppingCart.getGoodsInfoId(), productVo.getGoods().getBrandId(), productVo
                            .getGoods().getCatId());

                    Long marketId = 0L;

                    //团购促销Id
                    Long goodsGroupId=0L;
                    // 单品团购促销只能有一个
                    for (Marketing mark : markList) {
                        if ("10".equals(mark.getCodexType())) {
                            goodsGroupId = mark.getMarketingId();
                            break;
                        }

                    }

                    // 单品折扣促销只能有一个
                    for (Marketing mark : markList) {
                        if ("15".equals(mark.getCodexType())) {
                            marketId = mark.getMarketingId();
                            break;
                        }

                    }

                    Long activeMarketId = 0L;
                    // 活动促销
                    for (Marketing mark : markList) {
                        //活动促销不包括折扣促销、包邮、团购
                        if (!"15".equals(mark.getCodexType()) && !"12".equals(mark.getCodexType())&& !"10".equals(mark.getCodexType())) {
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
                    if(goodsGroupId!=0){
                        shoppingCart.setGoodsGroupId(goodsGroupId);
                    }
                }
                return shoppingCartMapper.addShoppingCart(shoppingCart);

            } else {
                ShoppingCart sc = shoppingCartMapper.selectShopingByParam(shoppingCart);
                if (shoppingCart.getFitId() == null) {
                    // 限购
                    if (limtShopping(sc, custId) == 0) {
                        return 1;
                    }
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
                    if (null != cookie && NPSTORESHOPCAR.equals(cookie.getName())) {
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
                    if (cookie != null && NPSTOREMID.equals(cookie.getName()) && cookie.getValue() != null && !"".equals(cookie.getValue())) {
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
            Long marketId = null;
            Long activeMarketId = null;
            //团购促销Id
            Long goodsGroupId=null;
            if (shoppingCart.getFitId() == null) {

                GoodsProductVo productVo = goodsProductService.getGoodsProductVoWithGoods(shoppingCart.getGoodsInfoId());
                // 单品优惠id
                marketId = marketService.queryByCreatimeMarketings(shoppingCart.getGoodsInfoId(), (long) 1, productVo.getGoods().getCatId(), productVo.getGoods().getBrandId());
                // 抢购活动优惠id
                activeMarketId = marketService.queryByCreatimeMarketings(shoppingCart.getGoodsInfoId(), (long) 5, productVo.getGoods().getCatId(), productVo.getGoods()
                        .getBrandId());
                if(activeMarketId==null ||activeMarketId==0){
                    activeMarketId = marketService.queryByCreatimeMarketings(shoppingCart.getGoodsInfoId(), (long) 2, productVo.getGoods().getCatId(), productVo.getGoods()
                            .getBrandId());
                }

                //团购促销Id
                goodsGroupId=marketService.queryByCreatimeMarketings(shoppingCart.getGoodsInfoId(), 4L, productVo.getGoods().getCatId(), productVo.getGoods().getBrandId());
            }
            mId += "-" + shoppingCart.getGoodsInfoId() + "e" + marketId + "e" + activeMarketId + "e"+ goodsGroupId+"e"+ "0";

            cook = new Cookie(NPSTORESHOPCAR, URLEncoder.encode(oldCar, ConstantUtil.UTF));
            cook.setMaxAge(15 * 24 * 3600);
            cook.setPath("/");
            response.addCookie(cook);
            Cookie cookie = new Cookie(NPSTOREMID, mId);
            cookie.setMaxAge(15 * 24 * 3600);
            cookie.setPath("/");
            response.addCookie(cookie);
            return 1;
        }
    }

    /**
     * 加载cookie中的购物车信息
     *
     * @param request
     * @return
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

                    if (null != cookie && NPSTORESHOPCAR.equals(cookie.getName()) && cookie.getValue() != null && !"".equals(cookie.getValue())) {
                        oldCar = URLDecoder.decode(cookie.getValue(), ConstantUtil.UTF);
                        oldCar = oldCar.substring(1, oldCar.length());
                        oldCar = oldCar.substring(0, oldCar.length() - 1);
                        cars = oldCar.split("e,");
                        if (null != cars && cars.length > 0) {
                            for (int i = 0; i < cars.length; i++) {
                                car = cars[i].split("-");
                                carUtil = new ShopCarUtil();

                                /* 如果符合套装的规则,说明是套装,否则就是货品 */
                                if (car[0].length() > 6 && PROINFO.equals(car[0].substring(0, 6))) {
                                    carUtil.setFitId(Long.parseLong(car[0].substring(6, car[0].length())));
                                    carUtil.setProductId(Long.parseLong(car[0]));
                                } else {
                                    carUtil.setProductId(Long.parseLong(car[0]));
                                    for (Cookie cook : cookies) {
                                        // 设置优惠
                                        if (cook != null && NPSTOREMID.equals(cook.getName()) && cook.getValue() != null && !"".equals(cook.getValue())) {
                                            String[] mIds = cook.getValue().split("-");
                                            // 取得所有cookie
                                            for (int j = 0; j < mIds.length; j++) {
                                                String[] mid = mIds[j].split("e");
                                                // 判断是否该商品
                                                if (mid[0] != null && mid.length==5 && car[0].equals(mid[0])) {
                                                    carUtil.setMarketId(Long.parseLong(mid[1]));
                                                    carUtil.setMarketActiveId(Long.parseLong(mid[2]));
                                                    carUtil.setGoodsGroupMarketingId(Long.parseLong(mid[3]));
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
     * @param productId
     * @param request
     *            请求对象
     * @param response
     *            相应对象
     * @return
     * @throws UnsupportedEncodingException
     */
    public int delCookShopCar(Long productId, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Integer count = 0;
        Cookie[] cookies = request.getCookies();
        String oldCar;
        String[] cars;
        String[] car;
        Cookie cook;
        String newMid = "";
        StringBuilder bufOldCar = new StringBuilder();
        StringBuilder bufNewMid = new StringBuilder();
        try {
            if (null != cookies) {
                for (Cookie cookie : cookies) {
                    if (null != cookie && NPSTORESHOPCAR.equals(cookie.getName())) {
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
                                }
                            }
                        }
                    }
                    if (cookie != null && NPSTOREMID.equals(cookie.getName()) && cookie.getValue() != null && !"".equals(cookie.getValue())) {
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
            cook = new Cookie(NPSTORESHOPCAR, URLEncoder.encode(bufOldCar.toString(), ConstantUtil.UTF));
            cook.setMaxAge(15 * 24 * 3600);
            cook.setPath("/");
            response.addCookie(cook);
            Cookie cookie = new Cookie(NPSTOREMID, URLEncoder.encode(newMid, ConstantUtil.UTF));
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

    /**
     * 删除已经购买商品
     *
     * @param request
     * @param shoppingCartId
     * @return
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
     * @return
     */
    @Override
    public int loadCoodeShopping(HttpServletRequest request) {
        Long custId = (Long) request.getSession().getAttribute(CUSTOMERID);
        ShoppingCartWareUtil wareUtil = shoppingCartService.selectPNameByParam(request);
        List<ShopCarUtil> list = null;
        try {
            list = loadCookShopCar(request);
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("加载cookie中的购物车信息错误" + e);
        }
        if (list != null && !list.isEmpty()) {
            for (ShopCarUtil su : list) {
                ShoppingCart shoppingCart = new ShoppingCart();
                if (su.getFitId() == null) {
                    shoppingCart.setGoodsInfoId(su.getProductId());
                } else {
                    // 设置套装商品id
                    shoppingCart.setGoodsInfoId(Long.parseLong(PROINFO + su.getFitId()));
                    // 设置套装id
                    shoppingCart.setFitId(su.getFitId());
                }
                shoppingCart.setCustomerId(custId);
                shoppingCart.setDelFlag("0");
                shoppingCart.setShoppingCartTime(new Date());
                shoppingCart.setGoodsNum(Long.valueOf(su.getGoodsNum()));
                shoppingCart.setMarketingId(su.getMarketId());
                shoppingCart.setDistinctId(wareUtil.getDistrictId());
                shoppingCart.setMarketingActivityId(su.getMarketActiveId());
                shoppingCart.setGoodsNum(Long.valueOf(su.getGoodsNum()));
                shoppingCart.setGoodsGroupId(su.getGoodsGroupMarketingId());
                int count = shoppingCartMapper.selectCountByReady(shoppingCart);
                if (count == 0) {
                    shoppingCartMapper.addShoppingCart(shoppingCart);
                }
            }

        }

        return 1;
    }

    /**
     * 修改要选中的订单优惠
     *
     * @param cart
     * @return
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

    /**
     * 查询购物车内容
     *
     * @param request
     * @param cartWareUtil
     * @param pb
     * @param response
     * @return
     */
    @Override
    public PageBean selectShoppingCart(HttpServletRequest request, ShoppingCartWareUtil cartWareUtil, PageBean pb, HttpServletResponse response) {
        Long customerId = (Long) request.getSession().getAttribute(CUSTOMERID);
        // 计算“满购件数打折”的件数
        int countnum = 0;
        // 计算“满购件数多少钱”的件数
        int countno = 0;
        String isMeetCondition = null;
        String isCondition = null;
        // 如果已经登录
        if (customerId != null) {
            pb.setUrl(MYSHOPPINGCART);
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
                        // 设置购物车内商品选中状态
                        Object sessionStatus = request.getSession().getAttribute(NPSTORESHOPSTATUS);
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
                        request.getSession().setAttribute(NPSTORESHOPSTATUS, sessionStatus);

                        if (((ShoppingCart) shoplist.get(i)).getFitId() != null) {
                            // 获取套装
                            getGroupGoods(cartWareUtil, shoplist, i);

                        } else {
                            // 获取普通商品
                            getGoodsDetailBean(cartWareUtil, customerId, shoplist, i);

                        }
                    } catch (Exception e) {
                        Map<String, Object> map = new HashMap<String, Object>();
                        // 删除商品根据id和customerId，防止越权
                        map.put("shoppingCartId", ((ShoppingCart) shoplist.get(i)).getShoppingCartId());
                        map.put(CUSTOMERID, customerId);
                        // 删除不存在的商品
                        shoppingCartMapper.delShoppingCartById(map);
                        LOGGER.info(e);
                        return null;
                    }

                }
                for (int i = 0; i < shoplist.size(); i++) {
                    if (((ShoppingCart) shoplist.get(i)).getFitId() == null) {
                        ((ShoppingCart) shoplist.get(i)).setMarketingList(marketService.selectMarketingByGoodsInfoId(((ShoppingCart) shoplist.get(i)).getGoodsDetailBean()
                                .getProductVo().getGoodsInfoId(), ((ShoppingCart) shoplist.get(i)).getGoodsDetailBean().getBrand().getBrandId(), ((ShoppingCart) shoplist.get(i))
                                .getGoodsDetailBean().getProductVo().getGoods().getCatId()));
                    }
                }

                List<Long> listadd = new ArrayList<Long>();
                List<Long> list = new ArrayList<Long>();
                for (int i = 0; i < shoplist.size(); i++) {
                    Map<String, Object> param = new HashMap<String, Object>();
                    if (((ShoppingCart) shoplist.get(i)).getFitId() == null) {
                        Marketing marketing = null;
                        boolean isT = true;
                        // 设置单品优惠
                        if (((ShoppingCart) shoplist.get(i)).getMarketingId() != null) {
                            marketing = marketService.marketingDetail(((ShoppingCart) shoplist.get(i)).getMarketingId(), ((ShoppingCart) shoplist.get(i)).getGoodsInfoId());
                            // 满够件数打折
                            if (marketing != null && marketing.getMarketingEnd().getTime() > new Date().getTime()) {

                                if (marketing.getFullbuyNoDiscountMarketing() != null) {
                                    if (marketing.getFullbuyNoDiscountMarketing().getMarketingId().intValue() == ((ShoppingCart) shoplist.get(i)).getMarketingId().intValue()) {
                                        countnum = marketing.getFullbuyNoDiscountMarketing().getCountCondition().intValue();
                                        countnum += 1;
                                        param.put(MARKETINGID, ((ShoppingCart) shoplist.get(i)).getMarketingId());
                                        param.put(COUNTCONDITION, countnum);
                                        fullbuyNoDiscountMarketingMapper.insertCountConditionByMarketing(param);
                                    }
                                    if (marketing.getFullbuyNoDiscountMarketing().getPackagesNo() <= countnum) {
                                        isMeetCondition = "1";
                                    } else {
                                        isMeetCondition = "0";
                                    }
                                    if ("1".equals(isMeetCondition)) {
                                        listadd.add(((ShoppingCart) shoplist.get(i)).getMarketingId());
                                    }

                                }

                                // 满购件数 给多少钱
                                if (marketing.getFullbuyNoCountMarketing() != null) {
                                    if (marketing.getFullbuyNoCountMarketing().getMarketingId().intValue() == ((ShoppingCart) shoplist.get(i)).getMarketingId().intValue()
                                            && ((ShoppingCart) shoplist.get(i)).getGoodsNum().intValue() == 1) {
                                        countno = marketing.getFullbuyNoCountMarketing().getCountCondition().intValue();
                                        countno += 1;
                                        param.put(MARKETINGID, ((ShoppingCart) shoplist.get(i)).getMarketingId());
                                        param.put(COUNTCONDITION, countno);
                                        fullbuyNoCountMarketingMapper.insertCountConditionByMarketing(param);
                                    }
                                    if (marketing.getFullbuyNoCountMarketing().getCountNo() <= countno) {
                                        isCondition = "1";
                                    } else {
                                        isCondition = "0";
                                    }
                                    if ("1".equals(isCondition)) {
                                        list.add(((ShoppingCart) shoplist.get(i)).getMarketingId());
                                    }
                                }
                            }
                            isT = false;
                        }
                        // 设置活动优惠
                        if (((ShoppingCart) shoplist.get(i)).getMarketingActivityId() != null) {
                            marketing = marketService.marketingDetailByActive(marketing, ((ShoppingCart) shoplist.get(i)).getMarketingActivityId(), isT);
                        }
                        ((ShoppingCart) shoplist.get(i)).setMarketing(marketing);
                        // 会员折扣
                        if (marketing == null && "1".equals(((ShoppingCart) shoplist.get(i)).getGoodsDetailBean().getProductVo().getIsCustomerDiscount())) {
                            // 判断该商品是否参加折扣
                            // 查询
                            CustomerPointLevel customerPointLevel = customerPointLevelMapper.selectCustomerLevelById(customerId);
                            ((ShoppingCart) shoplist.get(i)).setPointDiscount(customerPointLevel.getPointDiscount());
                        }
                    }
                }
                fullbuyNoDiscountMarketingMapper.update();
                fullbuyNoCountMarketingMapper.update();
                if (listadd != null) {
                    for (int k = 0; k < listadd.size(); k++) {
                        for (int i = 0; i < shoplist.size(); i++) {
                            Marketing marketing = null;
                            marketing = marketService.marketingDetail(listadd.get(k));
                            if (marketing != null && marketing.getFullbuyNoDiscountMarketing() != null && ((ShoppingCart) shoplist.get(i)).getMarketingId() != null
                                    && ((ShoppingCart) shoplist.get(i)).getMarketingId().intValue() == listadd.get(k).intValue()) {
                                ((ShoppingCart) shoplist.get(i)).getMarketing().getFullbuyNoDiscountMarketing().setIsMeetCondition("1");
                            }
                        }
                    }
                }
                if (list != null) {
                    for (int k = 0; k < list.size(); k++) {
                        for (int i = 0; i < shoplist.size(); i++) {
                            Marketing marketing = null;
                            marketing = marketService.marketingDetail(list.get(k));
                            if (marketing != null && marketing.getFullbuyNoCountMarketing() != null && ((ShoppingCart) shoplist.get(i)).getMarketingId() != null
                                    && ((ShoppingCart) shoplist.get(i)).getMarketingId().intValue() == list.get(k).intValue()) {
                                ((ShoppingCart) shoplist.get(i)).getMarketing().getFullbuyNoCountMarketing().setIsMeetCondition("1");
                            }
                        }
                    }
                }

            }
            pb.setList(shoplist);

        } else {
            // 如果未登录状态获取购物车
            try {
                List<ShopCarUtil> list = loadCookShopCar(request);
                if (list != null && !list.isEmpty()) {
                    pb.setUrl(MYSHOPPINGCART);
                    pb.setPageSize(5);

                    Integer totalCount = 1;

                    pb.setRows(Integer.parseInt(totalCount == null ? 0 + "" : totalCount + ""));
                    if (pb.getPageNo() > pb.getLastPageNo()) {
                        pb.setPageNo(pb.getLastPageNo());
                    }
                    if (pb.getPageNo() == 0) {
                        pb.setPageNo(1);
                    }

                    List<Object> shoplist = new ArrayList<Object>();
                    for (int i = 0; i < list.size(); i++) {
                        ShoppingCart sc = new ShoppingCart();
                        if (list.get(i).getFitId() == null) {
                            sc.setGoodsInfoId(list.get(i).getProductId());
                            sc.setShoppingCartId(list.get(i).getProductId());
                            sc.setMarketingId(list.get(i).getMarketId());
                            sc.setMarketingActivityId(list.get(i).getMarketActiveId());
                        } else {
                            // 设置套装商品id
                            sc.setGoodsInfoId(Long.parseLong(PROINFO + list.get(i).getFitId()));
                            sc.setShoppingCartId(Long.parseLong(PROINFO + list.get(i).getFitId()));
                            // 设置套装id
                            sc.setFitId(list.get(i).getFitId());

                        }

                        sc.setGoodsNum(Long.valueOf(list.get(i).getGoodsNum()));
                        sc.setMarketing(null);
                        sc.setDistinctId(list.get(i).getDistinctId());
                        shoplist.add(sc);
                    }

                    if (shoplist != null && !shoplist.isEmpty()) {
                        for (int i = 0; i < shoplist.size(); i++) {
                            Object sessionStatus = request.getSession().getAttribute(NPSTORESHOPSTATUS);
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
                            request.getSession().setAttribute(NPSTORESHOPSTATUS, sessionStatus);
                            if (((ShoppingCart) shoplist.get(i)).getFitId() != null) {
                                // 进入此处标示该购物车为套装，执行套装方法
                                getGroupGoods(cartWareUtil, shoplist, i);
                                // 设置套装商品库存end

                            } else {
                                // 进入此处标示该购物车为商品，执行商品方法
                                GoodsDetailBean goodsDetailBean = goodsProductService.queryDetailBeanByProductId(((ShoppingCart) shoplist.get(i)).getGoodsInfoId(),
                                        cartWareUtil.getDistrictId());
                                ((ShoppingCart) shoplist.get(i)).setGoodsDetailBean(goodsDetailBean);
                                // 限购
                                Marketing marketing = marketService.marketingDetail(marketService.queryByCreatimeMarketings(goodsDetailBean.getProductVo().getGoodsInfoId(), 3L,
                                        goodsDetailBean.getProductVo().getGoods().getCatId(), goodsDetailBean.getBrand().getBrandId()));
                                // 存在限购
                                if (marketing != null) {
                                    Long stock = marketing.getLimitBuyMarketing().getLimitCount();
                                    // 最近是否购买过该商品
                                    Long goodsNum = orderser.selectGoodsInfoCount(((ShoppingCart) shoplist.get(i)).getGoodsDetailBean().getProductVo().getGoodsInfoId(), null,
                                            marketing.getMarketingBegin());
                                    if (goodsNum != null) {
                                        stock = stock - goodsNum;
                                        if (stock < 0) {
                                            stock = 0L;
                                        }
                                    }
                                    if (((ShoppingCart) shoplist.get(i)).getGoodsDetailBean().getProductVo().getGoodsInfoStock() - stock >= 0) {
                                        // 如果存在限购，则把库存改为限购数量
                                        ((ShoppingCart) shoplist.get(i)).getGoodsDetailBean().getProductVo().setGoodsInfoStock(stock);

                                    }
                                }
                                ((ShoppingCart) shoplist.get(i)).setMarketingList(marketService.selectMarketingByGoodsInfoId(((ShoppingCart) shoplist.get(i)).getGoodsDetailBean()
                                                .getProductVo().getGoodsInfoId(), ((ShoppingCart) shoplist.get(i)).getGoodsDetailBean().getBrand().getBrandId(),
                                        ((ShoppingCart) shoplist.get(i)).getGoodsDetailBean().getProductVo().getGoods().getCatId()));
                            }
                        }
                        List<Long> listadd = new ArrayList<Long>();
                        List<Long> lists = new ArrayList<Long>();
                        for (int i = 0; i < shoplist.size(); i++) {
                            Map<String, Object> param = new HashMap<String, Object>();
                            if (((ShoppingCart) shoplist.get(i)).getFitId() == null) {
                                Marketing marketing = null;
                                boolean isT = true;
                                // 设置单品优惠
                                if (((ShoppingCart) shoplist.get(i)).getMarketingId() != null) {
                                    marketing = marketService.marketingDetail(((ShoppingCart) shoplist.get(i)).getMarketingId(), ((ShoppingCart) shoplist.get(i)).getGoodsInfoId());
                                    // 满够件数打折
                                    if (marketing != null && marketing.getMarketingEnd().getTime() > new Date().getTime()) {

                                        if (marketing.getFullbuyNoDiscountMarketing() != null) {
                                            if (marketing.getFullbuyNoDiscountMarketing().getMarketingId().intValue() == ((ShoppingCart) shoplist.get(i)).getMarketingId()
                                                    .intValue()) {
                                                countnum = marketing.getFullbuyNoDiscountMarketing().getCountCondition().intValue();
                                                countnum += 1;
                                                param.put(MARKETINGID, ((ShoppingCart) shoplist.get(i)).getMarketingId());
                                                param.put(COUNTCONDITION, countnum);
                                                fullbuyNoDiscountMarketingMapper.insertCountConditionByMarketing(param);
                                            }
                                            if (marketing.getFullbuyNoDiscountMarketing().getPackagesNo() <= countnum) {
                                                isMeetCondition = "1";
                                            } else {
                                                isMeetCondition = "0";
                                            }
                                            if ("1".equals(isMeetCondition)) {
                                                listadd.add(((ShoppingCart) shoplist.get(i)).getMarketingId());
                                            }

                                        }

                                        // 满购件数 给多少钱
                                        if (marketing.getFullbuyNoCountMarketing() != null) {
                                            if (marketing.getFullbuyNoCountMarketing().getMarketingId().intValue() == ((ShoppingCart) shoplist.get(i)).getMarketingId().intValue()
                                                    && ((ShoppingCart) shoplist.get(i)).getGoodsNum().intValue() == 1) {
                                                countno = marketing.getFullbuyNoCountMarketing().getCountCondition().intValue();
                                                countno += 1;
                                                param.put(MARKETINGID, ((ShoppingCart) shoplist.get(i)).getMarketingId());
                                                param.put(COUNTCONDITION, countno);
                                                fullbuyNoCountMarketingMapper.insertCountConditionByMarketing(param);
                                            }
                                            if (marketing.getFullbuyNoCountMarketing().getCountNo() <= countno) {
                                                isCondition = "1";
                                            } else {
                                                isCondition = "0";
                                            }
                                            if ("1".equals(isCondition)) {
                                                lists.add(((ShoppingCart) shoplist.get(i)).getMarketingId());
                                            }
                                        }
                                    }
                                    isT = false;
                                }
                                // 设置活动优惠
                                if (((ShoppingCart) shoplist.get(i)).getMarketingActivityId() != null) {
                                    marketing = marketService.marketingDetailByActive(marketing, ((ShoppingCart) shoplist.get(i)).getMarketingActivityId(), isT);
                                }
                                ((ShoppingCart) shoplist.get(i)).setMarketing(marketing);
                            }
                        }

                    }
                    pb.setList(shoplist);

                }

            } catch (UnsupportedEncodingException e) {
                LOGGER.error("加载cookie中的购物车信息错误" + e);
            }
        }
        return pb;
    }

    /**
     * 获取分组商品
     *
     * @param cartWareUtil
     * @param shoplist
     * @param i
     */
    public void getGroupGoods(ShoppingCartWareUtil cartWareUtil, List<Object> shoplist, int i) {
        Long stock = null;
        // 进入此处标示该购物车为套装，执行套装方法
        GoodsGroupVo goodsGroupVo = goodsGroupService.queryVoByPrimaryKey(((ShoppingCart) shoplist.get(i)).getFitId());
        ((ShoppingCart) shoplist.get(i)).setGoodsGroupVo(goodsGroupVo);
        // 该套装下所有的商品
        List<GoodsGroupReleProductVo> goodsGroupReleProductVos = ((ShoppingCart) shoplist.get(i)).getGoodsGroupVo().getProductList();
        for (int j = 0; j < goodsGroupReleProductVos.size(); j++) {

            GoodsDetailBean goodsDetailBean = goodsProductService.queryDetailBeanByProductId(goodsGroupReleProductVos.get(j).getProductDetail().getGoodsInfoId(),
                    cartWareUtil.getDistrictId());

            if (stock == null) {
                stock = goodsDetailBean.getProductVo().getGoodsInfoStock();
            } else {
                if (stock > goodsDetailBean.getProductVo().getGoodsInfoStock()) {
                    stock = goodsDetailBean.getProductVo().getGoodsInfoStock();
                }
            }
            // 重新设置套装下商品库存
            ((ShoppingCart) shoplist.get(i)).getGoodsGroupVo().getProductList().get(j).getProductDetail().setGoodsInfoStock(goodsDetailBean.getProductVo().getGoodsInfoStock());
        }
        ((ShoppingCart) shoplist.get(i)).getGoodsGroupVo().setStock(stock);
        // 重新设置list
        ((ShoppingCart) shoplist.get(i)).getGoodsGroupVo().setProductList(goodsGroupReleProductVos);
        // 设置套装商品库存end
    }

    /**
     * 获取商品详情
     *
     * @param cartWareUtil
     * @param customerId
     * @param shoplist
     * @param i
     */
    public void getGoodsDetailBean(ShoppingCartWareUtil cartWareUtil, Long customerId, List<Object> shoplist, int i) {
        // 进入此处标示该购物车为商品，执行商品方法
        GoodsDetailBean goodsDetailBean = goodsProductService.queryDetailBeanByProductId(((ShoppingCart) shoplist.get(i)).getGoodsInfoId(), cartWareUtil.getDistrictId());
        ((ShoppingCart) shoplist.get(i)).setGoodsDetailBean(goodsDetailBean);
        // 限购
        Marketing marketing = marketService.marketingDetail(marketService.queryByCreatimeMarketings(goodsDetailBean.getProductVo().getGoodsInfoId(), 3L, goodsDetailBean
                .getProductVo().getGoods().getCatId(), goodsDetailBean.getBrand().getBrandId()));
        // 存在限购
        if (marketing != null) {
            Long stock = marketing.getLimitBuyMarketing().getLimitCount();
            // 最近是否购买过该商品
            Long goodsNum = orderser.selectGoodsInfoCount(((ShoppingCart) shoplist.get(i)).getGoodsDetailBean().getProductVo().getGoodsInfoId(), customerId,
                    marketing.getMarketingBegin());
            if (goodsNum != null) {
                stock = stock - goodsNum;
                if (stock < 0) {
                    stock = 0L;
                }
            }
            if (((ShoppingCart) shoplist.get(i)).getGoodsDetailBean().getProductVo().getGoodsInfoStock() - stock >= 0) {
                // 如果存在限购，则把库存改为限购数量
                ((ShoppingCart) shoplist.get(i)).getGoodsDetailBean().getProductVo().setGoodsInfoStock(stock);

            }
        }
        ((ShoppingCart) shoplist.get(i)).setLimitMarket(marketing);
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
     * @param shoppingCartId
     * @param goodsInfoId
     * @param fitId
     * @param request
     * @param response
     * @return
     */
    @Override
    public int delGoodsGroupByS(Long shoppingCartId, Long goodsInfoId, Long fitId, HttpServletRequest request, HttpServletResponse response) {
        // 客户id
        Long customerId = (Long) request.getSession().getAttribute(CUSTOMERID);
        Long gId = null;
        if (customerId == null) {
            gId = Long.parseLong(PROINFO + fitId);
        }
        // 先查询到分组下的所有商品
        // 所有的优惠分组
        GoodsGroupVo goodsGroupVo = goodsGroupService.queryVoByPrimaryKey(fitId);
        // 优惠分组下的所有商品
        List<GoodsGroupReleProductVo> vos = goodsGroupVo.getProductList();
        Long dId = 0L;
        Object oid = request.getSession().getAttribute(DISTINCTID);
        if (oid != null) {
            dId = (Long) oid;
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
                    LOGGER.error("添加购物车失败！" + e);
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
     * @param shoppingId
     *            购物车id
     * @param status
     *            要修改的状态
     * @param request
     * @param response
     * @return
     */
    @Override
    public String changeShopStatus(Long shoppingId, String status, HttpServletRequest request, HttpServletResponse response) {
        // 获取当前选中状态的session
        Object obj = request.getSession().getAttribute(NPSTORESHOPSTATUS);
        String newStr = "";
        StringBuilder buf = new StringBuilder();
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
        request.getSession().setAttribute(NPSTORESHOPSTATUS, newStr);
        return status;
    }

    /**
     * 批量更改商品选中状态
     *
     * @param shoppingId
     *            购物车id
     * @param status
     *            要修改的状态
     * @param request
     * @param response
     * @return
     */
    @Override
    public String changeShopStatusByParam(Long[] shoppingId, String status, HttpServletRequest request, HttpServletResponse response) {
        StringBuilder newStr = new StringBuilder();
        for (Long id : shoppingId) {
            newStr.append(status);
            newStr.append("e");
            newStr.append(id);
            newStr.append("-");
        }
        request.getSession().setAttribute(NPSTORESHOPSTATUS, newStr);
        return status;
    }

    /**
     * 限购商品
     *
     * @param shoppingCart
     * @param custId
     * @return
     */
    public int limtShopping(ShoppingCart shoppingCart, Long custId) {

        GoodsProductVo productVo = goodsProductService.getGoodsProductVoWithGoods(shoppingCart.getGoodsInfoId());

        // 限购
        Marketing mk = marketService.marketingDetail(marketService.queryByCreatimeMarketings(productVo.getGoodsInfoId(), 3L, productVo.getGoods().getCatId(), productVo.getGoods()
                .getBrandId()));
        // 存在限购
        if (mk != null) {
            Long stock = mk.getLimitBuyMarketing().getLimitCount();
            // 最近是否购买过该商品
            Long goodsNum = orderser.selectGoodsInfoCount(shoppingCart.getGoodsInfoId(), custId, mk.getMarketingBegin());
            if (goodsNum != null && stock - goodsNum - shoppingCart.getGoodsNum() <= 0) {
                return 0;
            }
        }
        return 1;
    }

    /**
     * 立即加入购物车
     *
     * @param shoppingCart
     * @param response
     * @param request
     * @return
     */
    @Override
    public Long selectLastId(ShoppingCart shoppingCart, HttpServletResponse response, HttpServletRequest request) {
        try {
            addShoppingCart(shoppingCart, request, response);
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("添加购物车错误" + e);
        }
        return shoppingCartMapper.selectLastId(shoppingCart);
    }

    /**
     * 一键下单
     *
     * @param box
     * @param request
     * @param wareUtil
     * @return
     */
    @Override
    public Long[] insertShoppingCart(Long[] box, HttpServletRequest request, ShoppingCartWareUtil wareUtil) {
        String macAdd = UUID.randomUUID().toString().substring(0, 10);
        macAdd = macAdd.replaceAll("-", "");
        long result = customerServiceInterface.checkUsernameFlag(macAdd);
        CustomerAllInfo customerInfo = new CustomerAllInfo();
        while (result != 0) {
            // 会员名称
            customerInfo.setCustomerUsername(macAdd);
            // 会员别名
            customerInfo.setCustomerNickname("np" + TenpayUtil.buildRandom(4) + "");
            // 密码
            customerInfo.setCustomerPassword(PASSWORD);
            customerServiceMapper.addCustomer(customerInfo);
        }
        // 会员名称
        customerInfo.setCustomerUsername(macAdd);
        customerInfo.setIsTempCust("1");
        // 会员别名
        customerInfo.setCustomerNickname("np" + TenpayUtil.buildRandom(4) + "");
        // 密码
        customerInfo.setCustomerPassword(PASSWORD);
        customerServiceMapper.addCustomer(customerInfo);

        loginService.checkCustomerExists(request, macAdd, PASSWORD);
        // 设置为临时用户
        request.getSession().setAttribute("is_temp_cust", "0");
        Long custId = (Long) request.getSession().getAttribute(CUSTOMERID);
        Customer customer = customerServiceInterface.selectCustomerByUname(macAdd);

        List<ShopCarUtil> list = null;
        try {
            list = loadCookShopCar(request);
        } catch (UnsupportedEncodingException e) {
            LOGGER.error(COOKIE + e);
        }
        if (list != null && !list.isEmpty()) {
            for (ShopCarUtil su : list) {
                ShoppingCart shoppingCart = new ShoppingCart();
                if (su.getFitId() == null) {
                    shoppingCart.setGoodsInfoId(su.getProductId());
                } else {
                    // 设置套装商品id
                    shoppingCart.setGoodsInfoId(Long.parseLong(PROINFO + su.getFitId()));
                    // 设置套装id
                    shoppingCart.setFitId(su.getFitId());
                }
                shoppingCart.setCustomerId(custId);
                shoppingCart.setDelFlag("0");
                shoppingCart.setShoppingCartTime(new Date());
                shoppingCart.setGoodsNum(Long.valueOf(su.getGoodsNum()));
                shoppingCart.setMarketingId(su.getMarketId());
                shoppingCart.setMarketingActivityId(su.getMarketActiveId());
                shoppingCart.setCustomerId(customer.getCustomerId());
                shoppingCart.setGoodsNum(Long.valueOf(su.getGoodsNum()));
                int count = shoppingCartMapper.selectCountByReady(shoppingCart);

                // 进入此处标示该购物车为商品，执行商品方法
                GoodsDetailBean goodsDetailBean = goodsProductService.queryDetailBeanByProductId(shoppingCart.getGoodsInfoId(), wareUtil.getDistrictId());
                // 限购
                Marketing marketing = marketService.marketingDetail(marketService.queryByCreatimeMarketings(goodsDetailBean.getProductVo().getGoodsInfoId(), 3L, goodsDetailBean
                        .getProductVo().getGoods().getCatId(), goodsDetailBean.getBrand().getBrandId()));

                // 存在限购
                if (marketing != null) {
                    Long stock = marketing.getLimitBuyMarketing().getLimitCount();
                    // 最近是否购买过该商品
                    Long goodsNum = orderser.selectGoodsInfoCount(goodsDetailBean.getProductVo().getGoodsInfoId(), custId, marketing.getMarketingBegin());
                    if (goodsNum != null) {
                        stock = stock - goodsNum;
                        if (stock < 0) {
                            stock = 0L;
                        }
                    }
                    if (goodsDetailBean.getProductVo().getGoodsInfoStock() - stock >= 0 && stock < shoppingCart.getGoodsNum()) {
                        return new Long[0];
                    }
                }
                if (count == 0) {
                    shoppingCartMapper.addShoppingCart(shoppingCart);
                }
                for (int i = 0; i < box.length; i++) {
                    if (box[i].equals(shoppingCart.getGoodsInfoId())) {
                        box[i] = shoppingCartMapper.selectLastId(shoppingCart);
                    }

                }

            }
        }
        return box;
    }

    /**
     * 限购
     *
     * @param goodsDetailBean
     *            商品
     * @param customerId
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
     * 判断手机号是否是用户名或者已绑定
     *
     * @param mobel
     *            手机号
     * @param request
     * @return
     */
    @Override
    public long isSowMobel(String mobel, HttpServletRequest request) {
        long isExist = 0;
        Object isTempCust = request.getSession().getAttribute("is_temp_cust");
        if (isTempCust != null && "0".equals(isTempCust)) {
            isExist = customerServiceInterface.checkUsernameFlag(mobel);
            if (isExist == 0) {
                isExist = customerServiceInterface.checkMobileExist(mobel);

            } else {
                isExist = 1;
            }
        }
        return isExist;
    }

    /**
     * 查询购物商品信息
     *
     * @param list
     * @return
     */
    @Override
    public List<ShoppingCart> shopCartListByIds(List<Long> list) {

        return shoppingCartMapper.shopCartListByIds(list);
    }

    /**
     * 新商品结算页面
     *
     * @param request
     * @param box
     * @param response
     * @return
     */
    @Override
    public Map<String, Object> subshopCartMap(HttpServletRequest request, Long[] box, HttpServletResponse response, ShoppingCartWareUtil wareUtil, PointSet pointSet) {

        Map<String, Object> cartMap = new HashMap<String, Object>();

        // 查询所有购物车商品
        List<ShoppingCart> shoplist;
        // List<ParamIds> infoIds = new LinkedList<>();
        List<StoreTemp> thirdstore = new LinkedList<>();
        shoplist = shoppingCartMapper.shopCartListByIds(Arrays.asList(box));
        // 查询购物车有哪些商家的商品
        List<StoreTemp> storeList = shoppingCartMapper.selectStoreTempByshopcartIds(Arrays.asList(box));
        // 获取地址信息
        // 查询购物车商品详细
        // 判断是否为nullSiteOrderServiceImpl

//      处理单抢购的商品不能使用优惠劵.处理方式，判断商品数是否都是抢购的商品，如果是，则不查询优惠劵
//        add by 付陈林 2015年12月10日
        int shopNum=0;
        int rushNum=0;
        //edit end
        if (shoplist != null && !shoplist.isEmpty()) {
//            add by 付陈林 2015年12月10
            shopNum=shoplist.size();
            //edit end
            for (int i = 0; i < shoplist.size(); i++) {

                if (shoplist.get(i).getFitId() == null) {
                    // 查询商品详细
                    shoplist.get(i).setGoodsDetailBean(
                            goodsProductService.queryDetailBeanByProductId(shoplist.get(i).getGoodsInfoId(), shoplist.get(i).getDistinctId()));
                    // 查询商品参加的促销信息
                    /**
                     * 设置商品的营销信息id add by 付陈林 2015年12月10日
                     * **/
                    List<Marketing> markList = marketService.selectMarketingByGoodsInfoId(shoplist.get(i).getGoodsInfoId(), shoplist.get(i).getGoodsDetailBean().getBrand().getBrandId(), shoplist
                            .get(i).getGoodsDetailBean().getProductVo().getGoods().getCatId());

                    shoplist.get(i).setMarketingList(markList);
                    if(markList!=null&&markList.size()>0){
                        //2015-12-10 wuyanbo 注释此处代码（购物车中的促销、团购编号在初始化购物车数据时从数据库中查询，并非重新赋值）
                        /*shoplist.get(i).setMarketingId(markList.get(0).getMarketingId());
                        shoplist.get(i).setGoodsGroupId(markList.get(0).getGroupId());*/
                        if("11".equals(markList.get(0).getCodexType())){
                            rushNum=rushNum+1;
                        }
                    }
                } else {
                    Long stock = null;
                    // 如果商品是套装
                    GoodsGroupVo goodsGroupVo = goodsGroupService.queryVoByPrimaryKey(shoplist.get(i).getFitId());

                    shoplist.get(i).setGoodsGroupVo(goodsGroupVo);
                    StoreTemp st = new StoreTemp();
                    st.setThirdName(goodsGroupVo.getThirdName());
                    st.setThirdId(goodsGroupVo.getThirdId());
                    thirdstore.add(st);
                    // 给套装购物车塞上套装的第三方id
                    shoplist.get(i).setThirdId(goodsGroupVo.getThirdId());
                    // 该套装下所有的商品
                    List<GoodsGroupReleProductVo> goodsGroupReleProductVos = shoplist.get(i).getGoodsGroupVo().getProductList();
                    for (int j = 0; j < goodsGroupReleProductVos.size(); j++) {

                        GoodsDetailBean goodsDetailBean = goodsProductService.queryDetailBeanByProductId(goodsGroupReleProductVos.get(j).getProductDetail().getGoodsInfoId(),
                                wareUtil.getDistrictId());

                        if (stock == null) {
                            stock = goodsDetailBean.getProductVo().getGoodsInfoStock();
                        } else {
                            if (stock > goodsDetailBean.getProductVo().getGoodsInfoStock()) {
                                stock = goodsDetailBean.getProductVo().getGoodsInfoStock();
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

        storeList.addAll(thirdstore);
        // 把套装中重复的第三方id去掉
        for (int j = 0; j < storeList.size(); j++) {
            for (int k = storeList.size() - 1; k > j; k--) {
                if (storeList.get(k).getThirdId().equals(storeList.get(j).getThirdId())) {
                    storeList.remove(j);
                }

            }
            // 为购物车的第三方id重新排序,boss商城排前面
            if (storeList.get(j).getThirdId() == 0) {
                StoreTemp ste = storeList.get(0);
                storeList.set(0, storeList.get(j));
                storeList.set(j, ste);
            }
        }
        List<FreightTemplate> freight = new ArrayList<>();
        FreightTemplate ft = null;
        // 循环得到快递方式
        for (StoreTemp st : storeList) {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("freightIsDefault", "1");
            paramMap.put("freightThirdId", st.getThirdId());
            ft = freightTemplateMapper.selectFreightTemplateSubOrder(paramMap);
            if (null != ft) {
                freight.add(ft);
            }

        }
        // 查询所有参与的促销
        List<Marketing> marketinglist = marketingIdsListUtil(shoplist);
        // 根据会员ID获取积分对象
        Long customerId = (Long) request.getSession().getAttribute(CUSTOMERID);
        CustomerPoint customerPoint = couponService.selectCustomerPointByCustomerId(customerId);

        if (null != customerPoint && null != pointSet) {
            // 保存当前登录会员总积分
            cartMap.put("customerPoint", customerPoint.getPointSum());
            // 保存积分兑换金额的比例 （消费规则）
            cartMap.put("pointSet", pointSet.getConsumption());
        }
       /**
        * add by 付陈林 2015年11月
        * */
//      List<Coupon> couponList = getUsedCouponlist(request, shoplist);
        List<Coupon> couponList=new ArrayList<Coupon>();
        if(rushNum!=shopNum){
         couponList = getUsedCouponlist(request, shoplist);
         }
         /**
         * edit end
         * */
        if (CollectionUtils.isNotEmpty(freight)) {
            cartMap.put("frightlist", freight);
        }
        Customer cus = customerServiceInterface.queryCustomerById(customerId);
        CustomerPointLevel cuspointLevel = customerPointLevelMapper.selectByPrimaryKey(cus.getPointLevelId());
        cartMap.put("couponlist", couponList);
        cartMap.put("pointDiscount", cuspointLevel.getPointDiscount());
        cartMap.put(SHOPLIST, shoplist);
        cartMap.put(THIRDS, storeList);
        cartMap.put(MARKETINGLIST, marketinglist);
        return cartMap;
    }

    /**
     * 得到满足条件的优惠券
     *
     * @param request
     * @param box
     * @return
     */
    public int getUsedCouponBycodeNo(HttpServletRequest request, Long[] box, String codeNo) {

        Object object = request.getSession().getAttribute("vip");
        String vip = "0";
        if(null != object){
            vip = (String)object;
        }

        // 优惠券码
        // codeNo=request.getParameter("codeNo");

        // 当前购物车所拥有的购物券
        List<Coupon> couponList = null;
        List<Coupon> couponList2 = null;
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
                    if (CollectionUtils.isEmpty(shoplist)) {
                        break;
                    }
                }
            }

            // 商家分组后的中间变量金额
            BigDecimal sumprice = BigDecimal.ZERO;
            BigDecimal sumVipPrice = BigDecimal.ZERO;
            // 货品价格判断是否参与折扣
            BigDecimal goodsprice = BigDecimal.ZERO;
            BigDecimal goodsVipPrice = BigDecimal.ZERO;
            Map<String, Object> para = new HashMap<>();
            if (CollectionUtils.isNotEmpty(shoplist)) {

                for (int i = 0; i < shoplist.size(); i++) {

                    GoodsDetailBean detailBean = goodsProductService.queryDetailBeanByProductId((shoplist.get(i)).getGoodsInfoId(), (shoplist.get(i)).getDistinctId());
                    // 查询商品详细
                    shoplist.get(i).setGoodsDetailBean(detailBean);
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
                                goodsprice = premark.getDiscountInfo().multiply(goodsprice);
                                goodsVipPrice = premark.getDiscountInfo().multiply(goodsVipPrice);
                            }

                        }

                    }

                }
            }
            if (null != infoIds && !infoIds.isEmpty()) {
                // 查询该会员已经领取未使用的优惠券
                couponList2 = couponService.selectCouponListByIds(infoIds, request);
                // 查询订单商品可以使用的未领取的优惠券
                couponList = couponService.selectCouponListByIdsNew(infoIds, request);
                couponList.addAll(couponList2);
            }

            if (CollectionUtils.isNotEmpty(couponList)) {

                Coupon coupon = couponService.selectCouponByCodeNo(codeNo);
                if (coupon == null) {
                    return 0;
                }
                for (Coupon cou : couponList) {

                    if (cou.getCodeNo().equals(coupon.getCodeNo())) {
                        List<CouponRange> ranList = cou.getCouponrangList();
                        for (CouponRange ran : ranList) {
                            for (int i = 0; i < shoplist.size(); i++) {
                                if (cou.getCouponId().equals(ran.getCouponId()) && shoplist.get(i).getThirdId().equals(cou.getBusinessId())) {
                                    // 按分类
                                    if ("0".equals(ran.getCouponRangeType())
                                            && ran.getCouponRangeFkId().equals(shoplist.get(i).getGoodsDetailBean().getProductVo().getGoods().getCatId())) {
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
                                    if ("2".equals(ran.getCouponRangeType())
                                            && ran.getCouponRangeFkId().equals(shoplist.get(i).getGoodsDetailBean().getProductVo().getGoodsInfoId())) {
                                        // 商家的总金额
                                        sumprice = sumprice.add(goodsprice.multiply(BigDecimal.valueOf(shoplist.get(i).getGoodsNum())));
                                        sumVipPrice = sumVipPrice.add(goodsVipPrice.multiply(BigDecimal.valueOf(shoplist.get(i).getGoodsNum())));
                                    }
                                }
                            }

                            // 按品牌和分类创建优惠券判断同属一家商家
                            for (int i = 0; i < shoplist.size(); i++) {
                                if (shoplist.get(i).getThirdId().equals(cou.getBusinessId())) {
                                    // 满减
                                    if ("2".equals(cou.getCouponRulesType())) {
                                        if (!userdCoupon.contains(cou)) {
                                            // 满多少减金额
                                            BigDecimal priceflag = cou.getCouponFullReduction().getFullPrice();
                                            if("1".equals(vip)){
                                                if (priceflag.compareTo(sumVipPrice) == -1) {
                                                    userdCoupon.add(cou);
                                                }
                                            }else if("0".equals(vip)){
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
                                }
                            }
                            sumprice = BigDecimal.ZERO;
                            sumVipPrice = BigDecimal.ZERO;
                        }
                    }
                }

            }
        }
        if (CollectionUtils.isNotEmpty(userdCoupon)) {
            Coupon coupon2 = couponService.selectCouponByCodeNo(codeNo);
            if (coupon2.getCouponRulesType().equals("1")) {
                return couponService.searchCouponById(coupon2.getCouponId()).getCouponStraightDown().getDownPrice().intValue();
            } else {
                return couponService.searchCouponById(coupon2.getCouponId()).getCouponFullReduction().getReductionPrice().intValue();
            }
        } else {
            return 0;
        }
    }

    public List<Coupon> getUsedCouponlist(HttpServletRequest request, List<ShoppingCart> shoplists) {
        Object object = request.getSession().getAttribute("vip");
        String vip = "0";
        if(null != object){
            vip = (String)object;
        }

        // 当前购物车所拥有的购物券
        List<Coupon> couponList = null;
        // 过滤购物券得到满足条件优惠券
        List<Coupon> userdCoupon = new ArrayList<>();
        // 查询所有购物车商品
        List<ShoppingCart> shoplist = new ArrayList<>(shoplists);

        if (CollectionUtils.isNotEmpty(shoplist)) {
            List<ParamIds> infoIds = new LinkedList<>();
            // 去除套装,套装不允许使用优惠券
            for (ShoppingCart sp : shoplist) {
                if (sp.getFitId() != null) {
                    // 如果商品是套装,套装不允许使用优惠券
                    shoplist.remove(sp);
                    if (CollectionUtils.isEmpty(shoplist)) {
                        break;
                    }
                }
            }

            // 商家分组后的中间变量金额
            BigDecimal sumprice = BigDecimal.ZERO;
            BigDecimal sumVipPrice = BigDecimal.ZERO;
            // 货品价格判断是否参与折扣
            BigDecimal goodsprice = BigDecimal.ZERO;
            BigDecimal goodsVipPrice = BigDecimal.ZERO;
            Map<String, Object> para = new HashMap<>();
            if (CollectionUtils.isNotEmpty(shoplist)) {

                for (int i = 0; i < shoplist.size(); i++) {
                    // 查询商品详细
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
                        Marketing mark = marketService.querySimpleMarketingById(shoplist.get(i).getMarketingId());
                        if (mark != null) {
                            para.put(MARKETINGID, mark.getMarketingId());
                            para.put(GOODSID, shoplist.get(i).getGoodsInfoId());
                            PreDiscountMarketing premark = preDiscountMarketingMapper.selectByMarketId(para);
                            if (premark != null) {
                                // 货品价格
                                goodsprice = premark.getDiscountInfo().multiply(goodsprice);
                                goodsVipPrice = premark.getVipdiscountInfo().multiply(goodsVipPrice);
                            }

                        }

                    }
                    shoplist.get(i).setGoodsPrice(goodsprice);
                    shoplist.get(i).setGoodsPrePrice(goodsVipPrice);

                }
            }
            if (null != infoIds && !infoIds.isEmpty()) {

                couponList = couponService.selectCouponListByIds(infoIds, request);
            }

            if (CollectionUtils.isNotEmpty(couponList)) {

                for (Coupon cou : couponList) {

                    List<CouponRange> ranList = cou.getCouponrangList();
                    for (CouponRange ran : ranList) {
                        for (int i = 0; i < shoplist.size(); i++) {
                            if (cou.getCouponId().equals(ran.getCouponId()) && shoplist.get(i).getThirdId().equals(cou.getBusinessId())) {
                                        // 按分类
                                        if ("0".equals(ran.getCouponRangeType())
                                                && ran.getCouponRangeFkId().equals(shoplist.get(i).getGoodsDetailBean().getProductVo().getGoods().getCatId())) {
                                            // 商家的总金额
                                            sumprice = sumprice.add(shoplist.get(i).getGoodsPrice().multiply(BigDecimal.valueOf(shoplist.get(i).getGoodsNum())));
                                            sumVipPrice = sumVipPrice.add(shoplist.get(i).getGoodsPrePrice().multiply(BigDecimal.valueOf(shoplist.get(i).getGoodsNum())));
                                        }
                                        // 按品牌
                                        if ("1".equals(ran.getCouponRangeType()) && ran.getCouponRangeFkId().equals(shoplist.get(i).getGoodsDetailBean().getBrand().getBrandId())) {
                                            // 商家的总金额
                                            sumprice = sumprice.add(shoplist.get(i).getGoodsPrice().multiply(BigDecimal.valueOf(shoplist.get(i).getGoodsNum())));
                                            sumVipPrice = sumVipPrice.add(shoplist.get(i).getGoodsPrePrice().multiply(BigDecimal.valueOf(shoplist.get(i).getGoodsNum())));
                                        }
                                        // 按货品
                                        if ("2".equals(ran.getCouponRangeType()) && ran.getCouponRangeFkId().equals(shoplist.get(i).getGoodsDetailBean().getProductVo().getGoodsInfoId())) {
                                            // 商家的总金额
                                            sumprice = sumprice.add(shoplist.get(i).getGoodsPrice().multiply(BigDecimal.valueOf(shoplist.get(i).getGoodsNum())));
                                            sumVipPrice = sumVipPrice.add(shoplist.get(i).getGoodsPrePrice().multiply(BigDecimal.valueOf(shoplist.get(i).getGoodsNum())));
                                }
                            }
                        }
//                        edit by 付陈林 2015年12月7日
//                        // 按品牌和分类创建优惠券判断同属一家商家
//                        for (int i = 0; i < shoplist.size(); i++) {
//                            if (shoplist.get(i).getThirdId().equals(cou.getBusinessId())) {
//                                // 满减
//                                if ("2".equals(cou.getCouponRulesType())) {
//                                    if (!userdCoupon.contains(cou)) {
//                                        // 满多少减金额
//                                        BigDecimal priceflag = cou.getCouponFullReduction().getFullPrice();
//                                        if("1".equals(vip)){
//                                            if (priceflag.compareTo(sumVipPrice) == -1) {
//                                                userdCoupon.add(cou);
//                                            }
//                                        }else if("0".equals(vip)){
//                                            if (priceflag.compareTo(sumprice) == -1) {
//                                                userdCoupon.add(cou);
//                                            }
//                                        }
//                                    }
//                                } else {
//                                    // 直降的优惠券没有限制都允许
//                                    if (!userdCoupon.contains(cou)) {
//                                        userdCoupon.add(cou);
//                                    }
//                                }
//                            }
//                        }
//                        sumprice = BigDecimal.ZERO;
//                        sumVipPrice = BigDecimal.ZERO;
                    }
                    // 按品牌和分类创建优惠券判断同属一家商家
                    for (int i = 0; i < shoplist.size(); i++) {
                        if (shoplist.get(i).getThirdId().equals(cou.getBusinessId())) {
                            // 满减
                            if ("2".equals(cou.getCouponRulesType())) {
                                if (!userdCoupon.contains(cou)) {
                                    // 满多少减金额
                                    BigDecimal priceflag = cou.getCouponFullReduction().getFullPrice();
                                    if("1".equals(vip)){
                                        if (priceflag.compareTo(sumVipPrice) == -1) {
                                            userdCoupon.add(cou);
                                        }
                                    }else if("0".equals(vip)){
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
                        }
                    }
                    sumprice = BigDecimal.ZERO;
                    sumVipPrice = BigDecimal.ZERO;
//                    edit end;

                }

            }
        }
        return userdCoupon;
    }

    /**
     * 新产品使用购物车
     *
     * @param request
     * @param wareUtil
     * @param response
     * @return
     */
    @Override
    public Map<String, Object> shopCartMap(HttpServletRequest request, ShoppingCartWareUtil wareUtil, HttpServletResponse response) {
        Map<String, Object> cartMap = new HashMap<String, Object>();
        // 获取用户ID
        Long customerId = (Long) request.getSession().getAttribute(CUSTOMERID);
        // 如果用户登录了
        if (customerId != null) {
            // 修改购物车库存id
            Map<String, Object> upMap = new HashMap<String, Object>();
            upMap.put(DISTINCTID, wareUtil.getDistrictId());
            upMap.put(CUSTOMERID, customerId);
            this.shoppingCartMapper.updShoppingCartHouseByCstId(upMap);

            // 查询所有购物车商品
            List<ShoppingCart> shoplist = shoppingCartMapper.selectShoppingCartListByCustomerId(customerId);
            // 查询购物车商品详细
            selectShoppingCartDetail(customerId,shoplist, wareUtil);

            // 查询购物车有哪些商家的商品
            List<StoreTemp> storeList = shoppingCartMapper.selectStoreTemp(customerId);

            haveBoss(storeList, shoplist);
            // 查询所有参与的促销
            List<Marketing> marketinglist = marketingIdsListUtil(shoplist);
            cartMap.put(SHOPLIST, shoplist);
            cartMap.put(THIRDS, storeList);
            cartMap.put(MARKETINGLIST, marketinglist);

        } else {
            try {
                // 获取cookie中的数据
                List<ShopCarUtil> list = loadCookShopCar(request);
                // 定义购物车List
                List<ShoppingCart> shoplist = new ArrayList<ShoppingCart>();
                if (list != null && !list.isEmpty()) {
                    for (int i = 0; i < list.size(); i++) {
                        // 封装数据
                        ShoppingCart sc = new ShoppingCart();
                        sc.setGoodsInfoId(list.get(i).getProductId());
                        sc.setShoppingCartId(list.get(i).getProductId());
                        sc.setMarketingId(list.get(i).getMarketId());
                        sc.setMarketingActivityId(list.get(i).getMarketActiveId());
                        //团购促销优惠id
                        sc.setGoodsGroupId(list.get(i).getGoodsGroupMarketingId());
                        sc.setGoodsNum(Long.valueOf(list.get(i).getGoodsNum()));
                        sc.setMarketing(null);
                        sc.setFitId(list.get(i).getFitId());
                        sc.setDistinctId(list.get(i).getDistinctId());
                        shoplist.add(sc);
                    }

                    // 查询购物车商品详细
                    selectShoppingCartDetail(null,shoplist, wareUtil);
                    // 查询购物车有哪些商家的商品
                    List<StoreTemp> storeList = storeUtil(shoplist);
                    // 查询所有参与的促销
                    List<Marketing> marketinglist = marketingIdsListUtil(shoplist);
                    cartMap.put(SHOPLIST, shoplist);
                    cartMap.put(THIRDS, storeList);
                    cartMap.put(MARKETINGLIST, marketinglist);
                }

            } catch (UnsupportedEncodingException e) {
                LOGGER.error("新购物车错误", e);
            }

        }

        return cartMap;
    }

    /**
     * 存在boss商品
     *
     * @param storeList
     * @param shoplist
     */
    private void haveBoss(List<StoreTemp> storeList, List<ShoppingCart> shoplist) {
        Long thirdId = 0L;
        // 查询站点信息
        BasicSet bs = basicSetMapper.findBasicSet();
        // 将thirdName为boss的替换成站点的名称
        if (storeList != null && !storeList.isEmpty()) {
            for (int i = 0; i < storeList.size(); i++) {
                if (storeList.get(i).getThirdId().equals(thirdId)) {
                    // 赋值
                    // storeList.get(i).setThirdName(bs.getBsetName());
                    // 赋值，平台商品直接显示为商城自营
                    storeList.get(i).setThirdName("商城自营");
                }
            }
        }
        // 判断是否有套装
        int haveFit = 0;
        if (shoplist != null && !shoplist.isEmpty()) {
            for (int i = 0; i < shoplist.size(); i++) {
                if (shoplist.get(i).getFitId() != null) {
                    haveFit++;
                }
            }

        }

        int haveThird = 0;

        // haveFIt>0有套装
        if (haveFit > 0 && storeList != null && !storeList.isEmpty()) {
            // 判断storeList是否含有Boss信息
            for (int i = 0; i < storeList.size(); i++) {
                if (storeList.get(i).getThirdId().equals(thirdId)) {
                    haveThird++;
                }

            }
            if (haveThird == 0) {
                StoreTemp st = new StoreTemp();
                st.setThirdId(0L);
                st.setThirdName(bs.getBsetName());
                storeList.add(st);
            }

        }
    }

    /**
     * 查询ShoppingCart 每个商品的详细信息
     *
     * @param shoppingCartList
     * @param cartWareUtil
     */
    public void selectShoppingCartDetail(Long customerId,List<ShoppingCart> shoppingCartList, ShoppingCartWareUtil cartWareUtil) {
        // 判断是否为null
        if (shoppingCartList != null && !shoppingCartList.isEmpty()) {


            for (int i = 0; i < shoppingCartList.size(); i++) {
                // 如果商品不是套装
                if (shoppingCartList.get(i).getFitId() == null) {
                    // 查询商品详细
                    shoppingCartList.get(i).setGoodsDetailBean(
                            goodsProductService.queryDetailBeanByProductId(shoppingCartList.get(i).getGoodsInfoId(),
                                    shoppingCartList.get(i).getDistinctId()));
                    List<Marketing> marketings = null;
                    if (shoppingCartList.get(i).getGoodsDetailBean().getProductVo() != null) {

                        // 查询商品参加的促销信息
                        marketings = marketService.selectMarketingByGoodsInfoId(shoppingCartList.get(i).getGoodsInfoId(), shoppingCartList.get(i).getGoodsDetailBean().getBrand()
                                .getBrandId(), shoppingCartList.get(i).getGoodsDetailBean().getProductVo().getGoods().getCatId());
                    }
                    // 赋值
                    shoppingCartList.get(i).setMarketingList(marketings);

                    //折扣促销的价格中间变量
                    BigDecimal goodsPrice=shoppingCartList.get(i).getGoodsDetailBean().getProductVo().getGoodsInfoPreferPrice();
                    //折扣促销的Vip价格中间变量
                    BigDecimal goodsVipPrice=shoppingCartList.get(i).getGoodsDetailBean().getProductVo().getGoodsInfoVipPrice();
                    //团购促销价格的中间变量
                    BigDecimal goodsGroupPrice=shoppingCartList.get(i).getGoodsDetailBean().getProductVo().getGoodsInfoPreferPrice();
                    //团购促销Vip价格的中间变量
                    BigDecimal goodsGroupVipPrice=shoppingCartList.get(i).getGoodsDetailBean().getProductVo().getGoodsInfoVipPrice();
                    //判断是否参与了团购
                    int isgroup=0;
                    if (marketings != null && !marketings.isEmpty()) {

                        Map<String,Object> rushMap=new HashMap<>();

                        for (Marketing mark : marketings) {
                            /**
                             * edit by 付陈林
                             * 去除未登录的判断，
                             * if(mark.getRushs()!=null && !mark.getRushs().isEmpty() && customerId!=null){
                             * **/
                             if(mark.getRushs()!=null && !mark.getRushs().isEmpty()){
                                rushMap.put("rushId",mark.getRushs().get(0).getRushId());
                                rushMap.put("customerId",customerId);
                                rushMap.put("goodsInfoId",shoppingCartList.get(i).getGoodsInfoId());
                                //当存在抢购时 得到每个会员可以购买的货品数量
                                Integer countRush = rushCustomerMapper.selectByParamMap(rushMap);
                                countRush=mark.getRushs().get(0).getRushLimitation()-countRush;
                                mark.setCustomerbuynum(countRush);
                                 /***
                                  * 设置抢购金额
                                  * */
                                /*BigDecimal rushPrice = shoppingCartList.get(i).getGoodsDetailBean().getProductVo().getGoodsInfoPreferPrice().multiply(mark.getRushs().get(0).getRushDiscount());
                                BigDecimal rushVipPrice = shoppingCartList.get(i).getGoodsDetailBean().getProductVo().getGoodsInfoVipPrice().multiply(mark.getRushs().get(0).getRushVipDiscount());*/
                                // 2015-12-09 wuyanbo 修改抢购价计算方式（直接取计算后的抢购价）
                                BigDecimal rushPrice = mark.getRushs().get(0).getRushPrice();
                                BigDecimal rushVipPrice = mark.getRushs().get(0).getRushVipPrice();
                                 shoppingCartList.get(i).getGoodsDetailBean().getProductVo().setGoodsInfoPreferPrice(rushPrice);
                                shoppingCartList.get(i).getGoodsDetailBean().getProductVo().setGoodsInfoVipPrice(rushVipPrice);
                            }
//                            edit end
                            //当存在团购促销时(即使存在折扣促销)优先级为团购促销()
                            if(mark.getGroupon()!=null /*&& mark.getMarketingId().equals(shoppingCartList.get(i).getGoodsGroupId())*/){
                                isgroup=1;
                                shoppingCartList.get(i).getGoodsDetailBean().getProductVo().setGoodsInfoPreferPrice(mark.getGroupon().getGrouponPrice());
                                shoppingCartList.get(i).getGoodsDetailBean().getProductVo().setGoodsInfoVipPrice(mark.getGroupon().getGrouponVipPrice());
                            }else{
                                // 判断选择的促销是否过期或删除
                                if (mark.getPreDiscountMarketings() != null && !mark.getPreDiscountMarketings().isEmpty() /*&& isgroup==0*/) {
                                    for (PreDiscountMarketing premark : mark.getPreDiscountMarketings()) {
                                        // 参与了折扣
                                        if (premark.getGoodsId().equals(shoppingCartList.get(i).getGoodsInfoId())) {
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
                                            goodsPrice= goodsPrice.multiply(premark.getDiscountInfo());
                                            goodsPrice = BigDecimal.valueOf(Double.valueOf(myformat.format(goodsPrice)));
                                            shoppingCartList.get(i).getGoodsDetailBean().getProductVo().setGoodsInfoPreferPrice(goodsPrice);
                                            //VIP
                                            goodsVipPrice= goodsVipPrice.multiply(premark.getVipdiscountInfo());
                                            goodsVipPrice = BigDecimal.valueOf(Double.valueOf(myformat.format(goodsVipPrice)));
                                            shoppingCartList.get(i).getGoodsDetailBean().getProductVo().setGoodsInfoVipPrice(goodsVipPrice);
                                        }

                                    }
                                }
                            }

                        }

                        int marketingNum = 0;// 记录其他促销
                        int marketingActivityNum = 0;// 记录折扣促销

                        for (int j = 0; j < marketings.size(); j++) {
                            // 判断折扣促销是否过期或删除
                            if (shoppingCartList.get(i).getMarketingId() != null && shoppingCartList.get(i).getMarketingId().equals(marketings.get(j).getMarketingId())) {
                                marketingNum += 1;

                            }
                            // 判断其他促销促销是否过期或删除
                            if (shoppingCartList.get(i).getMarketingActivityId() != null
                                    && shoppingCartList.get(i).getMarketingActivityId().equals(marketings.get(j).getMarketingId())) {
                                marketingActivityNum += 1;
                            }

                        }
                        // 折扣促销过期或删除
                        if (marketingNum == 0) {
                            shoppingCartList.get(i).setMarketingId(null);
                        }
                        // 其他促销促销过期或删除
                        if (marketingActivityNum == 0) {
                            shoppingCartList.get(i).setMarketingActivityId(null);
                        }
                    }
                } else {
                    // 如果商品是套装
                    shoppingCartList.get(i).setThirdId(0L);
                    Long stock = null;
                    GoodsGroupVo goodsGroupVo = goodsGroupService.queryVoByPrimaryKey(shoppingCartList.get(i).getFitId());
                    shoppingCartList.get(i).setGoodsGroupVo(goodsGroupVo);
                    // 该套装下所有的商品
                    List<GoodsGroupReleProductVo> goodsGroupReleProductVos = shoppingCartList.get(i).getGoodsGroupVo().getProductList();
                    for (int j = 0; j < goodsGroupReleProductVos.size(); j++) {
                        GoodsDetailBean goodsDetailBean = null;

                        if (goodsGroupReleProductVos.get(j).getProductDetail() != null) {
                            goodsDetailBean = goodsProductService.queryDetailBeanByProductId(goodsGroupReleProductVos.get(j).getProductDetail().getGoodsInfoId(),
                                    cartWareUtil.getDistrictId());
                            if (stock == null) {
                                stock = goodsDetailBean.getProductVo().getGoodsInfoStock();
                            } else {
                                if (stock > goodsDetailBean.getProductVo().getGoodsInfoStock()) {
                                    stock = goodsDetailBean.getProductVo().getGoodsInfoStock();
                                }
                            }
                            // 重新设置套装下商品库存
                            shoppingCartList.get(i).getGoodsGroupVo().getProductList().get(j).getProductDetail()
                                    .setGoodsInfoStock(goodsDetailBean.getProductVo().getGoodsInfoStock());
                        }

                    }
                    shoppingCartList.get(i).getGoodsGroupVo().setStock(stock);
                    // 重新设置list
                    shoppingCartList.get(i).getGoodsGroupVo().setProductList(goodsGroupReleProductVos);
                    // 设置套装商品库存end
                }
            }

        }

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
                        // 排除折扣促销、包邮
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
     * 格式化商户 踢除重复的商户
     *
     * @param shoplist
     * @return List
     */
    public List<StoreTemp> storeUtil(List<ShoppingCart> shoplist) {
        // 申明
        List<StoreTemp> storeList = new ArrayList<StoreTemp>();
        // 判断商品是否为null
        if (shoplist != null && !shoplist.isEmpty()) {
            // 循环
            for (int i = 0; i < shoplist.size(); i++) {
                // 如果当前为第一个 直接放进去
                if (shoplist.get(i).getGoodsDetailBean() == null) {
                    GoodsDetailBean gdb = new GoodsDetailBean();
                    GoodsProductVo pv = new GoodsProductVo();
                    pv.setThirdId(0L);
                    pv.setThirdName("商城自营");
                    gdb.setProductVo(pv);
                    shoplist.get(i).setGoodsDetailBean(gdb);
                }
                if (storeList.isEmpty()) {
                    // 定义StoreTemp
                    StoreTemp st = new StoreTemp();
                    st.setThirdId(shoplist.get(i).getGoodsDetailBean().getProductVo().getThirdId());
                    if ("BOSS".equals(shoplist.get(i).getGoodsDetailBean().getProductVo().getThirdName())) {
                        st.setThirdName("商城自营");
                    } else {
                        st.setThirdName(shoplist.get(i).getGoodsDetailBean().getProductVo().getThirdName());
                    }
                    // 加入list
                    storeList.add(st);
                } else {
                    // 定义变量
                    int s = 0;
                    for (int j = 0; j < storeList.size(); j++) {
                        // 如果已经加入的已经存在相同的 s++

                        if (storeList.get(j).getThirdId().equals(shoplist.get(i).getGoodsDetailBean().getProductVo().getThirdId())) {
                            s++;
                        }

                    }
                    // 如果没有相同的
                    if (s == 0) {
                        // 定义StoreTemp
                        StoreTemp st = new StoreTemp();
                        st.setThirdId(shoplist.get(i).getGoodsDetailBean().getProductVo().getThirdId());
                        if ("BOSS".equals(shoplist.get(i).getGoodsDetailBean().getProductVo().getThirdName())) {
                            st.setThirdName("商城自营");
                        } else {
                            st.setThirdName(shoplist.get(i).getGoodsDetailBean().getProductVo().getThirdName());
                        }
                        // 加入list
                        storeList.add(st);
                    }

                }
            }

        }

        return storeList;
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
            paramMap.put(com.ningpai.other.util.CustomerConstantStr.STARTNUM, pb.getStartRowNum());
            paramMap.put(com.ningpai.other.util.CustomerConstantStr.ENDNUM, pb.getEndRowNum());
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
