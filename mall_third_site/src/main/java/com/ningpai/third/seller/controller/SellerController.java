/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.third.seller.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.ningpai.customer.bean.Customer;
import com.ningpai.customer.service.CustomerServiceMapper;
import com.ningpai.system.service.BasicSetService;
import com.ningpai.system.service.DistrictService;
import com.ningpai.system.util.AddressUtil;
import com.ningpai.third.seller.mapper.StoreInfoMapper;
import com.ningpai.third.sld.service.DomainCustomService;
import com.ningpai.third.util.ThirdStoreURLUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.goods.service.GoodsBrandService;
import com.ningpai.third.seller.bean.StoreInfo;
import com.ningpai.third.seller.bean.ThirdStoreMess;
import com.ningpai.third.seller.service.ApplyBrandService;
import com.ningpai.third.seller.service.SellerService;
import com.ningpai.third.seller.service.StroreContactService;
import com.ningpai.third.seller.util.SellerValueUtil;
import com.ningpai.third.util.MenuOperationUtil;
import com.ningpai.third.util.SellerConstants;

/**
 * 商家信息控制类
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年5月5日 下午6:07:38
 * @version 0.0.1
 */
@Controller
public class SellerController {

    private static final String THIRDID = "thirdId";
    private static final String CUSTOMERID = "customerId";
    /**
     * 商家信息Service
     */
    private SellerService sellerService;
    /**
     * 联系人信息Service
     */
    private StroreContactService stroreContactService;
    /**
     * 商品品牌service层接口
     */
    private GoodsBrandService goodsBrandService;
    @Resource(name = "ApplyBrandService")
    private ApplyBrandService applyBrandService;
    @Resource(name = "basicSetService")
    private BasicSetService basicSetService;
    /**
     * spring 注解 会员service
     */
    @Resource(name = "customerServiceMapper")
    private CustomerServiceMapper customerServiceMapper;
    /**
     * 根据会员查询店铺信息
     */
    @Resource(name = "sotreInfoMapper")
    public StoreInfoMapper sotreInfoMapper;

    /* 域名service */
    @Resource(name = "domainCustomService")
    private DomainCustomService domainCustomService;

    @Resource(name = "DistrictService")
    private DistrictService districtService;

    /**
     * 头部logo根据店铺状态进行跳转
     *
     * @return
     */
    @RequestMapping("third/sellerinfos")
    public ModelAndView sellerinfos(HttpServletRequest request, String n, String l) {
        // 店铺信息
        StoreInfo storeInfo = null;
        /**
         * 填充导航/左侧菜单索引 用于页面控制css选中
         */
        MenuOperationUtil.fillSessionMenuIndex(request, n, l);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        /**
         * 当前登录的会员ID
         */
        Long customerId = (Long) request.getSession().getAttribute(CUSTOMERID);
        // 获取该会员的详细信息
        Customer customer = customerServiceMapper.selectByPrimaryKey(customerId);
        storeInfo = sotreInfoMapper.selectByCustomerId(customer.getCustomerId());
        resultMap.put(SellerValueUtil.INFO, sellerService.selectByStoreId((Long) request.getSession().getAttribute(SellerConstants.THIRDID)));
        resultMap.put("seller", stroreContactService.selectByStoreId((Long) request.getSession().getAttribute(THIRDID)));
        request.getSession().setAttribute("siteUrl", basicSetService.findBasicSet());// 前台首页
        request.getSession().setAttribute("customer", customer);
        // 有店铺去我的店铺首页 没有店铺或者店铺审核未通过 或者为批示就去登陆页面
        if ("1".equals(storeInfo.getCheckStatus()) && "1".equals(storeInfo.getIsSubmit())) {
            return new ModelAndView(SellerConstants.SELLERINFO).addAllObjects(resultMap);
        } else {
            return new ModelAndView(new RedirectView("login.html"));

        }

    }

