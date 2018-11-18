package com.ningpai.thirdaudit.mapper.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.thirdaudit.mapper.ApplyBrandMapper;
/**
 * 应用品牌接口实现类
 *
 * */
@Repository("applybrandmapper")
public class ApplyBrandMapperImpl extends BasicSqlSupport implements
        ApplyBrandMapper {
    /**
     * 查询未审核列表
     *
     * @param pmap
     * @return
     */
    @Override
    public List<Object> queryApplyBrand(Map<String, Object> pmap) {

        return this.selectList(
                "com.ningpai.web.dao.ApplyBrandMapper.queryApplyBrand", pmap);
    }
    /**
     * 修改审核状态
     *
     * @param pmap
     * @return
     */
    @Override
    public int updateApplyBrand(Map<String, Object> pmap) {

        return this.update(
                "com.ningpai.web.dao.ApplyBrandMapper.updateApplyBrand", pmap);
    }
    /**
     * 查询未审核个数
     *
     * @param pmap
     * @return
     */
    @Override
    public int queryApplyBrandCount(Map<String, Object> pmap) {

        return this.selectOne(
                "com.ningpai.web.dao.ApplyBrandMapper.queryApplyBrandCount",
                pmap);
    }

}
