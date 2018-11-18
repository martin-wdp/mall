/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.logger.util;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.ningpai.util.MyLogger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;

import com.ningpai.logger.bean.OperationLog;
import com.ningpai.util.UtilDate;

/**
 * 日志导出工具
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年6月24日 下午4:32:13
 * @version 0.0.1
 */
public final class ExportLog {

    /**
     * 日志
     * */
    public static final MyLogger LOGGER = new MyLogger(ExportLog.class);

    private static final int WIDTH4000 = 4000;
    private static final int WIDTH6000 = 6000;
    private static final int WIDTH15000 = 15000;
    private static final int WIDTH3 = 3;
    private static final int WIDTH4 = 4;
    private static final int WIDTH5 = 5;
    private static final int WIDTH6 = 6;

    private ExportLog() {
    }

    /**
     * 日志导出
     * 
     * @param loggerList
     *            日志列表
     * @param response
     */
    public static void exportForLog(List<Object> loggerList, HttpServletResponse response) {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet hssfSheet = wb.createSheet("日志列表");
        hssfSheet.setColumnWidth(0, WIDTH4000);
        hssfSheet.setColumnWidth(1, WIDTH6000);
        hssfSheet.setColumnWidth(2, WIDTH6000);
        hssfSheet.setColumnWidth(WIDTH3, WIDTH6000);
        hssfSheet.setColumnWidth(WIDTH4, WIDTH6000);
        hssfSheet.setColumnWidth(WIDTH5, WIDTH15000);
        hssfSheet.createFreezePane(WIDTH6, 2);
        HSSFRow row1 = hssfSheet.createRow(0);
        hssfSheet.addMergedRegion(new CellRangeAddress(0, 0, 0, WIDTH5));
        HSSFCell cell1 = row1.createCell(0);
        CellStyle cenStyle = wb.createCellStyle();
        cenStyle.setAlignment(CellStyle.ALIGN_CENTER);
        cell1.setCellStyle(cenStyle);
        cell1.setCellValue("日志列表");
        // 创建表头
        HSSFRow titleRow = hssfSheet.createRow(1);
        titleRow.createCell(0).setCellValue("日志编号");
        titleRow.createCell(1).setCellValue("操作人");
        titleRow.createCell(2).setCellValue("操作人IP");
        titleRow.createCell(WIDTH3).setCellValue("操作类型");
        titleRow.createCell(WIDTH4).setCellValue("操作时间");
        titleRow.createCell(WIDTH5).setCellValue("操作内容");

        HSSFRow hssfRow;
        if (null != loggerList && !loggerList.isEmpty()) {
            for (int i = 0; i < loggerList.size(); i++) {
                OperationLog log = (OperationLog) loggerList.get(i);
                hssfRow = hssfSheet.createRow(2 + i);
                hssfRow.createCell(0).setCellValue(log.getOpId());
                hssfRow.createCell(1).setCellValue(log.getOpName());
                hssfRow.createCell(2).setCellValue(log.getOpIp());
                hssfRow.createCell(WIDTH3).setCellValue(log.getOpCode());
                hssfRow.createCell(WIDTH4).setCellValue(UtilDate.dataFormat(log.getOpTime()));
                hssfRow.createCell(WIDTH5).setCellValue(log.getOpContent());
            }
        }
        String filename = String.valueOf(System.currentTimeMillis()).concat(".xls");
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename=" + filename);
        OutputStream ouputStream;
        try {
            ouputStream = response.getOutputStream();
            wb.write(ouputStream);
            ouputStream.flush();
            ouputStream.close();
        } catch (IOException e) {
            LOGGER.error(""+e);
            ouputStream = null;
        }
    }
}
