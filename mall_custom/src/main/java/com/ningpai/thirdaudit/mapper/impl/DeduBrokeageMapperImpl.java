/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.thirdaudit.mapper.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.thirdaudit.bean.DeduBrokeage;
import com.ningpai.thirdaudit.bean.DeduBrokeageVo;
import com.ningpai.thirdaudit.mapper.DeduBrokeageMapper;

/**
 * @see DeduBrokeageMapper
 * @author NINGPAI-zhangqiang
 * @since 2014年7月25日 下午3:03:10
 * @version 0.0.1
 */
@Repository("deduBrokeageMapper")
public class DeduBrokeageMapperImpl extends BasicSqlSupport implements
        DeduBrokeageMapper {

    /*
     * 
     * 
     * @see
     * com.ningpai.thirdaudit.mapper.DeduBrokeageMapper#insertSelective(com.
     * ningpai.thirdaudit.bean.DeduBrokeage)
     */
    @Override
    public int insertSelective(DeduBrokeage record) {
        return this
                .insert("com.ningpai.thirdaudit.mapper.DeduBrokeageMapper.insertSelective",
                        record);
    }

    @Override
    public List<DeduBrokeageVo> selectBrokeageByStoreId(Long storeId) {

        return this
                .selectList(
                        "com.ningpai.thirdaudit.mapper.DeduBrokeageMapper.selectBroheageByStoreId",
                        storeId);
    }

    @Override
    public void deleteByStoreId(Long storeId) {
        this.delete(
                "com.ningpai.thirdaudit.mapper.DeduBrokeageMapper.deleteByStoreId",
                storeId);
    }

    @Override
    public Integer insertByStoreId(Map<String, Object> paramMap) {

        return this
                .insert("com.ningpai.thirdaudit.mapper.DeduBrokeageMapper.insertByStoreId",
                        paramMap);
    }
}
