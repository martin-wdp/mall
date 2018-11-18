/**
 * Copyright 2014 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.report.service.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.springframework.stereotype.Service;

import com.ningpai.common.lucene.main.LuceneIKUtil;
import com.ningpai.order.bean.Order;
import com.ningpai.order.service.OrderService;
import com.ningpai.report.bean.Report;
import com.ningpai.report.dao.ReportMapper;
import com.ningpai.report.service.ReportService;
import com.ningpai.report.util.DateUtil;
import com.ningpai.thirdaudit.bean.StoreInfo;
import com.ningpai.thirdaudit.mapper.StoreInfoMapper;
import com.ningpai.util.PageBean;

/**
 * @author Ningpai-HEHU
 * @version V1.0
 * @Description: np_report的service的实现类:
 * @date 2014-12-17 14:06:51
 */
@Service("ReportService")
public class ReportServiceImpl implements ReportService {

    /**
     * 记录日志对象
     */
    private static final Logger LOGGER = Logger.getLogger(LuceneIKUtil.class);

    /**
     * 时间格式化*
     */
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    private static final String REPORT = "report";
    private static final String ENDDATE = "endDate";

    /**
     * 订单业务接口
     */
    @Resource(name = "OrderService")
    private OrderService orderService;

    /**
     * 商家业务接口
     */
    @Resource(name = "storeMapper")
    private StoreInfoMapper storeInfoMapper;

    // Spring注入
    @Resource(name = "ReportMapper")
    private ReportMapper reportMapper;

    /**
     * 根据主键删除
     *
     * @param reportId
     * @return
     */
    @Override
    public int delete(Long reportId) {
        return this.reportMapper.deleteByPrimaryKey(reportId);
    }

    /**
     * 插入，空属性不会插入
     *
     * @param record
     * @return
     */
    @Override
    public int insert(Report record) {
        return this.reportMapper.insertSelective(record);
    }

    /**
     * 根据主键查询
     *
     * @param reportId
     * @return
     */
    @Override
    public Report select(Long reportId) {
        return this.reportMapper.selectByPrimaryKey(reportId);
    }

    /**
     * 根据主键修改，空值条件不会修改成null
     *
     * @param record
     * @return
     */
    @Override
    public int update(Report record) {

        return this.reportMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 删除多条记录
     *
     * @param reportIds
     * @return
     */
    @Override
    public int deleteMuilti(Long[] reportIds) {
        return this.reportMapper.deleteMuilti(reportIds);
    }

    /**
     * 分页查询列表
     *
     * @param record
     * @param pageBean
     * @return
     */
    @Override
    public PageBean selectList(Report record, PageBean pageBean) {
        pageBean.setObjectBean(record);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("pageBean", pageBean);
        pageBean.setRows(this.reportMapper.selectListCount(map));
        pageBean.setList(this.reportMapper.selectList(map));
        return pageBean;
    }

    /**
     * 根据参数查询报表
     *
     * @param record
     * @return
     */
    @Override
    public List<Object> selectSumListByParam(Report record) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(REPORT, record);
        return this.reportMapper.selectSumListByParam(map);
    }

    /**
     * 分页查询列表
     *
     * @param record
     * @param pageBean
     * @return
     */
    @Override
    public PageBean selectReportCateList(Report record, PageBean pageBean) {
        pageBean.setObjectBean(record);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("pageBean", pageBean);
        pageBean.setRows(this.reportMapper.selectReportCateListCount(map));
        pageBean.setList(this.reportMapper.selectReportCateList(map));
        return pageBean;
    }

    /**
     * 查询报表包含的订单
     *
     * @param reportId
     *            报表id
     * @param pb
     * @return
     */
    @Override
    public PageBean selectReportOrders(Long reportId, PageBean pb) {
        Report report = select(reportId);
        Order order = new Order();
        order.setBusinessId(report.getStoreId());
        // 这里值随便写，只要不为空即可
        order.setAlreadyPay("yes");
        order.setPayTimeStart(DateUtil.dateToString(report.getStartTime()));
        order.setPayTimeEnd(DateUtil.dateToString(report.getEndTime()));
        return orderService.searchOrderList(order, pb);
    }

