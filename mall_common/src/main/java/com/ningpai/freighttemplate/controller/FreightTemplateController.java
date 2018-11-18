package com.ningpai.freighttemplate.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.common.safe.CSRFTokenManager;
import com.ningpai.common.util.ConstantUtil;
import com.ningpai.freighttemplate.bean.FreightTemplate;
import com.ningpai.freighttemplate.service.FreightTemplateService;
import com.ningpai.freighttemplate.service.SysCityService;
import com.ningpai.freighttemplate.service.SysDistrictService;
import com.ningpai.freighttemplate.service.SysLogisticsCompanyService;
import com.ningpai.freighttemplate.service.SysProvinceService;
import com.ningpai.util.MyLogger;

/**
 * 运费模板 2014-12-16
 * 
 * @author ggn
 *
 */
@Controller
public class FreightTemplateController {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(FreightTemplateController.class);

    private static final String FREIGHTTEMPLATELIST_HTM = "freighttemplatelist.htm";

    // Spring注入
    @Resource(name = "FreightTemplateService")
    private FreightTemplateService freightTemplateService;
    // Spring注入
    @Resource(name = "SysLogisticsCompanyService")
    private SysLogisticsCompanyService sysLogisticsCompanyService;
    // Spring注入
    @Resource(name = "SysProvinceService")
    private SysProvinceService sysProvinceService;
    // Spring注入
    @Resource(name = "SysCityService")
    private SysCityService sysCityService;
    // Spring注入
    @Resource(name = "SysDistrictService")
    private SysDistrictService sysDistrictService;

    /**
     * 查询运费模板
     * 
     * @param request
     * @param freightTemplate
     * @return ModelAndView
     */
    @RequestMapping("freighttemplatelist")
    public ModelAndView searchAllTemplate(FreightTemplate freightTemplate, HttpServletRequest request) {
        String csrFToken = request.getSession().getAttribute(CSRFTokenManager.CSRF_TOKEN_FOR_SESSION_ATTR_NAME).toString();
        freightTemplate.setFreightThirdId(0L);
        LOGGER.info("获取运费模板集合");
        // 根据freightTemplate参数进行条件查询
        return new ModelAndView("jsp/freight/freightlist").addObject("list", freightTemplateService.searchAllTemplate(freightTemplate))
                .addObject(ConstantUtil.CSRFTOKEN, csrFToken);
    }

    /**
     * 查询运费模板名称和id
     * 
     * @author houyichang 2015/7/1
     * @return List FreightTemplate
     * */
    @RequestMapping("freightTemp")
    @ResponseBody
    public List<FreightTemplate> queryTemplate() {
        // 查询模板列表
        return this.freightTemplateService.queryTemplate();
    }

    /**
     * 复制模板
     * 
     * @param freightTemplateId
     * @return ModelAndView
     */
    @RequestMapping("copyfreighttemplate")
    public ModelAndView copyFreightTemplate(Long freightTemplateId) {
        // 非空验证 运费模板ID
        if (null != freightTemplateId && null != freightTemplateService.selectFreightTemplateDetail(freightTemplateId).getFreightTemplateName()) {
            // 非空验证 运费模板名称
                LOGGER.info("复制名称为：【" + freightTemplateService.selectFreightTemplateDetail(freightTemplateId).getFreightTemplateName() + "模板");
        }
        // 复制模板功能
        freightTemplateService.copyFreightTemplate(freightTemplateId);
        return new ModelAndView(new RedirectView(FREIGHTTEMPLATELIST_HTM));
    }

    /**
     * 删除模板信息
     * 
     * @param freightTemplateId
     *            模板ID
     * @return ModelAndView
     */
    @RequestMapping("deletefreighttemplate")
    public ModelAndView deleteFreightTemplate(Long freightTemplateId, Long freightThirdId) {
        // 非空验证 运费模板ID
        if (null != freightTemplateId && null != freightTemplateService.selectFreightTemplateDetail(freightTemplateId).getFreightTemplateName()) {
            // 非空验证 运费模板名称
                LOGGER.info("删除名称为：【" + freightTemplateService.selectFreightTemplateDetail(freightTemplateId).getFreightTemplateName() + "】模板");
        }
        // 删除模板
        freightTemplateService.deleteFreightTemplate(freightTemplateId, freightThirdId);
        return new ModelAndView(new RedirectView(FREIGHTTEMPLATELIST_HTM));
    }

