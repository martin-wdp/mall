/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.system.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.system.bean.Express;
import com.ningpai.system.dao.ExpressMapper;
import com.ningpai.system.util.SelectBean;

/**
 * 配送方式接口实现类
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2013年12月18日 下午4:20:27
 * @version 1.0
 */
@Repository("expressMapper")
public class ExpressMapperImpl extends BasicSqlSupport implements ExpressMapper {
    /**
     * 删除配送公司信息
     * 
     * @param expid
     * @return int
     */
    public int deleteByPrimaryKey(Long expid) {
        return this.insert(
                "com.ningpai.system.dao.ExpressMapper.deleteByPrimaryKey",
                expid);
    }

    /**
     * 添加配送设置信息
     * 
     * @param record
     * @return int
     */
    public int insert(Express record) {
        return 0;
    }

    /**
     * 添加配送设置信息--可选字段
     * 
     * @param record
     * @return int
     */
    public int insertSelective(Express record) {
        return this.insert(
                "com.ningpai.system.dao.ExpressMapper.insertSelective", record);
    }

    /**
     * 按expid查询配送信息
     * 
     * @param expid
     * @return
     */
    public Express selectByPrimaryKey(Long expid) {
        return this.selectOne(
                "com.ningpai.system.dao.ExpressMapper.selectByPrimaryKey",
                expid);
    }

    /**
     * 修改配送信息--可选字段
     * 
     * @param record
     * @return int
     */
    public int updateByPrimaryKeySelective(Express record) {
        return this
                .update("com.ningpai.system.dao.ExpressMapper.updateByPrimaryKeySelective",
                        record);
    }

    /**
     * 修改配送信息
     * 
     * @param record
     * @return int
     */
    public int updateByPrimaryKey(Express record) {
        return 0;
    }

    /**
     * 分页查询配送信息
     * 
     * @param map
     * @return
     */
    public List<Object> findByPageBean(Map<String, Object> map) {
        return this.selectList(
                "com.ningpai.system.dao.ExpressMapper.findByPageBean", map);
    }

    /**
     * 查询配送总行数
     * 
     * @return
     */
    public int findTotalCount(SelectBean selectBean) {

        return this.selectOne(
                "com.ningpai.system.dao.ExpressMapper.findTotalCount",
                selectBean);
    }

}
