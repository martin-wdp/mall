/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.m.weixin.dao.impl;

import org.springframework.stereotype.Repository;

import com.ningpai.m.weixin.bean.ThreePart;
import com.ningpai.m.weixin.dao.ThreePartMapper;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * @see com.ningpai.m.weixin.dao.ThreePartMapper
 * @author NINGPAI-zhangqiang
 * @since 2014年8月26日 下午6:03:25
 * @version 0.0.1
 */
@Repository("threePartMapperM")
public class ThreePartMapperImpl extends BasicSqlSupport implements ThreePartMapper {

    /*
     * 查询第三方
     * 
     * @see com.ningpai.site.threepart.dao.ThreePartMapper#selectThreePart(java.lang .String)
     */
    @Override
    public ThreePart selectThreePart(String threePartUid) {
        return this.selectOne("com.ningpai.web.dao.ThreePartMapper.selectThreePart", threePartUid);
    }

    /**
     * 插入
     * @param tp
     * @return int
     */
    @Override
    public int insertThreePart(ThreePart tp) {
        return this.insert("com.ningpai.web.dao.ThreePartMapper.insertThreePart", tp);
    }

}
