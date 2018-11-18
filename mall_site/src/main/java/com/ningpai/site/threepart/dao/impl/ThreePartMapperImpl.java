/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.site.threepart.dao.impl;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.site.threepart.bean.ThreePart;
import com.ningpai.site.threepart.dao.ThreePartMapper;

/**
 * @author ggn
 * 
 */
@Repository("ThreePartMapper")
public class ThreePartMapperImpl extends BasicSqlSupport implements ThreePartMapper {

    /*
     * 
     * 
     * @see com.ningpai.site.threepart.dao.ThreePartMapper#selectThreePart(java.lang .String)
     */
    @Override
    public ThreePart selectThreePart(String threePartUid) {
        return this.selectOne("com.ningpai.web.dao.ThreePartMapper.selectThreePart", threePartUid);
    }

    @Override
    public int insertThreePart(ThreePart tp) {
        return this.insert("com.ningpai.web.dao.ThreePartMapper.insertThreePart", tp);
    }

}
