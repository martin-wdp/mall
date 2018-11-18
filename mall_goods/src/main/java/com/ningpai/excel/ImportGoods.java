package com.ningpai.excel;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Repository;

import com.ningpai.customer.bean.Customer;
import com.ningpai.goods.bean.GoodsBrand;
import com.ningpai.goods.bean.GoodsCate;
import com.ningpai.goods.bean.GoodsImport;
import com.ningpai.goods.controller.GoodsController;
import com.ningpai.goods.dao.GoodsBrandMapper;
import com.ningpai.goods.dao.GoodsCateMapper;
import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.util.MyLogger;

/**
 * 商品导入类
 * */
@Repository("ImportGoods")
public class ImportGoods {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(GoodsController.class);

    private static final String RESULT = "result";
    private static final String LOGGERINFO1 = ")已存在！";

    @Resource(name = "GoodsCateMapper")
    private GoodsCateMapper goodsCateMapper;

    @Resource(name = "GoodsBrandMapper")
    private GoodsBrandMapper goodsBrandMapper;

    /**
     * 导出导入模板
     * 
     * @throws Exception
     */
    public void exportProductList(HttpServletResponse response) {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet1 = wb.createSheet("new Sheet");
        sheet1.createFreezePane(0, 1, 0, 5);
        sheet1.setColumnWidth(0, 4000);
        sheet1.setColumnWidth(1, 4000);
        sheet1.setColumnWidth(2, 4000);
        sheet1.setColumnWidth(3, 4000);
        sheet1.setColumnWidth(4, 4000);
        HSSFRow row = sheet1.createRow((short) 0);// 创建第一行
        HSSFCell cell;
        cell = row.createCell(0);// 创建第一列
        cell.setCellValue("商品名称");
        cell = row.createCell(1);
        cell.setCellValue("商品副标题");
        cell = row.createCell(2);
        cell.setCellValue("销售价格");
        cell = row.createCell(3);
        cell.setCellValue("成本价格");
        cell = row.createCell(4);
        cell.setCellValue("市场价格");
        cell = row.createCell(5);
        cell.setCellValue("SEO标签");
        cell = row.createCell(6);
        cell.setCellValue("SEO关键字");
        cell = row.createCell(7);
        cell.setCellValue("SEO详细优化");
        cell = row.createCell(8);
        // cell.setCellValue("商品详情");
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
            LOGGER.error("导出导入模板出错：" + e);
        }
    }

    /**
     * 解析上传的Excel文件
     * 
     * @param is
     *            流
     * @return 解析好的商品列表
     */
    public List<GoodsImport> importGoods(InputStream is) {
        List<GoodsImport> goodsList = new ArrayList<GoodsImport>();
        POIFSFileSystem fs;
        HSSFWorkbook wb;
        try {
            try {
                fs = new POIFSFileSystem(is);
                wb = new HSSFWorkbook(fs);
            } catch (IOException e) {
                LOGGER.error(""+e);
                return Collections.emptyList();
            }
            for (int numSheet = 0; numSheet < wb.getNumberOfSheets(); numSheet++) {

                HSSFSheet hssfSheet = wb.getSheetAt(numSheet);
                if (hssfSheet == null) {
                    continue;
                }

                for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                    HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                    if (hssfRow == null) {
                        continue;
                    }
                    GoodsImport goods = new GoodsImport();
                    // 循环列Cell
                    // 0学号 1姓名 2学院 3课程名 4 成绩
                    // for (int cellNum = 0; cellNum <=4; cellNum++) {
                    HSSFCell xh = hssfRow.getCell(0);
                    if (xh == null) {
                        continue;
                    }

                    goods.setGoodsName(getValue(xh));

                    // 设置商品副标题
                    HSSFCell xm = hssfRow.getCell(1);
                    goods.setGoodsSubtitle(getValue(xm));

                    // 设置商品销售价格
                    HSSFCell pri = hssfRow.getCell(2);
                    goods.setGoodsPrice(new BigDecimal(getValue(pri)));

                    // 设置商品成本价格
                    HSSFCell cp = hssfRow.getCell(3);
                    goods.setGoodsCostPrice(new BigDecimal(getValue(cp)));

                    // 设置商品市场价格
                    HSSFCell mp = hssfRow.getCell(4);
                    goods.setGoodsMarketPrice(new BigDecimal(getValue(mp)));

                    // 设置商品SEO标题
                    HSSFCell se = hssfRow.getCell(5);
                    goods.setSeoTit(getValue(se));

                    // 设置商品SEO关键字
                    HSSFCell sekey = hssfRow.getCell(6);
                    goods.setSeoKey(getValue(sekey));

                    // 设置商品SEO介绍
                    HSSFCell seodes = hssfRow.getCell(7);
                    goods.setSeoDes(getValue(seodes));

                    // 设置商品详情
                    // HSSFCell des = hssfRow.getCell(8);
                    // goods.setGoodsDes(getValue(des));
                    goodsList.add(goods);
                }
            }

            return goodsList;
        } catch (Exception e) {
            LOGGER.error(""+e);
            return Collections.emptyList();
        }
    }

    /**
     * 解析上传的Excel文件
     * 
     * @param is
     *            流
     * @return 501:分类id为空 502:分类id不是数字 503:分类名称为空 504：类型id为空 505：类型id不是数字
     *         506：分类扣率不是数字或小数 507：分类排序不是数字 508：父类id不是数字 509：分类等级为空 510：分类等级不是数字
     * 
     */
    public Map<String, Object> importGoodsCate(InputStream is) {
        Map<String, Object> map = new HashMap<String, Object>();

        List<GoodsCate> cateList = new ArrayList<GoodsCate>();
        GoodsCate cate;
        POIFSFileSystem fs;
        HSSFWorkbook wb;
        HSSFSheet sheet;
        HSSFRow row;
        int rows;
        try {
            try {
                fs = new POIFSFileSystem(is);
                wb = new HSSFWorkbook(fs);
            } catch (IOException e) {
                LOGGER.error(""+e);
                return null;
            }
            sheet = wb.getSheetAt(0);
            rows = sheet.getPhysicalNumberOfRows();
            Pattern numberPattern = Pattern.compile("[0-9]*");
            Pattern decimalPattern = Pattern.compile("([1-9]+[0-9]*|0)(\\.[\\d]+)?");
            for (int i = 1; i < rows; i++) {
                cate = new GoodsCate();
                row = sheet.getRow(i);

                // 1、设置分类id
                row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
                String catIdStr = row.getCell(0).getStringCellValue();
                if (catIdStr == null || "".equals(catIdStr)) {
                    map.put(RESULT, "501：第" + i + "行分类id不能为空");
                    return map;
                }
                Matcher isNum = numberPattern.matcher(catIdStr);
                // 分类id不是数字
                if (!isNum.matches()) {
                    map.put(RESULT, "502：第" + i + "行分类id不是数字");
                    return map;
                }
                if (goodsCateMapper.selectByPrimaryKey(Long.parseLong(catIdStr)) != null) {
                    map.put(RESULT, "5021：第" + i + "行分类id(" + catIdStr + LOGGERINFO1);
                    return map;
                }
                cate.setCatId(Long.parseLong(catIdStr));

                // 2、设置分类名称
                row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                String cateName = row.getCell(1).getStringCellValue();
                if (cateName == null || "".equals(cateName)) {
                    map.put(RESULT, "503:第" + i + "行分类名称为空");
                    return map;
                }
                if (goodsCateMapper.queryCateByCateName(cateName) != null) {
                    map.put(RESULT, "5031:第" + i + "行分类名称（" + cateName + "）已存在");
                    return map;
                }
                cate.setCatName(cateName);

                // 3、设置分类类型id
                row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
                String typeIdStr = row.getCell(2).getStringCellValue();
                if (typeIdStr == null || "".equals(typeIdStr)) {
                    map.put(RESULT, "504：第" + i + "行分类类型为空");
                    return map;
                }
                isNum = numberPattern.matcher(typeIdStr);
                // 分类id不是数字
                if (!"".equals(typeIdStr) && !isNum.matches()) {
                    map.put(RESULT, "505：第" + i + "行分类id不是数字");
                    return map;
                }
                if (!"".equals(typeIdStr)) {
                    cate.setTypeId(Long.parseLong(typeIdStr));
                }

                // 4、设置分类扣率
                row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
                String cateRateStr = row.getCell(3).getStringCellValue();
                Matcher isDecimal = decimalPattern.matcher(cateRateStr);
                // 分类扣率不是数字
                if (!"".equals(cateRateStr) && !isDecimal.matches()) {
                    map.put(RESULT, "506：第" + i + "行分类扣率不是数字");
                    return map;
                }
                cate.setCatRate(new BigDecimal(cateRateStr));

                // 5、设置分类排序
                if (row.getCell(4) != null) {
                    row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
                    String cateSortStr = row.getCell(4).getStringCellValue();
                    isNum = numberPattern.matcher(cateSortStr);
                    // 分类排序不是数字
                    if (!"".equals(cateSortStr) && !isNum.matches()) {
                        map.put(RESULT, "507：第" + i + "行分类排序不是数字");
                        return map;
                    }
                    if (!"".equals(cateSortStr)) {
                        cate.setCatSort(Integer.parseInt(cateSortStr));
                    }
                }

                // 11、设置分类级别
                row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
                String cateGradStr = row.getCell(5).getStringCellValue();
                isNum = numberPattern.matcher(cateGradStr);

                if ("".equals(cateGradStr)) {
                    map.put(RESULT, "508：第" + i + "行分类级别为空");// 分类级别是空
                    return map;
                }
                // 分类级别不是数字
                if (!"".equals(cateGradStr) && !isNum.matches()) {
                    map.put(RESULT, "509：第" + i + "行分类级别不是数字");
                    return map;
                }
                cate.setCatGrade(Integer.parseInt(cateGradStr));

                // 6、设置SEO标题
                if (row.getCell(6) != null) {
                    row.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
                    String seoTitle = row.getCell(6).getStringCellValue();
                    cate.setCatSeoTitle(seoTitle);
                }

                // 7、设置SEO关键词
                if (row.getCell(7) != null) {
                    row.getCell(7).setCellType(Cell.CELL_TYPE_STRING);
                    String seoKey = row.getCell(7).getStringCellValue();
                    cate.setCatSeoKeyword(seoKey);
                }

                // 8、设置SEO说明
                if (row.getCell(8) != null) {
                    row.getCell(8).setCellType(Cell.CELL_TYPE_STRING);
                    String seoDesc = row.getCell(8).getStringCellValue();
                    cate.setCatSeoDesc(seoDesc);
                }

                // 10、设置父类id
                row.getCell(9).setCellType(Cell.CELL_TYPE_STRING);
                String cateParentIdStr = row.getCell(9).getStringCellValue();
                if (cateParentIdStr == null || "".equals(cateParentIdStr)) {
                    map.put(RESULT, "510：第" + i + "行父类id为空");
                    return map;
                }
                isNum = numberPattern.matcher(cateParentIdStr);
                // 父类id不是数字
                if (!isNum.matches()) {
                    map.put(RESULT, "511：第" + i + "行父类id不是数字");
                    return map;
                }
                if (!"".equals(cateParentIdStr)) {
                    cate.setCatParentId(Long.parseLong(cateParentIdStr));
                }

                cateList.add(cate);
            }
            map.put("cateList", cateList);
            return map;
        } catch (Exception e) {
            LOGGER.error(""+e);
            map.put(RESULT, "400");
            return map;
        }
    }

    /**
     * 导入商品品牌
     * 
     * @param is
     * @return
     */
    public Map<String, Object> importGoodsBrand(HttpServletRequest request, InputStream is) {
        Map<String, Object> map = new HashMap<String, Object>();

        List<GoodsBrand> brandList = new ArrayList<GoodsBrand>();
        GoodsBrand brand;
        POIFSFileSystem fs;
        HSSFWorkbook wb;
        HSSFSheet sheet;
        HSSFRow row;
        int rows;
        try {
            try {
                fs = new POIFSFileSystem(is);
                wb = new HSSFWorkbook(fs);
            } catch (IOException e) {
                LOGGER.error(""+e);
                return null;
            }
            sheet = wb.getSheetAt(0);
            rows = sheet.getPhysicalNumberOfRows();
            Pattern numberPattern = Pattern.compile("[0-9]*");
            for (int i = 1; i < rows; i++) {
                brand = new GoodsBrand();
                row = sheet.getRow(i);

                // 1、设置品牌名称
                row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
                String brandName = row.getCell(0).getStringCellValue();
                if (goodsBrandMapper.queryBrandByBrandName(brandName) > 0) {
                    map.put(RESULT, "5011:第" + i + "行品牌名称(" + brandName + LOGGERINFO1);
                    return map;
                }
                if (brandName == null || "".equals(brandName)) {
                    map.put(RESULT, "501:第" + i + "行品牌名称为空");
                    return map;
                }
                if (goodsBrandMapper.queryByBrandName(brandName) > 0) {
                    map.put(RESULT, "5011:第" + i + "行品牌名称(" + brandName + LOGGERINFO1);
                    return map;
                }
                brand.setBrandName(brandName);

                // 2、设置品牌别名
                if (row.getCell(1) != null) {
                    row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                    String brandNickName = row.getCell(1).getStringCellValue();
                    brand.setBrandNickname(brandNickName);
                }

                // 3、设置品牌排序
                if (row.getCell(2) == null) {
                    map.put(RESULT, "5021:第" + i + "行品牌排序为空！");
                    return map;
                }
                row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
                String brandSortStr = row.getCell(2).getStringCellValue();
                Matcher isNum = numberPattern.matcher(brandSortStr);
                // 分类排序不是数字
                if (!"".equals(brandSortStr) && !isNum.matches()) {
                    map.put(RESULT, "502:第" + i + "行排序字段不是数字");
                    return map;
                }
                if (!"".equals(brandSortStr)) {
                    brand.setBrandSort(Integer.parseInt(brandSortStr));
                }

                // 4、设置品牌网址
                if (row.getCell(3) != null) {
                    row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
                    String brandUrl = row.getCell(3).getStringCellValue();
                    brand.setBrandUrl(brandUrl);
                }

                // 4、设置品牌logo
                if (row.getCell(4) != null) {
                    row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
                    String brandLogo = row.getCell(4).getStringCellValue();
                    brand.setBrandLogo(brandLogo);
                }

                // 5、设置SEO标题
                if (row.getCell(5) != null) {
                    row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
                    String seoTitle = row.getCell(5).getStringCellValue();
                    brand.setBrandSeoTitle(seoTitle);
                }

                // 6、设置SEO关键词
                if (row.getCell(6) != null) {
                    row.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
                    String seoKey = row.getCell(6).getStringCellValue();
                    brand.setBrandSeoKeyword(seoKey);
                }

                // 7、设置SEO说明
                if (row.getCell(7) != null) {
                    row.getCell(7).setCellType(Cell.CELL_TYPE_STRING);
                    String seoDesc = row.getCell(7).getStringCellValue();
                    brand.setBrandSeoDesc(seoDesc);
                }

                brandList.add(brand);
            }
            map.put("brandList", brandList);
            return map;
        } catch (Exception e) {
            LOGGER.error(""+e);
            Customer cust = (Customer) request.getSession().getAttribute("cust");
            OperaLogUtil.addOperaException(cust.getCustomerUsername(), e, request);
            map.put(RESULT, "400");
            return map;
        }
    }

    /**
     * 获取值
     *
     * */
    private String getValue(HSSFCell hssfCell) {
        if (hssfCell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
            // 返回布尔类型的值
            return String.valueOf(hssfCell.getBooleanCellValue());
        } else if (hssfCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            // 返回数值类型的值
            return String.valueOf(hssfCell.getNumericCellValue());
        } else {
            // 返回字符串类型的值
            return String.valueOf(hssfCell.getStringCellValue());
        }
    }

}
