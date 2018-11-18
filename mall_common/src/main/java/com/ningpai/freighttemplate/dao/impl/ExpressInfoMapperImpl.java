/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.freighttemplate.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ningpai.freighttemplate.bean.Express;
import com.ningpai.freighttemplate.dao.ExpressInfoMapper;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * 物流信息MapperImpl
 * 
 * @author Zhouh
 * @since 2014.5.22
 * @version 0.0.1
 * 
 */
@Repository("expressInfoMapperThird")
public class ExpressInfoMapperImpl extends BasicSqlSupport implements ExpressInfoMapper {
    /**
     * 删除
     *
     * @see
     */
    public int deleteByPrimaryKey(Long shoreExpId) {
        return 0;
    }

    /**
     * 插入
     *
     * @see
     */
    public int insert(Express record) {
        return 0;
    }

    /**
     * 插入
     *
     * @see
     */
    public int insertSelective(Express record) {
        return 0;
    }

    /**
     * 查询物流详细
     *
     * @see
     */
    public Express selectByPrimaryKey(Long shoreExpId) {
        return null;
    }

    /**
     * 修改物流
     *
     * @see
     */
    public int updateByPrimaryKeySelective(Express record) {
        return 0;
    }

    /**
     * 修改物流
     *
     * @see
     */
    public int updateByPrimaryKey(Express record) {
        return 0;
    }

    /**
     * 根据商家编号获取商家物流信息
     * 
     * @see
     *
     *      .Long)
     */
    public List<Express> selectByStoreId(Long storeId) {
        return this.selectList("com.ningpai.web.third.seller.mapper.ExpressInfoMapper.selectByStoreId", storeId);
    }

    /**
     * 插入物流信息
     * 
     * @see
     *
     *      .third.seller.bean.Express)
     */
    public int insertExpress(Express express) {
        return this.insert("com.ningpai.web.third.seller.mapper.ExpressInfoMapper.insertExpress", express);
    }

    /**
     * 根据商家物流编号修改物流信息
     * 
     * @see
     */
    public int updateStateByShoreExpId(Express state) {
        return this.update("com.ningpai.web.third.seller.mapper.ExpressInfoMapper.updateStateByShoreExpId", state);
    }

    /**
     * 设置默认物流
     * 
     * @see
     */
    public int updateStateBackByShoreExpId(Express state) {
        return this.update("com.ningpai.web.third.seller.mapper.ExpressInfoMapper.updateStateBackByShoreExpId", state);
    }

    /**
     * 根据商家物流编号与商家编号修改物流信息
     * 
     * @see
     */
    public int deleteByShoreExpIdUpdate(Express delflag) {
        return this.update("com.ningpai.web.third.seller.mapper.ExpressInfoMapper.deleteByShoreExpIdUpdate", delflag);
    }

    /**
     * 查询商家默认物流数量
     * 
     * @see
     */
    public int selectDefaultRows(Long storeId) {
        return this.selectOne("com.ningpai.web.third.seller.mapper.ExpressInfoMapper.selectDefaultRows", storeId);
    }

    /**
     * 根据商家物流编号修改物流信息
     * 
     * @see
     */
    public int updateExpress(Express express) {
        return this.update("com.ningpai.web.third.seller.mapper.ExpressInfoMapper.updateExpress", express);
    }

    /**
     * 根据商家物流编号查询信息
     * 
     * @see
     */
    public Express selectByshoreExpId(Long shoreExpId) {
        return this.selectOne("com.ningpai.web.third.seller.mapper.ExpressInfoMapper.selectByshoreExpId", shoreExpId);
    }

    /**
     * 批量修改操作
     * 
     * @see
     */
    public int updateBatch(List<Express> list) {
        return this.update("com.ningpai.web.third.seller.mapper.ExpressInfoMapper.updateBatch", list);
    }

}
