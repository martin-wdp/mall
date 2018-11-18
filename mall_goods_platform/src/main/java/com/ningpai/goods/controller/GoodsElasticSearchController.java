package com.ningpai.goods.controller;

import com.ningpai.goods.service.GoodsElasticSearchService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

/**
 * 创建商品索引ES
 * @author ggn
 */
@Controller
public class GoodsElasticSearchController {

    // Spring注入
    @Resource(name = "GoodsElasticSearchService")
    private GoodsElasticSearchService goodsElasticSearchServivice;

    /**
     * 插入索引
     */
    @RequestMapping("/insertelasticgoods")
    @ResponseBody
    public int insertLasticGoods() {
        return goodsElasticSearchServivice.createGoodsIndexToEs();
    }

    /**
     * 插入单个商品索引
     */
    @RequestMapping("/insertIndex")
    @ResponseBody
    public int insertIndex(Long goodsId){
        int result = goodsElasticSearchServivice.insertOneGoodsIndexToEs(goodsId);
        return result;
    }

    /**
     * 根据参数查询商品，并批量插入索引库
     * 2016-01-21 Wuyanbo添加
     */
    @RequestMapping("/insertBatchIndex")
    @ResponseBody
    public int insertBatchIndex(Long goodsId, Long firstCatId, Long secondCatId, Long thirdCatId, Long brandId){
        Map params = new HashMap();
        params.put("goodsId" , goodsId);
        params.put("firstCatId" , firstCatId);
        params.put("secondCatId" , secondCatId);
        params.put("thirdCatId" , thirdCatId);
        params.put("brandId" , brandId);

        int result = goodsElasticSearchServivice.insertBatchGoodsIndexToEs(params);
        return result;
    }

    /**
     * 删除单个商品索引
     */
    @RequestMapping("/deleteIndex")
    @ResponseBody
    public int deleteIndex(Long goodsInfoId){
        int result = goodsElasticSearchServivice.deleteGoodsIndexToEs(goodsInfoId);
        return result;
    }

    /**
     * 更新单个商品索引信息
     */
    @RequestMapping("/updateIndex")
    @ResponseBody
    public int updateIndex(Long goodsId){
        int result = goodsElasticSearchServivice.updateOneGoodsIndexToEs(goodsId);
        return result;
    }
}
