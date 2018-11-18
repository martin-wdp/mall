package com.ningpai.system.controller;

import com.ningpai.system.bean.LogisticsCompany;
import com.ningpai.system.bean.LogisticsSingle;
import com.ningpai.system.service.ILogisticsCompanyBiz;
import com.ningpai.system.service.LogisticsSingleService;
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
 * 快递单模板控制器
 * 
 * @author rennn
 *
 */
@Controller
public class LogisticsSingleController {
    // 物流单模板
    private LogisticsSingleService logisticsSingleService;
    // 物流公司
    private ILogisticsCompanyBiz iLogisticsCompanyBiz;

    /**
     * 获得所有Boss 快递单模板
     * 
     * @return
     */

    @RequestMapping("/logisticssingle")
    public ModelAndView getLogisticsSingleList(PageBean pb,
            HttpServletRequest request, Long n) {
        // Boss thirdId=0
        Long thirdId = 0L;
        ModelAndView mav = null;
        if (n != null) {
            // 第三方
            thirdId = (Long) request.getSession().getAttribute("thirdId");
            mav = new ModelAndView("logisticsSingle/third_logisticsSingle_List");
        } else {
            // boss
            mav = new ModelAndView("jsp/logisticsSingle/logisticsSingle_List");
        }

        mav.addObject("pb",
                logisticsSingleService.getLogisticsSingleList(pb, thirdId));

        return mav;
    }

    /**
     * 跳转到Boss添加快递单模板
     * 
     * @return ModelAndView
     */
    @RequestMapping("/toAddLogisticsSingle")
    public ModelAndView toAddLogisticsSingle() {
        // 获得boss的物流公司信息
        List<LogisticsCompany> companys = iLogisticsCompanyBiz
                .queryAllLogisticsCompany();

        return new ModelAndView("jsp/logisticsSingle/addLogisticsSingle")
                .addObject("companys", companys);
    }

    /**
     * 跳转到boss物流单模板修改页面
     * 
     * @param logisticsSingleId
     * @return
     */
    @RequestMapping("/toUpdateLogisticsSingle")
    public ModelAndView toUpdateLogisticsSingle(Long logisticsSingleId) {
        LogisticsSingle logisticsSingle = logisticsSingleService
                .selectLogisticsSingleById(logisticsSingleId, 0L);
        return new ModelAndView("jsp/logisticsSingle/updateLogisticsSingle")
                .addObject("logisticsSingle", logisticsSingle);
    }

    /**
     * 验证模板是否添加
     * 
     * @param thirdId
     * @param companyId
     * @return LogisticsSingle
     */
    @RequestMapping("/selectLogisticsSingle")
    @ResponseBody
    public LogisticsSingle selectLogisticsSingle(Long thirdId, Long companyId,
            HttpServletRequest request) {
        Long thirdIdNew =  thirdId;
        if (thirdIdNew == null) {
            thirdIdNew = (Long) request.getSession().getAttribute("thirdId");
        }
        return logisticsSingleService.selectLogisticsSingle(thirdIdNew, companyId);
    }

    /**
     * 删除物流模板
     * 
     * @return LogisticsSingle
     */
    @RequestMapping("/dellogisticssingle")
    @ResponseBody
    public int delLogisticsSingle(Long logisticsSingleId) {

        return logisticsSingleService.delLogisticsSingleById(logisticsSingleId);
    }

    /**
     * 添加物流单模板信息
     * 
     * @param logisticsSingle
     * @return ModelAndView
     */
    @RequestMapping("/addlogisticssingle")
    public ModelAndView addLogisticsSingle(LogisticsSingle logisticsSingle) {

        logisticsSingle.setCreateTime(new Date());// 创建时间
        logisticsSingle.setDelFlag("0");// 默认没有删除
        if (logisticsSingleService.addLogisticsSingle(logisticsSingle) > 0) {
            // return new ModelAndView("redirect:logisticssingle.htm");
            return new ModelAndView(new RedirectView("logisticssingle.htm"));
        }
        return null;
    }

    /**
     * 修改物流单模板信息
     * 
     * @param logisticsSingle
     * @return ModelAndView
     */
    @RequestMapping("/updatelogisticssingle")
    public ModelAndView updateLogisticsSingle(LogisticsSingle logisticsSingle) {
        // 修改成功
        if (logisticsSingleService.updateLogisticsSingle(logisticsSingle) > 0) {
            // 返回第三放的物流单模板列表
            if (logisticsSingle.getThirdId() > 0) {
                return new ModelAndView(new RedirectView(
                        "logisticssingle.htm?n=1"));
            }
            // 返回boss的物流单模板列表
            return new ModelAndView(new RedirectView("logisticssingle.htm"));
        }
        return null;
    }

    public LogisticsSingleService getLogisticsSingleService() {
        return logisticsSingleService;
    }

    @Resource(name = "LogisticsSingleService")
    public void setLogisticsSingleService(
            LogisticsSingleService logisticsSingleService) {
        this.logisticsSingleService = logisticsSingleService;
    }

    public ILogisticsCompanyBiz getiLogisticsCompanyBiz() {
        return iLogisticsCompanyBiz;
    }

    @Resource(name = "logisticsCompanyBizImpl")
    public void setiLogisticsCompanyBiz(
            ILogisticsCompanyBiz iLogisticsCompanyBiz) {
        this.iLogisticsCompanyBiz = iLogisticsCompanyBiz;
    }

}
