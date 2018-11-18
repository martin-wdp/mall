/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.information.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ningpai.information.bean.InfoUserDefined;
import com.ningpai.information.dao.InfoUserDefinedMapper;
import com.ningpai.information.service.InfoUserDefinedService;

/**
 * SERVICE实现类-文章自定义属性
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年3月26日下午5:12:03
 */
@Service("InfoUserDefinedService")
public class InfoUserDefinedServiceImpl implements InfoUserDefinedService {
    /** DAO-文章自定义属性 */
    private InfoUserDefinedMapper infoUDMapper;

    public InfoUserDefinedMapper getInfoUDMapper() {
        return infoUDMapper;
    }

    @Resource(name = "InfoUserDefinedMapper")
    public void setInfoUDMapper(InfoUserDefinedMapper infoUDMapper) {
        this.infoUDMapper = infoUDMapper;
    }

    /**
     * 根据主键删除
     *
     * @param infoUdId
     * @return
     * @see com.ningpai.information.service.InfoUserDefinedService#deleteUserDefinedById(java.lang.Long)
     */
    @Override
    public int deleteUserDefinedById(Long infoUdId) {
        /** 执行查询方法并把结果赋值给实体类 */
        InfoUserDefined infoUD = infoUDMapper.selectByPrimaryKey(infoUdId);
        /** 设置删除标记为1 */
        infoUD.setDelflag("1");
        /** 设置更新时间为当前时间 */
        infoUD.setUpdateDate(new Date());
        /** 执行更新方法并返回结果 */
        return infoUDMapper.updateByPrimaryKeySelective(infoUD);
    }

    /**
     * 添加
     *
     * @param record
     * @return
     * @see com.ningpai.information.service.InfoUserDefinedService#createUserDefined(com.ningpai.information.bean.InfoUserDefined)
     */
    @Override
    public int createUserDefined(InfoUserDefined record) {
        /**获取当前时间*/
        Date date = new Date();
        /**设置删除标记为0*/
        record.setDelflag("0");
        /**设置创建时间为当前时间*/
        record.setCreateDate(date);
        /**设置更新时间为当前时间*/
        record.setUpdateDate(date);
        /**执行添加方法并返回结果*/
        return infoUDMapper.insertSelective(record);
    }

    /**
     * 修改
     *
     * @param record
     * @return
     * @see com.ningpai.information.service.InfoUserDefinedService#updateUserDefined(com.ningpai.information.bean.InfoUserDefined)
     */
    @Override
    public int updateUserDefined(InfoUserDefined record) {
        /** 设置更新时间为当前时间 */
        record.setUpdateDate(new Date());
        /** 执行更新方法并返回结果 */
        return infoUDMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 查询所有
     *
     * @return
     * @see com.ningpai.information.service.InfoUserDefinedService#findAllUserDefined()
     */
    @Override
    public List<InfoUserDefined> findAllUserDefined() {
        /** 执行查询方法并返回结果 */
        return infoUDMapper.selectAll();
    }

}
