/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.temp.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.temp.bean.Megawizard;
import com.ningpai.temp.dao.MegawizardMapper;

/**
 * 页面说明数据操作类
 * 
 * @author NINGPAI-ZhuoYu
 * @since 2014-07-23 17:43:53
 */
@Repository("MegawizardMapper")
public class MegawizardMapperImpl extends BasicSqlSupport implements MegawizardMapper {

    @Override
    public int insert(Megawizard record) {
        return this.insert("com.ningpai.temp.dao.MegawizardMapper.insert", record);
    }

    @Override
    public int selectCountByTempId(int tempId) {
        return this.selectOne("com.ningpai.temp.dao.MegawizardMapper.selectCountByTempId", tempId);
    }

    @Override
    public List<Object> selectByTempId(Map<String, Object> map) {
        return this.selectList("com.ningpai.temp.dao.MegawizardMapper.selectByTempId", map);
    }

    @Override
    public int updateById(List<Long> list) {
        return this.update("com.ningpai.temp.dao.MegawizardMapper.updateById", list);
    }

    @Override
    public int updateByIdToContent(Megawizard meg) {
        return this.update("com.ningpai.temp.dao.MegawizardMapper.updateByIdToContent", meg);
    }

    @Override
    public Megawizard selectById(Long id) {
        return this.selectOne("com.ningpai.temp.dao.MegawizardMapper.selectById", id);
    }

    @Override
    public Megawizard selectBySort(Map<String, Object> map) {
        return this.selectOne("com.ningpai.temp.dao.MegawizardMapper.selectBySort", map);
    }

}
