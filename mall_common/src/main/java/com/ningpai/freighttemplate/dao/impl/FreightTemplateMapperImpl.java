package com.ningpai.freighttemplate.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.freighttemplate.bean.FreightTemplate;
import com.ningpai.freighttemplate.dao.FreightTemplateMapper;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * 运费模板 实现类 2014-12-16
 * 
 * @author ggn
 *
 */
@Repository("FreightTemplateMapper")
public class FreightTemplateMapperImpl extends BasicSqlSupport implements
        FreightTemplateMapper {

    /*
     * 查询运费模板列表
     * @see
     * com.ningpai.freighttemplate.dao.FreightTemplateMapper#searchAllTemplateList
     * (java.util.Map)
     */
    @Override
    public List<FreightTemplate> searchAllTemplateList(Map<String, Object> map) {

        return this
                .selectList(
                        "com.qpmall.web.dao.FreightTemplateMapper.searchAllTemplateList",
                        map);
    }

    /**
     * 根据id集合查询运费模板
     * @param freightIds
     * @return
     */
    @Override
    public List<FreightTemplate> selectByFreightIds(List<Long> freightIds) {

        return this.selectList(
                "com.qpmall.web.dao.FreightTemplateMapper.selectByFreightIds",
                freightIds);
    }

    /*
     * 更具模板ID 查询模板信息
     * @see com.ningpai.freighttemplate.dao.FreightTemplateMapper#
     * selectFreightTemplateById(java.lang.Long)
     */
    @Override
    public FreightTemplate selectFreightTemplateById(Long freightTemplateId) {

        return this
                .selectOne(
                        "com.qpmall.web.dao.FreightTemplateMapper.selectFreightTemplateById",
                        freightTemplateId);
    }

    /*
     * 插入模板
     * @see com.ningpai.freighttemplate.dao.FreightTemplateMapper#
     * insertNewFreightTemplate
     * (com.ningpai.freighttemplate.bean.FreightTemplate)
     */
    @Override
    public int insertNewFreightTemplate(FreightTemplate ft) {

        return this
                .insert("com.qpmall.web.dao.FreightTemplateMapper.insertNewFreightTemplate",
                        ft);
    }

    /*
     * 查询刚刚插入的模板ID
     * @see com.ningpai.freighttemplate.dao.FreightTemplateMapper#
     * selectFreightTemplateLastId()
     */
    @Override
    public Long selectFreightTemplateLastId() {

        return this
                .selectOne("com.qpmall.web.dao.FreightTemplateMapper.selectFreightTemplateLastId");
    }

    /*
     * 删除运费模板
     * @see
     * com.ningpai.freighttemplate.dao.FreightTemplateMapper#deleteFreightTemplate
     * (java.lang.Long)
     */
    @Override
    public int deleteFreightTemplate(Map<String, Object> map) {

        return this
                .update("com.qpmall.web.dao.FreightTemplateMapper.deleteFreightTemplate",
                        map);
    }

    /*
     * 设置其他模板为非默认
     * @see com.ningpai.freighttemplate.dao.FreightTemplateMapper#
     * noDefaultFreightTemplate
     * (com.ningpai.freighttemplate.bean.FreightTemplate)
     */
    @Override
    public int noDefaultFreightTemplate(FreightTemplate freightTemplate) {

        return this
                .update("com.qpmall.web.dao.FreightTemplateMapper.noDefaultFreightTemplate",
                        freightTemplate);
    }

    /*
     * 设置此模板为默认
     * @see
     * com.ningpai.freighttemplate.dao.FreightTemplateMapper#defaultFreightTemplate
     * (com.ningpai.freighttemplate.bean.FreightTemplate)
     */
    @Override
    public int defaultFreightTemplate(FreightTemplate freightTemplate) {

        return this
                .update("com.qpmall.web.dao.FreightTemplateMapper.defaultFreightTemplate",
                        freightTemplate);
    }

    /*
     * 修改运费模板
     * @see com.ningpai.freighttemplate.dao.FreightTemplateMapper#
     * updateByPrimaryKeySelective
     * (com.ningpai.freighttemplate.bean.FreightTemplate)
     */
    @Override
    public int updateByPrimaryKeySelective(FreightTemplate freightTemplate) {

        return this
                .update("com.qpmall.web.dao.FreightTemplateMapper.updateByPrimaryKeySelective",
                        freightTemplate);
    }

    /*
     * 查询运费模板
     * @see com.ningpai.freighttemplate.dao.FreightTemplateMapper#
     * selectFreightTemplateSubOrder(java.util.Map)
     */
    @Override
    public FreightTemplate selectFreightTemplateSubOrder(
            Map<String, Object> paramMap) {

        return this
                .selectOne(
                        "com.qpmall.web.dao.FreightTemplateMapper.selectFreightTemplateSubOrder",
                        paramMap);
    }

    /**
     * 查询运费模板名称和id
     *
     * @return List FreightTemplate
     * @author houyichang 2015/7/1
     */
    @Override
    public List<FreightTemplate> queryTemplate() {
        return this
                .selectList("com.qpmall.web.dao.FreightTemplateMapper.queryTemplate");
    }

}