    /**
     * 设置模板为默认
     * 
     * @param freightTemplate
     * @return ModelAndView
     */
    @RequestMapping("defaultfreighttemplate")
    public ModelAndView defaultFreightTemplate(FreightTemplate freightTemplate) {
        // 非空验证 运费模板名称
        if (null != freightTemplate.getFreightTemplateName()) {
            LOGGER.info("设置名称为：【" + freightTemplate.getFreightTemplateName() + "】模板");
        }
        freightTemplate.setFreightThirdId(0L);
        // 设置模版为默认
        freightTemplateService.defaultFreightTemplate(freightTemplate);
        return new ModelAndView(new RedirectView(FREIGHTTEMPLATELIST_HTM));
    }

    /**
     * 查询模板详细信息
     * 
     * @param freightTemplateId
     *            模板ID
     * @param request
     * @return ModelAndView
     */
    @RequestMapping("toupdatefreighttemplate")
    public ModelAndView toUpdateFreightTemplate(Long freightTemplateId, HttpServletRequest request) {
        String csrFToken = request.getSession().getAttribute(CSRFTokenManager.CSRF_TOKEN_FOR_SESSION_ATTR_NAME).toString();
        // 获取模板详细信息
        FreightTemplate ft = freightTemplateService.selectFreightTemplateDetail(freightTemplateId);
        // 非空验证 运费模板名称
        if (null != ft.getFreightTemplateName()) {
            LOGGER.info("查询名称为：【" + ft.getFreightTemplateName() + "】的详细信息");
        }
        return new ModelAndView("jsp/freight/updatefreightlist")
        // 获取系统省
                .addObject("provinceList", sysProvinceService.selectAllProvince())
                // 获取系统市区
                .addObject("cityList", sysCityService.selectAllCityByProvinceId(ft.getFreightProvinceId()))
                // 获取区县
                .addObject("districtList", sysDistrictService.selectAllDistrictByCityId(ft.getFreightCityId()))
                // 模板信息
                .addObject("freightTemplate", ft)
                // 快递公司
                .addObject("companylist", sysLogisticsCompanyService.selectAllCompnay()).addObject(ConstantUtil.CSRFTOKEN, csrFToken);
    }

    /**
     * 修改保存
     * 
     * @param request
     * @return ModelAndView
     */
    @RequestMapping("savefreight")
    public ModelAndView saveFreight(HttpServletRequest request, FreightTemplate freightTemplate) {
        // 非空验证 运费模板名称
        if (null != freightTemplate.getFreightTemplateName()) {
            LOGGER.info("修改名称为：【" + freightTemplate.getFreightTemplateName() + "】的模板");
        }
        // 保存模板
        freightTemplateService.saveFreight(request, freightTemplate);
        return new ModelAndView(new RedirectView(FREIGHTTEMPLATELIST_HTM));
    }

    /**
     * 去添加
     * 
     * @param request
     * @return ModelAndView
     */
    @RequestMapping("toAddFreightTemplate")
    public ModelAndView toAddFreightTemplate(HttpServletRequest request) {
        String csrFToken = request.getSession().getAttribute(CSRFTokenManager.CSRF_TOKEN_FOR_SESSION_ATTR_NAME).toString();
        return new ModelAndView("jsp/freight/addfreightlist")
        // 获取系统省
                .addObject("provinceList", sysProvinceService.selectAllProvince())
                // 快递公司
                .addObject("companylist", sysLogisticsCompanyService.selectAllCompnay()).addObject(ConstantUtil.CSRFTOKEN, csrFToken);

    }

    /**
     * 确定添加
     * 
     * @param request
     * @param freightTemplate
     * @return ModelAndView
     */
    @RequestMapping("addFreight")
    public ModelAndView addFreight(HttpServletRequest request, FreightTemplate freightTemplate) {
        // 非空验证 运费模板名称
        if (null != freightTemplate.getFreightTemplateName()) {
            LOGGER.info("新增名称为：【" + freightTemplate.getFreightTemplateName() + "】的模板");
        }
        freightTemplate.setFreightThirdId(0L);
        freightTemplateService.addFreight(request, freightTemplate);
        return new ModelAndView(new RedirectView(FREIGHTTEMPLATELIST_HTM));
    }

}
