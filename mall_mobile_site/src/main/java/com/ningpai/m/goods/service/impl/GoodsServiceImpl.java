/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.m.goods.service.impl;

import com.ningpai.common.lucene.main.LuceneIKUtil;
import com.ningpai.goods.bean.GoodsSpecDetail;
import com.ningpai.m.goods.dao.GoodsMapper;
import com.ningpai.m.goods.service.GoodsCateService;
import com.ningpai.m.goods.service.GoodsService;
import com.ningpai.m.goods.util.GoodsSiteSearchBean;
import com.ningpai.m.goods.util.ValueUtil;
import com.ningpai.m.goods.vo.*;
import com.ningpai.util.PageBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商品Service实现
 *
 * @author NINGPAI-YuanKangKang
 * @version 1.0
 * @since 2014年1月14日 上午10:44:52
 */
@Service("HsiteGoodsService")
public class GoodsServiceImpl implements GoodsService {

    @Resource(name = "luceneIkUtil")
    private LuceneIKUtil luceneIkUtil;
    private GoodsMapper goodsMapper;
    private GoodsCateService cateService;

    public GoodsCateService getCateService() {
        return cateService;
    }

    @Resource(name = "HsiteGoodsCateService")
    public void setCateService(GoodsCateService cateService) {
        this.cateService = cateService;
    }

    public GoodsMapper getGoodsMapper() {
        return goodsMapper;
    }

    @Resource(name = "HsiteGoodsMapper")
    public void setGoodsMapper(GoodsMapper goodsMapper) {
        this.goodsMapper = goodsMapper;
    }


