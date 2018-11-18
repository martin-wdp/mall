package com.ningpai.brand.dao.impl;

import org.springframework.stereotype.Repository;

import com.ningpai.brand.bean.GoodsBrand;
import com.ningpai.brand.dao.SynBrandMapper;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * E店宝同步品牌DAO层接口实现类
 * 
 * @author qiyuanyuan
 *
 */
@Repository("SynBrandMapper")
public class SynBrandMapperImpl extends BasicSqlSupport implements SynBrandMapper {

    /**
     * 检测品牌是否存在
     * 
     * @see com.ningpai.brand.dao.SynBrandMapper#checkBrand(java.lang.Long)
     */
    public int checkBrand(Long brandId) {
        return this.selectOne("com.bbw.web.dao.centaur.GoodsBrandMapper.checkBrand", brandId);
    }

    /**
     * 
     * 插入品牌
     * 
     * @see com.ningpai.brand.dao.SynBrandMapper#insertBrand(com.ningpai.brand.bean
     *      .GoodsBrand)
     */
    public int insertBrand(GoodsBrand gb) {
        return this.insert("com.bbw.web.dao.centaur.GoodsBrandMapper.insertSelective", gb);
    }

    /**
     * 修改品牌
     * 
     * @see com.ningpai.brand.dao.SynBrandMapper#updateBrand(com.ningpai.brand.bean
     *      .GoodsBrand)
     */
    public int updateBrand(GoodsBrand gb) {
        return this.update("com.bbw.web.dao.centaur.GoodsBrandMapper.updateByPrimaryKeySelective", gb);
    }

}
