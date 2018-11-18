package com.ningpai.goodsimport.service;

import com.ningpai.category.bean.ReturnObject;

/**
 * E店宝商品导入Service接口
 * 
 * @author qiyuanyuan
 * 
 */
public interface SynGoodsImportService {

    /**
     * 同步
     * 
     * @return ReturnObject
     */
    ReturnObject SynchronousGoodsImport();

    /**
     * 同步更新
     * 
     * @param goodsNo
     * @return ReturnObject
     */
    ReturnObject SynchronousUpdateGoodsImport(String goodsNo);

}
