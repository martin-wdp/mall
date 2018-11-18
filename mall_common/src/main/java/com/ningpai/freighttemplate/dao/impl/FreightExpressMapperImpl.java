package com.ningpai.freighttemplate.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ningpai.freighttemplate.bean.FreightExpress;
import com.ningpai.freighttemplate.bean.SysLogisticsCompany;
import com.ningpai.freighttemplate.dao.FreightExpressMapper;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * 运送方式实现类
 * 
 * @author ggn
 *
 */
@Repository("FreightExpressMapper")
public class FreightExpressMapperImpl extends BasicSqlSupport implements
        FreightExpressMapper {

    /*
     * 查询运送模板设置默认
     * @see
     * com.ningpai.freighttemplate.dao.FreightExpressMapper#selectTemplateExpress
     * (java.lang.Long)
     */
    @Override
    public List<FreightExpress> selectTemplateExpress(Long freightTemplateId) {

        return this
                .selectList(
                        "com.qpmall.web.dao.FreightExpressMapper.selectTemplateExpress",
                        freightTemplateId);
    }

    /**
     * 根据运费模板查询运费详细信息
     * @param freightTemplateIds
     * @return
     */
    @Override
    public List<FreightExpress> selectByFreightTemplateIds(
            List<Long> freightTemplateIds) {

        return this
                .selectList(
                        "com.qpmall.web.dao.FreightExpressMapper.selectByFreightTemplateIds",
                        freightTemplateIds);
    }

    /*
     * 插入新的运费模板默认
     * @see
     * com.ningpai.freighttemplate.dao.FreightExpressMapper#insertNewFreightExpress
     * (com.ningpai.freighttemplate.bean.FreightExpress)
     */
    @Override
    public int insertNewFreightExpress(FreightExpress fe) {

        return this
                .insert("com.qpmall.web.dao.FreightExpressMapper.insertNewFreightExpress",
                        fe);
    }

    /*
     * 查询刚刚插入的默认设置Id
     * @see
     * com.ningpai.freighttemplate.dao.FreightExpressMapper#selectLastDistributionId
     * ()
     */
    @Override
    public Long selectLastDistributionId() {

        return this
                .selectOne("com.qpmall.web.dao.FreightExpressMapper.selectLastDistributionId");
    }

    /*
     * 删除默认设置
     * @see
     * com.ningpai.freighttemplate.dao.FreightExpressMapper#deleteTemplateExpress
     * (java.lang.Long)
     */
    @Override
    public int deleteTemplateExpress(Long distributionId) {

        return this
                .update("com.qpmall.web.dao.FreightExpressMapper.deleteTemplateExpress",
                        distributionId);
    }

    /*
     * 获取运费运送方式
     * @see
     * com.ningpai.freighttemplate.dao.FreightExpressMapper#queryDisIdByCidandTid
     * (com.ningpai.freighttemplate.bean.FreightExpress)
     */
    @Override
    public FreightExpress queryDisIdByCidandTid(FreightExpress freightExpress) {

        return this
                .selectOne(
                        "com.qpmall.web.dao.FreightExpressMapper.queryDisIdByCidandTid",
                        freightExpress);
    }

    /*
     * 更新
     * @see com.ningpai.freighttemplate.dao.FreightExpressMapper#
     * updateByPrimaryKeySelective
     * (com.ningpai.freighttemplate.bean.FreightExpress)
     */
    @Override
    public int updateByPrimaryKeySelective(FreightExpress freightExpress) {

        return this
                .update("com.qpmall.web.dao.FreightExpressMapper.updateByPrimaryKeySelective",
                        freightExpress);
    }

    /*
     * 根据freightTemplateId删除
     * @see
     * com.ningpai.freighttemplate.dao.FreightExpressMapper#deleteFreExpByTid
     * (java.lang.Long)
     */
    @Override
    public int deleteFreExpByTid(Long freightTemplateId) {

        return this.update(
                "com.qpmall.web.dao.FreightExpressMapper.deleteFreExpByTid",
                freightTemplateId);
    }

    /*
     * 更具distributionId获取运费运送
     * @see
     * com.ningpai.freighttemplate.dao.FreightExpressMapper#selectFreightExpress
     * (java.lang.Long)
     */
    @Override
    public FreightExpress selectFreightExpressByDistributionId(
            Long distributionId) {

        FreightExpress fe = this
                .selectOne(
                        "com.qpmall.web.dao.FreightExpressMapper.selectFreightExpressByDistributionId",
                        distributionId);
        if (fe != null) {
            SysLogisticsCompany sc = this
                    .selectOne(
                            "com.qpmall.web.dao.SysLogisticsCompanyMapper.selectCompanyById",
                            fe.getLogComId());
            fe.setSysLogisticsCompany(sc);
        }
        return fe;
    }

}
