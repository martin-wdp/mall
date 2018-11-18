package com.ningpai.freighttemplate.dao;

import java.util.List;

import com.ningpai.freighttemplate.bean.FreightExpress;

/**
 * 运送方式
 * 
 * @author ggn
 *
 */
public interface FreightExpressMapper {

    /**
     * 查询运送模板设置默认
     * 
     * @param freightTemplateId
     * @return FreightExpress
     */
    List<FreightExpress> selectTemplateExpress(Long freightTemplateId);

    /**
     * 根据运费模板查询运费详细信息
     * 
     * @param freightTemplateIds
     * @return List
     */
    List<FreightExpress> selectByFreightTemplateIds(
            List<Long> freightTemplateIds);

    /**
     * 插入新的运费模板默认
     * 
     * @param fe
     * @return int
     */
    int insertNewFreightExpress(FreightExpress fe);

    /**
     * 查询刚刚插入的默认设置Id
     * 
     * @return Long
     */
    Long selectLastDistributionId();

    /**
     * 删除默认设置
     * 
     * @param distributionId
     * @return int
     */
    int deleteTemplateExpress(Long distributionId);

    /**
     * 获取运费运送方式
     * @param
     * @param
     * @return
     */
    FreightExpress queryDisIdByCidandTid(FreightExpress freightExpress);

    /**
     * 更新
     * 
     * @param freightExpress
     * @return int
     */
    int updateByPrimaryKeySelective(FreightExpress freightExpress);

    /**
     * 根据freightTemplateId删除
     * 
     * @param freightTemplateId
     * @return int
     */
    int deleteFreExpByTid(Long freightTemplateId);

    /**
     * 更具distributionId获取运费运送
     * @param distributionId
     * @return FreightExpress
     */
    FreightExpress selectFreightExpressByDistributionId(Long distributionId);

}
