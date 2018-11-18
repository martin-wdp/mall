/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.service.impl;

import com.ningpai.goods.bean.*;
import com.ningpai.goods.dao.CascDelMapper;
import com.ningpai.goods.dao.GoodsTypeMapper;
import com.ningpai.goods.service.*;
import com.ningpai.goods.util.SelectBean;
import com.ningpai.goods.util.ValueUtil;
import com.ningpai.goods.vo.GoodsTypeVo;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商品类型Service
 *
 * @author NINGPAI-YuanKangKang
 * @version 1.0
 * @since 2013年12月23日 上午10:32:36
 */
@Service("GoodsTypeService")
public class GoodsTypeServiceImpl implements GoodsTypeService {
    // 商品类型的Dao
    private GoodsTypeMapper goodsTypeMapper;
    // 商品类型关联品牌Service
    private GoodsTypeBrandService goodsTypeBrandService;
    // 商品类型关联商品规格Service
    private GoodsTypeSpecService goodsTypeSpecService;
    // 商品类型扩展属性Service
    private GoodsTypeExpandParamService goodsTypeExpandParamService;
    // 商品类型扩展属性值Service
    private GoodsTypeExpandParamValueService goodsTypeExpandParamValueService;
    // 商品详细参数Service
    private GoodsTypeParamService goodsTypeParamService;
    private CascDelMapper cascDelMapper;
    /**
     * 记录日志对象
     */
    private static final MyLogger LOGGER = new MyLogger(
            GoodsTypeServiceImpl.class);

    public CascDelMapper getCascDelMapper() {
        return cascDelMapper;
    }

    @Resource(name = "CascDelMapper")
    public void setCascDelMapper(CascDelMapper cascDelMapper) {
        this.cascDelMapper = cascDelMapper;
    }

    public GoodsTypeParamService getGoodsTypeParamService() {
        return goodsTypeParamService;
    }

    @Resource(name = "GoodsTypeParamService")
    public void setGoodsTypeParamService(
            GoodsTypeParamService goodsTypeParamService) {
        this.goodsTypeParamService = goodsTypeParamService;
    }

    public GoodsTypeExpandParamService getGoodsTypeExpandParamService() {
        return goodsTypeExpandParamService;
    }

    @Resource(name = "GoodsTypeExpandParamService")
    public void setGoodsTypeExpandParamService(
            GoodsTypeExpandParamService goodsTypeExpandParamService) {
        this.goodsTypeExpandParamService = goodsTypeExpandParamService;
    }

    public GoodsTypeExpandParamValueService getGoodsTypeExpandParamValueService() {
        return goodsTypeExpandParamValueService;
    }

    @Resource(name = "GoodsTypeExpandParamValueService")
    public void setGoodsTypeExpandParamValueService(
            GoodsTypeExpandParamValueService goodsTypeExpandParamValueService) {
        this.goodsTypeExpandParamValueService = goodsTypeExpandParamValueService;
    }

    public GoodsTypeBrandService getGoodsTypeBrandService() {
        return goodsTypeBrandService;
    }

    @Resource(name = "GoodsTypeBrandService")
    public void setGoodsTypeBrandService(
            GoodsTypeBrandService goodsTypeBrandService) {
        this.goodsTypeBrandService = goodsTypeBrandService;
    }

    public GoodsTypeSpecService getGoodsTypeSpecService() {
        return goodsTypeSpecService;
    }

    @Resource(name = "GoodsTypeSpecService")
    public void setGoodsTypeSpecService(
            GoodsTypeSpecService goodsTypeSpecService) {
        this.goodsTypeSpecService = goodsTypeSpecService;
    }

    public GoodsTypeMapper getGoodsTypeMapper() {
        return goodsTypeMapper;
    }

    @Resource(name = "GoodsTypeMapper")
    public void setGoodsTypeMapper(GoodsTypeMapper goodsTypeMapper) {
        this.goodsTypeMapper = goodsTypeMapper;
    }

    /**
     * 保存商品类型
     *
     * @param goodsType 商品类型实体 {@Link com.ningpai.goods.bean.GoodsType }
     * @param username  操作人名称
     * @return 最新插入的ID {@link java.lang.Long}
     */
    @Transactional
    public Long saveGoodsType(GoodsType goodsType, String username) {
        goodsType.setTypeCreateName(username);
        //执行添加方法
        this.goodsTypeMapper.insertSelective(goodsType);
        //打印日志
        LOGGER.info(ValueUtil.SAVEGOODSTYPE + username);
        //查询最后一个插入的id
        return this.goodsTypeMapper.selectLastId();
    }

