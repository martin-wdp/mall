/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.service.impl;

import com.ningpai.customer.bean.Customer;
import com.ningpai.excel.ImportGoods;
import com.ningpai.goods.bean.GoodsBrand;
import com.ningpai.goods.common.GB2Alpha;
import com.ningpai.goods.dao.CascDelMapper;
import com.ningpai.goods.dao.GoodsBrandMapper;
import com.ningpai.goods.service.GoodsBrandService;
import com.ningpai.goods.util.SelectBean;
import com.ningpai.goods.util.ValueUtil;
import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商品品牌service实现类
 *
 * @author NINGPAI-YuanKangKang
 * @version 1.0
 * @since 2013年12月16日 下午8:36:16
 */
@Service("GoodsBrandService")
public class GoodsBrandServiceImpl implements GoodsBrandService {
    // 注入GoodsBrandMapper
    private GoodsBrandMapper goodsBrandMapper;
    // 批量删除
    private CascDelMapper cascDelMapper;

    @Resource(name = "ImportGoods")
    private ImportGoods importGoods;
    /**
     * 记录日志对象
     */
    private static final MyLogger LOGGER = new MyLogger(
            GoodsBrandServiceImpl.class);

    public CascDelMapper getCascDelMapper() {
        return cascDelMapper;
    }

    @Resource(name = "CascDelMapper")
    public void setCascDelMapper(CascDelMapper cascDelMapper) {
        this.cascDelMapper = cascDelMapper;
    }

    public GoodsBrandMapper getGoodsBrandMapper() {
        return goodsBrandMapper;
    }

    @Resource(name = "GoodsBrandMapper")
    public void setGoodsBrandMapper(GoodsBrandMapper goodsBrandMapper) {
        this.goodsBrandMapper = goodsBrandMapper;
    }

    /**
     * 根据主键删除商品品牌
     *
     * @param brandId  品牌主键ID {@link java.lang.Long}
     * @param username 删除用户的用户名称
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    @Transactional
    public int deleteGoodsBrand(Long brandId, String username) {
        // 封装参数
        Map<String, String> map = new HashMap<String, String>();
        try {
            map.put("brandId", brandId.toString());
            map.put("del_name", username);
            // 根据主键删除商品品牌
            return this.goodsBrandMapper.deleteByPrimaryKey(map);
        } finally {
            // 打印日志
            LOGGER.info(ValueUtil.DELETEGOODSBRAND + username);
            this.cascDelMapper.cascDel(username);
            map = null;
        }
    }

    /**
     * 更新商品品牌信息
     *
     * @param goodsBrand 更新的商品品牌的实体 {@link com.ningpai.goods.bean.GoodsBrand}
     * @param username   更新的用户名称
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    @Transactional
    public int updateGoodsBrad(GoodsBrand goodsBrand, String username) {
        // 设置修改人名称
        goodsBrand.setBrandModifiedName(username);
        GB2Alpha obj1 = new GB2Alpha();
        String str= obj1.String2Alpha(goodsBrand.getBrandName(),"b");
        goodsBrand.setBrandNameInitial(str);
        // 打印日志
        LOGGER.info(ValueUtil.UPDATEGOODSBRAD + username);
        // 更新商品品牌信息
        return this.goodsBrandMapper.updateByPrimaryKeySelective(goodsBrand);
    }

    /**
     * 插入一条商品品牌信息
     *
     * @param goodsBrand 待插入的商品品牌的实体 {@link com.ningpai.goods.bean.GoodsBrand}
     * @param username   插入记录的用户名称
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    @Transactional
    public int insertGoodsBrand(GoodsBrand goodsBrand, String username) {
        // 为品牌创建者赋值
        goodsBrand.setBrandCreateName(username);
        // 设置品牌删除标记
        goodsBrand.setBrandDelflag("0");
        // 如果品牌详细信息为null
        // 就给品牌详细信息赋值
        if (goodsBrand.getBrandDesc() == null) {
            goodsBrand.setBrandDesc(goodsBrand.getBrandName());
        }
        // 如果品牌SEO详细信息为null
        // 就给品牌seo详细信息赋值
        if (goodsBrand.getBrandSeoDesc() == null) {
            goodsBrand.setBrandSeoDesc(goodsBrand.getBrandName());
        }
        // 如果品牌SEOkewyWoed为null
        // 就给品牌seokeyword详细信息赋值
        if (goodsBrand.getBrandSeoKeyword() == null) {
            goodsBrand.setBrandSeoKeyword(goodsBrand.getBrandName());
        }
        // 如果品牌SEO标题为null
        // 就给品牌seo标题赋值
        if (goodsBrand.getBrandSeoTitle() == null) {
            goodsBrand.setBrandSeoTitle(goodsBrand.getBrandName());
        }
        GB2Alpha obj1 = new GB2Alpha();
        String str= obj1.String2Alpha(goodsBrand.getBrandName(),"b");
        goodsBrand.setBrandNameInitial(str);
        //goodsBrand.set
        // 打印日志
        LOGGER.info(ValueUtil.INSERTGOODSBRAND + username);
        // 插入一条商品品牌信息
        return this.goodsBrandMapper.insertSelective(goodsBrand);
    }

    /**
     * 根据PageBean 查询分页列表
     *
     * @param pageBean
     * @return 查询到的列表封装到PageBean中
     */
    public PageBean queryByPageBean(PageBean pageBean) {
        // 查询总行数
        pageBean.setRows(this.goodsBrandMapper.queryTotalCount());
        // 定义一个HashMap集合
        Map<String, Integer> map = new HashMap<String, Integer>();
        try {
            // 封装分页参数
            map.put(ValueUtil.STARTROWNUM, pageBean.getStartRowNum());
            map.put(ValueUtil.ENDROWNUM, pageBean.getEndRowNum());
            // 设置列表
            pageBean.setList(this.goodsBrandMapper.queryByPageBean(map));
        } finally {
            // 参数置空
            map = null;
        }
        // 返回pageBean
        return pageBean;
    }