    /**
     * 查询商品列表
     *
     * @param pb         分页帮助类
     * @param searchBean 查询帮助类
     * @return 查询到的商品列表
     */
    public PageBean searchGoods(PageBean pb, GoodsSiteSearchBean searchBean) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("distinctId", searchBean.getDistinctId());
        // luceneIkUtil = new LuceneIKUtil();
        // 搜索引擎查询
        // List<String>
        // productIds=this.luceneDirceotry.dirceotry(searchBean.getTitle());
        Map<String, Object> resultMap = this.luceneIkUtil.dirceotry(searchBean.getTitle());
        List<String> productIds = (List<String>) resultMap.get("productIds");
        Map<String, String> nameMap = (Map<String, String>) resultMap.get("productLightNames");
        // 如果查询到的是空,就返回list为空的pb
        if (null == productIds) {
            return pb;
        }
        try {
            if (!productIds.isEmpty()) {
                map.put("productIds", productIds);
            } else {
                // 如果查询到的ID是空,就返回PB
                return pb;
            }
            map.put(ValueUtil.SEARCHBEAN, searchBean);
            pb.setRows(this.goodsMapper.searchTotalCount(map) == null ? 0 : this.goodsMapper.searchTotalCount(map));
            map.put("startRowNum", pb.getStartRowNum());
            map.put("endRowNum", pb.getEndRowNum());
            List<Object> goods = this.goodsMapper.searchGoods(map);
            setLightNames(goods, nameMap);
            pb.setList(goods);
            return pb;
        } finally {
            map = null;
            productIds = null;
        }
    }


    private void setLightNames(List<Object> goods, Map<String, String> nameMap) {
        for (Object o : goods) {
            GoodsShowListVo vo = (GoodsShowListVo) o;
            vo.setProductName(nameMap.get(vo.getGoodsInfoId() + ""));
        }

    }

    /**
     * 查询最新的三条记录
     *
     * @param catId 分类Id
     * @return 查询到的集合
     */
    public List<Object> queryTopThreeNew(Long catId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            List<Long> catIds = new ArrayList<Long>();
            this.cateService.calcAllSonCatIds(this.cateService.queryCateById(catId), catIds);
            map.put(ValueUtil.CATIDS, catIds);
            return this.goodsMapper.queryNewInfoTopThree(map);
        } finally {
            map = null;
        }
    }

    /**
     * 根据参数和分类ID查询商品列表
     *
     * @param pb         分页辅助
     * @param searchBean 查询参数
     * @param catId      分类ID
     * @return 封装了List的分页辅助
     */
    public PageBean searchGoods(PageBean pb, GoodsSiteSearchBean searchBean, Long catId) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Object> goodsList = new ArrayList<Object>();
        // 把查询参数置空
        searchBean.setTitle("");
        List<Long> catIds = new ArrayList<Long>();
        GoodsCateVo cate = null;
        try {
            map.put(ValueUtil.SEARCHBEAN, searchBean);
            // 获取分类的Vo信息
            cate = this.cateService.queryCateById(catId);
            // 获取出所有的子级分类的ID集合
            this.cateService.calcAllSonCatIds(cate, catIds);
            map.put("catIds", catIds);
            selListToPageBean(pb, map, goodsList);
            return pb;
        } finally {
            map = null;
            goodsList = null;
            catIds = null;
            cate = null;
        }
    }

    /**
     * 根据扩展参数和分类ID查询商品列表
     *
     * @param pb         分页辅助
     * @param searchBean 查询参数
     * @param catId      分类ID
     * @param distinctId 地区ID
     * @return 封装了List的分页辅助
     */
    public PageBean searchGoods(PageBean pb, GoodsSiteSearchBean searchBean, Long catId, String[] params, String distinctId) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<Object> goodsList = new ArrayList<Object>();
        String brandId = "";
        List<String> paramsList = new ArrayList<String>();
        List<String> specList = new ArrayList<String>();
        // 把查询参数置空
        searchBean.setTitle("");
        List<Long> catIds = new ArrayList<Long>();
        GoodsCateVo cate = null;
        try {
            map.put(ValueUtil.SEARCHBEAN, searchBean);
            // 获取出所有的子级分类的ID集合
            // 获取分类的Vo信息
            if (catId != null) {
                cate = this.cateService.queryCateById(catId);
                // 获取出所有的子级分类的ID集合
                this.cateService.calcAllSonCatIds(cate, catIds);
                map.put("catIds", catIds);
            }
            if (null != params && params.length > 0) {
                for (int i = 0; i < params.length; i++) {
                    /* 如果品牌就放进品牌的值中,如果是扩展参数就放进扩展参数的集合中,如果是规格就放进规格的集合中 */
                    if ("b".equals(params[i].substring(0, 1))) {
                        brandId = params[i].substring(1, params[i].length()).toString();
                    } else if ("e".equals(params[i].substring(0, 1))) {
                        paramsList.add(params[i].subSequence(1, params[i].length()).toString());
                    } else if ("s".equals(params[i].substring(0, 1))) {
                        specList.add(params[i].subSequence(1, params[i].length()).toString());
                    }
                }
            }
            /* 判断品牌不为空 */
            if (null != brandId && !"".equals(brandId)) {
                map.put("brandId", brandId);
            }
            /* 判断扩展参数不为空 */
            if (!paramsList.isEmpty()) {
                map.put("params", paramsList);
                map.put("paramLengh", paramsList.size());
            }
            /* 判断规格集合不为空 */
            if (!specList.isEmpty()) {
                map.put("specs", specList);
                map.put("specsLengh", specList.size());
            }
            map.put("distinctId", distinctId);
            // 查询List放进PageBean中
            selListToPageBean(pb, map, goodsList);
            return pb;
        } finally {
            map = null;
            goodsList = null;
            brandId = null;
            paramsList = null;
            specList = null;
        }
    }

    /**
     * 查询List放进PageBean中
     *
     * @param pb        分页帮助Bean
     * @param map       参数Map
     * @param goodsList 商品List
     */
    private void selListToPageBean(PageBean pb, Map<String, Object> map, List<Object> goodsList) {
        pb.setRows(this.goodsMapper.searchTotalCountByCatId(map) == null ? 0 : this.goodsMapper.searchTotalCountByCatId(map));
        map.put("startRowNum", pb.getStartRowNum());
        map.put("endRowNum", pb.getEndRowNum());
        if (null != goodsList && !goodsList.isEmpty()) {
            goodsList.get(0);
        }
        List<Object> goods = this.goodsMapper.queryGoodsListByCatId(map);
        pb.setList(goods);
    }

    /**
     * 根据商品ID查询商品的详细信息
     *
     * @param goodsId 商品ID
     * @return 查询到的详细信息Bean
     */
    public GoodsDetailVo queryGoodsDetailVoByGoodsId(Long goodsId) {
        return this.goodsMapper.queryGoodsDetailVoByGoodsId(goodsId);
    }

    /**
     * 根据商品详细信息返回默认的货品信息
     *
     * @param goodsDetailVo 商品详细信息
     * @return 默认的货品信息
     */
    public GoodsProductVo returnDefaultGoodsProduct(GoodsDetailVo goodsDetailVo) {
        if (null != goodsDetailVo.getProductVos() && !goodsDetailVo.getProductVos().isEmpty()) {
            return goodsDetailVo.getProductVos().get(0);
        } else {
            GoodsProductVo goodsProductVo = new GoodsProductVo();
            try {
                goodsProductVo.setGoodsInfoStock(0L);
                goodsProductVo.setGoodsInfoCostPrice(goodsDetailVo.getGoodsPrice());
                goodsProductVo.setGoodsInfoMarketPrice(goodsDetailVo.getGoodsPrice());
                goodsProductVo.setGoodsInfoPreferPrice(goodsDetailVo.getGoodsPrice());
                goodsProductVo.setGoodsInfoImgId(goodsDetailVo.getGoodsImg());
                return goodsProductVo;
            } finally {
                goodsProductVo = null;
            }
        }
    }

    /**
     * 根据传递的参数和类型Vo计算已经选择的条件
     *
     * @param params      已经选择的条件 {@link String}
     * @param goodsTypeVo 类型Vo {@link GoodsTypeVo}
     * @return 计算好的选中值的集合 {@link List}
     */
    public List<GoodsListScreenVo> calcScreenParam(String[] params, GoodsTypeVo goodsTypeVo) {
        if (null != params && params.length > 0) {
            List<GoodsListScreenVo> list = new ArrayList<GoodsListScreenVo>();
            GoodsListScreenVo screenVo;
            String param = "";
            try {
                for (int i = 0; i < params.length; i++) {
                    screenVo = new GoodsListScreenVo();
                    param = params[i];
                    if (param.indexOf("b") != -1) {
                        screenVo.setTitle("品牌");
                        screenVo.setValue(param.substring(1, param.length()).toString());
                        screenVo.setParentId("-1");
                        screenVo.setType("brand");
                        /* 设置品牌名称 */
                        for (int j = 0; j < goodsTypeVo.getBrands().size(); j++) {
                            if (goodsTypeVo.getBrands().get(j).getBrand().getBrandId() == Long.parseLong(screenVo.getValue())) {
                                screenVo.setText(goodsTypeVo.getBrands().get(j).getBrand().getBrandName());
                                break;
                            }
                        }
                        list.add(screenVo);
                    } else if (param.indexOf("e") != -1) {
                        screenVo.setValue(param.substring(1, param.length()).toString());
                        screenVo.setType("expand");
                        /* 循环类型的扩展参数 */
                        for (int j = 0; j < goodsTypeVo.getExpandParams().size(); j++) {
                            for (int j2 = 0; j2 < goodsTypeVo.getExpandParams().get(j).getValueList().size(); j2++) {
                                if (goodsTypeVo.getExpandParams().get(j).getValueList().get(j2).getExpandparamValueId() == Long.parseLong(screenVo.getValue())) {
                                    screenVo.setTitle(goodsTypeVo.getExpandParams().get(j).getExpandparamName());
                                    screenVo.setParentId(goodsTypeVo.getExpandParams().get(j).getExpandparamId().toString());
                                    screenVo.setText(goodsTypeVo.getExpandParams().get(j).getValueList().get(j2).getExpandparamValueName());
                                    break;
                                }
                            }
                        }
                        list.add(screenVo);
                    } else if (param.indexOf("s") != -1) {
                        screenVo.setValue(param.substring(1, param.length()).toString());
                        screenVo.setType("spec");
                        /* 循环规格信息 */
                        GoodsSpecDetail specDetail;
                        for (int j = 0; j < goodsTypeVo.getSpecVos().size(); j++) {
                            for (int j2 = 0; j2 < goodsTypeVo.getSpecVos().get(j).getGoodsSpec().getSpecDetails().size(); j2++) {
                                specDetail = (GoodsSpecDetail) goodsTypeVo.getSpecVos().get(j).getGoodsSpec().getSpecDetails().get(j2);
                                if (specDetail.getSpecDetailId() == Long.parseLong(screenVo.getValue())) {
                                    screenVo.setText(specDetail.getSpecDetailName());
                                    screenVo.setTitle(goodsTypeVo.getSpecVos().get(j).getGoodsSpec().getSpecName());
                                    screenVo.setParentId(goodsTypeVo.getSpecVos().get(j).getGoodsSpec().getSpecId().toString());
                                    break;
                                }
                            }
                        }
                        list.add(screenVo);
                    }

                }
                return list;
            } finally {
                list = null;
                screenVo = null;
                param = null;
            }
        } else {
            return new ArrayList<GoodsListScreenVo>();
        }
    }

    /**
     * 根据计算好的选中的参数和类型Vo计算vo中需要显示的信息
     *
     * @param screenList 计算好的选中的参数 {@link String}
     * @param typeVo     类型Vo {@link GoodsTypeVo}
     * @return 计算好的typeVo {@link GoodsTypeVo}
     */
    public GoodsTypeVo calcTypeVo(List<GoodsListScreenVo> screenList, GoodsTypeVo typeVo) {
        List<String> expand = new ArrayList<String>();
        List<String> spec = new ArrayList<String>();
        try {
            /* 如果为空 */
            if (null == screenList) {
                return typeVo;
            }
            /* 判断已选参数的集合值 */
            for (int i = 0; i < screenList.size(); i++) {
                if ("brand".equals(screenList.get(i).getType())) {
                    typeVo.setBrands(null);
                } else if ("expand".equals(screenList.get(i).getType())) {
                    expand.add(screenList.get(i).getParentId());
                } else if ("spec".equals(screenList.get(i).getType())) {
                    spec.add(screenList.get(i).getParentId());
                }
            }
            /* 循环匹配已经选中的扩展参数,置空已经选中的值 */
            if (null != expand && !expand.isEmpty()) {
                for (int i = 0; i < expand.size(); i++) {
                    for (int j = 0; j < typeVo.getExpandParams().size(); j++) {
                        if (typeVo.getExpandParams().get(j).getExpandparamId() == Long.parseLong(expand.get(i))) {
                            typeVo.getExpandParams().remove(j);
                        }
                    }
                }
            }
            /* 循环匹配已经选中的规格参数,置空已经选中的值 */
            if (null != spec && !spec.isEmpty()) {
                for (int i = 0; i < spec.size(); i++) {
                    for (int j = 0; j < typeVo.getSpecVos().size(); j++) {
                        if (typeVo.getSpecVos().get(j).getGoodsSpec().getSpecId() == Long.parseLong(spec.get(i))) {
                            typeVo.getSpecVos().remove(j);
                        }
                    }
                }
            }
            return typeVo;
        } finally {
            expand = null;
            spec = null;
        }
    }
}