    /**
     * 商家信息设置
     * 
     * @return
     */
    @RequestMapping("third/sellerinfo")
    public ModelAndView sellerinfo(HttpServletRequest request, String n, String l) {
        // 填充导航/左侧菜单索引 用于页面控制css选中
        MenuOperationUtil.fillSessionMenuIndex(request, n, l);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        // 当前登录的会员ID
        Long customerId = (Long) request.getSession().getAttribute(CUSTOMERID);
        // 获取该会员的详细信息
        Customer customer = customerServiceMapper.selectByPrimaryKey(customerId);

        //所在地区
        String address="";
        StoreInfo storeInfo=sellerService.selectByStoreId((Long) request.getSession().getAttribute(SellerConstants.THIRDID));
        if(storeInfo!=null&&storeInfo.getBussAddrId()!=null&&storeInfo.getBussAddrId().split(",").length==3){
         AddressUtil addressUtil = districtService.queryAddressNameByDistrictId(Long.valueOf(storeInfo.getBussAddrId().split(",")[2]));
       address=addressUtil.getProvinceName()+"-"+addressUtil.getCityName()+"-"+addressUtil.getDistrictName();
        }
        resultMap.put(SellerValueUtil.INFO, storeInfo);
        resultMap.put("address",address);
        resultMap.put("seller", stroreContactService.selectByStoreId((Long) request.getSession().getAttribute(THIRDID)));
        request.getSession().setAttribute("siteUrl", basicSetService.findBasicSet());// 前台首页
        request.getSession().setAttribute("customer", customer);// 当前登录的会员
        request.getSession().setAttribute("infostore", sellerService.selectByStoreId((Long) request.getSession().getAttribute(SellerConstants.THIRDID)));
        return new ModelAndView(SellerConstants.SELLERINFO).addAllObjects(resultMap);

    }

    /**
     * 修改商家信息
     * 
     * @param request
     * @param storeInfo
     *            商家信息
     * @return
     */
    @RequestMapping("third/updateseller")
    public ModelAndView updateSeller(HttpServletRequest request, StoreInfo storeInfo, MultipartHttpServletRequest multipartRequest) {
        Long storeId = (Long) request.getSession().getAttribute(THIRDID);
        storeInfo.setStoreId(storeId);
        sellerService.updateByStoreInfo(storeInfo, multipartRequest, request);
        return new ModelAndView(new RedirectView(request.getContextPath() + SellerConstants.REDIRECTSELLERINFO));
    }

    /**
     * 银行卡号验证
     * 
     * @param request
     * @return
     */
    @RequestMapping("third/bankcheck")
    public ModelAndView bankCheck(HttpServletRequest request, String n, String l) {
        // 填充导航/左侧菜单索引 用于页面控制css选中
        MenuOperationUtil.fillSessionMenuIndex(request, n, l);
        return new ModelAndView(SellerConstants.BANKCHECK).addObject(SellerConstants.SELLER,
                sellerService.selectByStoreId((Long) request.getSession().getAttribute(SellerConstants.THIRDID)));
    }

    /**
     * 银行卡号验证
     * 
     * @param request
     * @return
     */
    @RequestMapping("third/tobankcheck")
    public ModelAndView toBankCheck(HttpServletRequest request) {
        return new ModelAndView(SellerConstants.PAYTOBOSS);
    }

    /**
     * 银行卡号验证
     * 
     * @param request
     * @return
     */
    @RequestMapping("third/paytoboss")
    public ModelAndView payToBoss(HttpServletRequest request) {
        return new ModelAndView(SellerConstants.PAIED);
    }

    /**
     * 跳转信息接收设置
     * 
     * @param request
     * @param n
     * @param l
     * @return
     */
    @RequestMapping("third/massreceive")
    public ModelAndView massreceive(HttpServletRequest request, String n, String l) {
        // 填充导航/左侧菜单索引 用于页面控制css选中
        MenuOperationUtil.fillSessionMenuIndex(request, n, l);
        return new ModelAndView(SellerConstants.INFORECEIVE).addObject("messes",
                sellerService.selectMessByStoreId((Long) request.getSession().getAttribute(SellerConstants.THIRDID)));
    }

    /**
     * 跳转提示开店页面
     * 
     * @return
     */
    @RequestMapping("third/storetip")
    public ModelAndView storeTip() {
        return new ModelAndView(SellerConstants.OPENSTORETIP);
    }

    /**
     * 查询店铺消息设置
     * 
     * @param request
     * @param mid
     *            消息模块编号
     * @return
     */
    @RequestMapping("/querystoremess")
    @ResponseBody
    public ThirdStoreMess queryStoreMessBySidAndMid(HttpServletRequest request, Long mid) {
        return sellerService.queryStoreMessBySidAndMid(request, mid);
    }

    /**
     * 修改消息提示
     * 
     * @param request
     * @param mess
     * @return
     */
    @RequestMapping("/updatestoremess")
    public ModelAndView updateStoreMess(HttpServletRequest request, ThirdStoreMess mess) {
        sellerService.updateStoreMess(request, mess);
        return new ModelAndView(new RedirectView(request.getContextPath() + SellerConstants.REDIRECTMESS));
    }

    /**
     * 添加消息提示
     * 
     * @param request
     * @param mess
     * @return
     */
    @RequestMapping("/addstoremess")
    public ModelAndView addStoreMess(HttpServletRequest request, ThirdStoreMess mess) {
        sellerService.addStoreMess(request, mess);
        return new ModelAndView(new RedirectView(request.getContextPath() + SellerConstants.REDIRECTMESS));
    }

