/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.manager.mapper.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.manager.bean.ImageSet;
import com.ningpai.manager.bean.UpyunConf;
import com.ningpai.manager.mapper.SysHelperMapper;

/**
 * @author ggn
 * 
 */
@Repository("SysHelperMapper")
public class SysHelperMapperImpl extends BasicSqlSupport implements
        SysHelperMapper {

    /**
     * 查询配置
     * 
     * @see com.ningpai.manager.mapper.SysHelperMapper#selectUpyunConf()
     */
    @Override
    public UpyunConf selectUpyunConf() {
        return this
                .selectOne("com.ningpai.core.UpyunConfDaoImpl.selectUpyunConf");
    }

    /**
     * 查询商品图片列表
     * 
     * @see com.ningpai.manager.mapper.SysHelperMapper#selectImageSet()
     */
    @Override
    public List<ImageSet> selectImageSet() {
        return this
                .selectList("com.ningpai.core.dao.ImageSetMapper.selectSetList");
    }

}
