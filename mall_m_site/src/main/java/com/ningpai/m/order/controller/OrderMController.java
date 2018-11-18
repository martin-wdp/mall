/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.m.order.controller;

import com.ningpai.common.safe.CSRFTokenManager;
import com.ningpai.common.util.ConstantUtil;
import com.ningpai.common.util.alipay.sign.MD5;
import com.ningpai.common.util.alipay.util.UtilDate;
import com.ningpai.common.util.alipaymobile.config.AlipayConfig;
import com.ningpai.common.util.alipaymobile.util.AlipayNotify;
import com.ningpai.common.util.alipaymobile.util.AlipaySubmit;
import com.ningpai.common.util.tenpay.util.MD5Util;
import com.ningpai.coupon.bean.Coupon;
import com.ningpai.coupon.service.CouponService;
import com.ningpai.customer.bean.Customer;
import com.ningpai.customer.bean.CustomerAddress;
import com.ningpai.customer.service.CustomerServiceMapper;
import com.ningpai.freighttemplate.service.FreightTemplateService;
import com.ningpai.goods.service.GoodsGroupService;
import com.ningpai.goods.service.GoodsProductService;
import com.ningpai.goods.vo.GoodsGroupVo;
import com.ningpai.goods.vo.GoodsProductVo;
import com.ningpai.m.common.service.SeoService;
import com.ningpai.m.customer.service.CustomerAddressService;
import com.ningpai.m.customer.vo.CustomerConstants;
import com.ningpai.m.order.bean.OrderAddress;
import com.ningpai.m.order.service.OrderMService;
import com.ningpai.m.order.service.OrderPayService;
import com.ningpai.m.shoppingcart.bean.ShoppingCart;
import com.ningpai.m.shoppingcart.service.ShoppingCartService;
import com.ningpai.m.util.HttpRequestUtils;
import com.ningpai.m.util.HttpXmlRequestUtils;
import com.ningpai.m.util.PayTools;
import com.ningpai.m.weixin.controller.WXActivityController;
import com.ningpai.m.weixin.util.WXSendMSG;
import com.ningpai.marketing.bean.Marketing;
import com.ningpai.marketing.service.MarketingService;
import com.ningpai.order.bean.Order;
import com.ningpai.order.service.OrderService;
import com.ningpai.other.util.CustomerConstantStr;
import com.ningpai.system.bean.DeliveryPoint;
import com.ningpai.system.bean.Pay;
import com.ningpai.system.bean.Receivables;
import com.ningpai.system.dao.IExpressConfDao;
import com.ningpai.system.service.DeliveryPointService;
import com.ningpai.system.service.PayService;
import com.ningpai.system.service.PaymentService;
import com.ningpai.system.service.ReceivablesService;
import com.ningpai.system.service.impl.ExpressConfBizImpl;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PropertieUtil;
import com.ningpai.util.ShoppingCartConstants;
import com.qq.connect.utils.http.HttpClient;
import com.tenpay.ClientRequestHandler;
import com.tenpay.PackageRequestHandler;
import com.tenpay.PrepayIdRequestHandler;
import com.tenpay.util.WXUtil;
import com.tenpay.util.XMLUtil;
import com.unionpay.mpi.LogUtil;
import com.unionpay.mpi.MpiConfig;
import com.unionpay.mpi.MpiUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.*;

/**
 * 订单控制器
 *
 * @author NINGPAI-LIH
 * @version 1.0
 * @since 2014年4月14日 下午5:35:48
 */
@Controller
public class OrderMController {

    private static final MyLogger LOGGER = new MyLogger(OrderMController.class);

    private static final String CH_PAY = "ch_pay";
    private static final String TYPEID = "typeId";
    private static final String ADDRESSID = "addressId";
    private static final String DELIVERYPOINTID = "deliveryPointId";
    private static final String LOGIN_HTML = "../login.html";
    private static final String ISO_8859_1 = "ISO-8859-1";
    private static final String ORDER = "order";
    private static final String PAYLIST = "paylist";
    private static final String OUT_TRADE_NO = "out_trade_no";
    private static final String UTF_8 = "UTF-8";
    private static final String SUCCESS = "success";
    private static final String UNKNOWN = "unknown";
    /**
     * 物流信息详情页
     */
    public static final String LOGISTICS = "customer/logistics";
    @Resource(name = "payService")
    private PayService payService;

    @Resource(name = "receivablesService")
    private ReceivablesService receivablesService;

    private ExpressConfBizImpl expressConfBizImpl;

    private ShoppingCartService shoppingCartService;

    private OrderMService siteOrderService;

    private MarketingService marketingService;

    private OrderService orderser;

    private GoodsProductService goodsProductService;

    @Resource(name = "customerAddressServiceM")
    private CustomerAddressService addressService;

    @Resource(name = "SeoService")
    private SeoService seoService;

    @Resource(name = "FreightTemplateService")
    private FreightTemplateService freightTemplateService;

    @Resource(name = "GoodsGroupService")
    private GoodsGroupService goodsGroupService;

    @Resource(name = "customerAddressServiceM")
    private CustomerAddressService customerAddressService;

    @Resource(name = "OrderPayService")
    private OrderPayService orderPayService;

    @Resource(name = "CouponService")
    private CouponService couponService;

    @Resource(name = "customerServiceMapper")
    private CustomerServiceMapper customerServiceMapper;

    // 自提点
    @Resource(name = "DeliveryPointService")
    private DeliveryPointService deliveryPointService;

    @Resource(name = "PaymentService")
    PaymentService paymentService;

    /**
     * 配送方式设置数据操作类
     */
    @Resource(name = "expressConfDaoImpl")
    private IExpressConfDao expressConfDaoImpl;

    /**
     * 获取价格
     */
    @RequestMapping("/getMexpressprice")
    @ResponseBody
    public String getExpressPrice(Long addressId, HttpServletRequest request, Long distributionId) {
        // 用户编号
        Long customerId = (Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID);
        String thirdId = request.getParameter("thirdIds");
        String cartId = request.getParameter("cartIds");
        String[] thirdIds = thirdId.split(",");
        String[] cartIds = cartId.split(",");

        // 获取 收获地址城市
        CustomerAddress ca = addressService.queryCustomerAddressById(addressId, customerId);
        if (ca == null) {
            return "0";
        }
        BigDecimal price = new BigDecimal(0);
        // 判断商家订单
        for (int i = 0; i < thirdIds.length; i++) {
            if (thirdIds[i] != null && !"".equals(thirdIds[i])) {

                // 获取该订单的购物车Id
                List<Long> shopIds = new ArrayList<Long>();

                if ("0".equals(thirdIds[i])) {
                    for (int j = 0; j < cartIds.length; j++) {
                        // 获取购物车商品个数和重量
                        String s = cartIds[j];
                        String[] ids = s.split("-");
                        if (ids != null && ids.length != 0) {
                            for (String id : ids) {
                                shopIds.add(Long.valueOf(id));

                            }
                        }
                    }
                } else {
                    // 获取购物车商品个数和重量
                    String s = cartIds[i];
                    String[] ids = s.split("-");
                    if (ids != null && ids.length != 0) {
                        for (String id : ids) {
                            shopIds.add(Long.valueOf(id));

                        }
                    }
                }

                // 添加购物车id
                Long[] box = new Long[shopIds.size()];
                for (int j = 0; j < shopIds.size(); j++) {
                    box[j] = shopIds.get(j);
                }
                List<ShoppingCart> shlist = shoppingCartService.searchByProduct(request, box);
                BigDecimal weight = new BigDecimal(0);
                Integer nums = 0;
                if (shlist != null && !shlist.isEmpty()) {
                    for (ShoppingCart sc : shlist) {
                        // 判断是否是套装
                        if (sc.getFitId() == null) {
                            // 如果是普通商品，执行普通商品的方法
                            GoodsProductVo goodsProduct = goodsProductService.queryByPrimaryId(sc.getGoodsInfoId());
                            if (goodsProduct != null && "0".equals(goodsProduct.getIsMailBay())) {
                                weight = weight.add(goodsProduct.getGoodsInfoWeight().multiply(new BigDecimal(sc.getGoodsNum())));
                                nums += Integer.parseInt(sc.getGoodsNum().toString());
                            }

                        } else {
                            // 套装运费计算
                            GoodsGroupVo goodsGroupVo = goodsGroupService.queryVoByPrimaryKey(sc.getFitId());
                            // 遍历套装下的商品
                            for (int j = 0; j < goodsGroupVo.getProductList().size(); j++) {

                                weight = weight.add(goodsGroupVo.getProductList().get(j).getProductDetail().getGoodsInfoWeight().multiply(new BigDecimal(sc.getGoodsNum())));
                                nums += Integer.parseInt(sc.getGoodsNum().toString());

                            }

                        }
                    }

                }
                // 邮费计算
                price = price.add(freightTemplateService.getExpressPrice(distributionId, ca.getCity().getCityId(), Long.valueOf(thirdIds[i]), nums, weight));
            }

        }

        // 邮费
        return price.toString();
    }

