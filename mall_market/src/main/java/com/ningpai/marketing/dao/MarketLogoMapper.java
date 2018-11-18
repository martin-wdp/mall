package com.ningpai.marketing.dao;

import java.util.List;

import com.ningpai.marketing.bean.MarketLogo;

/**
 * 促销促销LOGO级联接口
 * 
 * @author qiyuanyuan
 *
 */

public interface MarketLogoMapper {
    /**
     * 根据主键删除 参数:主键 返回:删除个数
     * 
     * @ibatorgenerated 2015-05-21 13:15:53
     */
    int deleteByPrimaryKey(Long marketLogoId);

    /**
     * 插入，空属性也会插入 参数:pojo对象 返回:删除个数
     * 
     * @ibatorgenerated 2015-05-21 13:15:53
     */
    int insert(MarketLogo marketLogo);

    /**
     * 插入，空属性不会插入 参数:pojo对象 返回:删除个数
     * 
     * @ibatorgenerated 2015-05-21 13:15:53
     */
    int insertSelective(MarketLogo marketLogo);

    /**
     * 根据主键查询 参数:查询条件,主键值 返回:对象
     * 
     * @ibatorgenerated 2015-05-21 13:15:53
     */
    MarketLogoMapper selectByPrimaryKey(Long marketLogoId);

    /**
     * 根据主键修改，空值条件不会修改成null 参数:1.要修改成的值 返回:成功修改个数
     * 
     * @ibatorgenerated 2015-05-21 13:15:53
     */
    int updateByPrimaryKeySelective(MarketLogo marketLogo);

    /**
     * 根据主键修改，空值条件会修改成null 参数:1.要修改成的值 返回:成功修改个数
     * 
     * @ibatorgenerated 2015-05-21 13:15:53
     */
    int updateByPrimaryKey(MarketLogo marketLogo);

    /**
     * 根据促销ID查询促销ＬＯＧＯ
     * 
     * @param marketingId
     *            　　　　　　　　促销id{@link java.lang.Long} 　　
     * @return list
     */
    List<MarketLogo> queryLogoByMarketingId(Long marketingId);

    /**
     * 根据促销ID删除促销Logo
     * 
     * @param marketingId
     *            促销id{@link java.lang.Long} 　　
     * @return int
     */
    int delLogoByMarketingId(Long marketingId);
}