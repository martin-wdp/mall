/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.mobile.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ningpai.mobile.bean.MobSinglepageMark;
import com.ningpai.mobile.dao.MobSinglepageMarkMapper;
import com.ningpai.mobile.service.MobSinglepageMarkService;

/**
 * 移动版单页标签service层接口 service实现类
 *
 * @author zhangsl
 * @since 2014年11月24日 上午10:58:46
 * @version 0.0.1
 */
@Service("MobSinglepageMarkService")
public class MobSinglepageMarkServiceImpl implements MobSinglepageMarkService {

    /** 移动单页标签dao层接口 */
    @Resource(name = "MobSinglepageMarkMapper")
    private MobSinglepageMarkMapper mobSinglepageMarkMapper;

    /**
     * 查询所有移动版单页标签信息列表
     *
     * @return
     * @see com.ningpai.mobile.service.MobSinglepageMarkService#selectAllMarkInfo()
     */
    @Override
    public List<MobSinglepageMark> selectAllMarkInfo() {
        /** 执行查询方法并返回结果 */
        return mobSinglepageMarkMapper.selectAllMarkInfo();
    }

    /**
     * 添加移动版单页标签信息
     *
     * @param record
     * @return
     * @see com.ningpai.mobile.service.MobSinglepageMarkService#insertSelective(com.ningpai.mobile.bean.MobSinglepageMark)
     */

    @Override
    public int insertSelective(MobSinglepageMark record) {
        /** 设置名称为传入的名称 */
        record.setName(record.getName().trim());
        /** 设置创建时间为当前时间 */
        record.setCreateDate(new Date());
        /** 设置更新时间为当前时间 */
        record.setUpdateDate(new Date());
        /** 设置删除标记为0 */
        record.setDelflag("0");
        record.setMerchantId(-1L);
        /** 执行方法并返回结果 */
        return mobSinglepageMarkMapper.insertSelective(record);

    }

    /**
     * 根据主键ID查询移动版单页标签信息
     *
     * @param markId
     * @return
     * @see com.ningpai.mobile.service.MobSinglepageMarkService#selectMobMarkById(java.lang.Long)
     */
    @Override
    public MobSinglepageMark selectMobMarkById(Long markId) {
        /** 执行方法并返回结果 */
        return mobSinglepageMarkMapper.selectByPrimaryKey(markId);
    }

    /**
     * 根据主键ID更新移动版单页标签信息
     *
     * @param mobSinglepageMark
     * @return
     * @see com.ningpai.mobile.service.MobSinglepageMarkService#updateMobMarkById(com.ningpai.mobile.bean.MobSinglepageMark)
     */
    @Override
    public int updateMobMarkById(MobSinglepageMark mobSinglepageMark) {
        /** 设置名称 */
        mobSinglepageMark.setName(mobSinglepageMark.getName().trim());
        /** 设置时间 */
        mobSinglepageMark.setUpdateDate(new Date());
        /** 执行方法并返回结果 */
        return mobSinglepageMarkMapper.updateByPrimaryKeySelective(mobSinglepageMark);
    }

    /**
     * 根据主键ID逻辑删除 修改delflag的状态 0：未删除 1：已删除
     *
     * @param markId
     * @return
     * @see com.ningpai.mobile.service.MobSinglepageMarkService#updateDelStatus(java.lang.Long)
     */
    @Override
    public int updateDelStatus(Long markId) {
        return mobSinglepageMarkMapper.updateDelStatus(markId);
    }

    /**
     * 查询删除状态为不删除的所有移动版单页标签信息列表
     *
     * @return
     * @see com.ningpai.mobile.service.MobSinglepageMarkService#queryAllMarkInfoByDel()
     */

    @Override
    public List<MobSinglepageMark> queryAllMarkInfoByDel() {
        /** 执行方法并返回结果 */
        return mobSinglepageMarkMapper.queryAllMarkInfoByDel();
    }

    /**
     * 验证name是否存在
     *
     * @param name
     * @return 0:不存在 1：存在
     * @see com.ningpai.mobile.service.MobSinglepageMarkService#checkNameExist(java.lang.String)
     */
    @Override
    public int checkNameExist(String name) {
        /** 执行方法并返回结果 */
        return mobSinglepageMarkMapper.checkNameExist(name);
    }

}
