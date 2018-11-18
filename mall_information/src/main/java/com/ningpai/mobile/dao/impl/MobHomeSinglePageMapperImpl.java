/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.mobile.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.mobile.bean.MobHomeSinglePage;
import com.ningpai.mobile.dao.MobHomeSinglePageMapper;

/**
 * 移动版单页可视化配置 dao层实现类
 * 
 * @author zhangsl
 * @since 2014年12月5日 下午2:30:17
 * @version
 */
@Repository("MobHomeSinglePageMapper")
public class MobHomeSinglePageMapperImpl extends BasicSqlSupport implements MobHomeSinglePageMapper {

    /**
     * @Title: deleteByPrimaryKey
     * @Description: 根据ID删除
     * @param homepageId
     * @return
     * @see com.ningpai.mobile.dao.MobHomeSinglePageMapper#deleteByPrimaryKey(java.lang.Long)
     */
    @Override
    public int deleteByPrimaryKey(Long homepageId) {

        return this.delete("com.ningpai.mobile.dao.MobHomeSinglePageMapper.deleteByPrimaryKey", homepageId);
    }

    /**
     * @Title: insert
     * @Description: 添加
     * @param record
     * @return
     * @see com.ningpai.mobile.dao.MobHomeSinglePageMapper#insert(com.ningpai.mobile.bean.MobHomeSinglePage)
     */
    @Override
    public int insert(MobHomeSinglePage record) {

        return this.insert("com.ningpai.mobile.dao.MobHomeSinglePageMapper.insert", record);
    }

    /**
     * @Title: insertSelective
     * @Description: 添加-字段可选
     * @param record
     * @return
     * @see com.ningpai.mobile.dao.MobHomeSinglePageMapper#insertSelective(com.ningpai.mobile.bean.MobHomeSinglePage)
     */
    @Override
    public int insertSelective(MobHomeSinglePage record) {

        return this.insert("com.ningpai.mobile.dao.MobHomeSinglePageMapper.insertSelective", record);
    }

    /**
     * @Title: updateByPrimaryKeySelective
     * @Description: 修改-字段可选
     * @param record
     * @return
     * @see com.ningpai.mobile.dao.MobHomeSinglePageMapper#updateByPrimaryKeySelective(com.ningpai.mobile.bean.MobHomeSinglePage)
     */
    @Override
    public int updateByPrimaryKeySelective(MobHomeSinglePage record) {

        return this.update("com.ningpai.mobile.dao.MobHomeSinglePageMapper.updateByPrimaryKeySelective", record);
    }

    /**
     * @Title: updateByPrimaryKeyWithBLOBs
     * @Description: 修改-包括xml字符串
     * @param record
     * @return
     * @see com.ningpai.mobile.dao.MobHomeSinglePageMapper#updateByPrimaryKeyWithBLOBs(com.ningpai.mobile.bean.MobHomeSinglePage)
     */
    @Override
    public int updateByPrimaryKeyWithBLOBs(MobHomeSinglePage record) {

        return this.update("com.ningpai.mobile.dao.MobHomeSinglePageMapper.updateByPrimaryKeyWithBLOBs", record);
    }

    /**
     * @Title: updateByPrimaryKey
     * @Description: 修改-包括xml字符串
     * @param record
     * @return
     * @see com.ningpai.mobile.dao.MobHomeSinglePageMapper#updateByPrimaryKey(com.ningpai.mobile.bean.MobHomeSinglePage)
     */
    @Override
    public int updateByPrimaryKey(MobHomeSinglePage record) {

        return this.update("com.ningpai.mobile.dao.MobHomeSinglePageMapper.updateByPrimaryKey", record);
    }

    /**
     * @Title: selectByPrimaryKey
     * @Description: 根据ID获取模板信息
     * @param homepageId
     * @return
     * @see com.ningpai.mobile.dao.MobHomeSinglePageMapper#selectByPrimaryKey(java.lang.Long)
     */
    @Override
    public MobHomeSinglePage selectByPrimaryKey(Long homepageId) {

        return this.selectOne("com.ningpai.mobile.dao.MobHomeSinglePageMapper.selectByPrimaryKey", homepageId);
    }

    /**
     * @Title:queryInfoBySinglepageId
     * @Description: 根据移动版单页ID获取移动版模板信息
     * @param singlepageId
     * @return
     * @see com.ningpai.mobile.dao.MobHomeSinglePageMapper#queryInfoBySinglepageId(java.lang.Long)
     */
    @Override
    public MobHomeSinglePage queryInfoBySinglepageId(Long singlepageId) {

        return this.selectOne("com.ningpai.mobile.dao.MobHomeSinglePageMapper.queryInfoBySinglepageId", singlepageId);
    }

    /**
     * @Title:queryinitInfoCount
     * @Description:查询SinglepageId=-1的移动版模板信息 即查询用于初始化的模板信息的总条数
     * @return
     * @see com.ningpai.mobile.dao.MobHomeSinglePageMapper#queryinitInfoCount()
     */
    @Override
    public int queryinitInfoCount() {

        return this.selectOne("com.ningpai.mobile.dao.MobHomeSinglePageMapper.queryinitInfoCount");
    }

    /**
     * @Title:queryinitInfoBySinglepageId
     * @Description:查询SinglepageId=-1的移动版模板信息的集合 即查询用于初始化的模板信息
     * @return List<MobHomeSinglePage>
     * @see com.ningpai.mobile.dao.MobHomeSinglePageMapper#queryinitInfoBySinglepageId()
     */
    @Override
    public List<MobHomeSinglePage> queryinitInfoBySinglepageId() {
        return this.selectList("com.ningpai.mobile.dao.MobHomeSinglePageMapper.queryinitInfoBySinglepageId");
    }

}
