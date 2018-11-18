package com.ningpai.freighttemplate.dao;

import java.util.List;

import com.ningpai.freighttemplate.bean.FreightExpressAll;

/**
 * 运费模板掐地区设置 2014-12-17
 * 
 * @author ggn
 *
 */
public interface FreightExpressAllMapper {

    /**
     * 插入运费模板其他地区设置
     * 
     * @param falist
     * @return int
     */
    int insertFreightExpressAll(List<FreightExpressAll> falist);

    /**
     * 删除其他设置
     * 
     * @param distributionId
     * @return int
     */
    int deleteTemplateExpressAll(Long distributionId);

    /**
     * 插入其他设置
     * 
     * @param freightExpressAll
     * @return int
     */
    int insertSelective(FreightExpressAll freightExpressAll);
}
