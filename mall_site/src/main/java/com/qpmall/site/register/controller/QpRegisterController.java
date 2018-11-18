/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.qpmall.site.register.controller;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.ningpai.customer.service.CustomerServiceMapper;
import com.ningpai.freighttemplate.service.SysProvinceService;
import com.ningpai.other.bean.CityBean;
import com.ningpai.other.bean.CustomerAllInfo;
import com.ningpai.other.bean.DistrictBean;
import com.ningpai.other.bean.ProvinceBean;
import com.ningpai.other.util.IPAddress;
import com.ningpai.system.service.BasicSetService;
import com.ningpai.system.service.SysBasicService;
import com.ningpai.util.MyLogger;

import com.ningpai.util.UploadUtil;
import com.qpmall.site.register.bean.QpEnterpriseCertificationInfo;
import com.qpmall.site.register.service.QpEnterpriseCertificationInfoService;
import com.qpmall.site.register.service.RegisterService;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ningpai.index.service.TopAndBottomService;
import com.ningpai.system.service.AuthService;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 * 前台注册Controller
 *
 * @author luyong
 * @serialDate 2015-09-21
 */
@Controller
//@RequestMapping(value="/register")
public class QpRegisterController {


    private RegisterService registerService;

    @Resource(name = "basicSetService")
    private BasicSetService basicSetService;
    @Resource(name = "qpEnterpriseCertificationInfoService")
    private QpEnterpriseCertificationInfoService qpEnterpriseCertificationInfoService;
    @Resource(name = "SysProvinceService")
    private SysProvinceService sysProvinceService;
    @Resource(name = "TopAndBottomService")
    private TopAndBottomService topAndBottomService;




    /**
     * 记录日志对象
     */
    private static final MyLogger LOGGER = new MyLogger(QpRegisterController.class);

    /**
     * 注册发送手机验证码日志信息
     *
     * @author luyong
     * @serialData 2015-09-21
     */
    private static final String LOGGERINFO4 = "发送手机验证码,手机号码为：";
    /**
     * 用户
     */
    private CustomerServiceMapper customerServiceMapper;


    /**
     * 注册发送手机验证码
     *
     * @param request
     * @param mobile
     * @return
     * @throws java.io.IOException
     * @author luyong
     * @serialData 2015-09-21
     */
    @RequestMapping("/newRegisterSendcode")
    @ResponseBody
    public int registerSendcode(HttpServletRequest request,   //HttpServletRequest
                                @RequestParam(required = true) String mobile)//手机号码
            throws IOException {

        return registerService.sendPost(request, mobile);

    }

    /**
     * 跳转企业认证的ui界面
     *
     * @return
     */
    @RequestMapping(value = "/toValidateProtocol")
    public ModelAndView toValidateProtocol(HttpServletRequest request) {
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
        if ((Long) request.getSession().getAttribute("customerId") == null || "".equals((Long) request.getSession().getAttribute("customerId"))) {
            return topAndBottomService.getTopAndBottom(new ModelAndView(new RedirectView("login.html")));
        }
        if (qpEnterpriseCertificationInfoService.selectuserEnperseByCustomerId((Long) request.getSession().getAttribute("customerId")) != null) {

            return topAndBottomService.getTopAndBottom(new ModelAndView(new RedirectView("showEnpriseInfo.htm")));
        }
        List<QpEnterpriseCertificationInfo> qpEnterpriseCertificationInfos = qpEnterpriseCertificationInfoService.selectEnterpriseCertificationByCustomerId((Long) request.getSession().getAttribute("customerId"));
        if (qpEnterpriseCertificationInfos != null && qpEnterpriseCertificationInfos.size() > 0) {
            return topAndBottomService.getTopAndBottom(
                    new ModelAndView("/qpmalenterprise/authentication/qp_authentication")
                            .addObject("parameter", basicSetService.findBasicSet())
                            .addObject("qpEnterpriseCertificationInfo", qpEnterpriseCertificationInfos.get(0)));
        }
        return topAndBottomService.getTopAndBottom(new ModelAndView("/qpmalenterprise/authentication/qp_authentication")
                .addObject("parameter", basicSetService.findBasicSet()));
    }


    /**
     * 查询所有省份
     *
     * @return List<ProvinceBean> 省份集合
     * @see {@link com.ningpai.other.bean.ProvinceBean}
     */
    @RequestMapping("/getQPAllProvince")
    @ResponseBody
    public List<ProvinceBean> getQPAllProvince() {
        return customerServiceMapper.selectAllProvince();
    }

