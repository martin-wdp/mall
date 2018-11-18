package com.ningpai.businesscircle.controller;

import com.ningpai.businesscircle.bean.BusinessCircle;
import com.ningpai.businesscircle.service.BusinessCircleService;
import com.ningpai.common.safe.CSRFTokenManager;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * 商圈设置
 * 
 * @author ggn 2015-01-20
 *
 */
@Controller
public class BusinessCircleController {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(BusinessCircleController.class);

    private static final String REDIRECT = "selectBusinessCircles.htm";

    /*
     * spring 注入
     */
    @Resource(name = "BusinessCircleService")
    private BusinessCircleService businessCircleService;

    /**
     * 查询可以绑定给商家的商圈信息
     * 
     * @return List<Object>
     */
    @RequestMapping("/findBusinessCircles")
    @ResponseBody
    public List<Object> findBusinessCircles() {
        return businessCircleService.getAll("1", "状态");
    }

    /**
     * 查询商家已绑定的商圈信息
     * 
     * @param thirdId
     * @return BusinessCircle
     */
    @RequestMapping("/findBusinessCirclesById")
    @ResponseBody
    public BusinessCircle findBusinessCirclesById(Long thirdId) {
        // 根据商家编号查询商圈信息
        return businessCircleService.findBusinessCircleByThirdId(thirdId);
    }

    /**
     * 根据商圈Id查询商圈信息
     * 
     * @param businessCircleId
     * @return BusinessCircle
     */
    @RequestMapping("/findBusinessCircleByBusinessCircleId")
    @ResponseBody
    public BusinessCircle findBusinessCircleByBusinessCircleId(Long businessCircleId) {
        // 根据商圈Id 查询商圈信息
        return businessCircleService.findBusinessCircleByBusinessCircleId(businessCircleId);
    }

    /**
     * 修改商圈开启状态
     * 
     * @param businessCircleId
     * @param businessCircleIsOpen
     * @param pageNo
     * @return ModelAndView
     */
    @RequestMapping("/updeBusinessCircleById")
    public ModelAndView updeBusinessCirclesById(Long businessCircleId, String businessCircleIsOpen, int pageNo, HttpServletRequest request) {
        // 修改商圈的开启状态
        businessCircleService.updateBusinessCircleById(businessCircleId, businessCircleIsOpen);
        return new ModelAndView(new RedirectView("selectBusinessCircles.htm?CSRFToken=" + CSRFTokenManager.getTokenFromRequest(request) + "&pageNo=" + pageNo));
    }

    /**
     * 删除商圈
     * 
     * @param businessCircleId
     * @return int
     */
    @RequestMapping("/delBusinessCircleById")
    @ResponseBody
    public int updeBusinessCirclesById(Long businessCircleId) {
        return businessCircleService.delBusinessCircle(businessCircleId);

    }

    /**
     * 添加商圈
     * 
     * @param businessCircleId
     *            商圈ID
     * @param businessCircleName
     *            商圈名称
     * @param province
     *            省
     * @param city
     *            市
     * @param businessCircleRemark
     *            备注
     * @return ModelAndView 返回
     */
    @RequestMapping("/addBusinessCircle")
    public ModelAndView addBusinessCircle(Long businessCircleId, String businessCircleName, Long province, Long city, String businessCircleRemark) {
        try {

            // 判断商圈名不为空
            if (businessCircleName.length() > 0) {
                BusinessCircle bc = businessCircleService.findBusinessCircleByName(businessCircleName);
                // 修改
                if (businessCircleId != null && businessCircleId > 0) {
                    if (bc != null && bc.getBusinessCircleCityId().equals(businessCircleId)) {
                        return null;
                    } else {
                        // 申明一个商圈
                        BusinessCircle businessC = new BusinessCircle();
                        // 设置值
                        businessC.setBusinessCircleId(businessCircleId);
                        businessC.setBusinessCircleName(businessCircleName);
                        businessC.setBusinessCircleCityId(city);
                        businessC.setBusinessCircleProvinceId(province);
                        businessC.setBusinessCircleRemark(businessCircleRemark);
                        businessC.setBusinessCircleModifyTime(new Date());
                        // 修改商圈信息
                        businessCircleService.updateBusinessCircle(businessC);
                        return new ModelAndView(new RedirectView(REDIRECT));

                    }

                } else {
                    // 添加
                    if (bc == null) {
                        // 申明一个商圈
                        BusinessCircle businessCircle = new BusinessCircle();
                        // 设置值
                        businessCircle.setBusinessCircleName(businessCircleName);
                        businessCircle.setBusinessCircleCityId(city);
                        businessCircle.setBusinessCircleProvinceId(province);
                        businessCircle.setBusinessCircleRemark(businessCircleRemark);
                        businessCircle.setBusinessCircleCreateTime(new Date());
                        businessCircle.setBusinessCircleIsOpen("1");
                        // 添加商圈
                        businessCircleService.addBusinessCircle(businessCircle);
                        return new ModelAndView(new RedirectView(REDIRECT));

                    }

                }

            }
            return null;

        } catch (Exception e) {
            LOGGER.info("添加商圈错误！" + e);
            return null;
        }
    }

    /**
     * 根据名称判断商圈是否存在
     * 
     * @param request
     * @param businessCircleName
     * @return int 1存在0可以使用
     */
    @RequestMapping("/findBusinessCircleByName")
    @ResponseBody
    public Long findBusinessCircleByName(HttpServletRequest request, String businessCircleName) {
        try {

            // 按照名称查询商品是否存在
            BusinessCircle bc = businessCircleService.findBusinessCircleByName(businessCircleName);
            if (bc != null) {
                // 如果存在返回商圈ID
                return bc.getBusinessCircleId();
            } else {
                return 0L;
            }
        } catch (Exception e) {
            LOGGER.info("查询商圈错误！" + e);
            return -1L;
        }
    }

    /**
     * 获得所有商圈
     * 
     * @param pageBean
     * @param searchId
     * @param searchText
     * @return ModelAndView
     */
    @RequestMapping("/selectBusinessCircles")
    public ModelAndView selectBusinessCircles(PageBean pageBean, String searchId, String searchText) {
        try {
            // 设置跳转页面
            ModelAndView modeView = new ModelAndView("jsp/customer/businessCirclelist");
            // 设置查询条件
            modeView.addObject("searchId", searchId);
            modeView.addObject("searchText", searchText);
            pageBean.setUrl(REDIRECT);
            // 查询商圈列表
            modeView.addObject("pageBean", businessCircleService.findBusinessCircles(pageBean, searchId, searchText));
            return modeView;
        } catch (Exception e) {
            LOGGER.info("查询商圈错误！" + e);
            return null;
        }
    }

}
