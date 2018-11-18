/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.marketing.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.marketing.bean.Marketing;
import com.ningpai.marketing.dao.MarketingMapper;

/**
 * @author ggn 促销信息实现类
 */
@Repository("MarketingMapper")
public class MarketingMapperImpl extends BasicSqlSupport implements
        MarketingMapper {

    @Override
    public int delleteMarkting(Long marketingId,Long businessId) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("marketingId",marketingId);
        map.put("businessId",businessId);
        return this.delete("com.ningpai.marketing.dao.MarketingMapper.delleteMarkting",map);
    }

    /*
         * 查询促销信息列表
         *
         * @param paramMap
         *
         * @{link java.util.Map}
         *
         * @return MarketingMapper
         *
         * @see
         * com.ningpai.marketing.dao.MarketingMapper#marketOrderList(java.util.Map)
         */
    @Override
    public List<Object> marketOrderList(Map<String, Object> paramMap) {
        return this.selectList(
                "com.ningpai.marketing.dao.MarketingMapper.marketOrderList",
                paramMap);
    }

    /*
     * 查询促销总数
     * 
     * @param paramMap
     * 
     * @{link java.util.Map}
     * 
     * @return int
     * 
     * @see
     * com.ningpai.marketing.dao.MarketingMapper#marketOrderListCount(java.util
     * .Map)
     */
    @Override
    public int marketOrderListCount(Map<String, Object> paramMap) {
        return this
                .selectOne(
                        "com.ningpai.marketing.dao.MarketingMapper.marketOrderListCount",
                        paramMap);
    }

    /*
     * 查询促销详细信息
     * 
     * @param marketingId
     * 
     * @{link java.lang.Long}
     * 
     * @return Marketing
     * 
     * @see
     * com.ningpai.marketing.dao.MarketingMapper#marketingDetail(java.lang.Long)
     */
    @Override
    public Marketing marketingDetail(Long marketingId) {
        return this.selectOne(
                "com.ningpai.marketing.dao.MarketingMapper.marketingDetail",
                marketingId);
    }

    /**
     * 查询促销详细信息（不根据时间）
     * 
     * @param marketingId
     * @return
     */
    @Override
    public Marketing marketingDetailNotTime(Long marketingId) {
        return this
                .selectOne(
                        "com.ningpai.marketing.dao.MarketingMapper.marketingDetailNotTime",
                        marketingId);
    }

    /**
     * 根据日期查询促销信息
     * 
     * @param marketingId
     * @return
     */
    @Override
    public Marketing marketingDetailForTime(Long marketingId) {
        return this
                .selectOne(
                        "com.ningpai.marketing.dao.MarketingMapper.marketingDetailForTime",
                        marketingId);
    }

    /*
     * 创建营销
     * 
     * @param marketing
     * 
     * @{link java.lang.Long}
     * 
     * @return int
     * 
     * @see
     * com.ningpai.marketing.dao.MarketingMapper#insertMarketing(com.ningpai
     * .marketing.bean.Marketing)
     */
    @Override
    public int insertMarketing(Marketing marketing) {
        return this.insert(
                "com.ningpai.marketing.dao.MarketingMapper.insertMarketing",
                marketing);
    }

    /*
     * 修改促销
     * 
     * @param marketing
     * 
     * @return
     * 
     * @see
     * com.ningpai.marketing.dao.MarketingMapper#modifyMarketing(com.ningpai
     * .marketing.bean.Marketing)
     */
    @Override
    public int modifyMarketing(Marketing marketing) {
        return this.update(
                "com.ningpai.marketing.dao.MarketingMapper.modifyMarketing",
                marketing);
    }

    /*
     * 查询营销ID
     * 
     * @return Long
     * 
     * @see com.ningpai.marketing.dao.MarketingMapper#selectLastId()
     */
    @Override
    public Long selectLastId() {
        return this
                .selectOne("com.ningpai.marketing.dao.MarketingMapper.selectLastId");
    }

    /*
     * 修改营销
     * 
     * @param marketing {@link com.ningpai.marketing.bean.Marketing}
     * 
     * @return int
     * 
     * @see
     * com.ningpai.marketing.dao.MarketingMapper#updateMarketing(com.ningpai
     * .marketing.bean.Marketing)
     */
    @Override
    public int updateMarketing(Marketing marketing) {
        return this.update(
                "com.ningpai.marketing.dao.MarketingMapper.updateMarketing",
                marketing);
    }

    /*
     * 删除营销
     * 
     * @param
     * 
     * @{link java.lang.Long}
     * 
     * @return int
     * 
     * @see
     * com.ningpai.marketing.dao.MarketingMapper#deleteMarketingById(java.lang
     * .Long)
     */
    @Override
    public int deleteMarketingById(Map<String, Object> paramMap) {
        return this
                .update("com.ningpai.marketing.dao.MarketingMapper.deleteMarketingById",
                        paramMap);
    }

    /*
     * 批量删除促销
     * 
     * @param
     * 
     * @{link java.util.List}
     * 
     * @return int
     * 
     * @see
     * com.ningpai.marketing.dao.MarketingMapper#delAllMarketingByIds(java.util
     * .List)
     */
    @Override
    public int delAllMarketingByIds(Map<String, Object> paramMap) {

        return this
                .update("com.ningpai.marketing.dao.MarketingMapper.delAllMarketingByIds",
                        paramMap);
    }

    /*
     * 查询即将过期的促销
     * 
     * @param paramMap 查询参数
     * 
     * @return int
     * 
     * 
     * @see
     * com.ningpai.marketing.dao.MarketingMapper#sellerMarketingOverdueCount
     * (java.util.Map)
     */
    @Override
    public int sellerMarketingOverdueCount(Map<String, Object> paramMap) {
        return this
                .selectOne(
                        "com.ningpai.marketing.dao.MarketingMapper.sellerMarketingOverdueCount",
                        paramMap);
    }

    /*
     * 查询某个商品下的订单促销
     * 
     * @return 查询到的促销
     * 
     * @author NINGPAI-LIH
     * 
     * @see
     * com.ningpai.marketing.dao.MarketingScopeMapper#queryOrderMarketingByGoodsId
     * (java.util.Map)
     */
    @Override
    public List<Marketing> queryOrderMarketingByGoodsId(Map<String, Object> map) {
        return this
                .selectList(
                        "com.ningpai.marketing.dao.MarketingMapper.queryOrderMarketingByGoodsId",
                        map);
    }

    /**
     * 根据商品id和促销类型查询促销信息
     *
     * @param
     * @return
     */
    @Override
    public Marketing queryMarketingByGoodIdAndtype(Map<String, Object> map) {
        return this
                .selectOne(
                        "com.ningpai.marketing.dao.MarketingMapper.queryMarketingByGoodIdAndtype",
                        map);
    }

    @Override
    public List<Marketing> queryMarketingByGoodIdAndtypeList(Map<String, Object> map) {
        return this
                .selectList(
                        "com.ningpai.marketing.dao.MarketingMapper.queryMarketingByGoodIdAndtypeList",
                        map);
    }

    /*
     * 根据商品和活动分组id查询优惠id
     * 
     * @return 优惠id
     * 
     * @author NINGPAI-LIH
     * 
     * @see
     * com.ningpai.marketing.dao.MarketingMapper#queryByCreatimeMarketing(java
     * .util.Map)
     */
    @Override
    public Marketing queryByCreatimeMarketing(Map<String, Object> map) {
        return this
                .selectOne(
                        "com.ningpai.marketing.dao.MarketingMapper.queryByCreatimeMarketing",
                        map);
    }

    /*
     * 根据商品和活动分组id查询优惠id
     * 
     * @return 优惠id
     * 
     * @author NINGPAI-LIH
     * 
     * @see
     * com.ningpai.marketing.dao.MarketingMapper#queryByCreatimemarketings(java
     * .util.Map)
     */
    @Override
    public List<Marketing> queryByCreatimemarketings(Map<String, Object> map) {
        return this
                .selectList(
                        "com.ningpai.marketing.dao.MarketingMapper.queryByCreatimemarketings",
                        map);
    }

    /*
     * 查询团购所用的促销信息
     * 
     * @return 优惠id
     * 
     * @author NINGPAI-LIH
     * 
     * 
     * @see
     * com.ningpai.marketing.dao.MarketingMapper#queryByCreatimeMarketingGroupon
     * (java.util.Map)
     */
    @Override
    public List<Marketing> queryByCreatimeMarketingGroupon(
            Map<String, Object> map) {
        return this
                .selectList(
                        "com.ningpai.marketing.dao.MarketingMapper.queryByCreatimeMarketingGroupon",
                        map);
    }

    /*
     * 
     * 查询抢购所用的促销信息
     * 
     * @return 优惠id
     * 
     * @see
     * com.ningpai.marketing.dao.MarketingMapper#queryByCreatimeMarketingRush
     * (java.util.Map)
     */
    @Override
    public List<Marketing> queryByCreatimeMarketingRush(Map<String, Object> map) {
        return this
                .selectList(
                        "com.ningpai.marketing.dao.MarketingMapper.queryByCreatimeMarketingMR",
                        map);
    }

    /**
     * 根据marketingId查询促销信息
     * 
     * @param marketingId
     * @return
     */
    @Override
    public Marketing searchMarketByMarketingId(Long marketingId) {
        return this
                .selectOne(
                        "com.ningpai.marketing.dao.MarketingMapper.searchMarketByMarketingId",
                        marketingId);
    }

    /*
     * 查询促销信息列表
     * 
     * @param paramMap
     * 
     * @{link java.util.Map}
     * 
     * @return MarketingMapper
     * 
     * @see
     * com.ningpai.marketing.dao.MarketingMapper#marketingList(java.util.Map)
     */
    @Override
    public List<Object> marketingList(Map<String, Object> paramMap) {
        return this.selectList(
                "com.ningpai.marketing.dao.MarketingMapper.marketList",
                paramMap);
    }

    /*
     * 查询促销总数
     * 
     * @param paramMap
     * 
     * @{link java.util.Map}
     * 
     * @return int
     * 
     * @see
     * com.ningpai.marketing.dao.MarketingMapper#marketingListCount(java.util
     * .Map)
     */
    @Override
    public int marketingListCount(Map<String, Object> paramMap) {
        return this.selectOne(
                "com.ningpai.marketing.dao.MarketingMapper.marketListCount",
                paramMap);
    }

}
