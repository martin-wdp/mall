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
     * 根据扩展参数和分类ID查询商品列表
     *
     * @param pb         分页辅助
     * @param searchBean 查询参数
     * @param catId      分类ID
     * @param distinctId 地区ID
     * @return 封装了List的分页辅助
     */
    public PageBean searchGoodsByType(String type,PageBean pb, GoodsSiteSearchBean searchBean, Long catId, String[] params, String distinctId) {
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
            selListToPageBean(type,pb, map, goodsList);
            return pb;
        }
        finally {
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
     * 查询List放进PageBean中
     *
     * @param pb        分页帮助Bean
     * @param map       参数Map
     * @param goodsList 商品List
     */
    private void selListToPageBean(String type,PageBean pb, Map<String, Object> map, List<Object> goodsList) {
        pb.setRows(this.goodsMapper.searchTotalCountByCatId(map) == null ? 0 : this.goodsMapper.searchTotalCountByCatId(map));
        map.put("startRowNum", pb.getStartRowNum());
        map.put("endRowNum", pb.getEndRowNum());
        if (null != goodsList && !goodsList.isEmpty()) {
            goodsList.get(0);
        }
        List<Object> goods = null;//this.goodsMapper.queryGoodsListByCatId(map);
        List<String> list=new ArrayList<>();
        /*GetADSpecialPer(list);
        map.put("oemList",list);
        goods=this.goodsMapper.queryGoodsListByCatIdAndType(map);*/
        switch (type){
            case "a":GetADSpecialPer(list);
                map.put("oemList",list);
                goods=this.goodsMapper.queryGoodsListByCatIdAndType(map);
                break;
            case "b":GetDZSpecialPer(list);
                map.put("oemList",list);
                goods=this.goodsMapper.queryGoodsListByCatIdAndType(map);break;
            case "c":GetFTSpecialPer(list);
                map.put("oemList",list);
                goods=this.goodsMapper.queryGoodsListByCatIdAndType(map);break;
            case "d":GetLHSpecialPer(list);
                map.put("oemList",list);
                goods=this.goodsMapper.queryGoodsListByCatIdAndType(map);break;
            case "e":GetFuTSpecialPer(list);
                map.put("oemList",list);
                goods=this.goodsMapper.queryGoodsListByCatIdAndType(map);break;
            case "f":GetLNSpecialPer(list);
                map.put("oemList",list);
                goods=this.goodsMapper.queryGoodsListByCatIdAndType(map);break;
            case "g":GetRHYSpecialPer(list);
                map.put("oemList",list);
                goods=this.goodsMapper.queryGoodsListByCatIdAndType(map);break;
            case "h":GetYPSpecialPer(list);
                map.put("nameList",list);
                goods=this.goodsMapper.queryGoodsListByCatIdAndType(map);break;
            default:goods=this.goodsMapper.queryGoodsListByCatId(map);break;
        }
        pb.setList(goods);
    }

    /***
     * 获得奥迪专场数据
     * @return
     */
    private void GetADSpecialPer(List<String> list){
        list.add("LN   052 167 A21");
        list.add("LN   052 169 A1");
        list.add("LN   052 167 A24");
        list.add("LB   000 750 M3");
        list.add("L8KD 698 451");
        list.add("L4FD 698 451 B");
        list.add("L06L 115 562 A");
        list.add("L06E 115 562 A");
        list.add("L06D 115 562");
        list.add("8R0 133 843 K");
        list.add("L8R0 133 843 K");
        list.add("L4GD 133 843 A");
        list.add("L4GD 133 843");
        list.add("L4F0 133 843 B");
        list.add("7L0 129 620 A");
        list.add("4F0 819 439 A");
        list.add("L4GD 819 429");
        list.add("L8KD 819 441 A");
        list.add("7H0 819 631 A");
    }
    /***
     * 获得大众专场数据
     * @return
     */
    private void GetDZSpecialPer(List<String> list){
        list.add("LG   052 512 A2");
        list.add("LG   052 182 A2");
        list.add("LB   000 750 M3");
        list.add("LN   052 167 F4");
        list.add("LN   052 167 V4040");
        list.add("LN   052 167 V1040");
        list.add("L04E 115 561 C");
        list.add("L03C 115 561 B");
        list.add("L03C 115 561 E");
        list.add("L06J 115 403 K");
        list.add("L1KD 129 620 A");
        list.add("L036 129 620 M");
        list.add("L1KD 129 620 D");
        list.add("L1K0 201 051 K");
        list.add("L1K1 819 653 A");
        list.add("L180 819 638");
        list.add("L180 201 511");
        list.add("6Q0 201 051 J");
    }
    /***
     * 获得丰田专场数据
     * @return
     */
    private void GetFTSpecialPer(List<String> list){
        list.add("888680200");
        list.add("888681190");
        list.add("PZT018734GA01");
        list.add("888083517");
        list.add("888083516");
        list.add("888083523");
        list.add("888083518");
        list.add("888083522");
        list.add("888083524");
        list.add("888083520");
        list.add("888083400");
        list.add("882380030");
        list.add("90915YZZJ1");
        list.add("041520P010");
        list.add("90915CA001");
        list.add("90915YZZJ3");
        list.add("90915YZZJ4");
        list.add("90915YZZJ2");
        list.add("415231090");
        list.add("415238020");
        list.add("178010T020");
        list.add("178010H070");
        list.add("178010D050");
        list.add("1780130040");
        list.add("178010P020");
        list.add("1780138051");
        list.add("1780131090");
        list.add("1780131170");
        list.add("1780131110");
        list.add("1780138030");
        list.add("8713906060");
        list.add("2330031160");
        list.add("446530330");
        list.add("446630210");
    }
    /***
     * 获得路虎专场数据
     * @return
     */
    private void GetLHSpecialPer(List<String> list){
        list.add("AJ82766");
        list.add("C2C8432");
        list.add("C2D3670");
        list.add("C2P17595");
        list.add("C2Z21964");
        list.add("C2Z6525");
        list.add("CASA15W20");
        list.add("CASA55W30A");
        list.add("CASE0W20A");
        list.add("LLR009705");
        list.add("LLR011279");
        list.add("LR013148");
        list.add("LLR025306");
        list.add("LLR029078");
        list.add("PHE000112");
        list.add("LR030778");
        list.add("LR002748");
        list.add("LR009705");
        list.add("LR011279");
        list.add("LR011593");
        list.add("LR023977");
        list.add("LR029078");
        list.add("LR036369");
        list.add("LR056138");
        list.add("28LR052652");
        list.add("LR055454");
        list.add("TYK500050");
        list.add("XR858593");
    }
    /***
     * 获得福特专场数据
     * @return
     */
    private void GetFuTSpecialPer(List<String> list){
        list.add("6U7JM2C936AA");
        list.add("FN1119001A");
        list.add("WSDM2C200C");
        list.add("WSSM2C200D2");
        list.add("WSSM2C202AA");
        list.add("XT10QLVC");
        list.add("ESDM6C57A");
        list.add("WSSM2C204A2");
        list.add("WSSM2C915AA");
        list.add("WSSM2C915BA");
        list.add("WSSM2C929AA");
        list.add("WSSM2C929BA");
        list.add("WSSM2C930AA");
        list.add("WSSM2C948AA");
        list.add("1S7J6744MC");
        list.add("7S7G6714DA");
        list.add("C60514302");
        list.add("Z6D614302");
        list.add("9W7E6714AA");
        list.add("BM5G6714AA");
        list.add("1S7G6714DA");
        list.add("6G919601AC");
        list.add("AV619601AD");
        list.add("DS739601AC");
        list.add("Z62213Z40");
        list.add("5M5H18D543AA1");
        list.add("7M519601FA");
        list.add("DG81V3101");
        list.add("9M5918D543AA");
        list.add("AV6N18D543AA");
        list.add("AV6N19G244AA");
        list.add("DG9H18D483BA");
        list.add("7G9118D543AA1");
        list.add("5M519155AA");
    }
    /***
     * 获得雷诺专场数据
     * @return
     */
    private void GetLNSpecialPer(List<String> list){
        list.add("2012C00001");
        list.add("RC00001010");
        list.add("RC00001003");
        list.add("RC01001006");
        list.add("2011C00031");
        list.add("RC01001003");
        list.add("8200371661");
        list.add("440000027R");
        list.add("402069828R");
        list.add("410600771R");
    }
    /***
     * 获得润滑油专场数据
     * @return
     */
    private void GetRHYSpecialPer(List<String> list){
        list.add("LG   052 512 A2");
        list.add("LG   052 182 A2");
        list.add("LB   000 750 M3");
        list.add("LN   052 167 F4");
        list.add("LN   052 167 V4040");
        list.add("LN   052 167 V1040");
    }
    /***
     * 获得汽车用品专场数据
     * @return
     */
    private void GetYPSpecialPer(List<String> list){
        list.add("瑞卡罗 0-12岁 儿童座椅(坐式)");
        list.add("风王 多功能 6023气泵带车载小电器吸尘器(多功能)");
        list.add("IRIS 2L 车用垃圾桶DA3(不可折叠黑)");
        list.add("闪电 汽车闪电六合一多功能救生锤(SD280)");
        list.add("鑫苹果 高级蜡 掸子(高级蜡掸子)");
        list.add("IRIS 60L 车用整理箱BOX600(不可折叠蓝)");
        list.add("大顺 棒球锁 慧翔泽 汽车方向盘防盗锁(棒球锁 双曲线叶片锁芯 蓝色)");
        list.add("神龙干粉式灭火器(MFZ/ABC1型手提式干粉灭火器)");
        list.add("IRIS 25L 钓鱼箱FX37(可折叠绿)");
        list.add("IRIS 40L 整理箱CC21(不可折叠粉)");
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
                goodsProductVo.setGoodsInfoVipPrice(goodsDetailVo.getGoodsVipPrice());
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
