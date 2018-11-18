/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.site.goods.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ningpai.goods.bean.GoodsCate;
import com.ningpai.goods.site.goods.dao.GoodsCateMapper;
import com.ningpai.goods.site.goods.service.GoodsCateService;
import com.ningpai.goods.site.goods.vo.GoodsBreadCrumbVo;
import com.ningpai.goods.site.goods.vo.GoodsCateVo;

/***
 * 商品分类Service
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年1月13日 下午3:34:01
 * @version 1.0
 */
@Service("GoodsSiteGoodsCateService")
public class GoodsCateServiceImpl implements GoodsCateService {

    /**
     *商品分类Dao
     */
    private GoodsCateMapper goodsCateMapper;
    /**
     *商品分类Dao
     */
    public GoodsCateMapper getGoodsCateMapper() {
        return goodsCateMapper;
    }
    /**
     *商品分类Dao
     */
    @Resource(name = "GoodsSiteGoodsCateMapper")
    public void setGoodsCateMapper(GoodsCateMapper goodsCateMapper) {
        this.goodsCateMapper = goodsCateMapper;
    }

    /**
     * 查询所有的商品分类
     *
     * @return 排序好的商品分类
     * @see com.ningpai.goods.service.GoodsCateService#queryAllCate()
     */
    public GoodsCateVo queryAllCate() {
        //定义一个商品分类
        GoodsCateVo topParent = null;
        //定义一个商品分类集合
        List<GoodsCateVo> allCate = null;
        try {
            //查询数据并赋值给商品分类实体类
            topParent = this.goodsCateMapper.queryTopParent();
            //查询集合数据并赋值给商品分类集合
            allCate = this.goodsCateMapper.queryAllCate();
            //通过调用递归方法 得到排序好的集合
            topParent.setCateVos(this.calcCateVo(topParent.getCatId(), allCate));
            return topParent;
        } finally {
            topParent = null;
            allCate = null;
        }
    }

    /***
     * 递归判断
     * 
     * @param parentId
     *            父分类ID
     * @param allCateList
     *            所有的分类列表
     * @return 排序好的集合
     */
    public List<GoodsCateVo> calcCateVo(Long parentId, List<GoodsCateVo> allCateList) {
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
                //判断父类ID是否等于遍历的对象的父类ID
                if (parentId.equals(allCateList.get(i).getCatParentId())) {
                    // 必须在这里new对象 否则会覆盖原先的数据
                    cateVo = new GoodsCateVo();
                    //将对象赋值给迭代分类
                    cate = allCateList.get(i);
                    //分类实体的赋值
                    cateVo.setCatId(cate.getCatId());
                    cateVo.setCatName(cate.getCatName());
                    cateVo.setCatParentId(cate.getCatParentId());
                    cateVo.setCatSort(cate.getCatSort());
                    cateVo.setTypeId(cate.getTypeId());
                    cateVo.setCatDelflag(cate.getCatDelflag());
                    cateVo.setCatGrade(cate.getCatGrade());
                    cateVo.setCatSeoDesc(cate.getCatSeoDesc());
                    cateVo.setCatSeoKeyword(cate.getCatSeoKeyword());
                    cateVo.setCatSeoTitle(cate.getCatSeoTitle());
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
     * 根据类型ID查询VO信息,仅是查询当前分类本身以及父分类
     *
     * @param catId
     *            类型ID
     * @return 查询到的Vo信息
     * @see com.ningpai.goods.service.GoodsCateService#queryCateByCatId(java.lang .Long)
     */
    public GoodsCateVo queryCateByCatId(Long catId) {
        //商品分类
        GoodsCateVo topParent = null;
        try {
            //根据分类ID查询分类信息
            topParent = this.goodsCateMapper.queryCateVoByCatId(catId);
            return topParent;
        } finally {
            topParent = null;
        }
    }

    /**
     * 根据分类ID查询分类信息,并且计算好所有的子级关系
     *
     * @param catId
     *            分类ID
     * @return 查询到的分类信息
     * @see com.ningpai.goods.service.GoodsCateService#queryCateById(java.lang.Long)
     */
    public GoodsCateVo queryCateById(Long catId) {
        //定义一个商品分类
        GoodsCateVo topParent = null;
        //定义一个商品分类集合
        List<GoodsCateVo> allCate = null;
        try {
            //查询数据并赋值给商品分类实体类
            topParent = this.goodsCateMapper.queryCateVoByCatId(catId);
            //查询集合数据并赋值给商品分类集合
            allCate = this.goodsCateMapper.queryAllCate();
            //通过调用递归方法 得到排序好的集合
            topParent.setCateVos(this.calcCateVo(topParent.getCatId(), allCate));
            return topParent;
        } finally {
            topParent = null;
            allCate = null;
        }
    }

    /**
     * 计算出分类下所有子分类的ID
     *
     * @param cateVo
     *            分类信息
     * @param catIds
     *            子分类的集合
     * @return 计算出的子分类的集合
     * @see com.ningpai.goods.service.GoodsCateService#calcAllSonCatIds(com.ningpai .goods.vo.GoodsCateVo, java.util.List)
     */
    public Long calcAllSonCatIds(GoodsCateVo cateVo, List<Long> catIds) {
        catIds.add(cateVo.getCatId());
        Long catId;
        try {
            //如果商品分类的子分类不为Null并且商品分类的子分类的size大于0
            if (null != cateVo.getCateVos() && !cateVo.getCateVos().isEmpty()) {
                //循环商品分类的子分类
                for (int i = 0; i < cateVo.getCateVos().size(); i++) {
                    catId = this.calcAllSonCatIds(cateVo.getCateVos().get(i), catIds);
                    if (null != catId) {
                        catIds.add(catId);
                    }
                }
            }
            return null;
        } finally {
            catId = null;
        }
    }

    /**
     * 根据分类ID查询下一级的子分类Id集合
     *
     * @param catId
     *            分类ID
     * @return 查询到的集合
     * @see com.ningpai.site.goods.service.GoodsCateService#queryCatIdsByCatId(java .lang.Long)
     */
    public List<GoodsCate> queryCatIdsByCatId(Long catId) {
        //查询结果并赋值
        List<GoodsCate> list = this.goodsCateMapper.querySonCateByCatId(catId);
        try {
            //添加到集合
            list.add(0, this.goodsCateMapper.queryCateByPrimaryKey(catId));
            return list;
        } finally {
            list = null;
        }
    }

    /**
     * 根据分类ID查询分类和父分类信息
     *
     * @param catId
     *            分类ID
     * @return 查询到的分类Vo
     * @see com.ningpai.site.goods.service.GoodsCateService#queryCateAndParCateByCatId (java.lang.Long)
     */
    public GoodsCateVo queryCateAndParCateByCatId(Long catId) {
        //执行操作并返回结果
        return this.goodsCateMapper.queryCateAndParCateByCatId(catId);
    }

    /**
     * 根据分类ID查询面包屑辅助Bean
     *
     * @param catId
     *            分类ID {@link Long}
     * @return 查询到的面包屑辅助Bean {@link GoodsBreadCrumbVo}
     * @see com.ningpai.site.goods.service.GoodsCateService#queryBreadCrubByCatId (java.lang.Long)
     */
    public GoodsBreadCrumbVo queryBreadCrubByCatId(Long catId) {
        //执行操作并返回结果
        return this.goodsCateMapper.queryBreadCrubByCatId(catId);
    }

}
