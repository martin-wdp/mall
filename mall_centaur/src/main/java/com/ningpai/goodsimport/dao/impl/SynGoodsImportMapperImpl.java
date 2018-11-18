package com.ningpai.goodsimport.dao.impl;

import org.springframework.stereotype.Repository;

import com.ningpai.goodsimport.bean.GoodsImport;
import com.ningpai.goodsimport.dao.SynGoodsImportMapper;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * E店宝同步商品DAO接口实现类
 * 
 * @author qiyuanyuan
 * 
 */
@Repository("SynGoodsImportMapper")
public class SynGoodsImportMapperImpl extends BasicSqlSupport implements SynGoodsImportMapper {

    /**
     * 检测是否存在
     * 
     * @see com.ningpai.goodsimport.dao.SynGoodsImportMapper#checkGoods(java.lang
     *      .Long)
     */
    public int checkGoods(String goodsNo) {
        return this.selectOne("com.bbw.web.dao.centaur.GoodsImportMapper.checkGoods", goodsNo);
    }

    /**
     * 插入
     * 
     * @see com.ningpai.goodsimport.dao.SynGoodsImportMapper#insertGoods(com.ningpai
     *      .goodsimport.bean.GoodsImport)
     */
    public int insertGoods(GoodsImport goodImport) {
        return this.insert("com.bbw.web.dao.centaur.GoodsImportMapper.insertSelective", goodImport);
    }

    /**
     * 修改
     * 
     * @see com.ningpai.goodsimport.dao.SynGoodsImportMapper#updateGoods(com.ningpai
     *      .goodsimport.bean.GoodsImport)
     */
    public int updateGoods(GoodsImport goodImport) {
        return this.update("com.bbw.web.dao.centaur.GoodsImportMapper.updateByPrimaryKeySelective", goodImport);
    }

}
