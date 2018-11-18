/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.third.market.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import com.ningpai.common.util.ConstantUtil;
import com.ningpai.coupon.bean.Coupon;
import com.ningpai.coupon.service.CouponLelevlService;
import com.ningpai.coupon.service.CouponNoService;
import com.ningpai.coupon.service.CouponService;
import com.ningpai.customer.service.PointLevelServiceMapper;
import com.ningpai.goods.service.GoodsBrandService;
import com.ningpai.goods.service.GoodsProductService;
import com.ningpai.third.market.service.ThirdCouponService;
import com.ningpai.third.market.util.ThirdCouponPath;
import com.ningpai.third.util.MenuOperationUtil;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;
import com.ningpai.util.UploadUtil;

/**
 * <p>
 * 第三方优惠劵
 * </p>
 * 
 * @author zhanghl
 * @since 2014年4月15日 下午4:02:38
 * @version 2.0
 */
@Controller
public class CouponThirdController {

    // 创建操作日志对象
    private static final MyLogger LOGGER = new MyLogger(CouponThirdController.class);

    private static final String THIRDID = "thirdId";
    private static final String DATETIME = "yyyy-MM-dd HH:mm:ss";
    private static final String CUSTOMERLEVEL = "customerLevel";
    private static final String COUPON = "coupon";
    private static final String SKULIST = "skulist";

    // 优惠券接口
    private CouponService couponService;

    // 商品品牌service层接口
    private GoodsBrandService goodsBrandService;

    // 优惠券券码接口
    private CouponNoService couponNoService;

    // 优惠券等级范围
    private CouponLelevlService couponLelevlService;

    // 会员等级功能接口
    private PointLevelServiceMapper pointLevelServiceMapper;

    // 商品接口
    @Resource(name = "GoodsProductService")
    private GoodsProductService goodsProductService;

    // 第三方优惠劵接口
    @Resource(name = "ThirdCouponService")
    private ThirdCouponService thirdCouponService;

    /**
     * 查询优惠券列表
     * 
     * @param pageBean
     * @return ModelAndView
     */
    @RequestMapping("/thirdcouponlist")
    public ModelAndView thirdCouponList(Coupon coupon, PageBean pageBean, HttpServletRequest request, String n, String l, String startTime, String endTime) {
        MenuOperationUtil.fillSessionMenuIndex(request, n, l);
        coupon.setBusinessId((Long) request.getSession().getAttribute(THIRDID));
        pageBean.setUrl(ThirdCouponPath.INITCOUPONLIST);
        // 设置日期格式
        SimpleDateFormat df = new SimpleDateFormat(DATETIME);
        return new ModelAndView(ThirdCouponPath.COUPONLIST).addObject("pb", couponService.searchCouponList(coupon, pageBean, startTime, endTime)).addObject("nowdate",
                df.format(new Date()));
    }

    /**
     * 跳转添加优惠券页面
     * 
     * @return ModelAndView
     */
    @RequestMapping("/toaddcouponthird")
    public ModelAndView toAddCouponThird(HttpServletRequest request) {
        // 添加优惠券页面
        return new ModelAndView(ThirdCouponPath.ADDCOUPON)
        // 第三方店铺货品
                .addObject("prolist", goodsProductService.queryProductForMarketing((Long) request.getSession().getAttribute(THIRDID)))
                // 会员等级
                .addObject(CUSTOMERLEVEL, pointLevelServiceMapper.selectAllPointLevel());
    }

    /**
     * 处理数据 字符串（页面编辑器的标签）
     * 
     * @param str
     * @return
     */
    private String getStr(String str) {
        String strNew = str;
        strNew = strNew.replace("<p>", "");
        strNew = strNew.replace("</p>", "");
        strNew = strNew.replace("<br>", "");
        return strNew;
    }