    /**
     * 查询所有城市
     *
     * @return List<ProvinceBean> 省份集合
     * @see {@link com.ningpai.other.bean.ProvinceBean}
     */
    @RequestMapping("/getQPAllCities")
    @ResponseBody
    public List<CityBean> getQPAllCities() {
        return customerServiceMapper.selectAllCity();
    }

    /**
     * 查询所有区
     *
     * @return List<ProvinceBean> 省份集合
     * @see {@link com.ningpai.other.bean.ProvinceBean}
     */
    @RequestMapping("/getQPAllDistrict")
    @ResponseBody
    public List<DistrictBean> getQPAllDistrict() {
        return customerServiceMapper.selectAllDistrict();
    }

    /**
     * 根据省份编号 查询所有城市
     *
     * @param provinceId 省份编号 java.lang.Long {@link java.lang.Long}
     * @return
     */
    @RequestMapping("/getQPAllCityByPid")
    @ResponseBody
    public List<CityBean> getQPAllCityByPid(Long provinceId) {
        QpEnterpriseCertificationInfo qpEnterpriseCertificationInfo
                = new QpEnterpriseCertificationInfo();
        return customerServiceMapper.selectAllCityByPid(provinceId);
    }

    /**
     * 根据城市编号 查询所有区县
     *
     * @param cityId 城市编号 java.lang.Long {@link java.lang.Long}
     * @return
     */
    @RequestMapping("/getQPAllDistrictByCid")
    @ResponseBody
    public List<DistrictBean> getQPAllDistrictByCid(Long cityId) {
        return customerServiceMapper.selectAllDistrictByCid(cityId);
    }

