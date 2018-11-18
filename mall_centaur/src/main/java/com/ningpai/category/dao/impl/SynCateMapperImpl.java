package com.ningpai.category.dao.impl;

import org.springframework.stereotype.Repository;

import com.ningpai.category.bean.GoodsCategory;
import com.ningpai.category.dao.SynCateMapper;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * E店宝同步分类DAO接口实现类
 * 
 * @author qiyuanyuan
 *
 */
@Repository("SynCateMapper")
public class SynCateMapperImpl extends BasicSqlSupport implements SynCateMapper {

    /**
     * 判断分类是否存在
     * 
     * @see com.ningpai.category.dao.SynCateMapper#checkCate(java.lang.Long)
     */
    public int checkCate(Long catId) {
        return this.selectOne("com.bbw.web.dao.centaur.GoodsCategoryMapper.checkCate", catId);
    }

    /**
     * 插入分类信息
     * 
     * @see com.ningpai.category.dao.SynCateMapper#insertGoodsCate(com.ningpai.category
     *      .bean.GoodsCategory)
     */
    public int insertGoodsCate(GoodsCategory category) {
        return this.insert("com.bbw.web.dao.centaur.GoodsCategoryMapper.insertSelective", category);
    }

    /**
     * 修改分类信息
     * 
     * @see com.ningpai.category.dao.SynCateMapper#updateGoodsCate(com.ningpai.category
     *      .bean.GoodsCategory)
     */
    public int updateGoodsCate(GoodsCategory category) {
        return this.update("com.bbw.web.dao.centaur.GoodsCategoryMapper.updateByPrimaryKeySelective", category);
    }

}
