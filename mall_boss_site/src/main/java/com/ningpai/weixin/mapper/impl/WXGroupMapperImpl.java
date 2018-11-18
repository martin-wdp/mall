/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.weixin.mapper.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.weixin.bean.WXGroup;
import com.ningpai.weixin.mapper.WXGroupMapper;

/**
 * @see com.ningpai.m.weixin.dao.WXGroupMapper
 * @author NINGPAI-zhangqiang
 * @since 2014年9月3日 下午3:25:01
 * @version 0.0.1
 */
@Repository("wXGroupMapperc")
public class WXGroupMapperImpl extends BasicSqlSupport implements WXGroupMapper {
    /*
     * 
     * 
     * @see com.ningpai.weixin.mapper.WXGroupMapper#selectAllGroup()
     */
    @Override
    public List<WXGroup> selectAllGroup() {
        return this.selectList("com.ningpai.weixin.mapper.WXGroupMapper.selectAllGroup");
    }

}
