package com.ningpai.third.seller.mapper.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.third.seller.bean.ApplyBrand;
import com.ningpai.third.seller.mapper.ApplyBrandMapper;

/**
 * 自定义品牌的接口实现类
 */
@Repository("ApplyBrandMapper")
public class ApplyBrandMapperImpl extends BasicSqlSupport implements ApplyBrandMapper {

    /**
     * 查询申请的自定义品牌
     * @param thirdId 商家ID
     * @return
     */
    @Override
    public List<ApplyBrand> selectApplyBrand(Long thirdId) {
        return this.selectList("com.ningpai.third.seller.mapper.ApplyBrandMapper.selectApplyBrand", thirdId);
    }

    /**
     * 批量删除自定义品牌
     * @param map
     * @return
     */
    @Override
    public int delApplyBrand(Map<String, Object> map) {
        return this.update("com.ningpai.third.seller.mapper.ApplyBrandMapper.delApplyBrand", map);
    }

    /**
     * 新增自定义品牌
     * @param applyBrand
     * @return
     */
    @Override
    public int addApplyBrand(ApplyBrand applyBrand) {
        return this.insert("com.ningpai.third.seller.mapper.ApplyBrandMapper.addApplyBrand", applyBrand);
    }

    /**
     ＊ 获取新保存的自定义品牌的ID
     * @return
     */
    @Override
    public Long selectLastId() {
        return this.selectOne("com.ningpai.third.seller.mapper.ApplyBrandMapper.selectLastId");
    }

    /**
     * 获取自定义品牌的数量
     * @param pmap
     * @return
     */
    @Override
    public int queryApplyBrandCount(Map<String, Object> pmap) {
        return this.selectOne("com.ningpai.third.seller.mapper.ApplyBrandMapper.queryApplyBrandCount", pmap);
    }

    /**
     * 根据条件获取自定义品牌的集合
     * @param pmap
     * @return
     */
    @Override
    public List<Object> queryApplyBrand(Map<String, Object> pmap) {
        return this.selectList("com.ningpai.third.seller.mapper.ApplyBrandMapper.queryApplyBrand", pmap);
    }

    /**
     * 根据条件删除一条自定义品牌
     * @param map
     * @return
     */
    @Override
    public int delApplyBrands(Map<String, Object> map) {
        return this.update("com.ningpai.third.seller.mapper.ApplyBrandMapper.delApplyBrands", map);
    }

    /**
     * 根据条件更新一条自定义品牌
     * @param map
     * @return
     */
    @Override
    public int updateThirdApplyBrand(Map<String, Object> map) {
        return this.update("com.ningpai.third.seller.mapper.ApplyBrandMapper.updateThirdApplyBrand", map);
    }
}
