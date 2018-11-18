package com.ningpai.marketing.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.marketing.bean.PromotionLogo;
import com.ningpai.marketing.dao.PromotionLogoMapper;

/**
 * LOGO
 * @author ggn
 *
 */
@Repository("PromotionLogoMapper")
public class PromotionLogoMapperImpl extends BasicSqlSupport implements
        PromotionLogoMapper {

    /**
     * 根据主键删除 参数:主键 返回:删除个数
     * 
     * @ibatorgenerated 2015-05-21 13:15:54
     * 
     * @see
     * com.ningpai.marketing.dao.PromotionLogoMapper#deleteByPrimaryKey(java
     * .lang.Long)
     */
    @Override
    public int deleteByPrimaryKey(Long promotionLogoId) {
        return this.delete(
                "com.ningpai.dao.PromotionLogoMapper.deleteByPrimaryKey",
                promotionLogoId);
    }

    /**
     * 插入，空属性也会插入 参数:pojo对象 返回:删除个数
     * 
     * @ibatorgenerated 2015-05-21 13:15:54
     * 
     * @see
     * com.ningpai.marketing.dao.PromotionLogoMapper#insert(com.ningpai.marketing
     * .dao.PromotionLogoMapper)
     */
    @Override
    public int insert(PromotionLogo promotionLogo) {
        return this.insert("com.ningpai.dao.PromotionLogoMapper.insert",
                promotionLogo);
    }

    /**
     * 插入，空属性不会插入 参数:pojo对象 返回:删除个数
     * 
     * @ibatorgenerated 2015-05-21 13:15:54
     * 
     * @see
     * com.ningpai.marketing.dao.PromotionLogoMapper#insertSelective(com.ningpai
     * .marketing.bean.PromotionLogo)
     */
    @Override
    public int insertSelective(PromotionLogo promotionLogo) {
        return this.insert(
                "com.ningpai.dao.PromotionLogoMapper.insertSelective",
                promotionLogo);
    }

    /**
     * 根据主键查询 参数:查询条件,主键值 返回:对象
     * 
     * @ibatorgenerated 2015-05-21 13:15:54
     * 
     * @see
     * com.ningpai.marketing.dao.PromotionLogoMapper#selectByPrimaryKey(java
     * .lang.Long)
     */
    @Override
    public PromotionLogo selectByPrimaryKey(Long promotionLogoId) {
        return this.selectOne(
                "com.ningpai.dao.PromotionLogoMapper.selectByPrimaryKey",
                promotionLogoId);
    }

    /**
     * 根据主键修改，空值条件不会修改成null 参数:1.要修改成的值 返回:成功修改个数
     * 
     * @ibatorgenerated 2015-05-21 13:15:54
     * 
     * @see
     * com.ningpai.marketing.dao.PromotionLogoMapper#updateByPrimaryKeySelective
     * (com.ningpai.marketing.bean.PromotionLogo)
     */
    @Override
    public int updateByPrimaryKeySelective(PromotionLogo promotionLogo) {
        return this
                .update("com.ningpai.dao.PromotionLogoMapper.updateByPrimaryKeySelective",
                        promotionLogo);
    }

    /**
     * 根据主键修改，空值条件会修改成null 参数:1.要修改成的值 返回:成功修改个数
     * 
     * @ibatorgenerated 2015-05-21 13:15:54
     * 
     * @see
     * com.ningpai.marketing.dao.PromotionLogoMapper#updateByPrimaryKey(com.
     * ningpai.marketing.bean.PromotionLogo)
     */
    @Override
    public int updateByPrimaryKey(PromotionLogo promotionLogo) {
        return this.update(
                "com.ningpai.dao.PromotionLogoMapper.updateByPrimaryKey",
                promotionLogo);
    }

    /**
     * 查询所有促销LOGO
     * 
     * @return List
     * 
     * @see
     * com.ningpai.marketing.dao.PromotionLogoMapper#querypromotionlogoList()
     */
    @Override
    public List<Object> queryPromotionLogoList(Map<String, Object> paramMap) {
        return this.selectList(
                "com.ningpai.dao.PromotionLogoMapper.queryallpromotionlogo",
                paramMap);
    }

    /**
     * 
     * 查询促销LOGO数目
     * 
     * @return int
     * 
     * @see
     * com.ningpai.marketing.dao.PromotionLogoMapper#queryPromotionLogoCount()
     */
    @Override
    public int queryPromotionLogoCount(Map<String, Object> paramMap) {
        return this
                .selectOne(
                        "com.ningpai.dao.PromotionLogoMapper.queryallpromotionlogoCount",
                        paramMap);
    }

    /**
     * 根据促销logoid删除促销LOGO
     * 
     * @param list 促销LOGO Id
     * 
     * @return int
     * 
     * @see
     * com.ningpai.marketing.dao.PromotionLogoMapper#delAllPromotionLogo(java
     * .util.List)
     */
    @Override
    public int delAllPromotionLogo(List<Long> list) {
        return this
                .update("com.ningpai.dao.PromotionLogoMapper.delAllPromotionLogo",
                        list);
    }

    /**
     * 根据促销Logo名称查询行数
     * 
     * @param promotionLogoName 促销logo名称
     * 
     * @return int
     * 
     * @see
     * com.ningpai.marketing.dao.PromotionLogoMapper#checkLogoName(java.lang
     * .String)
     */
    @Override
    public int checkLogoName(String promotionLogoName) {
        return this.selectOne(
                "com.ningpai.dao.PromotionLogoMapper.checklogoname",
                promotionLogoName);
    }

    /**
     * 查询所有促销LOGO
     * 
     * @return
     * 
     * @see com.ningpai.marketing.dao.PromotionLogoMapper#queryAllLogoList()
     */
    @Override
    public List<PromotionLogo> queryAllLogoList() {
        return this
                .selectList("com.ningpai.dao.PromotionLogoMapper.queryalllogolist");
    }

    /**
     * 查询刚刚插入的LOGO的ID
     * 
     * @return Long
     * 
     * @see com.ningpai.marketing.dao.PromotionLogoMapper#selectLastId()
     */
    @Override
    public Long selectLastId() {
        return this
                .selectOne("com.ningpai.dao.PromotionLogoMapper.selectLastId");
    }

}
