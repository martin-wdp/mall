package com.ningpai.imagemanage.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.ningpai.imagemanage.bean.InfoImageClassify;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.common.util.ConstantUtil;
import com.ningpai.imagemanage.bean.InfoImageManage;
import com.ningpai.imagemanage.service.InfoImageClassifyService;
import com.ningpai.imagemanage.service.InfoImageManageService;
import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.uploadfileset.bean.UploadFileSet;
import com.ningpai.uploadfileset.service.UploadFileSetService;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;
import com.ningpai.util.UploadUtil;

/**
 * 控制器-资源图片信息
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年4月26日上午9:07:43
 */
@Controller
public class InfoImageManageController {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(InfoImageManageController.class);

    private static final String LOGGERINFO1 = "======根据分类，分页查询图片信息======";
    private static final String LOGGERINFO2 = ",用户名:";
    private static final String QUERYIMAGEMANAGEBYPBANDCID_CSRFTOKEN = "queryImageManageByPbAndCid.htm?CSRFToken=";
    private static final String QUERYIMAGEMANAGEBYPBANDCID_CLASSIFYID = "queryImageManageByPbAndCid.htm?classifyId=";

    /**
     * 用户名称
     */
    public static final String NAME = "name";

    public static final String OPERAPATH = "operaPath";

    /** 业务逻辑层依赖 */
    /** 资源图片类型SERVICE */
    private InfoImageClassifyService infoImageClassifyService;

    /** 资源图片信息SERVICE */
    private InfoImageManageService infoImageManageService;

    /** SERVICE-上传文件设置类 */
    @Resource(name = "UploadFileSetService")
    private UploadFileSetService uploadFileSetService;

    /**
     * 根据分类，分页查询图片信息
     *
     * @param pb
     * @param classifyId
     * @return
     */
    @RequestMapping("/queryImageManageByPbAndCid")
    public ModelAndView queryImageManageByPbAndCid(PageBean pb, Long classifyId) {
        LOGGER.debug(LOGGERINFO1);
        /** new一个视图对象 **/
        ModelAndView mav = new ModelAndView();
        mav.addObject("classifyId", classifyId);
        /** 查询所有图片管理分类 */
        mav.addObject("classifyList", infoImageClassifyService.selectAllImageClassifyForImg());
        /**
         * 如果传入的classifyId不为null并且大于0 如果不为null并且大于0调用查询图片资源方法时传入classifyId的值
         * 否则调用查询图片资源方法时就不传入classifyId的值
         */
        if (null != classifyId && classifyId >= 0) {
            /** 根据图片分类查询图片信息 */
            mav.addObject("pb", infoImageManageService.selectImageManageByParam(pb, classifyId, null, null, null));
        } else {
            /** 根据图片分类查询图片信息 */
            mav.addObject("pb", infoImageManageService.selectImageManageByParam(pb, null, null, null, null));
        }
        /** 设置视图 */
        mav.setViewName("jsp/infoImage/infoImageManageList");
        /** 返回结果 */
        return mav;
    }

    /**
     * 根据分类，分页查询图片信息，以供选择
     *
     * @param pb
     * @param classifyId
     * @return
     */
    @RequestMapping("/queryImageManageByPbAndCidForChoose")
    public ModelAndView queryImageManageByPbAndCidForChoose(PageBean pb, Long classifyId, Integer size, String location) {
        LOGGER.debug(LOGGERINFO1);
        /** 定义一个视图对象 */
        ModelAndView mav = new ModelAndView();
        /** 查询图片资源集合并添加到视图对象里 */
        mav.addObject("classifyList", infoImageClassifyService.selectAllImageClassify());
        mav.addObject("size", size);
        /** 获取当前的上传文件设置 */
        UploadFileSet ufs = this.uploadFileSetService.getCurrUploadFileSet();
        mav.addObject("ufs", ufs);
        /** 设置视图 */
        mav.setViewName("jsp/infoImage/chooseImage" + ((location == null || "".equals(location)) ? "" : ("_" + location)));
        /** 返回视图结果 */
        return mav;
    }

    /**
     * Ajax查询图片库，用于选取
     *
     * @param pb
     * @param classifyId
     * @return
     */
    @ResponseBody
    @RequestMapping("/ajaxQueryImageForChoose")
    public PageBean ajaxQueryImageForChoose(PageBean pb, Long classifyId, String startDate, String endDate, Long thirdId) {
        LOGGER.debug(LOGGERINFO1);
        /** 判断classifyId是否为null并且是否大于 */
        /** 不为null并且大于0时 */
        if (null != classifyId && classifyId >= 0) {
            /** 执行查询方法并把classifyId值传入方法 */
            return infoImageManageService.selectImageManageByParam(pb, classifyId, startDate, endDate, thirdId);
            /** classifyId为null或则不小于0时 */
        } else {
            /** 执行查询方法并把classifyId设置为null传入方法 */
            return infoImageManageService.selectImageManageByParam(pb, null, startDate, endDate, thirdId);
        }
    }

