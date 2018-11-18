package com.ningpai.goodsimport.dao;

import com.ningpai.goodsimport.bean.GoodsImport;

/**
 * E店宝同步商品DAO接口
 * 
 * @author qiyuanyuan
 * 
 */
public interface SynGoodsImportMapper {

    /**
     * 检测是否存在
     * 
     * @param goodsNo
     * @return int
     */
    int checkGoods(String goodsNo);

    /**
     * 插入
     * 
     * @param goodImport
     * @return int
     */
    int insertGoods(GoodsImport goodImport);

    /**
     * 修改
     * 
     * @param goodImport
     * @return int
     */
    int updateGoods(GoodsImport goodImport);

}