    /**
     * 根据时间段，查询结算周期在这个时间段内的商家的订单金额、凤凰卡金额、银联付款金额
     *
     * @param startDate
     *            开始时间
     * @param endDate
     *            结束时间
     * @return
     */
    @Override
    public Object generateReport(String startDate, String endDate) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("startRowNum", 0);
        paramMap.put("endRowNum", 100000);
        List<Object> stores = storeInfoMapper.selectAuditList(paramMap);
        int days = DateUtil.getDays(startDate, endDate);
        for (int i = 0; i <= days; i++) {
            // 当前日期
            String currDate = DateUtil.addSomeDays(startDate, i);
            // 当月中的第几天
            int dayOfMonth = DateUtil.getDayOfMonth(currDate);

            for (Object o : stores) {
                StoreInfo storeInfo = (StoreInfo) o;
                String settleDates = storeInfo.getBillingCycle();
                if (settleDates == null) {
                    continue;
                }
                String[] countdates = settleDates.split("\\|");
                for (String str : countdates) {
                    // 说明有结算日期含当天的店铺
                    if (str.equals(dayOfMonth + "")) {
                        // 获取上一个结算日期
                        String lastCountDate = DateUtil.getLastCountDate(currDate, countdates);
                        if (lastCountDate == null) {
                            continue;
                        }
                        // 这里这么写，是因为客户后来要把结算日期算法往后推一天
                        paramMap.put("startDate", DateUtil.addOneDay(lastCountDate));
                        paramMap.put(ENDDATE, DateUtil.addOneDay(currDate));
                        paramMap.put("storeId", storeInfo.getStoreId());
                        insertIntoReport(paramMap);
                        continue;
                    }
                }
            }
        }
        return null;
    }

    /**
     * 插入
     *
     * @param paramMap
     */
    private void insertIntoReport(Map<String, Object> paramMap) {
        List<Report> datas = reportMapper.queryReportData(paramMap);

        // 这个日期已经加了1天了。从N天00点00分00秒，变成了N+1天00点00分00秒了，所以要给他变回来，减去一天
        paramMap.put(ENDDATE, DateUtil.subOneDay(paramMap.get(ENDDATE).toString()));
        Report r = reportMapper.selectOneByParam(paramMap);
        if (r != null) {
            return;
        }
        if (datas == null) {
            return;
        }
        for (Report report : datas) {
            if (report == null) {
                continue;
            }
            report.setStartTime(DateUtil.stringToDate(paramMap.get("startDate").toString()));
            report.setEndTime(DateUtil.stringToDate(paramMap.get(ENDDATE).toString()));
            report.setCreateTime(new Date());
            reportMapper.insertSelective(report);
        }
    }

    /**
     * 根据时间段，查询结算周期在这个时间段内的商家的订单金额、凤凰卡金额、银联付款金额
     */
    public void generateTodayReport() {
        generateReport(DateUtil.dateToString(new Date()), DateUtil.dateToString(new Date()));
    }

    /**
     * 根据商家id，删除报表记录
     *
     * @param storeId
     *            商家id
     */
    @Override
    public void deleteByReportByStoreId(Long storeId) {
        reportMapper.deleteByStoreId(storeId);
    }

    /**
     * 根据条件导出报表
     *
     * @param report
     *            报表查询参数
     * @param response
     */
    @Override
    public void exportSumReport(Report report, HttpServletResponse response) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(REPORT, report);
        List<Object> reports = selectSumListByParam(report);
        HSSFWorkbook wb = new HSSFWorkbook();
        CellStyle style = wb.createCellStyle();
        HSSFRow tempRow;
        style.setAlignment(CellStyle.ALIGN_CENTER);
        HSSFSheet sheet1 = wb.createSheet("对账报表");
        sheet1.setColumnWidth(0, 600);
        sheet1.setColumnWidth(1, 10000);
        sheet1.setColumnWidth(2, 3000);
        sheet1.setColumnWidth(3, 3000);
        sheet1.setColumnWidth(4, 3000);
        sheet1.createFreezePane(255, 1);
        HSSFRow headRow = sheet1.createRow(0);
        headRow.createCell(0).setCellValue("编号");
        headRow.createCell(1).setCellValue("店铺名称");
        headRow.createCell(2).setCellValue("总流水");
        headRow.createCell(3).setCellValue("订单优惠金额");
        headRow.createCell(4).setCellValue("商品优惠金额");
        if (null != reports && !reports.isEmpty()) {
            // 循环传递过来的货品列表,并添加到文件中
            for (int i = 0; i < reports.size(); i++) {
                Report r = (Report) reports.get(i);
                tempRow = sheet1.createRow(1 + i);
                tempRow.createCell(0).setCellValue(i + 1);
                tempRow.createCell(1).setCellValue(r.getStoreName());
                tempRow.createCell(2).setCellValue(r.getTotalOrderMoney().setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue());
                tempRow.createCell(3).setCellValue(r.getTotalOrderPrePrice().setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue());
                tempRow.createCell(4).setCellValue(r.getTotalGoodsPrePrice().setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue());

            }

        }
        String filename = "商家报表汇总_" + String.valueOf(System.currentTimeMillis()).concat(".xls");
        // 设置下载时客户端Excel的名称
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(filename));
        OutputStream ouputStream;
        try {
            ouputStream = response.getOutputStream();
            wb.write(ouputStream);
            ouputStream.flush();
            ouputStream.close();
        } catch (IOException e) {
            LOGGER.error("根据条件导出报表错误：" + e);
        } finally {
            wb = null;
            filename = null;
            ouputStream = null;
            style = null;
            tempRow = null;
        }
    }

    /**
     * 导出类目报表
     *
     * @param report
     * @param response
     */
    @Override
    public void exportCateReport(Report report, HttpServletResponse response) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(REPORT, report);
        List<Object> reports = selectStoreCateReportList(report);
        HSSFWorkbook wb = new HSSFWorkbook();
        CellStyle style = wb.createCellStyle();
        HSSFRow tempRow;
        style.setAlignment(CellStyle.ALIGN_CENTER);
        HSSFSheet sheet1 = wb.createSheet("对账报表");
        sheet1.setColumnWidth(0, 3000);
        sheet1.setColumnWidth(1, 10000);
        sheet1.setColumnWidth(2, 3000);
        sheet1.setColumnWidth(3, 3000);
        sheet1.setColumnWidth(4, 3000);
        sheet1.setColumnWidth(5, 3000);
        sheet1.setColumnWidth(6, 3000);
        sheet1.setColumnWidth(7, 5000);
        sheet1.setColumnWidth(8, 5000);
        sheet1.createFreezePane(255, 1);
        HSSFRow headRow = sheet1.createRow(0);
        headRow.createCell(0).setCellValue("编号");
        headRow.createCell(1).setCellValue("店铺名称");
        headRow.createCell(2).setCellValue("分类名称");
        headRow.createCell(3).setCellValue("分类扣率");
        headRow.createCell(4).setCellValue("总流水");
        headRow.createCell(5).setCellValue("订单优惠金额");
        headRow.createCell(6).setCellValue("商品优惠金额");
        headRow.createCell(7).setCellValue("报表时间");
        headRow.createCell(8).setCellValue("结算状态");
        if (null != reports && !reports.isEmpty()) {
            // 循环传递过来的货品列表,并添加到文件中
            for (int i = 0; i < reports.size(); i++) {
                Report r = (Report) reports.get(i);
                tempRow = sheet1.createRow(1 + i);
                tempRow.createCell(0).setCellValue(i + 1);
                tempRow.createCell(1).setCellValue(r.getStoreName());
                tempRow.createCell(2).setCellValue(r.getCateName());
                tempRow.createCell(3).setCellValue(r.getCateRate().setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue());
                tempRow.createCell(4).setCellValue(r.getTotalOrderMoney().setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue());
                tempRow.createCell(5).setCellValue(r.getTotalOrderPrePrice().setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue());
                tempRow.createCell(6).setCellValue(r.getTotalGoodsPrePrice().setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue());
                tempRow.createCell(7).setCellValue(sdf.format(r.getStartTime()) + "~" + sdf.format(r.getEndTime()));
                if ("0".equals(r.getSettleStatus())) {
                    tempRow.createCell(8).setCellValue("未结算");
                } else if ("1".equals(r.getSettleStatus())) {
                    tempRow.createCell(8).setCellValue("已结算");
                }

            }

        }
        String filename = "商家类目报表_" + String.valueOf(System.currentTimeMillis()).concat(".xls");
        // 设置下载时客户端Excel的名称
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(filename));
        OutputStream ouputStream;
        try {
            ouputStream = response.getOutputStream();
            wb.write(ouputStream);
            ouputStream.flush();
            ouputStream.close();
        } catch (IOException e) {
            LOGGER.error("导出类目报表：" + e);
        } finally {
            wb = null;
            filename = null;
            ouputStream = null;
            style = null;
            tempRow = null;
        }
    }

    /**
     * 查询
     *
     * @param report
     * @return List
     */
    private List<Object> selectStoreCateReportList(Report report) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(REPORT, report);
        return this.reportMapper.selectStoreCateReportList(map);
    }

    /**
     * 根据商店id，删除报表记录
     *
     * @param storeId
     *            商店id
     */
    @Override
    public void settleReport(Long storeId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("storeId", storeId);
        this.reportMapper.settleReport(map);
    }

    /**
     * 根据分类id，删除报表记录
     *
     * @param reportId
     */
    @Override
    public void settleReportById(Long reportId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("reportId", reportId);
        this.reportMapper.settleReport(map);
    }

}
