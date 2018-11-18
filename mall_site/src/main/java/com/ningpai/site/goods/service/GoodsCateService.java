/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.site.goods.service;

import com.ningpai.goods.bean.GoodsCate;
import com.ningpai.site.goods.vo.GoodsBreadCrumbVo;
import com.ningpai.site.goods.vo.GoodsCateVo;

import java.util.List;
import java.util.Map;

/**
 * 商品分类serivce接口
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月17日 下午4:52:43
 * @version 1.0
 */
public interface GoodsCateService {
    /**
     * 查询3级分类的第一个分类
     * @param cid
     * @return
     */
    GoodsCate findCid(Long cid);
    /**
     * 查询分类
     * @param cid
     * @return
     */
    int findCatGrade(Long cid);
    /**
     * 查询所有的商品分类
     * 
     * @return 排序好的商品分类
     */
    GoodsCateVo queryAllCate();

    /**
     * 根据分类ID查询分类信息,并且计算好所有的子级关系
     * 
     * @param catId
     *            分类ID
     * @return 查询到的分类信息
     */
    GoodsCateVo queryCateById(Long catId);

    /**
     * 根据类型ID查询VO信息,仅是查询当前分类本身以及父分类
     * 
     * @param catId
     *            类型ID
     * @return 查询到的Vo信息
     */
    GoodsCateVo queryCateByCatId(Long catId);

    /**
     * 计算出分类下所有子分类的ID
     * 
     * @param cateVo
     *            分类信息
     * @param catIds
     *            子分类的集合
     * @return 计算出的子分类的集合
     */
    Long calcAllSonCatIds(GoodsCateVo cateVo, List<Long> catIds);

    /**
     * 根据分类ID查询下一级的子分类Id集合
     * 
     * @param catId
     *            分类ID
     * @return 查询到的集合
     */
    List<GoodsCate> queryCatIdsByCatId(Long catId);

    /**
     * 返回根据分类ID查询下一级的子分类Id集合以及
     * 分类关联的品牌集合
     *
     * @param catId
     *            分类ID
     * @return
     */
    Map<String,Object> queryGoodsCateAndBrandsByCatId(Long catId);

    /**
     * 根据分类ID查询分类和父分类信息
     * 
     * @param catId
     *            分类ID
     * @return 查询到的分类Vo
     */
    GoodsCateVo queryCateAndParCateByCatId(Long catId);

    /**
     * 根据分类ID查询面包屑辅助Bean
     * 
     * @param catId
     *            分类ID {@link Long}
     * @return 查询到的面包屑辅助Bean {@link GoodsBreadCrumbVo}
     */
    GoodsBreadCrumbVo queryBreadCrubByCatId(Long catId);

    /**
     * 根据分类ID计算列表页的URL
     * 
     * @param catId
     *            分类ID
     * @return 计算好的URL
     */
    String calcCatUrl(Long catId, String level);
    /**
     * 根据分类Name查询面包屑辅助Bean
     *
     * @param catName
     *            分类ID {@link Long}
     * @return 查询到的辅助Bean {@link GoodsBreadCrumbVo}
     */
    GoodsBreadCrumbVo queryBreadCrubByCatName(String catName);
}
