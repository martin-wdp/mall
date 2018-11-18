/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.customer.dao.impl;

import com.ningpai.customer.bean.InsideLetter;
import com.ningpai.customer.dao.InsideLetterMapper;
import com.ningpai.customer.vo.InsideLetterVo;
import com.ningpai.manager.base.BasicSqlSupport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @see com.ningpai.customer.dao.InsideLetterMapper
 * @author NINGPAI-zhangqiang
 * @since 2014年8月7日 上午11:02:51
 * @version 0.0.1
 */
@Repository("insideletter")
public class InsideLetterMapperImpl extends BasicSqlSupport implements
        InsideLetterMapper {

    /*
     * 
     * 
     * @see
     * com.ningpai.customer.dao.InsideLetterMapper#deleteByPrimaryKey(java.lang
     * .Long)
     */
    @Override
    public int deleteByPrimaryKey(Long letterId) {
        return 0;
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.customer.dao.InsideLetterMapper#insert(com.ningpai.customer
     * .bean.InsideLetter)
     */
    @Override
    public int insert(InsideLetter record) {
        return 0;
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.customer.dao.InsideLetterMapper#insertSelective(com.ningpai
     * .customer.bean.InsideLetter)
     */
    @Override
    public int insertSelective(InsideLetter record) {
        return this.insert(
                "com.ningpai.customer.dao.InsideLetterMapper.insertSelective",
                record);
    }

    @Override
    public int insertNotices(List<InsideLetter> letter) {
        return this.insert(
                "com.ningpai.customer.dao.InsideLetterMapper.insertNotices",
                letter);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.customer.dao.InsideLetterMapper#selectByPrimaryKey(java.lang
     * .Long)
     */
    @Override
    public InsideLetter selectByPrimaryKey(Long letterId) {
        return this
                .selectOne(
                        "com.ningpai.customer.dao.InsideLetterMapper.selectByPrimaryKey",
                        letterId);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.customer.dao.InsideLetterMapper#updateByPrimaryKeySelective
     * (com.ningpai.customer.bean.InsideLetter)
     */
    @Override
    public int updateByPrimaryKeySelective(InsideLetter record) {
        return 0;
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.customer.dao.InsideLetterMapper#updateByPrimaryKey(com.ningpai
     * .customer.bean.InsideLetter)
     */
    @Override
    public int updateByPrimaryKey(InsideLetter record) {
        return 0;
    }

    @Override
    public List<Object> queryInsideLetter(Map<String, Object> paramMap) {
        return this.selectList(
                "com.ningpai.customer.dao.InsideLetterMapper.selectList",
                paramMap);
    }

    @Override
    public int readedLetter(InsideLetterVo inside) {
        return this.insert(
                "com.ningpai.customer.dao.InsideLetterMapper.readed", inside);
    }

    @Override
    public int deleteLetter(Long relaId) {
        return this.update(
                "com.ningpai.customer.dao.InsideLetterMapper.deleteByrelaId",
                relaId);
    }

    @Override
    public Long letterIsRead(Map<String, Object> paramMap) {
        return this.selectOne(
                "com.ningpai.customer.dao.InsideLetterMapper.selectisread",
                paramMap);
    }

    @Override
    public int deleteLetterNo(InsideLetterVo inside) {
        return this.insert(
                "com.ningpai.customer.dao.InsideLetterMapper.deleteRead",
                inside);
    }

    @Override
    public int deleteByLetterIdCustId(Map<String, Object> paramMap) {
        return this
                .update("com.ningpai.customer.dao.InsideLetterMapper.deleteByletterIdCustId",
                        paramMap);
    }

    @Override
    public Long findInsideCount(Long customerId) {
        return this
                .selectOne(
                        "com.ningpai.customer.dao.InsideLetterMapper.findInsideCount",
                        customerId);
    }

    @Override
    public Long queryInsideCount(Long customerId) {
        return this
                .selectOne(
                        "com.ningpai.customer.dao.InsideLetterMapper.selectInsideCount",
                        customerId);
    }

}
