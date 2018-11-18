package com.ningpai.customer.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.ningpai.util.MyLogger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ningpai.customer.service.CustomerServiceMapper;
import com.ningpai.other.bean.CustomerAllInfo;

/**
 * 导出会员信息 2015-08-12
 *
 * @author ggn
 */
@Controller
public class ExportCustomerController {

    /**
     * 记录日志对象
     */
    private static final MyLogger LOGGER = new MyLogger(ExportCustomerController.class);

    // spring注入
    @Resource(name = "customerServiceMapper")
    private CustomerServiceMapper customerServiceMapper;

    /**
     * 导出会员信息
     */
    @RequestMapping("exportallcustomer")
    public void exportCustomer(HttpServletResponse response) {
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");
        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("会员信息");
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow((int) 0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
        // 设置宽度
        sheet.setColumnWidth(0, 9000);
        /*sheet.setColumnWidth(1, 6000);
        sheet.setColumnWidth(2, 6000);
        sheet.setColumnWidth(3, 6000);
        sheet.setColumnWidth(4, 6000);
        sheet.setColumnWidth(5, 4000);
        sheet.setColumnWidth(6, 4000);
        sheet.setColumnWidth(7, 4000);*/
        // modified by luyong start
        sheet.setColumnWidth(1, 6000);
        sheet.setColumnWidth(2, 6000);
        sheet.setColumnWidth(3, 6000);
        sheet.setColumnWidth(4, 6000);
        sheet.setColumnWidth(5, 6000);
        sheet.setColumnWidth(6, 6000);
        sheet.setColumnWidth(7, 6000);
        sheet.setColumnWidth(8, 6000);
        sheet.setColumnWidth(9, 6000);
        sheet.setColumnWidth(10, 6000);
        sheet.setColumnWidth(11, 6000);
        sheet.setColumnWidth(12, 6000);
        sheet.setColumnWidth(13, 4000);
        sheet.setColumnWidth(14, 4000);
        sheet.setColumnWidth(15, 6000);
        sheet.setColumnWidth(16, 8000);
        sheet.setColumnWidth(17, 4000);

        // 设置列头信息
        HSSFCell cell = row.createCell(0);
        /*cell.setCellValue("用户名");
        cell.setCellStyle(style);
        cell = row.createCell(1);
        cell.setCellValue("性别");
        cell.setCellStyle(style);
        cell = row.createCell(2);
        cell.setCellValue("手机");
        cell.setCellStyle(style);
        cell = row.createCell(3);
        cell.setCellValue("邮箱");
        cell.setCellStyle(style);
        cell = row.createCell(4);
        cell.setCellValue("身份证");
        cell.setCellStyle(style);
        cell = row.createCell(5);
        cell.setCellValue("所在地");
        cell.setCellStyle(style);
        cell = row.createCell(6);
        cell.setCellValue("业务员");
        cell.setCellStyle(style);
        cell = row.createCell(7);
        cell.setCellValue("积分");
        cell.setCellStyle(style);*/
        cell.setCellValue("会员账号");
        cell.setCellStyle(style);
        cell = row.createCell(1);
        cell.setCellValue("等级");
        cell.setCellStyle(style);
        cell = row.createCell(2);
        cell.setCellValue("用户名");
        cell.setCellStyle(style);
        cell = row.createCell(3);
        cell.setCellValue("联系人");
        cell.setCellStyle(style);
        cell = row.createCell(4);
        cell.setCellValue("联系电话");
        cell.setCellStyle(style);
        cell = row.createCell(5);
        cell.setCellValue("详细地址");
        cell.setCellStyle(style);
        cell = row.createCell(6);
        cell.setCellValue("邮箱");
        cell.setCellStyle(style);
        cell = row.createCell(7);
        cell.setCellValue("积分");
        cell.setCellStyle(style);
        cell = row.createCell(8);
        cell.setCellValue("状态");
        cell.setCellStyle(style);
        cell = row.createCell(9);
        cell.setCellValue("注册时间");
        cell.setCellStyle(style);
        cell = row.createCell(10);
        cell.setCellValue("认证通过时间");
        cell.setCellStyle(style);
        cell = row.createCell(11);
        cell.setCellValue("最后登录时间");
        cell.setCellStyle(style);
        cell = row.createCell(12);
        cell.setCellValue("业务员");
        cell.setCellStyle(style);
        cell = row.createCell(13);
        cell.setCellValue("企业类型");
        cell.setCellStyle(style);
        cell = row.createCell(14);
        cell.setCellValue("法定代表人");
        cell.setCellStyle(style);
        cell = row.createCell(15);
        cell.setCellValue("法定代表身份证");
        cell.setCellStyle(style);
        cell = row.createCell(16);
        cell.setCellValue("经营范围");
        cell.setCellStyle(style);
        cell = row.createCell(17);
        cell.setCellValue("注册资金");
        cell.setCellStyle(style);


        // modified by luyong end

        // 第五步，写入实体数据 实际应用中这些数据从数据库得到，
        List<CustomerAllInfo> list = customerServiceMapper.selectCustomerAllInfomation();

        for (int i = 0; i < list.size(); i++) {
            row = sheet.createRow((int) i + 1);
            CustomerAllInfo cus = list.get(i);
            boolean flag = false;
            if(null!=cus.getIsEnterprise()) {
                flag = "1".equals(cus.getIsEnterprise());
            }
            if (cus.getCustomerUsername() != null) {
                row.createCell(0).setCellValue(cus.getCustomerUsername());
            }
            row.createCell(1).setCellValue(flag?"企业用户":"普通用户");
            // 第四步，创建单元格，并设置值
            if (flag) {
                row.createCell(2).setCellValue(cus.getCompanyName());
            } else {
                row.createCell(2).setCellValue(cus.getCustomerNickname());
            }
            /*if (cus.getBussLegalName() != null) {
                row.createCell(1).setCellValue(cus.getBussLegalName());
            }*/
            if (cus.getInfoRealname()!=null) {
                row.createCell(3).setCellValue(flag?cus.getCompanyContactName():cus.getInfoRealname());
            }
            if (cus.getInfoMobile() != null) {
                row.createCell(4).setCellValue(flag?cus.getCompanyContactMoble():cus.getInfoMobile());
            }
            String addr = "";
            if(flag){
                if(cus.getCompanyAddress()!=null&&!"".equals(cus.getCompanyAddress())&&
                        cus.getCompanyAddress().indexOf(",")!=-1&&cus.getCompanyAddress().split(",").length==3){
                    String[] addresses = cus.getCompanyAddress().split(",");
                     addr = addresses[0]+"省"+addresses[1]+"市"+addresses[2]+"区(县)";
                }
                if(cus.getCompanyContactAddr()!=null){
                    addr  += cus.getCompanyContactAddr();
                }
            }else {
                if (cus.getProvince() != null && cus.getProvince().getProvinceName() != null && !"".equals(cus.getProvince().getProvinceName())) {
                    addr += cus.getProvince().getProvinceName() + "省";
                }
                if (cus.getCity() != null && cus.getCity().getCityName() != null && !"".equals(cus.getCity().getCityName())) {
                    addr += cus.getCity().getCityName() + "市";
                }
                if (cus.getDistrict() != null && cus.getDistrict().getDistrictName() != null && !"".equals(cus.getDistrict().getDistrictName())) {
                    addr += cus.getDistrict().getDistrictName() + "区(县)";
                }
                if (cus.getInfoAddress() != null) {
                    addr += cus.getInfoAddress();
                }
            }
            if(addr.length()>0){
                row.createCell(5).setCellValue(addr);
            }
            if (cus.getInfoEmail() != null) {
                row.createCell(6).setCellValue(cus.getInfoEmail());
            }
            if (cus.getInfoPointSum() != null) {
                row.createCell(7).setCellValue(cus.getInfoPointSum());
            }
            if (cus.getIsFlag()!=null) {
                row.createCell(8).setCellValue("0".equals(cus.getIsFlag())?"启用":"禁用");
            }
            if(cus.getCreateTime()!=null) {
                row.createCell(9).setCellValue(df.format(cus.getCreateTime()));
            }
            if(cus.getAuditTime()!=null){
                row.createCell(10).setCellValue(df.format(cus.getAuditTime()));
            }
            if(cus.getLoginTime()!=null){
                row.createCell(11).setCellValue(df.format(cus.getLoginTime()));
            }
           /* if (cus.getInfoCardid()!=null) {
                row.createCell(10).setCellValue(cus.getInfoCardid());
            }*/

            if(cus.getSalesmanName()!=null){
                row.createCell(12).setCellValue(cus.getSalesmanName());
            }
            String ComType="";
            if("1".equals(cus.getCompanyType())){
                ComType = "维修厂";
            }
            if("2".equals(cus.getCompanyType())){
                ComType = "4s店";
            }
            if("3".equals(cus.getCompanyType())){
                ComType = "快修链锁";
            }
            if("4".equals(cus.getCompanyType())){
                ComType = "经销商";
            }
            if(ComType.length()>0){
                row.createCell(13).setCellValue(ComType);
            }
            if(cus.getBussLegalName()!=null){
                row.createCell(14).setCellValue(cus.getBussLegalName());
            }
            if(cus.getBussLegalCardId()!=null){
                row.createCell(15).setCellValue(cus.getBussLegalCardId());
            }
            if(cus.getBussRange()!=null){
                row.createCell(16).setCellValue(cus.getBussRange());
            }
            if(cus.getCompanyCapital()!=null){
                row.createCell(17).setCellValue(cus.getCompanyCapital().setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
            }

           /* if (cus.getInfoGender() != null) {
                if ("1".equals(cus.getInfoGender())) {
                    row.createCell(1).setCellValue("男");
                } else if ("2".equals(cus.getInfoGender())) {
                    row.createCell(1).setCellValue("女");
                } else {
                    row.createCell(1).setCellValue("保密");
                }

            }
            if (cus.getInfoMobile() != null) {
                row.createCell(2).setCellValue(cus.getInfoMobile());
            }

            if (cus.getInfoEmail() != null) {
                row.createCell(3).setCellValue(cus.getInfoEmail());
            }

            if (cus.getInfoCardid() != null) {
                row.createCell(4).setCellValue(cus.getInfoCardid());
            }

            if (cus.getInfoAddress() != null) {
                row.createCell(5).setCellValue(cus.getInfoAddress());
            }

            if (cus.getInfoPointSum() != null) {
                row.createCell(6).setCellValue(cus.getInfoPointSum());
            }
            if (cus.getInfoPointSum() != null) {
                row.createCell(6).setCellValue(cus.getInfoPointSum());
            }*/

        }
        // 第六步，将文件存到指定位置
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
            LOGGER.error("导出会员信息失败，请查看原因", e);
        }

    }

}
