package com.qpmall.site.register.controller;

import com.ningpai.freighttemplate.service.SysProvinceService;
import com.ningpai.index.service.TopAndBottomService;
import com.ningpai.system.service.BasicSetService;
import com.ningpai.util.MyLogger;
import com.ningpai.util.UploadUtil;
import com.qpmall.site.register.bean.QpEnterpriseCertificationInfo;
import com.qpmall.site.register.service.QpEnterpriseCertificationInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * Created by ly-qpmall on 2015/10/19.
 */
@Controller
public class QpCustomerInfoUpdateController {

    /**
     * 记录日志对象
     */
    private static final MyLogger LOGGER = new MyLogger(QpCustomerInfoUpdateController.class);

    /**
     * 获取头尾
     */
    @Resource(name = "TopAndBottomService")
    private TopAndBottomService topAndBottomService;
    @Resource(name = "qpEnterpriseCertificationInfoService")
    private QpEnterpriseCertificationInfoService qpEnterpriseCertificationInfoService;

    @Resource(name = "basicSetService")
    private BasicSetService basicSetService;
    @Resource(name = "SysProvinceService")
    private SysProvinceService sysProvinceService;


    @RequestMapping(value = "/showEnpriseInfo")
    public ModelAndView showEnpriseInfo(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("customer/newenpriseinfo");
        //查询用户的认证信息ssss
        Long customerId = (Long) request.getSession().getAttribute("customerId");
        if(customerId==null){
            return new ModelAndView(new RedirectView("login.html"));
        }
        QpEnterpriseCertificationInfo qpEnterpriseCertificationInfo
                = qpEnterpriseCertificationInfoService.selectuserEnperseByCustomerId(customerId);
        List<QpEnterpriseCertificationInfo> qpEnterpriseCertificationInfos =
                qpEnterpriseCertificationInfoService.selectEnterpriseCertificationByCustomerId(customerId);
        if(qpEnterpriseCertificationInfos != null && qpEnterpriseCertificationInfos.size() > 0){
            mav.addObject("qpEnterpriseCertificationInfoLast", qpEnterpriseCertificationInfos.get(0));
        }else{
            mav.addObject("qpEnterpriseCertificationInfoLast", null);
        }

        mav.addObject("qpEnterpriseCertificationInfo", qpEnterpriseCertificationInfo);

        return topAndBottomService.getTopAndBottom(mav);
    }

    /**
     * initMyAddressAjax初始化地址
     * @param request
     * @return
     */
    @RequestMapping("/initMyAddressAjax")
    @ResponseBody
    public int[] initMyAddress(HttpServletRequest request){
        Long customerId = (Long) request.getSession().getAttribute("customerId");
        if(customerId==null){
            return null;
        }
        List<QpEnterpriseCertificationInfo> qpEnterpriseCertificationInfos =
                qpEnterpriseCertificationInfoService.selectEnterpriseCertificationByCustomerId(customerId);
        if(qpEnterpriseCertificationInfos != null && qpEnterpriseCertificationInfos.size() > 0){
            QpEnterpriseCertificationInfo qpEnterpriseCertificationInfo =qpEnterpriseCertificationInfos.get(0);
            if( qpEnterpriseCertificationInfo!=null){
                return new int[]{qpEnterpriseCertificationInfo.getEnterprise_province(),qpEnterpriseCertificationInfo.getEnterprise_city()
                        ,qpEnterpriseCertificationInfo.getEnterprise_county()};
            }else {
                return null;
            }
        }
        return null;
    }

