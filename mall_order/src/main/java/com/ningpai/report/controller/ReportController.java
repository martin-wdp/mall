package com.ningpai.report.controller;

import com.ningpai.report.bean.Report;
import com.ningpai.report.service.ReportService;
import com.ningpai.report.util.DateUtil;
import com.ningpai.thirdaudit.service.AuditService;
import com.ningpai.util.PageBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 商家对账控制器
 * 
 * @author NP-Heh
 * 
 */
@Controller
public class ReportController {
    /** 报表Service */
    @Resource(name = "ReportService")
    private ReportService reportService;
    
    @Resource(name="auditService")
    private AuditService auditService;

//    /** 导出工具Service */
//    @Resource(name = "exportService")
//    private ExportService exportService;

    /**
     * 报表列表
     * 
     * @param pb
     *            分页对象
     * @param report
     *            查询对象
     * @param startDate
     *            查询报表日期→开始时间
     * @param endDate
     *            查询报表日期→结束时间
     * @return
     */
    @RequestMapping("checkReport")
    public ModelAndView checkReport(PageBean pb, Report report, String startDate, String endDate,String attrName,String attrValue) {
        pb.setUrl("checkReport.htm");
        if (endDate != null && !"".equals(endDate)) {
            report.setEndDate(DateUtil.addOneDay(endDate));
        }
        if (startDate != null && !"".equals(startDate)) {
            report.setStartDate(startDate);
        } 
        Map<String, String> dateMap = new HashMap<String, String>();
        dateMap.put("today", DateUtil.dateToString(new Date()));
        dateMap.put("recentOneMonth", DateUtil.getDateOfLastNMonth(DateUtil.dateToString(new Date()),1));
        dateMap.put("recentThreeMonth", DateUtil.getDateOfLastNMonth(DateUtil.dateToString(new Date()),3));
        dateMap.put("recentSixMonth", DateUtil.getDateOfLastNMonth(DateUtil.dateToString(new Date()),6));
        return new ModelAndView("jsp/report/reportlist")
                .addObject("pb", reportService.selectList(report, pb))
                .addObject("startDate", startDate)
                .addObject("endDate", endDate)
                .addObject("dateMap", dateMap)
                .addObject("attrName", attrName)
                .addObject("attrValue", attrValue)
                .addObject("storeName",report.getStoreName())
                .addObject("totalOrderMoney",report.getTotalOrderMoney());
                
    }

    /**
     * 查询报表分类
     * @param pb
     * @param report
     * @param attrName
     * @param attrValue
     * @return
     */
    @RequestMapping("queryReportCate")
    public ModelAndView queryReportCate(PageBean pb,Report report,String attrName,String attrValue) {
        pb.setUrl("queryReportCate.htm");
        Map<String, String> dateMap = new HashMap<String, String>();
        dateMap.put("today", DateUtil.dateToString(new Date()));
        dateMap.put("recentOneMonth", DateUtil.getDateOfLastNMonth(DateUtil.dateToString(new Date()),1));
        dateMap.put("recentThreeMonth", DateUtil.getDateOfLastNMonth(DateUtil.dateToString(new Date()),3));
        dateMap.put("recentSixMonth", DateUtil.getDateOfLastNMonth(DateUtil.dateToString(new Date()),6));
        return new ModelAndView("jsp/report/reportcatelist")
                .addObject("pb", reportService.selectReportCateList(report, pb))
                .addObject("report", report)
                .addObject("store", auditService.selectByCustomerId(report.getStoreId()))
                .addObject("dateMap", dateMap)
                .addObject("attrName",attrName)
                .addObject("attrValue",attrValue)
                .addObject("cateName",report.getCateName())
                .addObject("totalOrderMoney",report.getTotalOrderMoney());
    }

    /**
     * 生成报表
     * 
     * @param startDate
     *            从该日期开始，根据每个商家的结算周期开始检查，并生成报表
     * @param endDate
     *            报表截止日期
     * @param request
     * @param response
     * @return
     * @throws java.io.IOException
     */
    @RequestMapping("generatReport")
    public ModelAndView generatReport(String startDate, String endDate, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String startDateNew = startDate;
        String endDateNew = endDate;
        if (startDateNew == null || "".equals(startDateNew)) {
            startDateNew = DateUtil.getDateOfLastMonth(DateUtil.dateToString(new Date()));
        }
        if (endDateNew == null || "".equals(endDateNew)) {
            endDateNew = DateUtil.dateToString(new Date());
        }
        reportService.generateReport(startDateNew, endDateNew);
        return new ModelAndView(new RedirectView("checkReport.htm"));
    }

