package com.ningpai.freighttemplate.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.freighttemplate.bean.FreightTemplate;

/**
 * 运费模板 2014-12-16
 * 
 * @author ggn
 *
 */
public interface FreightTemplateMapper {

    /**
     * 查询运费模板列表
     * 
     * @param map
     * @return List
     */
    List<FreightTemplate> searchAllTemplateList(Map<String, Object> map);

    /**
     * 根据id集合查询运费模板
     * 
     * @param freightIds
     * @return List
     */
    List<FreightTemplate> selectByFreightIds(List<Long> freightIds);

    /**
     * 更具模板ID 查询模板信息
     * 
     * @param freightTemplateId
     * @return FreightTemplate
     */
    FreightTemplate selectFreightTemplateById(Long freightTemplateId);

    /**
     * 插入模板
     * 
     * @param ft
     * @return int
     */
    int insertNewFreightTemplate(FreightTemplate ft);

    /**
     * 查询刚刚插入的模板ID
     * 
     * @return Long
     */
    Long selectFreightTemplateLastId();

    /**
     * 删除运费模板
     * 
     * @param map
     * @return int
     */
    int deleteFreightTemplate(Map<String, Object> map);

    /**
     * 设置其他模板为非默认
     * 
     * @param freightTemplate
     * @return int
     */
    int noDefaultFreightTemplate(FreightTemplate freightTemplate);

    /**
     * 设置此模板为默认
     * 
     * @param freightTemplate
     * @return int
     */
    int defaultFreightTemplate(FreightTemplate freightTemplate);

    /**
     * 修改运费模板
     * 
     * @param freightTemplate
     * @return int
     */
    int updateByPrimaryKeySelective(FreightTemplate freightTemplate);

    /**
     * 查询运费模板
     * 
     * @param paramMap
     * @return FreightTemplate
     */
    FreightTemplate selectFreightTemplateSubOrder(Map<String, Object> paramMap);

    /**
     * 查询运费模板名称和id
     * 
     * @author houyichang 2015/7/1
     * @return List FreightTemplate
     * */
    List<FreightTemplate> queryTemplate();
}
