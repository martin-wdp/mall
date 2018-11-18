/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.site.goods.service.impl;

import com.ningpai.goods.bean.GoodsCate;
import com.ningpai.goods.dao.GoodsTypeBrandMapper;
import com.ningpai.site.goods.dao.GoodsCateMapper;
import com.ningpai.site.goods.service.GoodsCateService;
import com.ningpai.site.goods.vo.GoodsBreadCrumbVo;
import com.ningpai.site.goods.vo.GoodsCateVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商品分类Service
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年1月13日 下午3:34:01
 * @version 1.0
 */
@Service("HsiteGoodsCateService")
public class GoodsCateServiceImpl implements GoodsCateService {
    private GoodsCateMapper goodsCateMapper;

    @Resource(name = "GoodsTypeBrandMapper")
    private GoodsTypeBrandMapper goodsTypeBrandMapper;

    public GoodsCateMapper getGoodsCateMapper() {
        return goodsCateMapper;
    }

    @Resource(name = "HsiteGoodsCateMapper")
    public void setGoodsCateMapper(GoodsCateMapper goodsCateMapper) {
        this.goodsCateMapper = goodsCateMapper;
    }

    /**
     * 查询
     * @param cid
     * @return
     */
    @Override
    public GoodsCate findCid(Long cid) {
        return goodsCateMapper.findCid(cid);
    }

    /**
     * 查询分类
     * @param cid
     * @return
     */
    @Override
    public int findCatGrade(Long cid) {
        return goodsCateMapper.findCatGrade(cid);
    }

    /**
     * 查询所有的商品分类
     * @return
     */
    public GoodsCateVo queryAllCate() {
        GoodsCateVo topParent = null;
        List<GoodsCateVo> allCate = null;
        try {
            topParent = this.goodsCateMapper.queryTopParent();
            allCate = this.goodsCateMapper.queryAllCate();
            topParent.setCateVos(this.calcCateVo(topParent.getCatId(), allCate));
            return topParent;
        } finally {
            topParent = null;
            allCate = null;
        }
    }

    /**
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
                if (parentId.equals(allCateList.get(i).getCatParentId())) {
                    // 必须在这里new对象 否则会覆盖原先的数据
                    cateVo = new GoodsCateVo();
                    cate = allCateList.get(i);
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
     * @param catId
     *            类型ID
     * @return
     */
    public GoodsCateVo queryCateByCatId(Long catId) {
        GoodsCateVo topParent = null;
        try {
            topParent = this.goodsCateMapper.queryCateVoByCatId(catId);
            topParent.setParentCat(this.goodsCateMapper.queryCateByPrimaryKey(topParent.getCatParentId()));
            return topParent;
        } finally {
            topParent = null;
        }
    }

    /**
     * 根据分类ID查询分类信息,并且计算好所有的子级关系
     * @param catId
     *            分类ID
     * @return
     */
    public GoodsCateVo queryCateById(Long catId) {
        GoodsCateVo topParent = null;
        List<GoodsCateVo> allCate = null;
        try {
            topParent = this.goodsCateMapper.queryCateVoByCatId(catId);
            allCate = this.goodsCateMapper.queryAllCate();
            topParent.setCateVos(this.calcCateVo(topParent.getCatId(), allCate));
            return topParent;
        } finally {
            topParent = null;
            allCate = null;
        }
    }

    /**
     * 计算出分类下所有子分类的ID
     * @param cateVo
     *            分类信息
     * @param catIds
     *            子分类的集合
     * @return
     */
    public Long calcAllSonCatIds(GoodsCateVo cateVo, List<Long> catIds) {
        catIds.add(cateVo.getCatId());
        Long catId;
        try {
            if (null != cateVo.getCateVos() && !cateVo.getCateVos().isEmpty()) {
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
     * @param catId
     *            分类ID
     * @return
     */
    public List<GoodsCate> queryCatIdsByCatId(Long catId) {
        List<GoodsCate> list = this.goodsCateMapper.querySonCateByCatId(catId);
        try {
            list.add(0, this.goodsCateMapper.queryCateByPrimaryKey(catId));
            return list;
        } finally {
            list = null;
        }
    }

    /**
     * 返回根据分类ID查询下一级的子分类Id集合以及
     * 分类关联的品牌集合
     *
     * @param catId
     *            分类ID
     * @return
     */
    @Override
    public Map<String,Object> queryGoodsCateAndBrandsByCatId(Long catId) {
            Map<String,Object> map=new HashMap<>();
            GoodsCateVo   cate=goodsCateMapper.queryCateVoByCatId(catId);
            List<GoodsCate> list = this.goodsCateMapper.querySonCateByCatId(cate.getCatParentId());
            //list.add(0, this.goodsCateMapper.queryCateByPrimaryKey(catId));
            map.put("goodscates",list);
            map.put("goodsBrands",goodsTypeBrandMapper.queryAllTypeBrand(cate.getTypeId()));
            return map;
    }

    /**
     * 根据分类ID查询分类和父分类信息
     * @param catId
     *            分类ID
     * @return
     */
    public GoodsCateVo queryCateAndParCateByCatId(Long catId) {
        return this.goodsCateMapper.queryCateAndParCateByCatId(catId);
    }

    /**
     * 根据分类ID查询面包屑辅助Bean
     * @param catId
     *            分类ID {@link Long}
     * @return
     */
    public GoodsBreadCrumbVo queryBreadCrubByCatId(Long catId) {
        return this.goodsCateMapper.queryBreadCrubByCatId(catId);
    }

    /**
     * 根据分类ID计算列表页的URL
     * @param catId
     *            分类ID
     * @param level
     * @return
     */
    public String calcCatUrl(Long catId, String level) {
        String url = "";
        if ("1".equals(level)) {
            url = this.goodsCateMapper.queryFirstCatIdByFirstCatId(catId) + "-" + catId.toString();
        } else if ("2".equals(level)) {
            url = this.goodsCateMapper.queryFirstSonCatIdBySecondCatId(catId) + "-"
                    + this.goodsCateMapper.queryParentIdBySecondCatId(catId).toString();
        }
        return url;
    }

    /**
     * 根据分类Name查询面包屑辅助Bean
     *
     * @param catName
     *            分类ID {@link Long}
     * @return 查询到的辅助Bean {@link GoodsBreadCrumbVo}
     */
    @Override
    public GoodsBreadCrumbVo queryBreadCrubByCatName(String catName) {
        // 创建封装容器
        Map<String, Object> map = new HashMap<>();
        // 填充容器
        map.put("catName", catName);
        return goodsCateMapper.queryBreadCrubByCatName(map);
    }

}
