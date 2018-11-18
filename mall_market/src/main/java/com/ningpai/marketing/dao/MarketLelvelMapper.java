package com.ningpai.marketing.dao;

import java.util.List;

import com.ningpai.marketing.bean.MarketLelvel;

/**
 * 促销会员级联接口
 * 
 * @author qiyuanyuan
 *
 */
public interface MarketLelvelMapper {
    /**
     * 根据主键删除 参数:主键 返回:删除个数
     * 
     * @ibatorgenerated 2015-05-21 13:15:16
     */
    int deleteByPrimaryKey(Long promotionLelvelId);

    /**
     * 插入，空属性也会插入 参数:pojo对象 返回:删除个数
     * 
     * @ibatorgenerated 2015-05-21 13:15:16
     */
    int insert(MarketLelvel marketLelvel);

    /**
     * 插入，空属性不会插入 参数:pojo对象 返回:删除个数
     * 
     * @ibatorgenerated 2015-05-21 13:15:16
     */
    int insertSelective(MarketLelvel marketLelvel);

    /**
     * 根据主键查询 参数:查询条件,主键值 返回:对象
     * 
     * @ibatorgenerated 2015-05-21 13:15:16
     */
    MarketLelvelMapper selectByPrimaryKey(Long promotionLelvelId);

    /**
     * 根据主键修改，空值条件不会修改成null 参数:1.要修改成的值 返回:成功修改个数
     * 
     * @ibatorgenerated 2015-05-21 13:15:16
     */
    int updateByPrimaryKeySelective(MarketLelvel marketLelvel);

    /**
     * 根据主键修改，空值条件会修改成null 参数:1.要修改成的值 返回:成功修改个数
     * 
     * @ibatorgenerated 2015-05-21 13:15:16
     */
    int updateByPrimaryKey(MarketLelvel marketLelvel);

    /**
     * 根据促销ID查询等级名称
     * 
     * @param marketingId
     *            促销ID{@link java.lang.Long}
     * @return List
     */
    List<MarketLelvel> queryLevelNameByMarketingId(Long marketingId);

    /**
     * 根据促销ID删除促销等级
     * 
     * @param marketingId
     *            促销ID{@link java.lang.Long}
     * @return int
     */
    int delMarketLevel(Long marketingId);

    /**
     * 根据促销ID查询等级
     * 
     * @param marketingId
     *            促销ID{@link java.lang.Long}
     * @return List
     */
    List<MarketLelvel> queryLevelByByMarketingId(Long marketingId);

    /**
     * 根据促销活动Id删除等级
     * 
     * @param marketingId
     *            促销ID{@link java.lang.Long}
     * @return
     */
    int deleteMarketingLevelBy(Long marketingId);

}