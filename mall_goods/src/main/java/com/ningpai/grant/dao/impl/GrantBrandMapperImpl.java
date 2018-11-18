/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.grant.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.grant.dao.GrantBrandMapper;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * 品牌授权管理Dao实现
 * 
 * @author NINGPAI-LIH
 * @since 2014年5月29日10:14:38
 * @version 1.0
 */
@Repository("GrantBrandMapper")
public class GrantBrandMapperImpl extends BasicSqlSupport implements GrantBrandMapper {

    /**
     * 分页查询列表
     *
     * @return
     */
    public List<Object> queryAllThirdGrandBrand(Map<String, Object> map) {

        return this.selectList("com.ningpai.third.grandbrand.dao.GrantBrandMapper.queryAllGoodsBrand", map);
    }

    /**
     * 查询行数
     *
     * @param map
     * @return
     */
    public int searchGrandBrandCount(Map<String, Object> map) {
        return this.selectOne("com.ningpai.third.grandbrand.dao.GrantBrandMapper.searchGoodsGoodsCount", map);
    }
    /**
     * 更新品牌状态
     *
     * @param map
     */
    @Override
    public void updateGrantBrands(Map<String, Object> map) {
        this.update("com.ningpai.third.grandbrand.dao.GrantBrandMapper.updateGrantBrands", map);
    }

}
