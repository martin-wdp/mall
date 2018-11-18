/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.m.customer.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.ningpai.common.util.ConstantUtil;
import com.ningpai.customer.bean.CustomerAddress;
import com.ningpai.customer.bean.CustomerFollow;
import com.ningpai.customer.dao.CustomerFollowMapper;
import com.ningpai.customer.service.CustomerFollowServiceMapper;
import com.ningpai.freighttemplate.service.SysProvinceService;
import com.ningpai.goods.bean.ProductWare;
import com.ningpai.goods.dao.GoodsProductMapper;
import com.ningpai.goods.service.ProductWareService;
import com.ningpai.m.customer.service.CustomerAddressService;
import com.ningpai.system.service.DefaultAddressService;
import com.ningpai.util.UploadUtil;
import com.qpmall.address.service.SimpleGetAddressService;
import com.qpmall.m.customer.bean.EnterpriseCertificationInfo;
import com.qpmall.m.customer.service.EnterpriseCertificationInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.ningpai.m.common.service.SeoService;
import com.ningpai.m.customer.service.CustomerService;
import com.ningpai.m.customer.vo.CustomerConstants;
import com.ningpai.m.util.LoginUtil;
import org.springframework.web.servlet.view.RedirectView;

/**
 * 手机端会员控制层
 *
 * @author NINGPAI-zhangqiang
 * @version 0.0.1
 * @since 2014年8月20日 上午10:16:32
 */
@Controller
public class CustomerControllerM {

    // spring 注解 会员service
    private CustomerService customerService;

    @Resource(name = "SeoService")
    private SeoService seoService;
    @Resource(name = "customerFollowServiceMapper")
    private CustomerFollowServiceMapper customerFollowServiceMapper;
    @Resource(name = "customerFollowMapper")
    private CustomerFollowMapper customerFollowMapper;
    @Resource(name = "GoodsProductMapper")
    private GoodsProductMapper goodsProductMapper;
    /**
     * 查询默认地址id
     */
    @Resource(name = "DefaultAddressService")
    private DefaultAddressService defaultAddressService;
    /**
     * 查询分仓库存
     */
    @Resource(name = "ProductWareService")
    private ProductWareService productWareService;

    @Resource(name = "enterpriseCertificationInfoService")
    private EnterpriseCertificationInfoService enterpriseCertificationInfoService;

    @Resource(name = "SysProvinceService")
    private SysProvinceService sysProvinceService;

    @Resource(name = "simpleGetAddressService")
    private SimpleGetAddressService simpleGetAddressService;

    @Resource(name = "customerAddressServiceM")
    private CustomerAddressService addressService;

