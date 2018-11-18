/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.thirdaudit.mapper;

import java.util.List;
import java.util.Map;

import com.ningpai.thirdaudit.bean.StoreInfoVo;

/**
 * 商家信息Vo接口
 *
 * */
public interface StoreInfoVoMapper {
    /**
     * 根据装饰城id查询商家
     * 
     * @param pmap
     * @return
     */
    List<Object> selectStoreBySelective(Map<String, Object> pmap);

    /**
     * 根据装饰城id查询商家总数
     * 
     * @param pmap
     * @return
     */
    int selectStoreCountBySelective(Map<String, Object> pmap);

    /**
     * 根据是否推荐到首页查询商家
     * 
     * @return
     */
    List<StoreInfoVo> selectStoreByrecommendedHomePage();
}