    /**
     * 跳转协议页面
     * 
     * @param request
     * @return
     */
    @RequestMapping("third/showprotocol")
    public ModelAndView showProtocol(HttpServletRequest request) {
        /*
         * String Useragreementuser =
         * basicSetService.findBasicSet().getBsetUseragreementuser();
         */
        return new ModelAndView(SellerConstants.PROTOCOL).addObject("basicest", basicSetService.findBasicSet());
    }

    /**
     * 跳转经营信息页面
     * 
     * @return
     */
    @RequestMapping("third/engageinfo")
    public ModelAndView engageInfo(HttpServletRequest request, StoreInfo storeInfo) {
        sellerService.updateByStoreInfo(storeInfo, (Long) request.getSession().getAttribute(SellerConstants.CUSTOMERID));
        return new ModelAndView(SellerConstants.ENGAGEINFO).addObject(SellerValueUtil.INFO,
                sellerService.selectByCustomerId((Long) request.getSession().getAttribute(SellerConstants.CUSTOMERID)));
    }

    /**
     * 保存公司经营信息
     * 
     * @param storeInfo
     *            公司信息 {@link StoreInfo}
     * @return
     */
    @RequestMapping("/saveengageinfo")
    public ModelAndView saveEngageinfo(HttpServletRequest request, StoreInfo storeInfo, MultipartHttpServletRequest multipartRequest) {
        // 保存商家公司经营信息
        sellerService.saveStoreInfo(request, storeInfo, multipartRequest);

        return new ModelAndView(new RedirectView(request.getContextPath() + "/aptitude.html"));
    }

    /**
     * 跳转资质页面
     * 
     * @return
     */
    @RequestMapping("third/aptitude")
    public ModelAndView aptitude(HttpServletRequest request) {

        return new ModelAndView(SellerConstants.APTITUDE).addObject(SellerValueUtil.INFO,
                sellerService.selectByCustomerId((Long) request.getSession().getAttribute(SellerConstants.CUSTOMERID)));
    }

    /**
     * 保存公司经营信息
     * 
     * @param storeInfo
     *            公司信息 {@link StoreInfo}
     * @return
     */
    @RequestMapping("/saveaptitude")
    public ModelAndView saveAptitude(HttpServletRequest request, StoreInfo storeInfo, MultipartHttpServletRequest multipartRequest) {
        // 保存商家公司经营信息
        sellerService.saveStoreInfo(request, storeInfo, multipartRequest);

        return new ModelAndView(new RedirectView(request.getContextPath() + "/filltax.html"));
    }

    /**
     * 跳转税务页面
     * 
     * @return
     */
    @RequestMapping("third/filltax")
    public ModelAndView fillTax(HttpServletRequest request) {

        return new ModelAndView(SellerConstants.TAX).addObject(SellerValueUtil.INFO,
                sellerService.selectByCustomerId((Long) request.getSession().getAttribute(SellerConstants.CUSTOMERID)));
    }

    /**
     * 保存公司经营信息
     * 
     * @param storeInfo
     *            公司信息 {@link StoreInfo}
     * @return
     */
    @RequestMapping("/savetax")
    public ModelAndView saveTax(HttpServletRequest request, StoreInfo storeInfo, MultipartHttpServletRequest multipartRequest) {
        // 保存商家公司经营信息
        sellerService.saveStoreInfo(request, storeInfo, multipartRequest);
        return new ModelAndView(new RedirectView(request.getContextPath() + "/wantengageinfo.html"));
    }

    /**
     * 跳转欲经营信息页面
     * 
     * @return
     */
    @RequestMapping("third/wantengageinfo")
    public ModelAndView wantEngageInfo(HttpServletRequest request) {

        return new ModelAndView(SellerConstants.WANTENGAGEINFO)
                .addObject(SellerValueUtil.INFO, sellerService.selectByCustomerId((Long) request.getSession().getAttribute(SellerConstants.CUSTOMERID)))
                .addObject("brandlist", goodsBrandService.queryAllBrand())
                .addObject("apbrand", applyBrandService.selectApplyBrand((Long) request.getSession().getAttribute(SellerConstants.CUSTOMERID)));
    }

    /**
     * 提交店铺审核
     * 
     * @return
     */
    @RequestMapping("third/auditsubmit")
    public ModelAndView auditSubmit(HttpServletRequest request) {

        return new ModelAndView(SellerConstants.AUDITSUBMIT).addObject(SellerValueUtil.INFO,
                sellerService.selectByCustomerId((Long) request.getSession().getAttribute(SellerConstants.CUSTOMERID)));
    }