    /**
     * 上传图片并添加图片信息
     *
     * @param request
     * @param response
     * @param infoImageManage
     * @return
     */
    @RequestMapping("/saveImageManagerAction")
    public ModelAndView saveImageManagerAction(MultipartHttpServletRequest request, HttpServletResponse response, @Valid InfoImageManage infoImageManage,
            BindingResult bindingResult) {
        /** 如果验证不通过 */
        if (bindingResult.hasErrors()) {
            /** 重定向到分页查询图片信息页面 */
            return new ModelAndView(new RedirectView(QUERYIMAGEMANAGEBYPBANDCID_CSRFTOKEN + request.getParameter(ConstantUtil.CSRFTOKEN)));
        }
        /** 通过request获取页面中的图片资源文件 */
        MultipartFile muFile = request.getFile("imgSrc");
        /** 如果图片资源的size大于0 */
        if (muFile.getSize() > 0) {
            /** 设置图片的路径为muFile */
            Map<String, String> map = UploadUtil.uploadFile(muFile, request);
            /** 设置图片地址 */
            infoImageManage.setImageManageUrl(map.get("oldimg"));
            /** 设置图片地址 */
            infoImageManage.setBigImgUrl(map.get("0"));
            /** 大图地址 */
            infoImageManage.setMiddleImgUrl(map.get("1"));
            /** 小图地址 */
            infoImageManage.setSmallImgUrl(map.get("2"));
            /** 设置上传时间为当前时间 */
            infoImageManage.setImageOnlineDate(new Date());
            /** 保存图片资源信息 */
            infoImageManageService.saveInfoImageManage(infoImageManage);
            /** 通过session获取当前用户 */
            String customerName = (String) request.getSession().getAttribute(NAME);
            /** 记录操作日志 */
            OperaLogUtil.addOperaLog(request, customerName, "保存图片信息", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO2 + customerName);
        }
        /** 返回结果视图 */
        return new ModelAndView(new RedirectView(QUERYIMAGEMANAGEBYPBANDCID_CSRFTOKEN + request.getParameter(ConstantUtil.CSRFTOKEN)));
    }

    /**
     * Ajax查询图片库分类，用于选取
     * @return
     */
    @ResponseBody
    @RequestMapping("/ajaxQueryImageCateForAdd")
    public List<InfoImageClassify> ajaxQueryImageCateForAdd() {
        LOGGER.debug("=====查询所有图片库分类=======");
        return infoImageClassifyService.selectAllImageClassify();
    }

    /**
     * 修改图片信息
     *
     * @return
     */
    @RequestMapping("/updateImageManageAction")
    public ModelAndView updateImageManageAction(@Valid InfoImageManage infoImageManage, BindingResult bindingResult, HttpServletRequest request) {
        /** 如果验证不通过 */
        if (bindingResult.hasErrors()) {
            /** 重定向到分页查询图片信息页面 */
            return new ModelAndView(new RedirectView(QUERYIMAGEMANAGEBYPBANDCID_CSRFTOKEN + request.getParameter(ConstantUtil.CSRFTOKEN)));
        }
        LOGGER.debug("======修改图片信息======");
        /** 通过form表单提交的infoImageManage来更新图片资源 */
        infoImageManageService.updateInfoImageManage(infoImageManage);
        /** 通过session获取当前用户 */
        String customerName = (String) request.getSession().getAttribute(NAME);
        /** 记录操作日志 */
        OperaLogUtil.addOperaLog(request, customerName, "修改图片信息", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO2 + customerName);
        /** 返回结果 */
        return new ModelAndView(new RedirectView(QUERYIMAGEMANAGEBYPBANDCID_CLASSIFYID + infoImageManage.getClassifyId() + ConstantUtil.CSRF
                + request.getParameter(ConstantUtil.CSRFTOKEN)));
    }

