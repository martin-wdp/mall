/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.service.impl;

import com.ningpai.customer.bean.Customer;
import com.ningpai.excel.ImportGoods;
import com.ningpai.goods.bean.GoodsCate;
import com.ningpai.goods.dao.CascDelMapper;
import com.ningpai.goods.dao.GoodsCateMapper;
import com.ningpai.goods.service.GoodsCateService;
import com.ningpai.goods.util.SelectBean;
import com.ningpai.goods.util.ValueUtil;
import com.ningpai.goods.vo.GoodsCateVo;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商品分类service实现
 *
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月17日 下午5:12:33
 */
@Service("GoodsCateService")
public class GoodsCateServiceImpl implements GoodsCateService {
    private GoodsCateMapper goodsCateMapper;
    private CascDelMapper cascDelMapper;

    @Resource(name = "ImportGoods")
    private ImportGoods importGoods;
    /**
     * 记录日志对象
     */
    private static final MyLogger LOGGER = new MyLogger(
            GoodsCateServiceImpl.class);

    public CascDelMapper getCascDelMapper() {
        return cascDelMapper;
    }

    @Resource(name = "CascDelMapper")
    public void setCascDelMapper(CascDelMapper cascDelMapper) {
        this.cascDelMapper = cascDelMapper;
    }

    public GoodsCateMapper getGoodsCateMapper() {
        return goodsCateMapper;
    }

    @Resource(name = "GoodsCateMapper")
    public void setGoodsCateMapper(GoodsCateMapper goodsCateMapper) {
        this.goodsCateMapper = goodsCateMapper;
    }

    /**
     * 添加一个商品分类
     *
     * @param goodsCate {@link com.ningpai.goods.bean.GoodsCate}
     * @return 影响的行数 {@link java.lang.Integer}
     */
    @Transactional
    public int insertGoodsCate(GoodsCate goodsCate, String username) {
        goodsCate.setCatCreateName(username);
        goodsCate.setCatDelflag("0");
        // 设置层级 如果为顶级分类 设置层级为0，否则设置为父分类层级+1
        if (goodsCate.getCatParentId() == 0) {
            goodsCate.setCatGrade(1);
        } else {
            goodsCate
                    .setCatGrade(queryGoodsCateById(goodsCate.getCatParentId())
                            .getCatGrade() + 1);
        }
        // 打印日志
        LOGGER.info(ValueUtil.INSERTGOODSCATE + username);
        // 添加一个商品分类
        return this.goodsCateMapper.insertSelective(goodsCate);
    }

    /**
     * 根据主键ID删除记录
     *
     * @param catId    分类的主键ID {@link java.lang.Long}
     * @param username 删除人名称
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    @Transactional
    public int delGoodsCate(Long catId, String username) {
        // 定义一个HashMap集合
        Map<String, String> map = new HashMap<String, String>();
        try {
            map.put("delName", username);
            map.put("catId", catId.toString());
            // 根据主键ID删除记录
            return this.goodsCateMapper.deleteByPrimaryKey(map);
        } finally {
            // 打印日志
            LOGGER.info(ValueUtil.DELGOODSCATE + username);
            this.cascDelMapper.cascDel(username);
            map = null;
        }
    }

    /**
     * 批量删除商品分类
     *
     * @param catIds   商品分类ID数组 {@link java.lang.Long}
     * @param username 删除人名称
     * @return 受影响的行数
     */
    @Transactional
    public int batchDelGoodsCate(Long[] catIds, String username) {
        // 定义一个HashMap集合
        Map<String, String> map = new HashMap<String, String>();
        Integer count = 0;
        try {
            for (int i = 0; i < catIds.length; i++) {
                map.put("delName", username);
                map.put("catId", catIds[i].toString());
                // 批量删除商品分类
                count += this.goodsCateMapper.deleteByPrimaryKey(map);
                map.clear();
            }
            return count;
        } finally {
            // 打印日志
            LOGGER.info(ValueUtil.BATCHDELGOODSCATE + username);
            this.cascDelMapper.cascDel(username);
            map = null;
            count = null;
        }
    }

