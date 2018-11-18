package com.ningpai.third.template.controller;

import com.ningpai.freighttemplate.bean.FreightExpress;
import com.ningpai.freighttemplate.bean.FreightTemplate;
import com.ningpai.freighttemplate.service.FreightTemplateService;
import com.ningpai.freighttemplate.service.SysCityService;
import com.ningpai.freighttemplate.service.SysDistrictService;
import com.ningpai.freighttemplate.service.SysProvinceService;
import com.ningpai.third.seller.bean.Express;
import com.ningpai.third.seller.service.ExpressInfoService;
import com.ningpai.third.util.MenuOperationUtil;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * 运费模板 2014-12-16
 * 
 * @author ggn
 *
 */
@Controller
public class FreightTemplateThirdController {

    private static final String THIRDID = "thirdId";

    private static final String FREIGHTTEMPLATELISTTHIRD_HTM = "freighttemplatelistthird.html";

    @Resource(name = "FreightTemplateService")
    private FreightTemplateService freightTemplateService;
    @Resource(name = "SysProvinceService")
    private SysProvinceService sysProvinceService;
    @Resource(name = "SysCityService")
    private SysCityService sysCityService;
    @Resource(name = "SysDistrictService")
    private SysDistrictService sysDistrictService;
    @Resource(name = "expressInfoService")
    private ExpressInfoService expressInfoService;

    /**
     * 查询运费模板
     * 
     * @param freightTemplate
     * @return ModelAndView
     */
    @RequestMapping("freighttemplatelistthird")
    public ModelAndView searchAllTemplate(FreightTemplate freightTemplate, HttpServletRequest request, String n, String l) {
        freightTemplate.setFreightThirdId(Long.valueOf(request.getSession().getAttribute(THIRDID).toString()));
        // 根据freightTemplate参数进行条件查询
        MenuOperationUtil.fillSessionMenuIndex(request, n, l);
        return new ModelAndView("freight/freightlist").addObject("list", freightTemplateService.searchAllTemplate(freightTemplate));
    }

    /**
     * 复制模板
     * 
     * @param freightTemplateId
     * @return ModelAndView
     */
    @RequestMapping("copyfreighttemplatethird")
    public ModelAndView copyFreightTemplate(Long freightTemplateId) {
        freightTemplateService.copyFreightTemplate(freightTemplateId);
        return new ModelAndView(new RedirectView(FREIGHTTEMPLATELISTTHIRD_HTM));
    }

    /**
     * 删除模板信息
     * 
     * @param freightTemplateId
     *            ,freightThirdId
     * @return ModelAndView
     */
    @RequestMapping("deletefreighttemplatethird")
    public ModelAndView deleteFreightTemplate(Long freightTemplateId, HttpServletRequest request, Long freightThirdId) {
        Long freightThirdIdNew = freightThirdId;
        freightThirdIdNew = (Long) request.getSession().getAttribute(THIRDID);
        freightTemplateService.deleteFreightTemplate(freightTemplateId, freightThirdIdNew);
        return new ModelAndView(new RedirectView(FREIGHTTEMPLATELISTTHIRD_HTM));
    }

    /**
     * 设置模板为默认
     * 
     * @param freightTemplate
     * @return ModelAndView
     */
    @RequestMapping("defaultfreighttemplatethird")
    public ModelAndView defaultFreightTemplate(HttpServletRequest request, FreightTemplate freightTemplate) {
        freightTemplate.setFreightThirdId(Long.valueOf(request.getSession().getAttribute(THIRDID).toString()));
        freightTemplateService.defaultFreightTemplate(freightTemplate);
        return new ModelAndView(new RedirectView(FREIGHTTEMPLATELISTTHIRD_HTM));
    }

    /**
     * 查询模板详细信息
     * 
     * @param freightTemplateId
     * @param request
     * @return ModelAndView
     */
    @RequestMapping("toupdatefreighttemplatethird")
    public ModelAndView toUpdateFreightTemplate(Long freightTemplateId, HttpServletRequest request) {
        Express expressmap = new Express();
        Long thirdId = (Long) request.getSession().getAttribute(THIRDID);
        expressmap.setStoreId(thirdId); // 商家编号
        FreightTemplate ft = freightTemplateService.selectFreightTemplateDetail(freightTemplateId);
        if (thirdId.equals(ft.getFreightThirdId())) {
            return new ModelAndView("freight/updatefreightlist").addObject("provinceList", sysProvinceService.selectAllProvince())
                    .addObject("cityList", sysCityService.selectAllCityByProvinceId(ft.getFreightProvinceId()))
                    .addObject("districtList", sysDistrictService.selectAllDistrictByCityId(ft.getFreightCityId())).addObject("freightTemplate", ft)
                    .addObject("companylist", expressInfoService.selectByStoreIds(expressmap));

        } else {
            return new ModelAndView();
        }
        // List<Express> Expresss =
        // expressInfoService.selectByStoreId(expressmap);
    }

    /**
     * 保存运费模板
     * 
     * @param request
     * @return ModelAndView
     */
    @RequestMapping("savefreightthird")
    public ModelAndView saveFreight(HttpServletRequest request, FreightTemplate freightTemplate) {
        freightTemplate.setFreightThirdId(Long.valueOf(request.getSession().getAttribute(THIRDID).toString()));
        freightTemplateService.saveFreight(request, freightTemplate);
        return new ModelAndView(new RedirectView(FREIGHTTEMPLATELISTTHIRD_HTM));
    }

    /**
     * 准备添加运费模板
     * 
     * @param request
     * @return ModelAndView
     */
    @RequestMapping("toAddFreightTemplatethird")
    public ModelAndView toAddFreightTemplate(HttpServletRequest request) {
        Express expressmap = new Express();
        expressmap.setStoreId((Long) request.getSession().getAttribute(THIRDID)); // 商家编号
        List<Express> expresss = expressInfoService.selectByStoreIds(expressmap);
        return new ModelAndView("freight/addfreightlist").addObject("provinceList", sysProvinceService.selectAllProvince()).addObject("companylist", expresss);

    }

    /**
     * 添加运费模板
     * 
     * @param request
     * @param freightTemplate
     * @return ModelAndView
     */
    @RequestMapping("addFreightthird")
    public ModelAndView addFreight(HttpServletRequest request, FreightTemplate freightTemplate) throws UnsupportedEncodingException {
        freightTemplate.setFreightThirdId(Long.valueOf(request.getSession().getAttribute(THIRDID).toString()));

        FreightExpress fe = freightTemplateService.selectFreightExpressByDistriThirdId(Long.valueOf(request.getSession().getAttribute(THIRDID).toString()));
        if (fe != null) {
            freightTemplate.setFreightIsDefault("0");
        } else {
            freightTemplate.setFreightIsDefault("1");
        }

        freightTemplateService.addFreight(request, freightTemplate);
        return new ModelAndView(new RedirectView(FREIGHTTEMPLATELISTTHIRD_HTM));
    }

}
