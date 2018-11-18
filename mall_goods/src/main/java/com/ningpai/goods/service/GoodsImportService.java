/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ningpai.goods.bean.GoodsImport;
import com.ningpai.goods.util.SelectBean;
import com.ningpai.util.PageBean;

/**
 * 商品导入临时数据表Service接口
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年10月20日 上午9:56:15
 * @version 1.0
 */
public interface GoodsImportService {
    /**
     * 根据主键查询导入Bean
     * 
     * @param id
     *            主键ID {@link Long}
     * @return 查询到的实体 {@link GoodsImport}
     */
    GoodsImport selectByPrimaryKey(Long id);

    /**
     * 根据参数和分页条件查询所有的Bean
     * 
     * @param pageBean
     *            分页辅助Bean {@link PageBean}
     * @param selectBean
     *            查询辅助Bean {@link SelectBean}
     * @return 查询到的集合封装进PageBean中 {@link PageBean}
     */
    PageBean selectAllGoodsImport(PageBean pageBean, SelectBean selectBean,
            Long thirdId);

    /**
     * 根据主键更新删除状态
     * 
     * @param id
     *            主键ID {@link Long}
     * @return 是否删除成功的标记 {@link Integer}
     */
    int deleteByPrimary(Long id);

    /**
     * 批量删除商品导入数据
     * 
     * @param goodsImportId
     *            需要删除的数据的ID数组 {@link Long}
     * @return 删除的数量 {@link Integer}
     */
    int batchDelGoodsImport(Long[] goodsImportId);

    /**
     * 批量保存商品导入数据
     * 
     * @param goodsImports
     *            需要导入数据的集合 {@link List} {@link GoodsImport}
     * @return 导入的条数 {@link Integer}
     */
    int saveGoodsImport(List<GoodsImport> goodsImports);

    /**
     * 更新商品导入状态
     * 
     * @param id
     *            主键ID {@link Long}
     * @return 更新的数量 {@link Integer}
     */
    int updateGoodsImportAdded(Long id);

    /**
     * 导入商品列表
     * 
     * @param request
     *            请求对象
     * @param response
     *            响应对象
     * @return 导入的标记 true 成功 false 失败
     */
    boolean importGoodsByExcel(HttpServletRequest request,
            HttpServletResponse response, MultipartHttpServletRequest request2);

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
    boolean execImport(List<GoodsImport> list, HttpServletRequest request);

}
