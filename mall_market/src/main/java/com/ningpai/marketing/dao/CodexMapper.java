/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.marketing.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.marketing.bean.Codex;

/**
 * 
 * @author ggn
 * 
 * @since 2014年3月21日上午17:14:54
 */
public interface CodexMapper {

    /**
     * 查询规则列表
     * 
     * @param paramMap
     *            {@link java.util.Map}
     * @return List
     */
    List<Object> selectCodexList(Map<String, Object> paramMap);

    /**
     * 查询规则总数
     * 
     * @param paramMap
     *            {@link java.util.Map}
     * @return int
     */
    int selectCodexListCount(Map<String, Object> paramMap);

    /**
     * 添加规则
     * 
     * @param codex
     *            {@link com.ningpai.marketing.bean.Codex}
     * @return int
     */
    int addCodex(Codex codex);

    /**
     * 查询所有规则
     * 
     * @return Codex {@link com.ningpai.marketing.bean.Codex}
     */
    List<Codex> selectCodexListUseBox();

    /**
     * 根据促销id的集合查询促销属于哪一种类型
     * 
     * @param marketingIds
     * @return
     */
    List<Codex> queryCodeByMarketingIds(List<Long> marketingIds);

    /**
     * 查询规则内容
     * 
     * @param codexId
     * @return Codex
     */
    Codex queryCodexDetail(Long codexId);

    /**
     * 根据分组id查询规则内容
     * 
     * @param codexId
     * @return Codex
     * @author NINGPAI-LIH
     */
    List<Codex> queryCodexListByParam(Long codexStatus);

}
