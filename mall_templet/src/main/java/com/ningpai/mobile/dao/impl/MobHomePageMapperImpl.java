/*
 * Copyright 2014 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.mobile.dao.impl;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.mobile.bean.MobHomePage;
import com.ningpai.mobile.dao.MobHomePageMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName: MobHomePageMapperImpl
 * @Description: DAO实现类-移动版首页
 * @author Wanghy
 * @date 2014年10月11日 下午1:59:09
 */
@Repository("MobHomePageMapper")
public class MobHomePageMapperImpl extends BasicSqlSupport implements MobHomePageMapper {

    @Override
    public int deleteByPrimaryKey(Long homepageId) {

        return this.delete("com.ningpai.mobile.dao.MobHomePageMapper.deleteByPrimaryKey", homepageId);
    }

    @Override
    public int insert(MobHomePage record) {

        return 0;
    }

    @Override
    public int insertSelective(MobHomePage record) {

        return this.insert("com.ningpai.mobile.dao.MobHomePageMapper.insertSelective", record);
    }

    @Override
    public MobHomePage selectThirdMob(Long storeId) {

        return this.selectOne("com.ningpai.mobile.dao.MobHomePageMapper.selectThirdMob",storeId);
    }

    @Override
    public int updateByPrimaryKeySelective(MobHomePage record) {

        return this.update("com.ningpai.mobile.dao.MobHomePageMapper.updateByPrimaryKeySelective", record);
    }

    @Override
    public int updateByPrimaryKeyWithBLOBs(MobHomePage record) {

        return this.update("com.ningpai.mobile.dao.MobHomePageMapper.updateByPrimaryKeyWithBLOBs", record);
    }

    @Override
    public int updateByPrimaryKey(MobHomePage record) {

        return this.update("com.ningpai.mobile.dao.MobHomePageMapper.updateByPrimaryKey", record);
    }

    @Override
    public MobHomePage selectByMerchantId(Long merchantId) {

        return this.selectOne("com.ningpai.mobile.dao.MobHomePageMapper.selectByMerchantId", merchantId);
    }

    @Override
    public MobHomePage selectByPrimaryKey(Long homepageId) {

        return this.selectOne("com.ningpai.mobile.dao.MobHomePageMapper.selectByPrimaryKey", homepageId);
    }

    @Override
    public List<MobHomePage> selectAllUnstatusByMerchantId(Long merchantId) {
        return this.selectList("com.ningpai.mobile.dao.MobHomePageMapper.selectAllUnstatusByMerchantId", merchantId);
    }

    @Override
    public int updateByMerchantId(Long merchantId) {

        return this.update("com.ningpai.mobile.dao.MobHomePageMapper.updateByMerchantId", merchantId);
    }

    @Override
    public int updateByhomepageIdAndMerchantId(MobHomePage record) {

        return this.update("com.ningpai.mobile.dao.MobHomePageMapper.updateByhomepageIdAndMerchantId", record);
    }

    @Override
    public MobHomePage queryCurrHomePageByMerchantId(Long merchantId) {

        return this.selectOne("com.ningpai.mobile.dao.MobHomePageMapper.queryCurrHomePageByMerchantId", merchantId);
    }
    /**
     * 根据商家ID获取该商家当前使用的模板信息
     *
     * @Description: 根据商家ID获取该商家当前使用的模板信息
     * @param storeId
     * @return
     */

    @Override
    public MobHomePage getCurrHomePageByStoreId(Long storeId) {
        return this.selectOne("com.ningpai.mobile.dao.MobHomePageMapper.queryCurrHomePageByStoreId", storeId);
    }

}
