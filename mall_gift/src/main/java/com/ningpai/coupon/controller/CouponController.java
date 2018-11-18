/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.coupon.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.ningpai.goods.util.ValueUtil;
import com.ningpai.util.*;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.common.safe.CSRFTokenManager;
import com.ningpai.coupon.bean.Coupon;
import com.ningpai.coupon.bean.CouponNo;
import com.ningpai.coupon.service.CouponLelevlService;
import com.ningpai.coupon.service.CouponNoService;
import com.ningpai.coupon.service.CouponService;
import com.ningpai.customer.bean.Customer;
import com.ningpai.customer.service.PointLevelServiceMapper;
import com.ningpai.goods.service.GoodsBrandService;
import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.other.util.CustomerConstantStr;
import com.ningpai.system.bean.BasicSet;
import com.ningpai.system.service.BasicSetService;

/**
 * @author ggn
 * 
 */
@Controller
public class CouponController {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(CouponController.class);

    private static final String PAGEBEAN = "pageBean";
    private static final String COUPON = "coupon";
    private static final String PRODUCTCATE = "productcate";
    private static final String SKULIST = "skulist";
    private static final String COUPONID = "couponId";
    private static final String PRODUCTBRAND = "productbrand";
    private static final String DATETIME = "yyyy-MM-dd HH:mm:ss";
    private static final String LOGGERINFO1 = "-->优惠券名称【";
    private static final String LOGGERINFO2 = "】,用户名：";
    private static final String LOGGERINFO3 = "添加优惠劵出错";

    /**
     * 优惠券service
     */
    private CouponService couponService;
    /**
     * 商品品牌service
     */
    private GoodsBrandService goodsBrandService;
    /**
     * 优惠券券码service
     */
    private CouponNoService couponNoService;
    /**
     * 优惠券等级service
     */
    private CouponLelevlService couponLelevlService;
    /**
     * 站点设置service
     */
    @Resource(name = "basicSetService")
    private BasicSetService basicSetService;

    private PointLevelServiceMapper pointLevelServiceMapper;

    /**
     * 添加优惠劵
     * 
     * @param request
     * @return
     */
    @RequestMapping("addcouponpx")
    public ModelAndView addCouponPx(HttpServletRequest request) {
        // 获取券码
        Coupon coupon = couponService.selectOneCouponNoByCouponIdAndUpdateNoIsGet(166L, (Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID));
        // 判断是否为空
        if (coupon == null) {
            // 添加
            couponService.addCouponC(166L);
            // 获取券码
            coupon = couponService.selectOneCouponNoByCouponIdAndUpdateNoIsGet(166L, (Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID));
        }
        // 赠送优惠券
        couponService.giveCusCoupon(coupon.getCodeNo(), (Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID));
        // 返回控制器
        return new ModelAndView("redirect:/item/3291.html");
    }

    /**
     * 添加优惠券
     * 
     * @return
     */
    @RequestMapping("addcounponpxc")
    @ResponseBody
    public int addCounponPxC(Long couponId) {
        try {
            // 添加
            couponService.addCouponC(couponId);
        } catch (Exception e) {
            LOGGER.error("",e);
            return 1;
        }

        return 0;
    }

    /**
     * 查询优惠券列表
     * 
     * @param pageBean
     * @return ModelAndView
     */
    @RequestMapping("/couponlist")
    public ModelAndView searchCouponList(Coupon coupon, PageBean pageBean, String startTime, String endTime, HttpServletRequest request) {
        // 设置本地路径
        pageBean.setUrl(Constants.INITCOUPONLIST);
        // 获取第三方id
        coupon.setBusinessId((Long) request.getSession().getAttribute("thirdId"));
        // 获取列表
        // 返回到页面
        return new ModelAndView(Constants.COUPONLIST, PAGEBEAN, couponService.searchCouponList(coupon, pageBean, startTime, endTime)).addObject("sx", request.getSession()
                .getAttribute(CSRFTokenManager.CSRF_TOKEN_FOR_SESSION_ATTR_NAME).toString());
    }

