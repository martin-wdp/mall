/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.service.impl;

import com.ningpai.excel.ImportGoods;
import com.ningpai.goods.bean.GoodsImport;
import com.ningpai.goods.dao.GoodsImportMapper;
import com.ningpai.goods.service.GoodsImportService;
import com.ningpai.goods.util.SelectBean;
import com.ningpai.goods.util.ValueUtil;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商品导入service 实现
 * 
 * @author zhangsl
 * @version 1.0
 */
@Service("GoodsImportService")
public class GoodsImportServiceImpl implements GoodsImportService {

    /**
     * 日志
     * */
    public static final MyLogger LOGGER = new MyLogger(GoodsImportServiceImpl.class);

    private GoodsImportMapper goodsImportMapper;
    private ImportGoods importGoods;

    public GoodsImportMapper getGoodsImportMapper() {
        return goodsImportMapper;
    }

    @Resource(name = "GoodsImportMapper")
    public void setGoodsImportMapper(GoodsImportMapper goodsImportMapper) {
        this.goodsImportMapper = goodsImportMapper;
    }

    public ImportGoods getImportGoods() {
        return importGoods;
    }

    @Resource(name = "ImportGoods")
    public void setImportGoods(ImportGoods importGoods) {
        this.importGoods = importGoods;
    }

    /**
     * 根据主键查询导入Bean
     *
     * @param id
     *            主键ID {@link Long}
     * @return 查询到的实体 {@link GoodsImport}
     */
    public GoodsImport selectByPrimaryKey(Long id) {
        return goodsImportMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据参数和分页条件查询所有的Bean
     *
     * @param pageBean
     *            分页辅助Bean {@link PageBean}
     * @param selectBean
     *            查询辅助Bean {@link SelectBean}
     * @return 查询到的集合封装进PageBean中 {@link PageBean}
     */
    public PageBean selectAllGoodsImport(PageBean pageBean,
            SelectBean selectBean, Long thirdId) {
        // 判断查询文本是否为空 若为空 将条件也设置为空
        if ("".equals(selectBean.getSearchText())) {
            selectBean.setCondition("");
        }
        // 定义一个HashMap集合
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("condition", selectBean.getCondition());
        map.put(ValueUtil.SEARCHTEXT, selectBean.getSearchText());
        map.put("thirdId", thirdId);
        // 设置总行数
        pageBean.setRows(this.goodsImportMapper.queryTotalCount(map));
        try {
            map.put(ValueUtil.STARTROWNUM, pageBean.getStartRowNum());
            map.put(ValueUtil.ENDROWNUM, pageBean.getEndRowNum());
            // 设置数据集合
            pageBean.setList(this.goodsImportMapper.queryAllGoodsImport(map));
        } finally {
            map = null;
        }
        // 返回集合
        return pageBean;
    }

    /**
     * 根据主键更新删除状态
     *
     * @param id
     *            主键ID {@link Long}
     * @return 是否删除成功的标记 {@link Integer}
     */
    @Transactional
    public int deleteByPrimary(Long id) {
        // 根据主键更新删除状态
        return goodsImportMapper.deleteByPrimaryKey(id);
    }

    /**
     * 批量删除商品导入数据
     *
     * @param goodsImportId
     *            需要删除的数据的ID数组 {@link Long}
     * @return 删除的数量 {@link Integer}
     */
    @Transactional
    public int batchDelGoodsImport(Long[] goodsImportId) {
        // 受影响的行数
        Integer count = 0;
        try {
            for (int i = 0; i < goodsImportId.length; i++) {
                // 批量删除商品导入数据
                count += this.goodsImportMapper
                        .deleteByPrimaryKey(goodsImportId[i]);
            }
            return count;
        } finally {
            count = null;
        }

    }

    /**
     * 批量保存商品导入数据
     *
     * @param goodsImports
     *            需要导入数据的集合 {@link List} {@link GoodsImport}
     * @return 导入的条数 {@link Integer}
     */
    @Transactional
    public int saveGoodsImport(List<GoodsImport> goodsImports) {
        Integer count = 0;
        try {
            for (int i = 0; i < goodsImports.size(); i++) {
                // 批量保存商品导入数据
                count += this.goodsImportMapper.insertSelective(goodsImports
                        .get(i));
            }
            return count;
        } finally {
            count = null;
        }
    }

    /**
     * 更新商品导入状态
     *
     * @param id
     *            主键ID {@link Long}
     * @return 更新的数量 {@link Integer}
     */
    @Transactional
    public int updateGoodsImportAdded(Long id) {
        // 更新商品导入状态
        return goodsImportMapper.updateGoodsImportAdded(id);
    }

    /**
     * 导入商品列表
     *
     * @param request
     *            请求对象
     * @param response
     *            响应对象
     * @return 导入的标记 true 成功 false 失败
     */
    public boolean importGoodsByExcel(HttpServletRequest request,
            HttpServletResponse response, MultipartHttpServletRequest request2) {
        MultipartFile file = request2.getFile("importExcel");
        try {
            if (file.isEmpty()) {
                return false;
            } else if (!".xls".equals(file.getOriginalFilename().substring(
                    file.getOriginalFilename().indexOf("."),
                    file.getOriginalFilename().length()))) {
                return false;
            }
            try {
                // 导入商品列表
                List<GoodsImport> goods=this.importGoods.importGoods(file.getInputStream());
                if(goods.isEmpty()){
                    return false;
                }
                return  execImport(goods,request);
            } catch (IOException e) {
                LOGGER.error(""+e);
                return false;
            }
        } finally {
            file = null;
        }
    }

    /**
     * 执行导入商品
     *
     * @param list
     *            待导入的商品列表
     * @param request
     *            请求对象
     * @param catId
     *            分类ID
     * @return 导入标记 true 成功,false 失败
     */
    @Transactional
    public boolean execImport(List<GoodsImport> list, HttpServletRequest request) {
        boolean flag = true;
        GoodsImport goodsImport = null;
        try {
            try {
                for (int i = 0; i < list.size(); i++) {
                    goodsImport = list.get(i);
                    goodsImport.setThirdId((Long) request
                            .getAttribute("imThirdId"));
                    goodsImport.setThirdName((String) request
                            .getAttribute("imThirdName"));
                    // 执行导入商品
                    this.goodsImportMapper.insertSelective(goodsImport);
                }
            } catch (Exception e) {
                LOGGER.error(""+e);
                flag = false;
            }
            return flag;
        } finally {
            goodsImport = null;
        }
    }

}
