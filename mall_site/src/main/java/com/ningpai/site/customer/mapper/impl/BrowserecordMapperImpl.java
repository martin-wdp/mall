/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.site.customer.mapper.impl;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.site.customer.bean.Browserecord;
import com.ningpai.site.customer.mapper.BrowserecordMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @see com.ningpai.site.customer.mapper.BrowserecordMapper
 * @author NINGPAI-zhangqiang
 * @since 2014年4月12日 下午4:57:39
 * @version 0.0.1
 */
@Repository("browserecordMapper")
public class BrowserecordMapperImpl extends BasicSqlSupport implements
        BrowserecordMapper {
    /**
     * 根据主键删除
     * @param map
     *            浏览编号
     * @return
     */
    @Override
    public int deleteByPrimaryKey(Map<String, Object> map) {
        return this
                .update("com.ningpai.site.customer.dao.BrowserecordMapper.deleteByPrimaryKey",
                        map);
    }

    /**
     * 插入数据
     * @param record
     *            要插入的数据的对象 {@link com.ningpai.site.customer.bean.Browserecord}
     * @return
     */
    @Override
    public int insert(Browserecord record) {
        return 0;
    }

    /**
     * 按条件插入数据
     * @param record
     *            要插入的数据的对象 {@link com.ningpai.site.customer.bean.Browserecord}
     * @return
     */
    @Override
    public int insertSelective(Browserecord record) {
        return 0;
    }

    /**
     * 按照主键编号查找
     * @param likeId
     *            浏览编号
     * @return
     */
    @Override
    public Browserecord selectByPrimaryKey(Long likeId) {
        return null;
    }

    /**
     * 按条件修改信息
     * @param record
     *            要修改的对象 {@link com.ningpai.site.customer.bean.Browserecord}
     * @return
     */
    @Override
    public int updateByPrimaryKeySelective(Browserecord record) {
        return 0;
    }

    /**
     * 根据主键编号修改信息
     * @param record
     *            要修改的对象 {@link com.ningpai.site.customer.bean.Browserecord}
     * @return
     */
    @Override
    public int updateByPrimaryKey(Browserecord record) {
        return 0;
    }

    /**
     * 查询浏览记录
     * @param customerId
     *            会员编号
     * @return
     */
    @Override
    public List<Browserecord> selectBrowserecord(Long customerId) {
        return this
                .selectList(
                        "com.ningpai.site.customer.dao.BrowserecordMapper.selectBrowserecord",
                        customerId);
    }

}
