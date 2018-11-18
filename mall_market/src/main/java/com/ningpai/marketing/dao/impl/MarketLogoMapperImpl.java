package com.ningpai.marketing.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.marketing.bean.MarketLogo;
import com.ningpai.marketing.dao.MarketLogoMapper;

/**
 * 促销LOGO级联接口实现类
 * 
 * @author qiyuanyuan
 *
 */
@Repository("MarketLogoMapper")
public class MarketLogoMapperImpl extends BasicSqlSupport implements
        MarketLogoMapper {

    /*
     * 根据主键删除 参数:主键 返回:删除个数
     * 
     * @ibatorgenerated 2015-05-21 13:15:53
     * 
     * @see
     * com.ningpai.marketing.dao.MarketLogoMapper#deleteByPrimaryKey(java.lang
     * .Long)
     */
    @Override
    public int deleteByPrimaryKey(Long marketLogoId) {
        return this.delete(
                "com.ningpai.dao.MarketLogoMapper.deleteByPrimaryKey",
                marketLogoId);
    }

    /*
     * 插入，空属性也会插入 参数:pojo对象 返回:删除个数
     * 
     * @ibatorgenerated 2015-05-21 13:15:53
     * 
     * @see
     * com.ningpai.marketing.dao.MarketLogoMapper#insert(com.ningpai.marketing
     * .bean.MarketLogo)
     */
    @Override
    public int insert(MarketLogo marketLogo) {
        return this.insert("com.ningpai.dao.MarketLogoMapper.insert",
                marketLogo);
    }

    /*
     * 插入，空属性不会插入 参数:pojo对象 返回:删除个数
     * 
     * @ibatorgenerated 2015-05-21 13:15:53
     * 
     * @see
     * com.ningpai.marketing.dao.MarketLogoMapper#insertSelective(com.ningpai
     * .marketing.bean.MarketLogo)
     */
    @Override
    public int insertSelective(MarketLogo marketLogo) {
        return this.insert("com.ningpai.dao.MarketLogoMapper.insertSelective",
                marketLogo);
    }

    /*
     * 根据主键查询 参数:查询条件,主键值 返回:对象
     * 
     * @ibatorgenerated 2015-05-21 13:15:53
     * 
     * @see
     * com.ningpai.marketing.dao.MarketLogoMapper#selectByPrimaryKey(java.lang
     * .Long)
     */
    @Override
    public MarketLogoMapper selectByPrimaryKey(Long marketLogoId) {
        return this.selectOne(
                "com.ningpai.dao.MarketLogoMapper.selectByPrimaryKey",
                marketLogoId);
    }

    /*
     * 根据主键修改，空值条件不会修改成null 参数:1.要修改成的值 返回:成功修改个数
     * 
     * @ibatorgenerated 2015-05-21 13:15:53
     * 
     * @see
     * com.ningpai.marketing.dao.MarketLogoMapper#updateByPrimaryKeySelective
     * (com.ningpai.marketing.bean.MarketLogo)
     */
    @Override
    public int updateByPrimaryKeySelective(MarketLogo marketLogo) {
        return this.update(
                "com.ningpai.dao.MarketLogoMapper.updateByPrimaryKeySelective",
                marketLogo);
    }

    /*
     * 根据主键修改，空值条件会修改成null 参数:1.要修改成的值 返回:成功修改个数
     * 
     * @ibatorgenerated 2015-05-21 13:15:53
     * 
     * @see
     * com.ningpai.marketing.dao.MarketLogoMapper#updateByPrimaryKey(com.ningpai
     * .marketing.bean.MarketLogo)
     */
    @Override
    public int updateByPrimaryKey(MarketLogo marketLogo) {
        return this.update(
                "com.ningpai.dao.MarketLogoMapper.updateByPrimaryKey",
                marketLogo);
    }

    /*
     * 根据促销ID查询促销ＬＯＧＯ
     * 
     * @param marketingId　　　　　　　　促销id{@link java.lang.Long} 　　
     * 
     * @return list
     * 
     * @see
     * com.ningpai.marketing.dao.MarketLogoMapper#queryLogoByMarketingId(java
     * .lang.Long)
     */
    @Override
    public List<MarketLogo> queryLogoByMarketingId(Long marketingId) {
        return this.selectList(
                "com.ningpai.dao.MarketLogoMapper.queryLogoByMarketingId",
                marketingId);
    }

    /*
     * 根据促销ID删除促销Logo
     * 
     * @param marketingId 促销id{@link java.lang.Long} 　　
     * 
     * @return int
     * 
     * @see
     * com.ningpai.marketing.dao.MarketLogoMapper#delLogoByMarketingId(java.
     * lang.Long)
     */
    @Override
    public int delLogoByMarketingId(Long marketingId) {
        return this.update("com.ningpai.dao.MarketLogoMapper.delMarketLogo",
                marketingId);
    }

}
