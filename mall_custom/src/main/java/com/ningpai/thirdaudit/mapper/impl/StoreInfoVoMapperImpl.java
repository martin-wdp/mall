/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.thirdaudit.mapper.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.thirdaudit.bean.StoreInfoVo;
import com.ningpai.thirdaudit.mapper.StoreInfoVoMapper;
/**
 * 商家信息Vo接口实现类
 *
 * */
@Repository("StoreInfoVoMapper")
public class StoreInfoVoMapperImpl extends BasicSqlSupport implements
        StoreInfoVoMapper {
    /**
     * 根据装饰城id查询商家
     *
     * @param pmap
     * @return
     */
    @Override
    public List<Object> selectStoreBySelective(Map<String, Object> pmap) {
        //
        return this
                .selectList(
                        "com.ningpai.thirdaudit.mapper.StoreInfoVoMapper.selectStoreBySelective",
                        pmap);
    }
    /**
     * 根据装饰城id查询商家总数
     *
     * @param pmap
     * @return
     */
    @Override
    public int selectStoreCountBySelective(Map<String, Object> pmap) {
        //
        return this
                .selectOne(
                        "com.ningpai.thirdaudit.mapper.StoreInfoVoMapper.selectStoreCountBySelective",
                        pmap);
    }
    /**
     * 根据是否推荐到首页查询商家
     *
     * @return
     */
    @Override
    public List<StoreInfoVo> selectStoreByrecommendedHomePage() {
        //
        return this
                .selectList("com.ningpai.thirdaudit.mapper.StoreInfoVoMapper.selectStoreByrecommendedHomePage");
    }

}