    /**
     * 根据主键删除商品类型
     *
     * @param typeId   主键ID {@link java.lang.Long}
     * @param username 操作人名称
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    @Transactional
    public int delGoodsType(Long typeId, String username) {
        //定义一个HashMap集合
        Map<String, String> map = new HashMap<String, String>();
        try {
            // 封装删除的参数
            map.put("delName", username);
            map.put("typeId", typeId.toString());
            //执行删除方法
            return this.goodsTypeMapper.deleteByPrimaryKey(map);
        } finally {
            //打印日志
            LOGGER.info(ValueUtil.DELGOODSTYPE + username);
            this.cascDelMapper.cascDel(username);
            // 参数置空
            map = null;
        }
    }

    /**
     * 更新商品类型
     *
     * @param goodsType 商品类型实体 {@Link com.ningpai.goods.bean.GoodsType }
     * @param username  操作人名称
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    @Transactional
    public int updateGoodsType(GoodsType goodsType, String username) {
        goodsType.setTypeModifiedName(username);
        //打印日志
        LOGGER.info(ValueUtil.UPDATEGOODSTYPE + username);
        //执行修改方法
        return this.goodsTypeMapper.updateByPrimaryKeySelective(goodsType);
    }

    /**
     * 根据商品类型ID 查询Vo
     *
     * @param typeId 主键ID {@link java.lang.Long}
     * @return 商品类型Vo实体 {@Link com.ningpai.goods.vo.GoodsTypeVo }
     */
    public GoodsTypeVo queryTypeVoByTypeId(Long typeId) {
        //执行查询方法
        return this.goodsTypeMapper.selectByPrimaryKey(typeId);
    }

    /**
     * 根据分页参数查询商品类型列表
     *
     * @param pb 分页辅助类
     * @return 分页辅助类
     */
    public PageBean queryListByPageBean(PageBean pb) {
        //设置总行数
        pb.setRows(this.goodsTypeMapper.queryTotalCount());
        //定义一个HashMap集合
        Map<String, Integer> map = new HashMap<String, Integer>();
        try {
            // 封装分页需要的参数
            map.put(ValueUtil.STARTROWNUM, pb.getStartRowNum());
            map.put(ValueUtil.ENDROWNUM, pb.getEndRowNum());
            //设置集合数据
            pb.setList(this.goodsTypeMapper.selectAllType(map));
        } finally {
            // 参数置空
            map = null;
        }
        return pb;
    }

    /**
     * 批量删除商品类型
     *
     * @param typeIds  需要删除的商品类型的集合 {@link java.lang.Long}
     * @param username 操作人名称
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    @Transactional
    public int batchDelType(Long[] typeIds, String username) {
        Integer count = 0;
        try {
            for (int i = 0; i < typeIds.length; i++) {
                count += delGoodsType(typeIds[i], username);
            }
            return count;
        } finally {
            //打印日志
            LOGGER.info(ValueUtil.BATCHDELTYPE + username);
            this.cascDelMapper.cascDel(username);
            // 参数置空
            count = null;
        }
    }

    /**
     * 保存商品类型以及相关参数
     *
     * @param goodsType 商品类型实体 {@link com.ningpai.goods.bean.GoodsType}
     * @param map       相关参数的封装参数 {@link java.util.Map}
     * @return 是否成功标记 返回0表示失败 返回1表示成功
     */
    @Transactional
    public int saveTypeAndParam(GoodsType goodsType, Map<String, String[]> map,
                                String username) {
        Long lastId = saveGoodsType(goodsType, username);
        if (lastId > 0) {
            try {
                //把参数brandIds放入map集合
                String[] brandIds = map.get("brandIds");
                //把参数specIds放入map集合
                String[] specIds = map.get("specIds");
                //把参数paramname放入map集合
                String[] paramname = map.get("paramname");
                //把参数paramnickname放入map集合
                String[] paramnickname = map.get("paramnickname");
                //把参数expandnames放入map集合
                String[] expandnames = map.get("expandnames");
                //把参数expandnicknames放入map集合
                String[] expandnicknames = map.get("expandnicknames");
                //把参数expandparamsort放入map集合
                String[] expandparamsort = map.get("expandparamsort");
                //把参数expandparamisshow放入map集合
                String[] expandparamisshow = map.get("expandparamisshow");
                //把参数expandvalues放入map集合
                String[] expandvalues = map.get("expandvalues");
                // 保存关联品牌表
                saveBrand(username, lastId, brandIds);
                // 保存关联规格表
                saveTypeSpec(username, lastId, specIds);
                // 保存商品扩展属性
                if (null != expandnames) {
                    // 创建一个迭代变量
                    GoodsTypeExpandParam typeExpandParam = null;
                    for (int i = 0; i < expandnames.length; i++) {
                        /**
                         * new一个实体类对象
                         * 并设置参数值
                         * */
                        typeExpandParam = new GoodsTypeExpandParam();
                        typeExpandParam.setTypeId(lastId);
                        typeExpandParam.setExpandparamName(expandnames[i]);
                        typeExpandParam
                                .setExpandparamNickname(expandnicknames[i]);
                        typeExpandParam.setExpandparamSort(Integer
                                .parseInt(expandparamsort[i]));
                        typeExpandParam
                                .setExpanparamIsshow(expandparamisshow[i]);
                        typeExpandParam
                                .setExpandparamDelflag(ValueUtil.DEFAULTDELFLAG);
                        Long paramId = this.goodsTypeExpandParamService
                                .saveExpandParam(typeExpandParam, username);
                        if (paramId > 0&&null != expandvalues[i]) {
                            // 保存商品扩展属性值
                                // 创建一个迭代变量
                                GoodsTypeExpandParamValue paramValue = null;
                                // 必须加入\\转义才可以正确分割
                                String[] values = expandvalues[i].split("\\|");
                                for (int k = 0; k < values.length; k++) {
                                    paramValue = new GoodsTypeExpandParamValue();
                                    paramValue
                                            .setExpandparamId(Long
                                                    .parseLong(String
                                                            .valueOf(paramId)));
                                    paramValue
                                            .setExpandparamValueDelflag(ValueUtil.DEFAULTDELFLAG);
                                    paramValue
                                            .setExpandparamValueName(values[k]);
                                    this.goodsTypeExpandParamValueService
                                            .saveParamValue(paramValue,
                                                    username);
                                }
                                // 置空参数
                                values = null;
                                // 置空参数
                                paramValue = null;
                            }
                    }
                    // 置空参数
                    typeExpandParam = null;
                }
                // 保存详细参数
                saveParam(username, lastId, paramname, paramnickname);
                // 保存成功
                return 1;
            } finally {
                //打印日志
                LOGGER.info(ValueUtil.SAVETYPEANDPARAM + username);
                // 指控所有的参数
                lastId = null;
            }
        }
        // 保存失败返回0
        return 0;
    }