    /**
     * 保存认证信息
     *
     * @param request
     * @param qpEnterpriseCertificationInfo
     * @return
     */
    @RequestMapping(value = "/saveEnCertInfo")
    public ModelAndView saveEnCertInfo(HttpServletRequest request,
                                       String company_pic1init,
                                       String company_pic2init,
                                       String company_pic3init,
                                       String buss_urlinit,
                                       String buss_tax_registinit,
                                       String buss_dept_noinit,
                                       MultipartHttpServletRequest mutirequest,
                                       @Valid QpEnterpriseCertificationInfo qpEnterpriseCertificationInfo) { //其他信息
        ModelAndView mav = new ModelAndView("/qpmalenterprise/authentication/qp_authenticationResult");
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
        //得到qpEnterpriseCertificationInfo的customerId数据
        if (qpEnterpriseCertificationInfo.getCustomerId() == null || "".equals(qpEnterpriseCertificationInfo.getCustomerId())) {
            qpEnterpriseCertificationInfo.setCustomerId((Long) request.getSession().getAttribute("customerId"));
        }
        if (qpEnterpriseCertificationInfo.getCustomerId() == null || "".equals(qpEnterpriseCertificationInfo.getCustomerId())) {
            LOGGER.info("未登录状态不能进行企业认证！");
            mav.addObject("nextURL", "login.html");
            mav.addObject("urlname", "返回去登录");
            return topAndBottomService.getTopAndBottom(mav.addObject("enInfo", "未登录状态不能进行企业认证"));
        }
        if (qpEnterpriseCertificationInfoService.selectuserEnperseByCustomerId(qpEnterpriseCertificationInfo.getCustomerId()) != null) {
            mav.addObject("nextURL", "customer/index.html");
            mav.addObject("urlname", "返回个人中心");
            return topAndBottomService.getTopAndBottom(mav.addObject("enInfo", "您已经提交认证信息。已通过审核或者是等待审核状态下不能再次提交认证信息！"));
        }
        LOGGER.info("提交认证信息！认证用户：" + qpEnterpriseCertificationInfo.getCustomerId() + "。");
        //处理文件上传
        String company_pic = "";
        //
        if (mutirequest.getFile("company_pic1") != null && !"".equals(mutirequest.getFile("company_pic1").getOriginalFilename())) {
            // 使用上传后路径
            company_pic = UploadUtil.uploadFile(mutirequest.getFile("company_pic1"), request).get("oldimg") + "##";
        }else if (company_pic1init != null && !"".equals(company_pic1init)) {
            company_pic = company_pic1init + "##";
        }
        if (mutirequest.getFile("company_pic2") != null && !"".equals(mutirequest.getFile("company_pic2").getOriginalFilename())) {
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
        }

        if (company_pic.length() == 0) {
            LOGGER.info("门头照片不能为空！");
            mav.addObject("nextURL", "toValidateProtocol.htm");
            mav.addObject("urlname", "返回重新提交认证");
            return topAndBottomService.getTopAndBottom(mav.addObject("enInfo", "门头照片不能为空！"));
        }
        if (qpEnterpriseCertificationInfo.getCompany_type() == null || "".equals(qpEnterpriseCertificationInfo.getCompany_type())) {
            LOGGER.info("没有选择企业类型");
            mav.addObject("nextURL", "toValidateProtocol.htm");
            mav.addObject("urlname", "返回重新提交认证");
            return topAndBottomService.getTopAndBottom(mav.addObject("enInfo", "请选择企业类型!"));
        }
        boolean flags = true;
        if ((buss_urlinit == null || "".equals(buss_urlinit)) &&
                (mutirequest.getFile("buss_url") == null || "".equals(mutirequest.getFile("buss_url").getOriginalFilename()))) {
            mav.addObject("nextURL", "toValidateProtocol.htm");
            mav.addObject("urlname", "返回重新提交认证");
            return topAndBottomService.getTopAndBottom(mav.addObject("enInfo", "请请上传证件照片！"));

        }
        if ((buss_dept_noinit == null || "".equals(buss_dept_noinit)) &&
                (mutirequest.getFile("buss_dept_no") == null || "".equals(mutirequest.getFile("buss_dept_no").getOriginalFilename()))) {
            mav.addObject("nextURL", "toValidateProtocol.htm");
            mav.addObject("urlname", "返回重新提交认证");
            return topAndBottomService.getTopAndBottom(mav.addObject("enInfo", "请请上传证件照片！"));

        }
        if ((buss_tax_registinit == null || "".equals(buss_tax_registinit)) &&
                (mutirequest.getFile("buss_tax_regist") == null || "".equals(mutirequest.getFile("buss_tax_regist").getOriginalFilename()))) {
            mav.addObject("nextURL", "toValidateProtocol.htm");
            mav.addObject("urlname", "返回重新提交认证");
            return topAndBottomService.getTopAndBottom(mav.addObject("enInfo", "请请上传证件照片！"));

        }
        qpEnterpriseCertificationInfo.setBusscard_url((buss_urlinit == null || "".equals(buss_urlinit)) ?
                UploadUtil.uploadFile(mutirequest.getFile("buss_url"), request).get("oldimg") : buss_urlinit);
        qpEnterpriseCertificationInfo.setBuss_dept_no_url((buss_dept_noinit == null || "".equals(buss_dept_noinit)) ?
                UploadUtil.uploadFile(mutirequest.getFile("buss_dept_no"), request).get("oldimg") : buss_dept_noinit);
        qpEnterpriseCertificationInfo.setBuss_tax_regist_url((buss_tax_registinit == null || "".equals(buss_tax_registinit)) ?
                UploadUtil.uploadFile(mutirequest.getFile("buss_tax_regist"), request).get("oldimg") : buss_urlinit);


        if (qpEnterpriseCertificationInfo.getCompany_name() == null || "".equals(qpEnterpriseCertificationInfo.getCompany_name())) {
            LOGGER.info("没有填写企业名称！");
            mav.addObject("urlname", "返回重新提交认证");
            mav.addObject("nextURL", "toValidateProtocol.htm");
            return topAndBottomService.getTopAndBottom(mav.addObject("enInfo", "请填写企业名称！"));
        }
        qpEnterpriseCertificationInfo.setCompany_pic_url(company_pic);
        /*qpEnterpriseCertificationInfo.setCompany_pic_url(company_pic);
        // 使用上传后路径
        qpEnterpriseCertificationInfo.setBusscard_url(UploadUtil.uploadFile(mutirequest.getFile("buss_url"), request).get("oldimg"));
        // 使用上传后路径
        qpEnterpriseCertificationInfo.setBuss_dept_no_url(UploadUtil.uploadFile(mutirequest.getFile("buss_dept_no"), request).get("oldimg"));
        // 使用上传后路径
        qpEnterpriseCertificationInfo.setBuss_tax_regist_url(UploadUtil.uploadFile(mutirequest.getFile("buss_tax_regist"), request).get("oldimg"));*/

        if (qpEnterpriseCertificationInfo.getEnterprise_province() == null || "".equals(qpEnterpriseCertificationInfo.getEnterprise_province()) ||
                qpEnterpriseCertificationInfo.getEnterprise_city() == null || "".equals(qpEnterpriseCertificationInfo.getEnterprise_city()) ||
                qpEnterpriseCertificationInfo.getEnterprise_county() == null || "".equals(qpEnterpriseCertificationInfo.getEnterprise_county())) {
            LOGGER.info("地址不详细!");
            mav.addObject("urlname", "返回重新提交认证");
            mav.addObject("nextURL", "toValidateProtocol.htm");
            return topAndBottomService.getTopAndBottom(mav.addObject("enInfo", "地址不详细!请选择详细地址."));
        }

        String address = "";
        Long proviceId = Long.valueOf(qpEnterpriseCertificationInfo.getEnterprise_province());
        address = sysProvinceService.selectProvinceById(proviceId) == null ? "" : sysProvinceService.selectProvinceById(proviceId).getProvinceName();
        address=address+",";
        Long cityId = Long.valueOf(qpEnterpriseCertificationInfo.getEnterprise_city());
        address = address + (qpEnterpriseCertificationInfoService.selectCityByPid(cityId) == null ? "" : qpEnterpriseCertificationInfoService.selectCityByPid(cityId).getCityName());
        address=address+",";
        Long districtId = Long.valueOf(qpEnterpriseCertificationInfo.getEnterprise_county());
        address = address + (qpEnterpriseCertificationInfoService.selectDistrictByCid(districtId) == null ? "" : qpEnterpriseCertificationInfoService.selectDistrictByCid(districtId).getDistrictName());
        //address=address+",";
        qpEnterpriseCertificationInfo.setCompany_address(address);
        //保存企业认证信息
        qpEnterpriseCertificationInfo.setCreate_time(new Date(System.currentTimeMillis()));
        qpEnterpriseCertificationInfo.setMod_time(new Date(System.currentTimeMillis()));
        int re = qpEnterpriseCertificationInfoService.saveQpEnterpriseCertificationInfo(qpEnterpriseCertificationInfo);
        if (re == 1) {
            LOGGER.info("用户ID:" + qpEnterpriseCertificationInfo.getCustomerId() + "提交企业认证信息成功！");
            mav.addObject("nextURL", "showEnpriseInfo.htm");
            mav.addObject("urlname", "去个人中心查看认证信息");
            return topAndBottomService.getTopAndBottom(mav.addObject("enInfo", "客服将在24小时内完成认证，\n" +
                    "                    请耐心等待。"));
        } else {
            LOGGER.info("用户ID:" + qpEnterpriseCertificationInfo.getCustomerId() + "提交企业认证信息失败！");
            mav.addObject("nextURL", "toValidateProtocol.htm");
            mav.addObject("urlname", "返回重新提交认证");
            return topAndBottomService.getTopAndBottom(mav.addObject("enInfo", "提交企业认证信息失败"));
        }

    }

