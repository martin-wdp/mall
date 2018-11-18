/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.comment.dao.impl;

import org.springframework.stereotype.Repository;

import com.ningpai.comment.bean.Infosetting;
import com.ningpai.comment.dao.InfosettingMapper;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * @see com.ningpai.comment.dao.InfosettingMapper
 * @author NINGPAI-zhangqiang
 * @since 2013年12月31日 下午6:39:53
 * @version 0.0.1
 */
@Repository("infosettingMapper")
public class InfosettingMapperImpl extends BasicSqlSupport implements InfosettingMapper {

    /*
     * 
     * 
     * @see com.ningpai.comment.dao.InfosettingMapper#updateByPrimaryKeySelective(com.ningpai.comment.bean.Infosetting)
     */
    @Override
    public int updateByPrimaryKeySelective(Infosetting record) {
        return this.update("com.ningpai.comment.dao.InfosettingMapper.updateByPrimaryKeySelective", record);
    }

    /*
     * 
     * 
     * @see com.ningpai.comment.dao.InfosettingMapper#selectInfoSetting()
     */
    @Override
    public Infosetting selectInfoSetting() {
        return this.selectOne("com.ningpai.comment.dao.InfosettingMapper.selectByPrimaryKey");
    }

}