    /**
     * 更新商品分类信息
     *
     * @param goodsCate 待更新的商品分类实体 {@link com.ningpai.goods.bean.GoodsCate}
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    @Transactional
    public int updateGoodsCate(GoodsCate goodsCate, String username) {
        goodsCate.setCatModifiedName(username);
        // 设置层级 如果为顶级分类 设置层级为0，否则设置为父分类层级+1
        if (goodsCate.getCatParentId() == 0) {
            goodsCate.setCatGrade(1);
        } else {
            goodsCate
                    .setCatGrade(queryGoodsCateById(goodsCate.getCatParentId())
                            .getCatGrade() + 1);
        }
        // 打印日志
        LOGGER.info(ValueUtil.UPDATEGOODSCATE + username);
        // 更新商品分类信息
        return this.goodsCateMapper.updateByPrimaryKeySelective(goodsCate);
    }

    /**
     * 根据分页帮助类查询分页列表
     *
     * @param pb
     * @return
     */
    public PageBean queryCateListByPageBean(PageBean pb, SelectBean selectBean) {
        // 定义一个HashMap集合
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // 如果查询条件辅助Bean不为空就添加查询条件
            if (null != selectBean) {
                map.put(ValueUtil.CONDITION, selectBean.getCondition());
                map.put(ValueUtil.SEARCHTEXT, selectBean.getSearchText());
            }
            // 查询数据的总行数并设置到PageBean中
            pb.setRows(this.goodsCateMapper.queryTotalCount(map));
            map.put(ValueUtil.STARTROWNUM, pb.getStartRowNum());
            map.put(ValueUtil.ENDROWNUM, pb.getEndRowNum());
            pb.setList(this.goodsCateMapper.queryByPageBean(map));
        } finally {
            map = null;
        }
        return pb;
    }

    /**
     * 根据主键ID查询
     *
     * @param catId
     * @return
     */
    public GoodsCateVo queryGoodsCateById(Long catId) {
        return this.goodsCateMapper.selectByPrimaryKey(catId);
    }

    /**
     * 获得分类下的所有的子分类
     *
     * @param pb 分页辅助类 {@link com.ningpai.util.PageBean}
     * @return 整理好的分类列表
     */
    public List<Object> getCateList(PageBean pb, SelectBean selectBean) {
        // 如果参数为空 就设置为空
        if ("".equals(selectBean.getSearchText())) {
            selectBean.setCondition("");
        }
        // 创建需要返回的集合
        List<Object> cateVoList = new ArrayList<Object>();
        // 根据分页查询所有的父分类的集合
        List<Object> parentList = this.queryCateListByPageBean(pb, selectBean)
                .getList();
        // 查询所有的分类信息
        List<GoodsCateVo> allCateList = this.goodsCateMapper.queryAllGoosCate();
        GoodsCateVo cate = null;
        GoodsCateVo cateVo = null;
        try {
            // 根据父分类集合循环调用计算的方法，整理出二叉树
            for (int i = 0; i < parentList.size(); i++) {
                // 从父分类中取出每一个分类
                cate = (GoodsCateVo) parentList.get(i);
                // 创建最上级的分类，并把属性转移给这个分类
                cateVo = new GoodsCateVo();
                cateVo.setCatId(cate.getCatId());
                cateVo.setCatName(cate.getCatName());
                cateVo.setCatParentId(cate.getCatParentId());
                cateVo.setCatSort(cate.getCatSort());
                cateVo.setTypeId(cate.getTypeId());
                cateVo.setTypeName(cate.getTypeName());
                cateVo.setCatDelflag(cate.getCatDelflag());
                cateVo.setCatGrade(cate.getCatGrade());
                cateVo.setCatSeoDesc(cate.getCatSeoDesc());
                cateVo.setCatSeoKeyword(cate.getCatSeoKeyword());
                cateVo.setCatSeoTitle(cate.getCatSeoTitle());
                cateVo.setGoodsCount(cate.getGoodsCount());
                // 根据取得的父分类ID和所有的分类列表计算分类实体，并添加到需要返回的集合中
                cateVo.setCateVos(this.calcCateVo(cateVo.getCatId(),
                        allCateList));
                cateVoList.add(cateVo);
            }
            // 返回整理好的分类集合
            return cateVoList;
        } finally {
            // 置空所有参数
            cateVoList = null;
            parentList = null;
            allCateList = null;
            cate = null;
            cateVo = null;
        }
    }

    /**
     * 使用递归根据父分类ID计算所有的自己分类
     *
     * @param parentId    第一级的父分类ID
     * @param allCateList 所有的分类 {@link java.util.List}
     *                    {@link com.ningpai.goods.vo.GoodsCateVo}
     * @return 计算好的分类实体
     */
    public List<GoodsCateVo> calcCateVo(Long parentId,
                                        List<GoodsCateVo> allCateList) {
        List<GoodsCateVo> cateVoList = new ArrayList<GoodsCateVo>();
        // 需要返回的分类实体
        GoodsCateVo cateVo = null;
        // 返回的分类实体的所有子分类
        List<GoodsCateVo> allSonCate = null;
        // 循环中的迭代分类
        GoodsCateVo cate = null;
        try {
            // 进行递归
            for (int i = 0; i < allCateList.size(); i++) {
                if (parentId.equals(allCateList.get(i).getCatParentId())) {
                    // 必须在这里new对象 否则会覆盖原先的数据
                    cateVo = new GoodsCateVo();
                    cate = allCateList.get(i);
                    cateVo.setCatId(cate.getCatId());
                    cateVo.setCatName(cate.getCatName());
                    cateVo.setCatParentId(cate.getCatParentId());
                    cateVo.setCatSort(cate.getCatSort());
                    cateVo.setTypeId(cate.getTypeId());
                    cateVo.setTypeName(cate.getTypeName());
                    cateVo.setCatDelflag(cate.getCatDelflag());
                    cateVo.setCatGrade(cate.getCatGrade());
                    cateVo.setCatSeoDesc(cate.getCatSeoDesc());
                    cateVo.setCatSeoKeyword(cate.getCatSeoKeyword());
                    cateVo.setCatSeoTitle(cate.getCatSeoTitle());
                    cateVo.setGoodsCount(cate.getGoodsCount());
                    // 执行递归
                    allSonCate = calcCateVo(cate.getCatId(), allCateList);
                    cateVo.setCateVos(allSonCate);
                    cateVoList.add(cateVo);
                }
            }
            // 返回计算好的所有的子分类列表
            return cateVoList;
        } finally {
            // 置空所有参数
            cateVoList = null;
            cateVo = null;
            allSonCate = null;
            cate = null;
        }
    }

    /**
     * 查询所有的商品分类
     *
     * @return 查询到的商品分类的集合 {@link java.util.List}
     */
    public List<GoodsCateVo> queryAllCate() {
        return this.goodsCateMapper.queryAllGoosCate();
    }

    @Override
    public List<GoodsCateVo> queryFirstLevelGoodsCate() {
        return this.goodsCateMapper.queryFirstLevelGoodsCate();
    }

    /**
     * 验证是否可以删除 如果传递过来的分类 下面有子类 就返回false表示不可以删除
     *
     * @param
     * @return 是否可以删除
     */
    public boolean checkDelWithCateId(Long cateId) {
        return this.goodsCateMapper.querySonCountByParentId(cateId) > 0 ? false
                : true;
    }

    /**
     * 根据分类名称查询商品分类
     *
     * @param cateName 分类名称
     * @return 查询到的实体 {@link com.ningpai.goods.bean.GoodsCate}
     */
    public GoodsCate queryCateByCateName(String cateName) {
        return this.goodsCateMapper.queryCateByCateName(cateName);
    }

    /**
     * 查询所有分类
     *
     * @return List
     */
    public List<GoodsCate> queryAllGoodCate() {
        return goodsCateMapper.queryAllGoodCate();
    }

    /**
     * 根据父分类ID 查询子分类列表
     *
     * @param parentId 父分类ID {@link Long}
     * @return 查询到的分类列表 {@link Long}
     */
    public List<GoodsCate> querySonCateByParentId(Long parentId) {
        return this.goodsCateMapper.querySonCatByParentId(parentId);
    }

    /**
     * 根据父分类ID 查询子分类列表
     *
     * @param parentId 父分类ID {@link Long}
     * @param cateName 父类名称
     * @return 查询到的分类列表 {@link Long}
     */
    @Override
    public List<GoodsCate> querySonCateByParentIdAndName(Long parentId,
                                                         String cateName) {
        // 定义一个HashMap集合
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("cateId", parentId);
        paramMap.put("cateName", cateName);
        // 根据父分类ID 查询子分类列表
        return this.goodsCateMapper.querySonCatByParm(paramMap);
    }

    /**
     * 根据父分类ID 查询子分类列表
     *
     * @param parentId 父分类ID {@link Long}
     * @param cateName 父类名称
     * @return 查询到的分类列表 {@link Long}
     */
    @Override
    public List<GoodsCateVo> querySonCateVoByParentIdAndName(Long parentId,
                                                             String cateName) {
        // 定义一个HashMap
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("cateId", parentId);
        paramMap.put("cateName", cateName);
        // 根据父分类ID 查询子分类列表
        return this.goodsCateMapper.querySonCatVoByParm(paramMap);
    }

    /**
     * 导出商品分类
     *
     * @param response
     */
    @SuppressWarnings("deprecation")
    @Override
    public void exportGoodsCate(HttpServletResponse response) {
        GoodsCateVo cate = null;
        List<GoodsCateVo> cates = goodsCateMapper.queryAllGoosCate();
        HSSFWorkbook wb = new HSSFWorkbook();
        CellStyle style = wb.createCellStyle();
        HSSFRow tempRow;
        style.setAlignment(CellStyle.ALIGN_CENTER);
        HSSFSheet sheet1 = wb.createSheet("商品分类列表");
        sheet1.createFreezePane(255, 1);
        HSSFRow headRow = sheet1.createRow(0);
        headRow.createCell(0).setCellValue("分类Id");
        headRow.createCell(1).setCellValue("分类名称");
        headRow.createCell(2).setCellValue("所属类型");
        headRow.createCell(3).setCellValue("类型Id");
        headRow.createCell(4).setCellValue("分类扣率");
        headRow.createCell(5).setCellValue("分类排序");
        headRow.createCell(6).setCellValue("分类级别");
        headRow.createCell(7).setCellValue("分类SEO标题");
        headRow.createCell(8).setCellValue("分类SEO关键词");
        headRow.createCell(9).setCellValue("分类SEO描述");
        headRow.createCell(10).setCellValue("父类id");
        if (null != cates && !cates.isEmpty()) {
            // 循环传递过来的货品列表,并添加到文件中
            for (int i = 0; i < cates.size(); i++) {
                cate = (GoodsCateVo) cates.get(i);
                tempRow = sheet1.createRow(1 + i);
                tempRow.createCell(0).setCellValue(cate.getCatId());
                tempRow.createCell(1).setCellValue(cate.getCatName());
                tempRow.createCell(2).setCellValue(cate.getTypeName());
                tempRow.createCell(3).setCellValue(
                        cate.getTypeId() == null ? "" : cate.getTypeId() + "");
                tempRow.createCell(4).setCellValue(
                        cate.getCatRate().doubleValue());
                tempRow.createCell(5)
                        .setCellValue(
                                cate.getCatSort() == null ? "" : cate
                                        .getCatSort() + "");
                tempRow.createCell(6).setCellValue(
                        cate.getCatGrade() == null ? "" : cate.getCatGrade()
                                + "");
                tempRow.createCell(7).setCellValue(
                        cate.getCatSeoTitle() == null ? "" : cate
                                .getCatSeoTitle());
                tempRow.createCell(8).setCellValue(
                        cate.getCatSeoKeyword() == null ? "" : cate
                                .getCatSeoKeyword());
                tempRow.createCell(9).setCellValue(
                        cate.getCatSeoDesc() == null ? "" : cate
                                .getCatSeoDesc());
                tempRow.createCell(10).setCellValue(
                        cate.getCatParentId() == null ? "" : cate
                                .getCatParentId() + "");
            }

        }
        // 设置下载时客户端Excel的名称
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename="
                + URLEncoder.encode("商品分类备份.xls"));
        OutputStream ouputStream;
        try {
            ouputStream = response.getOutputStream();
            wb.write(ouputStream);
            ouputStream.flush();
            ouputStream.close();
        } catch (IOException e) {
            LOGGER.error("导出商品分类错误" + e);
        } finally {
            cate = null;
            wb = null;
            ouputStream = null;
            style = null;
            tempRow = null;
        }
    }

    /**
     * 导出商品分类模板
     *
     * @param response
     */
    @SuppressWarnings("deprecation")
    @Override
    public void exportGoodsCateTemp(HttpServletResponse response) {
        HSSFWorkbook wb = new HSSFWorkbook();
        CellStyle style = wb.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_CENTER);
        HSSFSheet sheet1 = wb.createSheet("分类导入模板");
        sheet1.createFreezePane(255, 1);
        HSSFRow headRow = sheet1.createRow(0);
        headRow.createCell(0).setCellValue("分类Id（必填）");
        headRow.createCell(1).setCellValue("分类名称（必填）");
        headRow.createCell(2).setCellValue("类型Id（必填）");
        headRow.createCell(3).setCellValue("分类扣率（必填）");
        headRow.createCell(4).setCellValue("分类排序（必填）");
        headRow.createCell(5).setCellValue("分类级别（必填）");
        headRow.createCell(6).setCellValue("分类SEO标题");
        headRow.createCell(7).setCellValue("分类SEO关键词");
        headRow.createCell(8).setCellValue("分类SEO描述");
        headRow.createCell(9).setCellValue("父类id（必填）");
        // 设置下载时客户端Excel的名称
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename="
                + URLEncoder.encode("商品分类模板.xls"));
        OutputStream ouputStream;
        try {
            ouputStream = response.getOutputStream();
            wb.write(ouputStream);
            ouputStream.flush();
            ouputStream.close();
        } catch (IOException e) {
            LOGGER.error("导出商品分类模板" + e);
        } finally {
            wb = null;
            ouputStream = null;
            style = null;
        }
    }

    /**
     * 查询所有三级分类
     *
     * @return List
     */
    @Override
    public List<GoodsCate> queryAllGoodThirdCate() {
        return goodsCateMapper.queryAllGoodThirdCate();
    }

    /**
     * 导入商品分类
     *
     * @param request
     * @param response
     * @param multiRequest
     */
    @Override
    public String importGoodsCateByExcel(HttpServletRequest request,
                                         HttpServletResponse response,
                                         MultipartHttpServletRequest multiRequest) {
        try {
            MultipartFile file = multiRequest.getFile("importExcel");
            if (file==null||file.isEmpty()) {
                return "401";// 文件为空
            } else if (!".xls".equals(file.getOriginalFilename().substring(
                    file.getOriginalFilename().indexOf("."),
                    file.getOriginalFilename().length()))) {
                return "402";// 扩展名不正确
            }
            return execImportGoodsCate(
                    this.importGoods.importGoodsCate(file.getInputStream()),
                    request);
        } catch (IOException e) {
            LOGGER.error("导入商品失败:" + e.getLocalizedMessage());
            return "400";// 失败
        }
    }

    /**
     * 导入商品类型
     */
    public String execImportGoodsCate(Map<String, Object> map,
                                      HttpServletRequest request) {
        @SuppressWarnings("unchecked")
        List<GoodsCate> list = (List<GoodsCate>) map.get("cateList");
        if (list == null) {
            return map.get("result").toString();
        }

        try {
            if (null != list && !list.isEmpty()) {
                for (GoodsCate goodsCate : list) {
                    goodsCate.setCatDelflag("0");
                    goodsCateMapper.insertSelective(goodsCate);
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

}
