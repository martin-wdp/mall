/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.customer.service;

import java.util.Map;

import com.ningpai.customer.bean.InsideLetter;
import com.ningpai.customer.vo.InsideLetterVo;
import com.ningpai.util.PageBean;

/**
 * 站内信服务层接口
 *
 * */
public interface InsideLetterServiceMapper {
    /**
     * 删除站内信
     * 
     * @param letterId
     * @return 0 失败 1成功
     */
    int deleteByPrimaryKey(Long letterId);

    /**
     * 添加站内信
     * 
     * @param record
     * @return 0 失败 1成功
     */
    int insert(InsideLetter record);

    /**
     * 添加站内信
     * 
     * @param record
     * @return 0 失败 1成功
     */
    int insertSelective(InsideLetter record);

    /**
     * 根据会员Id添加站内信
     * 
     * @param record
     * @param customerIds
     * @return 0 失败 1成功
     */
    int insertNotices(InsideLetter record, Long[] customerIds);

    /**
     * 查询站内信
     * 
     * @param letterId
     * @return {@link InsideLetter}
     */
    InsideLetter selectByPrimaryKey(Long letterId);

    /**
     * 修改站内信
     * 
     * @param record
     * @return 0 失败 1成功
     */
    int updateByPrimaryKeySelective(InsideLetter record);

    /**
     * 修改站内信
     * 
     * @param record
     * @return 0 失败 1成功
     */
    int updateByPrimaryKey(InsideLetter record);

    /**
     * 查询全部站内信
     * 
     * @param paramMap
     *            装在查询的条件
     * @param pb
     *            根据条件查询得到的 分页对象
     * @return
     */
    PageBean queryInsideLetter(Map<String, Object> paramMap, PageBean pb);

    /**
     * 标记为已读
     */
    int readedLetter(InsideLetterVo inside);

    /**
     * 删除
     */
    int deleteLetter(Long relaId);

    /**
     * 是否已读
     */
    Long letterIsRead(Map<String, Object> paramMap);

    /**
     * 删除未读
     */
    int deleteLetterNo(InsideLetterVo inside);

    /**
     * 根据会员Id和letteId删除
     */
    int deleteByLetterIdCustId(Map<String, Object> paramMap);
}
