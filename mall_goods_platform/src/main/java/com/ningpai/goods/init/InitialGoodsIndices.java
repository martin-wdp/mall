package com.ningpai.goods.init;

import com.ningpai.goods.service.GoodsElasticSearchService;

/**
 * <p>
 *     初始化商品索引数据
 * </p>
 * @author liangck
 * @version 1.0
 * @since 15/8/25 16:34
 */
public class InitialGoodsIndices {
    /**商品索引service**/
    private GoodsElasticSearchService elasticSearchService;

    /**
     * 初始化创建索引数据
     */
    public void init(){
        elasticSearchService.createGoodsIndexToEs();
    }


    public GoodsElasticSearchService getElasticSearchService() {
        return elasticSearchService;
    }

    public void setElasticSearchService(GoodsElasticSearchService elasticSearchService) {
        this.elasticSearchService = elasticSearchService;
    }
}
