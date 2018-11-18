package com.ningpai.marketing.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.marketing.bean.PreDiscountMarketing;
import com.ningpai.marketing.dao.PreDiscountMarketingMapper;

/**
 * 折扣促销dao实现类
 * 
 * @author zhoux
 *
 */
@Repository("PreDiscountMarketingMapper")
public class PreDiscountMarketingMapperImpl extends BasicSqlSupport implements
        PreDiscountMarketingMapper {

    /**
     * 插入折扣信息
     * 
     * @param pdmlist
     * @return
     */
    @Override
    public int insertSelective(List<PreDiscountMarketing> pdmlist) {
        return this
                .insert("com.ningpai.marketing.dao.PreDiscountMarketingMapper.insertSelective",
                        pdmlist);
    }

    /**
     * 查询促销信息
     * 
     * @param param
     * @return
     */
    @Override
    public PreDiscountMarketing selectByMarketId(Map<String, Object> param) {
        return this
                .selectOne(
                        "com.ningpai.marketing.dao.PreDiscountMarketingMapper.selectByMarketId",
                        param);
    }

    /**
     * 查询折扣促销是否存在
     * 
     * @return
     */
    @Override
    public Integer queryByGoodsIdAndMarketingId(Map<String, Object> map) {
        return this
                .selectOne(
                        "com.ningpai.marketing.dao.PreDiscountMarketingMapper.queryByGoodsIdAndMarketingId",
                        map);
    }

    /**
     * 修改
     * 
     * @param
     * @return
     */
    @Override
    public int modifySelective(PreDiscountMarketing pdm) {
        return this
                .update("com.ningpai.marketing.dao.PreDiscountMarketingMapper.modifySelective",
                        pdm);
    }

    /**
     * 添加信息
     * 
     * @param
     * @return
     */
    @Override
    public int insertSelectiveSingle(PreDiscountMarketing pdm) {
        return this
                .insert("com.ningpai.marketing.dao.PreDiscountMarketingMapper.insertSelectiveSingle",
                        pdm);
    }

    /**
     * 查询促销折扣是否存在
     * 
     * @param map
     * @return
     */
    @Override
    public Integer countPreGoodsByMidAndGid(Map<String, Object> map) {
        return this
                .selectOne(
                        "com.ningpai.marketing.dao.PreDiscountMarketingMapper.countPreGoodsByMidAndGid",
                        map);
    }

    /**
     * 移除促销折扣信息
     * 
     * @param map
     * @return
     */
    @Override
    public Integer removePreGoodsByMidAndGid(Map<String, Object> map) {
        return this
                .update("com.ningpai.marketing.dao.PreDiscountMarketingMapper.removePreGoodsByMidAndGid",
                        map);
    }

    /*
     * 根据促销ID查询数量
     * 
     * @param marketingId
     * 
     * @return
     * 
     * @see
     * com.ningpai.marketing.dao.PreDiscountMarketingMapper#selectCountsByMarketId
     * (java.lang.Long)
     */
    @Override
    public List<PreDiscountMarketing> selectCountsByMarketId(Long marketingId) {
        return this
                .selectList(
                        "com.ningpai.marketing.dao.PreDiscountMarketingMapper.newselectByMarketId",
                        marketingId);
    }

    /*
     * 根据促销活动id删除关联的折扣信息
     * 
     * @param marketingId 促销活动ID{@link java.lang.Long}
     * 
     * @return int
     * 
     * @see
     * com.ningpai.marketing.dao.PreDiscountMarketingMapper#removePreCountByMid
     * (java.lang.Long)
     */
    @Override
    public int removePreCountByMid(Long marketingId) {
        return this
                .update("com.ningpai.marketing.dao.PreDiscountMarketingMapper.removePreGoodsByMid",
                        marketingId);
    }

}
