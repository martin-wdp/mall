package com.ningpai.manager.mapper.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.manager.bean.EchartsTimeCount;
import com.ningpai.manager.bean.IndexCountBean;
import com.ningpai.manager.mapper.IndexCountBeanMapper;

/**
 * 待办实现类
 * */
@Repository("IndexCountBeanMapper")
public class IndexCountBeanMapperImpl extends BasicSqlSupport implements
        IndexCountBeanMapper {

    /**
     * 
     * @see
     * com.ningpai.manager.mapper.IndexCountBeanMapper#selectIndexCount(java
     * .util.Map)
     */
    @Override
    public IndexCountBean selectIndexCount(Map<String, Object> paramMap) {
        IndexCountBean ib = new IndexCountBean();

        ib.setSubOrderCount((Integer) this
                .selectOne(
                        "com.ningpai.manager.index.IndexCountBeanMapper.selectSubOrderCount",
                        paramMap));
        ib.setPayOrderCount((Integer) this
                .selectOne(
                        "com.ningpai.manager.index.IndexCountBeanMapper.selectPayOrderCount",
                        paramMap));
        ib.setSendOrderCount((Integer) this
                .selectOne(
                        "com.ningpai.manager.index.IndexCountBeanMapper.selectSendOrderCount",
                        paramMap));
        ib.setYesterdayTurnover((BigDecimal) this
                .selectOne(
                        "com.ningpai.manager.index.IndexCountBeanMapper.selectYesterdayTurnover",
                        paramMap));
        ib.setSumTunover((BigDecimal) this
                .selectOne("com.ningpai.manager.index.IndexCountBeanMapper.selectSumTunover"));
        ib.setWarningCount((Integer) this
                .selectOne("com.ningpai.manager.index.IndexCountBeanMapper.selectWarningCount"));
        ib.setOutOfStock((Integer) this
                .selectOne("com.ningpai.manager.index.IndexCountBeanMapper.selectOutOfStock"));
        ib.setShelvesCount((Integer) this
                .selectOne("com.ningpai.manager.index.IndexCountBeanMapper.selectShelvesCount"));
        ib.setSumProduct((Integer) this
                .selectOne("com.ningpai.manager.index.IndexCountBeanMapper.selectSumProduct"));
        ib.setNewCustomerCount((Integer) this
                .selectOne(
                        "com.ningpai.manager.index.IndexCountBeanMapper.selectNewCustomerCount",
                        paramMap));
        ib.setSumCustomerCount((Integer) this
                .selectOne("com.ningpai.manager.index.IndexCountBeanMapper.selectSumCustomerCount"));
        ib.setNewConsulting((Integer) this
                .selectOne("com.ningpai.manager.index.IndexCountBeanMapper.selectNewConsulting"));
        ib.setNewComments((Integer) this
                .selectOne("com.ningpai.manager.index.IndexCountBeanMapper.selectNewComments"));
        return ib;
    }

    /**
     * @see
     * com.ningpai.manager.mapper.IndexCountBeanMapper#selectSubOrderList(java
     * .util.Map)
     */
    @Override
    public List<EchartsTimeCount> selectSubOrderList(Map<String, Object> map) {
        return this
                .selectList(
                        "com.ningpai.manager.index.IndexCountBeanMapper.selectSubOrderList",
                        map);
    }

    /**
     * @see
     * com.ningpai.manager.mapper.IndexCountBeanMapper#payorderlist(java.util
     * .Map)
     */
    @Override
    public List<EchartsTimeCount> payorderlist(Map<String, Object> map) {
        return this.selectList(
                "com.ningpai.manager.index.IndexCountBeanMapper.payorderlist",
                map);
    }

    /**
     * @see
     * com.ningpai.manager.mapper.IndexCountBeanMapper#sendorderlist(java.util
     * .Map)
     */
    @Override
    public List<EchartsTimeCount> sendorderlist(Map<String, Object> map) {
        return this.selectList(
                "com.ningpai.manager.index.IndexCountBeanMapper.sendorderlist",
                map);
    }

}
