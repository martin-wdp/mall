/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.thirdaudit.mapper.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.thirdaudit.mapper.ThirdManagerAuthorityMapper;

/**
 * @see om.ningpai.thirdaudit.mapper.ThirdManagerAuthorityMapper
 * @author NINGPAI-zhangqiang
 * @since 2014年5月27日 上午10:24:54
 * @version 0.0.1
 */
@Repository("auditManagerAuthorityMapper")
public class ThirdManagerAuthorityMapperImpl extends BasicSqlSupport implements
        ThirdManagerAuthorityMapper {
    /*
     * 
     * 
     * @see
     * com.ningpai.thirdaudit.mapper.ThirdManagerAuthorityMapper#addRecord(java
     * .util.Map)
     */
    @Override
    public int addRecord(Map<String, Object> paramMap) {
        return this
                .insert("com.ningpai.thirdaudit.mapper.ThirdManagerAuthorityMapper.addRecord",
                        paramMap);
    }

    /*
     * 
     * @see
     * com.ningpai.thirdaudit.mapper.ThirdManagerAuthorityMapper#insertAuthority
     * (java.util.Map)
     */
    @Override
    public int insertAuthority(Map<String, Object> map) {
        return this
                .insert("com.ningpai.thirdaudit.mapper.ThirdManagerAuthorityMapper.insertAuthority",
                        map);
    }

    /*
     * 
     * @see
     * com.ningpai.thirdaudit.mapper.ThirdManagerAuthorityMapper#selectLastId()
     */
    @Override
    public Long selectLastId() {
        return this
                .selectOne("com.ningpai.thirdaudit.mapper.ThirdManagerAuthorityMapper.selectLastId");
    }

    /*
     * 
     * @see
     * com.ningpai.thirdaudit.mapper.ThirdManagerAuthorityMapper#insertPageList
     * (java.util.Map)
     */
    @Override
    public int insertPageList(Map<String, Object> map) {
        return this
                .insert("com.ningpai.thirdaudit.mapper.ThirdManagerAuthorityMapper.insertPageList",
                        map);
    }

    /*
     * 
     * @see com.ningpai.thirdaudit.mapper.ThirdManagerAuthorityMapper#
     * selectAllThirdPageId()
     */
    @Override
    public List<Long> selectAllThirdPageId() {
        return this
                .selectList("com.ningpai.thirdaudit.mapper.ThirdManagerAuthorityMapper.selectAllThirdPageId");
    }

}