    /**
     * 查询优惠券
     * 
     * @param pageBean
     * @return ModelAndView
     */
    @RequestMapping("/querycouponlistall")
    @ResponseBody
    public PageBean queryCouponListAll(HttpServletRequest request, Coupon coupon, PageBean pageBean, String startTime, String endTime, String couponSearchName) {
        // 中文转换
        if (couponSearchName != null && !"".equals(couponSearchName)) {
            try {
                // 优惠券名称
                coupon.setCouponName(java.net.URLDecoder.decode(couponSearchName, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                LOGGER.error("",e);
            }
        }
        // 设置店铺id
        coupon.setBusinessId((Long) request.getSession().getAttribute("thirdId"));
        // 返回优惠券集合
        return couponService.searchCouponList(coupon, pageBean, startTime, endTime);
    }

    /**
     * 跳转修改优惠券
     * 
     * @param couponId
     * @return ModelAndView
     */
    @RequestMapping("/toupdatecoupon")
    public ModelAndView toUpdateCoupon(Long couponId, HttpServletRequest request) {
        // customerLevel会员等级
        // brandlist品牌集合
        // listLevel优惠券等级范围
        return new ModelAndView(Constants.TOUPDATECOUPON, COUPON, couponService.searchCouponById(couponId)).addObject(PRODUCTCATE, couponService.selectCouponRange(couponId, "0"))
                .addObject(PRODUCTBRAND, couponService.selectCouponRange(couponId, "1")).addObject(SKULIST, couponService.selectCouponRange(couponId, "2"))
                .addObject("brandlist", goodsBrandService.queryAllBrandList()).addObject("customerLevel", pointLevelServiceMapper.selectAllPointLevel())
                .addObject("listLevel", couponLelevlService.selectCouponLelvel(couponId))
                .addObject("sx", request.getSession().getAttribute(CSRFTokenManager.CSRF_TOKEN_FOR_SESSION_ATTR_NAME).toString());

    }

    /**
     * 查询优惠券详细信息
     * 
     * @param couponId
     * @return ModelAndView
     */
    @RequestMapping("/searchcouponbyid")
    public ModelAndView searchCouponById(Long couponId) {
        // couponNo 券码
        // levelNamelist 优惠券等级名称
        return new ModelAndView(Constants.COUPONDETAIL, COUPON, couponService.searchCouponById(couponId)).addObject(PRODUCTCATE, couponService.selectCouponRange(couponId, "0"))
                .addObject(PRODUCTBRAND, couponService.selectCouponRange(couponId, "1")).addObject(SKULIST, couponService.selectCouponRange(couponId, "2"))
                .addObject("couponNo", couponNoService.selectNoByCouponId(couponId)).addObject("levelNamelist", couponLelevlService.queryLevelNameByCouponId(couponId));
    }

    /**
     * ajax查询优惠券详细信息
     * 
     * @param couponId
     * @return
     */
    @RequestMapping("/searchCouponByIdAjax")
    @ResponseBody
    public Map<String, Object> searchCouponByIdAjax(Long couponId) {
        Map<String, Object> map = new HashMap<String, Object>();
        // 查询优惠券详细信息
        map.put(COUPON, couponService.searchCouponById(couponId));
        // 类型0的范围
        map.put(PRODUCTCATE, couponService.selectCouponRange(couponId, "0"));
        // 类型1的范围
        map.put(PRODUCTBRAND, couponService.selectCouponRange(couponId, "1"));
        // 类型2的范围
        map.put(SKULIST, couponService.selectCouponRange(couponId, "2"));
        // 查询优惠券券码
        map.put("couponNo", couponNoService.selectNoByCouponId(couponId));
        // 等级名称集合
        map.put("levelNamelist", couponLelevlService.queryLevelNameByCouponId(couponId));
        return map;
    }

    /**
     * 修改优惠券
     * 
     * @param request
     * @return ModelAndView
     */
    @RequestMapping("/doupdatecouponbyid")
    public ModelAndView doUpdateCouponById(HttpServletRequest request, @ModelAttribute("uploadForm") FileUploadForm uploadForm, Coupon coupon, String couponSTime,
            String couponETime, Long[] lelvelId, String status) {
        SimpleDateFormat formatDate = new SimpleDateFormat(DATETIME);

        try {
            // 开始时间转换格式
            coupon.setCouponStartTime(formatDate.parse(couponSTime));
            // 结束时间转换格式
            coupon.setCouponEndTime(formatDate.parse(couponETime));
        } catch (ParseException e) {
            LOGGER.info("更新优惠券！");
        }
        // 保存图片
        String couponPic = UploadUtil.uploadFileOne(uploadForm.getCouponImg(), request);
        // 图片路径放入实体类
        coupon.setCouponPic(couponPic);
        // 修改优惠券
        couponService.doUpdateCouponById(coupon, request, lelvelId, status);
        // 非空验证 优惠券名称
        if (null != coupon.getCouponName()) {
            OperaLogUtil.addOperaLog(request, (String) request.getSession().getAttribute(ValueUtil.NAME), "修改优惠劵", (String) request.getSession().getAttribute(ValueUtil.OPERAPATH)
                    + LOGGERINFO1 + coupon.getCouponName() + LOGGERINFO2 + (String) request.getSession().getAttribute(ValueUtil.NAME));
        }
        return new ModelAndView(new RedirectView(Constants.INITCOUPONLIST));
    }

    /**
     * 分页查询优惠劵劵码
     * 
     * @param pageBean
     *            分页参数
     * @param couponId
     *            劵码id
     * @param couponNo
     *            查询参数
     * @return
     */
    @RequestMapping("/selectcouponnolist")
    public ModelAndView selectCouponNoList(PageBean pageBean, Long couponId, CouponNo couponNo, HttpServletRequest request) {
        // 设置路径
        pageBean.setUrl(Constants.SELECTCOUPONNOLIST);
        return new ModelAndView(Constants.CODEXLIST).addObject(PAGEBEAN, couponNoService.selectList(pageBean, couponId, couponNo)).addObject(COUPONID, couponId)
                .addObject("sx", request.getSession().getAttribute(CSRFTokenManager.CSRF_TOKEN_FOR_SESSION_ATTR_NAME).toString());
    }

    /**
     * ajax获取优惠券列表
     * 
     * @param pageBean
     * @param couponId
     * @param couponNo
     * @return
     */
    @RequestMapping("/selectcouponnolistajax")
    @ResponseBody
    public Map<String, Object> selectcouponnolistajax(PageBean pageBean, Long couponId, CouponNo couponNo) {
        // 创建map
        Map<String, Object> map = new HashMap<String, Object>();
        // 查询券码
        map.put(PAGEBEAN, couponNoService.selectList(pageBean, couponId, couponNo));
        // 优惠券id
        map.put(COUPONID, couponId);
        return map;
    }

    /**
     * ajax获取优惠券列表
     * 
     * @param pageBean
     * @param couponId
     * @param couponNo
     * @return
     */
    @RequestMapping("/newselectcouponnolistajax")
    @ResponseBody
    public Map<String, Object> newselectcouponnolistajax(PageBean pageBean, Long couponId, CouponNo couponNo) {
        // 创建map
        Map<String, Object> map = new HashMap<String, Object>();
        // 查询券码
        map.put(PAGEBEAN, couponNoService.selectCouponList(couponId, couponNo));
        // 优惠券id
        map.put(COUPONID, couponId);
        return map;
    }

    /**
     * 跳转添加优惠券页面
     * 
     * @return ModelAndView
     */
    @RequestMapping("/toaddcoupon")
    public ModelAndView toAddCoupon(HttpServletRequest request) {
        // brandlist品牌列表
        // customerLevel会员等级
        return new ModelAndView(Constants.ADDCOUPON).addObject("brandlist", goodsBrandService.queryAllBrandList())
                .addObject("customerLevel", pointLevelServiceMapper.selectAllPointLevel())
                .addObject("sx", request.getSession().getAttribute(CSRFTokenManager.CSRF_TOKEN_FOR_SESSION_ATTR_NAME).toString());
    }

    /**
     * 添加优惠券
     * 
     * @param request
     * @param uploadForm
     * @param coupon
     * @return ModelAndView
     */
    @RequestMapping("/doaddcoupon")
    public ModelAndView doAddCoupon(HttpServletRequest request, @ModelAttribute("uploadForm") FileUploadForm uploadForm, @Valid Coupon coupon, String couponSTime,
            String couponETime, Long[] lelvelId, String status, String csrFToken, BindingResult result) {
        // 时间
        SimpleDateFormat formatDate = new SimpleDateFormat(DATETIME);
        try {
            // 开始时间转换格式
            coupon.setCouponStartTime(formatDate.parse(couponSTime));
            // 结束时间转换格式
            coupon.setCouponEndTime(formatDate.parse(couponETime));
        } catch (ParseException e) {
            // 日志
            String operaCode = LOGGERINFO3;
            String operaContent = request.getSession().getAttribute(CustomerConstantStr.OPERAPATH) + LOGGERINFO3;
            OperaLogUtil.addOperaLog(request, request.getSession().getAttribute(CustomerConstantStr.NAME).toString(), operaCode, operaContent);
        }
        // 保存图片
        String couponPic = UploadUtil.uploadFileOne(uploadForm.getCouponImg(), request);
        // 图片路径
        coupon.setCouponPic(couponPic);
        // 添加
        couponService.doAddCoupon(coupon, request, lelvelId, status);
        // 非空验证 优惠券名称
        if (null != coupon.getCouponName()) {
            // 日志
            OperaLogUtil.addOperaLog(request, (String) request.getSession().getAttribute(ValueUtil.NAME), "添加优惠券", (String) request.getSession().getAttribute(ValueUtil.OPERAPATH)
                    + LOGGERINFO1 + coupon.getCouponName() + LOGGERINFO2 + (String) request.getSession().getAttribute(ValueUtil.NAME));
        }
        return new ModelAndView(new RedirectView(Constants.INITCOUPONLIST));

    }

    /**
     * 添加优惠券
     *
     * @param request
     * @param coupon
     * @return ModelAndView
     */
    @RequestMapping("/doaddcouponnew")
    public ModelAndView doAddCouponNew(HttpServletRequest request, @Valid Coupon coupon, String couponSTime, String couponETime, Long[] lelvelId, String status, String csrFToken,
            BindingResult result) {
        // 时间
        SimpleDateFormat formatDate = new SimpleDateFormat(DATETIME);
        try {
            // 开始时间转换格式
            coupon.setCouponStartTime(formatDate.parse(couponSTime));
            // 结束时间转换格式
            coupon.setCouponEndTime(formatDate.parse(couponETime));
        } catch (ParseException e) {
            // 日志
            String operaCode = LOGGERINFO3;
            String operaContent = request.getSession().getAttribute(CustomerConstantStr.OPERAPATH) + LOGGERINFO3;
            OperaLogUtil.addOperaLog(request, request.getSession().getAttribute(CustomerConstantStr.NAME).toString(), operaCode, operaContent);
        }
        // boss保存购物券应该设置商家id为0
        coupon.setBusinessId(0L);
        // 添加
        couponService.doAddCoupon(coupon, request, lelvelId, status);
        // 非空验证 优惠券名称
        if (null != coupon.getCouponName()) {
            // 日志
            OperaLogUtil.addOperaLog(request, (String) request.getSession().getAttribute(ValueUtil.NAME), "添加优惠券", (String) request.getSession().getAttribute(ValueUtil.OPERAPATH)
                    + LOGGERINFO1 + coupon.getCouponName() + LOGGERINFO2 + (String) request.getSession().getAttribute(ValueUtil.NAME));
        }
        return new ModelAndView(new RedirectView(Constants.INITCOUPONLIST));

    }

    /**
     * uo 删除优惠券
     * 
     * @param
     *
     * @return INITCOUPONLIST
     */
    @RequestMapping("/delcoupon")
    public ModelAndView delCoupon(HttpServletRequest request, Long couponId) {
        // 根据主键查询优惠券
        Coupon coupon = couponService.searchCouponById(couponId);
        // 删除优惠券
        int result = couponService.delCoupon(couponId);
        // 非空验证 优惠券名称
        if (1 == result && null != coupon.getCouponName()) {
            OperaLogUtil.addOperaLog(request, (String) request.getSession().getAttribute(ValueUtil.NAME), "删除优惠券", (String) request.getSession().getAttribute(ValueUtil.OPERAPATH)
                    + LOGGERINFO1 + coupon.getCouponName() + LOGGERINFO2 + (String) request.getSession().getAttribute(ValueUtil.NAME));
        }
        return new ModelAndView(new RedirectView(Constants.INITCOUPONLIST));
    }

    /**
     * 批量删除优惠券
     * 
     * @param couponId
     * @return INITCOUPONLIST
     */
    @RequestMapping("/delallcoupon")
    public ModelAndView delAllCoupon(HttpServletRequest request, Long[] couponId) {
        // 删除优惠券
        couponService.delAllCoupon(couponId);
        // 日志
        OperaLogUtil.addOperaLog(request, (String) request.getSession().getAttribute(ValueUtil.NAME), ValueUtil.UPDATEWAREHOUSEINFO,
                (String) request.getSession().getAttribute(ValueUtil.OPERAPATH) + ",用户名：" + (String) request.getSession().getAttribute(ValueUtil.NAME));
        return new ModelAndView(new RedirectView(Constants.INITCOUPONLIST));
    }

    /**
     * 点“获取链接”，领取券码
     * 
     * @param couponId
     * @return
     */
    @RequestMapping("/getCouponNoByCouponId")
    public void getCouponNoByCouponId(Long couponId, HttpServletResponse response, HttpServletRequest request) {
        // 插叙优惠券详细
        Coupon coupon = couponService.searchCouponById(couponId);
        PrintWriter pw;
        try {
            pw = response.getWriter();
            Date d = new Date();
            // 如果开始时间 大于当前时间 并且 结束世界晚与当前时间 为有效
            if (coupon.getCouponEndTime().after(d)) {
                // 查询
                BasicSet bs = basicSetService.findBasicSet();
                // 返回
                pw.print(bs.getBsetAddress() + "/getOffCoupon-" + couponId + ".html");
            } else {
                // 优惠券无效
                pw.print("0");
            }
        } catch (IOException e) {
            Customer cust = (Customer) request.getSession().getAttribute("cust");
            OperaLogUtil.addOperaException(cust.getCustomerUsername(), e, request);
        }

    }

    /**
     * 领取优惠券提示页
     * 
     * @param couponId
     * @param flag
     * @return
     */
    @RequestMapping("openGetCouponPage")
    public ModelAndView openGetCouponPage(Long couponId, String flag, String url, Long codeId) {
        // 查询优惠券详细
        Coupon coupon = couponService.searchCouponById(couponId);
        // 获取优惠券数量
        int num = couponNoService.getCouponGetNoByCouponId(couponId);
        // 判断领取数量是否大于 优惠券数量
        if (coupon.getCouponGetNo() > num) {
            // 修改状态
            couponNoService.changeCouponGetAndStatus(codeId);
        }
        // 判断
        if (Integer.parseInt(flag) == 0) {
            return new ModelAndView(Constants.OPENFAILDIALOG);
        } else if (Integer.parseInt(flag) == 1) {
            return new ModelAndView(Constants.OPENSUCCESSDIALOG).addObject("url", url);
        } else {
            return new ModelAndView(Constants.OPENFAILDIALOG);
        }
    }

    public PointLevelServiceMapper getPointLevelServiceMapper() {
        return pointLevelServiceMapper;
    }

    @Resource(name = "pointLevelServiceMapper")
    public void setPointLevelServiceMapper(PointLevelServiceMapper pointLevelServiceMapper) {
        this.pointLevelServiceMapper = pointLevelServiceMapper;
    }

    public CouponService getCouponService() {
        return couponService;
    }

    @Resource(name = "CouponService")
    public void setCouponService(CouponService couponService) {
        this.couponService = couponService;
    }

    public GoodsBrandService getGoodsBrandService() {
        return goodsBrandService;
    }

    @Resource(name = "GoodsBrandService")
    public void setGoodsBrandService(GoodsBrandService goodsBrandService) {
        this.goodsBrandService = goodsBrandService;
    }

    public CouponNoService getCouponNoService() {
        return couponNoService;
    }

    @Resource(name = "CouponNoService")
    public void setCouponNoService(CouponNoService couponNoService) {
        this.couponNoService = couponNoService;
    }

    public CouponLelevlService getCouponLelevlService() {
        return couponLelevlService;
    }

    @Resource(name = "CouponLelevlService")
    public void setCouponLelevlService(CouponLelevlService couponLelevlService) {
        this.couponLelevlService = couponLelevlService;
    }

}
