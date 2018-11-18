/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.third.seller.mapper.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.third.seller.bean.Express;
import com.ningpai.third.seller.mapper.ExpressInfoMapper;

/**
 * 物流信息MapperImpl
 * 
 * @author Zhouh
 * @since 2014.5.22
 * @version 0.0.1
 * 
 */
@Repository("expressInfoMapper")
public class ExpressInfoMapperImpl extends BasicSqlSupport implements ExpressInfoMapper {

    @Override
    public Express selectExpressByCom(String expCompany, Long storeId) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("expCompany",expCompany);
        map.put("storeId",storeId);
        return this.selectOne("com.ningpai.third.seller.mapper.ExpressInfoMapper.selectExpressByCom", map);
    }

    /**
     * 根据主键删除对象
     * @param shoreExpId
     * @return
     */
    public int deleteByPrimaryKey(Long shoreExpId) {
        return 0;
    }

    /**
     * 保存一个物流公司
     * @param record
     * @return
     */
    public int insert(Express record) {
        return 0;
    }

    /**
     * 保存一个物流公司
     * @param record
     * @return
     */
    public int insertSelective(Express record) {
        return 0;
    }

    /**
     * 根据ID获取单个物流公司对象
     * @param shoreExpId
     * @return
     */
    public Express selectByPrimaryKey(Long shoreExpId) {
        return null;
    }

    /**
     * 根据ID修改单个的物流信息
     * @param record
     * @return
     */
    public int updateByPrimaryKeySelective(Express record) {
        return 0;
    }

    /**
     * 根据ID修改单个的物流信息
     * @param record
     * @return
     */
    public int updateByPrimaryKey(Express record) {
        return 0;
    }

    /**
     * 查询所有的物流信息
     * @param express
     *            店铺编号<@link Long>
     * @return
     */
    public List<Express> selectByStoreId(Express express) {
        return this.selectList("com.ningpai.third.seller.mapper.ExpressInfoMapper.selectByStoreId", express);
    }

    /**
     * 查询所有的物流信息
     * @param express
     *            店铺编号<@link Long>
     * @return
     */
    public List<Express> selectByStoreIds(Express express) {
        return this.selectList("com.ningpai.third.seller.mapper.ExpressInfoMapper.selectByStoreIds", express);
    }

    /**
     * 保存物流信息
     * @param express
     *            <@link Express>
     * @return
     */
    public int insertExpress(Express express) {
        return this.insert("com.ningpai.third.seller.mapper.ExpressInfoMapper.insertExpress", express);
    }

    /**
     * 更新物流信息
     * @param express
     *            =1 <@link Express>
     * @return
     */
    public int updateStateByShoreExpId(Express express)     {
        return this.update("com.ningpai.third.seller.mapper.ExpressInfoMapper.updateStateByShoreExpId", express);
    }

    /**
     * 根据ID更新物流信息
     * @param express
     *            =0 <@link Express>
     * @return
     */
    public int updateStateBackByShoreExpId(Express express) {
        return this.update("com.ningpai.third.seller.mapper.ExpressInfoMapper.updateStateBackByShoreExpId", express);
    }

    /**
     * 根据物流ID删除物流信息
     * @param delflag
     * @return
     */
    public int deleteByShoreExpIdUpdate(Express delflag) {
        return this.update("com.ningpai.third.seller.mapper.ExpressInfoMapper.deleteByShoreExpIdUpdate", delflag);
    }

    /**
     * 获取商家物流的数量
     * @param storeId
     *            店铺编号<@link Long>
     * @return
     */
    public int selectDefaultRows(Long storeId) {
        return this.selectOne("com.ningpai.third.seller.mapper.ExpressInfoMapper.selectDefaultRows", storeId);
    }

    /**
     * 修改物流信息
     * @param express
     *            <@link Express>
     * @return
     */
    public int updateExpress(Express express) {
        return this.update("com.ningpai.third.seller.mapper.ExpressInfoMapper.updateExpress", express);
    }

    /**
     * 获取单个的物流信息对象
     * @param shoreExpId
     *            <@link Long>
     * @return
     */
    public Express selectByshoreExpId(Long shoreExpId) {
        return this.selectOne("com.ningpai.third.seller.mapper.ExpressInfoMapper.selectByshoreExpId", shoreExpId);
    }

    /**
     * 批量更改物流信息的状态
     * @param list
     *            <@link List<Express>>
     * @return
     */
    public int updateBatch(List<Express> list) {
        return this.update("com.ningpai.third.seller.mapper.ExpressInfoMapper.updateBatch", list);
    }

}
