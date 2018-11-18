/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.third.grandbrand.service;

import java.util.List;

import com.ningpai.goods.bean.GoodsBrand;
import com.ningpai.util.PageBean;

/**
 * 品牌授权管理Service
 * 
 * @author NINGPAI-LIH
 * @since 2014年5月23日19:33:52
 * @version 1.0
 */
public interface GrandBrandService {
    /**
     * 查询该品牌商品的数量
     * @return
     */
    int checkGoodCount(Long brandId,Long thirdId);

    /**
     * 分页查询
     * @param pageBean
     * @param goodsBrand 商品品牌
     * @param rateStatus 品牌状态
     * @param thirdId    商家ID
     * @return
     */
    PageBean queryAllGoodsGrandBrand(PageBean pageBean, GoodsBrand goodsBrand, String rateStatus, Long thirdId);

    /**
     * 查询全部品牌
     * 
     * @param thirdId
     *            查询参数
     * @return
     */
    List<Object> queryAllGoodsGrandBrand(Long thirdId);

    /**
     * 查询为加入的品牌
     * 
     * @return
     */
    PageBean queryForGoodsGrandBrand(PageBean pageBean, GoodsBrand goodsBrand, Long thirdId, String forBrand);

    /**
     * 循环申请品牌
     * 
     * @param brandId
     *            品牌id
     * @param thirdId
     *            第三方商品的签约id
     */
    void forTheGoodsBrand(String[] brandId, Long thirdId);

    /**
     * 更改品牌标记为删除
     * 
     * @param goodsBrand 品牌对象
     */
    void updateGrandBrand(GoodsBrand goodsBrand);

    /**
     *批量修改品牌标记为删除
     * 
     * @param brandIds 品牌ids数组
     */
    void updateGrandBrands(Long[] brandIds,Long thirdId);

}