    /**
     * 修改图片信息
     *
     * @return
     */
    @RequestMapping("/updateImageManageActionToFormer")
    public ModelAndView updateImageManageActionToFormer(@Valid InfoImageManage infoImageManage, BindingResult bindingResult, Long oldClassifyId, HttpServletRequest request) {
        /** 如果验证不通过 */
        if (bindingResult.hasErrors()) {
            /** 重定向到分页查询图片信息页面 */
            return new ModelAndView(new RedirectView(QUERYIMAGEMANAGEBYPBANDCID_CSRFTOKEN + request.getParameter(ConstantUtil.CSRFTOKEN)));
        }
        LOGGER.debug("======修改图片信息======");
        /** 通过获取页面form表单提交的infoImageManage实体类来更新图片资源 */
        infoImageManageService.updateInfoImageManage(infoImageManage);
        /** 通过session获取当前的操作人 */
        String customerName = (String) request.getSession().getAttribute(NAME);
        /** 记录操作日志 */
        OperaLogUtil.addOperaLog(request, customerName, "修改图片信息", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO2 + customerName);
        /** 返回结果 */
        return new ModelAndView(new RedirectView(QUERYIMAGEMANAGEBYPBANDCID_CLASSIFYID + oldClassifyId + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN)));
    }

    /**
     * 删除图片信息
     *
     * @param infoImageManageId
     * @return
     */
    @RequestMapping("/deleteImageManageAction")
    public ModelAndView deleteImageManageAction(Long infoImageManageId, HttpServletRequest request) {
        LOGGER.debug("======删除图片信息======要删除的图片ID为：" + infoImageManageId);
        /** 通过获取的infoImageManageId来删除对应的图片资源信息 */
        infoImageManageService.deleteInfoImageManage(infoImageManageId);
        /** 通过session获取到当前的用户姓名 */
        String customerName = (String) request.getSession().getAttribute(NAME);
        /** 记录操作日志 */
        OperaLogUtil.addOperaLog(request, customerName, "删除图片信息", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO2 + customerName);
        /** 返回结果 */
        return new ModelAndView(new RedirectView(QUERYIMAGEMANAGEBYPBANDCID_CSRFTOKEN + request.getParameter(ConstantUtil.CSRFTOKEN)));
    }

    /**
     * 删除图片信息并跳转回当前查询的页面
     *
     * @param infoImageManageId
     * @return
     */
    @RequestMapping("/deleteImageManageActionToFormer")
    public ModelAndView deleteImageManageActionToFormer(Long infoImageManageId, Long classifyId, HttpServletRequest request) {
        LOGGER.debug("======删除图片信息======");
        /** 根据页面传入的infoImageManageId来执行删除图片资源操作 */
        infoImageManageService.deleteInfoImageManage(infoImageManageId);
        /** 通过session获取操作者姓名 */
        String customerName = (String) request.getSession().getAttribute(NAME);
        /** 记录操作日志 */
        OperaLogUtil.addOperaLog(request, customerName, "删除图片信息", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO2 + customerName);
        /** 返回结果 */
        return new ModelAndView(new RedirectView(QUERYIMAGEMANAGEBYPBANDCID_CLASSIFYID + classifyId + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN)));
    }

    /**
     * 批量删除图片
     */
    @RequestMapping("/batchDeleteImageManageAction")
    @ResponseBody
    public void batchDeleteImageManageAction(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.debug("======批量删除图片信息======");
        try {
            /**
             * 定义一个String类型数组 通过request获取页面中的imageManageIds数组
             */
            String[] imageManageIds = request.getParameterValues("imageManageIds[]");
            /** 循环imageManageIds数组 */
            for (int i = 0; i < imageManageIds.length; i++) {
                Long infoImageManageId = Long.valueOf(imageManageIds[i]);
                /** 执行删除图片资源操作 */
                infoImageManageService.deleteInfoImageManage(infoImageManageId);

            }
            /** 通过session获取操作人姓名 */
            String customerName = (String) request.getSession().getAttribute(NAME);
            /** 记录操作日志 */
            OperaLogUtil.addOperaLog(request, customerName, "批量删除图片信息", request.getSession().getAttribute(OPERAPATH) + ",用户名：" + customerName);

        } catch (Exception e) {
            LOGGER.error("============批量删除图片信息失败：",e);
        }
    }

    public InfoImageClassifyService getInfoImageClassifyService() {
        return infoImageClassifyService;
    }

    @Resource(name = "InfoImageClassifyService")
    public void setInfoImageClassifyService(InfoImageClassifyService infoImageClassifyService) {
        this.infoImageClassifyService = infoImageClassifyService;
    }

    public InfoImageManageService getInfoImageManageService() {
        return infoImageManageService;
    }

    @Resource(name = "InfoImageManageService")
    public void setInfoImageManageService(InfoImageManageService infoImageManageService) {
        this.infoImageManageService = infoImageManageService;
    }
}
