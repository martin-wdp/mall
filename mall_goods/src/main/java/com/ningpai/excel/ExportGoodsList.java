/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.excel;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;

import com.ningpai.goods.controller.GoodsController;
import com.ningpai.goods.vo.GoodsListVo;
import com.ningpai.goods.vo.GoodsProductReleSpecVo;
import com.ningpai.goods.vo.GoodsProductVo;
import com.ningpai.util.MyLogger;

/**
 * 导出商品列表数据
 *
 * @author NINGPAI-YuanKangKang
 * @since 2014年4月24日 上午9:33:30
 */
public final class ExportGoodsList {

    /**
     * 记录日志对象
     */
    private static final MyLogger LOGGER = new MyLogger(GoodsController.class);

    private ExportGoodsList() {
        super();
    }

    /**
     * 导出商品列表
     *
     * @param goodsList
     *            待导出的商品列表
     */
    public static void exportGoodsList(List<Object> goodsList, HttpServletResponse response) {
        // 创建文档
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet1 = wb.createSheet("商品列表");
        sheet1.setColumnWidth(0, 6000);
        sheet1.setColumnWidth(1, 15000);
        sheet1.setColumnWidth(2, 6000);
        sheet1.setColumnWidth(3, 6000);
        sheet1.setColumnWidth(4, 6000);
        sheet1.setColumnWidth(5, 4000);
        sheet1.createFreezePane(6, 2);
        HSSFRow row1 = sheet1.createRow(0);
        sheet1.addMergedRegion(new CellRangeAddress(0, 0, 0, 5));
        HSSFCell cell1 = row1.createCell(0);
        CellStyle cenStyle = wb.createCellStyle();
        cenStyle.setAlignment(CellStyle.ALIGN_CENTER);
        cell1.setCellStyle(cenStyle);
        cell1.setCellValue("商品列表");
        HSSFRow row2 = sheet1.createRow(1);
        row2.createCell(0).setCellValue("商品编号");
        row2.createCell(1).setCellValue("商品名称");
        row2.createCell(2).setCellValue("销售价格");
        row2.createCell(3).setCellValue("商品状态");
        row2.createCell(4).setCellValue("商品分类");
        row2.createCell(5).setCellValue("商品品牌");

        // 循环Bean并添加值到文件中
        HSSFRow tempRow;
        if (null != goodsList && !goodsList.isEmpty()) {
            for (int i = 0; i < goodsList.size(); i++) {
                GoodsListVo listVo = (GoodsListVo) goodsList.get(i);
                tempRow = sheet1.createRow(2 + i);
                tempRow.createCell(0).setCellValue(listVo.getGoodsNo());
                tempRow.createCell(1).setCellValue(listVo.getGoodsName());
                tempRow.createCell(2).setCellValue(String.valueOf(listVo.getGoodsPrice()));
                tempRow.createCell(3).setCellValue("1".equals(listVo.getGoodsAdded()) ? "上架销售中" : "下架");
                tempRow.createCell(4).setCellValue(listVo.getGoodsCate() == null ? "" : listVo.getGoodsCate().getCatName());
                tempRow.createCell(5).setCellValue(listVo.getGoodsBrand().getBrandName());
            }
        }
        String filename = String.valueOf(System.currentTimeMillis()).concat(".xls");
        // 设置下载时客户端Excel的名称
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename=" + filename);
        OutputStream ouputStream;
        try {
            ouputStream = response.getOutputStream();
            wb.write(ouputStream);
            ouputStream.flush();
            ouputStream.close();
        } catch (IOException e) {
            LOGGER.error("导出商品列表错误:" + e);
        }

    }

    /**
     * 导出货品列表
     *
     * @param productList
     *            待导出的货品列表
     * @throws Exception
     */
    public static void exportProductList(List<Object> productList, HttpServletResponse response) {
        GoodsProductReleSpecVo specVo;
        HSSFWorkbook wb = new HSSFWorkbook();
        CellStyle style = wb.createCellStyle();
        HSSFRow tempRow;
        style.setAlignment(CellStyle.ALIGN_CENTER);
        HSSFSheet sheet1 = wb.createSheet("货品列表");
        sheet1.createFreezePane(255, 1);
        HSSFRow headRow = sheet1.createRow(0);
        headRow.createCell(0).setCellValue("货品编号");
        headRow.createCell(1).setCellValue("货品名称");
        headRow.createCell(2).setCellValue("货品副标题");
        headRow.createCell(3).setCellValue("剩余库存");
        headRow.createCell(4).setCellValue("销售价格");
        headRow.createCell(5).setCellValue("成本价格");
        headRow.createCell(6).setCellValue("市场价格");
        headRow.createCell(7).setCellValue("货品状态");

        if (null != productList && !productList.isEmpty()) {
            GoodsProductVo product = (GoodsProductVo) productList.get(0);
            // 循环获取货品关联的规格值,并设置表头
            for (int i = 0; i < product.getSpecVo().size(); i++) {
                specVo = product.getSpecVo().get(i);
                headRow.createCell(8 + i).setCellValue(specVo.getSpec().getSpecName());
            }
            // 循环传递过来的货品列表,并添加到文件中
            for (int i = 0; i < productList.size(); i++) {
                product = (GoodsProductVo) productList.get(i);
                tempRow = sheet1.createRow(1 + i);
                tempRow.createCell(0).setCellValue(product.getGoodsInfoItemNo());
                tempRow.createCell(1).setCellValue(product.getGoodsInfoName());
                tempRow.createCell(2).setCellValue(product.getGoodsInfoSubtitle());
                tempRow.createCell(3).setCellValue(product.getGoodsInfoStock());
                tempRow.createCell(4).setCellValue(Double.parseDouble(String.valueOf(product.getGoodsInfoPreferPrice())));
                tempRow.createCell(5).setCellValue(Double.parseDouble(String.valueOf(product.getGoodsInfoCostPrice())));
                tempRow.createCell(6).setCellValue(Double.parseDouble(String.valueOf(product.getGoodsInfoMarketPrice())));
                tempRow.createCell(7).setCellValue("1".equals(product.getGoodsInfoAdded()) ? "上架销售中" : "下架");
                // 循环获取货品关联的规格值,并设置表头
                for (int j = 0; j < product.getSpecVo().size(); j++) {
                    specVo = product.getSpecVo().get(j);
                    tempRow.createCell(8 + j).setCellValue(specVo.getSpecValueRemark());
                }

            }

        }
        String filename = String.valueOf(System.currentTimeMillis()).concat(".xls");
        // 设置下载时客户端Excel的名称
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename=" + filename);
        OutputStream ouputStream;
        try {
            ouputStream = response.getOutputStream();
            wb.write(ouputStream);
            ouputStream.flush();
            ouputStream.close();
        } catch (IOException e) {
            LOGGER.error("导出货品列表错误：" + e);
        } finally {
            specVo = null;
            wb = null;
            filename = null;
            ouputStream = null;
            style = null;
            tempRow = null;
        }
    }

}