    /**
     * 新运费计算流程
     *
     * @param addressId
     * @param request
     * @param
     * @return
     */
    @RequestMapping("/getnewMexpressprice")
    @ResponseBody
    public Map<String, Object> getnewexpressprice(Long addressId, HttpServletRequest request) {
        Object object = request.getSession().getAttribute("vip");
        String vip = "0";
        if (null != object) {
            vip = (String) object;
        }

        String[] shopCartIds = request.getParameterValues("shopIds[]");
        List<Long> cartList = new ArrayList<>();
        Long customerId = (Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID);
        if (shopCartIds != null && shopCartIds.length != 0) {
            for (String str : shopCartIds) {
                cartList.add(Long.parseLong(str));
            }
        }
        // 获取 收获地址城市
        CustomerAddress ca = customerAddressService.queryCustomerAddressById(addressId, customerId);
        if (ca == null) {
            return null;
        }
        Map<String, Object> freightMap = null;
        if (CollectionUtils.isNotEmpty(cartList) && addressId != null) {
            freightMap = shoppingCartService.getNewExpressPrice(ca.getCity().getCityId(), cartList, vip);
        }

        return freightMap;
    }

    /**
     * 跳转到更改发票页
     *
     * @return
     */
    @RequestMapping("tochangeInvoice")
    public ModelAndView tochangeInvoice(HttpServletRequest request,  String invoiceType, String invoiceTitle, Long chPay, Long deliveryPointId, Long typeId, Long addressId) {
        Long customerId = (Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID);
        if (customerId == null) {
            return new ModelAndView(CustomerConstants.REDIRECTLOGINTOINDEX);
        } else {
            ModelAndView mav = new ModelAndView("order/invoice");
                mav.addObject("invoiceType", invoiceType).addObject("invoiceTitle", invoiceTitle);
            // 支付类型
            mav.addObject(CH_PAY, chPay);
            // 物流方式
            mav.addObject(TYPEID, typeId);
            // 收件地址
            mav.addObject(ADDRESSID, addressId);

            // 自提点
            mav.addObject(DELIVERYPOINTID, deliveryPointId);
            mav.addObject(ConstantUtil.PAGENAME, "发票信息");
            return mav;
        }

    }

    /**
     * 跳转到订单确认页
     *
     * @param box       购物车id
     * @param request   促销id
     *                  <p/>
     *                  第三方标识
     * @param
     * @param addressId 地址id
     * @param typeId    0：快递配送 1：上面自提
     * @return
     * @throws UnsupportedEncodingException
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("/suborder")
    public ModelAndView subOrder(Long[] box, String invoiceTitle, String invoiceType, HttpServletRequest request, Long deliveryPointId, Long chPay, Long chExpress, Long addressId, Long typeId, OrderAddress orderAddress, String codeNo) throws UnsupportedEncodingException {
        Long[] boxNew = box;
        OrderAddress orderAddressNew = orderAddress;
        Long typeIdNew = typeId;
        Long customerId = (Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID);
        if (boxNew != null) {
            request.getSession().setAttribute("box", boxNew);
        } else {
            boxNew = (Long[]) request.getSession().getAttribute("box");
        }

        if (customerId == null) {
            return new ModelAndView(new RedirectView(LOGIN_HTML));
        }
        CustomerAddress customerAddress = null;
        // 查询收货地址
        if (customerId != null) {

            customerAddress = addressService.queryDefaultAddr(customerId);
        }
        if (null == customerAddress) {
            // 查询上一次收货地址
            customerAddress = addressService.selectByCIdFirst(customerId);
        }

        Map<String, Object> map = shoppingCartService.newsubOrder(request, boxNew, customerAddress);
        // 获取购物车列表
        List<ShoppingCart> shoplist = (List<ShoppingCart>) map.get("shoplist");
        if (shoplist == null || shoplist.isEmpty()) {
            return new ModelAndView(new RedirectView("../myshoppingmcart.html"));
        }

        DeliveryPoint deliveryPoint = null;
        // 微信收货地址为空
        if (orderAddressNew.getAddressName() == null || orderAddressNew.getAddressPhone() == null || orderAddressNew.getAddressDetail() == null
                || orderAddressNew.getAddressDetailInfo() == null) {
            if (customerAddress != null) {
                // 给订单地址赋值
                orderAddressNew = new OrderAddress();
                orderAddressNew.setAddressId(customerAddress.getAddressId());
                orderAddressNew.setAddressDetailInfo(customerAddress.getAddressDetail());
                orderAddressNew.setAddressName(customerAddress.getAddressName());
                orderAddressNew.setAddressPhone(customerAddress.getAddressMoblie());
                orderAddressNew.setAddressDetail(customerAddress.getProvince().getProvinceName() + " "+customerAddress.getCity().getCityName()
                        + " "+ customerAddress.getDistrict().getDistrictName());
            }
            if (typeIdNew == null || typeIdNew == 0) {
                // 表示快递方式选择为快递配送
                typeIdNew = 0L;
            } else {
                // 标识配送方式为快递配送
                deliveryPoint = deliveryPointService.getDeliveryPoint(deliveryPointId);

            }
        } else {
            // 城市
            orderAddressNew.setAddressCitySecondStageName(new String(orderAddressNew.getAddressCitySecondStageName().getBytes(ISO_8859_1), ConstantUtil.UTF));
            // 区县
            orderAddressNew.setAddressCountiesThirdStageName(new String(orderAddressNew.getAddressCountiesThirdStageName().getBytes(ISO_8859_1), ConstantUtil.UTF));
            // 详细地址
            orderAddressNew.setAddressDetail(new String(orderAddressNew.getAddressDetail().getBytes(ISO_8859_1), ConstantUtil.UTF));
            // 省市县详细地址
            orderAddressNew.setAddressDetailInfo(new String(orderAddressNew.getAddressDetailInfo().getBytes(ISO_8859_1), ConstantUtil.UTF));
            // 收货人名称
            orderAddressNew.setAddressName(new String(orderAddressNew.getAddressName().getBytes(ISO_8859_1), ConstantUtil.UTF));
            // 收货人手机
            orderAddressNew.setAddressPhone(new String(orderAddressNew.getAddressPhone().getBytes(ISO_8859_1), ConstantUtil.UTF));
            // 省
            orderAddressNew.setProviceFirstStageName(new String(orderAddressNew.getProviceFirstStageName().getBytes(ISO_8859_1), ConstantUtil.UTF));
        }
        ModelAndView mav = null;
        if (customerAddress == null) {
            mav = new ModelAndView(CustomerConstants.REVISEADDR);
            mav.addObject("needUrl", "need");
            mav.addObject(ConstantUtil.PAGENAME, "添加收货地址");
            mav.addObject("needRetrun", "1");
        } else {
            mav = new ModelAndView("order/suborder");
            mav.addObject(ConstantUtil.PAGENAME, "提交订单");
        }
        // 优惠券
        Coupon coupon = null;
        if (StringUtils.isNotEmpty(codeNo)) {
            coupon = couponService.selectCouponByCodeNo(codeNo);
        }
        // 发票类型
        mav.addObject("invoiceType", invoiceType);
        // 发票抬头
        mav.addObject("invoiceTitle", invoiceTitle);
        // 自提点
        mav.addObject(DELIVERYPOINTID, deliveryPointId);
        // 运费模板
        mav.addObject("ch_express", chExpress).addObject("coupon", coupon);
        mav.addObject("map", map).addObject("sx", request.getSession().getAttribute(CSRFTokenManager.CSRF_TOKEN_FOR_SESSION_ATTR_NAME)).addObject("customer", customerAddress)
                .addObject(TYPEID, typeIdNew).addObject("dps", deliveryPoint).addObject(CH_PAY, chPay).addObject(PAYLIST, paymentService.selectAllForSite())
                .addObject("orderAddress", orderAddressNew);
        return seoService.getCurrSeo(mav);
    }

    /**
     * 设置配送方式和支付方式
     *
     * @return
     */
    @RequestMapping("toFreightAndPay")
    public ModelAndView toFreightAndPay(HttpServletRequest request, Long typeId, Long addressId, Long chPay, Long deliveryPointId) {
        Long customerId = (Long) request.getSession().getAttribute(CustomerConstants.CUSTOMERID);
        Long typeIdNew = typeId;
        if (customerId == null) {
            return new ModelAndView(new RedirectView(LOGIN_HTML));
        }
        if (customerId != null) {
            if (typeIdNew == null) {
                typeIdNew = 0L;
            }
            CustomerAddress customerAddress = null;
            // 查询收货地址
            if (customerId != null) {

                customerAddress = addressService.queryDefaultAddr(customerId);
            }
            if (null == customerAddress) {
                // 查询上一次收货地址
                customerAddress = addressService.selectByCIdFirst(customerId);
            }
            Long[] box = (Long[]) request.getSession().getAttribute("box");
            // 查询货品信息
            Map<String, Object> map = shoppingCartService.newsubOrder(request, box, customerAddress);
            ModelAndView mav = new ModelAndView("order/payType");
            mav.addObject("map", map).addObject("sx", request.getSession().getAttribute(CSRFTokenManager.CSRF_TOKEN_FOR_SESSION_ATTR_NAME)).addObject(TYPEID, typeIdNew)
                    .addObject("ch_pay2", request.getSession().getAttribute(CH_PAY)).addObject(PAYLIST, paymentService.selectAllForSite());
            // 订单收货地址
            mav.addObject(ADDRESSID, addressId);
            // 支付方式
            mav.addObject(CH_PAY, chPay);
            // 自提点id
            mav.addObject(DELIVERYPOINTID, deliveryPointId);
            // 获取自提点对象的信息
            mav.addObject("expressConf", expressConfDaoImpl.getExpressConfByUsedField());
            // 订单收货地址
            CustomerAddress address;
            // 查询收货地址
            if (addressId != null) {
                address = addressService.queryCustomerAddressById(addressId, customerId);
            } else {
                address = addressService.selectByCIdFirst(customerId);
            }
            // 收货地址当前城市所有的自提点
            List<DeliveryPoint> dps = null;
            if (address != null) {
                dps = deliveryPointService.selectDeliveryPointBycityId(Long.valueOf(address.getInfoCity()));
            }
            mav.addObject("dps", dps);
            mav.addObject(ConstantUtil.PAGENAME, "配送方式");
            return seoService.getCurrSeo(mav);
        } else {
            return new ModelAndView(CustomerConstants.REDIRECTLOGINTOINDEX);
        }
    }

