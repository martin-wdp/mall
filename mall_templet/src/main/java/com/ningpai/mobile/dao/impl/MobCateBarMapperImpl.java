/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.mobile.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ningpai.channel.bean.ChannelAdver;
import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.mobile.bean.MobCateBar;
import com.ningpai.mobile.dao.MobCateBarMapper;
import com.ningpai.mobile.vo.MobCateBarVo;

/**
 * DAO实现类-移动版分类导航
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年8月19日下午1:38:34
 */
@Repository("MobCateBarMapper")
public class MobCateBarMapperImpl extends BasicSqlSupport implements MobCateBarMapper {

    /**
     */
    @Override
    public List<ChannelAdver> selectStoreListImage(String userStatus) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("user_status", userStatus);
        return this.selectList("com.ningpai.channel.dao.ChannelAdverMapper.selectStoreListImage", map);
    }

    /**
     */
    @Override
    public int deleteByPrimaryKey(Long cateBarId) {
        return this.delete("com.ningpai.mobile.dao.MobCateBarMapper.deleteByPrimaryKey", cateBarId);
    }

    /**
     */
    @Override
    public int insert(MobCateBar record) {

        return this.insert("com.ningpai.mobile.dao.MobCateBarMapper.insert", record);
    }

    /**
     */
    @Override
    public int insertSelective(MobCateBar record) {

        return this.insert("com.ningpai.mobile.dao.MobCateBarMapper.insertSelective", record);
    }

    /**
     */
    @Override
    public int updateByPrimaryKeySelective(MobCateBar record) {

        return this.update("com.ningpai.mobile.dao.MobCateBarMapper.updateByPrimaryKeySelective", record);
    }

    /**
     */
    @Override
    public int updateByPrimaryKey(MobCateBar record) {

        return this.update("com.ningpai.mobile.dao.MobCateBarMapper.updateByPrimaryKey", record);
    }

    /**
     */
    @Override
    public MobCateBar selectByPrimaryKey(Long cateBarId) {

        return this.selectOne("com.ningpai.mobile.dao.MobCateBarMapper.selectByPrimaryKey", cateBarId);
    }

    /**
     */
    @Override
    public List<MobCateBar> selectByParentId(Long parentId) {

        return this.selectList("com.ningpai.mobile.dao.MobCateBarMapper.selectByParentId", parentId);
    }

    /**
     */
    @Override
    public int selectCountByCateId(Long cateId) {

        return this.selectOne("com.ningpai.mobile.dao.MobCateBarMapper.selectCountByCateId");
    }

    /**
     */
    @Override
    public List<MobCateBarVo> selectAll() {

        return this.selectList("com.ningpai.mobile.dao.MobCateBarMapper.selectAll");
    }

    @Override
    public List<MobCateBar> selectAllForMobChoose() {
        return this.selectList("com.ningpai.mobile.dao.MobCateBarMapper.selectAllForMobChoose");
    }

    /**
     */
    @Override
    public int selectCountByPb() {

        return this.selectOne("com.ningpai.mobile.dao.MobCateBarMapper.selectCountByPb");
    }

    /**
     */
    @Override
    public List<Object> selectAllByPb(Map<String, Object> map) {

        return this.selectList("com.ningpai.mobile.dao.MobCateBarMapper.selectAllByPb", map);
    }

}