    /**
     * 添加优惠劵
     * 
     * @param request
     * @param coupon
     *            优惠劵信息
     * @param sTime
     *            优惠劵开始时间
     * @param eTime
     *            优惠劵结束日期
     * @param lelvelId
     *            会员级别
     * @param status
     *            优惠劵状态
     * @param request2
     * @return
     */
    @RequestMapping("/addcouponthird")
    public ModelAndView addCouponThird(HttpServletRequest request, Coupon coupon, String sTime, String eTime, Long[] lelvelId, String status, MultipartHttpServletRequest request2) {

        // 去除编辑器带的html标签
        if (null != coupon.getCouponRemark()) {
            coupon.setCouponRemark(getStr(coupon.getCouponRemark()));
        }
        // 上传的图片信息
        MultipartFile imageFile = request2.getFile("couponImg");
        // 上传图片
        if (imageFile == null) {
            // 优惠劵图片
            imageFile = request2.getFile("picFile");
        }
        if (!imageFile.isEmpty()) {
            // 优惠劵图片
            coupon.setCouponPic(UploadUtil.uploadFile(imageFile, request).get("oldimg"));
        }
        // 时间格式化
        SimpleDateFormat formatDate = new SimpleDateFormat(DATETIME);
        // 获取第三方店铺id
        coupon.setBusinessId((Long) request.getSession().getAttribute(THIRDID));
        try {
            // 优惠劵开始日期
            coupon.setCouponStartTime(formatDate.parse(sTime));
            // 优惠劵结束日期
            coupon.setCouponEndTime(formatDate.parse(eTime));
        } catch (ParseException e) {
            LOGGER.error("添加优惠劵时时间转换错误：" + e);
        }
        // 执行添加优惠券操作
        couponService.doAddCoupon(coupon, request, lelvelId, status);
        // 设置要跳转的页面路径
        return new ModelAndView(new RedirectView(ThirdCouponPath.INITCOUPONLIST));
    }

    /**
     * 删除优惠券
     * 
     * @param couponId
     * @return INITCOUPONLIST
     */
    @RequestMapping("/delcouponthird")
    public ModelAndView delCouponThird(Long couponId) {
        // 根据优惠劵ID删除单个优惠劵信息
        couponService.delCoupon(couponId);
        // 重定向到优惠劵列表
        return new ModelAndView(new RedirectView(ThirdCouponPath.INITCOUPONLIST));
    }

    /**
     * 新删除第三方店铺优惠券
     * 
     * @param couponId
     *            优惠券id
     * @param request
     * @return
     */
    @RequestMapping("/newdelcouponthird")
    @ResponseBody
    public int newDelCouponThird(Long couponId, HttpServletRequest request) {
        // 获取店铺Id
        Long thirdId = (Long) request.getSession().getAttribute(ConstantUtil.THIRDID);
        // 执行删除优惠券操作
        return couponService.newdelCoupon(couponId, thirdId);
    }

    /**
     * 新批量删除优惠券
     * 
     * @param couponId
     *            优惠券id
     * @param request
     * @return int 执行删除操作返回的结果
     */
    @RequestMapping("/newdelcouponlist")
    @ResponseBody
    public int newdelCouponList(Long[] couponId, HttpServletRequest request) {
        // 获取店铺Id
        Long thirdId = (Long) request.getSession().getAttribute(ConstantUtil.THIRDID);
        // 执行删除优惠券操作
        return couponService.delAllCoupon(couponId, thirdId);
    }

    /**
     * 跳转优惠券详细信息
     * 
     * @param couponId
     * @return ModelAndView
     */
    @RequestMapping("/tocoupondetailthird")
    public ModelAndView toCouponDetatilThird(Long couponId, HttpServletRequest request) {
        // 设置要跳转的页面
        return new ModelAndView("/coupon/coupondetailthird", COUPON,
        // 查询优惠券详细信息
                couponService.searchCouponById(couponId)).addObject(SKULIST, couponService.selectCouponRange(couponId, "2"))
        // 查询范围
                .addObject("brandlist", goodsBrandService.queryAllBrandList())
                // 查询所有品牌
                .addObject(CUSTOMERLEVEL, pointLevelServiceMapper.selectAllPointLevel())
                // 查询此优惠券的等级范围
                .addObject("listLevel", couponLelevlService.selectCouponLelvel(couponId));
    }

