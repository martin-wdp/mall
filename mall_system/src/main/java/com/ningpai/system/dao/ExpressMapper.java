/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.system.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.system.bean.Express;
import com.ningpai.system.util.SelectBean;

/**
 * 配送公司接口
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2013年12月18日 下午4:18:27
 * @version 1.0
 */
public interface ExpressMapper {

    /**
     * 删除配送公司信息
     * 
     * @param expid
     * @return int
     */
    int deleteByPrimaryKey(Long expid);

    /**
     * 添加配送设置信息
     * 
     * @param record
     * @return int
     */
    int insert(Express record);

    /**
     * 添加配送设置信息--可选字段
     * 
     * @param record
     * @return int
     */
    int insertSelective(Express record);

    /**
     * 按expid查询配送信息
     * 
     * @param expid
     * @return
     */
    Express selectByPrimaryKey(Long expid);

    /**
     * 修改配送信息--可选字段
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(Express record);

    /**
     * 修改配送信息
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKey(Express record);

    /**
     * 查询配送总行数
     * 
     * @return
     */
    int findTotalCount(SelectBean selectBean);

    /**
     * 分页查询配送信息
     * 
     * @param map
     * @return
     */
    List<Object> findByPageBean(Map<String, Object> map);

}