    @RequestMapping(value = "/updateEnpriseInfo")
    public ModelAndView updateEnpriseInfo(HttpServletRequest request,
                                          //MultipartHttpServletRequest mutirequest,
                                          @Valid QpEnterpriseCertificationInfo qpEnterpriseCertificationInfo) {
        ModelAndView mav = new ModelAndView("customer/newenpriseinforeshult");
        //得到qpEnterpriseCertificationInfo的customerId数据
        if (qpEnterpriseCertificationInfo.getCustomerId() == null || "".equals(qpEnterpriseCertificationInfo.getCustomerId())) {
            qpEnterpriseCertificationInfo.setCustomerId((Long) request.getSession().getAttribute("customerId"));
        }
        if (qpEnterpriseCertificationInfo.getCustomerId() == null || "".equals(qpEnterpriseCertificationInfo.getCustomerId())) {
            LOGGER.info("未登录状态不能进行企业认证！");
            return mav.addObject("enInfo", "未登录状态不能进行企业认证");
        }
        if (qpEnterpriseCertificationInfo.getEnterprise_id() == null || "".equals(qpEnterpriseCertificationInfo.getEnterprise_id())) {
            LOGGER.info("没有修改id号！");
            return mav.addObject("enInfo", "参数不对！");
        }

        LOGGER.info("提交认证信息！认证用户：" + qpEnterpriseCertificationInfo.getCustomerId() + "。");
       /* //处理文件上传
        String company_pic = "";
        //
        if (mutirequest.getFile("company_pic1") != null && !"".equals(mutirequest.getFile("company_pic1").getOriginalFilename())) {
            // 使用上传后路径
            company_pic = UploadUtil.uploadFile(mutirequest.getFile("company_pic1"), request).get("oldimg");
        }
        if (mutirequest.getFile("company_pic2") != null && !"".equals(mutirequest.getFile("company_pic2").getOriginalFilename())) {
            // 使用上传后路径
            if (company_pic.length() > 0) {
                company_pic = company_pic + "##" + UploadUtil.uploadFile(mutirequest.getFile("company_pic2"), request).get("oldimg");
            } else {
                company_pic = UploadUtil.uploadFile(mutirequest.getFile("company_pic2"), request).get("oldimg");
            }
        }
        if (mutirequest.getFile("company_pic3") != null && !"".equals(mutirequest.getFile("company_pic3").getOriginalFilename())) {
            // 使用上传后路径
            if (company_pic.length() > 0) {
                company_pic = company_pic + "##" + UploadUtil.uploadFile(mutirequest.getFile("company_pic3"), request).get("oldimg");
            } else {
                company_pic = UploadUtil.uploadFile(mutirequest.getFile("company_pic3"), request).get("oldimg");
            }
        }

        if (company_pic.trim().length() == 0) {
            qpEnterpriseCertificationInfo.setCompany_pic_url(null);
        } else {
            qpEnterpriseCertificationInfo.setCompany_pic_url(company_pic);
        }
        if (mutirequest.getFile("buss_url") == null || "".equals(mutirequest.getFile("buss_url").getOriginalFilename())) {
            qpEnterpriseCertificationInfo.setBusscard_url(null);
        } else {
            qpEnterpriseCertificationInfo.setBusscard_url(UploadUtil.uploadFile(mutirequest.getFile("buss_url"), request).get("oldimg"));
        }

        if (mutirequest.getFile("buss_dept_no") == null || "".equals(mutirequest.getFile("buss_dept_no").getOriginalFilename())) {
            qpEnterpriseCertificationInfo.setBuss_dept_no_url(null);
        } else {
            qpEnterpriseCertificationInfo.setBuss_dept_no_url(UploadUtil.uploadFile(mutirequest.getFile("buss_dept_no"), request).get("oldimg"));
        }

        if (mutirequest.getFile("buss_tax_regist") == null || "".equals(mutirequest.getFile("buss_tax_regist").getOriginalFilename())) {
            qpEnterpriseCertificationInfo.setBuss_tax_regist_url(null);
        } else {
            qpEnterpriseCertificationInfo.setBuss_tax_regist_url(UploadUtil.uploadFile(mutirequest.getFile("buss_tax_regist"), request).get("oldimg"));
        }*/

        if (qpEnterpriseCertificationInfo.getCompany_name() == null || "".equals(qpEnterpriseCertificationInfo.getCompany_name())) {
           /* LOGGER.info("没有填写企业名称！");
            return topAndBottomService.getTopAndBottom(mav.addObject("enInfo", "请填写企业名称!"));*/
            qpEnterpriseCertificationInfo.setCompany_name(null);
        }


        if (qpEnterpriseCertificationInfo.getEnterprise_province() == null || "".equals(qpEnterpriseCertificationInfo.getEnterprise_province()) ||
                qpEnterpriseCertificationInfo.getEnterprise_city() == null || "".equals(qpEnterpriseCertificationInfo.getEnterprise_city()) ||
                qpEnterpriseCertificationInfo.getEnterprise_county() == null || "".equals(qpEnterpriseCertificationInfo.getEnterprise_county())) {
            LOGGER.info("地址不详细!");
            qpEnterpriseCertificationInfo.setEnterprise_province(null);
            qpEnterpriseCertificationInfo.setEnterprise_city(null);
            qpEnterpriseCertificationInfo.setEnterprise_county(null);
            //return topAndBottomService.getTopAndBottom(mav.addObject("enInfo", "地址不详细!请选择详细地址.!"));
            qpEnterpriseCertificationInfo.setCompany_address(null);
        }else {

            String address = "";
            Long proviceId = Long.valueOf(qpEnterpriseCertificationInfo.getEnterprise_province());
            address = sysProvinceService.selectProvinceById(proviceId) == null ? "" : sysProvinceService.selectProvinceById(proviceId).getProvinceName();
            Long cityId = Long.valueOf(qpEnterpriseCertificationInfo.getEnterprise_city());
            address = address + (qpEnterpriseCertificationInfoService.selectCityByPid(cityId) == null ? "" : qpEnterpriseCertificationInfoService.selectCityByPid(cityId).getCityName());
            Long districtId = Long.valueOf(qpEnterpriseCertificationInfo.getEnterprise_county());
            address = address + (qpEnterpriseCertificationInfoService.selectDistrictByCid(districtId) == null ? "" : qpEnterpriseCertificationInfoService.selectDistrictByCid(districtId).getDistrictName());

        }
        //保存企业认证信息
        qpEnterpriseCertificationInfo.setMod_time(new Date(System.currentTimeMillis()));
        int re = qpEnterpriseCertificationInfoService.updateQpEnterpriseCertificationInfo(qpEnterpriseCertificationInfo);
        if (re == 1) {
            return topAndBottomService.getTopAndBottom(mav.addObject("enInfo", "修改成功!"));
        } else {
            return topAndBottomService.getTopAndBottom(mav.addObject("enInfo", "修改信息失败!"));
        }
    }
}
