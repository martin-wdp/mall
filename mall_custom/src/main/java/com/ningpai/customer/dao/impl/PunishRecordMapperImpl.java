/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.customer.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.customer.bean.PunishRecord;
import com.ningpai.customer.dao.PunishRecordMapper;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * 处罚记录实现类
 * */
@Repository("PunishRecordMapper")
public class PunishRecordMapperImpl extends BasicSqlSupport implements
        PunishRecordMapper {
    /**
     * 根据主键删除记录
     * @param id
     * @return 受影响的行数
     * */
    @Override
    public int deleteByPrimaryKey(Long id) {

        return 0;
    }
    /**
     * 插入一条记录
     *
     * @param record
     * */
    @Override
    public int insert(PunishRecord record) {

        return 0;
    }
    /**
     * 添加
     *
     * @param record
     * @return
     */
    @Override
    public int insertSelective(PunishRecord record) {

        return this.insert(
                "com.ningpai.customer.dao.PunishRecordMapper.insertSelective",
                record);
    }
    /**
     * 根据主键id查询记录
     * @param id
     * */
    @Override
    public PunishRecord selectByPrimaryKey(Long id) {

        return null;
    }
    /**
     * 根据主键进行修改
     *
     * @param record
     * */
    @Override
    public int updateByPrimaryKeySelective(PunishRecord record) {

        return 0;
    }
    /**
     * 根据主键进行修改
     *
     * @param record
     * */
    @Override
    public int updateByPrimaryKey(PunishRecord record) {

        return 0;
    }
    /**
     * 根据商家id查询
     *
     * @param thirdId
     * */
    @Override
    public PunishRecord queryInfoByThirdId(Long thirdId) {

        return this
                .selectOne(
                        "com.ningpai.customer.dao.PunishRecordMapper.queryInfoByThirdId",
                        thirdId);
    }
    /**
     * 根据商家id进行查询集合
     * @param thirdId
     * */
    @Override
    public List<PunishRecord> queryInfoByTidandDate(Long thirdId) {

        return this
                .selectList(
                        "com.ningpai.customer.dao.PunishRecordMapper.queryInfoByTidandDate",
                        thirdId);
    }
    /**
     * 第三方显示处罚记录用
     *
     * @param paramMap
     * @return
     */
    @Override
    public List<Object> selectRecordByPage(Map<String, Object> paramMap) {

        return this
                .selectList(
                        "com.ningpai.customer.dao.PunishRecordMapper.selectRecordByPage",
                        paramMap);
    }
    /**
     * 根据商家id查询所有行数
     *
     * @param thirdId
     * */
    @Override
    public int selectAllCountByTid(Long thirdId) {

        return this
                .selectOne(
                        "com.ningpai.customer.dao.PunishRecordMapper.selectAllCountByTid",
                        thirdId);
    }
    /**
     * 后台显示处罚记录用
     */
    @Override
    public List<Object> selectPunishedRecordByPage(Map<String, Object> paramMap) {

        return this
                .selectList(
                        "com.ningpai.customer.dao.PunishRecordMapper.selectPunishedRecordByPage",
                        paramMap);
    }
    /**
     * 查询所有根据参数
     *
     * @param paramMap
     * */
    @Override
    public int selectPunishedAllCountByTid(Map<String, Object> paramMap) {

        return this
                .selectOne(
                        "com.ningpai.customer.dao.PunishRecordMapper.selectPunishedAllCountByTid",
                        paramMap);
    }

}
