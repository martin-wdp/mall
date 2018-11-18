package com.ningpai.thirdaudit.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.thirdaudit.bean.ApplyBrandVo;
import com.ningpai.thirdaudit.service.ApplyBrandService;
import com.ningpai.thirdaudit.service.StoreCommonSerivce;
import com.ningpai.util.PageBean;

/**
 * 自定义品牌Controller
 * 
 * @author Zhuoy
 *
 */
@Controller
public class ApplyBrandvoController {

    // 列表页
    private static String page_list = "jsp/customer/applybrand";

    // 自定义品牌
    private ApplyBrandService applyBrandService;

    private StoreCommonSerivce storeCommonService;

    /**
     * 自定义品牌列表
     * 
     * @param pb
     * @param applyBrandVo
     *            自定义品牌VO对象{@link com.ningpai.thirdaudit.bean.ApplyBrandVo}
     * @param searchId
     * @param searchText
     * @return
     */
    @RequestMapping("/queryapplybrandlist")
    public ModelAndView queryApplyBrand(PageBean pb, ApplyBrandVo applyBrandVo, Integer searchId, String searchText) {
        if (searchId != null) {
            if (searchId == 1) {
                applyBrandVo.setApplyBrandName(searchText);
            } else if (searchId == 2) {
                applyBrandVo.setStoreName(searchText);
            }
        }
        // 把自定义品牌赋给分页对象
        pb.setObjectBean(applyBrandVo);
        // 设置分页路径
        pb.setUrl("queryapplybrandlist.htm");
        // 定义一个ModelAndView
        ModelAndView mav = new ModelAndView();
        // 设置跳转页面路径
        mav.setViewName(page_list);
        // 查询的自定义品牌列表
        mav.addObject("pageBean", applyBrandService.queryApplyBrand(pb));
        // 自定义品牌VO
        mav.addObject("applyBrandVo", applyBrandVo);
        mav.addObject("searchId", searchId);
        mav.addObject("searchText", searchText);
        return mav;
    }

    /**
     * 修改自定义品牌
     * 
     * @param applyBrandIds
     *            自定义品牌Id{@link java.lang.Long}
     * @param reason
     *            拒绝原因 {@link java.lang.String}
     * @param applyStatus
     *            自定义品牌状态 {@link java.lang.String}
     * @return
     */
    @RequestMapping("/updateapply")
    public ModelAndView updateApplyBrand(Long[] applyBrandIds, String reason, String applyStatus) {
        // 根据自定义品牌ID 修改原因 修改状态修改自定义品牌
        applyBrandService.updateApplyBrand(applyBrandIds, reason, applyStatus);
        if ("1".equals(applyStatus)) {
            String[] appl = new String[applyBrandIds.length];
            for (int i = 0; i < applyBrandIds.length; i++) {
                appl[i] = applyBrandIds[i].toString();
            }
            storeCommonService.applyBrandToTrueBrand(appl);
        }
        return new ModelAndView(new RedirectView("queryapplybrandlist.htm"));
    }

    public StoreCommonSerivce getStoreCommonService() {
        return storeCommonService;
    }

    @Resource(name = "StoreCommonSerivce")
    public void setStoreCommonService(StoreCommonSerivce storeCommonService) {
        this.storeCommonService = storeCommonService;
    }

    public ApplyBrandService getApplyBrandService() {
        return applyBrandService;
    }

    @Resource(name = "applybrandservice")
    public void setApplyBrandService(ApplyBrandService applyBrandService) {
        this.applyBrandService = applyBrandService;
    }

}
