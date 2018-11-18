package com.ningpai.system.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.system.bean.ServiceSupport;
import com.ningpai.system.service.HelpCenterService;
import com.ningpai.system.service.ServiceSupportMapperService;
import com.ningpai.util.MenuSession;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;
import com.ningpai.util.UploadUtil;

/**
 * 服务支持Controller
 * 
 * @Description:
 * @author NINGPAI-ZhuoYu
 * @since 2014-07-28 15:05:13
 * @version V1.0
 */
@Controller
public class ServiceSupportMapperController {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(ServiceSupportMapperController.class);

    // session中保存的登录用户的id
    private static final String LOGINUSERID = "loginUserId";

    /**
     * 用户名称
     */
    public static final String NAME = "name";

    /**
     * "operaPath"
     */
    public static final String OPERAPATH = "operaPath";

    private static final String SELECTSERVICESUPPORTLIST_HTM = "selectServiceSupportList.htm";
    private static final String HELPLIST = "helpList";
    private static final String LOGGERINFO1 = ",用户名:";

    @Resource(name = "serviceSupportMapperService")
    private ServiceSupportMapperService serviceSupportMapperService;

    @Resource(name = "helpCenterService")
    private HelpCenterService helpCenterService;

    /**
     * 添加服务支持
     * 
     * @param
     * @return
     */
    @RequestMapping("/newServiceSupport")
    public ModelAndView newServiceSupport(MultipartHttpServletRequest request, @Valid ServiceSupport serviceSupport, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView(new RedirectView(SELECTSERVICESUPPORTLIST_HTM));
        }
        try {
            MultipartFile muFile = request.getFile("imageSrc");
            if (null != muFile && muFile.getSize() > 0) {
                serviceSupport.setServiceImageUrl(UploadUtil.uploadFileOne(muFile, request));

            }
            serviceSupport.setDelfalg("0");
            serviceSupport.setCreateTime(new Date());
            serviceSupport.setCreateUserId((Long) request.getSession().getAttribute(LOGINUSERID));
            serviceSupportMapperService.insertSelective(serviceSupport);

            String customerName = (String) request.getSession().getAttribute(NAME);
            OperaLogUtil.addOperaLog(request, customerName, "添加服务支持", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
        } catch (Exception e) {
            LOGGER.error("添加服务支持错误：=>",e);
        }
        return new ModelAndView(new RedirectView(SELECTSERVICESUPPORTLIST_HTM));
    }

    /**
     * 显示单个
     * 
     * @param id
     * @return
     */
    @RequestMapping("/selectServiceSupportById")
    public ModelAndView selectServiceSupportById(Long id) {
        ModelAndView mav = new ModelAndView();
        ServiceSupport serviceSupport = serviceSupportMapperService.selectByPrimaryKey(id);
        mav.setViewName("jsp/system/serviceSupport/show_serviceSupport");
        mav.addObject("serviceSupport", serviceSupport);
        mav.addObject(HELPLIST, helpCenterService.selectAll());
        return mav;
    }

    /**
     * 显示单个ajax
     *
     * @param id
     * @return
     */
    @RequestMapping("/selectservicesupportbyidajax")
    @ResponseBody
    public ServiceSupport selectservicesupportbyidajax(Long id) {
        return serviceSupportMapperService.selectByPrimaryKey(id);
    }

    /**
     * 修改服务支持
     * 
     * @param serviceSupport
     * @return
     */
    @RequestMapping("/updateServiceSupport")
    public ModelAndView updateServiceSupport(MultipartHttpServletRequest request, @Valid ServiceSupport serviceSupport, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView(new RedirectView(SELECTSERVICESUPPORTLIST_HTM));
        }
        try {
            MultipartFile muFile = request.getFile("imageSrc");
            if (muFile != null && muFile.getSize() > 0) {
                    serviceSupport.setServiceImageUrl(UploadUtil.uploadFileOne(muFile, request));
            }
            serviceSupport.setUpdateTime(new Date());
            serviceSupport.setUpdateUserId((Long) request.getSession().getAttribute(LOGINUSERID));
            serviceSupportMapperService.updateByPrimaryKeySelective(serviceSupport);

            String customerName = (String) request.getSession().getAttribute(NAME);
            OperaLogUtil.addOperaLog(request, customerName, "修改服务支持", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
        } catch (Exception e) {
            LOGGER.error("修改服务支持错误：=>" ,e);
        }
        return new ModelAndView(new RedirectView(SELECTSERVICESUPPORTLIST_HTM));
    }

    /**
     * 跳转新增页面
     * 
     * @return
     */
    @RequestMapping("/newService")
    public ModelAndView newService() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("jsp/system/serviceSupport/show_serviceSupport");
        mav.addObject(HELPLIST, helpCenterService.selectAll());
        return mav;
    }

    /**
     * 分页显示
     * 
     * @param pb
     * @return
     */
    @RequestMapping("/selectServiceSupportList")
    public ModelAndView selectServiceSupportList(PageBean pb, HttpServletRequest request) {
        MenuSession.sessionMenu(request);
        ModelAndView mav = new ModelAndView();
        serviceSupportMapperService.selectAllServiceSupport(pb);
        mav.setViewName("jsp/system/serviceSupport/serviceSupport_List");
        mav.addObject("pb", pb);
        mav.addObject(HELPLIST, helpCenterService.selectAll());
        return mav;
    }

    /**
     * 删除服务支持
     * 
     * @param request
     * @return
     */
    @RequestMapping("/updateServiceSupportDelfalg")
    public ModelAndView updateServiceSupportDelfalg(HttpServletRequest request) {
        try {
            String[] ids = request.getParameterValues("id[]");
            serviceSupportMapperService.updateServiceSupportByDelfalg(ids);
            String customerName = (String) request.getSession().getAttribute(NAME);
            OperaLogUtil.addOperaLog(request, customerName, "删除服务支持", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
        } catch (Exception e) {
            LOGGER.error("删除服务支持错误：=>",e);
        }
        return new ModelAndView(new RedirectView(SELECTSERVICESUPPORTLIST_HTM));
    }
}
