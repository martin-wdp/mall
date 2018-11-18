package com.ningpai.api.dao.impl;

import com.ningpai.api.bean.OGoodsCategory;
import com.ningpai.api.dao.IGoodsCategoryMapper;
import com.ningpai.manager.base.BasicSqlSupport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 *
 * @author lih
 * @version 2.0
 * @since 15/9/6
 */
@Repository("openGoodsCategoryMapper")
public class GoodsCategoryMapperImpl extends BasicSqlSupport implements IGoodsCategoryMapper{
    /**
     * 商品分类列表
     *
     * @param map 分页参数
     * @return 商品分类列表
     */
    @Override
    public List<OGoodsCategory> list(Map<String, Object> map) {
        return this.selectList("com.ningpai.goodscategory.dao.OGoodsCategoryMapper.list",map);
    }

    /**
     * 查询商品分类总数
     *
     * @return 总数
     */
    @Override
    public int count() {
        return this.selectOne("com.ningpai.goodscategory.dao.OGoodsCategoryMapper.count");
    }
}
