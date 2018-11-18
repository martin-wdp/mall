/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ningpai.goods.bean.GoodsCate;
import com.ningpai.goods.util.SelectBean;
import com.ningpai.goods.vo.GoodsCateVo;
import com.ningpai.util.PageBean;

/**
 * 商品分类serivce接口
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月17日 下午4:52:43
 * @version 1.0
 */
public interface GoodsCateService {
    /**
     * 添加一个商品分类
     * 
     * @param goodsCate
     *            {@link com.ningpai.goods.bean.GoodsCate}
     * @return 影响的行数 {@link java.lang.Integer}
     */
    int insertGoodsCate(GoodsCate goodsCate, String username);

    /**
     * 根据主键ID删除记录
     * 
     * @param catId
     *            分类的主键ID {@link java.lang.Long}
     * @param username
     *            删除人名称
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    int delGoodsCate(Long catId, String username);

    /**
     * 批量删除商品分类
     * 
     * @param catIds
     *            商品分类ID数组 {@link java.lang.Long}
     * @param username
     *            删除人名称
     * @return 受影响的行数
     */
    int batchDelGoodsCate(Long[] catIds, String username);

    /**
     * 更新商品分类信息
     * 
     * @param goodsCate
     *            待更新的商品分类实体 {@link com.ningpai.goods.bean.GoodsCate}
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    int updateGoodsCate(GoodsCate goodsCate, String username);

    /**
     * 根据分页帮助类查询分页列表
     * 
     * @param pb
     *            {@link com.ningpai.goods.util.PageBean}
     * @return {@link com.ningpai.goods.util.PageBean}
     */
    PageBean queryCateListByPageBean(PageBean pb, SelectBean selectBean);

    /**
     * 根据主键ID查询
     * 
     * @param catId
     * @return
     */
    GoodsCateVo queryGoodsCateById(Long catId);

    /**
     * 获得分类下的所有的子分类
     * 
     * @param pb
     *            分页辅助类 {@link com.ningpai.util.PageBean}
     * @return 整理好的分类列表
     */
    List<Object> getCateList(PageBean pb, SelectBean selectBean);

    /**
     * 使用递归根据父分类ID计算所有的自己分类
     * 
     * @param parentId
     *            第一级的父分类ID
     * @param allCateList
     *            所有的分类 {@link java.util.List}
     *            {@link com.ningpai.goods.vo.GoodsCateVo}
     * @return 计算好的分类实体
     */
    List<GoodsCateVo> calcCateVo(Long parentId, List<GoodsCateVo> allCateList);

    /**
     * 查询所有的商品分类
     * 
     * @return 查询到的商品分类的集合 {@link java.util.List}
     */
    List<GoodsCateVo> queryAllCate();
    /**
     * 查询一级的商品分类
     *
     * @return 所有的商品分类列表 {@link java.util.List}
     *         {@link com.ningpai.goods.bean.GoodsCate}
     */
    List<GoodsCateVo> queryFirstLevelGoodsCate();

    /**
     * 验证是否可以删除 如果传递过来的分类 下面有子类 就返回false表示不可以删除
     * 
     * @param specId
     *            将要删除的分类ID
     * @return 是否可以删除
     */
    boolean checkDelWithCateId(Long cateId);

    /**
     * 根据分类名称查询商品分类
     * 
     * @param cateName
     *            分类名称
     * @return 查询到的实体 {@link com.ningpai.goods.bean.GoodsCate}
     */
    GoodsCate queryCateByCateName(String cateName);

    /**
     * 查询所有分类
     * 
     * @return List
     */
    List<GoodsCate> queryAllGoodCate();

    /**
     * 根据父分类ID 查询子分类列表
     * 
     * @param parentId
     *            父分类ID {@link Long}
     * @return 查询到的分类列表 {@link Long}
     */
    List<GoodsCate> querySonCateByParentId(Long parentId);

    /**
     * 根据父分类ID 查询子分类列表
     *
     * @param parentId
     *            父分类ID {@link Long}
     * @param cateName
     *            父类名称
     * @return 查询到的分类列表 {@link Long}
     */
    List<GoodsCate> querySonCateByParentIdAndName(Long parentId, String cateName);

    /**
     * 根据父分类ID 查询子分类列表
     *
     * @param parentId
     *            父分类ID {@link Long}
     * @param cateName
     *            父类名称
     * @return 查询到的分类列表 {@link Long}
     */
    List<GoodsCateVo> querySonCateVoByParentIdAndName(Long parentId,
            String cateName);

    /**
     * 查询所有三级分类
     * 
     * @return List
     */
    List<GoodsCate> queryAllGoodThirdCate();

    /**
     * 导出商品分类
     * 
     * @param response
     */
    void exportGoodsCate(HttpServletResponse response);

    /**
     * 导出商品分类模板
     * 
     * @param response
     */
    void exportGoodsCateTemp(HttpServletResponse response);

    /**
     * 导入商品分类
     * 
     * @param request
     * @param response
     * @param multiRequest
     */
    String importGoodsCateByExcel(HttpServletRequest request,
            HttpServletResponse response,
            MultipartHttpServletRequest multiRequest);

}