    /**
     * 会员中心
     *
     * @return {@link ModelAndView}
     */
    @RequestMapping("/customer/indexm")
    public ModelAndView index(HttpServletRequest request) {
        // 跳转个人中心
        ModelAndView mav = null;
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            // 验证登录
            if (LoginUtil.checkLoginStatus(request)) {
                request.getSession().setAttribute("cust", customerService.selectByPrimaryKey((Long) request.getSession().getAttribute(CustomerConstants.CUSTOMERID)));
                mav = new ModelAndView(CustomerConstants.CUSTOMERINDEX);
                mav.addAllObjects(resultMap);
            } else {
                mav = new ModelAndView("redirect:/login.html?url=/customer/index.html?tag=4");
            }
            mav.addObject(ConstantUtil.PAGENAME,"个人中心");
            return seoService.getCurrSeo(mav);
        } finally {
            mav = null;
            resultMap = null;
        }
    }

    /**
     * 查看我的收藏
     *
     * @throws IOException
     */
    @RequestMapping("customer/myfollw")
    public ModelAndView myfollw(HttpServletRequest request) throws IOException {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        ModelAndView mav;
        // 验证登录
        if (LoginUtil.checkLoginStatus(request)) {
            request.getSession().setAttribute("cust", customerService.selectByPrimaryKey((Long) request.getSession().getAttribute(CustomerConstants.CUSTOMERID)));
            // 查询收藏记录
            mav = new ModelAndView("customer/newfollow");
            List<CustomerFollow> customerFollows =
                    customerFollowServiceMapper.selectByCustomerId((Long) request.getSession().getAttribute(CustomerConstants.CUSTOMERID));
            for (CustomerFollow customerFollow : customerFollows) {
                //根据货品ID获取 该货品的评分
                customerFollow.setUtilBean(customerFollowMapper.queryCommentCountAndScoreByProductId(customerFollow.getGoodsId()));
                /**
                 * 下面1,2,3条是在前台点击收藏的商品时使用的 houyichag 2015/9/29
                 *
                 * */
                // 1.查询出后台设置的默认地区
                Long distinctId = this.defaultAddressService.getDefaultIdService();
                // 2.根据默认地区以及货品id去查询改货品的分仓库存
                ProductWare productWare = this.productWareService.queryProductWareByProductIdAndDistinctId(customerFollow.getGoodsId(), distinctId);
                // 3.对customerFollows中的goodBean对象中的库存进行赋值
                customerFollow.getGood().setGoodStock(productWare.getWareStock());
            }
            mav.addObject("myfollwList", customerFollows);
            mav.addObject(ConstantUtil.BACKLEVURL,"customer/index.html?tag=4");
            mav.addObject(ConstantUtil.PAGENAME,"我的收藏");
        } else {
            mav = new ModelAndView("redirect:/login.html?url=/customer/index.html?tag=4");
        }
        return seoService.getCurrSeo(mav);
    }

    /**
     * 取消关注
     *
     * @param request
     * @param followId
     *            关注编号
     * @return ModelAndView
     */
    @RequestMapping("/cancelfollow")
    public ModelAndView cancelFollow(HttpServletRequest request, Long followId) {
        ModelAndView mav;

        // 验证登录
        if (LoginUtil.checkLoginStatus(request)) {
            // 用户Id
            Long customerId = (Long) request.getSession().getAttribute(CustomerConstants.CUSTOMERID);
            // 取消关注
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("followId",followId);
            param.put("customerId",customerId);
            goodsProductMapper.deleteFolloById(param);
            mav = new ModelAndView(new RedirectView(request.getContextPath() + "/customer/myfollw.htm"));
        } else {
            mav = new ModelAndView("redirect:/login.html?url=/customer/index.html?tag=4");
        }
        return seoService.getCurrSeo(mav);
    }

    /**
     * 发送手机验证码
     *
     * @throws IOException
     */
    @RequestMapping("/sendcode")
    @ResponseBody
    public int sendcode(HttpServletRequest request, String moblie ,@RequestParam(defaultValue = "1")String type) throws IOException {
        if("".equals(type)){
            type = "1" ;
        }
        return customerService.sendPost(request,moblie,type);
    }


    /**
     * 跳转手机用户认证页面/认证信息浏览页面
     * @return
     */
    @RequestMapping("/customer/toAttestation")
    public ModelAndView toAttestation(HttpServletRequest request){
        ModelAndView mav = null;
        //得到enterpriseCertificationInfo的customerId数据
        if (!LoginUtil.checkLoginStatus(request)) {
            mav = new ModelAndView("redirect:/login.html?url=/customer/index.html?tag=4");
            return seoService.getCurrSeo(mav);
        }
        Long customerId = (Long) request.getSession().getAttribute(CustomerConstants.CUSTOMERID);
        mav = new ModelAndView("customer/suppliers");
        mav.addObject(ConstantUtil.BACKLEVURL,"customer/toAccountManage.html");
        EnterpriseCertificationInfo enterpriseInfo =
                enterpriseCertificationInfoService.selectuserEnperseByCustomerId((Long) request.getSession().getAttribute("customerId"));
        if (enterpriseInfo != null) {
            mav.addObject("submitFlag", "2");
            mav.addObject(ConstantUtil.PAGENAME,"我的认证信息");
            mav.addObject("enter", enterpriseInfo);
            return seoService.getCurrSeo(mav);
        }else {
            mav.addObject("submitFlag", "0");
            mav.addObject(ConstantUtil.PAGENAME,"提交认证信息");
        }
        List<EnterpriseCertificationInfo> enterpriseCertificationInfos = enterpriseCertificationInfoService.selectEnterpriseCertificationByCustomerId((Long) request.getSession().getAttribute("customerId"));
        if (enterpriseCertificationInfos != null && enterpriseCertificationInfos.size() > 0) {
            EnterpriseCertificationInfo enterpriseCertificationInfo = enterpriseCertificationInfos.get(0);
                    Long proviceId = Long.valueOf(enterpriseCertificationInfo.getEnterpriseProvince());
            if(simpleGetAddressService.selectProvinceByPid(enterpriseCertificationInfo.getEnterpriseProvince()) != null ){
                enterpriseCertificationInfo.setEnterpriseProvinceName(simpleGetAddressService.selectProvinceByPid(enterpriseCertificationInfo.getEnterpriseProvince()).getAddressName());
            }
            if(simpleGetAddressService.selectCityByCid(enterpriseCertificationInfo.getEnterpriseCity()) != null ){
                enterpriseCertificationInfo.setEnterpriseCityName(simpleGetAddressService.selectCityByCid(enterpriseCertificationInfo.getEnterpriseCity()).getAddressName());
            }
            if(simpleGetAddressService.selectDistrictById(enterpriseCertificationInfo.getEnterpriseCounty()) != null ){
                enterpriseCertificationInfo.setEnterpriseCountyName(simpleGetAddressService.selectDistrictById(enterpriseCertificationInfo.getEnterpriseCounty()).getAddressName());
            }
            enterpriseCertificationInfo.setCompanyPicUrl((enterpriseCertificationInfo.getCompanyPicUrl()!=null&&enterpriseCertificationInfo.getCompanyPicUrl().indexOf("##")!=-1)?
                    enterpriseCertificationInfo.getCompanyPicUrl().split("##")[0]:"");
            return seoService.getCurrSeo(
                    mav.addObject("enter", enterpriseCertificationInfo));
        }
        EnterpriseCertificationInfo enterpriseCertificationInfo = new EnterpriseCertificationInfo();
        mav.addObject("enter", enterpriseCertificationInfo);
        return seoService.getCurrSeo(mav);
    }
    /**
     * 上传单张图片 保存原图
     *
     * @param request
     *            请求对象
     * @param resp
     *            响应对象
     * @throws IOException
     */
    @RequestMapping("/uploadImgSingleM")
    @ResponseBody
    public String  uploadImgSingleM(MultipartHttpServletRequest request,
                                HttpServletResponse resp) throws IOException {
        //PrintWriter out = resp.getWriter();
        // 定义一个字符串变量初始值为Null
       // String msg = null;
        // 获取页面中name为specImg的file文件流
        MultipartFile file = request.getFile("specImg");
        // 获取资源文件的名称
        file.getOriginalFilename();
        // 判断资源文件是否属于图片资源
        // 如果不是msg就赋值为111
        // 如果是就上传资源文件
        String imgUrl = "";
        if (!checkExtendsName(file.getOriginalFilename())) {
            imgUrl = "111";
        } else {
            imgUrl =  UploadUtil.uploadFile(file, request).get("0");
        }
       // out.append("<script>eval(parent.specImgSucc('" + imgUrl + "'));</script>");
        return imgUrl;
    }

    /**
     * 检查手机验证码
     *
     * @return 0不同 1相同
     * @throws IOException
     */
    @RequestMapping("validate/getMCode")
    @ResponseBody
    public int getMCode(HttpServletRequest request, String code,String mobile) throws IOException {
        return customerService.getMCode(request, code,mobile);
    }


    /**
     * 保存认证信息
     *
     * @param request
     * @param enterpriseCertificationInfo
     * @return
     */
    @RequestMapping(value = "/saveEnCertInfo")
    public ModelAndView saveEnCertInfo(HttpServletRequest request,
                                       String addDefault,
                                       MultipartHttpServletRequest mutirequest,
                                       @Valid EnterpriseCertificationInfo enterpriseCertificationInfo) { //其他信息
        ModelAndView mav = null;
        if (!LoginUtil.checkLoginStatus(request)) {
            mav = new ModelAndView("redirect:/login.html?url=/customer/index.html?tag=4");
            return seoService.getCurrSeo(mav);
        }
        mav = new ModelAndView("customer/suppliersresult");
        mav.addObject(ConstantUtil.PAGENAME,"提交认证结果");
        mav.addObject("jumpUrl", "customer/toAttestation.htm");
        Long customerId = (Long) request.getSession().getAttribute(CustomerConstants.CUSTOMERID);
        if (enterpriseCertificationInfo.getCustomerid() == null || "".equals(enterpriseCertificationInfo.getCustomerid())) {
            enterpriseCertificationInfo.setCustomerid(customerId);
        }
        if (enterpriseCertificationInfoService.selectuserEnperseByCustomerId(enterpriseCertificationInfo.getCustomerid()) != null) {
            mav.addObject("submitFlag", "0");
            mav.addObject("jumpName", "查看我的认证信息");
            return seoService.getCurrSeo(mav.addObject("enInfo", "您已经提交认证信息。已通过审核或者是等待审核状态下不能再次提交认证信息！"));
        }
        //处理文件上传
        String company_pic = "";
        //
        if (mutirequest.getFile("pic1") != null && !"".equals(mutirequest.getFile("pic1").getOriginalFilename())) {
            // 使用上传后路径
            company_pic = UploadUtil.uploadFile(mutirequest.getFile("pic1"), request).get("oldimg") + "##";
        }else if (enterpriseCertificationInfo.getCompanyPicUrl() != null && !"".equals(enterpriseCertificationInfo.getCompanyPicUrl())) {
            company_pic = enterpriseCertificationInfo.getCompanyPicUrl() + "##";
        }
        /*if (mutirequest.getFile("company_pic2") != null && !"".equals(mutirequest.getFile("company_pic2").getOriginalFilename())) {
            // 使用上传后路径
            if (company_pic.length() > 0) {
                company_pic = company_pic + UploadUtil.uploadFile(mutirequest.getFile("company_pic2"), request).get("oldimg") + "##";
            } else {
                company_pic = UploadUtil.uploadFile(mutirequest.getFile("company_pic2"), request).get("oldimg") + "##";
            }
        }else if (company_pic2init != null && !"".equals(company_pic2init)) {
            if (company_pic.length() > 0) {
                company_pic = company_pic + company_pic3init + "##";
            } else {
                company_pic = company_pic3init + "##";
            }
        }
        if (mutirequest.getFile("company_pic3") != null && !"".equals(mutirequest.getFile("company_pic3").getOriginalFilename())) {

            // 使用上传后路径
            if (company_pic.length() > 0) {
                company_pic = company_pic + "##" + UploadUtil.uploadFile(mutirequest.getFile("company_pic3"), request).get("oldimg");
            } else {
                company_pic = UploadUtil.uploadFile(mutirequest.getFile("company_pic3"), request).get("oldimg");
            }
        } else  if (company_pic3init != null && !"".equals(company_pic3init)) {
            if (company_pic.length() > 0) {
                company_pic = company_pic + "##" + company_pic3init;
            } else {
                company_pic = company_pic3init;
            }
        }*/

        if (company_pic.length() == 0) {
            mav.addObject("submitFlag", "0");
            mav.addObject("jumpName", "重新编辑认证信息");
            return seoService.getCurrSeo(mav.addObject("enInfo", "门头照片不能为空！"));
        }
        if (enterpriseCertificationInfo.getCompanyType() == null || "".equals(enterpriseCertificationInfo.getCompanyType())) {
            mav.addObject("submitFlag", "0");
            mav.addObject("jumpName", "重新编辑认证信息");
            return seoService.getCurrSeo(mav.addObject("enInfo", "请选择企业类型!"));
        }
        boolean flags = true;
        if ((enterpriseCertificationInfo.getBusscardUrl() == null || "".equals(enterpriseCertificationInfo.getBusscardUrl())) &&
                (mutirequest.getFile("pic5") == null || "".equals(mutirequest.getFile("pic5").getOriginalFilename()))) {
            mav.addObject("submitFlag", "0");
            mav.addObject("jumpName", "重新编辑认证信息");
             return seoService.getCurrSeo(mav.addObject("enInfo", "请请上传证件照片！"));

        }
        if ((enterpriseCertificationInfo.getBussDeptNoUrl() == null || "".equals(enterpriseCertificationInfo.getBussDeptNoUrl())) &&
                (mutirequest.getFile("pic6") == null || "".equals(mutirequest.getFile("pic6").getOriginalFilename()))) {
            mav.addObject("submitFlag", "0");
            mav.addObject("jumpName", "重新编辑认证信息");
            return seoService.getCurrSeo(mav.addObject("enInfo", "请请上传证件照片！"));

        }
        if ((enterpriseCertificationInfo.getBussTaxRegistUrl() == null || "".equals(enterpriseCertificationInfo.getBussTaxRegistUrl())) &&
                (mutirequest.getFile("pic7") == null || "".equals(mutirequest.getFile("pic7").getOriginalFilename()))) {
            mav.addObject("submitFlag", "0");
            mav.addObject("jumpName", "重新编辑认证信息");
            return seoService.getCurrSeo(mav.addObject("enInfo", "请请上传证件照片！"));

        }
        enterpriseCertificationInfo.setBusscardUrl((enterpriseCertificationInfo.getBusscardUrl() == null || "".equals(enterpriseCertificationInfo.getBusscardUrl())) ?
                UploadUtil.uploadFile(mutirequest.getFile("pic5"), request).get("oldimg") : enterpriseCertificationInfo.getBusscardUrl());
        enterpriseCertificationInfo.setBussDeptNoUrl((enterpriseCertificationInfo.getBussDeptNoUrl() == null || "".equals(enterpriseCertificationInfo.getBussDeptNoUrl())) ?
                UploadUtil.uploadFile(mutirequest.getFile("pic6"), request).get("oldimg") : enterpriseCertificationInfo.getBussDeptNoUrl());
        enterpriseCertificationInfo.setBussTaxRegistUrl((enterpriseCertificationInfo.getBussTaxRegistUrl() == null || "".equals(enterpriseCertificationInfo.getBussTaxRegistUrl())) ?
                UploadUtil.uploadFile(mutirequest.getFile("pic7"), request).get("oldimg") : enterpriseCertificationInfo.getBusscardUrl());
        if (enterpriseCertificationInfo.getCompanyName() == null || "".equals(enterpriseCertificationInfo.getCompanyName())) {
            mav.addObject("submitFlag", "0");
            mav.addObject("jumpName", "重新编辑认证信息");
            return seoService.getCurrSeo(mav.addObject("enInfo", "请填写企业名称！"));
        }
        enterpriseCertificationInfo.setCompanyPicUrl(company_pic);
        /*enterpriseCertificationInfo.setCompany_pic_url(company_pic);
        // 使用上传后路径
        enterpriseCertificationInfo.setBusscard_url(UploadUtil.uploadFile(mutirequest.getFile("buss_url"), request).get("oldimg"));
        // 使用上传后路径
        enterpriseCertificationInfo.setBuss_dept_no_url(UploadUtil.uploadFile(mutirequest.getFile("buss_dept_no"), request).get("oldimg"));
        // 使用上传后路径
        enterpriseCertificationInfo.setBuss_tax_regist_url(UploadUtil.uploadFile(mutirequest.getFile("buss_tax_regist"), request).get("oldimg"));*/

        if (enterpriseCertificationInfo.getEnterpriseProvince() == null || "".equals(enterpriseCertificationInfo.getEnterpriseProvince()) ||
                enterpriseCertificationInfo.getEnterpriseCity() == null || "".equals(enterpriseCertificationInfo.getEnterpriseCity()) ||
                enterpriseCertificationInfo.getEnterpriseCounty() == null || "".equals(enterpriseCertificationInfo.getEnterpriseCounty())) {
            mav.addObject("submitFlag", "0");
            mav.addObject("jumpName", "重新编辑认证信息");
            return seoService.getCurrSeo(mav.addObject("enInfo", "地址不详细!请选择详细地址."));
        }

        String address = "";
        Long proviceId = Long.valueOf(enterpriseCertificationInfo.getEnterpriseProvince());
        address = sysProvinceService.selectProvinceById(proviceId) == null ? "" : sysProvinceService.selectProvinceById(proviceId).getProvinceName();
        enterpriseCertificationInfo.setEnterpriseProvinceName(sysProvinceService.selectProvinceById(proviceId).getProvinceName());
        address=address+",";
        Long cityId = Long.valueOf(enterpriseCertificationInfo.getEnterpriseCity());
        address = address + (simpleGetAddressService.selectCityByCid(cityId) == null ? "" : simpleGetAddressService.selectCityByCid(cityId).getAddressName());
        enterpriseCertificationInfo.setEnterpriseCityName(simpleGetAddressService.selectCityByCid(cityId).getAddressName());
        address=address+",";
        Long districtId = Long.valueOf(enterpriseCertificationInfo.getEnterpriseCounty());
        address = address + (simpleGetAddressService.selectDistrictById(districtId) == null ? "" : simpleGetAddressService.selectDistrictById(districtId).getAddressName());
        enterpriseCertificationInfo.setEnterpriseCountyName(simpleGetAddressService.selectDistrictById(districtId).getAddressName());
        //address=address+",";
        enterpriseCertificationInfo.setCompanyAddress(address);
        //保存企业认证信息
        enterpriseCertificationInfo.setCreateTime(new Date(System.currentTimeMillis()));
        enterpriseCertificationInfo.setModTime(new Date(System.currentTimeMillis()));
        //将地址和企业相关信息添加为用户默认收货地址收货人为企业名称
        if(addDefault !=null && "1".equals(addDefault)){
            CustomerAddress customerAddress = new CustomerAddress();
            customerAddress.setAddressName(enterpriseCertificationInfo.getCompanyName());
            customerAddress.setAddressDetail(enterpriseCertificationInfo.getCompanyContactAddr());
            customerAddress.setAddressMoblie(enterpriseCertificationInfo.getCompany_contact_moble());
            customerAddress.setInfoProvince(enterpriseCertificationInfo.getEnterpriseProvince()+"");
            customerAddress.setInfoCity(enterpriseCertificationInfo.getEnterpriseCity()+"");
            customerAddress.setInfoCounty(enterpriseCertificationInfo.getEnterpriseCounty()+"");
            // 1为使用
            customerAddress.setIsDefault("1");
            // 将所有的地址都更改为默认状态0
            addressService.updateAddressDef(customerId);
            addressService.addAddress(customerAddress, customerId);
        }
        int re = enterpriseCertificationInfoService.saveEnterpriseCertificationInfo(enterpriseCertificationInfo);
        if (re == 1) {
            mav.addObject("submitFlag", "1");
            mav.addObject("jumpName", "查看我的认证信息");
            return seoService.getCurrSeo(mav.addObject("enInfo", "客服将在24小时内完成认证,请耐心等待。").addObject("enter",enterpriseCertificationInfo));
        } else {
            mav.addObject("submitFlag", "0");
            mav.addObject("jumpName", "重新提交认证信息");
            return seoService.getCurrSeo(mav.addObject("enInfo", "提交企业认证信息失败"));
        }

    }
    /**
     * 检查文件扩展名是否为图片
     *
     * @param fileName
     *            上传的文件的文件名
     * @return
     */
    private boolean checkExtendsName(String fileName) {
        if (fileName.indexOf(".") < 0) {
            return false;
        }
        String extend = fileName.substring(fileName.lastIndexOf(".") + 1);
        String[] extendNames = { "jpg", "jpeg", "bmp", "png", "gif" };
        for (String extendName : extendNames) {
            if (extend.equals(extendName)) {
                return true;
            }
        }
        return false;
    }

    public CustomerService getCustomerService() {
        return customerService;
    }

    @Resource(name = "customerServiceM")
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

}