    /**
     * 保存商品类型关联品牌
     *
     * @param username 操作人名称呢过
     * @param lastId   类型ID
     * @param brandIds 商品类型ID数组
     */
    @Transactional
    private void saveBrand(String username, Long lastId, String[] brandIds) {
        if (brandIds != null) {
            // 创建一个迭代对象
            GoodsTypeBrand goodsTypeBrand = null;
            for (int i = 0; i < brandIds.length; i++) {
                // 添加进各种参数
                goodsTypeBrand = new GoodsTypeBrand();
                goodsTypeBrand.setTypeId(lastId);
                goodsTypeBrand.setBrandId(Long.parseLong(brandIds[i]));
                goodsTypeBrand.setDelflag(ValueUtil.DEFAULTDELFLAG);
                //执行添加方法
                this.goodsTypeBrandService.insertTypeBrand(goodsTypeBrand,
                        username);
            }
            // 置空参数
            goodsTypeBrand = null;
        }
    }

    /**
     * 保存商品类型关联规格表
     *
     * @param username 操作人名称
     * @param lastId   插入的最新的类型ID
     * @param specIds  关联的类型的数组
     */
    @Transactional
    private void saveTypeSpec(String username, Long lastId, String[] specIds) {
        if (specIds != null) {
            // 创建一个迭代对象
            GoodsTypeSpec goodsTypeSpec = null;
            for (int i = 0; i < specIds.length; i++) {
                //new一个实体类对象
                goodsTypeSpec = new GoodsTypeSpec();
                //设置属性值
                goodsTypeSpec.setTypeId(lastId);
                goodsTypeSpec.setSpecId(Long.parseLong(specIds[i]));
                goodsTypeSpec.setTypeSpecDelflag(ValueUtil.DEFAULTDELFLAG);
                //执行保存方法
                this.goodsTypeSpecService.saveTypeSpec(goodsTypeSpec, username);
            }
            // 置空参数
            goodsTypeSpec = null;
        }
    }

    /**
     * 保存商品详细参数
     *
     * @param username      操作人名称呢过
     * @param lastId        插入的类型的最新ID
     * @param paramname     详细参数名
     * @param paramnickname 详细参数别名
     */
    @Transactional
    private void saveParam(String username, Long lastId, String[] paramname,
                           String[] paramnickname) {
        // 保存商品详细参数
        if (null != paramname) {
            GoodsTypeParam goodsTypeParam = null;
            for (int i = 0; i < paramname.length; i++) {
                //new一个实体类对象
                goodsTypeParam = new GoodsTypeParam();
                //设置参数
                goodsTypeParam.setParamDelflag(ValueUtil.DEFAULTDELFLAG);
                goodsTypeParam.setParamName(paramname[i]);
                goodsTypeParam.setParamNickname(paramnickname[i]);
                goodsTypeParam.setTypeId(lastId);
                //执行保存方法
                this.goodsTypeParamService.saveTypeParam(goodsTypeParam,
                        username);
            }
            // 置空参数
            goodsTypeParam = null;
        }
    }