    /**
     * 将报表状态改为已结算
     * 
     * @param reportId
     *            报表的Id
     * @return
     */
    @RequestMapping("settleReport")
    @ResponseBody
    public String settleReport(Long reportId) {
        Report report = new Report();
        report.setReportId(reportId);
        report.setSettleStatus("1");
        reportService.update(report);
        return "1";
    }

    /**
     * 根据商家id删除报表
     * 
     * @param storeIds
     *            商家id
     * @return
     */
    @RequestMapping("deleReportByStoreId")
    public ModelAndView deleReport(Long[] storeIds,HttpServletRequest request) {
        for(Long storeId:storeIds) {
            reportService.deleteByReportByStoreId(storeId);
        }
        return new ModelAndView("redirect:checkReport.htm?CSRFToken="+request.getSession().getAttribute("token"));
    }

    /**
     * 根据报表id删除报表
     * @param reportIds
     * @param storeId
     * @param request
     * @return
     */
    @RequestMapping("deleReportById")
    public ModelAndView deleReportById(Long[] reportIds,Long storeId,HttpServletRequest request) {
        for(Long reportId:reportIds) {
            reportService.delete(reportId);
        }
        return new ModelAndView("redirect:queryReportCate.htm?CSRFToken="+request.getSession().getAttribute("token")+"&storeId="+storeId);
    }

    /**
     * 报表详情
     * 
     * @param reportId
     *            报表ID
     * @return
     */
    @RequestMapping("reportDetail")
    public ModelAndView reportDetail(Long reportId) {
        return new ModelAndView("jsp/report/reportdetail").addObject("report", reportService.select(reportId));
    }
    /**
     * 分类报表详情
     *
     * @param reportId
     *            报表ID
     * @return
     */
    @RequestMapping("catereportDetail")
    @ResponseBody
    public Report catereportDetail(Long reportId) {
        Report report=null;
        if(null!=reportId){
          report=reportService.select(reportId);
        }
        return report;
    }

    /**
     * 导出汇总报表
     * 
     * @param report
     *            查询报表对象
     * @param response
     */
    @RequestMapping("exportSumReport")
    public void exportSumReport(Report report,HttpServletResponse response) {
        if (report.getStartDate() == null || "".equals(report.getStartDate())) {
            report.setStartDate(DateUtil.getDateOfLastMonth(DateUtil.dateToString(new Date())));
        }
        if (report.getEndDate() == null || "".equals(report.getEndDate())) {
            report.setEndDate(DateUtil.dateToString(new Date()));
        }
        report.setTimeType("1");
        reportService.exportSumReport(report, response);
    }
    
    
    /**
     * 导出类目报表
     * 
     * @param report
     *            查询报表对象
     * @param response
     */
    @RequestMapping("exportCateReport")
    public void exportCateReport(Report report,HttpServletResponse response) {
        if (report.getStartDate() == null || "".equals(report.getStartDate())) {
            report.setStartDate(DateUtil.getDateOfLastMonth(DateUtil.dateToString(new Date())));
        }
        if (report.getEndDate() == null || "".equals(report.getEndDate())) {
            report.setEndDate(DateUtil.dateToString(new Date()));
        }
        //按开始时间查询
        report.setTimeType("1");
        reportService.exportCateReport(report, response);
    }

    /**
     * 根据商店id和分类id，删除报表记录
     * @param storeId
     * @return
     */
    @RequestMapping("settleReportByStoreId")
    @ResponseBody
    public int settleReportByStoreId(Long storeId) {
        reportService.settleReport(storeId);
        return 1;
    }

    /**
     * 根据分类id，删除报表记录
     * @param reportId
     * @return
     */
    @RequestMapping("settleReportById")
    @ResponseBody
    public int settleReportById(Long reportId) {
        reportService.settleReportById(reportId);
        return 1;
    }

}
