package com.ningpai.site.giftshop.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.util.MyLogger;
import com.ningpai.customer.service.CustomerServiceMapper;
import com.ningpai.index.service.TopAndBottomService;
import com.ningpai.order.service.OrderService;
import com.ningpai.site.customer.vo.CustomerConstantStr;
import com.ningpai.site.giftshop.bean.Gift;
import com.ningpai.site.giftshop.bean.GiftOrder;
import com.ningpai.site.giftshop.service.GiftOrderService;
import com.ningpai.site.giftshop.service.GiftShopSiteService;
import com.ningpai.site.giftshop.vo.GiftOrderVo;
import com.ningpai.site.giftshop.vo.GiftSearchVo;
import com.ningpai.util.PageBean;

/**
 * 积分商城控制类
 * 
 * @author qiyuanyuan
 *
 */
@Controller
public class GiftShopSiteController {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(GiftShopSiteController.class);

    private OrderService orderService;

    private static final String CUSTOMERID = "customerId";

    private static final String GIFTORDER_HTML = "/giftorder.html";

    @Resource(name = "TopAndBottomService")
    private TopAndBottomService topAndBottomService;

    private GiftShopSiteService giftShopSiteService;

    private CustomerServiceMapper customerServiceMapper;

    private PrintWriter pw;

    private GiftOrderService giftOrderService;

    /**
     * 积分商城列表
     * 
     * @param request
     * @param pb
     *            分页{@link com.ningpai.util.PageBean}
     * @param gift
     *            赠品{@link com.ningpai.site.giftshop.bean.Gift}
     * @return mav
     */
    @RequestMapping("/giftshop")
    public ModelAndView giftShop(HttpServletRequest request, PageBean pb, GiftSearchVo gift) {
        ModelAndView mav = new ModelAndView("giftshop/gift_list");
        Map<String, Object> resultMap = new HashMap<>();
        Long customerId = (Long) request.getSession().getAttribute(CUSTOMERID);
        if (customerId != null) {
            resultMap.put("cusId", customerId);
            resultMap.put("cusInfo", customerServiceMapper.selectByPrimaryKey(customerId));
        }
        resultMap.put("catelist", giftShopSiteService.searchGiftCate());
        resultMap.put("pb", giftShopSiteService.searchGiftList(gift, pb));
        resultMap.put("pCate", giftShopSiteService.selectByParentId(gift.getGiftParentId()));
        resultMap.put("sCate", giftShopSiteService.selectByCateId(gift.getGiftCateId()));
        resultMap.put("gift", gift != null ? gift : null);
        resultMap.put("orderlist", giftOrderService.orderList());
        // 日志记录
        LOGGER.info("获取积分商城列表");
        return topAndBottomService.getTopAndBottom(mav).addAllObjects(resultMap);
    }

    /**
     * 赠品详情页
     * 
     * @param giftId
     *            赠品Id {@link java.lang.Long}
     * @param request
     * @param response
     * @return mav
     */
    @RequestMapping("/giftdetail")
    public ModelAndView giftDetail(Long giftId, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("giftshop/gift_detail");
        Map<String, Object> resultMap = new HashMap<>();
        Long customerId = (Long) request.getSession().getAttribute(CUSTOMERID);
        if (customerId != null) {
            resultMap.put("cusId", customerId);
            resultMap.put("cusInfo", customerServiceMapper.selectByPrimaryKey(customerId));
        }
        Gift gift = giftShopSiteService.selectByGiftId(giftId);

        resultMap.put("pCate", giftShopSiteService.selectByParentId(gift.getGiftParentId()));
        resultMap.put("sCate", giftShopSiteService.selectByCateId(gift.getGiftCateId()));
        resultMap.put("catelist", giftShopSiteService.searchGiftCate());
        resultMap.put("gift", gift);
        resultMap.put("orderlist", giftOrderService.orderList());
        // 日志记录
        LOGGER.info("赠品详情页,赠品ID为：" + giftId);
        return topAndBottomService.getTopAndBottom(mav).addAllObjects(resultMap);
    }