    /**
     * 查询所有的商品类型
     *
     * @return 查询到的列表 {@link java.util.List}
     * {@link com.ningpai.goods.bean.GoodsType}
     */
    public List<Object> queryAllType() {
        //定义一个HashMap集合
        Map<String, Integer> map = new HashMap<String, Integer>();
        try {
            //设置开始行数
            map.put(ValueUtil.STARTROWNUM, 0);
            //设置结束行数
            map.put(ValueUtil.ENDROWNUM, this.goodsTypeMapper.queryTotalCount());
            //执行查询方法
            return this.goodsTypeMapper.selectAllType(map);
        } finally {
            map = null;
        }
    }

    /**
     * 根据商品分类ID查询商品类型VO
     *
     * @param catId 商品分类ID {@link java.lang.Long}
     * @return 查询到的VO实体 {@link com.ningpai.goods.vo.GoodsTypeVo}
     */
    public GoodsTypeVo queryTypeVoByCatId(Long catId) {
        //执行查询方法
        return this.goodsTypeMapper.queryTypeVoByCatId(catId);
    }

    /**
     * @param pageBean
     * @param selectBean
     * @return PageBean
     */
    public PageBean searchListByPageBean(PageBean pageBean,
                                         SelectBean selectBean) {
        pageBean.setObjectBean(selectBean);
        // 判断查询文本是否为空 若为空 将条件也设置为空
        if ("".equals(selectBean.getSearchText())) {
            selectBean.setCondition("");
        }
        //设置总行数
        pageBean.setRows(this.goodsTypeMapper.searchTotalCount(selectBean));
        //定义一个HashMap集合
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            //设置开始行数
            map.put(ValueUtil.STARTROWNUM, pageBean.getStartRowNum());
            //设置结束行数
            map.put(ValueUtil.ENDROWNUM, pageBean.getEndRowNum());
            //设置参数
            map.put(ValueUtil.CONDITION, selectBean.getCondition());
            map.put(ValueUtil.SEARCHTEXT, selectBean.getSearchText());
            //设置集合数据
            pageBean.setList(this.goodsTypeMapper.searchAllType(map));

        } finally {
            map = null;
        }
        //返回PageBean
        return pageBean;
    }

    /**
     * 导出商品类型
     *
     * @param response
     */
    @SuppressWarnings("deprecation")
    @Override
    public void exportGoodsType(HttpServletResponse response) {
        GoodsType type = null;
        //查询数据并赋值给list集合
        List<GoodsType> types = goodsTypeMapper.selectAll();
        HSSFWorkbook wb = new HSSFWorkbook();
        CellStyle style = wb.createCellStyle();
        HSSFRow tempRow;
        style.setAlignment(CellStyle.ALIGN_CENTER);
        HSSFSheet sheet1 = wb.createSheet("商品分类列表");
        sheet1.createFreezePane(255, 1);
        HSSFRow headRow = sheet1.createRow(0);
        headRow.createCell(0).setCellValue("类型id");
        headRow.createCell(1).setCellValue("类型名称");
        if (null != types && !types.isEmpty()) {
            // 循环传递过来的货品列表,并添加到文件中
            for (int i = 0; i < types.size(); i++) {
                type = (GoodsType) types.get(i);
                tempRow = sheet1.createRow(1 + i);
                tempRow.createCell(0).setCellValue(type.getTypeId());
                tempRow.createCell(1).setCellValue(type.getTypeName());
            }

        }
        // 设置下载时客户端Excel的名称
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename="
                + URLEncoder.encode("商品类型备份.xls"));
        OutputStream ouputStream;
        try {
            ouputStream = response.getOutputStream();
            wb.write(ouputStream);
            ouputStream.flush();
            ouputStream.close();
        } catch (IOException e) {
            LOGGER.error(""+e);
            ouputStream = null;
        } finally {
            type = null;
            wb = null;
            ouputStream = null;
            style = null;
            tempRow = null;
        }
    }

    /**
     * 验证类型名称是否可用
     *
     * @param typeName 待验证的类型名称
     * @return 可用返回true 不可用返回false
     */
    public boolean checkTypeName(String typeName) {
        //执行方法返回结果
        return this.goodsTypeMapper.queryCountByTypeName(typeName) > 0 ? false
                : true;
    }

}