    @RequestMapping("/checkpatchcaForRegister")
    @ResponseBody
    public int checkpatchcaForRegister(HttpServletRequest req, String enterValue, String mobile) {
        // 获取sessoin中的验证码
        String codeMobileNum = mobile + "codeMobileNum";
        req.getSession().getAttribute("");
        String enterValuevalue = (Integer) req.getSession().getAttribute(codeMobileNum) + "";
        if (null != enterValue) {
            // 验证页面传过来的验证码是否与session保存的验证码相等
            return enterValue.equals(enterValuevalue) ? 1 : 0;
        }
        return 0;
    }


    /**
     * 检查登陆状态
     *
     * @param request
     * @return
     * @author luyong
     * @serialData 2015-09-21
     */

    private boolean checkLoginStatus(HttpServletRequest request) {
        if (request.getSession().getAttribute("cust") == null && request.getSession().getAttribute("user") == null) {
            return false;
        }
        return true;
    }


    public RegisterService getRegisterService() {
        return registerService;
    }

    @Resource(name = "registerServiceSite")
    public void setRegisterService(RegisterService registerService) {
        this.registerService = registerService;
    }

    public CustomerServiceMapper getCustomerServiceMapper() {
        return customerServiceMapper;
    }

    @Resource(name = "customerServiceMapper")
    public void setCustomerServiceMapper(CustomerServiceMapper customerServiceMapper) {
        this.customerServiceMapper = customerServiceMapper;
    }


}