    /**
     * 批量删除商品品牌
     *
     * @param brandIds 商品品牌ID的集合 {@link java.lang.Long}
     * @param username 删除人名称
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    @Transactional
    public Integer batchDeleteGodosBrand(Long[] brandIds, String username) {
        // 定义一个HashMap集合
        Map<String, String> map = new HashMap<String, String>();
        Integer count = 0;
        try {
            // 遍历品牌ID的数组 并删除
            for (int i = 0; i < brandIds.length; i++) {
                map.put("brandId", brandIds[i].toString());
                map.put("del_name", username);
                // 批量删除商品品牌
                count += this.goodsBrandMapper.deleteByPrimaryKey(map);
                map.clear();
            }
        } finally {
            // 打印日志
            LOGGER.info(ValueUtil.BATCHDELETEGODOSBRAND + username);
            this.cascDelMapper.cascDel(username);
            map = null;
            count = null;
        }
        return count;
    }

    /**
     * 根据Id查询商品品牌
     *
     * @param brandId 商品品牌ID {@link java.lang.Long}
     * @return {@link com.ningpai.goods.bean.GoodsBrand}
     */
    public GoodsBrand queryBrandById(Long brandId) {
        // 根据Id查询商品品牌
        return this.goodsBrandMapper.selectByPrimaryKey(brandId);
    }

    /**
     * 查询所有的商品品牌
     *
     * @return 查询到的商品品牌的集合 {@link java.util.List}
     * {@link com.ningpai.goods.bean.GoodsBrand}
     */
    public List<GoodsBrand> queryAllBrand() {
        // 查询所有的商品品牌
        return this.goodsBrandMapper.queryAllBrand();
    }

    /**
     * 查询所有的商品品牌
     *
     * @return 查询到的商品品牌的集合 {@link java.util.List}
     * {@link com.ningpai.goods.bean.GoodsBrand}
     */
    public List<GoodsBrand> queryAllGoodsBrandBy_PL() {
        // 查询所有的商品品牌
        return this.goodsBrandMapper.queryAllGoodsBrandBy_PL();
    }
    /**
     * 根据品牌类型查询商品品牌
     * ADD BY LY 15-11-05
     * @return 查询到的商品品牌的集合 {@link java.util.List}
     *         {@link com.ningpai.goods.bean.GoodsBrand}
     */
    @Override
    public List<GoodsBrand> queryGoodsBrand(Map<String, Object> param) {
        return this.goodsBrandMapper.queryGoodsBrand(param);
    }

    /**
     * 根君名称查询商品品牌
     *
     * @return 查询到的商品品牌的集合 {@link java.util.List}
     * {@link com.ningpai.goods.bean.GoodsBrand}
     */
    @Override
    public List<GoodsBrand> queryallbrandbyName(String brandName) {
        // 根君名称查询商品品牌
        return this.goodsBrandMapper.queryallbrandbyName(brandName);
    }