    /**
     * 保存欲经营信息
     * 
     * @param storeInfo
     *            公司信息 {@link StoreInfo}
     * @return
     */
    @RequestMapping("/savewantengage")
    public ModelAndView saveWantengage(HttpServletRequest request, StoreInfo storeInfo, MultipartHttpServletRequest multipartRequest, String cids, String bids) {
        // 保存商家公司经营信息
        sellerService.saveStoreCateAndBrand(request, sellerService.saveStoreInfo(request, storeInfo, multipartRequest), cids, bids);
        return new ModelAndView(new RedirectView(request.getContextPath() + SellerConstants.REDIRECTAUDITSUBMIT));
    }

    /**
     * 跳轉店鋪功能設置頁面
     * 
     * @param data
     *            頁面渲染數據
     * @param request
     * @return 功能設置頁面
     */
    @RequestMapping("/gofuncsetview")
    public String goFuncSetView(Model data, HttpServletRequest request, String n, String l) {
        // 填充导航/左侧菜单索引 用于页面控制css选中
        MenuOperationUtil.fillSessionMenuIndex(request, n, l);
        Long thirdId = (Long) request.getSession().getAttribute(SellerConstants.THIRDID);
        /* 店鋪基本信息 */
        StoreInfo info = sellerService.selectByStoreId(thirdId);
        data.addAttribute("info", info);
        /* 店铺URL */
        data.addAttribute("storeUrl", ThirdStoreURLUtil.getThirdStoreURL());
        /* 域名 */
        data.addAttribute("domainCustom", domainCustomService.findDomainCustom(thirdId));
        /* 跳转功能设置页面 */
        return "/seller/storeBasicSet";
    }

    /**
     * 修改店铺首页信息
     * 
     * @param storeInfo
     *            店铺信息
     * @param request
     * @return 跳转到店铺功能设置页面
     */
    @RequestMapping("/updateStoreIndexState")
    public String updateStoreIndexState(StoreInfo storeInfo, HttpServletRequest request) {
        storeInfo.setStoreId((Long) request.getSession().getAttribute(THIRDID));
        sellerService.updateStoreIndexState(storeInfo);
        return "redirect:/gofuncsetview.htm";
    }

    /**
     * 修改店铺首页信息(是否开启店铺首页)
     * 
     * @param storeInfo
     *            店铺信息
     * @param request
     * @return 跳转到店铺功能设置页面
     */
    @RequestMapping("/updateStoreIndexStateAlax")
    @ResponseBody
    public int updateStoreIndexStateAjax(StoreInfo storeInfo, HttpServletRequest request, String isStoreIndex) {
        // 是否开始商家首页
        storeInfo.setIsStoreIndex(isStoreIndex);
        // 当前商家Id
        storeInfo.setStoreId((Long) request.getSession().getAttribute(THIRDID));
        // 修改店铺信息
        return sellerService.updateStoreIndexState(storeInfo);

    }

    /**
     * 验证店铺名称是否已存在
     *
     * @param storeName
     *            店铺名称
     * @return 查询出来的行数
     * @author houyichang 2015/9/18
     * */
    @RequestMapping("/queryStoreNameCount")
    @ResponseBody
    public int queryStoreNameCount(String storeName) {
        return this.sellerService.queryCountByStoreName(storeName);
    }

    /**
     * 0:可以通过 1:不允许通过
     * @param storeName
     * @param request
     * @return
     */
    @RequestMapping("/checqpmallNameIsUsing")
    @ResponseBody
    public int checqpmallNameIsUsing(String storeName,HttpServletRequest request) {
        StoreInfo storeInfo=sellerService.selectByStoreName(storeName);
        if(storeInfo==null){
            return 0;
        }else{
            if(storeInfo.getCustomerid().equals(request.getSession().getAttribute(CUSTOMERID))){
                return  0;
            }
            return 1;
        }

    }

    public SellerService getSellerService() {
        return sellerService;
    }

    @Resource(name = "sellerService")
    public void setSellerService(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    public StroreContactService getStroreContactService() {
        return stroreContactService;
    }

    @Resource(name = "StroreContactService")
    public void setStroreContactService(StroreContactService stroreContactService) {
        this.stroreContactService = stroreContactService;
    }

    public GoodsBrandService getGoodsBrandService() {
        return goodsBrandService;
    }

    @Resource(name = "GoodsBrandService")
    public void setGoodsBrandService(GoodsBrandService goodsBrandService) {
        this.goodsBrandService = goodsBrandService;
    }
}
