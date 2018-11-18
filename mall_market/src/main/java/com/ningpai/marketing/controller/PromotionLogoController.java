package com.ningpai.marketing.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.common.safe.CSRFTokenManager;
import com.ningpai.customer.controller.CustomerController;
import com.ningpai.goods.util.PathUtil;
import com.ningpai.goods.util.ValueUtil;
import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.marketing.bean.PromotionLogo;
import com.ningpai.marketing.service.PromotionLogoService;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;
import com.ningpai.util.UploadUtil;

/**
 * 促销LOGO控制器
 * 
 * @author qiyuanyuan
 *
 */
@Controller
public class PromotionLogoController {

    // 记录日志对象
    private static final MyLogger LOGGER = new MyLogger(CustomerController.class);

    private static final String LOGOIMAGE = "logoImage";
    private static final String PICFILE = "picFile";
    private static final String OLDIMG = "oldimg";

    @Resource(name = "PromotionLogoService")
    private PromotionLogoService promotionLogoService;

    /**
     * 分页查询促销LOGO列表
     * 
     * @param pb
     *            分页{@link com.ningpai.util.PageBean}
     * @param promotionLogo
     *            促销LOGO对象{@link com.ningpai.marketing.bean.PromotionLogo}
     * @return pb
     */
    @RequestMapping("/findalllogo")
    public ModelAndView findAllLogo(PageBean pb, PromotionLogo promotionLogo) {
        LOGGER.info(ValueUtil.FINDALLLOGOINFO);
        // 把查询到的LOGO列表放在视图中
        return new ModelAndView(PathUtil.ALLLOGOINFO, "pb", promotionLogoService.queryAllPromotionLogo(pb, promotionLogo));
    }

    /**
     * 添加促销LOGO
     * 
     * @param logo
     *            促销LOGO对象{@link com.ningpai.marketing.bean.PromotionLogo}
     * @param request
     * @param request2
     * @return
     */
    @RequestMapping("/addlogo")
    public ModelAndView addLogo(@Valid PromotionLogo logo, HttpServletRequest request, MultipartHttpServletRequest request2) {
        MultipartFile imageFile = request2.getFile(LOGOIMAGE);
        if (imageFile == null) {
            imageFile = request2.getFile(PICFILE);
        }
        if (!imageFile.isEmpty()) {
            logo.setPromotionLogoUrl(UploadUtil.uploadFile(imageFile, request).get(OLDIMG));
        }
        // 添加促销LOGO
        this.promotionLogoService.addPromotionLogo(logo);
        // 操作日志记录操作
        OperaLogUtil.addOperaLog(request, (String) request.getSession().getAttribute(ValueUtil.NAME), ValueUtil.ADDLOGOINFO,
                (String) request.getSession().getAttribute(ValueUtil.OPERAPATH) + "-->LOGO名称【" + logo.getPromotionLogoName() + "】,用户名："
                        + (String) request.getSession().getAttribute(ValueUtil.NAME));
        // 重定向到查询标签列表控制器
        return new ModelAndView(new RedirectView(PathUtil.ALLLOGOCONTROLLER + ValueUtil.TOKENPARAM + CSRFTokenManager.getTokenFromRequest(request)));
    }

    /**
     * 验证促销Logo名称是否可用
     * 
     * @param promotionLogoName
     *            促销logo名称
     * @return 可用返回true 不可用返回false
     */
    @RequestMapping("/checklogoname")
    @ResponseBody
    public boolean checkLogoName(String promotionLogoName) {
        if (promotionLogoName != null && !"".equals(promotionLogoName)) {
            LOGGER.info(ValueUtil.CHECKLOGONAMEINFO + "促销logo名称" + promotionLogoName);
        } else {
            LOGGER.info(ValueUtil.CHECKLOGONAMEINFO);
        }
        // 执行验证促销LOGO名称是否可用
        return promotionLogoService.checkLogoName(promotionLogoName);
    }

    /**
     * 删除促销LOGO
     * 
     * @param request
     * @return
     */
    @RequestMapping("/dellogo")
    @ResponseBody
    public int delLogo(HttpServletRequest request, Long[] promotionLogoId) {
        // 获取店铺Id封装成Sting数组
        // String[] promotionLogoIds =
        // request.getParameterValues("promotionLogoId");
        // 操作日志
        OperaLogUtil.addOperaLog(request, (String) request.getSession().getAttribute(ValueUtil.NAME), "批量删除店铺", (String) request.getSession().getAttribute(ValueUtil.OPERAPATH)
                + "批量删除店铺,用户名：" + (String) request.getSession().getAttribute(ValueUtil.NAME));
        return promotionLogoService.delAllPromotionLogo(promotionLogoId);
    }

    /**
     * 更新商品标签
     * 
     * @param logo
     * 
     * @return
     */
    @RequestMapping("/updatelogo")
    public ModelAndView updateLogo(@Valid PromotionLogo logo, int pageNo, HttpServletRequest request, MultipartHttpServletRequest request2) {
        // 根据主键获取单个的商品标签对象
        PromotionLogo promotionLogo = promotionLogoService.selectByLogoId(logo.getPromotionLogoId());
        MultipartFile imageFile = request2.getFile(LOGOIMAGE);
        if (imageFile == null) {
            imageFile = request2.getFile(PICFILE);
        }
        if (!imageFile.isEmpty()) {
            logo.setPromotionLogoUrl(UploadUtil.uploadFile(imageFile, request).get(OLDIMG));
        }
        this.promotionLogoService.updatePromotionLogo(logo);
        OperaLogUtil.addOperaLog(request, (String) request.getSession().getAttribute(ValueUtil.NAME), ValueUtil.UPDATETAGINFO,
                (String) request.getSession().getAttribute(ValueUtil.OPERAPATH) + "-->logo名称【" + promotionLogo.getPromotionLogoName() + "】,用户名："
                        + (String) request.getSession().getAttribute(ValueUtil.NAME));
        return new ModelAndView(new RedirectView(PathUtil.ALLLOGOCONTROLLER + ValueUtil.TOKENPARAM + CSRFTokenManager.getTokenFromRequest(request) + "&pageNo=" + pageNo));
    }

    /**
     * 添加促销LOGO
     * 
     * @param logo
     *            促销LOGO对象{@link com.ningpai.marketing.bean.PromotionLogo}
     * @param request
     * @param request2
     * @return
     */
    @RequestMapping("/addthirdlogo")
    @ResponseBody
    public PromotionLogo addThirdLogo(@Valid PromotionLogo logo, HttpServletRequest request, MultipartHttpServletRequest request2) {
        MultipartFile imageFile = request2.getFile(LOGOIMAGE);
        PromotionLogo promotionLogo = null;
        if (imageFile == null) {
            imageFile = request2.getFile(PICFILE);
        }
        if (!imageFile.isEmpty()) {
            logo.setPromotionLogoUrl(UploadUtil.uploadFile(imageFile, request).get(OLDIMG));
        }
        int i = this.promotionLogoService.addPromotionLogo(logo);
        if (i > 0) {
            promotionLogo = promotionLogoService.selectLastLogo();
        }
        return promotionLogo;

    }
}