    /**
     * 查看个人所有的购物券
     *
     * @return
     */
    @RequestMapping("tocouponlist")
    public ModelAndView tocouponlist(HttpServletRequest request, Long typeId, Long addressId, Long chPay, Long deliveryPointId, String codeNo) {
        Long customerId = (Long) request.getSession().getAttribute(CustomerConstants.CUSTOMERID);
        Long typeIdNew = typeId;
        if (customerId == null) {
            return new ModelAndView(new RedirectView(LOGIN_HTML));
        }
        if (customerId != null) {
            if (typeIdNew == null) {
                typeIdNew = 0L;
            }
            Long[] box = (Long[]) request.getSession().getAttribute("box");
            // 查询地区
            // ShoppingCartWareUtil wareUtil =
            // shoppingCartService.selectPNameByParam(request);
            ModelAndView mav = new ModelAndView("order/couponlist");
            mav.addObject("sx", request.getSession().getAttribute(CSRFTokenManager.CSRF_TOKEN_FOR_SESSION_ATTR_NAME)).addObject(TYPEID, typeIdNew)
                    .addObject("ch_pay2", request.getSession().getAttribute(CH_PAY));
            // 订单收货地址
            mav.addObject(ADDRESSID, addressId);
            // 支付方式
            mav.addObject(CH_PAY, chPay);
            // 自提点id
            mav.addObject(DELIVERYPOINTID, deliveryPointId);
            mav.addObject("codeNo", codeNo);
            // 订单收货地址
            List<Coupon> couponlist = shoppingCartService.getUsedCouponlist(request, box);
            mav.addObject("couponlist", couponlist);
            mav.addObject(ConstantUtil.PAGENAME, "优惠券列表");
            return seoService.getCurrSeo(mav);
        } else {
            return new ModelAndView(CustomerConstants.REDIRECTLOGINTOINDEX);
        }
    }

    /**
     * 查看货品详情
     */
    @RequestMapping("toproductsList")
    public ModelAndView toproductsList(HttpServletRequest request) {
        Long customerId = (Long) request.getSession().getAttribute(CustomerConstants.CUSTOMERID);
        if (customerId == null) {
            return new ModelAndView(new RedirectView(LOGIN_HTML));
        }
        if (customerId != null) {
            CustomerAddress customerAddress = null;
            // 查询收货地址
            if (customerId != null) {

                customerAddress = addressService.queryDefaultAddr(customerId);
            }
            if (null == customerAddress) {
                // 查询上一次收货地址
                customerAddress = addressService.selectByCIdFirst(customerId);
            }
            Long[] box = (Long[]) request.getSession().getAttribute("box");
            // 查询货品信息
            Map<String, Object> map = shoppingCartService.newsubOrder(request, box, customerAddress);
            ModelAndView mav = new ModelAndView("order/productsList");
            mav.addObject("map", map);
            mav.addObject(ConstantUtil.PAGENAME, "商品清单");
            return seoService.getCurrSeo(mav);
        } else {
            return new ModelAndView(CustomerConstants.REDIRECTLOGINTOINDEX);
        }
    }

    /**
     * 跳转订单物流详情页面
     *
     * @return
     */
    @RequestMapping("/toOrderExpressDetail")
    public ModelAndView toOrderExpressDetail(Long orderId, HttpServletRequest request) {
        Long customerId = (Long) request.getSession().getAttribute(CustomerConstants.CUSTOMERID);
        if (customerId == null) {
            return seoService.getCurrSeo(new ModelAndView("redirect:/login.html"));
        }
        ModelAndView mav = new ModelAndView(LOGISTICS);
        mav.addObject(ConstantUtil.PAGENAME, "商品评论列表");
        mav.addObject(ConstantUtil.BACKLEVURL, "customer/detail-" + orderId + ".html");
        mav.addObject("orderExpress", orderPayService.selectOrderExpress(orderId));
        //mav.addObject("orderExpress",siteOrderService.getPayOrder(orderId));
        return seoService.getCurrSeo(mav);
    }

    /**
     * 查询订单优惠
     *
     * @param goodsIds
     * @return int
     */
    @RequestMapping("/selectordermarketingbygoodsids")
    @ResponseBody
    public List<Marketing> selectordermarketingbygoodsids(Long[] goodsIds) {
        return new ArrayList<Marketing>();
    }

    /**
     * 修改优惠
     *
     * @param shoppingCartId
     * @param orderMarketId
     * @return int
     */
    @RequestMapping("/changeshoppingcartordermarkets")
    @ResponseBody
    public int changeshoppingcartordermarkets(Long shoppingCartId, Long orderMarketId) {
        return 0;
    }

    /**
     * 提交订单
     *
     * @param shoppingCartId  所购买的的货品ID
     * @param codeNo          订单使用的优惠券
     * @param addressId       收货地址ID
     * @param chPay           付款方式
     * @param chExpress       配送方式ID
     * @param deliveryPointId 自提点Id
     * @param point           兑换积分
     * @return ModelAndView
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("/submitorder")
    public ModelAndView submitOrder(HttpServletRequest request, Long point, String invoiceType, String invoiceTitle,String invoiceContent,String customerRemark,Long deliveryPointId, Long[] shoppingCartId, String codeNo, Long addressId, Long chPay, Long chExpress, HttpServletResponse response, Long[] marketingId, Long[] thirdId, Long typeId, OrderAddress orderAddress)
            throws UnsupportedEncodingException {

        if (point != null && point < 0) {
            throw new RuntimeException("参数格式不正确！");
        }
        // 用户编号
        Long customerId = (Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID);
        // 是否登录
        if (customerId == null) {
            // 返回登录页
            return new ModelAndView(new RedirectView(LOGIN_HTML));
        }
        // 当前选择的地址
        // ShoppingCartWareUtil wareUtil =
        // shoppingCartService.selectPNameByParam(request);
        List<Order> orders = null;
        // 保存订单
        orders = siteOrderService.newsubmitOrder(point, invoiceType, invoiceTitle, invoiceContent,customerRemark,request, shoppingCartId, typeId, orderAddress, deliveryPointId);

        // 返回购物车
        if (orders == null) {
            return new ModelAndView(new RedirectView(request.getContextPath() + "/myshoppingmcart.htm"));
        }
        Order order = orders.get(0);
        // 推送消息
        //2015-12-29 wuyanbo close
        /*orderPayService.sendOrderRe(order, request, response);
        for (int i = 0; i < orders.size(); i++) {
            Order or = orderPayService.queryGoodsProducts(orders.get(i).getOrderId());
            orders.set(i, or);
        }*/
        // 订单成功页
//        ModelAndView mav = new ModelAndView("order/check_out_de2");
//        mav.addObject(ConstantUtil.PAGENAME, "订单提交成功");
        // 订单信息
//        mav.addObject("orders", orders);
//        mav.addObject(ORDER, orders.get(0));
        // 支付方式列表
//        mav.addObject(PAYLIST, payService.queryAllPaySet());
//        return seoService.getCurrSeo(mav);

