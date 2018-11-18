/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.grant.service;

import com.ningpai.goods.bean.GoodsBrand;
import com.ningpai.util.PageBean;

/**
 * 品牌授权管理Service
 * 
 * @author NINGPAI-LIH
 * @since 2014年5月29日10:14:25
 * @version 1.0
 */
public interface GrantBrandService {

    /**
     * 分页查询
     * 
     * @param pageBean
     *            分页参数
     * @param goodsBrand
     *            查询参数
     * @return
     */
    PageBean queryAllGoodsGrandBrand(PageBean pageBean, GoodsBrand goodsBrand);

    /**
     * 
     * @param brandIds
     *            要修改的品牌id数组
     */
    void updateGrantBrands(Long[] brandIds, String reason, String rateStatuts);
}