    /**
     * 跳转修改优惠券
     * 
     * @param couponId
     * @return ModelAndView
     */
    @RequestMapping("/toupdatecouponthird")
    public ModelAndView toUpdateCouponThird(Long couponId, HttpServletRequest request) {
        // 修改优惠券页面
        return new ModelAndView(ThirdCouponPath.TOUPDATECOUPON, COUPON, couponService.searchCouponById(couponId))
        // 第三方店铺货品
                .addObject("prolist", goodsProductService.queryProductForMarketing((Long) request.getSession().getAttribute(THIRDID)))
                // 促销分类范围
                .addObject("catelist", thirdCouponService.selectThirdMarketingScope(couponId, "0"))
                // 促销品牌范围
                .addObject("brandlist", thirdCouponService.selectThirdMarketingScope(couponId, "1"))
                // 会员等级
                .addObject(CUSTOMERLEVEL, pointLevelServiceMapper.selectAllPointLevel())
                // 优惠券使用范围 优惠券使用等级
                .addObject(SKULIST, couponService.selectCouponRange(couponId, "2")).addObject("listLevel", couponLelevlService.selectCouponLelvel(couponId));
    }

    /**
     * 查看优惠券详情
     * 
     * @param couponId
     *            优惠券ID {@link java.lang.Long}
     * @return
     */
    public Map<String, Object> seachThirdCounponDetail(Long couponId) {
        // 定义Map对象
        Map<String, Object> map = new HashMap<String, Object>();
        // 优惠券信息
        map.put(COUPON, couponService.searchCouponById(couponId));
        // 优惠券范围（分类）
        map.put("productcate", thirdCouponService.selectThirdMarketingScope(couponId, "0"));
        // 优惠券范围（品牌）
        map.put("productbrand", thirdCouponService.selectThirdMarketingScope(couponId, "1"));
        // 优惠券范围（货品）
        map.put(SKULIST, couponService.selectCouponRange(couponId, "2"));
        // 优惠券券码
        map.put("couponNo", couponNoService.selectNoByCouponId(couponId));
        // 优惠券等级
        map.put("levelNamelist", couponLelevlService.queryLevelNameByCouponId(couponId));
        return map;
    }

    /**
     * 修改优惠券
     * 
     * @return ModelAndView
     */
    @RequestMapping("/doupdatethirdcouponbyid")
    public ModelAndView doUpdateThirdCouponById(HttpServletRequest request, Coupon coupon, String sTime, String eTime, Long[] lelvelId, MultipartHttpServletRequest request2,
            String status) {
        // 要上传的图片信息
        MultipartFile imageFile = request2.getFile("couponImg");
        coupon.setBusinessId((Long) request.getSession().getAttribute(ConstantUtil.THIRDID));
        // 上传图片
        if (imageFile == null) {
            imageFile = request2.getFile("picFile");
        }
        if (!imageFile.isEmpty()) {
            coupon.setCouponPic(UploadUtil.uploadFile(imageFile, request).get("oldimg"));
        }
        // 转换日期类型
        SimpleDateFormat formatDate = new SimpleDateFormat(DATETIME);

        try {
            // 设置优惠劵开始日期
            coupon.setCouponStartTime(formatDate.parse(sTime));
            // 设置优惠劵结束日期
            coupon.setCouponEndTime(formatDate.parse(eTime));
        } catch (ParseException e) {
            LOGGER.error("修改优惠劵时时间转换错误：" + e);
        }
        // 修改优惠券
        couponService.doUpdateCouponById(coupon, request, lelvelId, status);
        // 重定向到优惠劵列表页
        return new ModelAndView(new RedirectView(ThirdCouponPath.INITCOUPONLIST));
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