    /**
     * 积分订单
     * 
     * @param type
     * @param request
     * @return
     */
    @RequestMapping("/giftorder")
    public ModelAndView giftOrder(HttpServletRequest request, PageBean pb, String date, String type) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        Map<String, Object> resultMap = new HashMap<String, Object>();
        ModelAndView mav = new ModelAndView("customer/newgiftorder");
        Long customerId = (Long) request.getSession().getAttribute(CUSTOMERID);
        if (customerId == null) {
            // 没登录的用户跳转到登录页面
            mav = new ModelAndView(CustomerConstantStr.LOGINREDIRECT).addObject(CustomerConstantStr.URL, CustomerConstantStr.CUSTOMERS + GIFTORDER_HTML);
        } else {
            paramMap.put(CustomerConstantStr.CUSTOMERID, (Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID));
            paramMap.put(CustomerConstantStr.DATE, date);
            paramMap.put(CustomerConstantStr.TYPE, type);
            pb.setUrl("customer/newgiftorder");
            resultMap.put(CustomerConstantStr.TYPE, type);
            resultMap.put(CustomerConstantStr.DATE, date);
            resultMap.put(CustomerConstantStr.PB, giftOrderService.queryGiftOrder(pb, paramMap));
        }
        return topAndBottomService.getTopAndBottom(mav).addAllObjects(resultMap);
    }

    /**
     * 提交赠品订单
     * 
     * @param giftOrder
     * @param request
     * @return
     */
    @RequestMapping("subgiftorder")
    public ModelAndView subGiftOrder(GiftOrder giftOrder, HttpServletRequest request, HttpServletResponse response, Long giftStock) {
        response.setContentType("text/html;charset=UTF-8");
        try {
            pw = response.getWriter();
            Long customerId = (long) request.getSession().getAttribute(CUSTOMERID);
            // 判断是否越权，积分是否为负数
            if (giftOrder != null && giftOrder.getCustomerId().equals(customerId) && giftOrder.getOrderIntegral() >= 0) {
                pw.print(giftOrderService.subOrder(giftOrder, giftStock));
                // 日志记录
                LOGGER.info("提交赠品订单成功");
            } else {
                pw.print(-1);
            }
        } catch (IOException e) {
            LOGGER.error("兑换失败",e);
        } finally {
            if (pw != null) {
                pw.close();
            }
        }

        return null;
    }

    /**
     * 赠品订单页
     * 
     * @param request
     * @param giftOrderId
     * @return
     */
    @RequestMapping("/comfirmofgiftorder")
    public ModelAndView comfirmogift(HttpServletRequest request, Long giftOrderId, String fromUrl) {
        ModelAndView mav = null;

        // 检查用户是否登录
        Object cust = request.getSession().getAttribute(CUSTOMERID);
        Long customerId = (Long) cust;
        if (customerId == null) {
            mav = new ModelAndView(CustomerConstantStr.LOGINREDIRECT).addObject(CustomerConstantStr.URL, CustomerConstantStr.CUSTOMERS + GIFTORDER_HTML);
        } else {
            giftOrderService.updateOrderVice(giftOrderId);
            // 控制跳转
            if ("index".equals(fromUrl)) {
                // 首页
                mav = new ModelAndView(new RedirectView(request.getContextPath() + CustomerConstantStr.CUSTOMERS + "/index.html"));
            } else if ("giftorder".equals(fromUrl)) {
                mav = new ModelAndView(new RedirectView(request.getContextPath() + CustomerConstantStr.CUSTOMERS + GIFTORDER_HTML));
            }
        }
        // 跳转订单页面topAndBottomService.getTopAndBottom(mav)
        return mav;
    }

    /**
     * 订单详情
     * 
     * @param giftOrderId
     *            主键id
     * @return
     */
    @RequestMapping("/giftorderdetail")
    public ModelAndView giftOrderDetails(Long giftOrderId, HttpServletRequest request) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Long customerId = (Long) request.getSession().getAttribute(CUSTOMERID);
        ModelAndView mav = null;
        mav = new ModelAndView("customer/giftorderdetail");
        GiftOrderVo order = giftOrderService.orderDetail(giftOrderId);
        if (order.getCustomerId().equals(customerId)) {
            resultMap.put("order", order);
            if (order.getTemp2() != null && !"".equals(order.getTemp2()) && order.getTemp3() != null && !"".equals(order.getTemp3())) {
                resultMap.put("relations", orderService.queryExpressInfoUrl(Integer.parseInt(order.getTemp2()), order.getTemp3()));
            }
            return topAndBottomService.getTopAndBottom(mav).addAllObjects(resultMap);
        } else {
            mav = new ModelAndView(CustomerConstantStr.LOGINREDIRECT).addObject(CustomerConstantStr.URL, CustomerConstantStr.CUSTOMERS + "/myorder.html");
            return topAndBottomService.getTopAndBottom(mav);
        }
    }

    /**
     * 点击父分类跳转控制器
     * 
     * @param cateId
     *            父分类Id
     * @return
     */
    @RequestMapping("/jumpgift")
    public ModelAndView jumpgift(Long cateId) {
        return new ModelAndView(new RedirectView("../giftshop/" + this.giftShopSiteService.selectSonCateId(cateId) + ".html"));
    }

    public GiftShopSiteService getGiftShopSiteService() {
        return giftShopSiteService;
    }

    @Resource(name = "GiftShopSiteService")
    public void setGiftShopSiteService(GiftShopSiteService giftShopSiteService) {
        this.giftShopSiteService = giftShopSiteService;
    }

    @Resource(name = "customerServiceMapper")
    public void setCustomerServiceMapper(CustomerServiceMapper customerServiceMapper) {
        this.customerServiceMapper = customerServiceMapper;
    }

    @Resource(name = "GiftOrderService")
    public void setGiftOrderService(GiftOrderService giftOrderService) {
        this.giftOrderService = giftOrderService;
    }

    @Resource(name = "OrderService")
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

}