    /**
     * 参数查询分页bean
     *
     * @param pageBean
     * @param selectBean
     * @return int
     */
    public PageBean searchByPageBean(PageBean pageBean, SelectBean selectBean) {
        // 判断查询文本是否为空 若为空 将条件也设置为空
        if ("".equals(selectBean.getSearchText())) {
            selectBean.setCondition("");
        }
        // 设置PageBean的总行数
        pageBean.setRows(this.goodsBrandMapper.searchTotalCount(selectBean));
        // 定义一个HashMap集合
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // 设置开始行数
            map.put(ValueUtil.STARTROWNUM, pageBean.getStartRowNum());
            // 设置结束行数
            map.put(ValueUtil.ENDROWNUM, pageBean.getEndRowNum());
            map.put(ValueUtil.CONDITION, selectBean.getCondition());
            map.put(ValueUtil.SEARCHTEXT, selectBean.getSearchText());
            map.put(ValueUtil.SELECTBEAN, selectBean);
            // 分页查询数据
            pageBean.setList(this.goodsBrandMapper.searchAllBrand(map));
        } finally {
            map = null;
        }
        // 返回结果
        return pageBean;
    }

    /**
     * 查询所有品牌
     *
     * @return List
     */
    public List<GoodsBrand> queryAllBrandList() {
        // 查询所有品牌
        return goodsBrandMapper.queryAllBrandList();
    }

    /**
     * 验证品牌名称是否可用
     *
     * @param brandNmae 待验证的品牌名称
     * @return 可用返回true 不可用返回false
     */
    public boolean checkBrandName(String brandNmae) {
        // 验证品牌名称是否可用
        return this.goodsBrandMapper.selectByBrandName(brandNmae) > 0 ? false
                : true;
    }

    /**
     * 导出商品品牌
     *
     * @param response
     */
    @SuppressWarnings("deprecation")
    @Override
    public void exportGoodsBrand(HttpServletResponse response) {
        GoodsBrand brand = null;
        // 查询所有的品牌信息
        List<GoodsBrand> brands = goodsBrandMapper.queryAllBrandList();
        HSSFWorkbook wb = new HSSFWorkbook();
        CellStyle style = wb.createCellStyle();
        HSSFRow tempRow;
        style.setAlignment(CellStyle.ALIGN_CENTER);
        HSSFSheet sheet1 = wb.createSheet("商品分类列表");
        sheet1.createFreezePane(255, 1);
        HSSFRow headRow = sheet1.createRow(0);
        headRow.createCell(0).setCellValue("序号");
        headRow.createCell(1).setCellValue("品牌名称");
        headRow.createCell(2).setCellValue("品牌别名");
        headRow.createCell(3).setCellValue("品牌排序");
        // headRow.createCell(4).setCellValue("品牌网址");
        headRow.createCell(4).setCellValue("品牌logo");
        // headRow.createCell(6).setCellValue("品牌SEO标题");
        // headRow.createCell(7).setCellValue("品牌SEO关键词");
        // headRow.createCell(8).setCellValue("品牌SEO描述");
        if (null != brands && !brands.isEmpty()) {
            // 循环传递过来的货品列表,并添加到文件中
            for (int i = 0; i < brands.size(); i++) {
                brand = (GoodsBrand) brands.get(i);
                tempRow = sheet1.createRow(1 + i);
                tempRow.createCell(0).setCellValue(i + 1);
                tempRow.createCell(1).setCellValue(brand.getBrandName());
                tempRow.createCell(2).setCellValue(
                        brand.getBrandNickname() == null ? "" : brand
                                .getBrandNickname());
                tempRow.createCell(3).setCellValue(
                        brand.getBrandSort() == null ? "" : brand
                                .getBrandSort() + "");
                // tempRow.createCell(4).setCellValue(brand.getBrandUrl()==null?"":brand.getBrandUrl());
                tempRow.createCell(4).setCellValue(
                        brand.getBrandLogo() == null ? "" : brand
                                .getBrandLogo());
                // tempRow.createCell(6).setCellValue(brand.getBrandSeoTitle()==null?"":brand.getBrandSeoTitle());
                // tempRow.createCell(7).setCellValue(brand.getBrandSeoKeyword()==null?"":brand.getBrandSeoKeyword());
                // tempRow.createCell(8).setCellValue(brand.getBrandSeoDesc()==null?"":brand.getBrandSeoDesc());
            }

        }
        // 设置下载时客户端Excel的名称
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename="
                + URLEncoder.encode("商品品牌备份.xls"));
        OutputStream ouputStream;
        try {
            ouputStream = response.getOutputStream();
            wb.write(ouputStream);
            ouputStream.flush();
            ouputStream.close();
        } catch (IOException e) {
            LOGGER.error("导出商品品牌错误" + e);
        } finally {
            brand = null;
            wb = null;
            ouputStream = null;
            style = null;
            tempRow = null;
        }
    }

    /**
     * 导出商品品牌模板
     *
     * @param response
     */
    @SuppressWarnings("deprecation")
    @Override
    public void exportGoodsBrandTemp(HttpServletResponse response) {
        HSSFWorkbook wb = new HSSFWorkbook();
        CellStyle style = wb.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_CENTER);
        HSSFSheet sheet1 = wb.createSheet("品牌导入模板");
        sheet1.createFreezePane(255, 1);
        HSSFRow headRow = sheet1.createRow(0);
        headRow.createCell(0).setCellValue("品牌名称（必填）");
        headRow.createCell(1).setCellValue("品牌别名");
        headRow.createCell(2).setCellValue("品牌排序（必填）");
        // headRow.createCell(3).setCellValue("品牌网址");
        // headRow.createCell(3).setCellValue("品牌logo");
        // headRow.createCell(5).setCellValue("品牌SEO标题");
        // headRow.createCell(6).setCellValue("品牌SEO关键词");
        // headRow.createCell(7).setCellValue("品牌SEO描述");
        // 设置下载时客户端Excel的名称
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename="
                + URLEncoder.encode("商品品牌模板.xls"));
        OutputStream ouputStream;
        try {
            ouputStream = response.getOutputStream();
            wb.write(ouputStream);
            ouputStream.flush();
            ouputStream.close();
        } catch (IOException e) {
            LOGGER.error("导出商品品牌模板错误" + e);
        } finally {
            wb = null;
            ouputStream = null;
            style = null;
        }
    }

    /**
     * 导入商品品牌
     *
     * @param request
     * @param response
     * @param multiRequest
     */
    @Override
    public String importGoodsBrandByExcel(HttpServletRequest request,
                                          HttpServletResponse response,
                                          MultipartHttpServletRequest multiRequest) {
        try {
            // 获取name 为importExcel的资源文件
            MultipartFile file = multiRequest.getFile("importExcel");
            // 判断file是否为空
            // 如果是就返回401
            if (file==null||file.isEmpty()) {
                return "401";// 文件为空
            } else if (!".xls".equals(file.getOriginalFilename().substring(
                    file.getOriginalFilename().indexOf("."),
                    file.getOriginalFilename().length()))) {
                return "402";// 扩展名不正确
            }
            return execImportGoodsBrand(
                    this.importGoods.importGoodsBrand(request,
                            file.getInputStream()), request);
        } catch (IOException e) {
            LOGGER.error("导入商品失败:" + e.getLocalizedMessage());
            return "400";// 失败
        }
    }

    /**
     * 导入商品品牌
     */
    public String execImportGoodsBrand(Map<String, Object> map,
                                       HttpServletRequest request) {
        @SuppressWarnings("unchecked")
        List<GoodsBrand> list = (List<GoodsBrand>) map.get("brandList");
        if (list == null) {
            return map.get("result").toString();
        }

        try {
            if (null != list && !list.isEmpty()) {
                for (GoodsBrand goodsBrand : list) {
                    goodsBrand.setBrandDelflag("0");
                    GB2Alpha obj1 = new GB2Alpha();
                    String str= obj1.String2Alpha(goodsBrand.getBrandName(),"b");
                    goodsBrand.setBrandNameInitial(str);
                    goodsBrandMapper.insertSelective(goodsBrand);
                }
                return "200";
            } else {
                return "400";
            }
        } catch (Exception e) {
            LOGGER.error(""+e);
            Customer cust = (Customer) request.getSession()
                    .getAttribute("cust");
            OperaLogUtil.addOperaException(cust.getCustomerUsername(), e,
                    request);
        }
        return "200";
    }

    /**
     * 验证品牌名称，不可重复添加
     *
     * @param brandName 待验证的品牌名称
     * @return 返回查询条数，若=0则可以添加，反之不可添加
     */
    @Override
    public int selectByBrandName(String brandName) {
        // 验证品牌名称，不可重复添加
        return goodsBrandMapper.selectByBrandName(brandName);
    }
}
