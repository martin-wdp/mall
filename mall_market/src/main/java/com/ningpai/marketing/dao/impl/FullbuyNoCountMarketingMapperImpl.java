package com.ningpai.marketing.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.marketing.bean.FullbuyNoCountMarketing;
import com.ningpai.marketing.dao.FullbuyNoCountMarketingMapper;

/**
 * 满件数多少钱
 * 
 * @author zhouxu
 *
 */
@Repository("FullbuyNoCountMarketingMapper")
public class FullbuyNoCountMarketingMapperImpl extends BasicSqlSupport
        implements FullbuyNoCountMarketingMapper {

    /*
     * 插入满件数多少钱数据
     * 
     * @param fcountno
     * 
     * @return
     * 
     * @see
     * com.ningpai.marketing.dao.FullbuyNoCountMarketingMapper#insertSelective
     * (com.ningpai.marketing.bean.FullbuyNoCountMarketing)
     */
    @Override
    public int insertSelective(FullbuyNoCountMarketing fcountno) {
        return this
                .insert("com.ningpai.marketing.dao.FullbuyNoCountMarketingMapper.insert",
                        fcountno);
    }

    /*
     * 查询数据
     * 
     * @param marketingId
     * 
     * @return
     * 
     * @see
     * com.ningpai.marketing.dao.FullbuyNoCountMarketingMapper#selectByMarketId
     * (java.lang.Long)
     */
    @Override
    public FullbuyNoCountMarketing selectByMarketId(Long marketingId) {
        return this
                .selectOne(
                        "com.ningpai.marketing.dao.FullbuyNoCountMarketingMapper.selectByMarketId",
                        marketingId);
    }

    /*
     * 插入计数
     * 
     * @param marketingId
     * 
     * @return
     * 
     * @see com.ningpai.marketing.dao.FullbuyNoCountMarketingMapper#
     * insertCountConditionByMarketing(java.util.Map)
     */
    @Override
    public int insertCountConditionByMarketing(Map<String, Object> param) {
        return this
                .update("com.ningpai.marketing.dao.FullbuyNoCountMarketingMapper.insertCountConditionByMarketing",
                        param);
    }

    /*
     * 更新满购件数 金额计数都为空
     * 
     * @see com.ningpai.marketing.dao.FullbuyNoCountMarketingMapper#update()
     */
    @Override
    public int update() {
        return this
                .update("com.ningpai.marketing.dao.FullbuyNoCountMarketingMapper.update");
    }

    /*
     * 修改满购件数 金额
     * 
     * @param fcountno
     * 
     * @return
     * 
     * @see
     * com.ningpai.marketing.dao.FullbuyNoCountMarketingMapper#modifySelective
     * (com.ningpai.marketing.bean.FullbuyNoCountMarketing)
     */
    @Override
    public int modifySelective(FullbuyNoCountMarketing fcountno) {
        return this
                .update("com.ningpai.marketing.dao.FullbuyNoCountMarketingMapper.modifySelective",
                        fcountno);
    }

    /*
     * 查询数据
     * 
     * @param marketingId
     * 
     * @return
     * 
     * @see com.ningpai.marketing.dao.FullbuyNoCountMarketingMapper#
     * selectCountsByMarketId(java.lang.Long)
     */
    @Override
    public List<FullbuyNoCountMarketing> selectCountsByMarketId(Long marketingId) {
        return this
                .selectList(
                        "com.ningpai.marketing.dao.FullbuyNoCountMarketingMapper.selectByMarketId",
                        marketingId);
    }

    /*
     * 根据促销id删除满件打X折信息
     * 
     * @param marketingId 促销活动Id{@link java.lang.Long}
     * 
     * @return int
     * 
     * @see com.ningpai.marketing.dao.FullbuyNoCountMarketingMapper#
     * delFullbuyNoCounttByNarketingId(java.lang.Long)
     */
    @Override
    public int delFullbuyNoCounttByNarketingId(Long marketingId) {
        return this
                .update("com.ningpai.marketing.dao.FullbuyNoCountMarketingMapper.delFullbuyNoCountByMarketingId",
                        marketingId);
    }
}
