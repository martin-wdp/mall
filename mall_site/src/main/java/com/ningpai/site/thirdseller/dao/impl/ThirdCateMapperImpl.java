package com.ningpai.site.thirdseller.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.site.thirdseller.dao.ThirdCateMapper;
import com.ningpai.site.thirdseller.vo.ThirdCateVo;

/**
 * 第三方店铺分类DAO实现
 * @author qiyuanyuan
 *
 */
@Repository("ThirdCateMapper")
public class ThirdCateMapperImpl extends BasicSqlSupport implements ThirdCateMapper{

    @Override
    public Long selectByCustomerId(Long customerId) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("customerId",customerId);
        return this.selectOne("com.ningpai.third.site.goods.dao.ThirdCateMapper.selectByCustomerId", map);
    }

    @Override
    public String findStoreFlag(Long storeId) {
        return this.selectOne("com.ningpai.third.site.goods.dao.ThirdCateMapper.findStoreFlag",storeId);
    }

    /*
     * 
     * @see com.ningpai.site.thirdseller.dao.ThirdCateMapper#queryAllThirdCate(java.util.Map)
     */
    @Override
    public List<ThirdCateVo> queryAllThirdCate(Map<String, Object> map) {
        
        return this.selectList("com.ningpai.third.site.goods.dao.ThirdCateMapper.queryAllThirdCate", map);
    }

    /*
     * 
     * @see com.ningpai.site.thirdseller.dao.ThirdCateMapper#queryAllCateForList(java.lang.Long)
     */
    @Override
    public List<ThirdCateVo> queryAllCateForList(Long thirdId) {
        
        return this.selectList("com.ningpai.third.site.goods.dao.ThirdCateMapper.queryAllParentCate", thirdId);
    }

    /*
     * 
     * @see com.ningpai.site.thirdseller.dao.ThirdCateMapper#selectByPrimaryKey(java.lang.Long)
     */
    @Override
    public ThirdCateVo selectByPrimaryKey(Long catId) {
        
        return this.selectOne("com.ningpai.third.site.goods.dao.ThirdCateMapper.selectByPrimaryKey", catId);
    }

    /*
     * 
     * @see com.ningpai.site.thirdseller.dao.ThirdCateMapper#queryThirdCateByParentCateId(java.util.Map)
     */
    @Override
    public List<ThirdCateVo> queryThirdCateByParentCateId(Map<String, Object> map) {
        
        return this.selectList("com.ningpai.third.site.goods.dao.ThirdCateMapper.queryThirdCateByParentId", map);
    }

}