        //直接跳转到支付页面
        ModelAndView mav = new ModelAndView("order/check_out_de3");
        mav.addObject("openid", null);
        mav.addObject(ORDER, orders.get(0));
        mav.addObject(PAYLIST, payService.queryAllPaySet());
        mav.addObject(ConstantUtil.PAGENAME, "支付方式");
        return seoService.getCurrSeo(mav);
    }

    /**
     * 去付款
     *
     * @param orderId
     * @return ModelAndView
     */
    @RequestMapping("gopayorder")
    public ModelAndView gopayorder(Long orderId) {
        ModelAndView mav = new ModelAndView("order/orderdetail").addObject(ORDER, siteOrderService.getPayOrder(orderId)).addObject(PAYLIST, payService.queryAllPaySet());
        return seoService.getCurrSeo(mav);
    }

    /**
     * 支付宝确认付款(支付宝支付)
     *
     * @param orderId
     * @param request
     * @return ModelAndView
     */
    @RequestMapping(value = "/payorder", produces = "text/html;charset=UTF-8")
    public ModelAndView payOrder(Long orderId, String orderCode, HttpServletRequest request, Long payId, HttpServletResponse response) {
        Long customerId = (Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID);
        if (customerId == null) {
            return new ModelAndView(new RedirectView(LOGIN_HTML));
        }
        // 返回订单商品的商品名称，取第一个
        String goodsName = orderser.queryGoodsInfoName(orderId);
        ModelAndView mav = new ModelAndView();
        Order order = siteOrderService.getPayOrder(orderId);
        // 查询使用的支付信息
        Pay p = payService.findByPayId(payId);
        if (p != null) {
            this.getReceivables(p, request, order);
            if ("1".equals(p.getPayType())) {//支付宝支付
                AlipayConfig.partner = p.getApiKey();
                AlipayConfig.seller_id = p.getApiKey();
                Properties properties = PropertieUtil.readPropertiesFile(OrderMController.class.getClassLoader().getResourceAsStream("com/ningpai/web/config/alipay.properties"));
                // 本地karaf路径
                AlipayConfig.private_key = properties.getProperty("PRIVATE_KEY");

                /*** 更新支付id ***/
                Order ordVo = new Order();
                ordVo.setPayId(payId);

                // 支付类型
                String paymentType = "1";
                // 必填，不能修改
                // 服务器异步通知页面路径
                //String notifyUrl = p.getPayComment() + "paysucccessybm.htm";
                String notifyUrl = "http://" + request.getServerName() + request.getContextPath() + "/paysucccessybm.htm";
                // 需http://格式的完整路径，不能加?id=123这类自定义参数

                // 页面跳转同步通知页面路径
                //String returnUrl = p.getPayComment() + "paysucccess.htm";
                String returnUrl = "http://" + request.getServerName() + request.getContextPath() + "/paysucccess.htm";
                // 需http://格式的完整路径，不能加?id=123这类自定义参数，不能写成http://localhost/

                // 商户订单号
                String outTradeNo = order.getOrderCode();
                // 商户网站订单系统中唯一订单号，必填

                // 订单名称
                String subject = goodsName;
                // 必填

                // 付款金额
                String totalFee = order.getOrderPrice().toString();
                // 必填

                // 商品展示地址
                String showUrl = p.getPayUrl();
                // 必填，需以http://开头的完整路径，例如：http://www.商户网址.com/myorder.html

                // 订单描述
                String body = "手机网购订单";
                // 选填

                // 超时时间
                String itBPay = "";
                // 选填

                // 钱包token
                String externToken = "";
                // 选填

                // ////////////////////////////////////////////////////////////////////////////////

                // 把请求参数打包成数组
                Map<String, String> sParaTemp = new HashMap<String, String>();
                sParaTemp.put("service", "alipay.wap.create.direct.pay.by.user");
                sParaTemp.put("partner", AlipayConfig.partner);
                sParaTemp.put("seller_id", AlipayConfig.seller_id);
                sParaTemp.put("_input_charset", AlipayConfig.input_charset);
                /*sParaTemp.put("sign_type", "RSA");
                sParaTemp.put("sign", "RSA");*/
                sParaTemp.put("payment_type", paymentType);
                sParaTemp.put("notify_url", notifyUrl);
                sParaTemp.put("return_url", returnUrl);
                sParaTemp.put(OUT_TRADE_NO, outTradeNo);
                sParaTemp.put("subject", subject);
                sParaTemp.put("total_fee", totalFee);
                //sParaTemp.put("show_url", showUrl);
                sParaTemp.put("body", body);
                //sParaTemp.put("it_b_pay", itBPay);
                //sParaTemp.put("extern_token", externToken);

                // 建立请求
                String sHtmlText = AlipaySubmit.buildRequest(sParaTemp, "get", "确认");
                // 建立请求
                mav.addObject("sHtmlText", sHtmlText);
                mav.setViewName("order/netbank");

            } else if ("2".equals(p.getPayType())) {
                LOGGER.error("1");
                // 字符集编码
                String encoding = ConstantUtil.UTF;
                /**
                 * 初始化证书
                 */
                String rootPath = request.getSession().getServletContext().getRealPath("WEB-INF/unionconfig");
                // MpiConfig.getConfig().loadPropertiesFromSrc();//
                // 从classpath加载mpi.properties文件
                // MpiConfig.getConfig().loadProperties(pro);从properties中加载
                MpiConfig.getConfig().loadPropertiesFromPath(rootPath); // 从指定目录下加载rootPAth不包含文件名
                /**
                 * 组装请求报文
                 */
                String requestUrl = MpiConfig.getConfig().getFrontRequestUrl();


                //前台页面传过来的
                //String merId = req.getParameter("merId");
                //String txnAmt = req.getParameter("txnAmt");

                Map<String, String> requestData = new HashMap<String, String>();

                /***银联全渠道系统，产品参数，除了encoding自行选择外其他不需修改***/
                requestData.put("version", ConstantUtil.version);              //版本号，全渠道默认值
                requestData.put("encoding", ConstantUtil.UTF);              //字符集编码，可以使用UTF-8,GBK两种方式
                requestData.put("signMethod", "01");                          //签名方法，只支持 01：RSA方式证书加密
                requestData.put("txnType", "01");                          //交易类型 ，01：消费
                requestData.put("txnSubType", "01");                          //交易子类型， 01：自助消费
                requestData.put("bizType", "000201");                      //业务类型，B2C网关支付，手机wap支付
                requestData.put("channelType", "08");                      //渠道类型，这个字段区分B2C网关支付和手机wap支付；07：PC,平板  08：手机

                /***商户接入参数***/
                requestData.put("merId", p.getPayAccount());                  //商户号码，请改成自己申请的正式商户号或者open上注册得来的777测试商户号
                //requestData.put("merId", "898110255330226");    	          //商户号码，请改成自己申请的正式商户号或者open上注册得来的777测试商户号
                requestData.put("accessType", "0");                          //接入类型，0：直连商户
                requestData.put("orderId", order.getOrderCode());             //商户订单号，8-40位数字字母，不能含“-”或“_”，可以自行定制规则
                requestData.put("txnTime", UtilDate.getOrderNum());        //订单发送时间，取系统时间，格式为YYYYMMDDhhmmss，必须取当前时间，否则会报txnTime无效
                requestData.put("currencyCode", "156");                      //交易币种（境内商户一般是156 人民币）
                requestData.put("txnAmt", getMoney(order.getOrderPrice().toString()));  //交易金额，单位分，不要带小数点
                requestData.put("reqReserved", "buyao");                      //请求方保留域，透传字段（可以实现商户自定义参数的追踪）本交易的后台通知,对本交易的交易状态查询交易、对账文件中均会原样返回，商户可以按需上传，长度为1-1024个字节

                //前台通知地址 （需设置为外网能访问 http https均可），支付成功后的页面 点击“返回商户”按钮的时候将异步通知报文post到该地址
                //如果想要实现过几秒中自动跳转回商户页面权限，需联系银联业务申请开通自动返回商户权限
                //异步通知参数详见open.unionpay.com帮助中心 下载  产品接口规范  网关支付产品接口规范 消费交易 商户通知
                LOGGER.info("request.getServerName:" + request.getServerName() + "//request.getContextPath:" + request.getContextPath());
                String returnUrl = "http://" + request.getServerName() + request.getContextPath() + "/qmpaysuc.htm";
                //requestData.put("frontUrl", "http://172.18.137.63:8080/ACPSample_WuTiaoZhuan/frontRcvResponse");
                requestData.put("frontUrl", returnUrl);

                //后台通知地址（需设置为【外网】能访问 http https均可），支付成功后银联会自动将异步通知报文post到商户上送的该地址，失败的交易银联不会发送后台通知
                //后台通知参数详见open.unionpay.com帮助中心 下载  产品接口规范  网关支付产品接口规范 消费交易 商户通知
                //注意:1.需设置为外网能访问，否则收不到通知    2.http https均可  3.收单后台通知后需要10秒内返回http200或302状态码
                //    4.如果银联通知服务器发送通知后10秒内未收到返回状态码或者应答码非http200，那么银联会间隔一段时间再次发送。总共发送5次，每次的间隔时间为0,1,2,4分钟。
                //    5.后台通知地址如果上送了带有？的参数，例如：http://abc/web?a=b&c=d 在后台通知处理程序验证签名之前需要编写逻辑将这些字段去掉再验签，否则将会验签失败
                // 服务器异步通知页面路径
                String notifyUrl = "http://" + request.getServerName() + request.getContextPath() + "/unionpaynotifysuccess.html";
                requestData.put("backUrl", notifyUrl);
                /**
                 * 签名
                 */
                MpiUtil.sign(requestData, encoding);
                String html = createAutoFormHtml(requestUrl, requestData, encoding);


                LOGGER.error("打印请求HTML，此为请求报文，为联调排查问题的依据：" + html);
                //将生成的html写到浏览器中完成自动跳转打开银联支付页面；这里调用signData之后，将html写到浏览器跳转到银联页面之前均不能对html中的表单项的名称和值进行修改，如果修改会导致验签不通过
                //resp.getWriter().write(html);

                //response.setContentType("text/html;charset=UTF-8");
                //response.setCharacterEncoding(ConstantUtil.UTF);

                mav.addObject("sHtmlText", html);
                mav.setViewName("order/netbanks");

            } else {
                // 获取openid

                String ip = request.getRemoteAddr();
                // 订单信息
                mav.addObject(ORDER, order);
                // ip地址
                mav.addObject("ip", ip);
                // 订单id
                mav.addObject(ConstantUtil.ORDERID, orderId);
                // 商品名称
                mav.addObject("goodsInfoName", goodsName);
                // 订单价格
                mav.addObject("payPrice", order.getOrderPrice().toString().replace(".", ""));
                // 回调函数
                mav.addObject("callBackUrl", "http://" + request.getServerName() + request.getContextPath() + "/wxpaysuc.htm");

                mav.setViewName("pay/pay");
            }
        }
        return seoService.getCurrSeo(mav);
    }

    /**
     * 银联支付异步通知
     *
     * @param req
     * @param resp
     */
    @RequestMapping("/unionpaynotifysuccess")
    public void unionpayNotifySuccess(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        LOGGER.info("银联支付异步通知接收后台通知开始");

        req.setCharacterEncoding("ISO-8859-1");
        String encoding = req.getParameter(ConstantUtil.param_encoding);
        // 获取银联通知服务器发送的后台通知参数
        Map<String, String> reqParam = getAllRequestParam(req);

        LogUtil.printRequestLog(reqParam);

        Map<String, String> valideData = null;
        if (null != reqParam && !reqParam.isEmpty()) {
            Iterator<Map.Entry<String, String>> it = reqParam.entrySet().iterator();
            valideData = new HashMap<String, String>(reqParam.size());
            while (it.hasNext()) {
                Map.Entry<String, String> e = it.next();
                String key = (String) e.getKey();
                String value = (String) e.getValue();
                value = new String(value.getBytes("ISO-8859-1"), encoding);
                valideData.put(key, value);
            }
        }

        //重要！验证签名前不要修改reqParam中的键值对的内容，否则会验签不过
        if (!MpiUtil.validate(valideData, encoding)) {
            LogUtil.writeLog("验证签名结果[失败].");
            //验签失败，需解决验签问题

        } else {
            LogUtil.writeLog("验证签名结果[成功].");
            //【注：为了安全验签成功才应该写商户的成功处理逻辑】交易成功，更新商户订单状态

            String orderId = valideData.get("orderId"); //获取后台通知的数据，其他字段也可用类似方式获取
            String respCode = valideData.get("respCode"); //获取应答码，收到后台通知了respCode的值一般是00，可以不需要根据这个应答码判断。

            Order or = siteOrderService.getPayOrderByCode(orderId);
            // ——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
            Receivables receivables = this.receivablesService.queryByOrderCode(orderId);
            if ("0".equals(or.getOrderStatus())) {
                siteOrderService.payOrder(or.getOrderId());
                if (null != receivables) {
                    // 修改订单支付状态为支付成功
                    receivables.setReceivablesTime(new Date());
                    this.receivablesService.updatePayStatus(receivables);//收款表 订单状态
                }
            }
            //返回给银联服务器http 200  状态码
            resp.getWriter().print("ok");
        }
        LogUtil.writeLog("银联支付异步接收后台通知结束");
    }

    /**
     * 银联支付）获取请求参数中所有的信息
     *
     * @param request
     * @return
     */
    public static Map<String, String> getAllRequestParam(final HttpServletRequest request) {
        Map<String, String> res = new HashMap<String, String>();
        Enumeration<?> temp = request.getParameterNames();
        if (null != temp) {
            while (temp.hasMoreElements()) {
                String en = (String) temp.nextElement();
                String value = request.getParameter(en);
                res.put(en, value);
                //在报文上送时，如果字段的值为空，则不上送<下面的处理为在获取所有参数数据时，判断若值为空，则删除这个字段>
                //System.out.println("ServletUtil类247行  temp数据的键=="+en+"     值==="+value);
                if (null == res.get(en) || "".equals(res.get(en))) {
                    res.remove(en);
                }
            }
        }
        return res;
    }

    /**
     * 银联支付同步回调
     *
     * @param request 请求
     * @return
     */
    @RequestMapping("qmpaysuc")
    public ModelAndView qmpaysuc(HttpServletRequest request, HttpServletResponse resp) throws IOException {
        /*Order or =null;
        or = siteOrderService.getPayOrderByCode("2016012609480880");
        // 更新订单状态
        if ("0".equals(or.getOrderStatus())) {
            siteOrderService.payOrder(or.getOrderId());
        }
        if ("2".equals(or.getOrderMType())) {
            Customer customer = customerServiceMapper.queryCustomerInfo(or.getCustomerId());
            // 返回订单商品的商品名称，取第一个
            String goodsName = orderser.queryGoodsInfoName(or.getOrderId());
            // 微信发货通知
            Map<String, Object> paraMap = new HashMap<>();
            paraMap.put(ConstantUtil.OPENID, customer.getCustomerUsername());
            paraMap.put("orderNo", or.getOrderCode());
            paraMap.put("orderPrice", or.getOrderPrice());
            paraMap.put("goodsName", goodsName);
            paraMap.put(ConstantUtil.ORDERID, or.getOrderId());
        }
        if ("4".equals(or.getOrderCargoStatus())) {
            orderser.modifyOrderByKey(or.getOrderId(), "2");
        }

        ModelAndView mav = new ModelAndView("order/pay_success").addObject(ORDER, or);

        return seoService.getCurrSeo(mav);*/

        /**
         * 初始化证书
         */
        String rootPath = request.getSession().getServletContext().getRealPath("WEB-INF/unionconfig");
        // MpiConfig.getConfig().loadPropertiesFromSrc();//
        // 从classpath加载mpi.properties文件
        // MpiConfig.getConfig().loadProperties(pro);从properties中加载
        MpiConfig.getConfig().loadPropertiesFromPath(rootPath); // 从指定目录下加载rootPAth不包含文件名

        StringBuffer buffer = new StringBuffer();
        LOGGER.info("银联支付同步通知开始");

        request.setCharacterEncoding("ISO-8859-1");
        String encoding = request.getParameter(ConstantUtil.param_encoding);
        // 获取银联通知服务器发送的后台通知参数
        Map<String, String> reqParam = getAllRequestParam(request);

        LogUtil.printRequestLog(reqParam);

        Map<String, String> valideData = null;
        if (null != reqParam && !reqParam.isEmpty()) {
            Iterator<Map.Entry<String, String>> it = reqParam.entrySet().iterator();
            valideData = new HashMap<String, String>(reqParam.size());
            while (it.hasNext()) {
                Map.Entry<String, String> e = it.next();
                String key = (String) e.getKey();
                String value = (String) e.getValue();
                value = new String(value.getBytes("ISO-8859-1"), encoding);
                valideData.put(key, value);
                buffer.append(key + ":" + value + ";");
            }
        }
        Order or = null;
        //重要！验证签名前不要修改reqParam中的键值对的内容，否则会验签不过
        if (!MpiUtil.validate(valideData, encoding)) {
            LogUtil.writeLog("银联支付验证签名结果[失败].");
            //验签失败，需解决验签问题
            int i = 10 / 0;
        } else {
            LogUtil.writeLog("银联支付验证签名结果[成功].");
            //【注：为了安全验签成功才应该写商户的成功处理逻辑】交易成功，更新商户订单状态

            String orderOldCode = valideData.get("orderId"); //获取后台通知的数据，其他字段也可用类似方式获取
            String respCode = valideData.get("respCode"); //获取应答码，收到后台通知了respCode的值一般是00，可以不需要根据这个应答码判断。
            or = siteOrderService.getPayOrderByCode(orderOldCode);
            // 更新订单状态
            if ("0".equals(or.getOrderStatus())) {
                siteOrderService.payOrder(or.getOrderId());
            }
            if ("2".equals(or.getOrderMType())) {
                Customer customer = customerServiceMapper.queryCustomerInfo(or.getCustomerId());
                // 返回订单商品的商品名称，取第一个
                String goodsName = orderser.queryGoodsInfoName(or.getOrderId());
                // 微信发货通知
                Map<String, Object> paraMap = new HashMap<>();
                paraMap.put(ConstantUtil.OPENID, customer.getCustomerUsername());
                paraMap.put("orderNo", or.getOrderCode());
                paraMap.put("orderPrice", or.getOrderPrice());
                paraMap.put("goodsName", goodsName);
                paraMap.put(ConstantUtil.ORDERID, or.getOrderId());
            }
            if ("4".equals(or.getOrderCargoStatus())) {
                orderser.modifyOrderByKey(or.getOrderId(), "2");
            }
        }
        LogUtil.writeLog("BackRcvResponse接收后台通知结束");
        //返回给银联服务器http 200  状态码
        resp.getWriter().print("ok");
        LOGGER.info("银联支付同步返回参数：" + buffer.toString());

        ModelAndView mav = new ModelAndView("order/pay_success").addObject(ORDER, or);

        return seoService.getCurrSeo(mav);
    }

    /**
     * 提供第三方支付
     * 功能：前台交易构造HTTP POST交易表单的方法示例<br>
     *
     * @param action   表单提交地址<br>
     * @param hiddens  以MAP形式存储的表单键值<br>
     * @param encoding 上送请求报文域encoding字段的值<br>
     * @return 构造好的HTTP POST交易表单<br>
     */
    public static String createAutoFormHtml(String action, Map<String, String> hiddens, String encoding) {
        StringBuffer sf = new StringBuffer();
        sf.append("<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=" + encoding + "\"/></head><body>");
        sf.append("<form id = \"pay_form\" action=\"" + action
                + "\" method=\"post\">");
        if (null != hiddens && 0 != hiddens.size()) {
            Set<Map.Entry<String, String>> set = hiddens.entrySet();
            Iterator<Map.Entry<String, String>> it = set.iterator();
            while (it.hasNext()) {
                Map.Entry<String, String> ey = it.next();
                String key = ey.getKey();
                String value = ey.getValue();
                sf.append("<input type=\"hidden\" name=\"" + key + "\" id=\""
                        + key + "\" value=\"" + value + "\"/>");
            }
        }
        sf.append("</form>");
        sf.append("</body>");
        sf.append("<script type=\"text/javascript\">");
        sf.append("document.all.pay_form.submit();");
        sf.append("</script>");
        sf.append("</html>");
        return sf.toString();
    }


    /**
     * 支付宝确认付款(支付宝支付，提供第三方支付)
     * zz:pl
     *
     * @param orderId   订单id
     * @param returnUrl 完整同步回调url eg：http://www.three.com/paysucccess.htm
     * @param request
     * @param payId     支付方式主键 默认25
     * @param response
     * @return
     */
    @RequestMapping(value = "/payOrderByThred", produces = "text/html;charset=UTF-8")
    public ModelAndView payOrderByThree(Long orderId, String returnUrl, HttpServletRequest request, Long payId, HttpServletResponse response) {
        /*Long customerId = (Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID);
        if (customerId == null) {
            return new ModelAndView(new RedirectView(LOGIN_HTML));
        }*/
        // 返回订单商品的商品名称，取第一个
        String goodsName = orderser.queryGoodsInfoName(orderId);
        ModelAndView mav = new ModelAndView();
        Order order = siteOrderService.getPayOrder(orderId);
        // 查询使用的支付信息
        Pay p = payService.findByPayId(payId);
        if (p != null) {
            this.getReceivables(p, request, order);
            if ("1".equals(p.getPayType())) {
                AlipayConfig.partner = p.getApiKey();
                AlipayConfig.seller_id = p.getApiKey();
                Properties properties = PropertieUtil.readPropertiesFile(OrderMController.class.getClassLoader().getResourceAsStream("com/ningpai/web/config/alipay.properties"));
                // 本地karaf路径
                AlipayConfig.private_key = properties.getProperty("PRIVATE_KEY");

                /*** 更新支付id ***/
                Order ordVo = new Order();
                ordVo.setPayId(payId);

                // 支付类型
                String paymentType = "1";
                // 必填，不能修改
                // 服务器异步通知页面路径
                //String notifyUrl = p.getPayComment() + "paysucccessybm.htm";
                //String notifyUrl = "http://"+request.getServerName()+request.getContextPath() + "/paysucccessybm.htm";
                String notifyUrl = "http://" + request.getServerName() + request.getContextPath() + "/paysucccessybm.htm";
                //String backUrl="http://"+request.getServerName()+request.getContextPath()+"/GetPayType.htm?orderId="+orderId;
                // 需http://格式的完整路径，不能加?id=123这类自定义参数

                // 页面跳转同步通知页面路径
                //String returnUrl = p.getPayComment() + "paysucccess.htm";
                //String returnUrl = "http://"+request.getServerName()+request.getContextPath() + "/paysucccess.htm";
                // 需http://格式的完整路径，不能加?id=123这类自定义参数，不能写成http://localhost/

                // 商户订单号
                String outTradeNo = order.getOrderCode();
                // 商户网站订单系统中唯一订单号，必填

                // 订单名称
                String subject = goodsName;
                // 必填

                // 付款金额
                String totalFee = order.getOrderPrice().toString();
                // 必填

                // 商品展示地址
                String showUrl = p.getPayUrl();
                // 必填，需以http://开头的完整路径，例如：http://www.商户网址.com/myorder.html

                // 订单描述
                String body = "手机网购订单";
                // 选填

                // 超时时间
                String itBPay = "";
                // 选填

                // 钱包token
                String externToken = "";
                // 选填

                // ////////////////////////////////////////////////////////////////////////////////

                // 把请求参数打包成数组
                Map<String, String> sParaTemp = new HashMap<String, String>();
                sParaTemp.put("service", "alipay.wap.create.direct.pay.by.user");
                sParaTemp.put("partner", AlipayConfig.partner);
                sParaTemp.put("seller_id", AlipayConfig.seller_id);
                sParaTemp.put("_input_charset", AlipayConfig.input_charset);
                sParaTemp.put("payment_type", paymentType);
                sParaTemp.put("notify_url", notifyUrl);
                sParaTemp.put("return_url", returnUrl);
                sParaTemp.put(OUT_TRADE_NO, outTradeNo);
                sParaTemp.put("subject", subject);
                sParaTemp.put("total_fee", totalFee);
                //sParaTemp.put("show_url", showUrl);
                sParaTemp.put("body", body);
                //sParaTemp.put("it_b_pay", itBPay);
                //sParaTemp.put("extern_token", externToken);

                // 建立请求
                String sHtmlText = AlipaySubmit.buildRequest(sParaTemp, "get", "确认");
                // 建立请求
                mav.addObject("sHtmlText", sHtmlText);
                mav.setViewName("order/netbank");

            } else {
                // 获取openid

                String ip = request.getRemoteAddr();
                // 订单信息
                mav.addObject(ORDER, order);
                // ip地址
                mav.addObject("ip", ip);
                // 订单id
                mav.addObject(ConstantUtil.ORDERID, orderId);
                // 商品名称
                mav.addObject("goodsInfoName", goodsName);
                // 订单价格
                mav.addObject("payPrice", order.getOrderPrice().toString().replace(".", ""));
                // 回调函数
                mav.addObject("callBackUrl", "http://" + request.getServerName() + request.getContextPath() + "/wxpaysuc.htm");

                mav.setViewName("pay/pay");
            }
        }
        return seoService.getCurrSeo(mav);

    }


    /**
     * 根据支付的订单 插入收款单
     *
     * @param order 订单对象
     * @return
     */
    public void getReceivables(Pay p, HttpServletRequest request, Order order) {

        /* 根据订单号去查询是否有记录 */
        Receivables receivables1 = this.receivablesService.queryByOrderCode(order.getOrderCode());
        if (null == receivables1) {
            /* 添加收款单信息 */
            Receivables receivables = new Receivables();
            Random random = new Random();
            int code = random.nextInt(10001);
            String codes = "2015" + code;
            // 收款单编号 产生的随机数
            receivables.setCashRegisterCode(codes);
            // 收款单IP
            receivables.setPayIp(getIpAddr(request));
            // 会员ID
            /*receivables.setCustomerId(order.getCustomerUsername());*/
            receivables.setCustomerId(order.getCustomerUsername());//order.getCustomerUsername()为空
            // 收款账号
            receivables.setPayAccount(p.getPayAccount());
            // 给收款单信息设置IP地址
            // receivables.setPayIp(InetAddress.getLocalHost().getHostAddress());
            // 设置生成时间
            receivables.setPayTime(new Date());
            // 设置付款时间
            receivables.setReceivablesTime(new Date());
            // 给收款单信息设置支付方式
            receivables.setPayMode(p.getPayName());
            // 设置支付类型
            receivables.setPayType("在线支付");
            // 给收款单信息设置付款金额
            receivables.setPayMoney(order.getOrderPrice());
            receivables.setOrderCode(order.getOrderCode());
            // 给付款单信息设置是否支付成功
            receivables.setPayStatus("1");
            // 执行为收款单信息添加信息操作
            this.receivablesService.addReceivables(receivables);
        }
    }

    /**
     * 获取ip地址
     *
     * @param request
     * @return
     */
    public String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }


    /**
     * 获取微信参数(微信支付)
     *
     * @param orderId
     * @param payType
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("getorderinfo")
    @ResponseBody
    public Map<String, Object> getOrderInfo(Long orderId,String payType, HttpServletRequest request, HttpServletResponse response) {

        // 订单
        Order order = orderser.getPayOrder(orderId);
        // 商品名称 返回第一个商品的名称
        String goodsName = orderser.queryGoodsInfoName(orderId);

        Map<String,Object> map = new HashMap<>();
        map.put("order",order);
        map.put("goodsName",goodsName);
        /*如果微信支付需要创建预支付订单*/
        if("wechat".equals(payType)){
                Map wxOrder =  creWechatOrder(order,goodsName,request,response);
            //根据预支付订单初始化支付参数
            TreeMap<Object,Object> parameters = new TreeMap<>();
            if("SUCCESS".equals(wxOrder.get("return_code"))){
                parameters.put("appid","wx5d544edce0cfc9ac");
                parameters.put("partnerid","1340333801");
                parameters.put("package","Sign=WXPay");
                parameters.put("noncestr",WXUtil.getNonceStr());
                parameters.put("timestamp",WXUtil.getTimeStamp());
                parameters.put("prepayid",wxOrder.get("prepay_id"));
                String sign = PayTools.getSign(parameters);
                parameters.put("sign",sign);
            }
            parameters.put("resultcode",wxOrder.get("result_code"));
            parameters.put("returnmsg",wxOrder.get("return_msg"));
            parameters.put("returncode",wxOrder.get("return_code"));
            LOGGER.info("微信根据预支付订单初始化的支付参数"+parameters.toString());
            map.put("wxPayJson",parameters);
        }

        return map;
    }

    /**
     * 创建微信预支付订单
     */
    private Map creWechatOrder(Order order,String goodsName,HttpServletRequest request,HttpServletResponse response){
        Map responseMap = new HashMap();
        try{

        String creOrdUrl = "https://api.mch.weixin.qq.com/pay/unifiedorder";
        String appid="wx5d544edce0cfc9ac"
                ,mch_id="1340333801"
                ,device_info="WEB"
                ,body=goodsName
                ,out_trade_no=order.getOrderCode()
                ,spbill_create_ip= request.getRemoteAddr()
                ,notify_url="http://" + request.getServerName() + request.getContextPath() + "/wxpaysuc.htm"
                ,trade_type="APP";
         int  total_fee=order.getOrderPrice().multiply(new BigDecimal(100)).intValue();//金额以分为单位
         String nonce_str=WXUtil.getNonceStr();
            //spbill_create_ip="127.0.0.1";


        TreeMap<Object,Object> parameters = new TreeMap<>();
        parameters.put("appid",appid);
        parameters.put("mch_id",mch_id);
        parameters.put("device_info",device_info);
        parameters.put("nonce_str",nonce_str);
        parameters.put("body",body);
        parameters.put("out_trade_no",out_trade_no);
        parameters.put("spbill_create_ip",spbill_create_ip);
        parameters.put("notify_url",notify_url);
        parameters.put("trade_type",trade_type);
        parameters.put("total_fee",String.valueOf(total_fee));
        String sign = PayTools.getSign(parameters);
        parameters.put("sign",sign);

        String requestXML = HttpXmlRequestUtils.getRequestXml(parameters);
        //提交数据到微信统一支付接口
        String responseResult =HttpXmlRequestUtils.httpsRequest(creOrdUrl, "POST", requestXML);
            responseMap = XMLUtil.doXMLParse(responseResult);

        LOGGER.info("微信预支付订单生成参数"+parameters.toString());
        LOGGER.info("微信预支付订单响应报文"+responseResult);

        }catch (Exception e){
            e.printStackTrace();
            responseMap.put("return_code","false");
        }
        return responseMap;
    }


    /**
     * 获取微信参数(微信支付)
     *
     * @param outTradeNo
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("callback")
    @ResponseBody
    public String callback(String outTradeNo, HttpServletRequest request, HttpServletResponse response) {

        Order or = siteOrderService.getPayOrderByCode(outTradeNo);
        Pay pay = payService.findByPayId(37L);
        // 判断返回结果
        if ("0".equals(or.getOrderStatus())) {
            orderser.modifyOrderByKey(or.getOrderId(), "1");
            //同时添加装箱单
            orderser.initContainerRelation(or.getOrderId());
            return "true";
        }
       return "false";

    }


    /**
     * 获取微信参数(微信支付)
     *
     * @param orderId
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("getwxparam")
    @ResponseBody
    public Map<String, Object> getWXParam(String openid, Long orderId, HttpServletRequest request, HttpServletResponse response) {

        Pay pay = payService.findByPayId(37L);
        // 订单
        Order order = orderser.getPayOrder(orderId);
        // 商品名称
        String goodsName = orderser.queryGoodsInfoName(orderId);
        this.getReceivables(pay, request, order);
        return siteOrderService.getWXUrl(openid, request, response, order, pay, goodsName);
    }

    /**
     * 获取微信用户Code
     *
     * @param request
     * @param response
     */
    @RequestMapping("getcodebyopenid")
    public ModelAndView getWXCode(Long orderId, HttpServletRequest request, HttpServletResponse response) {
        LOGGER.error("微信支付获取openid开始：orderId=" + orderId);
        // 微信配置

        Pay pay = payService.findByPayId(37L);
        String backUrl = "http://" + request.getServerName() + request.getContextPath() + "/GetPayType.htm?orderId=" + orderId;
        //String backUrl="http://www.qpmall.com/mobile/GetPayType.htm?orderId=3051";
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + pay.getApiKey() + "&redirect_uri=" + URLEncoder.encode(backUrl)
                + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect";

        /*GetMethod getTokenMethod = new GetMethod(url);
        HttpClient clientToken = new HttpClient();
        getTokenMethod.getParams().setContentCharset(ConstantUtil.UTF);
        String res = "";
        try {
            clientToken.executeMethod(getTokenMethod);
            res = getTokenMethod.getResponseBodyAsString();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        LOGGER.error("微信getWXCode：backUrl：" + backUrl);
        //LOGGER.error("微信getWXCode：res："+res);
        return new ModelAndView("redirect:" + url);
    }

    /**
     * 支付成功
     *
     * @param request
     * @param agr1
     * @return ModelAndView
     */
    @RequestMapping("/paysucccess")
    public ModelAndView paySuccess(HttpServletRequest request, HttpServletResponse agr1, HttpServletResponse response) {
        Pay p = payService.findByPayId(25L);
        // 设置商户号
        AlipayConfig.partner = p.getApiKey();
        // 设置商户秘钥
        AlipayConfig.private_key = p.getSecretKey();
        Map<String, String> params = new HashMap<String, String>();
        Map<?, ?> requestParams = request.getParameterMap();
        for (Iterator<?> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            // 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
            try {
                valueStr = new String(valueStr.getBytes(ISO_8859_1), UTF_8);
            } catch (UnsupportedEncodingException e) {
                LOGGER.error("转码错误：" + e);
            }
            params.put(name, valueStr);
        }

        // 订单号
        String orderCode = null;
        try {
            orderCode = new String(request.getParameter(OUT_TRADE_NO).getBytes(ISO_8859_1), UTF_8);
            // 支付宝交易号

        } catch (UnsupportedEncodingException e) {
            LOGGER.error("获取支付宝交易号错误：" + e);
        }

        // 计算得出通知验证结果
        boolean verifyResult = AlipayNotify.verify(params);
        // 重新将session登入
        Order or = siteOrderService.getPayOrderByCode(orderCode);
        if (verifyResult) {// 验证成功

            // 更新订单状态
            if ("0".equals(or.getOrderStatus())) {
                siteOrderService.payOrder(or.getOrderId());
            }
            if ("2".equals(or.getOrderMType())) {
                Customer customer = customerServiceMapper.queryCustomerInfo(or.getCustomerId());
                // 返回订单商品的商品名称，取第一个
                String goodsName = orderser.queryGoodsInfoName(or.getOrderId());
                // 微信发货通知
                Map<String, Object> paraMap = new HashMap<>();
                paraMap.put(ConstantUtil.OPENID, customer.getCustomerUsername());
                paraMap.put("orderNo", or.getOrderCode());
                paraMap.put("orderPrice", or.getOrderPrice());
                paraMap.put("goodsName", goodsName);
                paraMap.put(ConstantUtil.ORDERID, or.getOrderId());
                WXSendMSG.sendWxMsgForOrderPaySucc(paraMap, request, response);
            }
            if ("4".equals(or.getOrderCargoStatus())) {
                orderser.modifyOrderByKey(or.getOrderId(), "2");
            }

        }

        ModelAndView mav = new ModelAndView("order/pay_success").addObject(ORDER, or);

        return seoService.getCurrSeo(mav);

    }

    /**
     * h5微信支付回调
     *
     * @param request
     * @throws IOException
     */
    @RequestMapping("/wxpaysuc")
    public void wxpaySuc(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String line = null;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }

        // xml解析
        Document document;
        try {
            document = DocumentHelper.parseText(sb.toString());
            Element root = document.getRootElement();
            // 微信号
            String appId = "";
            // 订单号
            String outTradeNo = "";

            // 返回结果
            String resultCode = "";
            // 商户编号
            String mId = "";

            List<Element> elements = root.elements();
            for (Iterator<Element> it = elements.iterator(); it.hasNext(); ) {
                Element element = it.next();
                if ("appid".equals(element.getName())) {
                    appId = element.getText();
                } else if ("bank_type".equals(element.getName())) {
                    element.getText();
                } else if ("mch_id".equals(element.getName())) {
                    mId = element.getText();
                } else if ("result_code".equals(element.getName())) {
                    resultCode = element.getText();
                } else if (OUT_TRADE_NO.equals(element.getName())) {
                    outTradeNo = element.getText();
                }

            }
            Order or = siteOrderService.getPayOrderByCode(outTradeNo);
            Pay pay = payService.findByPayId(37L);
            // 判断返回结果
            if ("SUCCESS".equals(resultCode) && appId.equals(pay.getApiKey()) && mId.equals(pay.getPartner()) && "0".equals(or.getOrderStatus())) {
                // 判断商户号与财付通账号是否匹配
                // 判断订单状态

                siteOrderService.payOrder(or.getOrderId());
                Receivables receivables = this.receivablesService.queryByOrderCode(outTradeNo);
                if (null != receivables) {
                    // 修改订单支付状态为支付成功
                    receivables.setReceivablesTime(new Date());
                    this.receivablesService.updatePayStatus(receivables);//收款表 订单状态
                }

                //siteOrderService.payOrder(or.getOrderId());
                sendSucess(response, "SUCCESS");
            }

        } catch (DocumentException e1) {
            LOGGER.error("微信支付错误：" + e1);
            sendSucess(response, "FAIL");
        }
    }

    /**
     * 成功回调
     */
    public void sendSucess(HttpServletResponse response, String msg) throws IOException {
        PrintWriter out = response.getWriter();
        out.println(msg);
        out.flush();
        out.close();
    }

    /**
     * 支付成功
     *
     * @param request
     * @param agr1
     * @return ModelAndView
     */
    @RequestMapping("/paysucccesswx")
    public ModelAndView paySuccessWx(HttpServletRequest request, HttpServletResponse agr1, String orderCodex) {
        // 重新将session登入
        Order or = siteOrderService.getPayOrderByCode(orderCodex);
        ModelAndView mav = new ModelAndView("order/pay_success").addObject(ORDER, or);
        return seoService.getCurrSeo(mav);

    }

    /**
     * 根据商品id查询购买过该商品的记录
     *
     * @return
     */
    @RequestMapping("/addcartsuc")
    public ModelAndView addCartSuc(Long goodsInfoId) {
        List<GoodsProductVo> goodsProductVos = orderser.queryGoodsProductVoByOrderGoods(goodsInfoId);
        return seoService.getCurrSeo(new ModelAndView(ShoppingCartConstants.ADDCART).addObject("list", goodsProductVos).addObject(ConstantUtil.PAGENAME, "加入购物车"));
    }

    /**
     * 根据订单id进行支付
     *
     * @param orderId 订单id
     * @return
     */
    @RequestMapping("/orderdetailpay")
    public ModelAndView orderDetailPay(Long orderId) {
        Order order = orderPayService.queryGoodsProducts(orderId);
        ModelAndView mav = new ModelAndView("order/check_out_de2");
        mav.addObject(ORDER, order);
        mav.addObject(PAYLIST, payService.queryAllPaySet());
        mav.addObject("coupon", couponService.selectCouponByCodeNo(order.getCouponNo()));
        mav.addObject(ConstantUtil.PAGENAME, "立即支付");
        return seoService.getCurrSeo(mav);
    }

    /**
     * 根据订单id进行支付
     *
     * @return
     */
    @RequestMapping("/GetPayType")
    public ModelAndView GetPayType(Long orderId, HttpServletRequest request, HttpServletResponse response) {
        Pay pay = payService.findByPayId(37L);
        WXActivityController controller = new WXActivityController();
        String openid = controller.getWXOpenid(pay, request, response);
        LOGGER.error("openid" + openid);
        ModelAndView mav = new ModelAndView("order/check_out_de3");
        mav.addObject("openid", openid);

        Order order = orderPayService.queryGoodsProducts(orderId);
        //ModelAndView mav = new ModelAndView("order/check_out_de2");
        mav.addObject(ORDER, order);
        mav.addObject(PAYLIST, payService.queryAllPaySet());
        mav.addObject("coupon", couponService.selectCouponByCodeNo(order.getCouponNo()));
        return seoService.getCurrSeo(mav);
    }

    /**
     * 根据订单id进行支付
     *
     * @return
     */
    @RequestMapping("/GetPayType2")
    public ModelAndView GetPayType2(Long orderId) {
        ModelAndView mav = new ModelAndView("order/check_out_de3");
        mav.addObject("openid", null);
        Order order = orderPayService.queryGoodsProducts(orderId);
        //ModelAndView mav = new ModelAndView("order/check_out_de2");
        mav.addObject(ORDER, order);
        mav.addObject(PAYLIST, payService.queryAllPaySet());
        mav.addObject("coupon", couponService.selectCouponByCodeNo(order.getCouponNo()));
        mav.addObject(ConstantUtil.PAGENAME, "支付方式");
        return seoService.getCurrSeo(mav);
    }

    /**
     * 支付宝支付成功异步回调
     *
     * @param request
     * @param response
     * @return ModelAndView
     * @throws Exception
     */
    @RequestMapping("/paysucccessybm")
    public void paySuccessyb(HttpServletRequest request, HttpServletResponse response) throws Exception {
        LOGGER.error("支付宝异步回调paysucccessybm开始：");
        // 获取支付宝信息
        Pay p = payService.findByPayId(25L);

        // 设置商户号
        AlipayConfig.partner = p.getApiKey();
        // 设置商户秘钥
        AlipayConfig.private_key = p.getSecretKey();
        // 获取支付宝POST过来反馈信息
        Map<String, String> params = new HashMap<String, String>();
        Map<?, ?> requestParams = request.getParameterMap();
        for (Iterator<?> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            // 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
            // valueStr = new String(valueStr.getBytes(ISO_8859_1), "gbk");
            params.put(name, valueStr);
        }

        // 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
        String outTradeNo = new String(request.getParameter(OUT_TRADE_NO).getBytes(ISO_8859_1), UTF_8);

        // 支付宝交易号
        // String trade_no = new
        // String(request.getParameter("trade_no").getBytes(ISO_8859_1),
        // UTF_8);

        // 交易状态
        String tradeStatus = new String(request.getParameter("trade_status").getBytes(ISO_8859_1), UTF_8);
        // RSA签名解密
        // 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//

        if (AlipayNotify.verify(params)) {// 验证成功

            chechOrderStatus(tradeStatus, outTradeNo);
            sendSucess(response, SUCCESS);
            System.out.println(SUCCESS); // 请不要修改或删除

            // ////////////////////////////////////////////////////////////////////////////////////////
        } else {// 验证失败
            System.out.println("fail");
        }

    }

    /**
     * @param tradeStatus 支付宝交易号 交易状态
     * @param outTradeNo  支付宝 支付成功 返回的订单号
     * @return
     */
    public void chechOrderStatus(String tradeStatus, String outTradeNo) {

        // ——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
        Receivables receivables = this.receivablesService.queryByOrderCode(outTradeNo);
        if ("TRADE_FINISHED".equals(tradeStatus)) {
            // 判断该笔订单是否在商户网站中已经做过处理
            // 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
            // 如果有做过处理，不执行商户的业务程序
            // 重新将session登入
            Order or = siteOrderService.getPayOrderByCode(outTradeNo);
            if ("0".equals(or.getOrderStatus())) {
                siteOrderService.payOrder(or.getOrderId());
                if (null != receivables) {
                    // 修改订单支付状态为支付成功
                    receivables.setReceivablesTime(new Date());
                    this.receivablesService.updatePayStatus(receivables);
                }
            }
            // 注意：
            // 该种交易状态只在两种情况下出现
            // 1、开通了普通即时到账，买家付款成功后。
            // 2、开通了高级即时到账，从该笔交易成功时间算起，过了签约时的可退款时限（如：三个月以内可退款、一年以内可退款等）后。
        } else if ("TRADE_SUCCESS".equals(tradeStatus)) {
            // 判断该笔订单是否在商户网站中已经做过处理
            // 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
            // 如果有做过处理，
            Order or = siteOrderService.getPayOrderByCode(outTradeNo);
            LOGGER.error("or.getOrderStatus() : " + or.getOrderStatus());
            if ("0".equals(or.getOrderStatus())) {
                LOGGER.error("or.getOrderStatus() : " + or.getOrderId());
                siteOrderService.payOrder(or.getOrderId());
                LOGGER.error("receivables1 : " + receivables.getOrderCode());
                if (null != receivables) {
                    // 修改订单支付状态为支付成功
                    receivables.setReceivablesTime(new Date());
                    LOGGER.error("receivables2 : " + receivables.getOrderCode() + "  " + receivables.getPayStatus());
                    this.receivablesService.updatePayStatus(receivables);//收款表 订单状态
                    receivables = this.receivablesService.queryByOrderCode(outTradeNo);
                    LOGGER.error("receivables3 : " + receivables.getPayStatus());
                }
            }
            // 注意：
            // 该种交易状态只在一种情况下出现——开通了高级即时到账，买家付款成功后。
        }
    }


    /**
     * 查询所有的支付方式
     *
     * @return 查询到的支付方式的列表
     */
    @RequestMapping("/queryAllPaySet")
    @ResponseBody
    public List<Object> queryAllPaySet() {
        return this.payService.queryAllPaySet();
    }

    public PayService getPayService() {
        return payService;
    }

    @Resource(name = "payService")
    public void setPayService(PayService payService) {
        this.payService = payService;
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

    public OrderMService getSiteOrderService() {
        return siteOrderService;
    }

    @Resource(name = "OrderMService")
    public void setSiteOrderService(OrderMService siteOrderService) {
        this.siteOrderService = siteOrderService;
    }

    public MarketingService getMarketingService() {
        return marketingService;
    }

    @Resource(name = "MarketingService")
    public void setMarketingService(MarketingService marketingService) {
        this.marketingService = marketingService;
    }

    public OrderService getOrderser() {
        return orderser;
    }

    @Resource(name = "OrderService")
    public void setOrderser(OrderService orderser) {
        this.orderser = orderser;
    }

    public GoodsProductService getGoodsProductService() {
        return goodsProductService;
    }

    @Resource(name = "GoodsProductService")
    public void setGoodsProductService(GoodsProductService goodsProductService) {
        this.goodsProductService = goodsProductService;
    }

    /**
     * 元转换成分
     *
     * @param amount
     * @return
     */
    public static String getMoney(String amount) {
        if (amount == null) {
            return "";
        }
        // 金额转化为分为单位
        String currency = amount.replaceAll("\\$|\\￥|\\,", "");  //处理包含, ￥ 或者$的金额
        int index = currency.indexOf(".");
        int length = currency.length();
        Long amLong = 0l;
        if (index == -1) {
            amLong = Long.valueOf(currency + "00");
        } else if (length - index >= 3) {
            amLong = Long.valueOf((currency.substring(0, index + 3)).replace(".", ""));
        } else if (length - index == 2) {
            amLong = Long.valueOf((currency.substring(0, index + 2)).replace(".", "") + 0);
        } else {
            amLong = Long.valueOf((currency.substring(0, index + 1)).replace(".", "") + "00");
        }
        return amLong.toString();
    }
}
