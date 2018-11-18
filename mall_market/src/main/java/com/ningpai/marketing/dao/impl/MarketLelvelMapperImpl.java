package com.ningpai.marketing.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.marketing.bean.MarketLelvel;
import com.ningpai.marketing.dao.MarketLelvelMapper;

/**
 * 促销会员级联接口实现类
 * 
 * @author qiyuanyuan
 *
 */
@Repository("MarketLelvelMapper")
public class MarketLelvelMapperImpl extends BasicSqlSupport implements
        MarketLelvelMapper {

    /*
     * 根据主键删除 参数:主键 返回:删除个数
     * 
     * @ibatorgenerated 2015-05-21 13:15:16
     * 
     * @see
     * com.ningpai.marketing.dao.MarketLelvelMapper#deleteByPrimaryKey(java.
     * lang.Long)
     */
    @Override
    public int deleteByPrimaryKey(Long promotionLelvelId) {
        return this.delete(
                "com.ningpai.dao.MarketLelvelMapper.deleteByPrimaryKey",
                promotionLelvelId);
    }

    /*
     * 插入，空属性也会插入 参数:pojo对象 返回:删除个数
     * 
     * @ibatorgenerated 2015-05-21 13:15:16
     * 
     * @see
     * com.ningpai.marketing.dao.MarketLelvelMapper#insert(com.ningpai.marketing
     * .bean.MarketLelvel)
     */
    @Override
    public int insert(MarketLelvel marketLelvel) {
        return this.insert("com.ningpai.dao.MarketLelvelMapper.insert",
                marketLelvel);
    }

    /*
     * 插入，空属性不会插入 参数:pojo对象 返回:删除个数
     * 
     * @ibatorgenerated 2015-05-21 13:15:16
     * 
     * @see
     * com.ningpai.marketing.dao.MarketLelvelMapper#insertSelective(com.ningpai
     * .marketing.bean.MarketLelvel)
     */
    @Override
    public int insertSelective(MarketLelvel marketLelvel) {
        return this.insert(
                "com.ningpai.dao.MarketLelvelMapper.insertSelective",
                marketLelvel);
    }

    /*
     * 根据主键查询 参数:查询条件,主键值 返回:对象
     * 
     * @ibatorgenerated 2015-05-21 13:15:16
     * 
     * @see
     * com.ningpai.marketing.dao.MarketLelvelMapper#selectByPrimaryKey(java.
     * lang.Long)
     */
    @Override
    public MarketLelvelMapper selectByPrimaryKey(Long promotionLelvelId) {
        return this.selectOne(
                "com.ningpai.dao.MarketLelvelMapper.selectByPrimaryKey",
                promotionLelvelId);
    }

    /*
     * 根据主键修改，空值条件不会修改成null 参数:1.要修改成的值 返回:成功修改个数
     * 
     * @ibatorgenerated 2015-05-21 13:15:16
     * 
     * @see
     * com.ningpai.marketing.dao.MarketLelvelMapper#updateByPrimaryKeySelective
     * (com.ningpai.marketing.bean.MarketLelvel)
     */
    @Override
    public int updateByPrimaryKeySelective(MarketLelvel marketLelvel) {
        return this
                .update("com.ningpai.dao.MarketLelvelMapper.updateByPrimaryKeySelective",
                        marketLelvel);
    }

    /*
     * 根据主键修改，空值条件会修改成null 参数:1.要修改成的值 返回:成功修改个数
     * 
     * @ibatorgenerated 2015-05-21 13:15:16
     * 
     * @see
     * com.ningpai.marketing.dao.MarketLelvelMapper#updateByPrimaryKey(com.ningpai
     * .marketing.dao.MarketLelvelMapper)
     */
    @Override
    public int updateByPrimaryKey(MarketLelvel marketLelvel) {
        return this.update(
                "com.ningpai.dao.MarketLelvelMapper.updateByPrimaryKey",
                marketLelvel);
    }

    /*
     * 根据促销ID查询等级名称
     * 
     * @param marketingId 促销ID{@link java.lang.Long}
     * 
     * @return List
     * 
     * @see
     * com.ningpai.marketing.dao.MarketLelvelMapper#queryLevelNameByMarketingId
     * (java.lang.Long)
     */
    @Override
    public List<MarketLelvel> queryLevelNameByMarketingId(Long marketingId) {
        return this
                .selectList(
                        "com.ningpai.dao.MarketLelvelMapper.queryLevelNameByMarketingId",
                        marketingId);
    }

    /*
     * 根据促销ID删除促销等级
     * 
     * @param marketingId 促销ID{@link java.lang.Long}
     * 
     * @return int
     * 
     * @see
     * com.ningpai.marketing.dao.MarketLelvelMapper#delMarketLevel(java.lang
     * .Long)
     */
    @Override
    public int delMarketLevel(Long marketingId) {
        return this.update("com.ningpai.dao.MarketLelvelMapper.delMarketLevel",
                marketingId);
    }

    /*
     * 根据促销ID查询等级
     * 
     * @param marketingId 促销ID{@link java.lang.Long}
     * 
     * @return List
     * 
     * @see
     * com.ningpai.marketing.dao.MarketLelvelMapper#queryLevelByByMarketingId
     * (java.lang.Long)
     */
    @Override
    public List<MarketLelvel> queryLevelByByMarketingId(Long marketingId) {
        return this.selectList(
                "com.ningpai.dao.MarketLelvelMapper.queryLevelByMarketingId",
                marketingId);
    }

    /*
     * 根据促销活动Id删除等级
     * 
     * @param marketingId 促销ID{@link java.lang.Long}
     * 
     * @return
     * 
     * @see
     * com.ningpai.marketing.dao.MarketLelvelMapper#deleteMarketingLevelBy(java
     * .lang.Long)
     */
    @Override
    public int deleteMarketingLevelBy(Long marketingId) {
        return this
                .update("com.ningpai.dao.MarketLelvelMapper.deleteMarketingLelvelMarketing",
                        marketingId);
    }

}
