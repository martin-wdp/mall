package com.ningpai.marketing.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.marketing.bean.FullbuyNoDiscountMarketing;
import com.ningpai.marketing.dao.FullbuyNoDiscountMarketingMapper;

/**
 * 满x件打y折
 * 
 * @author zhoux
 *
 */
@Repository("FullbuyNoDiscountMarketingMapper")
public class FullbuyNoDiscountMarketingMapperImpl extends BasicSqlSupport
        implements FullbuyNoDiscountMarketingMapper {

    /**
     * 插入满x件打y折数据
     * 
     * @param fbuyno
     */
    @Override
    public int insertSelective(FullbuyNoDiscountMarketing fbuyno) {
        return this
                .insert("com.ningpai.marketing.dao.FullbuyNoDiscountMarketingMapper.insert",
                        fbuyno);
    }

    /**
     * 查询促销信息
     * 
     * @param marketingId
     * @return
     */
    @Override
    public FullbuyNoDiscountMarketing selectByMarketId(Long marketingId) {
        return this
                .selectOne(
                        "com.ningpai.marketing.dao.FullbuyNoDiscountMarketingMapper.selectByMarketId",
                        marketingId);
    }

    /**
     * 插入计数
     * 
     * @param param
     * @return
     */
    @Override
    public int insertCountConditionByMarketing(Map<String, Object> param) {
        return this
                .update("com.ningpai.marketing.dao.FullbuyNoDiscountMarketingMapper.insertCountConditionByMarketing",
                        param);
    }

    /**
     * 更新满购件数打折计数为空
     * 
     * @return
     */
    @Override
    public int update() {
        return this
                .update("com.ningpai.marketing.dao.FullbuyNoDiscountMarketingMapper.update");
    }

    /**
     * 修改满购件数打折
     * 
     * @param fbuyno
     * @return
     */
    @Override
    public int modifySelective(FullbuyNoDiscountMarketing fbuyno) {
        return this
                .insert("com.ningpai.marketing.dao.FullbuyNoDiscountMarketingMapper.modifySelective",
                        fbuyno);
    }

    /**
     * 查询促销信息
     * 
     * @param marketingId
     * @return
     */
    @Override
    public List<FullbuyNoDiscountMarketing> selectdiscountsByMarketId(
            Long marketingId) {
        return this
                .selectList(
                        "com.ningpai.marketing.dao.FullbuyNoDiscountMarketingMapper.selectByMarketId",
                        marketingId);
    }

    /**
     * 根据促销id删除满件打X折信息
     * 
     * @param marketingId
     *            促销活动Id{@link java.lang.Long}
     * @return int
     */
    @Override
    public int delFullbuyNoDiscountByNarketingId(Long marketingId) {
        return this
                .update("com.ningpai.marketing.dao.FullbuyNoDiscountMarketingMapper.delFullbuyNoDiscountByMarketingId",
                        marketingId);
    }

}
