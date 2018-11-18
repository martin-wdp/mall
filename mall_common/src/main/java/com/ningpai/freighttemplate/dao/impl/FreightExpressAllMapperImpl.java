package com.ningpai.freighttemplate.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ningpai.freighttemplate.bean.FreightExpressAll;
import com.ningpai.freighttemplate.dao.FreightExpressAllMapper;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * 运费模板其他地区设置
 * 
 * @author ggn
 *
 */
@Repository("FreightExpressAllMapper")
public class FreightExpressAllMapperImpl extends BasicSqlSupport implements
        FreightExpressAllMapper {

    /*
     * 插入运费模板其他地区设置
     * @see com.ningpai.freighttemplate.dao.FreightExpressAllMapper#
     * insertFreightExpressAll(java.util.List)
     */
    @Override
    public int insertFreightExpressAll(List<FreightExpressAll> list) {

        return this
                .insert("com.qpmall.web.dao.FreightExpressAllMapper.insertFreightExpressAll",
                        list);
    }

    /*
     * 删除其他设置
     * @see com.ningpai.freighttemplate.dao.FreightExpressAllMapper#
     * deleteTemplateExpressAll(java.lang.Long)
     */
    @Override
    public int deleteTemplateExpressAll(Long distributionId) {

        return this
                .update("com.qpmall.web.dao.FreightExpressAllMapper.deleteTemplateExpressAll",
                        distributionId);
    }

    /*
     * 插入其他设置
     * @see
     * com.ningpai.freighttemplate.dao.FreightExpressAllMapper#insertSelective
     * (com.ningpai.freighttemplate.bean.FreightExpressAll)
     */
    @Override
    public int insertSelective(FreightExpressAll freightExpressAll) {

        return this.insert(
                "com.qpmall.web.dao.FreightExpressAllMapper.insertSelective",
                freightExpressAll);
    }

}
