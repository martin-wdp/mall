/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.customer.dao;

import com.ningpai.customer.bean.InsideLetter;
import com.ningpai.customer.vo.InsideLetterVo;

import java.util.List;
import java.util.Map;

/**
 * 站内信接口
 *
 * */
public interface InsideLetterMapper {
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
     * 根据会员Id插入站内信
     * 
     * @param letter
     * @return
     */

    int insertNotices(List<InsideLetter> letter);

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
     * @return {@link List<Object>}
     */
    List<Object> queryInsideLetter(Map<String, Object> paramMap);

    /**
     * 查询站内信个数
     * 
     * @param customerId
     * @return 站内信个数
     */
    Long queryInsideCount(Long customerId);

    /**
     * 标记为已读
     */
    int readedLetter(InsideLetterVo inside);

    /**
     * 删除
     */
    int deleteLetter(Long relaId);

    /**
     * 判断是否是可读
     * */
    Long letterIsRead(Map<String, Object> paramMap);

    /**
     * 删除未读
     */
    int deleteLetterNo(InsideLetterVo inside);

    /**
     * 根据会员Id和letteId删除
     */
    int deleteByLetterIdCustId(Map<String, Object> paramMap);
    /**
     * 查询未查看的站内信个数
     *
     * @param customerId
     * @return 未查看站内信个数
     */
    Long findInsideCount(Long customerId);
}
