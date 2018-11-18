/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.third.grandbrand.mapper.impl;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.ningpai.goods.bean.GoodsBrand;
import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.third.grandbrand.bean.GrandBrand;
import com.ningpai.third.grandbrand.mapper.GrandBrandMapper;

/**
 * <p>品牌授权管理Dao实现</p>
 * @author NINGPAI-LIH
 * @since 2014年5月23日19:27:42
 * @version 1.0
 */
@Repository("GrandBrandMapper")
public class GrandBrandMapperImpl extends BasicSqlSupport implements GrandBrandMapper {

    /**
     * 分页查询列表
     * @param map
     * @return
     */
    public List<Object> queryAllThirdGrandBrand(Map<String, Object> map) {

        return this.selectList("com.ningpai.third.grandbrand.dao.ThirdGrandBrandMapper.queryAllThirdGoodsBrand", map);
    }

    /**
     * 查询该品牌商品的数量
     * @param map
     * @return
     */
    @Override
    public int checkGoodCount(Map<String, Object> map) {
        return this.selectOne("com.ningpai.third.grandbrand.dao.ThirdGrandBrandMapper.checkGoodCount", map);
    }

    /**
     * 查询行数
     * @param map
     * @return
     */
    public int searchGrandBrandCount(Map<String, Object> map) {
        return this.selectOne("com.ningpai.third.grandbrand.dao.ThirdGrandBrandMapper.searchGoodsGoodsCount", map);
    }

    /**
     * 申请品牌
     * @param grandBrands
     */
    public void forTheGoodsBrand(List<GrandBrand> grandBrands) {
        this.insert("com.ningpai.third.grandbrand.dao.ThirdGrandBrandMapper.forTheGoodsBrand", grandBrands);
    }

    /**
     * 未申请品牌列表
     * @param map
     * @return
     */
    public List<Object> forQueryAllThirdGoodsBrand(Map<String, Object> map) {
        return this.selectList("com.ningpai.third.grandbrand.dao.ThirdGrandBrandMapper.forQueryAllThirdGoodsBrand", map);
    }

    /**
     * 更改品牌标记为删除
     * @param goodsBrand 商品品牌
     */
    public void updateGrandBrand(GoodsBrand goodsBrand) {
        this.update("com.ningpai.third.grandbrand.dao.ThirdGrandBrandMapper.updateGrandBrand", goodsBrand);
    }

    /**
     * 批量修改品牌标记为删除
     * @param map
     */
    public void updateGrandBrands(Map<String, Object> map) {
        this.update("com.ningpai.third.grandbrand.dao.ThirdGrandBrandMapper.updateGrandBrands", map);
    }

    /**
     * 查询第三方品牌列表
     * @param map
     * @return
     */
    public List<Object> queryAllByThirdGoodsBrand(Map<String, Object> map) {
        return this.selectList("com.ningpai.third.grandbrand.dao.ThirdGrandBrandMapper.queryAllByThirdGoodsBrand", map);
    }

}
